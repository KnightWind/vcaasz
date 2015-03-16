<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/static/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<title>${LANG['system.menu.config.host']}</title>
</head>
<body>
<div class="emile">
<div class="emile_01">
<form name="configForm" id="configForm" action="/system/email/savehost" method="post">
	<input type="hidden" name="id" id="id" value="${emailConfig.id}"/>
	<input type="hidden" name="siteId" id="siteId" value="${emailConfig.siteId}"/>
    	<div class="emile_01_top"><span> ${LANG['system.email.config.t1']}</span></div>
			<table class="form-table">
			  <tr>
			    <td align="right">
			      ${LANG['system.email.config.host.address']}
			    </td>
			    <td class="form-table-td">
			      <input name="emailHost" id="emailHost"  class="text-input" value="${emailConfig.emailHost}"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="right">
			      ${LANG['system.email.config.host.sender']}
			    </td>
			    <td class="form-table-td">
			      <input type="text" name="emailName" id="emailName" class="text-input" value="${emailConfig.emailName}"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="right">
			      ${LANG['system.email.config.host.sendemail']}
			    </td>
			    <td class="form-table-td">
			      <input type="text" name="emailSender" id="emailSender" class="text-input" value="${emailConfig.emailSender}"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="right">
			      ${LANG['system.email.config.confirmaccount']}
			    </td>
			    <td class="form-table-td">
			      <input type="text" name="emailAccount" id="emailAccount" class="text-input" value="${emailConfig.emailAccount}"/>
			    </td>
			  </tr>
			  <tr>
			    <td align="right">
			      ${LANG['system.email.config.host.confirmpass']}
			    </td>
			    <td class="form-table-td">
			      <input type="password" name="emailPassword" id="emailPassword" class="text-input" value="${emailConfig.emailPassword}"/>
			    </td>
			  </tr>
			</table>
			<div class="button_submit">
 				<input class="skipThese" name="emile_button" id="emile_button" type="submit"  value="${LANG['system.email.config.submit']}" />
 				<c:if test="${msgCode!=null && msgCode!='' && msgCode=='1'}">
            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
            		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
            	</c:if>
            	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='2'}">
            		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
            		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
            	</c:if>            	
 			</div>  
</form>
</div> 
     <div class="emile_02">     
     <div class="emile_02_top"><span> ${LANG['system.eventlog.type.300']}</span></div>
     <form name="checkForm" id="checkForm" action="/system/email/checkHost" method="post">
  		<table class="form-table" style="margin-left:115px">
		  <tr>
		    <td align="right" valign="top">
		    	${LANG['system.email.config.host.content']}
		    </td>
		    <td class="form-table-td">
		    	<textarea class="textarea-input"  name="emailBody" id="emailBody"  cols="" rows="" value="" >${LANG['system.email.test.content']}</textarea>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		    	${LANG['system.email.config.confirm.address']}
		    </td>
		    <td class="form-table-td">
		    	<input class="text-input"  type="text" name="toEmail" id="toEmail"  value="${user.email }"/>
		    </td>
		  </tr>
		</table>   	
		<div>
			<input class="skipThese" name="emile_button" id="emile_button" type="submit"  value="${LANG['system.email.config.send']}" />
           	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='3'}">
           		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
           		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
           	</c:if>
           	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='4'}">
           		<c:set var="emailMsgKey" value="system.email.msgcode.${msgCode}"/>
           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:#258021'>${LANG[emailMsgKey]}</label>
           	</c:if>
		</div>
     </form>
</div> 
 <div class="emile_02" userName="t1"></div>
 <div class="emile_03" userName="t2"></div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#configForm, #checkForm").find("input, textarea").not(".skipThese").uniform();
		$("#configForm :input").tipsy({ trigger: 'focus', fade: false, gravity: 'sw', opacity: 1 });
		$("#checkForm :input").tipsy({ trigger: 'focus', fade: false, gravity: 'sw', opacity: 1 });
		$.validator.addMethod("noSpace", function(value, element) {
			return value=="" || (value != ""&&value.indexOf(" ") < 0 );
	 	}, "${LANG['bizconf.jsp.admin.email_config.res1']}");
		var v = $("#configForm").validate({
			errorClass: "warning",
			rules: {
	            'emailHost' : {noSpace:true, required:true,rangelength:[1, 64]},
	            'emailName' : {required:true,rangelength:[1, 64]},
	            'emailSender' : {required:true, email: true,rangelength: [1, 64]},
	            'emailAccount' : {required:true,rangelength: [1, 64]},
	            'emailPassword' : {noSpace:true, required:true,rangelength: [1, 64]}
	        },

	        messages: {
	            'emailHost' : {noSpace:"${LANG['bizconf.jsp.admin.email_config.res2']}", required:"${LANG['system.email.config.host.warninfo2']}",rangelength:"${LANG['system.email.config.lengthinfo32']}"},
	            'emailName' : {required:"${LANG['system.email.config.host.warninfo3']}",rangelength:"${LANG['system.email.config.lengthinfo32']}"},
	            'emailSender' : {required:"${LANG['system.email.config.host.warninfo1']}", email: "${LANG['system.email.config.host.warninfo6']}",rangelength:"${LANG['system.email.config.lengthinfo64']}"},
	            'emailAccount' : {required:"${LANG['system.email.config.host.warninfo4']}",rangelength:"${LANG['system.email.config.lengthinfo32']}"},
	            'emailPassword' : {noSpace:"${LANG['bizconf.jsp.admin.email_config.res3']}", required:"${LANG['system.email.config.host.warninfo5']}",rangelength:"${LANG['system.email.config.lengthinfo32']}"}
	        
	        },
	        success: function (label) {
	            $(label).each(function () {
	                $('#' + this.htmlFor).tipsy('hide').removeAttr('title');
	            });
	        },
	        errorPlacement: function (error, element) {
	        	var errorEl = $(".tipsy");
	            element.attr('title', error.text());
	        	if (!errorEl) {
	                element.tipsy('show');	
	        	}
	        }
		});	
		
		var v2 = $("#checkForm").validate({
			errorClass: "warning",
			rules: {
	            'emailBody' : {required:true,rangelength: [1, 64]},
	            'toEmail' : {required:true, email: true,rangelength: [1, 64]}
	        },
	        messages: {
	            'emailBody' : {required:"${LANG['system.email.config.host.warninfo7']}",rangelength:"${LANG['system.email.config.lengthinfo32']}"},
	            'toEmail' : {required:" ${LANG['system.email.config.host.warninfo8']}", email: "${LANG['system.email.config.host.warninfo6']}",rangelength:"${LANG['system.email.config.lengthinfo64']}"}
	        },
	        success: function (label) {
	            $(label).each(function () {
	                $('#' + this.htmlFor).tipsy('hide').removeAttr('title');
	            });
	        },
	        errorPlacement: function (error, element) {
	        	var errorEl = $(".tipsy");
	            element.attr('title', error.text());
	        	if (!errorEl) {
	                element.tipsy('show');	
	        	}
	        }
		});	
		
	});
	</script>
</body>
</html>
