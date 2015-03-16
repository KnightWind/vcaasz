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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css?var=11" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
	<style type="text/css" >
		.edit{
			 border: 1 solid #DADADA;
		}
		.show-area {
		    border: 0 solid #DADADA;
		    border-radius: 3px 3px 3px 3px;
		    box-shadow: 0 1px #F7F7F7 inset;
		    height: 16px;
		    padding: 5px;
		}
		.error-text {
		    background: none repeat scroll 0 0 #FAE2E2;
		    border-color: #C56360;
		    color: #C51500;
		}
		.show_border{
			border: 1px solid #DADADA;
		}
		.hidden_border{
			border: 1px solid #fff;
		}
	</style>
</head>
  <body>
  	<div class="invite-dialog">
	<div class="wrapper">
	<section>
	<div class="choose">
			<p><a class="input-gray" onclick="inventFromContacts()">${LANG['website.user.invite.edite.selectbycontacts']}</a></p>
			<form id="phoneCall" onsubmit="return false;">
			<input type="text" id="itemName" placeholder="${LANG['website.user.invite.edite.name']}" class="input-text name">
			<input  type="text" id="itemPhone" placeholder="${LANG['website.user.invite.edite.telephone']}" class="input-text phone">
			<i class="icon icon-add" onclick="addItem();"></i>
			<span class="form-tip" style="top: 52px;"><span id="itemPhoneTip"></span></span>
			<span id="errormsg" class="error-item" style="display: none;"><span class="error"><i class="icon"></i><label id="errorinfo">${LANG['website.user.invite.edite.errorinfo7']}</label></span></span>
			</form>
	</div>
	<div class="user-list">
		<ul>
		</ul>
		<div class="common-empty" style="display: none;">
						${LANG['website.user.inviteuser.edit.nodata']}
		</div>
	</div>
	</section>
	</div>
		<div class="form-buttons">
				<input id="sendbtn"  type="button" class="button input-gray" onclick="saveContact();" value="${LANG['website.user.invite.tel.calling.action']}" />
				<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
		</div>
	</div>
</body>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript">
var confId = "${confId}";

function saveContact() {
	if(hasRepeatPhone()){
		return;
	}
	if($("li .error-item").length>0)return;
	var data = {};
	data.confId = confId;
	data.isCalling = true;
	data.users = [];
	$.each($("li"), function(i, elem) {
		var userbase = {};
		userbase.userName = $(elem).find("input[name=contactName]").val();
		userbase.telephone = $(elem).find("input[name=phone]").val();
		//userbase.telephone = $(elem).find(".phone").val();
		userbase.userId = $(elem).find("input[name=userId]").val();
		if(userbase.userEmail || userbase.telephone){
			data.users.push(userbase);
		}
	});
	if(data.users.length==0){
		$("li").each(function(){
			$(this).remove();
		});		 
		parent.errorDialog("${LANG['website.user.invite.edite.errorinfo3']}");
		return;
	}
	app.inventContact(data, function(result) {
		if(result) {
				//alert(result);
				var url = parent.$("#eidtInventContact").find("iframe").attr("src");
				if(url){
					url = addUrlParam(url, "t", Math.random());
					parent.$("#eidtInventContact").find("iframe").attr("src", url);	
				}
				//closeDialog(result);
				document.write(result);
		}else{
			top.errorDialog(result.message);
		}
	}, {message:"${LANG['bizconf.jsp.inviteFirst.res2']}...", ui:parent});
}

function inventFromContacts(){
	parent.inventFromContacts();
}
	

function isRepeatedPhone(phone){
	if(!phone){
		return false;
	}
	if($("input[name=phone]").filter("[value='"+phone+"']").length >0){
		return true;
	}
	return false;
}

//是否有重复号码
function hasRepeatPhone(){
	var regsiter = new Object();
	for(var i=0; i<$("input[name=phone]").length;i++){
		var ipt = $("input[name=phone]").get(i);
		if(!($(ipt).val())){
			continue;
		}
		if(regsiter[$(ipt).val()]){
			$(ipt).focus();
			parent.errorDialog("${LANG['website.user.invite.edite.errorinfo8']}");
			return true;
		}
		regsiter[$(ipt).val()]=true;
	}
	return false;
}


var reg_email =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var reg_tel = /(^((\+86)?|\(\+86\)|\+86\s)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s)0?([1-9]\d-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(#\d{4})?$)/;
function addItem(){
		if(!$("#errormsg").is(":hidden")){
				return;
		}
		 var name =$("#itemName").val();
		 var email =$("#itemEmail").val();
		 var phone =$("#itemPhone").val();
		 if(!phone){
			//top.errorDialog("${LANG['website.user.invite.edite.errorinfo9']}");
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo9']}");
			$("#errormsg").show();
			$("#itemPhone").addClass("error-text");
			$("#itemPhone").focus();
			return;
		//}else if(email && !reg_email.test(email)){
		//	top.errorDialog("${LANG['bizconf.jsp.inviteFirst.res8']}");
		//	$("#itemEmail").focus();
		//	return;
		}else if(phone && !reg_tel.test(phone)){
			//top.errorDialog("${LANG['website.user.invite.edite.errorinfo7']}");
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo7']}");
			$("#errormsg").show();
			$("#itemPhone").addClass("error-text");
			$("#itemPhone").focus();
			return;
		}else if(isRepeatedPhone(phone)){
			//top.errorDialog("${LANG['website.user.invite.edite.errorinfo8']}");
			$("#errorinfo").html("${LANG['website.user.invite.phone.repeat']}");
			$("#errormsg").show();
			$("#itemPhone").addClass("error-text");
			$("#itemPhone").focus();
			return;
		}
		var data = new Object();
		data.name = name;
		data.email = email;
		data.phone = phone;
		appendRow(data);
		
		$("#itemName").val("");
		$("#itemEmail").val("");
		$("#itemPhone").val("");
}

function appendRow(data){
	var userId = data.userId || "";
	var len = $("ul li").length+1;
	
	var html =  "<li><span class=\"numb\">"+len+"</span>"
				+"<input  name=\"userId\" value=\""+userId+"\" type=\"hidden\"><input type=\"text\" name=\"contactName\" class=\"show-area name\" value=\""+data.name+"\"/>"
				+"<input class=\"show-area phone\" name=\"phone\" value=\""+data.phone+"\">"
				+"<i class=\"icon\"></i></li>";
				
	$(html).appendTo("ul");
	if($("#sendbtn").is(":hidden")){
		$("#sendbtn").show();
		$(".anchor-cancel").css("margin-left","50px");
		
		$(".common-empty").hide();
	}
}
 

function addByExtral(datas){
	var repated = "";
	for(var i=0;i<datas.length;i++){
		if(isRepeatedPhone(datas[i].phone)){
			repated += datas[i].name;
			repated += ",";
			continue;
		}
		appendRow(datas[i]);
	}
	var text = "${LANG['bizconf.jsp.inviteFirst.res11']}";
	if(repated){
		text += "${LANG['bizconf.jsp.inviteFirst.res12']} "+repated+ "${LANG['website.user.invite.phone.repeat']}";
		top.errorDialog(text);
	}
}

$(document).ready(function(){
	//$("#itemPhone").watermark("${LANG['website.user.contact.edit.warninfo12']}");
	
	if($("li").length<1 && !$("#sendbtn").is(":hidden")){
		$("#sendbtn").hide();
		$(".anchor-cancel").css("margin-left","0px");
	}
	if ($("li").length<1) {
		$(".common-empty").show();
	}
	
	$("li").live({mouseover:function(){
		$(this).find("i").addClass("icon-remove");
		$(this).find("input").removeClass("hidden_border");
		$(this).find("input").addClass("show_border");
	},mouseout:function(){
		$(this).find("i").removeClass("icon-remove");
		$(this).find("input").removeClass("show_border");
		$(this).find("input").addClass("hidden_border");
		 
	}});
	
	$("input[name=phone]").live({
		keyup:function(){
			var errorhtml = "<span class=\"error-item\"><span class=\"error\"><i class=\"icon\"></i>${LANG['website.user.invite.edite.errorinfo7']}</span></span>";
			if(!reg_tel.test($(this).val())){
				$(this).parent().find(".error-item").remove();
				$(this).parent().append(errorhtml);
				$(this).addClass("error-text");
				$(this).css("border","1px solid #C56360");
				
			}else if(reg_tel.test($(this).val())){
				$(this).removeAttr("style");
				$(this).parent().find(".error-item").remove();
				$(this).removeClass("error-text");
			}
		}
	});
	
	$("li i").live("click",function(){
		$(this).parent().remove();
		//刷新序号
		var trs = $(".numb");
		for(var i=0;i<trs.length;i++){
			$(trs[i]).html((i+1)+".");
		}
		
		if($("li").length<1 && !$("#sendbtn").is(":hidden")){
			$("#sendbtn").hide();
			$(".anchor-cancel").css("margin-left","0px");
			$(".common-empty").show();
		}
	});
	
	
	$("#itemPhone").keyup(function(){
		if(!$(this).val()){
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo9']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(!reg_tel.test($(this).val())){
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo7']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(isRepeatedPhone($(this).val())){
			$("#errorinfo").html("${LANG['website.user.invite.phone.repeat']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else{
			$("#errormsg").hide();
			$(this).removeClass("error-text");
		}
	});
	
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=itemName]").watermark("${LANG['website.user.invite.edite.name']}");
		$("input[name=itemPhone]").watermark("${LANG['website.user.invite.edite.telephone']}");
	}
	
	$.formValidator.initConfig({
		formID : "phoneCall",
		debug : false,
		ajaxForm: true,
		onSuccess : function() {
			//saveContact();
		},
		onError : function() {
			
		}
	});
	
	$("#itemPhone").formValidator({
		empty : true,
		onShow : "${LANG['website.user.profile.val.mobile'] }",
		onFocus: "",
		onCorrect: "",
		onEmpty: ""
	});
	setCursor("itemName", $("#itemName").val().length);
});

function closeDialog(result) {
	var dialog = parent.$("#inventContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
</html>
