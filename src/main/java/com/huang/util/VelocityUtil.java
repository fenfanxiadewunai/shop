package com.huang.util;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

public class VelocityUtil {

	@Resource
	private  VelocityConfigurer velocityConfigurer;

	public  VelocityEngine getVelocityEngine(){
		return velocityConfigurer.getVelocityEngine();
	}

	public VelocityConfigurer getVelocityConfigurer() {
		return velocityConfigurer;
	}

	public void setVelocityConfigurer(VelocityConfigurer velocityConfigurer) {
		this.velocityConfigurer = velocityConfigurer;
	}
	
	
} 
