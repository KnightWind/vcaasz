<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<title>index</title>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript">
	jQuery(function($) {
		var links = [
		             "/user/contact/list",
		             "/user/group/list"];
		$(".switchList").click(function() {
			var index = $(this).index();
			var href = links[index];
			$("#contentFrame").attr("src",href);
			$(".meau_btn a").removeClass("activeMeau-left activeMeau-right");
			$(this).find("a").addClass("activeMeau-left activeMeau-right");
		});

	});
	
	function refreshIHeight() {
		var height = $("#contentFrame").contents().find("body").height() + 50;
		$("#contentFrame").height(height);  
	}
</script>
</head>
<body>
<div class="main_content">
  <div class="meeting_top" style="margin-top: 20px;">
    <ul>
      <li class="meau_01 switchList">
      <span class="meau_btn"><a href="javascript:;" class="activeMeau-left activeMeau-right"><img src="${baseUrlStatic}/images/lianxiren.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.contacts_main.res1']}</a></span></li>
      <li class="meau_02 switchList">
      <span class="meau_btn"><a href="javascript:;"><img src="${baseUrlStatic}/images/qunzu_b.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.contacts_main.res2']}</a></span></li>
    </ul>
  </div>
   <iframe frameborder="0" width="100%" height="650px;" id="contentFrame" name="contentFrame" scrolling="no" src="/user/contact/list"></iframe>
</div>
</body>
</html>

