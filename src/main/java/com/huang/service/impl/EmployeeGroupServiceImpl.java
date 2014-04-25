package com.huang.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.EmployeeGroupDao;
import com.huang.dao.PrivilegeSystemDao;
import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.service.EmployeeGroupService;


@Repository("employeeGroupService")
public class EmployeeGroupServiceImpl implements EmployeeGroupService{
	
	@Resource
	private EmployeeGroupDao employeeGroupDao;
	
	@Resource
	private PrivilegeSystemDao privilegeSystemDao;

	@Override
	public void add(String username, String groupid) {
		employeeGroupDao.add(username, groupid);
	}

	@Override
	public void delete(String username, String groupid) {
		employeeGroupDao.delete(username, groupid);
	}

	@Override
	public List<PrivilegeGroup> find(String username) {
		List<PrivilegeGroup> ret = employeeGroupDao.find(username);
		initList(ret);
		return ret;
	}

	@Override
	public List<PrivilegeGroup> findAll() {
		List<PrivilegeGroup> ret = employeeGroupDao.findAll();
		initList(ret);
		return ret;
	}
	
	private void init(PrivilegeGroup group){
		List<SystemPrivilege> privileges = privilegeSystemDao.findPrivilegeByGroupId(group.getGroupid());
		group.setPrivileges(new HashSet<SystemPrivilege>(privileges));
	}
	
	private void initList(List<PrivilegeGroup> groups){
		for(PrivilegeGroup group:groups){
			init(group);
		}
	}

}
