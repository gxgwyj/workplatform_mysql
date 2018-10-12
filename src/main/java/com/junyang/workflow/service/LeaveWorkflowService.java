package com.junyang.workflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junyang.common.utils.PrimaryKeyManager;
import com.junyang.oa.dao.LeaveMapper;
import com.junyang.oa.model.Leave;
import com.junyang.oa.vo.LeaveVo;
import com.junyang.security.service.PersonService;
@Service
public class LeaveWorkflowService {
	@Autowired
	private LeaveMapper  leaveMapper;
	@Autowired
	private IdentityService  identityService;
	@Autowired
	private RuntimeService  runtimeService;
	
	@Autowired
	private TaskService  taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private PersonService personService;
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
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);//启动流程实例
		leave.setId(key);
		leave.setProcessInstanceId(processInstance.getId());
		leaveMapper.insert(leave);//保存请假表单数据
		return processInstance;
	}
	/**
	 
	 */
	public List<Leave> findTodoTasks(String userId){
		List<Leave> list = new ArrayList<Leave>();
		// 根据当前人的ID查询(认领任务)
		TaskQuery  taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
		List<Task> tasks = taskQuery.list();
		for(Task task : tasks){//任务
			String processInstanceId = task.getProcessInstanceId();//获得流程实例Id
			ProcessInstance  processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			List<Task> ts = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
			String businessKey = processInstance.getBusinessKey();
			if(businessKey == null){
				continue;
			}
			Leave leave = leaveMapper.selectByPrimaryKey(businessKey);
			leave.setProcessInstance(processInstance);//流程实例
			leave.setTask(task);
			leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
			leave.setApplyName(personService.getPersonVoByCode(leave.getUserId()).getName());
			list.add(leave);
		}
		return list;
	}
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
    public Leave  getLeaveService(String leaveId){
    	LeaveVo leaveVo = new LeaveVo();
    	leaveVo.setId(leaveId);
    	List<Leave> list = leaveMapper.selectLeaveVoList(leaveVo);
    	Leave leave = null;
    	if(list!=null && list.size()>0){
    		leave = list.get(0);
    	}
    	return leave;
    }

}
