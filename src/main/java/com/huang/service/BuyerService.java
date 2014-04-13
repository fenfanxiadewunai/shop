package com.huang.service;

import java.util.List;

import com.huang.domain.user.Buyer;

public interface BuyerService {
	
	public boolean exist(String username);
	
	public boolean checkUser(String username,String password);
	
	public void updatePassword(String username,String newpassword);
	
	public void add(Buyer buyer);
	
	Buyer get(String username);
	
	public int countAll();
	public List<Buyer> find(int pageSize,int pageOffset);
	public void setVisibleStatus(String[] usernames,boolean status);
}
