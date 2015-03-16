<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['system.notice.list.Create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	
	<cc:base var="NOTICE_FLAG_ANYONE"/>
	<cc:base var="NOTICE_FLAG_LOGINED"/>
	
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#noticeForm").find("input, textarea").not(".skipThese").uniform();
		$('#noticeForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
		var ruleString = {
				required: {
					"title": "${LANG['system.notice.title.input']}",
					"contents": "${LANG['system.notice.contents.input']}",
					"stopTime": "${LANG['system.notice.stoptime.input']}",
					"loginedFlag": "请选择可见性"
				},
				rangelength: {
					"title": "${LANG['system.notice.title.rangelength']}",
					"content": "${LANG['system.notice.content.rangelength']}"
				},
				custom: {
					"dateISO": "${LANG['system.notice.date.dateISO']}",
					"title": "${LANG['bizconf.jsp.admin.createNotice.res1']}"
				}
		};
		
		$.validator.addMethod("checkTitle", function(value, element) {   
	    	return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]{2,32}$/.test(value);
	 	}, ruleString.custom.title);
		
		var v = $("#noticeForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				var frame = parent.$("#noticeDiv");
				var noticeID = "${notice.id}";
				var notice = {};
				notice.title = $("#title").val();
				notice.content = $("#content").val();
				notice.loginedFlag = $("#loginedFlag").val();
				if (noticeID && noticeID.length>0) {
					notice.id = noticeID;
					app.updateSiteNotice(notice, function(result) {
						frame.trigger("closeDialog", [result]);
					},{message:"${LANG['system.site.change']}", ui:parent});
				} else {
					app.createSiteNotice(notice, function(result) {
						frame.trigger("closeDialog", [result]);
					},{message:"${LANG['system.site.save']}", ui:parent});	
				}
			},
			rules: {
	            'title' : {required:true, rangelength: [2, 32], checkTitle:true},
	            'content' : {required:true, rangelength: [1,2000]},
	            'loginedFlag': {required:true}
	        },
	        messages: {
	            'title' : {required: ruleString.required.title, rangelength: ruleString.rangelength.title},
	            'content' : {required: ruleString.required.contents, rangelength: ruleString.rangelength.content},
	            'loginedFlag':{required: ruleString.required.loginedFlag}
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
	});	
	</script>
</head>
<body onload="loaded()" style="font-size: 12px;padding: 10px 15px; margin: 0px;">
	<form id="noticeForm" name="loginform" action="">
		<table align="center" cellpadding="4" cellspacing="0" border="0px">
			<tr>
				<td colspan="2">
						&nbsp;
				</td>
			</tr>		
			<tr>
				<td align="right" width="80px"><span class='red_star'>*</span>${LANG['system.notice.list.Title']}</td>
				<td><input id="title" name="title" type="text" class="" value="${notice.title }"/></td>
			</tr>
			<tr>
				<td align="right" width="80px"><span class='red_star'>*</span>公告可见权限</td>
				<td>
					<select id="loginedFlag" name="loginedFlag">
						<option value="${NOTICE_FLAG_ANYONE }" <c:if test="${NOTICE_FLAG_ANYONE == notice.loginedFlag}">selected</c:if>>所有人可见</option>
						<option value="${NOTICE_FLAG_LOGINED }" <c:if test="${NOTICE_FLAG_LOGINED == notice.loginedFlag}">selected</c:if>>仅登录后可见</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right" width="80px" valign="top"><span class='red_star'>*</span>${LANG['system.notice.list.Contents']}</td>
				<td>
					<textarea id="content" name="content" style="width:520px;height:250px;resize:none;font-size: 12px;"><c:out value="${notice.content }" /></textarea>
				</td>
			</tr>
			<td colspan="2" align="center">
				<input name="submit" type="submit"  value="${LANG['system.ok']}" class="form-button skipThese" id="submitForm"/>
				<input name="submit" type="button"  value="${LANG['system.cancel']}" class="form-button closeButton skipThese"/>
			</td>
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#noticeDiv");
	frame.trigger("loaded");
}
</script>
