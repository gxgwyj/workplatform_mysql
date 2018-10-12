<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户编辑页面</title>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/jquery/css/screen.css"" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/res/My97DatePicker/WdatePicker.js"></script>
<%@include file="/WEB-INF/jsp/common/js.jsp" %>
</head>
<body>
<form action="<%=path%>/security/person/savePerson.do" id="signupForm" method="post" enctype="multipart/form-data">
<div align="center">
	<table  class="form_table"  >
		<tr>
			<th>用户编码：</th>
			<td>
			 <input type="hidden" id="id"  name="id" value="${personVo.id}" />
			 <input type="text" class="textStyle" id="code" name="code" value="${personVo.code}" onkeyup="editloginName(this);"/>
			</td>
			<td rowspan="5" style="text-align: center;width: 120px;">
			<input type="hidden" name="img" value="${personVo.img}">
			<img style="width: 120px;height: 150px;" src="<%=path%>/media/image/getImage.do?path=${personVo.img}">
			</td>
		</tr>
		<tr>
			<th>登录名：</th>
			<td ><input type="text" class="textStyle" id="loginname" name="loginname" value="${personVo.loginname}"/></td>
		</tr>
		<tr>
			<th>中文姓名：</th>
			<td ><input type="text" class="textStyle" id="name" name="name" value="${personVo.name}"/></td>
		</tr>
		<tr>
			<th>英文姓名：</th>
			<td ><input type="text"  class="textStyle" id="ename" name="ename" value="${personVo.ename}"/></td>
		</tr>
		<tr>
		<th >性别：</th>
			<td >
				<select name="sex">
					<option value="男" <c:if test="${'男' eq personVo.sex}">selected</c:if> >男</option>
					<option value="女" <c:if test="${'女' eq personVo.sex}">selected</c:if> >女</option>
				</select>
			</td>
		</tr>
		<tr>
		<th>身份证号：</th>
			<td >
				<input type="text"  class="textStyle" id="card"  name="card"  value="${personVo.card}" />
			</td>
			<td >
				<input type="file" name="personImg">
			</td>
		</tr>
		<tr>
		<th>出生年月：</th>
			<td  colspan="2" >
				<input type="text"  class="Wdate" onFocus="WdatePicker();"  id="birth" name="birth" value="<fmt:formatDate value="${personVo.birth}" pattern="yyyy-MM-dd" />" style="cursor: pointer;"/>
				</td>
		</tr>
		<tr>
		<th>民族：</th>
			<td  colspan="2" >
				<input type="text"  class="textStyle" id="national" name="national"  value="${personVo.national}" />
			</td>
		</tr>
		<tr>
		<th>家庭电话：</th>
			<td  colspan="2" >
				<input type="text"  class="textStyle"  id="tel" name="tel" value="${personVo.tel}" />
			</td>
		</tr>
		<tr>
		<th>移动电话：</th>
			<td  colspan="2" >
				<input type="text"  class="textStyle"  id="phone" name="phone" value="${personVo.phone}" />
			</td>
		</tr>
		<tr>
		<th>电子邮箱：</th>
			<td  colspan="2" >
				<input type="text"  class="textStyle" id="email" name="email" value="${personVo.email}" />
			</td>
		</tr>
		<tr>
		<th>家庭住址：</th>
			<td  colspan="2" >
				<textarea style="width:300px;" id="address" name="address" >${personVo.address}</textarea>
			</td>
		</tr>
		<tr>
			<th>所属部门：</th>
			<td  colspan="2" >
				<select name="orgId" id="orgId" >
					<option value="">请选择</option>
					<c:if test="${not empty organizationList}">
					  <c:forEach items="${organizationList}" var="organization">
					  	<option value="${organization.oId}"  <c:if test="${personVo.orgId eq organization.oId}">selected</c:if> >${organization.oName}</option>
					  </c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
			<tr>
		<th>状态：</th>
			<td  colspan="2" >
				<select name="state" id="state" >
					<option value="enabled"  <c:if test="${'enabled' eq personVo.state}">selected</c:if> >启用</option>
					<option value="disabled" <c:if test="${'disabled' eq personVo.state}">selected</c:if>>停用</option>
				</select>
			</td>
		</tr>
	</table>
</div>
<div align="center">
	<input type="button" class="toolBarbtn" onclick="savePerson();" value="保存"/>
	<input type="button" class="toolBarbtn" onclick="goBack();" value="返回"/>
</div>
</form>
</body>
<script type="text/javascript">
	function savePerson(){
		$("form").submit();
	}
	function goBack(){
		history.back(-1);
	}
	$("#signupForm").validate({
		rules: {
			code: "required",
			name: "required",
			ename: "required",
			card: "required",
			birth: "required",
			national: "required",
			tel: "required",
			phone: "required",
			email: "required",
			address: "required"
		},
		messages: {
			code: "用户编码不能为空！",
			name: "中文姓名不能为空！",
			ename:"英文姓名不能为空！",
			card: "身份证号不能为空！",
			birth:"出生年月不能为空！",
			national:"民族不能为空！",
			tel: "家庭电话不能为空！",
			phone:"移动电话不能为空！",
			email:"电子邮箱不能为空！",
			address: "家庭住址不能为空！"
		}
	});
	function editloginName(obj){
		$("#loginname").val(obj.value);
	}
</script>
</html>