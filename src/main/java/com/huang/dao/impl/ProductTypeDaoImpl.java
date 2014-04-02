package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductTypeDao;
import com.huang.domain.ProductType;


@Repository("productTypeDao")
public class ProductTypeDaoImpl implements ProductTypeDao{
	
	@Resource
	private DaoFactory dao;
	
	
	public void add(ProductType product) {
		if(product.getParent()==null){
			dao.getSqlSession().insert("com.huang.mapper.ProductType.addNoParent", product);
		}
		else{
			dao.getSqlSession().insert("com.huang.mapper.ProductType.addWithParent", product);
		}
	}

	public ProductType getById(int typeid) {
		return (ProductType)dao.getSqlSession().selectOne("com.huang.mapper.ProductType.getById", typeid);
	}

	public List<ProductType> findByName(String name, int pageSize,int pageOffset) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			params.put("name", "%"+name+"%");
		}
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<ProductType> ret = dao.getSqlSession().selectList("com.huang.mapper.ProductType.findByName", params);
		
		return ret;
	}

	public int countByName(String name) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			params.put("name", "%"+name+"%");
		}
		
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.ProductType.countByName",params);
		
	}

	public void delete(int typeid) {
		dao.getSqlSession().insert("com.huang.mapper.ProductType.deleteById", typeid);
	}

	public int countAll() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.ProductType.countAll");
	}

	public List<ProductType> find(int pageSize, int pageOffset, String order,
			String sort,String parentid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		params.put("order", order);
		params.put("sort", sort);
		if(parentid != null&&!parentid.equals("")){
			params.put("parentid", Integer.parseInt(parentid));
		}
		
		List<ProductType> ret = dao.getSqlSession().selectList("com.huang.mapper.ProductType.find", params);
		
		return ret;
	}

	@Override
	public List<ProductType> findwithparent(int pageSize, int pageOffset,String parentid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		if(parentid!=null&&!parentid.equals("")){
			params.put("parentid", Integer.parseInt(parentid));
		}
		List<ProductType> ret = dao.getSqlSession().selectList("com.huang.mapper.ProductType.findwithparent", params);
		
		return ret;
	}

	@Override
	public int countwithparentid(String parentid) {
		
		int ret = 0;
		
		if(parentid==null||parentid.equals("")){
			ret = dao.getSqlSession().selectOne("com.huang.mapper.ProductType.countwithparentid");
		}
		else{
			ret = dao.getSqlSession().selectOne("com.huang.mapper.ProductType.countwithparentid",Integer.parseInt(parentid));
		}
		
		return ret;
	}

	@Override
	public void update(ProductType product) {
		dao.getSqlSession().update("com.huang.mapper.ProductType.update", product);
	}

	@Override
	public List<ProductType> findwithChild(int pageSize, int pageOffset,
			String parentid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		if(parentid!=null&&!parentid.equals("")){
			params.put("parentid", Integer.parseInt(parentid));
		}
		List<ProductType> ret = dao.getSqlSession().selectList("com.huang.mapper.ProductType.findwithChild", params);
		
		return ret;
	}

	@Override
	public ProductType getParentByChildId(int childTypeId) {
		return (ProductType)dao.getSqlSession().selectOne("com.huang.mapper.ProductType.getParentByChildId", childTypeId);
	}

}
