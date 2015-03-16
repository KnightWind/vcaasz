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
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/m_wangjimima.css"/>

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<script type="text/javascript">
	$(document).ready(function(){
		$('#passwordForgetForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"email": "${LANG['bizconf.jsp.admin.password_forget.res1']}",
					"authCode": "${LANG['bizconf.jsp.admin.password_forget.res2']}"
				},
				rangelength: {
					"email": "${LANG['bizconf.jsp.admin.login.res4fix1']}",
					"authCode": "${LANG['bizconf.jsp.admin.login.res4']}"
				},
				custom: {
					"email": "${LANG['bizconf.jsp.admin.password_forget.res3']}"
				}
		};
		var v = $("#passwordForgetForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				sendEmail();
			},
			rules: {
	            'systemEmail' : {required:true, rangelength: [6, 32], email: true},
	            'authCode' : {required:true, rangelength: [4, 4]}
	        },
	        messages: {
	            'systemEmail' : {required: ruleString.required.email, rangelength: ruleString.rangelength.email, email:ruleString.custom.email},
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
<title>${LANG['bizconf.jsp.admin.password_forget.res5']}-${LANG['bizconf.jsp.admin.password_forget.res6']}</title>
</head>

<body>

<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res2']}-->
<jsp:include page="header.jsp" />
<!--${LANG['bizconf.jsp.admin.password_forget.res7']}-->     
<form action="/system/password/sendEmail" name="passwordForgetForm" id="passwordForgetForm" method="post">
<div class="main">
  <h3>${LANG['bizconf.jsp.admin.login.res12']}</h3>
  <p>*  ${LANG['bizconf.jsp.admin.password_forget.res8fix']}</p>
	<input type="hidden" name="random" id="random" value=""/>
	<input type="hidden" name="type" id="type" value="forgetpass"/>
	<ul>
    	<li><span style="width: 100px;">${LANG['bizconf.jsp.admin.password_forget.res10']}</span><input id="systemEmail" name="systemEmail" class="text01" type="text" /></li>
    	<li><span style="width: 100px;">${LANG['bizconf.jsp.admin.password_forget.res11']}</span><input id="authCode" name="authCode" type="text" id="authCode" maxlength="4" class="text02"/>
                    <img id="authCodeImg" class="yanzhengma" alt="${LANG['bizconf.jsp.admin.login.res10']}" src=""  width="0" height="0" style="border: 1px solid #bbbbbb;" onclick="randomImg()"/>
                    &nbsp;&nbsp;&nbsp;
                    <a class="change" href="javascript:;" onclick="randomImg()">${LANG['bizconf.jsp.admin.password_forget.res12']}</a>
   	    </li>
	</ul>
	<div style="clear: both;"></div>
	<input name="button01" class="button01" type="submit" value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}" style="margin-left: 220px;" />
    <input name="button02" class="button02" type="button" value="${LANG['bizconf.jsp.admin.arrange_org_user.res11']}" onclick="javascript:back();" />
</div>        
    </form>   
    <!--${LANG['bizconf.jsp.admin.password_forget.res13']}-->       
<jsp:include page="footer.jsp" />      


</body>
</html>


<script type="text/javascript">


function randomImg() {
	var random = new Date().getTime();
	$("#random").val(random);
	$("#authCodeImg").attr("src", "/valid/image?type=forgetpass&random="+random);
}

$(function() {
	randomImg();
	$("#authCodeImg").width(66);
	$("#authCodeImg").height(22);
	
});

function sendEmail(){
	var authCode = $("#authCode").val();
	var type = $("#type").val();
	var random = $("#random").val();
	var systemEmail = $("#systemEmail").val();
	app.forgotAdminPass(authCode, type, random, systemEmail, function(result) {
		randomImg();
		if (result && result.status==2) {
			successDialog("${LANG['bizconf.jsp.admin.password_forget.res14']}", function() {
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
function back(){
	window.history.back(-1);
//	$("#passwordForgetForm").submit();
}
</script>
