<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${LANG['system.sysUser.list.system.user.title']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#sysUserForm").find("input, select, textarea").not(".skipThese").uniform();
		$('#sysUserForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
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
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s\u4e00-\u9fa5]{1,32}$/.test(value);
	 	}, ruleString.custom.checkUserName);	
		$.validator.addMethod("checkEnName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s]{1,32}$/.test(value);
	 	}, ruleString.custom.checkEnName);
		$.validator.addMethod("checkMobile", function(value, element) {       
	    	return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
	 	}, ruleString.custom.checkMobile);
		
		var v = $("#sysUserForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				saveSysUser();
			},
			rules: {
	            'loginName' : {required:true, rangelength: [4, 16], checkLoginName:true},
	            'trueName' : {required:true, rangelength: [1, 32], checkUserName:true},
	            'enName' : {notRequired:true, rangelength:[1, 32], checkEnName:true},
	            'email' : {required:true, rangelength:[6, 64], email: true},
	            'mobile' : {notRequired:true, rangelength:[4, 32], checkMobile: true},
	            'remark' : {notRequired:true, maxlength: 256}
	        },
	        messages: {
	            'loginName' : {required:ruleString.required.loginName, rangelength: ruleString.rangelength.loginName},
	            'trueName' : {required:ruleString.required.trueName, rangelength: ruleString.rangelength.trueName},
	            'loginPass' : {required:ruleString.required.loginPass, rangelength: ruleString.rangelength.loginPass},
	            'confirmPass' : {required:ruleString.required.confirmPass, rangelength: ruleString.rangelength.loginPass, equalTo:ruleString.custom.equalTo},
	            'enName' : {required:ruleString.required.enName, rangelength: ruleString.rangelength.enName},
	            'email' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email},
	            'mobile': {rangelength: ruleString.rangelength.mobile, checkMobile: ruleString.custom.mobile},
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
		//${LANG['bizconf.jsp.system.createSystemUser.res1']}
		var siteId = "${systemUser.id}";
		if (siteId) {
			$("#loginPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16], equalTo: '#loginPass'});
		}
	
	});	
	
	function saveSysUser() {
		var sysUserId = "${systemUser.id}";
		var sysUser = {};
		sysUser.loginName = $("#loginName").val();
		sysUser.loginPass = $("#loginPass").val();
		sysUser.trueName = $("#trueName").val();
		sysUser.enName = $("#enName").val();
		sysUser.sysType = $("#sysType").val();
		sysUser.email = $("#email").val();
		sysUser.mobile = $("#mobile").val();
		sysUser.remark = $("#remark").val();
		if (sysUserId && sysUserId.length>0) {
			sysUser.id = sysUserId;
			app.updateSysUser(sysUser, function(result) {
				var frame = parent.$("#userDiv");
				frame.trigger("closeDialog", [result]);
			}, {message:"${LANG['system.site.change']}", ui:parent});
		} else {
			app.createSysUser(sysUser, function(result) {
				var frame = parent.$("#userDiv");
				frame.trigger("closeDialog", [result]);
			}, {message:"${LANG['system.site.save']}", ui:parent});	
		}
	}
	</script>
</head>
<body onload="loaded()">
<form id="sysUserForm" name="sysUserForm" action="" method="post">
	<table class="table_create_system_user">
	  <tr>
	    <td align="right">
	      <label class='red_star'>*</label>${LANG['system.sysUser.list.loginName']}
	    </td>
	    <td class="table-td">
	      <input id="loginName" name="loginName" class="create_system_user_input" type="text" value="${systemUser.loginName}"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">
	      <label class='red_star'>*</label>${LANG['system.sysUser.list.loginPass']}
	    </td>
	    <td class="table-td">
	      <input id="loginPass" name="loginPass" class="create_system_user_input" type="password"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">
	      <label class='red_star'>*</label>${LANG['system.user.confirmPass']}
	    </td>
	    <td class="table-td">
	      <input id="confirmPass" name="confirmPass" class="create_system_user_input" type="password"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">
	      <label class='red_star'>*</label>${LANG['system.sysUser.list.userName']}
	    </td>
	    <td class="table-td">
	      <input id="trueName" name="trueName" class="create_system_user_input" type="text" value="${systemUser.trueName}"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">
	      ${LANG['system.sysUser.list.enName']}
	    </td>
	    <td class="table-td">
	      <input id="enName" name="enName" class="create_system_user_input" type="text" value="${systemUser.enName}"/>
	    </td>
	  </tr>
	  <c:if test="${empty systemUser}">
		  <tr>
		    <td align="right">
		      <label class='red_star'>*</label>${LANG['bizconf.jsp.system.createSystemUser.res2']}
		    </td>
		    <td class="table-td">
		       <select id="sysType" name="sysType">
		       		<option value="999">${LANG['bizconf.jsp.system.createSystemUser.res3']}</option>
		       		<option value="6">${LANG['bizconf.jsp.system.createSystemUser.res4']}</option>
		       </select>
		    </td>
		  </tr>
	  </c:if>
	  <tr>
	    <td align="right">
	      <label class='red_star'>*</label>${LANG['system.sysUser.list.email']}
	    </td>
	    <td class="table-td">
	      <input id="email" name="email" class="create_system_user_input" type="text" value="${systemUser.email}"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right">
	      ${LANG['system.sysUser.list.telephone']}
	    </td>
	    <td class="table-td">
	      <input id="mobile" name="mobile" class="create_system_user_input" type="text" value="${systemUser.mobile}"/>
	    </td>
	  </tr>
	  <tr>
	    <td align="right" valign="top">
	      <div style="margin-top: 5px">${LANG['system.sysUser.list.remark']}</div>
	    </td>
	    <td class="table-td" style="height: 65px;">
	      <textarea id="remark" name="remark" style="width: 261px; height:50px;resize:none;">${systemUser.remark}</textarea>
	    </td>
	  </tr>
	</table>
	<div style="width: 100px; margin: 0px auto">
		<input id="submitForm" name="create_system_user_button" class="create_system_user_button skipThese" type="submit"  value="${LANG['system.submit']}" />
	</div> 
</form>	
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#userDiv");
	frame.trigger("loaded");
}
</script>
