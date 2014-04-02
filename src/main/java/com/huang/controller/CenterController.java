package com.huang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/center")
public class CenterController {

	@RequestMapping("/main.do")
	public String toMain(){
		return "page/controlcenter/default";
	}
	
	@RequestMapping("/top.do")
	public String toTop(){
		return "page/controlcenter/top";
	}
	
	@RequestMapping("/left.do")
	public String toLeft(){
		return "page/controlcenter/left";
	}
	
	@RequestMapping("/right.do")
	public String toRight(){
		return "page/controlcenter/right";
	}
	
	@RequestMapping("/end.do")
	public String toEnd(){
		return "page/controlcenter/end";
	}
	
	
	
	
}
