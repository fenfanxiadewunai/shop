package com.huang.domain;

import java.util.ArrayList;
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
	private ProductTypeDao productTypeDao;

	@Test
    public void testAdd() {
		ProductType product = new ProductType();
		product.setName("sh3");
		product.setNote("china3");
		product.setVisible(false);
		productTypeDao.add(product);
    }
	
	@Test
    public void testGetById() {
		ProductType product = productTypeDao.getById(1);
		System.out.println(product);
    }
	
	@Test
    public void testFind() {
		List<ProductType> ret = productTypeDao.findByName("sh", 2, 1);
		for(ProductType pro : ret){
			System.out.println(pro);
		}
    }
	
	@Test
	public void testCount(){
		int count = productTypeDao.countByName("huang");
		System.out.println(count);
	}
	
	@Test
	public void testDelete(){
		productTypeDao.delete(1);
	}
	
	@Test
    public void testGetSubTypeids() {
		List<Integer> param = new ArrayList<Integer>();
		param.add(10);
		List<Integer> ret = productTypeDao.getSubTypeids(param);
		for(Integer pro : ret){
			System.out.println(pro);
		}
    }
	
	
	@Test
	public void testGetAllSubTypeids(){
		List<Integer> all = new ArrayList<Integer>();
		all.add(5);
		List<Integer> a = new ArrayList<Integer>();
		a.add(5);
		getAllSubTypeids(all, a);
		for(Integer i:all){
			System.out.println(i);
		}
	}
	
	@Test
	public void testGetFirstSubTypeidsWithNull(){
		List<ProductType> ret = productTypeDao.getFirstSubTypes(null);
		for(ProductType p:ret){
			System.out.println(p);
		}
	}
	
	@Test
	public void testGetFirstSubTypeidsWIthNotNull(){
		List<ProductType> ret = productTypeDao.getFirstSubTypes(5);
		for(ProductType p:ret){
			System.out.println(p);
		}
	}
	
	private void getAllSubTypeids(List<Integer>all,List<Integer> typeids){
		List<Integer> subtypeids = productTypeDao.getSubTypeids(typeids);
		if(subtypeids!=null&&subtypeids.size()>0){
			all.addAll(subtypeids);
			getAllSubTypeids(all, subtypeids);
		}
	}
	
	
	
}
