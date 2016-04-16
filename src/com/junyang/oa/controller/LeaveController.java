package com.junyang.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.page.Page;
import com.junyang.common.util.ControllerUtil;
import com.junyang.oa.model.Leave;
import com.junyang.security.vo.PersonVo;
import com.junyang.workflow.service.LeaveWorkflowService;


@Controller
@RequestMapping(value = "/oa/leave")
public class LeaveController {
	@Autowired
	private LeaveWorkflowService leaveWorkflowService;
	private static final String LEAVE_LIST_VIEW = "oa/leave/leaveList";
	private static final String LEAVE_FORM_VIEW = "oa/leave/leaveEdit";
	private static final String REDIRECT_LEAVE_LIST_ACTION ="redirect:myleaveList.do";
	
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
	private ModelAndView startLeave(@ModelAttribute("leave")Leave leave){
		PersonVo person = ControllerUtil.getCurrentUser();
		leave.setUserId(person.getId());
		ProcessInstance  processInstance = leaveWorkflowService.startWorkflow(leave);//启动请假流程
		//redirectAttributes.addAttribute("message", "流程已经启动，ID【"+processInstance.getId()+"】");
		return new ModelAndView(REDIRECT_LEAVE_LIST_ACTION);
	}

}
