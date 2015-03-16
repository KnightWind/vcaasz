<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.search.css">

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js"></script>
<script type="text/javascript"> 
$(function() {
	if (!$.browser.msie || $.browser.version>7) {
		$("#operator").watermark("${LANG['bizconf.jsp.admin.site_eventlog_list.res1']}");
	}
	$("#logsForm").find("input, select").not(".skipThese").uniform();
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
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
		parent.viewUserConfDetails(id);
	}
}
</script>
<title>site info</title>
</head>
<body>
<div class="main_content">
<form id="logsForm" name="logsForm" action="/admin/siteUserLogs/list" method="post">
 <div class="m_top1"> 
	<input class="skipThese" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
	<input class="skipThese" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<div style="float: left;">
   		<select name="logType" id="logType" onchange="javascript:btnSearch();">
	   		<cc:logs var="SITEUSER_EVENTLOG_TOTAL"/>
	   		<c:forEach var="eachType" items="${SITEUSER_EVENTLOG_TOTAL}"  varStatus="itemStatus">
	   			<c:set var ="typeName" value="siteAdmin.eventlog.type.${eachType }"/>
	   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
	   		</c:forEach>
    	</select>	
	</div>
<%-- 	<c:if test="${isSuperSiteAdmin}"> --%>
		<input class="" type="text" name="operator" id="operator" value="${operator}" onkeydown='enterSumbit("/admin/siteUserLogs/list")' style="width: 160px;float:left;text-indent:1em;height: 17px;margin-left: -5px;"/>
	   	<input type="button" name="a_ss_button" id="search" class="searchs_button skipThese"/>
<%--    	</c:if>  --%>
 </div>
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" >
   <cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/>
   <cc:sort var="EVENTLOG_SORT_DEFAULT"/><cc:sort var="EVENTLOG_SORT_STATUS"/><cc:sort var="EVENTLOG_SORT_CREATETIME"/> 
   <tr class="table003" height="38"  >
   	  <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"  style="cursor: pointer;" onclick="javascript:sort('${EVENTLOG_SORT_STATUS}');">
   	  <div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.status"]}</b></span>
   	  	  <c:if test="${EVENTLOG_SORT_STATUS!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	      <c:if test="${EVENTLOG_SORT_STATUS==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	      <c:if test="${EVENTLOG_SORT_STATUS==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
   	  </div>
   	  </td>
	  <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"  style="cursor: pointer;" onclick="javascript:sort('${EVENTLOG_SORT_CREATETIME}');">
	  <div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.logtime"]}</b></span>
	  	  <c:if test="${EVENTLOG_SORT_CREATETIME!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	      <c:if test="${EVENTLOG_SORT_CREATETIME==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	      <c:if test="${EVENTLOG_SORT_CREATETIME==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
	  </div>
	  </td>
      <td width="15%"  height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.module"]}&nbsp;</b></span></div></td>
      <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.list.meeting.title"]}</b></span></div></td>
      <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.list.meeting.type"]}</b></span></div></td>
      <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.user"]}</b></span></div></td>
      <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.object"]}&nbsp;</b></span></div></td>
      <td width="10%"  height="38" bgcolor="d3eaef" class="STYLE10" style=" border-right:none;"><div align="center" ><span><b>${LANG["system.eventlog.title.option.ip"]}</b></span></div></td>
      <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["event.log.detail"]}</b></span></div></td>
  </tr>
        <cc:logs var="EVENTLOG_SECCEED"></cc:logs>
      	<cc:logs var="EVENTLOG_FAIL"></cc:logs>
      	<cc:logs var="SITEUSER_CONF_UPDATE"></cc:logs>
      	<c:if test="${fn:length(logList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="9"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     	<c:forEach var= "eventLog" items = "${logList}"  varStatus="status">
      <tr class="table001" height="32">
      	<td height="32">
      	<div align="center"><span>
      		<c:if test="${eventLog.logStatus==EVENTLOG_SECCEED}">${LANG['website.message.succeed']}</c:if>
        	<c:if test="${eventLog.logStatus==EVENTLOG_FAIL}">${LANG['website.message.fail']}</c:if>
      	</span></div>
      	</td>
      	<td height="32">
      	<div align="center"><span>
      		<fmt:formatDate  value="${eventLog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
      	</span></div>
      	</td>
        <c:set var ="typeName" value="siteAdmin.eventlog.type.${eventLog.funcModule}"/>
        <td height="32"><div align="center">${LANG[typeName]}</div></td>
        <c:set var="confName" value="${confNameMap[eventLog.confId]}"/>
        <c:set var="confFunc" value="${confFuncMap[eventLog.confId]}"/>
        <c:if test="${not empty confName }">
       		<td height="32"><div align="center">${ confName}</div></td>
        </c:if>
        <c:if test="${empty confName }">
        	<td height="32"><div align="center">- -</div></td>
        </c:if>
        <c:if test="${not empty confFunc }">
       		<td height="32"><div align="center">${ confFunc}</div></td>
        </c:if>
        <c:if test="${empty confFunc }">
        	<td height="32"><div align="center">- -</div></td>
        </c:if>
        <td height="32"><div align="center">${operatorList[status.count-1]}</div></td>
<!--        <td height="32"><div align="center">${operatorObjectList[status.count-1]}</div></td>-->
        <c:set var="eventUser" value="${eventLog.optName}"/>
        <c:if test="${not empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">${eventUser }</div></td>
        </c:if>
        <c:if test="${empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">- -</div></td>
        </c:if>
        <td height="32"><div align="center">${eventLog.createIp}</div></td>
        <td height="32" class="STYLE19">
          <c:if test="${SITEUSER_CONF_UPDATE == eventLog.funcModule}">
        	<div align="center"><a onclick="viewDetails(${eventLog.id})" href="javascript:;" style="color:#2B67AA;">${LANG["event.log.detail.view"]}</a></div>
          </c:if>
          <c:if test="${SITEUSER_CONF_UPDATE != eventLog.funcModule}">
        	<div align="center" style="opacity:0.5;filter:alpha(opacity=10);">${LANG["event.log.detail.view"]}</div>
          </c:if>
        </td>
      </tr>
      </c:forEach>
	   <tr>
	    <td class="table_bottom" height="38" colspan="9">
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

function btnSearch(){
	resetPageNo();
	$("#logsForm").submit(); 
}

</script>
