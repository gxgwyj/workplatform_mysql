<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/res/jquery-treetable-master/css/jquery.treetable.theme.default.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/res/css/style.css">
<script src="<%=path%>/res/jquery/jquery-1.7.1.min.js"></script>
<script src="<%=path%>/res/jquery-treetable-master/jquery.treetable.js"></script>
<script src="<%=path%>/res/js/treetable.js"></script>
<title>菜单列表</title>
</head>

<body>
   <table id="menuTreeTable"  class="gridtable">
      <tr>
        <th style="width: 20%;">菜单名称</th>
        <th style="width: 20%;">菜单标题</th>
        <th style="width: 20%;">菜单编码</th>
        <th style="width: 20%">菜单链接</th>
        <th style="width: 20%">操作</th>
      </tr>
	  <tr data-tt-id="-1">
	    <td>系统菜单根目录</td>
	    <td></td>
	    <td></td>
	    <td></td>
	    <td style="text-align:center;"></td>
	  </tr>
   </table>
</body>
<script type="text/javascript">
      var option = {
          //点击菜单名称是否相应
          clickableNodeNames:true,
          //可以展开
          expandable: true,
          //table中的某一列作为树形菜单展开
          column:0,
          //父子菜单之间的缩进
          indent:19,
          //默认展开树表格
          initialState:"expanded"
      };
       //添加后台数据
      var menuListJson = ${menuListJson};
      //构造结构化表格
      var trHtml =  new createTable(menuListJson).init('rootMenu');
      $("#menuTreeTable").append(trHtml); 
      $('#menuTreeTable').treetable(option);
      //高亮显示选中的行
      $("#menuTreeTable tbody").on("mousedown", "tr", function() {
         $(".selected").not(this).removeClass("selected");
         $(this).toggleClass("selected");
      });   
 <!--添加菜单-->     
 function addChildern(pId,pName){
    window.location.href = "<%=path%>/security/menu/menuEdit.do?pId="+pId+"&pName="+encodeURI(encodeURI(pName));
 }
 <!--编辑菜单-->     
 function menuEditNew(id){
	 window.location.href = "<%=path%>/security/menu/menuEditNew.do?id="+id;
 }
 <!--删除菜单-->
 function removeMenu(menuId){
	 if(confirm("数据删除后不可恢复，是否删除？")){
		 $.ajax({
			 type:'get',
			 url:'<%=path%>/security/menu/removeMenu.do',
			 data:{id:menuId},
			 dataType:'json',
			 success:function(msg){
				 if(msg.result==true){
					 window.location.href ="<%=path%>/security/menu/menuList.do";
				 }
			 }
		 });
	 }
 }
</script>
</html>