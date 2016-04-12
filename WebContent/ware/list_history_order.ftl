<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货记录</title>
</head>

<body>
<a href="ware.action?id=${id}&status=${status}&categoryId=${categoryId}">返回</a>
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

<table>
<tr>
<td>供货商</td>
<td>进货时间</td>
<td>价格</td>
<td>数量</td>
</tr>
<#if orderItemList??>
<#list orderItemList as orderItem>
<tr>
<#if orderItem.order?? && orderItem.order.vendorId != -1>
<td>${vendorMap.get(orderItem.order.vendorId)}</td>
<#else>
<td>未知供货商</a>
</#if>
<td>${orderItem.order.createDate?string("yyyy-MM-dd")}</td>
<td>#{orderItem.cost;m2M2}</td>
<td>${orderItem.number}</td>
<tr>
</#list>
</#if>
</table>
<br />

</body>
</html>
