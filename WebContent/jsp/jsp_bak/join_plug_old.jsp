<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script> 
<title>插件验证</title>
</head>
<body>
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="downClientDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--弹出层主题内容区域开始========================================================================-->  
      <div class="First_Steps_quick_b" style=" background:#FFF">
        <div class="First_Steps_title_a"> <a  href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">加入会议</h3>
          <p class="tit_b">请输入你的账号后进入该会议。</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong">如果客户端未启动，请<a  href="javascript:" onclick="download();" style=" color:#03F; text-decoration:underline;">点击下载</a>安装客户端。</p>
        </div>
      </div>
      <!--弹出层主题内容区域开始========================================================================-->      
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="startClientDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--弹出层主题内容区域开始========================================================================-->  
		<div class="First_Steps_quick_b" style=" background:#FFF">
	        <div class="First_Steps_title_a"> <a   href="javascript:"  onclick="javascript:closeDialog();" ></a>
	          <h3 class="tit_a">加入会议</h3>
	          <p class="tit_b">请输入你的账号后进入该会议。</p>
	        </div>
	        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        <div class="First_Steps_top" style=" background:#FFF"> </div>
	        <div class="First_Steps_main_quick">
	         <p class="qidong">请稍后，正在启动客户端...</p>
	        </div>
     	</div>
      <!--弹出层主题内容区域开始========================================================================-->      
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>
 <form name="downForm" id="downForm" method="post" action="/join/download" target="downFrame">
 <input name="userName" type="hidden" value="${userName}"/>
 <input name="cId" type="hidden" value="${cId}"/>
</form>
<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>

<%--<object id="meetplugin" codebase="http://www.confcloud.cn/download/kip/mcieplgmeet.cab" width=0
        height=0 classid="clsid:2BB4DED4-1CF2-4b5f-8345-95D73E4197A1"
        VIEWASTEXT></object>
        
--%>

</body>
</html>



<script type="text/javascript">

 	<c:if test="${msgFlag!=null && msgFlag!=''}"> 
		function initPage(){
			//var msgFlag="${msgFlag}";
			<c:set var="msgName" value="website.conf.join.type.${msgFlag}"/>
			var msgFlag="${LANG[msgName]}";
			alert(msgFlag);
		}
		initPage();
		 	</c:if> 
		
	<c:if test="${msgFlag==null || msgFlag==''}">
		//alert($.browser.mozilla);
		function startConf(){
			if($.browser.msie) {
				startConfForIe();
			}else if($.browser.mozilla) {
				startConfForFireFox();
			}else{
				alert("请使用 IE 或者 FireFox 浏览器进入会议");//downClient();//
			}
		}
		function initPage(){
			startConf();
		}
		initPage();
		/******************************************************************************
		
		       			IE 通过插件启动会议
		
		*******************************************************************************/

		function startConfForIe(){
			createObjectForIe();
			var mtgObj=document.getElementById("meetplugin");
			try{
				if(mtgObj){
					mtgObj.attachEvent("IePluginDownloadComplete", startSucceedForIe);
					mtgObj.attachEvent("IePluginDownloadException",startFailedForIe);
					//alert("${preParam}");
					mtgObj.StartConf("${preParam}");
					//	var mtgObj=document.getElementById("meetplugin ");  //获取插件的对象
					//	mtgObj.attachEvent("IePluginDownloadComplete", function1);  //绑定成功启动客户端事件，function1为收到插件返回的事件后执行的方法，第三方自己定义和实现成功入会后的方法
					//	mtgObj.attachEvent("IePluginDownloadException",function2);//绑定启动客户端异常事件，function2为收到插件返回异常后执行的方法，第三方自己定义和实现入会异常后的方法
					//	mtgObj.StartConf("preparam");  //调用插件的接口方法，preparam为小参数
				}
			}catch(e){
			//	alert("e=="+e);
				downClient();
				return null;
			}
		} 
		
		/*
		<object id="oxford" codebase="./download/kip/mcieplgoxf.cab" width=0
        height=0 classid="clsid:B194D8DD-FD3B-422b-AD0E-95E43489F447"
        VIEWASTEXT></object>
	
        */

		function createObjectForIe(){
			var ieObject=document.createElement("object");
			if(ieObject){
				ieObject.setAttribute("id","meetplugin");
				ieObject.setAttribute("width","0px");
				ieObject.setAttribute("height","0px");
				ieObject.setAttribute("codebase","http://www.confcloud.cn/download/kip/mcieplgmeet.cab");	
				ieObject.setAttribute("classid","clsid:2BB4DED4-1CF2-4b5f-8345-95D73E4197A1");
				document.body.appendChild(ieObject);
			}	
		
		}
		
		
		function startSucceedForIe(){
			//alert("IE启动成功");
			$("#downClientDiv").hide();
			$("#startClientDiv").show();

			closeDialogForDelay();
		}
		
		function startFailedForIe(){
			downClient();
		//	alert("IE启动失败");
		}
		
		
		
		/******************************************************************************
		
						FireFox通过插件启动会议
		
		*******************************************************************************/
		
		
		function startConfForFireFox(){
			createObjectForFireFox();
			var plg=document.getElementById("joinConf");  //获取插件的对象
			if(plg){
				try{
					plg.StartConf("${preParam}");  //调用插件的接口方法，preparam为小参数
				}catch(e){
					downClient();
					return null;
				}
				var startStaus=plg.LoadPlugin; 
				if(startStaus){
					startSucceedForFireFox();
				}else{
					startFailedForFireFox();
				}
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
				//var plg=document.getElementById("joinConf");  //获取插件的对象
				//plg.StartConf("preparam");  //调用插件的接口方法，preparam为小参数
				//b=plg.LoadPlugin; 
			}
		}
		
		
		function startSucceedForFireFox(){

			$("#downClientDiv").hide();
			$("#startClientDiv").show();
			closeDialogForDelay();
			
		}
		
		function startFailedForFireFox(){
			downClient();
			//alert("Firefox启动失败");
		}
	 </c:if> 

		function downClient(){
			//调用下载的URL地址，去下载Client
			$("#downClientDiv").show();
			$("#startClientDiv").hide();
			
			//alert("正在下载中，请稍候…………");
		}
		function download(){
			//调用下载的URL地址，去下载Client
			//$("#downClientDiv").show();
			//window.open("/join/download?cId="+cId);
			$("#downForm").submit();
			closeDialogForDelay();
			//alert("正在下载中，请稍候…………");
		}
		
		function closeDialogForDelay(){
			window.setTimeout(closeDialog,2000);
		}
	window.onload=	function() {
		var frame = parent.$("#joinMeeting");
		frame.trigger("loaded");
		}
	
	function closeDialog() {
		var dialog = parent.$("#joinMeeting");
		dialog.trigger("closeDialog");
	}
</script>
