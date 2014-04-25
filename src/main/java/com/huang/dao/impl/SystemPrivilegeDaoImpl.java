package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.SystemPrivilegeDao;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.domain.privilege.SystemPrivilegePK;


@Repository("systemPrivilegeDao")
public class SystemPrivilegeDaoImpl implements SystemPrivilegeDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public void add(SystemPrivilege privilege) {
		dao.getSqlSession().insert("com.huang.mapper.SystemPrivilege.add",privilege);		
	}

	@Override
	public void delete(SystemPrivilegePK id) {
		
	}

	@Override
	public SystemPrivilege getById(SystemPrivilegePK id) {
		return (SystemPrivilege)dao.getSqlSession().selectOne("com.huang.mapper.SystemPrivilege.getById", id);
	}

	@Override
	public List<SystemPrivilege> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<SystemPrivilege> ret = dao.getSqlSession().selectList("com.huang.mapper.SystemPrivilege.find", params);
		
		return ret;
	}
	
	@Override
	public List<SystemPrivilege> findAll() {
		List<SystemPrivilege> ret = dao.getSqlSession().selectList("com.huang.mapper.SystemPrivilege.findAll");
		return ret;
	}

	@Override
	public int count() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.SystemPrivilege.count");
	}

}
