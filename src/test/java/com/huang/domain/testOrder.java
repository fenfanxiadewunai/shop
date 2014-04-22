package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.book.Order;
import com.huang.service.OrderService;



@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testOrder {

	
	@Resource
	private OrderService orderService;
	
	@Test
	public void testOrderId(){
		Order order = orderService.createOrder(null, null);
		System.out.println(order.getOrderid());
	}
}
