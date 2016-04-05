<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
<title>组织架构列表</title>
</head>
<body>
<form action="<%=path%>/workflow/processList.do" method="post">
<div class="searchForm">
  <table>
  	<tr>
  		<td>部门代码：<input type="text" name="queryCode" value="${queryOrganizationVo.queryCode}"></td>
  		<td>部门名称：<input type="text" name="queryName" value="${queryOrganizationVo.queryName}"></td>
  		<td><input type="button" value="查询" onclick="queryData();" class="toolBarbtn" ></td>
  	</tr>
  </table>	
</div>
<div class="toolBarForm">
  <input type="button" class="toolBarbtn" value="添加" onclick="addData();"  >
  <input type="button" class="toolBarbtn"  value="删除" onclick="deleteData();">
  <input type="button" class="toolBarbtn"  value="导出">
</div>
<div class="dataMain">
<table   class="datalist" width="120%">  
    <tr>  
       <th>流程定义ID</th>  
       <th>部署ID</th>  
       <th>流程名称</th>  
       <th>流程KEY</th>
       <th>版本号</th>
       <th>XML</th>
       <th>流程图片</th>
       <th>部署时间</th>
       <th>是否挂起</th>
       <th>操作</th>  
   </tr>  
   <c:forEach  items="${objects}" var="object">
      <c:set var = "process" value="${object[0]}"/>
      <c:set var = "deployment" value="${object[1]}"/>
   	  <tr>  
       <td style="text-align: center;">${process.id}</td>  
       <td style="text-align: center;">${process.deploymentId}</td>  
       <td style="text-align: center;">${process.name}</td>
       <td style="text-align: center;">${process.key}</td>  
       <td style="text-align: center;">${process.version}</td>  
       <td style="text-align: center;"><a target="_blank" href='${ctx }/workflow/resource/read.do?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName}</a></td>  
       <td style="text-align: center;"><a target="_blank" href='${ctx }/workflow/resource/read.do?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName}</a></td>  
       <td style="text-align: center;">
       <fmt:formatDate value="${deployment.deploymentTime}" pattern="yyyy-MM-dd HH:mm:ss" />
       </td>  
       <td style="text-align: center;">
       ${process.suspended}|
       <c:if test="${!process.suspended}">
       <a href="processdefinition/update.do?state=suspend&processDefinitionId=${process.id}">挂起</a>
       </c:if>
       <c:if test="${process.suspended}">
       <a href="processdefinition/update.do?state=active&processDefinitionId=${process.id}">激活</a>
       </c:if>
       </td>    
       <td style="text-align: center;" >
       		<a>【编辑】</a>
       	</td>  
   	</tr> 
   </c:forEach>
</table>
</div>
<%@include file="/WEB-INF/jsp/common/page.jsp" %>
</form>
</body>
<script type="text/javascript">
<c:if test="${not empty message}">
	alert("${message}");
</c:if>
function queryData(){
	document.forms[0].submit();
}
function deleteData(){
	if($("input[name='orgId']:checked").length==0){
		alert("请选择要删除的数据！");
		return;
	}
	$.ajax({
	 	type:'POST', 
	 	async:false,
        url:"<%=path%>/security/organization/organizationRemove.do",
        dataType:"text",
        data:$("form").serialize(),
       	success: function(result){
           	if("success"==result){
           		$("form").submit();
           	}
   	    }
		
	});
}
function editData(id){
	window.location.href="<%=path%>/security/organization/organizationEdit.do?id="+id;	
}
function addData(){
	window.location.href="<%=path%>/security/organization/organizationEdit.do";	
}
</script>
</html>