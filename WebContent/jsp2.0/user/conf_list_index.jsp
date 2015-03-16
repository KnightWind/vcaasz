<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link rel="stylesheet" type="text/css" href="/assets/css/biz.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
</head>
<body>
<div style="padding: 15px;">
	<div class="tabs-nav">
		<ul class="tabs clearfix">
			<li data-name="tabs-today" class="today active">
				<a href="javascript:;">${LANG['website.user.conf.list.today']}</a>
			</li>
			<li data-name="tabs-week" class="week">
				<a href="javascript:;">${LANG['website.user.conf.list.week']}</a>
			</li>
			<li data-name="tabs-month" class="month">
				<a href="javascript:;">${LANG['website.user.conf.list.month']}</a>
			</li>
			<li data-name="tabs-total" class="total">
				<a href="javascript:;">${LANG['website.user.conf.list.all']}</a>
			</li>
			<li>
				<a>${LANG['bizconf.jsp.admin.login.res10']}</a>
			</li>
		</ul>
	</div>
	<div class="summary">
		<div class="attend">
			<input id="check_atttend" type="checkbox" value="1"/>
			<label for="check_atttend">${LANG['bizconf.jsp.conf_list_main.res8']}</label>
		</div>
		<div class="tabs-content">
			<div id="tabs-today" class="tabs-panel">
				<i class="icon icon-date fix-png">&nbsp;</i>
				<span>2013-09-16</span>
			</div>
			<div id="tabs-week" class="tabs-panel">
				<i class="icon icon-date fix-png">&nbsp;</i>
				<span>2013-09-16 — 2013-09-16</span>
			</div>
			<div id="tabs-month" class="tabs-panel">
				<input id="monthStart" type="text" readonly="readonly"/> — <input id="monthEnd" type="text" readonly="readonly"/>
			</div>
			<div id="tabs-total" class="tabs-panel">
				<input id="allStart" type="text" /> — <input id="allEnd" type="text" />
			</div>
		</div>		
		<div class="search">
			<form method="get">
				<input type="text" class="input-text" placeholder="${LANG['website.conf.input.conftheme']}" />
				<button class="submit-search" type="button">${LANG['website.common.search.name']}</button>
			</form>
		</div>
	</div>
	<div class="clearfix"></div>
	<div id="loadingContainer" style="display: none;position: absolute;">
		<img src='/static/images/loading.gif'/>
		<span style="position: relative;bottom: 10px;">${LANG['website.common.loading.message']}</span>
	</div>
	<iframe allowtransparency='true' frameborder="0" width="100%" height="100%" id="contentFrame" name="contentFrame" scrolling="no" src=""></iframe>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.meeting.dashboard.js"></script>
</body>
</html>
