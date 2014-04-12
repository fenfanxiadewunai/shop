package com.huang.service;

import java.util.HashMap;
import java.util.List;

import com.huang.domain.Brand;
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
	
	public List<ProductInfo> findwithtypeids(List<Integer> typeids);
	
	public List<ProductInfo> findwithtypeids(List<Integer> typeids,int num);
	
	
	public List<Brand> getBrandsByProductTypeId(Integer typeid);

	List<Brand> getBrandsByProductTypeIds(List<Integer> typeids);

	List<ProductInfo> getTopSellProduct(Integer typeid, int num);

	List<ProductInfo> getTopSellProduct(int num);
	
	List<ProductInfo> getListProductInfoByIds(List<Integer> ids, int num);

	ProductInfo getByIdWithProductStyle(int id);
	
}
