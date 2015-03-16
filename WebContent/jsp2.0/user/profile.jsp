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
	<!--  个人设置 -->
		<span class="title">${LANG['website.user.profile.set'] }</span>
		<span class="desc">${LANG['website.user.profile.desc'] }</span>
	</div>
	<div class="body">
		<form id="personal-form" action="/user/profile/setupInfo" method="post" class="personal-form">
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.email'] }：</label>
				<div class="widget">
					<input type="text" name="userEmail" <c:if test="${not empty currentUser.userEmail}">readonly="readonly" style='border:0px;'</c:if> id="userEmail" value="${currentUser.userEmail}" class="input-text" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="userEmailTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.name'] }：</label>
				<div class="widget">
					<input name="trueName" id="trueName" type="text" value="${currentUser.trueName}" class="input-text" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="trueNameTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.user.profile.englishname'] }：</label>
				<div class="widget">
					<input name="enName" id="enName" type="text" value="${currentUser.enName}" class="input-text" />
					<span class="form-tip"><span id="enNameTip"></span></span>
				</div>
			</div>

			<div class="form-item">
				<label class="title">${LANG['website.user.profile.telephone'] }：</label>
				<div class="widget">
					<input type="text" name="mobile" id="mobile" value="${currentUser.mobile}" class="input-text" />
					<span class="form-tip"><span id="mobileTip"></span></span>
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
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js?var=1"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
var validString = {
		loginName: {
			onShow: "${LANG['website.user.profile.val.loginname'] }",
			onFocus: "${LANG['website.user.profile.val.loginname'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.loginname.space'] }",
			onInputError: "${LANG['website.user.profile.val.loginname.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.loginname.onregexperror'] }"
		},
		trueName: {
			onShow: "${LANG['website.user.profile.val.truename'] }",
			onFocus: "${LANG['website.user.profile.val.truename'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.truename.space'] }",
			onInputError: "${LANG['website.user.profile.val.truename.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.truename.onregexperror'] }"
		},
		enName: {
			onShow: "${LANG['website.user.profile.val.enname'] }",
			onFocus: "${LANG['website.user.profile.val.enname'] }",
			onCorrect: "",
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.enname.space'] }",
			onInputError: "${LANG['website.user.profile.val.enname.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.enname.onregexperror'] }"
		},
		userEmail: {
			onShow: "${LANG['website.user.profile.val.email'] }",
			onFocus: "${LANG['website.user.profile.val.email'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.email.space'] }",
			onInputError: "${LANG['website.user.profile.val.email.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.email.onregexperror'] }"
		},
		mobile: {
			onShow: "${LANG['website.user.profile.val.mobile'] }",
			onFocus: "${LANG['website.user.profile.val.mobile'] }",
			onCorrect: "",
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.mobile.space'] }",
			onInputError: "${LANG['website.user.profile.val.mobile.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.mobile.onregexperror'] }"
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
		$("#loginName").formValidator({
			onShow : validString.loginName.onShow,
			onFocus : validString.loginName.onFocus,
			onCorrect : validString.loginName.onCorrect
		}).inputValidator({
			min : 4,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.loginName.emptyError
			},
			onError : validString.loginName.onInputError
		}).regexValidator({
				regExp : "loginname",
				dataType : "enum",
				onError : validString.loginName.onRegExpError
		});
		$("#trueName").formValidator({
			onShow : validString.trueName.onShow,
			onFocus : validString.trueName.onFocus,
			onCorrect : validString.trueName.onCorrect
		}).inputValidator({
			min : 1,
			max : 32,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.trueName.emptyError
			},
			onError : validString.trueName.onInputError
		}).regexValidator({
			regExp : "truename",
			dataType : "enum",
			onError : validString.trueName.onRegExpError
		});
		$("#enName").formValidator({
			empty:true,
			onShow : validString.enName.onShow,
			onFocus : validString.enName.onFocus,
			onCorrect : validString.enName.onCorrect,
			onEmpty:validString.enName.onEmpty
		}).inputValidator({
			min : 0,
			max: 32,
			onError : validString.enName.onInputError
		}).regexValidator({
				regExp : "enname",
				dataType : "enum",
				onError : validString.enName.onRegExpError
		});

		$("#userEmail").formValidator({
			onShow : validString.userEmail.onShow,
			onFocus : validString.userEmail.onFocus,
			onCorrect : validString.userEmail.onCorrect
		}).inputValidator({
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.userEmail.emptyError
			},
			onError : validString.userEmail.onInputError
		}).regexValidator({
			regExp : "email",
			dataType : "enum",
			onError : validString.userEmail.onRegExpError
		});

		$("#mobile").formValidator({
			empty : true,
			onShow : validString.mobile.onShow,
			onFocus : validString.mobile.onFocus,
			onCorrect : validString.mobile.onCorrect,
			onEmpty : validString.mobile.onEmpty
		}).inputValidator({
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : ""
			},
			onError : validString.mobile.onInputError
		}).regexValidator({
			regExp : "tel",
			dataType : "enum",
			onError : validString.mobile.onRegExpError
		});
		
		//setCursor("loginName", $("#loginName").val().length);
	});
</script>
</body>
</html>