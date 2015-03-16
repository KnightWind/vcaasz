<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['system.site.list.create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!-- Css -->	
<link rel="stylesheet" type="text/css" href="/static/css/zhandianxinxi.css" />
<!-- Javascript -->
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript">
$(document).ready(function(){
	$(".queren").click(function() {
		var frame = parent.$("#siteDiv");
		frame.trigger("closeDialog");
	});
});
function loaded(){
	var frame = parent.$("#siteDiv");
	frame.trigger("loaded");
}
</script>
</head>
<body onload="loaded()">
<div class="xinxi">
	<h3 class="xinxi01">${LANG['bizconf.jsp.system.viewSite.res1']}</h3>
	<ul>
    	<li><span class="m_l">${LANG['bizconf.jsp.admin.arrange_org_user.res9']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${userBase.userEmail}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${userBase.trueName}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.admin.user_info.res2']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${userBase.enName}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.add_site_user.res2']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${userBase.numPartis}</span></li>
        <c:if test="${!empty userBase.mobile}">
           <li><span class="m_l">${LANG['bizconf.jsp.admin.conf_list.res9']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${userBase.mobile}</span></li>
        </c:if>
        <c:if test="${empty userBase.mobile}">
           <li><span class="m_l">${LANG['bizconf.jsp.admin.conf_list.res9']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${LANG['bizconf.jsp.admin.edit_userbase.res5']}</span></li>
        </c:if>
     	<c:choose>
     		<c:when test="${userBase.delFlag eq 2}">
     			<li><span class="m_l">${LANG['bizconf.jsp.admin.user_info.res4']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${LANG['website.system.userlist.deleteduser']}</span></li>
     		</c:when>
     		<c:otherwise>
     			<c:if test="${userBase.userStatus eq 1}">
		        	<li><span class="m_l">${LANG['bizconf.jsp.admin.user_info.res4']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${LANG['site.status.1']}</span></li>
		        </c:if>
		        <c:if test="${userBase.userStatus eq 0}">
		        	<li><span class="m_l">${LANG['bizconf.jsp.admin.user_info.res4']}${LANG['website.common.symbol.colon']}</span><span class="m_r">${LANG['site.status.2']}</span></li>
		        </c:if>
     		</c:otherwise>
     	</c:choose>
     </ul>
    
	<input name="queren" class="queren" type="button" value="${LANG['bizconf.jsp.admin.user_info.res9']}" />    
</div>
</body>
</html>
