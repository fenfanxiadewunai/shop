package com.huang.service;

import com.huang.domain.book.OrderContactInfo;

public interface OrderContactInfoService {

	
	public int add(OrderContactInfo orderContactInfo);
	
	public void update(OrderContactInfo orderContactInfo);
	
	public OrderContactInfo getById(int id);
	
	public void delete(int id);
}
