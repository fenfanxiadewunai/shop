package com.huang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huang.aware.IUserAware;
import com.huang.domain.User;

@Controller
@RequestMapping("/user")
public class GreetingController implements IUserAware{
	
	private User user;
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	@RequestMapping("hello.do")
	public void hello(){
		System.out.println(user.getUserName());
	}

}
