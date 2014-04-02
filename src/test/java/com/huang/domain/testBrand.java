package com.huang.domain;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.BrandService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testBrand {
	
	@Resource 
	private BrandService brandService;

	@Test
    public void testAdd() {
		Brand brand = new Brand(UUID.randomUUID().toString(), "222", "huang2.jpg");
		brandService.add(brand);
		
    }
	
	@Test
    public void testGetById() {
		Brand brand = brandService.getByCode("50f651f2-5747-4829-bd22-153c9c282c38");
		System.out.println(brand);
    }

}
