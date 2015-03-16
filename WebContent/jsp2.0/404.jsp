<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.404.res1']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<style>
	.main-container {
	    background: url("/assets/images/404.png") no-repeat scroll 0 0 transparent;
	    height: 249px;
	    left: 50%;
	    margin: -125px auto 0 -150px;
	    position: absolute;
	    top: 50%;
	    width: 299px;
	}
	.errro-message {
		position: absolute;
		top: 210px;
		left: 130px;
		font-size: 14px;
	}
	</style>
</head>
<body>
<div class="main-container">
	<div class="errro-message">
		您要找的页面已飞往火星...
	</div>
</div>
</body>
</html>
