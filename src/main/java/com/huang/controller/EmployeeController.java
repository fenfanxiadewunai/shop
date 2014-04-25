package com.huang.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.huang.domain.privilege.Department;
import com.huang.domain.privilege.Employee;
import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.service.DepartmentService;
import com.huang.service.EmployeeService;
import com.huang.util.PageUtil;
import com.huang.util.Pagenation;
import com.huang.vo.EmployeeVO;


@Controller
@RequestMapping("/controller/employee")
public class EmployeeController {
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private DepartmentService departmentService;
	
	
	@RequestMapping("/addUI.do")
	public String toAddUI(Model model) {
		int count = departmentService.count();
		List<Department> ret = departmentService.find(count, 0);
		model.addAttribute("departments", ret);
		return "page/department/addemployee";
	}
	
	@RequestMapping("/queryUI.do")
	public String toQueryUI(Model model) {
		int count = departmentService.count();
		List<Department> ret = departmentService.find(count, 0);
		model.addAttribute("departments", ret);
		return "page/department/query";
	}

	@RequestMapping("/add.do")
	public String toAdd(Model model,EmployeeVO evo,HttpServletRequest request) throws Exception{
		
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;

		CommonsMultipartFile cfile = (CommonsMultipartFile) rm
				.getFile("picture");
		
		Employee emp = new Employee(evo);
		
		if(evo.getDepartmentid()!=null&&!evo.getDepartmentid().equals("")){
			emp.setDepartment(new Department(evo.getDepartmentid()));
		}
		
		if (!cfile.isEmpty() && cfile.getSize() > 0) {
			byte[] bfile = cfile.getBytes();
			String suffix = cfile.getOriginalFilename().substring(
					cfile.getOriginalFilename().lastIndexOf("."));
			String imagename = UUID.randomUUID() + suffix;
			String virpathdir = "/images/employee/" + emp.getUsername();
			String realpathdir = request.getSession().getServletContext()
					.getRealPath(virpathdir);
			File savedir = new File(realpathdir);
			if (!savedir.exists())
				savedir.mkdirs();
			OutputStream out = new FileOutputStream(new File(realpathdir,
					imagename));
			out.write(bfile);
			out.flush();
			out.close();
			emp.setImageName(imagename);
		}
		employeeService.add(emp);
		return "redirect:list.do";
	}

	
	@RequestMapping("/list.do")
	public String toList(Model model,String currentPage,EmployeeVO evo){
		int pageSize = 3;
		if (currentPage == null || currentPage.equals(""))
			currentPage = "1";
		int cPage = Integer.parseInt(currentPage);

		int totalCount = 0;
		Pagenation page = null;
		List<Employee> result = null;
		
		if("true".equals(evo.getQuery())){
			HashMap<String,Object> map = new HashMap<String,Object>();
			if(evo.getUsername()!=null&&!evo.getUsername().equals("")){
				map.put("username", "%"+evo.getUsername()+"%");
			}
			if(evo.getRealname()!=null&&!evo.getRealname().equals("")){
				map.put("realname", "%"+evo.getRealname()+"%");
			}
			if(evo.getDepartmentid()!=null&&!evo.getDepartmentid().equals("")){
				map.put("departmentid", evo.getDepartmentid());
			}
			
			totalCount = employeeService.countByDynamic(map);
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			map.put("pageSize", page.getPageSize());
			map.put("pageOffset", page.getBeginIndex());
			result = employeeService.findByDynamic(map);
		}else{
			totalCount = employeeService.count(null);
			page = PageUtil.createPage(pageSize, cPage, totalCount);
			result = employeeService.find(page.getPageSize(),page.getBeginIndex());
		}
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		return "page/department/employeelist";
	}
	
	@RequestMapping("/checkexist.do")
	public String toCheck(Model model,String username){
		boolean exist = employeeService.exist(username);
		model.addAttribute("exist", exist);
		return "page/department/usernameIsExist";
	}
	
	@RequestMapping("/privilegeGroupSetUI.do")
	public String toSetPrivilegeGroupUI(Model model,String username){
		Employee employee = employeeService.getById(username);
		List<PrivilegeGroup> allgroups = employeeService.findAllPrivilegeGroupList();
		model.addAttribute("itsgroups", employee.getGroups());
		model.addAttribute("allgroups", allgroups);
		model.addAttribute("username", username);
		return "page/department/privilegeSet";
	}
	
	@RequestMapping("/privilegeGroupSet.do")
	public String toSetPrivilegeGroup(Model model,String username,String[] groups){
		if(username!=null&&!username.equals("")){
			List<String> groupids = Arrays.asList(groups);
			employeeService.updatePrivilegeGroup(username, groupids);
		}
		return "redirect:list.do";
	}
	
	
}
