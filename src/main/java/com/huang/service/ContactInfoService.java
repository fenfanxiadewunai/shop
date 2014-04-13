package com.huang.service;

import com.huang.domain.user.ContactInfo;

public interface ContactInfoService {
	public int add(ContactInfo contactInfo);
	
	public void update(ContactInfo contactInfo);
	
	public ContactInfo getById(int id);

}
