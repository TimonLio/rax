<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看/编辑</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var wareList;

function addOption(objSelect, text, value, expend) {
	var objOption	= document.createElement("OPTION");
	var obj			= objSelect.appendChild(objOption);
	objOption.text	= text;
	objOption.value	= value;
	//console.debug("AddOption:" + objOption.text + "," + objOption.value + "," + expend);
	if (expend == true && objSelect.options.length > objSelect.size) {
  		objSelect.size = objSelect.options.length;
		if (objSelect.selectedIndex > 0) {
  			objSelect.options[objSelect.selectedIndex].selected = false;
  		}
  	}
}

function delOption(objSelect, idx) {
	try {
		//console.debug("DelOption:" + objSelect.options[idx].text + "," + objSelect.options[idx].value);
		objSelect.remove(idx);
	} catch (ex) { dojo.debug(ex.description); }
}

function doSearch(keyword) {
	var params = {
		keyword:keyword
	}
	dojo.xhrPost({
		url: "/erp/json/ware_find.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			var idx = 0;
			var opt = null;
			var obj = dojo.byId("search_result");
			for (var i = obj.options.length; i > 0; i--) {
				idx = i - 1;
				opt = obj.options[idx];
				if (false == dojo.some(json.wareList, function(arg) {
					if (this.value == arg.id) {
						arg.flag = true;
						return this.value;
					}}, opt)) {
          			delOption(obj, idx);
				}
			}

			var ware = null;
			for (var i = 0; i < json.wareList.length; i++) {
				ware = json.wareList[i];
				// console.debug(ware);
				if (false == ("flag" in ware)) {
					addOption(obj, ware.name, ware.id);
				}
			}
			wareList = json.wareList;
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response); }
	});
}

function onKeyIn(event) {
	if (dojo.trim(this.value).length > 1) {
		doSearch(this.value);
		// console.debug(this.value);
	}
}

function onSelect(event) {
	if (this.selectedIndex < 0) return;
	var obj = this.options[this.selectedIndex];
	// console.debug(obj.text + ":" + obj.value);

	var objId		= dojo.byId("sell_item_save_wareId");
	var objPrice	= dojo.byId("sell_item_save_price");
	var objNum		= dojo.byId("sell_item_save_number");
	var objName		= dojo.byId("sell_item_save_ware_name");
	var ware;
	for (var i = 0; i < wareList.length; i++) {
		ware = wareList[i];
		if (obj.value == ware.id) {
			objId.value			= ware.id;
			objPrice.value		= ware.lastPrice;
			objNum.value		= 1;
			objName.innerHTML	= ware.name;
			break;
		}
	}
	objNum.focus();
	objNum.select();
}

function onSubmit() {
	var obj = dojo.byId("sell_item_save_wareId");
	if (obj.value <= 0) return;

	dojo.xhrPost({
		url: "/erp/json/sell_item_add.action",
		form: dojo.byId("sell_item_save"),
		handleAs: "json",
		load: function(json) {
			var ret = false;
			if (json.id > 0) {
				var objId		= dojo.byId("sell_item_save_wareId");
				var objPrice	= dojo.byId("sell_item_save_price");
				var objNum		= dojo.byId("sell_item_save_number");
				var objItems	= dojo.byId("itemsTable");
				var ware;
				if (wareList != null) {
					for (var i = 0; i < wareList.length; i++) {
						ware = wareList[i];
						if (objId.value == ware.id) {
							addRow(objItems, [objNum.value, objPrice.value, ware.name, ware.number - objNum.value]);
							break;
						}
					}
					objId.value		= "";
					objPrice.value	= "";
					objNum.value	= "";
				}
				ret	= true;
			}
			this.onResponse(ret);
		},
		error: function(response) { this.onResponse(false); },
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var color		= flag ? "#00FF00" : "#FF0000";
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= text;
			obj.style.backgroundColor	= color;
			setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= "";
			obj.style.backgroundColor	= "#000000";
		}
	});
}

function addRow(objTable, cells) {
	var row;
	var cell;
	try {
		row = objTable.insertRow(objTable.rows.length);
		for (var i = 0; i < cells.length; i++) {
			cell = row.insertCell(row.cells.length);
			cell.innerHTML = cells[i];
		}
	} catch (ex) { alert(ex.description); }
}

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("search_result");
	dojo.connect(obj, "ondblclick", obj, onSelect);
	obj = dojo.byId("search");
	dojo.connect(obj, "onkeyup", obj, onKeyIn);
	obj = dojo.byId("submitBtn");
	dojo.connect(obj, "onclick", obj, onSubmit);
	
	obj = dojo.byId("sell_item_save_id");
	if (obj.value == 0) {
		dojo.byId("add_div").style.display = "block";
	}
});

</script>
<style type="text/css">
select { width:380px; }
label { cursor:pointer; }
#add_div { display:none; }
</style>
</head>

<body>
<a href="sell_list.action">返回发货单列表</a>
<a href="sell.action?id=${sellId?c}">返回发货信息</a>
<#if id != 0>
<a href="sell_item_delete.action?id=${id?c}&sellId=${sellId?c}" onclick="return confirm('确实要删除这项么？')">删除</a>
</#if>
<br /><br />

<div>
<@s.form action="sell_item_save">
    <@s.label label="宝贝名称" name="ware.name"/>
    <@s.textfield label="价格" name="price" value="#{price;m2M2}"/>
    <@s.textfield label="数量" name="number"/>
    <@s.hidden name="sellId"/>
    <@s.hidden name="wareId"/>
    <@s.hidden name="id"/>
</@s.form>
<input type="button" id="submitBtn" value=" 提 交 "/>
<span id="submitStatus"></span>
</div>
<br />

<div id="add_div">
<div>
<@s.textfield label="搜索" name="search"/>
<a href="ware.action" target="_blank">添加新的宝贝</a><br />
<@s.select name="search_result" size="16"/><br />
</div>

<table id="itemsTable">
<tr>
<td>数量</td>
<td>价格</td>
<td>宝贝名称</td>
<td>剩余库存</td>
</tr>
<#if sellItemList??>
<#list sellItemList as item>
<tr>
<td>${item.number}</td>
<td>#{item.price;m2M2}</td>
<td>${item.ware.name}</td>
<td>${item.ware.number}</td>
</tr>
</#list>
</#if>
</table>
</div>

</body>
</html>
