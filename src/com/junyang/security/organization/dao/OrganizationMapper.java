package com.junyang.security.organization.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.junyang.common.model.page.Page;
import com.junyang.security.organization.model.Organization;
import com.junyang.security.organization.vo.OrganizationVo;
import com.junyang.security.organization.vo.QueryOrganizationVo;

public interface OrganizationMapper  {
    int deleteByPrimaryKey(String oId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    List<Organization> selectOrganizations();
    
    List<OrganizationVo> selectOrganizationPage(@Param("page")Page page,@Param("queryOrganizationVo")QueryOrganizationVo queryOrganizationVo);
    
    OrganizationVo selectVoByPrimaryKey(String oId);
    
}