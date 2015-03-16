<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['website.user.invite.reject.title']}</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/reject.css" />
</head>
<body style="overflow-y: hidden;">
		<div id="content">
			<div class="wrapper">
				<div class="reject fix-png">
					<h3>${LANG['website.user.invite.reject.rejectinvite']}</h3>
					<div class="infomation">
						<div class="item">
							<span class="title">${LANG['website.user.invite.accept.conftheme']}</span>
							<div class="content">${confBase.confName}</div>
						</div>
						<div class="item">
							<span class="title">${LANG['website.user.invite.accept.confhost']}</span>
							<div class="content">${confBase.compereName}</div>
						</div>
						<div class="item">
							<span class="title">${LANG['website.user.invite.accept.confstarttime']}</span>
							<div class="content"><fmt:formatDate value="${confBase.startTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>  ${timeZoneDesc}</div>
						</div>
						<div class="item">
							<span class="title">${LANG['website.user.invite.accept.confendtime']}</span>
							<div class="content"><fmt:formatDate value="${confBase.endTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>  ${timeZoneDesc}</div>
						</div>
						<div class="item">
							<span class="title">${LANG['website.user.invite.accept.confmeetingno']}</span>
							<div class="content">${confBase.userSecure}</div>
						</div>
						<c:if test="${!empty confBase.publicConfPass}">
				            <div class="item">
				            	<span class="title">${LANG['website.user.invite.accept.publicconfpwd']}</span>
				            	<div class="content">${confBase.publicConfPass}</div>
				            </div>
				        </c:if>
					</div>
				</div>
				<div class="regret fix-png">
					<p>${LANG['website.user.invite.reject.changeyoumind']}</p>
					<button class="input-green" onclick="window.location='/user/inviteUser/recv?cuid=${confUser.id}&confId=${confBase.id}'"><a href="/user/inviteUser/recv?cuid=${confUser.id}&confId=${confBase.id}" style="color: #fff;">${LANG['website.user.invite.reject.acceptit']}</a></button>
				</div>
			</div>
		</div>
</body>
</html>
