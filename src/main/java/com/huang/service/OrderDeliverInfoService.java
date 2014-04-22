package com.huang.service;

import com.huang.domain.book.OrderDeliverInfo;

public interface OrderDeliverInfoService {

	
	public int add(OrderDeliverInfo orderDeliverInfo);
	
	public void update(OrderDeliverInfo orderDeliverInfo);
	
	public OrderDeliverInfo getById(int id);

	public void delete(int id);
}
