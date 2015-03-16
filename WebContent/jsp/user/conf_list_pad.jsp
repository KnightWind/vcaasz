<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>${LANG['bizconf.jsp.conf_list_pad.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/conf_list.css?ver=${version}"/>

<c:if test="${!empty user}">
	<c:set var="pageSize" value="${user.pageSize}"></c:set>
</c:if>
<c:if test="${empty user}">
	<c:set var="pageSize" value="10"></c:set>
</c:if>
<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-1.9.2.custom.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/date.js?ver=${version}"></SCRIPT>
<script type="text/javascript">

function initJoinConf(){
	parent.parent.joinMeetingReload();
}
initJoinConf();
/*
 * ${LANG['bizconf.jsp.conf_list_index.res3']}
 */
function viewConf(id){
	parent.parent.viewConf(id);
}

function updateBookMeeting(id) {
	parent.parent.createReservationConf(id);
}
function updateAllBookMeeting(id) {
	parent.parent.updateReservationConf(id);
}
//${LANG['bizconf.jsp.conf_list_index.res4']}
function delSignleConf(id) {
	parent.parent.delACycleConf(id);
}
function editInventContact(id) {
	parent.parent.editInventContact(id);
}
/*
 * ${LANG['bizconf.jsp.attended_conf_list.res2']}
 */
function reCreateReservationConf(id) {
	parent.parent.reCreateReservationConf(id);
}
/*
 * ${LANG['bizconf.jsp.conf_list_index.res5']}
 * updateCycleBookMeetingInfo()
 */
function updateCycleBookMeetingInfo(id) {
	parent.parent.updateCycleMeetingInfo(id);
}

function sendNoticeEmail(id) {
	var userId = "${user.id}";
	if(userId){
		var data = {};
		data.confId = id;
		app.sendNoticeEmail(data, function(result) {
			if(result){
				if(result.status && result.status==1){
					parent.parent.successDialog(result.message);
				} else {
					parent.parent.errorDialog(result.message);
				}
			}
		},{message:"${LANG['bizconf.jsp.add_calendar_notice.res9']}...", ui:top});		
	} else {
		parent.parent.addCalendar(id);
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
	togglePanel(selector, icon, function(){
		parent.refreshIHeight();	
	});	
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
	
	$("#"+id).find(".mySpan").html("${LANG['bizconf.jsp.conf_list_index.res6']}"+info);
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
		$("#"+id).find(".bgview").append("<div><span class=\"mySpan\" style=\"line-height: 25px;\">"+'${LANG['bizconf.jsp.conf_list_index.res7']}'+"</span></div>");
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
	parent.parent.joinMeeting(joinType,cId);
} 

function delConf(confId){
	parent.parent.confirmDialog("${LANG['bizconf.jsp.attended_conf_list.res3']}",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/delete/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.parent.errorDialog('${LANG['bizconf.jsp.attended_conf_list.res4']}');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});	
}


function delConfForPerConf(confId){
	parent.parent.confirmDialog("${LANG['bizconf.jsp.conf_list_pad.res2']}",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/delete/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.parent.errorDialog('${LANG['bizconf.jsp.conf_list_pad.res3']}');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});	
}
/*
 * ${LANG['bizconf.jsp.conf_list_index.res8']}
 */
function hideConf(confId){
	parent.parent.confirmDialog("${LANG['bizconf.jsp.conf_list_index.res9']}",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/hide/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.parent.errorDialog('${LANG['bizconf.jsp.conf_list_index.res10']}');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});	
}

function delAllConf(cycId,confId){
	parent.parent.confirmDialog("${LANG['bizconf.jsp.conf_list_index.res11']}",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/deleteCycleConfInfo/"+cycId+"?confId="+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.parent.errorDialog('${LANG['bizconf.jsp.conf_list_index.res12']}');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		}); 
	});
}

function inventContact(confId){
	parent.parent.editInventContact(confId);
// 	//alert("${LANG['bizconf.jsp.conf_list_index.res13']}");	inviteFirst.jsp
// 	var url = "/user/contact/goInviteContacts";
// 	var features = "height=670, width=990, top=100, left=150,toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=yes, status=yes";
// 	window.open(url,"inviteMeeting",features);	
}

function refreshTips() {
	switchDuration();
	switchTime();
	$('.timeago').tipsy({ trigger: 'hover', fade: false, gravity: 'sw', opacity: 0.65 });
	$('.attendee').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 'w', opacity: 0.65 });
	$('.viewtip').tipsy({ trigger: 'hover', fade: false, gravity: 's', opacity: 0.65 });
	//$('.content').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 's', opacity: 0.65 });
}

var $l = {
	seconds: "${LANG['bizconf.jsp.conf_list_index.res14']}",
	minute: "${LANG['bizconf.jsp.conf_list_index.res15']}",
	minutes: "${LANG['bizconf.jsp.conf_list_index.res1500']}",
	hour: "${LANG['bizconf.jsp.conf_list_index.res1501']}",
	hours: "${LANG['bizconf.jsp.conf_list_index.res1502']}",
	day: "${LANG['bizconf.jsp.conf_list_index.res1700']}",
	days: "${LANG['bizconf.jsp.conf_list_index.res1701']}",
	month: "${LANG['bizconf.jsp.conf_list_index.res1503']}",
	months: "${LANG['bizconf.jsp.conf_list_index.res1900']}",
	year: "${LANG['bizconf.jsp.conf_list_index.res1504']}",
	years: "${LANG['bizconf.jsp.conf_list_index.res2000']}"
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
		var duraText = "";
		if(duration && duration=="${LANG['bizconf.jsp.conf_list_pad.res4']}"){
			var endTime = $(elem).attr("endTime");
			var date = "";
			var time = "";
			if(endTime){
				date = endTime.substring(0,10);
				time = endTime.substring(11);
			}
			duraText = "${LANG['bizconf.jsp.conf_list_pad.res4']}";
			$(elem).find(".durationMin").html(date+"\n"+time);
			 var prefix = "${LANG['bizconf.jsp.attendConfloglist.res7']}:";
			 var suffix = "";
			 var title = prefix+endTime+suffix;
			$(elem).attr("title", title);
		} else {
			duration = parseInt(duration, 10);
			var duraHour = duration/60>>0;
			var duraMis = duration%60;
			if(duraHour>0){
				if(duraHour==1){
					duraText += duraHour+" ${LANG['bizconf.jsp.conf_list_index.res16']}";	
				} else {
					duraText += duraHour+" ${LANG['bizconf.jsp.conf_list_index.res16000']}";					
				}
			}
			if(duraMis>0){
				if(duraMis==1) {
					duraText += duraMis+" ${LANG['bizconf.jsp.attended_conf_list.res10']}";		
				} else {
					duraText += duraMis+" ${LANG['bizconf.jsp.attended_conf_list.res10000']}";
				}
				
			}
			if(!duraText){
				duraText = "0 ${LANG['bizconf.jsp.attended_conf_list.res10']}";
			}
			$(elem).find(".durationMin").text(duraText);
		    var prefix = "${LANG['bizconf.jsp.conf_list_index.res23']}:";
		    var suffix = "";
		    var title = prefix+duraText+suffix;
			$(elem).attr("title", title);
		}
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
	var currentMonth = currentDate.getMonth();
	var startYear = startDate.getFullYear();
	var startMonth = startDate.getMonth();
	if(currentYear==startYear && currentMonth==startMonth){	
		var currentDay = currentDate.getDate();
		var startDay = startDate.getDate();
		if((startDay-currentDay)==0){
			$(elem).find(".date").text("${LANG['bizconf.jsp.conf_list.res3']}");
		} else if((startDay-currentDay)==1) {
			$(elem).find(".date").text("${LANG['bizconf.jsp.conf_list_index.res24']}");
		} else if((startDay-currentDay)==-1) {
			$(elem).find(".date").text("${LANG['bizconf.jsp.conf_list_index.res25']}");
		} else {
			$(elem).find(".date").text(formatDate(startDate));
		}
	} else {
		$(elem).find(".date").text(formatDate(startDate));
	}
}
function switchTitle(currentDate, startDate, endDate, elem, status) {
    //${LANG['bizconf.jsp.conf_list_index.res21']}
    var prefix = "${LANG['bizconf.jsp.conf_list_index.res26.before']}";
    var suffix = "${LANG['bizconf.jsp.conf_list_index.res26']}";    
    if(status=="come"){
    //${LANG['bizconf.jsp.conf_list_index.res27']}   
    	prefix = "${LANG['bizconf.jsp.conf_list_index.res28.before']}";
        suffix = "${LANG['bizconf.jsp.conf_list_index.res28']}";
    } else if(status=="run"){
    //${LANG['bizconf.jsp.conf_list_index.res29']}
    	prefix = "${LANG['bizconf.jsp.conf_list_index.res30']}";
        suffix = "";    
    } else if(status=="miss"){
    //${LANG['bizconf.jsp.conf_list_index.res31']}
    	prefix = "";
        suffix = "${LANG['bizconf.jsp.conf_list_index.res32']}";    
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
//${LANG['bizconf.jsp.conf_list_index.res34']}
jQuery(function($) {
	refreshTips();	
	showPassConf();
	getParticipantInfo();
	$(".m_search").watermark("${LANG['bizconf.jsp.conf_list_index.res33']}");
	$(".has_more").click(function() {
		var hasMoreBtn = $(this);
		hasMoreBtn.hide();
		var loading = $(this).prev();
		var hasContainer = $(this).closest(".has_more_container");
		loading.show();
		var data = {};
		var options = {};
		data.userRole = parent.$("#conf_user_role").val();
		data.dateScopeFlag = parent.$("#date_scope_flag").val();
		if(data.dateScopeFlag==1){
			var todayDate = parent.$("#today_date").val();
			if(todayDate){
				options.beginTime = todayDate.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = todayDate.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==2){
			var beginTime = parent.$("#weekStart").text();
			var endTime = parent.$("#weekEnd").text();
			if(beginTime && endTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = endTime.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==3){
			var beginTime = parent.$("#monthStart").val();
			var endTime = parent.$("#monthEnd").val();
			if(beginTime && endTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = endTime.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==4){
			var beginTime = parent.$("#allStart").val();
			var endTime = parent.$("#allEnd").val();
			if(beginTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				
			}
			if(endTime) {
				options.endTime = endTime.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}

		var confName =  parent.$(".m_search_list").val();
		if(confName && confName!="${LANG['bizconf.jsp.attendConfloglist.res3']}"){
			options.confName = confName;
		}
		data.confStatus = $(this).attr("status");
		data.pageNo = parseInt($(this).attr("pageNo"), 10);
		app.loadMoreConf(data,function(result) {
			if(result.status){
				loading.find("img").hide();
				loading.find("span").text(result.message);			
			} else {
				hasMoreBtn.attr("pageNo", data.pageNo+1);
				loading.hide();
				hasMoreBtn.show();
				hasContainer.before(result);
			}
			parent.refreshIHeight();
			refreshTips();
			getParticipantInfo();
		}, options);
	});
	parent.refreshIHeight();
});
function showPassConf() {
	var checked = parent.$("#conf_check_box").attr("checked");
	if(!checked){
		$("#pass_conf_container").hide();
	} else {
		$("#pass_conf_container").show();
	}	
}

function getParticipantInfo(){
	$("div[name=attendeer]").each(function(){
		var confId = $(this).attr("confId");
		var self = $(this);
	    var html = "";
	    var attendCount = $(this).find(".attendCount").text();
	    if(attendCount=="0") {
	    	$.ajax({
		      	type: "POST",
		      	url:"/user/conf/getConfInviteUser",
		      	data:{confId:confId},
		      	dataType:"json",
		      	success:function(data){
					html ="${LANG['bizconf.jsp.conf_list_index.res35']}";
					if(data){
						var renshu = "<img src=\"${baseUrlStatic}/images/ico24.jpg\" width=\"20\" height=\"20\" align=\"absmiddle\" />";
						if(data.length>0){
							html = "";
						}
						renshu += "<span class='attendCount'>"+data.length+"</span>";
						$(self).empty().html(renshu);
						for(var i=0;i<data.length&&i<5;i++){
							if(data[i]){
								html +=data[i].name+"<br/>";
							}
						}
					}else{
						alert('Server inner error!');
					}
					$(self).attr("original-title",html);
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	//alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		       		html = "${LANG['bizconf.jsp.conf_list_index.res36']}";
		       		$(self).attr("original-title",html);
		        }
			});  	    
	    }
	});
}
window.setInterval(refreshTips, 60000);
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
<div class="main_content" id="main_container" style="margin: 0px;">
<fmt:setLocale value="en-us"/>

 <!--${LANG['bizconf.jsp.conf_list_index.res40']}-->
<c:if test="${fn:length(dringConfList)>0 }">
<div class="conf_list_container">
	<div class="tab_title">
		<div align="left" style="float: left;width:45%">
	      	<span class="panel_icon panel_icon_off" onclick="toggleIcon(this)">&nbsp;</span>
	      	<span class="m_title" onclick="toggleIcon(this)">
	      	<c:choose>
	      		<c:when test="${user != null}">
	      			${LANG['bizconf.jsp.conf_list_index.res41']}
	      		</c:when>
	      		<c:otherwise>
	      			${LANG['bizconf.jsp.conf_list_index.res41']}
	      		</c:otherwise>
	      	</c:choose>
	      	</span>
	  		<div class="number_taxt">
	       		<div class="no_left"></div>
	            <div class="no_center"><span class="meeting-count">${dringConfRows}</span></div>
	            <div class="no_right"></div>
	       	</div>      		
		</div>
		<div align="right" style="float: right;width:50%;display: none;">
	      	<form action=""><input class="m_search" name="" type="text"  onblur="quickSearch(this)" onkeyup="quickSearch(this)"/></form>	
		</div>
	</div>
	<div id="running_list" class="tab_content" style="display: block;">
	<c:if test="${fn:length(dringConfList)<=0}">
		<div class="empty_result">${LANG['bizconf.jsp.conf_list_pad.res5']}</div>
	</c:if>
	<div class="empty_result" style="display: none;">${LANG['bizconf.jsp.conf_list_index.res42']}</div>
	<cc:confList var="CONF_PERMANENT_ENABLED_MAIN"/>
	<c:forEach var="dringConf" items="${dringConfList}" varStatus="dringStatus">
	<div class="extras-container">
		<div class="extras-tr" style="border-bottom:${empty dringConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
	      	<div class="date_part">
	      	  <fmt:formatDate var="runStartTime" value="${dringConf.startTime}" pattern="yyyy-MM-dd HH:mm" />
	      	  <fmt:formatDate var="runEndTime" value="${dringConf.endTime}" pattern="yyyy-MM-dd HH:mm" />
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
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res43']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res44']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res45']})</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        		</c:forEach>
	        		</a></span> 
<%-- 	        		<span class="content" title="${dringConf.confDesc}"><a href="javascript:;" onclick="viewConf(${dringConf.id})">${dringConf.confDesc}</a></span> --%>
	        	</div>
	        </div>
			<div class="action_part">
				<table  border="0" align="right" cellpadding="0" cellspacing="0">
          			<tr>
<%--	          			<c:if test="${user.id eq dringConf.compereUser and dringConf.permanentConf eq 1}">--%>
	          			<c:if test="${user.id eq dringConf.compereUser }">
	          				<td class="" ><!-- ${LANG['bizconf.jsp.conf_list_index.res53']} -->
		          					<div class="k_f" onclick="inventContact(${dringConf.id})"><img src="/static/images/yaoqing.jpg" width="21" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res13']}</div>
		          			</td>
	          			</c:if>
          				<c:if test="${dringConf.permanentConf eq 0}">
	          				<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_index.res23']}:${dringConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}" duration="${dringConf.duration}" endTime="${runEndTime}" status="run"><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${dringConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}</div></div></td>
          				</c:if>
          				<c:if test="${dringConf.permanentConf eq 1}">
	          				<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.attendConfloglist.res7']}:" duration="${LANG['bizconf.jsp.conf_list_pad.res4']}" endTime="<fmt:formatDate value="${dringConf.endTime}" pattern="yyyy-MM-dd HH:mm" />" status="run"><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin"  style="text-align: center;white-space:normal;line-height:normal;">${LANG['bizconf.jsp.conf_list_pad.res4']}</div></div></td>
          				</c:if>
			            <td><div class="k02 k09 viewtip" title="${LANG['bizconf.jsp.attendConfloglist.res5']}${dringConf.compereName}"><img src="${baseUrlStatic}/images/mac.jpg" width="18" height="21" align="absmiddle" /><div class="k02_span">${dringConf.compereName}</div></div></td>
<%--			            <td><div name="attendeer" confId="${dringConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${dringConf.maxUser}</div></td>--%>
			            <td><div class="k04 attendee" title="${LANG['bizconf.jsp.conf_list_index.res46']}${dringConf.pcNum+dringConf.phoneNum}"><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${dringConf.pcNum+dringConf.phoneNum}</div></td>
			            <td><div class="extras-default viewtip"  title="${LANG['bizconf.jsp.conf_list_index.res47']}" onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.jpg" width="20" height="20" /></a></div></td>
			            <td>
			            	<div class="k06">
			            		<c:if test="${user.id != dringConf.compereUser}">
				            		<span class="button_common">
						          		<a href="javascript:joinMeeting(1,'${dringConf.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">${LANG['bizconf.jsp.conf_list_index.res48']}</a>
						       		</span>
						       	</c:if>
						       	<c:if test="${user.id eq dringConf.compereUser}">
						       		<span class="button_common">
						          		<a href="javascript:joinMeeting(1,'${dringConf.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">${LANG['bizconf.jsp.conf_list_index.res49']}</a>
						       		</span>
						       	</c:if>
			            	</div>
			            </td>
          			</tr>
        		</table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action" style="border-bottom:${empty dringConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
   			<div align="right" style="padding-top:10px">
		 		<c:if test="${dringConf.createUser eq user.id && CONF_PERMANENT_ENABLED_MAIN eq dringConf.permanentConf}">
		 		<a class="email04" href="javascript:;" onclick="delConfForPerConf(${dringConf.id});">
		 			<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}
		 		</a>
		 		</c:if>
	   			<a class="email05" href="javascript:;" onclick="viewConf(${dringConf.id});"><img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res50']}</a>
	   			
   			</div>
   		</div>
   		<c:if test="${!empty dringConf.confDesc}">
			<div class="extras-desc">
				<div style="width: 60%;line-height: 18px;text-indent: 2em">
				<c:set var="drDesc" value="${fn:replace(dringConf.confDesc,' ','&nbsp;')}" />
				${fn:replace(drDesc,vEnter,"<br>")}
				</div>
			</div>
		</c:if>		
	</div>	
	</c:forEach>
	<c:if test="${fn:length(dringConfList)==pageSize }">
	<div  class="has_more_container">
		<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
			<img src="/static/images/loading.gif" alt="" />
			<span style="position: absolute;left: 35px;">${LANG['bizconf.jsp.conf_list_pad.res6']}...</span>
		</div>
		<a class="has_more" href="javascript:;" status="1" pageNo="2">${LANG['bizconf.jsp.conf_list_pad.res7']}</a>
	</div>
	</c:if>
</div> 	
</div>   
</c:if>
 <!--${LANG['bizconf.jsp.conf_list_index.res51']}-->
<%-- <c:if test="${fn:length(upcomingConfList)>0 }">  --%>
<div class="conf_list_container">
<div class="tab_title">
	<div align="left" style="float: left;width:45%">
      	<span class="panel_icon panel_icon_off" onclick="toggleIcon(this)">&nbsp;</span>
      	<span class="m_title" onclick="toggleIcon(this)">
	      	<c:choose>
	      		<c:when test="${user != null}">
			      	${LANG['bizconf.jsp.conf_list_index.res52']}
	      		</c:when>
	      		<c:otherwise>
	      			${LANG['bizconf.jsp.conf_list_index.res52']}
	      		</c:otherwise>
	      	</c:choose>
      	</span>
  		<div class="number_taxt">
       		<div class="no_left"></div>
            <div class="no_center"><span class="meeting-count">${UpcomingConfRows}</span></div>
            <div class="no_right"></div>
       	</div>	
	</div>
	<div align="right" style="float: right;width:50%;display: none;">
      	<form action=""><input class="m_search" name="" type="text"  onblur="quickSearch(this)" onkeyup="quickSearch(this)"/></form>	
	</div>
</div>
<div id="coming_list" class="tab_content" style="display: block;">
	<c:if test="${fn:length(upcomingConfList)<=0}">
		<div id="no_data" class="no_meeting">
		  	<div class="no_meeting_box">
		    	<p>${LANG['bizconf.jsp.conf_list_pad.res8']}</p>
		    	<c:if test="${empty user}">
		    		<a href="javascript:;" onclick="parent.parent.createReservationConf()">${LANG['bizconf.jsp.conf_list_index.res39']}</a>
		    	</c:if>
		    	<c:if test="${!empty user && user.userRole==1 }">
		    		<a href="javascript:;" onclick="parent.parent.createReservationConf()">${LANG['bizconf.jsp.conf_list_index.res39']}</a>
		    	</c:if>
		    </div>
		</div>
	</c:if>
	<div class="empty_result" style="display: none;">${LANG['bizconf.jsp.conf_list_index.res42']}</div>
  	<c:forEach var="upcomingConf" items="${upcomingConfList}" varStatus="upcomingStatus">
  	<div class="extras-container">
		<div class="extras-tr" style="border-bottom:${empty upcomingConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
			<div class="date_part">
				<fmt:formatDate var="comingStartTime" value="${upcomingConf.startTime}" pattern="yyyy-MM-dd HH:mm" />
				<fmt:formatDate var="comingEndTime" value="${upcomingConf.endTime}" pattern="yyyy-MM-dd HH:mm" />
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
	        		<span class="title">
	        		<a href="javascript:;" onclick="viewConf(${upcomingConf.id})">${upcomingConf.confName}
	        			<c:forEach var="confCyc" items="${cycs}">
			        			<c:if test="${confCyc.id eq upcomingConf.cycleId}">
			        				<c:choose>
			        					<c:when test="${confCyc.cycleType eq '1'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res43']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res44']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res45']})</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        		</c:forEach>		
	        		</a>
	        		</span> 
<%-- 	        		<span class="content" title="${upcomingConf.confDesc}"><a href="javascript:;" onclick="viewConf(${upcomingConf.id})">${upcomingConf.confDesc}</a></span> --%>
	        	</div>
	        </div>
			<div class="action_part">
				<table  border="0" align="right" cellpadding="0" cellspacing="0">
	          		<tr>
	          			<td class="" ><!-- ${LANG['bizconf.jsp.conf_list_index.res53']} -->
	          				<c:if test="${user.id eq upcomingConf.compereUser}">
	          					<div class="k_f" onclick="inventContact(${upcomingConf.id})"><img src="/static/images/yaoqing.jpg" width="21" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res13']}</div>
	          				</c:if>
	          			</td>
	          			<c:if test="${upcomingConf.permanentConf eq 0}">
	          				<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_index.res23']}:${upcomingConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}" duration="${upcomingConf.duration}"><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${upcomingConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}</div></div></td>
	          			</c:if>
	          			<c:if test="${upcomingConf.permanentConf eq 1}">
	          				<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_pad.res4']}" duration="${LANG['bizconf.jsp.conf_list_pad.res4']}" endTime="<fmt:formatDate value="${upcomingConf.endTime}" pattern="yyyy-MM-dd HH:mm" />" ><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin" style="text-align: center;white-space:normal;line-height:normal;">${LANG['bizconf.jsp.conf_list_pad.res4']}</div></div></td>
	          			</c:if>
	            		<td><div class="k02 k09 viewtip" title="${LANG['bizconf.jsp.attendConfloglist.res5']}${upcomingConf.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span">${upcomingConf.compereName}</div></div></td>
	            		<td><div onclick="<c:if test="${!empty user}">editInventContact('${upcomingConf.id}')</c:if>" name="attendeer" confId="${upcomingConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/ico24.jpg" width="20" height="20" align="absmiddle" /><span class='attendCount'>0</span></div></td>
	            		<td><div title="${LANG['bizconf.jsp.conf_list_index.res47']}" class="viewtip extras-default"  onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.jpg" width="20" height="20" /></a></div></td>
	            		<td>
	            			<c:if test="${upcomingConf.joinTimeFlag}">
	            				<div class="k06">
	            					<c:if test="${user.id != upcomingConf.compereUser}">
					            		<span class="button_common">
							          		<a href="javascript:joinMeeting(1,'${upcomingConf.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">${LANG['bizconf.jsp.conf_list_index.res48']}</a>
							       		</span>
							       	</c:if>
							       	<c:if test="${user.id eq upcomingConf.compereUser}">
							       		<span class="button_common">
							          		<a href="javascript:joinMeeting(1,'${upcomingConf.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">22222${LANG['bizconf.jsp.conf_list_index.res54']}</a>
							       		</span>
							       	</c:if>
			            		</div>
	            			</c:if>
		            		<c:if test="${!upcomingConf.joinTimeFlag}">
		            			<div class="k_n_06" >
		            				<a href="javascript:;">${LANG['bizconf.jsp.conf_list_index.notres48']}</a>
		            			</div>
	            			</c:if>
	            		</td>
	          		</tr>
        		</table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action" style="border-bottom:${empty upcomingConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
				<div align="left" style="padding-top:10px">
					<a class="email01" href="javascript:sendNoticeEmail(${upcomingConf.id});"><img src="${baseUrlStatic}/images/email05.png" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>Outlook</a>
					<a class="email02" href="javascript:sendNoticeEmail(${upcomingConf.id});" style=""> <img src="${baseUrlStatic}/images/ico002.png" width="16" height="11" align="absmiddle" style=" padding-right:5px;" />Gmail</a>
					<a class="email03" href="javascript:sendNoticeEmail(${upcomingConf.id});" style="display: none;"><img src="${baseUrlStatic}/images/ico003.png" width="14" height="17" align="absmiddle" style=" padding-right:5px;" />Foxmail</a>
				</div>
				<div align="right">
					<c:if test="${user.id eq upcomingConf.createUser}">
						 <c:choose>
						 	<c:when test="${upcomingConf.cycleId!=0}">
								<a class="email04" href="javascript:;" onclick="delAllConf(${upcomingConf.cycleId},${upcomingConf.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res55']}</a>
								<a class="email04" href="javascript:;" onclick="delConf(${upcomingConf.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
							 	<a class="email05" href="javascript:;" onclick="updateCycleBookMeetingInfo(${upcomingConf.id})">
									<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res56']}</a>	
<!--								<a class="email05" href="javascript:;" onclick="updateAllBookMeeting(${upcomingConf.cycleId})">-->
								<a class="email05" href="javascript:;" onclick="updateBookMeeting(${upcomingConf.id})">
									<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res57']}</a>
						 	</c:when>
						 	<c:otherwise>
						 		<a class="email04" href="javascript:;" onclick="delConf(${upcomingConf.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
						 		<a class="email05" href="javascript:;" onclick="updateBookMeeting(${upcomingConf.id})">
								<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res57']}</a>	
						 	</c:otherwise>
						 </c:choose>
					 </c:if>
				</div>
		</div>
		<c:if test="${!empty upcomingConf.confDesc}">
			<div class="extras-desc">
				<div style="width: 60%;line-height: 18px;text-indent: 2em">
					<c:set var="upDesc" value="${fn:replace(upcomingConf.confDesc,' ','&nbsp;')}" />
					${fn:replace(upDesc,vEnter,"<br>")}
				</div>
			</div>
		</c:if>	  	
  	</div>	
	</c:forEach>
	<c:if test="${fn:length(upcomingConfList)==pageSize }">
	<div  class="has_more_container">
		<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
			<img src="/static/images/loading.gif" alt="" />
			<span style="position: absolute;left: 35px;">${LANG['bizconf.jsp.conf_list_pad.res6']}...</span>
		</div>
		<a class="has_more" href="javascript:;" status="2" pageNo="2">${LANG['bizconf.jsp.conf_list_pad.res7']}</a>
	</div>
	</c:if>
</div>
</div> 
<%-- </c:if>  --%>
<!--${LANG['bizconf.jsp.conf_list_pad.res9']}-->
	<div class="conf_list_container" id="pass_conf_container" style="display: none;">
		<div class="tab_title">
			<div align="left" style="float: left;width:45%">
		      	<span class="panel_icon panel_icon_off" onclick="toggleIcon(this)">&nbsp;</span>
		      	<span class="m_title" onclick="toggleIcon(this)">${LANG['bizconf.jsp.conf_list_pad.res10']}</span>
		       	<div class="number_taxt">
		       		<div class="no_left"></div>
		            <div class="no_center"><span class="meeting-count">${AttendedConfRows}</span></div>
		            <div class="no_right"></div>
		       	</div>     		
			</div>
			<div align="right" style="float: right;width:50%;display: none;">
		      	<form action=""><input class="m_search" name="" type="text"  onkeyup="quickSearch(this)" style="display: none;"/></form>	
			</div>
		</div>
		<div id="passing_list" class="tab_content" style="display: block;">
			<c:if test="${fn:length(attendedConfList)<=0}">
				<div id="no_data" class="no_meeting">
				  	<div class="no_meeting_box">
				    	<p>${LANG['bizconf.jsp.conf_list_pad.res11']}</p>
				    	<c:if test="${empty user}">
				    		<a href="javascript:;" onclick="parent.parent.createReservationConf()">${LANG['bizconf.jsp.conf_list_index.res39']}</a>
				    	</c:if>
				    	<c:if test="${!empty user && user.userRole==1 }">
				    		<a href="javascript:;" onclick="parent.parent.createReservationConf()">${LANG['bizconf.jsp.conf_list_index.res39']}</a>
				    	</c:if>
				    </div>
				</div>
			</c:if>
			
			<cc:confList var="CONF_PERMANENT_UNABLE"/>
		    <c:forEach var="attendedConf" items="${attendedConfList}" varStatus="attendedStatus">
		    <div class="extras-container">	
				<div class="extras-tr"  style="border-bottom:${empty attendedConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
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
					        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res43']})</span>
					        					</c:when>
					        					<c:when test="${confCyc.cycleType eq '2'}">
					        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res44']})</span>
					        					</c:when>
					        					<c:when test="${confCyc.cycleType eq '3'}">
					        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res45']})</span>
					        					</c:when>
					        				</c:choose>
					        			</c:if>
					        		</c:forEach>
					        	</a>
				        	</span>
<%-- 			        	 	<span class="content" title="${attendedConf.confDesc}"> --%>
<%-- 			        	 		<a href="javascript:;" onclick="viewConf(${attendedConf.id})">${attendedConf.confDesc}</a> --%>
<!-- 			        	 	</span> -->
			        	 </div>
			        </div>
					<div class="action_part" style="float: right;">
						<table  border="0" align="right" cellpadding="0" cellspacing="0">
				          <tr>
				           	<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_index.res23']}:${attendedConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}"  duration="${attendedConf.duration}"><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${attendedConf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}</div></div></td>
				            <td><div class="k02 k09 viewtip" title="${LANG['bizconf.jsp.attendConfloglist.res5']}${attendedConf.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span">${attendedConf.compereName}</div></div></td>
				            <td><div  name="attendeer" confId="${attendedConf.id}" class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${attendedConf.maxUser}</div></td>
				            <td><div class="extras-default viewtip" title="${LANG['bizconf.jsp.conf_list_index.res47']}"  onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.jpg" width="20" height="20" /></a></div></td>
				              <td>
				            	<div class="k06">
				            		<c:if test="${user.id eq attendedConf.createUser}">
				            			<a href="javascript:;" onclick="reCreateReservationConf(${attendedConf.id})">${LANG['bizconf.jsp.attended_conf_list.res11']}</a>
			              			</c:if>
				            	</div>
				           	  </td>
				          </tr>
				        </table>
					</div>
					<span class="fader" onclick="faderClick(this)"></span>		
				</div>
		   		<div class="extras-action" style="border-bottom:${empty attendedConf.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
					<div align="right" style="padding-top:10px">
<!--						<c:if test="${user.id eq attendedConf.createUser && CONF_PERMANENT_UNABLE eq attendedConf.permanentConf}">-->
<!--		   					<a class="email04" href="#" onclick="delConf(${attendedConf.id});"><img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.attended_conf_list.res12']}</a>-->
<!--		   				</c:if>-->
		   				<a class="email05" href="javascript:;" onclick="viewConf(${attendedConf.id});"><img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res50']}</a>
		   			</div>
				</div>
		   		<c:if test="${!empty attendedConf.confDesc}">
					<div class="extras-desc">
						<div style="width: 60%;line-height: 18px;text-indent: 2em">
						<c:set var="atDesc" value="${fn:replace(attendedConf.confDesc,' ','&nbsp;')}" />
						${fn:replace(atDesc,vEnter,"<br>")}
						</div>
					</div>
				</c:if>					
			</div>	
			</c:forEach>
			<c:if test="${fn:length(attendedConfList)==pageSize }">
				<div  class="has_more_container">
					<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
						<img src="/static/images/loading.gif" alt="" />
						<span style="position: absolute;left: 35px;">${LANG['bizconf.jsp.conf_list_pad.res6']}...</span>
					</div>
					<a class="has_more" href="javascript:;" status="3" pageNo="2">${LANG['bizconf.jsp.conf_list_pad.res7']}</a>
				</div>
			</c:if>
		</div>    
	</div>
<!--${LANG['bizconf.jsp.conf_list_pad.res12']}-->	   
</div>
<!--   space -->
<div style="height: 80px;width: 500px">&nbsp;</div>
  
</body>

</html>
