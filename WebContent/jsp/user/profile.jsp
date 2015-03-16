<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.profile.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>


<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>
<script type="text/javascript">
var profileForm = null;
$(function() {
	$('body').bind("click", function() {
		$(".message_span").remove();
	});
	var ruleString = {
		required: {
			"loginName": "${LANG['valid.input.loginName']}",
			"trueName": "${LANG['valid.input.userName']}",
			"oldpassword": "${LANG['valid.input.oldPass']}",
			"password": "${LANG['valid.input.loginPass']}",
			"confirmpass": "${LANG['valid.input.passConfirm']}",
			"userEmail": "${LANG['valid.input.email']}",
			"mobile": "${LANG['valid.input.phone']}"
		},
		rangelength: {
			"loginName": "${LANG['valid.rangelength416']}",
			"truename": "${LANG['valid.rangelength132']}",
			"enName": "${LANG['valid.rangelength132']}",
			"password": "${LANG['valid.rangelength616']}",
			"userEmail": "${LANG['valid.rangelength664']}",
			"mobile": "${LANG['valid.rangelength432']}"
		},
		custom: {
			"loginName": "${LANG['valid.checkLoginName']}",
			"checkUserName": "${LANG['valid.checkUserName']}",
			"checkEnName": "${LANG['valid.checkEnName']}",
			"checkMobile": "${LANG['valid.checkPhone']}",
			"userEmail": "${LANG['valid.checkEmail']}",
			"equalTo": "${LANG['valid.password.confirm']}"
		}
	};
	$("#profileForm :input").tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
	$.validator.addMethod("notRequired", function(value, element) {
		if(value==null || value=="" || value.length==0){
			$(element).tipsy('hide').removeAttr('original-title');
		}
    	return true;
 	}, "");
	$.validator.addMethod("checkLoginName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_]{4,16}$/.test(value);
 	}, ruleString.custom.loginName);
	
	$.validator.addMethod("checkUserName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s\u4e00-\u9fa5]{1,32}$/.test(value);
 	}, ruleString.custom.checkUserName);
		
	$.validator.addMethod("checkEnName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s]{1,32}$/.test(value);
 	}, ruleString.custom.checkEnName);

	$.validator.addMethod("checkMobile", function(value, element) {       
		return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86\-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86\-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
	}, ruleString.custom.checkMobile);
	
	profileForm = $("#profileForm").validate({
		onkeyup: false,
		errorClass: "warning",
		rules: {
            'loginName' : {required:true, rangelength: [4, 16], checkLoginName:true},
            'trueName' : {required:true, rangelength: [1, 32], checkUserName:true},
            'userEmail' : {required:true, rangelength: [6, 64], email: true},
            'enName' : {notRequired:true, rangelength:[1, 32], checkEnName:true},
            'mobile' : {notRequired:true, rangelength:[4, 32], checkMobile:true}
        },
        messages: {
            'loginName' : {required:ruleString.required.loginName, rangelength: ruleString.rangelength.loginName},
            'trueName':  {required:ruleString.required.trueName, rangelength: ruleString.rangelength.trueName},
            'enName' : {rangelength: ruleString.rangelength.enName},
            'oldpassword' : {required:ruleString.required.oldpassword, rangelength: ruleString.rangelength.password},
            'loginPass' : {required:ruleString.required.password, rangelength: ruleString.rangelength.password},
            'loginPass2' : {required:ruleString.required.confirmpass, rangelength: ruleString.rangelength.password, equalTo: ruleString.custom.equalTo},
            'userEmail' : {required:ruleString.required.userEmail, rangelength: ruleString.rangelength.userEmail, email: ruleString.custom.userEmail},
            'mobile' : {rangelength:ruleString.rangelength.mobile}
        },
        success: function (label) {
            $(label).each(function () {
                $('#' + this.htmlFor).tipsy('hide').removeAttr('original-title');
            });
        },
        errorPlacement: function (error, element) {
        	var errorEl = $(".tipsy");
        	var errorText = error.text();
        	if (!errorEl || errorEl.length==0) {
	            element.attr('original-title', errorText);
                element.tipsy('show');	
        	} else {
        		//for update first tip div title
	        	var elTitle = element.attr('original-title');
	        	if (elTitle && elTitle.length>0 && elTitle!=errorText) {
	        		element.attr('original-title', error.text());
	                element.tipsy('show');	
	        	}
        	}
        }
	});	
});

function saveInfo() {
	if(profileForm.form()) {
		document.getElementsByName("profileForm")[0].submit();	
	}
}

$(document).keydown(function(e){ 
	var doPrevent; 
	if (e.keyCode == 8) { 
		var d = e.srcElement || e.target; 
		if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
			doPrevent = d.readOnly || d.disabled; 
		}else{
			doPrevent = true; 
		}
	}else{
		doPrevent = false; 
	}
	if (doPrevent){
		e.preventDefault(); 
	} 
}); 
</script>
</head>
<body>
<div class="main_content">
<form name="profileForm" id="profileForm" action="/user/profile/setupInfo" method="post">
  <div class="intercalate_main_top">
	<h3>${LANG['bizconf.jsp.profile.res1']}</h3>
    <p>${LANG['bizconf.jsp.profile.res2']}</p>
  </div>
  <table class="Personal_settings_main" cellpadding="0" cellspacing="0" border="0" >
  	<tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.login.res1']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " id="loginName" name="loginName" type="text" <c:if test="${(currentUser.userRole eq 1) && (siteBase.chargeMode==1 || siteBase.chargeMode==2)}">readonly  style='border:0px;'</c:if> value="${currentUser.loginName}"/>
        </td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res3000']}</strong></td>
    </tr>
    <tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.add_contacts.res7']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " name="trueName" id="trueName" type="text" value="${currentUser.trueName}"/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res5000']}</strong></td>
    </tr>
    <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.add_contacts.res8']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " name="enName" id="enName" type="text" value="${currentUser.enName}"/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res7fix1']}</strong></td>
    </tr>
    <tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.profile.res8']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " type="password" name="oldpassword" id="oldpassword" value=""/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res9']}</strong></td>
    </tr>
    <tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.profile.res10']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " name="loginPass" id="loginPass" type="password" value=""/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res11']}</strong></td>
    </tr>
    <tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.profile.res12']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " type="password" name="loginPass2" id="loginPass2"/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res11']}</strong></td>
    </tr>
     <tr height="40">
    	<td align="right" width="160"><span>*</span>${LANG['bizconf.jsp.add_contacts.res9']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " type="text" name="userEmail" id="userEmail" value="${currentUser.userEmail}"/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res13']}user@bizconf.cn</strong></td>
    </tr>
     <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conflogs.res4']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings " type="text" name="mobile" id="mobile" value="${currentUser.mobile}"/></td>
        <td align="left"><strong>${LANG['bizconf.jsp.profile.res14']}+86 010-88888888${LANG['bizconf.jsp.profile.res15']}</strong></td>
    </tr>
    <tr>
    	<td></td>
        <td>
        <div style="margin-top: 20px;">
        <a class="Personal_settings_main_hold" style="float:left;margin-top:0px;" href="javascript:;" onclick="saveInfo()">
        	<img src="/static/images/ys_r_bg.png" width="16" height="14" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.conf_default_setup.res45']}</a>
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
        </td>

        <td></td>
    </tr>
    <tr height="40">
    	<td></td>
        <td>
			&nbsp;
        </td>
        <td></td>
    </tr>
  </table>
</form>
<script type="text/javascript">
$(function() {
	$("#oldpassword").val("");
	$("#loginPass").val("");
	$("#loginPass2").val("");
	
	$("#loginPass").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$("#oldpassword").rules("add", { required: true, rangelength: [6, 16]});
			$(this).rules("add", { required: true, rangelength: [6, 16]});
			$("#loginPass2").rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").tipsy('hide').removeAttr('original-title');
			$(this).rules("remove");
			$("#oldpassword").rules("remove");
			$("#loginPass2").rules("remove");
		}
	});
	
	$("#oldpassword").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$("#loginPass").rules("add", { required: true, rangelength: [6, 16]});
			$(this).rules("add", { required: true, rangelength: [6, 16]});
			$("#loginPass2").rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
		} else {
			$("#oldpassword").tipsy('hide').removeAttr('original-title');
			$(this).rules("remove");
			$("#loginPass").rules("remove");
			$("#loginPass2").rules("remove");
		}
	});
	
	$("#loginPass2").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$(this).rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
			$("#loginPass").rules("add", { required: true, rangelength: [6, 16]});
			$("#oldpassword").rules("add", { required: true, rangelength: [6, 16]});
		} else {
			$("#loginPass2").tipsy('hide').removeAttr('original-title');
			$(this).rules("remove");
			$("#loginPass").rules("remove");
			$("#loginPass2").rules("remove");
		}
	});
});
</script>
</body>
</html>
