<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<title>${LANG['website.user.download.center.page.title']}</title>
	<meta name=”viewport” content=”width=device-width, initial-scale=1″ />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.index.css" />
	<script type="text/javascript" src="/assets/js/login/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/lib/jquery-1.7.2.js"></script>
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
		
		.face-warn-new{
			padding: 135px 0 40px;
			background: transparent url(/assets/images/bg/prompt-large.png) center 55px no-repeat;
			width:100%;
			text-align: center;
			font-size: 4em;
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
<body style="padding:0px;margin:auto;width:90%;">
	<div style="margin-left: 1em;margin-right: 1em;margin-top: 3em;text-align: center;overflow: hidden;margin:auto;">
		<div class="biz-titleBar" style="position: relative;display: none;">
			<span class="biz-title"><i class="biz-icon icon-join"></i>${LANG['website.user.download.center.page.title']}</span>
			<a class="biz-close" onclick="return closeDialog()"></a>
		</div>
		<div style="text-align: center;height:50em;padding-bottom: 4em;padding-top: 4em;">
			<!-- 提示  -->
			<div id="tip2">
				<div class="list-names">
					 <div id="BootPlugin_Tip2" class="face-warn-new" style="line-height:2em; color: #FF8247">
					 	${LANG['website.user.download.center.page.bootplugintip']}<br>
			        	${LANG['website.user.join.page.tip9'] }
					 </div> 	
				</div>
			</div>
			<!-- 微信 -->
<%--			<div id='popweixin'>--%>
<%--			    <div class='tip top2bottom animate-delay-1' style="height:50em;line-height:2em;">--%>
<%--		    		<img width="70%" height="40%" src='/assets/images/06.png'/>--%>
<%--			        <p id="popweixintip" style="height:60em;font-size: 27em;color: #FF8247">--%>
<%--			        	${LANG['website.user.download.center.page.bootplugintip']}<br>--%>
<%--			        	${LANG['website.user.join.page.tip9'] }--%>
<%--			        </p>--%>
<%--			    </div>--%>
<%--			</div>--%>
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
	        mobile: !!u.match(/applewebkit.*mobile.*/), //是否为移动终端
	        android: u.indexOf('android') > -1, //android终端
	        iPhone: u.indexOf('iphone') > -1, //是否为iPhone
	        iPad: u.indexOf('ipad') > -1, //是否iPad
	        webApp: !!u.match(/applewebkit.*mobile.*/) && u.indexOf('safari/') == -1 //是否web应该程序，没有头部与底部
	    };
	}
	function try_to_open_app() {
		var b=browser();
		var ua = navigator.userAgent.toLowerCase();
		var wechar = ua.indexOf("micromessenger") > -1;
		var qq = ua.indexOf("qq") > -1;
		
		if(b.ios||b.iPhone||b.iPad || b.android){
			/** 刚开始进来进行判断是不是微信或者qq浏览器 */
			if(wechar || qq){
				$("#popweixin").css("display","block");
				if(b.ios||b.iPhone||b.iPad){
					$("#popweixintip").css("font-size","5em");
				}else{
					$("#popweixintip").css("font-size","2em");
				}
		    }
		}
	}
	try_to_open_app();
	</script>
</body>
</html>
