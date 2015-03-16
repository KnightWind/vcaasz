<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<fmt:formatDate var="todayDateTime" value="${defaultDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link rel="stylesheet" type="text/css" href="/assets/css/biz.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
<%@ include file="/jsp/common/cookie_util.jsp"%>
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
</head>
<body>
<div style="padding: 15px;">
	<div class="tabs-nav">
	<input type="hidden" id="conf_user_role" value="${userRole}"/>
	<input type="hidden" id="date_scope_flag" value="1"/>
	<input type="hidden" id="today_date" value="${todayDateTime}"/>
			<ul class="tabs clearfix"  style="width: 80%; float: left;">
				<li dateScopeFlag="1" data-name="tabs-today" class="today active datetabli">
					<a href="javascript:;">${LANG['website.common.today.name'] }</a>
				</li>
				<li dateScopeFlag="2" data-name="tabs-week" class="week datetabli">
					<a href="javascript:;">${LANG['website.user.conf.list.week'] }</a>
				</li>
				<li dateScopeFlag="3" data-name="tabs-month" class="month datetabli">
					<a href="javascript:;">${LANG['website.user.conf.list.month'] }</a>
				</li>
				<li dateScopeFlag="4" data-name="tabs-total" class="total datetabli">
					<a href="javascript:;">${LANG['website.user.conf.list.all'] }</a>
				</li>
			</ul>
		<div style="float: left; width: 19%; height: 20px; line-height: 22px;" align="right">
			 <div  onclick="sycnConf();" style="cursor: pointer;"><img src="/assets/images/icon_refresh.png" alt="${LANG['bizconf.jsp.admin.login.res10']}" align="top"  /> <span>${LANG['bizconf.jsp.admin.login.res10']}</span></div>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="summary">
			<!--  显示已主持的会议 -->
		<c:if test="${isLogined}">
		<span class="attend">
			<input id="check_atttend" type="checkbox" value="1"/>
			<label for="check_atttend">
				<c:if test="${userRole==1}">
				${LANG['website.user.conf.list.showhost']}
				</c:if>
				<c:if test="${userRole==2}">
				${LANG['website.user.conf.list.showattend']}
				</c:if>
			</label>
		</span>
		</c:if>
		<div class="tabs-content">
			<div id="tabs-today" class="tabs-panel">
				<i class="icon icon-date fix-png">&nbsp;</i>
				<span><fmt:formatDate value="${defaultDate}" pattern="yyyy-MM-dd" /></span>
			</div>
			<div id="tabs-week" class="tabs-panel">
				<i class="icon icon-date fix-png">&nbsp;</i>
				<span id="weekStart">2013-09-16</span> — <span id="weekEnd">2013-09-16</span>
			</div>
			<div id="tabs-month" class="tabs-panel">
			 	<input class="input-text" id="monthStart" type="text" readonly="readonly"  />
			 	<span> — </span>
			 	<input class="input-text"  id="monthEnd" type="text" readonly="readonly"/>
			</div>
			<div id="tabs-total" class="tabs-panel">
				<input class="input-text" id="allStart" type="text" /> — <input class="input-text" id="allEnd" type="text" />
			</div>
		</div>		
		<div class="search">
				<input type="text" class="input-text" name="confName" id="confName" value=""  onkeydown="javascript:enterSubmit()" placeholder="${LANG['website.user.conf.list.search.message'] }"  />
				<button class="submit-search" type="button" onclick="javascript:searchConf()" >${LANG['website.common.search.name']}</button>
		</div>
	</div>
	<div class="clearfix"></div>
	<div id="loadingContainer" style="display: none;position: absolute;">
		<img src='/assets/images/animate/loading.gif'/>
		<span style="position: relative;bottom: 10px;">${LANG['website.common.loading.message'] }</span>
	</div>
	<iframe allowtransparency='true' frameborder="0" width="100%" height="100%" id="contentFrame" name="contentFrame" scrolling="no" src="" style="display: none;"></iframe>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.ajax.js"></SCRIPT>	
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.date.js"></SCRIPT>	
<script type="text/javascript">
var isLogin = "${isLogined}";
var userRole = "${userRole}";

var nowDate = new Date("${serverDate}");
var monthStartValue = getMonthStartDate(nowDate);
$( "#monthStart" ).val(monthStartValue);
$( "#allStart" ).val(monthStartValue);
var monthEndValue = getMonthEndDate(nowDate);
$( "#monthEnd" ).val(monthEndValue);
$( "#allEnd" ).val(monthEndValue);
var monthStartDay = nowDate.getDate()-1;
var maxDay = getMonthMaxDay(nowDate.getFullYear(), nowDate.getMonth()+1)-nowDate.getDate();

var CONSTANT_MDEAFAULT={
	searchMsg : "${LANG['website.user.conf.list.search.message']}"//会议主题
};


function sycnConf(){
	var url = $("#contentFrame").attr("src");
	showLoading();
	$.ajax({
      	type: "POST",
      	url:"/user/conf/syncConf/",
      	dataType:"json",
      	success:function(data){
			if(data.status == 100){
				//top.successDialog("数据已同步");
				 $("#contentFrame").attr("src",url);
			}else{
				top.errorDialog("${LANG['website.user.conf.Synchronousdata.failure']}");
			}
			hideLoading();
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
        }
	}); 
	
}

</script>

<script type="text/javascript" src="/assets/js/apps/biz.meeting.dashboard.js"></script>
</body>
</html>

