<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="banner fix-png">
	<div class="brand-wrapper">
		<div align="left" class="logo-wrapper" style="width: auto;">
	    	<div class="" style="margin-left: 20px;margin-top: 10px;">
	    		<c:if test="${!empty siteBase.siteLogo}">
	    			<p><img id="site-logo" class="fix-png" src="${siteBase.siteLogo}" height="60px;" onerror="this.src='/assets/images/icons/logo.png';" /></p>
	  			</c:if>
	  			<c:if test="${empty siteBase.siteLogo}">
		  			<p><img id="site-logo" class="fix-png" src="/assets/images/icons/logo.png" onload="javascript:DrawImage(this,120,60)"/></p>
	  			</c:if>
			</div>
	    </div>
	    <div class="logo-title-wrapperx" style="float: left; margin-left: 20px">
	    	<div style="margin-top: 20px;">
	  			<p id="siteName">${siteBase.siteName}</p>
	  			<p id="siteEnName">${siteBase.enName}</p>
			</div>
	    </div>
    </div>
    <div class="action-wrapper">
    	<div class="profile">
    		<c:if test="${!empty user}">
	    		<i class="icon icon_login"></i>
	    		<span class="profile_name mr5">${LANG['website.user.headmenu.welcome']}, ${user.trueName }</span>
<!-- 	    		<a class="profile_action" href="/help/doc" target="_blank">${LANG['website.admin.header.menu.help'] }</a> |  -->
	    		<a class="profile_action" href="javascript:logout('${LANG['website.user.js.message.logout.prompt'] }')" >${LANG['website.common.logout.name'] }</a>
		  	</c:if>
		  	<c:if test="${empty user }">
			  	<i class="icon icon_login"></i>
	    		<a class="profile_action" href="javascript:userLogin();">${LANG['website.common.login.name']}</a>
		  	</c:if> 
    	</div>
    	<div class="region"> <form name="langForm" id="langForm">
			<span class="language"> 
				<select name="jumpMenu_language" id="jumpMenu_language" >
					<option value="zh-cn"> ${LANG['website.common.language.zh']}</option>
					<option value="en-us"> ${LANG['website.common.language.en']}</option>
				</select>
			</span>    
			</form>	
    	</div>
    </div>
</div>
