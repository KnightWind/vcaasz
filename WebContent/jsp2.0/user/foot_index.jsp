<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<div id="footer" style="position: static;">
   <ul class="nav">
     <c:choose>
	     <c:when test="${currentLanguage eq 'en-us'}">
	     <li><a href="javascript:changeLang('zh-cn')">中文</a></li>
	     </c:when>
	     <c:otherwise>
	     <li><a href="javascript:changeLang('en-us')">EN</a></li>
	     </c:otherwise>
     </c:choose>
     <li><a href="/support/download" target="_blank">${LANG['website.user.download.center.page.title']}</a></li>
     <li><a href="/sla" target="_blank">${LANG['bizconf.jsp.conf.bizconf.foot.video.title']}</a></li>
     <li><a href="/help" target="_blank">${LANG['bizconf.jsp.conf.bizconf.foot.use.instruction']}</a></li>
     <li>${LANG['website.user.footer.info.hotline']}</li>
   </ul>
   <p class="copyright">${LANG['website.user.footer.info.rightreserved']}</p>
 </div>