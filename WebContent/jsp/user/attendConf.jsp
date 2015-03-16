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
	<!-- Javascript -->
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
	<script type="text/javascript">
		var validString  = {
			required:{
				userName: "${LANG['bizconf.jsp.attendConf.res1']}",
				meetingNo: "${LANG['bizconf.jsp.attendConf.res2']}"
			}
		};
		function editMeeting(){
			var data = {};
			data.userName = $("#userName").val();
			data.meetingNo = $("#meetingNo").val();
			app.quickMeeting(data, function(result) {
				if(result && result.status==1){
					var frame = parent.$("#tempMeeting");
					frame.trigger("closeDialog", [result]);
				}
			});
		};		
		function checkForm() {
			var confName = $("#userName").val();
			var meetingNo = $("#meetingNo").val();
			if(!confName){
				parent.errorDialog(validString.required.userName, function() {
					$("#userName").focus();
				});
				return false;
			} else if(!meetingNo){
				parent.errorDialog(validString.required.meetingNo, function() {
					$("#meetingNo").focus();
				});
				return false;
			}			
			return true;
		}
		jQuery(function($) {
			$("#startButton").click(function() {
				if(checkForm()){
					editMeeting();
				}	
			});
			$("#meetingForm").find("input").not(".skipThese").uniform();
		});
		
		function enterKey(){
		    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.attendConf.res3']}   
		    if (event.keyCode == 13){       //${LANG['bizconf.jsp.attendConf.res4']}
		    	if(checkForm()){
					editMeeting();
				}
		    } 
		}
	</script>
</head>
<body onload="loaded()">
<form id="meetingForm" action="">
<div class="">
	<table style="margin: 25px auto;">
		<tr>
			<td>${LANG['system.sysUser.list.userName']}</td>
			<td><input type="text" id="userName" name="userName"/></td>
		</tr>
		<tr>
			<td>${LANG['user.menu.conf.safe.number']}</td>
			<td>
				<input type="text" id="meetingNo" name="meetingNo"  onkeypress="enterKey();"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>	
				<input id="startButton" type="button" value="${LANG['user.menu.conf.start']}" class="button1 skipThese"/>
			</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#quickMeeting");
	frame.trigger("loaded");
}
</script>
