<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入买家收货信息</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">
function setFee(event) {
	var value = isNaN(event) ? this.value : event;
	var obj = dojo.byId("sell_import_fee");
	obj.value = value;
}
function setFeeReal(event) {
	var value = isNaN(event) ? this.value : event;
	var obj = dojo.byId("sell_import_feeReal");
	obj.value = value;
}
function setDate(event) {
	var obj		= dojo.byId("sell_import_date");
	obj.value	= this.value;
}
function onFeeChange(event) {
	var value = isNaN(event) ? parseFloat(this.value) : event;
	var objs = document.getElementsByName("feeSel");
	for (var i = 0; i < objs.length; i++) {
		if (parseFloat(objs[i].value) == value) objs[i].checked = true
		else objs[i].checked = false;
	}
}
function onFeeRealChange(event) {
	var value = isNaN(event) ? parseFloat(this.value) : event;
	var objs = document.getElementsByName("feeRealSel");
	for (var i = 0; i < objs.length; i++) {
		if (parseFloat(objs[i].value) == value) objs[i].checked = true;
		else objs[i].checked = false;
	}
}
function onDateChange(event) {
	var objs = document.getElementsByName("sel");
	for (var i = 0; i < objs.length; i++) {
		objs[i].checked = (objs[i].value == this.value) ? true : false;
	}
}
function onImportContent(event) {
	var addr = this.value;
	var addrs;
	/*
	addrs = addr.split("\n");
	//console.debug("addrs[0]:" + addrs[0]);
	//console.debug("addrs[1]:" + addrs[1]);
	//console.debug("addrs[2]:" + addrs[2]);
	//console.debug("addrs[3]:" + addrs[3]);

	addrs	= addrs[1].split("：");
	addr	= dojo.trim(addrs[1]);
	addrs	= addr.split(" ");
	//console.debug("addrs[0]:" + addrs[0]);
	//console.debug("addrs[1]:" + addrs[1]);
	//console.debug("addrs[2]:" + addrs[2]);
	//console.debug("addrs[3]:" + addrs[3]);
	*/

	addrs = addr.split("，");
	if (addrs.length < 2) return;
	addr = addrs[2];
	if (addrs.length > 4) {
		addr = addrs[3];
	}
	addrs = dojo.trim(addr).split(" ");
	console.debug("addrs:" + addrs);

	switch (addrs[0]) {
		case "浙江省":
		case "江苏省":
		case "上海市":
		case "上海":
			fee = 5;
			feeReal = 4.5;
			break;
		case "安徽省":
            fee = 10;
			feeReal = 8;
			break;
		case "山东省":
		case "广东省":
		case "福建省":
		case "北京":
		case "北京市":
		case "天津":
		case "天津市":
		case "河南省":
		case "河北省":
		case "湖南省":
		case "湖北省":
		case "江西省":
			fee = 10;
			feeReal = 9;
			break;
		case "西藏自治区":
		case "新疆维吾尔自治区":
            fee = 15;
			feeReal = 15;
			break;
		default:
			fee = 10;
			feeReal = 10;
			if (addrs[0] == "四川省" && addrs[1] == "成都市") feeReal = 9;
	}
	//console.debug("addr:" + addr + " fee:" + fee + " feeReal:" + feeReal);

	setFee(fee);
	onFeeChange.call(null, fee);

	setFeeReal(feeReal);
	onFeeRealChange.call(null, feeReal);
}
dojo.addOnLoad(function (){
	var obj;
	var sels;
	var sel;

	obj	= dojo.byId("sell_import_date");
	dojo.connect(obj, "onchange", obj, onDateChange);
	sels = document.getElementsByName("sel");
	for (var i = 0; i < sels.length; i++) {
		sel = sels[i];
		dojo.connect(sel, "onclick", sel, setDate);
		if (sel.value == obj.value) {
			sel.checked = true;
		}
	}

	obj	= dojo.byId("sell_import_fee");
	dojo.connect(obj, "onkeyup", obj, onFeeChange);
	sels = document.getElementsByName("feeSel");
	for (var i = 0; i < sels.length; i++) {
		dojo.connect(sels[i], "onclick", sels[i], setFee);
		if (Math.floor(sels[i].value) == obj.value) sels[i].checked = true;
		else sels[i].checked = false;
	}

	obj	= dojo.byId("sell_import_feeReal");
    dojo.connect(obj, "onkeyup", obj, onFeeRealChange);
	sels = document.getElementsByName("feeRealSel");
	for (var i = 0; i < sels.length; i++) {
		dojo.connect(sels[i], "onclick", sels[i], setFeeReal);
		if (Math.floor(sels[i].value) == obj.value) sels[i].checked = true;
		else sels[i].checked = false;
	}

	obj = dojo.byId("sell_import_content");
	dojo.connect(obj, "onkeyup", obj, onImportContent);
	//dojo.connect(obj, "onfocus", obj, obj.select);
});
</script>
<style type="text/css">
label { cursor:pointer; }
</style>
</head>

<body>
<a href="sell_list.action">返回</a>
<br />

<div>
<@s.form action="sell_import">
    <@s.textarea label="地址" name="receiverAddress" cols="80" rows="4"/>
    <@s.textfield label="日期" name="date"/>
    <@s.radio name="sel" list="dateSel"/>
    <@s.textfield label="收取运费" name="fee"/>
    <@s.radio name="feeSel" list="{0, 5, 10, 12, 15, 20, 25}"/>
    <@s.textfield label="实际运费" name="feeReal"/>
    <@s.radio name="feeRealSel" list="{0, 3.5, 4, 5, 8, 10, 12, 15, 20, 25}"/>
    <@s.radio label="快递" name="expressId" list="expressSel"/>
    <@s.textfield label="折扣" name="discount"/>
    <@s.textfield label="快递单备注" name="commentExpress"/>
    <@s.textfield label="发货单备注" name="commentInvoice"/>
    <@s.textfield label="发件人" name="sender"/>
    <@s.hidden name="sellId"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
