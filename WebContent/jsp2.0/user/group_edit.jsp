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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
<%--		<link type="text/css" rel="stylesheet" href="/assets/css/apps/settings.css" />--%>
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body style="padding: 0px;">
<div class="adduser-dialog">
	<form method="post" id="group-form" class="group-form">
		<div class="form-content">
			<div class="form-item username-widget">
				<label class="title">${LANG['website.user.invite.group.groupname']}${LANG['website.common.symbol.colon']}</label>
				<div class="widget">
					<input type="text" id="groupName" style="width: 170px;margin-right: 10px;" value="${group.groupName}"  class="input-text" placeholder="${LANG['website.user.invite.group.groupname']}" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="groupNameTip"></span></span>
				</div>
			</div>
			<div class="form-item remark-widget">
				<label class="title">${LANG['website.user.group.edit.remark']}${LANG['website.common.symbol.colon']}</label>
				<div class="widget">
				<textarea class="input-area"  id="groupDesc" value="${group.groupDesc}"  style="width: 260px;">${group.groupDesc}</textarea>
					<span class="count" style="padding-left: 20px;color: #B0B0B0;"><label id="nowCountSpan">0</label>/256</span>
				</div>
			</div>
		</div>
		<div class="form-buttons">
			<input type="submit" class="button input-gray" value="${LANG['website.common.option.confirm']}">
			<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>
	</form>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
	var validString = {
			groupName: {
				onShow: "${LANG['website.user.group.edit.warminfo1']}",
				onFocus: "${LANG['website.user.group.edit.warminfo1']}",
				onCorrect: "",
				emptyError: "${LANG['website.user.group.edit.warminfo3']}",
				onInputError: "${LANG['website.user.group.edit.warminfo4']}"
			},
			groupDesc: {
			}
	};
	$(document).ready(function(){
		$.formValidator.initConfig({
			formID : "group-form",
			debug : false,
			onSuccess : function() {
				saveGroup();
			},
			onError : function() {
			}
		});

		$("#groupName").formValidator({
			onShow : validString.groupName.onShow,
			onFocus : validString.groupName.onFocus,
			onCorrect : validString.groupName.onCorrect
		}).inputValidator({
			min : 1,
			max:32,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.groupName.emptyError
			},
			onError : validString.groupName.onInputError
		});
		 
		$("#groupDesc").formValidator({
			empty : true,
			onShow : "",
			onFocus : "",
			onCorrect : "",
			onEmpty : ""
		}).inputValidator({
			min : 1,
			max : 256,
			onError : ""
		});
		setCursor("groupName", $("#groupName").val().length);

		var tmpLength = countLength($("#groupDesc").val());
		$("#nowCountSpan").html(tmpLength);
		
		$("#groupDesc").keyup(function() {
			var nowLength =  countLength($("#groupDesc").val());
			if (nowLength>256) {
				$(".count").css("color", "#C91600");
			} else {
				$(".count").css("color", "#B0B0B0");
			}
			$("#nowCountSpan").text(nowLength);
		}).bind('paste', function(){
			$(this).prev('label').hide();
		});
	});
	
	function saveGroup() {
		var data = {};
		data.id = "${group.id}";
		data.groupName = $("#groupName").val();
		data.groupDesc = $("#groupDesc").val();
		app.addGroup(data, function(result) {
			if(result){
				if(result.status==1){
					top.successDialog(result.message);
					top.refreshChildIframe("/user/group/list","contentFrame");
					closeDialog();
				} else {
					top.errorDialog(result.message);
				}
			}
		});		
	}
	function closeDialog(result) {
		var dialog = top.$("#editGroupDiv");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}	

	function countLength(str){
		if(str)
		return  str.length;
		else
			return 0 ;
	}
</script>

</body>
</html>