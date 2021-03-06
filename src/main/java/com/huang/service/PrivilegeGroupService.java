package com.huang.service;

import java.util.List;

import com.huang.domain.privilege.PrivilegeGroup;

public interface PrivilegeGroupService {

	public String addProvilegeGroup(PrivilegeGroup group);
	
	public void updatePrivilegeGroup(PrivilegeGroup group);
	
	public void deletePrivilegeGroup(String id);
	
	public PrivilegeGroup getPrivilegeGroupById(String id);
	
	public int countPrivilegeGroup();
	
	public List<PrivilegeGroup> findPrivilegeGroup(int pageSize,int pageOffset); 
	public List<PrivilegeGroup> findAllPrivilegeGroup(); 
}
