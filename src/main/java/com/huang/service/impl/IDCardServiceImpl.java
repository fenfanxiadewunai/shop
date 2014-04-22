package com.huang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.IDCardDao;
import com.huang.domain.privilege.IDCard;
import com.huang.service.IDCardService;


@Repository("cardService")
public class IDCardServiceImpl implements IDCardService{

	@Resource
	private IDCardDao cardDao;
	
	@Override
	public int add(IDCard card) {
		return cardDao.add(card);
	}

	@Override
	public void update(IDCard card) {
		cardDao.update(card);
	}

	@Override
	public void delete(int id) {
		cardDao.delete(id);
	}

	@Override
	public IDCard getById(int id) {
		return cardDao.getById(id);
	}

}
