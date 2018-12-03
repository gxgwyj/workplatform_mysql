package com.junyang.security.service;

import com.junyang.common.model.tree.MyPage;
import com.junyang.security.model.Organization;
import com.junyang.security.vo.OrganizationVo;
import com.junyang.security.vo.QueryOrganizationVo;

import java.util.List;

public interface OrganizationService {
	public void saveOrupdateOrg(Organization organization);
	public void changeOrganization (Organization organization);
	public List<Organization> findOrganizationList();
	public void removeOrganizations(String[] ids);
	public Organization getOrganization(String id);
	public OrganizationVo getOrganizationVo(String id);
	public MyPage<OrganizationVo> findOrganizationPage(MyPage page, QueryOrganizationVo queryOrganizationVo);
}