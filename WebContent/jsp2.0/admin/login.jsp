<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.login.res6']}</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.index.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/login.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
<script type="text/javascript">
function DrawImage(ImgD,iwidth,iheight){
    var image=new Image();
    image.src=ImgD.src;
    if(image.width>0 && image.height>0){
      if(image.width/image.height>= iwidth/iheight){
          if(image.width>iwidth){
              ImgD.width=iwidth;
              ImgD.height=(image.height*iwidth)/image.width;
          }else{
              ImgD.width=image.width;
              ImgD.height=image.height;
          }
      }else{
          if(image.height>iheight){
              ImgD.height=iheight;
              ImgD.width=(image.width*iheight)/image.height;
          }else{
              ImgD.width=image.width;
              ImgD.height=image.height;
          }
      }
    }
}
</script>
</head>

<body>
<div id="content">
	<div class="wrapper">
		<div id="accountBox" class="clearfix sys-login-box">
			<div class="logo">
				<c:if test="${!empty siteBase.siteLogo}">
					<img onerror="this.src='/Formal/images/home/logo_admin.png';" style="margin-top: 17px;" width="120px;" height="60px;" src="${siteBase.siteLogo}" class="fix-png" alt="标志" />
				</c:if>
				<c:if test="${empty siteBase.siteLogo}">
					<img src="Formal/images/home/logo_admin.png" class="fix-png" style="margin-top: 17px;" alt="标志" onload="javascript:DrawImage(this,120,60)"/>
				</c:if>
				<c:if test="${!empty lang && lang == 'zh-cn'}">
					<div class="site-name-one">
		          		<div class="site-name-zh nobr" title="">${LANG['website.admin.login.logintitle'] }</div>
		          	</div>
	          	</c:if>
	          	<c:if test="${!empty lang && lang == 'en-us'}">
					<div class="site-name-one-en">
		          		<div class="site-name-en" title="">${LANG['website.admin.login.logintitle'] }</div>
		          	</div>
	          	</c:if>
			</div>
			<div class="error" id="dangerAlert">
				<span class="form-tip"><span id="loginNameTip"></span></span>
				<span class="form-tip"><span id="loginPassTip"></span></span>
			</div>
			<form class="sys-login-form" name="sysLoginForm" id="sysLoginForm" action="/admin/login/check" method="post" class="clearfix">
				<input type="hidden" name="random" id="random" value=""/>
				<input type="hidden" name="type" id="type" value="siteadminlogin"/>
				<div class="form-body">
					<div class="form-item">
						<input autofocus="autofocus" class="input-text" id="loginName" name="loginName" placeholder="${LANG['website.admin.login.page.loginname'] }" type="text" value="" />
					</div>
					<div class="form-item">
						<input class="input-text" id="loginPass" name="loginPass" placeholder="${LANG['website.admin.login.page.loginpass'] }" type="password" value="" />
					</div>
					<div class="form-item captcha-item clearfix">
						<input type="text" placeholder="${LANG['website.common.authcode.name'] }" class="input-text captcha" name="authCode" id="authCode" autocomplete="off" />
						<img id="authCodeImg" src="" alt="${LANG['website.common.authcode.name'] }" class="codeimg" width="66" height="22" onclick="randomImg()"/>
						<a onclick="return randomImg()" class="change">${LANG['website.common.authcode.next'] }</a>
					</div>
					<div class="form-item">
						<input class="input-check" id="remember" name="remember" type="checkbox">
						<label for="remember" class="for-check remember">${LANG['website.admin.login.page.remember'] }</label>
						<a href="/admin/password/forget" class="forget" target="_blank">${LANG['website.admin.login.page.forget'] }</a>
					</div>
				</div>
				<div class="form-item">
					<input type="submit" value="${LANG['website.common.login.name'] }" class="input-submit" />
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
var LOADING_CONSTANT={
		loadingMessage: "${LANG['website.common.loading.message']}"
};
</script>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.widgets.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>

<script type="text/javascript">
var ADMIN_LOGIN_CONSTANT={
		login_remind_title:"${LANG['website.admin.login.js.message.title.remind.common']}",//提醒 
		login_confirm:"${LANG['website.common.option.confirm']}",//确定
		login_authcode_empty:"${LANG['website.admin.login.js.message.authcode.empty']}"//请输入验证码
		
};
</script>
<c:if test="${!empty errorMessage}">
<script type="text/javascript">

function errorDialog(message, callback) {
	$("<div/>").alertDialog({
		"title": ADMIN_LOGIN_CONSTANT.login_remind_title,
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "error",
		"buttonOk": ADMIN_LOGIN_CONSTANT.login_confirm,
		"successCallback": callback
	});
}
$(function() {
	errorDialog("${errorMessage}");
});
</script>
</c:if>
<script type="text/javascript">
var ADMIN_LOGIN_CONSTANT={
		login_remind_title:"${LANG['website.admin.login.js.message.title.remind.common']}",//提醒 
		login_confirm:"${LANG['website.common.option.confirm']}",//确定
		login_authcode_empty:"${LANG['website.admin.login.js.message.authcode.empty']}"//请输入验证码
		
};
$(function() {
	randomImg();
	var sessionFlag = "${userSessionFlag}";
	if(sessionFlag && sessionFlag=="true"){
		resetPage();
	}
	fillInUserName();
	$.formValidator.initConfig({
		oneByOneVerify:true,
		formID : "sysLoginForm",
		debug : false,
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	$("#loginName").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "${LANG['website.user.login.js.message.loginname.unblank']}"
		},
		onError : "${LANG['website.user.login.js.message.loginname.nowrite']}"
	});
	$("#loginPass").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "${LANG['website.user.login.js.message.loginpass.unblank']}"
		},
		onError : "${LANG['website.user.login.js.message.loginpass.nowrite']}"
	});
	
	$("#authCode").formValidator({
		tipID: "loginPassTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		onError : ADMIN_LOGIN_CONSTANT.login_authcode_empty
	});
	
	$("#remember").change(function(e) {
		rememberMe();
	});
});
function rememberMe() {
	var checkRemember = $("#remember").attr("checked");
	if(checkRemember){
		var userName = $("#loginName").val();
		if(userName){
			$.cookie("userNameForAdmin", userName, { expires: 7});
		}	
	} else {
		$.cookie("userNameForAdmin", null);
	}
}

function fillInUserName() {
	var userName = $.cookie("userNameForAdmin");
	if(userName){
		$("#loginName").val(userName);
		$("#remember").attr("checked", "checked");
	}
}
function randomImg() {
	var random = new Date().getTime();
	$("#random").val(random);
	$("#authCodeImg").attr("src", "/valid/image?type=siteadminlogin&random="+random);
}
function resetPage() {
	if (typeof(window.top.reloadPage) !== "undefined") {
    	window.top.reloadPage();
	}
}
</script>
</body>
</html>
