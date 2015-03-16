<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.settings.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
	<cc:siteList var="EMPOWER_CODE_FIELD_LIST"/>
	<cc:siteList var="EMPOWER_ENABLED"/>
</head>
<body style="padding: 0px;">
	<div class="manager-body">
		<form id="saveUserForm" action="" method="post" class="saveUserForm">
			<input id="userId" type="hidden" value="${userBase.id}"/>
			<div class="form-body">
				<div class="form-content">
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.loginname'] }：</label>
						<div class="widget">
							<input id="loginName" name="loginName" type="text" <c:if test="${(userBase.userRole eq 1) && (siteBase.chargeMode==1 || siteBase.chargeMode==2)}">readonly  style='border:0px;'</c:if> value="${userBase.loginName}" class="input-text" />
							<span class="form-required"></span>
							<span class="form-tip"><span id="loginNameTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.user.edit.password'] }：</label>  <!-- 密码 -->
						<div class="widget">
							<input id="loginPass" name="loginPass" type="password" class="input-text"/>
								<c:if test="${empty userBase}">
									<span class="form-required"></span>
								</c:if>
							<span class="form-tip"><span id="loginPassTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.user.edit.password.confirm'] }：</label>  <!-- 确认密码 -->
						<div class="widget">
							<input id="loginPass2" name="loginPass2" type="password" class="input-text"/>
								<c:if test="${empty userBase}">								
									<span class="form-required"></span>
								</c:if>
							<span class="form-tip"><span id="loginPass2Tip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.name'] }：</label>
						<div class="widget">
							<input name="trueName" id="trueName" type="text" value="${userBase.trueName}" class="input-text" />
							<span class="form-required"></span>
							<span class="form-tip"><span id="trueNameTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.englishname'] }：</label>
						<div class="widget">
							<input name="enName" id="enName" type="text" value="${userBase.enName}" class="input-text" />
							<span class="form-tip"><span id="enNameTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.userbase.title.emailaddress'] }：</label>
						<div class="widget">
							<input type="text" name="userEmail" id="userEmail" value="${userBase.userEmail}" class="input-text" />
							<span class="form-required"></span>
							<span class="form-tip"><span id="userEmailTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.user.profile.telephone'] }：</label>
						<div class="widget">
							<input type="text" name="mobile" id="mobile" value="${userBase.mobile}" class="input-text" />
							<span class="form-tip"><span id="mobileTip"></span></span>
						</div>
					</div>
					<c:if test="${fn:length(orgList)>0 }">
						<div class="form-item orgListTR">
							<label class="title">${LANG['website.admin.user.edit.department'] }：</label>   <!-- 用户部门 -->
							<div class="widget">
								<c:forEach var="org" items="${orgList}" >
		                        	<span class="orgOptions" style="display: none;" level="${org.orgLevel }" value="${org.id}" pid="${org.parentId}" name="${org.orgName}"></span>
		                        </c:forEach>
								<select id="orgLevel1" name="" style="padding: 3px; border: 1px solid #ABADB3;width:80px;">
		                        	<option pid="-1" value="0">${LANG['website.admin.user.edit.select'] }</option>
				              		<c:forEach var="org" items="${orgList}" >
				              			<c:if test="${org.orgLevel==1}">
				              				<option level="${org.orgLevel }" value="${org.id}" <c:if test="${!empty dep1 && dep1 eq org.id}">selected</c:if>>${org.orgName}</option>
				              			</c:if>
				              		</c:forEach>
				              	</select>
				              	<select id="orgLevel2" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
				              		<option pid="-1" value="0">${LANG['website.admin.user.edit.select'] }</option>
				              		<c:forEach var="org" items="${orgList}" >
				              			<c:if test="${org.orgLevel==2}">
				              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep2 && dep2 eq org.id}">selected</c:if>>${org.orgName}</option>
				              			</c:if>
				              		</c:forEach>
				              	</select>
				              	<select id="orgLevel3" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
				              		<option pid="-1" value="0">${LANG['website.admin.user.edit.select'] }</option>
				              		<c:forEach var="org" items="${orgList}" >
				              			<c:if test="${org.orgLevel==3}">
				              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep3 && dep3 eq org.id}">selected</c:if>>${org.orgName}</option>
				              			</c:if>
				              		</c:forEach>
				              	</select>
				              	<select id="orgLevel4" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
				              		<option pid="-1" value="0">${LANG['website.admin.user.edit.select'] }</option>
				              		<c:forEach var="org" items="${orgList}" >
				              			<c:if test="${org.orgLevel==4}">
				              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep4 && dep4 eq org.id}">selected</c:if>>${org.orgName}</option>
				              			</c:if>
				              		</c:forEach>
				              	</select>
							</div>
						</div>
					</c:if>
					<c:choose>
						<c:when test="${siteBase.chargeMode eq 1 and empty userBase}">
							<input type="hidden" id="hid_userRole" name="userRole" value="2"/>
						</c:when>
						<c:when test="${siteBase.chargeMode eq 1 and not empty userBase}">
							<input type="hidden" id="hid_userRole" name="userRole" value="${userBase.userRole }"/>
						</c:when>
						<c:otherwise>
							<div class="form-item">
								<label class="title">${LANG['website.admin.user.edit.userrole'] }：</label>   <!-- 用户角色-->
								<div class="widget">
									<input type="radio" name="userRole" id="compereRole" value="1"
										<c:if test="${empty userBase or userBase.userRole eq '1'}">checked="checked"</c:if>/>
									<label for="compereRole">${LANG['website.admin.user.edit.userrole.role1'] }</label>
									<input type="radio" name="userRole" id="freeRole" value="2" 
										<c:if test="${userBase.userRole eq '2'}">checked="checked"</c:if>/>
									<label for="freeRole">${LANG['website.admin.user.edit.userrole.role2'] }</label>
								</div>
							</div>
							<div class="form-item" id="siteAuthorization">
								<label class="title">${LANG['website.admin.user.edit.site.empower'] }：</label>  <!-- 站点授权-->
								<div class="widget">
									<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_LIST}" varStatus="status">
										<c:set var="eachFieldName" value="${eachField[1]}"/>
										<c:if test='${sitePower[eachFieldName]==EMPOWER_ENABLED && fn:indexOf(eachFieldName,"Flag")>-1 && eachField[3]==1}'>
											<c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>
											<input class="clientOption" type="checkbox"  name="${eachFieldName}" id="${eachFieldName}" value="${EMPOWER_ENABLED}"  <c:if test="${userConfig[eachFieldName]==EMPOWER_ENABLED && userConfig.userId > 0 }"> checked </c:if> />
											<label for="${eachFieldName}">${LANG[langName]}</label>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="form-item">
						<label class="title">${LANG['website.admin.user.edit.effect.limit'] }：</label>   <!-- 用户有效期限-->
						<div class="widget">
							<input class="expiry-date" id="allDate" type="radio" name="expiryDate" value="1" checked="checked">
							<label for="allDate" style=""><span class="equal-width">${LANG['website.admin.user.edit.effect.limit.always'] }</span></label>  <!-- 一直有效 -->
							<input class="expiry-date" id="endOneDate" type="radio" name="expiryDate" value="2" >
							<label for="endOneDate" style=""><span class="equal-width">${LANG['website.admin.user.edit.effect.limit.to'] }</span></label>  <!-- 截止到 -->
							<span class="expiry-date-forever">
								<input id="expiryDateForEver" type="text" class="input-text" value="" readonly="readonly" style="width: 85px;">
							</span>
						</div>
					</div>
					<div class="form-item remark-widget">
						<label class="title">${LANG['website.user.contact.edit.remark']}：</label>
						<div class="widget">
							<textarea class="input-area"  id="remark" style="margin-right: 20px;"  value="${userBase.remark}">${userBase.remark}</textarea>
							<span class="count">
								<span id='nowCountSpan'>0</span>/256
							</span>
<%--							<span class="form-tip"><span id="remarkTip"></span></span>--%>
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
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.date.js"></SCRIPT>
<fmt:formatDate var="userExprieDate" value="${userBase.exprieDate}" pattern="yyyy-MM-dd"/>
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
			onInputError: "${LANG['website.user.profile.val.newpass.oninputerror'] }"
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
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.mobile.space'] }",
			onInputError: "${LANG['website.user.profile.val.mobile.oninputerror'] }",
			onRegExpError: "${LANG['website.user.profile.val.mobile.onregexperror'] }"
		}
		
};
$(document).ready(function(){
		var lang = getBrowserLang(); 
		if (lang=="zh-cn") {
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
		} else {
			$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
		}
		$("#expiryDateForEver").datepicker({
			changeMonth: true,
			changeYear: true,			
			dateFormat: "yy-mm-dd",
			onClose: function(dateText) {
			}
		});
		$.formValidator.initConfig({
			oneByOneVerify:true,
			wideWord: true,
			formID : "saveUserForm",
			onSuccess : function() {
				saveUser();
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
		if (userId) {
			$("#loginPass").formValidator({
				empty : true,
				onShow : validString.loginPass.onShow,
				onFocus : validString.loginPass.onFocus,
				onCorrect : validString.loginPass.onCorrect,
				onEmpty : ""
			}).inputValidator({
				min : 6,
				max : 16,
				empty : {
					leftEmpty : false,
					rightEmpty : false,
					emptyError : validString.loginPass.emptyError
				},
				onError : validString.loginPass.onInputError
			});
			
			$("#loginPass2").formValidator({
				onShow : validString.loginPass2.onShow,
				onFocus : validString.loginPass2.onFocus,
				onCorrect : validString.loginPass2.onCorrect
			}).inputValidator({
				min : 0,
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
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.userEmail.emptyError
			},
			onError : validString.userEmail.onInputError
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
			onEmpty : validString.mobile.onEmpty
		}).inputValidator({
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : ""
			},
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
			onError : " "
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
		setCursor("loginName", $("#loginName").val().length);
		
		var now = new Date();
		var endDateStr = now.add(DATETYPE_CONSTANT.MONTH,1).format("yyyy-MM-dd");
		
		/*
		var year = now.getFullYear();
		var month = now.getMonth()+2;
		month = month<10?"0"+month:month;
		var day = now.getDate();
		day = day<10?"0"+day:day;
		var tempDate = year+"-"+month+"-"+day;
		*/
		var permanetUser = "${userBase.permanentUser}";
		var userDate = "${userExprieDate}";
		if(userDate){
			if(permanetUser && permanetUser=="true"){
				//$( "#expiryDateForEver" ).val(tempDate);
				$( "#expiryDateForEver" ).val(endDateStr);
				$("#expiryDateForEver").attr("disabled",'disabled');
			} else {
				$( "#expiryDateForEver" ).val(userDate);	
			}
		} else {
			//$( "#expiryDateForEver" ).val(tempDate);	
			$( "#expiryDateForEver" ).val(endDateStr);	
			$("#expiryDateForEver").attr("disabled",'disabled');
		}
		if(permanetUser){
			if(permanetUser=="true"){
				$("input:radio[name=expiryDate]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=expiryDate]:eq(1)").attr("checked",'checked');
			}
		}
		$("input:radio[name=expiryDate]").change(function() {
			var value = $(this).val();
			if(value=="1"){
				$("#expiryDateForEver").attr("disabled",'disabled');
			} else {
				$("#expiryDateForEver").removeAttr("disabled");
			}
		});
		var userRole = "${userBase.userRole}";
		if(userRole == "2"){
			$("#siteAuthorization").hide();
		}
		$("input:radio[name=userRole]").change(function() {
			var value = $(this).val();
			if(value=="2"){
				$("#siteAuthorization").hide();
			} else {
				$("#siteAuthorization").show();
			}
		});
		$("input[name='outCallFlag']").attr("disabled", "disabled");
		var checked = $("input[name='phoneFlag']").attr("checked");
		if(checked=="checked"){
			$("input[name='outCallFlag']").removeAttr("disabled");
		}else{
			$("input[name='outCallFlag']").removeAttr("checked", "checked");
		}
		$("input[name='phoneFlag']").change(function() {
			var value = $(this).val();
			var checked = $(this).attr("checked");
			if(checked=="checked"){
				$("input[name='outCallFlag']").removeAttr("disabled");
			} else {
				$("input[name='outCallFlag']").attr("disabled", "disabled");
				$("input[name='outCallFlag']").removeAttr("checked", "checked");
			}
		});
		
		//用户部门
		
		$("#orgLevel1").change(function() {
			var level4 = $("#orgLevel4");
			var level3 = $("#orgLevel3");
			var level2 = $("#orgLevel2");
			level4.hide();
			level3.hide();
			var id = $(this).val();
			if(id==0){
				level2.hide();
				level2.find("option:eq(0)").attr("selected", "selected");
				level3.find("option:eq(0)").attr("selected", "selected");
				level4.find("option:eq(0)").attr("selected", "selected");
			} else {
				level2.empty();
				$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level2);
				$(".orgOptions").each(function(){
					var pid = $(this).attr("pid");
					var value = $(this).attr("value");
					var name = $(this).attr("name");
		            if(pid==id) {
		            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level2);
		            }
		    	});   
				level2.show();
			}
        });
		
		$("#orgLevel2").change(function() {
			var level4 = $("#orgLevel4");
			var level3 = $("#orgLevel3");
			var level1 = $("#orgLevel1");
			level4.hide();
			var id = $(this).val(); 
			if(id==0){
				level3.hide();
				level3.find("option:eq(0)").attr("selected", "selected");
				level4.find("option:eq(0)").attr("selected", "selected");
			} else {
				level3.empty();
				$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level3);
				$(".orgOptions").each(function(){
					var pid = $(this).attr("pid");
					var value = $(this).attr("value");
					var name = $(this).attr("name");
		            if(pid==id) {
		            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level3);
		            }
		    	});
				level3.show();
			}
        });
		
		$("#orgLevel3").change(function() {
			var level4 = $("#orgLevel4");
			var level2 = $("#orgLevel2");
			level4.find(".org-option").remove();
			var id = $(this).val();
			if(id==0){
				level4.hide();
				level4.find("option:eq(0)").attr("selected", "selected");
			} else {
				level4.empty();
				$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level4);
				$(".orgOptions").each(function(){
					var pid = $(this).attr("pid");
					var value = $(this).attr("value");
					var name = $(this).attr("name");
		            if(pid==id) {
		            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level4);
		            }
		    	});
				level4.show();
			}
        });
		
		$("#orgLevel4").change(function() {
			var id = $(this).val();
        });
		displayOrgOption();
});

function displayOrgOption() {
	var dep1 = "${dep1}";
	if(dep1){
		$("#orgLevel1").val(dep1);
		var level2 = $("#orgLevel2");
		level2.empty();
		$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level2);
		$(".orgOptions").each(function(){
			var pid = $(this).attr("pid");
			var value = $(this).attr("value");
			var name = $(this).attr("name");
            if(pid==dep1) {
            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level2);
            }
    	});   
		level2.show();
	}
	var dep2 = "${dep2}";
	if(dep2){
		$("#orgLevel2").val(dep2).show();
		var level3 = $("#orgLevel3");
		level3.empty();
		$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level3);
		$(".orgOptions").each(function(){
			var pid = $(this).attr("pid");
			var value = $(this).attr("value");
			var name = $(this).attr("name");
            if(pid==dep2) {
            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level3);
            }
    	});   
		level3.show();
	}
	var dep3 = "${dep3}";
	if(dep3){
		$("#orgLevel3").val(dep3).show();
		var level4 = $("#orgLevel4");
		level4.empty();
		$("<option pid='-1' value='0' selected='selected'>${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>").appendTo(level4);
		$(".orgOptions").each(function(){
			var pid = $(this).attr("pid");
			var value = $(this).attr("value");
			var name = $(this).attr("name");
            if(pid==dep3) {
            	$("<option pid="+pid+" value="+value+">"+name+"</option>").appendTo(level4);
            }
    	});   
		level4.show();
	}
	var dep4 = "${dep4}";
	if(dep4){
		$("#orgLevel4").val(dep4).show();
	}
}
	
function saveUser() {
	var userId = "${userBase.id}";
	var user = {};
	user.loginName = $("#loginName").val();
	user.loginPass = $("#loginPass").val();
	user.trueName = $("#trueName").val();
	user.enName = $("#enName").val();
	user.userEmail = $("#userEmail").val();
	user.mobile = $("#mobile").val();
	user.remark = $("#remark").val();
	
	
	if($("input:radio[name=userRole]").length > 0){
		user.userRole = $('input:radio[name="userRole"]:checked').val();
	}else if($("#hid_userRole").length>0){
		user.userRole = $("#hid_userRole").val();
	}
//		user.orgId = $("#orgId").val();
	var orgListTR = $(".orgListTR");
	if(orgListTR && orgListTR.length>0){
		var orgId = getSelectOrg();	
		if(orgId && orgId>0){
			user.orgId = orgId;
		}
	}
	
	var exprieDateRadio = $('input:radio[name="expiryDate"]:checked').val();
	if(exprieDateRadio=="2"){
		user.exprieDate = $("#expiryDateForEver").val()+" 23:59:59"; 
	}
	
	var func = {};
	<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_LIST}" varStatus="status">
		<c:set var="eachFieldName" value="${eachField[1]}"/>
		<c:if test='${sitePower[eachFieldName]==EMPOWER_ENABLED && fn:indexOf(eachFieldName,"Flag")>-1 && eachField[2]==1}'>
		func.${eachFieldName} = $("input[name=${eachFieldName}]:checked").length>0?1:0;
		</c:if>
	</c:forEach>
	if (userId && userId.length>0) {
		user.id = userId;
		app.updateSiteUser(user,func, function(result) {
			var frame = parent.$("#userDiv");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.change']}", ui:parent});
	} else {
		app.createSiteUser(user,func, function(result) {
			var frame = parent.$("#userDiv");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.save']}", ui:parent});	
	}
}
function getSelectOrg() {
	var orgId = $("#orgLevel4").val();
	if(orgId==0){
		orgId = $("#orgLevel3").val();
		if(orgId==0){
			orgId = $("#orgLevel2").val();
			if(orgId==0){
				orgId = $("#orgLevel1").val();
			}
		}
	}
	return orgId;
}
</script>
</body>
</html>