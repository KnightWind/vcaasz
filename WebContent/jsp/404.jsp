<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.404.res1']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Css -->	
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>

</head>
<style>
body {
	background:url(${baseUrlStatic}/images/Error_page_bg.jpg) left top repeat-x;
}
</style>
<body>
<div class="Error_page ">
  <div class="Error_page_main">
    <dl>
      <dt><img src="${baseUrlStatic}/images/ico404.png" width="244" height="160" /></dt>
      <dd class="right_page">
          <p>${LANG['bizconf.jsp.404.res2']}</p>
<!--           <p class="page_center">${LANG['bizconf.jsp.404.res3']} </p> -->
<!--           <h3>5${LANG['bizconf.jsp.404.res4']}</h3> -->
      </dd>
    </dl>
  </div>
</div>
</body>
</html>
