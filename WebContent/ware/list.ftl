<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分组宝贝列表</title>
<style type="text/css">
.categoryList ul{ list-style:none; }
.categoryList li{ float:left; margin-right:20px; }
.row { clear:both; }
.right { text-align:right; width:40px; }
.mid { text-align:center; width:40px; }
.wide { width:100px; }
</style>
</head>

<body>
<a href="index.action">返回首页</a>
<a href="ware_list_hid.action?categoryId=${categoryId}">列出隐藏的宝贝</a>
<a href="ware.action?categoryId=${categoryId}">添加新的宝贝</a>
<br /><br />

<@s.form action="ware_search" target="ware_search_result" theme="simple">
    搜索关键字:<@s.textfield label="关键字" name="keyword"/><@s.submit value=" 提 交 "/>
</@s.form>
<br />

<div class="categoryList">
<#if categoryList??>
<#assign w = 0>
<#list categoryList as category>
<#if category.id = categoryId>
[ ${category.name} ]
<#else>
<a href="?categoryId=${category.id}">${category.name}</a>
</#if>
<#assign w = w + category.name?length/>
<#if 70 < w>
  <br />
  <#assign w = 0>
</#if>
</#list>
</#if>
</div>
<br />

<div>
当前分类宝贝数:<#if wareList??>${wareList.size()}<#else>0</#if>
<a href="ware_export.action?categoryId=${categoryId?c}">导出当前分类</a>
</div>
<br />

<table>
<tr>
<td class="right">成本</td>
<td class="mid wide">最近进货日期</td>
<td class="mid wide">最近卖出日期</td>
<td class="mid">库存</td>
<td>宝贝名称</td>
</tr>
<#if wareList??>
<#list wareList as ware>
<tr>
<td class="right">#{ware.cost;m2M2}</td>
<td class="mid wide">${statList.get(ware_index).lastBuyDate?string("yyyy-MM-dd")}</td>
<td class="mid wide">${statList.get(ware_index).lastSellDate?string("yyyy-MM-dd")}</td>
<td class="mid">${ware.number}</td>
<td><a href="ware.action?id=${ware.id?c}&categoryId=${categoryId?c}">${ware.name}</a></td>
</tr>
</#list>
</#if>
</table>
<br />

</body>
</html>
