package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.book.OrderContactInfo;
import com.huang.domain.user.Gender;
import com.huang.service.OrderContactInfoService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testOrderContactInfo {
	
	@Resource
	private OrderContactInfoService orderContactInfoService;
	
	@Test
	public void testAddOrderId(){
		
		OrderContactInfo info = new OrderContactInfo();
		info.setAddress("上海");
		info.setBuyerName("huang");
		info.setEmail("357145481@qq.com");
		info.setGender(Gender.MAN);
		info.setMobile("18817350871");
		info.setPostcode("200072");
		info.setTel("");
		int id = orderContactInfoService.add(info);
		
		System.out.println(id);
		
		
		OrderContactInfo info2 = orderContactInfoService.getById(id);
		System.out.println(info2);
		
		info2.setTel("123");
		orderContactInfoService.update(info2);
		
		
		OrderContactInfo info3 = orderContactInfoService.getById(id);
		
		System.out.println(info3);
	}
}
