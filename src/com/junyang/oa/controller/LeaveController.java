package com.junyang.oa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;









import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junyang.common.Constants;
import com.junyang.common.model.page.Page;
import com.junyang.common.util.ControllerUtil;
import com.junyang.common.util.JsonUtil;
import com.junyang.oa.model.Leave;
import com.junyang.security.vo.PersonVo;
import com.junyang.workflow.service.LeaveWorkflowService;


@Controller
@RequestMapping(value = "/oa/leave")
public class LeaveController {
	@Autowired
	private LeaveWorkflowService leaveWorkflowService;
	@Autowired
	private TaskService  taskService;
	private static final String LEAVE_LIST_VIEW = "oa/leave/leaveList";
	private static final String LEAVE_FORM_VIEW = "oa/leave/leaveEdit";
	private static final String REDIRECT_LEAVE_LIST_ACTION ="redirect:myleaveList.do";
	private static final String TASK_LIST_VIEW = "oa/leave/taskList";
	
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
		leave.setUserId(person.getCode());
		ProcessInstance  processInstance = leaveWorkflowService.startWorkflow(leave);//启动请假流程
		//redirectAttributes.addAttribute("message", "流程已经启动，ID【"+processInstance.getId()+"】");
		return new ModelAndView(REDIRECT_LEAVE_LIST_ACTION);
	}
	/**
	 * 我的代办请假
	 * @param leave
	 * @return
	 */
	@RequestMapping("myleaveApprove")
	private ModelAndView taskList(@ModelAttribute("leave")Leave leave){
		ModelAndView  mv = new ModelAndView(TASK_LIST_VIEW);
		PersonVo person = ControllerUtil.getCurrentUser();
		leave.setUserId(person.getId());
		List<Leave> list = leaveWorkflowService.findTodoTasks(person.getCode());
		mv.addObject("list",list);
		return mv;
	}
	/**
	 * 任务签收
	 */
	@RequestMapping(value = "task/claim")
    public ModelAndView claim(HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes) {
		String taskId = request.getParameter("taskId");
        String userCode = ControllerUtil.getCurrentUser().getCode();
        taskService.claim(taskId, userCode);//签收任务
        redirectAttributes.addFlashAttribute("message", "任务已签收");
        return new ModelAndView(REDIRECT_LEAVE_LIST_ACTION);
    }
	/**
	 * 获取请假数据
	 */
	@RequestMapping(value = "getLeaveJson")
	public void getLeaveJson(HttpServletRequest request,HttpServletResponse  response) throws IOException{
		String leaveId = request.getParameter("leaveId");
		Leave leave = leaveWorkflowService.getLeaveService(leaveId);
		JsonUtil.setDateFormat("yyyy-MM-dd");
		response.getWriter().print(JsonUtil.Object2Json(leave));  //换成这个就好了
		response.getWriter().close();
	}
	/**
	 * 任务办理
	 * @throws Exception 
	 */
	@RequestMapping(value = "complate")
	public ModelAndView complate(HttpServletRequest  request) throws Exception{
		String taskId = request.getParameter("taskId");
		String isAgree = request.getParameter("isAgree");
		String suggestion = java.net.URLDecoder.decode(request.getParameter("suggestion"), "UTF-8");
		Map<String, Object> variables = new  HashMap<String, Object>();
		try {
			//taskService.complete(taskId, variables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:myleaveApprove.do");
	}
}
