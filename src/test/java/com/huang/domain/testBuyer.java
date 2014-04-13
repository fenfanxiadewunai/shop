package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.user.Buyer;
import com.huang.service.BuyerService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testBuyer {
	
	@Resource 
	private BuyerService buyerService;

	@Test
    public void testAdd() {
		Buyer buyer = new Buyer();
		buyer.setUsername("huang2");
		buyer.setEmail("zhileihuang@126.com");
		buyer.setPassword("123456");
		buyerService.add(buyer);
		
		
		boolean exist = buyerService.exist("huang");
		System.out.println(exist);
    }
	
	@Test
    public void testCheckUser() {
		boolean ret = buyerService.checkUser("huang123", "123456");
		System.out.println(ret);
    }
	

}
