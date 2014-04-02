package com.huang.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huang.dao.ProductTypeDao;
import com.huang.domain.ProductType;

@SuppressWarnings("resource")
public class ProductTypeMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ProductTypeDao dao = (ProductTypeDao)context.getBean("dao");
		ProductType product = new ProductType();
		product.setName("sh");
		product.setNote("china");
		product.setVisible(false);
		dao.add(product);
	}
}
