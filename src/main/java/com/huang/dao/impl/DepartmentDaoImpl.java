package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.DepartmentDao;
import com.huang.domain.privilege.Department;


@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public String add(Department department) {
		dao.getSqlSession().insert("com.huang.mapper.Department.add",department);
		return department.getDepartmentid();
	}

	@Override
	public void update(Department department) {
		dao.getSqlSession().update("com.huang.mapper.Department.update", department);
	}

	@Override
	public void delete(String id) {
		dao.getSqlSession().delete("com.huang.mapper.Department.delete",id);
	}

	@Override
	public Department getById(String id) {
		return (Department)dao.getSqlSession().selectOne("com.huang.mapper.Department.getById", id);
	}

	@Override
	public int count() {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Department.count");
	}

	@Override
	public List<Department> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<Department> ret = dao.getSqlSession().selectList("com.huang.mapper.Department.find", params);
		
		return ret;
	}

}
