<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.settings.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
</head>
<body style="padding: 0px;">
<div class="user-detail-dialog">
	<div class="form-body">
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.loginname'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" title="${user.loginName}">${user.loginName}</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.userbase.title.name'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" title="${user.trueName}">${user.trueName}</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.enname'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" title="${user.enName}">${user.enName}</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.userstatus'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget">
				<c:if test="${user.userStatus eq '0'}">${LANG['system.site.list.Status.lock']}</c:if>
				<c:if test="${user.userStatus eq '1'}">${LANG['site.admin.userlist.active']}</c:if>
			</div>
		</div>
		<c:if test="${!empty orgName && orgName != ''}">
		 	<div class="form-item">
				<label class="title">${LANG['website.admin.view.user.orgname'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget">${orgName}</div>
		</div>
	 	</c:if>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.userrole'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget">
				<c:choose>
		    		<c:when test="${user.userRole eq '1'}">${LANG['bizconf.jsp.admin.user_info.res6']}</c:when>
		    		<c:otherwise>${LANG['bizconf.jsp.admin.user_info.res7']}</c:otherwise>
		    	</c:choose>
			</div>
		</div>
		<c:if test="${user.userRole eq '1'}">
			<div class="form-item siteAuthor">
				<label class="title">${LANG['website.admin.view.user.empower'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					<c:if test="${config.phoneFlag eq 1 and siteConfig.phoneFlag eq 1}">${LANG['bizconf.jsp.admin.conf_list.res9']}&nbsp;&nbsp;&nbsp;&nbsp;</c:if> 
		  			<c:if test="${config.outCallFlag eq 1 and siteConfig.outCallFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res7']}&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
				    <c:if test="${config.shareMediaFlag eq 1 and siteConfig.shareMediaFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res8']}&nbsp;&nbsp;&nbsp;&nbsp;</c:if> 
				    <c:if test="${config.recordFlag eq 1 and siteConfig.recordFlag eq 1}">${LANG['bizconf.jsp.admin.edit_userbase.res9']}</c:if> 
				</div>
			</div>
		</c:if>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.expiry.date'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" id="expiryDateForEver">${LANG['website.admin.view.user.been.effective'] }</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.email'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" title="${user.userEmail}">${user.userEmail}</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.phone'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget">${user.mobile}</div>
		</div>
		<div class="form-item">
			<label class="title">${LANG['website.admin.view.user.remark'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="widget" title="${user.remark}">${user.remark}</div>
		</div>
	</div>
	<div class="form-buttons">
	 	<input type="button" class="button input-gray anchor-cancel" value="${LANG['website.common.option.confirm'] }" />
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<fmt:formatDate var="userExprieDate" value="${user.exprieDate}" pattern="yyyy-MM-dd"/>
<script type="text/javascript">
$(document).ready(function(){
	var permanetUser = "${user.permanentUser}";
	var userDate = "${userExprieDate}";
	if(userDate){
		if(permanetUser && permanetUser=="false"){
			$( "#expiryDateForEver" ).text(userDate);	
		}
	}
	var phoneFlag = "${config.phoneFlag}";
	var outCallFlag = "${config.outCallFlag}";
	var shareMediaFlag = "${config.shareMediaFlag}";
	var recordFlag = "${config.recordFlag}";
	if(phoneFlag == "1" || outCallFlag == "1" || shareMediaFlag == "1" || recordFlag == "1"){
		$(".siteAuthor").show();
	}else{
		$(".siteAuthor").hide();
	}
});
</script>
</body>
</html>