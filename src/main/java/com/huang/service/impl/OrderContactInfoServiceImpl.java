package com.huang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderContactInfoDao;
import com.huang.domain.book.OrderContactInfo;
import com.huang.service.OrderContactInfoService;

@Repository("orderContactInfoService")
public class OrderContactInfoServiceImpl implements OrderContactInfoService{
	
	@Resource
	private OrderContactInfoDao orderContactInfoDao;

	@Override
	public int add(OrderContactInfo orderContactInfo) {
		return orderContactInfoDao.add(orderContactInfo);
	}

	@Override
	public void update(OrderContactInfo orderContactInfo) {
		orderContactInfoDao.update(orderContactInfo);
	}

	@Override
	public OrderContactInfo getById(int id) {
		return orderContactInfoDao.getById(id);
	}

	@Override
	public void delete(int id) {
		
	}
}
