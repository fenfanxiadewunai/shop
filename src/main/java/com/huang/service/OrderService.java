package com.huang.service;

import com.huang.domain.BuyCart;
import com.huang.domain.book.Order;

public interface OrderService {
	public Order createOrder(BuyCart cart,String username);
}
