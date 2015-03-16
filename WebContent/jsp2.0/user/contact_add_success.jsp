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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
</head>
<body style="padding: 0px;">
<div class="import-dialog" style="height: 482px;overflow: hidden;">
	<c:if test="${successFlag eq 1 }"> 
	<div class="face-success"  style="margin-top:50px; height:212px;overflow-y: auto;">${LANG['website.user.contact.addsuccess.successinfo']}
<%--		<c:if test="${excelimp}">--%>
<%--			<div class="box">--%>
<%--					<div class="head">--%>
<%--						共${total }条信息，导入${succnum}条。--%>
<%--					</div>--%>
<%--					<c:if test="${fn:length(repts)>0 }">--%>
<%--					<div class="body">--%>
<%--					<p style="padding-left: 30px;"> 重复的有：</p>--%>
<%--						<ul class="items clearfix">--%>
<%--							<c:forEach var="rpt" items ="${repts}"  varStatus="status">	--%>
<%--								<li>${rpt }</li>--%>
<%--							</c:forEach>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--				</c:if>--%>
<%--				<c:if test="${fn:length(unsaves)>0 }">--%>
<%--					<div class="body">--%>
<%--					<p style="padding-left: 30px;">数据校验失败的有：</p>--%>
<%--						<ul class="items clearfix">--%>
<%--							<c:forEach var="unsave" items ="${unsaves}"  varStatus="status">	--%>
<%--								<li>${unsave}</li>--%>
<%--							</c:forEach>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--				</c:if>--%>
<%--			</div>--%>
<%--		</c:if>--%>
	</div>
	</c:if>
	<div class="form-buttons">
		<input type="button" value="${LANG['website.common.option.confirm']}" onclick="closeDialog();" class="button input-gray">
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">

function closeDialog() {
	var dialog = top.$("#impSuccessDialog");
	dialog.trigger("closeDialog");
}
</script>

</body>
</html>