package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.GeneratedOrderIdDao;

@Repository("generatedOrderIdDao")
public class GeneratedOrderIdDaoImpl implements GeneratedOrderIdDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public void addOrderId() {
		dao.getSqlSession().update("com.huang.mapper.GeneratedOrderId.add");
	}
	
	@Override
	public int getOrderId() {
		int orderid = dao.getSqlSession().selectOne("com.huang.mapper.GeneratedOrderId.get");
		return orderid;
	}

}
