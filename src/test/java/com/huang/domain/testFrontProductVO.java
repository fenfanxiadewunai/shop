package com.huang.domain;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.service.ProductService;
import com.huang.vo.FrontProductVO;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testFrontProductVO {
	
	@Resource
	private ProductService productService;
	
	@Test
    public void testByDynamic() {
		FrontProductVO pvo = new FrontProductVO();
		pvo.setSort(null);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.putAll(pvo.getSortParam());
		map.put("visible", true);
		int count = productService.countByDynamic(map);
		System.out.println(count);
		map.put("pageSize", count);
		map.put("pageOffset", 0);
		List<ProductInfo> ret = productService.findByDynamic(map);
		for(ProductInfo p:ret){
			System.out.println(p);
		}
    }
	
	@Test
    public void testByDynamicWithTypeid() {
		FrontProductVO pvo = new FrontProductVO();
		pvo.setSort("sellpricedesc");
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.putAll(pvo.getSortParam());
		map.put("visible", true);
		map.put("typeid", 28);
		int count = productService.countByDynamic(map);
		System.out.println(count);
		map.put("pageSize", count);
		map.put("pageOffset", 0);
		List<ProductInfo> ret = productService.findByDynamic(map);
		for(ProductInfo p:ret){
			System.out.println(p);
		}
    }
}
