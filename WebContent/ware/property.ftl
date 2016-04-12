<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝属性</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

function parseToken(name) {
	var params = {
		tokenize: name
	}
	dojo.xhrPost({
		url: "/erp/json/tokenize.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			console.debug(json);
			dojo.byId("ware_save_tokenize").value = json.tokenize;
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response.status);	}
	});
}

function onSubmit(event) {
	var id			= dojo.byId("ware_save_id").value;
	var tokenize	= dojo.byId("ware_save_tokenize").value;
	if (id == 0 && tokenize.length == 0) {
		parseToken();
		return false;
	}
	return true;
}

dojo.addOnLoad(function (){
	var obj;
	
	obj = dojo.byId("ware_save_ware_name");
	var name = dojo.trim(obj.value);
	if (name.length > 0) parseToken(name);
	
	obj = dojo.byId("ware_save");
	//dojo.connect(obj, "onsubmit", obj, onSubmit);
});

</script>
</head>

<body>
<#if status != 1>
<a href="ware_list.action?categoryId=${categoryId}">返回</a>
<#else>
<a href="ware_list_hid.action">返回</a>
</#if>
<a href="ware_list_history_order.action?id=${id?c}&status=${status}&categoryId=${categoryId}">进货记录</a>
<a href="ware_list_history_price.action?id=${id?c}&status=${status}&categoryId=${categoryId}">进货统计</a>
<#if id != 0>
<a href="ware_delete.action?id=${id}" onclick="return confirm('确实要删除这个宝贝么？')">删除</a>
</#if>
<br /><br />

<div>
<@s.form action="ware_save">
    <@s.textfield label="名称" name="ware.name"/>
    <@s.textfield label="条码" name="ware.barcode"/>
    <@s.textfield label="成本" name="ware.cost"/>
    <@s.textfield label="参考价格" name="ware.lastPrice"/>
    <@s.textfield label="数量" name="ware.number"/>
    <@s.textfield label="警戒数量" name="ware.numberAlarm"/>
    <@s.radio label="启用警戒" name="ware.numberAlarmEnable" list="{0, 1}"/>
    <@s.select label="分类" name="categoryId" list="categoryMap"/>
    <@s.textarea rows="3" cols="40" label="关键词" name="tokenize"/>
    <@s.hidden name="id"/>
    <@s.hidden name="status"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
