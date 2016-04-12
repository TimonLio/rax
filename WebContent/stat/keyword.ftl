<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>词表属性</title>
<script language="javascript" type="text/javascript" src="../js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

function parseToken() {
	var params = {
		tokenize: dojo.byId("paragraph").value
	}
	dojo.xhrPost({
		url: "/erp/json/tokenize.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) {
			dojo.byId("tokenize").value = json.tokenize;
			this.onResponse(true);
		},
		error: function(response) { 
			console.debug(response.status);
			this.onResponse(false);
		},
		onResponse: function(flag) {
			var text		= flag ? "提交成功" : "提交失败";
			var obj			= dojo.byId("computeStatus");
			obj.innerHTML	= text;
			objTimer		= setTimeout(dojo.hitch(this, "onFinish"), 3000);
		},
		onFinish: function() {
			var obj			= dojo.byId("computeStatus");
			obj.innerHTML	= "";
		}
	});
}

function submitToken() {
	var params = {
		tokenize: dojo.byId("tokenize").value
	}
	dojo.xhrPost({
		url: "/erp/json/save_tokens.action",
		content: params,
		preventCache: true,
		handleAs: "json",
		load: function(json) {
			this.onResponse(true, json.status);
		},
		error: function(response) { 
			console.debug(response.status);
			this.onResponse(false);
		},
		onResponse: function(flag, num) {
			var text		= flag ? "提交成功:" + num + "个新词" : "提交失败";
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

function onCompute(event) {
	var text = dojo.trim(dojo.byId("paragraph").value);
	if (text.length > 0) parseToken();
}

function onSubmit(event) {
	var text = dojo.trim(dojo.byId("tokenize").value);
	if (text.length > 0) {
		submitToken();
		dojo.byId("paragraph").select();
	}
}

dojo.addOnLoad(function (){
	var obj;
	obj = dojo.byId("paragraph");
	dojo.connect(obj, "onfocus", obj, obj.select);
	obj = dojo.byId("computeButton");
	dojo.connect(obj, "onclick", obj, onCompute);
	obj = dojo.byId("submitButton");
	dojo.connect(obj, "onclick", obj, onSubmit);
});

</script>
</head>

<body>
<a href="stat_summary.action">返回</a><br /><br />

<div>词库关键词数量: ${status}</div>
<br />

<div>
<@s.textarea rows="2" cols="80" label="宝贝名" id="paragraph"/>
<input type="button" id="computeButton" value=" 计 算 " />
<span id="computeStatus"></span>
<br />

<@s.textarea rows="4" cols="80" label="关键词" id="tokenize"/>
<input type="button" id="submitButton" value=" 提 交 " />
<span id="submitStatus"></span>
<br />

</div>

</body>
</html>
