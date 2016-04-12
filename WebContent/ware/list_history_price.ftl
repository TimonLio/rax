<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史进货价格统计</title>
</head>

<body>
<a href="ware.action?id=${id}&status=${status}&categoryId=${categoryId}">返回</a>
<br /><br />

<div>总数:<#if historyList??>${historyList.size()}<#else>0</#if></div><br />

<table>
<tr>
<td>供货商</td>
<td>平均进价</td>
<td>最低进价</td>
<td>最高进价</td>
<td>数量</td>
</tr>
<#if historyList??>
<#list historyList as history>
<tr>
<td><#if history.vendor??>${history.vendor.title}<#else>未知供货商</#if></td>
<td>#{history.avg;m2M2}</td>
<td>#{history.min;m2M2}</td>
<td>#{history.max;m2M2}</td>
<td>${history.sum}</td>
<tr>
</#list>
</#if>
</table>
<br />

</body>
</html>
