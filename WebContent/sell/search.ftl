<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单列表</title>
<style type="text/css">
.comment { color: red; }
</style>
</head>

<body>
<@s.form action="sell_search" theme="simple">
    <@s.textfield label="关键字" name="keyword"/>&nbsp;
    <@s.submit value=" 提 交 "/>
</@s.form>
<br />

<div>符合条件出库单数:<#if sellList??>${sellList.size()}<#else>0</#if></div>
<br />

<#if sellList??>
<#list sellList as sell>
<div>
${imTypeSel.get(sell.customerIMType)}:${sell.customerIM}
<#if sell.customerIMComment != "">
<span class="comment">${sell.customerIMComment}</span>
</#if><br />
姓名:${sell.customerName}<br />
日期:${sell.sendDate?string("yyyy-MM-dd")}<br />
地址:${sell.customerAddress}<br />
<a href="sell.action?id=${sell.id?c}">查看/编辑</a>
</div>
<br />
</#list>
</#if>

</body>
</html>
