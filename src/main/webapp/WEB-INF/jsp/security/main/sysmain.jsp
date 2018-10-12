<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/common/ext.jsp" %>
<link href="res/css/style.css" rel="stylesheet" type="text/css" />
<title>系统主界面</title>
</head>
<body>
  <div id="pageTitle"  class="maintop">
     <span class="spanuser">
        <img src="res/img/png/user.png" >
        <font color="red" style="font-weight: bold;">${sessionScope.userInfo.name}</font>
     </span>
     <span class="spanbtn">
        <img  src="res/img/png/logout.png" style="cursor: pointer;" title="退出登录" onclick="logout();">
     </span>
  </div>
</body>
<script type="text/javascript">
<!--Ext grid-->
var gridStore=Ext.create('Ext.data.Store', {
    storeId:'simpsonsStore',
    fields:['name', 'email', 'phone'],
    data:{'items':[
        { 'name': 'Lisa',  "email":"lisa@simpsons.com",  "phone":"555-111-1224"  },
        { 'name': 'Bart',  "email":"bart@simpsons.com",  "phone":"555-222-1234" },
        { 'name': 'Homer', "email":"homer@simpsons.com",  "phone":"555-222-1244"  },
        { 'name': 'Marge', "email":"marge@simpsons.com", "phone":"555-222-1254"  }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'items'
        }
    }
});

var gridPanel=Ext.create('Ext.grid.Panel', {
    title: '部门列表',
    store: Ext.data.StoreManager.lookup('simpsonsStore'),
    columns: [
        { text: 'Name',  dataIndex: 'name' },
        { text: 'Email', dataIndex: 'email', flex: 1 },
        { text: 'Phone', dataIndex: 'phone' }
    ],
    height: 300,
    width: 800,
    <!--分页-->
    bbar: Ext.create('Ext.PagingToolbar', {
                    displayInfo: true,
                    emptyMsg: "没有数据"
                })
});
   //菜单数据
   var store = Ext.create('Ext.data.TreeStore', {
    root: {
        expanded: true,
        children: [
            { text: "系统管理", expanded: true, children: [
                { text: "组织管理",
                  children:[
                     { text: "部门管理", leaf: true},
                     { text: "人员管理", leaf: true}
                  ] 
                },
                { text: "权限管理", leaf: true}
            ] },
            { text: "buy lottery tickets", leaf: true }
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
    Ext.create('Ext.container.Viewport', {
          layout: 'border',
          items: [
                   {
                    region: 'north',
                    contentEl: 'pageTitle',
                    border: false,
                    margin: '0 0 5 0'
                   },
                   treePanel,
                   {
			        region: 'south',
			        title: '系统信息',
			        collapsible: true,
			        split: true,
			        height: 100,
			        minHeight: 100
                   }, 
                   {
			        region: 'east',
			        title: '系统工具',
			        collapsible: true,
			        split: true,
			        width: 150
			       }, 
			       {
			        region: 'center',
			        xtype: 'tabpanel', 
			        activeTab: 0,      
			        items:
			        [
			        {
			            title: '部门管理',
			            items:[
			               gridPanel
			            ]
			         }
			         ]
                  }
                 ]
});

<!--退出登录-->
function logout(){
    if(confirm("您确定要退出系统？")){
       document.location.href="system/logout.do";
    }
}

</script>
</html>