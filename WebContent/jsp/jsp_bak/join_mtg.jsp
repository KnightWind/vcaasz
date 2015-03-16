<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>进入 会议</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script> 
	</head>
	<body>
	<%--
	<object  id="meetplugin" width=0  height=0 classid="clsid:94FCF018-BD09-4523-9DA9-1557E3485252" ></object>
	<embed id="joinConf" name="joinConf" type="application/x-vnd-eSpace Meeting-scriptableplugin" width=0    height=0/>
	--%>
  </body>
</html>
<script type="text/javascript">
		//alert($.browser.mozilla);
		function initPage(){
			if($.browser.msie) {
				alert( $.browser.version);
				createObjectForIe();
			}else if($.browser.mozilla) {
				alert( $.browser.version);
				createObjectForFireFox();
			}else{
				alert("请使用IE、FireFox启动");
			}
			alert(document.body.innerHTML);
		}
		initPage();
		
		
		function createObjectForIe(){
			var ieObject=document.createElement("object");
			if(ieObject){
				ieObject.setAttribute("id","meetplugin");
				ieObject.setAttribute("width","0");
				ieObject.setAttribute("height","0");
				ieObject.setAttribute("classid","clsid:94FCF018-BD09-4523-9DA9-1557E3485252");
				document.body.appendChild(ieObject);
			}
			
		//	var mtgObj=document.getElementById("meetplugin ");  //获取插件的对象
		//	mtgObj.attachEvent("IePluginDownloadComplete", function1);  //绑定成功启动客户端事件，function1为收到插件返回的事件后执行的方法，第三方自己定义和实现成功入会后的方法
		//	mtgObj.attachEvent("IePluginDownloadException",function2);//绑定启动客户端异常事件，function2为收到插件返回异常后执行的方法，第三方自己定义和实现入会异常后的方法
		//	mtgObj.StartConf("preparam");  //调用插件的接口方法，preparam为小参数

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
		
	</script>