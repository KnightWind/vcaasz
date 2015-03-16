<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['website.user.password.reset.password'] } - ${LANG['website.common.index.title.base.1'] }</title>
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
				<a class="login"><i class="icon icon-forget fix-png"></i>${LANG['website.user.password.reset.password'] }</a>
			</div>
			<div class="region">
			<%--
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
					<p class="desc">${LANG['website.user.password.reset.page.content'] }</p>
					<form action="/user/password/save" name="passwordResetForm" id="passwordResetForm" class="passwordResetForm" method="post">
						<input type="hidden" name="sid" id="sid" value="${sid}"/>
						<input type="hidden" name="auth" id="auth" value="${auth}"/>
						<input type="hidden" name="ts" id="ts" value="${ts}"/>
						<input type="hidden" name="ln" id="ln" value="${ln}"/>
						<input type="hidden" name="random" id="random" value=""/>
						<input type="hidden" name="type" id="type" value="resetpass"/>
						<div class="form-item email-widget">
							<label class="title">${LANG['website.user.password.reset.page.password'] }：</label>
							<div class="widget">
								<input type="password" class="input-text" id="lp" name="lp"/>
								<p class="error">
<!-- 									<i class="icon"></i>请正确填写密码！ -->
									<span class="form-tip"><span id="lpTip"></span></span>
								</p>
							</div>
						</div>
						<div class="form-item email-widget">
							<label class="title">${LANG['website.user.password.reset.page.password.confirm'] }：</label>
							<div class="widget">
								<input type="password" class="input-text" id="clp" name="clp"/>
								<p class="error">
<!-- 									<i class="icon"></i>请正确填写密码！ -->
									<span class="form-tip"><span id="clpTip"></span></span>
								</p>
							</div>
						</div>
						<div class="form-item captcha-widget">
							<label class="title">${LANG['website.common.authcode.name'] }：</label>
							<div class="widget">
								<input type="text" class="input-text" id="authCode" name="authCode"/>
								<img id="authCodeImg" alt="${LANG['website.common.authcode.name'] }" src="" class="captcha" width="0" height="0" style="border: 1px solid #bbbbbb;"/>
								<a class="update-captcha" onclick="return randomImg()">${LANG['website.common.authcode.next'] }</a>
								<p class="error">
<!-- 									<i class="icon"></i>请输入验证码！ -->
									<span class="form-tip"><span id="authCodeTip"></span></span>
								</p>
							</div>
						</div>
						<div class="form-item submit-widget">
							<div class="widget">
								<button class="input-submit" type="submit" >${LANG['website.common.option.submit'] }</button>
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
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></SCRIPT>
<script type="text/javascript">
	var validString = {
			lp: {
				onShow: "${LANG['website.user.password.reset.lp.onshow']}",
				onFocus: "${LANG['website.user.password.reset.lp.onshow']}",
				onCorrect: "",
				emptyError: "${LANG['website.user.password.reset.lp.emptyerror']}",
				onInputError: "${LANG['website.user.password.reset.lp.inputerror']}"
			},
			clp: {
				onShow: "",
				onFocus: "",
				onCorrect: "",
				emptyError: "${LANG['website.user.password.reset.clp.emptyerror']}",
				onInputError: "${LANG['website.user.password.reset.clp.oninputerror']}",
				onCompareError: "${LANG['website.user.password.reset.clp.oncompareerror']}"
			},
			authCode: {
				onInputError: "${LANG['website.user.password.reset.authCode.oninputerror']}"
			}
	};
	var FORGET_CONSTANT={
			remind_title:"${LANG['website.user.password.forget.js.message.remind']}",			//提醒
			pubconfirm:"${LANG['website.common.option.confirm']}",								//确定
			pubcancel:"${LANG['website.common.option.cancel']}",								//取消
			sendok:"${LANG['website.user.password.forget.js.message.sendok']}"					//我们已经向"+userEmail+"发送了密码重置邮件, 请注意查收。
			
	};
	
	$(function() {
		randomImg();
		$("#authCodeImg").width(66);
		$("#authCodeImg").height(22);
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "passwordResetForm",
			debug : false,
			onSuccess : function() {
				resetPass();
			},
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		
		
		$("#lp").formValidator({
			onShow : validString.lp.onShow,
			onFocus : validString.lp.onFocus,
			onCorrect : ""
		}).inputValidator({
			min : 6,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.lp.emptyError
			},
			onError : validString.lp.onInputError
		}).functionValidator({
			fun:function(val,elem){
		    	var loginPass = $("#lp").val();
				//位数,字母字符和数字的校验
				if(!ValidatorPass(loginPass)){
					return "密码安全级别较低，请以数字、字母、特殊字符中任意两种混合设置";	
				}else{
					return true;
				}
			}
		});
		
		$("#clp").formValidator({
			onShow : validString.clp.onShow,
			onFocus : validString.clp.onFocus,
			onCorrect : validString.clp.onCorrect
		}).inputValidator({
			min : 6,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.clp.emptyError
			},
			onError : validString.clp.onInputError
		}).compareValidator({
			desID : "lp",
			operateor : "=",
			onError : validString.clp.onCompareError
		});
		
		$("#authCode").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			onError : validString.authCode.onInputError
		});
	});
	
	function randomImg() {
		var random = new Date().getTime();
		$("#random").val(random);
		$("#authCodeImg").attr("src", "/valid/image?type=resetpass&random="+random);
	}
	
	function resetPass() {
		var data = {};
		data.ts = $("#ts").val();
		data.sid = $("#sid").val();
		data.auth = $("#auth").val();
		data.authCode = $("#authCode").val();
		data.type = $("#type").val();
		data.random = $("#random").val();
		data.ln = $("#ln").val();
		data.lp = $("#lp").val();
		data.clp = $("#clp").val();

		app.resetUserPass(data, function(result) {
			randomImg();
			if (result && result.status == 2) {
				$("#lp").val("");
				$("#clp").val("");
				$("#authCode").val("");
				successDialog("${LANG['bizconf.jsp.password_reset.res6']}");
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
</script>
</body>
</html>