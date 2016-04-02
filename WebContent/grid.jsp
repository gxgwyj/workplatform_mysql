<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织架构列表</title>
<%@include file="/WEB-INF/jsp/common/ext.jsp" %>>
</head>
<body>

</body>
<script type="text/javascript">
Ext.onReady(function () {
	var pageUrl ="<%=path%>/security/organization/organizationListJson.do";
    Ext.define("Province", {
        extend: "Ext.data.Model",
        fields: [
            { name: "ProvinceID" },
            { name: "ProvinceNo" },
            { name: "ProvinceName" }
        ]
    });

    var store = Ext.create("Ext.data.JsonStore", {
        pageSize: 10, // 分页大小
        model: "Province",
        proxy: {
            type: "ajax",
            url: pageUrl,
            reader: {
                type: "json",
                root: 'records',
                totalProperty  : 'recordTotal'
            }
        }
    });
    store .load({ params: { start: 0, limit: 10} });

    Ext.create("Ext.grid.Panel", {
        title: "Ext.grid.Panel",
        renderTo: Ext.getBody(),
        frame: true,
        height: 310,
        width: 400,
        store: store,
        columns: [
            { header: "ID", width: 50, dataIndex: "ProvinceID", sortable: true },
            { header: "编号", width: 100, dataIndex: "ProvinceNo", sortable: true },
            { header: "名称", width: 135, dataIndex: "ProvinceName", sortable: true }
        ],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg: '显示{0}-{1}条，共{2}条',
            emptyMsg: "没有数据"
        })
    });
});
</script>
</html>