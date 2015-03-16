<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>schedule meeting</title>
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css?ver=${version}">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
	<!-- Javascript -->
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.plugin.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
	<script type="text/javascript">
	var immediaForm = null;
	$(document).ready(function(){
		$('#confName').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
		$("#confName").watermark("${LANG['valid.input.confTitle']}");
		var ruleString  = {
				required:{
					"confName": "${LANG['valid.input.confTitle']}"
				},
				rangelength: {
					"confName": "${LANG['valid.rangelength132']}"
				}
			};
		immediaForm = $("#immediaForm").validate({
				onkeyup: false,
				errorClass: "warning",
				submitHandler: function(form) {
					editMeeting();
				},
				rules: {
		            'confName' : {required:true, rangelength: [1, 32]}
		        },
		        messages: {
		            'confName' : {required:ruleString.required.confName, rangelength: ruleString.rangelength.confName}
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
		
		function editMeeting(){
			//${LANG['bizconf.jsp.create_Immediately_Conf.res1']}
			var data = {};
			data.confName = $("#confName").val();
			app.tempMeeting(data, function(result) {
				if(result && result.status==1){
// 					var frame = parent.$("#joinMeeting");
// 					<cc:confList var="JOIN_TYPE_CONFID"/>
// 					$(frame).find("iframe").attr("src", "/join?joinType=${JOIN_TYPE_CONFID}&cId="+result.id);
// 					var url = "/join?joinType=${JOIN_TYPE_CONFID}&cId="+result.id;
					closeDialog(result);
				} else {
					parent.errorDialog(result.message);
				}
			}, {message:"${LANG['bizconf.jsp.create_Immediately_Conf.res2']}...", ui:parent});
		};

		function quickStart() {
			if(immediaForm.form()){
				editMeeting();
			}	
		}	
	</script>
</head>
<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinPublicDiv">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->  
	  <form name="immediaForm" id="immediaForm" method="post" action="">
      	<div class="First_Steps_quick_a" style=" background:#FFF;height: 173px;">
	        <div class="First_Steps_title_a" style="height: 75px;"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
	          <h3 class="tit_a">${LANG['user.menu.conf.immediatly']}</h3>
	          <p class="tit_b">${LANG['user.menu.conf.join.desc']}</p>
	        </div>
        	<div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        <div class="First_Steps_main_quick">
	          <table class="box_a_quick">
	            <tr class="box01">
	              <td align="right" class="left_text">${LANG['system.list.meeting.title']}</td>
	              <td align="left" style="padding-left: 10px;">
	              	<input class="immedia_text" id="confName" name="confName" type="text" value="${confNameDefault}"/></td>
	            </tr>
	          </table>
	        </div>
	        <div class="First_Steps_bottom01" style="height: 30px;">
	          <button type="submit" value="${LANG['bizconf.jsp.create_Immediately_Conf.res3']}" class="but07">
	          		<img src="/static/images/join.png" width="16" height="15" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['user.menu.conf.start']}
	          	</button>
	        </div>
      	</div>
      </form>
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->      
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
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#joinMeeting");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#joinMeeting");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
