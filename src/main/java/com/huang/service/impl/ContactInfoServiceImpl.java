package com.huang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ContactInfoDao;
import com.huang.domain.user.ContactInfo;
import com.huang.service.ContactInfoService;

@Repository("contactInfoService")
public class ContactInfoServiceImpl implements ContactInfoService{
	
	@Resource
	private ContactInfoDao contactInfoDao;

	@Override
	public int add(ContactInfo contactInfo) {
		return contactInfoDao.add(contactInfo);
	}

	@Override
	public void update(ContactInfo contactInfo) {
		contactInfoDao.update(contactInfo);
	}

	@Override
	public ContactInfo getById(int id) {
		return contactInfoDao.getById(id);
	}

}
