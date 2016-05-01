package com.junyang.template.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class Activiti {

	@Test
	public void test() {
		/**
		 * 部署流程
		 */
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource("leave.bpmn")
		  .deploy();
		System.out.println("====================流程部署完成!=====================");
		/**
		 * 启动一个流程实例
		 */
//		Map<String,Object> variables=new HashMap<String,Object>();
//		variables.put("employeeName","Kermit");
//		variables.put("numberOfDays", new Integer(4));
//		variables.put("vacationMotivation", "I'm really tired!");
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
//		System.out.println("====================启动流程实例完成!=====================");
//		/**
//		 * 用户分配任务
//		 */
//		TaskService taskService = processEngine.getTaskService();
//		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
//		System.out.println(tasks.size());
//		/**
//		 * 执行第一个任务(领导审批未通过)
//		 */
//		Task task=tasks.get(0);
//		System.out.println(task.getName());
//		Map<String,Object> taskVariables=new HashMap<String, Object>();
//		taskVariables.put("vacationApproved", "false");
//		taskVariables.put("managerMotivation", "We have a tight deadline!");
//		taskService.complete(task.getId(), taskVariables);
//		System.out.println(task.getId());
//		/**
//		 * 挂起流程实例（当挂起流程定时时，	 就不能创建新流程了：会抛出一个异常）
//		 */
//		System.out.println("====================挂起一个流程====================");
//		//repositoryService.suspendProcessDefinitionByKey("vacationRequest");
//		try {
//			//runtimeService.startProcessInstanceById("vacationRequest");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		/**
//		 *查询API
//		 */
//		List<Task> tasks2=taskService.createTaskQuery().taskAssignee("kermit").processVariableValueEquals("orderId", "0815").orderByDueDate().asc().list();
	}

}
