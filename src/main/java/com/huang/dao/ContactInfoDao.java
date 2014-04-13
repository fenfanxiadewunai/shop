package com.huang.dao;

import com.huang.domain.user.ContactInfo;

public interface ContactInfoDao {
	
	public int add(ContactInfo contactInfo);
	
	public void update(ContactInfo contactInfo);
	
	public ContactInfo getById(int id);

}
