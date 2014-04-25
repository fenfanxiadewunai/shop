package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.EmployeeGroupDao;
import com.huang.domain.privilege.PrivilegeGroup;


/**  eg 表 **/
@Repository("employeeGroupDao")
public class EmployeeGroupDaoImpl implements EmployeeGroupDao{

	@Resource
	private DaoFactory dao;
	
	@Override
	public void add(String username, String groupid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("groupid", groupid);
		dao.getSqlSession().insert("com.huang.mapper.EmployeeGroup.add",params);
	}

	@Override
	public void delete(String username, String groupid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("groupid", groupid);
		dao.getSqlSession().delete("com.huang.mapper.EmployeeGroup.delete",params);
	}

	@Override
	public List<PrivilegeGroup> find(String username) {
		List<PrivilegeGroup> ret = dao.getSqlSession().selectList("com.huang.mapper.EmployeeGroup.find", username);
		return ret;
	}

	@Override
	public List<PrivilegeGroup> findAll() {
		List<PrivilegeGroup> ret = dao.getSqlSession().selectList("com.huang.mapper.EmployeeGroup.findAll");
		return ret;
	}

}
