<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/m_genggaimima.css"/>

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<script type="text/javascript">
	$(document).ready(function(){
		$('#passwordResetForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"lp": "${LANG['bizconf.jsp.admin.password_reset.res1']}",
					"clp": "${LANG['bizconf.jsp.admin.password_reset.res2']}",
					"authCode": "${LANG['bizconf.jsp.admin.password_forget.res2']}"
				},
				rangelength: {
					"lp": "${LANG['bizconf.jsp.admin.login.res4fix2']}",
					"authCode": "${LANG['bizconf.jsp.admin.login.res4']}"
				},
				custom: {
					"equalTo": "${LANG['bizconf.jsp.admin.password_reset.res3']}"
				}
		};
		var v = $("#passwordResetForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				resetPass();
			},
			rules: {
	            'lp' : {required:true, rangelength: [6, 16]},
	            'clp' : {required:true, rangelength: [6, 16], equalTo: '#lp'},
	            'authCode' : {required:true, rangelength: [4, 4]}
	        },
	        messages: {
	            'lp' : {required: ruleString.required.lp, rangelength: ruleString.rangelength.lp},
	            'clp' : {required: ruleString.required.clp, rangelength: ruleString.rangelength.lp, equalTo:ruleString.custom.equalTo},
	            'authCode' : {required: ruleString.required.authCode, rangelength: ruleString.rangelength.authCode}
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
<title>${LANG['bizconf.jsp.admin.password_forget.res5']}-${LANG['bizconf.jsp.admin.index.res7']}</title>
</head>

<body>
<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res2']}-->
<jsp:include page="header.jsp" />    
<!--${LANG['bizconf.jsp.admin.password_forget.res7']}-->     

<div class="main">
<form action="/system/password/save" name="passwordResetForm" id="passwordResetForm" method="post">
	<input type="hidden" name="type" id="type" value="resetpass"/>
	<input type="hidden" name="random" id="random" value=""/>
	<input type="hidden" name="sid" id="sid" value="${sid}"/>
	<input type="hidden" name="auth" id="auth" value="${auth}"/>
	<input type="hidden" name="ts" id="ts" value="${ts}"/>
	<input type="hidden" name="ln" id="ln" value="${ln}"/>
  <h3>${LANG['bizconf.jsp.admin.index.res7']}</h3>
  
	<ul>
    	<li><span style="width: 120px;">${LANG['bizconf.jsp.admin.password_reset.res4']}：</span><input id="lp" name="lp" class="text01" type="password" /></li>
    	<li><span style="width: 120px;">${LANG['bizconf.jsp.admin.password_reset.res5']}</span><input id="clp" name="clp" class="text01" type="password" /></li>
    	<li><span style="width: 120px;">${LANG['bizconf.jsp.admin.password_forget.res11']}</span><input id="authCode" name="authCode" type="text" class="text02 " style="margin-right: 15px;"/>
                    <img id="authCodeImg" class="yanzhengma" alt="${LANG['bizconf.jsp.admin.login.res10']}" src=""  width="0" height="0" style="border: 1px solid #bbbbbb;margin-left: 0px;" onclick="randomImg()"/>
                    &nbsp;&nbsp;<a class="change" href="javascript:;" onclick="randomImg()">${LANG['bizconf.jsp.admin.password_forget.res12']}</a>
   	    </li>
	</ul>
	<div style="clear: both;"></div>
	<input name="button01" class="button01" type="submit" value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}" style="margin-left: 240px;"/>
    <input name="button02" class="button02" type="button" value="${LANG['bizconf.jsp.admin.createOrg.res4']}" onclick="javascript:back();" />
</form>  
</div>      

 <!--${LANG['bizconf.jsp.admin.password_forget.res13']}-->       
<jsp:include page="footer.jsp" />  
</body>
</html>


<script type="text/javascript">

function resetPass() {
	var data = {};
	data.ts = $("#ts").val();
	data.sid = $("#sid").val();
	data.auth = $("#auth").val();
	data.authCode = $("#authCode").val();
	data.type = $("#type").val();
	data.random = $("#random").val();
	data.ln = $("#ln").val();	
	data.lp = $("#lp").val();
	data.clp = $("#clp").val();
	app.resetAdminPass(data, function(result) {
		if (result && result.status==2) {
			successDialog("${LANG['bizconf.jsp.admin.password_reset.res6']}", function() {
				//window.location.href = "/system/login";
			});
		} else {
			errorDialog(result.message);
		}

	});
	
}
function successDialog(message, callback) {
	$("<div/>").successDialog({
		"title": "${LANG['system.tip']}",
		"dialogClass" : "ui-dialog-smile",
		"message" : message,
		"actions": ["${LANG['system.close']}"],
		"callback": callback
	});
}
function errorDialog(message) {
	$("<div/>").errorDialog({
		"title": "${LANG['system.tip']}",
		"dialogClass" : "ui-dialog-smile",
		"message" : message,
		"actions": ["${LANG['system.close']}"]
	});
}
function randomImg() {
	var random = new Date().getTime();
	$("#random").val(random);
	$("#authCodeImg").attr("src", "/valid/image?type=syslogin&random="+random);
}
function back(){
	window.history.back(-1);
}
$(function() {
	randomImg();
	$("#authCodeImg").width(66);
	$("#authCodeImg").height(22);
	
});
</script>
