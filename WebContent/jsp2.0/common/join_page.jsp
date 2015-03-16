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
}
.biz-close:hover{
    background-image: url(/assets/images/buttons/close-hover.png);
}
</style>	
</head>
<body>
<div style="width: 474px;height:auto;overflow: hidden;">
<div class="biz-titleBar"><span class="biz-title"><i class="biz-icon icon-join"></i>${LANG['website.user.index.js.message.title.join.conf'] }</span><a onclick="return closeDialog()" class="biz-close"></a></div>

<cc:confList var="JOIN_TYPE_CONFID"/>
<cc:confList var="JOIN_TYPE_SECURE_CODE"/>
<cc:confList var="HOST_JOIN_TYPE_EMAIL"/>
<cc:confList var="JOIN_TYPE_OURURL"/>
<c:set var="userName" value=""/>
<!-- 通过会议ID进行入会 -->
<c:if test="${currentUser!=null }"><c:set var="userName" value="${currentUser.trueName }"/></c:if>
<c:if test="${joinType==JOIN_TYPE_SECURE_CODE}">
<form class="join-secure-form" id="joinSecureForm" name="joinSecureForm"  method="post">
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
      <input type="hidden" name="blank" id="blank" value="0"/>
	<p class="error">
	<span class="form-tip"><span id="userNameTip"></span></span>
	<span class="form-tip"><span id="codeTip"></span></span>
	</p>
	
	<div class="form-content">
<!-- 		<div class="form-item"> -->
<!-- 			<label class="form-title">${LANG['website.user.join.page.name'] }${LANG['website.common.symbol.colon'] }</label> -->
<!-- 			<div class="form-text"> -->
<!-- 				<input type="text" class="input-text" name="userName" id="userName"   value="${userName}"  placeholder="${LANG['website.user.join.page.name'] }" /> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="form-item row-item">
			<label class="form-title">${LANG['website.user.view.conf.conf.confCode']}${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input type="text" class="input-text"  name="code" id="code"   placeholder="${LANG['website.user.view.conf.conf.confCode']}" />
			</div>
		</div>
	</div>
	<div class="form-buttons">
		<%-- <input type="button" onclick="javascript:joinconf();" class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}"> --%>
		<input type="button"  id="joinBtn" class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}">
	</div>
</form>
</c:if>



<c:if test="${joinType==JOIN_TYPE_CONFID}">
<form class="join-secure-form" id="joinConfIdForm" name="joinConfIdForm"  method="post" action="/join">
      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	<p class="error">
		<span class="form-tip"><span id="userNameTip"></span></span>
		<span class="form-tip"><span id="cPassTip"></span></span>
	</p>
	<div class="form-content">
		<div class="form-item">
			<label class="form-title">${LANG['website.user.join.page.name'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
			<c:if test="${currentUser!=null }">
              	</c:if>
				<input  name="userName" id="userName" type="text" value="${userName}" class="input-text" placeholder="${LANG['website.user.join.page.name'] }" />
			</div>
		</div>
		 <c:if test="${passCheck==1}">
		<div class="form-item row-item">
			<label class="form-title">${LANG['website.user.join.page.cpass'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input  class="input-text" name="cPass" id="cPass"  type="password"  placeholder="${LANG['website.user.join.page.cpass'] }" />
			</div>
		</div>	
		 </c:if>
	</div>
	<div class="form-buttons">
		<%-- <input  type="button" onclick="javascript:joinconf();"  class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}"> --%>
		<input type="submit"  id="joinBtn" class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}">
	</div>
</form>
</c:if>


<c:if test="${joinType==HOST_JOIN_TYPE_EMAIL}">
<form class="join-secure-form" id="joinEmailForm" name="joinEmailForm"  method="post" action="/join">
      <input type="hidden" name="cId" id="cId" value="${cId}"/>
      <input type="hidden" name="uId" id="uId" value="${uId}"/>
      <input type="hidden" name="scode" id="scode" value="${scode}"/>
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	<!-- <p class="error"><i class="icon"></i></p> -->
	<p class="error">
		<span class="form-tip"><span id="userNameTip"></span></span>
		<span class="form-tip"><span id="cPassTip"></span></span>
	</p>
	<div class="form-content">
		<div class="form-item">
			<label class="form-title">${LANG['website.user.join.page.name'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input  name="userName" id="userName" type="text" value="${userName}" class="input-text" placeholder="${LANG['website.user.join.page.name'] }" />
			</div>
		</div>
		 <c:if test="${passCheck==1}">
		<div class="form-item row-item">
			<label class="form-title">${LANG['website.user.join.page.cpass'] }${LANG['website.common.symbol.colon'] }</label>
			<div class="form-text">
				<input name="cPass" id="cPass"  type="password"   class="input-text" placeholder="${LANG['website.user.join.page.cpass'] }" />
			</div>
		</div>	
		</c:if>
	</div>
	<div class="form-buttons">
		<input type="submit"  id="joinBtn" class="button input-gray" value="${LANG['website.user.join.page.joinbtn']}">
		<%--onclick="javascript:joinconf();" --%>
	</div>
</form>
</c:if>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script> 
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${joinType==JOIN_TYPE_SECURE_CODE}">
	$.formValidator.initConfig({
		oneByOneVerify:true,
		formID : "joinSecureForm",
		debug : false,
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	$("#userName").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 32,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "${LANG['website.user.join.page.msg.name.notrim']}"//"姓名两边不能有空符号"
		},
		onError : "${LANG['website.user.join.page.msg.noname']}"//请输入姓名
	});
	$("#code").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "${LANG['website.user.join.page.msg.code.notrim']}"//"安全会议号码两边不能有空符号"
		},
		onError : "${LANG['website.user.join.page.msg.nocode']}"//"请输入安全会议号码！"
	});
	//setCursor("userName", $("#userName").val().length);
	</c:if>
	
	<c:if test="${joinType == JOIN_TYPE_CONFID|| joinType == HOST_JOIN_TYPE_EMAIL}">
	
	
 	$.formValidator.initConfig({
 		validatorGroup:"2",
 		oneByOneVerify:true,
 		<c:if test="${joinType==JOIN_TYPE_CONFID}">
 		formID : "joinConfIdForm",
 		</c:if>
 		<c:if test="${joinType==HOST_JOIN_TYPE_EMAIL}">
 		formID : "joinEmailForm",
 		</c:if>
 		debug : false,
 		onError : function() {
 			//alert("具体错误，请看网页上的提示");
 		}
 	});
 	$("#userName").formValidator({
 		validatorGroup:"2",
 		onShow : "",
 		onFocus : "",
 		onCorrect : ""
 	}).inputValidator({
 		min : 1,
 		max : 32,
 		empty : {
 			leftEmpty : false,
 			rightEmpty : false,
 			emptyError : "${LANG['website.user.join.page.msg.name.notrim']}"//"姓名两边不能有空符号"
 		},
 		onError : "${LANG['website.user.join.page.msg.noname']}"//请输入姓名
 	});
 	//setCursor("userName", $("#userName").val().length);
	 	<c:if test="${passCheck==1}">
	 	$("#cPass").formValidator({
	 		validatorGroup:"2",
	 		onShow : "",
	 		onFocus : "",
	 		onCorrect : ""
	 	}).inputValidator({
	 		min : 1,
	 		empty : {
	 			leftEmpty : false,
	 			rightEmpty : false,
	 			emptyError : "${LANG['website.user.join.page.msg.cpass.notrim']}"//"安全密码两边不能有空符号"
	 		},
	 		onError : "${LANG['website.user.join.page.msg.nocpass']}"//"请输入密码！"
	 	});	
	 	</c:if>
	</c:if>
	
	//加入会议
	$("#joinBtn").click(function(){
		//IE 
		if($.browser.msie){
			var code = $("#code").val();
			//getJoinMeetingUrl(code,'','');
			joinMeetingMutipartWay(code);
			sleep(1500,function(){
				var dialog = parent.$("#joinMeeting");
				dialog.trigger("closeDialog");
			});
		}else{
			$("#joinSecureForm").attr("action","/join/gotojoinPage");
			$("#joinSecureForm").submit();
		}
	});
	/**
	会议ID回车加入
	$("#code").keydown(function(event){
		if(event.keyCode == '13'){
			if($.browser.msie){//IE的回车不起作用，必须是点击事件
				var code = $("#code").val();
				getJoinMeetingUrl(code,'','');
				sleep(2000,function(){
					var dialog = parent.$("#joinMeeting");
					dialog.trigger("closeDialog");
				});
			}else{
				$("#joinSecureForm").attr("action","/join/gotojoinPage");
				$("#joinSecureForm").submit();
			}
		}
	});*/
});

function joinMeetingMutipartWay(confNo){
	//协议入会地址
	var protcolUrl = "${zoom_http}";
		protcolUrl += "join";
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&confid=";
		protcolUrl += "&pwd=";
		//alert("protcolUrl="+protcolUrl);
		$.ajax({
	      	type: "POST",
	      	url: "/join/getClickOnceStatu",
	      	async:false,//必须要同步执行 否则不能启动会议
	      	dataType:"json",
	      	success:function(data){
	      		if(data.state == 1 && data.url){
	      			var zoomurl = data.url+"?dr=https://bizconf.zoomus.cn"
	      			+"/client&action=join&confno="+confNo+"&confid=&pwd=";
	      			
	      			//alert("zoomurl="+zoomurl);
	      			window.location = zoomurl;
	      		}else{
	      			//协议启动
	      			window.location = protcolUrl;
	      		}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	//alert("error");
	        	//错误的情况直接协议启动
	        	window.location = protcolUrl;
	        }
	});  	
}


function closeDialog() {
	var dialog = parent.$("#joinMeeting");
	dialog.trigger("closeDialog");
}

window.onload=function(){
	<c:if test="${jump==true}">
	sleep(50,function(){
		<c:if test="${joinType == JOIN_TYPE_CONFID}">
			$("#joinConfIdForm").submit();
		</c:if>
		<c:if test="${joinType == HOST_JOIN_TYPE_EMAIL}">
			$("#joinEmailForm").submit();
		</c:if>
	});
	</c:if>
};

function sleep(millSeconds, callBack) {
    if (millSeconds > 0) {
        window.setTimeout(callBack, millSeconds);
    } else {
        callBack();
    }
}
</script>
</body>
</html>
