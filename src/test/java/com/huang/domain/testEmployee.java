package com.huang.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.privilege.Department;
import com.huang.domain.privilege.Employee;
import com.huang.domain.privilege.IDCard;
import com.huang.domain.user.Gender;
import com.huang.service.EmployeeService;
import com.huang.util.MD5;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testEmployee {

	
	@Resource 
	private EmployeeService employeeService;

	@Test
    public void testAdd() {
		IDCard idCard = new IDCard(1);
		Department depart = new Department("2d9cdd5e-5506-477e-b998-723fdcbbe331");
		
		Employee emp = new Employee("huang",MD5.MD5Encode("123456"),"黄",Gender.MAN,"硕士",idCard,"上海大学","18817350871","357145481@qq.com","huang.jpg",true,depart);
		
		employeeService.add(emp);
		
    }
	
	@Test
    public void testGet() {
		Employee emp = employeeService.getById("huang");
		System.out.println(emp);
		
    }
	
	@Test
    public void testValidate() {
		boolean ret = employeeService.validate("huang123", "123456");
		System.out.println(ret);
		
    }
	
}
