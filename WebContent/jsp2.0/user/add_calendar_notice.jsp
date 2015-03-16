<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body style="padding: 0px;">
<div class="adduser-dialog mail-notifier-dialog">
	<form method="post" id="personal-form" class="login-form">
		<div class="mail-notifier-section">
			<p class="notes">
				 ${LANG['website.user.calendar.reminder.titleinfo1']}<br />
				${LANG['website.user.calendar.reminder.titleinfo2']}
			</p>
			<div class="form-content">
				<div class="form-item username-widget">
					<label class="title">${LANG['website.user.calendar.reminder.emailaddress']}</label>
					<div class="widget">
						<input type="text" id="email" style="width: 170px;margin-right: 10px;"  class="input-text"/>
						<span class="form-required"></span>
						<span class="form-tip"><span id="emailTip"></span></span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-buttons">
			<input type="submit" class="button input-gray" value="${LANG['website.common.option.send']}">
			<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>
	</form>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
	function closeDialog(result) {
		var dialog = top.$("#addCalendar");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
	
	$(document).ready(function(){
		$.formValidator.initConfig({
			formID : "personal-form",
			debug : false,
			//theme: "contact",
			onSuccess : function() {
				sendEmail();
			},
			onError : function() {
				
			}
		});
		 
		$("#email").formValidator({
			onShow : "${LANG['website.user.calendar.reminder.warninfo1']}",
			onFocus : "${LANG['website.user.calendar.reminder.warninfo1']}",
			onCorrect : ""
		}).inputValidator({
			min : 6,
			max : 64,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : "${LANG['website.user.calendar.reminder.warninfo2']}"
			},
			onError : "${LANG['website.user.calendar.reminder.warninfo3']}"
		}).regexValidator({
			regExp : "email",
			dataType : "enum",
			onError : "${LANG['website.user.calendar.reminder.warninfo3']}"
		});
		setCursor("email", $("#email").val().length);
	});
	
	function sendEmail() {
		var data = {};
		var frame = parent.$("#addCalendar");
		data.confId = frame.data("data");
		if(!data.confId){data.confId = '${confId}';}
//	 	data.userName = $("#userName").val();
		data.email = $("#email").val();
		app.sendNoticeEmail(data, function(result) {
			if(result){
				if(result.status && result.status==1){
					parent.successDialog(result.message);
				} else {
					parent.errorDialog(result.message);
				}
				closeDialog();
			}
		},{message:"${LANG['website.user.calendar.reminder.sending']}...", ui:parent});
	}
</script>

</body>
</html>
