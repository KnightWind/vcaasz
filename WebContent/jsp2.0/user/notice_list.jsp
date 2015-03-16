<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/notice.css" />
</head>
<body>
<form id="queryNotice" name="queryNotice" action="/user/notice/list" method="post">
	<div class="box">
		<div class="head">
			<span class="title">${LANG['website.user.notice.title'] }</span> <!-- 公告信息 -->
			<span class="desc">${LANG['website.user.notice.desc'] }</span><!-- 您可以查看管理员发布的公告信息 -->
		</div>
	</div>
	<c:if test="${fn:length(noticeList)<=0 }">
		<div class="module">
			<div class="nodata">${LANG['website.user.notice.nodata'] }</div><!-- 此栏目暂无数据 -->
		</div>
	</c:if>
	
	<c:if test="${fn:length(noticeList)>0 }">
		<c:forEach var= "notice" items = "${noticeList}"  varStatus="status">
			<div class="module notice-module">
				<div class="module-head">
					<h3 class="title" style="cursor: default;"><i class="icon icon-dict"></i>${notice.title} (<fmt:formatDate value="${notice.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>)</h3>
					<span class="rside">${LANG['website.user.notice.publisher'] }: ${publishUserList[status.count-1]}</span>
				</div>
				<div class="module-body forcebr">
					${notice.content}
				</div>
			</div>
		</c:forEach>
		<div class="pagebar clearfix">
			<jsp:include page="page.jsp" />
		</div>
	</c:if>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
</body>
</html>