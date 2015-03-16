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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
	<cc:confList var="JOIN_TYPE_CONFID"/>
</head>
<body>
<form method="post" class="join-intime-form" name="immediaForm" id="immediaForm"  action="/user/conf/createNewImmediatelyConf">
	<p class="error">
		<span class="form-tip"><span id="confNameTip"></span></span>
	</p>
	<div class="join-intime-section">
		<div class="form-content">
			<div class="form-item">
				<label class="form-title">${LANG['website.user.create.immedia.conf.confname']}</label>
				<div class="form-text">
					<input type="text" class="input-text" id="confName" name="confName"  placeholder="${LANG['website.user.create.immedia.conf.confname']}" value="${confNameDefault}" />
				</div>
			</div>	
		</div>
	</div>
	<div class="form-buttons">
		<input type="submit" class="button input-gray" value="${LANG['website.user.create.immedia.startnow']}">
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
var CREATE_IMME_CONSTANT={
		confname_empty:"${LANG['website.user.create.immedia.js.message.confname.empty']}",//"请输入会议主题"
		name_unspace:"${LANG['website.user.create.immedia.js.message.confname.unspace']}",// "会议主题不能有空符号"
		name_length:"${LANG['website.user.create.immedia.js.message.confname.length']}",//  "1-32个任意字符！"
		joining:"${LANG['website.user.create.immedia.js.message.joining']}"//正在进入会议
};
	$(document).ready(function(){
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "immediaForm",
			debug : false,
			onSuccess : function() {
				editMeeting();
			},
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		$("#confName").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			max: 32,
			onErrorMin: CREATE_IMME_CONSTANT.confname_empty,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : CREATE_IMME_CONSTANT.name_unspace
			},
			onError : CREATE_IMME_CONSTANT.name_length
		});
		setCursor("confName", $("#confName").val().length);
	});
		
	function editMeeting(){
		var data = {};
		data.confName = $("#confName").val();
		app.tempMeeting(data, function(result) {
			if(result && result.status==1){
				parent.joinMeeting("${JOIN_TYPE_CONFID}",result.id);
				parent.showURL("/user/conf/getControlPadIndex?userRole=1");
				closeDialog(result);
				//var frame = parent.$("#immediateConf");
				//$(frame).find("iframe").attr("src", "/join?joinType=${JOIN_TYPE_CONFID}&cId="+result.id);
			} else {
				parent.errorDialog(result.message);
			}
		}, {message:CREATE_IMME_CONSTANT.joining, ui:parent});
	};

	function closeDialog(result) {
		var dialog = parent.$("#immediateConf");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
</script>	
</body>
</html>