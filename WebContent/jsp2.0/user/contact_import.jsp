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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<div class="import-dialog">
	<div class="wrapper">
		<section>
			<div class="import-panel">
				<div class="tabview">
					<ul class="tabs clearfix">
						<li class="tab company active" data-tab="company"><a href="/user/contact/showEnterpriseContacts">${LANG['website.user.contact.import.byregsiter']}</a></li>
						<li class="tab namelist" data-tab="namelist"><a href="/user/contact/importContactsByExcel">${LANG['website.user.contact.import.byexcel']}</a></li>
					</ul>
					<div class="views">
						<div class="view active" data-view="company">
							<iframe frameborder="0" width="100%" height="382px;" id="encontentFrame" name="encontentFrame" scrolling="no" src="/user/contact/showEnterpriseContacts"></iframe>
						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="form-buttons import-dialog-buttons"> 
			<input type="button" class="button input-gray" onclick="doImportContacts();" value="${LANG['website.common.option.confirm']}">
			<a  class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
function initTab() {
	$(".tabs").slideBar({
        clickCallback: function(elem) {
        	var url = $(elem).attr("href");
        	//console.log(name);
        	//alert(name);
        	selectTab(url);
        }
    });
}

function selectTab(url) {
	importFlag = url;
	$("#encontentFrame").attr("src",url);
	// var viewspanel = $(".views");
	// viewspanel.children('[data-view]').hide().filter('[data-view="' + name + '"]').show();         		
}
jQuery(function($) {
	initTab();
});

var importFlag = '/user/contact/showEnterpriseContacts';
function doImportContacts() {
	if(importFlag == "/user/contact/showEnterpriseContacts"){
		var data = window.frames["encontentFrame"].getContactsIds();
		if(data && data.length>0){
			app.addContacts(data, function(result) {
				if(result){
					if(result.status==1){
						//top.successDialog(result.message);
						top.showImportSuccessDialog('/user/contact/showImportInfoDialog?successflag=1');
						top.showURL("/user/contact/goContacts");
						closeDialog();
					} else {
						parent.errorDialog(result.message);
					}
				}
			}, {message:"${LANG['website.user.contact.import.importing']}...", ui:parent});	
		} else {
			parent.errorDialog("${LANG['website.user.contact.import.selectitem']}");
		}		
	} else {
		window.frames["encontentFrame"].importContacts();
	}
	//var data = {"name": "alan", "email": "asd@qq.com", "phone": "12312311"};
	//window.frames["contactFrame"].importContacts();
	//closeDialog();
}

function showInfo(info,status){
	if(status==1){
		top.successDialog(info);
	}else{ 
		top.errorDialog(info);
	}
	//刷新页面列表
	top.showURL("/user/contact/goContacts");
	closeDialog();
}

function closeDialog() {
	var dialog = top.$("#importContactDialog");
	dialog.trigger("closeDialog");
}
</script>
</body>
</html>