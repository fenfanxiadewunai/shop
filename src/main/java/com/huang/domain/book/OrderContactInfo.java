package com.huang.domain.book;

import com.huang.domain.user.Gender;
import com.huang.vo.DeliverVO;

public class OrderContactInfo {
	
	private Integer contactid;
	private String buyerName;
	private String address;
	private String email;
	private String postcode;
	private String tel;
	private String mobile;
	private Gender gender = Gender.MAN;
	
	
	public Integer getContactid() {
		return contactid;
	}

	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}

	public OrderContactInfo(){}
	
	public OrderContactInfo(DeliverVO dvo){
		this.init(dvo);
	}
	
	public void init(DeliverVO dvo){
		if(dvo.getBuyerIsrecipients()){
			this.setBuyerName(dvo.getRecipients());
			this.setGender(dvo.getGender());
			this.setAddress(dvo.getAddress());
			this.setPostcode(dvo.getPostcode());
			this.setTel(dvo.getTel());
			this.setMobile(dvo.getMobile());
			this.setEmail(dvo.getEmail());
		}else{
			this.setBuyerName(dvo.getBuyer());
			this.setGender(dvo.getBuyer_gender());
			this.setAddress(dvo.getBuyer_address());
			this.setPostcode(dvo.getBuyer_postcode());
			this.setTel(dvo.getBuyer_tel());
			this.setMobile(dvo.getBuyer_mobile());
		}
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactid == null) ? 0 : contactid.hashCode());
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
		OrderContactInfo other = (OrderContactInfo) obj;
		if (contactid == null) {
			if (other.contactid != null)
				return false;
		} else if (!contactid.equals(other.contactid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderContactInfo [contactid=" + contactid + ", buyerName="
				+ buyerName + ", address=" + address + ", email=" + email
				+ ", postcode=" + postcode + ", tel=" + tel + ", mobile="
				+ mobile + ", gender=" + gender + "]";
	}
	
	
	
	
	
}
