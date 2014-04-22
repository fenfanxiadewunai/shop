package com.huang.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.huang.dao.OrderDao;
import com.huang.domain.BuyCart;
import com.huang.domain.BuyItem;
import com.huang.domain.ProductStyle;
import com.huang.domain.book.Order;
import com.huang.domain.book.OrderItem;
import com.huang.domain.book.OrderState;
import com.huang.domain.user.Buyer;
import com.huang.domain.user.ContactInfo;
import com.huang.service.BuyerService;
import com.huang.service.GeneratedOrderIdService;
import com.huang.service.OrderContactInfoService;
import com.huang.service.OrderDeliverInfoService;
import com.huang.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	@Resource
	private GeneratedOrderIdService generatedOrderIdService;
	
	@Resource
	private OrderDeliverInfoService orderDeliverInfoService;
	
	@Resource
	private OrderContactInfoService orderContactInfoService;
	
	@Resource
	private BuyerService buyerService;
	
	@Resource 
	private OrderDao orderDao;
	

	@Override
	public synchronized Order createOrder(BuyCart cart, String username) {
		Order order = new Order();
		Buyer buyer = buyerService.get(username);
		order.setBuyer(buyer);
		order.setDeliverFee(cart.getDeliveFee());
		order.setNote(cart.getNote());
		order.setState(OrderState.WAITCONFIRM);
		order.setPaymentWay(cart.getPaymentWay());
		order.setProductTotalPrice(cart.getTotalSellPrice());
		order.setTotalPrice(cart.getOrderTotalPrice());
		order.setPayablefee(cart.getOrderTotalPrice());
		for(BuyItem item : cart.getItems()){
			ProductStyle style = item.getProduct().getStyle();
			OrderItem oitem = new OrderItem(item.getProduct().getName(),item.getProduct().getId(),item.getProduct().getSellprice(),
											item.getAmount(),style.getName(),style.getId());
			order.addOrderItem(oitem);
		}
		order.setOrderid(buildOrderId(order.getCreateDate()));
		if(buyer.getContactInfo()==null){
			buyer.setContactInfo(new ContactInfo());
			buyer.getContactInfo().setAddress(order.getOrderContactInfo().getAddress());
			buyer.getContactInfo().setPostcode(order.getOrderContactInfo().getPostcode());;
			buyer.getContactInfo().setPhone(order.getOrderContactInfo().getTel());;
			buyer.getContactInfo().setMobile(order.getOrderContactInfo().getMobile());;
			if(buyer.getRealname()==null) buyer.setRealname(order.getOrderContactInfo().getBuyerName());;
			if(buyer.getGender()==null)buyer.setGender(order.getOrderContactInfo().getGender());
		}
		orderDao.add(order);
		return order;
	}
	
	private String buildOrderId(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		StringBuilder sb = new StringBuilder(format.format(date));
		sb.append(fillZero(8, String.valueOf(generatedOrderIdService.buildOrderId())));
		return sb.toString();
	}
	
	private String fillZero(int length,String source){
		StringBuilder result = new StringBuilder(source);
		for(int i = result.length();i<length;i++){
			result.insert(0, '0');
		}
		return result.toString();
	}

}
