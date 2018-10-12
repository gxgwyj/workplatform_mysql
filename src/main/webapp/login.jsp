<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>登录界面</title>
</head>
<body>
<form id="login_form" action="<%=path%>/security/login.do" method="post">
      <div style="background: #DBEAF9;height: 100px;">
        <div style="float: left;">
         <img alt="工作流程管理平台" src="<%=path%>/res/images/logo.png" style="margin-left: 20px;margin-top: 30px;">
        </div>
        <div style="float: left;margin-top: 30px; margin-left: 20px;">
         <span style="font-size: 40px;font-weight: bolder;">工作流程管理平台</span>
        </div>
      </div>
      <div style="margin-top: 100px;">
         <div align="center">
            <label for="code" style="font-weight: bolder;">账号：</label>
            <input type="text" id="code" name="code" style="width: 200px;height: 25px;">
         </div>
         <div align="center" style="margin-top: 30px;">
            <label for="pwd" style="font-weight: bolder;">密码：</label>
            <input type="password" id="pwd" name="pwd" style="width: 200px;height: 25px;">
         </div>
         <div align="center" style="margin-top: 30px;">
         	<div id="msg_error" class="msg_error" style="border: 1px solid #e4393c;width: 250px;display: none;"></div>
         </div>
         <div align="center" style="margin-top: 30px;">
           <div style="border: 1px solid;width: 250px;height: 30px;line-height: 30px;font-weight: bolder;cursor: pointer;background: #1B7EC3;" onclick="form_submit();">登&nbsp;&nbsp;&nbsp;录</div>
         </div>
      </div>
       <div align="center" style="background: #DBEAF9;height: 80px; margin-top: 120px; line-height: 80px;"  >
            <div>版本信息:1.1.1.20151001</div>
       </div>
       </form>
</body>
<script type="text/javascript">
<!--表单提交-->
function form_submit(){
	if(form_Validate()){
		document.getElementById("login_form").submit();
	}
}
<!--验证-->
function form_Validate(){
	var code = document.getElementById("code").value;
	var pwd = document.getElementById("pwd").value;
	if(""==code || ""==pwd){
		var msg ="" ;
		if(""==code && ""==pwd){
			msg = "用户名和密码不能为空";
		}
		if(""==code && ""!=pwd){
			msg = "用户名不能为空";
		}
		if(""!=code && ""==pwd){
			msg = "密码不能为空";
		}
		document.getElementById("msg_error").innerHTML = msg;
		document.getElementById("msg_error").style.display = "block";
		return false;
	}else{
		document.getElementById("msg_error").style.display = "none";
		return true;    		
	}
}
<!--验证回传信息-->
<c:if test="${loginMsg!=null}">
   document.getElementById("msg_error").innerHTML = "${loginMsg.showMsg}";
   document.getElementById("msg_error").style.display = "block";
</c:if>

</script>
</html>