package com.huang.dao;

import com.huang.domain.book.Order;

public interface OrderDao {
	public String add(Order order);
	
	public void update(Order order);
	
	public Order getById(int id);
	
	public void delete(int id);
}
