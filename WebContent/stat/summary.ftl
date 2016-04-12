<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统计数据</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8',parseOnLoad:true"></script>
<script language="javascript" type="text/javascript">

dojo.require("dijit.form.DateTextBox");
dojo.require("dojo.parser");

var objTimer = null;

dojo.addOnLoad(function (){
	var obj = dojo.byId("feeButton");
	dojo.connect(obj, "onclick", obj, onCompute);
});

function onCompute() {
	var startObj = dojo.byId("startDate");
	var endObj = dojo.byId("endDate");
	var params = {
		startDate:startObj.value,
		endDate:endObj.value
	}
	dojo.xhrPost({
		url: "/erp/json/fee_stat.action",
		content: params,
		handleAs: "json",
		load: function(json) {
			var obj = dojo.byId("feeLayer");
			var objDiv;
			var statFee;

			obj.innerHTML = "";
			for (var i = 0; i < json.feeList.length; i++) {
				statFee = json.feeList[i];
				objDiv = document.createElement("DIV");
				objDiv.className = "float";
				objDiv.innerHTML = statFee.expressName;
				obj.appendChild(objDiv);

				objDiv = document.createElement("DIV");
				objDiv.className = "float";
				objDiv.innerHTML = statFee.number;
				obj.appendChild(objDiv);

				objDiv = document.createElement("DIV");
				objDiv.innerHTML = statFee.amount;
				obj.appendChild(objDiv);
			}
			this.onResponse(true);
		},
		error: function(response) { this.onResponse(false); },
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= text;
			objTimer		= setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("submitStatus");
			obj.innerHTML	= "";
		}
	});
}

</script>
<style type="text/css">
@import "../js/dijit/themes/tundra/tundra.css";
.float { float:left; margin-right:12px; width:80px; text-align:right; }
.row { clear:both; }
.right { text-align:right; width:40px; }
.mid { text-align:center; width:40px; }
</style>

</head>

<body class="tundra">
<a href="../index.action">返回首页</a>
<a href="keyword.action">关键词管理</a>
<br /><br />

<div>库存数:#{storeAmount;m2M2}</div><br />

<div>
<div class="float">日期</div>
<div class="float">利润</div>
<div class="float">营业额</div>
<div class="float">件数</div>
</div>
<#if weekProfitList??>
<#list weekProfitList as profit>
<div class="row">
<div class="float">${profit.statDate?string("yy-MM-dd")}</div>
<div class="float">#{(profit.profit - profit.discount + profit.fee - profit.feeReal);m2M2}</div>
<div class="float">#{(profit.amount - profit.discount + profit.fee);m2M2}</div>
<div class="float">${profit.number}</div>
</div>
</#list>
</#if>
<br /><br />

<div>
<div class="float">月份</div>
<div class="float">利润</div>
<div class="float">营业额</div>
<div class="float">件数</div>
</div>
<#if monthProfitList??>
<#list monthProfitList as profit>
<div class="row">
<div class="float">${profit.statDate?string("yy-MM")}</div>
<div class="float">#{(profit.profit - profit.discount + profit.fee - profit.feeReal);m2M2}</div>
<div class="float">#{(profit.amount - profit.discount + profit.fee);m2M2}</div>
<div class="float">${profit.number}</div>
</div>
</#list>
</#if>
</div>
<br /><br /><br />


<table>
<tr><td colspan="5">最近30天销售数量排行榜</td></tr>
<tr>
<td>名次</td>
<td class="mid">总数</td>
<td class="right">总额</td>
<td class="mid">库存</td>
<td>宝贝名称</td>
</tr>
<#if numberRankList??>
<#list numberRankList as rank>
<tr>
<td>${rank_index + 1}</td>
<td class="mid">${rank.totalNumber}</td>
<td class="right">#{rank.totalPrice;m2M2}</td>
<td class="mid">${rank.wareNumber}</td>
<td>${rank.wareName}</td>
</tr>
</#list>
</#if>
<tr><td colspan="5">&nbsp;</td></tr>
<tr><td colspan="5">最近30天销售额排行榜</td></tr>
<tr>
<td>名次</td>
<td class="right">总额</td>
<td class="mid">总数</td>
<td class="mid">库存</td>
<td>宝贝名称</td>
</tr>
<#if amountRankList??>
<#list amountRankList as rank>
<tr>
<td>${rank_index + 1}</td>
<td class="right">#{rank.totalPrice;m2M2}</td>
<td class="mid">${rank.totalNumber}</td>
<td class="mid">${rank.wareNumber}</td>
<td>${rank.wareName}</td>
</tr>
</#list>
</#if>
</table>
<br /><br />

<div>
<div>
<label for="startDate">起始日期</label>
<input type="text" id="startDate" name="startDate" value="${startDate}" dojoType="dijit.form.DateTextBox" length="20"/>
<label for="startDate">结算日期</label>
<input type="text" id="endDate" name="endDate" value="${endDate}" dojoType="dijit.form.DateTextBox" length="20"/>
<input type="button" id="feeButton" value=" 计 算 " />
<span id="submitStatus"></span>
</div><br />

<div>
<div class="float">快递</div>
<div class="float">数量</div>
<div>费用</div>
<div id="feeLayer">
</div>
</div>

</body>
</html>
