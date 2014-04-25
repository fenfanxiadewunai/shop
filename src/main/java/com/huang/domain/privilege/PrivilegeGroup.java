package com.huang.domain.privilege;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class PrivilegeGroup {

	private String groupid;
	private String name;
	
	private Set<SystemPrivilege> privileges = new HashSet<SystemPrivilege>();
	
	public PrivilegeGroup() {}
	
	public PrivilegeGroup(String groupid, String name) {
		this.groupid = groupid;
		this.name = name;
	}
	
	public PrivilegeGroup(String name) {
		this.groupid = UUID.randomUUID().toString();
		this.name = name;
	}
	
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<SystemPrivilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<SystemPrivilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "PrivilegeGroup [groupid=" + groupid + ", name=" + name
				+ ", privileges=" + privileges + "]";
	}
	
	
	
	
}
