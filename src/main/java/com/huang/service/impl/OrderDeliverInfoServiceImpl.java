package com.huang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderDeliverInfoDao;
import com.huang.domain.book.OrderDeliverInfo;
import com.huang.service.OrderDeliverInfoService;


@Repository("orderDeliverInfoService")
public class OrderDeliverInfoServiceImpl implements OrderDeliverInfoService{

	
	@Resource
	private OrderDeliverInfoDao orderDeliverInfoDao;
	
	@Override
	public int add(OrderDeliverInfo orderDeliverInfo) {
		return orderDeliverInfoDao.add(orderDeliverInfo);
	}

	@Override
	public void update(OrderDeliverInfo orderDeliverInfo) {
		orderDeliverInfoDao.update(orderDeliverInfo);
	}

	@Override
	public OrderDeliverInfo getById(int id) {
		return orderDeliverInfoDao.getById(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
