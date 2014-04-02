package com.huang.service;

import java.util.HashMap;
import java.util.List;

import com.huang.domain.ProductInfo;

public interface ProductService {
	
	public int add(ProductInfo product);

	public ProductInfo getById(int id);
	
	public int countAll();
	
	public List<ProductInfo> find(int pageSize,int pageOffset);
	
	public void update(ProductInfo product);
	
	public int countByDynamic(HashMap<String,Object> map);
	
	public List<ProductInfo> findByDynamic(HashMap<String,Object> map);
	
	public void setVisibleStatus(int[] productids,boolean status);
	
	public void setCommendStatus(int[] productids,boolean status);
}
