<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/email-server.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.admin.email.config.headinfo0']}</span>
		<span class="desc">${LANG['website.admin.email.config.headinfo1']}</span>
	</div>
	<div class="body">
		<form name="configForm" id="configForm" action="/admin/email/savehost" method="post">
		<input type="hidden" name="id" id="id" value="${emailConfig.id}"/>
		<input type="hidden" name="siteId" id="siteId" value="${currentSiteAdmin.siteId}"/>
		<div class="fieldset">
			<div class="types">
				<label for="useCloud">
					<input class="customEmailFlag" id="useCloud" type="radio" name="customFlag" value="0" <c:if test="${emailConfig.siteId==0 }">checked </c:if> />${LANG['website.admin.email.config.usesystemconfig']}
				</label>
				<label for="usePersonal" class="rlabel">
					<input class="customEmailFlag" id="usePersonal" type="radio" name="customFlag" value="1" <c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">checked </c:if>/>${LANG['website.admin.email.config.customconfig']}
				</label>
			</div>
			<div class="contents">
				<div class="form-item">
					<label class="title" style="width: 130px;">${LANG['website.admin.email.config.serveraddress']}</label>
					<div class="widget">
						<input type="text" class="input-text" name="emailHost" id="emailHost"   <c:if test="${emailConfig.siteId==0 }">disabled style='opacity:0.5;filter:alpha(opacity=50);'</c:if>  value="<c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">${emailConfig.emailHost}</c:if>"/>
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailHostTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.email.config.sender']}</label>
					<div class="widget">
						<input type="text" class="input-text"  name="emailName" id="emailName"  <c:if test="${emailConfig.siteId==0 }">disabled style='opacity:0.5;filter:alpha(opacity=50);'</c:if> value="<c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">${emailConfig.emailName}</c:if>" />
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailNameTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.email.config.senderemail']}</label>
					<div class="widget">
						<input type="text" class="input-text" name="emailSender" id="emailSender" <c:if test="${emailConfig.siteId==0 }">disabled style='opacity:0.5;filter:alpha(opacity=50);'</c:if> value="<c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">${emailConfig.emailSender}</c:if>"/>
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailSenderTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.email.config.authenticationaccount']}</label>
					<div class="widget">
						<input type="text" name="emailAccount" id="emailAccount" class="input-text"  <c:if test="${emailConfig.siteId==0 }">disabled style='opacity:0.5;filter:alpha(opacity=50);' </c:if> value="<c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">${emailConfig.emailAccount}</c:if>" />
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailAccountTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.email.config.authenticationpassword']}</label>
					<div class="widget">
						<input type="password" class="input-text"  name="emailPassword" id="emailPassword"  <c:if test="${emailConfig.siteId==0 }">disabled style='opacity:0.5;filter:alpha(opacity=50);' </c:if> value="<c:if test="${emailConfig.siteId  == currentSiteAdmin.siteId }">${emailConfig.emailPassword}</c:if>"/>
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailPasswordTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title"></label>
					<div class="widget">
<%--						<button class="input-gray">${LANG['website.common.option.submit']}</button>--%>
						<input class="input-gray"  id="y_emile_button" type="submit"  value="${LANG['system.email.config.submit']}" />
						<c:if test="${msgCode!=null && msgCode!='' && msgCode=='1'}">
		            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
		            		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
		            	</c:if>
		            	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='2'}">
		            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
		            		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
		            	</c:if>   
					</div>
				</div>
			</div>
		</div>
		</form>
		<form name="checkForm" id="checkForm" action="/admin/email/checkHost" method="post">
		<div class="fieldset testing-info">
			<div class="legend"><span>${LANG['website.admin.email.config.testcontent']}</span></div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.email.config.testcontent.zhengwen']}</label>
				<div class="widget area-widget clearfix" style="*margin-left: 0px;_margin-left: 0px;">
					<textarea class="input-area" name="emailBody" id="emailBody" >${LANG['system.email.test.content']}</textarea>
					<span class="form-required"></span>
					<span class="form-tip"><span id="emailBodyTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.email.config.testemailaddress']}</label>
				<div class="widget">
					<input type="text" class="input-text"  name="toEmail" id="toEmail"  value="${currentSiteAdmin.userEmail }" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="toEmailTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title"></label>
				<div class="widget">
					 	<input type="submit" class="input-gray" value="${LANG['website.common.option.send']}"/>
						<c:if test="${msgCode!=null && msgCode!='' && msgCode=='3'}">
		            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
<%--		            		<div class="y_conceal_box">--%>
		            		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
<%--		            		</div>--%>
		            	</c:if>
		            	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='4'}">
		            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
<%--		            		<div class="y_conceal_box">--%>
		            		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" />${LANG[emailMsgKey]}
<%--		            		</div>--%>
		            	</c:if>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
var validString = {
		emailHost: {
			onShow: "${LANG['website.admin.email.config.lengthlimit']}",
			onFocus: "${LANG['website.admin.email.config.lengthlimit']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.admin.email.config.lengthlimit']}"
		},
		emailName: {
			onShow: "${LANG['website.admin.email.config.lengthlimit']}",
			onFocus: "${LANG['website.admin.email.config.lengthlimit']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.admin.email.config.lengthlimit']}"
		},
		emailSender: {
			onShow: "${LANG['website.user.profile.val.email']}",
			onFocus: "${LANG['website.user.profile.val.email']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.user.profile.val.email.oninputerror']}",
			onRegExpError: "${LANG['website.user.profile.val.email.oninputerror']}"
		},
		emailAccount: {
			onShow: "${LANG['website.admin.email.config.lengthlimit']}",
			onFocus: "${LANG['website.admin.email.config.lengthlimit']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.admin.email.config.lengthlimit']}"
		},
		emailPassword: {
			onShow: "${LANG['website.admin.email.config.lengthlimit']}",
			onFocus: "${LANG['website.admin.email.config.lengthlimit']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.admin.email.config.lengthlimit']}"
		},
		emailBody: {
			onShow: "${LANG['website.admin.email.config.lengthlimit']}",
			onFocus: "${LANG['website.admin.email.config.lengthlimit']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.admin.email.config.lengthlimit']}"
		},
		toEmail: {
			onShow: "${LANG['website.user.profile.val.email']}",
			onFocus: "${LANG['website.user.profile.val.email']}",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space']}",
			onInputError: "${LANG['website.user.profile.val.email.oninputerror']}",
			onRegExpError: "${LANG['website.user.profile.val.email.oninputerror']}"
		}
};
$(function() {
	
	$(".customEmailFlag").change(function() {
		var value = $(this).val();
		if(value=="0") {
			$.formValidator.resetTipState();
			$("#emailHost").unFormValidator(true);
			$("#emailName").unFormValidator(true);
			$("#emailSender").unFormValidator(true);
			$("#emailAccount").unFormValidator(true);
			$("#emailPassword").unFormValidator(true);
			$("#configForm").find(".input-text").attr("disabled", "disabled").css({"color":"#888", "opacity":"0.5", "filter":"alpha(opacity=50)"});
		} else {
			$("#emailHost").unFormValidator(false);
			$("#emailName").unFormValidator(false);
			$("#emailSender").unFormValidator(false);
			$("#emailAccount").unFormValidator(false);
			$("#emailPassword").unFormValidator(false);
			$("#configForm").find(".input-text").removeAttr("disabled").css({"color":"#000", "opacity":"1", "filter":"alpha(opacity=100)"});
		}
	});
	
	$.formValidator.initConfig({
		oneByOneVerify:true,
		formID : "configForm",
		debug : false,
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	$("#emailHost").formValidator({
		onShow : validString.emailHost.onShow,
		onFocus : validString.emailHost.onFocus,
		onCorrect : validString.emailHost.onCorrect
	}).inputValidator({
		min : 1,
		max : 64,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailHost.emptyError
		},
		onError : validString.emailHost.onInputError
	});
	$("#emailName").formValidator({
		onShow : validString.emailName.onShow,
		onFocus : validString.emailName.onFocus,
		onCorrect : validString.emailName.onCorrect
	}).inputValidator({
		min : 1,
		max : 64,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailName.emptyError
		},
		onError : validString.emailName.onInputError
	});
	$("#emailSender").formValidator({
		onShow : validString.emailSender.onShow,
		onFocus : validString.emailSender.onFocus,
		onCorrect : validString.emailSender.onCorrect
	}).inputValidator({
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailSender.emptyError
		},
		onError : validString.emailSender.onInputError
	}).regexValidator({
		regExp : "email",
		dataType : "enum",
		onError : validString.emailSender.onRegExpError
	});
	$("#emailAccount").formValidator({
		onShow : validString.emailAccount.onShow,
		onFocus : validString.emailAccount.onFocus,
		onCorrect : validString.emailAccount.onCorrect
	}).inputValidator({
		min : 1,
		max : 64,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailAccount.emptyError
		},
		onError : validString.emailAccount.onInputError
	});
	$("#emailPassword").formValidator({
		onShow : validString.emailPassword.onShow,
		onFocus : validString.emailPassword.onFocus,
		onCorrect : validString.emailPassword.onCorrect
	}).inputValidator({
		min : 1,
		max : 64,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailPassword.emptyError
		},
		onError : validString.emailPassword.onInputError
	});
	
	var customFlag = $('input:radio[name="customFlag"]:checked').val();
	if (customFlag== "0") {
		$("#emailHost").unFormValidator(true);
		$("#emailName").unFormValidator(true);
		$("#emailSender").unFormValidator(true);
		$("#emailAccount").unFormValidator(true);
		$("#emailPassword").unFormValidator(true);
	} else {
		$("#emailHost").unFormValidator(false);
		$("#emailName").unFormValidator(false);
		$("#emailSender").unFormValidator(false);
		$("#emailAccount").unFormValidator(false);
		$("#emailPassword").unFormValidator(false);
	}
	
	//邮件服务器信息测试
	$.formValidator.initConfig({
		validatorGroup:"2",
		oneByOneVerify:true,
		formID : "checkForm",
		debug : false,
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	
	$("#emailBody").formValidator({
		validatorGroup:"2",
		onShow : validString.emailBody.onShow,
		onFocus : validString.emailBody.onFocus,
		onCorrect : validString.emailBody.onCorrect
	}).inputValidator({
		min : 1,
		max : 64,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.emailBody.emptyError
		},
		onError : validString.emailBody.onInputError
	});
	$("#toEmail").formValidator({
		validatorGroup:"2",
		onShow : validString.toEmail.onShow,
		onFocus : validString.toEmail.onFocus,
		onCorrect : validString.toEmail.onCorrect
	}).inputValidator({
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.toEmail.emptyError
		},
		onError : validString.toEmail.onInputError
	}).regexValidator({
		regExp : "email",
		dataType : "enum",
		onError : validString.toEmail.onRegExpError
	});

	setCursor("emailHost", $("#emailHost").val().length);
});
</script>
</body>
</html>