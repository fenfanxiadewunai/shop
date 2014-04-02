package com.huang.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.UploadFileDao;
import com.huang.domain.UploadFile;

@Repository("uploadFileDao")
public class UploadFileDaoImpl implements UploadFileDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public void add(UploadFile file) {
		dao.getSqlSession().insert("com.huang.mapper.UploadFile.add", file);		
	}

	@Override
	public UploadFile getById(int id) {
		return (UploadFile)dao.getSqlSession().selectOne("com.huang.mapper.UploadFile.getById", id);
	}

	@Override
	public List<UploadFile> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<UploadFile> ret = dao.getSqlSession().selectList("com.huang.mapper.UploadFile.find", params);
		
		return ret;
	}

	@Override
	public int count() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.UploadFile.count");
	}

	@Override
	public void update(UploadFile file) {
		dao.getSqlSession().update("com.huang.mapper.UploadFile.update", file);
	}

	@Override
	public void batchdelete(List<Integer> list) {
		dao.getSqlSession().delete("com.huang.mapper.UploadFile.batchdelete", list);
	}

	@Override
	public List<UploadFile> batchselect(List<Integer> list) {
		List<Object> ol = dao.getSqlSession().selectList("com.huang.mapper.UploadFile.batchselect", list);
		List<UploadFile> ret = new ArrayList<UploadFile>();
		for(Object object:ol){
			ret.add((UploadFile)object);
		}
		return ret;
	}

}
