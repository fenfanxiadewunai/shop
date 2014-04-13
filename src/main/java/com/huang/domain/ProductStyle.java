package com.huang.domain;

import java.io.Serializable;

public class ProductStyle  implements Serializable{
	private static final long serialVersionUID = -1955848772900972554L;
	private int id;
	private String name;
	private String imagename;
	private boolean visible = true;
	private ProductInfo product;
	
	public ProductStyle(){}
	public ProductStyle(String name, String imagename) {
		this.name = name;
		this.imagename = imagename;
	}
	
	
	public ProductStyle(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductStyle other = (ProductStyle) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String getFullImagePath(){
		return "/images/product/"+this.product.getType().getTypeid()+"/"+this.getProduct().getId()+"/prototype/"+this.getImagename();
	}
	
	public String get140ImagePath(){
		return "/images/product/"+this.product.getType().getTypeid()+"/"+this.getProduct().getId()+"/140/"+this.getImagename();
	}
	
	
}
