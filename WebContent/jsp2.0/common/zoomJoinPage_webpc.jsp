<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title>${LANG['website.user.join.page.title']}</title>
	<meta name=”viewport” content=”width=device-width, initial-scale=1″ />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--	<link type="text/css" rel="stylesheet" href="https://www.zoomus.cn/static/38490/css/all.min.css" />--%>
<%--	<link type="text/css" rel="stylesheet" href="https://www.zoomus.cn/static/38490/css/frame.min.css" />--%>
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.index.css" />
	<script type="text/javascript" src="/assets/js/login/jquery.min.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.common.js?vc=09"></script>
	<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/lib/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"></script>
	<style>
		body {
		   min-width: 2px;
		   _width:expression((document.documentElement.clientWidth||document.body.clientWidth)<1002?"1002px":"");
		   overflow-x: auto;
		/*    overflow-y: auto; */
		}
		.biz-titleBar { 
			position: relative; 
		/* 	width: 100%; */
			background: #F9F9F9;
			background: linear-gradient(top,#F9F9F9,#EBEBEB);
			background: -moz-linear-gradient(top,#F9F9F9,#EBEBEB);
			background: -webkit-linear-gradient(top,#F9F9F9,#EBEBEB);
			filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F9F9F9,endColorstr=#EBEBEB);
		    padding: 0 15px;
			border-bottom: 1px solid #CCCCCC;
			height: 36px;
			line-height: 36px;   	
		}
		.biz-title {
		    cursor: default;
		    overflow: hidden;
		    text-overflow: ellipsis;
		    color: #333333;
		/*     height: 33px; */
		/*     line-height: 33px; */
		    padding: 0 16px 0 0;
			font-family: "Microsoft Yahei","微软雅黑","宋体";
			font-size: 15px;
		 
		}
		.icon-join {
		    background: url("/assets/images/sprite/icons-png8.png") no-repeat scroll -154px -188px transparent;
		    height: 18px;
		    margin: 7px 5px 7px 0;
		    width: 18px;
		}
		.biz-icon {
		    display: inline-block;
		    vertical-align: middle;
		}
		.biz-close {
		    display: block;
		    outline: none;
		    position: absolute;
		    text-decoration: none;
		    background: url("/assets/images/buttons/close.png") no-repeat scroll center center transparent;
		    height: 13px;
		    right: 10px;
		    text-indent: -9999em;
		    top: 10px;
		    width: 13px;
		}
		.biz-close:hover{
		    background-image: url(/assets/images/buttons/close-hover.png);
		}
		
	</style>
	<script type="text/javascript">
	$(function(){
		<c:if test="${blank==null || blank==''}">
			$(".biz-titleBar").css("display","none");//新开的窗口添加隐藏
		</c:if>
		<c:if test="${role==1}">
			$(".biz-titleBar").css("display","none");//新开的窗口添加隐藏
		</c:if>
	});
	</script>
</head>
<body style="padding:0px;margin:0 auto;width:600px;">
	<div style="width: 474px;height:auto;overflow: hidden;">
		<div class="biz-titleBar" style="position: relative;">
			<span class="biz-title"><i class="biz-icon icon-join"></i>${LANG['website.user.join.page.title']}</span>
			<a class="biz-close" onclick="return closeDialog()"></a>
		</div>
		<div class="list-names">
			<!-- 标记macOS -->
			<input type="hidden" id="is_mac_os" value="false"/>
			<input type="hidden" id="plugin_name" value="zoom.us launcher plugin" />
			<!-- 标记火狐浏览器 -->
			<input type="hidden" id="browser" value="firefox"/>
			<!-- 标记confno -->
			<input type="hidden" id="confno" value="${conf.confZoomId}"/>
			<!-- start 、join -->
			<input type="hidden" id="action" value="${action}"/>
			<!-- 主持人id -->
			<input type="hidden" id="hostId" value="${hostId}"/>
			<!-- 会议名字 -->
			<input type="hidden" id="meetingName" value="${conf.confName}"/>
			<!-- 主持人的token -->
			<input type="hidden" id="token" value="${token}"/>
			<!-- 会议的UUID -->
			<input type="hidden" id="UUID" value="${UUID}"/>
			<input type="hidden" id="uname" value="${uname}"/>
			<!-- 会议的密码 -->
			<input type="hidden" id="pwd" value="${pwd}"/>
			<input type="hidden" id="surl" value="${conf.startUrl}"/>
			<input type="hidden" id="jurl" value="${conf.joinUrl}"/>
			<input type="hidden" id="role" value="${role}"/>
			<!-- 插件 -->
			<embed id="BootPlugin" name="Bizconf launcher plugin" type="application/x-cfcloud-scriptable-plugin" style="width:0px;height:0px;" hidden="true"></embed>
<%--			<embed id="BootPlugin" name="BootPlugin" type="application/x-zoom-scriptable-plugin" style="width:0;height:0;" hidden="true"></embed>--%>
			<!-- mac检查插件设置该值 -->
			<input type="hidden" id="check_plugin_frame_field" value="true" /> 
			<!-- 插件提示 成功 -->
			<div id="tip0" style="display:none">
				<div class="list-names">
					<div id="BootPlugin_Tip0" class="face-success"  style="color: #000000;font-size: 14px;text-align: center;">
			 			 ${LANG['website.user.joinmeeting.page.protcoltext1']}<a style="text-decoration: underline;font-size: 16px;font-weight: bold;" href="javascript:downloadplugin();"><strong>${LANG['website.user.leftmenu.support.download']}</strong></a> ${LANG['website.user.joinmeeting.page.protcoltext2']}
					 </div> 	
					<div id="onclickBtn_protcol" style="text-align:center;padding-bottom:20px; margin:0 auto; ">
						<a onclick="javascript:joinConferenceByProtcol();" class="input-button"/>${LANG['website.user.join.page.title']}</a>
					</div>
				</div>
			</div>
			<div id="tip1" style="display:none">
				<div class="list-names">
					<div id="BootPlugin_Tip" class="face-success"  style="color: #000000">
			 			${LANG['website.user.join.page.tip1']}
					 </div> 	
					<div id="onclickBtn" style="text-align:center;padding-bottom:20px; margin:0 auto; ">
						<a onclick="javascript:tryToProtcolStart();" class="input-button"/>${LANG['website.user.join.page.title']}</a>
					</div>
				</div>
			</div>
			<!-- 插件提示 失败 -->
			<div id="tip2" style="display:none">
				<div class="list-names">
					 <div id="BootPlugin_Tip2" class="face-warn" style="color: #000000">
					 	${LANG['website.user.join.page.tip2']}
					 </div> 	
					 <div style="font-weight:bold; text-align: center;font-size: 14px;color:#FF8247;">${LANG['website.user.join.page.tip10']}</div><br/> 	
					<div id="onclickBtn2" style="text-align:center;padding-bottom:20px; margin:0 auto; ">
						<a onclick="javascript:downloadplugin();" class="input-button"/>${LANG['website.user.download.center.page.download.now']}</a>
					</div>
				</div>
			</div>
			<iframe style="width:0px;height:0px;" src="" id="check_plugin_frame_2"></iframe>
			<iframe style="width:0px;height:0px;" src="" id="check_plugin_frame_3"></iframe>
		</div>
	</div>
<script type="text/javascript">

var clickOnceUrl = '${clickOnceUrl}';

function joinConference(){
	var confNo = $("#confno").val();
	var hostId = $("#hostId").val();
	var uname = $("#uname").val();
	var token = $("#token").val();
	var uuid = $("#UUID").val();
	var pwd = "";//是会议号加入需要手动输入密码
	
	if($("#action").val() == 'start'){
		//getStartMeetingUrl(confNo,hostId,uname,token);
		stratMeetingByClickOnce(clickOnceUrl,confNo,hostId,uname,token);
	}else{
		//getJoinMeetingUrl(confNo,uuid,pwd);
		joinMeetingByClickOnce(clickOnceUrl,confNo,uuid,pwd);
	}
}

function downloadplugin(){
	//alert("${clientDownLoadUrl}");
	//return;
	window.location = "${clientDownLoadUrl}";
}

//协议入会
function joinConferenceByProtcol(){
	var confNo = $("#confno").val();
	var hostId = $("#hostId").val();
	var uname = $("#uname").val();
	var token = $("#token").val();
	var uuid = $("#UUID").val();
	var pwd = "";//是会议号加入需要手动输入密码
	//协议地址
	var encodeName = encodeURIComponent(encodeURIComponent(uname));
	if(startMeetingEncodeOnce()){
		encodeName = encodeURIComponent(uname);
	}
	
	var protcolUrl = "${zoom_http}";
	if($("#action").val() == "start"){
		protcolUrl += "start";
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&sid="+hostId;	//主持人登录需要这个
		protcolUrl += "&stype=100";	//主持人自动登录必备参数
		protcolUrl += "&uid="+hostId;
		protcolUrl += "&uname="+encodeName;	//入会人名字
		protcolUrl += "&token="+token;//主持人token
	}else{
		protcolUrl += $("#action").val();
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&confid="+uuid;
		protcolUrl += "&pwd="+$("#pwd").val();
	}
	$("#check_plugin_frame_3").attr("src",protcolUrl);
}
</script>
<script type="text/javascript" src="${zoom_all_min}"></script>
<script type="text/javascript">
$(function(){
	//插件
	var np = $("#BootPlugin")[0];
	var timer;
	//开启插件
	function startPlugin() {
		//alert("111");
		window.clearInterval(timer);
		$("#BootPlugin_Tip").html("${LANG['website.user.join.page.tip4']}").show();
		var ret = -2;
		try{
			var params = {};
			if($("#action").val() == "join"){//参会者加入会议
				params["dr"] = "https://www.zoomus.cn/client";
				params["action"] = "join";
				params["confno"] = $("#confno").val();
				params["pwd"] = $("#pwd").val();
			}else{//主持人加入会议
				params["action"] = "start";
				params["sid"] = $("#hostId").val();
				params["stype"] = "100";
				params["token"] = $("#token").val();
				params["uid"] = $("#hostId").val();
				params["uname"] = $("#uname").val();
				params["confno"] = $("#confno").val();
				//params["mcv"] = "0.92.11227.0929";可选
			}
			if($('#is_mac_os').val() == 'true') {
				ret = np.launchApplication($.param(params));
			} else {
				var paramName;
				for(paramName in params) {
					if(params.hasOwnProperty(paramName)) {
						np.setParameter(paramName, params[paramName]);
					}
				}
				ret = np.launchApplication();
				
				//alert(ret);
			}
		}catch (e) {
			alert("入会传参出错："+e);
		}
		// 检查请求插件返回结果
		switch (ret) {
			case 0:  // success
				$("#tip1").css("display","block"); //一般情况都是通过连接加入该会议
				$("#BootPlugin_Tip").html("${LANG['website.user.join.page.tip5']}").show();
				$("#onclickBtn").css("display","none");
				myfun();//自动关闭弹窗
				break;
			case -1: // error
			case 1:  // error
			case 2:  // uninstalled
				//统一成一个下载路径
				$("#tip2").css("display","block");
				var tip1 = $("#tip1").is(":visible");
				if(tip1){
					$("#tip1").css("display","none");
				}
				break;
			case 3:  // not latest version
				$("#BootPlugin_Tip").html("${LANG['website.user.join.page.tip6']}").show();
				break;
		}
		//跳转
		function jump(url, loadInTop){
			if (loadInTop) {
				top.location.replace(SB.baseUrl + SB.contextPath + url);
			} else {
				window.location.replace(SB.baseUrl + SB.contextPath + url);
			}
		}
	}
	
	function startPluginOuter() {//mac系统进行判断
		if($('#is_mac_os').val() == 'true') {
			
			$("#BootPlugin").hide();
			var tip1Status = $("#tip1").is(":visible");
			if(tip1Status){
				$("#tip1").css("display","none");
			}
			$("#tip2").css("display","block");
			$("#BootPlugin_Tip2").addClass("face-success");
			$("#BootPlugin_Tip2").removeClass("face-warn");
			$("#BootPlugin_Tip2").html("${LANG['website.user.join.page.macpro.tip.info']}").show();
			
			var uname = $("#uname").val();
			var encodeName = encodeURIComponent(encodeURIComponent(uname));
			if(startMeetingEncodeOnce()){
				encodeName = encodeURIComponent(uname);
			}
			
			var url = "${zoom_http}" + $("#action").val()+"?confno="+$("#confno").val();
			if($("#action").val() == "start"){
				url += "&sid="+$("#hostId").val();	//主持人登录需要这个
				url += "&stype=100";				//主持人自动登录必备参数
				url += "&uid="+$("#hostId").val();
				url += "&uname="+encodeName;	//入会人名字
				url += "&token="+$("#token").val();//主持人token
			}else{
				url += "&confid="+$("#UUID").val();
				url += "&pwd="+$("#pwd").val();
			}
			$("#check_plugin_frame_2").attr('src',url);
			
			/**
			var exist = false;
			try {
				if(np.isZoomusAppExist && np.isZoomusAppExist()) {
					exist = true;
					$("#check_plugin_frame_field").val("true");//修改插件值
				}
    	    } catch(e) {
    	    }
    	    if(exist) {//客户度存在，通过协议调用客户端
				$("#BootPlugin").hide();
				$("#tip2").css("display","none");	//提示下载关闭
				$("#tip1").css("display","block"); //一般情况都是通过连接加入该会议
				$("#BootPlugin_Tip").html("${LANG['website.user.join.page.tip5']}").show();
				$("#onclickBtn").css("display","none");
				
				if($("#action").val() == "start"){
					var requestURL = $("#surl").val();
					$('#check_plugin_frame_2').attr('src',requestURL);
				}else{
					var requestURL = $("#jurl").val();
					$('#check_plugin_frame_2').attr('src',requestURL);
				}
				
				var url = "${zoom_http}" + $("#action").val()+"?confno="+$("#confno").val();
				if($("#action").val() == "start"){
					url += "&sid="+$("#hostId").val();	//主持人登录需要这个
					url += "&stype=100";				//主持人自动登录必备参数
					url += "&uid="+$("#hostId").val();
					url += "&uname="+$("#uname").val();	//入会人名字
					url += "&token="+$("#token").val();//主持人token
				}else{
					url += "&confid="+$("#UUID").val();
					url += "&pwd="+$("#pwd").val();
				}
				$("#check_plugin_frame_2").attr('src',url);
				//window.location = requestURL;
    	    } else {
    	    	$("#tip2").css("display","block");//提示下载开启
    	    	var tip1 = $("#tip1").is(":visible");
				if(tip1){
					$("#tip1").css("display","none");
				}
    	    }*/
		} else {
			startPlugin();
		}
	}
	
	function getOS(){//检查系统版本
		var platform =navigator.platform;
		//var platform ="MacIntel";
		var isAir = (platform.toLowerCase().indexOf("air") > -1);
		var isMac = (platform.toLowerCase().indexOf("mac")  > -1); 
		if(isMac || isAir){ 
			$("#is_mac_os").val("true");
		}
	}
	
	function getUserAgent(){ //检查浏览器
	   var OsObject = "";  
	   if(navigator.userAgent.indexOf("MSIE")>0 || navigator.userAgent.indexOf("rv")>0) { //rv ie11
		   $('#browser').val("ie");
	   }  
	   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
		   $('#browser').val("firefox");  
	   }  
	   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
		   $('#browser').val("safari"); 
	   }   
	   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
		   $('#browser').val("camino"); 
	   }  
	} 
	
	function checkPlugin() {
		//判断插件是否运行
		if(np.launchApplication) {
			$("#BootPlugin").css({
				width: 0,
				height: 0
			});
			var tip2Status = $("#tip2").is(":visible");
			if(tip2Status){
				$("#tip2").css("display","none");
			}
			$("#tip1").css("display","block");
			startPluginOuter();
		} else {
			//alert("juge!");
			tryToProtcolStart();
			$("#BootPlugin").css({
				width: 0,
				height: 0
			});
			//如果支持clickOnce入会
			if(clickOnceUrl && $('#browser').val() == 'ie'){
				$("#tip1").css("display","block");
			}else{
				$("#tip0").show();
				$("#tip1").hide();
				$("#tip2").hide();
			}
			/**if($('#browser').val() != 'ie') {//火狐 谷歌 和360
				//插件
				$("#BootPlugin").css({
					width: 0,
					height: 0
				});
				var tip1 = $("#tip1").is(":visible");
				if(tip1){
					$("#tip1").css("display","none");
				}
				$("#tip2").css("display","block");
			} else {//ie浏览器，不可以使用$("#BootPlugin")[0]
				$("#BootPlugin").css({
					width: 0,
					height: 0
				});
			
				//如果支持clickOnce入会
				if(clickOnceUrl){
					$("#tip1").css("display","block");
				}else{
					$("#tip0").show();
					$("#tip1").hide();
					$("#tip2").hide();
				}
			}*/
		}
	}
	getOS();//检查系统版本
	getUserAgent();//检测浏览器
	//timer = window.setInterval(checkPlugin, 1000);
	//首先检查插件
	checkPlugin();
});

function myfun(){
	if($('#browser').val() != 'ie'){
		var dialog = parent.$("#joinMeeting");
		dialog.trigger("closeDialog");	
	}
}
//window.onload=myfun;
function closeDialog(result) {
	var dialog = parent.$("#joinMeeting");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}

function tryToProtcolStart(){
	var uname = $("#uname").val();
	var encodeName = encodeURIComponent(encodeURIComponent(uname));
	if(startMeetingEncodeOnce()){
		encodeName = encodeURIComponent(uname);
	}
	var url = "${zoom_http}" + $("#action").val()+"?confno="+$("#confno").val();
	if($("#action").val() == "start"){
		url += "&sid="+$("#hostId").val();	//主持人登录需要这个
		url += "&stype=100";				//主持人自动登录必备参数
		url += "&uid="+$("#hostId").val();
		url += "&uname="+encodeName;	//入会人名字
		url += "&token="+$("#token").val();//主持人token
	}else{
		url += "&confid="+$("#UUID").val();
		url += "&pwd="+$("#pwd").val();
	}
	$("#check_plugin_frame_2").attr('src',url);
}
</script>
<c:if test="${system == 'mac'} ">
<!-- mac引用 -->
<script type="text/javascript" src="${zoom_j_min}"></script>
</c:if>
</body>
</html>
