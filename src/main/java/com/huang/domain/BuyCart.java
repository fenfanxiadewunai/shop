package com.huang.domain;

import java.util.ArrayList;
import java.util.List;

public class BuyCart {
	
	private List<BuyItem> items = new ArrayList<BuyItem>();
	
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
	
}
