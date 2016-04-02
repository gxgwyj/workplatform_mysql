<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色编辑</title>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/jquery/css/screen.css"" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
</head>
<body>
<form action="<%=path%>/security/role/roleSave.do" id="signupForm" method="post" >
<div align="center">
	<table  class="form_table" >
		<tr>
			<th>角色名称：</th>
			<td>
				<input type="text" class="textStyle" name="rName" value="${role.rName}"/>
				<input type="hidden" id="rId"  name="rId" value="${role.rId}" />
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td>
				<select name="state">
					<option value="enabled"  <c:if test="${'enabled' eq personVo.state}">selected</c:if> >启用</option>
					<option value="disabled" <c:if test="${'disabled' eq personVo.state}">selected</c:if>>停用</option>
				</select>
			</td>
		</tr>
	</table>
</div>
<div align="center">
	<input type="button" class="toolBarbtn" onclick="saveRole();" value="保存"/>
	<input type="button" class="toolBarbtn" onclick="goBack();" value="返回"/>
</div>
</form>
</body>
<script type="text/javascript">
	function saveRole(){
		$("form").submit();
	}
	function goBack(){
		window.location.href="<%=path%>/security/role/roleList.do";
	}
	$("#signupForm").validate({
		rules: {
			rName: "required"
		},
		messages: {
			rName: "角色名称不能为空！"
		}
	});
</script>
</html>