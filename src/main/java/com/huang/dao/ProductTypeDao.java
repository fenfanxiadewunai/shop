package com.huang.dao;

import java.util.List;

import com.huang.domain.ProductType;

public interface ProductTypeDao {
	
	public void add(ProductType product);
	
	public ProductType getById(int id);
	
	/**
	 * 根据name属性查找
	 * @param name
	 * @param pageSize  每页大小
	 * @param pageOffset  起始
	 * @param order   排序字段
	 * @param sort    升序或降序
	 * @return
	 */
	public List<ProductType> findByName(String name,int pageSize,int pageOffset);
	
	public List<ProductType> find(int pageSize,int pageOffset,String order,String sort,String parentid);
	
	
	public List<ProductType> findwithparent(Integer pageSize,Integer pageOffset,String parentid);
	
	
	public List<ProductType> findwithChild(Integer pageSize,Integer pageOffset,String parentid);
	
	
	public int countByName(String name);
	
	public int countAll();
	
	public int countwithparentid(String parentid);
	
	public void delete(int typeid);
	
	public void update(ProductType product);
	
	public ProductType getParentByChildId(int childTypeId);
	
	public List<Integer> getSubTypeids(List<Integer> typeids);
	
	List<ProductType> getFirstSubTypes(Integer typeid);
}
