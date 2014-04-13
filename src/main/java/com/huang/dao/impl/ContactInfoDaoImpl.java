package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ContactInfoDao;
import com.huang.domain.user.ContactInfo;

@Repository("contactInfoDao")
public class ContactInfoDaoImpl implements ContactInfoDao{

	@Resource
	private DaoFactory dao;

	@Override
	public void update(ContactInfo contactInfo) {
		dao.getSqlSession().update("com.huang.mapper.ContactInfo.update", contactInfo);
	}

	@Override
	public ContactInfo getById(int id) {
		return (ContactInfo)dao.getSqlSession().selectOne("com.huang.mapper.ContactInfo.getById", id);
	}

	@Override
	public int add(ContactInfo contactInfo) {
		dao.getSqlSession().insert("com.huang.mapper.ContactInfo.add", contactInfo);
		return contactInfo.getContactid();
	}

}
