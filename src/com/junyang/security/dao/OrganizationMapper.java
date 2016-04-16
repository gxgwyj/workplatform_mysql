package com.junyang.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.junyang.common.model.page.Page;
import com.junyang.security.model.Organization;
import com.junyang.security.vo.OrganizationVo;
import com.junyang.security.vo.QueryOrganizationVo;

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