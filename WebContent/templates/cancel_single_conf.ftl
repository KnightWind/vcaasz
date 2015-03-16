<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style></style>
</head>
  <body>
  	<label style="font-size: 16px;">尊敬的${user.userName}，您好！</label><br/>
	<!--<#if user.hostFlag!=1>-->
			<label style="font-size: 16px;">您被邀请出席(${conf.compereName})的会议已取消。(${conf.compereName})请您知晓。</label><br/>
	<!--<#else>-->
	    <label style="font-size: 16px;">您是“${conf.confName}”的主持人，您的会议已取消。</label><br/>
	<!--</#if>-->
	<label style="font-size: 16px;">
    	会议时间：${conf.startTime?string("yyyy年MM月dd日  HH时mm分")}(${timezone})
	</label><br/>
		<p>您有任何问题，可以参考http://${siteaddress}/help &nbsp;或与&nbsp; cs@confcloud.cn &nbsp;联系 。</p>
		<br/>
		<br/>
	<p>------------------------------------------------------------------</p>
   	<label style="font-size: 16px;">Dear ${user.userName}：</label><br/>
   <!--<#if user.hostFlag!=1>-->
		<label style="font-size: 16px;">You were be invited to attend (${conf.compereName})'s meeting has been canceled.</label><br/>
	<!--<#else>-->
	    	<label style="font-size: 16px;">You  meeting "${conf.confName}". has been canceled, Below is the meeting detail info:</label><br/>
	<!--</#if>-->
	<label style="font-size: 16px;">
    	Meeting Time：${conf.startTime?string("yyyy-MM-dd HH:mm:ss")}(${entimezone})
	</label><br/>
		<p>Any questions, please refer to http://${siteaddress}/help or contact cs@confcloud.cn</p>
		<br/>
		<br/>
    <br/>
  </body>
</html>
