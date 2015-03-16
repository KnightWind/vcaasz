<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${LANG['bizconf.jsp.index.res24']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Css -->	
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css?ver=${version}" />
<!-- Javascript -->
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
var resetPassForm = null;
	$(document).ready(function(){
		$('#resetPassForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"oldPass": "${LANG['valid.input.oldPass']}",
					"newPass": "${LANG['valid.input.loginPass']}",
					"passConfrim": "${LANG['valid.input.passConfirm']}"
				},
				rangelength: {
					"pass": "${LANG['valid.rangelength616']}"
				},
				custom: {
					"equalTo": "${LANG['valid.password.confirm']}"
				}
		};
		resetPassForm = $("#resetPassForm").validate({
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
	function checkForm() {
		if(resetPassForm.form()){
			resetPass();
		}
	}
	function enterKey(){
	    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.attendConf.res3']}   
	    if (event.keyCode == 13){       //${LANG['bizconf.jsp.attendConf.res4']}
	    	checkForm();
	    } 
	}

</script>
</head>
<body onload="loaded()">
<form name="resetPassForm" id="resetPassForm" action="/user/password/resetPass" method="post">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
        <div class="First_Steps_quick_a" style=" background:#FFF;">
          <div class="First_Steps_title_a">
            <h3 class="tit_a">${LANG['bizconf.jsp.index.res24']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.resetPass.res1']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <table class="box_a_quick02">
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.profile.res8']}</td>
                <td align="left">
                  <input class="right_text_a" id="orgPass" name="orgPass" type="password" value="" />
                </td>
              </tr>
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.profile.res10']}</td>
                <td align="left">
                  <input class="right_text_a" id="loginPass" name="loginPass" type="password" />
                </td>
              </tr>
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.profile.res12']}</td>
                <td align="left">
                  <input class="right_text_a" id="loginPassConfirm" name="loginPassConfirm" type="password" value="" onkeypress="enterKey();"/>
                </td>
              </tr>
              <tr align="left">
            <td align="left" colspan="3" style="height: 50px;">  
            <div class="but150" style=" margin-bottom:70px;"><span class="button_common">
            	<a href="javascript:;" onclick="javascript:checkForm();" ><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.add_group.res5']}</a> </span>
            </div>
            </td>
            </tr>
            </table>
          </div>
          </div> 
        
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>

<input type="hidden" id="noticeID"/>
</body>
</html>
<script type="text/javascript">
function resetPass(){
	var data = {};
	data.orgPass = $("#orgPass").val();
	data.loginPass = $("#loginPass").val();
	app.forceResetUserPass(data, function(result) {
		if(result){
			if(result.status=="1"){
				parent.successDialog(result.message);
				closeDialog();
			} else {
				parent.errorDialog(result.message);
			}
		}
	}, {message:"${LANG['bizconf.jsp.resetPass.res2']}...", ui:parent});	
}

function closeDialog(){
	var frame = parent.$("#resetPass");
	frame.trigger("closeDialog");
}
function loaded(){
	var frame = parent.$("#resetPass");
	frame.trigger("loaded");
}
</script>
