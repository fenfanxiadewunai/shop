package com.huang.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductTypeDao;
import com.huang.domain.ProductType;
import com.huang.service.ProductTypeService;

@Repository("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService{
	
	
	@Resource
	private ProductTypeDao productTypeDao;

	@Override
	public void add(ProductType product) {
			productTypeDao.add(product);
	}

	@Override
	public ProductType getById(int id) {
		return productTypeDao.getById(id);
	}

	@Override
	public List<ProductType> findByName(String name, int pageSize, int pageOffset) {
		return productTypeDao.findByName(name, pageSize, pageOffset);
	}

	@Override
	public int countByName(String name) {
		
		return productTypeDao.countByName(name);
		
	}

	@Override
	public void delete(int typeid) {
		productTypeDao.delete(typeid);
	}

	@Override
	public int countAll() {
		return productTypeDao.countAll();
	}

	@Override
	public List<ProductType> find(int pageSize, int pageOffset, String order,
			String sort,String parentid) {
		return productTypeDao.find(pageSize, pageOffset, order, sort,parentid);
	}

	@Override
	public List<ProductType> findwithparent(Integer pageSize, Integer pageOffset,String parentid) {
		return productTypeDao.findwithparent(pageSize, pageOffset,parentid);
	}

	@Override
	public int countwithparentid(String parentid) {
		return productTypeDao.countwithparentid(parentid);
	}

	@Override
	public void update(ProductType product) {
		productTypeDao.update(product);
	}

	@Override
	public List<ProductType> findwithChild(Integer pageSize, Integer pageOffset,
			String parentid) {
		return productTypeDao.findwithChild(pageSize, pageOffset, parentid);
	}

	@Override
	public ProductType getParentByChildId(int childTypeId) {
		return productTypeDao.getParentByChildId(childTypeId);
	}
	
	@Override
	public List<ProductType> getParentTypes(int childTypeId) {
		List<ProductType> parents = new ArrayList<ProductType>();
		parents.add(productTypeDao.getById(childTypeId));
		ProductType parent = productTypeDao.getParentByChildId(childTypeId);
		while(parent!=null){
			parents.add(0, parent);
			parent = productTypeDao.getParentByChildId(parent.getTypeid());
		}
		return parents;
	}

	@Override
	public List<Integer> getSubTypeids(List<Integer> typeids) {
		return productTypeDao.getSubTypeids(typeids);
	}
	
	@Override
	public void getAllSubTypeids(List<Integer>all,List<Integer> typeids){
		List<Integer> subtypeids = productTypeDao.getSubTypeids(typeids);
		if(subtypeids!=null&&subtypeids.size()>0){
			all.addAll(subtypeids);
			getAllSubTypeids(all, subtypeids);
		}
	}
	
	@Override
	public List<Integer> getAllSubTypeids(Integer typeid){
		List<Integer> typeids = new ArrayList<Integer>();
		List<Integer> alltypeids = new ArrayList<Integer>();
		typeids.add(typeid);
		alltypeids.add(typeid);
		getAllSubTypeids(alltypeids,typeids);
		return alltypeids;
	}

	@Override
	public List<ProductType> getFirstSubTypes(Integer typeid) {
		return productTypeDao.getFirstSubTypes(typeid);
	}

}
