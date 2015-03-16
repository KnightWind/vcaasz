<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<cc:siteList var="EMPOWER_CODE_FIELD_LIST"/>
<cc:siteList var="EMPOWER_ENABLED"/>
<cc:siteList var="EMPOWER_CODE_VIDEO"/>
<cc:siteList var="EMPOWER_CODE_AUDIO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.edit_userbase.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/popupbox.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css" />	
	<style type="text/css">
		#orgLevel1 {
			width: 70px;
		}
	</style>
	
	<%@ include file="/jsp/common/cookie_util.jsp"%>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	<fmt:formatDate var="userExprieDate" value="${userBase.exprieDate}" pattern="yyyy-MM-dd"/>
	<script type="text/javascript">
	$(document).ready(function(){
		var lang = getBrowserLang(); 
		if (lang=="zh-cn") {
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
		} else {
			$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
		}
		$( "#userDateText" ).datepicker({
			minDate: +0,
			changeMonth: true,
			changeYear: true,			
			dateFormat: "yy-mm-dd",
			showOn: "both",
			buttonImage: "/static/images/calendar.jpg",
			buttonImageOnly: true,
			onClose: function() {
			}
		});
		

		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth()+2;
		month = month<10?"0"+month:month;
		var day = now.getDate();
		day = day<10?"0"+day:day;
		var tempDate = year+"-"+month+"-"+day;
		var permanetUser = "${userBase.permanentUser}";
		var userDate = "${userExprieDate}";
		if(userDate){
			if(permanetUser && permanetUser=="true"){
				$( "#userDateText" ).val(tempDate);
			} else {
				$( "#userDateText" ).val(userDate);	
			}
		} else {
			$( "#userDateText" ).val(tempDate);	
		}
		if(permanetUser){
			if(permanetUser=="true"){
				$("input:radio[name=userDateRadio]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=userDateRadio]:eq(1)").attr("checked",'checked');
			}
		}
		
		
		$("#saveUserForm").find("input, select, textarea").not(".skipThese").uniform();
		$('#saveUserForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"loginName": "${LANG['system.sysUser.loginName.input']}",
					"trueName": "${LANG['system.sysUser.trueName.input']}",
					"oldPass": "${LANG['system.user.oldPass.input']}",
					"loginPass": "${LANG['system.sysUser.loginPass.input']}",
					"confirmPass": "${LANG['system.user.confirmPass.input']}",
					"enName": "${LANG['system.sysUser.enName.input']}",
					"email": "${LANG['system.sysUser.email.input']}"
				},
				maxlength: {
					"remark": "${LANG['system.sysUser.remark.maxlength']}"
				},
				rangelength: {
					"loginName": "${LANG['system.sysUser.loginName.rangelength']}",
					"trueName": "${LANG['system.sysUser.trueName.rangelength']}",
					"loginPass": "${LANG['system.sysUser.loginPass.rangelength']}",
					"enname": "${LANG['system.sysUser.enname.rangelength']}",
					"email": "${LANG['system.sysUser.email.rangelength']}",
					"mobile": "${LANG['system.sysUser.mobile.rangelength']}"
				},
				custom: {
					"equalTo": "${LANG['system.user.confirmpass.custom']}",
					"email": "${LANG['system.sysUser.email.custom']}",
					"mobile": "${LANG['system.sysUser.mobile.custom']}",
					"checkLoginName": "${LANG['system.sysUser.checkLoginName.custom']}",
					"checkUserName": "${LANG['system.sysUser.checkUserName.custom']}",
					"checkEnName":"${LANG['system.sysUser.checkEnName.custom']}"
				}
		};
		
		$.validator.addMethod("notRequired", function(value, element) {
			if(value==null || value=="" || value.length==0){
				$(element).tipsy('hide').removeAttr('original-title');
			}
	    	return true;
	 	}, "");
		$.validator.addMethod("noSpace", function(value, element) {
			return value=="" || (value != ""&&value.indexOf(" ") < 0 );
	 	}, "${LANG['bizconf.jsp.admin.add_site_user.res2']}");
		$.validator.addMethod("checkLoginName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_]{4,16}$/.test(value);
	 	}, ruleString.custom.checkLoginName);
		$.validator.addMethod("checkUserName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s\u4e00-\u9fa5]{1,32}$/.test(value);
	 	}, ruleString.custom.checkUserName);	
		$.validator.addMethod("checkEnName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s]{1,32}$/.test(value);
	 	}, ruleString.custom.checkEnName);
		$.validator.addMethod("checkMobile", function(value, element) {       
	    	return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
	 	}, ruleString.custom.checkMobile);
		var v = $("#saveUserForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				saveSysUser();
			},
			rules: {
	            'loginName' : {required:true, rangelength: [4, 16], checkLoginName:true},
	            'trueName' : {required:true, rangelength: [1, 32], checkUserName:true},
	            'enName' : {notRequired:true, rangelength:[1, 32], checkEnName:true},
	            'userEmail' : {required:true, rangelength:[6, 64], email: true},
	            'mobile' : {notRequired:true, rangelength:[4, 32], checkMobile: true},
	            'remark' : {notRequired:true, maxlength: 256}
	        },
	        messages: {
	            'loginName' : {required:ruleString.required.loginName, rangelength: ruleString.rangelength.loginName},
	            'trueName' : {required:ruleString.required.trueName, rangelength: ruleString.rangelength.trueName},
	            'loginPass' : {required:ruleString.required.loginPass, rangelength: ruleString.rangelength.loginPass},
	            'confirmPass' : {required:ruleString.required.confirmPass, rangelength: ruleString.rangelength.loginPass, equalTo:ruleString.custom.equalTo},
	            'oldPass' : {required:ruleString.required.oldPass, rangelength: ruleString.rangelength.loginPass},
	            'enName' : {required:ruleString.required.enName, rangelength: ruleString.rangelength.enName},
	            'userEmail' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email},
	            'mobile': {rangelength: ruleString.rangelength.mobile, checkMobile: ruleString.custom.mobile},
	            'remark': {maxlength: ruleString.maxlength.remark}
	        },
	        success: function (label) {
	            $(label).each(function () {
	                $('#' + this.htmlFor).tipsy('hide').removeAttr('original-title');
	            });
	        },
	        errorPlacement: function (error, element) {
	        	var errorEl = $(".tipsy");
	        	var errorText = error.text();
	        	if (!errorEl || errorEl.length==0) {
		            element.attr('original-title', errorText);
	                element.tipsy('show');	
	        	} else {
	        		//for update first tip div title
		        	var elTitle = element.attr('original-title');
		        	if (elTitle && elTitle.length>0 && elTitle!=errorText) {
		        		element.attr('original-title', error.text());
		                element.tipsy('show');	
		        	}
	        	}
	        }
		});			
		
		$(".closeButton").click(function() {
			var frame = parent.$("#userDiv");
			frame.trigger("closeDialog");
		});
		
		//update passworld required to verify or disable
		var siteId = "${userBase.id}";
		if (siteId && siteId.length>0) {
			$("#loginPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, notRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
		} else {
			$("#loginPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16]});
			$("#confirmPass").rules("add", {noSpace: true, required:true, rangelength:[6, 16], equalTo: '#loginPass'});
		}
		
		//org
		
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
// 				$("#orgLevel2 option").each(function(){
// 					var pid = $(this).attr("pid");
// 					if(pid=="-1"){
// 						$(this).attr("selected", "selected");
// 					}
// 		            if(pid==id || pid=="-1") {
// 		            	$(this).show();
// 		            } else {
// 		            	$(this).hide();
// 		            }
// 		    	});   
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
// 				$("#orgLevel3 option").each(function(){
// 					var pid = $(this).attr("pid");
// 					if(pid=="-1"){
// 						$(this).attr("selected", "selected");
// 					}
// 		            if(pid==id || pid=="-1") {
// 		            	$(this).show();
// 		            } else {
// 		            	$(this).hide();
// 		            }
// 		    	});   
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
// 				$("#orgLevel4 option").each(function(){
// 					var pid = $(this).attr("pid");
// 					if(pid=="-1"){
// 						$(this).attr("selected", "selected");
// 					}
// 					if(pid==id || pid=="-1") {
// 		            	$(this).show();
// 		            } else {
// 		            	$(this).hide();
// 		            }
// 		    	});
				level4.show();
			}
        });
		
		$("#orgLevel4").change(function() {
			var id = $(this).val();
        });
		displayOrgOption();
		
		showFunc($("select[name=userRole]"));
		$("input[name='OutCallFlag']").attr("disabled", "disabled");
		var checked = $("input[name='phoneFlag']").attr("checked");
		if(checked=="checked"){
			$("input[name='OutCallFlag']").removeAttr("disabled");
		}
		$("input[name='phoneFlag']").change(function() {
			var value = $(this).val();
			var checked = $(this).attr("checked");
			if(checked=="checked"){
				$("input[name='OutCallFlag']").removeAttr("disabled");
			} else {
				$("input[name='OutCallFlag']").attr("disabled", "disabled");
				$("input[name='OutCallFlag']").removeAttr("checked", "checked");
			}
			$.uniform.update();
		});
		
	});	
	
	$(document).keydown(function(e){ 
		var doPrevent; 
		if (e.keyCode == 8) { 
			var d = e.srcElement || e.target; 
			if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
				doPrevent = d.readOnly || d.disabled; 
			}else{
				doPrevent = true; 
			}
		}else{
			doPrevent = false; 
		}
		if (doPrevent){
			e.preventDefault(); 
		} 
	}); 
	
	function saveSysUser() {
		var userId = "${userBase.id}";
		var user = {};
		user.loginName = $("#loginName").val();
		user.loginPass = $("#loginPass").val();
		user.trueName = $("#trueName").val();
		user.enName = $("#enName").val();
		user.userEmail = $("#userEmail").val();
		user.mobile = $("#mobile").val();
		user.remark = $("#remark").val();
		if($("select[name=userRole]").length>0){
			user.userRole = $("select[name=userRole]").val();
		}else if($("#hid_userRole").length>0){
			user.userRole = $("#hid_userRole").val();
		}
// 		user.orgId = $("#orgId").val();
		var orgListTR = $(".orgListTR");
		if(orgListTR && orgListTR.length>0){
			var orgId = getSelectOrg();	
			if(orgId && orgId>0){
				user.orgId = orgId;
			}
		}
		
		var exprieDateRadio = $('input:radio[name="userDateRadio"]:checked').val();
		if(exprieDateRadio=="2"){
			user.exprieDate = $("#userDateText").val()+" 23:59:59"; 
		}
		
		var func = {};
		<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_LIST}" varStatus="status">
			<c:set var="eachFieldName" value="${eachField[1]}"/>
			<c:if test='${sitePower[eachFieldName]==EMPOWER_ENABLED && fn:indexOf(eachFieldName,"Flag")>-1 && eachField[2]==1}'>
			func.${eachFieldName} = $("input[name=${eachFieldName}]:checked").length>0?1:0;
			</c:if>
		</c:forEach>
		//func.phoneFlag = $("input[name=phoneFlag]:checked").length>0?1:0;
		//func.autoFlag = $("input[name=autoFlag]:checked").length>0?1:0;
		
		//func.shareMediaFlag = $("input[name=shareMediaFlag]:checked").length>0?1:0;
		//func.recordFlag = $("input[name=recordFlag]:checked").length>0?1:0;
		//var orgPass = $("#oldPass").val();
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
	
	function showFunc(obj){
		if($(obj).val()==1){
			$("#conffuc").show();
		}else{
			$("#conffuc").hide();
		}
	}
	
	function initLevelOrg(id, elem){
		app.getAdminLevelOrg(id, function(result) {
			if(result && result.status==1){
				var orgUserList = result.orgUserList;
				for ( var i = 0; i < orgUserList.length; i++) {
					var org = orgUserList[i];
					$("<option class='org-option' value="+org.id+">"+org.orgName+"</option>").appendTo(elem);	
				}
				elem.show();
			}
		});
	}
	
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
// 			$("#orgLevel3 option").each(function(){
// 				var pid = $(this).attr("pid");
// 				if(pid=="-1"){
// 					$(this).attr("selected", "selected");
// 				}
// 	            if(pid==dep2 || pid=="-1") {
// 	            	$(this).show();
// 	            } else {
// 	            	$(this).hide();
// 	            }
// 	    	});
// 			$("#orgLevel3").show();
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
// 			$("#orgLevel4 option").each(function(){
// 				var pid = $(this).attr("pid");
// 				if(pid=="-1"){
// 					$(this).attr("selected", "selected");
// 				}
// 	            if(pid==dep3 || pid=="-1") {
// 	            	$(this).show();
// 	            } else {
// 	            	$(this).hide();
// 	            }
// 	    	});
// 			$("#orgLevel4").show();
		}
		var dep4 = "${dep4}";
		if(dep4){
			$("#orgLevel4").val(dep4).show();
		}
	}
	
	function relateOrg() {
		parent.relateOrg();
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
</head> 
<body onload="loaded()">
<form id="saveUserForm"  action="" method="post">
     		<input type="hidden" name="id" value="${userBase.id}"/>
     		<c:forEach var="org" items="${orgList}" end="0">
     			<c:set var="rootOrg" value="${org.id }"/>
     		</c:forEach>
     		<input type="hidden" id="rootOrgId" name="rootOrgId" value="${rootOrg}"/>
			<table class="table_create_system_user" style="height: 423px">
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label>${LANG['system.sysUser.list.loginName']}
				    </td>
				    <td class="table-td">
				      <input id="loginName" name="loginName" <c:if test="${(userBase.userRole eq 1) and (siteBase.chargeMode==1 || siteBase.chargeMode==2)}">readonly style='border:0px; background:none;'</c:if> class="create_system_user_input" type="text" value="${userBase.loginName}"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label>${LANG['system.sysUser.list.loginPass']}
				    </td>
				    <td class="table-td">
				      <input id="loginPass" name="loginPass" class="create_system_user_input" type="password"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label>${LANG['system.user.confirmPass']}
				    </td>
				    <td class="table-td">
				      <input id="confirmPass" name="confirmPass" class="create_system_user_input" type="password"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label>${LANG['system.sysUser.list.userName']}
				    </td>
				    <td class="table-td">
				      <input id="trueName" name="trueName" class="create_system_user_input" type="text" value="${userBase.trueName}"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      ${LANG['system.sysUser.list.enName']}
				    </td>
				    <td class="table-td">
				      <input id="enName" name="enName" class="create_system_user_input" type="text" value="${userBase.enName}"/>
				    </td>
				  </tr>
				  <c:if test="${fn:length(orgList)>0 }">
 				  <tr class="orgListTR"> 
 				    <td align="right"> 
 				      	${LANG['bizconf.jsp.admin.edit_userbase.res3']} 
 				    </td> 
 				    <td class="table-td">
<!--  				    	<input class="skipThese" type="button" value="${LANG['bizconf.jsp.admin.edit_userbase.res4']}" onclick="relateOrg()"/> -->
<!--  				    	<label id="relate_org"></label>  -->
<!--  				       <select name="" id="">  -->
<%--                            <option <c:if test="${!empty userBase && userBase.orgId eq '0'}">selected</c:if> value="0" >${LANG['bizconf.jsp.admin.edit_userbase.res5']}</option> --%>
<%--                            <c:forEach var="org" items="${orgList}" > --%>
<%--                                <option level="${org.orgLevel }" <c:if test="${!empty userBase && userBase.orgId eq org.id}">selected</c:if> value="${org.id}" >${org.orgName}</option> --%>
<%--                            </c:forEach> --%>
<!--                         </select> -->
                        <c:forEach var="org" items="${orgList}" >
                        	<span class="orgOptions" style="display: none;" level="${org.orgLevel }" value="${org.id}" pid="${org.parentId}" name="${org.orgName}"></span>
                        </c:forEach>
                        <select class="skipThese" id="orgLevel1" name="" style="padding: 3px; border: 1px solid #ABADB3;width:80px;">
                        	<option pid="-1" value="0">${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>
		              		<c:forEach var="org" items="${orgList}" >
		              			<c:if test="${org.orgLevel==1}">
		              				<option level="${org.orgLevel }" value="${org.id}" <c:if test="${!empty dep1 && dep1 eq org.id}">selected</c:if>>${org.orgName}</option>
		              			</c:if>
		              		</c:forEach>
		              	</select>
		              	<select class="skipThese" id="orgLevel2" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
		              		<option pid="-1" value="0">${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>
		              		<c:forEach var="org" items="${orgList}" >
		              			<c:if test="${org.orgLevel==2}">
		              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep2 && dep2 eq org.id}">selected</c:if>>${org.orgName}</option>
		              			</c:if>
		              		</c:forEach>
		              	</select>
		              	<select class="skipThese" id="orgLevel3" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
		              		<option pid="-1" value="0">${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>
		              		<c:forEach var="org" items="${orgList}" >
		              			<c:if test="${org.orgLevel==3}">
		              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep3 && dep3 eq org.id}">selected</c:if>>${org.orgName}</option>
		              			</c:if>
		              		</c:forEach>
		              	</select>
		              	<select class="skipThese" id="orgLevel4" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
		              		<option pid="-1" value="0">${LANG['bizconf.jsp.admin.edit_userbase.res2']}</option>
		              		<c:forEach var="org" items="${orgList}" >
		              			<c:if test="${org.orgLevel==4}">
		              				<option pid="${org.parentId}" value="${org.id}" <c:if test="${!empty dep4 && dep4 eq org.id}">selected</c:if>>${org.orgName}</option>
		              			</c:if>
		              		</c:forEach>
		              	</select>
 				    </td> 
 				  </tr> 
				  </c:if>
				  <c:choose>
					<c:when test="${siteBase.chargeMode eq 1 and empty userBase}">
							<input type="hidden" id="hid_userRole" name="userRole" value="2"/>
					</c:when>
					<c:when test="${siteBase.chargeMode eq 1 and not empty userBase}">
							<input type="hidden" id="hid_userRole" name="userRole" value="${userBase.userRole }"/>
					</c:when>
					<c:otherwise>
						<tr>
						    <td align="right">
						      ${LANG['site.admin.edituser.userrole']}
						    </td>
						    <td class="table-td">
						      <select class="skipThese" name="userRole" onchange="showFunc(this);" style="padding: 3px; border: 1px solid #ABADB3;width:80px;">
				               	<option value="1" <c:if test="${empty userBase or userBase.userRole eq '1'}">selected="selected"</c:if>>${LANG['site.admin.edituser.role1']}</option>
						      	<option value="2" <c:if test="${userBase.userRole eq '2'}">selected="selected"</c:if>>${LANG['site.admin.edituser.role2']}</option>
				              </select>
						    </td>
						</tr>
						<c:if test="${sitePower != null}"> 
						<tr id="conffuc" <c:if test="${empty userBase || userBase.userRole eq '2'}">style="display:none"</c:if> >
						    <td align="right">
						      ${LANG['bizconf.jsp.admin.edit_userbase.res6']}
						    </td>
						    <td class="table-td">
						    <%--
						    <c:forEach var ="eachField" items="${EMPOWER_CODE_FIELD_LIST}" varStatus="status">
						    <c:set var="eachFieldName" value="${eachField[1]}"/>
						    </c:forEach>
						    <c:if test="${eachField[0]==EMPOWER_CODE_VIDEO || eachField[0]==EMPOWER_CODE_AUDIO }"><c:set var="tmpNum" value="${tmpNum+1 }"/></c:if>
						    --%>
						    <table width="100%" border="0" >
								<tr>
								<c:set var="modNum" value="0"/>
								<c:set var="modMax" value="4"/>
								<c:set var="tmpNum" value="0"/>
								<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_LIST}" varStatus="status">
									<c:set var="eachFieldName" value="${eachField[1]}"/>
									<c:set var="modNum" value="${tmpNum % modMax }"/>
									<c:if test="${modNum ==0  && tmpNum > 0}">
										</tr><tr>
										<c:set var="tmpNum" value="0"/>
									</c:if>
									<c:if test='${sitePower[eachFieldName]==EMPOWER_ENABLED && fn:indexOf(eachFieldName,"Flag")>-1 && eachField[3]==1}'>
										<td>
											<input class="extraConfig" name="${eachFieldName}" id="${eachFieldName}" type="checkbox" value="${EMPOWER_ENABLED}"  <c:if test="${userConfig[eachFieldName]==EMPOWER_ENABLED && userConfig.userId > 0 }"> checked </c:if> />
											<label>
												<c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>${LANG[langName]}
											</label>
										</td>
										<%--<c:if test="${eachField[0]==EMPOWER_CODE_VIDEO }"><td id="videoNumTd"></td> </c:if>--%>
										<c:set var="tmpNum" value="${tmpNum+1}"/>
									</c:if>
								</c:forEach>
								<c:if test="${modNum>0}">
									<c:forEach begin="${modNum+1 }" end="${modMax}" >
										<td>&nbsp;</td>
									</c:forEach>
								</c:if>
								</tr>
							</table>
<%-- 						      <input type="checkbox" <c:if test="${config.phoneFlag eq 1 and siteConfig.phoneFlag eq 1}">checked</c:if> <c:if test="${siteConfig.phoneFlag eq 0}">disabled="disabled"</c:if> value="1" name="phoneFlag"/>${LANG['bizconf.jsp.admin.conf_list.res9']}&nbsp; 
 						      <input type="checkbox" value="1" name="autoFlag" <c:if test="${config.autoFlag eq 1 and siteConfig.autoFlag eq 1}">checked</c:if> <c:if test="${siteConfig.autoFlag eq 0}">disabled="disabled"</c:if> />${LANG['bizconf.jsp.admin.edit_userbase.res7']}&nbsp; 
 						      <input type="checkbox" value="1" name="shareMediaFlag" <c:if test="${config.shareMediaFlag eq 1 and siteConfig.shareMediaFlag eq 1}">checked</c:if> <c:if test="${siteConfig.shareMediaFlag eq 0}">disabled="disabled"</c:if> />${LANG['bizconf.jsp.admin.edit_userbase.res8']}&nbsp; 
 						      <input type="checkbox" value="1" name="recordFlag"<c:if test="${config.recordFlag eq 1 and siteConfig.recordFlag eq 1}">checked</c:if> <c:if test="${siteConfig.recordFlag eq 0}">disabled="disabled"</c:if>/>${LANG['bizconf.jsp.admin.edit_userbase.res9']} 
 						    
							  <c:if test="${siteConfig.phoneFlag eq 1}"> 
						      <input type="checkbox" <c:if test="${config.phoneFlag eq 1 and siteConfig.phoneFlag eq 1}">checked</c:if> value="1" name="phoneFlag"/>${LANG['bizconf.jsp.admin.conf_list.res9']}&nbsp;
						      </c:if>
						      <c:if test="${siteConfig.autoFlag eq 1}">
						      <input type="checkbox" value="1" name="autoFlag" <c:if test="${config.autoFlag eq 1 and siteConfig.autoFlag eq 1}">checked</c:if>  />${LANG['bizconf.jsp.admin.edit_userbase.res7']}&nbsp;
						      </c:if>
						      <c:if test="${siteConfig.shareMediaFlag eq 1}">
						      <input type="checkbox" value="1" name="shareMediaFlag" <c:if test="${config.shareMediaFlag eq 1 and siteConfig.shareMediaFlag eq 1}">checked</c:if>  />${LANG['bizconf.jsp.admin.edit_userbase.res8']}&nbsp;
						      </c:if>
						      <c:if test="${siteConfig.recordFlag eq 1}">
						      <input type="checkbox" value="1" name="recordFlag"<c:if test="${config.recordFlag eq 1 and siteConfig.recordFlag eq 1}">checked</c:if> />${LANG['bizconf.jsp.admin.edit_userbase.res9']}
						      </c:if>
 						      --%>
						    </td>
						</tr>
						</c:if>
					</c:otherwise>
				  </c:choose>
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label> ${LANG['admin.user.edituser.effinfo1']}
				    </td> 
				    <td class="table-td">
				      <input name="userDateRadio" class="" type="radio" value="1" checked="checked" />${LANG['admin.user.edituser.effinfo2']}
				      <input name="userDateRadio" class="" type="radio" value="2" />${LANG['admin.user.edituser.effinfo3']}
				      <input id="userDateText" class="" value="" type="text" style="width:90px;" readonly="readonly"/><span style="margin-left: 10px;">${LANG['admin.user.edituser.effinfo4']}</span>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      <label class='red_star'>*</label>${LANG['system.sysUser.list.email']}
				    </td>
				    <td class="table-td">
				      <input id="userEmail" name="userEmail" class="create_system_user_input" type="text" value="${userBase.userEmail}"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right">
				      ${LANG['system.sysUser.list.telephone']}
				    </td>
				    <td class="table-td">
				      <input id="mobile" name="mobile" class="create_system_user_input" type="text" value="${userBase.mobile}"/>
				    </td>
				  </tr>
				  <tr>
				    <td align="right" valign="top">
				      <div style="margin-top: 5px">${LANG['system.sysUser.list.remark']}</div>
				    </td>
				    <td class="table-td" style="height: 65px;">
				      <textarea id="remark" name="remark" style="width: 261px; height:50px;resize:none;">${userBase.remark}</textarea>
				    </td>
				  </tr>
				</table>
				<div style="width: 260px; margin: 0px auto">
					<input name="emile_button" class="create_system_user_button skipThese" type="submit"  value="${LANG['system.submit']}" />
					<input name="emile_button02"  class="create_system_user_button closeButton skipThese" type="button"  value="${LANG['system.cancel']}" style="margin-left: 100px" />
				</div>     		
     	</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#userDiv");
	frame.trigger("loaded");
}
</script>
