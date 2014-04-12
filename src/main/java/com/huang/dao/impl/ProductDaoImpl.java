package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductDao;
import com.huang.domain.Brand;
import com.huang.domain.ProductInfo;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public int add(ProductInfo product) {
		dao.getSqlSession().insert("com.huang.mapper.Product.add", product);
		return product.getId();
	}

	@Override
	public ProductInfo getById(int id) {
		return (ProductInfo)dao.getSqlSession().selectOne("com.huang.mapper.Product.getById", id);
	}

	@Override
	public int countAll() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Product.countAll");
	}

	@Override
	public List<ProductInfo> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<ProductInfo> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.find", params);
		
		return ret;
	}

	@Override
	public void update(ProductInfo product) {
		dao.getSqlSession().update("com.huang.mapper.Product.update", product);		
	}

	@Override
	public int countByDynamic(HashMap<String,Object> map) {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Product.countByDynamic",map);
	}

	@Override
	public List<ProductInfo> findByDynamic(HashMap<String,Object> map) {
		List<ProductInfo> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.findByDynamic", map);
		return ret;
	}

	@Override
	public void setVisibleStatus(int[] productids, boolean status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("productids", productids);
		params.put("status", status);
		dao.getSqlSession().update("com.huang.mapper.Product.setvisible", params);	
	}

	@Override
	public void setCommendStatus(int[] productids, boolean status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("productids", productids);
		params.put("status", status);
		dao.getSqlSession().update("com.huang.mapper.Product.setcommend", params);	
	}

	@Override
	public List<ProductInfo> findwithtypeids(List<Integer> typeids) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("typeids", typeids);
		List<ProductInfo> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.findwithtypeids", params);
		return ret;
	}

	@Override
	public List<ProductInfo> findwithtypeids(List<Integer> typeids, int num) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(typeids!=null&&typeids.size()>0){
			params.put("typeids", typeids);
		}
		params.put("pageSize", num);
		params.put("pageOffset", 0);
		List<ProductInfo> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.findwithtypeids", params);
		return ret;
	}

	@Override
	public List<Brand> getBrandsByProductTypeId(Integer typeid) {
		List<Brand> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.getBrandsByProductTypeId", typeid);
		return ret;
	}

	@Override
	public List<Brand> getBrandsByProductTypeIds(List<Integer> typeids) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(typeids!=null&&typeids.size()>0){
			params.put("typeids", typeids);
		}
		List<Brand> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.getBrandsByProductTypeIds", params);
		return ret;
	}


	@Override
	public List<ProductInfo> getListProductInfoByIds(List<Integer> ids,int num) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(ids!=null&&ids.size()>0){
			params.put("ids", ids);
		}
		params.put("pageSize", num);
		params.put("pageOffset", 0);
		List<ProductInfo> ret = dao.getSqlSession().selectList("com.huang.mapper.Product.getListProductInfoByIds", params);
		return ret;
	}



}
