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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<div class="choose-dialog">
	<div class="wrapper">
		<section>
			<div class="choose-panel">
				<div class="tabview choose-content">
					<ul class="tabs clearfix">
						<li class="tab contact active" data-tab="contact"><a href="/user/contact/invitelist">${LANG['website.user.invite.choose.contact']}</a></li>
						<li class="tab group" data-tab="group"><a href="/user/group/invitelist">${LANG['website.user.invite.choose.group']}</a></li>
						<li class="tab account" data-tab="account"  style="width: 130px;"><a href="/user/contact/showEnterpriseContacts?showAll=1">${LANG['website.user.invite.choose.regsiter']}</a></li>
					</ul>
					<div class="views">
						<iframe frameborder="0" width="100%" style="height: 382px;" id="contactFrame" name="contactFrame" scrolling="no" src="/user/contact/invitelist"></iframe>
					</div>
				</div>
			</div>
		</section>
		<div class="form-buttons">
			<input type="button" value="${LANG['website.common.option.confirm']}" onclick="selectContacts();" class="button input-gray">
			<a onclick="javascript:closeDialog();" class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
		</div>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
function initTab() {
	$(".tabs").slideBar({
        clickCallback: function(elem) {
        	var href = $(elem).attr("href");
        	$("#contactFrame").attr("src",href);
        }
    });
}
jQuery(function($) {
	initTab();
});

function selectContacts() {
	//var data = {"name": "alan", "email": "asd@qq.com", "phone": "12312311"};
	var datas = window.frames["contactFrame"].getContactsData();
	//var dialog = parent.$("#inventContact").find("iframe")[0].contentWindow.appendRow(data);
	if(datas && datas.ids){
		$.ajax({
	      	type: "POST",
	      	url:"/user/group/getContactsByGroups",
	      	data:{ids:datas.ids},
	      	dataType:"json",
	      	async:false,
	      	success:function(data){
	      		var contacts = new Array();
				if(data){
					for(var i=0;i<data.length;i++){
						var item = new Object();
						item.name = data[i].contactName;
						item.email = data[i].contactEmail;
						item.phone = data[i].contactPhone || data[i].contactMobile;
						item.userId = data[i].contactId;
						contacts.push(item);
					}
					parent.$("#inventContact").find("iframe")[0].contentWindow.addByExtral(contacts);
				}else{
					alert('Server inner error!');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		});  
	}else if(datas){
		var win = top.$("#inventContact").find("iframe")[0].contentWindow || top.$("#inventContact").find("iframe")[0].window;
		win.addByExtral(datas);
		//alert(datas);
	}
	closeDialog();
} 


function closeDialog(result) {
	var dialog = top.$("#importContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
</body>
</html>