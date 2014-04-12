package com.huang.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.ProductService;
import com.huang.service.ProductTypeService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testProduct {
	@Resource 
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;

	@Test
    public void testAdd() {
		ProductInfo product = new ProductInfo();
		product.setName("足球");
		product.setBaseprice(100f);
		product.setBrand(new Brand("1eef8d4c-f030-486e-8f10-46486ab57550"));    //肯定先存在
		product.setCode("UI002");
		product.setDescription("好产品");
		product.setMarketprice(600f);
		product.setModel("T60");
		product.setSellprice(300f);
		product.setSexrequest(Sex.NONE);
		product.addProductStyle(new ProductStyle("红色","xxx.gif"));  // style 依托style
		product.setType(new ProductType(28));     //  肯定首先存在，产品类别
		product.setWeight(50);
		int id = productService.add(product);
		
		System.out.println(id);
		
    }
	
	@Test
    public void testGetById() {
		ProductInfo product = productService.getById(1);
		System.out.println(product);
    }
	
	@Test
    public void testFind() {
		List<ProductInfo> list = productService.find(3, 0);
		for(ProductInfo p:list){
			System.out.println(p);
		}
    }
	
	@Test
    public void testCountDynamic() {
		String name = "足球";
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("name", "%"+name+"%");
		int totalCount = productService.countByDynamic(map);
		System.out.println(totalCount);
    }
	
	
	@Test
    public void testFindDynamic() {
		String name = "足球";
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("startbaseprice", 101);
		map.put("endbaseprice", 125);
		map.put("name", "%"+name+"%");
		int count = productService.countByDynamic(map);
		System.out.println(count);
		map.put("pageOffset", 0);
		map.put("pageSize", count);
		List<ProductInfo> results = productService.findByDynamic(map);
		for(ProductInfo p:results){
			System.out.println(p);
		}
    }
	
	@Test
    public void testBatchSetVisibleFalse() {
		int[] productids = new int[]{1,2};
		productService.setVisibleStatus(productids, false);
    }
	
	@Test
    public void testBatchSetVisibleTure() {
		int[] productids = new int[]{1,2};
		productService.setVisibleStatus(productids, true);
    }
	
	@Test
    public void testBatchSetCommendFalse() {
		int[] productids = new int[]{1,2};
		productService.setCommendStatus(productids, false);
    }
	
	@Test
    public void testBatchSetCommendTure() {
		int[] productids = new int[]{1,2};
		productService.setCommendStatus(productids, true);
    }
	
	@Test
    public void testFindwithtypeids() {
		List<Integer> alltypeids = productTypeService.getAllSubTypeids(5);
		List<ProductInfo> ret = productService.findwithtypeids(alltypeids,4);
		for(ProductInfo p:ret){
			System.out.println(p);
		}
    }
	
	@Test
	public void testGetBrandsByProductTypeId(){
		List<Brand> ret = productService.getBrandsByProductTypeId(28);
		for(Brand b:ret){
			System.out.println(b);
		}
	}
	
	@Test
	public void testGetBrandsByProductTypeIds(){
		List<Integer> typeids = new ArrayList<Integer>();
		typeids.add(5);
		typeids.add(9);
		typeids.add(10);
		typeids.add(11);
		typeids.add(12);
		typeids.add(28);
		List<Brand> ret = productService.getBrandsByProductTypeIds(typeids);
		for(Brand b:ret){
			System.out.println(b);
		}
	}
	
	@Test
	public void testGetTopSellProduct(){
		int typeid = 5;
		List<ProductInfo> ret = productService.getTopSellProduct(typeid, 10);
		for(ProductInfo b:ret){
			System.out.println(b);
		}
	}
	
	@Test
	public void testGetTopSellProductWithNull(){
		List<ProductInfo> ret = productService.getTopSellProduct(null, 10);
		for(ProductInfo b:ret){
			System.out.println(b);
		}
	}
}
