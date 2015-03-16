<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/static/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link type="text/css" rel="stylesheet" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/login.css"/>
<link rel="stylesheet" type="text/css" href="/static/css/style.css" />
<style type="text/css">
*html input {
	border: expression((this.type=="text" || this.type=="password")?"0px":"");
	padding: 0px;
}
</style>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>

<c:if test="${!empty errorMessage}">
<script type="text/javascript">
$(function() {
	errorDialog("${errorMessage}");
});
</script>
</c:if>
<c:if test="${!empty infoMessage}">
<script type="text/javascript">
successDialog("${infoMessage}");
</script>
</c:if>

<script type="text/javascript">
$(function() {
	reloadPage();
	$("#authCodeImg").width(66);
	$("#authCodeImg").height(22);
	randomImg();
	$('#sysLoginForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
    $('#sysLoginForm').validate({
    	onkeyup: false,
        rules: {
            'loginName' : {required:true},
            'loginPass' : {required:true},
            'authCode'  : {required:true, rangelength: [4, 4]}
        },
        messages: {
            'loginName' : {required:'${LANG['bizconf.jsp.admin.login.res1']}'},
            'loginPass' : {required:'${LANG['bizconf.jsp.admin.login.res2']}'},
            'authCode'  : {required:'${LANG['bizconf.jsp.admin.login.res3']}', rangelength: "${LANG['bizconf.jsp.admin.login.res4000']}"}
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
function reloadPage() {
    if (window.top!=window.self) {
    	top.location.href = location.href;
    }
}
function randomImg() {
	var random = new Date().getTime();
	$("#random").val(random);
	$("#authCodeImg").attr("src", "/valid/image?type=syslogin&random="+random);
}

function errorDialog(message) {
	$("<div/>").errorDialog({
		"title": "${LANG['system.tip']}",
		"dialogClass" : "ui-dialog-smile",
		"message" : message,
		"actions": ["${LANG['system.close']}"]
	});
}
function successDialog(message) {
	$("<div/>").successDialog({
		"title": "${LANG['system.tip']}",
		"dialogClass" : "ui-dialog-smile",
		"message" : message,
		"actions": ["${LANG['system.close']}"]
	});
}
</script>
<title>${LANG['bizconf.jsp.admin.login.res6']}</title>
</head>

<body>
<form name="sysLoginForm" id="sysLoginForm" action="/system/login/check" method="post">
	<input type="hidden" name="random" id="random" value=""/>
	<input type="hidden" name="type" id="type" value="syslogin"/>
	<div class="login_main" align="center">
    	<div class="main_top">
<%--         <img class="logo" src="${baseUrlStatic}/images/login_logo.gif" width="94" height="44" /> --%>
	<c:choose>
		<c:when test="${cslogin }">
	        <span class="logo-label" style="width: 400px;text-align: center;overflow: hidden;white-space:nowrap; ">${LANG['website.system.title.tip1']}</span>
		</c:when>
		<c:otherwise>
	        <span class="logo-label" style="width: 400px;text-align: center;overflow: hidden;white-space:nowrap; ">${LANG['website.system.title.tip2']}</span>
		</c:otherwise>
	</c:choose>
        </div>        
    	<div class="main_bottom">
       	    <div class="m_left"><img class="ico01" src="${baseUrlStatic}/images/ico13.gif" width="107" height="97" /></div>
        	<div class="m_right">
            	<div class="m_right01"><span>${LANG['bizconf.jsp.admin.arrange_org_user.res7']}</span><input name="loginName" id="loginName" type="text"/></div>
                <div class="m_right02"><span>${LANG['bizconf.jsp.admin.login.res7']}&nbsp;&nbsp;${LANG['bizconf.jsp.admin.login.res8']}</span><input name="loginPass" id="loginPass" type="password"/></div>
                <div class="m_right03">
                	<span>${LANG['bizconf.jsp.admin.login.res9']}</span>
                    <input name="authCode" type="text" id="authCode"/>
                    <img id="authCodeImg" class="yanzheng" alt="${LANG['bizconf.jsp.admin.login.res10']}" src=""  width="0" height="0" onclick="randomImg()"/>
                    <a class="code_link" href="javascript:;" onclick="randomImg()">${LANG['bizconf.jsp.admin.login.res11']}</a>
                </div>
               
             <div class="m_right04">
             <input type="submit" class="denglu" name="loginBtn" value="${LANG['bizconf.jsp.admin.login.res6']}"/>
             <a class="wangji" href="/system/password/forget">${LANG['bizconf.jsp.admin.login.res12']}</a>             
             </div> 
             </div>
 	    </div>
       </div>
</form>
</body>
</html>
