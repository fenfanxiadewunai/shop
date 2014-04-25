package com.huang.dao;

import java.util.List;

import com.huang.domain.privilege.SystemPrivilege;
import com.huang.domain.privilege.SystemPrivilegePK;

public interface SystemPrivilegeDao {
	public void add(SystemPrivilege privilege);
	
	public void delete(SystemPrivilegePK id);
	
	public SystemPrivilege getById(SystemPrivilegePK id);
	
	public List<SystemPrivilege> find(int pageSize,int pageOffset);
	
	public int count();

	public List<SystemPrivilege> findAll();
	
}
