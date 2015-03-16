<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${LANG['website.user.join.page.title']}</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<link rel="stylesheet" href="/assets/js/login/mobilecss/jquery.mobile-1.3.0-beta.1.css">
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/login/jquery.mobile-1.3.0-beta.1.min.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"></script>
<style>
.face-warn-new{
	padding: 135px 0 40px;
	background: transparent url(/assets/images/bg/prompt-large.png) center 55px no-repeat;
	width:100%;
	text-align: center;
	color: #509B00;
}
.face-submit{
	width:25%;
	height:80px;
	text-align:center;
	padding-bottom:20px;
	margin:0 auto; 
}
/** 微信提示**/
#popweixin {
    width:100%;
    height:100%;
    overflow:hidden;
    position:fixed;
    z-index:1000;
    background:rgba(0,0,0,.5);
    top:0;
    left:0;
    margin-left: auto;
	margin-right: auto;
	TEXT-ALIGN: center;
    display:none;
}
#popweixin .tip {
    width:100%;
    background:#fff;
    z-index:1001;
}
.top2bottom {
    -webkit-animation:top2bottom 1.2s ease;
    -moz-animation:top2bottom 1.2s ease;
    -o-animation:top2bottom 1.2s ease;
    animation:top2bottom 1.2s ease;
    -webkit-animation-fill-mode:backwards;
    -moz-animation-fill-mode:backwards;
    -o-animation-fill-mode:backwards;
    animation-fill-mode:backwards
}
.animate-delay-1 {
    -webkit-animation-delay:1s;
    -moz-animation-delay:1s;
    -o-animation-delay:1s;
    animation-delay:1s
}
@-webkit-keyframes top2bottom {
    0% {
    -webkit-transform:translateY(-300px);
    opacity:.6
}
100% {
    -webkit-transform:translateY(0px);
    opacity:1
}
}@keyframes top2bottom {
    0% {
    transform:translateY(-300px);
    opacity:.6
}
100% {
    transform:translateY(0px);
    opacity:1
}
/** 微信提示**/
</style>
</head>
<body>
	<div data-role="page" data-theme="c">
		<div data-role="header" data-position="fixed">
			<h1>${LANG['bizconf.jsp.conf.bizconf.head.title'] }</h1>
		</div>
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
		<!-- 登录者名字 -->
		<input type="hidden" id="uname" value="${uname}"/>
		<!-- 会议名字 -->
		<input type="hidden" id="meetingName" value="${conf.confName}"/>
		<!-- 主持人的token -->
		<input type="hidden" id="token" value="${token}"/>
		<!-- 会议的UUID -->
		<input type="hidden" id="UUID" value="${UUID}"/>
		<!-- 会议的密码 -->
		<input type="hidden" id="pwd" value="${pwd}"/>
		<input type="hidden" id="surl" value="${conf.startUrl}"/>
		<input type="hidden" id="jurl" value="${conf.joinUrl}"/>
		<!-- 插件 -->
		<embed id="BootPlugin" name="Bizconf launcher plugin" type="application/x-cfcloud-scriptable-plugin" style="width:0px;height:0px;" hidden="true"></embed>
<%--		<embed id="BootPlugin" name="BootPlugin" type="application/x-zoom-scriptable-plugin" style="width:0;height:0;" hidden="true"></embed>--%>
		<!-- mac检查插件设置该值 -->
		<input type="hidden" id="check_plugin_frame_field" value="true" /> 
		
		<div style="margin-left: 1em;margin-right: 1em;margin-top: 3em;text-align: center;">
			<fieldset class="ui-grid-a" style="line-height: 1.5em;">
				<!-- 插件提示  -->
				<div id="tip" style="width: 100%">
					<div class="list-names">
						 <span id="tip_install" style="text-align: center;color: #509B00;">
						 	${LANG['website.user.join.page.tip5']}
						 </span>
						 <span id="tip_no_install" style="">
						 	${LANG['website.user.join.page.tip7']}
						 </span>
						 <div id="tip_no_install_info" style="font-weight:bold;font-size: 1em;color:#FF8247;width: 100%">${LANG['website.user.join.page.tip8']}</div><br/> 	
						 <div id="tip_click_start" class="ui-block-b face-submit" style="width: 100%;display: none;">
						 	${LANG['website.conf.mobile.join.chrome.info1']} <a id="ajoinBtn" href="#">${LANG['website.user.conf.list.enter.meeting']}</a> ${LANG['website.conf.mobile.join.chrome.info2']}
						 </div>
						 <div id="tip_no_install_btn" class="ui-block-b face-submit" style="width: 100%"><input type="button" data-inline="true" onclick="downloadplugin();" value="立即下载"></div>
					</div>
				</div>
				<div id='popweixin'>
				    <div class='tip top2bottom animate-delay-1' style="height:50em;padding-bottom: 4em;padding-top: 4em;">
<%--			    		<img width="85%" height="66%" src='/assets/images/06.png'/>--%>
				        <p id="popweixintip" style="line-height:2em; color: #FF8247">
				        	${LANG['website.user.join.page.tip9']}
				        </p>
				    </div>
				</div>
			<iframe style="width:0px;height:0px;" src="" id="check_plugin_frame_2"></iframe>
			<iframe style="width:0px;height:0px;" src="" id="check_plugin_frame_3"></iframe>
			</fieldset>
		</div>
		<div data-role="footer" data-position="fixed">
			<h1>${LANG['website.user.footer.info.hotline'] }</h1>
			<h1>${LANG['website.user.footer.info.rightreserved'] }</h1>
		</div>
	</div>
	<script type="text/javascript">
		//移动客户端自动调用
		function browser() {
		   	var u = navigator.userAgent.toLowerCase();
		    var app = navigator.appVersion.toLowerCase();
		    return {
		        txt: u, // 浏览器版本信息
		        version: (u.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1], // 版本号       
		        msie: /msie/.test(u) && !/opera/.test(u), // IE内核
		        mozilla: /mozilla/.test(u) && !/(compatible|webkit)/.test(u), // 火狐浏览器
		        safari: /safari/.test(u) && !/chrome/.test(u), //是否为safair
		        chrome: /chrome/.test(u), //是否为chrome
		        opera: /opera/.test(u), //是否为oprea
		        presto: u.indexOf('presto/') > -1, //opera内核
		        webKit: u.indexOf('applewebkit/') > -1, //苹果、谷歌内核
		        gecko: u.indexOf('gecko/') > -1 && u.indexOf('khtml') == -1, //火狐内核
		        mobile: !!u.match(/applewebkit.*mobile.*/), //是否为移动终端
		        ios: !!u.match(/\(i[^;]+;( u;)? cpu.+mac os x/), //ios终端
		        android: u.indexOf('android') > -1, //android终端
		        iPhone: u.indexOf('iphone') > -1, //是否为iPhone
		        iPad: u.indexOf('ipad') > -1, //是否iPad
		        webApp: !!u.match(/applewebkit.*mobile.*/) && u.indexOf('safari/') == -1 //是否web应该程序，没有头部与底部
		    };
		}	
		var bw=browser();
		
		function downloadplugin(){
			var b=browser();
			if(!b.android){
				window.location = "${ipk}";//使用此链接可以连接到appstore
			}else{
				window.location = "${apk}";
			}
		}
		var timeout;
		var t1 =0;
		var t2 =0;
		function openAppStore() {//自动下载IPK
			var b=browser();
			if(b.ios||b.iPhone || b.iPad){//
				var tip1 = $("#tip_no_install").is(":visible");
		    	var tip2 = $("#tip_no_install_info").is(":visible");
		    	var tip3 = $("#tip_no_install_btn").is(":visible");
		    	if(!tip1 || !tip2 || !tip3){
		    		$("#tip_no_install").css("display","block");
			    	$("#tip_no_install_info").css("display","block");
			    	$("#tip_no_install_btn").css("display","block");
		    	}
				$("#tip_install").css("display","block");
				window.location = "${ipk}";
			}
		} 
		
		function tryOpenApp() {
			var b=browser();
			var ua = navigator.userAgent.toLowerCase();
			var wechar = ua.indexOf("micromessenger") > -1;
			var qq = ua.indexOf("qq") > -1;
			if(b.ios||b.iPhone||b.iPad || b.android || b.mobile){
				if(b.ios||b.iPhone||b.iPad){//input-button
					$(".input-button").css("font-size","5em");
				}else{
					$(".input-button").css("font-size","2em");
				}
				/** 刚开始进来进行判断是不是微信或者qq浏览器 */
				if(wechar || qq){
					$("#tip").css("display","none");
					$("#popweixin").css("display","block");
			    }else{
			    	$("#tip_install").css("display","block");
			    	/*
			    	var tip1 = $("#tip_no_install").is(":visible");
			    	var tip2 = $("#tip_no_install_info").is(":visible");
			    	var tip3 = $("#tip_no_install_btn").is(":visible");
			    	if(tip1 || tip2 || tip3){
			    		$("#tip_no_install").css("display","none");
				    	$("#tip_no_install_info").css("display","none");
				    	$("#tip_no_install_btn").css("display","none");
				    	$("#tip_install").css("display","block");
			    	}
			    	*/
			    	//var url = "zoomus://zoom.us/"+$("#action").val()+"?confno="+$("#confno").val();
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
					//alert(url);
					$("#check_plugin_frame_3").attr("src",url);
			    }
			}
			if(b.ios||b.iPhone||b.iPad){
				if(!wechar && !qq){
					timeout = setTimeout('openAppStore()', 3000);
				}
			}
		}
		tryOpenApp();
		
		if(bw.chrome && bw.android){
			clickJoinByProtcol();
		}
		
		function clickJoinByProtcol(){
			$("#tip_click_start").show();
			
			var servername = "${zoom_http}";
			/*
			if("${system}" == "iphone" || "${system}" == "ipad"){
				servername = "bizconf://bizconf.zoom.us/";
			}
			*/
			var url = servername + $("#action").val()+"?confno="+$("#confno").val();
			
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
			$("#ajoinBtn").attr("href",url);
		}
		
	</script>
	<c:if test="${system == 'mac'} ">
		<!-- mac引用 -->
		<script type="text/javascript" src="${zoom_j_min}"></script>
	</c:if>
</body>
</html>
