<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>快递费结算</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8',parseOnLoad:true"></script>
<script language="javascript" type="text/javascript">
dojo.require("dijit.form.DateTextBox");
dojo.require("dojo.parser");
</script>
<style type="text/css">
@import "../js/dijit/themes/tundra/tundra.css";
.float { float:left; margin-right:12px; width:80px; text-align:right; }
.row { clear:both; }
#saveButton { display: none; }
</style>

</head>

<body class="tundra">
<a href="express_list.action">返回</a>
<a href="express_save_settle_date.action?id=${id?c}&endDate=${endDate}">保存结算日期</a>
<br /><br />

<div>
<form action="express_summary.action" method="POST">
<label for="startDate">起始日期</label>
<input type="text" id="startDate" name="startDate" value="${startDate}" dojoType="dijit.form.DateTextBox" length="20"/>
<label for="startDate">截至日期</label>
<input type="text" id="endDate" name="endDate" value="${endDate}" dojoType="dijit.form.DateTextBox" length="20"/>
<input type="hidden" name="id" value="${id?c}"/>
<input type="submit" value=" 计 算 " />
</form>
</div>
<br />

<div>
<span>件数: <#if sellList??>${sellList.size()}<#else>0</#if> 件</span><br />
<span>结算: <#if sell??>#{sell.feeReal;m2M2}<#else>0</#if> 元</span>
</div>
<br />

<#assign total=0/>
<table>
<tr>
<td>编号</td>
<td>日期</td>
<td>费用</td>
<td>地址</td>
<td>联系方式</td>
<td>操作</td>
</tr>
<#if sellList??>
<#list sellList as sell>
<tr>
<td>${sell_index + 1}</td>
<td>${sell.sendDate?string("yyyy-MM-dd")}</td>
<td>#{sell.feeReal;m2M2}</td>
<td>${sell.customerAddress}</td>
<td>${sell.customerName}(${imTypeSel.get(sell.customerIMType)}:${sell.customerIM})</td>
<td><a href="../sell.action?id=${sell.id?c}" target="_blank">查看</a></td>
</tr>
</#list>
</#if>
</table><br />

</body>
</html>
