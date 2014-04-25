package com.huang.service;

import java.util.HashMap;
import java.util.List;

import com.huang.domain.privilege.Employee;
import com.huang.domain.privilege.PrivilegeGroup;

public interface EmployeeService {
	
	public void add(Employee employee);
	
	public Employee getById(String id);
	
	public int count(String username);
	
	public List<Employee> find(int pageSize,int pageOffset);
	
	public boolean exist(String username);

	public int countByDynamic(HashMap<String, Object> map);

	public List<Employee> findByDynamic(HashMap<String, Object> map);
	
	public boolean validate(String username,String password);

	public void addPrivilegeGroup(String username, PrivilegeGroup group);

	public void addPrivilegeGroupList(String username, List<PrivilegeGroup> groups);

	public List<PrivilegeGroup> findAllPrivilegeGroupList();
	
	public void updatePrivilegeGroup(String username,List<String> groupids);

}
