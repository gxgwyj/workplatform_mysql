<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/res/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/res/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/res/zTree_v3/css/zTreeStyle/zTreeStyle.css" >
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
<script type="text/javascript" src="<%=path%>/res/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path%>/res/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=path%>/res/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<title>角色列表</title>
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
  <input type="button" class="toolBarbtn" value="添加" onclick="addData();"  >
  <input type="button" class="toolBarbtn"  value="删除" onclick="deleteData();">
  <input type="button" class="toolBarbtn"  value="导出">
</div>
<div class="dataMain">
<table  class="datalist" style="width: 100%;">  
    <tr>  
       <th style="width:5%;"><input type="checkbox" onclick="checkAll(this,'rId');"/></th> 
       <th>角色名称</th>
       <th>角色编码</th>   
       <th>状态</th>
       <th>操作</th>  
   </tr>  
   <c:forEach  items="${page.records}" var="role">
   	  <tr>  
   	   <td style="text-align: center;"><input type="checkbox"  name="rId" value="${role.rId}" /></td>
       <td style="text-align: center;">${role.rName}</td>
       <td style="text-align: center;">${role.rCode}</td>    
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
//定义树对象
var zTreeObject;
var roleId="";
//setting 配置
var setting={
   treeId:"ztree1",
   view:{showLine: true,showIcon:true},
   data:{simpleData:{enable: true,idKey:"id",pIdKey: "pId"}},
	check:{
		enable: true,
    	chkStyle: "checkbox",
    	chkboxType: { "Y": "ps", "N": "ps" }
 }
};
function queryData(){
	document.forms[0].submit();
}
function deleteData(){
	if($("input[name='rId']:checked").length==0){
		alert("请选择要删除的数据！");
		return;
	}
	$.ajax({
	 	type:'POST', 
	 	async:false,
        url:"<%=path%>/security/role/roleRemove.do",
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
	window.location.href="<%=path%>/security/role/roleEdit.do?id="+id;	
}
function addData(){
	window.location.href="<%=path%>/security/role/roleEdit.do";	
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