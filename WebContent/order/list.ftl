<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货单列表</title>
</head>

<body>
<a href="../index.action">返回首页</a>
<a href="order_list.action">待处理进货单</a>
<a href="order_listAll.action">所有进货单</a>
<br /><br />
<a href="order.action">添加新进货单</a>
<br /><br />

<div>
<span>页数:${pager.currentPage}/${pager.totalPage}</span>&nbsp;&nbsp;
<span>${pager.getPreviousSlide()}</span>
<span>${pager.getBackLink()}</span>
<span>${pager.getPageLinks()}</span>
<span>${pager.getNextLink()}</span>
<span>${pager.getNextSlide()}</span>&nbsp;&nbsp;
<span>总数:${pager.totalItems}</span>
<div>
<br /><br />

<#if orderList??>
<#list orderList as order>
<div>
日期:${order.createDate?string("yyyy-MM-dd")}<br />
备注:${order.comment}<br />
<a href="order_export.action?id=${order.id?c}" target="_blank">导出XLS档</a>
<a href="order.action?id=${order.id?c}">查看/编辑</a>
<a href="order_item.action?orderId=${order.id?c}">添加购买的宝贝</a>
<#if order.status = 0>
<a href="order_status.action?id=${order.id?c}&status=1">设为已处理</a>
</#if>
</div>
<br />
</#list>
</#if>

</body>
</html>
