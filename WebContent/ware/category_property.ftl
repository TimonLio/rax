<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝贝属性</title>
</head>

<body>
<a href="ware_category_list.action">返回</a><br /><br />

<div>
<@s.form action="ware_category_save">
    <@s.textfield label="名称" name="category.name"/>
    <@s.hidden name="id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

<#if id != 0>
<a href="ware_category_delete.action?id=${id}" onclick="return confirm('确实要删除这个分类么？')">删除</a>
</#if>

</body>
</html>
