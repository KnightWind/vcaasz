<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shanghai Shrine Telecom Welcome Email (Beijing)</title>
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
.style2 {font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #ffffff;}
.style4 {
	color: #ffffff;
	font-size: 12px;
	font-weight: bold;
}
.style5 {
	font-size: 12px;
	font-weight: bold;
}
.style6 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold; }
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
}
.STYLE15 {	font-size: 12px
}
.STYLE16 {	font-size: 14px
}

-->
</style>
</head>

<body><a name="chi"></a><table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
  <tr><td>
<table width="700" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="117" bgcolor="003974"><table width="681" border="0" cellspacing="0" cellpadding="4">
        <tr>
          <td width="21" height="80">&nbsp;</td>
          <td width="194" height="60"><img src="http://meeting.confcloud.cn/assets/images/email/logo.jpg"/></td>
          <td width="442"></td>
        </tr>
      </table>      </td>
  </tr>
  <tr>
    <td><table width="500" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="469" height="23"><a href="#eng"><div align="right"><font face=arial size=2>English Version</font></div></a></td>
        <td width="31">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><br />
      <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><div align="center" class="style1" style="font-size:14px;"><strong>欢迎使用商会云会议中心</strong></div></td>
      </tr>
	  <tr>
        <td><div align="center" class="style1" style="font-size:12px;"><strong>&nbsp;</strong></div></td>
      </tr>
	  <tr>
        <td><div align="center" class="style1" style="font-size:12px;"><strong>企业站点信息</strong></div></td>
      </tr>
      <br />
      <tr>
        <td height="44"><p class="style1">尊敬的${userBase.trueName}，您好！
          </p>
          <p class="style1">感谢您使用<strong>商会云会议中心</strong>,您的企业信息已成功修改，您可以点击 <a href="http://${siteBase.siteSign}.confcloud.cn/admin">商会云企业管理平台</a> 登录系统。</p>
          <p class="style1"><strong>1)您的企业站点相关信息如下：</strong></p>
         </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">企业名称</span></div></td>
            <td width="431" class="style1"><strong>${siteBase.siteName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">站点时区</span></div></td>
            <td class="style1"><strong>${timezone}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">站点类型</span></div></td>
            <td class="style1"><strong>
				 <#if siteBase.siteFlag==1>
					 正式
				 <#else>
					试用
				 </#if>
			</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">租用模式</span></div></td>
            <td class="style1"><strong>
				<#if siteBase.hireMode==1>
					包月
				 <#else>
					计时  
				 </#if>
			</strong></td>
          </tr> 
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">站点生效日期</span></div></td>
            <td class="style1"><strong>${siteBase.effeDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">站点到期日期</span></div></td>
            <td class="style1"><strong>${siteBase.expireDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
        </table><br />
		</td>
      </tr>
       <tr>
        <td height="44"> 
          <p class="style1"><strong>2)您的帐号信息如下</strong></p>
         </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">登录名</span></div></td>
            <td width="431" class="style1"><strong>${userBase.loginName}</strong></td>
          </tr>
          <#if userBase.loginPass?? && userBase.loginPass!=''>
    		 <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">密码</span></div></td>
            <td class="style1"><strong>${userBase.loginPass}</strong></td>
          </tr>
   		 </#if>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">用户名</span></div></td>
            <td class="style1"><strong>${userBase.trueName}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">邮箱</span></div></td>
            <td class="style1"><strong>${userBase.userEmail}</strong></td>
          </tr> 
        </table><br />
		</td>
      </tr>
	 <tr>
        <td class="style1"><br/>
          <p><strong>3)技术支持 </strong></p></td>
      </tr>
	    <tr>
        <td><br /><table width="566" border="0" align="center" cellpadding="4" cellspacing="0">
          <tr>
		       <td colspan="2" height="31"><span class="style1"><strong>商会云</strong>提供全天候的客户服务，如果您在会议使用中有任何问题可以参考<a href="http://www.confcloud.cn/help">用户手册</a></span></td>
		  </tr>
          <tr>
            <td colspan="2"><span class="style1">如果您无法接入会议，或者在会议过程中遇到任何问题，可通过以下方式联系我们的客服人员:</span></td>
          </tr>
          <tr>
            <td colspan="2"><span class="style1">电话： <strong>400&nbsp;082&nbsp;6161</strong>
 </span></td>
          </tr>
		    <tr>
            <td width="50%" height="31" class="style1">邮箱 <strong>:</strong> <a href="mailto:cs@bizconf.cn">cs@bizconf.cn</a></td>
            <td width="50%" class="style1">&nbsp;</td>
          </tr>
		  <tr>
		      <td colspan="2">
			      <span class="style6">欢迎访问商会云官网</span><span class="style1"> <a
				href="http://www.confcloud.cn">www.confcloud.cn</a> </span> </td>
		  </tr>
		  
        </table><br /></td>
      </tr>
	   <tr>
        <td><p></p></td>
      </tr>
	    <tr>
	  
        <td><p></p></td>
      </tr>
	   
      <tr>
        <td class="style1"><p>&nbsp;</p>
                <br>   
            </p></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="24" bgcolor="ffffff" class="style6"><div align="right" class="style4">
      <table width="500" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="456">
		  <div align="right" style="text-align: right;padding: 50px 0 10px;font-size: 22px;font-weight: bold;font-family: 'Microsoft Yahei';color: #003576;">
		    商会云
		  </div>
		  </td>
          <td width="44">&nbsp;</td>
        </tr>
      </table>
      </div></td>
  </tr>
</table></td>
  </tr>
</table>

<p><br />

<a name="eng"></a><table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
  <tr><td>
<table width="700" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="117" bgcolor="003974"><table width="681" border="0" cellspacing="0" cellpadding="4">
        <tr>
          <td width="21" height="80">&nbsp;</td>
          <td width="194"><img src="http://meeting.confcloud.cn/assets/images/email/logo.jpg" /></td>
          <td width="442"></td>
        </tr>
      </table>      </td>
  </tr>
  <tr>
    <td><table width="500" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="469" height="23"><a href="#chi"><div align="right"><font face=arial size=2>Chinese Version</font></div></a></td>
        <td width="31">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><br />
      <table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><div align="center" class="style1"><strong>Welcome to ConfCloud Conferencing Center</strong></div></td>
      </tr>
      <br />
	  <tr>
        <td><div align="center" class="style1" style="font-size:12px;"><strong>&nbsp</strong></div></td>
      </tr>
	  <tr>
        <td><div align="center" class="style1" style="font-size:12px;"><strong>Enterprise Site Info</strong></div></td>
      </tr>
      <tr>
        <td height="44"><p class="style1">Dear ${userBase.trueName}！
          <p class="style1">Thank you for using <strong>ConfCloud</strong>. Your enterprise site info  has been modified. please click 
		  <a href="http://${siteBase.siteSign}.confcloud.cn/admin">ConfCloud Enterprise Management platform</a> to log in. </p>
          <p class="style1"><span class="style6">1)Below is your Enterprise Site Info：</span>          
          </td>
      </tr>
      <tr>
        <td><br />
		 
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">Enterprise Name</span></div></td>
            <td width="431" class="style1"><strong>${siteBase.siteName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Site TimeZone</span></div></td>
            <td class="style1"><strong>${entimezone}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Site Type</span></div></td>
            <td class="style1"><strong>
			<#if siteBase.siteFlag==1>
				Formal
			 <#else>
				Trial
			 </#if>
			</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Lease Mode</span></div></td>
            <td class="style1"><strong>
			<#if siteBase.hireMode==1>
				Monthly Payment
			 <#else>
				Timing  
			 </#if>
			</strong></td>
          </tr> 
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Effective Time</span></div></td>
            <td class="style1"><strong>${siteBase.effeDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Due Time</span></div></td>
            <td class="style1"><strong>${siteBase.expireDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
        </table><br />
		</td>
      </tr>
	   <tr>
        <td height="44"> 
          <p class="style1"><strong>2)Below is your account info</strong></p>
         </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">Login Name</span></div></td>
            <td width="431" class="style1"><strong>${userBase.loginName}</strong></td>
          </tr>
          <#if userBase.loginPass?? && userBase.loginPass!=''>
    		<tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Password</span></div></td>
            <td class="style1"><strong>${userBase.loginPass}</strong></td>
          </tr>
   		 </#if>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">User Name</span></div></td>
            <td class="style1"><strong>${userBase.trueName}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Email</span></div></td>
            <td class="style1"><strong>${userBase.userEmail}</strong></td>
          </tr> 
        </table><br />
		</td>
      </tr>
	  
	<tr>
        <td class="style1"> <br/>
          <p class="style6">3) Support</p></td>
      </tr>
	  <tr>
        <td><br /><table width="566" border="0" align="center" cellpadding="4" cellspacing="0">
          <tr>
		       <td colspan="2"><span class="style1">Any questions, please refer to <a href="http://www.confcloud.cn/help">User Guide</a></span></td>
		  </tr>
          <tr>
            <td width="100%" colspan="2"><span class="style1">If you have trouble accessing or using your confcloud conference, 
									 ConfCloud Helpdesk is always available for you. Call or email Customer Support Team below:
								</span></td>
          </tr>
          <tr>
            <td colspan="2"><span class="style1">Tel: <strong> 400&nbsp;082&nbsp;6161</strong>
          </span></td>
          </tr>
		   <tr>
            <td width="50%"  class="style1">Email: <a href="mailto:cs@bizconf.cn">cs@bizconf.cn</a></td>
           
          </tr>
		  <tr>
		       <td colspan="2"><span class="style1"><strong>Welcome to visit confcloud website</strong></span><strong> </strong><a
						href="http://www.confcloud.cn" class="style1">www.confcloud.cn</a><span></td>
		  </tr>
        </table>
        <br /></td>
      </tr>
	   <tr>
	  
        <td><p></p></td>
      </tr>
	   <tr>
	  
        <td><p></p></td>
      </tr>
      
      <tr>
        <td class="style1"><p>&nbsp;</p>
                <br>   
            </p></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="24" bgcolor="ffffff" class="style6"><div align="right" class="style4">
      <table width="500" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="456">
				<div align="right" style="text-align: right;padding: 50px 0 10px;font-size: 22px;font-weight: bold;font-family: 'Microsoft Yahei';color: #003576;">
				confcloud.
		  </div>
		  </td>
          <td width="44">&nbsp;</td>
        </tr>
      </table>
      </div></td>
  </tr>
</table></td>
  </tr>
</table>

</body>
</html>