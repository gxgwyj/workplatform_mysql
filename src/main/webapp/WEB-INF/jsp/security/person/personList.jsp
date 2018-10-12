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
<title>组织架构列表</title>
</head>
<body>
<form action="<%=path%>/security/person/personList.do" method="post">
<div class="searchForm">
  <table>
  	<tr>
  		<td>用户代码：<input type="text" name="qCode" value="${queryPersonVo.qCode}"></td>
  		<td>用户名称：<input type="text" name="qName" value="${queryPersonVo.qName}"></td>
  		<td>性别：
  			<select name="qSex" id="qSex" >
					<option value="">请选择</option>
				  	<option value="男"  <c:if test="${queryPersonVo.qSex eq '男'}">selected</c:if> >男</option>
				  	<option value="女"  <c:if test="${queryPersonVo.qSex eq '女'}">selected</c:if> >女</option>
			</select>
  		</td>
  		<td>所属部门：
  			<select name="qOrgId" id="qOrgId" >
					<option value="">请选择</option>
					<c:if test="${not empty organizationList}">
					  <c:forEach items="${organizationList}" var="organization">
					  	<option value="${organization.oId}"  <c:if test="${queryPersonVo.qOrgId eq organization.oId}">selected</c:if> >${organization.oName}</option>
					  </c:forEach>
					</c:if>
			</select>
  		</td>
  		<td><input type="button" value="查询" onclick="queryData();" class="toolBarbtn" ></td>
  	</tr>
  </table>	
</div>
<div class="toolBarForm">
  <input type="button" class="toolBarbtn" value="添加" onclick="addData();"  >
  <input type="button" class="toolBarbtn"  value="导出">
</div>
<div class="dataMain">
<table width = "100%"  class="datalist">  
    <tr>  
       <th >序号</th> 
       <th >用户代码</th>
       <th >登录名</th>  
       <th >部门</th>
       <th >用户姓名</th>  
       <th >性别</th>  
       <th >出生年月</th>
       <th >民族</th>
       <th >移动电话</th>
       <th >邮箱</th>
       <th >状态</th>
       <th >操作</th>   
   </tr>  
   <c:forEach  items="${personVoList}" var="personVo" varStatus="status">
   	  <tr>  
   	   <td style="text-align: center;">${status.count}</td>
       <td style="text-align: center;">${personVo.code}</td>  
       <td style="text-align: center;">${personVo.loginname}</td>  
       <td style="text-align: center;">${personVo.orgName}</td>
       <td style="text-align: center;">${personVo.name}</td>
       <td style="text-align: center;">${personVo.sex}</td>
       <td style="text-align: center;">
       	<fmt:formatDate value="${personVo.birth}" pattern="yyyy-MM-dd" />
       	</td>
       <td style="text-align: center;">${personVo.national}</td>
       <td style="text-align: center;">${personVo.phone}</td>
       <td style="text-align: center;">${personVo.email}</td>        
       <td style="text-align: center;">
       <c:if test="${personVo.state eq 'enabled'}">启用</c:if>
       <c:if test="${personVo.state eq 'disabled'}"><font color="red">停用</font></c:if>
       </td>
       <td style="text-align: center;" >
       		<a onclick="editData('${personVo.id}');">【编辑】</a>
       		<a onclick="editRole('${personVo.id}');">【角色设置】</a>
       	</td>  
   	</tr> 
   </c:forEach>
</table>
</div>
<%@include file="/WEB-INF/jsp/common/page.jsp" %>
</form>
<div id="w" class="easyui-window" title="用户角色设置" data-options="modal:true,closed:true" style="width:500px;height:400px;padding:10px;">
<table>
<tr>
	<td>
		已有角色：
		<select id="roles"  style="width:200px;height: 300px;" size="15"  multiple=”multiple” >
		</select>
	</td>
	<td style="text-align: center;">
		<input type="button" onclick="addPersonRole();" value="<<">
		<input type="button" onclick="removePersonRole();" value=">>" >
	</td>
	<td>
		未有角色：
		<select id="noroles" style="width:200px;height: 300px;" size="15"  multiple=”multiple” >
		</select>
	</td>
</tr>
</table>
</div>
</body>
<script type="text/javascript">
function queryData(){
	document.forms[0].submit();
}
var perId = "";
function editData(id){
	window.location.href="<%=path%>/security/person/personEdit.do?id="+id;	
}
function addData(){
	window.location.href="<%=path%>/security/person/personEdit.do";	
}
function editRole(personId){
	perId = personId;
	$.ajax({
	 	type:'POST', 
	 	async:false,
        url:"<%=path%>/security/role/getPersonRoleJson.do?personId="+personId,
        dataType:"json",
       	success: function(result){
       		$("#roles").empty(); 
       		$("#noroles").empty(); 
       		$(result.roles).each(function(i,n){
       			$("#roles").append("<option value='"+n.rId+"'>"+n.rName+"</option>");
       		});
	        $(result.noroles).each(function(i,n){
	        	$("#noroles").append("<option value='"+n.rId+"'>"+n.rName+"</option>");
       		});
       		$('#w').window('open');
   	    }
		
	});
}
function addPersonRole(){
	var options = $("#noroles").find("option:selected");
	if(options.length>0){
		var roleIdsStr = options[0].value;
		for(var i = 1;i<options.length;i++){
			roleIdsStr += ","+options[i].value;
		}
		$.ajax({
		 	type:'POST', 
		 	async:false,
	        url:"<%=path%>/security/role/addPersonRole.do",
	        data:{"personId":perId,"roleIdsStr":roleIdsStr},
	        dataType:"text",
	       	success: function(result){
	       		if("success" == result){
	       			options.remove();
	       			$(options).each(function(i,n){
		       			$("#roles").append("<option value='"+n.value+"'>"+n.text+"</option>");
		       		});
	       		}else{
	       			alert("添加失败！");
	       		}
	   	    }
			
		});
	}
}
function removePersonRole(){
	var options = $("#roles").find("option:selected");
	if(options.length>0){
		var roleIdsStr = options[0].value;
		for(var i = 1;i<options.length;i++){
			roleIdsStr += ","+options[i].value;
		}
		$.ajax({
		 	type:'POST', 
		 	async:false,
	        url:"<%=path%>/security/role/removePersonRoles.do",
	        data:{"personId":perId,"roleIdsStr":roleIdsStr},
	        dataType:"text",
	       	success: function(result){
	       		if("success" == result){
	       			options.remove();
	       			$(options).each(function(i,n){
		       			$("#noroles").append("<option value='"+n.value+"'>"+n.text+"</option>");
		       		});
	       		}else{
	       			alert("添加失败！");
	       		}
	   	    }
			
		});
	}
	
}
</script>
</html>