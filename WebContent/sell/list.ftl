<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单列表</title>
<style media="print">
body { display: none; }
</style>
<style type="text/css">
.comment { color: red; }
</style>
</head>

<body>
<a href="index.action">返回首页</a>
<a href="sell_list.action?currentPage=${pager.currentPage}">待处理发货单</a>
<a href="sell_list_all.action?currentPage=${pager.currentPage}">所有发货单</a>
<a href="sell_search.action" target="_blank">搜索发货单</a>
<br /><br />
<a href="sell.action">添加新出库单</a>
<a href="sell_import_input.action">从淘宝地址导入出库单</a><br /><br />

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

<#if sellList??>
<#list sellList as sell>
<div>
${imTypeSel.get(sell.customerIMType)}:${sell.customerIM}
<#if sell.customerIMComment != "">
<span class="comment">${sell.customerIMComment}</span>
</#if><br />
姓名:${sell.customerName}<br />
<#if status = -1>
日期:${sell.sendDate?string("yyyy-MM-dd")}<br />
</#if>
地址:${sell.customerAddress}<br />
<a href="sell.action?id=${sell.id?c}">查看/编辑</a>
<a href="sell_item_import_input.action?sellId=${sell.id?c}">从淘宝导入售出记录</a>
<a href="express_input.action?sellId=${sell.id?c}" target="_balnk">打印快递单</a>
<a href="invoice.action?sellId=${sell.id?c}" target="_balnk">打印发货单</a>
<#if sell.status = 0>
<a href="sell_update_status.action?status=${status}&id=${sell.id?c}&s=1">设为已处理</a>
<#else>
<a href="sell_update_status.action?status=${status}&id=${sell.id?c}&s=0">设为未处理</a>
</#if>
</div>
<br />
</#list>
</#if>

</body>
</html>
