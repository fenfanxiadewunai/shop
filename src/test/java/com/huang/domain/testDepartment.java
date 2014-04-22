package com.huang.domain;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.privilege.Department;
import com.huang.service.DepartmentService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testDepartment {
	@Resource 
	private DepartmentService departmentService;

	@Test
    public void testAdd() {
		String name = "计算机";
		departmentService.add(name);
    }
	
	@Test
    public void testGet() {
		String id = "123";
		Department department = departmentService.getById(id);
		System.out.println(department);
		
		department.setName("计算机2");
		departmentService.update(department);
		
		Department department2 = departmentService.getById(id);
		System.out.println(department2);
    }
	
	@Test
    public void testCount() {
		int count = departmentService.count();
		List<Department> ret = departmentService.find(count, 0);
		for(Department depart : ret){
			System.out.println(depart);
		}
    }
	
	
	
	
}
