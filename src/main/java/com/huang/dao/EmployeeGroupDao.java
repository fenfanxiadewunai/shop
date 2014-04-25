package com.huang.dao;

import java.util.List;

import com.huang.domain.privilege.PrivilegeGroup;

public interface EmployeeGroupDao {

	/**  eg 表的操作 **/
	
	public void add(String username,String groupid);
	
	public void delete(String username,String groupid);
	
	public List<PrivilegeGroup> find(String username);
	public List<PrivilegeGroup> findAll(); 
}
