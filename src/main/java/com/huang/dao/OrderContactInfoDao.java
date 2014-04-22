package com.huang.dao;

import com.huang.domain.book.OrderContactInfo;

public interface OrderContactInfoDao {
	public int add(OrderContactInfo orderContactInfo);
	
	public void update(OrderContactInfo orderContactInfo);
	
	public OrderContactInfo getById(int id);
	
	public void delete(int id);
}
