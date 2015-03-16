<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${LANG['website.user.join.page.title']}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	$(function(){ 
	    $("#mainFrame").load(function(){ 
	    	//alert("hey !");
	    	//$("#mainFrame").cotent.find("")
	    	var dialog = parent.$("#joinMeeting");
			//dialog.trigger("closeDialog");	
	    }); 
	}); 
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <body>
	<div>
		${lang['website.user.join.page.tip5'] }
	</div>
	<div  style="height:780px;">
		<c:if test="${ joinType eq 1}">
			<iframe frameborder="0"  id="mainFrame" name="mainFrame" scrolling="no" src="${conf.startUrl}"></iframe>
		</c:if>
		<c:if test="${ joinType eq 2}">
			<iframe frameborder="0"  id="mainFrame" name="mainFrame" scrolling="no" src="${conf.joinUrl}"></iframe>
		</c:if>
		<c:if test="${ joinType eq 3}">
			<iframe frameborder="0"  id="mainFrame" name="mainFrame" scrolling="no" src="${conf.joinUrl}"></iframe>
		</c:if>
		<c:if test="${ joinType eq 4}">
			<iframe frameborder="0"  id="mainFrame" name="mainFrame" scrolling="no" src="${conf.startUrl}"></iframe>
		</c:if>
	</div>
  </body>
</html>
