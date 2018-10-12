<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
<title>组织架构列表</title>
</head>
<body>
<form action="<%=path%>/security/organization/organizationList.do" method="post">
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
<table width = "100%"  class="datalist">  
    <tr>  
       <th style="width:5%;"><input type="checkbox" onclick="checkAll(this,'orgId');"/></th> 
       <th style="width:17%;">部门代码</th>  
       <th style="width:17%;">部门名称</th>  
       <th style="width:17%;">上级部门</th>  
       <th style="width:10%;">状态</th>
       <th style="width:17%;">操作</th>  
   </tr>  
   <c:forEach  items="${organizationList}" var="organization">
   	  <tr>  
   	   <td style="text-align: center;"><input type="checkbox"  name="orgId" value="${organization.id}" /></td>
       <td style="text-align: center;">${organization.code}</td>  
       <td style="text-align: center;">${organization.name}</td>  
       <td style="text-align: center;">${organization.pName}</td>  
       <td style="text-align: center;">
       <c:if test="${organization.state eq '1'}">启用</c:if>
       <c:if test="${organization.state eq '0'}"><font color="red">停用</font></c:if>
       </td>
       <td style="text-align: center;" >
       		<a onclick="editData('${organization.id}');">【编辑】</a>
       	</td>  
   	</tr> 
   </c:forEach>
</table>
</div>
<%@include file="/WEB-INF/jsp/common/page.jsp" %>
</form>
</body>
<script type="text/javascript">
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