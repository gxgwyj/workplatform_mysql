<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>登录界面</title>
    <script type="text/javascript" src="res/js/md5.js"></script>
    <script type="text/javascript" src="res/jquery/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="res/js/jsencrypt.js"></script>
</head>
<body>
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
            <label for="loginName" style="font-weight: bolder;">账号：</label>
            <input type="text" id="loginName" name="loginName" style="width: 200px;height: 25px;">
         </div>
         <div align="center" style="margin-top: 30px;">
            <label for="pwd" style="font-weight: bolder;">密码：</label>
            <input type="password" id="pwd" name="pwd" style="width: 200px;height: 25px;">
         </div>
          <div align="center" style="margin-top: 30px;">
              <label for="randomCode" style="font-weight: bolder;">验证码：</label>
              <input type="text" id="randomCode" name="randomCode" style="width: 100px;height: 25px;">
              <img src="/open/randomCode.do" alt="验证码，点击更换验证码" onclick="changeImg(this)" style="width: 80px;height: 40px;vertical-align:middle;">
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
</body>
<script type="text/javascript">
    var  publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDvoXplm2SJGGmqq/L31ERYaeRC" +
        "XEjjT/jSNb8PqjnjAz1/gGwaaXwHUmcaLr0LUBq5VY5akoWjsPp46w4fUbrNeF8w" +
        "AhGbwKkwHAAy6jfsxHsPINWUUt1F4fbhPoM2hzp1eg19kKkxaOR7gGEX+BQf7QZI" +
        "8x7aQNuYKex1shLe/QIDAQAB";

function form_submit(){
	if(form_Validate()){
        var requestData = createRequestData();
        $.ajax({
            type:"POST",
            url:"<%=path%>/security/login.do",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data:JSON.stringify(requestData),
            success: function (msg) {
                if ("200" == msg.res_code) {
                    window.location.href="<%=path%>/toPage?path=security/main/main";
                }else {
                    $("#msg_error").html(msg.res_msg);
                    $("#msg_error").show();
                }
            },
        });

	}
}
function createRequestData() {
    var loginName = $("#loginName").val();
    var pwd = hex_md5($("#pwd").val());
    var randomCode = $("#randomCode").val();
    var sign = hex_md5(loginName + pwd + randomCode);
    var obj = {loginName:loginName,pwd:pwd,randomCode:randomCode};
    var jsonStr = JSON.stringify(obj);
    console.log("json格式的字符串："+jsonStr);
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    var data = encrypt.encrypt(jsonStr);
    console.log("加密后的数据串："+data);
    var requestData = {sign:sign,data:data};
    return requestData;

}
<!--验证-->
function form_Validate(){
	var loginName = $("#loginName").val();
	var pwd = $("#pwd").val();
	if(""==loginName || ""==pwd){
		var msg ="" ;
		if(""==loginName && ""==pwd){
			msg = "用户名和密码不能为空";
		}
		if(""==loginName && ""!=pwd) {
            msg = "用户名不能为空";
        }
		if(""!=loginName && ""==pwd) {
            msg = "密码不能为空";
        }
		$("#msg_error").html(msg);
		$("#msg_error").show();
		return false;
	}else{
		$("#msg_error").hide();
		return true;    		
	}
}

function changeImg(obj) {
    $(obj).attr("src","/open/randomCode.do");
}

</script>
</html>