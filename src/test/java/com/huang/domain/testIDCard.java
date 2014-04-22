package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.huang.domain.privilege.IDCard;
import com.huang.service.IDCardService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testIDCard {
	@Resource 
	private IDCardService cardService;

	@Test
    public void testGet() {
		IDCard card = new IDCard("410883198903141515", "上海");
		cardService.add(card);
		System.out.println(card);
		
		IDCard card2 = cardService.getById(card.getId());
		System.out.println(card2);
		
    }
	
}
