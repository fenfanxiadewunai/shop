package com.huang.dao;

import java.util.List;

import com.huang.domain.privilege.SystemPrivilege;

public interface PrivilegeSystemDao {
	
	
	/**  ps 表的操作 **/
	
	public void addProvilege(String groupid,Integer privilegeid);
	
	public void deletePrivilege(String groupid,Integer privilegeid);
	
	public List<SystemPrivilege> findPrivilegeByGroupId(String groupid); 
}
