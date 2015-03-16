<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>add group</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
var emailForm = null;
$(document).ready(function(){
	$('#emailForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
	var ruleString = {
			required: {
				"email": "${LANG['bizconf.jsp.add_calendar_notice.res1']}"
			},
			rangelength: {
				"email": "${LANG['bizconf.jsp.add_calendar_notice.res2000']}"
			},
			custom: {
				"email": "${LANG['bizconf.jsp.add_calendar_notice.res4']}"
			}
	};
	emailForm = $("#emailForm").validate({
		onkeyup: false,
		errorClass: "warning",
		submitHandler: function(form) {
			sendEmail();
		},
		rules: {
            'email' : {required:true, rangelength:[6, 32], email: true}
        },
        messages: {
            'email' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email}
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
<form action="" id="emailForm">
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
        <div class="First_Steps_quick_e" style=" background:#FFF;width:460px;">
          <div class="First_Steps_title_a"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.add_calendar_notice.res5']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.add_calendar_notice.res6']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <table class="box_a_quick02">
              <tr class="box01">
                <td align="right" class="left_text_a"><span style=" color:#F00">* </span>${LANG['bizconf.jsp.add_calendar_notice.res7']}</td>
                <td align="left">
                  <input class="right_text_a" id="email" name="email" type="text" />
                </td>
              </tr>
            </table>
          </div>
        </div>
        
        <div class="but361">
         	<button type="submit" value="${LANG['bizconf.jsp.add_calendar_notice.res8']}" class="but_send">
          		<img src="/static/images/tixing.png" width="16" height="15" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.add_calendar_notice.res8']}
          	</button>
<!-- 	    <span class="button_common"> -->
<!-- 	        <a href="javascript:;" onclick="submitForm()"> -->
<!-- 	        <img src="/static/images/tixing.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;"/>${LANG['bizconf.jsp.add_calendar_notice.res8']}</a> -->
<!-- 	    </span> -->
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
</form>
</body>
</html>
<script type="text/javascript">
function sendEmail() {
	var data = {};
	var frame = parent.$("#addCalendar");
	data.confId = frame.data("data");
// 	data.userName = $("#userName").val();
	data.email = $("#email").val();
	app.sendNoticeEmail(data, function(result) {
		if(result){
			if(result.status && result.status==1){
				parent.successDialog(result.message);
			} else {
				parent.errorDialog(result.message);
			}
			closeDialog();
		}
	},{message:"${LANG['bizconf.jsp.add_calendar_notice.res9']}...", ui:parent});
}
function loaded() {
	var frame = parent.$("#addCalendar");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#addCalendar");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
