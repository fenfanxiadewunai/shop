package com.huang.dao;

import com.huang.domain.book.OrderItem;

public interface OrderItemDao {
	public int add(OrderItem orderItem);
	
	public void update(OrderItem orderItem);
	
	public OrderItem getById(int id);
	
	public void delete(int id);
}
