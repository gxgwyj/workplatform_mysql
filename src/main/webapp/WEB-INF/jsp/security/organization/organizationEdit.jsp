<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑组织结构</title>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/jquery/css/screen.css"" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
</head>
<body>
<form action="<%=path%>/security/organization/organizationSave.do" id="signupForm" method="post" >
<div align="center">
	<table  class="form_table" >
		<tr>
			<th>部门编码：</th>
			<td>
			 <input type="hidden" id="oId"  name="oId" value="${organization.id}" />
			 <input type="text" class="textStyle" name="oCode" value="${organization.code}"/>
			</td>
		</tr>
		<tr>
			<th>部门名称：</th>
			<td><input type="text" class="textStyle" name="oName" value="${organization.name}"/></td>
		</tr>
		<tr>
			<th>上级部门：</th>
			<td>
				<input type="text" id="pName" class="textStyle"  value="${organization.pName}" style="cursor: pointer;" readonly="readonly" onclick="showParentDept(this);"/>
				<input type="hidden"  id="pId" name="oPid" value="${organization.pId}" />
				</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td>
				<select name="state">
					<option value="1">启用</option>
					<option value="0">停用</option>
				</select>
			</td>
		</tr>
	</table>
</div>
<div align="center">
	<input type="button" class="toolBarbtn" onclick="saveOrg();" value="保存"/>
	<input type="button" class="toolBarbtn" onclick="goBack();" value="返回"/>
</div>
</form>
<div id ="deptDiv" class="showDataDiv" >
	<div style="height: 30px;">
		<a class="closeBtn"  onclick="cancelP();">取消上级部门</a>
		<a class="closeBtn" onclick="closeWindow();">关闭</a>
	</div>
	<div>
                <ul>
                    <li><a>部门1</a></li>
                    <li><a>部门1</a></li>
                    <li><a>部门1</a></li>
                    <li><a>部门1</a></li>
                    <li><a>部门1</a></li>
                </ul>
     </div>           
</div>
</body>
<script type="text/javascript">
	function saveOrg(){
		$("form").submit();
	}
	function goBack(){
		window.location.href="<%=path%>/security/organization/organizationList.do";
	}
	$("#signupForm").validate({
		rules: {
			oCode: "required",
			oName: "required"
		},
		messages: {
			oCode: "部门编码不能为空！",
			oName: "部门名称不能为空！"
		}
	});
	function showParentDept(obj){
		$.ajax({
				type:"get",
				url:"<%=path%>/security/organization/getOrganizationListJson.do",
				dataType: "json", 
				success:function(result){
					var jsonObj = result;
					var strHtml = "";
					for(var i = 0;i<jsonObj.length;i++){
						if(jsonObj[i].oId != $("#oId").val()){
							strHtml+="<li><a onclick=\"selectParentDept('"+jsonObj[i].oName+"','"+jsonObj[i].oId+"');\">"+jsonObj[i].oName+"</a></li>";
						}
					}
					$("#deptDiv").find("ul").html(strHtml);
				}
		});
		var heightVal = $(obj).height();
		var x = $(obj).offset().top;
		var y = $(obj).offset().left;
	 	$("#deptDiv").css("position", "absolute"); 
	    $("#deptDiv").css("top",x+heightVal); 
        $("#deptDiv").css("left", y); 
        $("#deptDiv").show();
	}
	function selectParentDept(pName,pId){
		$("#pName").val(pName);
		$("#pId").val(pId);
		$("#deptDiv").hide();
	}
	function closeWindow(){
		$("#deptDiv").hide();
	}
	function cancelP(){
		$("#pName").val("");
		$("#pId").val("");
		$("#deptDiv").hide();
	}
</script>
</html>