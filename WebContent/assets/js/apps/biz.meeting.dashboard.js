/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/
function enterSubmit(){
	var event=arguments.callee.caller.arguments[0]||window.event;
    if (event.keyCode == 13){       
    	searchConf();
    }   
}
function refreshIHeight() {
	hideLoading();
	var height = $("#mainFrame").contents().find("body").outerHeight();
	$("#mainFrame").height(height);
	parent.resizeHeight();
}
function showLoading() {
//	$("#contentFrame").hide();
	$("#mainFrame").hide();
	var pWidth = parent.$("#mainFrame").width();
	var pHeight = $(parent.window).height();
	if(pHeight<450){
		pHeight = 450;
	}
	var left = (pWidth-50)/2;
	var top = (pHeight-150)/2;
	$("#loadingContainer").css({left:left,top:top}).show();
}

function hideLoading() {
	$("#loadingContainer").hide();
	$("#mainFrame").show();
//	$("#contentFrame").show();
}

function initIframe() {
//	showLoading();
	var url = "";
	if(isLogin && isLogin=="true"){
		url="/user/conf/getControlPadConf?userRole="+userRole;
//		$("#contentFrame").attr("src", "/user/conf/getControlPadConf?userRole="+userRole);
	} else {
		var todayTime = $("#todayDate").text();
		if(todayTime==null || todayTime==""){
			todayTime=nowDate.format("yyyy-MM-dd 00:00:00");
		}
		beginTime = todayTime.parseDate().format("yyyy-MM-dd 00:00:00");
		endTime = todayTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd 00:00:00");//+" 00:00:00"
//		var url = "/user/conf/getControlPadPublicConf";
		url="/user/conf/getControlPadPublicConf";
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
		$("#contentFrame").attr("src", url);
	}
	
	$("#contentFrame").attr("src", url);
}
function initTab() {
	$(".tabs").slideBar({
        clickCallback: function(elem) {
        	var name = elem.parent().attr('data-name');
        	selectTab(name);
        	initIframe();
        }
    });
}
function selectTab(tabName) {
    $("#"+tabName).show().siblings().hide();
    
    if(tabName=="1" || tabName=="2" || tabName=="3" || tabName=="4"){
	    $("#date_scope_flag").val(tabName);
	    var url="";
	    if(isLogin && isLogin=="true"){
			url="/user/conf/getControlPadConf?userRole="+userRole;
		} else {
			url="/user/conf/getControlPadPublicConf";
		}
	    loadIframe(url);
    }
}
function getChildPanel(id) {
	return $("#contentFrame")[0].contentWindow.$("#"+id);
}
function displayPassList() {
	var passList = $("#contentFrame")[0].contentWindow.$("#pass_conf_container");
	if (passList.is(":visible")) {
		passList.hide();
	} else {
		passList.show();
	}
	refreshIHeight();
}

function showDateRange() {
	var weekStart = getWeekStartDate(nowDate);
	var weekEnd = getWeekEndDate(nowDate);
	$("#weekStart").text(weekStart);
	$("#weekEnd").text(weekEnd);
	if(isLogin && isLogin=="false"){
		var today = nowDate.format("yyyy-MM-dd");
		$("#todayDate").text(today);
	}
}


function loadIframe(urlParam) {
	var url = "/user/conf/getControlPadConf";
	if(urlParam) {
		url=urlParam;
	}
	var userRole = $("#conf_user_role").val();
	if(userRole){
		url = addUrlParam(url, "userRole", userRole);
	}
	var dateScopeFlag = $("#date_scope_flag").val();
	if(dateScopeFlag){
		url = addUrlParam(url, "dateScopeFlag", dateScopeFlag);
	}
	//搜索条件
	var confName = $("#confName").val();
	if(confName!="" && confName!=CONSTANT_MDEAFAULT.searchMsg){
		url = addUrlParam(url, "confName", confName, true);
	}
	//今天的会议
	var todayTime = $("#today_date").val();

	
	if(todayTime && dateScopeFlag=="1") {
		beginTime = todayTime.parseDate().format("yyyy-MM-dd 00:00:00");
		endTime = todayTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd 00:00:00");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
	}
	//本周的会议
	var weekStart = $("#weekStart").text();
	var weekEnd = $("#weekEnd").text();
	if(weekStart && weekEnd && dateScopeFlag=="2") {
		beginTime = weekStart.parseDate().format("yyyy-MM-dd 00:00:00");
		endTime = weekEnd.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd 00:00:00");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
	}
	//本月的会议
	var beginTime = $("#monthStart").val();
	var endTime = $("#monthEnd").val();

//	alert("beginTime=="+beginTime +"\nendTime=="+endTime);
	if(beginTime && endTime && dateScopeFlag=="3"){
		beginTime = beginTime.parseDate().format("yyyy-MM-dd 00:00:00");
		endTime = endTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd 00:00:00");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
	}
	
	//所有的会议
	var allStart = $("#allStart").val();
	var allEnd = $("#allEnd").val();

//	alert("allStart=="+allStart +"\nallEnd=="+allEnd);
	if(allStart && allEnd && dateScopeFlag=="4"){
		allStart = allStart.parseDate().format("yyyy-MM-dd 00:00:00");
		allEnd = allEnd.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd 00:00:00");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", allStart);
		url = addUrlParam(url, "endTime", allEnd);
	}
	url = addT(url);
//	alert(url);
	$("#contentFrame").attr("src", url);
}



function compareDate(startDate,endDate){
	var status=false;
	if(startDate.isEmpty() || endDate.isEmpty()){
		return status;
	}
	var date1=startDate.parseDate();
	var date2=endDate.parseDate().add(DATETYPE_CONSTANT.DAY,1);
	status=date1.before(date2);
	return status;
}


function refreshIframe() {
	clearCookie("reload",document.domain);
	if(isLogin && isLogin=="true"){
		loadIframe("/user/conf/getControlPadConf");
	} else {
		loadIframe("/user/conf/getControlPadPublicConf");
	}
}

window.setInterval(refreshIframe, 5*60*1000);

function searchConf() {
	if(isLogin && isLogin=="true"){
		loadIframe("/user/conf/getControlPadConf");
	} else {
		loadIframe("/user/conf/getControlPadPublicConf");
	}
}
jQuery(function($) {
	initTab();
	initIframe();
	$("#check_atttend").change(function(e) {
		var checked = $(this).attr("checked");
		if (checked) {
			getChildPanel("passConfPanel").show();
		} else {
			getChildPanel("passConfPanel").hide();
		}
		refreshIHeight();
	});
	$('.datetabli').click(function() {
		var index = parseInt($(this).attr("dateScopeFlag"), 10);
		selectTab(index);
		showLoading();
		refreshIframe();
	});
	
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$( "#monthStart" ).datepicker({
		minDate: -monthStartDay,
		maxDate: maxDay,
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		onClose: function(dateText) {
			if(compareDate(dateText,$( "#monthEnd").val())){
				refreshIframe();
			}
		}
	});

	$( "#monthEnd" ).datepicker({
		minDate: -monthStartDay,
		maxDate: maxDay,
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		onClose: function(dateText) {
			if(compareDate($("#monthStart").val(),dateText)){
				refreshIframe();
			}
		}
	});

	$( "#allStart, #allEnd" ).datepicker({
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		onClose: function() {
			if(compareDate($("#allStart").val(), $("#allEnd").val())){
				refreshIframe();
			}
		}
	});	
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=confName]").watermark(CONSTANT_MDEAFAULT.searchMsg);
	}
	showDateRange();
});

