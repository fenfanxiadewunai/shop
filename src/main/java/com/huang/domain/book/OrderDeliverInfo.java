package com.huang.domain.book;

import com.huang.domain.user.Gender;
import com.huang.vo.DeliverVO;

public class OrderDeliverInfo {
	private String recipients;
	private String address;
	private String email;
	private String postcode;
	private String tel;
	private String mobile;
	private Gender gender = Gender.MAN;
	
	private DeliverWay deliverWay;
	
	private String requirement;
	
	public OrderDeliverInfo(){}
	
	public OrderDeliverInfo(DeliverVO dvo){
		this.init(dvo);
	}
	public void init(DeliverVO dvo){
		this.setRecipients(dvo.getRecipients());
		this.setGender(dvo.getGender());
		this.setAddress(dvo.getAddress());
		this.setPostcode(dvo.getPostcode());
		this.setEmail(dvo.getEmail());
		this.setTel(dvo.getTel());
		this.setMobile(dvo.getMobile());
	}
	
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
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

	public DeliverWay getDeliverWay() {
		return deliverWay;
	}

	public void setDeliverWay(DeliverWay deliverway) {
		this.deliverWay = deliverway;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	
	
	
}
