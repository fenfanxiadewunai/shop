package com.huang.spring.test;

public class OrderServiceImpl implements OrderService {

	@NewResource
	private OrderDao orderDao;

	private String id;

	public OrderServiceImpl() {
	}

	@Override
	public void save() {
		orderDao.insert();
	}

	
	

}
