<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>MS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--Css-->
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<style>
	.biz-table {
		margin: 28px auto 15px auto;
	}
	.biz-table td {
		height: 33px;
	}
	.biz-table-td {
		padding-left: 10px;
	}
	.biz-table-input {
	    color: #666666;
	    height: 18px;
	    width: 262px;
	}
	.red_star {position: relative;right: 5px;color: #FF0000}
	.submitbutton, .closeButton{
		background: url("/static/images/button01.jpg") no-repeat center center;
	    background-color: #663399;
	    border: 0px;
	    color: #333333;
	    cursor: pointer;
	    height: 24px;
	    line-height:24px;
	    margin: 7px;
	    text-align: center;
	    width: 72px;
	}
	</style>
	<!-- Javascript -->
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>	
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	<script type="text/javascript">
	var msForm = null;
	$(document).ready(function(){
		$("#msForm").find("input, select, textarea").not(".skipThese").uniform();
		$('#msForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"msName": "${LANG['bizconf.jsp.system.editms.warninfo1']}",
					"msIp": "${LANG['bizconf.jsp.system.editms.warninfo2']}",
					"msSupplier": "${LANG['bizconf.jsp.system.mslist.showinfo3']}"
				},
				maxlength: {
					"msDesc": "${LANG['system.sysUser.remark.maxlength']}"
				},
				rangelength: {
					"msName": "${LANG['valid.rangelength132']}",
					"msIp": "${LANG['valid.rangelength132']}",
					"msSupplier": "${LANG['valid.rangelength132']}"
				},
				custom: {
					"ip": "${LANG['bizconf.jsp.system.editms.warninfo3']}"
				}
		};
		$.validator.addMethod("notRequired", function(value, element) {
			if(value==null || value=="" || value.length==0){
				$(element).tipsy('hide').removeAttr('original-title');
			}
	    	return true;
	 	}, "");
		jQuery.validator.addMethod("ip", function(value, element) {
		    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
		    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
		}, "${LANG['bizconf.jsp.system.editms.warninfo3']}");
		msForm = $("#msForm").validate({
			onkeyup: false,
			errorClass: "warning",
			rules: {
	            'msName' : {required:true, rangelength: [1, 32]},
	            'msIp' : {required:true, rangelength: [1, 32], ip:true},
	            'msSupplier' : {required:true, rangelength:[1, 32]},
	            'msDesc' : {notRequired:true, maxlength: 128}
	        },
	        messages: {
	            'msName' : {required:ruleString.required.msName, rangelength: ruleString.rangelength.msName},
	            'msIp' : {required:ruleString.required.msIp, rangelength: ruleString.rangelength.msIp, ip:ruleString.custom.ip},
	            'msSupplier' : {required:ruleString.required.msSupplier, rangelength: ruleString.rangelength.msSupplier},
	            'msDesc': {maxlength: ruleString.maxlength.remark}
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
	});
	</script>
</head>
<body onload="loaded()">
<form id="msForm" name="msForm" action="" method="post">
<table class="biz-table">
<tr>
	<td align="right">${LANG['bizconf.jsp.system.mslist.showinfo2']}</td>
	<td class="biz-table-td"><input class="biz-table-input" type="text" name="msName" id="msName" value="${ms.msName}"/></td>
</tr>
<tr>
	<td align="right">MS ip</td>
	<td class="biz-table-td"><input class="biz-table-input" type="text" name="msIp" id="msIp" value="${ms.msIp}"/></td>
</tr>
<tr>
	<td align="right">${LANG['bizconf.jsp.system.mslist.showinfo3']}</td>
	<td class="biz-table-td"><input class="biz-table-input" type="text" name="msSupplier" id="msSupplier" value="${ms.msSupplier}"/></td>
</tr>
<tr>
	<td align="right">${LANG['bizconf.jsp.system.mslist.showinfo4']}</td>
	<td class="biz-table-td">
		<input type="radio" name="inUse" id="inUseTrue" <c:if test="${empty ms || ms.inUse eq 1}">checked="checked"</c:if> value="1"/>${LANG['bizconf.jsp.system.mslist.showinfo5']}
		<input type="radio" name="inUse" id="inUseFalse" <c:if test="${ms.inUse eq 0}">checked="checked"</c:if> value="0"/> ${LANG['site.admin.conf.config.option.disable']}
	</td>
</tr>

<tr>
	<td align="right" valign="top">${LANG['system.sysUser.list.remark']}</td>
	<td class="biz-table-td">
	<textarea name="msDesc" id="msDesc" style="width: 261px; height:50px;resize:none;">${ms.msDesc}</textarea></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td class="biz-table-td">
		<input class="submitbutton skipThese" type="button" name="" id="saveBtn" value="${LANG['bizconf.jsp.system.email_template.res5']}" onclick="saveMS()"/>
		<input class="closeButton skipThese" type="button" name="" id="" value="${LANG['system.cancel']}" onclick="closeDialog()"/>
	</td>
</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#msDiv");
	frame.trigger("loaded");
}

function saveMS() {
	if(msForm.form()) {
		var ms = {};
		ms.id = "${ms.id}";
		ms.msName = $("#msName").val();
		ms.msIp = $("#msIp").val();
		ms.msSupplier = $("#msSupplier").val();
		ms.inUse = $("#inUseFalse").attr("checked")?0:1;
		ms.msDesc = $("#msDesc").val();
		app.saveOrUpdateMS(ms, function(result){
			if(result.status == 1){
				parent.successDialog(result.message);
				parent.showURL("/system/ms/showmses");
				closeDialog();
			}else{
				parent.errorDialog(result.message);
			}
		}, null);
	}
}
function closeDialog() {
	var frame = parent.$("#msDiv");
	frame.trigger("closeDialog");
}
</script>
