package com.huang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductStyleDao;
import com.huang.domain.ProductStyle;
import com.huang.service.ProductStyleService;

@Repository("productStyleService")
public class ProductStyleServiceImpl implements ProductStyleService{
	

	@Resource
	private ProductStyleDao productStyleDao;

	@Override
	public void add(ProductStyle productStyle) {
		productStyleDao.add(productStyle);
	}

	@Override
	public ProductStyle getById(int id) {
		return productStyleDao.getById(id);
	}

	@Override
	public void update(ProductStyle productStyle) {
		productStyleDao.update(productStyle);
	}

	@Override
	public List<ProductStyle> find(int productid) {
		return productStyleDao.find(productid);
	}

	@Override
	public void setVisibleStatus(int[] productstyleids, boolean status) {
		productStyleDao.setVisibleStatus(productstyleids, status);
	}

}
