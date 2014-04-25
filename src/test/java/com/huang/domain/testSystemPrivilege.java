package com.huang.domain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.domain.privilege.SystemPrivilege;
import com.huang.service.SystemPrivilegeService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testSystemPrivilege {

	
	@Resource 
	private SystemPrivilegeService systemPrivilegeService;

	@Test
    public void testInit() {
		List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
		privileges.add(new SystemPrivilege("department", "view", "部门查看"));
		privileges.add(new SystemPrivilege("department", "insert", "部门添加"));
		privileges.add(new SystemPrivilege("department", "update", "部门修改"));
		privileges.add(new SystemPrivilege("department", "delete", "部门删除"));
		
		privileges.add(new SystemPrivilege("employee", "view", "员工查看"));
		privileges.add(new SystemPrivilege("employee", "insert", "员工添加"));
		privileges.add(new SystemPrivilege("employee", "update", "员工信息修改"));
		privileges.add(new SystemPrivilege("employee", "leave", "员工离职设置"));
		privileges.add(new SystemPrivilege("employee", "privilegeSet", "员工权限分配"));
		systemPrivilegeService.add(privileges);
    }
	
	@Test
    public void testGet() {
		int count = systemPrivilegeService.count();
		List<SystemPrivilege> ret = systemPrivilegeService.find(count, 0);
		for(SystemPrivilege privilege : ret){
			System.out.println(privilege);
		}
    }
	
}
