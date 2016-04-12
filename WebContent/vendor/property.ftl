<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商详情</title>
<script language="javascript" type="text/javascript" src="js/dojo/dojo.js" djConfig="isDebug:false,usePlainJson:true,bindEncoding:'UTF-8'"></script>
<script language="javascript" type="text/javascript">
//dojo.addOnLoad(function (){});
</script>
<style type="text/css">
</style>
</head>

<body>
<a href="vendor_list.action">返回供货商列表</a>
<#if id != 0>
<a href="vendor_delete.action?id=${id?c}" onclick="return confirm('确实要删除这家供货商么？')">删除</a>
</#if>
<br /><br />

<div>
<@s.form action="vendor_save">
    <@s.textfield label="名称" name="vendor.title"/>
    <@s.textfield label="姓名" name="vendor.name"/>
    <@s.textfield label="电话1" name="vendor.phone1"/>
    <@s.textfield label="电话2" name="vendor.phone2"/>
    <@s.textfield label="地址" name="vendor.address"/>
    <@s.textfield label="联系方式" name="vendor.IM"/>
    <@s.radio label="类型" name="vendor.IMType" list="imTypeSel"/>
    <@s.textarea label="备注" name="vendor.comment" cols="40" rows="5"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
