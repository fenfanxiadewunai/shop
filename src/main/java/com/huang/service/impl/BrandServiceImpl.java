package com.huang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.BrandDao;
import com.huang.domain.Brand;
import com.huang.service.BrandService;

@Repository("brandService")
public class BrandServiceImpl implements BrandService{
	
	@Resource
	private BrandDao brandDao;

	@Override
	public void add(Brand brand) {
		brandDao.add(brand);
	}

	@Override
	public Brand getByCode(String code) {
		return brandDao.getByCode(code);
	}

	@Override
	public List<Brand> findByName(String name, int pageSize, int pageOffset) {
		return brandDao.findByName(name, pageSize, pageOffset);
	}

	@Override
	public int countByName(String name) {
		return brandDao.countByName(name);
	}

	@Override
	public void update(Brand brand) {
		brandDao.update(brand);
	}

}
