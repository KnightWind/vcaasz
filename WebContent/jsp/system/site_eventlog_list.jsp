<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.search.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript"> 
$(function() {
	$("#logsForm").find("select, input").not(".skipThese").uniform();
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	$(".gaoji").toggle(function () {
	    $("#search-condition").show();
	}, function () {
		$("#search-condition").hide();
	});
	$("#search").click(function() {
		btnSearch();
	});
});

function enterSumbit(url){  
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.admin.conf_list.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.admin.conf_list.res4']}
    	resetPageNo();
    	logsForm.action=url;
    	logsForm.submit();	
    }   
} 


function viewDetails(id) {
	if(id != 0){
		parent.viewSysAdminDetails(id);
	}
}
</script>
<title>site info</title>
</head>
<body>
<div class="main_right">
<form id="logsForm" name="logsForm" action="/system/logs/siteloglist" method="post">
	<input class="skipThese" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
	<input class="skipThese" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
 <div class="m_top">
    <div class="text_search">
    	<div style="float: left;">
	    	<select name="logType" id="logType" onchange="javascript:btnSearch();">
		   		<cc:logs var="SITE_EVENTLOG_TOTAL"/>
		   		<c:forEach var="eachType" items="${SITE_EVENTLOG_TOTAL}"  varStatus="itemStatus">
		   			<c:set var ="typeName" value="siteAdmin.eventlog.type.${eachType }"/>
		   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
		   		</c:forEach>
    		</select>
    	</div>
		<input class="" type="text" name="userName" id="userName" value="${userName}" onkeydown='enterSumbit("/system/logs/siteloglist")'  style="width: 160px;float:left;text-indent:1em;height: 17px;margin-left: -5px;"/>
    	<input type="button" name="search" id="search" class="searchs_button skipThese"/>
    </div>
   
  </div>
    	<div id="search-condition" style="display: none; width:100%; height: 300px;">
    		${LANG['bizconf.jsp.system.eventlog_list.res1']}
    	</div>
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#D6D6D6 1px solid; border-top:none; border-bottom:none;">
  <cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/>
   <cc:sort var="EVENTLOG_SORT_DEFAULT"/><cc:sort var="EVENTLOG_SORT_STATUS"/><cc:sort var="EVENTLOG_SORT_CREATETIME"/> 
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10" style="cursor: pointer;" onclick="javascript:sort('${EVENTLOG_SORT_STATUS}');"><div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.status"]}</b></span>
         <c:if test="${EVENTLOG_SORT_STATUS!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_STATUS==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_STATUS==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
     </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10" style="cursor: pointer;" onclick="javascript:sort('${EVENTLOG_SORT_CREATETIME}');"><div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.logtime"]}</b></span>
	        <c:if test="${EVENTLOG_SORT_CREATETIME!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_CREATETIME==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_CREATETIME==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
        </div></td>
        <td width="19%" height="38" bgcolor="d3eaef" class="STYLE10" ><div align="center"><span><b>${LANG["system.eventlog.title.option.module"]}&nbsp;</b></span>
        </div></td>
        <td width="13%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.site.sign"]}</b></span></div></td>
         <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.user"]}</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.object"]}&nbsp;</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.ip"]}</b></span></div></td>
        <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["event.log.detail"]}</b></span></div></td>
      </tr>
      <cc:logs var="EVENTLOG_SECCEED"></cc:logs>
      	<cc:logs var="EVENTLOG_FAIL"></cc:logs>
      	<cc:logs var="SITE_ADMIN_USER_UPDATE"></cc:logs>
      	<c:if test="${fn:length(logList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="8"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     	<c:forEach var= "eventLog" items = "${logList}"  varStatus="status">
       <tr>
        <td height="32" class="STYLE19">
        	<div align="center"><span>
       		<c:if test="${eventLog.logStatus==EVENTLOG_SECCEED}">${LANG['website.message.succeed']}</c:if>
        	<c:if test="${eventLog.logStatus==EVENTLOG_FAIL}">${LANG['website.message.fail']}</c:if>
        	</span></div>
        </td>
        <td height="32" class="STYLE19" ><div align="center">
        <fmt:formatDate  value="${eventLog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div></td>
        <c:set var ="typeName" value="siteAdmin.eventlog.type.${eventLog.funcModule}"/>
        <td height="32" class="STYLE19"><div align="center">${LANG[typeName]}</div></td>
        <td height="32" class="STYLE19"><div align="center" class="site_sign_${eventLog.siteId}"></div></td>
        <td height="32" class="STYLE19"><div align="center" userName="${eventLog.createUser}" class="site_${eventLog.siteId}User_${eventLog.createUser}"></div></td>
<!--        <c:set var="eventUser" value="${operatorObjectList[eventLog.userId]}"/>-->
        <c:set var="eventUser" value="${eventLog.optName}"/>
        <c:if test="${not empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">${eventUser }</div></td>
        </c:if>
        <c:if test="${empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">- -</div></td>
        </c:if>
        <td height="32" class="STYLE19"><div align="center">${eventLog.createIp}</div></td>
        <td height="32" class="STYLE19">
          <c:if test="${SITE_ADMIN_USER_UPDATE == eventLog.funcModule}">
        	<div align="center"><a onclick="viewDetails(${eventLog.id})" href="javascript:;">${LANG["event.log.detail.view"]}</a></div>
        </c:if>
        <c:if test="${SITE_ADMIN_USER_UPDATE != eventLog.funcModule}">
        	<div align="center" style="opacity:0.5;filter:alpha(opacity=10);">${LANG["event.log.detail.view"]}</div>
        </c:if>
        </td>
      </tr>
      </c:forEach>
    </table>
  </tr>
  <tr>
    <td class="table_bottom" height="38">
    <jsp:include page="/jsp/common/page_info.jsp" />
    </td>
  </tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
function sort(sortField){
	var formId=($("#sortField").closest("form").attr("id"));
	var oldSortField=$("#sortField").val();
	var oldSortType=$("#sortord").val();
	if(oldSortField==sortField){
		if(oldSortType=="${SORT_DESC}"){
			$("#sortord").val("${SORT_ASC}");
		}else{
			if(oldSortType=="${SORT_ASC}"){
				$("#sortord").val("${SORT_DESC}");
			}
		}
	}else{
		$("#sortField").val(sortField);
		$("#sortord").val("${SORT_ASC}");
	}
	resetPageNo();
	$("#"+formId).submit();
	
}
function initPage(){

	var logUserArr=new Array();
	var eachUserArr=new Array();
	<c:forEach var="eachSiteAdmin" items="${userList}" varStatus="status">
	eachUserArr=new Array();
	eachUserArr.push("${eachSiteAdmin.id}");
	eachUserArr.push("${eachSiteAdmin.siteId}");
	eachUserArr.push("${eachSiteAdmin.trueName}");
	logUserArr.push(eachUserArr);
	eachUserArr=null;
	</c:forEach>
	if(logUserArr!=null && logUserArr.length>0){
		for(var ii=0;ii<logUserArr.length;ii++){
			$(".site_"+logUserArr[ii][1]+"User_"+logUserArr[ii][0]).html(logUserArr[ii][2]);	
		}
	}
	logUserArr=null;
	var siteArray=new Array();
	var siteInfo=new Array();
	<c:forEach var="siteInfo" items="${siteList}" varStatus="status">
	siteInfo=new Array();
	siteInfo.push("${siteInfo.id}");
	siteInfo.push("${siteInfo.siteSign}");
	siteArray.push(siteInfo);
	siteInfo=null;
	</c:forEach>
	
	
	
	if(siteArray!=null && siteArray.length>0){
		for(var ii=0;ii<siteArray.length;ii++){
			$(".site_sign_"+siteArray[ii][0]).html(siteArray[ii][1]);	
		}
	}
}

initPage();

function btnSearch(){
	resetPageNo();
	$("#logsForm").submit(); 
}

</script>
