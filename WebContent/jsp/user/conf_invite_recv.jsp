<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_invite_recv.res0']}</title>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-1.9.2.custom.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
//${LANG['bizconf.jsp.conf_invite_recv.res1']}
function successDialog(message) {
	$("<div id=\"successDiv\"/>").alertDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"message": message,
		"type": "success",
		"actions": ["${LANG['system.ok']}"]
	});
}
function errorDialog(message, callback) {
	$("<div id=\"errorDiv\"/>").alertDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"type": "error",
		"message": message,
		"actions": ["${LANG['system.ok']}"],
		"callback": callback
	});
}
//add calendar
function addCalendar(id) {
	$("<div id=\"addCalendar\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res3']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/email/outlook",
		"data": id,
		"type" : VIEW_TYPE.calendar,
		"action" : ACTION.create,
		"width" : 484,
		"height" : 204
	});	
}

function sendNoticeEmail() {
	var confUser = "${confUser}";
	var confId = "${confId}";
	var user = "${user.id}";
	if(confUser && user){
		var data = {};
		data.confId = "${confId}";
		app.sendNoticeEmail(data, function(result) {
			if(result){
				if(result.status && result.status==1){
					successDialog(result.message);
				} else {
					errorDialog(result.message);
				}
			}
		},{message:"${LANG['bizconf.jsp.add_calendar_notice.res9']}...", ui:window});		
	} else {
		addCalendar(confId);
	}
}
</script>
<script type="text/javascript">
$(function() {
if ($.browser.msie && $.browser.version<7) {
	$(".png").each(function() {
		$(this).attr('width',$(this).width());
		$(this).attr('height',$(this).height());
		var imgSrc = $(this).attr('src');
		$(this).get(0).runtimeStyle.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader' + '(src=\'' + imgSrc + '\', sizingMethod=\'scale\');';
		$(this).attr('src', '/static/images/blank.gif');
	});		
}
});
</script>
</head>

<body style="overflow-y: hidden;">
<!---->
<jsp:include page="header.jsp" />

<div id="head_bar">
</div>
<div class="Forget_password_main">
	<div class="Forget_password_top">
    <img class="refuse_box png" src="${baseUrlStatic}/images/agree.png" width="52" height="41" />   
      <h3>${LANG['bizconf.jsp.conf_invite_recv.res5']}</h3>
    </div>
  <div class="Forget_password_center">
   	<div class="Forget_password_center_01">
    	<table id="join_main">
        	<tr height="">
            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res6']}</td>
            	<td>${confBase.confName}</td>
            </tr>
            <tr>
            	<td align="right"  class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res7']}</td>
            	<td>${confBase.compereName}</td>
            </tr>
            <tr>
            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res8']}</td>
            	<td><fmt:formatDate value="${confBase.startTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>  ${timeZoneDesc}</td>
            </tr>
            <tr>
            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res9']}</td>
            	<td><fmt:formatDate value="${confBase.endTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>  ${timeZoneDesc}</td>
            </tr>
            <tr>
            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res10']}</td>
            	<td>${confBase.userSecure}</td>
            </tr>
            <c:if test="${!empty confBase.publicConfPass}">
             <tr>
            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res11']}</td>
            	<td>${confBase.publicConfPass}</td>
            </tr>
            </c:if>
<!--             <tr>-->
<!--            	<td align="right" class="join_m01">${LANG['bizconf.jsp.conf_invite_recv.res12']}</td>-->
<!--            	<td>010-00001111</td>-->
<!--            </tr>-->
        </table>
    </div>
    <!--  
   <img class="jion_li" src="${baseUrlStatic}/images/jion_li.png" width="5" height="417" />
   <div class="Forget_password_center_02">
    	<h3>${LANG['bizconf.jsp.conf_invite_recv.res13']}:</h3>
        <p>${LANG['bizconf.jsp.conf_invite_recv.res14']}</p>
        
        <a class="join_a" href="javascript:sendNoticeEmail();"><img src="${baseUrlStatic}/images/email05.png" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>Outlook</a>
        <a class="join_b" href="javascript:sendNoticeEmail();"> <img src="${baseUrlStatic}/images/ico002.png" width="16" height="11" align="absmiddle" style=" padding-right:5px;" />Gmail</a>
        <a class="join_c" href="javascript:sendNoticeEmail();"><img src="${baseUrlStatic}/images/ico003.png" width="14" height="17" align="absmiddle" style=" padding-right:5px;" />Foxmail</a>
    </div> -->
    </div>
    
    
</div>

<!--${LANG['bizconf.jsp.conf_invite_recv.res15']}-->
<div id="copy_close"><span class="copy_text" >Copyright © 2003-2012 Shanghai Shrine Telecom Co., Ltd. 2012. All rights reserved.Version:eMeeting V5.0</span> </div>
</body>
</html>
