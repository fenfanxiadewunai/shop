package com.huang.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.service.PrivilegeGroupService;
import com.huang.service.SystemPrivilegeService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;
import com.huang.vo.EmployeeVO;


@Controller
@RequestMapping("/controller/privilegegroup")
public class PrivilegeGroupController {

	@Resource
	private PrivilegeGroupService privilegeGroupService;
	
	@Resource
	private SystemPrivilegeService systemPrivilegeService; 
	
	@RequestMapping("/addUI.do")
	public String toAddUI(Model model) {
		List<SystemPrivilege> privileges =systemPrivilegeService.findAll();
		model.addAttribute("privileges", privileges);
		return "page/department/addprivilegegroup";
	}
	
	@RequestMapping("/editUI.do")
	public String toEditUI(Model model,String groupid) {
		PrivilegeGroup group = privilegeGroupService.getPrivilegeGroupById(groupid);
		List<SystemPrivilege> groupprivileges = new ArrayList<SystemPrivilege>(group.getPrivileges());
		List<SystemPrivilege> allprivileges =systemPrivilegeService.findAll();
		model.addAttribute("groupprivileges", groupprivileges);
		model.addAttribute("allprivileges", allprivileges);
		model.addAttribute("group", group);
		return "page/department/editprivilegegroup";
	}
	
	@RequestMapping("/delete.do")
	public String toDelete(String groupid) {
		privilegeGroupService.deletePrivilegeGroup(groupid);
		return "redirect:list.do";
	}
	
	@RequestMapping("/edit.do")
	public String toEdit(Model model,String groupid,String name,String[]privileges) {
		if(name!=null&&!name.equals("")){
			PrivilegeGroup group = privilegeGroupService.getPrivilegeGroupById(groupid);
			group.setName(name);
			Set<SystemPrivilege> privilegesSet = new HashSet<SystemPrivilege>();
			for(int i = 0;i<privileges.length;i++){
				privilegesSet.add(new SystemPrivilege(Integer.parseInt(privileges[i])));
			}
			group.setPrivileges(privilegesSet);
			privilegeGroupService.updatePrivilegeGroup(group);
		}
		return "redirect:list.do";
	}
	
	@RequestMapping("/add.do")
	public String toAdd(Model model,String name,String[]privileges){
		if(name!=null&&!name.equals("")){
			PrivilegeGroup group = new PrivilegeGroup(name);
			Set<SystemPrivilege> privilegesSet = new HashSet<SystemPrivilege>();
			for(int i = 0;i<privileges.length;i++){
				privilegesSet.add(new SystemPrivilege(Integer.parseInt(privileges[i])));
			}
			group.setPrivileges(privilegesSet);
			privilegeGroupService.addProvilegeGroup(group);
			return "redirect:list.do";
		}
		return "redirect:addUI.do";
	}

	
	@RequestMapping("/list.do")
	public String toList(Model model,String currentPage,EmployeeVO evo){
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);
		int totalCount = privilegeGroupService.countPrivilegeGroup();
		Pagenation page = PageUtil.createPage(pageSize, cPage, totalCount);
		List<PrivilegeGroup> result = privilegeGroupService.findPrivilegeGroup(page.getPageSize(),page.getBeginIndex());
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		return "page/department/privilegegrouplist";
	}
	
}
