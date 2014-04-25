package com.huang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.SystemPrivilegeDao;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.domain.privilege.SystemPrivilegePK;
import com.huang.service.SystemPrivilegeService;


@Repository("systemPrivilegeService")
public class SystemPrivilegeServiceImpl implements SystemPrivilegeService{

	@Resource
	private SystemPrivilegeDao systemPrivilegeDao;
	
	@Override
	public void add(SystemPrivilege privilege) {
		systemPrivilegeDao.add(privilege);
	}

	@Override
	public void delete(SystemPrivilegePK id) {
		
	}

	@Override
	public SystemPrivilege getById(SystemPrivilegePK id) {
		return systemPrivilegeDao.getById(id);
	}

	@Override
	public List<SystemPrivilege> find(int pageSize, int pageOffset) {
		return systemPrivilegeDao.find(pageSize, pageOffset);
	}

	@Override
	public int count() {
		return systemPrivilegeDao.count();
	}

	@Override
	public void add(List<SystemPrivilege> privileges) {
		for(SystemPrivilege privilege: privileges){
			systemPrivilegeDao.add(privilege);
		}
	}

	@Override
	public List<SystemPrivilege> findAll() {
		return systemPrivilegeDao.findAll();
	}

}
