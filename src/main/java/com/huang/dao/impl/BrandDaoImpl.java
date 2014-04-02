package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.BrandDao;
import com.huang.domain.Brand;

@Repository("brandDao")
public class BrandDaoImpl implements BrandDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public void add(Brand brand) {
		dao.getSqlSession().insert("com.huang.mapper.Brand.add", brand);
	}

	@Override
	public Brand getByCode(String code) {
		return (Brand)dao.getSqlSession().selectOne("com.huang.mapper.Brand.getByCode", code);
	}

	@Override
	public List<Brand> findByName(String name, int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			params.put("name", "%"+name+"%");
		}
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<Brand> ret = dao.getSqlSession().selectList("com.huang.mapper.Brand.findwithName", params);
		
		return ret;
	}

	@Override
	public int countByName(String name) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			params.put("name", "%"+name+"%");
		}
		
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Brand.countByName",params);
		
	}

	@Override
	public void update(Brand brand) {
		dao.getSqlSession().update("com.huang.mapper.Brand.update", brand);
	}

}
