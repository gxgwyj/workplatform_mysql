<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.pageStyle{
margin-top: 5px;
padding-top:10px;
height: 30px;
}
.pageStyle a{
	border: 1px solid black;
	padding:5px;
    cursor: pointer;
    
}
.pageStyle a:hover{
	border: 1px solid #38f;
    background-color: #f2f8ff none repeat scroll 0 0;
}
</style>    
<div align="center" class = "pageStyle">
<input type="hidden" name="pages" value="${page.pages}">
<input type="hidden" name="pageNum" value="${page.pageNum}"/>
<input type="hidden" name="isTurnPage" value="true"/>
<a onclick="goToPage(1);"><strong>首页</strong></a>
<c:if test="${page.pageNum eq 1}">
  <a><strong>上一页</strong></a>
</c:if>
<c:if test="${page.pageNum gt 1}">
  <a onclick="goToPage(${page.pageNum}-1);"><strong>上一页</strong></a>
</c:if>
<c:if test="${page.pageNum eq page.pages}">
  <a><strong>下 一页</strong></a>
</c:if>
<c:if test="${page.pageNum lt page.pages}">
  <a onclick="goToPage(${page.pageNum}+1);"><strong>下一页</strong></a>
</c:if>
<a onclick="goToPage(${page.pages});"><strong>末页</strong></a>
<span><strong>跳转到 <input id="toPage" name="toPage"  type="text" style="height: 20px;width: 30px;">页</strong></span>
<a onclick="turnPage();"><strong>GO</strong></a>
<span style="margin-left: 50px;"><strong>共计&nbsp;&nbsp;${page.pages}&nbsp;&nbsp;页&nbsp;&nbsp;每页&nbsp;&nbsp;${page.pageSize}&nbsp;&nbsp;条信息 &nbsp;&nbsp;当前第&nbsp;&nbsp;${page.pageNum}&nbsp;&nbsp;页</strong></span>
</div>
<script type="text/javascript">
function goToPage(pageNo){
	var pageTotal = document.all.pages.value;
	if(pageNo=="" || Number(pageNo)==0){
		return false;
	} 
	if(Number(pageNo)>Number(pageTotal)){
		alert("超出翻页范围！！");
		return false;
	}
	document.all.pageNum.value = pageNo;
	document.forms[0].action =window.location.href;
	document.forms[0].submit();
}
function turnPage(){
	var pageNo = $("#toPage").val();
	goToPage(pageNo);
}
</script>