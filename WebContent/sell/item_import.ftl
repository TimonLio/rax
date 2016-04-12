<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入卖出宝贝列表</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

var sellId;
var wareList;
var importList;
var importIdx = 0;

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("sellId");
	sellId = obj.value;

	obj = dojo.byId("importSubmitBtn");
	dojo.connect(obj, "onclick", obj, onImport);

	obj = dojo.byId("importResult");
	dojo.connect(obj, "ondblclick", obj, onConfirm);

	obj = dojo.byId("searchResult");
	dojo.connect(obj, "ondblclick", obj, onSelect);

	obj = dojo.byId("importLayer");
	obj.style.display = "block";
	obj = dojo.byId("searchLayer");
	obj.style.display = "none";
	
});

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
	return;

	try {
		row = document.createElement("TR");
		for (var i = 0; i < cells.length; i++) {
			cell = document.createElement("TD");
			cell.innerHTML = cells[i];
			row.appendChild(cell);
		}
		var hasTbody = false;
		var tbody = document.createElement("TBODY");
		if (objTable.hasChildNodes ) {
			for (var i = 0; i < objTable.childNodes.length; i++) {
				node = objTable.childNodes[i];
				if (node.nodeType == 1) {
					hasTbody = true;
					tbody = node;
					break;
				}
			}
		}
		tbody.appendChild(row);
		if (hasTbody == false) objTable.appendChild(tbody);
	} catch (ex) { alert(ex.desription); }
}

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

function delOption(objSelect, idx, expend) {
	try {
		//console.debug("DelOption:" + objSelect.options[idx].text + "," + objSelect.options[idx].value);
		objSelect.remove(idx);
		if (expend == true && objSelect.options.length >= 2) objSelect.size = objSelect.options.length;
	} catch (ex) { alert(ex.description); }
}

function onImport(event) {
	var obj = dojo.byId("sellContent");
	if (dojo.trim(obj.value).length > 1) {
		doParse(obj.value);
		//console.debug(obj.value);
	}
	obj = dojo.byId("importLayer");
	obj.style.display = "none";
	obj = dojo.byId("searchLayer");
	obj.style.display = "block";
}

function doParse(content) {
	var params = {
		sellContent:content,
		sellId:sellId
	}
	dojo.xhrPost({
		url: "/erp/json/sell_item_import.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			var obj = dojo.byId("importResult");
			while (obj.options.length > 0) {
				obj.remove(0);
			}
			var item = null;
			var total = 0;
			for (var i = 0; i < json.itemList.length; i++) {
				item = json.itemList[i];
				//console.debug(item);
				addOption(obj, item.name, i, true);
				total += item.number * item.price;
			}
			if (total + json.totalExFee != json.totalPrice) {
				dojo.byId("checkingPrompt").style.display = "block";
			}
			importList = json.itemList;			
			//console.debug(importList);
		} catch(e) { console.debug(e.toString()); } },
		error: function(response) { console.debug(response.status);	}
	});
}

function onConfirm(event) {
	if (this.selectedIndex < 0) return;
	var obj = this.options[this.selectedIndex];
	//console.debug(obj.text + obj.value);
	importIdx = obj.value;
	doSearch(obj.text);
}

function doSearch(keyword, id) {
	var params = {
		keyword:keyword
	}
	dojo.xhrPost({
		url: "/erp/json/ware_find_fulltext.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) { try {
			var idx = 0;
			var opt = null;
			var obj = dojo.byId("searchResult");
			while (obj.options.length > 0) {
				obj.remove(0);
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
		error: function(response) { console.debug(response.status); }
	});
}

function onSelect(event) {
	if (this.selectedIndex < 0) return;
	var obj = this.options[this.selectedIndex];
	//console.debug(obj.text + ":" + obj.value);

	var itemId;
	var itemName;
	var itemPrice;
	var itemNum;

	try {
		var item = importList[importIdx];
		itemNum = item.number;
		itemPrice = item.price;
	} catch(ex) {
		console.debug(ex.toString());
	}

	var ware;
	for (var i = 0; i < wareList.length; i++) {
		ware = wareList[i];
		if (obj.value == ware.id) {
			itemName = ware.name;
			itemId = ware.id;
			break;
		}
	}

	if (confirm("名称:" + itemName + "\n数量:" + itemNum + "\n单价:" + itemPrice)) {
		console.debug("id:" + itemId + " num:"  + itemNum + " itemName:" + itemName + " price:" + itemPrice);
		while (this.options.length > 0) {
			this.remove(0);
		}

		var obj = dojo.byId("importResult");
		for (var i = 0; i < obj.options.length; i++) {
			if (obj.options[i].value == importIdx) {
         		delOption(obj, i);
         		break;
			}
		}
		onSubmit(itemId, itemPrice, itemNum);
	}
}

function onSubmit(itemId, itemPrice, itemNum) {
	var params = {
		wareId:itemId,
		price:itemPrice,
		number:itemNum,
		sellId:sellId
	}
	dojo.xhrPost({
		url: "/erp/json/sell_item_add.action",
		content: params,
		handleAs: "json",
		load: function(json) {
			var ret = false;
			if (json.id > 0) {
				var objItems = dojo.byId("itemsTable");
				var ware;
				if (wareList != null) {
					for (var i = 0; i < wareList.length; i++) {
						ware = wareList[i];
						if (itemId == ware.id) {
							addRow(objItems, [itemNum, itemPrice, ware.name, ware.number - itemNum]);
							break;
						}
					}
				}
				ret	= true;
			}
			this.onResponse(ret);
		},
		error: function(response) { 
			console.debug("onError:" + response);
			this.onResponse(false);
		},
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= text;
			setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= "";
		}
	});
}

</script>
<style type="text/css">
select { width:380px; }
label { cursor:pointer; }
#checkingPrompt { color:red; display:none; }
</style>
</head>

<body>
<a href="sell_list.action">返回发货单列表</a>
<a href="sell.action?id=${sellId?c}">返回发货信息</a>
<br /><br />

<div id="importLayer">
<textarea id="sellContent" name="sellContent" cols="80" rows="8"></textarea><br />
<input type="button" id="importSubmitBtn" value=" 提 交 "/>
</div>
<div id="searchLayer">
<select id="importResult" size="2"></select><br />
<select id="searchResult" size="12"></select><br />
</div>
<br />

<div id="checkingPrompt">标价与实付总价不符, 请检查是否VIP价</div>
<br/>

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
<br />

<div>
<span id="submitStatus"></span>
</div>

<input type="hidden" id="sellId" name="sellId" value="${sellId?c}" />
</body>
</html>
