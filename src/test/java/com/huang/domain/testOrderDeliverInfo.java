package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.book.DeliverWay;
import com.huang.domain.book.OrderDeliverInfo;
import com.huang.domain.user.Gender;
import com.huang.service.OrderDeliverInfoService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testOrderDeliverInfo {
	
	@Resource
	private OrderDeliverInfoService orderDeliverInfoService;
	
	@Test
	public void testAddOrderId(){
		
		OrderDeliverInfo info = new OrderDeliverInfo();
		info.setAddress("上海");
		info.setDeliverWay(DeliverWay.EMS);
		info.setEmail("357145481@qq.com");
		info.setGender(Gender.MAN);
		info.setMobile("18817350871");
		info.setPostcode("200072");
		info.setRecipients("huang");
		info.setRequirement("必须快");
		info.setTel("");
		
		int id = orderDeliverInfoService.add(info);
		
		System.out.println(id);
		
		
		OrderDeliverInfo info2 = orderDeliverInfoService.getById(id);
		System.out.println(info2);
		
		info2.setTel("123");
		orderDeliverInfoService.update(info2);
		
		
		OrderDeliverInfo info3 = orderDeliverInfoService.getById(id);
		
		System.out.println(info3);
	}
}
