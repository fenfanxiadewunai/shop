package com.huang.domain;

import java.io.Serializable;

public class Brand implements Serializable{
	
	private static final long serialVersionUID = 2751418786028475645L;
	private String code;
	private String name;
	private Boolean visible = true;
	private String logopath;
	
	public Brand(){}
	
	public Brand(String code){
		this.code = code;
	}
	
	public Brand(String code,String name,boolean visible,String logopath){
		this.code = code;
		this.name = name;
		this.visible = visible;
		this.logopath = logopath;
	}
	
	public Brand(String code,String name,String logopath){
		this.code = code;
		this.name = name;
		this.visible = true;
		this.logopath = logopath;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public String getLogopath() {
		return logopath;
	}
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Brand other = (Brand) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Brand [code=" + code + ", name=" + name + ", visible="
				+ visible + ", logopath=" + logopath + "]";
	}
	
	

}
