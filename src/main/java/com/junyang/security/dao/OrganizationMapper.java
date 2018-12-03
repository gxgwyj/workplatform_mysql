package com.junyang.security.dao;

import com.junyang.security.model.Organization;
import com.junyang.security.vo.OrganizationVo;
import com.junyang.security.vo.QueryOrganizationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper  {
    int deleteByPrimaryKey(String oId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    List<Organization> selectOrganizations();
    
    List<OrganizationVo> selectOrganizationPage(@Param("queryOrganizationVo")QueryOrganizationVo queryOrganizationVo);
    
    OrganizationVo selectVoByPrimaryKey(String oId);
    
}