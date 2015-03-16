/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/
                
var $l = timeObj;
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
function switchTitle(currentDate, startDate, endDate, elem, status) {
    //过去的会议
    var prefix = CONFLIST_CONSTANT.enahead;
    var suffix = CONFLIST_CONSTANT.ahead;    
    if(status=="come"){
    //即将开始的会议   
    	prefix = CONFLIST_CONSTANT.enlater;
        suffix = CONFLIST_CONSTANT.later;
    } else if(status=="run"){
    //正在进行的会议
    	prefix = CONFLIST_CONSTANT.opened;
        suffix = "";    
    } else if(status=="miss"){
    //错过的会议
    	prefix = "";
        suffix = CONFLIST_CONSTANT.attend;    
    }
    var distanceMillis = currentDate.getTime()-startDate.getTime();
    var words = getwords(distanceMillis);
    var title = prefix+words+suffix;
    $(elem).attr("title", title);
}
function switchWords(currentDate, startDate, endDate, elem) {
	var currentYear = currentDate.getFullYear();
	var currentMonth = currentDate.getMonth();
	var startYear = startDate.getFullYear();
	var startMonth = startDate.getMonth();
	var dateText = formatDate(startDate);
	if(currentYear==startYear && currentMonth==startMonth){	
		var currentDay = currentDate.getDate();
		var startDay = startDate.getDate();
		if((startDay-currentDay)==0){
			dateText = todayName;
		} else if((startDay-currentDay)==1) {
			dateText = tomoName;
		} else if((startDay-currentDay)==-1) {
			dateText = yestName;
		}
	}
	$(elem).find(".date").text(dateText);
}

function switchCalendar() {
	$(".calendar").each(function(index, elem) {
		var dateStartStr = $(elem).attr("starttime").replace(/-/g,"/");
		var dateEndStr = $(elem).attr("endtime").replace(/-/g,"/");
		var status = $(elem).attr("status");

		var currentDate = new Date(serverDate); //get server date
		var startDate = new Date(dateStartStr);
		var endDate = new Date(dateEndStr);
		switchWords(currentDate, startDate, endDate, elem);
		switchTitle(currentDate, startDate, endDate, elem, status);
	});
}

function switchDuration() {
	$(".duration").each(function(index, elem){
		var duration = $(elem).attr("duration");
		var duraText = "";
		if(!duration){
			var endTime = $(elem).attr("endTime");
			var date = "";
			var time = "";
			if(endTime){
				date = endTime.substring(0,10);
				time = endTime.substring(11);
			}
			$(elem).find(".duration-text").html(date+"\n"+time);
			 var prefix = CONFLIST_CONSTANT.endtime+" "+colon;
			 var suffix = "";
			 var title = prefix+endTime+suffix;
			$(elem).find(".duration-tipsy").attr("original-title", title);
		} else {
			duration = parseInt(duration, 10);
			var duraHour = duration/60>>0;
			var duraMis = duration%60;
			if(duraHour>0){
				if(duraHour==1){
					duraText += duraHour+" "+ hourUnit;
				} else {
					duraText += duraHour+" "+ hoursUnit;
				}
			}
			if(duraMis>0){
				if(duraMis==1) {
					duraText += " " +duraMis+" " + minuteUnit;
				} else {
					duraText += " " +duraMis+" "+ minutesUnit;
				}
			}
			if(!duraText){
				duraText = "0 " + minuteUnit;
			}
			$(elem).find(".duration-text").text(duraText);
		    var prefix = CONFLIST_CONSTANT.confduration+""+colon;
		    var suffix = "";
		    var title = prefix+duraText+suffix;
		    $(elem).find(".duration-tipsy").attr("original-title", title);
		}
	});
}
function getParticipants(){
	$(".inviters").each(function(){
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
					html = CONFLIST_CONSTANT.noinviter;
					if(data){
						if(data.length>0){
							html = "";
						}
						$(self).find(".attendCount").text(data.length);
						for(var i=0;i<data.length&&i<5;i++){
							if(data[i]){
								html +=data[i].name+"<br/>";
							}
						}
						if(data.length>5){
							html += "...";
						}
					}else{
						alert('Server inner error!');
					}
					$(self).find(".attendance-tipsy").attr("original-title", html);
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		       		html = CONFLIST_CONSTANT.findfail+""+exmark;
		       		$(self).find(".attendance-tipsy").attr("original-title",html);
		        }
			});  	    
	    }
	});
}
function showPassConf() {
	var checked = parent.$("#check_atttend").attr("checked");
	if(checked){
		$("#passConfPanel").show();
	} else {
		$("#passConfPanel").hide();
	}	
}
function switchPanelTitle() {
	var userRole = parent.$("#conf_user_role").val();
	if (userRole==1) {
		$("#attendConfTitle").text(CONFLIST_CONSTANT.meetingHosted);
	} else {
		$("#attendConfTitle").text(CONFLIST_CONSTANT.meetingAttended);
	}
}
function refreshTips() {
	//switchCalendar();
	switchDuration();
	//$('.calendar').tipsy({ trigger: 'hover', fade: false, gravity: 'sw', opacity: 0.65 });
	$('.duration-tipsy').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 's', opacity: 0.65 });
	$('.compere-tipsy').tipsy({ trigger: 'hover', fade: false, gravity: 's', opacity: 0.65 });
	$('.attendance-tipsy').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 'w', opacity: 0.65 });
}
jQuery(function($) {
	refreshTips();
	getParticipants();
	switchPanelTitle();
	showPassConf();
	// 模块收展效果
	$(document).on('click', '.meeting-module .module-head', function() {
			var header = jQuery(this), module = header.closest('.meeting-module');
			if (header.find('.icon-expand').size() > 0) {
				module.toggleClass('module-close');
			}
			parent.refreshIHeight();
	});
	
	// 模块收展效果
	/**$(document).on('click', '.settings', function() {
			var settingIcon = jQuery(this), 
			actionPanel = settingIcon.closest('.module-item').find(".item-actions");
			$(".item-actions").not(actionPanel).hide();
			$(".settings ").not(settingIcon).removeClass("settings-active");
			settingIcon.toggleClass('settings-active');
			actionPanel.toggle();
			parent.refreshIHeight();
	});*/
	parent.refreshIHeight();
	
	/* 更多功能更换
	 $(".has_more").click(function() {
		var hasMoreBtn = $(this);
		hasMoreBtn.hide();
		var loading = $(this).prev();
		var hasContainer = $(this).closest(".has_more_container");
		var ulList = hasContainer.prev();
		loading.show();
		var data = {};
		var options = {};
		data.userRole = parent.$("#conf_user_role").val();
		data.dateScopeFlag = parent.$("#date_scope_flag").val();
		if(data.dateScopeFlag==1){
			var todayDate = parent.$("#today_date").val();
			if(todayDate){
				options.beginTime = todayDate.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = todayDate.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==2){
			var beginTime = parent.$("#weekStart").text();
			var endTime = parent.$("#weekEnd").text();
			if(beginTime && endTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = endTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==3){
			var beginTime = parent.$("#monthStart").val();
			var endTime = parent.$("#monthEnd").val();
			if(beginTime && endTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				options.endTime = endTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}
		if(data.dateScopeFlag==4){
			var beginTime = parent.$("#allStart").val();
			var endTime = parent.$("#allEnd").val();
			if(beginTime){
				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
				
			}
			if(endTime) {
				options.endTime = endTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
			}
		}

		var confName =  parent.$("#confName").val();
		if(confName && confName!= CONFLIST_CONSTANT.confname){
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
//				hasContainer.before(result);
				ulList.append(result);
			}
			parent.refreshIHeight();
			refreshTips();
			getParticipants();
		}, options);
	}); 
	 */
	
});

function joinMeeting(joinType,cId,confNo,hostId,uname,token){//cId,cPass,code){
	//从这进行判断是否ie
	//if($.browser.msie){
		//getStartMeetingUrl(confNo,hostId,uname,token);
	//	joinMeetingMutipartWay(confNo,hostId,uname,token);
	//	return;
	//}
	//joinMeetingMutipartWay(confNo,hostId,uname,token);
	
	top.joinMeeting(joinType,cId);
}

//多种方式入会尝试  优先使用clickOnce不可用情况下使用协议启动
function joinMeetingMutipartWay(confNo,hostId,uname,token){
	
	//协议入会地址
	var name = encodeURIComponent(encodeURIComponent(uname));
	//alert($.browser.version);
	//alert($.browser.msie);
	
	 
	 if(browserMatch.browser == 'IE' && parseInt(browserMatch.version)>9){
		 alert('ok');
		 name = encodeURIComponent(uname);
	 }
	 
	var protcolUrl = "cfcloud://www.confcloud.cn/";
		protcolUrl += "start";
		protcolUrl += "?confno="+confNo;
		protcolUrl += "&sid="+hostId;//主持人登录需要这个
		protcolUrl += "&stype=100";	//主持人自动登录必备参数
		protcolUrl += "&uid="+hostId;
		protcolUrl += "&uname="+name;	//入会人名字
		protcolUrl += "&token="+token;//
		$.ajax({
	      	type: "POST",
	      	url: "/join/getClickOnceStatu",
	      	async:false,//必须要同步执行 否则不能启动会议
	      	dataType:"json",
	      	success:function(data){
	      		if(data.state == 1 && data.url){
	      			var zoomurl = data.url+"?dr=https://bizconf.zoomus.cn"
	      			+"/client&action=start&confno="+confNo+"&sid="+hostId+"&stype=100&uid="+hostId+"&uname="+uname+"&token="+token;
	      			//alert("zoomurl =="+zoomurl);
	      			window.location = zoomurl;
	      		}else{
	      			//协议启动
	      			//alert(protcolUrl);
	      			window.location = protcolUrl;
	      		}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	//alert("error");
	        	//错误的情况直接协议启动
	        	window.location = protcolUrl;
	        }
	});  
}

