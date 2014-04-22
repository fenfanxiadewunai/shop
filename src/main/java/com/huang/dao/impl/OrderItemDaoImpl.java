package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderItemDao;
import com.huang.domain.book.OrderItem;


@Repository("orderItemDao")
public class OrderItemDaoImpl implements OrderItemDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public int add(OrderItem orderItem) {
		return 0;
	}

	@Override
	public void update(OrderItem orderItem) {
		
	}

	@Override
	public OrderItem getById(int id) {
		return null;
	}

	@Override
	public void delete(int id) {
		
	}

}
