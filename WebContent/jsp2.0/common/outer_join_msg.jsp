<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script> 
<title>${LANG['bizconf.jsp.common.join_msg.res1']}</title>

<style>
.outer-join-meeting {
	width: 474px;
	height: 292px;
	margin: 0 auto;
	border: 3px solid #9AB3C9;
	top: 50%;
	left: 50%;
	position: absolute;
	margin-top: -246px;
	margin-left: -237px;
}
.biz-titleBar { 
	position: relative; 
/* 	width: 100%; */
	background: #F9F9F9;
	background: linear-gradient(top,#F9F9F9,#EBEBEB);
	background: -moz-linear-gradient(top,#F9F9F9,#EBEBEB);
	background: -webkit-linear-gradient(top,#F9F9F9,#EBEBEB);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F9F9F9,endColorstr=#EBEBEB);
    padding: 0 15px;
	border-bottom: 1px solid #CCCCCC;
	height: 36px;
	line-height: 36px;   	
}
.biz-title {
    cursor: default;
    overflow: hidden;
    text-overflow: ellipsis;
    color: #333333;
/*     height: 33px; */
/*     line-height: 33px; */
    padding: 0 16px 0 0;
	font-family: "Microsoft Yahei","微软雅黑","宋体";
	font-size: 15px;
 
}
.icon-join {
    background: url("/assets/images/sprite/icons-png8.png") no-repeat scroll -154px -188px transparent;
    height: 18px;
    margin: 7px 5px 7px 0;
    width: 18px;
}
.biz-icon {
    display: inline-block;
    vertical-align: middle;
}
.biz-close {
    display: block;
    outline: none;
    position: absolute;
    text-decoration: none;
    background: url("/assets/images/buttons/close.png") no-repeat scroll center center transparent;
    height: 13px;
    right: 10px;
    text-indent: -9999em;
    top: 10px;
    width: 13px;
    display: none;
}
.biz-close:hover{
    background-image: url(/assets/images/buttons/close-hover.png);
}
.form-buttons {
    display: none;
}
</style>
</head>
<body>
<div class="outer-join-meeting">
	<div class="biz-titleBar"><span class="biz-title"><i class="biz-icon icon-join"></i>${LANG['website.user.conf.list.enter.meeting.failure']}</span><a onclick="return closeDialog()" class="biz-close"></a></div>
	<div class="prompt-dialog">
		<div class="wrapper">
			<div class="prompt-section">
				<div class="prompt-error">
					<div class="face-failure">
						<c:set var="msgName" value="outer.conf.join.error.${errorCode}"/>${LANG[msgName]}
					</div>
				</div>		
			</div>
		</div>
		<div class="form-buttons">
			<input type="button" onclick="javascript:closeDialog();" class="button input-gray anchor-cancel" value="${LANG['website.common.option.confirm']}" />
		</div>
	</div>
</div>
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
	//resetFrameHeight();
	</c:if>
}
function resetFrameHeight(){
    var pageHeight=document.body.scrollHeight;
    var pageHeight=pageHeight+5;
  //  parent.changeIframeSize("dialogFrame",0,pageHeight);
}
//initPage();
		
</script>

</body>
</html>
