<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/settings.css" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<%--	<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>--%>
	<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
	<cc:siteList var="USER_FAVOR_LANGUAGE"/>
</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.user.favor.title'] }</span>
		<span class="desc">${LANG['website.user.favor.desc'] }</span>
	</div>
	<div class="body">
		<form action="/admin/profile/updateFavor" method="post" class="personal-form">
<%--				<div class="widget">--%>
<%--					<span class="cautionHint">${LANG['website.user.favor.caution'] }</span>--%>
<%--				</div>--%>
<%--			<div class="form-item audioTR">--%>
<%--				<label class="title">${LANG['website.user.favor.timezone'] }：</label>--%>
<%--				<div class="widget">--%>
<%--					<select id="timeZoneId" name="timeZoneId">--%>
<%--						<c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}">--%>
<%--							<c:set var="eachLang" value="website.timezone.city.zone.${eachTimeZone.timeZoneId}"/>--%>
<%--							<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId == user.timeZoneId }">selected="selected"</c:if> >${LANG[eachLang]} </option>--%>
<%--						</c:forEach>--%>
<%--					</select>--%>
<%--				</div>--%>
<%--			</div>--%>
			<div class="form-item audioTR">
				<label class="title">${LANG['website.user.favor.lang'] }：</label>
				<div class="widget">
					<select class="selectOption" id="favorLanguage" name="favorLanguage">
						<c:forEach var="eachLanguage" items="${USER_FAVOR_LANGUAGE}">
							<c:set var="language" value="system.language.${eachLanguage}"/>
							<option value="${eachLanguage}" <c:if test="${eachLanguage == user.favorLanguage }">selected="selected"</c:if> >${LANG[language]}</option>
					    </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-item audioTR">
				<label class="title">${LANG['website.user.favor.page.data'] }：</label>
				<div class="widget">
					<select class="selectOption" id="pageSize" name="pageSize">
						<c:forEach var="eachPageSize" items="${USER_FAVOR_PAGE_SIZE}">
							<c:set var="eachPage" value="${eachPageSize}"/>
							<option value="${eachPageSize}" <c:if test="${eachPageSize == user.pageSize }">selected="selected"</c:if> >${eachPageSize} ${LANG['user.favor.page.records']}</option>
						</c:forEach>
					</select>
					<span class="hint">${LANG['website.user.favor.page.hint'] }</span>
				</div>
			</div>
			
			
			<div class="form-item submit-item">
				<div class="widget">
					<button type="submit" class="input-gray">${LANG['website.common.option.save'] }</button>
					<c:if test="${!empty infoMessage}">
	        			<span class="message_span">
	        			<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
	        			</span>
	        			<c:if test="${changelan}">
		        			<script type="text/javascript">
		        				$(document).ready(function(){
		        					setTimeout(function(){
		        						top.location.reload(true);
		        					},500);
		        				});
		        			</script>
	        			</c:if>
		 			</c:if>
		 			<c:if test="${!empty errorMessage}">
						<span class="message_span">
		           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
		           		</span>
	           		</c:if>
				</div>
			</div>
		</form>
	</div>
</div>
</body>
</html>