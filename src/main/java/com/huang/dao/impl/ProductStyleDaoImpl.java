package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductStyleDao;
import com.huang.domain.ProductStyle;


@Repository("productStyleDao")
public class ProductStyleDaoImpl implements ProductStyleDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public List<ProductStyle> find(int productid) {
		
		List<ProductStyle> ret = dao.getSqlSession().selectList("com.huang.mapper.ProductStyle.find", productid);
		
		return ret;
	}

	@Override
	public void add(ProductStyle productStyle) {
		dao.getSqlSession().insert("com.huang.mapper.ProductStyle.add", productStyle);
	}

	@Override
	public void update(ProductStyle productStyle) {
		dao.getSqlSession().update("com.huang.mapper.ProductStyle.update", productStyle);
	}

	@Override
	public ProductStyle getById(int id) {
		return (ProductStyle)dao.getSqlSession().selectOne("com.huang.mapper.ProductStyle.getById", id);
	}

	@Override
	public void setVisibleStatus(int[] productstyleids, boolean status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("productstyleids", productstyleids);
		params.put("status", status);
		dao.getSqlSession().update("com.huang.mapper.ProductStyle.setvisible", params);	
	}
	
	

}
