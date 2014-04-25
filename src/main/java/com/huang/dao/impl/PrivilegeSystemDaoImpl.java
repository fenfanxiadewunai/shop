package com.huang.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huang.dao.PrivilegeSystemDao;
import com.huang.domain.privilege.SystemPrivilege;

/**  ps 表 **/
@Repository("privilegeSystemDao")
public class PrivilegeSystemDaoImpl implements PrivilegeSystemDao{
	
	@Resource
	private DaoFactory dao;

	@Override
	public void addProvilege(String groupid, Integer privilegeid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("groupid", groupid);
		params.put("privilegeid", privilegeid);
		dao.getSqlSession().insert("com.huang.mapper.PrivilegeSystem.add",params);
	}

	@Override
	public void deletePrivilege(String groupid, Integer privilegeid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("groupid", groupid);
		params.put("privilegeid", privilegeid);
		dao.getSqlSession().delete("com.huang.mapper.PrivilegeSystem.delete",params);
	}

	@Override
	public List<SystemPrivilege> findPrivilegeByGroupId(String groupid) {
		List<SystemPrivilege> ret = dao.getSqlSession().selectList("com.huang.mapper.PrivilegeSystem.find", groupid);
		return ret;
	}

}
