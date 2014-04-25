package com.huang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.BuyerDao;
import com.huang.domain.user.Buyer;
import com.huang.service.BuyerService;
import com.huang.util.MD5;


@Repository("buyerService")
public class BuyerServiceImpl implements BuyerService{
	
	@Resource
	private BuyerDao buyerDao;


	@Override
	public boolean exist(String username) {
		return buyerDao.exist(username);
	}

	@Override
	public boolean checkUser(String username, String password) {
		return buyerDao.checkUser(username, MD5.MD5Encode(password));
	}

	@Override
	public void updatePassword(String username, String newpassword) {
		buyerDao.updatePassword(username, MD5.MD5Encode(newpassword));
	}

	@Override
	public void add(Buyer buyer) {
		buyer.setPassword(MD5.MD5Encode(buyer.getPassword()));
		buyerDao.add(buyer);
	}

	@Override
	public Buyer get(String username) {
		return buyerDao.get(username);
	}
	
	@Override
	public Buyer getWithPassword(String username) {
		return buyerDao.getWithPassword(username);
	}

	@Override
	public int countAll() {
		return buyerDao.countAll();
	}

	@Override
	public List<Buyer> find(int pageSize, int pageOffset) {
		return buyerDao.find(pageSize, pageOffset);
	}

	@Override
	public void setVisibleStatus(String[] usernames, boolean status) {
		buyerDao.setVisibleStatus(usernames, status);
	}

}
