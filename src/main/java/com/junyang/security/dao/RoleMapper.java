package com.junyang.security.dao;

import com.junyang.security.model.Role;
import com.junyang.security.vo.QueryRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    int deleteByPrimaryKey(String rId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRolePage(@Param("queryRoleVo")QueryRoleVo queryRoleVo);
    
    Set<Role> selecPersonRoleByPersonId(String personId);
    
	List<Role> selectPersonNoRoleByPersonId(String personId);
}