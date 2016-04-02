package com.junyang.security.role.service;

import java.util.List;
import java.util.Set;

import com.junyang.common.model.page.Page;
import com.junyang.security.role.model.PersonRole;
import com.junyang.security.role.model.Role;
import com.junyang.security.role.vo.QueryRoleVo;

public interface RoleService {
	public void saveOrUpdateRole(Role role);
	public List<Role> findRoleList();
	public void removeRoles(String[] ids);
	public Role getRoleById(String id);
	public List<Role> findRolePage(Page page,QueryRoleVo queryRoleVo);
	public Set<Role> findPersonRoleByPersonId(String personId);
	public List<Role> findPersonNoRoleByPersonId(String personId);
	public void savePersonRoles(List<PersonRole> personRole);
	public void removePersonRoles(String personId,String[] roleIds);


}
