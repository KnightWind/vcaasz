<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<link rel="stylesheet" href="/assets/js/login/mobilecss/jquery.mobile-1.3.0-beta.1.css">
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/login/jquery.mobile-1.3.0-beta.1.min.js"></script>
</head>
<body>
<div data-role="page" data-theme="b">
  <div data-role="header" data-position="fixed"><h1>${LANG['bizconf.jsp.conf.bizconf.head.title.mobile'] }</h1></div>
  <div style="margin-left: 1em;margin-right: 1em;margin-top: 3.5em;">
  	<div data-role="content">
  		<span id="joinError" style="color: red;font-weight: bold;"></span><!-- 错误提示信息 -->
 	  	<input type="text" name="confNoCode" id="confNoCode" placeholder="${LANG['website.user.view.conf.conf.confCode'] }" >
  		<a href="javascript:joinMeeting();" name="confNoBtn" id="confNoBtn" data-theme="b" data-role="button">${LANG['website.user.join.page.title'] }</a>
  	</div>
  </div>
  <div data-role="footer" data-position="fixed">
  	<h1>${LANG['website.user.footer.info.hotline'] }</h1>
  	<h1>${LANG['website.user.footer.info.rightreserved'] }</h1>
  </div>
</div>
<script type="text/javascript">
function joinMeeting(){
	var code = $("#confNoCode").val();
	if(code == null || code == undefined || code == ""){
		$("#joinError").text("${LANG['website.user.conf.notnull.confid'] }");
		return false;
	}else{
		var url = "/join/gotojoinPage?joinType=2&code="+code;//blank表示新打开窗口
		window.open(url);
	}
}
</script>
</body>
</html>
