package com.huang.domain;

import java.util.ArrayList;
import java.util.List;

import com.huang.domain.book.OrderContactInfo;
import com.huang.domain.book.OrderDeliverInfo;
import com.huang.domain.book.PaymentWay;

public class BuyCart {
	/* 购物项 */
	private List<BuyItem> items = new ArrayList<BuyItem>();
	/* 收货人配送信息 */
	private OrderDeliverInfo deliverInfo;
	/* 订购者联系信息 */
	private OrderContactInfo contactInfo;
	/* 收货人与订购者是否相同 */
	private Boolean buyerIsrecipients;
	/* 支付方式 */
	private PaymentWay paymentWay;
	/* 配送费 */	
	private float deliveFee = 10f;
	/* 附言 */
	private String note;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public PaymentWay getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(PaymentWay paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Boolean getBuyerIsrecipients() {
		return buyerIsrecipients;
	}

	public void setBuyerIsrecipients(Boolean buyerIsrecipients) {
		this.buyerIsrecipients = buyerIsrecipients;
	}

	public OrderDeliverInfo getDeliverInfo() {
		return deliverInfo;
	}

	public void setDeliverInfo(OrderDeliverInfo deliverInfo) {
		this.deliverInfo = deliverInfo;
	}

	public OrderContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(OrderContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<BuyItem> getItems() {
		return items;
	}

	public void setItems(List<BuyItem> items) {
		this.items = items;
	}
	
	public void addBuyItem(BuyItem item){
		if(items.contains(item)){
			for(BuyItem bItem : items){
				if(bItem.equals(item)){
					bItem.setAmount(bItem.getAmount()+1);
					break;
				}
			}
		}else{
			items.add(item);
		}
	}
	
	public void deleteBuyItem(BuyItem item){
		if(this.items.contains(item))
			this.items.remove(item);
	}
	
	public void deleteBuyItem(Integer productid,Integer styleid){
		ProductInfo product = new ProductInfo(productid);
		ProductStyle ps = new ProductStyle(styleid);
		product.addProductStyle(ps);
		deleteBuyItem(new BuyItem(product));
	}

	public void deleteAll(){
		items.clear();
	}
	
	public float getTotalSellPrice(){
		float result = 0f;
		for(BuyItem item : items){
			result += item.getProduct().getSellprice()*item.getAmount();
		}
		return result;
	}
	
	public float getTotalSavePrice(){
		float result = 0f;
		for(BuyItem item : items){
			result += item.getProduct().getMarketprice() * item.getAmount();
		}
		return result - getTotalSellPrice();
	}

	public float getDeliveFee() {
		return deliveFee;
	}

	public void setDeliveFee(float deliveFee) {
		this.deliveFee = deliveFee;
	}
	
	public float getOrderTotalPrice(){
		return getTotalSellPrice()+getDeliveFee();
	}
	
	
}
