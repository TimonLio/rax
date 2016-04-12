<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检查买家收货信息</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

function setDate(value) {
	var obj		= dojo.byId("express_date");
	obj.value	= this.value;
}

function onDateChange(event) {
	var objs = document.getElementsByName("sel");
	for (var i = 0; i < objs.length; i++) {
		objs[i].checked = (objs[i].value == this.value) ? true : false;
	}
}

function checkPhoneNumber(objId) {
	if (dojo.byId(objId).value.length != 11) {
		dojo.byId("express_labelPhone").style.display = "block";
	}
}

dojo.addOnLoad(function (){
	var obj		= dojo.byId("express_date");
	var sels	= document.getElementsByName("sel");
	var sel;
	dojo.connect(obj, "onchange", obj, onDateChange);
	for (var i = 0; i < sels.length; i++) {
		sel	= sels[i];
		dojo.connect(sel, "onclick", sel, setDate);
		if (sel.value == obj.value) sel.checked = true;
	}
	checkPhoneNumber("express_sell_customerPhone1");
});

</script>
<style type="text/css">
label { cursor:pointer; }
#express_labelPhone { color: red; display:none; }
</style>
<style media="print">
body { display: none; }
</style>
</head>

<body>
<div>
<@s.form action="express">
    <@s.textfield label="姓名" name="sell.customerName"/>
    <@s.textarea label="地址" name="sell.customerAddress" cols="80" rows="2"/>
    <@s.textfield label="电话1" name="sell.customerPhone1"/>
    <@s.label name="labelPhone" value="电话号码位数不正确"/>
    <@s.textfield label="电话2" name="sell.customerPhone2"/>
    <@s.textfield label="邮编" name="sell.customerPostCode"/>
    <@s.textfield label="日期" name="date"/>
    <@s.radio name="sel" list="dateSel"/>
    <@s.radio label="快递" name="sell.expressId" list="expressSel"/>
    <@s.textarea label="快递单备注" name="sell.commentExpress" cols="80" rows="2"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.textfield label="发件人电话" name="senderPhone"/>
    <@s.textarea label="发件人地址" name="senderAddress" cols="80" rows="2"/>
    <@s.textfield label="发件人邮编" name="senderPostCode"/>
    <@s.hidden name="sell.expressId"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
