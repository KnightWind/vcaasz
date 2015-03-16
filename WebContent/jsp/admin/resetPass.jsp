<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.admin.index.res7']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Css -->	
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />	
<!-- Javascript -->
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#resetPassForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"oldPass": "${LANG['bizconf.jsp.admin.resetPass.res1']}",
					"newPass": "${LANG['bizconf.jsp.admin.resetPass.res2']}",
					"passConfrim": "${LANG['bizconf.jsp.admin.password_reset.res2']}"
				},
				rangelength: {
					"pass": "${LANG['bizconf.jsp.admin.login.res4fix2']}"
				},
				custom: {
					"equalTo": "${LANG['bizconf.jsp.admin.resetPass.res3']}"
				}
		};
		var v = $("#resetPassForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				resetPass();
			},
			rules: {
	            'orgPass' : {required:true, rangelength: [6, 16]},
	            'loginPass' : {required:true, rangelength: [6, 16]},
	            'loginPassConfirm' : {required:true, rangelength: [6, 16], equalTo: '#loginPass'}
	        },
	        messages: {
	            'orgPass' : {required: ruleString.required.oldPass, rangelength: ruleString.rangelength.pass},
	            'loginPass' : {required: ruleString.required.newPass, rangelength: ruleString.rangelength.pass},
	            'loginPassConfirm': {required: ruleString.required.passConfrim, rangelength: ruleString.rangelength.pass, equalTo: ruleString.custom.equalTo}
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
<body onload="loaded()">
<form name="resetPassForm" id="resetPassForm" action="" method="post">
<div class="First_Steps_quick_a" style=" background:#FFF;">
          <div style="height: 30px">
          	<p class="tit_b">${LANG['bizconf.jsp.admin.resetPass.res4']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <table class="box_a_quick02">
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.admin.resetPass.res5']}</td>
                <td align="left">
                  <input class="right_text_a" id="orgPass" name="orgPass" type="password" value="" />
                </td>
              </tr>
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.admin.resetPass.res6']}</td>
                <td align="left">
                  <input class="right_text_a" id="loginPass" name="loginPass" type="password" />
                </td>
              </tr>
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.admin.resetPass.res7']}</td>
                <td align="left">
                  <input class="right_text_a" id="loginPassConfirm" name="loginPassConfirm" type="password" value="" />
                </td>
              </tr>
              <tr align="left">
            <td align="left" colspan="3">  
            <div class="but150" style=" margin-bottom:70px;">
            	<input class="form-button" value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}" type="submit">
            </div>
            </td>
            </tr>
            </table>
          </div>
          </div> 
  </form>
</body>
</html>
<script type="text/javascript">
function resetPass(){
	var data = {};
	data.orgPass = $("#orgPass").val();
	data.loginPass = $("#loginPass").val();
	app.forceResetAdminPass(data, function(result) {
		if(result){
			if(result.status=="1"){
				parent.successDialog(result.message);
				closeDialog();
			} else {
				closeDialog(result);
			}
		}
	}, {message:"${LANG['bizconf.jsp.admin.resetPass.res8']}...", ui:parent});	
}

function closeDialog(result){
	var frame = parent.$("#resetPass");
	if(result){
		frame.trigger("closeDialog", [result]);
	} else {
		frame.trigger("closeDialog");	
	}
}
function loaded(){
	var frame = parent.$("#resetPass");
	frame.trigger("loaded");
}
</script>
