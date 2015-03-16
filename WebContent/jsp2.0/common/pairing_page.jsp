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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
<style>
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
    background: url("/assets/images/sprite/h323.png") no-repeat scroll transparent;
    height: 28px;
    width: 28px;
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
}
.biz-close:hover{
    background-image: url(/assets/images/buttons/close-hover.png);
}
</style>	
</head>
<body>
<div style="width: 474px;height:auto;overflow: hidden;">
<div class="biz-titleBar"><span class="biz-title"><i class="biz-icon icon-join"></i>H323/SIP终端接入</span><a onclick="return closeDialog()" class="biz-close"></a></div>
<form class="join-secure-form">
	<p class="error">
		<span class="form-tip"><span id="userNameTip"></span></span>
		<span class="form-tip"><span id="cPassTip"></span></span>
	</p> 
	<div class="form-content">
		<div class="form-item">
			<label class="form-title">${LANG['website.user.join.page.code'] } ${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input  name="confNo" id="confNo" type="text" value="${confNo}" class="input-text" placeholder="${LANG['website.user.join.page.code'] }" />
			</div>
		</div>
		 
		<div class="form-item row-item">
			<label class="form-title">${LANG['website.user.joinmeeting.page.pairCode']} ${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input  class="input-text" name="pairCode" id="pairCode"  type="text"  placeholder="${LANG['website.user.joinmeeting.page.pairCode']}" />
			</div>
		</div>	
	</div>
	<div class="form-buttons">
		<input type="button"  id="joinBtn" class="button input-gray" value="${LANG['website.user.joinmeeting.page.connectbutton']}">
	</div>
 
</form>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script> 
<script type="text/javascript">
	function closeDialog() {
		var dialog = parent.$("#joinfromsipor323");
		dialog.trigger("closeDialog");
	}
	
	
	function pairingMeeting(confNo,pairCode){
		if(!confNo){
			top.errorDialog("${LANG['website.user.pairingmcu.page.alertinfo0']}");
			$("#confNo").focus();
			return false;
		}else if(!pairCode){
			top.errorDialog("${LANG['website.user.pairingmcu.page.alertinfo1']}");
			$("#pairCode").focus();
			return false;
		}
		$.ajax({
	      	type: "POST",
	      	url: "/join/pairingMeeting",
	      	data:{'confNo':confNo,'pairCode':pairCode},
	      	async:false,
	      	dataType:"json",
	      	success:function(data){
	      		//配对成功
	      		 if(data.state == 1){
	      			top.successDialog("${LANG['website.user.pairingmcu.page.alertinfo2']}");
	      			setTimeout("closeDialog()",1000);
	      		//会议ID不对
	      		 }else if(data.state == 3001){
	      			 top.errorDialog("${LANG['website.user.pairingmcu.page.alertinfo3']}");
	      		//配对码不对
	      		 }else if(data.state == 3021){
	      			 top.errorDialog("${LANG['website.user.pairingmcu.page.alertinfo4']}");
	      		//其他原因导致配对不成功
	      		 }else{
	      			 top.errorDialog("${LANG['website.user.pairingmcu.page.alertinfo5']}");
	      		 }
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	 alert("${LANG['website.user.pairingmcu.page.alertinfo6']}");
	        }
	});  	
}
	
	$(document).ready(function(){
		$("#joinBtn").click(function(){
			var confNo = $("#confNo").val();
			var pairCode = $("#pairCode").val();
			pairingMeeting(confNo,pairCode);
		});
		
	});
</script> 
</body>
</html>
