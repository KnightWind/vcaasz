<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title>${LANG['website.user.join.page.title']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
   	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.index.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/login.css" />
	<script type="text/javascript" src="/assets/js/login/jquery.min.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"></script>
	<style>
.input-select option {
	padding: 0 5px;
}
.input-text, .input-area {
	border: 1px solid #DADADA;
	border-radius: 3px;
	padding: 5px;
	height: 16px;
	box-shadow: 0 1px #f7f7f7 inset;
}
.input-text:focus, .input-area:focus {
	border: 1px solid #8caac8;
	box-shadow: 0 0 5px #d6e2ed;
}
.input-button,.input-submit,.input-green,.input-gray,.input-cancel,.input-disable,.input-warn {
	border-radius: 3px;
	border-style: solid;
	border-width: 1px;
	cursor: pointer;
	height: 30px;
	padding: 0 20px;
	text-align: center;
	overflow: visible;
	vertical-align: middle;
	margin: 0;
}
:root .input-button,
:root .input-submit,
:root .input-green,
:root .input-gray,
:root .input-cancel,
:root .input-disable,
:root .input-warn {
	min-width: 80px;
}
button.input-disable, .input-disable[type=button], .input-disable[type=submit] {
	padding: 0 20px;
	height: 30px;
	line-height: 100%;
}
.input-button,.input-submit {
	background: #44ADEF;
	background: linear-gradient(top,#44ADEF,#2B94D6);
	background: -moz-linear-gradient(top,#44ADEF,#2B94D6);
	background: -webkit-linear-gradient(top,#44ADEF,#2B94D6);
	border-color: #44ADEF;
	color: #FFFFFF;
	box-shadow: 0 1px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#44ADEF,endColorstr=#2B94D6);
}
.input-button:hover,.input-submit:hover {
	background: #39AFED;
	background: linear-gradient(top,#5EC5FF,#39AFED);
	background: -moz-linear-gradient(top,#5EC5FF,#39AFED);
	background: -webkit-linear-gradient(top,#5EC5FF,#39AFED);
	text-decoration: none;
	border-color: #44ADEF;
	box-shadow: 0 0.5px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#5EC5FF,endColorstr=#39AFED);
}
.input-button:active,.input-submit:active {
	background: #2B94D6;
	background: linear-gradient(bottom,#44ADEF,#2B94D6);
	background: -moz-linear-gradient(bottom,#44ADEF,#2B94D6);
	background: -webkit-linear-gradient(bottom,#44ADEF,#2B94D6);
	text-decoration: none;
	box-shadow: 0 1px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#2B94D6,endColorstr=#44ADEF);
}
.input-gray,.input-cancel {
	background: #FFFFFF;
	background: linear-gradient(top,#FFFFFF,#ECECEC);
	background: -moz-linear-gradient(top,#FFFFFF,#ECECEC);
	background: -webkit-linear-gradient(top,#FFFFFF,#ECECEC);
	border-color: #CCCCCC;
	color: #666666;
	box-shadow: 0 1px #F7F7F7 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FFFFFF,endColorstr=#ECECEC);
}
.input-gray:hover,.input-cancel:hover {
	background: #FDFDFD;
	background: linear-gradient(top,#FFFFFF,#F7F7F7);
	background: -moz-linear-gradient(top,#FFFFFF,#F7F7F7);
	background: -webkit-linear-gradient(top,#FFFFFF,#F7F7F7);
	text-decoration: none;
	box-shadow: 0 -1px #F0F0F0 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FFFFFF,endColorstr=#F7F7F7);
}
.input-gray:active,.input-cancel:active {
	background: #DADADA;
	background: linear-gradient(bottom,#FEFEFE,#DADADA);
	background: -moz-linear-gradient(bottom,#FEFEFE,#DADADA);
	background: -webkit-linear-gradient(bottom,#FEFEFE,#DADADA);
	text-decoration: none;
	box-shadow: 0 1px #D9D8D8 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#DADADA,endColorstr=#FEFEFE);
}
.input-warn {
	background: #F26866;
	background: linear-gradient(top,#F26866,#B60307);
	background: -moz-linear-gradient(top,#F26866,#B60307);
	background: -webkit-linear-gradient(top,#F26866,#B60307);
	border-color: #B81F24;
	color: #FFFFFF;
	box-shadow: 0 1px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F26866,endColorstr=#B60307);
}
.input-warn:hover {
	background: #EE6764;
	background: linear-gradient(top,#F39492,#EE6764);
	background: -moz-linear-gradient(top,#F39492,#EE6764);
	background: -webkit-linear-gradient(top,#F39492,#EE6764);
	text-decoration: none;
	border-color: #ED605C;
	box-shadow: 0 0.5px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F39492,endColorstr=#EE6764);
}
.input-warn:active {
	background: #B60307;
	background: linear-gradient(bottom,#F26866,#B60307);
	background: -moz-linear-gradient(bottom,#F26866,#B60307);
	background: -webkit-linear-gradient(bottom,#F26866,#B60307);
	text-decoration: none;
	box-shadow: 0 1px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#B60307,endColorstr=#F26866);
}

.input-green {
	background: #83c74c;
	background: linear-gradient(top,#83c74c,#6eb337);
	background: -moz-linear-gradient(top,#83c74c,#6eb337);
	background: -webkit-linear-gradient(top,#83c74c,#6eb337);
	border-color: #5da127;
	color: #FFFFFF!important;
	box-shadow: 0 1px #83c84c inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#83c74c, endColorstr=#6eb337);
}
.input-green:hover {
	background: #8cce58;
	background: linear-gradient(top,#a0e26c,#8cce58);
	background: -moz-linear-gradient(top,#a0e26c,#8cce58);
	background: -webkit-linear-gradient(top,#a0e26c,#8cce58);
	text-decoration: none;
	border-color: #71A745;
	box-shadow: 0 0.5px #94da5d inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#a0e26c, endColorstr=#8cce58);
}
.input-green:active {
	background: #6EB337;
	background: linear-gradient(bottom,#83C74C,#6EB337);
	background: -moz-linear-gradient(bottom,#83C74C,#6EB337);
	background: -webkit-linear-gradient(bottom,#83C74C,#6EB337);
	text-decoration: none;
	box-shadow: 0 1px #6DA442 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#6EB337, endColorstr=#83C74C);
}
a.input-button,a.input-submit,a.input-green,a.input-gray,a.input-cancel,a.input-disable,a.input-warn {
	display: inline-block;
	min-width: 38px!important;
	height: 28px;
	line-height: 28px;
	text-decoration: none;
	color: #333333;
}
a.input-button,a.input-submit,a.input-green,
.input-button:link, .input-submit:link, .input-green:link,
.input-button:visited, .input-submit:visited, .input-green:visited {
	color: #FFFFFF;
}
.input-disable,.input-disable:hover,.input-disable:active,a.input-disable,a.input-disable:hover,a.input-disable:active {
	background: #F2F0F0;
	border-color: #CCCCCC;
	color: #B5B5B5!important;
	cursor: default;
	box-shadow: none;
	background: linear-gradient(top, #F0F0F0, #EBEBEB);
	background: -moz-linear-gradient(top, #F0F0F0, #EBEBEB);
	background: -webkit-linear-gradient(top, #F0F0F0, #EBEBEB);
	text-decoration: none;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#F0F0F0, endColorstr=#EBEBEB);
}
input[readonly], input[readonly]:focus, input[readonly]:hover {
	background-color: #F9F9F9;
	cursor: default;
	box-shadow: none;
}
input[type=radio],input[type=checkbox] {
	vertical-align: middle;
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
</head>
<body >
<jsp:include page="/jsp2.0/user/loginHead.jsp" />
	<div style="width: 1360px;height: 480px; margin-top: 20px;margin-bottom: -40px;margin-left:auto;margin-right:auto">
		<div style="width: 1000px;height: 480px;border: solid 1px #CCCCCC;margin-left: 180px;">
			<div style="overflow: hidden;">
				<div class="list-names">
					<!-- 标记macOS -->
					<input type="hidden" id="is_mac_os" value="false" /> <input type="hidden" id="plugin_name" value="zoom.us launcher plugin" />
					<!-- 标记火狐浏览器 -->
					<input type="hidden" id="browser" value="firefox" />
					<!-- 标记confno -->
					<input type="hidden" id="confno" value="${conf.confZoomId}" />
					<!-- start 、join -->
					<input type="hidden" id="action" value="${action}" />
					<!-- 主持人id -->
					<input type="hidden" id="hostId" value="${hostId}" />
					<!-- 会议名字 -->
					<input type="hidden" id="meetingName" value="${conf.confName}" />
					<!-- 主持人的token -->
					<input type="hidden" id="token" value="${token}" />
					<!-- 会议的UUID -->
					<input type="hidden" id="UUID" value="${UUID}" />
					
					<input type="hidden" id="uname" value="${uname}" />
					<!-- 会议的密码 -->
					<input type="hidden" id="pwd" value="${pwd}" />
					<input type="hidden" id="surl" value="${conf.startUrl}" /> 
					<input type="hidden" id="jurl" value="${conf.joinUrl}" />
					<!-- 插件 -->
					<embed id="BootPlugin" name="Bizconf launcher plugin" type="application/x-cfcloud-scriptable-plugin" style="width:0px;height:0px;" hidden="true"></embed>
<%--				<embed id="BootPlugin" name="BootPlugin" type="application/x-zoom-scriptable-plugin" style="width:0px;height:0px;" hidden="true"></embed>--%>
					<!-- mac检查插件设置该值 -->
					<input type="hidden" id="check_plugin_frame_field" value="true" />
					
					<!-- 协议启动 -->
					<div id="tip0" style="margin-top:97px;display:none ">
						<div id="BootPlugin_Tip0" class="face-success"
							style="color: #000000;font-size: 14px;text-align: center;">
								${LANG['website.user.joinmeeting.page.protcoltext1']}<a style="text-decoration: underline;font-size: 16px;font-weight: bold;" href="javascript:downloadplugin();"><strong>${LANG['website.user.leftmenu.support.download']}</strong></a> ${LANG['website.user.joinmeeting.page.protcoltext2']}
							</div>
						<div id="onclickBtn_protcol"
							style="text-align:center;padding-bottom:20px; margin:0 auto; ">
							<a onclick="javascript:joinConferenceByProtcol();" class="input-button" />${LANG['website.user.join.page.title']}</a>
						</div>
					</div>
					<!-- 插件提示 成功 -->
					<div id="tip1" style="margin-top:97px;display:none ">
						<div id="BootPlugin_Tip" class="face-success"
							style="color: #000000">${LANG['website.user.join.page.tip1']}</div>
						<div id="onclickBtn"
							style="text-align:center;padding-bottom:20px; margin:0 auto; ">
							<a onclick="javascript:joinConferenceByProtcol();" class="input-button" />${LANG['website.user.join.page.title']}</a>
						</div>
					</div>
					<!-- 插件提示 失败 -->
					<div id="tip2" style="margin-top:97px;display:none">
						<div class="list-names">
							<div id="BootPlugin_Tip2" class="face-warn"
								style="color: #000000">${LANG['website.user.join.page.tip2']}</div>
							<div id="instructionTip" style="font-weight:bold; text-align: center;font-size: 14px;color:#FF8247;">${LANG['website.user.join.page.tip8']}</div><br/>
							<div id="onclickBtn2"
								style="text-align:center;padding-bottom:20px; margin:0 auto; ">
								<a onclick="javascript:downloadplugin();" class="input-button" />${LANG['website.user.download.center.page.download.now']}</a>
							</div>
						</div>
					</div>
					<iframe style="width:0px;height:0px;" src="" id="check_plugin_frame_2"></iframe>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="wrapper">
			<p>${LANG['website.user.footer.info.rightreserved']}</p>
       	 	<p>${LANG['website.user.footer.info.hotline']}</p>
		</div>
	</div>
<script type="text/javascript">

var clickOnceUrl = '${clickOnceUrl}';
//alert(clickOnceUrl);

function joinConference(){
	var confNo = $("#confno").val();
	var hostId = $("#hostId").val();
	var uname = $("#uname").val();
	var token = $("#token").val();
	var uuid = $("#UUID").val();
	var pwd = $("#pwd").val();//是连接入会自动填充密码
	
	if($("#action").val() == 'start'){
		//top.getStartMeetingUrl(confNo,hostId,uname,token);
		top.stratMeetingByClickOnce(clickOnceUrl,confNo,hostId,uname,token);
	}else{
		//top.getJoinMeetingUrl(confNo,uuid,pwd);
		top.joinMeetingByClickOnce(confNo,uuid,pwd);
	}
}

function downloadplugin(){
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
	var protcolUrl = "${zoom_http}";
	if($("#action").val() == "start"){
		protcolUrl += "start";
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&sid="+hostId;	//主持人登录需要这个
		protcolUrl += "&stype=100";	//主持人自动登录必备参数
		protcolUrl += "&uid="+hostId;
		protcolUrl += "&uname="+encodeURIComponent(encodeURIComponent(uname));	//入会人名字
		protcolUrl += "&token="+token;//主持人token
	}else{
		protcolUrl += $("#action").val();
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&confid="+uuid;
		protcolUrl += "&pwd="+$("#pwd").val();
	}
	//alert(protcolUrl);
	//protcolUrl = encodeURIComponent(protcolUrl);
	$("#check_plugin_frame_2").attr("src",protcolUrl);
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
			
			var url = "${zoom_http}" + $("#action").val()+"?confno="+$("#confno").val();
			if($("#action").val() == "start"){
				url += "&sid="+$("#hostId").val();	//主持人登录需要这个
				url += "&stype=100";				//主持人自动登录必备参数
				url += "&uid="+$("#hostId").val();
				url += "&uname="+encodeURIComponent(encodeURIComponent($("#uname").val()));	//入会人名字
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
			//对错误提示进行隐藏 2014-07-23
			var tip2Status = $("#tip2").is(":visible");
			if(tip2Status){
				$("#tip2").css("display","none");
			}
			$("#tip1").css("display","block");
			startPluginOuter();
		} else {
			tryToProtcolStrat();
			
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
	//timer = window.setInterval('checkPlugin', 1000);
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


function tryToProtcolStrat(){
	var url = "${zoom_http}" + $("#action").val()+"?confno="+$("#confno").val();
	if($("#action").val() == "start"){
		url += "&sid="+$("#hostId").val();	//主持人登录需要这个
		url += "&stype=100";				//主持人自动登录必备参数
		url += "&uid="+$("#hostId").val();
		url += "&uname="+encodeURIComponent(encodeURIComponent($("#uname").val()));	//入会人名字
		url += "&token="+$("#token").val();//主持人token
	}else{
		url += "&confid="+$("#UUID").val();
		url += "&pwd="+$("#pwd").val();
	}
	//url = encodeURIComponent(url);
	$("#check_plugin_frame_2").attr('src',url);
}
</script>

<c:if test="${system == 'mac'}">
<!-- mac引用 -->
<script type="text/javascript" src="${zoom_j_min}"></script>
</c:if>
</body>
</html>
