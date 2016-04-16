package com.junyang.workflow.service;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junyang.common.util.PrimaryKeyManager;
import com.junyang.oa.dao.LeaveMapper;
import com.junyang.oa.model.Leave;
@Service
public class LeaveWorkflowService {
	@Autowired
	private LeaveMapper  leaveMapper;
	@Autowired
	private IdentityService  identityService;
	@Autowired
	private RuntimeService  runtimeService;
	/**
	 * 启动请假流程
	 * @param leave
	 * @return
	 */
	public ProcessInstance startWorkflow(Leave  leave){
		String key = PrimaryKeyManager.getPrimaryKey();
		Map<String,Object> variables = new HashMap<String, Object>();
		String businessKey = key;//获取业务Id
		identityService.setAuthenticatedUserId(leave.getUserId());//用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", businessKey, variables);//启动流程实例
		leave.setId(key);
		leave.setProcessInstanceId(processInstance.getId());
		leaveMapper.insert(leave);//保存请假表单数据
		return processInstance;
	}

}
