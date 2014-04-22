package com.huang.dao;

import com.huang.domain.privilege.IDCard;

public interface IDCardDao {
	
	public int add(IDCard card);
	
	public void update(IDCard card);
	
	public void delete(int id);
	
	public IDCard getById(int id);
	
}
