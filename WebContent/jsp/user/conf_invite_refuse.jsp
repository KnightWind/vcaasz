<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_invite_recv.res4']}</title>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript">
jQuery(function($) {
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
<!--${LANG['bizconf.jsp.conf_invite_refuse.res2']}-->
<div class="Forget_password_main">
	<div class="Forget_password_top">
      <img class="refuse_box png" src="${baseUrlStatic}/images/refuse.png" width="47" height="41" />
      <h1>${LANG['bizconf.jsp.conf_invite_refuse.res3']}</h1>
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
    <img class="jion_li png" src="${baseUrlStatic}/images/jion_li.png" width="5" height="417" />
    <div class="Forget_password_center_02">
    	<h3>${LANG['bizconf.jsp.conf_invite_refuse.res4']}</h3>
        <p>${LANG['bizconf.jsp.conf_invite_refuse.res5']}<a href="/user/inviteUser/recv?cuid=${confUser.id}&confId=${confBase.id}">${LANG['bizconf.jsp.conf_invite_refuse.res6']}"${LANG['bizconf.jsp.conf_invite_refuse.res7']}"</a></p>
        <p>${LANG['bizconf.jsp.conf_invite_refuse.res8']}</p>
    </div>
    </div>
    
    
</div>

<!--${LANG['bizconf.jsp.conf_invite_recv.res15']}-->
<div id="copy_close"><span class="copy_text" >Copyright © 2003-2012 Shanghai Shrine Telecom Co., Ltd. 2012. All rights reserved.Version:eMeeting V5.0</span> </div>
</body>
</html>
