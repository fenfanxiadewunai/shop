package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderContactInfoDao;
import com.huang.domain.book.OrderContactInfo;

@Repository("orderContactInfoDao")
public class OrderContactInfoDaoImpl implements OrderContactInfoDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public int add(OrderContactInfo orderContactInfo) {
		dao.getSqlSession().insert("com.huang.mapper.OrderContactInfo.add", orderContactInfo);
		return orderContactInfo.getContactid();
	}

	@Override
	public void update(OrderContactInfo orderContactInfo) {
		dao.getSqlSession().update("com.huang.mapper.OrderContactInfo.update", orderContactInfo);		
	}

	@Override
	public OrderContactInfo getById(int id) {
		return (OrderContactInfo)dao.getSqlSession().selectOne("com.huang.mapper.OrderContactInfo.getById", id);
	}

	@Override
	public void delete(int id) {
		
	}

}
