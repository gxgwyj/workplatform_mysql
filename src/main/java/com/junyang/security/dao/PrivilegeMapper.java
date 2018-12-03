package com.junyang.security.dao;

import com.junyang.security.model.Privilege;
import org.apache.ibatis.annotations.Param;


public interface PrivilegeMapper {
    int deleteByPrimaryKey(String pId);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(String pId);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    int deleteByRoleIdAndMenuIds(@Param("menuIds")String[] menuIds,@Param("roleId")String roleId);
}