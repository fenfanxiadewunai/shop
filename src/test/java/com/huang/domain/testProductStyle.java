package com.huang.domain;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.ProductService;
import com.huang.service.ProductStyleService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"}) 
public class testProductStyle {
	
	@Resource 
	private ProductService productService;
	
	@Resource 
	private ProductStyleService productStyleService;
	
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
		ProductStyle productStyle = new ProductStyle("红色", "xxx.gif");
		product.addProductStyle(productStyle);  // style 依托style
		product.setType(new ProductType(28));     //  肯定首先存在，产品类别
		product.setWeight(50);
		int id = productService.add(product);
		productStyleService.add(productStyle);
		
		System.out.println(id);
		
    }
	
	@Test
    public void testGetById() {
		ProductStyle pro = productStyleService.getById(6);
		System.out.println(pro);
		
    }
	
	@Test
    public void testFind() {
		List<ProductStyle> ret = productStyleService.find(12);
		for(ProductStyle p:ret){
			System.out.println(p);
		}
		
    }
	
	@Test
    public void testBatchSetVisibleFalse() {
		int[] productstyleids = new int[]{1,2};
		productStyleService.setVisibleStatus(productstyleids, false);
    }
	
	@Test
    public void testBatchSetVisibleTure() {
		int[] productstyleids = new int[]{1,2};
		productStyleService.setVisibleStatus(productstyleids, true);
    }
	
	
	

}
