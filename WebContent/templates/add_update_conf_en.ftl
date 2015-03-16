<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style></style>
</head>
  <body>
   	<label style="font-size: 11px;">Dear ${user.userName}：</label><br/>

   <!--<#if user.hostFlag!=1>-->
		<label style="font-size: 11px;">You were be invited to attend ${conf.compereName}'s meeting “${conf.confName}” has been modified.</label><br/>
	<!--<#else>-->
	    	<label style="font-size: 11px;">You  meeting "${conf.confName}". has been modified, Below is the meeting detail info:</label><br/>
	<!--</#if>-->
   
    <label style="font-size: 11px;">
    <!--<#if conf.clientCycleConf>-->
    	
    <!--<#elseif conf.portalCycleConf>-->
    	Meeting Time：${conf.startTime?string("HH:mm:ss")}(${entimezone}) Cycle type:${cycleModeEn}, Cycle scope：${cycleRepeatEn}
    <!--<#else>-->
    	Meeting Time：${conf.startTime?string("yyyy-MM-dd HH:mm:ss")}(${entimezone})
    <!--</#if>-->
    </label><br/>
    <!--<label style="font-size: 11px;">Meeting Time:${conf.startTime?string("HH:mm:ss")}(${entimezone})</label><br/>-->
	<!--<#if conf.hostKey?? && conf.hostKey!=''>-->
	    <label style="font-size: 11px;">Meeting Password:${conf.hostKey!'(none)'}</label><br/>
	<!--</#if>-->
	  <label style="font-size: 11px;">You can join meeting through those ways:</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">Join meeting by PC,MAC,IPAD,IPhone，Android equipment:</label><br/>
		<label style="font-size: 11px;">please click this link:${joinUrl}</label><br/>
		<label style="font-size: 11px;">Or visit http://${siteaddress}, and input meeting ID: ${conf.confZoomId}   
			<!--<#if conf.hostKey?? && conf.hostKey!=''>-->
		     	  password: ${conf.hostKey!'(无)'}
		    <!--</#if>-->
		</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">Join by scanner Qr code: </label><br/>
		<label style="font-size: 11px;">click to view Qr code: ${qccodeURL}</label><br/>
		<label style="font-size: 11px;">Meeting ID:${conf.confZoomId}</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">Join by H323/SIP endpoint:</label><br/>
		<label style="font-size: 11px;">Meeting IP：${rcIPs}</label><br/>
		<label style="font-size: 11px;">Meeting ID：${conf.confZoomId}</label><br/>
		<label style="font-size: 11px;">H323/SIP password：${conf.phonePass}</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<br/>
		-
		<label style="font-size: 11px;">Any questions, please refer to http://${siteaddress}/help or contact cs@confcloud.cn</label><br/>
		<br/>
		<br/>
		<label style="font-size: 11px;">thanks.	 </label><br/>
    <br/>
    
    <p>------------------------------------------------------------------</p>
    
    <label style="font-size: 11px;">尊敬的${user.userName}，您好！</label><br/>
 
	<!--<#if user.hostFlag!=1>-->
			<label style="font-size: 11px;">您被邀请出席 ${conf.compereName} 的会议“${conf.confName}”已修改。</label><br/>
	<!--<#else>-->
	    <label style="font-size: 11px;">您的会议 "${conf.confName}"已修改：</label><br/>
	<!--</#if>-->
    
    <label style="font-size: 11px;">
    <!--<#if conf.clientCycleConf>-->
    <!--<#elseif conf.portalCycleConf>-->
    	会议时间：${conf.startTime?string("HH:mm:ss")}(${timezone}) 周期类型:${cycleMode}, 周期范围：${cycleRepeat}
    <!--<#else>-->
    	会议时间：${conf.startTime?string("yyyy-MM-dd HH:mm:ss")}(${timezone})
    <!--</#if>-->
    </label><br/>
     <!--<label style="font-size: 11px;">会议时间： ${conf.startTime?string("HH:mm:ss")}(${timezone})</label><br/>-->
    <label style="font-size: 11px;"> 您可以通过以下方式加入会议：</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">通过PC，MAC，IPAD，IPhone，Android设备加入：</label><br/>
		<label style="font-size: 11px;">点击链接加入：${joinUrl}</label><br/>
		<label style="font-size: 11px;">或者访问    http://${siteaddress}  加入，输入会议ID：${conf.confZoomId}  
			<!--<#if conf.hostKey?? && conf.hostKey!=''>-->
		     	 会议密码：${conf.hostKey!'(无)'}
		    <!--</#if>-->
		</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">扫描二维码加入： </label><br/>
		<label style="font-size: 11px;">点击查看二维码：${qccodeURL}</label><br/>
		<label style="font-size: 11px;">会议ID：${conf.confZoomId}</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<label style="font-size: 11px;">通过H323/SIP终端加入：</label><br/>
		<label style="font-size: 11px;">会议地址：${rcIPs}</label><br/>
		<label style="font-size: 11px;">会议ID：${conf.confZoomId}</label><br/>
		<label style="font-size: 11px;">H323/SIP终端密码：${conf.phonePass}</label><br/>
		<label style="font-size: 11px;">------------------------------------------------------------</label><br/>
		<br/>
		<br/>
		-
		<label style="font-size: 11px;">您有任何问题，可以参考http://${siteaddress}/help或与cs@confcloud.cn联系 。</label><br/>
		<br/>
		<br/>
		<label style="font-size: 11px;">谢谢。	 </label><br/>
  </body>
</html>

