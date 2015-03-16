<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="head">
    <div class="header_left">
    <img class="logo" src="${baseUrlStatic}/images/logo02.png" height="48" />
    <span class="name">${LANG['bizconf.jsp.system.header.res1']}</span>
    </div>
    <c:if test="${!empty user}">
<div class="header_right">
     	<div class="header_right01">
           <ul class="head_right">
           <li>
             <form name="langForm" id="langForm">
	        <select name="lang" id="lang">
	          <option value="zh-cn">${LANG['website.lang.zh']}</option>
	          <option value="en-us">${LANG['website.lang.en']}</option>
	        </select>
	      </form>
           </li>
<%--     <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li> --%>
<%--     <li><img class="help" src="${baseUrlStatic}/images/help.jpg" width="16" height="16" /></li> --%>
<%--     <li><a  title="${LANG['website.message.help']}" href="#">${LANG['website.message.help']}</a></li> --%>
    <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li>
    <li><img class="go_out" src="${baseUrlStatic}/images/go_out.jpg" width="16" height="16" /></li>
    <li><a  title="${LANG['website.message.logout']}" href="javascript:logout();">${LANG['website.message.logout']}</a></li>
    </ul> 
          
     	</div>
        <div class="header_right02">
        <div class="aname"><img src="${baseUrlStatic}/images/user.jpg" width="15" height="15" /><span>${LANG['website.message.welcome']}${LANG['bizconf.jsp.admin.header.res1']}${user.trueName }${LANG['bizconf.jsp.admin.CopyOfadminIndex.res7']}</span></div>
        </div>
        
  </div>    
    </c:if>    
</div> 
