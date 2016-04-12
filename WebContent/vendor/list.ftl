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
<a href="../index.action">返回首页</a>
<a href="vendor.action?id=0">添加供货商</a>
<br /><br />

<div>总数:<#if vendorList??>${vendorList.size()}</#if></div><br />

<#if vendorList??>
<#list vendorList as vendor>
<div>${(vendor_index + 1)?string?left_pad(2)?replace(" ", "&nbsp;")}
<a href="vendor.action?id=${vendor.id?c}" title="${imTypeSel.get(vendor.IMType)}:${vendor.IM}">${vendor.title}</a>
<a href="vendor_history.action?id=${vendor.id?c}">进货历史</a>
</div>
</#list>
</#if>

</body>
</html>
