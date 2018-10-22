package com.junyang.oa.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.Page;
import com.junyang.common.model.tree.MyPage;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junyang.common.utils.ControllerUtil;
import com.junyang.common.utils.JsonUtil;
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
	@Autowired
    private  RuntimeService runtimeService;
	 @Autowired
     private  RepositoryService repositoryService;
	private static final String LEAVE_LIST_VIEW = "oa/leave/leaveList";
	private static final String LEAVE_FORM_VIEW = "oa/leave/leaveEdit";
	private static final String REDIRECT_LEAVE_LIST_ACTION ="redirect:myleaveList.do";
	private static final String TASK_LIST_VIEW = "oa/leave/taskList";
	
	@RequestMapping(value="myleaveList")
	private ModelAndView myleaveList(@ModelAttribute("page") MyPage page){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("page", new MyPage<>());
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
		String pid = request.getParameter("pid");
		String taskId = request.getParameter("taskId");
		String isAgree = request.getParameter("isAgree");
		String suggestion = java.net.URLDecoder.decode(request.getParameter("suggestion"), "UTF-8");
		Map<String, Object> variables = new  HashMap<String, Object>();
		//获得流程实例
		ProcessInstance  processInstance  = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
		//执行实例  
        ExecutionEntity execution = (ExecutionEntity) processInstance;
        //当前实例的执行到哪个节点  
        String activitiId = execution.getActivityId();
        if("deptLeaderAudit".equals(activitiId)){//部门领导审批
        	variables.put("deptLeaderPass", isAgree);
        }
        if("usertask4".equals(activitiId)){//人事审批
        	variables.put("hrPass", isAgree);
        }
		try {
			//taskService.complete(taskId, variables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:myleaveApprove.do");
	}
	public void getNextTaskDefine(String pid){
		List<String>  activeActivity = runtimeService.getActiveActivityIds(pid);
		//获得流程实例
		ProcessInstance  processInstance  = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
		//获得流程定义
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());  
		//执行实例  
        ExecutionEntity execution = (ExecutionEntity) processInstance;
        //当前实例的执行到哪个节点  
        String activitiId = execution.getActivityId();
        //获得当前任务的所有节点  
		List<ActivityImpl> activitiList = def.getActivities();
		for (ActivityImpl activityImpl : activitiList) {
			String id = activityImpl.getId();
			if (activitiId.equals(id)) {
				System.out.println("当前任务：" + activityImpl.getProperty("name")); // 输出某个节点的某种属性
				List<PvmTransition> outTransitions = activityImpl
						.getOutgoingTransitions();// 获取从某个节点出来的所有线路
				for (PvmTransition tr : outTransitions) {
					PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
					System.out.println("下一步任务任务：" + ac.getProperty("name"));
					System.out.println("下一步任务类型：" + ac.getProperty("type"));
				}
				break;
			}
		}
	}
}
