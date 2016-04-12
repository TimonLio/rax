<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货单详情</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">
dojo.require("dojo.number");
var totalAmount = 0;
function setFee(value) {
	var obj = document.getElementById("sell_save_fee");
	obj.value = value;
	updateTotalSpan(value, null);
}
function setFeeReal(value) {
	var obj = document.getElementById("sell_save_feeReal");
	obj.value = value;
}
function onFeeChange(value) {
	var objs = document.getElementsByName("feeSel");
	value = parseFloat(value);
	console.debug("onFeeChange:" + value);
	for (var i = 0; i < objs.length; i++) {
		if (parseFloat(objs[i].value) == value) objs[i].checked = true
		else objs[i].checked = false;
	}
	updateTotalSpan(value, null);
}
function onFeeRealChange(value) {
	var objs = document.getElementsByName("feeRealSel");
	value = parseFloat(value);
	//console.debug("onFeeRealChange:" + value);
	for (var i = 0; i < objs.length; i++) {
	    radioValue = parseFloat(objs[i].value);
		if (radioValue == value) objs[i].checked = true;
		else objs[i].checked = false;
	}
}
function setDate(event) {
	var obj		= dojo.byId("sell_save_date");
	obj.value	= this.value;
}
function onDateChange(event) {
	var objs = document.getElementsByName("sel");
	for (var i = 0; i < objs.length; i++) {
		objs[i].checked = (objs[i].value == this.value) ? true : false;
	}
}
function updateTotalSpan(newFee, newDiscount) {
	var objTotal = dojo.byId("span_total");
	var objTotalSum = dojo.byId("span_total_sum");
	var fee = (newFee == null) ? parseFloat(dojo.byId("sell_save_fee").value) : parseFloat(newFee);
	var discount = (newDiscount == null) ? parseFloat(dojo.byId("sell_save_discount").value) : parseFloat(newDiscount);
	if (isNaN(discount)) discount = 0;
	console.debug("totalAmount:" + totalAmount + " fee:" + fee + " discount:" + discount);
	objTotal.innerHTML = dojo.number.format(totalAmount - discount, {places:2});
	objTotalSum.innerHTML = dojo.number.format(totalAmount + fee - discount, {places:2});
}
function onDiscountChange(event) {
	var objDiscount = dojo.byId("sell_save_discount");
	var objAmount = dojo.byId("sell_save_amount");
	var newDiscount = parseFloat(objDiscount.value);
	if (isNaN(newDiscount)) newDiscount = 0;
	var newAmount = totalAmount - newDiscount;
	objAmount.value = dojo.number.format(newAmount, {places:2});
	if (event != null) updateTotalSpan(null, newDiscount);
}
function onAmountChange(event) {
	var objDiscount = dojo.byId("sell_save_discount");
	var objAmount = dojo.byId("sell_save_amount");
	var newAmount = parseFloat(objAmount.value);
	if (isNaN(newAmount)) return;
	var newDiscount = totalAmount - newAmount;
	objDiscount.value = dojo.number.format(newDiscount, {places:2});
	if (event != null) updateTotalSpan(null, newDiscount);
}
function checkPhoneNumber(objId) {
	if (dojo.byId(objId).value.length != 11) {
		dojo.byId("sell_save_labelPhone").style.display = "block";
	}
}
dojo.addOnLoad(function (){
	var obj;
	var sels;
	var sel;

	obj = dojo.byId("sell_save_fee");
	onFeeChange(obj.value);

	obj = dojo.byId("sell_save_feeReal");
	onFeeRealChange(obj.value);

	obj	= dojo.byId("sell_save_discount");
	dojo.connect(obj, "onkeyup", obj, onDiscountChange);
	
	obj	= dojo.byId("sell_save_amount");
	dojo.connect(obj, "onkeyup", obj, onAmountChange);

	obj	= dojo.byId("sell_save_date");
	dojo.connect(obj, "onchange", obj, onDateChange);
	sels = document.getElementsByName("sel");
	for (var i = 0; i < sels.length; i++) {
		sel = sels[i];
		dojo.connect(sel, "onclick", sel, setDate);
		if (sel.value == obj.value) {
			sel.checked = true;
		}
	}
	
	checkPhoneNumber("sell_save_customerPhone1");
});
</script>
<style type="text/css">
label { cursor:pointer; }
.float { float:left; margin-right:12px; width:40px; }
#sell_save_labelPhone { color: red; display:none; }
</style>
</head>

<body>
<a href="sell_list.action">返回发货单列表</a>
<#if id != 0>
<a href="express_input.action?sellId=${id?c}" target="_balnk">打印快递单</a>
<a href="sell_delete.action?id=${id?c}" onclick="return confirm('确实要删除这笔交易么？')">删除</a>
</#if>
<br /><br />

<div>
<@s.form action="sell_save">
    <@s.textfield label="IM" name="customerIM"/>
    <@s.radio label="类型" name="customerIMType" list="imTypeSel"/>
    <@s.textfield label="备注" name="customerIMComment"/>
    <@s.textfield label="姓名" name="customerName"/>
    <@s.textarea label="地址" name="customerAddress" cols="80" rows="2"/>
    <@s.textfield label="日期" name="date"/>
    <@s.radio name="sel" list="dateSel"/>
    <@s.textfield label="电话1" name="customerPhone1"/>
    <@s.label name="labelPhone" value="电话号码位数不正确"/>
    <@s.textfield label="电话2" name="customerPhone2"/>
    <@s.textfield label="邮编" name="customerPostCode"/>
    <@s.textfield label="收取运费" name="fee" onkeyup="javascript:onFeeChange(this.value)"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}" onclick="javascript:setFee(this.value)"/>
    <@s.textfield label="实际运费" name="feeReal" onkeyup="javascript:onFeeRealChange(this.value)"/>
    <@s.radio name="feeRealSel" list="{0, 3.5, 4, 5, 8, 10, 12, 15, 20, 25}" onclick="javascript:setFeeReal(this.value)"/>
    <@s.radio label="快递" name="expressId" list="expressSel"/>
    <@s.textfield label="折扣" name="discount"/>
    <@s.textfield label="折后合计" name="amount"/>
    <@s.textfield label="快递单备注" name="commentExpress"/>
    <@s.textfield label="发货单备注" name="commentInvoice"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<#if id != 0>
<a href="sell_item.action?sellId=${id?c}">添加购买的宝贝</a>
<a href="sell_item_import_input.action?sellId=${id?c}">从淘宝导入售出记录</a>
<a href="invoice.action?sellId=${id?c}" target="_balnk">打印发货单</a><br /><br />

<#assign total=0/>
<#assign totalSum=0/>
<table>
<tr>
<td>编号</td>
<td>操作</td>
<td>数量</td>
<td>价格</td>
<td>宝贝名称</td>
<td>剩余库存</td>
</tr>
<#if sellItemList??>
<#list sellItemList as item>
<#assign total=total + item.price * item.number/>
<tr>
<td>${item_index + 1}</td>
<td><a href="sell_item_delete.action?id=${item.id?c}&sellId=${id?c}" onclick="return confirm('确实要删除这项么？')">删</a></td>
<td>${item.number}</td>
<td>#{item.price;m2M2}</td>
<td><a href="sell_item.action?id=${item.id?c}">${item.ware.name}</a></td>
<td>${item.ware.number}</td>
</tr>
</#list>
</#if>
</table><br />

<#if sellItemList??>
<#assign totalSum=total + fee?number - discount?number/>
<script language="javascript" type="text/javascript">
totalAmount=parseFloat(#{total;m2M2});
console.debug("totalAmount:" + totalAmount);
onAmountChange(null);
onDiscountChange(null);
</script>
</#if>

<span>合计: <span id="span_total">#{total;m2M2}</span></span><br />
<span>总计: <span id="span_total_sum">#{totalSum;m2M2}</span></span>
</#if>

</body>
</html>
