<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>MSGROUP</title>
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
	var msGroupForm = null;
	$(document).ready(function(){
		$("#msGroupForm").find("input, select, textarea").not(".skipThese").uniform();
		$('#msGroupForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"groupName": "${LANG['bizconf.jsp.system.editgroup.warninfo1']}",
					"msInfo": "${LANG['bizconf.jsp.system.editgroup.warninfo2']}"
				},
				maxlength: {
					"groupDesc": "${LANG['system.sysUser.remark.maxlength']}"
				},
				rangelength: {
					"groupName": "${LANG['valid.rangelength132']}",
					"msInfo": "${LANG['valid.rangelength132']}"
				}
		};
		$.validator.addMethod("notRequired", function(value, element) {
			if(value==null || value=="" || value.length==0){
				$(element).tipsy('hide').removeAttr('original-title');
			}
	    	return true;
	 	}, "");
		msGroupForm = $("#msGroupForm").validate({
			onkeyup: false,
			errorClass: "warning",
			rules: {
	            'groupName' : {required:true, rangelength: [1, 32]},
	            'msInfo' : {required:true},
	            'groupDesc' : {notRequired:true, maxlength: 128}
	        },
	        messages: {
	            'groupName' : {required:ruleString.required.groupName, rangelength: ruleString.rangelength.groupName},
	            'msInfo' : {required:ruleString.required.msInfo},
	            'groupDesc': {maxlength: ruleString.maxlength.remark}
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
	
	
	function loaded() {
		var frame = parent.$("#msgroupDiv");
		frame.trigger("loaded");
	}

	function saveMSGroup() {
		if(msGroupForm.form()) {
			var group = {};
			group.id = '${mg.id}';
			group.groupName = $("#groupName").val();
			group.msIds = $("#msIds").val();
			group.groupDesc = $("#groupDesc").val();
			app.saveOrUpdateMSGroup(group, function(result){
				if(result.status == 1){
					parent.successDialog(result.message);
					parent.showURL("/system/ms/showmsgroups");
					closeDialog();
				}else{
					parent.errorDialog(result.message);
				}
			}, null);		
		}
	}

	function closeDialog() {
		var frame = parent.$("#msgroupDiv");
		frame.trigger("closeDialog");
	}

	function showMsSelect(){
		var ids = $("#msIds").val();
		parent.showMSSelect('${mg.id}',ids);
	}

	function setMsValue(text,ids){
		$("#msInfo").val(text);
		$("#msIds").val(ids);
	}
</script>
</head>
<body onload="loaded();">
<form id="msGroupForm" name="msGroupForm" action="" method="post">
<table class="biz-table">
<tr>
	<td align="right">${LANG['bizconf.jsp.system.msgrouplist.showinfo2']}</td>
	<td class="biz-table-td"><input class="biz-table-input" type="text" name="groupName" id="groupName" value="${mg.groupName}"/></td>
</tr>
<tr>
	<td align="right">${LANG['bizconf.jsp.system.msgrouplist.showinfo3']}</td>
	<td class="biz-table-td">
		<input readonly="readonly"  class="biz-table-input" onfocus="showMsSelect();" type="text" name="msInfo" id="msInfo" value="${msinfo}"/>
		<input class="skipThese" type="hidden" name="msIds" id="msIds" value="${msids}"/>
	</td>
</tr>
<tr>
	<td align="right" valign="top">${LANG['system.sysUser.list.remark']}</td>
	<td class="biz-table-td">
	<textarea name="groupDesc" id="groupDesc" style="width: 261px; height:50px;resize:none;">${mg.groupDesc}</textarea></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>
		<input class="submitbutton skipThese" type="button" name="" id="saveBtn" value="${LANG['bizconf.jsp.system.email_template.res5']}" onclick="saveMSGroup()"/>
		<input class="submitbutton skipThese" type="button" name="" id="" value="${LANG['system.cancel']}" onclick="closeDialog()"/>
	</td>
</tr>
</table>
</form>
</body>
</html>
