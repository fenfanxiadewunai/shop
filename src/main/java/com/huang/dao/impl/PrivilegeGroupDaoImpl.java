package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.PrivilegeGroupDao;
import com.huang.domain.privilege.PrivilegeGroup;


@Repository("privilegeGroupDao")
public class PrivilegeGroupDaoImpl implements PrivilegeGroupDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public String addProvilegeGroup(PrivilegeGroup group) {
		dao.getSqlSession().insert("com.huang.mapper.PrivilegeGroup.add",group);
		return group.getGroupid();
	}

	@Override
	public void updatePrivilegeGroup(PrivilegeGroup group) {
		dao.getSqlSession().update("com.huang.mapper.PrivilegeGroup.update", group);
	}

	@Override
	public void deletePrivilegeGroup(String id) {
		dao.getSqlSession().delete("com.huang.mapper.PrivilegeGroup.delete",id);
	}

	@Override
	public PrivilegeGroup getPrivilegeGroupById(String id) {
		return (PrivilegeGroup)dao.getSqlSession().selectOne("com.huang.mapper.PrivilegeGroup.getById", id);
	}

	@Override
	public int countPrivilegeGroup() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.PrivilegeGroup.count");
	}

	@Override
	public List<PrivilegeGroup> findPrivilegeGroup(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		List<PrivilegeGroup> ret = dao.getSqlSession().selectList("com.huang.mapper.PrivilegeGroup.find", params);
		return ret;
	}

	@Override
	public List<PrivilegeGroup> findAllPrivilegeGroup() {
		List<PrivilegeGroup> ret = dao.getSqlSession().selectList("com.huang.mapper.PrivilegeGroup.findAll");
		return ret;
	}

}
