<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>出库单</title>
<style type="text/css">
body { font-size:16px; margin:0px; }
</style>
<style media="print">
.noprint { display: none; }  
</style>
</head>

<body>
<table border="0">
  <tr>
    <td colspan="4" align="center"> 购 物 清 单 - ${sell.customerName}(${sell.customerIM})</td>
  </tr>
  <tr>
    <td height="8" colspan="4" align="center"></td>
  </tr>
  <tr>
    <td >货品名称</td>
    <td width="40" align="right">数量</td>
    <td width="60" align="right">单价</td>
    <td align="right">小节</td>
  </tr>
  <#assign total=sell.fee - sell.discount/>
  <#assign maxlines=8/>
  <#list sellItemList as item>
  <#assign total=total + item.price * item.number/>
  <tr>
    <td>${item.ware.name}</td>
    <td align="right">${item.number}</td>
    <td align="right">#{item.price;m2M2}</td>
    <td align="right">#{(item.price * item.number);m2M2}</td>
  </tr>
  </#list>
  <tr>
    <td height="5" colspan="4"></td>
  </tr>
  <#if sell.discount?number gt 0>
  <#assign maxlines = maxlines - 1/>
  <tr>
    <td colspan="2"></td>
    <td align="right">优惠</td>
    <td align="right">-#{sell.discount;m2M2}</td>
  </tr>
  </#if>
  <tr>
    <td colspan="2"><#if sell.commentInvoice?length gt 0>备注: ${sell.commentInvoice}</#if></td>
    <td align="right">邮费</td>
    <td align="right">+#{sell.fee;m2M2}</td>
  </tr>
  <tr>
    <td colspan="2"><#if sell.sender?length gt 0>${sell.sender}<#else>冰心抹茶</#if> 祝您购物愉快!</td>
    <td align="right">合计</td>
    <td align="right">#{total;m2M2}</td>
  </tr>
  <tr>
    <td colspan="4">本店商品皆为私人消耗品，拆封后恕不退换。</td>  
  </tr>
</table>
<div class="noprint">
<br />
<br />
&nbsp;&nbsp;
<#if sellItemList?size gt maxlines>
<font color="red">需换大纸打印</font>
</#if>
<a href='invoice_input.action?sellId=${sellId?c}'>编辑</a>
</div>
</body>
</html>
