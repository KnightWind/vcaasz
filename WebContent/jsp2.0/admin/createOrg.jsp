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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
</head>
<body style="padding: 0px;">
<div class="adduser-dialog">
	<form method="post" id="organizForm" class="organizForm">
		<input id="orgPId" type="hidden" value="${pId}" />
		<div class="form-content">
			<div class="form-item username-widget">
				<label class="title">${LANG['website.admin.org.create.orgname'] }</label>
				<div class="widget">
					<input type="text" id="orgName" style="width: 170px;margin-right: 10px;" value="${siteOrg.orgName}"  class="input-text" />
					<span class="form-required"></span>
					<span class="form-tip"><span id="orgNameTip"></span></span>
				</div>
			</div>
			<div class="form-item remark-widget">
				<label class="title">${LANG['website.admin.org.create.orgdesc'] }</label>
				<div class="widget">
					<textarea class="input-area"  id="orgDesc"   value=""  style="width: 260px;">${siteOrg.orgDesc}</textarea>
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
			orgName: {
				onShow: "${LANG['website.user.group.edit.warminfo1']}",
				onFocus: "${LANG['website.user.group.edit.warminfo1']}",
				onCorrect: "",
				emptyError: "${LANG['website.user.group.edit.warminfo3']}",
				onInputError: "${LANG['website.user.group.edit.warminfo4']}"
			},
			orgDesc: {
			}
	};
	$(document).ready(function(){
		$.formValidator.initConfig({
			formID : "organizForm",
			wideWord: true,
			debug : false,
			onSuccess : function() {
				saveOrganiz();
			},
			onError : function() {
			}
		});

		$("#orgName").formValidator({
			onShow : validString.orgName.onShow,
			onFocus : validString.orgName.onFocus,
			onCorrect : validString.orgName.onCorrect
		}).inputValidator({
			min : 1,
			max:32,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.orgName.emptyError
			},
			onError : validString.orgName.onInputError
		});
		 
		$("#orgDesc").formValidator({
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
		setCursor("orgName", $("#orgName").val().length);

		var tmpLength = $("#orgDesc").val().Lenb();
		$("#nowCountSpan").html(tmpLength);
		
		$("#orgDesc").keyup(function() {
			var nowLength = $(this).val().Lenb();
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
	
	function saveOrganiz(){
		var frame = parent.$("#organizDiv");
		var organizID = "${siteOrg.id}";
		var organiz = {};
		organiz.orgName = $("#orgName").val();
		organiz.orgDesc = $("#orgDesc").val();
		if (organizID && organizID.length>0) {
			organiz.id = organizID;
			app.updateOrganiz(organiz, function(result) {
				frame.trigger("closeDialog", [result]);
			},{message:"${LANG['bizconf.jsp.admin.createOrg.res1']}...", ui:parent});
		} else {
			organiz.parentId = $("#orgPId").val();
			app.createOrganiz(organiz, function(result) {
				frame.trigger("closeDialog", [result]);
			},{message:"${LANG['bizconf.jsp.admin.arrange_org_user.res4']}...", ui:parent});	
		}
	}
	function closeDialog(result) {
		var dialog = parent.$("#organizDiv");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}	
</script>

</body>
</html>