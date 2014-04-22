package com.huang.dao;

import com.huang.domain.book.OrderDeliverInfo;

public interface OrderDeliverInfoDao {
	
	public int add(OrderDeliverInfo orderDeliverInfo);
	
	public void update(OrderDeliverInfo orderDeliverInfo);
	
	public OrderDeliverInfo getById(int id);

	public void delete(int id);
}
