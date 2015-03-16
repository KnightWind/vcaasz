<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/const-tag" prefix="cc"%>
<%@ taglib uri="/WEB-INF/jstltags/licfn.tld" prefix="myfn"%>
<div class="banner fix-png">
	<div class="brand-wrapper">
		<cc:base var="DEFAULT_SITE_BRAND"/>
		<c:if test="${siteBase.siteSign eq DEFAULT_SITE_BRAND}">
			<div class="logo-wrapper">
				<div class="logo-img">
					<c:if test="${!empty siteBase.siteLogo}">
		    			<p><img class="fix-png" src="${siteBase.siteLogo}" onerror="this.src='/assets/images/icons/logo.png';" onload="javascript:DrawImage(this,120,60)"/></p>
		  			</c:if>
		  			<c:if test="${empty siteBase.siteLogo}">
			  			<p><img class="fix-png" src="/assets/images/icons/logo.png" onload="javascript:DrawImage(this,120,60)"/></p>
		  			</c:if>
				</div>
			</div>
			<div class="logo-title-wrapper">
				<img src="/assets/images/name-part.png" class="name fix-png" alt="标志">
			</div>
		</c:if>
		<c:if test="${siteBase.siteSign ne DEFAULT_SITE_BRAND}">
			<div class="logo-wrapper">
		    	<div class="logo-img">
		    		<c:if test="${!empty siteBase.siteLogo}">
		    			<p><img class="fix-png" src="${siteBase.siteLogo}" onerror="this.src='/assets/images/icons/logo.png';" onload="javascript:DrawImage(this,120,60)"/></p>
		  			</c:if>
		  			<c:if test="${empty siteBase.siteLogo}">
			  			<p><img class="fix-png" src="/assets/images/icons/logo.png" onload="javascript:DrawImage(this,120,60)"/></p>
		  			</c:if>
				</div>
		    </div>
		    <div class="logo-title-wrapper">
		    	<div style="margin-top: 20px;">
		  			<p id="siteName">${siteBase.siteName}</p>
		  			<p id="siteEnName">${siteBase.enName}</p>
				</div>
		    </div>		
		</c:if>	
	</div>
    <div class="action-wrapper">
		<c:if test="${siteBase.siteDiy==1}">
    	<c:if test="${!empty siteBase.siteBanner}">
	    	<img class="concat-phone" width="300" height="60" onerror="this.src='/assets/images/concat-phone.png';"  src="${siteBase.siteBanner}" alt="${LANG['website.user.header.info.contactphone']}">
    	</c:if>
    	<c:if test="${ empty siteBase.siteBanner }">
<!--     		<img class="concat-phone" src="/assets/images/concat-phone.png" alt="banner"> -->
    	</c:if>
    	</c:if>
    </div>
</div>