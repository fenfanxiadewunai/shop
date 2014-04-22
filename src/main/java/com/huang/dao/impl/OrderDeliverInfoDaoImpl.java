package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderDeliverInfoDao;
import com.huang.domain.book.OrderDeliverInfo;


@Repository("orderDeliverInfoDao")
public class OrderDeliverInfoDaoImpl implements OrderDeliverInfoDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public int add(OrderDeliverInfo orderDeliverInfo) {
		dao.getSqlSession().insert("com.huang.mapper.OrderDeliverInfo.add", orderDeliverInfo);
		return orderDeliverInfo.getDeliverid();
	}

	@Override
	public void update(OrderDeliverInfo orderDeliverInfo) {
		dao.getSqlSession().update("com.huang.mapper.OrderDeliverInfo.update", orderDeliverInfo);
	}

	@Override
	public OrderDeliverInfo getById(int id) {
		return (OrderDeliverInfo)dao.getSqlSession().selectOne("com.huang.mapper.OrderDeliverInfo.getById", id);
	}

	@Override
	public void delete(int id) {
		
	}

}
