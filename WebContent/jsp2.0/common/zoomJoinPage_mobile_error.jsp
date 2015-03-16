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
</style>
</head>
<body>
	<div data-role="page" data-theme="c">
		<div data-role="header" data-position="fixed">
			<h1>${LANG['bizconf.jsp.conf.bizconf.head.title'] }</h1>
		</div>
		<div style="margin-left: 1em;margin-right: 1em;margin-top: 3em;text-align: center;">
			<fieldset class="ui-grid-a" style="line-height: 1.5em;">
				<!-- 错误提示 -->
				<div class="wrapper" style="width: 100%;">
					<div class="prompt-section">
						<div class="prompt-error">
							<div class="ui-block-b face-failure" style="width: 100%;">
								<c:set var="msgName" value="website.conf.join.type.${errorCode}"/>${LANG[msgName]}
							</div>
						</div>		
					</div>
				</div>
			</fieldset>
		</div>
		<div data-role="footer" data-position="fixed">
			<h1>${LANG['website.user.footer.info.hotline'] }</h1>
			<h1>${LANG['website.user.footer.info.rightreserved'] }</h1>
		</div>
	</div>
</body>
</html>
