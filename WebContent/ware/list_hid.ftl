<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>被隐藏的宝贝列表</title>
</head>

<body>
<a href="index.action">返回首页</a>
<a href="ware_list.action?categoryId=${categoryId}">返回宝贝列表</a>
<br /><br />
<div>总数:<#if wareList??>${wareList.size()}<#else>0</#if></div><br />

<#if wareList??>
<#list wareList as ware>
<div>
<a href="ware.action?id=${ware.id?c}&status=${status}">${ware.name}</a>
</div>
</#list>
</#if>
<br />

</body>
</html>
