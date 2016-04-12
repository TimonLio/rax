<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分组宝贝列表</title>
<style type="text/css">
.categoryList ul{ list-style:none; }
.categoryList li{ float:left; margin-right:20px; }
</style>
</head>

<body>
<a href="../index.action">返回首页</a>
<a href="express_property.action">添加新的快递</a>
<br /><br />

<div>总数:<#if expressList??>${expressList.size()}</#if></div><br />

<table>
<tr>
<td>名字</td>
<td>电话</td>
<td>结算日期</td>
<td>操作</td>
</tr>
<#if expressList??>
<#list expressList as ex>
<tr>
<td>${ex.exName}</td>
<td>${ex.phone}</td>
<td>${ex.settleDate?string("yyyy-MM-dd")}</td>
<td><a href="express_property.action?id=${ex.id?c}">查看</a>&nbsp;<a href="express_summary.action?id=${ex.id?c}">结算</a></td>
</tr>
</#list>
</#if>
</table>
<br />

</body>
</html>
