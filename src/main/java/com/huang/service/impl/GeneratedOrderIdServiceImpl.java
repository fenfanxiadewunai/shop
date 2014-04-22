package com.huang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.GeneratedOrderIdDao;
import com.huang.service.GeneratedOrderIdService;

@Repository("generatedOrderIdService")
public class GeneratedOrderIdServiceImpl implements GeneratedOrderIdService{

	@Resource
	private GeneratedOrderIdDao generatedOrderIdDao;
	
	@Override
	public int buildOrderId() {
		generatedOrderIdDao.addOrderId();
		return generatedOrderIdDao.getOrderId();
	}

}
