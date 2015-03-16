<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style></style>
</head>
<body style=" margin:0; padding:0; border:0; background:#;font-family:Arial, Helvetica, sans-serif;">
<div style=" margin:0; padding:0; border:0; background:#FFF; width:700px; height:auto;font-family:Arial, Helvetica, sans-serif; "> 
  <!--头部介绍========================================================================-->
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr height="60" >
      <td colspan="2">
        <h2 style=" margin:0; padding:0; border:0; font-size:20px; color:#333; border-bottom:#ccc 1px solid; padding-bottom:5px; margin:0px 40px; font-family:"微软雅黑";">${siteBase.siteName}站点即将到期提醒</h2>
      </td>
    </tr>
    <tr>
      <td  colspan="2">
        <h2 style="margin:0; padding:0; border:0; color:#EB6C00; font-size:12px; margin:5px 40px 15px">尊敬的${user.trueName}，您好！</h2>
      </td>
    </tr>
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px; font-family:Arial, Helvetica, sans-serif;">感谢您使用ConfCloud系统。您的站点还有<strong style=" color:#F00;">${exp_date}</strong>天到期，为不影响您的使用，请及时向您的销售代表或客服联系。</p>
      </td>
    </tr>
    
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px 10px; font-family:Arial, Helvetica, sans-serif;">您的企业站点相关信息如下：</p>
      </td>
    </tr>
    <tr  height="22">
      <td align="right" width="35%"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">企业名称：</span></td>
      <td align="left"  width="65%"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.siteName}</span></td>
    </tr>
    <tr  height="22">
      <td align="right" width="35%"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">企业标识：</span></td>
      <td align="left"  width="65%"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.siteSign}</span></td>
    </tr>
    <tr  height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">站点时区：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${timezone}</span></td>
    </tr>
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">站点类型：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		 <#if siteBase.siteFlag==1>
             正式
         <#else>
            试用
         </#if>
	  </span></td>
    </tr>
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">租用模式：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		 <#if siteBase.hireMode==1>
            包月
         <#else>
            记时  
         </#if>
	  </span></td>
    </tr>
    
    <tr  height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">计费类型：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		<#if siteBase.chargeMode==1>
            name host
        <#elseif siteBase.chargeMode==2>
            active user
        <#elseif siteBase.chargeMode==3>
            seats
        <#else>
            time
        </#if>
	  </span></td>
    </tr>
    <!--<tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">最大点数：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.license}</span></td>
    </tr>-->
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">站点生效日期：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.effeDate?string("yyyy-MM-dd")}</span></td>
    </tr>
     <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">站点到期日期：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.expireDate?string("yyyy-MM-dd")}</span></td>
    </tr>
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px 10px; font-family:Arial, Helvetica, sans-serif;">
<br />
温馨提示:<br />
站点到期后，企业用户及企业管理员不能登录系统，无法对站点进行各项管理、无法创建会议、无法进入会议等！</p>
      </td>
    </tr>
  </table>
  
  <!--英文-->
  
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr height="60" >
      <td colspan="2">
        <h2 style=" margin:0; padding:0; border:0; font-size:20px; color:#333; border-bottom:#ccc 1px solid; padding-bottom:5px; margin:0px 40px; font-family:"微软雅黑";">${siteBase.siteName} site is going to expire </h2>
      </td>
    </tr>
    <tr>
      <td  colspan="2">
        <h2 style="margin:0; padding:0; border:0; color:#EB6C00; font-size:12px; margin:5px 40px 15px">Dear ${user.trueName},</h2>
      </td>
    </tr>
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px; font-family:Arial, Helvetica, sans-serif;">Thanks for using ConfCloud System.Your site will be expired within <strong style=" color:#F00;">${exp_date}</strong> days. For not affect your use, please do not hesitate to contact your sales representative or customer service.</p>
      </td>
    </tr>
    
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px 10px; font-family:Arial, Helvetica, sans-serif;">Below is your Enterprise Site Info：</p>
      </td>
    </tr>
    <tr  height="22">
      <td align="right" width="35%"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Enterprise Name：</span></td>
      <td align="left"  width="65%"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.siteName}</span></td>
    </tr>
    <tr  height="22">
      <td align="right" width="35%"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Enterprise Identify：</span></td>
      <td align="left"  width="65%"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.siteSign}</span></td>
    </tr>
    <tr  height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Site Time Zone：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${timezone}</span></td>
    </tr>
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Site Type：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		 <#if siteBase.siteFlag==1>
             	Formal
         <#else>
            	trial
         </#if>
	  </span></td>
    </tr>
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Lease mode：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		 <#if siteBase.hireMode==1>
            Monthly Payment
         <#else>
            Timing  
         </#if>
	  </span></td>
    </tr>
    
    <tr  height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px;line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Charge Type：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">
		<#if siteBase.chargeMode==1>
            name host
        <#elseif siteBase.chargeMode==2>
            active user
        <#elseif siteBase.chargeMode==3>
            seats
        <#else>
            time
        </#if>
	  </span></td>
    </tr>
    <!--<tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Max Attendees：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.license}</span></td>
    </tr>-->
    <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Effective Time：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.effeDate?string("yyyy-MM-dd")}</span></td>
    </tr>
     <tr height="22">
      <td align="right"><span  style="margin:0; padding:0; border:0; color:#333; font-size:12px; line-height:22px; margin-left:65px; margin-top:18px; font-weight:bold;">Due Time：</span></td>
      <td align="left"><span style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px;font-family:Arial, Helvetica, sans-serif; line-height:20px; margin-left:5px;line-height:24px;">${siteBase.expireDate?string("yyyy-MM-dd")}</span></td>
    </tr>
    <tr>
      <td  colspan="2">
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px 10px; font-family:Arial, Helvetica, sans-serif;">
<br />
Warm Prompt:<br />
Once the site expires, the enterprise user and admin won't be able to do any action like log in system, manage site, create meeting or join meeting, etc.</p>
      </td>
    </tr>
  </table>
  <table cellpadding="0" cellspacing="0" border="0" width="700" style="background:#004D9E;">
    <tr height="40" align="center" >
      <td>
        <p style="margin:0; padding:0; border:0; font-size:12px; color:#fff; line-height:20px; font-family:Arial, Helvetica, sans-serif">Copyright © 2003-2013 confcloud Telecom Co., Ltd. All right Reserved.&nbsp;&nbsp; 沪ICP备 11034277号-8 </p>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
