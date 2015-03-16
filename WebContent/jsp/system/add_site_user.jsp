<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${LANG['bizconf.jsp.system.add_site_user.res1']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/Popup.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	<script type="text/javascript">
	$(document).ready(function(){
		var outCallFlag = "${siteConfig.outCallFlag}";
		$("input[name='outCallFlag']").attr("disabled", "disabled");
		var checked = $("input[name='phoneFlag']").attr("checked");
		if(checked=="checked" && outCallFlag != 0){
			$("input[name='outCallFlag']").removeAttr("disabled");
		}
		$("input[name='phoneFlag']").change(function() {
			var value = $(this).val();
			var checked = $(this).attr("checked");
			if(checked=="checked" && outCallFlag != 0){
				$("input[name='outCallFlag']").removeAttr("disabled");
			} else {
				$("input[name='outCallFlag']").attr("disabled", "disabled");
				$("input[name='outCallFlag']").removeAttr("checked", "checked");
			}
			$.uniform.update();
		});
		
		$('#saveUserForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"loginName": "${LANG['system.sysUser.loginName.input']}",
					"trueName": "${LANG['system.sysUser.trueName.input']}",
					"loginPass": "${LANG['system.sysUser.loginPass.input']}",
					"confirmPass": "${LANG['system.user.confirmPass.input']}",
					"enName": "${LANG['system.sysUser.enName.input']}",
					"email": "${LANG['system.sysUser.email.input']}"
				},
				maxlength: {
					"remark": "${LANG['system.sysUser.remark.maxlength']}"
				},
				rangelength: {
					"loginName": "${LANG['system.sysUser.loginName.rangelength']}",
					"trueName": "${LANG['system.sysUser.trueName.rangelength']}",
					"loginPass": "${LANG['system.sysUser.loginPass.rangelength']}",
					"enname": "${LANG['system.sysUser.enname.rangelength']}",
					"email": "${LANG['system.sysUser.email.rangelength']}",
					"mobile": "${LANG['system.sysUser.mobile.rangelength']}"
				},
				custom: {
					"equalTo": "${LANG['system.user.confirmpass.custom']}",
					"email": "${LANG['system.sysUser.email.custom']}",
					"mobile": "${LANG['system.sysUser.mobile.custom']}",
					"checkLoginName": "${LANG['system.sysUser.checkLoginName.custom']}",
					"checkUserName": "${LANG['system.sysUser.checkUserName.custom']}",
					"checkEnName":"${LANG['system.sysUser.checkEnName.custom']}"
				}
		};
		$.validator.addMethod("notRequired", function(value, element) {
			if(value==null || value=="" || value.length==0){
				$(element).tipsy('hide').removeAttr('original-title');
			}
	    	return true;
	 	}, "");
		$.validator.addMethod("noSpace", function(value, element) {
			return value=="" || (value != ""&&value.indexOf(" ") < 0 );
	 	}, "${LANG['bizconf.jsp.admin.add_site_user.res2']}");
		$.validator.addMethod("checkLoginName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_]{4,16}$/.test(value);
	 	}, ruleString.custom.checkLoginName);
		$.validator.addMethod("checkUserName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&]{1,32}$/.test(value);
	 	}, ruleString.custom.checkUserName);	
		$.validator.addMethod("checkEnName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&]{1,32}$/.test(value);
	 	}, ruleString.custom.checkEnName);
		/**
			${LANG['bizconf.jsp.admin.add_site_user.res3']}  ${LANG['bizconf.jsp.admin.add_site_user.res4']} 1 ${LANG['bizconf.jsp.admin.add_site_user.res5']} 3${LANG['bizconf.jsp.admin.add_site_user.res6']}5 ${LANG['bizconf.jsp.admin.add_site_user.res7']} 8
			13211111111, 015111111111, +8615811111111, +86015811111111, (+86)13111111111
			${LANG['bizconf.jsp.admin.add_site_user.res8']} ${LANG['bizconf.jsp.admin.add_site_user.res9']}-${LANG['bizconf.jsp.admin.add_site_user.res10']}-${LANG['bizconf.jsp.admin.add_site_user.res11']}
			1334567890, 031-3145678-123, 010-11111111, (+86)010-13901691-123
		*/
		$.validator.addMethod("checkMobile", function(value, element) {       
	    	return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
	 	}, ruleString.custom.checkMobile);
		var v = $("#saveUserForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				saveSysUser();
			},
			rules: {
	            'loginName' : {required:true, rangelength: [4, 16], checkLoginName:true},
	            'trueName' : {required:true, rangelength: [1, 32], checkUserName:true},
	            'enName' : {required:true, rangelength:[1, 32], checkEnName:true},
	            'userEmail' : {required:true, rangelength:[6, 32], email: true},
	            'mobile' : {notRequired:true, rangelength:[4, 32], checkMobile: true},
	            'remark' : {notRequired:true, maxlength: 256},
	        	'licNum':{required:true,digits:true,min:1,max:100}
			},
	        messages: {
	            'loginName' : {required:ruleString.required.loginName, rangelength: ruleString.rangelength.loginName},
	            'trueName' : {required:ruleString.required.trueName, rangelength: ruleString.rangelength.trueName},
	            'loginPass' : {required:ruleString.required.loginPass, rangelength: ruleString.rangelength.loginPass},
	            'confirmPass' : {required:ruleString.required.confirmPass, rangelength: ruleString.rangelength.loginPass, equalTo:ruleString.custom.equalTo},
	            'enName' : {required:ruleString.required.enName, rangelength: ruleString.rangelength.enName},
	            'userEmail' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email},
	            'mobile': {rangelength: ruleString.rangelength.mobile},
	            'remark': {maxlength: ruleString.maxlength.remark}
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
		
		$(".closeButton").click(function() {
			var frame = parent.$("#userDiv");
			frame.trigger("closeDialog");
		});

		//update passworld required to verify or disable
		var siteId = "${user.id}";
		if (siteId && siteId.length>0) {
			$("#loginPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16], equalTo: '#loginPass'});
		}
	});	
	
	function saveSysUser() {
		var user = {};
		user.siteId = "${user.siteId}";
		if("${siteId}"!=""){
			user.siteId = "${siteId}";		
		}
		user.loginName = $("#loginName").val();
		user.loginPass = $("#loginPass").val();
		user.trueName = $("#trueName").val();
		user.enName = $("#enName").val();
		user.userEmail = $("#userEmail").val();
		user.mobile = $("#mobile").val();
		user.remark = $("#remark").val();
		user.id = "${user.id}";
		if($("#licNum").length>0){
			user.licNum = $("#licNum").val();
		}
		
		user.phoneFlag = $("input[name=phoneFlag]:checked").length>0?1:0;
		user.outCallFlag = $("input[name=outCallFlag]:checked").length>0?1:0;
		user.shareMediaFlag = $("input[name=shareMediaFlag]:checked").length>0?1:0;
		user.recordFlag = $("input[name=recordFlag]:checked").length>0?1:0;
		
		app.saveHost(user, function(result) {
			if(parent.$("#hostManagerDiv").find("iframe")[0]) {
				parent.$("#hostManagerDiv").find("iframe")[0].src = "/system/lic/listHost?siteId="+user.siteId;	
			}
			var frame = parent.$("#hostDiv");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.save']}", ui:parent});	
	
	}
	</script>		
</head>
<body onload="loaded()">
<form id="saveUserForm" action="" method="post">
        		<input type="hidden" name="id" value="${user.id}"/>
<table border="0" cellpadding="0" cellspacing="0" class="host_main" style="margin-top: 25px;">
	<tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.arrange_org_user.res7']}</td>
        <td class="host_right"><input <c:if test="${not empty user}">readonly="readonly" style='border:0px;'</c:if> id="loginName" type="text" name="loginName" value="${user.loginName}"/></td>
    </tr>
    <tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.user_info.res3']}</td>
        <td class="host_right"><input id="loginPass" name="loginPass"  type="password" /></td>
    </tr>
    <tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.resetPass.res7']}</td>
        <td class="host_right"><input id="confirmPass" name="confirmPass"  type="password" /></td>
    </tr>
    <tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
        <td class="host_right"><input id="trueName" name="trueName"  type="text" value="${user.trueName}"></td>
    </tr>
    <tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.user_info.res2']}</td>
        <td class="host_right"><input id="enName" name="enName"  type="text" value="${user.enName}"></td>
    </tr>
    <!--  -->
    <c:if test="${empty user}">
	    <tr>
	    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.system.add_site_user.res2']}</td>
	        <td class="host_right"><input id="licNum" name="licNum"  type="text" ></td>
	    </tr>
    </c:if>
    <tr>
    	<td class="host_left" align="right">${LANG['bizconf.jsp.admin.edit_userbase.res6']}</td>
        <td class="host_checkbox">
        	<input name="phoneFlag" type="checkbox" value="1" <c:if test="${config.phoneFlag eq 1 and siteConfig.phoneFlag eq 1}">checked</c:if> value="1" name="phoneFlag"  <c:if test="${siteConfig.phoneFlag eq 0}">disabled="disabled"</c:if> />${LANG['bizconf.jsp.admin.conf_list.res9']}
        	<input type="checkbox" value="1" name="outCallFlag" id="outCallFlag" <c:if test="${config.outCallFlag eq 1 and siteConfig.outCallFlag eq 1}">checked</c:if>   <c:if test="${siteConfig.outCallFlag eq 0}">disabled="disabled"</c:if>/>${LANG['bizconf.jsp.admin.edit_userbase.res7']}
        	<input type="checkbox" value="1" name="shareMediaFlag" <c:if test="${config.shareMediaFlag eq 1 and siteConfig.shareMediaFlag eq 1}">checked</c:if>  <c:if test="${siteConfig.shareMediaFlag eq 0}">disabled="disabled"</c:if>/>${LANG['bizconf.jsp.admin.edit_userbase.res8']}
        	<input type="checkbox" value="1" name="recordFlag" <c:if test="${config.recordFlag eq 1 and siteConfig.recordFlag eq 1}">checked</c:if>  <c:if test="${siteConfig.recordFlag eq 0}">disabled="disabled"</c:if>/>${LANG['bizconf.jsp.admin.edit_userbase.res9']}
        </td>
    </tr>
    <tr>
    	<td class="host_left" align="right"><font color="red">*</font>${LANG['bizconf.jsp.admin.arrange_org_user.res9']}</td>
        <td class="host_right"><input id="userEmail" name="userEmail" type="text" value="${user.userEmail}" ></td>
    </tr>
    <tr>
    	<td class="host_left" align="right">${LANG['bizconf.jsp.admin.conf_list.res9']}</td>
        <td class="host_right"><input id="mobile" name="mobile"  type="text" value="${user.mobile}"></td>
    </tr>
    <tr>
    	<td class="host_left" align="right" valign="top" colspan="1" rowspan="3" ><span style=" padding-top:5px; display:block">${LANG['bizconf.jsp.admin.createOrg.res3']}</span></td>
        <td class="host_right"><textarea id="remark" name="remark"  cols="1" rows="3"  style=" width:330px; height:60px; margin-top:5px;border: 1px solid #CCCCCC ">${user.remark }</textarea></td>
    </tr>
</table>
<input type="submit" class="summit_btn Public_button" value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}"><a class="close_btn Public_button" href="#" onclick="closeDiaglog();">${LANG['bizconf.jsp.admin.createOrg.res4']}</a>
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#hostDiv");
	frame.trigger("loaded");
}

function closeDiaglog(result){
	var frame = parent.$("#hostDiv");
	frame.trigger("closeDialog", result);
}
</script>


