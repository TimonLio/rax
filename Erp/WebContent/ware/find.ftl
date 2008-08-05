<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索结果</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">

dojo.addOnLoad(function (){
  var obj;
  obj = dojo.byId("ware_search_keyword");
  obj.focus();
  obj.select();
});

</script>
</head>

<body>
<@s.url id="urlList" action="ware_list">
	<@s.param name="page" value="page" />
</@s.url>
<a href="${urlList}">返回宝贝列表</a>
<br /><br />
<div>总数:<#if wareList??>${wareList.size()}<#else>0</#if></div><br />

<@s.form action="ware_search" target="_blank" theme="simple">
    搜索关键字:<@s.textfield label="关键字" name="keyword"/><br />
    搜索库存量:<@s.textfield label="库存" name="min" size="2"/> - <@s.textfield name="max" size="2"/>
	<@s.submit value=" 提 交 "/>
</@s.form>
<br />

<#if wareList??>
<#list wareList as ware>
<div>
<@s.url id="url" action="ware">
	<@s.param name="id" value="#{ware.id}"/>
	<@s.param name="page" value="page"/>
</@s.url>
<a href="${url}">${ware.name} - (${ware.number})</a>
</div>
</#list>
</#if>
<br />



</body>
</html>