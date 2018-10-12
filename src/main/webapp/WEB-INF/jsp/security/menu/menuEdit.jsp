<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单编辑</title>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/jquery/css/screen.css"" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
</head>
<body>
<form action="<%=path%>/security/menu/saveMenu.do" id="signupForm" method="post" >
<div align="center">
	<table  class="form_table" >
		<tr>
			<th>父级菜单：</th>
			<td>
			 <input type="hidden"  name="pid" value="${menuVo.pid}" />
			 <input type="text" class="textStyle"  value="${menuVo.pName}"/>
			</td>
		</tr>
		<tr>
			<th>菜单名称：</th>
			<td>
				<input type="hidden" class="textStyle" id="id" name="id" value="${menuVo.id}"/>
				<input type="text" class="textStyle" id="name" name="name" value="${menuVo.name}"/>
			</td>
		</tr>
		<tr>
			<th>菜单标题：</th>
			<td>
				<input type="text" class="textStyle" id="title" name="title" value="${menuVo.title}" />
			</td>
		</tr>
		<tr>
			<th>菜单编码：</th>
			<td>
				<input type="text" class="textStyle" id="mcode" name="mcode" value="${menuVo.mcode}" />
			</td>
		</tr>
		<tr>
			<th>菜单图标：</th>
			<td>
				<input type="text"  class="textStyle" id="icon" name="icon" value="${menuVo.icon}" />
			</td>
		</tr>
		<tr>
			<th>菜单状态：</th>
			<td>
				<select name="state">
					<option value="enabled"  <c:if test="${'enabled' eq personVo.state}">selected</c:if> >启用</option>
					<option value="disabled" <c:if test="${'disabled' eq personVo.state}">selected</c:if>>停用</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>菜单链接：</th>
			<td>
				<input type="text"  class="textStyle" id="url" name="url"  value="${menuVo.url}" style="width: 300px;" />
			</td>
		</tr>
	</table>
</div>
<div align="center">
	<input type="button" class="toolBarbtn" onclick="saveMenu();" value="保存"/>
	<input type="button" class="toolBarbtn" onclick="goBack();" value="返回"/>
</div>
</form>
</body>
<script type="text/javascript">
$("#signupForm").validate({
	rules: {
		name: "required",
		title: "required"
	},
	messages: {
		name: "菜单名称不能为空！",
		title:"菜单标题不能为空！"
	}
});
function saveMenu(){
	$("form").submit();
}
function goBack(){
	window.location.href="<%=path%>/security/menu/menuList.do.do";
}

</script>

</html>