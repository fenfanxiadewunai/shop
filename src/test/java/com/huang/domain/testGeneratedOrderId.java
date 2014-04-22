package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.GeneratedOrderIdService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testGeneratedOrderId {
	
	@Resource
	private GeneratedOrderIdService generatedOrderIdService;
	
	@Test
	public void testAddOrderId(){
		int orderid = generatedOrderIdService.buildOrderId();
		System.out.println(orderid);
	}
}
