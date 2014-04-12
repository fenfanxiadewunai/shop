package com.huang.dao;

import java.util.List;

import com.huang.domain.Brand;

public interface BrandDao {

	public void add(Brand brand);

	public Brand getByCode(String code);
	
	/**
	 * 支持模糊查询，如果不传入name，则查询全部
	 * @param name
	 * @param pageSize
	 * @param pageOffset
	 * @return
	 */
	public List<Brand> findByName(String name,int pageSize,int pageOffset);
	
	public int countByName(String name);
	
	public void update(Brand brand);
	
	

}
