package com.huang.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.ProductDao;
import com.huang.dao.ProductStyleDao;
import com.huang.domain.Brand;
import com.huang.domain.ProductInfo;
import com.huang.domain.ProductStyle;
import com.huang.service.ProductService;
import com.huang.service.ProductTypeService;

@Repository("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductStyleDao productStyleDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProductTypeService productTypeService; 
	
	@Override
	public int add(ProductInfo product) {
		int id = productDao.add(product);
		List<ProductStyle> pros = product.getStyles();
		for(ProductStyle p: pros){
			productStyleDao.add(p);
		}
		return id; 
	}

	@Override
	public ProductInfo getById(int id) {
		return productDao.getById(id);
	}
	
	@Override
	public ProductInfo getByIdWithProductStyle(int id) {
		ProductInfo product = productDao.getById(id);
		List<ProductStyle> ps = productStyleDao.find(product.getId());
		for(ProductStyle pp:ps){
			product.addProductStyle(pp);
		}
		return product;
	}

	@Override
	public int countAll() {
		return productDao.countAll();
	}

	@Override
	public List<ProductInfo> find(int pageSize, int pageOffset) {
		return productDao.find(pageSize, pageOffset);
	}

	@Override
	public void update(ProductInfo product) {
		productDao.update(product);
	}

	@Override
	public int countByDynamic(HashMap<String, Object> map) {
		return productDao.countByDynamic(map);
	}

	@Override
	public List<ProductInfo> findByDynamic(HashMap<String, Object> map) {
		List<ProductInfo> ret = productDao.findByDynamic(map);
		for(ProductInfo p:ret){
			List<ProductStyle> ps = productStyleDao.find(p.getId());
			for(ProductStyle pp:ps){
				p.addProductStyle(pp);
			}
		}
		return ret;
	}

	@Override
	public void setVisibleStatus(int[] productids, boolean status) {
		productDao.setVisibleStatus(productids, status);
	}

	@Override
	public void setCommendStatus(int[] productids, boolean status) {
		productDao.setCommendStatus(productids, status);
	}

	@Override
	public List<ProductInfo> findwithtypeids(List<Integer> typeids) {
		return productDao.findwithtypeids(typeids);
	}

	@Override
	public List<ProductInfo> findwithtypeids(List<Integer> typeids, int num) {
		return productDao.findwithtypeids(typeids,num);
	}
	
	@Override
	public List<ProductInfo> getTopSellProduct(Integer typeid, int num) {
		List<ProductInfo> ret = null;
		if(typeid!=null){
			List<Integer> typeids = productTypeService.getAllSubTypeids(typeid);
			ret =  productDao.findwithtypeids(typeids,num);
		}
		else{
			ret = productDao.findwithtypeids(null, num);
		}
		return ret;
		
	}
	
	@Override
	public List<ProductInfo> getTopSellProduct(int num) {
		return getTopSellProduct(null, num);
		
	}

	@Override
	public List<Brand> getBrandsByProductTypeId(Integer typeid) {
		return productDao.getBrandsByProductTypeId(typeid);
	}
	@Override
	public List<Brand> getBrandsByProductTypeIds(List<Integer> typeids) {
		return productDao.getBrandsByProductTypeIds(typeids);
	}

	@Override
	public List<ProductInfo> getListProductInfoByIds(List<Integer> ids, int num) {
		return productDao.getListProductInfoByIds(ids, num);
	}

}
