package com.huang.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.PrivilegeGroupDao;
import com.huang.dao.PrivilegeSystemDao;
import com.huang.domain.privilege.PrivilegeGroup;
import com.huang.domain.privilege.SystemPrivilege;
import com.huang.service.PrivilegeGroupService;


@Repository("privilegeGroupService")
public class PrivilegeGroupServiceImpl implements PrivilegeGroupService{

	@Resource
	private PrivilegeGroupDao privilegeGroupDao;
	
	@Resource
	private PrivilegeSystemDao privilegeSystemDao;
	
	@Override
	public String addProvilegeGroup(PrivilegeGroup group) {
		String groupid = privilegeGroupDao.addProvilegeGroup(group);
		for(SystemPrivilege privilege:group.getPrivileges()){
			privilegeSystemDao.addProvilege(groupid, privilege.getId());
		}
		return groupid;
	}

	@Override
	public void updatePrivilegeGroup(PrivilegeGroup group) {
		String groupid = group.getGroupid();
		PrivilegeGroup oldprivilegeGroup = privilegeGroupDao.getPrivilegeGroupById(groupid);
		initPrivileges(oldprivilegeGroup);
		for(SystemPrivilege privilege:oldprivilegeGroup.getPrivileges()){
			privilegeSystemDao.deletePrivilege(groupid, privilege.getId());
		}
		for(SystemPrivilege privilege:group.getPrivileges()){
			privilegeSystemDao.addProvilege(groupid, privilege.getId());
		}
		privilegeGroupDao.updatePrivilegeGroup(group);
	}

	@Override
	public void deletePrivilegeGroup(String id) {
		PrivilegeGroup privilegeGroup = privilegeGroupDao.getPrivilegeGroupById(id);
		initPrivileges(privilegeGroup);
		for(SystemPrivilege privilege:privilegeGroup.getPrivileges()){
			privilegeSystemDao.deletePrivilege(id, privilege.getId());
		}
		privilegeGroupDao.deletePrivilegeGroup(id);
	}

	@Override
	public PrivilegeGroup getPrivilegeGroupById(String id) {
		PrivilegeGroup privilegeGroup = privilegeGroupDao.getPrivilegeGroupById(id);
		initPrivileges(privilegeGroup);
		return privilegeGroup;
	}

	@Override
	public int countPrivilegeGroup() {
		return privilegeGroupDao.countPrivilegeGroup();
	}

	@Override
	public List<PrivilegeGroup> findPrivilegeGroup(int pageSize, int pageOffset) {
		List<PrivilegeGroup> groups = privilegeGroupDao.findPrivilegeGroup(pageSize, pageOffset);
		for(PrivilegeGroup group : groups){
			initPrivileges(group);
		}
		return groups;
	}
	
	private void initPrivileges(PrivilegeGroup group){
		List<SystemPrivilege> privileges = privilegeSystemDao.findPrivilegeByGroupId(group.getGroupid());
		group.setPrivileges(new HashSet<SystemPrivilege>(privileges));
	}

	@Override
	public List<PrivilegeGroup> findAllPrivilegeGroup() {
		return privilegeGroupDao.findAllPrivilegeGroup();
	}

}
