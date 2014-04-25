package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.BuyerDao;
import com.huang.domain.user.Buyer;


@Repository("buyerDao")
public class BuyerDaoImpl implements BuyerDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public boolean exist(String username) {
		int i = dao.getSqlSession().selectOne("com.huang.mapper.Buyer.countByName", username);
		return i>0;
	}

	@Override
	public boolean checkUser(String username, String password) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		int i = dao.getSqlSession().selectOne("com.huang.mapper.Buyer.checkUser", params);
		return i>0;
	}

	@Override
	public void updatePassword(String username, String newpassword) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", newpassword);
		dao.getSqlSession().selectOne("com.huang.mapper.Buyer.updatepassword", params);
	}

	@Override
	public void add(Buyer buyer) {
		dao.getSqlSession().insert("com.huang.mapper.Buyer.add", buyer);
	}

	@Override
	public Buyer get(String username) {
		return dao.getSqlSession().selectOne("com.huang.mapper.Buyer.getByUserName", username);
	}
	
	@Override
	public Buyer getWithPassword(String username) {
		return dao.getSqlSession().selectOne("com.huang.mapper.Buyer.getByUserNameWithPassword", username);
	}

	@Override
	public int countAll() {
		return dao.getSqlSession().selectOne("com.huang.mapper.Buyer.countAll");
	}

	@Override
	public List<Buyer> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		List<Buyer> ret = dao.getSqlSession().selectList("com.huang.mapper.Buyer.find", params);
		return ret;
	}

	@Override
	public void setVisibleStatus(String[] usernames, boolean status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("usernames", usernames);
		params.put("status", status);
		dao.getSqlSession().update("com.huang.mapper.Buyer.setvisible", params);	
	}

}
