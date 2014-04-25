package com.huang.service;

import java.util.List;

import com.huang.domain.privilege.SystemPrivilege;
import com.huang.domain.privilege.SystemPrivilegePK;

public interface SystemPrivilegeService {

	public void add(SystemPrivilege privilege);
	
	public void delete(SystemPrivilegePK id);
	
	public SystemPrivilege getById(SystemPrivilegePK id);
	
	public List<SystemPrivilege> find(int pageSize,int pageOffset);
	
	public int count();
	
	public void add(List<SystemPrivilege> privileges);
	
	public List<SystemPrivilege> findAll();
}
