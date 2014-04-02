package com.huang.domain;

import java.io.Serializable;

public class ProductType implements Serializable{
	
	private static final long serialVersionUID = -774714259504541680L;

	private int typeid;
	
	private String name;
	
	private String note;
	
	private Boolean visible;
	
	private ProductType parent;
	
	private int child;

	public ProductType(String parentid) {
        this.typeid = Integer.parseInt(parentid);
	}

	public ProductType(int typeid, String name, String note) {
		this.typeid = typeid;
		this.name = name;
		this.note = note;
		this.visible=true;
	}
	
	public ProductType(String name,String note){
		this(name,note,true);
	}
	
	public ProductType(){}
	
	public ProductType(String name,String note,Boolean visible){
		this.name = name;
		this.note = note;
		this.visible = visible;
	}

	public ProductType(int typeid) {
		this.typeid = typeid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	
	

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "ProductType [typeid=" + typeid + ", name=" + name + ", note="
				+ note + ", visible=" + visible + ", parent=" + parent
				+ ", child=" + child + "]";
	}
	
	
	
}

