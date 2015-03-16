<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="head">
    <div class="header_left">
    <c:if test="${!empty siteBase.siteLogo}">
		<img id="logo_icon" class="logo png" src="${siteBase.siteLogo}" width="100" height="48" onerror="this.src='${baseUrlStatic}/images/logo.png';"/>
	</c:if>
	<c:if test="${empty siteBase.siteLogo}">
		<img id="logo_icon" class="logo png" src="/static/images/logo.png" width="100" height="48" alt=""/>
	</c:if>
    <span class="name">${siteBase.siteName}</span>
    </div>
    <c:if test="${!empty user}">
    <div class="header_right">
     	<div class="header_right01">
        	<ul class="head_right">
        		<li>
		          	<form name="langForm" id="langForm">
		          		<select name="jumpMenu" id="jumpMenu" >
		              		<option value="zh-cn">${LANG['website.lang.zh']}</option>
			          		<option value="en-us">${LANG['website.lang.en']}</option>
		            	</select>
		          	</form>
        		</li>
<%-- 			    <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li> --%>
<%-- 			    <li><img class="help" src="${baseUrlStatic}/images/help.jpg" width="16" height="16" /></li> --%>
<%-- 			    <li><a  title="${LANG['bizconf.jsp.admin.CopyOfadminIndex.res4']}" href="#">${LANG['website.message.help']}</a></li> --%>
			    <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li>
			    <li><img class="go_out" src="${baseUrlStatic}/images/go_out.jpg" width="16" height="16" /></li>
			    <li><a  title="${LANG['bizconf.jsp.admin.CopyOfadminIndex.res5']}" href="javascript:logout();">${LANG['website.message.logout']}</a></li>
			</ul> 
     	</div>
        <div class="header_right02">
        <div class="aname"><img src="${baseUrlStatic}/images/user.jpg" width="15" height="15" /><span>${LANG['bizconf.jsp.admin.CopyOfadminIndex.res6']}${user.trueName }${LANG['bizconf.jsp.admin.CopyOfadminIndex.res7']}</span></div>
        </div>
        
  </div>
    </c:if>
    
</div>   
