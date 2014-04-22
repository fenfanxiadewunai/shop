package com.huang.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.service.EmployeeService;
import com.huang.vo.EmployeeVO;


@Controller
@RequestMapping("/employee")
public class EmployeeManageController {
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping("/login.do")
	public String toLogin(Model model,EmployeeVO evo,HttpSession session){
		
		if(evo.getUsername()!=null&&!evo.getUsername().equals("")&&evo.getPassword()!=null&&!evo.getPassword().equals("")){
			if(employeeService.validate(evo.getUsername(), evo.getPassword())){
				session.setAttribute("employee", employeeService.getById(evo.getUsername()));
				return "redirect:/center/main.do";
			}
			model.addAttribute("message", "用户名或密码有误");
		}
		
		return "page/department/login";
	}
	
	@RequestMapping("/logout.do")
	public String toLogout(HttpSession session){
		session.removeAttribute("employee");
		return "redirect:/employee/login.do";
	}

}
