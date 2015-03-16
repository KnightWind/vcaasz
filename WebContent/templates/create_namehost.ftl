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
        <td><div align="center" class="style1"><strong>欢迎使用商会云会议中心</strong></div></td>
      </tr>
      <br />
      <tr>
        <td height="44"><p class="style1">尊敬的${user.trueName}，您好！
          </p>
          <p class="style1">感谢您申请<strong>商会云会议中心</strong>帐户。您的帐户相关信息如下：</p>
          <p class="style1"><strong>1) 帐户信息</strong></p>
         </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="90%"><ul>
              <li><span class="style6">您可以访问<a href="http://${siteaddress}">商会云会议平台</a>登录系统：<a href="http://${siteaddress}">http://${siteaddress}</a></span></li>
			  <li><span class="style6">以下是您的会议帐号信息</span></li>
            </ul></td>
            <td width="10%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">用户名</span></div></td>
            <td width="431" class="style1"><strong>${user.trueName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">登录名</span></div></td>
            <td class="style1"><strong>${user.loginName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">密码</span></div></td>
            <td class="style1"><strong>${user.loginPass}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">邮箱</span></div></td>
            <td class="style1"><strong>${user.userEmail}</strong></td>
          </tr>
        </table><br />
		<#if lics??>
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="50%"><ul>
			  <li><span class="style6">以下是您的授权方数信息</span></li>
            </ul></td>
            <td width="50%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">最大点数</span></div></td>
            <td width="203" bgcolor="003366" class="style2"><div align="center"><span class="style4">生效时间</span></div></td>
            <td width="203" bgcolor="003366" class="style2"><div align="center"><span class="style4">到期时间</span></div></td>
          </tr>
          <#list lics as lic>
          <tr>
            <td class="style1"><strong>${lic.licNum}</strong></td>
            <td class="style1"><strong>${lic.effeDate?string("yyyy-MM-dd")}</strong></td>
            <td class="style1"><strong>${lic.expireDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
		</#list>
        </table>
		</#if>
		<br />
		</td>
      </tr>
       
	 <tr>
        <td class="style1"><br/>
          <p><strong>2)技术支持 </strong></p></td>
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
            <td width="50%" height="31"><span class="style6">商会云服务支持中心</span></td>
            <td width="50%" class="style1">&nbsp;</td>
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
	   
      <!--<tr>
        <td>
		<p class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		<p><span class="style6">接入号码表</span></p></td>
      </tr>
      <tr>
        <td class="style1"><p>&nbsp;</p>
         <table width="556" border="1" align="center" cellpadding="2" cellspacing="0">
              <tr>
                <td width="20%" bgcolor="003366" class="style2" height="35" ><div align="center" ><span class="style5">国家</span></div>   </td>
                <td width="30%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">号码类型</span></div></td>
                <td width="50%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">号码</span></div></td>
              </tr>
              <tr>
                <td class="style1">全球</td>
                <td class="style1">直拨号</td>
                <td class="style1">${accessNumber1!'8610 58214900'}</td>
              </tr>
              <tr>
				<td class="style1">中国</td>
                <td class="style1">国内免费</td>
                <td class="style1">${accessNumber2!'800 870 1088'}</td>
              </tr>
              <tr>
                <td class="style1">中国</td>
                <td class="style1">本地付费</td>
                <td class="style1">${accessNumber3!'400 874 1088'}</td>
			</tr>
           
            </table>  
                <br>   
            </p></td>
      </tr>-->
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
        <td><div align="center" class="style1"><strong>Welcome to ConfCloud  Conferencing Center</strong></div></td>
      </tr>
      <br />
      <tr>
        <td height="44"><p class="style1">Dear ${user.trueName}！
          <p class="style1">Thank you for using <strong>ConfCloud Conferencing Center</strong>. This email contains your personal account information and all materials to drive you in using the service.
         	</p>
          <p class="style1"><span class="style6">1) Account Information</span>          
          </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="90%"><ul>
                 <li><span class="style6">You can visit the <a href="http://${siteaddress}">confcloud</a> via this link:<a href="http://${siteaddress}">http://${siteaddress}</a></span></li>
                 <li><span class="style6">The following is your meeting account information:</span></li>
            </ul></td>
            <td width="10%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">User Name:</span></div></td>
            <td width="431" class="style1"><strong>${user.trueName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Login Name:</span></div></td>
            <td class="style1"><strong>${user.loginName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Password:</span></div></td>
            <td class="style1"><strong>${user.loginPass}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Email:</span></div></td>
            <td class="style1"><strong>${user.userEmail}</strong></td>
          </tr>
        </table><br />
		<#if lics??>
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="50%"><ul>
			  <li><span class="style6">Below is Authorized Points info：</span></li>
            </ul></td>
            <td width="50%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="153" bgcolor="003366" class="style2"><div align="center"><span class="style4">Max Attendee Number</span></div></td>
            <td width="183" bgcolor="003366" class="style2"><div align="center"><span class="style4">Effective Time</span></div></td>
            <td width="183" bgcolor="003366" class="style2"><div align="center"><span class="style4">Due time</span></div></td>
          </tr>
		 <#list lics as lic>
          <tr>
            <td class="style1"><strong>${lic.licNum}</strong></td>
            <td class="style1"><strong>${lic.effeDate?string("yyyy-MM-dd")}</strong></td>
            <td class="style1"><strong>${lic.expireDate?string("yyyy-MM-dd")}</strong></td>
          </tr>
		</#list>
        </table>
	</#if>
		<br />
		
		
		</td>
      </tr>
      
	<tr>
        <td class="style1"> <br/>
          <p class="style6">2)Support</p></td>
      </tr>
	  <tr>
        <td><br /><table width="566" border="0" align="center" cellpadding="4" cellspacing="0">
          <tr>
		       <td colspan="2"><span class="style1">Any questions, please refer to <a href="http://www.confcloud.cn/help">User Guide</a></span></td>
		  </tr>
          <tr>
            <td width="100%" colspan="2"><span class="style1">If you have trouble accessing or using your confcloud conference, 
									confcloud helpdesk is always available for you. Call or email Customer Support Team below:
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
		       <td colspan="2"><span class="style1"><strong>Welcome to visit confcloud Telecom Co., Ltd. company website</strong></span><strong> </strong><a
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
        <td>
		<p class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		<p><span class="style6">Dial-In numbers</span></p></td>
      </tr>
      <tr>
        <td class="style1"><p>&nbsp;</p>
           <table width="556" border="1" align="center" cellpadding="2" cellspacing="0">
              <tr>
                <td width="20%" bgcolor="003366" class="style2" height="35" ><div align="center" ><span class="style5">Country</span></div>   </td>
                <td width="30%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">Number Type</span></div></td>
                <td width="50%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">Number</span></div></td>
              </tr>
              <tr>
                <td class="style1">International</td>
                <td class="style1">Direct dial in number</td>
                <td class="style1">${accessNumber1!'8610 58214900'}</td>
              </tr>
              <tr>
				<td class="style1">China</td>
                <td class="style1">Toll Free</td>
                <td class="style1">${accessNumber2!'800 870 1088'}</td>
              </tr>
              <tr>
                <td class="style1">China</td>
                <td class="style1">Toll</td>
                <td class="style1">${accessNumber3!'400 874 1088'}</td>
			</tr>
            </table>     
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
				confcloud
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