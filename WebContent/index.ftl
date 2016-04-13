<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ERP</title>
</head>

<body>
<p>
<a href="stat/stat_summary.action">统计数据</a>
<a href="express/express_list.action">快递公司</a>
<a href="order/order_list.action">进货单列表</a>
<a href="vendor/vendor_list.action">供货商</a>
<a href="ware_list.action">宝贝列表</a>
<a href="ware_category_list.action">分类列表</a>
<a href="sell_list.action">发货单列表</a>
</p>

<table>
<tr>
<td>数量</td>
<td>警戒数</td>
<td>宝贝名称</td>
<td>操作</td>
</tr>
<#if wareList??>
<#list wareList as ware>
<tr>
<td>${ware.number}</td>
<td>#{ware.numberAlarm}</td>
<td><a href="ware.action?id=${ware.id?c}">${ware.name}</a></td>
<td><a href="ware_alarm.action?id=${ware.id?c}">暂时不提醒</a></td>
</tr>
</#list>
</#if>
</table>
<br />

</body>
</html>