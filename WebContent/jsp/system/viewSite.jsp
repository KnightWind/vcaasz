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
    	<li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res2']}</span><span class="m_r">${siteBase.siteName}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res3']}</span><span class="m_r">${siteBase.enName}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res4']}</span><span class="m_r">${siteBase.siteSign}</span></li>
       
    </ul>
    <h3 class="xinxi02">${LANG['bizconf.jsp.system.viewSite.res5']}</h3>
    <ul>
		<c:choose>
			<c:when test="${siteBase.hireMode==1}">
				<li><span class="m_l">${LANG['system.site.list.hireMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.hireMode.month']}</span></li>			
			</c:when>
			<c:when test="${siteBase.hireMode==2}">
				<li><span class="m_l">${LANG['system.site.list.hireMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.hireMode.minutes']}</span></li>			
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${siteBase.chargeMode==1}">
				<li><span class="m_l">${LANG['system.site.list.chargeMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.chargeMode.Name Host']}</span></li>			
			</c:when>
			<c:when test="${siteBase.chargeMode==2}">
				<li><span class="m_l">${LANG['system.site.list.chargeMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.chargeMode.Active User']}</span></li>			
			</c:when>
			<c:when test="${siteBase.chargeMode==3}">
				<li><span class="m_l">${LANG['system.site.list.chargeMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.chargeMode.Seats']}</span></li>			
			</c:when>
			<c:when test="${siteBase.chargeMode==4}">
				<li><span class="m_l">${LANG['system.site.list.chargeMode']}${LANG['bizconf.jsp.admin.conf_list.res6']}</span><span class="m_r">${LANG['system.site.list.chargeMode.time']}</span></li>			
			</c:when>
		</c:choose>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res6']}</span><span class="m_r">${siteBase.license }</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res7']}</span><span class="m_r"><fmt:formatDate  value="${siteBase.effeDate}" type="date" pattern="yyyy-MM-dd"/></span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res8']}</span><span class="m_r"><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></span></li>
     
    </ul>
    <h3 class="xinxi03">${LANG['bizconf.jsp.system.viewSite.res9']}</h3>
    <ul>
    	<li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res10']}</span><span class="m_r">${siteAdmin.trueName}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res11']}  ${LANG['bizconf.jsp.system.viewSite.res12']}</span><span class="m_r">${siteAdmin.mobile}</span></li>
        <li><span class="m_l">${LANG['bizconf.jsp.system.viewSite.res13']}  ${LANG['bizconf.jsp.system.viewSite.res14']}</span><span class="m_r">${siteAdmin.userEmail}</span></li>
        
    </ul>
	<input name="queren" class="queren" type="button" value="${LANG['bizconf.jsp.admin.user_info.res9']}" />    
</div>
</body>
</html>
