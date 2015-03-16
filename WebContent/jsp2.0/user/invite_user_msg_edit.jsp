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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css?var=12" />
	<style type="text/css" >
		.edit{
			 border: 1px solid #DADADA;
		}
		.show-area {
		    border: 1px solid #FFFFFF;
		    border-radius: 3px 3px 3px 3px;
/* 		    box-shadow: 0 1px #F7F7F7 inset; */
		    height: 15px;
		    line-height: 15px;
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
	<c:set var="inviteCount" value="${fn:length(confUserList)}" />
</head>
  <body>
  	<div class="invite-dialog">
		<div class="wrapper">
			<section>
				<div class="choose">
					<p><a class="input-gray" onclick="inventFromContacts()">${LANG['website.user.invite.edite.selectbycontacts']}</a></p><p style="float: right;margin-top: -25px;margin-right: 11px;" ><a  href="/user/inviteUser?confId=${confId}" >${LANG['website.user.invite.list.invited']} ${inviteCount}  ${LANG['website.user.invite.list.people']}</a></p>
					<form onsubmit="return false;">
						<input type="text" id="itemName" maxlength="32" name="itemName" placeholder="${LANG['website.user.invite.edite.name']}" class="input-text name">
						<input  type="text" id="itemPhone" name="itemPhone"  placeholder="${LANG['website.user.org.regsiter.list.mobile']}" class="input-text email">
						<i class="icon icon-add" onclick="addItem();"></i>
						<span id="errormsg" class="error-item" style="display: none;"><span class="error"><i class="icon"></i><label id="errorinfo">${LANG['website.user.invite.edite.errorinfo1']}</label></span></span>
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
			<div class="form-buttons">
				<input id="sendbtn"  type="button" class="button input-gray" onclick="saveContact();" value="${LANG['website.common.option.send']}" />
				<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js?vad=11"></script>
<script type="text/javascript">
var confId = "${confId}";
//var nodata = "<div class=\"common-empty\">您尚未邀请与会者</div>";
function saveContact() {
	if(hasRepeatPhone()){
		return;
	}
	if($("li .error-item").length>0)return;
	var data = {};
	data.confId = confId;
	data.sendType = "${sendType}";
	data.isCalling = false;
	data.users = [];
	$.each($("li"), function(i, elem) {
		var userbase = {};
		userbase.userName = $(elem).find("input[name=contactName]").val();
		userbase.userEmail = $(elem).find("input[name=email]").val();
		userbase.telephone = $(elem).find("input[name=phone]").val();
		userbase.contactId = $(elem).find("input[name=userId]").val();
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
	
	app.inventContactMsg(data, function(result) {
		if(result) {
			var url = parent.$("#eidtInventContact").find("iframe").attr("src");
			if(url){
				url = addUrlParam(url, "t", Math.random());
				parent.$("#eidtInventContact").find("iframe").attr("src", url);	
			}
			document.write(result);
		}else{
			top.errorDialog(result.message);
		}
	}, {message:"${LANG['bizconf.jsp.inviteFirst.res2']}...", ui:parent},function(){
		parent.errorDialog("${LANG['website.user.invite.msg.failtureinfo']}");
	});
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

//判断是否有重复记录
function hasRepeatPhone(){
	var regsiter = new Object();
	for(var i=0; i<$("input[name=phone]").length;i++){
		var ipt = $("input[name=phone]").get(i);
		if(!($(ipt).val())){
			continue;
		}
		if(regsiter[$(ipt).val()]){
			$(ipt).focus();
			parent.errorDialog("${LANG['website.site.user.meeting.sms.warninfo01']}");
			return true;
		}
		regsiter[$(ipt).val()]=true;
	}
	return false;
}

var reg_email =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var reg_tel = /(^((\+86)?|\(\+86\)|\+86\s)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s)0?([1-9]\d-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/;
var reg_phone =  /^0{0,1}(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/;
function addItem(){
	if(!$("#errormsg").is(":hidden")){
		return;
	}
	var name =$("#itemName").val();
	var phone =$("#itemPhone").val();
	var email =$("#itemEmail").val();
	
	if(!phone){
		$("#errorinfo").html("${LANG['website.site.user.meeting.sms.warninfo02']}");
		$("#errormsg").show();
		$("#itemPhone").addClass("error-text");
		$("#itemPhone").focus();
		return;
	}
	/*
	else if(phone && !reg_phone.test(phone)){
	$("#errorinfo").html("请正确填写手机号码");
	$("#errormsg").show();
	$("#itemPhone").addClass("error-text");
	$("#itemPhone").focus();
	return;
}
	*/
	
	var data = new Object();
	data.name = name;
	data.phone = phone;
	data.email = email;
	appendRow(data);
	
	$("#itemName").val("");
	$("#itemPhone").val("");
	$("#itemEmail").val("");
	$("#itemName").focus();
}
function appendRow(data){
	var userId = data.userId || "";
	var len = $("ul li").length+1;
	
	var html =  "<li><span class=\"numb\">"+len+"</span>"
				+"<input  name=\"userId\" value=\""+userId+"\" type=\"hidden\"><input type=\"text\" maxlength=\"32\" name=\"contactName\" class=\"show-area name\" value=\""+data.name+"\"/>"
				+"<input class=\"show-area email\" name=\"phone\" value=\""+data.phone+"\">"
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
		text += "${LANG['bizconf.jsp.inviteFirst.res12']} "+repated+ " ${LANG['bizconf.jsp.invitebymsg.repeatedtel']}";
		top.errorDialog(text);
	}
}

$(document).ready(function(){
	if($("li").length<1 && !$("#sendbtn").is(":hidden")){
		$("#sendbtn").hide();
		$(".anchor-cancel").css("margin-left","0px");
	}
	if ($("li").length<1) {
		$(".common-empty").show();
	}
	
	/**
	$("itemEmail").keyup(function(event){
		if(event.keyCode == '13'){
			addItem();
		}else{
			if(!$(this).val()){
				$("#errorinfo").html("邮箱不能为空！");
				$("#errormsg").show();
				$(this).addClass("error-text");
			}else if(!reg_email.test($(this).val())){
				$("#errorinfo").html("请输入正确邮箱！");
				$("#errormsg").show();
				$(this).addClass("error-text");
			}else{
				$("#errormsg").hide();
				$(this).removeClass("error-text");
			}
		}
	});*/
	
	$("li").live({mouseover:function(){
		$(this).find("i").addClass("icon-remove");
		//$(this).find("input").css("border","1px solid #DADADA");
		$(this).find("input").removeClass("hidden_border");
		$(this).find("input").addClass("show_border");
	},mouseout:function(){
		$(this).find("i").removeClass("icon-remove");
		//$(this).find("input").css("border","1px solid #fff");
 		$(this).find("input").removeClass("show_border");
 		$(this).find("input").addClass("hidden_border");
		 
	}});
	
	$("input[name=phone]").live({
		keyup:function(){
			if($(this).val().length!=11){
				var errorinfo = "<span class=\"error-item\"><span class=\"error\"><i class=\"icon\"></i>${LANG['website.site.user.meeting.sms.warninfo03']}</span></span>";
				$(this).parent().find(".error-item").remove();
				$(this).parent().append(errorinfo);
				$(this).addClass("error-text");
				$(this).css("border","1px solid #C56360");
			}else if(!reg_phone.test($(this).val())){
				var errorhtml = "<span class=\"error-item\"><span class=\"error\"><i class=\"icon\"></i>${LANG['website.site.user.meeting.sms.warninfo04']}</span></span>";
				$(this).parent().find(".error-item").remove();
				$(this).parent().append(errorhtml);
				$(this).addClass("error-text");
				$(this).css("border","1px solid #C56360");
			}else{
				$(this).removeAttr("style");
				$(this).parent().find(".error-item").remove();
				$(this).removeClass("error-text");
			}
		}
	});
	
	
	//删除邀请人
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
	//校验邮箱是否可用
	/*
	$("#itemEmail").keyup(function(event){//blur
		if(!$(this).val()){
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo2']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if($(this).val().length>64){
			$("#errorinfo").html("${LANG['system.email.config.lengthinfo64']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(!reg_email.test($(this).val())){
			$("#errorinfo").html("${LANG['website.user.invite.edite.errorinfo1']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(isRepeatedEmail($(this).val())){
			$("#errorinfo").html("${LANG['website.user.invite.email.repeat']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else{
			$("#errormsg").hide();
			$(this).removeClass("error-text");
			 
			if (event.keyCode == 13){       
		    	addItem();	
		    }   
		}
	});
	*/
	
	//检验手机号码
	$("#itemPhone").keyup(function(event){
		if(!$(this).val()){
			$("#errorinfo").html("${LANG['website.site.user.meeting.sms.warninfo02']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if($(this).val().length<11){
			$("#errorinfo").html("${LANG['website.site.user.meeting.sms.warninfo03']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(!reg_phone.test($(this).val())){
			$("#errorinfo").html("${LANG['website.site.user.meeting.sms.warninfo04']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else if(isRepeatedPhone($(this).val())){
			$("#errorinfo").html("${LANG['website.site.user.meeting.sms.warninfo05']}");
			$("#errormsg").show();
			$(this).addClass("error-text");
		}else{
			$("#errormsg").hide();
			$(this).removeClass("error-text");
			 
			if (event.keyCode == 13){       
		    	addItem();	
		    }   
		}
	});
	
	
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=itemName]").watermark("${LANG['website.user.invite.edite.name']}");
		$("input[name=itemPhone]").watermark("${LANG['website.user.org.regsiter.list.mobile']}");
	}
	setCursor("itemName", $("#itemName").val().length);
	
});
//function enterSumbit(){  
//   var event=arguments.callee.caller.arguments[0]||window.event;   
//    if (event.keyCode == 13){       
//    	addItem();	
//    }   
//} 
function closeDialog(result) {
	var dialog = parent.$("#inventContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
function viewInviteUser(confId){
	top.editInventContact(confId);
}
</script>
</html>
