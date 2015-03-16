<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<cc:confList var="JOIN_TYPE_CONFID"></cc:confList>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${siteBase.siteName}--${LANG['bizconf.jsp.index.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>

<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<script language="javascript">
	if(window.top!=window.self){
		window.top.location.href="/"; 
	}
 function killerrors() {
	 return true;
 }
 window.onerror = killerrors;
</script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-1.9.2.custom.min.js?ver=${version}"></script>
<!--[if lte IE 6]>  
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></SCRIPT>  
<![endif]-->
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-i18n.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.tipsy.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<%@ include file="/jsp/common/cookie_util.jsp"%>
<c:if test="${!empty errorMessage}">
<script type="text/javascript">
$(function() {
	errorDialog("${errorMessage}");
});
</script>
</c:if>
<c:if test="${!empty infoMessage}">
<script type="text/javascript">
$(function() {
	successDialog("${infoMessage}");	
});
</script>
</c:if>
<cc:confList var="JOIN_TYPE_CONFID"/>
<script type="text/javascript">

/*
 *${LANG['bizconf.jsp.index.res4']}Iframe${LANG['bizconf.jsp.index.res5']}
 *	
 */

function changeIframeSize(frameId,widthNum,heightNum){
	var frameObj=document.getElementById(frameId);
	if (frameObj){
		if (widthNum>0){
			frameObj.style.posWidth=widthNum;
			//frameObj.setAttribute("width",widthNum)
		}
		if (heightNum>0){
			//style.height
			if ($.browser.msie && $.browser.version<7) {
				frameObj.style.posHeight=heightNum;	
			} else {
				frameObj.style.height=heightNum+"px";	
			}
			//frameObj.setAttribute("height",heightNum)
		}
	} 
	$("#joinMeeting").height(heightNum);
	$("#joinMeeting").parent().height(heightNum);
// 	$("#joinMeeting").trigger("resize");
}

jQuery(function($) {
	if ($.browser.msie && $.browser.version<7) {//for ie6 png
		$(".png").each(function() {
			$(this).attr('width',$(this).width());
			$(this).attr('height',$(this).height());
			var imgSrc = $(this).attr('src');
			$(this).get(0).runtimeStyle.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader' + '(src=\'' + imgSrc + '\', sizingMethod=\'scale\');';
			$(this).attr('src', '/static/images/blank.gif');
		});		
	}
	$('body').bind(EVENT_CREATE, function(event, result, type) {
		if(type==VIEW_TYPE.bookMeeting) {
			jumpToMeetingPanel(result);
		} else if(type==VIEW_TYPE.group){
			if(result && result.status==1){
				successDialog(result.message);
				showChildURL("/user/group/list");	
			} else {
				errorDialog(result.message);
			}
		} else if (type==VIEW_TYPE.contact) {
			if(result && result.status==1){
				successDialog(result.message);
				showURL("/user/contact/goContacts");	
			} else {
				errorDialog(result.message);
			}
		} else if(type==VIEW_TYPE.attendee){
			if(result && result.status==1){
				successDialog(result.message);
				refreshChildIframe();
			} else {
				errorDialog(result.message);
			}
		} else if(type==VIEW_TYPE.tempMeeting) {
			joinImmediatelyConf(result);
		}
	});
	$('body').bind(EVENT_UPDATE, function(event, result, type) {
		if(type==VIEW_TYPE.bookMeeting) {
			refreshChildIframe();
		}
	});

	//left menu
	$(".nav-menu").slideBar({
        clickCallback: function(elem) {
            var isIgnore = elem.hasClass("isParent");
            if (!isIgnore) {
                var href = elem.attr("href");
                var loginUser = "${user.id}";
    			if(loginUser || href=="/downCenter/downClient" || href=="/user/conf/getPublicControlPadIndex" || href=="/user/notice/list") {
                    showURL(href);	
    			} else {
    				login();
    			}
            } 
        }
    });
	
	//resize iframe height
	$("#mainFrame").load(function(){
		resizeHeight();  
		joinMtgFromEmail();
    });
	//change lang
	//$("#langForm").find("select").not(".skipThese").uniform();
	$("#langForm select").change(function () {
		var lang = $(this).val();
		changeLang(lang);
	});

	$(window).bind("resize", function() {
		resizeHeight();
		//resizeFloatBar();
	});
	//call online
	//$(".floatContactMe").callOnline();
	 
	popupNotice();
// 	showRemindBar();
	var needResetPass = "${needResetPass}";
	if(needResetPass != null && needResetPass =="true"){
		resetPass();
	}
	//findFastIp();
});
window.setInterval(resizeHeight, 1000);
function resizeHeight() {
	var height = $("#mainFrame").contents().find("body").height()+50;
	var screenHeight = $(window).height()-144;
	if(screenHeight>0){
		if(height<screenHeight) {
			height = screenHeight;
		}
		if(height<600){
			height = 600;
		}
		$("#mainFrame").height(height);
		$(".main_right").height(height);
		$(".main_left").height(height);	
	}
}
      
function loadImg(param) {
	if(!ISFASTIP) {
		ISFASTIP=true;
		var fastIp = $(param).attr("domain");
		$.cookie("b_down_s", fastIp);
	}
}

function findFastIp() {
	var autoUrl = "${downloadServers}";
	autoUrl = autoUrl.split(",");
	var container = $("#b_down_s");
	for(var i=0;i<autoUrl.length;i++) {
		var param = autoUrl[i].trim();
		var ipUrl = formatIpUrl(param);
		$('<img style="display:none" src='+ipUrl+' width=0 height=0 onLoad="loadImg(this)" domain='+param+'>').appendTo(container);
	}
}

function reloadPage() {
	window.location.href="/";
}
//refresh Main Iframe Content
function showURL(url) {
	url = addUrlParam(url, "t", Math.random());
	$('#mainFrame').attr("src", url);
}
//refresh Child Iframe
function showChildURL(url) {
	url = addUrlParam(url, "t", Math.random());
	$("#mainFrame")[0].contentWindow.$("#contentFrame").attr("src", url);
}

function hideTabPanel(elem, callback) {
	var tabContent = window.frames["mainFrame"].document.getElementById(elem);
	if(tabContent && $(tabContent).is(":visible")){
		var icon = $(tabContent).prev().find(".panel_icon");
		hidePanel(tabContent, icon, function(){
			callback(tabContent);
		});
	} else {
		callback(tabContent);
	}	
}
function showTabPanel(elem, callback) {
	var tabContent = window.frames["mainFrame"].document.getElementById(elem);
	if(tabContent && $(tabContent).is(":hidden")){
		var icon = $(tabContent).prev().find(".panel_icon");
		showPanel(tabContent, icon, function(){
			callback(tabContent);
		});
	} else {
		callback(tabContent);
	}
}
function jumpToMeetingPanel(result) {
	$(".nav-menu li").removeClass("active");
	var favorLi = $(".nav-menu").find(".ico11").parent();
	favorLi.addClass("active");
	showURL("/user/conf/getControlPadIndex?userRole=1");
}

function jumpToFavorLi(url) {
	url+="?t="+ Math.random();
	$(".nav-menu li").removeClass("active");
	var favorLi = $(".nav-menu").find(".ico6").parent();
	favorLi.addClass("active");
	$("#mainFrame").attr("src", url); 
}

//refresh group list
function refreshGroupList() {
	showChildURL("/user/group/list");	
}
//refresh child iframe
function refreshChildIframe() {
	var iframeSrc = $("#mainFrame")[0].contentWindow.$("#contentFrame").attr("src");
	showChildURL(iframeSrc);
}
//show billing detail
function showTelDetail(id,year,month) {
	$("<div id=\"billingView\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res8']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/common/billing/showTelDetail?userId="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 624,
		"height" : 587
	});
}

//show billing detail
function showDataFeeDetail(id,year,month) {
	$("<div id=\"dataFeeView\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res8']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/common/billing/showDataDetail?userId="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 624,
		"height" : 550
	});
}
//
function showConflogs(id) {
	$("<div id=\"logview\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res8']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conflog/loglist?userPage=true&confId="+id,
		"type" : VIEW_TYPE.group,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 574
	});
}
//add group
function addGroup(id) {
	$("<div id=\"addGroup\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.add_group.res1']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/group/editGroup?id="+id,
		"type" : VIEW_TYPE.group,
		"action" : ACTION.create,
		"width" : 474,
		"height" : 304
	});
}
//view contact
function viewContact(id) {
	$("<div id=\"viewContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res9']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/group/showContacts?viewOnly=1&group_id="+id,
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.view,
		"width" : 804,
		"height" : 584
	});	
}
//add contact
function addContact(id) {
	$("<div id=\"addContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.add_contacts.res1']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/contact/editContact?id="+id,
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 474,
		"height" : 488
	});	
}
//import contact
function importContact() {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res10']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/contact/goContactsSelect",
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 631
	});	
}
//add contact from group
function addContactFromGroup(id) {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res11']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/group/importContacts?group_id="+id,
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 584
	});	
}
//bulk import contact
function bulkImportContact() {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res12']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/contact/importPop",
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 631
	});	
}
//invent contact
function inventContact(confId,isTel) {
	var telCall = "false";
	if(!!isTel){telCall = "true";}
	$("<div id=\"inventContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.conf_list_main.res2']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/contact/goInviteContacts?confId="+confId+"&isTelCall="+telCall,
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 475
	});	
}
//invent contact
function editInventContact(confId) {
	$("<div id=\"eidtInventContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.conf_list_main.res2']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/inviteUser?confId="+confId,
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 624,
		"height" : 539
	});	
}

function contactImport() {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res13']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/contact/importPop",
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 804,
		"height" : 625
	});	
}
//conf info
function viewConf(id) {
	$("<div id=\"viewMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res14']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conf/view/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"action" : ACTION.view,
		"width" : 684,
		"height" : 409
	});
}

//view group
function viewGroup(id) {
	$("<div id=\"viewGroup\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res15']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/group/showContacts?group_id=" + id,
		"type" : VIEW_TYPE.group,
		"action" : ACTION.view,
		"width" : 804,
		"height" : 584
	});
}
//create or update createNewReservationConf
function createReservationConf(id) {
	var loginUser = "${user.id}";
	if(loginUser){
		if (id) {
			$("<div id=\"bookMeeting\"/>").showDialog({
				"title" : "${LANG['bizconf.jsp.index.res16']}",
				"dialogClass" : "ui-dialog-user",
				"url" : "/user/conf/update/" + id,
				"type" : VIEW_TYPE.bookMeeting,
				"action" : ACTION.update,
				"width" : 684,
				"height" : 580
			});
		} else {
			$("<div id=\"bookMeeting\"/>").showDialog({
				"title" : "${LANG['bizconf.jsp.index.res17']}",
				"dialogClass" : "ui-dialog-user",
				"url" : "/user/conf/createReservationConf",
				"type" : VIEW_TYPE.bookMeeting,
				"action" : ACTION.create,
				"width" : 684,
				"height" : 580
			});
		}
		
	} else {
		login();	
	}
}
//${LANG['bizconf.jsp.attended_conf_list.res2']}
function reCreateReservationConf(id) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.attended_conf_list.res2']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conf/reCreateconf/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"action" : ACTION.update,
		"width" : 684,
		"height" : 580
	});	
}

//update all ReservationConf
function updateReservationConf(id) {
	$("<div id=\"updateOneMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res18']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conf/getCycleConfDate/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"action" : ACTION.update,
		"width" : 474,
		"height" : 434
	});	
}

function delACycleConf(id) {
	$("<div id=\"updateOneMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res18']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conf/getCycleConfDate/" + id+"?delCycle=1",
		"type" : VIEW_TYPE.bookMeeting,
		"action" : ACTION.update,
		"width" : 474,
		"height" : 434
	});	
}

//${LANG['bizconf.jsp.conf_list_index.res5']}
function updateCycleMeetingInfo(id) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.conf_list_index.res5']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/conf/updateCycleConfInfo/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"action" : ACTION.update,
		"width" : 680,
		"height" : 580
	});	
}

//createImmediatelyConf
function createImmediatelyConf() {
	var loginUser = "${user.id}";
	if(loginUser){
		$("<div id=\"joinMeeting\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.index.res19']}",
			"dialogClass" : "ui-dialog-user",
			"url" : "/user/conf/createImmediatelyConf",
			"type" : VIEW_TYPE.tempMeeting,
			"action" : ACTION.create,
			"width" : 474,
			"height" : 197
		});	
	} else {
		login();
	}
}
function joinImmediatelyConf(result) {
	var url = "/join?joinType=${JOIN_TYPE_CONFID}&cId="+result.id;
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res19']}",
		"dialogClass" : "ui-dialog-user",
		"url" : url,
		"type" : VIEW_TYPE.linkTempMeeting,
		"action" : ACTION.create,
		"width" : 474,
		"height" : 184
	});	
	
}
//add calendar
function addCalendar(id) {
	$("<div id=\"addCalendar\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res3']}",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/user/email/outlook",
		"data": id,
		"type" : VIEW_TYPE.calendar,
		"action" : ACTION.create,
		"width" : 484,
		"height" : 204
	});	
}
//quick attendConf
function attendConf() {
	$("<div id=\"quickMeeting\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.help.res8']}",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/user/conf/attendConf",
		"type" : VIEW_TYPE.quickMeeting,
		"action" : ACTION.create,
		"width" : 400,
		"height" : 150
	});
}


function joinMeeting(joinType,cId){
	if(joinType==null || joinType==""){
		return false;
	}
	var titleName="${LANG['bizconf.jsp.help.res8']}";
	if(joinType=="${JOIN_TYPE_CONFID}"){
		titleName="${LANG['bizconf.jsp.conf_list_index.res48']}";
	}
	var divHeight="auto";
	if ($.browser.msie && $.browser.version<7) {
		divHeight=230;
	}
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-user",
		"url" : "/join/joinpage?joinType="+joinType+"&cId="+cId,
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" : divHeight
	});
}



function joinMeetingReload(){
	
	
	var domain=getDomain(); 
	var reloadValue=getCookieByDomain("reload",domain);
	if(reloadValue==null || reloadValue==""){
		return null;
	}
	var reloadArr=reloadValue.split(",");
	if(reloadArr.length!=7){
		return null;
	}

	var reload=reloadArr[0];
	var cId=reloadArr[1];
	var userName=reloadArr[2];
	var joinType=reloadArr[3];
	var code=reloadArr[4];
	var cpass=reloadArr[5];
	var rId=reloadArr[6];
	
	//alert("reload=="+reload);
	$("#reload").val(reload);
	$("#cId").val(cId);
	$("#rId").val(rId);
	$("#userName").val(userName);
	$("#joinType").val(joinType);
	$("#code").val(code);
	$("#cPass").val(cpass);
	
	
	if(joinType==null || joinType==""){
		return false;
	}
	var titleName="${LANG['bizconf.jsp.help.res8']}";
	if(joinType=="${JOIN_TYPE_CONFID}"){
		titleName="${LANG['bizconf.jsp.conf_list_index.res48']}";
	}
	
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-user",
		"url" : "about:blank",
		//"data":{"cId":cId,"cPass":cPass,"code":code,"reLoad":reLoad,"joinType":joinType,"userName":userName},
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" : "auto"
	});
	
	var frameObj=document.getElementById("dialogFrame");
	//alert("frameObj=="+frameObj);
	if(frameObj){
		document.getElementById("reLoadJoinConf").submit();
	}
	
}
function joinMeetingFromEmail(joinMtgUrl){
	var  titleName="${LANG['bizconf.jsp.conf_list_index.res48']}";
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-user",
		"url" : joinMtgUrl,//"/join/joinpage?joinType="+joinType+"&cId="+cId,
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" :"auto"
	});
}
function errorDialog(message, callback) {
	$("<div id=\"errorDiv\"/>").alertDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"type": "error",
		"message": message,
		"actions": ["${LANG['system.ok']}"],
		"callback": callback
	});
}

function confirmDialog(message, callback) {
	$("<div id=\"confirmDiv\"/>").alertDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"type": "confirm",
		"message": message,
		"actions": ["${LANG['system.cancel']}", "${LANG['system.ok']}"],
		"callback" : callback
	});
}

function successDialog(message) {
	$("<div id=\"successDiv\"/>").alertDialog({
		"title" : "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"message": message,
		"type": "success",
		"actions": ["${LANG['system.ok']}"]
	});
}

function logout() {
	$("<div/>").alertDialog({
		"title": "${LANG['bizconf.jsp.conf_invite_recv.res2']}",
		"dialogClass" : "ui-dialog-user",
		"message" : "${LANG['bizconf.jsp.index.res20']}?",
		"type": "confirm",
		"actions": ["${LANG['system.cancel']}", "${LANG['system.ok']}"],
		"callback" : function() {
			window.location.href="/user/logout";
			window.event.returnValue = false;
		}
	});
}
function login() {
	$("<div id=\"loginDialog\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res21']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/login",
		"type" : VIEW_TYPE.login,
		"action" : ACTION.view,
		"width" : 604,
		"height" : 354
	});
}

function popupNotice() { 
	var noticeId = "${sysNotice.id}";
	if(noticeId){
		var existId = $.cookie("noticeId");
		if( !existId || (existId && existId!=noticeId) ){
			$("<div id=\"popupNotice\"/>").showDialog({
				"title" : "${LANG['bizconf.jsp.index.res22']}",
				"dialogClass" : "ui-dialog-user",
				"url" : "/user/notice/popUpNotice/"+noticeId,
				"type" : VIEW_TYPE.notice,
				"action" : ACTION.view,
				"width" : 677,
				"height" : 564
			});
		}		
	}
}

/*
 * ${LANG['bizconf.jsp.index.res23']}
 */
function resetPass(){
	$("<div id=\"resetPass\"/>").showDialog({
		"title" : "${LANG['bizconf.jsp.index.res24']}",
		"dialogClass" : "ui-dialog-user",
		"url" : "/user/resetPass",
		"type" : VIEW_TYPE.notice,
		"action" : ACTION.view,
		"width" : 474,
		"height" : 285
	});
}

function getMainDomain(){
	var domain=document.domain;
	if(domain!=null && domain.length>0){
		var firstDotIndex=domain.indexOf(".");
		domain=domain.substr(firstDotIndex+1);
	}
	return domain;
}

function getDomain(){
	return document.domain;
}
function setNoticeCookie(noticeId) {
	$.cookie("noticeId", noticeId, { expires: 365 ,domain:getDomain()});
}
function showRemindBar() {
	if(!$.browser.mozilla && !$.browser.msie) { 
		var remindBar = $.cookie("remindBar");
		if(!remindBar){
			$("#remindBar").show();
		}
	}
	
}
function closeRemindBar() {
	$.cookie("remindBar", true, { expires: 365 ,domain:getDomain()});
	$("#remindBar").hide();
}
function jumpToFavor() {
	var loginUser = "${user.id}";
	if(loginUser) {
		jumpToFavorLi("/user/favor/getTimeZone");
	} else {
		login();
	}
}
function getDateScopCode(date) {
	var serverDate = new Date("${serverDate}");
	var dateScopFlag = 0;
	var startTimeStamp = date.getTime();
	var dayEndTimeStamp = getDayEnd(serverDate).getTime();
	var weekEndTimeStamp = getWeekEnd(serverDate).getTime();
	var monthEndTimeStamp = getMonthEnd(serverDate).getTime();
	if(startTimeStamp<dayEndTimeStamp){
		dateScopFlag = 1;
	} else if(startTimeStamp<weekEndTimeStamp){
		dateScopFlag = 2;
	}else if(startTimeStamp<monthEndTimeStamp){
		dateScopFlag = 3;
	}else {
		dateScopFlag = 4;
	}
	return dateScopFlag;
}  
function getMonthDays(nowYear, nowMonth){  
	var monthStartDate = new Date(nowYear, nowMonth, 1);  
	var monthEndDate = new Date(nowYear, nowMonth + 1, 1);  
	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);  
	return days;  
} 
function getDayEnd(date) {
	var tempDate = new Date();
	tempDate.setFullYear(date.getFullYear());
	tempDate.setMonth(date.getMonth());
	tempDate.setDate(date.getDate()+1);	
	tempDate.setHours(0);
	tempDate.setMinutes(0) ;
	tempDate.setSeconds(0) ;
	return tempDate;
}
function getWeekEnd(date) {
	var tempDate = new Date();
	tempDate.setFullYear(date.getFullYear());
	tempDate.setMonth(date.getMonth());
	tempDate.setDate(date.getDate()+(6-date.getDay())+1);	
	tempDate.setHours(0);
	tempDate.setMinutes(0) ;
	tempDate.setSeconds(0) ;
	return tempDate; 	
}
function getMonthEnd(date) {
	var monthDays = getMonthDays(date.getFullYear(), date.getMonth());
	var tempDate = new Date();
	tempDate.setFullYear(date.getFullYear());
	tempDate.setMonth(date.getMonth());
	tempDate.setDate(monthDays+1);	
	tempDate.setHours(0);
	tempDate.setMinutes(0) ;
	tempDate.setSeconds(0) ;	  
	return tempDate; 	
}
</script>
</head>

<body>
<c:if test="${empty user }">
	<div id="remindBar" style="display: none;">
		<table width="100%" height="40" bgcolor="#FFFFCC" align="center" style="">
			<tbody>
				<tr>
					<td align="center" style="font-size: 16px;">
						${LANG['bizconf.jsp.index.res2500']}&nbsp;&nbsp;&nbsp;
						<a class="remind_link" type="button" href="javascript:closeRemindBar();">&nbsp;&nbsp;${LANG['bizconf.jsp.index.res28']}&nbsp;&nbsp;</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</c:if>
<cc:confList var="HOST_JOIN_TYPE_EMAIL"/>
<!--${LANG['bizconf.jsp.conf_invite_recv.res4']}-->
<jsp:include page="header.jsp" />

<div id="head_bar">
  <div class="nav_profile" >
  	<c:if test="${!empty user}">
  		<img style="float: left; margin-right: 10px;margin-top: 14px;_margin-top: 0px;" class="png" src="${baseUrlStatic}/images/header_bar_01.png" width="14" height="16" align="absmiddle" />
  		<span class="nav_name" title="${user.trueName }">${LANG['website.message.welcome']}${user.trueName }&nbsp;&nbsp;</span>
  		<a style="display:inline-block;text-decoration: underline;" href="javascript:void(0);" onclick="logout();return false;">${LANG['website.message.logout']}</a>
  	</c:if>
  	<c:if test="${empty user }">
  		<img class="png" src="${baseUrlStatic}/images/header_bar_01.png" width="14" height="16" align="absmiddle" />
  		<a href="javascript:login();">${LANG['website.message.login']}</a>
  	</c:if> 
  </div>
  <div class="subnav">
  <ul>
    <c:if test="${empty user}">
		<li class="sub01"><img src="${baseUrlStatic}/images/subnav01.png" width="20" height="20" onclick="createImmediatelyConf()"/><a class="subnav_01" href="javascript:createImmediatelyConf();">${LANG['user.menu.conf.immediate']}</a></li>
    	<li class="sub02"><img src="${baseUrlStatic}/images/subnav02.png" width="20" height="19" onclick="createReservationConf()"/><a class="subnav_02" href="javascript:createReservationConf();">${LANG['user.menu.conf.reservation']}</a></li>
    </c:if>
    <c:if test="${!empty user && user.userRole==1 }">
		<li class="sub01"><img src="${baseUrlStatic}/images/subnav01.png" width="20" height="20" onclick="createImmediatelyConf()"/><a class="subnav_01" href="javascript:createImmediatelyConf();">${LANG['user.menu.conf.immediate']}</a></li>    
    	<li class="sub02"><img src="${baseUrlStatic}/images/subnav02.png" width="20" height="19" onclick="createReservationConf()"/><a class="subnav_02" href="javascript:createReservationConf();">${LANG['user.menu.conf.reservation']}</a></li>
    </c:if>
    <li class="sub03"><img src="${baseUrlStatic}/images/subnav03.png" width="17" height="20" onclick="joinMeeting(2)"/><a class="subnav_03" href="javascript:joinMeeting(2);">${LANG['user.menu.conf.join.confid']}</a></li>
  </ul>  
  </div>
  <ul class="nav_right">
    <li class="time_bj" align="absmiddle" onclick="jumpToFavor();">
    	<c:set var="timeZoneDesc" value="website.timezone.city.${siteBase.timeZoneId}"/>
    	<c:set var="fullTimeZoneDesc" value="website.timezone.city.zone.${siteBase.timeZoneId}"/>
    	<a style="font-weight: bold;" title="${LANG[fullTimeZoneDesc]}${LANG['website.message.time']}" href="javascript:;">
    		${LANG[timeZoneDesc]}${LANG['website.message.time']}</a>
    </li>
    <li class="language_box">
      <form name="langForm" id="langForm">
        <select name="jumpMenu_language" id="jumpMenu_language" style="padding: 2px;width: 70px;">
          <option value="zh-cn">中文</option>
          <option value="en-us">English</option>
        </select>
      </form>
    </li>
  </ul>  
</div>
<!--${LANG['bizconf.jsp.index.res30']}-->
<div class="main_left">
<ul class="nav-menu">
  <c:if test="${!isLogined}">
	  <li class="active">
	      <a href="/user/conf/getPublicControlPadIndex" class="ico0" >
	          <span class="icon0-img"></span>
	          <span class="icon0-span">${LANG['user.menu.conf.public']}</span>
	          <span class="icon-arrow"></span>
	      </a>
	  </li>
  </c:if>
  <c:if test="${isLogined}">
  <li>
      <a href="javascript:;" class="isParent ico1 nav-ul-on" >
          <span class="icon1-img"></span>
          <span class="icon1-span">${LANG['user.menu.conf.myconf']}</span>
          <span class="icon-arrow"></span>
      </a>
      <ul>
	      <c:if test="${isConfHost}">
	          <li class="active">
	              <a href="/user/conf/getControlPadIndex?userRole=1" class="ico11">
	                  <span class="icon11-img"></span>
	                  <span class="icon11-span">${LANG['user.menu.conf.myhost']}</span>
	                  <span></span>
	              </a>
	          </li>
	      </c:if>
          	<li>
              <a href="/user/conf/getControlPadIndex?userRole=2" class="ico12">    
                  <span class="icon12-img"></span>
                  <span class="icon12-span">${LANG['user.menu.conf.myact']}</span>
                  <span></span>
              </a>
          </li>
      </ul>
  </li>
  </c:if>
  <li>
      <a href="/user/contact/goContacts" class="ico2">
          <span class="icon2-img"></span>
          <span class="icon2-span">${LANG['siteAdmin.eventlog.type.4200']}</span>
          <span></span>
      </a>
  </li>
  <li>
      <a href="/user/notice/list" class="ico3">
          <span class="icon3-img"></span>
          <span class="icon3-span">${LANG['system.menu.notice.list']}</span>
          <span></span>
      </a>
  </li>
  <c:if test="${isConfHost and siteBase.siteFlag eq 1}">
   <li>
    	<a href="<%=request.getContextPath()%>/common/billing/userBillList" class="ico9">
    	<span class="icon9-img"></span>
    	<span class="icon9-span">${LANG['bizconf.jsp.index.res31']}</span>
    	</a>
  </li>
</c:if>
  <c:if test="${isLogined}"> 
  <li>
      <a href="javascript" class="isParent ico4 nav-ul-off">
          <span class="icon4-img"></span>
          <span class="icon4-span">${LANG['bizconf.jsp.hostConfloglist.res2']}</span>
          <span class="icon-arrow"></span> 
      </a>
      <ul style="display:none;">
	      <c:if test="${isConfHost}">
	          <li>
	              <a href="/user/conflog/list?isCreator=true" class="ico41">
	                  <span class="icon41-img"></span>
	                  <span class="icon41-span">${LANG['bizconf.jsp.index.res32']}</span>
	                  <span></span>
	              </a>
	          </li>
	      </c:if>
          <li>
              <a href="/user/conflog/list?isCreator=false" class="ico42">
                  <span class="icon42-img"></span>
                  <span class="icon42-span">${LANG['bizconf.jsp.index.res33']}</span>
                  <span></span> 
              </a>
          </li>
      </ul>
  </li>
  </c:if>
  <c:if test="${isConfHost}">
	  <li>
	      <a href="/user/confConfig/getConfConfig" class="ico5">
	          <span class="icon5-img"></span>
	          <span class="icon5-span">${LANG['user.menu.conf.default.setup']}</span>
	          <span></span>
	      </a>
	  </li>
  </c:if>
  <c:if test="${isLogined}">
	  <li>
	      <a href="/user/favor/getTimeZone" class="ico6">
	          <span class="icon6-img"></span>
	          <span class="icon6-span">${LANG['user.menu.conf.favor.setup']}</span>
	          <span></span>
	      </a>
	  </li>
	  <li>
	      <a href="/user/profile" class="ico7">
	          <span class="icon7-img"></span>
	          <span class="icon7-span">${LANG['user.menu.conf.person.setup']}</span>
	          <span></span>
	      </a>
	  </li>
  </c:if>
  <li>
      <a href="javascript:;" class="isParent ico8 nav-ul-off">
          <span class="icon8-img"></span>
          <span class="icon8-span">${LANG['user.menu.conf.support']}</span>
          <span class="icon-arrow"></span>
      </a>
      <ul style="display:none;">
            <li>
                <a href="/help" target="_blank" class="ignore ico81">
                    <span class="icon81-img"></span>
                    <span class="icon81-span">${LANG['bizconf.jsp.help.res1']}</span>
                    <span></span>
                </a>
            </li>
            <li>
                <a href="/downCenter/downClient" class="ico82">    
                    <span class="icon82-img"></span>
                    <span class="icon82-span">${LANG['user.menu.conf.down.center']}</span>
                    <span></span>
                </a>
            </li>
        </ul>
  </li>
</ul>
</div>
<!--${LANG['bizconf.jsp.index.res34']}-->
<div class="main_right" style="margin-left: 190px;">
<c:if test="${isLogined}"> 
  <c:if test="${isConfHost}"> 
  	<iframe frameborder="0" width="100%" height="600" id="mainFrame" name="mainFrame" scrolling="no" src="/user/conf/getControlPadIndex?userRole=1#"></iframe>
  </c:if>
  <c:if test="${!isConfHost}"> 
  	<iframe frameborder="0" width="100%" height="600" id="mainFrame" name="mainFrame" scrolling="no" src="/user/conf/getControlPadIndex?userRole=2#"></iframe>
  </c:if>
</c:if>

<c:if test="${!isLogined}">
   <iframe frameborder="0" width="100%" height="600" id="mainFrame" name="mainFrame" scrolling="no" src="/user/conf/getPublicControlPadIndex"></iframe>
</c:if>
</div>
<!--${LANG['bizconf.jsp.conf_invite_recv.res15']}-->
<jsp:include page="footer.jsp" />


  <div style="display:none" id="reloadDiv">
  <form name="reLoadJoinConf" id="reLoadJoinConf" action="/join?" method="post" target="dialogFrame">
  	<input type="hidden" name="cId" id="cId" value=""/>
  	<input type="hidden" name="rId" id="rId" value=""/>
  	<input type="hidden" name="cPass" id="cPass" value=""/>
  	<input type="hidden" name="joinType" id="joinType" value=""/>
  	<input type="hidden" name="userName" id="userName" value=""/>
  	<input type="hidden" name="reload" id="reload" value=""/>
  	<input type="hidden" name="code" id="code" value=""/>
  		<!-- setCookie("reload","1,${cId},${userName},${joinType},${code},${cPass}",domain); -->
  </form>
  </div>
  <div style="display: none;" id="b_down_s"></div>
  <div class="floatContactMe" style="position: absolute;top:200px; left: 0px;width: 170px;height: 280px;display: none;">
  	
  </div>
</body>
</html>


<script type="text/javascript">

function joinMtgFromEmail(){

	<c:if test="${joinFlag==true && joinType==HOST_JOIN_TYPE_EMAIL}">

	var joinUrl="${joinUrl}";
	joinMeetingFromEmail(joinUrl);
	</c:if>
	
}
</script>
<script type="text/javascript">
	function changeLang(langVal){
		var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/";
		window.location.href=jumpUrl;
	}
	function initPage(){
		var useLang="${lang}";
		$("#jumpMenu_language").val(useLang); 
		
	}
	initPage();
</script>
