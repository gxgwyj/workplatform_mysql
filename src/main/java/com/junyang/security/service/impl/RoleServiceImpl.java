package com.junyang.security.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.junyang.common.model.tree.MyPage;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.dao.PersonRoleMapper;
import com.junyang.security.dao.RoleMapper;
import com.junyang.security.model.PersonRole;
import com.junyang.security.model.Role;
import com.junyang.security.service.RoleService;
import com.junyang.security.vo.QueryRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private RoleMapper roleMapper;
	@Autowired
    private PersonRoleMapper personRoleMapper;
	
	@Override
	public void saveOrUpdateRole(Role role) {
		if(role.getrId()!=null && !"".equals(role.getrId().trim())){
			roleMapper.updateByPrimaryKey(role);
		}else{
			roleMapper.insert(role);
		}
	}

	@Override
	public List<Role> findRoleList() {
		return null;
	}

	@Override
	public void removeRoles(String[] ids) {
		if(ids!=null && ids.length>0){
			for(String id:ids){
				roleMapper.deleteByPrimaryKey(id);
			}
		}
		
	}

	@Override
	public Role getRoleById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public MyPage<Role> findRolePage(MyPage page, QueryRoleVo queryRoleVo) {
		Page<Object> queryResult = PageHelper.startPage(page.getPageNum(), page.getPageSize());
		roleMapper.selectRolePage(queryRoleVo);
		return new MyPage<>(queryResult);
	}

	@Override
	public Set<Role> findPersonRoleByPersonId(String personId) {
		return roleMapper.selecPersonRoleByPersonId(personId);
	}

	@Override
	public List<Role> findPersonNoRoleByPersonId(String personId) {
		return roleMapper.selectPersonNoRoleByPersonId(personId);
	}

	@Override
	public void savePersonRoles(List<PersonRole> personRoles) {
		if(personRoles!=null && personRoles.size()>0){
			for (int i = 0; i < personRoles.size(); i++) {
				personRoleMapper.insert(personRoles.get(i));
			}
		}
	}

	@Override
	public void removePersonRoles(String personId, String[] roleIds) {
		if(!StringUtil.isEmpty(personId) && roleIds!=null && roleIds.length>0){
			for (int i = 0; i < roleIds.length; i++) {
				personRoleMapper.deletePersonRoles(personId, roleIds[i]);
			}
		}
	}

}
