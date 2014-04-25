package com.huang.service;

import java.util.List;

import com.huang.domain.privilege.PrivilegeGroup;

public interface EmployeeGroupService {
	
	public void add(String username,String groupid);
	public void delete(String username,String groupid);
	public List<PrivilegeGroup> find(String username);
	public List<PrivilegeGroup> findAll(); 

}
