package com.huang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductDao;
import com.huang.dao.ProductStyleDao;
import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.service.ProductService;

@Repository("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductStyleDao productStyleDao;
	
	@Resource
	private ProductDao productDao;
	@Override
	public int add(ProductInfo product) {
		int id = productDao.add(product);
		Set<ProductStyle> pros = product.getStyles();
		for(ProductStyle p: pros){
			productStyleDao.add(p);
		}
		return id; 
	}

	@Override
	public ProductInfo getById(int id) {
		return productDao.getById(id);
	}

	@Override
	public int countAll() {
		return productDao.countAll();
	}

	@Override
	public List<ProductInfo> find(int pageSize, int pageOffset) {
		return productDao.find(pageSize, pageOffset);
	}

	@Override
	public void update(ProductInfo product) {
		productDao.update(product);
	}

	@Override
	public int countByDynamic(HashMap<String, Object> map) {
		return productDao.countByDynamic(map);
	}

	@Override
	public List<ProductInfo> findByDynamic(HashMap<String, Object> map) {
		return productDao.findByDynamic(map);
	}

	@Override
	public void setVisibleStatus(int[] productids, boolean status) {
		productDao.setVisibleStatus(productids, status);
	}

	@Override
	public void setCommendStatus(int[] productids, boolean status) {
		productDao.setCommendStatus(productids, status);
	}

}
