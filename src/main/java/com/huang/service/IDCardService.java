package com.huang.service;

import com.huang.domain.privilege.IDCard;

public interface IDCardService {
	
	public int add(IDCard card);
	
	public void update(IDCard card);
	
	public void delete(int id);
	
	public IDCard getById(int id);
}
