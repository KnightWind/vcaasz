<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['system.menu.config.host']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="/static/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/static/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/kindeditor-4.1.5/kindeditor.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<script type="text/javascript">
$(function() {
	$("#tempTypeForm").find("select").not(".skipThese").uniform();
});
</script>
</head>
<body>
<div class="emile" style="height: 520px;">
 <div class="emile_03">     
     <div class="emile_03_top" style="margin-top: 15px;"><span>${LANG['system.menu.config.template']}</span></div>
       		<input type="hidden" id="eTempId" />
       		<div style="margin-top: 8px;margin-bottom: 5px;height: 30px;">
       			<form id="tempTypeForm" action="">
			        <select name="tempType" onchange="updateSysEmailTemplate(this.value);">
			        	<option value="1">${LANG['system.email.type.invite']}</option>
			        	<option value="2">${LANG['system.email.type.update']}</option>
			        	<option value="3">${LANG['system.email.type.cancel']}</option>
<%--			        	<option value="4">${LANG['system.email.type.remind']}</option>--%>
			        </select>
       			</form>
	        </div>
	        <div>
	        <textarea class="text06"  name="econtentBody" id="econtentBody"  cols="80" rows="10" ></textarea> 
	        <div align="left" style="margin-top: 5px;" class="emile_03_bottom">
	       		<input name="emile_button" id="emile_button" type="button" onclick="javascript:saveSysEmailTemplate();" value=" ${LANG['system.email.config.savetemp']}" />&nbsp;
	       		<input name="emile_button" id="emile_button" type="button" onclick="javascript:resetSysEmailTemplate();" value="${LANG['system.email.config.recovertemp']}" />
	        </div>
</div>         
 <div class="emile_02" userName="t1"></div>
 <div class="emile_03" userName="t2"></div>
</div>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="econtentBody"]', {
			filterMode: false,
			resizeType : 0,
			width: "750px",
			height: "400px",
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			afterCreate: function() {
				var type = $("select[name=tempType]").val();
				updateSysEmailTemplate(type);
			},
			afterBlur:function(){
			}, 
			items : [
				'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons']
		});
	});
	
	/*${LANG['bizconf.jsp.admin.emailTemplate.res1']}*/
	function updateSysEmailTemplate(type) {
		app.updateSysEmailTemplate(type, function(result) {
			if (result && result.emailContent) {
				$("#eTempId").val(result.id);
				editor.html(result.emailContent);
			}
		});
	}
	/*${LANG['bizconf.jsp.admin.emailTemplate.res2']}*/
	function saveSysEmailTemplate() {
		var tid = $("#eTempId").val();
		var t_content = "";
		if(editor.isEmpty()){
			parent.errorDialog("${LANG['system.email.temp.errorinfo2']}");
			return;
		} else {
			t_content = editor.html();
		}
		var type = $("select[name=tempType]").val();
		app.saveSysEmailTemplate(tid, t_content, type, function(result) {
			if (result) {
				if(result.message=="success"){
					parent.successDialog("${LANG['system.email.temp.alertinfo1']}");		
				} else {
					parent.errorDialog("${LANG['system.email.temp.syserror']}");
				}
			
			}
		});
	}	
	/*${LANG['bizconf.jsp.admin.emailTemplate.res3']}*/
	function resetSysEmailTemplate() {
		var type = $("select[name=tempType]").val();
		app.resetSysEmailTemplate(type, function(result) {
			if (result && result.emailContent) {
				editor.html(result.emailContent);
			}
		});
	}	
</script>
</body>
</html>
