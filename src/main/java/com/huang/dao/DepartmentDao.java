package com.huang.dao;

import java.util.List;

import com.huang.domain.privilege.Department;

public interface DepartmentDao {
	public String add(Department department);
	
	public void update(Department department);
	
	public void delete(String id);
	
	public Department getById(String id);
	
	public int count();
	
	public List<Department> find(int pageSize,int pageOffset);
	
}
