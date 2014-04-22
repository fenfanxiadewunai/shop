package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.OrderDao;
import com.huang.domain.book.Order;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public String add(Order order) {
		dao.getSqlSession().insert("com.huang.mapper.Order.add", order);
		return order.getOrderid();
	}

	@Override
	public void update(Order order) {
		dao.getSqlSession().update("com.huang.mapper.Order.update", order);
	}

	@Override
	public Order getById(int id) {
		return (Order)dao.getSqlSession().selectOne("com.huang.mapper.Order.getById", id);
	}

	@Override
	public void delete(int id) {
		
	}

}
