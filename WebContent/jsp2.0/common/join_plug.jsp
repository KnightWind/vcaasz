
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${LANG['bizconf.jsp.common.join_msg.res1']}</title>
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<style>
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
		.join-plug-dialog {
			width: 474px;
			height:220px;
		}
	</style>
	<%@ include file="/jsp/common/cookie_util.jsp"%>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
</head>
<body>
<div class="join-plug-dialog">
	<div class="biz-titleBar">
		<span class="biz-title"><i class="biz-icon icon-join"></i>${LANG['website.user.index.js.message.title.join.conf'] }</span>
		<a onclick="return closeDialog()" class="biz-close"></a>
	</div>
	<!-- 未安装插件时，提示安装插件 -->
	<div class="participate-dialog" id="installPlugDiv" style="display:none">
		<div style="height: 155px;padding: 20px 20px 0px 20px;overflow: hidden;">
			<div class="install">
				<div class="desc">
					<div style="width:370px " class="nobr" title="${LANG['website.user.join.plug.uninstall.rows.1'] }">${LANG['website.user.join.plug.uninstall.rows.1'] }</div>
					<ul class="step">
						<li class="first">${LANG['website.user.join.plug.uninstall.rows.2'] }</li>
						<li class="second">${LANG['website.user.join.plug.uninstall.rows.3'] }</li>
					</ul>
				</div>
				<p class="clue">
					${LANG['website.user.join.plug.uninstall.rows.4'] }
				</p>
			</div> 
		</div>
	</div>
	<!-- 加载插件 for Ie -->
	<div class="participate-dialog" id="loadIePlugDiv" style="display:none">
		<div style="height: 155px;padding: 10px 0px;">
			<div class="process" style="padding: 0 40px;">
				<div class="desc" title="${LANG['website.user.join.plug.loading.rows.1'] }">
					${LANG['website.user.join.plug.loading.rows.2.tip.4']}
					<%-- ${LANG['website.user.join.plug.loading.rows.1'] } --%>
				</div>
				<p class="clue">
					${LANG['website.user.join.plug.loading.rows.2.tip.1'] }
					<a href="javascript:downClient()">${LANG['website.user.join.plug.loading.rows.2.tip.2'] }</a>
					${LANG['website.user.join.plug.loading.rows.2.tip.3'] }
				</p>
			</div>
		</div>
	</div>
	<!-- 加载插件 for FireFox -->
	<div class="participate-dialog" id="loadFoxPlugDiv" style="display:none">
		<div style="height: 155px;padding: 10px 0px;">
			<div class="process" style="padding: 0 40px;">
				<div class="desc" title="${LANG['website.user.join.plug.loading.rows.1'] }">
					${LANG['website.user.join.plug.loading.rows.2.tip.4']}
					<%-- ${LANG['website.user.join.plug.loading.rows.1'] } --%>
				</div>
				<p class="clue">
					${LANG['website.user.join.plug.loading.rows.2.tip.1'] }
					<a href="javascript:downClient()">${LANG['website.user.join.plug.loading.rows.2.tip.2'] }</a>
					${LANG['website.user.join.plug.loading.rows.2.tip.3'] }
				</p>
			</div>
		</div>
	</div>
	
	<!-- 入会成功 -->
	<div class="participate-dialog" id="joinCompleteDiv" style="display:none">
		<div style="height: 155px;padding: 10px 0px;">
			<div class="process" style="padding: 0 40px;">
				<div class="desc" title="${LANG['website.user.join.plug.loading.rows.1'] }">
					${LANG['website.user.join.plug.loading.rows.2.tip.5'] }
				</div>
				<p class="clue">
					${LANG['website.user.join.plug.loading.rows.2.tip.1'] }
					<a href="javascript:downClient()">${LANG['website.user.join.plug.loading.rows.2.tip.2'] }</a>
					${LANG['website.user.join.plug.loading.rows.2.tip.3'] }
				</p>
			</div>
		</div>
	</div>
	<!-- IE不安装提示信息 -->
	<div class="participate-dialog" id="notInstallPlugDiv" style="display:none">
		<div style="height: 155px;padding: 10px 0px;">
			<div class="process" style="padding: 0 40px;">
				<div class="desc" title="${LANG['website.user.join.plug.loading.rows.1'] }">
					${LANG['website.user.join.plug.loading.rows.2.tip.6'] }
				</div>
				<p class="clue">
					${LANG['website.user.join.plug.loading.rows.2.tip.1'] }
					<a href="javascript:downClient()">${LANG['website.user.join.plug.loading.rows.2.tip.2'] }</a>
					${LANG['website.user.join.plug.loading.rows.2.tip.3'] }
				</p>
			</div>
		</div>
	</div>	
	<!-- 下载Client -->
	<div class="participate-dialog" id="downClientDiv" style="display:none;" >
		<div class="wrapper">
			<div class="download">
				<div class="desc nobr">
					<div class="nobr" title="${LANG['website.user.join.plug.download.rows.1'] }">${LANG['website.user.join.plug.download.rows.1'] }</div>
					<ul class="step">
						${LANG['website.user.join.plug.download.rows.2'] }
						${LANG['website.user.join.plug.download.rows.3'] }
					</ul>
				</div>
				<p class="down">
					<button class="input-button"  onclick="javascript:download()">${LANG['website.user.join.plug.download.button'] }</button>
				</p>
				<p class="clue">
					${LANG['website.user.join.plug.download.rows.4'] }
					<a href="/downCenter/downClientHelp" target="_blank">${LANG['website.user.join.plug.download.rows.5'] }</a>
				</p>
			</div>
		</div>
	</div>
	<form name="downForm" id="downForm" method="post" action="/join/download" target="downFrame">
	</form>
	<input name="userName" id="userName" type="hidden" value="${userName}"/>
	<input name="cId" id="cId" type="hidden" value="${cId}"/>
	<input name="rId" id="rId"  type="hidden" value="${rId}"/>
	<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>
	<cc:confList var="CLIENT_DOWNLOAD_URL"/>
	 <c:if test="${fn:toLowerCase(client.browserName) eq 'ie'}">
		<fmt:parseNumber var="browserVersion" value="${client.browserVersion}" integerOnly="true"/>
<%-- 		<c:if test="${browserVersion lt 11}"> --%>
			<c:if test="${client.browserBits ne 64}">
				<object id="meetplugin" width="0" height="0" codebase="${CLIENT_DOWNLOAD_URL}/kip/mcieplgmeet.cab"  classid="clsid:620157D6-D4D5-4b65-9F32-BDFFA68BC6BC" ></object>
			</c:if>
			<c:if test="${client.browserBits eq 64}">
				<object id="meetplugin" width="0" height="0" codebase="${CLIENT_DOWNLOAD_URL}/kip/mcieplgmeet64.cab"  classid="clsid:C6CFC186-D26E-4722-A2E8-C23807A3C5C1" ></object>
			</c:if>		
<%-- 		</c:if> --%>
	</c:if>
  	<c:if test="${fn:toLowerCase(client.browserName) ne 'ie'}">
		<c:if test="${fn:toLowerCase(client.browserName) eq 'firefox'}">
			<embed id="joinConf" name="joinConf" type="application/x-vnd-eSpace Meeting-scriptableplugin" width=0 height=0/>
		</c:if>
		<c:if test="${fn:toLowerCase(client.browserName) ne 'firefox'}">
			<embed id="joinConf" name="joinConf" type="application/x-vnd-eSpace Meeting-webkitplugin" width=0 height=0/>
		</c:if>	
	</c:if>   

</div>
<script language='javascript' for="meetplugin" event="IePluginDownloadComplete()">
     IePluginDownloadCompletefunc();
</script>
<script language='javascript' for="meetplugin" event="IePluginDownloadFile(type)">
     IePluginDownloadFilefunc(type);
</script>
</script>
<script language='javascript' for="meetplugin" event="IePluginDownloadException()">
     IePluginDownloadExceptionfunc();
</script>
<script type="text/javascript">
        var winBit=window.navigator.platform;
        var startStatus=false;
		var startCount = 0;

		
        function clearReload(){
            var domain=getDomain();
            clearCookie("reload",domain);
        }
        
		//IE没有安装插件提示层
		function notInstallPlug() {
            $("#notInstallPlugDiv").siblings(".participate-dialog").hide();
            $("#notInstallPlugDiv").show();
		}
		//控制IE下插件显示Plugin层
        function showPlugDiv(){
        	var reload = "${reload}";
        	if (reload) {
        		notInstallPlug();
        		sleep(5000,downClient);
        	} else {
        		installPlug();
        	}
        }
        //打开下载插件层
        function installPlug(){
        	$("#installPlugDiv").siblings(".participate-dialog").hide();
    		$("#installPlugDiv").show();
    		
            var domain=getDomain();
            setCookie("reload","1,${cId},${userName},${joinType},${code},${cPass},${rId}",domain);
            window.setTimeout(downClient,20000);
        }
      	//打开加载层
        function loadIePlug(){
        	$("#loadIePlugDiv").siblings(".participate-dialog").hide();
            $("#loadIePlugDiv").show();
        }
      	
        function loadFoxPlug(){
        	$("#loadFoxPlugDiv").siblings(".participate-dialog").hide();
            $("#loadFoxPlugDiv").show();
        }
      	
      	//成功入会
        function joinComplete(){
        	$("#joinCompleteDiv").siblings(".participate-dialog").hide();
            $("#joinCompleteDiv").show();
            sleep(10000,closeDialog);
    	}
      	
      	//打开下载层
        function downClient(){
        	$("#downClientDiv").siblings(".participate-dialog").hide();
            $("#downClientDiv").show();
            window.setTimeout(clearReload,500);
        }
      	
      	//调用下载的URL地址，去下载Client
        var downCount=0;
        function download(){
        	//if(downCount<=0){
        		// $("#downLoadLink").attr("clickCount",1);
                // alert("${LANG['bizconf.jsp.common.join_plug.res14fix']}");
                 //return false;
	        	clearReload();
	        	var downUrl="/join/download";
	        	downUrl+="?cId="+$("#cId").val();
	        	downUrl+="&rId="+$("#rId").val();
	        	winBit=window.navigator.platform;
	        	if($.browser.msie && "Win64" == winBit){
	        		downUrl+="&ieBit=64";
	        	}
	        	downUrl+="&userName="+encodeURIComponent($("#userName").val());
	        	$("#downForm").attr("action", downUrl);
	        	$("#downForm").submit();
	        	closeDialogForDelay();
	        	downCount++;
        	//}else{
        	//	$("#downLoadLink").attr("clickCount",1);
        	//	alert("${LANG['bizconf.jsp.common.join_plug.res14fix']}");
        	//}
        }
      	
        /******************************************************************************
        							通过插件启动会议
		*******************************************************************************/
		
        function startConf(){
        		var browserName = "${client.browserName}";
        		var browserVersion = "${client.browserVersion}";
        		if (browserName) {
        			browserName = browserName.toLowerCase();
        			if (browserName=="ie") {
//         				if (browserVersion<11) {
        					startConfForIe();	
//         				} else {
//         					downClient();
//         				}
        			} else if (browserName=="firefox" || browserName=="chrome") {
        				startConfForFireFox();
        			} else {
        				downClient();
        			}
        		} else {
        			downClient();
        		}
//                 if($.browser.msie) {
//                 	startConfForIe();
//                 }else if($.browser.mozilla) {
//                     startConfForFireFox();
//                 }else{
//                 	var userAgent = navigator.userAgent;
//                 	if(userAgent.indexOf("KHTML") > -1 || userAgent.indexOf("Konqueror") > -1 || userAgent.indexOf("AppleWebKit") > -1){
//                 		startConfForFireFox();
//                 	}else{
//                         downClient();
//                 	}
//                 }
        }
        
        /******************************************************************************
                                IE 通过插件启动会议
        *******************************************************************************/
      	//启动IE插件
        function startConfForIe(){
                var mtgObj=document.getElementById("meetplugin");
                try{
                        if(mtgObj){
                                mtgObj.StartConf("${preParam}");
                                loadIePlug();
                        }
                }catch(e){
                        showPlugDiv();
                }
        }
        
        function IePluginDownloadFilefunc(downloadFile) {
        }
      	//IE启动成功需要处理的内容
        function IePluginDownloadCompletefunc() {
        	sleep(5000,joinComplete);
        	startStatus=true;
            closeDialogForDelay();
        }
      	//IE启动失败调用 的方法
        function IePluginDownloadExceptionfunc() {
        	downClient();
        }
        /******************************************************************************
                                        FireFox通过插件启动会议
        *******************************************************************************/
        function startConfForFireFox(){
                var plg = document.getElementById("joinConf");  //获取插件的对象
                if(plg){
                        try{
                             plg.StartConf("${preParam}");   //调用插件的接口方法，preparam为小参数
                        }catch(e){
                                downClient();
                                return null;
                        }
                        //var startStaus=plg.LoadPlugin;
                        //if(startStaus){
                        //        startSucceedForFireFox();
                        //}else{
                        //        startFailedForFireFox();
                        //}
                }
        }
        function createObjectForFireFox(){
            var firefox=document.createElement("embed");
            if(firefox){
                    firefox.setAttribute("id","joinConf");
                    firefox.setAttribute("name","joinConf");
                    firefox.setAttribute("width","0");
                    firefox.setAttribute("height","0");
                    firefox.setAttribute("type","application/x-vnd-eSpace Meeting-scriptableplugin");
                    document.body.appendChild(firefox);
            }
    	}
        function NPN_PluginDownloadFile(type,result) {
        	loadFoxPlug();
        } 
        
        function NPN_PluginDownloadComplete(type,result) {
        	startSucceedForFireFox();
        }

        function NPN_PluginDownloadException(type,result) {
        	startFailedForFireFox();
        }

        function startSucceedForFireFox(){
        		sleep(5000,joinComplete);
                startStatus=true;
                closeDialogForDelay();
        }
        function startFailedForFireFox(){
                downClient();
        }
        
        function closeDialogForDelay() {
        }
        
        function closeDialog() {
            clearReload();
         	var topUrl=top.location+"";
 	        if(topUrl.indexOf("?")>-1){
	        	var domain="${domain}";
 	        	top.location="http://"+domain;
 	        }else{
 				top.location=topUrl;
 	        }
			
   
    	    
			//var dialog = parent.$("#joinMeeting");
            //dialog.html("").remove();//trigger("closeDialog");
        }
        window.onload =  function() {
        	startConf();
    	};
        /**
         * 类似Java的Sleep方法
         *
         * @param {Object} millSeconds   等待时间：毫秒数
         * @param {Object} callBack  回调函数
         */
        function sleep(millSeconds, callBack) {
            if (millSeconds > 0) {
                window.setTimeout(callBack, millSeconds);
            } else {
                callBack();
            }
        }
</script>
</body>
</html>