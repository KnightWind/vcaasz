<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.system.profile.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<!-- <link rel="stylesheet" type="text/css" href="/static/js/tipsy-master/src/stylesheets/tipsy.css" /> -->
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
</head>
<body>
<div class="emile">
<div class="emile_01">
<form name="profileForm" id="profileForm" action="/system/profile/savefaverSetting" method="post">
    	<div class="emile_01_top"><span>${LANG['website.user.leftmenu.preferences']}</span></div>
    	<table class="form-table" style="margin-left: 162px;margin-top: 82px;">
		  <tr>
		    <td align="right">
		      ${LANG['website.user.favor.page.data'] }
		    </td>
		    <td class="form-table-td">
		   		<select class="selectOption" id="pageSize" name="pageSize" style="border: 1px solid #C1C1C1;">
							<c:forEach var="eachPageSize" items="${USER_FAVOR_PAGE_SIZE}">
								<c:set var="eachPage" value="${eachPageSize}"/>
								<option value="${eachPageSize}" <c:if test="${sysUser.pageSize eq  eachPageSize}">selected="selected"</c:if>>${eachPageSize} ${LANG['user.favor.page.records']}</option>
							</c:forEach>
				</select>
		    </td>
		  </tr>
		</table>
		<div>
			<input style="margin-left: 165px;" class="skipThese" name="emile_button" id="emile_button" type="submit"  value="${LANG['system.email.config.submit']}" />
 			<c:if test="${!empty infoMessage}">
 				<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>${infoMessage}</label>
 			</c:if>
			<c:if test="${!empty errorMessage}">
           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:red'>${errorMessage}</label>
           	</c:if>
		</div>
</form>
</body>
</html>
