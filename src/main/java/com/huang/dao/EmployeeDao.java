package com.huang.dao;

import java.util.HashMap;
import java.util.List;

import com.huang.domain.privilege.Employee;

public interface EmployeeDao {
	public void add(Employee employee);
	
	public Employee getById(String id);
	
	public int count(String username);
	
	public List<Employee> find(int pageSize,int pageOffset);
	
	public int countByDynamic(HashMap<String, Object> map);

	public List<Employee> findByDynamic(HashMap<String, Object> map);
	
	public boolean validate(String username,String password);
}
