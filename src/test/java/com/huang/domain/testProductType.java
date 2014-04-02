package com.huang.domain;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.dao.ProductTypeDao;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})   
public class testProductType extends AbstractJUnit4SpringContextTests{
	
	@Resource 
	private ProductTypeDao dao;

	@Test
    public void testAdd() {
		ProductType product = new ProductType();
		product.setName("sh3");
		product.setNote("china3");
		product.setVisible(false);
		dao.add(product);
    }
	
	@Test
    public void testGetById() {
		ProductType product = dao.getById(1);
		System.out.println(product);
    }
	
	@Test
    public void testFind() {
		List<ProductType> ret = dao.findByName("sh", 2, 1);
		for(ProductType pro : ret){
			System.out.println(pro);
		}
    }
	
	@Test
	public void testCount(){
		int count = dao.countByName("huang");
		System.out.println(count);
	}
	
	@Test
	public void testDelete(){
		dao.delete(1);
	}
	
	
}
