<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商列表</title>
<style media="print">
body { display: none; }
</style>
<style type="text/css">
</style>
</head>

<body>
<a href="vendor_list.action">返回供货商列表</a>
<br /><br />

<div>总数:<#if historyList??>${historyList.size()}</#if></div><br />

<table>
<tr>
<td>宝贝名称</td>
<td>平均进价</td>
<td>最低进价</td>
<td>最高进价</td>
<td>数量</td>
</tr>
<#if historyList??>
<#list historyList as history>
<tr>
<td>${history.ware.name}</a>
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
