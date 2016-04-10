package com.junyang.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.page.Page;
import com.junyang.common.util.ControllerUtil;
import com.junyang.oa.model.Leave;
import com.junyang.security.person.model.Person;
import com.junyang.security.person.vo.PersonVo;


@Controller
@RequestMapping(value = "/oa/leave")
public class LeaveController {
	private static final String LEAVE_LIST_VIEW="oa/leave/leaveList";
	private static final String LEAVE_FORM_VIEW="oa/leave/leaveEdit";
	
	@RequestMapping(value="myleaveList")
	private ModelAndView myleaveList(@ModelAttribute("page") Page page,HttpServletRequest request){
		if(!Constants.TURN_PAGE.equals(request.getParameter(Constants.TURN_PAGE))){
			page.setPageNo(1);
		}
		page.initTurnPageUrl("");
		List  list = null;
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("list", list);
		map.put("page", page);
		return new ModelAndView(LEAVE_LIST_VIEW,map);
	}
	/**
	 * 新建请假申请
	 * @return
	 */
	@RequestMapping("creatForm")
	private ModelAndView creatForm(){
		return new ModelAndView(LEAVE_FORM_VIEW);
	}
	/**
	 * 启动请假流程
	 * @param leave
	 * @return
	 */
	@RequestMapping("startLeave")
	private ModelAndView startLeave(@ModelAttribute("leave") Leave leave){
		PersonVo person = ControllerUtil.getCurrentUser();
		leave.setUserId(person.getId());
		return new ModelAndView(LEAVE_FORM_VIEW);
	}

}
