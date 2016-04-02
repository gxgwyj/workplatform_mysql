<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/common/ext.jsp" %>
<link href="<%=path%>/res/css/style.css" rel="stylesheet" type="text/css" />
<title>系统主界面</title>
</head>
<body>
  <div id="pageTitle"  class="maintop">
     <span class="spanuser">
        <img src="<%=path%>/res/images/png/user.png" >
        <font color="red" style="font-weight: bold;">${sessionScope.userInfo.name}</font>
     </span>
     <span class="spanbtn">
        <img  src="<%=path%>/res/images/png/logout.png" style="cursor: pointer;" title="退出登录" onclick="logout();">
     </span>
  </div>
  <!--操作区域-->
  <div id="pageCenter" width="600px;" >
  </div>
</body>
<script type="text/javascript">
   var menuTree = eval(${menuTree});
   <!--菜单数据-->
   var store = Ext.create('Ext.data.TreeStore', {
    root: {
        expanded: true,
        children: [
              menuTree
        ]
    }
  });
  var treePanel=Ext.create('Ext.tree.Panel', {
       title: '导航菜单',
       region: 'west',
       width: 150,
       store: store,
       collapsible: true,
       rootVisible: false,
       margin: '0 5 0 0'
     });
     <!--事件添加-->
     treePanel.on('itemclick', function(view, record) {
          //如果是树的子节点，则新建一个tab标签页，显示超连接    
          if (record.get('leaf')) {
        	    var tabId = record.get('id');
        	    var tab = Ext.getCmp(tabId);
        	    var tabPanel=Ext.getCmp('tabPanel');
        	    if(typeof(tab) == 'undefined'){
        	    	var url="<%=path%>/"+record.get('url');
                    var tabTitle=record.get('text');
                    tab={
                       id:tabId,		
                       title:tabTitle,
                       html:"<iframe src=\""+url+"\" height=\"500px\" width=\"100%\" frameborder=\"0\"></iframe>",
                       closable:true
                    };        	    	
        	    }
                //创建tab并且是tab直接处于活动状态
                tabPanel.setActiveTab(tab);
             }
     });
  <!--tabPanel-->
  var tabPanel=Ext.create('Ext.tab.Panel', {
      id:'tabPanel',
      region:'center',
      activeTab:0,
      contentEl:'pageCenter'
  });
  <!--toolPanel-->
  var toolPanel=Ext.create('Ext.panel.Panel',{
      id:'toolPanel',
      region: 'east',
      title: '系统工具',
      collapsible: true,
      split: true,
      width: 150
  }); 
  <!--sysInfoPanel-->
  var sysInfoPanel=Ext.create('Ext.panel.Panel',{
      region: 'south',
	  title: '系统信息'
	  /**
      collapsible: true,
	  split: true,
	  height: 100,
      minHeight: 100
      **/
  });
  <!--topPanel-->
  var topPanel=Ext.create('Ext.panel.Panel',{
      region: 'north',
      contentEl: 'pageTitle',
      border: false,
      margin: '0 0 5 0'
  });
 <!--页面框架布局-->   
 Ext.create('Ext.container.Viewport', {
          layout: 'border',
          items: [
                   topPanel,
                   treePanel,
                   sysInfoPanel,
			       tabPanel
                 ]
});

<!--退出登录-->
function logout(){
    if(confirm("您确定要退出系统？")){
       document.location.href="<%=path%>/security/logout.do";
    }
}
<!--设置mainFrame的高度-->
(function(){
//var mainHeight=Ext.getCmp('centerPanel').getSize().height;
//document.getElementById("mainFrame").height=mainHeight;
})();
<!--选项卡操作-->
function addTab(url,target){
   var tab=new {
       title: 'Tab 1',
       html : 'A simple tab'
   };
}

</script>
</html>