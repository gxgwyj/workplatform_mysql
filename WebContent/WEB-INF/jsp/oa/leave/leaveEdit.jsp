<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建请假</title>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/jquery/css/screen.css"" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
<script type="text/javascript" src="<%=path%>/res/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="<%=path%>/security/role/roleSave.do" id="signupForm" method="post" >
<div align="center">
	<table  class="form_table" >
		<tr>
			<th>申请人：</th>
			<td>
				<input type="text" class="textStyle" value="${sessionScope.sessionUser.name}"/>
			</td>
		</tr>
		<tr>
			<th>请假类型：</th>
			<td>
				<select id="leaveType" name="leaveType">
					<option value="1">公休</option>
					<option value="2">病假</option>
					<option value="3">调休</option>
					<option value="4">事假</option>
					<option value="5">婚假</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>开始时间：</th>
			<td>
				<input type="text"  class="Wdate" onFocus="WdatePicker();"  id="birth" name="birth" value="<fmt:formatDate value="${personVo.birth}" pattern="yyyy-MM-dd" />" style="cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<th>结束时间：</th>
			<td>
				<input type="text"  class="Wdate" onFocus="WdatePicker();"  id="birth" name="birth" value="<fmt:formatDate value="${personVo.birth}" pattern="yyyy-MM-dd" />" style="cursor: pointer;"/>
			</td>
		</tr>
		<tr>
			<th>请假原因：</th>
			<td>
				<textarea rows="10" cols="50"></textarea>
			</td>
		</tr>
	</table>
</div>
<div align="center">
	<input type="button" class="toolBarbtn" onclick="saveRole();" value="保存"/>
	<input type="button" class="toolBarbtn" onclick="goBack();" value="申请"/>
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