<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>淘宝在线</title>
<link type="text/css" href="../css/smoothness/jquery-ui-1.8.11.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="../js/jquery-ui-1.8.11.min.js"></script>
<script language="javascript" type="text/javascript">

$(document).ready(function() {

	$("input:radio[name=deliverDate]").click(function() {
		//console.log("onClick val:" + $(this).val());
		$("#sell_import_created").val($(this).val());
	}).each(function() {
		if ($(this).val() == $("#sell_import_created").val()) { $(this).attr("checked", "checked"); }
	});
	
	$("input:radio[name=postFeeRealOption]").click(function() {
		//console.log("onClick val:" + $(this).val());
		$("#sell_import_postFeeReal").val($(this).val());
	}).each(function() {
		if ($(this).val() == $("#sell_import_postFeeReal").val()) { $(this).attr("checked", "checked"); }
	});
	
	$("input:radio[name=expressId]").click(function() {
		//console.log("onClick val:" + $(this).val());
		$.cookie("default_expressId", $(this).val());
	}).each(function() {
		if ($(this).val() == $.cookie("default_expressId")) { $(this).attr("checked", "checked"); }
	});
	
	$("tr.item").hover(
		function() { $(this).addClass("over"); },
		function() { $(this).removeClass("over"); }
	).click(function() {
		var itemObj = $(this);
		var itemIid = $(this).find("img").attr("title");
		var itemName = $(this).find(".item_name").text();
		var params = { keyword: itemName };
		$.ajax({
			url: "/erp/json/ware_find_fulltext.action",
			type: "POST",
			cache: false,
			dataType: "json",
			data: params,
			success: function(data, textStatus, jqXHR) {
				var ul = $("#selection > ul").empty();
				var dlg = $("#selection").dialog({ modal: true, width: 600, title: itemName, position: "top"});
				$.each(data.wareList, function(index, obj) {
					//console.debug("id:" + obj.id + " name:" + obj.name);
					var li = $("<li />").text(obj.name)
						.data("id", obj.id)
						.click(function() {
							itemObj.data("wareId", $(this).data("id"));
							//console.debug("wareId:" + obj.id);
							itemObj.find(".item_ware").text($(this).text()).removeClass("highlight");
							ul.empty();
							dlg.dialog("close");
						})
						.hover(
							function() { $(this).addClass("over"); },
							function() { $(this).removeClass("over"); }
						);
					ul.append(li);
				});
			}
		});
	});

    $("#sell_import_submit").click(function() {

    	$(this).attr({"disabled": "disabled"});
    	
    	var bind = true;    	
    	$("tr.item").each(function() {
    		bind = bind && (false == $(this).hasClass("finish"));
    		if ($(this).data("wareId") == null) {
    			$(this).effect("highlight", {}, 1000)
    				.find(".item_ware").text("请绑定一个宝贝").addClass("highlight");
    			bind = false;
    		}
    	});

		if (bind) {
			var params = {
				buyerNick: $("#sell_import_buyerNick").val(),
				receiverName: $("#sell_import_receiverName").val(),
				receiverMobile: $("#sell_import_receiverMobile").val(),
				receiverPhone: $("#sell_import_receiverPhone").val(),
				receiverAddress: $("#sell_import_receiverAddress").val(),
				receiverZip: $("#sell_import_receiverZip").val(),
				expressId: $("input:radio[name=expressId]:checked").val(),
				commentExpress: $("#sell_import_commentExpress").val(),
				commentInvoice: $("#sell_import_commentInvoice").val(),
				postFee: $("#sell_import_postFee").val(),
				postFeeReal: $("#sell_import_postFeeReal").val(),
				created: $("#sell_import_created").val()
			};
			//console.log(params);
			$.ajax({
				url: "/erp/json/trade_import.action",
				type: "POST",
				cache: false,
				dataType: "json",
				data: params,
				success: function(data, textStatus, jqXHR) {
					//console.debug("data:" + data + " textStatus:" + textStatus);
					if (data.sellId > 0) {
						$("#sell_import_submit").attr({"disabled": null});
						$("tr.item").each(function() {
							var obj_tr = $(this);
							var args = { 
								sellId: data.sellId,
								wareId: $(this).data("wareId"),
								price: $(this).find(".item_price").text(),
								number: $(this).find(".item_number").text()
							};
							//console.debug(args);
							$.ajax({
								url: "/erp/json/trade_import_item.action",
								type: "POST",
								cache: false,
								dataType: "json",
								data: args,
								success: function(data, textStatus, jqXHR) {
									if (data.id > 0) {
										obj_tr.effect("highlight", {}, 1000)
    										.find(".item_ware").addClass("finish");
									}
								}
							})
    					});
    				}
    	
				}
			});
		} else {
			setTimeout(function() { $("#sell_import_submit").attr({"disabled": null}); }, 1000);
		}
    	return false;
    });
    
});

</script>
<style media="print">
body { display: none; }
</style>
<style type="text/css">
td { padding: 3px; }
.highlight { color: red; }
.finish { color: green; }
.title { background: #DDD; }
.title td { padding: 6px; }
.item.over { background: #EEE; }
#selection li { list-style: none; padding: 6px; }
#selection li.over { background: #EEE; }
</style>
</head>

<body>
<a href="trade_list.action">返回</a>
<br /><br />

<div>
<@s.form action="sell_import">
	<@s.textfield label="买家ID" name="buyerNick"/>
    <@s.textfield label="收件人姓名" name="receiverName"/>
    <@s.textarea label="收件人地址" name="receiverAddress" cols="80" rows="2"/>
    <@s.textfield label="收件人移动电话" name="receiverMobile"/>
    <@s.textfield label="收件人固定电话" name="receiverPhone"/>
    <@s.textfield label="收件人邮编" name="receiverZip"/>
    <@s.textfield label="发货日期" name="created"/>
    <@s.radio name="deliverDate" list="deliverDateOption"/>
    <@s.textfield label="收取运费" name="postFee"/>
    <@s.textfield label="实际运费" name="postFeeReal" value="0"/>
    <@s.radio name="postFeeRealOption" list="{0, 3.5, 4, 5, 8, 10, 12, 15, 20, 25}"/>
    <@s.radio label="快递" name="expressId" list="expressOption" />
    <@s.textfield label="买家留言" name="buyerMessage"/>
    <@s.textfield label="买家备注" name="buyerMemo"/>
    <@s.textfield label="卖家备注" name="sellerMemo"/>
    <@s.textfield label="快递单备注" name="commentExpress"/>
    <@s.textfield label="发货单备注" name="commentInvoice"/>
    <@s.submit name="submit" value=" 提 交 "/>
</@s.form>
</div>

<!--
	private String payment;
	private String postFee;
	private String totalFee;
	private String discountFee;
	private String adjustFee;
-->

<#if orders??>
<table>
<tr class="title">
<td class="" colspan="2">名称</td>
<td class="">标价</td>
<td class="">数量</td>
<td class="">关联宝贝</td>
</tr>
<#list orders as o>
<tr class="item">
<td class=""><img src="${o.picPath}" width="50" title="${o.numIid}" /></td>
<td class="item_name">${o.title}</td>
<td class="item_price">${o.price}</td>
<td class="item_number">${o.num}</td>
<td class="item_ware"></td>
</tr>
</#list>

</table>
</#if>

<div id="selection">
<ul>
</ul>
</div>

<br /><br />

</body>
</html>
