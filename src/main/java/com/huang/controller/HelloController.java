package com.huang.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hello")
public class HelloController {
	
//	@Resource 
//	private ProductTypeDao dao;

	@RequestMapping("/hello.do")
	public String hello(){
		return "index";
	}
	
	@RequestMapping("/sayHi.do")
	public String toName(Model model){
		model.addAttribute("name", "huang");
		return "vm/main";
	}
	
	@RequestMapping("/sayHello.do")
	public String toHello(HttpServletRequest request){
		request.setAttribute("now", new Date());
		return "vm/main";
	}
	
//	@RequestMapping("/sayHello2.do")
//	public String toHello2(Model model,HttpServletRequest request){
//		request.setAttribute("now", new Date());
//		model.addAttribute("request", request);
//		System.out.println(request.getLocalName());
//		System.out.println(request.getLocalPort());
//		return "vm/main";
//	}
	
	
}
