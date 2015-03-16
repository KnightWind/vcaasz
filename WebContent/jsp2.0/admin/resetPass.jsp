<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body style="padding: 0px;">
<div class="modifypwd-dialog modifypwd-dialog-admin">
	<div class="wrapper">
		<form name="resetPassForm" id="resetPassForm" action="" method="post">
			<div class="modifypwd-section">
				<div class="form-item">
					<label class="title">${LANG['website.user.resetpass.page.oldpass'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<input type="password" name="oldpassword" id="oldpassword" value="" class="input-text" />
						<span class="required"></span>
						<span class="form-tip"><span id="oldpasswordTip"></span></span>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.user.resetpass.page.newpass'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<input type="password" name="loginPass" id="loginPass" value="" class="input-text" />
						<span class="required"></span>
						<span class="form-tip"><span id="loginPassTip"></span></span>
					</div>
				</div>
				<div class="form-item confirm-item">
					<label class="title">${LANG['website.user.resetpass.page.confirmpass'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<input type="password" name="loginPass2" id="loginPass2" class="input-text" onkeypress="enterKey();"/>
						<span class="required"></span>
						<span class="form-tip"><span id="loginPass2Tip"></span></span>
					</div>
				</div>
			</div>
			<div class="form-buttons">
				<input type="submit" class="button input-gray" value="${LANG['website.common.option.confirm'] }"> 
				<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel'] }</a>
			</div>
		</form>
	</div>
	</div>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></script>
	<script type="text/javascript">
		var validString = {
				oldPassWord: {
					onShow: "",
					onFocus: "",
					onCorrect: "",
					emptyError: "${LANG['website.user.profile.val.oldpass.space'] }",
					onInputError: "${LANG['website.user.profile.val.oldpass.oninputerror'] }"
				},
				loginPass: {
					onShow: "${LANG['website.user.profile.val.newpass.onshow'] }",
					onFocus: "${LANG['website.user.profile.val.newpass.onfocus'] }",
					onCorrect: "",
					emptyError: "${LANG['website.user.profile.val.newpass.space'] }",
					onInputError: "${LANG['website.user.profile.val.newpass.oninputerror'] }",
					safeError:"${LANG['website.user.profile.val.newpass.tip1'] }"
				},
				loginPass2: {
					onShow: "",
					onFocus: "",
					onCorrect: "",
					emptyError: "${LANG['website.user.profile.val.confrim.space'] }",
					onInputError: "${LANG['website.user.profile.val.confrim.pass.oninputerror'] }",
					onCompareError: "${LANG['website.user.profile.val.confrim.twice'] }"
				}
		};
		$(document).ready(function(){
			$.formValidator.initConfig({
				oneByOneVerify:true,
				formID : "resetPassForm",
				onSuccess : function() {
					resetPass();
				},
				debug : false,
				onError : function() {
					//alert("具体错误，请看网页上的提示");
				}
			});
			$("#oldpassword").formValidator({
				onShow : validString.oldPassWord.onShow,
				onFocus : validString.oldPassWord.onFocus,
				onCorrect : validString.oldPassWord.onCorrect
			}).inputValidator({
				min : 1,
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.oldPassWord.emptyError
				},
				onError : validString.oldPassWord.onInputError
			});
			$("#loginPass").formValidator({
				onShow : validString.loginPass.onShow,
				onFocus : validString.loginPass.onFocus,
				onCorrect : validString.loginPass.onCorrect
			}).inputValidator({
				min : 6,
				max : 16,
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.loginPass.emptyError
				},
				onError : validString.loginPass.onInputError
			}).functionValidator({
				fun:function(val,elem){
			    	var loginPass = $("#loginPass").val();
					//位数,字母字符和数字的校验
					if(!ValidatorPass(loginPass)){
						return validString.loginPass.safeError;	
					}else{
						return true;
					}
				}
			});
			
			$("#loginPass2").formValidator({
				onShow : validString.loginPass2.onShow,
				onFocus : validString.loginPass2.onFocus,
				onCorrect : validString.loginPass2.onCorrect
			}).inputValidator({
				min : 6,
				max : 16,
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.loginPass2.emptyError
				},
				onError : validString.loginPass2.onInputError
			}).compareValidator({
				desID : "loginPass",
				operateor : "=",
				onError : validString.loginPass2.onCompareError
			});
		});
		
		function closeDialog(){
			var frame = parent.$("#resetPass");
			frame.trigger("closeDialog");
		
		}
		
		function enterKey(){
		    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异   
		    if (event.keyCode == 13){       //监听回车键
		    	resetPass();
		    } 
		}
		
		function resetPass(){
			var data = {};
			data.orgPass = $("#oldpassword").val();
			data.loginPass = $("#loginPass").val();
			app.forceResetAdminPass(data, function(result) {
				if(result){
					if(result.status=="1"){
						parent.successDialog(result.message);
						closeDialog();
					} else {
						parent.errorDialog(result.message);
					}
				}
			}, {message:"${LANG['website.common.option.save.message']}...", ui:parent});	
		}
	</script>
</body>
</html>