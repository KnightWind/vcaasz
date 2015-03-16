<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.conf_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<%@ include file="/jsp/common/cookie_util.jsp"%>
<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<fmt:formatDate var="todayDateTime" value="${defaultDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></SCRIPT>	
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/date.js?ver=${version}"></SCRIPT>
<script type="text/javascript">
jQuery(function($) {
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$(".m_search_list").watermark("${LANG['bizconf.jsp.attendConfloglist.res3']}");
	$(".m_search_list").keypress(function(e){
		if(e.keyCode == 13){
			refreshIframe();
			return false;
		}
	});
	$(".m_search_btn").click(function(){
			refreshIframe();
			return false;
	});
	$('.m_top_tab li').click(function() {
		var index = parseInt($(this).attr("dateScopeFlag"), 10);
		selectTab(index);
		showLoading();
		refreshIframe();
	});
	$(".pass_conf_check").click(function() {
		var checked = $("#conf_check_box").attr("checked");
		if(!checked){
			$("#conf_check_box").attr("checked", "checked");
		} else {
			$("#conf_check_box").removeAttr("checked", "checked");
		}
		togglePassList();
	});
	$(".checkbox_01").click(function() {
		togglePassList();
	});
	var nowDate = new Date("${serverDate}");
	var monthStartValue = getMonthStartDate(nowDate);
	$( "#monthStart" ).val(monthStartValue);
	$( "#allStart" ).val(monthStartValue);
	var monthEndValue = getMonthEndDate(nowDate);
	$( "#monthEnd" ).val(monthEndValue);
	$( "#allEnd" ).val(monthEndValue);
	var monthStartDay = nowDate.getDate()-1;
	var maxDay = getMonthMaxDay(nowDate.getFullYear(), nowDate.getMonth()+1)-nowDate.getDate();
	$( "#monthStart" ).datepicker({
		minDate: -monthStartDay,
		maxDate: maxDay,
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/new03.png",
		buttonImageOnly: true,
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
		showOn: "both",
		buttonImage: "/static/images/new03.png",
		buttonImageOnly: true,
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
		showOn: "both",
		buttonImage: "/static/images/new03.png",
		buttonImageOnly: true,
		onClose: function() {
			if(compareDate($("#allStart").val(), $("#allEnd").val())){
				refreshIframe();
			}
		}
	});	
	showDateRange();
	initIframe();
});

function showLoading() {
	$("#contentFrame").hide();
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
	$("#contentFrame").show();
}

function selectTab(index) {
	$(".m_top_tab_default").removeClass("m_top_tab_active");
	$(".m_top_tab_default:eq("+(index-1)+")").addClass("m_top_tab_active");
	$("#date_scope_flag").val(index);
	var isLogin = "${isLogined}";
	if(index==1){
		$(".dateScopeFlag").hide();
		if(isLogin && isLogin=="false"){
			$(".todayTD").show();
		}
	} else {
		if(isLogin && isLogin=="false"){
			$(".todayTD").hide();
		}
		$(".dateScopeFlag:eq("+(index-2)+")").show().siblings().hide();	
	}
}

function quickSearchList(elem) {
	var searchValue = $(elem).val();
	var panels = $("#contentFrame")[0].contentWindow.$(".conf_list_container");
	for(var i=0; i<panels.length;i++){
		var listPanel = $(panels[i]);
		var noResult = listPanel.find(".empty_result"); 
		var container = listPanel.find(".extras-container");
		if(searchValue) {
			var findList = $(container).hide().filter(":contains(" + searchValue + ")");
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
}

function compareDate(startDate,endDate){
	var status=false;
	if(startDate.isEmpty() || endDate.isEmpty()){
		return status;
	}
	var date1=startDate.parseDate();
	var date2=endDate.parseDate().add(DateType.DAY,1);
	status=date1.before(date2);
	return status;
}

function showDateRange() {
	var now = new Date("${serverDate}");
	var weekStart = getWeekStartDate(now);
	var weekEnd = getWeekEndDate(now);
	$("#weekStart").text(weekStart);
	$("#weekEnd").text(weekEnd);
	var isLogin = "${isLogined}";
	if(isLogin && isLogin=="false"){
		var today = now.format("yyyy-MM-dd");
		$("#todayDate").text(today);
	}
}
function refreshIHeight() {
	hideLoading();
	var height = $("#contentFrame").contents().find("body").height() + 50;
	$("#contentFrame").height(height);  
}
function togglePassList() {
	var passList = $("#contentFrame")[0].contentWindow.$("#pass_conf_container");
	if (passList.is(":visible")) {
		passList.hide();
	} else {
		passList.show();
	}
	refreshIHeight();
}

function initIframe() {
	showLoading();
	var isLogin = "${isLogined}";
	var userRole = "${userRole}";
	if(isLogin && isLogin=="true"){
		$("#contentFrame").attr("src", "/user/conf/getControlPadConf?userRole="+userRole);
	} else {
		 alert("please login!");
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
	var confName = $(".m_search_list").val();
	if(confName && confName!="${LANG['bizconf.jsp.attendConfloglist.res3']}"){
		url = addUrlParam(url, "confName", confName, true);
	}
	//今天的会议
	var todayTime = $("#todayDate").text();
	if(todayTime && dateScopeFlag=="1") {
		beginTime = todayTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
		endTime = todayTime.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
	}
	//本月的会议
	var beginTime = $("#monthStart").val();
	var endTime = $("#monthEnd").val();

	if(beginTime && endTime && dateScopeFlag=="3"){
		beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
		endTime = endTime.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", beginTime);
		url = addUrlParam(url, "endTime", endTime);
	}
	
	//所有的会议
	var allStart = $("#allStart").val();
	var allEnd = $("#allEnd").val();
	if(allStart && allEnd && dateScopeFlag=="4"){
		allStart = allStart.parseDate().format("yyyy-MM-dd hh:mm:ss");
		allEnd = allEnd.parseDate().add(DateType.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
		url = addUrlParam(url, "beginTime", allStart);
		url = addUrlParam(url, "endTime", allEnd);
	}
	url = addT(url);
	$("#contentFrame").attr("src", url);
}

function refreshIframe() {
	clearCookie("reload",document.domain);
	var isLogin = "${isLogined}";
	if(isLogin && isLogin=="true"){
		loadIframe("/user/conf/getControlPadConf");
	} else {
		alert("please login!");
	}
}
window.setInterval(refreshIframe, 5*60*1000);
</script>
</head>
<body>
<div class="main_content">
	<input type="hidden" id="conf_user_role" value="${userRole}"/>
	<input type="hidden" id="date_scope_flag" value="1"/>
	<input type="hidden" id="today_date" value="${todayDateTime}"/>
	<div class="meeting_top">
		  <ul class="m_top_tab">
		    <li dateScopeFlag="1"><a class="m_top_tab_default m_top_tab_active" href="javascript:;">${LANG['bizconf.jsp.conf_list.res3']}</a></li>
		    <li dateScopeFlag="2"><a class="m_top_tab_default" href="javascript:;">${LANG['bizconf.jsp.conf_list.res2']}</a></li>
		    <li dateScopeFlag="3"><a class="m_top_tab_default" href="javascript:;">${LANG['bizconf.jsp.conf_list.res4']}</a></li>
		    <li dateScopeFlag="4"><a class="m_top_tab_default" href="javascript:;">${LANG['bizconf.jsp.conf_list.res5']}</a></li>
		  </ul>	
	</div>
	<table  border="0" align="center" cellpadding="0" cellspacing="0" id="table_box_02" width="100%">
    <tr height="40" bgcolor="#fff" style="background:#FFF" >
		  <td width="10%" class="STYLE00 todayTD">
		  		<c:if test="${isLogined}">
	      			<div class="" style="width: 140px;">
	        			<input id="conf_check_box" name="" type="checkbox" value="" class="checkbox_01"/>
	        			<span class="pass_conf_check" style="cursor: pointer;padding-left: 0px;">${LANG['bizconf.jsp.conf_list.res7']}</span>
	       			</div>
      	  		</c:if>
      	  		<c:if test="${!isLogined}">
      	  			<div style="width:140px;">
      	  				<h3><span id="todayDate"></span></h3>
      	  			</div>
      	  		</c:if>	
      	  </td>
	      <td width="70%" class="STYLE00">
	        <div class="dateScopeFlag" style="display: none;">
	        	<h3><span id="weekStart">2013-05-06</span> ${LANG['bizconf.jsp.conf_list.res8']} <span id="weekEnd" style="padding-left: 0px;">2013-06-09</span></h3>
	        </div>
	        <div class="dateScopeFlag" style="display: none;">
	        	<span style="margin-left: 8px;"></span>
		        <input id="monthStart" type="text" class="t01" readonly="readonly" style="" />
		        <span>${LANG['bizconf.jsp.conf_list.res6']}</span>
		        <input id="monthEnd" type="text"  class="t02"  readonly="readonly" style="margin-left: 8px;"/>
	        </div>
	        <div class="dateScopeFlag" style="display: none;">
	        	<span style="margin-left: 8px;"></span>
		   		<input id="allStart" type="text" class="t01" style=""/>
		        <span>${LANG['bizconf.jsp.conf_list.res6']}</span>
		        <input id="allEnd" type="text"  class="t02" style="margin-left: 8px;" />
	        </div>
	      </td>
      <td width="20%" class="STYLE00" align="right">
      	<div style="height: 40px;line-height: 40px;width: 270px;position: relative;top:10px;">
      	    <div>
        		<form action="">
        			<input class="m_search_list" name="" type="text" watermark="${LANG['bizconf.jsp.attendConfloglist.res3']}"/>
        			<a class="m_search_btn" href="javascript:;"></a>
        		</form>
        	</div>
      	</div>
      </td>
    </tr>
  </table>
	<div style="clear: both;"></div>
		<div id="loadingContainer" style="display: none;position: absolute;">
			<img src='/static/images/loading.gif'/>
			<span style="position: relative;bottom: 10px;">${LANG['bizconf.jsp.user.conf_list.res1']}...</span>
		</div>
		<iframe frameborder="0" width="100%" height="100%" id="contentFrame" name="contentFrame" scrolling="no" src=""></iframe>
</div>
</body>
</html>
