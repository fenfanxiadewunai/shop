package com.huang.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.domain.ProductType;
import com.huang.service.ProductTypeService;


@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource 
	private ProductTypeService productTypeService;

	@RequestMapping("/hello.do")
	public String hello(){
		ProductType product = productTypeService.getById(3);
		System.out.println(product);
		return "index";
	}
	
	@RequestMapping("/hh.huang")
	public String toName(String name){
		System.out.println(name);
		return "index";
	}
	
	
}
