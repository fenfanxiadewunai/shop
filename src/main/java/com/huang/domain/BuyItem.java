package com.huang.domain;

public class BuyItem {
	
	private ProductInfo product;
	private int amount = 1;
	public BuyItem() {
	}
	public BuyItem(ProductInfo product) {
		this.product = product;
		this.amount = 1;
	}
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		String result = product.getId() +"_";
		result += product.getStyles().iterator().next().getId();
		return result.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyItem other = (BuyItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if(product.getStyles().size()!=other.product.getStyles().size())
			return false;
		int styleid = product.getStyle().getId();
		int otherStyleid = other.product.getStyle().getId();
		if(styleid!=otherStyleid) return false;
		return true;
	}
	
	
	
	
}
