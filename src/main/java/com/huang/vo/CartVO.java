package com.huang.vo;

public class CartVO {
	private Integer productid;
	private Integer styleid;
	
	private String buyitemid;
	
	
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getStyleid() {
		return styleid;
	}
	public void setStyleid(Integer styleid) {
		this.styleid = styleid;
	}
	public String getBuyitemid() {
		return buyitemid;
	}
	public void setBuyitemid(String buyitemid) {
		this.buyitemid = buyitemid;
		if(this.buyitemid!=null){
			String[] values = this.buyitemid.split("-");
			if(values.length==2){
				this.productid = new Integer(values[0]);
				this.styleid = new Integer(values[1]);
			}
		}
	}
	
	
	
	
}
