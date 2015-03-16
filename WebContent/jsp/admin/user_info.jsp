<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<title>${LANG['bizconf.jsp.admin.user_info.res1']}</title>
</head>
<body onload="loaded();">
<div class="popup_box">
	<div class="popup_box_main">
    <table>
  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.arrange_org_user.res7']}:</td>
    <td class="changes_right">${user.loginName}</td>
  </tr>
  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}:</td>
    <td class="changes_right">${user.trueName}</td>
  </tr>
  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.user_info.res2']}:</td>
    <td class="changes_right">${user.enName}</td>
  </tr>
<%--  <tr>--%>
<%--    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.user_info.res3']}:</td>--%>
<%--    <td  class="changes_right">${user.loginPass}</td>--%>
<%--  </tr>--%>
	<tr>
		 <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.user_info.res4']}:</td>
		 <td  class="changes_right">
		 	<c:if test="${user.userStatus eq '0'}">${LANG['system.site.list.Status.lock']}</c:if>
			<c:if test="${user.userStatus eq '1'}">${LANG['site.admin.userlist.active']}</c:if>
		 </td>
	</tr>
	<c:if test="${!empty orgName && orgName != ''}">
	 	<tr> 
	 	    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.edit_userbase.res3']}:</td> 
			<td class="changes_right">${orgName}</td>
	 	</tr> 
 	</c:if>
	
  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.user_info.res5']}:</td>
    <td class="changes_right">
    	<c:choose>
    		<c:when test="${user.userRole eq '1'}">${LANG['bizconf.jsp.admin.user_info.res6']}</c:when>
    		<c:otherwise>${LANG['bizconf.jsp.admin.user_info.res7']}</c:otherwise>
    	</c:choose>
    </td>
  </tr>

  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.arrange_org_user.res9']}:</td>
    <td class="changes_right">${user.userEmail}</td>
  </tr>
  <tr>
    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.conf_list.res9']}:</td>
    <td class="changes_right">${user.mobile}</td>
  </tr>
  <c:if test="${user.userRole eq '1'}">
	  <tr>
	    <td align="right" class="changes_left">${LANG['bizconf.jsp.admin.user_info.res8']}:</td>
	    <td class="changes_right"><p>
	    <c:if test="${config.phoneFlag eq 1 and siteConfig.phoneFlag eq 1}">${LANG['bizconf.jsp.admin.conf_list.res9']}&nbsp;</c:if> 
<%--	    <c:if test="${config.OutCallFlag eq 1 and siteConfig.OutCallFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res7']}&nbsp;</c:if> --%>
	    <c:if test="${config.shareMediaFlag eq 1 and siteConfig.shareMediaFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res8']}&nbsp;</c:if> 
	    <c:if test="${config.recordFlag eq 1 and siteConfig.recordFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res9']}</c:if> 
	    </p></td>
	  </tr>
  </c:if>
</table>
   <a class="user_messages_btn" href="#" onclick="closeDialog();" >${LANG['bizconf.jsp.admin.user_info.res9']}</a> 
 </div>
</div>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#viewUserDiv");
	frame.trigger("loaded");
}

function closeDialog(result) {
	var dialog = parent.$("#viewUserDiv");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
