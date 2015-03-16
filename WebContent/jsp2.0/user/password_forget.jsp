<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['website.user.login.loginpass.forget'] } - ${LANG['website.common.index.title.base.1'] }</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/forget.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.index.css" />
</head>
<body>
	<!--#include file="/assets/inc/header.html"-->
	<nav id="navigator">
		<div class="wrapper">
			<div class="dynamic">
				<a class="login"><i class="icon icon-forget fix-png"></i>${LANG['website.user.login.loginpass.forget'] }</a>
			</div>
			<div class="region">
			<%--
				<!-- <a class="timezone"><i class="icon icon-zone"></i>北京时间</a> -->
				<c:set var="fullTimeZoneDesc" value="website.timezone.city.${siteBase.timeZoneId}"/>
			<a class="timezone" href="#"><i class="icon icon-zone fix-png"></i>${LANG[fullTimeZoneDesc]}${LANG['website.common.time.name']}</a> 
			
				<span class="language">
					<select>
						<option>${LANG['website.common.language.zh']}</option>
					</select>
				</span>
				 --%>
			</div>
		</div>
	</nav>
	<div id="content">
		<div class="wrapper">
			<div class="forget-box">
				<b class="border-cover"></b>
				<div class="box-wrapper box-wrapper-reset-pass">
					<p class="desc">${LANG['website.user.password.forget.message.1'] }</p>
					<form id="pass-forgot-form" method="post" class="pass-forgot-form">
						<input type="hidden" name="random" id="random" value=""/>
						<input type="hidden" name="type" id="type" value="forgetpass"/>
						<div class="form-item email-widget">
							<label class="title">${LANG['website.user.password.forget.email.address'] }${LANG['website.common.symbol.colon'] }</label>
							<div class="widget">
								<input type="text" class="input-text" id="userEmail" name="userEmail"/>
								<p class="error">
									<span class="form-tip"><span id="userEmailTip"></span></span>
								</p>
							</div>
						</div>
						<div class="form-item captcha-widget">
							<label class="title">${LANG['website.common.authcode.name']}${LANG['website.common.symbol.colon'] }</label>
							<div class="widget">
								<input type="text" class="input-text" id="authCode" name="authCode"/>
								<img id="authCodeImg" alt="${LANG['website.common.authcode.name']}" src="" class="captcha" width="0" height="0" style="border: 1px solid #bbbbbb;"/>
								<a class="update-captcha" onclick="return randomImg()">${LANG['website.common.authcode.next']}</a>
								<p class="error">
										<span class="form-tip"><span id="authCodeTip"></span></span>
								</p>
							</div>
						</div>
						<div class="form-item submit-widget">
							<div class="widget">
								<button class="input-submit" type="submit" >${LANG['website.common.option.submit']}</button>
							</div>
						</div>
					</form>
				</div>
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
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.widgets.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">



var FORGET_CONSTANT={
		email_show:"${LANG['website.user.password.forget.js.message.email.onshow']}",		//"邮件如alanliu@bizconf.cn"
		email_focus:"${LANG['website.user.password.forget.js.message.email.onfocus']}",		//邮件如alanliu@bizconf.cn
		email_unblank:"${LANG['website.user.password.forget.js.message.email.unblank']}",	//邮箱两边不能有空符号
		email_error:"${LANG['website.user.password.forget.js.message.email.error']}",		//请正确填写邮箱！
		authcode_error:"${LANG['website.user.password.forget.js.message.authcode.error']}",	//请填写验证码！
		remind_title:"${LANG['website.user.password.forget.js.message.remind']}",			//提醒
		pubconfirm:"${LANG['website.common.option.confirm']}",								//确定
		pubcancel:"${LANG['website.common.option.cancel']}",								//取消
		sendok:"${LANG['website.user.password.forget.js.message.sendok']}"					//我们已经向"+userEmail+"发送了密码重置邮件, 请注意查收。
		
};

	function randomImg() {
		var random = new Date().getTime();
		$("#random").val(random);
		$("#authCodeImg").attr("src", "/valid/image?type=forgetpass&random="+random);
	}
	
	$(function() {
		randomImg();
		$("#authCodeImg").width(66);
		$("#authCodeImg").height(22);
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "pass-forgot-form",
			debug : false,
			onSuccess : function() {
				sendEmail();
			},
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		
		$("#userEmail").formValidator({
			onShow : FORGET_CONSTANT.email_show,
			onFocus : FORGET_CONSTANT.email_focus,
			onCorrect : ""
		}).inputValidator({
			min : 6,
			max : 64,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : FORGET_CONSTANT.email_unblank
			},
			onError : FORGET_CONSTANT.email_error
		}).regexValidator({
			regExp : "email",
			dataType : "enum",
			onError : FORGET_CONSTANT.email_error
		});
		
		$("#authCode").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			onError : FORGET_CONSTANT.authcode_error
		})
// 		.functionValidator({
// 		    fun:function(val,elem){
// 		    	var authValue = $("#random").val();
// 		        if(val==authValue){
// 				    return true;
// 			    }else{
// 				    return "请正确填写验证码";
// 			    }
// 			}
// 		})
		;
	});
	
	function checkForm() {
		if(passwordForgetForm.form()){
			sendEmail();
		}
	}
	
	function sendEmail(){
		var authCode = $("#authCode").val();
		var type = $("#type").val();
		var random = $("#random").val();
		var userEmail = $("#userEmail").val();

		app.forgotUserPass(authCode, type, random, userEmail, function(result) {
			randomImg();
			if (result && result.status==2) {
				$("#authCode").val("");
				$("#userEmail").val("");
				successDialog(FORGET_CONSTANT.sendok.replaceAll("####userEmail####",userEmail));	
			} else {
				errorDialog(result.message);
			}
		});
	}

	function successDialog(message) {
		$("<div/>").alertDialog({
			"title": FORGET_CONSTANT.remind_title,
			"dialogClass" : "ui-dialog-alert",
			"message" : message,
			"type": "success",
			"buttonOk": FORGET_CONSTANT.pubconfirm,
			"successCallback" : function() {
				
			}
		});
	}
	
	function errorDialog(message, callback) {
		$("<div/>").alertDialog({
			"title": FORGET_CONSTANT.remind_title,
			"dialogClass" : "ui-dialog-alert",
			"message" : message,
			"type": "prompt",
			"buttonOk": FORGET_CONSTANT.pubconfirm,
			"buttonCancel": FORGET_CONSTANT.pubcancel,
			"successCallback" : callback 
		});
	} 
	function login() {
		var userBase = {};
		userBase.loginName = $("#loginName").val();
		userBase.loginPass = $("#loginPass").val();
		userBase.authCode = $("#authCode").val();
		userBase.type = $("#type").val();
		userBase.random = $("#random").val();
		app.userLogin(userBase, function(result) {
			if(result) {
				if(result.status==10){
					//rememberMe();
					parent.location.href = "/";
					//closeDialog();
				} else {
					//randomImg();
					//parent.errorDialog(result.message);
					document.getElementById("dangerAlert").innerHTML= result.message; 
				}
			}
		});
	}
	
</script>
</body>
</html>