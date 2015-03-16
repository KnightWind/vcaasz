<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/email-server.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
</head>
<body>
<div class="box">
<div class="head">
		<span class="title">${LANG['website.admin.email.template.titleinfo0']}</span>
		<span class="desc">${LANG['website.admin.email.template.titleinfo1']}</span>
</div>
<div class="tabview" style="width: 765px;height:520px;margin-top: 20px;">
	<input type="hidden" id="eTempId" />
	<ul class="tabs tabs-contact-list clearfix">
		<li class="tab active" data-tab="conf-invite"><a> ${LANG['website.admin.email.template.typeinvite']}</a></li>
		<li class="tab" data-tab="conf-update"><a>${LANG['website.admin.email.template.typemodify']}</a></li>
		<li class="tab" data-tab="conf-cancel"><a>${LANG['website.admin.email.template.typecancel']}</a></li>
	</ul>
	<div class="views" style="margin: 20px 0px;">
		<div data-view="conf-invite" style="display: block;">
			<textarea class="text06"  name="econtentInvitBody" id="econtentInvitBody"  cols="80" rows="10" ></textarea>                   
		</div>
		<div data-view="conf-update" style="display: none;">
			<textarea class="text06"  name="econtentUpdateBody" id="econtentUpdateBody"  cols="80" rows="10" ></textarea>
		</div>
		<div data-view="conf-cancel" style="display: none;">
			<textarea class="text06"  name="econtentCancelBody" id="econtentCancelBody"  cols="80" rows="10" ></textarea>
		</div>
	</div>
	<div class="actions">
		<button type="button" class="input-gray" id="saveTempButton">${LANG['website.admin.email.template.savetemplate']}</button>
		<button type="button" class="input-gray" id="resetTempButton" style="margin-left: 30px;">${LANG['website.admin.email.template.recorvytodeafult']}</button>
		<span class="message_span" style="display: none;">
	        	<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${LANG['system.email.temp.alertinfo1']}</label>
	    </span>
	</div>
</div>
</div>

<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/lib/kindeditor.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
var editorForInvite, editorForUpdate, editorForCancel;
KindEditor.ready(function(K) {
	editorForInvite = K.create('textarea[name="econtentInvitBody"]', {
		filterMode: false,
		resizeType : 0,
		width: "750px",
		height: "400px",
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		afterCreate: function() {
			updateAdminEmailTemplate(1);
		},
		afterBlur:function(){
		}, 
		items : [
			'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons'],
		themesPath: "/assets/css/kindeditor-4.1.5/themes/",
		langPath: "/assets/css/kindeditor-4.1.5/lang/"
	});
});

KindEditor.ready(function(K) {
	editorForUpdate = K.create('textarea[name="econtentUpdateBody"]', {
		filterMode: false,
		resizeType : 0,
		width: "750px",
		height: "400px",
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		afterCreate: function() {
			updateAdminEmailTemplate(2);
		},
		afterBlur:function(){
		}, 
		items : [
			'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons'],
		themesPath: "/assets/css/kindeditor-4.1.5/themes/",
		langPath: "/assets/css/kindeditor-4.1.5/lang/"
	});
});

KindEditor.ready(function(K) {
	editorForCancel = K.create('textarea[name="econtentCancelBody"]', {
		filterMode: false,
		resizeType : 0,
		width: "750px",
		height: "400px",
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		afterCreate: function() {
			updateAdminEmailTemplate(3);
		},
		afterBlur:function(){
		}, 
		items : [
			'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons'],
		themesPath: "/assets/css/kindeditor-4.1.5/themes/",
		langPath: "/assets/css/kindeditor-4.1.5/lang/"
	});
});

$(function() {
	$(".tabs").slideBar({
	    clickCallback: function(elem) {
	        elem.blur();
	        var tab = elem.parent(),
			name = tab.attr('data-tab'),
			panel = tab.closest('.tabview');

			// switch tabs status
			tab.addClass('active').siblings().removeClass('active');
			$(".message_span").hide();
			// switch views status
			var viewspanel = panel.find('[data-view]:first').parent();
			viewspanel.children('[data-view]').hide().filter('[data-view="' + name + '"]').show();
	    }
	});
	
	$("#saveTempButton").bind("click", function() {
		var index = $(".tabs").find(".active").index()+1;
		saveAdminEmailTemplate(index);
	});
	
	$("#resetTempButton").bind("click", function() {
		var index = $(".tabs").find(".active").index()+1;
		resetAdminEmailTemplate(index);
	});
});

//初始化模板
function updateAdminEmailTemplate(type) {
	app.updateAdminEmailTemplate(type, function(result) {
		if (result && result.emailContent) {
			if (type=="1") {
				editorForInvite.html(result.emailContent);	
			} else if(type=="2") {
				editorForUpdate.html(result.emailContent);
			} else {
				editorForCancel.html(result.emailContent);				
			}
		}
	});
}
//保存模板
function saveAdminEmailTemplate(type) {
	var tid = $("#eTempId").val();
	var t_content = "";
	if(editorForInvite.isEmpty() || editorForUpdate.isEmpty() || editorForCancel.isEmpty()){
		top.errorDialog("${LANG['website.admin.email.template.inputtempcontent']}");
		return;
	} else {
		if(type=="1") {
			t_content = editorForInvite.html();	
		} else if(type=="2") {
			t_content = editorForUpdate.html();
		} else {
			t_content = editorForCancel.html();
		}
	}
	app.saveAdminEmailTemplate(tid, t_content, type, function(result) {
		if (result) {
			if(result.message=="success"){
				//parent.successDialog("${LANG['system.email.temp.alertinfo1']}");		
				$(".message_span").show();
			} else {
				parent.errorDialog("${LANG['system.email.temp.syserror']}");
			}
		
		}
	});
}	
//恢复模板
function resetAdminEmailTemplate(type) {
	app.resetAdminEmailTemplate(type, function(result) {
		if (result && result.emailContent) {
			if(type=="1") {
				editorForInvite.html(result.emailContent);	
			} else if(type=="2") {
				editorForUpdate.html(result.emailContent);	
			} else {
				editorForCancel.html(result.emailContent);	
			}
		}
	});
}	
</script>
</body>
</html>