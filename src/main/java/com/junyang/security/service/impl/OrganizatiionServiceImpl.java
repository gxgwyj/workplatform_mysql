package com.junyang.security.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.junyang.common.model.tree.MyPage;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.dao.OrganizationMapper;
import com.junyang.security.model.Organization;
import com.junyang.security.service.OrganizationService;
import com.junyang.security.vo.OrganizationVo;
import com.junyang.security.vo.QueryOrganizationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganizatiionServiceImpl implements OrganizationService {

	@Autowired
    private OrganizationMapper organizationMapper;
	
	@Override
	public void saveOrupdateOrg(Organization organization) {
		if(StringUtil.isEmpty(organization.getoId())){
			organizationMapper.insert(organization);
			System.out.println(":"+organization.getoId());
		}else{
			organizationMapper.updateByPrimaryKeySelective(organization);
		}
	}
	@Override
	public void changeOrganization(Organization organization) {
		organizationMapper.updateByPrimaryKey(organization);
		
	}
	@Override
	public List<Organization> findOrganizationList() {
		return organizationMapper.selectOrganizations(); 
	}
	@Override
	public void removeOrganizations(String[] ids) {
		if(ids!=null && ids.length>0){
			for(String id:ids){
				organizationMapper.deleteByPrimaryKey(id);
			}
		}
	}
	@Override
	public Organization getOrganization(String id) {
		return organizationMapper.selectByPrimaryKey(id);
	}
	@Override
	public MyPage<OrganizationVo> findOrganizationPage(MyPage page, QueryOrganizationVo queryOrganizationVo) {
		Page<OrganizationVo> queryResult = PageHelper.startPage(page.getPageNum(), page.getPageSize());
		organizationMapper.selectOrganizationPage(queryOrganizationVo);
		MyPage<OrganizationVo> pageResult = new MyPage<>(queryResult);
		return pageResult;
	}
	@Override
	public OrganizationVo getOrganizationVo(String id) {
		return organizationMapper.selectVoByPrimaryKey(id);
	}

}
