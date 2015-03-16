<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.system.profile.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/static/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript">
$(function() {
	var ruleString = {
		required: {
			"username": "${LANG['system.user.trueName.input']}",
			"enname": "${LANG['system.user.enName.input']}",
			"orgpassword": "${LANG['system.admin.profile.msg3']}",
			"password": "${LANG['system.user.loginPass.input']}",
			"confirmpass": "${LANG['system.user.confirmPass.input']}",
			"email": "${LANG['system.user.email.input']}",
			"telephone": "${LANG['system.user.mobile.input']}"
		},
		rangelength: {
			"username": "${LANG['system.user.trueName.rangelength']}",
			"enname": "${LANG['system.user.enname.rangelength']}",
			"password": "${LANG['system.user.password.rangelength']}",
			"email": "${LANG['system.user.email.rangelength']}",
			"telephone": "${LANG['system.user.telephone.rangelength']}"
		},
		custom: {
			"checkUserName": "${LANG['system.user.trueName.custom']}",
			"checkEnName": "${LANG['system.user.enName.custom']}",
			"email": "${LANG['system.user.email.custom']}",
			"mobile": "${LANG['system.sysUser.mobile.custom']}",
			"equalTo": "${LANG['system.user.confirmpass.custom']}",
			"checkMobile": "${LANG['bizconf.jsp.admin.profile.res2']}"
		}
	};

	$("#profileForm :input").tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
	$("#profileForm").find("input").not(".skipThese").uniform();
	$.validator.addMethod("notRequired", function(value, element) {
		if(value==null || value=="" || value.length==0){
			$(element).tipsy('hide').removeAttr('original-title');
		}
    	return true;
 	}, ruleString.custom.checkLoginName);
	$.validator.addMethod("checkUserName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s\u4e00-\u9fa5]{1,32}$/.test(value);
 	}, ruleString.custom.checkUserName);	
	$.validator.addMethod("checkEnName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s]{1,32}$/.test(value);
 	}, ruleString.custom.checkEnName);
	$.validator.addMethod("checkMobile", function(value, element) {       
    	return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
 	}, ruleString.custom.checkMobile);
	
	var v = $("#profileForm").validate({
		onkeyup: false,
		errorClass: "warning",
		rules: {
            'trueName' : {required:true, rangelength: [1, 32], checkUserName:true},
            'enName' : {notRequired:true, rangelength: [1, 32], checkEnName:true},
            'email' : {required:true, rangelength: [6, 64], email: true},
            'telephone' : {notRequired:true, rangelength:[4, 32], checkMobile: true}
        },

        messages: {
            'trueName' : {required:ruleString.required.username, rangelength: ruleString.rangelength.username},
            'enName' : {required:ruleString.required.enname, rangelength: ruleString.rangelength.enName},
            'orgPass' : {required:ruleString.required.orgpassword, rangelength: ruleString.rangelength.password},
            'loginPass' : {required:ruleString.required.password, rangelength: ruleString.rangelength.password},
            'loginPass2' : {required:ruleString.required.confirmpass, rangelength: ruleString.rangelength.email, equalTo: ruleString.custom.equalTo},
            'email' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email},
            'telephone' : {rangelength:ruleString.rangelength.telephone, checkMobile: ruleString.custom.mobile}
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
</script>
</head>
<body>
<div class="emile">
<div class="emile_01">
<form name="profileForm" id="profileForm" action="/system/profile/submit" method="post">
	<input type="hidden" name="id" id="id" value="${emailConfig.id}"/>
	<input type="hidden" name="siteId" id="siteId" value="${emailConfig.siteId}"/>
    	<div class="emile_01_top"><span>${LANG['system.user.title']}</span></div>
    	<table class="form-table" style="margin-left: 112px;">
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['system.user.username']}
		    </td>
		    <td class="form-table-td">
		      <input name="trueName" id="trueName"  class="text-input" value="${currentSystemUser.trueName}"/>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      ${LANG['system.user.enname']}
		    </td>
		    <td class="form-table-td">
		      <input name="enName" id="enName"  class="text-input" value="${currentSystemUser.enName}"/>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['system.admin.profile.orgpass']}
		    </td>
		    <td class="form-table-td">
		      <input type="password" name="orgPass" id="orgPass" class="text-input" value=""/> (${LANG['system.user.password.keepempty']})
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['system.user.password']}
		    </td>
		    <td class="form-table-td">
		      <input type="password" name="loginPass" id="loginPass" class="text-input" value=""/> (${LANG['system.user.password.keepempty']})
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['system.user.confirmPass']}
		    </td>
		    <td class="form-table-td">
		      <input type="password" name="loginPass2" id="loginPass2" class="text-input" value=""/> (${LANG['system.user.password.keepempty']})
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['system.user.email']}</span>
		    </td>
		    <td class="form-table-td">
		      <input type="text" name="email" id="email" class="text-input" value="${currentSystemUser.email}"/>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		      ${LANG['system.user.telephone']}
		    </td>
		    <td class="form-table-td">
		      <input type="text" name="telephone" id="telephone" class="text-input" value="${currentSystemUser.telephone}"/>
		    </td>
		  </tr>
		</table>
		<div>
			<input class="skipThese" name="emile_button" id="emile_button" type="submit"  value="${LANG['system.email.config.submit']}" />
 			<c:if test="${!empty infoMessage}">
 				<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${infoMessage}</label>
 			</c:if>
			<c:if test="${!empty errorMessage}">
           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:red'>${errorMessage}</label>
           	</c:if>
		</div>
</form>
<script type="text/javascript">
$(function() {
	$("#orgPass").val("");
	$("#loginPass").val("");
	$("#loginPass2").val("");
	
	$("#orgPass").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$("#loginPass").rules("add", { required: true, rangelength: [6, 16]});
			$(this).rules("add", { required: true, rangelength: [6, 16]});
			$("#loginPass2").rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
		} else {
			$("#orgPass").tipsy('hide').removeAttr('original-title');
			$(this).rules("remove");
			$("#loginPass").rules("remove");
			$("#loginPass2").rules("remove");
		}
	});
	
	$("#loginPass").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$("#orgPass").rules("add", { required: true, rangelength: [6, 16]});
			$(this).rules("add", { required: true, rangelength: [6, 16]});
			$("#loginPass2").rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").tipsy('hide').removeAttr('original-title');
			$(this).rules("remove");
			$("#orgPass").rules("remove");
			$("#loginPass2").rules("remove");
		}
	});
	
	$("#loginPass2").change(function() {
		if($(this).val() && $(this).val().length>0) {
			$(this).rules("add", { required: true, rangelength: [6, 16], equalTo: '#loginPass'});
			$("#loginPass").rules("add", { required: true, rangelength: [6, 16]});
			$("#orgPass").rules("add", { required: true, rangelength: [6, 16]});
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
