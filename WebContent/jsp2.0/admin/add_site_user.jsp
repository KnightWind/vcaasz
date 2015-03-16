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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.settings.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
</head>
<body style="padding: 0px;">
	<div class="manager-body">
		<form id="saveAdminUserForm" action="" method="post" class="saveAdminUserForm">
			<input id="userId" type="hidden" name="id" value="${user.id}"/>
			<div class="form-body">
				<div class="form-content">
<%--					<div class="form-item">--%>
<%--						<label class="title">${LANG['website.user.profile.loginname'] }：</label>--%>
<%--						<div class="widget">--%>
<%--							<input id="loginName" name="loginName" type="text" <c:if test="${(user.userRole eq 1) && (siteBase.chargeMode==1 || siteBase.chargeMode==2)}">readonly  style='border:0px;'</c:if> value="${user.loginName}" class="input-text" />--%>
<%--							<span class="form-required"></span>--%>
<%--							<span class="form-tip"><span id="loginNameTip"></span></span>--%>
<%--						</div>--%>
<%--					</div>--%>
					<div class="form-item">
						<label class="title">${LANG['website.admin.userbase.title.emailaddress'] }：</label>
						<div class="widget">
							<input type="text" name="userEmail" id="userEmail" value="${user.userEmail}" class="input-text" readonly="readonly"/>
							<span class="form-required"></span>
							<span class="form-tip"><span id="userEmailTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.siteadmin.edit.password'] }</label>
						<div class="widget">
							<input id="loginPass" name="loginPass" type="password" value=""  class="input-text" />
							<c:if test="${empty user}">
								<span class="form-required"></span>
							</c:if>
							<span class="form-tip"><span id="loginPassTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.siteadmin.edit.confirmpassword'] }</label>
						<div class="widget">
							<input id="loginPass2" name="loginPass2" type="password" value=""  class="input-text" />
							<c:if test="${empty user}">
								<span class="form-required"></span>
							</c:if>
							<span class="form-tip"><span id="loginPass2Tip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.name'] }：</label>
						<div class="widget">
							<input name="trueName" id="trueName" type="text" value="${user.trueName}" class="input-text" />
							<span class="form-required"></span>
							<span class="form-tip"><span id="trueNameTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.englishname'] }：</label>
						<div class="widget">
							<input name="enName" id="enName" type="text" value="${user.enName}" class="input-text" />
							<span class="form-tip"><span id="enNameTip"></span></span>
						</div>
					</div>
					
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.telephone'] }：</label>
						<div class="widget">
							<input type="text" name="mobile" id="mobile" value="${user.mobile}" class="input-text" />
							<span class="form-tip"><span id="mobileTip"></span></span>
						</div>
					</div>
					
					<!-- 包月的情况可以设置并发数，计时的情况则设置默认最大并发数 -->
				   <div class="form-item">
					   <c:choose>
						   	<c:when test="${currentsite.hireMode eq 1}">
				   				<label class="title">${LANG['bizconf.jsp.system.add_site_user.res2']}：</label>
						   		<div class="widget">
									<input id="licNum" value="${user.numPartis}" name="licNum"  type="text" class="input-text" readonly="readonly"/>
									<span class="form-tip"><span id="mobileTip"></span></span>
								</div>
						   	</c:when>
						   	<c:otherwise>
								<input id="licNum" value="200" name="licNum"  type="hidden" class="input-text" readonly="readonly">
						   	</c:otherwise>
					   </c:choose>
					</div>
					<div class="form-item remark-widget">
						<label class="title">${LANG['website.user.contact.edit.remark']}：</label>
						<div class="widget">
							<textarea class="input-area"  id="remark" style="margin-right: 20px;"  value="${user.remark}">${user.remark}</textarea>
<%--							<span class="form-tip"><span id="remarkTip"></span></span>--%>
							<span class="count">
								<span id='nowCountSpan'>0</span>/256
							</span>
						</div>
					</div>			
				</div>
			</div>
			<div class="form-buttons">
				<input type="submit" class="button input-gray" value="${LANG['website.common.option.confirm']}"> 
				<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
			</div>
		</form>
	</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></SCRIPT>
<script type="text/javascript">
var validString = {
		loginName: {
			onShow: "${LANG['website.user.profile.val.loginname'] }",
			onFocus: "${LANG['website.user.profile.val.loginname'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.loginname.space'] }",
			onInputError: "${LANG['website.user.profile.val.loginname.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.loginname.onregexperror'] }"
		},
		loginPass: {
			onShow: "${LANG['website.user.profile.val.newpass.onshow'] }",
			onFocus: "${LANG['website.user.profile.val.newpass.onfocus'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.newpass.space'] }",
			onInputError: "${LANG['website.user.profile.val.newpass.oninputerror'] }",
			tip1:"${LANG['website.user.profile.val.newpass.tip1'] }"
		},
		loginPass2: {
			onShow: "",
			onFocus: "",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.confrim.space'] }",
			onInputError: "${LANG['website.user.profile.val.confrim.pass.oninputerror'] }",
			onCompareError: "${LANG['website.user.profile.val.confrim.twice'] }"
		},
		trueName: {
			onShow: "${LANG['website.user.profile.val.truename'] }",
			onFocus: "${LANG['website.user.profile.val.truename'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.truename.space'] }",
			onInputError: "${LANG['website.user.profile.val.truename.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.truename.onregexperror'] }"
		},
		enName: {
			onShow: "${LANG['website.user.profile.val.enname'] }",
			onFocus: "${LANG['website.user.profile.val.enname'] }",
			onCorrect: "",
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.enname.space'] }",
			onInputError: "${LANG['website.user.profile.val.enname.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.enname.onregexperror'] }"
		},
		userEmail: {
			onShow: "${LANG['website.user.profile.val.email'] }",
			onFocus: "${LANG['website.user.profile.val.email'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.email.space'] }",
			onInputError: "${LANG['website.user.profile.val.email.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.email.onregexperror'] }"
		},
		mobile: {
			onShow: "${LANG['website.user.profile.val.mobile'] }",
			onFocus: "${LANG['website.user.profile.val.mobile'] }",
			onCorrect: "",
			onEmpty: "${LANG['website.site.user.meeting.sms.warninfo02']}",
			emptyError: "${LANG['website.user.profile.val.mobile.space'] }",
			onInputError: "${LANG['website.user.profile.val.mobile.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.mobile.onregexperror'] }"
		}
		
};
$(document).ready(function(){
		
		$.formValidator.initConfig({
			oneByOneVerify:true,
			wideWord: true,
			formID : "saveAdminUserForm",
			onSuccess : function() {
				saveSysUser();
			},
			debug : false,
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		$("#loginName").formValidator({
			onShow : validString.loginName.onShow,
			onFocus : validString.loginName.onFocus,
			onCorrect : validString.loginName.onCorrect
		}).inputValidator({
			min : 4,
			max : 16,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.loginName.emptyError
			},
			onError : validString.loginName.onInputError
		}).regexValidator({
				regExp : "loginname",
				dataType : "enum",
				onError : validString.loginName.onRegExpError
		});
		var userId = $("#userId").val();
		if (userId) {				//对用户进行修改操作
			$("#loginPass").formValidator({
				empty : true,
				onShow : validString.loginPass.onShow,
				onFocus : validString.loginPass.onFocus,
				onCorrect : validString.loginPass.onCorrect,
				onEmpty : ""
			}).inputValidator({
				min : 6,
				max : 16,
				/*
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.loginPass.emptyError
				},
				*/
				onError : validString.loginPass.onInputError
			}).functionValidator({
				fun:function(val,elem){
			    	var loginPass = $("#loginPass").val();
					//var loginPass2 = $("#loginPass2").val();
					//位数,字母字符和数字的校验
					if(!ValidatorPass(loginPass)){
						return validString.loginPass.tip1;
					}else{
						return true;
					}
				}
			});
			
			$("#loginPass2").formValidator({
				//empty : true,
				onShow : validString.loginPass2.onShow,
				onFocus : validString.loginPass2.onFocus,
				onCorrect : validString.loginPass2.onCorrect,
				onEmpty : ""
			}).inputValidator({
				min : 0,
				/*
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.loginPass2.emptyError
				},*/
				onError : validString.loginPass2.onInputError
			}).functionValidator({
			    fun:function(val,elem){
			    	var loginPass = $("#loginPass").val();
					var loginPass2 = $("#loginPass2").val();
					
					if (loginPass!=loginPass2) {
					    return validString.loginPass2.onCompareError;
				    }else{
					    return true;
				    }
					//位数,字母字符和数字的校验
					if(!ValidatorPass(loginPass2)){
						return validString.loginPass.tip1;
					}
				}
			});
		} else {
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
						return validString.loginPass.tip1;	
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
			}).functionValidator({
			    fun:function(val,elem){
			    	var loginPass = $("#loginPass").val();
					var loginPass2 = $("#loginPass2").val();
					
					if (loginPass!=loginPass2) {
					    return validString.loginPass2.onCompareError;
				    }else{
					    return true;
				    }
					//位数,字母字符和数字的校验
					if(!ValidatorPass(loginPass2)){
						return validString.loginPass.tip1;
					}
				}
			});
		}
		
		$("#trueName").formValidator({
			onShow : validString.trueName.onShow,
			onFocus : validString.trueName.onFocus,
			onCorrect : validString.trueName.onCorrect
		}).inputValidator({
			min : 1,
			max : 32,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.trueName.emptyError
			},
			onError : validString.trueName.onInputError
		});
		$("#enName").formValidator({
			empty:true,
			onShow : validString.enName.onShow,
			onFocus : validString.enName.onFocus,
			onCorrect : validString.enName.onCorrect,
			onEmpty:validString.enName.onEmpty
		}).inputValidator({
			min : 0,
			max: 32,
			onError : validString.enName.onInputError
		}).regexValidator({
				regExp : "enname",
				dataType : "enum",
				onError : validString.enName.onRegExpError
		});

		$("#userEmail").formValidator({
			onShow : validString.userEmail.onShow,
			onFocus : validString.userEmail.onFocus,
			onCorrect : validString.userEmail.onCorrect
		}).inputValidator({
			min : 1,
			max : 64,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.userEmail.emptyError
			},
			onError : "${LANG['website.user.contact.edit.emailtoolong']}"
				//validString.userEmail.onInputError
		}).regexValidator({
			regExp : "email",
			dataType : "enum",
			onError : validString.userEmail.onRegExpError
		});

		$("#mobile").formValidator({
			empty : true,
			onShow : validString.mobile.onShow,
			onFocus : validString.mobile.onFocus,
			onCorrect : validString.mobile.onCorrect,
			//onEmpty : validString.mobile.onEmpty
			onEmpty : ""
		}).inputValidator({
			/*
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : ""
			},
			*/
			onError : validString.mobile.onInputError
		}).regexValidator({
			regExp : "tel",
			dataType : "enum",
			onError : validString.mobile.onRegExpError
		});
		
		$("#remark").formValidator({
			empty : true,
			onShow : "",
			onFocus : "",
			onCorrect : "",
			onEmpty : ""
		}).inputValidator({
			min : 0,
			max : 256,
			onError : ""
		});
		
		var tmpLength = byteLength($("#remark").val());
		$("#nowCountSpan").html(tmpLength);
		if (tmpLength>256) {
			$(".count").css("color", "#C91600");
		} else {
			$(".count").css("color", "#B0B0B0");
		}		
		
		$("#remark").keyup(function() {
			var nowLength=byteLength($(this).val());
			if (nowLength>256) {
				$(".count").css("color", "#C91600");
			} else {
				$(".count").css("color", "#B0B0B0");
			}
			$("#nowCountSpan").text(nowLength);
		}).bind('paste', function(){
			$(this).prev('label').hide();
		});
		 
		
		//update passworld required to verify or disable
		var userId = "${user.id}";
		if (userId && userId.length>0) {
			$("#loginPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16], equalTo: '#loginPass'});
		}
		
		//setCursor("loginName", $("#loginName").val().length);
	});
	
function saveSysUser() {
	var userId = "${user.id}";
	var user = {};
	user.loginName = $("#loginName").val();
	user.loginPass = $("#loginPass").val();
	user.trueName = $("#trueName").val();
	user.enName = $("#enName").val();
	user.userEmail = $("#userEmail").val();
	user.mobile = $("#mobile").val();
	user.remark = $("#remark").val();
	user.numPartis = $("#licNum").val();	//最大方数
	user.siteId = "${currentsite.id}";
	
	
	if (userId && userId.length>0) {
		user.id = userId;
		app.updateHost(user, function(result) {
			if(result.status == 1){
				top.successDialog("${LANG['system.site.update.success']}");
				top.showURL("/admin/site/listHosts");
			}
			var frame = parent.$("#eidtHostDialog");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.change']}", ui:parent});
	}else{
		
	}
	
}
</script>
</body>
</html>