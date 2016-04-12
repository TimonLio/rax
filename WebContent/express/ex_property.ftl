<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>快递公司属性</title>
</head>

<body>
<a href="express_list.action">返回</a>
<br /><br />

<div>
<@s.form action="express_save">
    <@s.textfield label="名字" name="express.exName"/>
    <@s.textfield label="电话" name="express.phone"/>
    <@s.textfield label="结算日期" name="express.settleDate" value="${express.settleDate?string('yyyy-MM-dd')}"/>
    <@s.textfield label="模板尺寸X" name="express.tplSizeX"/>
    <@s.textfield label="模板尺寸Y" name="express.tplSizeY"/>
    <@s.textfield label="模板日期X" name="express.tplDateX"/>
    <@s.textfield label="模板日期Y" name="express.tplDateY"/>
    <@s.textfield label="模板日期格式" name="express.tplDateFmt"/>
    <@s.textfield label="模板发件人姓名X" name="express.tplSrcNameX"/>
    <@s.textfield label="模板发件人姓名Y" name="express.tplSrcNameY"/>
    <@s.textfield label="模板发件人电话X" name="express.tplSrcPhoneX"/>
    <@s.textfield label="模板发件人电话Y" name="express.tplSrcPhoneY"/>
    <@s.textfield label="模板发件人邮编X" name="express.tplSrcZipX"/>
    <@s.textfield label="模板发件人邮编Y" name="express.tplSrcZipY"/>
    <@s.textfield label="模板发件人地址左下X" name="express.tplSrcAddrLBX"/>
    <@s.textfield label="模板发件人地址左下Y" name="express.tplSrcAddrLBY"/>
    <@s.textfield label="模板发件人地址右上X" name="express.tplSrcAddrRTX"/>
    <@s.textfield label="模板发件人地址右上Y" name="express.tplSrcAddrRTY"/>    
    <@s.textfield label="模板发件人地址缩进" name="express.tplSrcAddrIndent"/>    
    <@s.textfield label="模板收件人姓名X" name="express.tplDstNameX"/>
    <@s.textfield label="模板收件人姓名Y" name="express.tplDstNameY"/>
    <@s.textfield label="模板收件人电话1X" name="express.tplDstPhone1X"/>
    <@s.textfield label="模板收件人电话1Y" name="express.tplDstPhone1Y"/>
    <@s.textfield label="模板收件人电话2X" name="express.tplDstPhone2X"/>
    <@s.textfield label="模板收件人电话2Y" name="express.tplDstPhone2Y"/>
    <@s.textfield label="模板收件人邮编X" name="express.tplDstZipX"/>
    <@s.textfield label="模板收件人邮编Y" name="express.tplDstZipY"/>
    <@s.textfield label="模板收件人地址左下X" name="express.tplDstAddrLBX"/>
    <@s.textfield label="模板收件人地址左下Y" name="express.tplDstAddrLBY"/>
    <@s.textfield label="模板收件人地址右上X" name="express.tplDstAddrRTX"/>
    <@s.textfield label="模板收件人地址右上Y" name="express.tplDstAddrRTY"/>     
    <@s.textfield label="模板收件人地址缩进" name="express.tplDstAddrIndent"/> 
    <@s.hidden name="express.id"/>
    <@s.submit value=" 提 交 "/>
</@s.form>
</div>

</body>
</html>
