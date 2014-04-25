package com.huang.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.privilege.Department;
import com.huang.domain.privilege.Permission;
import com.huang.service.DepartmentService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;

@Controller
@RequestMapping("/controller/department")
public class DepartmentController {
	@Resource
	private DepartmentService departmentService;
	
	@RequestMapping("/list.do")
	@Permission(module="department",privilege="view")
	public String toList(Model model, String currentPage) {
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);

		int totalCount = departmentService.count();
		Pagenation page = PageUtil.createPage(pageSize, cPage, totalCount);

		List<Department> result = departmentService.find(page.getPageSize(),
				page.getBeginIndex());
		model.addAttribute("result", result);
		model.addAttribute("page", page);

		return "page/department/departmentlist";
	}
	
	@RequestMapping("/addUI.do")
	@Permission(module="department",privilege="insert")
	public String toAddUI() {
		return "page/department/adddepartment";
	}

	@RequestMapping("/add.do")
	@Permission(module="department",privilege="insert")
	public String toAdd(Model model,String name){
		departmentService.add(name);
		return "redirect:list.do";
	}
	
	@RequestMapping("/delete.do")
	@Permission(module="department", privilege="delete")
	public String toDelete(Model model,String departmentid){
		departmentService.delete(departmentid);
		return "redirect:list.do";
	}
	
	@RequestMapping("/editUI.do")
	@Permission(module="department", privilege="update")
	public String toEditUI(Model model, String departmentid) {
		Department department = departmentService.getById(departmentid);
		model.addAttribute("department", department);
		return "page/department/editdepartment";
	}

	@RequestMapping("/edit.do")
	@Permission(module="department", privilege="update")
	public String toEdit(Model model,String departmentid,String name) throws Exception{
		Department department = new Department();
		department.setDepartmentid(departmentid);
		department.setName(name);
		departmentService.update(department);
		return "redirect:list.do";
	}
	
}
