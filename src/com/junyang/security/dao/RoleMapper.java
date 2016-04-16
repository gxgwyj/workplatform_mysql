package com.junyang.security.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.junyang.common.model.page.Page;
import com.junyang.security.model.Role;
import com.junyang.security.vo.QueryRoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(String rId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRolePage(@Param("page")Page page,@Param("queryRoleVo")QueryRoleVo queryRoleVo);
    
    Set<Role> selecPersonRoleByPersonId(String personId);
    
	List<Role> selectPersonNoRoleByPersonId(String personId);
}