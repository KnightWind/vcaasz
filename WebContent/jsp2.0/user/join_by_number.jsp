<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.dialog.css" />
</head>
<body>
<form class="join-secure-form">
	<p class="error"><i class="icon"></i>Please enter the security Meeting No.</p>
	<div class="form-content">
		<div class="form-item">
			<label class="form-title">${LANG['com.bizconf.vcaasz.entity.UserBase.trueName']}${LANG['website.common.symbol.colon']}</label>
			<div class="form-text">
				<input type="text" class="input-text" placeholder="${LANG['com.bizconf.vcaasz.entity.UserBase.trueName']}" />
			</div>
		</div>
		<div class="form-item row-item error-item">
			<label class="form-title">${LANG['bizconf.jsp.conf.bizconf.securityconfnumber']}${LANG['website.common.symbol.colon']}</label>
			<div class="form-text">
				<input type="text" class="input-text" placeholder="${LANG['bizconf.jsp.conf.bizconf.securityconfnumber']} />
			</div>
		</div>	
	</div>
	<div class="form-buttons">
		<input type="button" class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}">
	</div>
</form>
</body>
</html>