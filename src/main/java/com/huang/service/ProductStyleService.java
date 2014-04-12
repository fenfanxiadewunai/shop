package com.huang.service;

import java.util.List;

import com.huang.domain.ProductStyle;

public interface ProductStyleService {
	
	public void add(ProductStyle productStyle);

	public ProductStyle getById(int id);
	
	
	public void update(ProductStyle productStyle);
	
	public List<ProductStyle> find(int productid);
	
	public void setVisibleStatus(int[] productstyleids,boolean status);

}
