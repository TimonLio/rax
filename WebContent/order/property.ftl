<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货单详情</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("order_save_order_fee");
	onFeeChange(obj.value);
});

function setFee(value) {
	var obj = document.getElementById("order_save_order_fee");
	obj.value = value;
}

function onFeeChange(value) {
	var objs = document.getElementsByName("feeSel");
	value = parseFloat(value);
	for (var i = 0; i < objs.length; i++) {
		if (parseFloat(objs[i].value) == value) objs[i].checked = true
		else objs[i].checked = false;
	}
}

</script>
<style type="text/css">
label { cursor:pointer; }
</style>
</head>

<body>
<a href="order_list.action">返回进货单列表</a>
<br /><br />
<#if id != 0>
<#if order.status == 0>
<a href="order_delete.action?id=${id?c}" onclick="return confirm('确实要删除这笔订单么？')">删除</a>
<a href="order_status.action?id=${id?c}&status=1">确认到货</a>
<#else>
<a href="order_status.action?id=${id?c}&status=0">撤除订单</a>
</#if>
</#if>
<br /><br />

<div>
<@s.form action="order_save">
    <#if order??>
	<@s.label label="创建" value="${order.createDate?string('yyyy-MM-dd')}"/>
	</#if>
	<@s.select label="供货商" name="order.vendorId" list="vendorMap"/>
	<@s.textarea label="备注" name="order.comment" cols="80" rows="2"/>
    <@s.textfield label="重量" name="order.weight" />
    <@s.textfield label="运费" name="order.fee" onkeyup="javascript:onFeeChange(this.value)"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}" onclick="javascript:setFee(this.value)"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<#if id != 0>
<a href="order_item.action?orderId=${id?c}">添加新项目</a>
<a href="order_export.action?id=${id?c}" target="_blank">导出XLS档</a>
<br /><br />
</#if>

<#assign total=0/>
<#assign totalSum=0/>
<table>
<tr>
<td>编号</td>
<#if order??>
<#if order.status == 0>
<td>操作</td>
</#if>
</#if>
<td>数量</td>
<td>价格</td>
<td>宝贝名称</td>
</tr>
<#if orderItemList??>
<#list orderItemList as item>
<#assign total=total + item.cost * item.number/>
<tr>
<td>${item_index + 1}</td>
<#if order??>
<#if order.status == 0>
<td><a href="order_item_delete.action?id=${item.id?c}&orderId=${id?c}" onclick="return confirm('确实要删除这项么？')">删</a></td>
</#if>
</#if>
<td>${item.number}</td>
<td>${item.cost}</td>
<td><a href="order_item.action?id=${item.id?c}&orderId=${id?c}" title="${item.cost} * ${item.number}">${item.ware.name}</a></td>
</tr>
</#list>
</#if>
</table><br />

<#if order??>
<#assign totalSum=total + order.fee/>
</#if>

<span>货款: #{total;m2M2}</span><br />
<span>总额: #{totalSum;m2M2}</span>

</body>
</html>
