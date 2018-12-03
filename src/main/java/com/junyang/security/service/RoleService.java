package com.junyang.security.service;

import com.junyang.common.model.tree.MyPage;
import com.junyang.security.model.PersonRole;
import com.junyang.security.model.Role;
import com.junyang.security.vo.QueryRoleVo;

import java.util.List;
import java.util.Set;

public interface RoleService {
	public void saveOrUpdateRole(Role role);
	public List<Role> findRoleList();
	public void removeRoles(String[] ids);
	public Role getRoleById(String id);
	public MyPage<Role> findRolePage(MyPage page, QueryRoleVo queryRoleVo);
	public Set<Role> findPersonRoleByPersonId(String personId);
	public List<Role> findPersonNoRoleByPersonId(String personId);
	public void savePersonRoles(List<PersonRole> personRole);
	public void removePersonRoles(String personId,String[] roleIds);


}
