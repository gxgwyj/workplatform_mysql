<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/res/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/res/jquery-easyui-1.4.4/themes/icon.css">
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
<script type="text/javascript" src="<%=path%>/res/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<title>请假列表</title>
</head>
<body>
<form action="<%=path%>/security/role/roleList.do" method="post">
<div class="searchForm">
  <table>
  	<tr>
  		<td>角色名称：<input type="text" name="qrName" value="${queryRoleVo.qrName}"></td>
  		<td><input type="button" value="查询" onclick="queryData();" class="toolBarbtn" ></td>
  	</tr>
  </table>	
</div>
<div class="toolBarForm">
  <input type="button" class="toolBarbtn" value="新建请假" onclick="addData();"  >
</div>
<div class="dataMain">
<table  class="datalist" style="width: 100%;">  
    <tr>  
       <th>类型</th>  
       <th>申请人</th>
       <th>申请时间</th>
       <th>开始时间</th>
       <th>结束时间</th>
       <th>当前节点</th>   
       <th>任务创建时间</th>
       <th>操作</th>
   </tr>  
   <c:forEach  items="${list}" var="leave">
	   	<c:set var="task" value="${leave.task}" />
		<c:set var="pInstance" value="${leave.processInstance}" />
  		<tr id="${leave.id}" tid="${task.id }" >
			<td style="text-align: center;">
			<c:if test="${'1' eq leave.leaveType}">公休</c:if>
			<c:if test="${'2' eq leave.leaveType}">病假</c:if>
			<c:if test="${'3' eq leave.leaveType}">调休</c:if>
			<c:if test="${'4' eq leave.leaveType}">事假</c:if>
			<c:if test="${'5' eq leave.leaveType}">婚假</c:if>
			</td>
			<td style="text-align: center;" >${leave.applyName}</td>
			<td style="text-align: center;"><fmt:formatDate value="${leave.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			<td style="text-align: center;"><fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			<td style="text-align: center;"><fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			<td style="text-align: center;">
				<a title="点击查看流程图" href='#' onclick="showActiveProcess('${pInstance.id}','${pInstance.processDefinitionId}');" >${task.name}</a>
			</td>
			<td style="text-align: center;"><fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			<td style="text-align: center;">
				<c:if test="${empty task.assignee }">
					<a href="<%=path%>/oa/leave/task/claim.do?taskId=${task.id}">签收</a>
				</c:if>
				<c:if test="${not empty task.assignee }">
					<a  tkey='${task.taskDefinitionKey}' tname='${task.name }' leaveId='${leave.id}' href="#" onclick="doLeave(this,'${task.id}');">办理</a>
				</c:if>
			</td>
	</tr>
   </c:forEach>
</table>
</div>
<%@include file="/WEB-INF/jsp/common/page.jsp" %>
</form>
<div id="w" class="easyui-window" title="流程当前节点" data-options="modal:true,closed:true" style="width:715px;height:325px;padding:10px;">
<img id="activeImg">
</div>
<div id="leaveDetail" class="easyui-window" title="请假详情" data-options="modal:true,closed:true" style="width:500px;height:350px;padding:10px;">
<table align="center">
	<tr>
		<td>请假人</td>
		<td>
		  <input type="text" id ="applyName" readonly="readonly">
		  <input type="hidden" id = "taskId" >
		</td>
	</tr>
	<tr>
		<td>申请时间</td>
		<td><input type="text" id ="applyTime" readonly="readonly"></td>
	</tr>
	<tr>
		<td>请假种类</td>
		<td><input type="text" id ="leaveType" readonly="readonly"></td>
	</tr>
	<tr>
		<td>开始时间</td>
		<td><input type="text" id ="startTime" readonly="readonly"></td>
	</tr>
	<tr>
		<td>结束时间</td>
		<td><input type="text" id ="endTime" readonly="readonly"></td>
	</tr>
	<tr>
		<td>请假原因</td>
		<td>
		<textarea  id ="reason" readonly="readonly" rows="" cols="">
		</textarea>
		</td>
	</tr>
	<tr>
		<td>审批意见</td>
		<td>
		<textarea  id ="suggestion" rows="" cols="">
		</textarea>
		</td>
	</tr>
</table>
<div align="center">
   <input type="button" value= "同意" onclick="agreeLeave();" />
   <input type="button" value= "驳回" onclick="dismiss();"/>
</div>
</div>
</body>
<script type="text/javascript">
function showActiveProcess(pid,pdid){
	//获取树形菜单的json格式
	$("#activeImg").attr("src","<%=path%>/workflow/process/getActiveActivityImage.do?pid="+pid+"&pdid="+pdid);
	$('#w').window('open');
}
<c:if test="${not empty message}">
alert("${message}");
</c:if>
function doLeave(obj,taskId){
	var leaveId = $(obj).attr("leaveId");
	$.ajax({
		type:"get",
		url:"<%=path%>/oa/leave/getLeaveJson.do?leaveId="+leaveId,
		dataType:"json",
		success:function(leave){
			$("#applyName").val(leave.applyName);
			$("#applyTime").val(leave.applyTime);
			if(leave.leaveType=="1"){
				$("#leaveType").val("公休");
			}
			if(leave.leaveType=="2"){
				$("#leaveType").val("病假");
			}
			if(leave.leaveType=="3"){
				$("#leaveType").val("调休");
			}
			if(leave.leaveType=="4"){
				$("#leaveType").val("事假");
			}
			if(leave.leaveType=="5"){
				$("#leaveType").val("婚假");
			}
			$("#startTime").val(leave.startTime);
			$("#endTime").val(leave.endTime);
			$("#reason").html(leave.reason);
			$("#taskId").val(taskId);
		}
	});
	$('#leaveDetail').window('open');
}
//申请通过
function agreeLeave(){
	complate(true);
}
//申请驳回
function dismiss(){
	complate(false);
}
//办理任务完成
function complate(isAgree){
	var suggestion = $("#suggestion").val();
	var taskId = $("#taskId").val();
	window.location.href = "<%=path%>/oa/leave/complate.do?taskId="+taskId+"&isAgree="+isAgree+"&suggestion="+encodeURI(encodeURI(suggestion));
}
</script>
</html>