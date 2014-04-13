package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.user.ContactInfo;
import com.huang.service.ContactInfoService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testContactInfo {
	
	@Resource 
	private ContactInfoService contactInfoService;

	@Test
    public void testAdd() {
		ContactInfo con = new ContactInfo("shanghai","200072","18817350871","18817350871");
		int id = contactInfoService.add(con);
		System.out.println(id);
		
    }
	
	@Test
    public void testUpdate() {
		ContactInfo con = new ContactInfo("shanghai","200072","18817350871","18817350871");
		int id = contactInfoService.add(con);
		System.out.println(id);
		
		ContactInfo con2 = contactInfoService.getById(id);
		System.out.println(con2);
		
		con2.setAddress("hangzhou");
		contactInfoService.update(con2);
		
		ContactInfo con3 = contactInfoService.getById(id);
		System.out.println(con3);
		
    }

}
