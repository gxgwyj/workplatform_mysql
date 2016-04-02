package com.junyang.security.role.dao;

import com.junyang.security.role.model.PersonRole;

public interface PersonRoleMapper {
    int deleteByPrimaryKey(String prId);

    int insert(PersonRole record);

    int insertSelective(PersonRole record);

    PersonRole selectByPrimaryKey(String prId);

    int updateByPrimaryKeySelective(PersonRole record);

    int updateByPrimaryKey(PersonRole record);
    
    int deletePersonRoles(String personId,String roleId);
}