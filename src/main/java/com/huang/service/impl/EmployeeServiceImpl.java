package com.huang.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.EmployeeDao;
import com.huang.dao.IDCardDao;
import com.huang.domain.privilege.Employee;
import com.huang.service.EmployeeService;
import com.huang.util.MD5;



@Repository("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private IDCardDao cardDao;
	
	@Override
	public void add(Employee employee) {
		int cartid = cardDao.add(employee.getIdCard());
		employee.getIdCard().setId(cartid);
		employee.setPassword(MD5.MD5Encode(employee.getPassword()));
		employeeDao.add(employee);
	}

	@Override
	public Employee getById(String id) {
		return employeeDao.getById(id);
	}

	@Override
	public int count(String username) {
		return employeeDao.count(username);
	}

	@Override
	public List<Employee> find(int pageSize, int pageOffset) {
		return employeeDao.find(pageSize, pageOffset);
	}

	@Override
	public boolean exist(String username) {
		int i = employeeDao.count(username);
		return i>0;
	}

	@Override
	public int countByDynamic(HashMap<String, Object> map) {
		return employeeDao.countByDynamic(map);
	}

	@Override
	public List<Employee> findByDynamic(HashMap<String, Object> map) {
		return employeeDao.findByDynamic(map);
	}

	@Override
	public boolean validate(String username, String password) {
		return employeeDao.validate(username, MD5.MD5Encode(password));
	}

}
