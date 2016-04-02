package com.junyang.security.organization.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.page.ExtPage;
import com.junyang.common.model.page.Page;
import com.junyang.common.util.JsonUtil;
import com.junyang.common.util.StringUtil;
import com.junyang.security.organization.model.Organization;
import com.junyang.security.organization.service.OrganizationService;
import com.junyang.security.organization.vo.OrganizationVo;
import com.junyang.security.organization.vo.QueryOrganizationVo;

@Controller
@RequestMapping(value="security/organization/")
public class OrganizationAction {
    private static final String MENU_LIST_VIEW="security/organization/organizationList";
    private static final String MENU_EDIT_VIEW="security/organization/organizationEdit";
    private static final String MENU_LIST_ACTION="security/organization/organizationList.do";
    private static final String REDIRECT_MENU_LIST_ACTION="redirect:organizationList.do";
    @Autowired
    private OrganizationService organizationService;
	/**
	 * 人员列表页面
	 * @return
	 */
	@RequestMapping(value="organizationList")
	private ModelAndView organizationList(@ModelAttribute("queryOrganizationVo") QueryOrganizationVo queryOrganizationVo,@ModelAttribute("page") Page page,HttpServletRequest request){
		if(!Constants.TURN_PAGE.equals(request.getParameter(Constants.TURN_PAGE))){
			page.setPageNo(1);
		}
		page.initTurnPageUrl(MENU_LIST_ACTION);
		List<OrganizationVo> organizationList = organizationService.findOrganizationPage(page,queryOrganizationVo);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("organizationList", organizationList);
		map.put("page", page);
		map.put("queryOrganizationVo", queryOrganizationVo);
		return new ModelAndView(MENU_LIST_VIEW,map);
	}
	/**
	 *返回json格式的请求 
	 * @return
	 */
	@RequestMapping(value="organizationListJson")
	@ResponseBody
	private String organizationListJson(HttpServletRequest request){
		int start = Integer.valueOf(request.getParameter("start"));
		int limit = Integer.valueOf(request.getParameter("limit"));
		ExtPage  extPage = new ExtPage();
		extPage.setStart(start);
		extPage.setLimit(limit);
		extPage.setPageSize(10);
		extPage.setPageNo(start/limit+1);
		List<OrganizationVo> records = null;//organizationService.findOrganizationPage(extPage);
		extPage.setRecords(records);
		String pageJson = JsonUtil.Object2Json(extPage);
		return pageJson;
	}
	@RequestMapping(value="organizationRemove")
	@ResponseBody
	private String organizationRemove(HttpServletRequest request){
		String[] ids = request.getParameterValues("orgId");
		if(ids!=null && ids.length>0){
			organizationService.removeOrganizations(ids);
		}
		return "success";
	}
	@RequestMapping(value="organizationEdit")
	private ModelAndView organizationEdit(HttpServletRequest request){
		String id = request.getParameter("id");
		OrganizationVo organization = new OrganizationVo();
		if(!StringUtil.isEmpty(id)){
			organization = organizationService.getOrganizationVo(id);
		}
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("organization", organization);
		return new ModelAndView(MENU_EDIT_VIEW,map);
	}
	@RequestMapping(value="organizationSave")
	private ModelAndView organizationSave(@ModelAttribute("organization")Organization organization){
		organizationService.saveOrupdateOrg(organization);
		return new ModelAndView(REDIRECT_MENU_LIST_ACTION);
	}
	@RequestMapping(value="getOrganizationListJson")
	private void getOrganizationListJson(HttpServletRequest request,HttpServletResponse response){
		List<Organization> records = organizationService.findOrganizationList();
		try {
//			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JsonUtil.Object2Json(records));  //换成这个就好了
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
