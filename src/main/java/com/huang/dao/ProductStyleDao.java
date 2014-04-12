package com.huang.dao;

import java.util.List;

import com.huang.domain.ProductStyle;


public interface ProductStyleDao {
	
	public void add(ProductStyle productStyle);

	public List<ProductStyle> find(int productid);
	
	public void update(ProductStyle productStyle);
	
	public ProductStyle getById(int id);
	
	public void setVisibleStatus(int[] productstyleids,boolean status);

}
