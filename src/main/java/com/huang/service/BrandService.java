package com.huang.service;

import java.util.List;

import com.huang.domain.Brand;

public interface BrandService {

	public void add(Brand brand);

	public Brand getByCode(String code);

	public List<Brand> findByName(String name, int pageSize, int pageOffset);

	public int countByName(String name);

	public void update(Brand brand);

}
