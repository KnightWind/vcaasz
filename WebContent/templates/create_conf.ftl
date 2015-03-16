<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style></style>
</head>
<body style=" margin:0; padding:0; border:0; background:#E6E6E6">
<div style=" margin:0; padding:0; border:0; background:#FFF; margin:0px auto; width:700px;"> 
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#EB6C00; font-size:12px; margin:50px 40px 16px">尊敬的${user.trueName}，您好！</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px;">您是<span href="#" style=" margin:0; padding:0; border:0; color:#EB6C00; font-weight:bold;" >"${conf.confName}"</span>的主持人,
         请<a href="http://${siteaddress}/?joinUrl=${joinUrl}" style=" margin:0; padding:0; border:0;" >点击加入</a>，可以开始您的会议！</p>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px;">以下是您的会议详细信息：</p>
      </td>
    </tr>
  </table>
  <table cellpadding="0" cellspacing="0" border="0">
  <tr>
  	<td>
  
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议主题：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.confName}</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议主持人</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.compereName}</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议开始时间：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.startTime?string("yyyy-MM-dd HH:mm")}(${timezone})</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议结束时间：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.endTime?string("yyyy-MM-dd HH:mm")}(${timezone})</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议安全号：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.compereSecure}</p>
      </td>
    </tr>
    <#if conf.publicConfPass!=''>
    <tr>
      <td>
        <h2 style="margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">会议密码：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.publicConfPass!'无'}</p>
      </td>
    </tr>
    </#if>
    <#if conf.confType==1 || conf.confType==3>
    <tr>
					<td>
									<h2 style="margin:0;padding:0;border:0;color:#333;font-size:12px;margin-left:65px;margin-top:18px;">
										此会议提供电话功能，您可以拨打如下电话号码，加入会议：
									</h2>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing=0 style="margin:18px 0 0 65px;font-size:12px;border:1px solid #C1C1C1;">
										<tr bgcolor="#004d9e">
											<td align="center" style="width:120px;color:#FFFFFF;padding:5px 0px;border-right:1px solid #C1C1C1;">国家</td>
											<td align="center" style="width:150px;color:#FFFFFF;padding:5px 0px;border-right:1px solid #C1C1C1;">号码类型</td>
											<td align="center" style="width:150px;color:#FFFFFF;padding:5px 0px;">号码</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;">全球</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;">直拨号</td>
											<td align="center" style="padding:5px 0px;border-bottom:1px solid #C1C1C1;">${accessNumber1!'8610 5821 4900'}</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;">中国</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;">国内免费</td>
											<td align="center" style="padding:5px 0px;border-bottom:1px solid #C1C1C1;">${accessNumber2!'800 870 1088'}</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;">中国</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;">本地付费</td>
											<td align="center" style="padding:5px 0px;">${accessNumber3!'400 874 1088'}</td>
										</tr>
									</table>
								</td>
							</tr>
    </#if>
    <tr>
      <td>
        <p style=" margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px; margin-bottom:20px;  "> 您有任何问题，可以参考<a href="http://${userGuider!'www.confcloud.cn/help'}" style=" margin:0; padding:0; border:0; color:#EB6C00" >用户手册</a>或联系客服:400 082 6161</p>
      </td>
    </tr>
  </table>
  </td>
  </tr></table>
  
  <!--英文-->
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#EB6C00; font-size:12px; margin:50px 40px 16px">Dear ${user.trueName},</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px;">You are the Moderator of meeting: <span href="#" style=" margin:0; padding:0; border:0; color:#EB6C00; font-weight:bold;" >"${conf.confName}"</span>,
         Please click <a href="http://${siteaddress}/?joinUrl=${joinUrl}" style=" margin:0; padding:0; border:0;" >"join in"</a> to join your meeting</p>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:22px; margin:0px 40px;">Below is the meeting detail info：</p>
      </td>
    </tr>
  </table>
  <table cellpadding="0" cellspacing="0" border="0">
  <tr>
  	<td>
  
  <table cellpadding="0" cellspacing="0" border="0" width="700" style=" background:#fff;">
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Meeting Topic：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.confName}</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Meeting Moderator</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style=" font-family: arial;margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.compereName}</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Meeting Start Time：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.startTime?string("yyyy-MM-dd HH:mm")}(${timezone})</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Meeting End Time：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.endTime?string("yyyy-MM-dd HH:mm")}(${timezone})</p>
      </td>
    </tr>
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Security Code：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.compereSecure}</p>
      </td>
    </tr>
    <#if conf.publicConfPass!=''>
    <tr>
      <td>
        <h2 style="font-family: arial;margin:0; padding:0; border:0; color:#333; font-size:12px; margin-left:65px; margin-top:18px; ">Meeting Password：</h2>
      </td>
    </tr>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px;  ">${conf.publicConfPass!'None'}</p>
      </td>
    </tr>
    </#if>
    <#if conf.confType==1 || conf.confType==3>
    <tr>
					<td>
									<h2 style="font-family: arial;margin:0;padding:0;border:0;color:#333;font-size:12px;margin-left:65px;margin-top:18px;">
										This meeting support telephone function，Your Security Code is ${conf.compereSecure}， please dial below access number to join the meeting：
									</h2>
								</td>
							</tr>
							<tr>
								<td>
									<table cellspacing=0 style="font-family: arial;margin:18px 0 0 65px;font-size:12px;border:1px solid #C1C1C1;">
										<tr bgcolor="#004d9e">
											<td align="center" style="width:120px;color:#FFFFFF;padding:5px 0px;border-right:1px solid #C1C1C1;font-family: arial;">Country</td>
											<td align="center" style="width:150px;color:#FFFFFF;padding:5px 0px;border-right:1px solid #C1C1C1;font-family: arial;">Number Type</td>
											<td align="center" style="width:150px;color:#FFFFFF;padding:5px 0px;font-family: arial;">Number</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;font-family: arial;">International</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;font-family: arial;">Direct dial in number</td>
											<td align="center" style="padding:5px 0px;border-bottom:1px solid #C1C1C1;font-family: arial;">${accessNumber1!'8610 5821 4900'}</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;font-family: arial;">China</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;border-bottom:1px solid #C1C1C1;font-family: arial;">Toll Free</td>
											<td align="center" style="padding:5px 0px;border-bottom:1px solid #C1C1C1;font-family: arial;">${accessNumber2!'800 870 1088'}</td>
										</tr>
										<tr>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;font-family: arial;">China</td>
											<td align="center" style="padding:5px 0px;border-right:1px solid #C1C1C1;font-family: arial;">Toll</td>
											<td align="center" style="padding:5px 0px;font-family: arial;">${accessNumber3!'400 874 1088'}</td>
										</tr>
									</table>
								</td>
							</tr>
    </#if>
    <tr>
      <td>
        <p style="font-family: arial; margin:0; padding:0; border:0; font-size:12px; color:#666666; line-height:20px; margin-left:65px;line-height:22px; margin-bottom:20px;  "> Any questions, please refer to <a href="http://${userGuider!'www.confcloud.cn/help'}" style=" margin:0; padding:0; border:0; color:#EB6C00" >User Guide</a> or contact Customer Service:400 082 6161</p>
      </td>
    </tr>
  </table>
  </td>
  </tr></table>
  
  <table cellpadding="0" cellspacing="0" border="0" width="700" style="background:#ccc;">
    <tr height="40" align="center" bgcolor="#004D9E">
      <td>
        <p style="margin:0; padding:0; border:0; font-size:12px; color:#fff; line-height:20px; font-family:Arial, Helvetica, sans-serif">Copyright © 2003-2013 confcloud Telecom Co., Ltd. All right Reserved. 沪ICP备 11034277号-8 </p>
      </td>
    </tr>
  </table>
</div>
</body>
</html>