<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>企业用户管理平台</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<style>
.tab_title {
	clear: left;
	background: #EAF4FC;
	height: 34px;
	border: 1px solid #D2D8DB;
	margin-top: 10px;
}
.tab_content {
	display: none;
}
.extras-container {
	display: block;
}
.extras-action {
	display: none;
	height: 50px;
	border-bottom: 1px solid #D2D8DB;
	border-left: 1px solid #D2D8DB;
	border-right: 1px solid #D2D8DB;
	color: #666666;
}

.extras-tr {
	clear: both;
	border-left: 1px solid #D2D8DB;
	border-bottom: 1px solid #D2D8DB;
	border-right: 1px solid #D2D8DB;
	height: 50px;
	position: relative;
}

.extras-default {
	border-left: 1px solid #FFFFFF;
	border-right: 1px solid #FFFFFF;
	cursor: pointer;
	height: 50px;
	width: 46px;
	position: relative;
}

.extras-default:hover {
	border-left: 1px solid #D3D6D6;
	border-right: 1px solid #D3D6D6;
}

.extras-default img {
	margin: 15px 14px;
	outline: none;
}

.extras-hover {
	border-left: 1px solid #D3D6D6;
	border-right: 1px solid #D3D6D6;
}

.extras-actived {
	border-bottom: 1px solid #FFFFFF;
	border-left: 1px solid #D3D6D6;
	border-right: 1px solid #D3D6D6;
	z-index: 1002;
}
.action_part {
	width: 630px;
/* 	float: right; */
	height: 50px;
	_height: 49px;
	background: #FFFFFF;
	position: absolute;
	right: 0px;
}
.panel_icon_on {
	background-image:url("/static/images/drop-down.png");
	display: block;
	height: 16px;
	width: 16px;
	cursor: pointer;
	float: left;
	margin: 8px;
}

.panel_icon_off {
	background-image:url("/static/images/drop-up.png");
	display: block;
	height: 16px;
	width: 16px;
	cursor: pointer;
	float: left;
	margin: 8px;
}

.open-state .fader {
	z-index: 1 !important;
}

.extras-tr .fader {
	background: url("/static/images/bg-fader.png") repeat scroll 0 0 transparent;
	_background: transparent;
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader (src='/static/images/bg-fader.png', sizingMethod='scale');
	display: none;
	height: 50px;
	line-height:50px;
	left: 0;
	position: absolute;
	top: 0;
	width: 100%;
	z-index: 1001;
}

	.bgview{
			height: 78px;
    		left: 0;
    		position: absolute;
    		top: 0;
    		width: 155px;
			background-image: url("/static/images/u223_normal.png");
			background-repeat: no-repeat;
	}
		.mycontarin{
			height: 78px;
    		left: 533px;
    		overflow: hidden;
   			position: absolute;
    		top: 323px;
    		width: 155px;
    		z-index: 1011;
		}
		
		.mycontarin1{
			height: 178px;
    		left: 533px;
    		overflow: hidden;
   			position: absolute;
    		top: 323px;
    		width: 155px;
    		
		}
		.mySpan{
			font-family:宋体;
			font-size:12px;
			font-weight:normal;
			font-style:normal;
			text-decoration:none;
			color:#FFFFFF;
		}
</style>
<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<script type="text/javascript">

function initJoinConf(){
	parent.joinMeetingReload();
}
initJoinConf();
/*
 * 查看会议详情
 */
function viewConf(id){
	parent.viewConf(id);
}

function updateBookMeeting(id) {
	parent.createReservationConf(id);
}
function updateAllBookMeeting(id) {
	parent.updateReservationConf(id);
}
//删除单个周期会议
function delSignleConf(id) {
	parent.delACycleConf(id);
}
function editInventContact(id) {
	parent.editInventContact(id);
}
/*
 * 重新创建会议
 */
function reCreateReservationConf(id) {
	parent.reCreateReservationConf(id);
}
/*
 * 修改周期会议中所有会议的信息
 * updateCycleBookMeetingInfo()
 */
function updateCycleBookMeetingInfo(id) {
	parent.updateCycleMeetingInfo(id);
}

function sendNoticeEmail(id) {
	var userId = "${user.id}";
	if(userId){
		var data = {};
		data.confId = id;
		app.sendNoticeEmail(data, function(result) {
			if(result){
				if(result.status && result.status==1){
					parent.successDialog(result.message);
				} else {
					parent.errorDialog(result.message);
				}
			}
		},{message:"正在添加日历提醒...", ui:parent});		
	} else {
		parent.addCalendar(id);
	}
}
function toggleExtra(elem) {
	$(".extras-action").slideUp("fast");
	$(".extras-default").removeClass("extras-actived");	
	var extrasTR = $(elem).closest(".extras-tr").next();
	var display = extrasTR.is(":visible"); 
	if(display==true){
		$(".fader").hide();
		extrasTR.slideUp("fast");
		$(elem).removeClass("extras-actived");	
	} else {
		$(".fader").show();
		extrasTR.slideDown("fast");
		$(elem).addClass("extras-actived");
	}
}

function toggleIcon(elem) {
	var icon = $(elem);
	if(!$(elem).hasClass("panel_icon")){
		icon = $(elem).prev();
	}
	var selector = $(elem).closest(".tab_title").next();
	togglePanel(selector, icon);	
}
function faderClick(elem) {
	$(".extras-action").slideUp();
	$(".extras-default").removeClass("extras-actived");
	$(".fader").hide();	
}

function showInfoView(info,id,event){
	var myEvent = event || window.event;
	var targetObj = myEvent.srcElement || myEvent.target;
	var offset = $(targetObj).offset();
	
	$("#"+id).find(".mySpan").html("主持人："+info);
	//$("#"+id).css("left", myEvent.clientX-120 + "px").css("top",myEvent.clientY-60 + "px");
	$("#"+id).css("z-index", "99").css("left", offset.left-60 + "px").css("top",offset.top-60 + "px");
	$("#"+id).css("visibility", "");
}
function hidenInfoView(id){
	$("#"+id).css("visibility", "hidden");
	showflag = false;
}
var showflag = true;
var confInfos = new Object();
function showInfos(confId,id,event){
	var myEvent = event || window.event;
	showflag = true;
	if(confInfos[confId]){
		var time = new Date().getTime()-confInfos[confId].time;
		if(time>30000){
			refreshData(confId,id,myEvent);
		}else{
			var data = confInfos[confId].datas;
			showDivOp(id,data,myEvent);
		}
	}else{
		refreshData(confId,id,myEvent);
	}
}

function showDivOp(id,data,myEvent){
	var targetObj = myEvent.srcElement || myEvent.target;
	var offset = $(targetObj).offset();
	$("#"+id).find(".bgview").empty();
	$("#"+id).find(".bgview").append("<div  style=\"padding-top: 6px; width:100%;\"></div>");
	if(data.length==0){
		$("#"+id).find(".bgview").append("<div><span class=\"mySpan\" style=\"line-height: 25px;\">"+'尚无参会者'+"</span></div>");
	}
	for(var i=0;i<data.length;i++){
		$("#"+id).find(".bgview").append("<div><span class=\"mySpan\" style=\"line-height: 25px;\">"+data[i].name+"</span></div>");				
	}
	if(showflag){
		$("#"+id).css("z-index", "999").css("visibility", "").css("left", offset.left+40 + "px").css("top",offset.top-90 + "px");
	}else{
		$("#"+id).css("visibility", "hidden");
	}
}

function refreshData(confId,id,myEvent){
	$.ajax({
      	type: "POST",
      	url:"/user/conf/getConfInviteUser",
      	data:{confId:confId},
      	dataType:"json",
      	success:function(data){
			if(data){
				var conf = new Object();
				conf["time"] = new Date().getTime();
				conf["datas"] = data;
				confInfos[confId] = conf;
				showDivOp(id,data,myEvent);
			}else{
				alert('Server inner error!');
			}
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
        }
	});  
}

function joinMeeting(joinType,cId){//cId,cPass,code){
	//alert("hi!");
	//var url = "/join/joinpage?joinType=&cId="+cId;
	parent.joinMeeting(joinType,cId);
//	var features = "height=670, width=890, top=200, left=150,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=yes";
//	window.open(url,"joinMeeting",features);	
} 



/*
function enterMeeting(cid,joinType){
	var url = "/join/joinpage?cId="+cid+"&joinType="+joinType;
	var features = "height=670, width=890, top=200, left=150,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=yes";
	window.open(url,"enterMeeting",features);	
}
*/
function delConf(confId){
	parent.confirmDialog("确认取消会议？",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/delete/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.errorDialog('取消会议出现异常！');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});	
}
/*
 * 所有企业用户都可以隐藏已错过的会议
 */
function hideConf(confId){
	parent.confirmDialog("确认隐藏会议？",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/hide/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.errorDialog('隐藏会议出现异常！');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});	
}

function delAllConf(cycId,confId){
	parent.confirmDialog("确认取消整个周期会议？",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/deleteCycleConfInfo/"+cycId+"?confId="+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.errorDialog('取消周期会议失败！');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});
}

function inventContact(confId){
	parent.inventContact(confId);
// 	//alert("邀请");	inviteFirst.jsp
// 	var url = "/user/contact/goInviteContacts";
// 	var features = "height=670, width=990, top=100, left=150,toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=yes, status=yes";
// 	window.open(url,"inviteMeeting",features);	
}
function refreshList() {
	parent.showURL("/user/conf/listConf");
}
function refreshTips() {
	switchDuration();
	switchTime();
	$('.timeago').tipsy({ trigger: 'hover', fade: false, gravity: 'sw', opacity: 0.65 });
	$('.attendee').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 'w', opacity: 0.65 });
	$('.viewtip').tipsy({ trigger: 'hover', fade: false, gravity: 's', opacity: 0.65 });
}

var $l = {
	seconds: "不到 1 分钟",
	minute: "大约 1 分钟",
	minutes: "%d 分钟",
	hour: "大约 1 小时",
	hours: "大约 %d 小时",
	day: "1 天",
	days: "%d 天",
	month: "大约 1 个月",
	months: "%d 月",
	year: "大约 1 年",
	years: "%d 年"
};
function substitute(string, number) {
    return string.replace(/%d/i, number);
}
function getwords(distanceMillis) {
    var seconds = Math.abs(distanceMillis) / 1000;
    var minutes = seconds / 60;
    var hours = minutes / 60;
    var days = hours / 24;
    var years = days / 365;
    var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) ||
        seconds < 90 && substitute($l.minute, 1) ||
        minutes < 45 && substitute($l.minutes, Math.round(minutes)) ||
        minutes < 90 && substitute($l.hour, 1) ||
        hours < 24 && substitute($l.hours, Math.round(hours)) ||
        hours < 42 && substitute($l.day, 1) ||
        days < 30 && substitute($l.days, Math.round(days)) ||
        days < 45 && substitute($l.month, 1) ||
        days < 365 && substitute($l.months, Math.round(days / 30)) ||
        years < 1.5 && substitute($l.year, 1) ||
        substitute($l.years, Math.round(years));
        return words;
}
function switchDuration() {
	$(".durationTime").each(function(index, elem){
		var status = $(elem).attr("status");
		var duration = $(elem).attr("duration");
			duration = parseInt(duration, 10);
		var duraHour = duration/60>>0;
		var duraMis = duration%60;
		var duraText = "";
		if(duraHour>0){
			duraText += duraHour+" 小时";
		}
		if(duraMis>0){
			duraText += duraMis+" 分钟";
		} 
		if(!duraText){
			duraText = "0 分钟";
		}
		$(elem).find(".durationMin").text(duraText);
// 		if(status && status=="run"){
// 			var dateEndStr = $(elem).attr("endtime").replace(/-/g,"/");
// 			var endDate = new Date(dateEndStr);
// 			var currentDate = new Date("${serverDate}");
// 		    var distanceMillis = endDate.getTime()-currentDate.getTime();
// 		    var words = getwords(distanceMillis);
		    //过去的会议
// 		    var prefix = "";
// 		    var suffix = "后会议结束";  
// 		    var title = prefix+words+suffix;
// 		    var title = prefix+duraText+suffix;
// 		    $(elem).attr("title", title);			
// 		} else {
		    var prefix = "会议时长:";
		    var suffix = "";
		    var title = prefix+duraText+suffix;
			$(elem).attr("title", title);
// 		}
	});
}
function switchTime() {
	$(".timeago").each(function(index, elem) {
		var dateStartStr = $(elem).attr("starttime").replace(/-/g,"/");
		var dateEndStr = $(elem).attr("endtime").replace(/-/g,"/");
		var status = $(elem).attr("status");

		var currentDate = new Date("${serverDate}");
		var startDate = new Date(dateStartStr);
		var endDate = new Date(dateEndStr);
		switchWords(currentDate, startDate, endDate, elem);
		switchTitle(currentDate, startDate, endDate, elem, status);
	});
}

function switchWords(currentDate, startDate, endDate, elem) {
	var currentYear = currentDate.getFullYear();
	var startYear = startDate.getFullYear();
	if(currentYear==startYear){	
		var currentDay = currentDate.getDate();
		var startDay = startDate.getDate();
		if((startDay-currentDay)==0){
			$(elem).find(".date").text("今天");
		} else if((startDay-currentDay)==1) {
			$(elem).find(".date").text("明天");
		} else if((startDay-currentDay)==-1) {
			$(elem).find(".date").text("昨天");
		} else {
			$(elem).find(".date").text(formatDate(startDate));
		}
	} else {
		$(elem).find(".date").text(formatDate(startDate));
	}
}
function switchTitle(currentDate, startDate, endDate, elem, status) {
    //过去的会议
    var prefix = "";
    var suffix = "前加入的会议";    
    if(status=="come"){
    //即将开始的会议   
    	prefix = "";
        suffix = "后开始会议";
    } else if(status=="run"){
    //正在进行的会议
    	prefix = "会议已开始";
        suffix = "";    
    } else if(status=="miss"){
    //错过的会议
    	prefix = "";
        suffix = "前错过的会议";    
    }
    var distanceMillis = currentDate.getTime()-startDate.getTime();
    var words = getwords(distanceMillis);
    var title = prefix+words+suffix;
    $(elem).attr("title", title);
}

function quickSearch(elem) {
	var searchValue = $(elem).val();
	var listPanel = $(elem).closest(".tab_title").next();
	var noResult = listPanel.find(".empty_result"); 
	var container = listPanel.find(".extras-container");
	if(searchValue) {
		var findList = $(container).hide().filter(":contains('" + searchValue + "')");
		if(findList.length>0){
			noResult.hide();
			findList.show();
		} else {
			noResult.show();
		}
	} else {
		noResult.hide();
		$(container).show();
	}
}

jQuery(function($) {
	refreshTips();
	$(".m_search").watermark('会议主题、参会人');
});


//查询会议参会人
$(document).ready(function(){
	getParticipantInfo();
});

function getParticipantInfo(){
	$("div[name=attendeer]").each(function(){
		var confId = $(this).attr("confId");
		var self = $(this);
	    var html = "";
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/getConfInviteUser",
	      	data:{confId:confId},
	      	dataType:"json",
	      	success:function(data){
				html ="尚无参会人";
				if(data){
					var renshu = "<img src=\"${baseUrlStatic}/images/renshu.png\" width=\"20\" height=\"20\" align=\"absmiddle\" />";
					if(data.length>0){
						html = "";
					}
					renshu += data.length;
					$(self).empty().html(renshu);
					for(var i=0;i<data.length&&i<5;i++){
						if(data[i]){
							html +=data[i].name+"<br/>";
						}
					}
				}else{
					alert('Server inner error!');
				}
				$(self).attr("title",html);
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	//alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	       		html = "查询参会人失败！";
	       		$(self).attr("title",html);
	        }
		});  
	});
}
window.setInterval(refreshTips, 60000);
window.setInterval(refreshList, 5*60*1000);
</script>
</head>
<body>
<DIV id="showHostInfo" class="mycontarin" style="visibility:hidden;">
			<div class="bgview" align="center">
				<div style="padding-top: 26px;"><p><span class="mySpan"></span></p></div>
			</div>
</DIV>
<DIV id="showMenberList" class="mycontarin1" style="visibility: hidden;">
		<div class="bgview" style="background-image: url('/static/images/u227_normal.png'); height: 178px;" align="center">
			 
		</div>
</DIV>
<c:if test="${fn:length(missConfList)>0 || fn:length(dringConfList)>0 || fn:length(upcomingConfList)>0 || fn:length(attendedConfList)>0}">
<div class="list_top">
  	<img src="/static/images/rili_title.png" width="20" height="20" align="absmiddle" />
  	<h3>一周的会议</h3>
</div>
</c:if>
<div class="main_content" id="main_container">
<c:if test="${fn:length(missConfList)<=0 && fn:length(dringConfList)<=0 && fn:length(upcomingConfList)<=0 && fn:length(attendedConfList)<=0}">
<div id="no_data" class="no_meeting">
  	<div class="no_meeting_box">
    	<p>您好，您当前没有任何会议信息</p>
    	<c:if test="${empty user}">
    		<a href="javascript:;" onclick="parent.createReservationConf()">预约会议</a>
    	</c:if>
    	<c:if test="${!empty user && user.userRole==1 }">
    		<a href="javascript:;" onclick="parent.createReservationConf()">预约会议</a>
    	</c:if>
    </div>
</div>
</c:if>
<fmt:setLocale value="en-us"/>

 <!--正在进行的的会议-->
<c:if test="${fn:length(dringConfList)>0 }"> 
<div class="tab_title">
	<div align="left" style="float: left;width:45%">
      	<span class="panel_icon panel_icon_off" onclick="toggleIcon(this)">&nbsp;</span>
      	<span class="m_title" onclick="toggleIcon(this)">
      	<c:choose>
      		<c:when test="${user != null}">
      			进行中的会议
      		</c:when>
      		<c:otherwise>
      			进行中的会议
      		</c:otherwise>
      	</c:choose>
      	</span>
  		<div class="number_taxt">
       		<div class="no_left"></div>
            <div class="no_center"><span class="meeting-count">${dringConfRows}</span></div>
            <div class="no_right"></div>
       	</div>      		
	</div>
	<div align="right" style="float: right;width:50%">
      	<form action=""><input class="m_search" name="" type="text"  onblur="quickSearch(this)" onkeyup="quickSearch(this)"/></form>	
	</div>
</div>
<div id="running_list" class="tab_content" style="display: block;">
	<div class="empty_result" style="display: none;">抱歉，没有找到符合条件的会议</div>
	<c:forEach var="dringConf" items="${dringConfList}" varStatus="dringStatus">
	<div class="extras-container">
		<div class="extras-tr">
	      	<div class="date_part">
	      	  <fmt:formatDate var="runStartTime" value="${dringConf.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	      	  <fmt:formatDate var="runEndTime" value="${dringConf.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	          <div class="timeago date-holder" title="${runStartTime}" startTime="${runStartTime}" endTime="${runEndTime}" status="run"> 
	          	<span class="date">
	          		<fmt:formatDate value="${dringConf.startTime}" pattern="yyyy-MM-dd" />
	          	</span> 
	          	<span class="time">
	          		<fmt:formatDate value="${dringConf.startTime}" pattern="HH:mm"/></span> 
	          	</div>
	        </div>
	        <div class="text_part" align="left">
	        	<div class="date-holder_01"> 
	        		<span class="title">
	        		<a href="javascript:;" onclick="viewConf(${dringConf.id})">${dringConf.confName}
	        			<c:forEach var="confCyc" items="${cycs}">
			        			<c:if test="${confCyc.id eq dringConf.cycleId}">
			        				<c:choose>
			        					<c:when test="${confCyc.cycleType eq '1'}">
			        						<span style="color:#47ADE9;">(日会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(周会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(月会议)</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        		</c:forEach>
	        		</a></span> 
	        		<span class="content"><a href="javascript:;" onclick="viewConf(${dringConf.id})">${dringConf.confDesc}</a></span>
	        	</div>
	        </div>
			<div class="action_part">
				<table  border="0" align="right" cellpadding="0" cellspacing="0">
          			<tr>
          				<td><div class="k02 viewtip durationTime" title="会议时长:${dringConf.duration}分钟" duration="${dringConf.duration}" endTime="${runEndTime}" status="run"><img src="${baseUrlStatic}/images/clockbtn.png" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${dringConf.duration}分钟</div></div></td>
			            <td><div class="k02 k09 viewtip" title="主持人${dringConf.compereName}"><img src="${baseUrlStatic}/images/mac.png" width="18" height="21" align="absmiddle" /><div class="k02_span">${dringConf.compereName}</div></div></td>
			            <td><div name="attendeer" confId="${dringConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${dringConf.maxUser}</div></td>
			            <td><div class="k04 attendee" title="pc:${dringConf.pcNum}<br/>phoneCall:${dringConf.phoneNum}"><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${dringConf.pcNum+dringConf.phoneNum}</div></td>
			            <td><div class="extras-default viewtip"  title="查看会议选项" onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.png" width="20" height="20" /></a></div></td>
			            <td><div class="k06" onclick="javascript:joinMeeting(1,'${dringConf.id}');">
			            		<c:if test="${user.id != dringConf.compereUser}">
			            			<a href="javascript:;">加入会议</a>
			            		</c:if>
			            		<c:if test="${user.id eq dringConf.compereUser}">
			            			<a href="javascript:;">进入会议</a>
			            		</c:if>
			            </div></td>
          			</tr>
        		</table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action">
   			<div align="right" style="padding-top:10px">
<%--    				<c:if test="${user.id eq dringConf.createUser}"> --%>
<%-- 	   				<a class="email04" href="javascript:;" onclick="delConf(${dringConf.id});"><img src="${baseUrlStatic}/images/email02.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;" />删除</a> --%>
<%--    				</c:if> --%>
	   			<a class="email05" href="javascript:;" onclick="viewConf(${dringConf.id});"><img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />查看详情</a>
   			</div>
   		</div>		
	</div>	
	</c:forEach>
</div>   
</c:if>
 <!--即将开始的的会议-->
<c:if test="${fn:length(upcomingConfList)>0 }"> 
<div class="tab_title">
	<div align="left" style="float: left;width:45%">
      	<span class="panel_icon panel_icon_off" onclick="toggleIcon(this)">&nbsp;</span>
      	<span class="m_title" onclick="toggleIcon(this)">
	      	<c:choose>
	      		<c:when test="${user != null}">
			      	即将召开的会议  
	      		</c:when>
	      		<c:otherwise>
	      			即将召开的会议
	      		</c:otherwise>
	      	</c:choose>
      	</span>
  		<div class="number_taxt">
       		<div class="no_left"></div>
            <div class="no_center"><span class="meeting-count">${UpcomingConfRows}</span></div>
            <div class="no_right"></div>
       	</div>	
	</div>
	<div align="right" style="float: right;width:50%">
      	<form action=""><input class="m_search" name="" type="text"  onblur="quickSearch(this)" onkeyup="quickSearch(this)"/></form>	
	</div>
</div>
<div id="coming_list" class="tab_content" style="display: block;">
	<div class="empty_result" style="display: none;">抱歉，没有找到符合条件的会议</div>
  	<c:forEach var="upcomingConf" items="${upcomingConfList}" varStatus="upcomingStatus">
  	<div class="extras-container">
		<div class="extras-tr">
			<div class="date_part">
				<fmt:formatDate var="comingStartTime" value="${upcomingConf.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="comingEndTime" value="${upcomingConf.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	          <div class="timeago date-holder" title="${comingStartTime}" startTime="${comingStartTime}" endTime="${comingEndTime}" status="come"> 
		        <span class="date">
   		      		<fmt:formatDate value="${upcomingConf.startTime}" pattern="yyyy-MM-dd" />
	          	</span>
	          	<span class="time">
	          		<fmt:formatDate value="${upcomingConf.startTime}" pattern="HH:mm"/>
	          	</span> 
	          </div>
	        </div>
	        <div class="text_part" align="left">
	        	<div class="date-holder_01"> 
	        		<span class="title"><a href="javascript:;" onclick="viewConf(${upcomingConf.id})">${upcomingConf.confName}
	        			<c:forEach var="confCyc" items="${cycs}">
			        			<c:if test="${confCyc.id eq upcomingConf.cycleId}">
			        				<c:choose>
			        					<c:when test="${confCyc.cycleType eq '1'}">
			        						<span style="color:#47ADE9;">(日会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(周会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(月会议)</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        		</c:forEach>		
	        		</a></span> 
	        		<span class="content"><a href="javascript:;" onclick="viewConf(${upcomingConf.id})">${upcomingConf.confDesc}</a></span>
	        	</div>
	        </div>
			<div class="action_part">
				<table  border="0" align="right" cellpadding="0" cellspacing="0">
	          		<tr>
	          			<td class="" ><!-- 邀请（主持人专用） -->
	          				<c:if test="${user.id eq upcomingConf.compereUser}">
	          					<div class="k_f <c:if test="${upcomingConf.maxUser==0}">k_d</c:if>" onclick="inventContact(${upcomingConf.id})"><img src="/static/images/yaoqing.png" width="21" height="16" align="absmiddle" style=" padding-right:5px;" />邀请</div>
	          				</c:if>
	          			</td>
	          			<td><div class="k02 viewtip durationTime" title="会议时长:${upcomingConf.duration}分钟" duration="${upcomingConf.duration}"><img src="${baseUrlStatic}/images/clockbtn.png" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${upcomingConf.duration}分钟</div></div></td>
	            		<td><div class="k02 k09 viewtip" title="主持人${upcomingConf.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.png" width="20" height="20" align="absmiddle" /><div class="k02_span">${upcomingConf.compereName}</div></div></td>
	            		<td><div onclick="editInventContact('${upcomingConf.id}')" name="attendeer" confId="${upcomingConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${upcomingConf.maxUser}</div></td>
	            		<td><div title="查看会议选项" class="viewtip extras-default <c:if test="${upcomingStatus.first}">extras-actived</c:if>"  onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.png" width="20" height="20" /></a></div></td>
	            		<td>
		            		<div class="k06" onclick="javascript:joinMeeting(1,'${upcomingConf.id}');">
			            		<c:if test="${user.id != upcomingConf.compereUser}">
			            			<a href="javascript:;">加入会议</a>
			            		</c:if>
			            		<c:if test="${user.id eq upcomingConf.compereUser}">
			            			<a href="javascript:;">开始会议</a>
			            		</c:if>
		            		</div>
	            		</td>
	          		</tr>
        		</table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action" <c:if test="${upcomingStatus.first}">style="display:block"</c:if>>
				<div align="left" style="padding-top:10px">
					<a class="email01" href="javascript:sendNoticeEmail(${upcomingConf.id});"><img src="${baseUrlStatic}/images/email05.png" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>Outlook</a>
					<a class="email02" href="javascript:sendNoticeEmail(${upcomingConf.id});"> <img src="${baseUrlStatic}/images/ico002.png" width="16" height="11" align="absmiddle" style=" padding-right:5px;" />Gmail</a>
					<a class="email03" href="javascript:sendNoticeEmail(${upcomingConf.id});"><img src="${baseUrlStatic}/images/ico003.png" width="14" height="17" align="absmiddle" style=" padding-right:5px;" />Foxmail</a>
				</div>
				<div align="right">
					<c:if test="${user.id eq upcomingConf.createUser}">
						 <c:choose>
						 	<c:when test="${upcomingConf.cycleId!=0}">
								<a class="email04" href="javascript:;" onclick="delAllConf(${upcomingConf.cycleId},${upcomingConf.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>取消全部</a>
								<a class="email04" href="javascript:;" onclick="delSignleConf(${upcomingConf.cycleId});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>取消</a>
							 	<a class="email05" href="javascript:;" onclick="updateCycleBookMeetingInfo(${upcomingConf.id})">
									<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>修改全部</a>	
								<a class="email05" href="javascript:;" onclick="updateAllBookMeeting(${upcomingConf.cycleId})">
									<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>修改</a>
						 	</c:when>
						 	<c:otherwise>
						 		<a class="email04" href="javascript:;" onclick="delConf(${upcomingConf.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>取消</a>
						 		<a class="email05" href="javascript:;" onclick="updateBookMeeting(${upcomingConf.id})">
								<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>修改</a>	
						 	</c:otherwise>
						 </c:choose>
					 </c:if>
				</div>
		</div>	  	
  	</div>	
	</c:forEach>
</div> 
</c:if> 
  <!--参加过的会议-->
  <c:if test="${fn:length(attendedConfList)>0 }">
<div class="tab_title">
	<div align="left" style="float: left;width:45%">
      	<span class="panel_icon panel_icon_on" onclick="toggleIcon(this)">&nbsp;</span>
      	<span class="m_title" onclick="toggleIcon(this)">已参加的会议</span>
       	<div class="number_taxt">
       		<div class="no_left"></div>
            <div class="no_center"><span class="meeting-count">${AttendedConfRows}</span></div>
            <div class="no_right"></div>
       	</div>     		
	</div>
	<div align="right" style="float: right;width:50%">
      	<form action=""><input class="m_search" name="" type="text" onblur="quickSearch(this)"  onkeyup="quickSearch(this)" style="display: none;"/></form>	
	</div>
</div>
<div id="passing_list" class="tab_content">
	<div class="empty_result" style="display: none;">抱歉，没有找到符合条件的会议</div>
  <c:forEach var="attendedConf" items="${attendedConfList}" varStatus="attendedStatus">
  <div class="extras-container">	
		<div class="extras-tr">
			<div class="date_part">
				<fmt:formatDate var="passStartTime" value="${attendedConf.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="passEndTime" value="${attendedConf.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	            <div class="timeago date-holder" title="${passStartTime}" startTime="${passStartTime}" endTime="${passEndTime}" status="pass"> 
	            	<span class="date">
	            		<fmt:formatDate value="${attendedConf.startTime}" pattern="yyyy-MM-dd" />
	            	</span> 
	            	<span class="time">
	            		<fmt:formatDate value="${attendedConf.startTime}" pattern="HH:mm"/>
	            	</span> 
	            </div>
	        </div>
	        
	        <div class="text_part" align="left">
	        	<div class="date-holder_01"> 
		        	<span class="title">
		        	<a href="javascript:;" onclick="viewConf(${attendedConf.id})">
		        		${attendedConf.confName}
		        		<c:forEach var="confCyc" items="${cycs}">
		        			<c:if test="${confCyc.id eq attendedConf.cycleId}">
		        				<c:choose>
		        					<c:when test="${confCyc.cycleType eq '1'}">
		        						<span style="color:#47ADE9;">(日会议)<span>
		        					</c:when>
		        					<c:when test="${confCyc.cycleType eq '2'}">
		        						<span style="color:#47ADE9;">(周会议)</span>
		        					</c:when>
		        					<c:when test="${confCyc.cycleType eq '3'}">
		        						<span style="color:#47ADE9;">(月会议)</span>
		        					</c:when>
		        				</c:choose>
		        			</c:if>
		        		</c:forEach>
		        	</a>
		        	</span>
	        	 <span class="content"><a href="javascript:;" onclick="viewConf(${attendedConf.id})">${attendedConf.confDesc}</a></span></div>
	        </div>
			<div class="action_part">
			<table  border="0" align="right" cellpadding="0" cellspacing="0">
	          <tr>
	           	<td><div class="k02 viewtip durationTime" title="会议时长:${attendedConf.duration}分钟"  duration="${attendedConf.duration}"><img src="${baseUrlStatic}/images/clockbtn.png" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${attendedConf.duration}分钟</div></div></td>
	            <td><div class="k02 k09 viewtip" title="主持人${attendedConf.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.png" width="20" height="20" align="absmiddle" /><div class="k02_span">${attendedConf.compereName}</div></div></td>
	            <td><div  name="attendeer" confId="${attendedConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${attendedConf.maxUser}</div></td>
	            <td><div class="k04 attendee" title="pc:${attendedConf.pcNum}<br/>phoneCall:${attendedConf.phoneNum}"><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${dringConf.pcNum+dringConf.phoneNum}</div></td>
	            <td><div class="extras-default viewtip" title="查看会议选项"  onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.png" width="20" height="20" /></a></div></td>
	            <td>
	            	<div class="k06"><a href="javascript:;" onclick="reCreateReservationConf(${attendedConf.id})">重新创建</a></div>
	            </td>
	          </tr>
	        </table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action">
			<div align="right" style="padding-top:10px">
				
   				<a class="email04" href="javascript:;" onclick="delConf(${attendedConf.id});"><img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;" />删除</a>
   				<a class="email05" href="javascript:;" onclick="viewConf(${attendedConf.id});"><img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />查看详情</a>
   			</div>
		</div>	
</div>
	</c:forEach>
</div>   
  </c:if>
<!--错过的会议-->
<c:if test="${fn:length(missConfList)>0 }">
<div class="tab_title">
	<div align="left" style="float: left;width:45%">
      	<span class="panel_icon panel_icon_on" onclick="toggleIcon(this)">&nbsp;</span>
      	<span class="m_title" onclick="toggleIcon(this)">未参加的会议</span>
      	<div class="number_taxt">
       		<div class="no_left"></div>
            <div class="no_center"><span class="meeting-count">${MissConfRows}</span></div>
            <div class="no_right"></div>
       	</div>  	
	</div>
	<div align="right" style="float: right;width:50%">
      	<form action=""><input class="m_search" name="" type="text"  onblur="quickSearch(this)" onkeyup="quickSearch(this)" style="display: none;"/></form>	
	</div>
</div>

<div id="miss_list" class="tab_content">
	<div class="empty_result" style="display: none;">抱歉，没有找到符合条件的会议</div>
	<c:forEach var="missConf" items="${missConfList}" varStatus="missStatus">
		<div class="extras-container">
			<div class="extras-tr">
				<div class="date_part">
					<fmt:formatDate var="missStartTime" value="${missConf.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:formatDate var="missEndTime" value="${missConf.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
		        	<div class="timeago date-holder" title="${missStartTime}" startTime="${missStartTime}" endTime="${missEndTime}" status="miss"> 
		         		<span class="date"><fmt:formatDate value="${missConf.startTime}" pattern="yyyy-MM-dd" /></span> 
		         		<span class="time"><fmt:formatDate value="${missConf.startTime}" pattern="HH:mm"/></span> 
		        	</div>
		    	</div>
		    	<div align="center">
        	</div>
				<div class="text_part" align="left">
					<div class="date-holder_01">
						<span class="title">
						<a href="javascript:;" onclick="viewConf(${missConf.id})">
							${missConf.confName}
							<c:forEach var="confCyc" items="${cycs}">
			        			<c:if test="${confCyc.id eq missConf.cycleId}">
			        				<c:choose>
			        					<c:when test="${confCyc.cycleType eq '1'}">
			        						<span style="color:#47ADE9;">(日会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(周会议)</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(月会议)</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        			</c:forEach>
						</a>
						</span> 
						<span class="content"><a href="javascript:;" onclick="viewConf(${missConf.id})">${missConf.confDesc}</a></span></div>
				</div>
				<div style="float: right;">
					<table  border="0" align="right" cellpadding="0" cellspacing="0">
			         <tr>
			         	<td><div class="k02 viewtip durationTime" title="会议时长 :${missConf.duration}分钟"  duration="${missConf.duration}"><img src="${baseUrlStatic}/images/clockbtn.png" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${missConf.duration}分钟</div></div></td>
			           <td><div class="k02 k09 viewtip" title="主持人${missConf.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.png" width="20" height="20" align="absmiddle" /><div class="k02_span">${missConf.compereName}</div></div></td>
			           <td><div name="attendeer" confId="${missConf.id}"  class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${missConf.maxUser}</div></td>
			           <td><div class="extras-default viewtip" title="查看会议选项" onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.png" width="20" height="20" /></a></div></td>
			           <td>
				           <c:if test="${user.id eq missConf.createUser}">
				          	 <div class="k06"><a href="javascript:;" onclick="reCreateReservationConf(${missConf.id})">重新创建</a></div>
				           </c:if>
				           <c:if test="${user.id != missConf.createUser}">
				          	 <div class="k06" style="background: transparent;"></div>
				           </c:if>
			           </td>
			         </tr>
			       </table>
				</div>
				<span class="fader" onclick="faderClick(this)"></span>		
			</div>
	   		<div class="extras-action">
	   			<div align="right" style="padding-top:10px">
	   				<c:if test="${user.id eq missConf.createUser}">
	   					<a class="email04" href="javascript:;" onclick="delConf(${missConf.id});"><img src="${baseUrlStatic}/images/email02.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;" />删除</a>
	   				</c:if>
	   				<a class="email05" href="javascript:;" onclick="hideConf(${missConf.id});"><img src="${baseUrlStatic}/images/hide.png" width="14" height="15" align="absmiddle" style=" padding-right:5px;" />隐藏</a>
	   				<a class="email05" href="javascript:;" onclick="viewConf(${missConf.id});"><img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />查看详情</a>
	   			</div>
	   		</div>
		</div>		
	</c:forEach>
</div>
</c:if> 
  </div>
  <div style="height: 80px;width: 500px">&nbsp;</div>
  
</body>

</html>