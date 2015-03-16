<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/settings.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.user.profile.set'] }</span>
		<span class="desc">${LANG['website.user.profile.desc.pass'] }</span>
	</div>
	<div class="body">
		<form id="personal-form" action="/user/profile/resetPass" method="post" class="personal-form">
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.oldpass'] }：</label>
				<div class="widget">
					<input type="password" name="oldpassword" id="oldpassword" value="" class="input-text" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="oldpasswordTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.newpass'] }：</label>
				<div class="widget">
					<input type="password" name="loginPass" id="loginPass" value="" class="input-text" />
					<span class="form-required"></span>
<!-- 					<span class="error"><i class="icon"></i>请正确填写新密码！</span> -->
					<span class="form-tip"><span id="loginPassTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.confirm.pass'] }：</label>
				<div class="widget">
					<input type="password" name="loginPass2" id="loginPass2" class="input-text" />
					<span class="form-required"></span>
<!-- 					<span class="error"><i class="icon"></i>确认密码和新密码必须一致！</span> -->
					<span class="form-tip"><span id="loginPass2Tip"></span></span>
				</div>
			</div>
			<div class="form-item submit-item">
				<div class="widget">
					<button type="submit" class="input-gray">${LANG['website.common.option.save'] }</button>
					<c:if test="${!empty infoMessage}">
	        			<span class="message_span">
	        			<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
	        			</span>
		 			</c:if>
		 			<c:if test="${!empty errorMessage}">
						<span class="message_span">
		           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
		           		</span>
	           		</c:if>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></script>
<script type="text/javascript">
var validString = {
		oldPassWord: {
			onShow: "",
			onFocus: "",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.oldpass.space'] }",
			onInputError: "${LANG['website.user.profile.val.oldpass.oninputerror'] }"
		},
		loginPass: {
			onShow: "${LANG['website.user.profile.val.newpass.onshow'] }",
			onFocus: "${LANG['website.user.profile.val.newpass.onfocus'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.newpass.space'] }",
			onInputError: "${LANG['website.user.profile.val.newpass.oninputerror'] }",
			safeError:"${LANG['website.user.profile.val.newpass.tip1'] }"
			
		},
		loginPass2: {
			onShow: "",
			onFocus: "",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space'] }",
			onInputError: "${LANG['website.user.profile.val.confrim.pass.oninputerror'] }",
			onCompareError: "${LANG['website.user.profile.val.confrim.twice'] }"
		}
};
$(document).ready(function(){
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "personal-form",
			debug : false,
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		$("#oldpassword").formValidator({
			onShow : validString.oldPassWord.onShow,
			onFocus : validString.oldPassWord.onFocus,
			onCorrect : validString.oldPassWord.onCorrect
		}).inputValidator({
			min : 1,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.oldPassWord.emptyError
			},
			onError : validString.oldPassWord.onInputError
		});
		$("#loginPass").formValidator({
			onShow : validString.loginPass.onShow,
			onFocus : validString.loginPass.onFocus,
			onCorrect : validString.loginPass.onCorrect
		}).inputValidator({
			min : 6,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.loginPass.emptyError
			},
			onError : validString.loginPass.onInputError
		}).functionValidator({
			fun:function(val,elem){
		    	var loginPass = $("#loginPass").val();
				//位数,字母字符和数字的校验
				if(!ValidatorPass(loginPass)){
					return validString.loginPass.safeError;	
				}else{
					return true;
				}
			}
		});
		
		$("#loginPass2").formValidator({
			onShow : validString.loginPass2.onShow,
			onFocus : validString.loginPass2.onFocus,
			onCorrect : validString.loginPass2.onCorrect
		}).inputValidator({
			min : 6,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.loginPass2.emptyError
			},
			onError : validString.loginPass2.onInputError
		}).compareValidator({
			desID : "loginPass",
			operateor : "=",
			onError : validString.loginPass2.onCompareError
		});
		
		setCursor("oldpassword", $("#oldpassword").val().length);
	});
</script>
</body>
</html>