<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="${baseUrlStatic}/js/date.js?ver=${version}"></script>
<div id="tool_bar" class="subnav">
  <h3>${LANG['bizconf.jsp.quick_tools.res1']}</h3>
  <ul>
    <c:if test="${empty user}">
		<li class="sub01"><img src="${baseUrlStatic}/images/subnav01.png" width="26" height="24" onclick="createImmediatelyConf()"/><a class="subnav_01" href="javascript:createImmediatelyConf();">${LANG['bizconf.jsp.help.res6']}</a></li>
    	<li class="sub02"><img src="${baseUrlStatic}/images/subnav02.png" width="26" height="26" onclick="createReservationConf()"/><a class="subnav_02" href="javascript:createReservationConf();">${LANG['bizconf.jsp.conf_list_index.res39']}</a></li>
    </c:if>
    <c:if test="${!empty user && user.userRole==1 }">
		<li class="sub01"><img src="${baseUrlStatic}/images/subnav01.png" width="26" height="24" onclick="createImmediatelyConf()"/><a class="subnav_01" href="javascript:createImmediatelyConf();">${LANG['bizconf.jsp.help.res6']}</a></li>    
    	<li class="sub02"><img src="${baseUrlStatic}/images/subnav02.png" width="26" height="26" onclick="createReservationConf()"/><a class="subnav_02" href="javascript:createReservationConf();">${LANG['bizconf.jsp.conf_list_index.res39']}</a></li>
    </c:if>
    <li class="sub03"><img src="${baseUrlStatic}/images/subnav03.png" width="23" height="26" onclick="joinMeeting(2)"/><a class="subnav_03" href="javascript:joinMeeting(2);">${LANG['bizconf.jsp.help.res8']}</a></li>
  </ul>
  <a class="close" href="javascript:closeToolBar();" style="display: none;"></a>
  <fmt:formatDate var="dDate" value="${defaultDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
  <div style="float: right;color:#EB6D00;cursor: pointer;margin-right:10px" onclick="jumpToFavor()"><span style="text-decoration:underline;">${siteBase.timeZoneDesc }${LANG['bizconf.jsp.create_Reservation_Conf.res97']}</span> <span id="toolsDateTimeSpan">${dDate}</span> </div> 
</div>

<script type="text/javascript">
var timer=null;
var nextDateTime;
var dateTimeStamp="${dDate}".parseTimeStamp();
function showTime(){
	if(dateTimeStamp<=0){
		window.clearInterval(timer);
	}
	nextDateTime=new Date(dateTimeStamp);
	$("#toolsDateTimeSpan").html(nextDateTime.format("yyyy-MM-dd hh:mm:ss"));
	dateTimeStamp+=1000;
}
//showTime();
timer=window.setInterval("showTime()",1000);
function jumpToFavor() {
	var loginUser = "${user}";
	if(loginUser) {
		parent.jumpToFrame("/user/favor/getTimeZone");
	} else {
		login();
	}
}
</script>
