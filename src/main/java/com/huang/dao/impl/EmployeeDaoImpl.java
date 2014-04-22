package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.EmployeeDao;
import com.huang.domain.privilege.Employee;


@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public void add(Employee employee) {
		dao.getSqlSession().insert("com.huang.mapper.Employee.add",employee);
	}

	@Override
	public Employee getById(String id) {
		return (Employee)dao.getSqlSession().selectOne("com.huang.mapper.Employee.getById", id);
	}

	@Override
	public int count(String username) {
		if(username!=null&&!username.equals("")){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("username", username);
			return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Employee.count",params);
		}else{
			return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Employee.count");
		}
		
	}

	@Override
	public List<Employee> find(int pageSize, int pageOffset) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		
		List<Employee> ret = dao.getSqlSession().selectList("com.huang.mapper.Employee.find", params);
		
		return ret;
	}

	@Override
	public int countByDynamic(HashMap<String, Object> map) {
		return (Integer)dao.getSqlSession().selectOne("com.huang.mapper.Employee.countByDynamic",map);
	}

	@Override
	public List<Employee> findByDynamic(HashMap<String, Object> map) {
		List<Employee> ret = dao.getSqlSession().selectList("com.huang.mapper.Employee.findByDynamic", map);
		return ret;
	}

	@Override
	public boolean validate(String username, String password) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		int ret = dao.getSqlSession().selectOne("com.huang.mapper.Employee.validate",params);
		return ret>0;
	}

}
