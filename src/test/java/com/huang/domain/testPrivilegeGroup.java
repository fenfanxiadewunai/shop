package com.huang.domain;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huang.dao.PrivilegeSystemDao;
import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.service.PrivilegeGroupService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"file:src/main/resources/springmvc.xml"})  
public class testPrivilegeGroup {

	
	@Resource 
	private PrivilegeGroupService privilegeGroupService;
	
	@Resource 
	private PrivilegeSystemDao privilegeSystemDao;
	
	@Test
    public void testAdd() {
		PrivilegeGroup group = new PrivilegeGroup("上海");
		Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();
		privileges.add(new SystemPrivilege(2));
		privileges.add(new SystemPrivilege(3));
		group.setPrivileges(privileges);
		privilegeGroupService.addProvilegeGroup(group);
    }
	
	@Test
    public void testFindAll() {
		print();
    }
	
	@Test
    public void testUpdate() {
		System.out.println("before update groupid=25d41443-7a84-4306-82f0-a00323bfde20");
		print();
		System.out.println("after update groupid=25d41443-7a84-4306-82f0-a00323bfde20");
		PrivilegeGroup group = privilegeGroupService.getPrivilegeGroupById("596eceae-4d39-4932-9af0-c79dbeec8609");
		group.getPrivileges().clear();
		Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();
		privileges.add(new SystemPrivilege(2));
		privileges.add(new SystemPrivilege(3));
		group.setPrivileges(privileges);
		privilegeGroupService.updatePrivilegeGroup(group);
		print();
    }
	
	@Test
    public void testDelete() {
		System.out.println("before delete groupid=1");
		print();
		System.out.println("after delete groupid=1");
		privilegeGroupService.deletePrivilegeGroup("1");
		print();
    }
	
	@Test
    public void testDeletePrivilegeSystem() {
		privilegeSystemDao.deletePrivilege("596eceae-4d39-4932-9af0-c79dbeec8609", 2);
    }
	
	
	private void print(){
		int count = privilegeGroupService.countPrivilegeGroup();
		List<PrivilegeGroup> groups = privilegeGroupService.findPrivilegeGroup(count, 0);
		for(PrivilegeGroup group: groups){
			System.out.println(group);
		}
	}
	
	
	
}
