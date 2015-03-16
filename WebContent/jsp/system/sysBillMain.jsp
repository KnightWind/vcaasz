<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/page.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<title>index</title>
<style>
.tr_center {
	border:#D2D8DB 1px solid;
	border-right:none;
}
.tr_main {
	border-bottom:#D2D8DB 1px solid;
	color:#666666;
}
.tr_top {
	border:#D2D8DB 1px solid;
	border-bottom:none;
	margin-top:26px;
}
.tr_bottom {
	border:#D2D8DB 1px solid;
	border-top:none
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
		var links = [
		             "/common/billing/sysBillList",
		             "/common/billing/sysBillList"];
		$(".switchList").click(function() {
			var index = $(this).index();
			var index = $(this).index();
			var href = links[index];
			$("#contentFrame").attr("src",href);
			$(".meau_btn a").removeClass("activeMeau-left activeMeau-right");
			$(this).find("a").addClass("activeMeau-left activeMeau-right");
			$("#query").submit();
		});
		
		$("#contentFrame").load(function(){
			var h = getHeight();  
			parent.resizeHeight(h+10);
	    });
	});
	
	function getHeight() {
		var height = $("#contentFrame").contents().find("body").height()+50;
		if(height<850){
			height = 850;
		}
		$("#contentFrame").height(height); 
		return height;
	}

</script>
</head>
<body>
<div class="main_content">
  <!-- <div class="meeting_top">
    <ul>
      <li class="meau_01 switchList"><span class="meau_btn"><a href="javascript:;" class="activeMeau-left activeMeau-right"><img src="${baseUrlStatic}/images/icon_a.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:15px;"  />${LANG['bizconf.jsp.admin.siteBillMain.res1']}</a></span></li>
      <li class="meau_02 switchList"><span class="meau_btn"><a href="javascript:;"><img src="${baseUrlStatic}/images/icon_b.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:15px;"  />${LANG['bizconf.jsp.admin.siteBillMain.res2']}</a></span></li>
    </ul>
  </div> -->
  <div class="meeting_main" >
  	<iframe frameborder="0" width="100%" height="650px;" id="contentFrame" name="contentFrame" scrolling="no" src="/common/billing/sysBillList"></iframe>
  </div>
</div>
</body>
</html>
