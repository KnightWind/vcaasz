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
	<c:choose>
		<c:when test="${currentLang eq 'zh-cn' }">
			<iframe id="PDF" name="PDF" allowtransparency='true'  height="630px" style="padding: 10px;" width="97%" scrolling="auto" 
	                	src="/help/problem.pdf">
	     	</iframe>
		</c:when>
		<c:otherwise>
			<iframe id="PDF" name="PDF" allowtransparency='true'  height="630px" style="padding: 10px;" width="97%" scrolling="auto" 
	                	src="/help/problem_en.pdf">
	     	</iframe>
		</c:otherwise>
	</c:choose>
 </div>
 </div>
</body>
</html>
