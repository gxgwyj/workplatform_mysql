package com.junyang.workflow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.page.Page;
@Controller
@RequestMapping(value = "/workflow")
public class ActivitiController {
	  private static final String PROCESSDEF_LIST_ACTION="workflow/processList.do";
      @Autowired
      private  RepositoryService repositoryService;
      /**
       * 流程定义列表
       * @param request
       * @return
       */
      @RequestMapping(value = "/processList")
      public ModelAndView processList(HttpServletRequest request,@ModelAttribute("page") Page page) {
    	  if(!Constants.TURN_PAGE.equals(request.getParameter(Constants.TURN_PAGE))){
  				page.setPageNo(1);
  			}
  		  page.initTurnPageUrl(PROCESSDEF_LIST_ACTION);
    	  ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
    	  int firstResult = (page.getPageNo()-1)*10+1;
    	  int maxResults = page.getPageSize();
    	  List<Object[]> objects = new ArrayList<Object[]>();
    	  List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(firstResult-1,maxResults);
    	  Deployment deployment = null;
    	  for(ProcessDefinition processDefinition:processDefinitionList){
    		  String deploymentId = processDefinition.getDeploymentId();
    		  deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    		  objects.add(new Object[]{processDefinition,deployment});
    	  }
    	  int rest = (int)processDefinitionQuery.count()%page.getPageSize();
    	  int pageTotal = (int)processDefinitionQuery.count()/page.getPageSize();
    	  page.setPageTotal(rest==0?pageTotal:pageTotal+1);
    	  page.setRecordTotal((int)processDefinitionQuery.count());
    	  ModelAndView mv = new ModelAndView("workflow/processList");
    	  mv.addObject("page", page);
    	  mv.addObject("objects", objects);
    	  return mv;
      }
      @RequestMapping(value="resource/read")
      public void readResource(@RequestParam("processDefinitionId") String processDefinitionId,@RequestParam("resourceType") String resourceType,HttpServletResponse  response){
    	  ProcessDefinition  processDefinition  = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();//根据流程定义id或得流程定义
    	  String resourceName = "";
    	  if("image".equals(resourceType)){
    		  resourceName = processDefinition.getDiagramResourceName();
    	  }else if("xml".equals(resourceType)){
    		  resourceName = processDefinition.getResourceName();
    	  }
    	  InputStream  resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
    	  byte[] btArray = new byte[1024];
    	  int length = -1;
    	  try {
			while((length=resourceAsStream.read(btArray,0,1024))!=-1){
				response.getOutputStream().write(btArray, 0, length);
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
    	  
      }
}
