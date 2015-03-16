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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function DrawImage(ImgD,iwidth,iheight){
	    var image=new Image();
	    image.src=ImgD.src;
	    if(image.width>0 && image.height>0){
	      if(image.width/image.height>= iwidth/iheight){
	          if(image.width>iwidth){
	              ImgD.width=iwidth;
	              ImgD.height=(image.height*iwidth)/image.width;
	          }else{
	              ImgD.width=image.width;
	              ImgD.height=image.height;
	          }
	      }else{
	          if(image.height>iheight){
	              ImgD.height=iheight;
	              ImgD.width=(image.width*iheight)/image.height;
	          }else{
	              ImgD.width=image.width;
	              ImgD.height=image.height;
	          }
	      }
	    }
	}
	</script>
	<!--[if IE 6]>
	<script type="text/javascript" src="/assets/js/lib/jquery.bgiframe-2.1.2.js"></script>
	<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
	<![endif]-->
</head>
<body>
<div class="login-dialog">
	<div class="brand fix-png">
		<div class="logo-img">
			<img src="/assets/images/login-icon.png" class="logo fix-png" alt="标志" onload="javascript:DrawImage(this,120,60)" />				
		</div>
		<div class="site-name-one" >
       		<div class="site-name-zh nobr">${LANG['website.user.login.logintitle'] }</div>
       	</div>
		<a href="javascript:;" class="close anchor-cancel"></a>
	</div>
	<div class="section-login">
		<form method="post" class="login-form" id="login-form">
			<p class="error" id="dangerAlert">
				<span class="form-tip"><span id="loginNameTip"></span></span>
				<span class="form-tip"><span id="loginPassTip"></span></span>
			</p>
			<div class="form-item email-widget">
				<div class="widget">
					<input type="text" class="input-text fix-png" name="loginName" id="loginName" placeholder="${LANG['website.user.login.loginname'] }"/>
				</div>
			</div>
			<div class="form-item password-widget">
				<div class="widget">
					<input type="password" class="input-text fix-png" name="loginPass" id="loginPass" placeholder="${LANG['website.user.login.loginpass'] }"/>
				</div>
			</div>
			<div class="form-item remember-widget">
				<div class="widget">
					<span class="keep-login">
						<input type="checkbox" name="remember" id="remember">
						<label for="remember">${LANG['website.user.login.loginname.hold'] }</label>
					</span>
					<a class="forget" href="/user/password/forget" target="_blank">${LANG['website.user.login.loginpass.forget'] }${LANG['website.common.symbol.quesmark'] }</a>
				</div>
			</div>
			<div class="form-item submit-widget">
				<div class="widget">
					<button id="submit1" class="input-submit" type="submit">${LANG['website.user.login.loginbtn'] }</button>
				</div>
			</div>
		</form>	
	</div>
</div>

<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
	$(function() {
		var sessionFlag = "${userSessionFlag}";
		if(sessionFlag && sessionFlag=="true"){
			resetPage();
		}
		fillInUserName();
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "login-form",
			debug : false,
			onSuccess : function() {
				login();
			},
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		$("#loginName").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : "${LANG['website.user.login.js.message.loginname.unblank']}"
			},
			onError : "${LANG['website.user.login.js.message.loginname.nowrite']}"
		});
		$("#loginPass").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : "${LANG['website.user.login.js.message.loginpass.unblank']}"
			},
			onError : "${LANG['website.user.login.js.message.loginpass.nowrite']}"
		});
		setCursor("loginName", $("#loginName").val().length);
	});	
	function login() {
		var userBase = {};
		userBase.loginName = $("#loginName").val();
		userBase.loginPass = $("#loginPass").val();
		userBase.authCode = $("#authCode").val();
		userBase.type = $("#type").val();
		userBase.random = $("#random").val();
		app.userLogin(userBase, function(result) {
			if(result) {
				if(result.status==10){
					rememberMe();
					parent.location.href = "/";
				} else {
					$("#loginName").removeClass("input-text-correct").addClass("input-text-error");
					$("#loginPass").removeClass("input-text-correct").addClass("input-text-error");
					$("#loginNameTip").find("div").removeClass("onCorrect").addClass("onError").text(result.message);
					$("#loginPassTip").find("div").removeClass("onCorrect").addClass("onError").text(result.message);
				}
			}
		});
	}
	
	function rememberMe() {
		var checkRemember = $("#remember").attr("checked");
		if(checkRemember){
			var userName = $("#loginName").val();
			if(userName){
				$.cookie("userName", userName, { expires: 7});
			}	
		} else {
			$.cookie("userName", null);
		}
	}
	
	function fillInUserName() {
		var userName = $.cookie("userName");
		if(userName){
			$("#loginName").val(userName);
			$("#remember").attr("checked", "checked");
		}
	}
	function resetPage() {
	    window.top.reloadPage();
	}
</script>
</body>
</html>