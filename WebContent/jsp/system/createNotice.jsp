<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${LANG['system.notice.list.Create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<style>
		.form-uni-input {
				outline: 0;
				padding: 3px;
			  font-size: 12px;
			  font-family: "Helvetica Neue", Arial, Helvetica, sans-serif;
			  font-weight: normal;
  			  color: #000;
			  border-top: solid 1px #aaa;
			  border-left: solid 1px #aaa;
			  border-bottom: solid 1px #ccc;
			  border-right: solid 1px #ccc;
			  -webkit-border-radius: 3px;
			  -moz-border-radius: 3px;
			  border-radius: 3px; 
		}
	</style>
	<%@ include file="/jsp/common/cookie_util.jsp"%>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/kindeditor-4.1.5/kindeditor.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('.input-sw').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		$('#stopTime').tipsy({ trigger: 'manual', fade: false, gravity: 'w', opacity: 1 });
		var lang = getBrowserLang(); 
		if (lang=="zh-cn") {
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
		} else {
			$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
		}
		$( "#stopTime" ).datepicker({
			minDate: +1,
			changeMonth: true,
			changeYear: true,			
			dateFormat: "yy-mm-dd",
			showOn: "both",
			buttonImage: "/static/images/calendar.jpg",
			buttonImageOnly: true,
			onClose: function() {
				$(this).trigger("blur");
			}
		});
		var ruleString = {
				required: {
					"title": "${LANG['system.notice.title.input']}",
					"contents": "${LANG['system.notice.contents.input']}",
					"stopTime": "${LANG['system.notice.stoptime.input']}"
				},
				rangelength: {
					"title": "${LANG['system.notice.title.rangelength']}"
				},
				custom: {
					"dateISO": "${LANG['system.notice.date.dateISO']}"
				}
		};
		var v = $("#noticeForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				var frame = parent.$("#noticeDiv");
				var noticeID = "${notice.id}";
				var notice = {};
				notice.title = $("#title").val();
				notice.content = $("#contents").val();
				notice.stopTime = $("#stopTime").val();
				if (noticeID && noticeID.length>0) {
					notice.id = noticeID;
					app.updateNotice(notice, function(result) {
						frame.trigger("closeDialog", [result]);
					},{message:"${LANG['system.site.change']}", ui:parent});
				} else {
					app.createNotice(notice, function(result) {
						frame.trigger("closeDialog", [result]);
					},{message:"${LANG['system.site.save']}", ui:parent});	
				}
			},
			rules: {
	            'title' : {required:true, rangelength: [2, 32]},
	            'contents' : {required:true},
	            'stopTime' : {required:true, dateISO:true}
	        },
	        messages: {
	            'title' : {required: ruleString.required.title, rangelength: ruleString.rangelength.title},
	            'contents' : {required: ruleString.required.contents},
	            'stopTime': {required: ruleString.required.stopTime, dateISO: ruleString.custom.dateISO}
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
			var frame = parent.$("#noticeDiv");
			frame.trigger("closeDialog");
		});
		
		$("#preview").click(function() {
			if(v.form()) {
				var title = $("#title").val();
				var content = $("#contents").val();
				parent.previewNotice({"title":title, "content":content});	
			}	
		});
	});	
	</script>
</head>
<body onload="loaded()" style="font-size: 12px;padding: 10px 15px; margin: 0px;">
	<form id="noticeForm" name="loginform" action="" method="post">
		<table align="center" cellpadding="4" cellspacing="0" border="0px">
			<tr>
				<td colspan="2">
						<span style="color:red">* ${LANG['bizconf.jsp.system.createNotice.res1']}</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="60px"><span class='red_star'>*</span>${LANG['system.notice.list.Title']}</td>
				<td><input id="title" name="title" type="text" class="input-sw form-uni-input" style="width:260px;"/></td>
			</tr>
			<tr>
				<td align="right" width="60px" valign="top"><span class='red_star'>*</span>${LANG['system.notice.list.Contents']}</td>
				<td>
					<input class="form-valid-hidden input-sw" id="contents" name="contents" />
					<textarea id="content" name="content" style="visibility: hidden;">
						<c:if test="${!empty notice}">
							<c:out value="${notice.content }" />
						</c:if>
					</textarea>
				</td>
			</tr>
			<tr>
				<td align="right" width="60px"><span class='red_star'>*</span>${LANG['system.notice.list.StopTime']}</td>
				<td><input name="stopTime" type="text" id="stopTime"  class="form-uni-input" readonly="readonly"/></td>
			</tr>
			<td colspan="2" align="center">
				<input type="button"  value="${LANG['system.preview']}" class="form-button" id="preview"/>
				<input type="submit"  value="${LANG['system.ok']}" class="form-button" id="submitForm"/>
				<input type="button"  value="${LANG['system.cancel']}" class="form-button closeButton"/>
			</td>
		
		</table>
	
	
	</form>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 0,
					width: "560px",
					height: "230px",
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					afterCreate: function() {
						$("#contents").val(editor.html());
					},
					afterBlur:function(){
						$("#contents").val(editor.html());
						$("#contents").trigger('blur');
					}, 
					items : [
						'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
		</script>			
</body>
</html>
<c:if test="${!empty notice}">
<fmt:formatDate var="stopTime" value="${notice.stopTime}" type="date" pattern="yyyy-MM-dd"/>
<script type="text/javascript">
$(function() {
	$("#title").val("${notice.title}");
	var stopTime = '${stopTime}';
	if (stopTime && stopTime.length>0) {
		stopTime = stopTime.substring(0, 10);
	}
	$("#stopTime").val(stopTime);
});
</script>
</c:if>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#noticeDiv");
	frame.trigger("loaded");
}
</script>
