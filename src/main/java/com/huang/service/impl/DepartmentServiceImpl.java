package com.huang.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.DepartmentDao;
import com.huang.domain.privilege.Department;
import com.huang.service.DepartmentService;


@Repository("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Resource
	private DepartmentDao departmentDao;

	@Override
	public String add(Department department) {
		return departmentDao.add(department);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	public void delete(String id) {
		departmentDao.delete(id);
	}

	@Override
	public Department getById(String id) {
		return departmentDao.getById(id);
	}

	@Override
	public int count() {
		return departmentDao.count();
	}

	@Override
	public List<Department> find(int pageSize, int pageOffset) {
		return departmentDao.find(pageSize, pageOffset);
	}

	@Override
	public void add(String name) {
		Department department = new Department();
		department.setDepartmentid(UUID.randomUUID().toString());
		department.setName(name);
		departmentDao.add(department);
	}

}
