package com.junyang.security.controller;

import com.junyang.common.model.tree.MyPage;
import com.junyang.common.utils.JsonUtil;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.model.Organization;
import com.junyang.security.service.OrganizationService;
import com.junyang.security.vo.OrganizationVo;
import com.junyang.security.vo.QueryOrganizationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="security/organization/")
public class OrganizationController {
	private static final String MENU_LIST_VIEW = "security/organization/organizationList";
	private static final String MENU_EDIT_VIEW = "security/organization/organizationEdit";
	private static final String REDIRECT_MENU_LIST_ACTION = "redirect:organizationList.do";
    @Autowired
    private OrganizationService organizationService;
	/**
	 * 人员列表页面
	 * @return
	 */
	@RequestMapping(value="organizationList")
	private ModelAndView organizationList(@ModelAttribute("queryOrganizationVo") QueryOrganizationVo queryOrganizationVo, @ModelAttribute("page") MyPage page){
		MyPage<OrganizationVo> myPage = organizationService.findOrganizationPage(page,queryOrganizationVo);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("page", myPage);
		map.put("queryOrganizationVo", queryOrganizationVo);
		return new ModelAndView(MENU_LIST_VIEW,map);
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
