<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
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
       <th style="width:5%;"><input type="checkbox" onclick="checkAll(this,'leId');"/></th> 
       <th>类型</th>  
       <th>申请人</th>
       <th>申请时间</th>
       <th>开始时间</th>
       <th>结束时间</th>
       <th>当前节点</th>   
       <th>流程状态</th>
       <th>当前处理人</th>
   </tr>  
   <c:forEach  items="${myleaveList}" var="role">
   	  <tr>  
   	   <td style="text-align: center;"><input type="checkbox"  name="rId" value="${role.rId}" /></td>
       <td style="text-align: center;">${role.rName}</td>  
       <td style="text-align: center;">
       <c:if test="${role.state eq 'enabled'}">启用</c:if>
       <c:if test="${role.state eq 'disabled'}"><font color="red">停用</font></c:if>
       </td>
       <td style="text-align: center;" >
       		<a onclick="editData('${role.rId}');">【编辑】</a>
       		<a onclick="editMenu('${role.rId}');">【权限】</a>
       		<a onclick="editData('${role.rId}');">【用户】</a>
       	</td>  
   	</tr> 
   </c:forEach>
</table>
</div>
<%@include file="/WEB-INF/jsp/common/page.jsp" %>
</form>
<div id="w" class="easyui-window" title="角色权限" data-options="modal:true,closed:true" style="width:400px;height:400px;padding:10px;">
<div id="treeView" class="ztree" style="height: 300px;"></div>
<div align="center">
	<input type="button" value="保存" onclick="assignMenu();">
</div>
</div>
</body>
<script type="text/javascript">
function queryData(){
	document.forms[0].submit();
}
function addData(){
	window.location.href="<%=path%>/oa/leave/creatForm.do";	
}
function editMenu(id){
	roleId = id;
	//获取树形菜单的json格式
	$.ajax({
	 	type:'POST', 
	 	async:false,
        url:"<%=path%>/security/role/getRoleMenuListJson.do?roleId="+id,
        dataType:"json",
       	success: function(data){
       	    var zNodes1=data;
       	    zTreeObject=$.fn.zTree.init($("#treeView"),setting,zNodes1);
       		$('#w').window('open');
   	    }
		
	});

}
//保存分配的功能菜单
function assignMenu(){
   //获得被选中的checkbox
   var checkedNodes=zTreeObject.getCheckedNodes();
   var menuIdsStr="";
   if(checkedNodes.length>0){
	   menuIdsStr =  checkedNodes[0].id;
   }
   for(var i=1;i<checkedNodes.length;i++){
	   menuIdsStr=menuIdsStr+","+checkedNodes[i].id;
   }
   //ajax表单提交
   $.ajax({
	   type:'POST', 
 	   async:false,
       url:"<%=path%>/security/menu/assignMenu.do",
       dataType:"json",
       data:{"menuIdsStr":menuIdsStr,"roleId":roleId},
       success: function(result){
    		zTreeObject=$.fn.zTree.init($("#treeView"),setting,result);
	   }
		
	});
}         
</script>
</html>