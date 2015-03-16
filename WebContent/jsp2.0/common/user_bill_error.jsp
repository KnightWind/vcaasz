<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${baseUrlStatic}/css/user/reset.css" />
<link rel="stylesheet" type="text/css"
	href="${baseUrlStatic}/css/user/popupbox.css" />
<link rel="stylesheet" type="text/css"
	href="${baseUrlStatic}/css/user/box.css" />
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<title>${LANG['website.retpass.error.title']}</title>
</head>
<body>
	<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0"
		id="joinMtgErrorDiv" style="margin-left: 35%;margin-right: 20%;margin-top: 5%">
		<tbody>
			<tr class="no-header">
				<td class="overlay-hdL"></td>
				<td class="overlay-hdC"></td>
				<td class="overlay-hdR"></td>
			</tr>
			<tr>
				<td class="overlay-bdL"></td>
				<td class="overlay-content">
					<div class="First_Steps_quick_a" style=" background:#FFF">
						<div class="First_Steps_title_a" style="height: 52px;">
							<h3 class="tit_a">${LANG['bizconf.jsp.admin.index.res13']}</h3>
						</div>
						<div style=" background:#fff">
							<img class="toa_quick" src="/static/images/min.jpg" width="410"
								height="1" />
						</div>
						<div class="First_Steps_top" style=" background:#FFF"></div>
						<div class="First_Steps_main_quick" style="height: 82px;">
							<div class="prompt_message_main"
								style="background-color:#FFFFFF;">
								<dl>
									<dt>
										<img src="/static/images/add_messages02.png" width="26"
											height="26" />
									</dt>
									<dd>
										<c:set var="msgName"
											value="website.admin.bill.error.tip.${errorCode}" />
										<p>${LANG[msgName]}</p>
									</dd>
								</dl>
							</div>
						</div>
					</div></td>
				<td class="overlay-bdR"></td>
			</tr>
			<tr>
				<td class="overlay-ftL"></td>
				<td class="overlay-ftC"></td>
				<td class="overlay-ftR"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>

<script type="text/javascript">
function initPage(){
<c:if test="${errorCode!=null && errorCode!=''}">
	$("#joinMtgErrorDiv").show();
	resetFrameHeight();
</c:if>
}
function closeDialog() {
    var topUrl=top.location+"";//.toLowerCase();
    if(topUrl.indexOf("?")>=0){
    	var domain="${domain}";
    	top.location="http://"+domain;
    }
	var dialog = parent.$("#joinMeeting");
	dialog.trigger("closeDialog");
}
window.onload=function(){
	var frame = parent.$("#joinMeeting");
	frame.trigger("loaded");
	<c:if test="${errorCode!=null && errorCode!=''}">
	$("#joinMtgErrorDiv").show();
	resetFrameHeight();
</c:if>
}
function resetFrameHeight(){
    var pageHeight=document.body.scrollHeight;
    var pageHeight=pageHeight+5;
    parent.changeIframeSize("dialogFrame",0,pageHeight);
}
//initPage();
</script>
