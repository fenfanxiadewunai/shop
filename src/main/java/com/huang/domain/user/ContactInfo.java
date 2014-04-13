package com.huang.domain.user;

import java.io.Serializable;

public class ContactInfo implements Serializable{

	private static final long serialVersionUID = 1048574040460503085L;
	private int contactid;
	private String address;
	private String postcode;
	private String phone;
	private String mobile;
	private Buyer buyer;
	
	public ContactInfo(){}
	
	public ContactInfo(String address, String postcode, String phone,
			String mobile) {
		this.address = address;
		this.postcode = postcode;
		this.phone = phone;
		this.mobile = mobile;
	}
	
	public int getContactid() {
		return contactid;
	}

	public void setContactid(int contactid) {
		this.contactid = contactid;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contactid;
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
		ContactInfo other = (ContactInfo) obj;
		if (contactid != other.contactid)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ContactInfo [contactid=" + contactid + ", address=" + address
				+ ", postcode=" + postcode + ", phone=" + phone + ", mobile="
				+ mobile + ", buyer=" + buyer + "]";
	}
	
	
	
}
