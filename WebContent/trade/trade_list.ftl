<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>淘宝在线</title>
<link type="text/css" href="../css/smoothness/jquery-ui-1.8.11.css" rel="stylesheet" />	
<script language="javascript" type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-ui-1.8.11.min.js"></script>
<script language="javascript" type="text/javascript">

$(document).ready(function() {

	$("tr.title").click(function() {
		$(this).find("input:checkbox").each(function() { this.checked = !this.checked; });
		//alert("tr on click");
		//return false;
	});
	
	//$("input:checkbox").click(function() { alert("checkbox on click:" + this.checked); return false; });

	$("#btn_submit").click(function() {
		var tids = [];
		$("input:checkbox:checked").each(function() { tids.push($(this).attr("value")); });
		//alert("/erp/trade/trade_input.action?tids=" + tids.join(","));
		window.location.href = "/erp/trade/trade_input.action?tids=" + tids.join(",");
	});
});

</script>
<style media="print">
body { display: none; }
</style>
<style type="text/css">
.right { text-align:right; width:40px; }
.mid { text-align:center; width:40px; }
.wide { width:160px; }
td { padding: 3px; }
.title { background: #DDD; }
</style>
</head>

<body>
<a href="../index.action">返回首页</a>
<br /><br />

<div>当前在线订单数：<#if trades??>${trades.size()}<#else>0</#if></div>
<br />

<#if trades??>
<table>
<#list trades as t>
<tr class="title">
<td class="" colspan="2">
	<input type="checkbox" value="${t.tid?c}"/>
	订单编号：${t.tid?c}
</td>
<td class="" colspan="2">成交时间：${t.created?string("yyyy-MM-dd HH:mm:ss")}</td>
<td class="">买家旺旺：${t.buyerNick}</td>
</tr>

<#list t.orders as o>
<tr>
<td class=""><img src="${o.picPath}" width="50" title="${o.title}" /></td>
<td class="">${o.title}</td>
<td class="">${o.price}</td>
<td class="">${o.num}</td>
<#if o_index==0>
<td class="" rowspan="${t.orders?size}">
	${t.payment}<br />
	(含快递 ${t.postFee})<br />
	<#if t.buyerMessage??><img src="../images/memo.png" title="${t.buyerMessage}"/></#if>
</td>
</#if>
</tr>

<tr>
<td colspan="5"></td>
</tr>

</#list>

<tr><td colspan="5">&nbsp;</td></tr>
</#list>
</table>

<input id="btn_submit" type="button" value=" 提 交 " />
</#if>

</body>
</html>
