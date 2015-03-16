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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
	<%@ include file="/jsp/common/cookie_util.jsp"%>
	
</head>
<body style="padding: 0px;">
<div class="notice-dialog">
	<div class="article">
		<h3 class="title">${notice.title}</h3>
		<p class="time"><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/></p>
		<div class="content">
			${notice.content}
		</div>
	</div>
	<div class="notice-buttons">
		<div class="remember"><input id="noticeRemember" type="checkbox" name="noticeRemember"> <label for="noticeRemember">${LANG['website.user.notice.unshow'] }</label></div>
		<div class="form-buttons">
			<input type="button" class="button input-gray" value="${LANG['website.common.option.confirm'] }" onclick="javascript:closeDialog();">
		</div>
	</div>
</div>

<input type="hidden" id="noticeID" value="${notice.id}"/>
<script type="text/javascript">
function closeDialog(){
	var checkNotice = $("input:checkbox[name=noticeRemember]:eq(0)").attr("checked");
	if(checkNotice && checkNotice=="checked"){
		var noticeId = $("#noticeID").val();
		parent.rememberNotice(noticeId);
	//	$.cookie("noticeId", noticeId, { expires: 365 ,domain:document.domain});
	}
	var frame = parent.$("#popupNotice");
	frame.trigger("closeDialog");

}
</script>
</body>
</html>