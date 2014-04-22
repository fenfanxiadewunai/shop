package com.huang.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.IDCardDao;
import com.huang.domain.privilege.IDCard;


@Repository("cardDao")
public class IDCardDaoImpl implements IDCardDao{

	
	@Resource
	private DaoFactory dao;
	
	@Override
	public int add(IDCard card) {
		dao.getSqlSession().insert("com.huang.mapper.IDCard.add",card);
		return card.getId();
	}

	@Override
	public void update(IDCard card) {
	}

	@Override
	public void delete(int id) {
		dao.getSqlSession().delete("com.huang.mapper.IDCard.delete",id);
	}

	@Override
	public IDCard getById(int id) {
		return (IDCard)dao.getSqlSession().selectOne("com.huang.mapper.IDCard.getById", id);
	}

}
