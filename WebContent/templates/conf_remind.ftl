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

<body><a name="chi"></a>
<table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
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
      <br/>
	  <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
	  <tr>
        <td align="left"><br/>
		<table width="650" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		        <td width="10%">
					 <div style="background:#86B82F;width: 80px;height: 24px;line-height:24px;text-align: center;float: left;">
								<a href="http://${siteaddress}/${acceptUrl}" style="color: #fff;text-decoration: none;font-family: Arial, Helvetica, sans-serif;font-size: 12px;">接受</a>
					</div>
				</td>
            <td width="20%">
				&nbsp;
			</td>
			<td width="10%">
				<div style="background:#00448D;width: 80px;height: 24px;line-height:24px;text-align: center;">
							<a href="http://${siteaddress}/${refuseURI}" style="color: #fff;text-decoration: none;font-family: Arial, Helvetica, sans-serif;font-size: 12px;">谢绝</a>
				</div>
			</td>
			<td width="60%">
				&nbsp;
			</td>
		  </tr>
		</table>
		</td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td height="44" style="padding-top:10px"><p class="style1">尊敬的${user.userName}，您好！
          </p>
          <p class="style1">系统提醒您出席会议<strong>"${conf.confName}"</strong> 。请点击 <a href="http://${siteaddress}/?joinUrl=${joinUrl}">加入</a>，可以进入您的会议！您的会议详细信息如下：</p>
          <p class="style1"><strong>  会议信息</strong></p>
         </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="50%">
				<!-- <ul>
              <li><span class="style6">会议信息</span></li>
            </ul>-->
			</td>
            <td width="50%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">会议主题</span></div></td>
            <td width="431" class="style1"><strong>${conf.confName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">安全会议号码</span></div></td>
            <td class="style1"><strong>${securityCode}</strong></td>
          </tr>
          <tr ${ispublicconf}>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">会议密码</span></div></td>
            <td class="style1"><strong>${conf.publicConfPass!'无'}</strong></td>
          </tr>
		  <#if conf.phonePass != ''>
			<tr>
				<td bgcolor="003366" class="style2"><div align="center"><span class="style5">H323/SIP终端密码</span></div></td>
				<td class="style1"><strong>${conf.phonePass}</strong></td>
			</tr>	
		  </#if> 
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">会议主持人</span></div></td>
            <td class="style1"><strong>${conf.compereName}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">会议开始时间</span></div></td>
            <td class="style1"><strong>${conf.startTime?string("yyyy-MM-dd HH:mm")}(${timezone})</strong></td>
          </tr> 
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">会议结束时间</span></div></td>
            <td class="style1"><strong>${conf.endTime?string("yyyy-MM-dd HH:mm")}(${timezone})</strong></td>
          </tr>
        </table><br /></td>
      </tr>
      <tr ${istelconf}>
        <td>
		<p class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		<p class="style6">快速接入</p>
        </td>
      </tr>
      <tr ${istelconf}>
        <td><br /><table ${istelconf} width="579" border="0" align="center" cellpadding="4" cellspacing="0">
          
          <tr>
            <td width="100%" colspan="2"><ul>
              <li><span class="style6">此会议提供电话功能，您可以拨打如下电话号码，加入会议：</span></li>
            </ul></td>
            

          </tr>
          <tr>
            <td colspan="2"><table ${istelconf} width="96%" border="1" align="center" cellpadding="2" cellspacing="0">
              <tr>
                <td width="30%" bgcolor="003366" class="style2" height="35" ><div align="center" ><span class="style5">国家</span></div>   </td>
                <td width="30%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">号码类型</span></div></td>
                <td width="40%" bgcolor="003366" class="style2" height="35"><div align="center" ><span class="style5">号码</span></div></td>
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
           
            </table></td>
            </tr>
      
        </table><br /></td>
      </tr>
      <tr>
        <td class="style1"><br/>
      
          <p><strong>技术支持 </strong></p>          </td>
      </tr>
      <tr>
        <td><br /><table width="566" border="0" align="center" cellpadding="4" cellspacing="0">
          <tr>
		       <td colspan="2" height="31"><span class="style1"><strong>商会云</strong>提供全天候的客户服务，如果您在会议使用中有任何问题可以参考<a href="http://www.confcloud.cn/help">用户手册</a></span></td>
		  </tr>
          <tr>
            <td colspan="2"><span class="style1">如果您无法接入会议，或者在使用电话会议过程中遇到任何问题，可通过以下方式联系我们的客服人员:</span></td>
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
				href="http://www.confcloud.cn"> www.confcloud.cn</a> </span> </td>
		  </tr>
		  
        </table><br /></td>
      </tr>
	   <tr>
	  
        <td><p></p></td>
      </tr>
	    <tr>
	  
        <td><p></p></td>
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
      </table></td>
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
        <td><div align="center" class="style1"><strong>Welcome to ConfCloud</strong></div></td>
      </tr>
      <br />
	  <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
	  <tr>
        <td align="left"><br/>
		<table width="650" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		        <td width="15%" valign="middle">
					 <div style="background:#86B82F;width: 80px;height: 24px;line-height:24px;text-align: center;float: left;">
								<a href="http://${siteaddress}/${acceptUrl}" style="color: #fff;text-decoration: underline;font-family: Arial, Helvetica, sans-serif;font-size: 12px;">Accept</a>
					</div>
				</td>
            <td width="20%">
				&nbsp;
			</td>
			<td width="15%" valign="middle">
				<div style="background:#00448D;width: 80px;height: 24px;line-height:24px;text-align: center;">
							<a href="http://${siteaddress}/${refuseURI}" style="color: #fff;text-decoration: underline;font-family: Arial, Helvetica, sans-serif;font-size: 12px;">Refuse</a>
				</div>
			</td>
			<td width="50%">
				&nbsp;
			</td>
		  </tr>
		</table>
		</td>
      </tr>
      <tr>
        <td><div align="center" class="style1">&nbsp;</div></td>
      </tr>
      <tr>
        <td height="44"><p class="style1">Dear ${user.userName}!</p>
          <p class="style1">System remind you to attend meeting "${conf.confName}". Please click <a href="http://${siteaddress}/?joinUrl=${joinUrl}">join in</a>  to join your meeting. Below is the meeting detail info:</p>
          <p class="style1"><span class="style6">Meeting Information:</span>  </p>        
          </td>
      </tr>
      <tr>
        <td><br />
		<table width="556" border="0" align="center" cellpadding="4" cellspacing="0">
		     <tr>
		         <td width="80%">
				
			</td>
            <td width="50%" class="style1">&nbsp;</td>
		  </tr>
		</table>
		<table width="556" border="1" align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="103" bgcolor="003366" class="style2"><div align="center"><span class="style4">Meeting Topic:</span></div></td>
            <td width="431" class="style1"><strong>${conf.confName}</strong></td>
          </tr>
          <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Security Code:</span></div></td>
            <td class="style1"><strong>${securityCode}</strong></td>
          </tr>
          <tr ${ispublicconf}>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Meeting Password:</span></div></td>
            <td class="style1"><strong>${conf.publicConfPass!'none'}</strong></td>
          </tr>
		  <#if conf.phonePass != ''>
			<tr>
				<td bgcolor="003366" class="style2"><div align="center"><span class="style5">H323/SIP password</span></div></td>
				<td class="style1"><strong>${conf.phonePass}</strong></td>
			</tr>	
		  </#if>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Meeting Moderator:</span></div></td>
            <td class="style1"><strong>${conf.compereName}</strong></td>
          </tr>
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Meeting Start Time:</span></div></td>
            <td class="style1"><strong>${conf.startTime?string("yyyy-MM-dd HH:mm")}(${entimezone})</strong></td>
          </tr> 
		  <tr>
            <td bgcolor="003366" class="style2"><div align="center"><span class="style5">Meeting End Time:</span></div></td>
            <td class="style1"><strong>${conf.endTime?string("yyyy-MM-dd HH:mm")}(${entimezone})</strong></td>
          </tr>
        </table><br /></td>
      </tr>
      <tr ${istelconf}>
        <td><p class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		<p class="style6">Quick Start </p>
        </td>
      </tr>
      <tr ${istelconf}>
        <td><br />
		<table width="579" border="0" ${istelconf} align="center" cellpadding="4" cellspacing="0">
          <tr>
            <td width="100%" colspan="2"><ul>
              <li><span class="style6">This meeting support telephone function，please dial below access number to join the meeting：</span></li>
            </ul></td>
          </tr>
          <tr>
            <td colspan="2"><table width="96%" ${istelconf} border="1" align="center" cellpadding="2" cellspacing="0">
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
           
            </table></td>
            </tr>
      
        </table>
        <br />
        </td>
      </tr>
      <tr>
	 
        <td class="style1"> <br/>
          <p class="style6">Support</p></td>
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
		       <td colspan="2"><span class="style1"><strong>Welcome to visit ConfCloud company website</strong></span><strong> </strong><a
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
       
       
    </table></td>
  </tr>
  <tr>
    <td height="24" bgcolor="ffffff" class="style6"><div align="right" class="style4">
      <table width="500" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="456"><div align="right" style="text-align: right;padding: 50px 0 10px;font-size: 22px;font-weight: bold;font-family: 'Microsoft Yahei';color: #003576;">
		  confcloud.
		  </div></td>
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

