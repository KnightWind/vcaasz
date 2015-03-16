<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css?var=19" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body style="padding: 0px;">
<div class="adduser-dialog contact-dialog">
	<p class="notes">
		 ${LANG['website.user.contact.edit.titleinfo1']}<br />
	</p>
	<form id="contact-form" method="post" action="#" class="contact-form">
		<div class="form-content">
			<div class="form-item username-widget">
				<label class="title">${LANG['website.user.contact.edit.name']}：</label>
				<div class="widget">
					<input type="text" id="contactName" style="width: 170px;margin-right: 10px;" value="${contact.contactName}"  class="input-text" placeholder="${LANG['website.user.contact.edit.name']}" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="contactNameTip"></span></span>
				</div>
			</div>
			<div class="form-item english-widget">
				<label class="title"> ${LANG['website.user.contact.edit.enname']}：</label>
				<div class="widget">
					<input type="text" id="contactNameEn" style="width: 170px;margin-right: 10px;" value="${contact.contactNameEn}" class="input-text" placeholder="${LANG['website.user.contact.edit.enname']}" />
					<span class="form-tip"><span id="contactNameEnTip"></span></span>
				</div>
			</div>
			<!-- error-item -->
			<div class="form-item email-widget ">
				<label class="title">${LANG['website.user.contact.edit.email']}：</label>
				<div class="widget"> 
					<input type="text" class="input-text" style="width: 170px;margin-right: 10px;" id="contactEmail"  value="${contact.contactEmail}" placeholder="${LANG['website.user.contact.edit.email']}" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="contactEmailTip"></span></span>
				</div>
			</div>
			<div class="form-item landline-widget">
				<label class="title"> ${LANG['website.user.contact.edit.telphone']}：</label>
				<div class="widget">
					<input type="text" id="contactPhone" style="width: 170px;margin-right: 10px;" value="${contact.contactPhone}" class="input-text" placeholder="${LANG['website.user.contact.edit.telphone']}" />
					<span class="form-tip"><span id="contactPhoneTip"></span></span>
				</div>
			</div>
			<div class="form-item mobile-widget">
				<label class="title">${LANG['website.user.contact.edit.mobile']}：</label>
				<div class="widget">
					<input type="text" id="contactMobile" style="width: 170px;margin-right: 10px;" value="${contact.contactMobile}" class="input-text" placeholder="${LANG['website.user.contact.edit.mobile']}" />
					<span class="form-tip"><span id="contactMobileTip"></span></span>
				</div>
			</div>
			<div class="form-item remark-widget">
				<label class="title">${LANG['website.user.contact.edit.remark']}：</label>
				<div class="widget">
					<textarea class="input-area"  id="remark" style="margin-right: 10px;"  value="${contact.contactDesc}">${contact.contactDesc}</textarea>
					<span class="count">
						<span id='nowCountSpan'>0</span>/256
					</span>
				</div>
			</div>
		</div>
		<div class="form-buttons">
			<input type="submit" class="button input-gray" value="${LANG['website.common.option.confirm']}"> 
			<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>
	</form>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<script type="text/javascript">
	var validString = {
		trueName: {
			onShow: "${LANG['website.user.profile.val.truename'] }",
			onFocus: "${LANG['website.user.profile.val.truename'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.truename.space'] }",
			onInputError: "${LANG['website.user.profile.val.truename.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.truename.onregexperror'] }"
		},
		enName: {
			onShow: "${LANG['website.user.profile.val.truename'] }",
			onFocus: "${LANG['website.user.profile.val.truename'] }",
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
		},
		cellPhone: {
			onShow: "${LANG['website.user.profile.val.cellphone'] }",
			onFocus: "${LANG['website.user.profile.val.cellphone'] }",
			onCorrect: "",
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.cellphone.space'] }",
			onInputError: "${LANG['website.user.profile.val.cellphone.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.cellphone.onregexperror'] }"
		},
		remark: {
			onShow: "",
			onFocus: "",
			onCorrect: "",
			onEmpty: "",
			emptyError: "",
			onInputError: ""
		}
			
	}; 
	$(document).ready(function(){
		var tmpLength = countLength($("#remark").val());
		$("#nowCountSpan").html(tmpLength);
		if (tmpLength>256) {
			$(".count").css("color", "#C91600");
		} else {
			$(".count").css("color", "#B0B0B0");
		}
		
		$.formValidator.initConfig({
			formID : "contact-form",
			debug : false,
			ajaxForm: true,
			onSuccess : function(){
				saveContact();
			},
			onError : function(){
				
			}
		});
		 
		$("#contactName").formValidator({
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
		});
		 
		$("#contactNameEn").formValidator({
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
		
		$("#contactEmail").formValidator({
			onShow : validString.userEmail.onShow,
			onFocus : validString.userEmail.onFocus,
			onCorrect : validString.userEmail.onCorrect
		}).inputValidator({
			min : 1,
			max : 64,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.userEmail.emptyError
			},
			onError : "${LANG['website.user.contact.edit.emailtoolong']}"
		}).regexValidator({
			regExp : "email",
			dataType : "enum",
			onError : validString.userEmail.onRegExpError
		});
		
		$("#contactPhone").formValidator({
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
			//onError : validString.mobile.onInputError
			onError : validString.cellPhone.onInputError
		}).regexValidator({
			regExp : "tel",
			dataType : "enum",
			//onError : validString.mobile.onRegExpError
			onError : validString.cellPhone.onRegExpError
		});
		
		$("#contactMobile").formValidator({
			empty : true,
			onShow : validString.cellPhone.onShow,
			onFocus : validString.cellPhone.onFocus,
			onCorrect : validString.cellPhone.onCorrect,
			onEmpty : validString.cellPhone.onEmpty
		}).inputValidator({
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : ""
			},
			//onError : validString.cellPhone.onInputError
			onError : validString.mobile.onInputError
		}).regexValidator({
			regExp : "mobile",
			dataType : "enum",
			//onError : validString.cellPhone.onRegExpError
			onError : validString.mobile.onRegExpError
		});
		
		$("#remark").formValidator({
			empty : true,
			onShow : "",
			onFocus : "",
			onCorrect : "",
			onEmpty : ""
		}).inputValidator({
			min : 0,
			max : 256,
			onError : ""
		});
		
		
		$("#remark").keyup(function() {
			var nowLength=countLength($(this).val());
			if (nowLength>256) {
				$(".count").css("color", "#C91600");
			} else {
				$(".count").css("color", "#B0B0B0");
			}
			$("#nowCountSpan").text(nowLength);
		}).bind('paste', function(){
			$(this).prev('label').hide();
		});
		
		setCursor("contactName", $("#contactName").val().length);
	});
	
	function saveContact() {
		var data = {};
		data.contactName = $("#contactName").val();
		data.contactNameEn = $("#contactNameEn").val();
		data.contactEmail = $("#contactEmail").val();
		data.contactPhone = $("#contactPhone").val();
		data.contactMobile = $("#contactMobile").val();
		data.contactDesc = $("#remark").val();
		data.id = "${contact.id}";
		app.addContact(data, function(result) {
			if(result){
				if(result.status==1){
					top.refreshChildIframe("/user/contact/list","contentFrame");
					top.successDialog(result.message);
					closeDialog();	
				} else {
					parent.errorDialog(result.message);
				}
			}
		});
	}
	
	function closeDialog(result) {
		var dialog = parent.$("#editcontactDialog");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
	
	
	function countLength(str){
		if(str)
		return  str.length;
		else
			return 0 ;
	}
</script>

</body>
</html>