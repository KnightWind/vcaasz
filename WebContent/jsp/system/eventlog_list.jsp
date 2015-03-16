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
	$("#logsForm").find("input, select").not(".skipThese").uniform();
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

function viewDetails(id) {
	if(id != 0){
		parent.viewSysSiteDetails(id);
	}
}
function viewSite(id) {
	if(id != 0){
		parent.viewSite(id);
	}
}

function enterSumbit(url){  
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.admin.conf_list.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.admin.conf_list.res4']}
    	resetPageNo();
    	logsForm.action=url;
    	logsForm.submit();	
    }   
} 
</script>
<title>site info</title>
</head>
<body>

<div class="main_right">
<form id="logsForm" name="logsForm" action="/system/logs/syslist" method="post">
	<input class="skipThese" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
	<input class="skipThese" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<%--<input type="hidden" name=""--%>
 <div class="m_top">
    <div class="text_search">
    	<div style="float: left;">
    	   		<select name="logType" id="logType" onchange="javascript:btnSearch();">
		   		<cc:logs var="SYSTEM_EVENTLOG_TOTAL"/>
		   		<cc:logs var="SYSTEM_EVENTLOG_COMMON"/>
		   		<cc:logs var="SYSTEM_SITE_UPDATE"/>
		   		<cc:logs var="SYSTEM_SITE_CREATE"/>
		   		<c:if test="${isSuperSystemAdmin}">
		   			<c:forEach var="eachType" items="${SYSTEM_EVENTLOG_TOTAL}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="system.eventlog.type.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
		   			</c:forEach>
		   		</c:if>
		   		<c:if test="${!isSuperSystemAdmin}">
		   			<c:forEach var="eachType" items="${SYSTEM_EVENTLOG_COMMON}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="system.eventlog.type.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
		   			</c:forEach>
		   		</c:if>
		    	</select>
    	</div>

    	<c:if test="${isSuperSystemAdmin}">
    	<input class="" type="text" name="userName" id="userName" value="${userName}" onkeydown='enterSumbit("/system/logs/syslist")'  style="width: 160px;float:left;text-indent:1em;height: 17px;margin-left: -5px;"/>
    	<input type="button" name="search" id="search" class="searchs_button skipThese"/>
		</c:if>
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
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${EVENTLOG_SORT_STATUS}');" style="cursor: pointer;"><div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.status"]}</b></span>
           <c:if test="${EVENTLOG_SORT_STATUS!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_STATUS==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${EVENTLOG_SORT_STATUS==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
       </div></td>
        <td width="22%" height="38" bgcolor="d3eaef" class="STYLE10"  style="cursor: pointer;" onclick="javascript:sort('${EVENTLOG_SORT_CREATETIME}');"><div align="center"><span style="text-decoration: underline;"><b>${LANG["system.eventlog.title.logtime"]}</b></span>
	        <c:if test="${EVENTLOG_SORT_CREATETIME!=sortField}"> <img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></c:if>
	        <c:if test="${EVENTLOG_SORT_CREATETIME==sortField && SORT_ASC==sortord}"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></c:if>
	        <c:if test="${EVENTLOG_SORT_CREATETIME==sortField  && SORT_DESC==sortord}"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></c:if>
        </div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"  >
        <div align="center"><span><b>${LANG["system.eventlog.title.option.module"]}&nbsp;</b></span>
         </div></td>
        <td width="14%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.user"]}</b></span></div></td>
        <td width="14%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.object"]}</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["system.eventlog.title.option.ip"]}</b></span></div></td>
        <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG["event.log.detail"]}</b></span></div></td>
      </tr>
      	<cc:logs var="EVENTLOG_SECCEED"></cc:logs>
      	<cc:logs var="EVENTLOG_FAIL"></cc:logs>
       <c:if test="${fn:length(logList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="7"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
      <c:if test="${fn:length(logList)>0 }">
     	<c:forEach var= "eventLog" items = "${logList}"  varStatus="status">
       <tr>
        <td height="32"  class="STYLE19">
        	<div align="center" ><span>
        	<c:if test="${eventLog.logStatus==EVENTLOG_SECCEED}">${LANG['website.message.succeed']}</c:if>
        	<c:if test="${eventLog.logStatus==EVENTLOG_FAIL}">${LANG['website.message.fail']}</c:if>
        	</span></div>
        </td>
        <td height="32" class="STYLE19" ><div align="center">
        <fmt:formatDate  value="${eventLog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div></td>
        <c:set var ="typeName" value="system.eventlog.type.${eventLog.funcModule}"/>
        <td height="32" class="STYLE19"><div align="center">${LANG[typeName]}</div></td>
        <td height="32" class="STYLE19"><div align="center" id="userId_${status.index}" userId="${eventLog.createUser}"></div></td>
<!--        <c:set var="eventUser" value="${operatorObjectList[eventLog.userId]}"/>-->
        <c:set var="eventUser" value="${eventLog.optName}"/>
        <c:if test="${not empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">
      		<c:choose>
	      		<c:when test="${SYSTEM_SITE_UPDATE == eventLog.funcModule || SYSTEM_SITE_CREATE == eventLog.funcModule}">
	      			<a onclick="viewSite(${eventLog.optId})" href="javascript:;">${eventUser }</a>
	      		</c:when>
	      		<c:otherwise>
	      			${eventUser }
	      		</c:otherwise>
      		</c:choose>
      		</div></td>
        </c:if>
        <c:if test="${empty eventUser }">
      		<td height="32" class="STYLE19"><div align="center">- -</div></td>
        </c:if>
        <td height="32" class="STYLE19"><div align="center">${eventLog.createIp}</div></td>
        <td height="32" class="STYLE19">
        <c:if test="${SYSTEM_SITE_UPDATE == eventLog.funcModule}">
        	<div align="center"><a onclick="viewDetails(${eventLog.id})" href="javascript:;">${LANG["event.log.detail.view"]}</a></div>
        </c:if>
        <c:if test="${SYSTEM_SITE_UPDATE != eventLog.funcModule}">
        	<div align="center" style="opacity:0.5;filter:alpha(opacity=10);">${LANG["event.log.detail.view"]}</div>
        </c:if>
        </td>
      </tr>
      </c:forEach>
      </c:if>
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

<script type="text/Javascript">
	function initPage(){
		var sysUserArr=new Array();
		var eachUser=new Array();
		<c:forEach var="sysUser" items = "${sysUserList}" varStatus="status">
		eachUser=new Array();
		eachUser.push("${sysUser.id}");
		eachUser.push("${sysUser.trueName}");
		sysUserArr.push(eachUser);
		</c:forEach>
		var logSize="${fn:length(logList)}";
		var eachUserId=0;
		for(var ii=0;ii<logSize;ii++){
			eachUserId=$("#userId_"+ii).attr("userId");
			if(eachUserId>0 && sysUserArr!=null && sysUserArr.length>0){
				for(var jj=0;jj<sysUserArr.length;jj++){
					if(sysUserArr[jj][0]==eachUserId){
						$("#userId_"+ii).html(sysUserArr[jj][1]);
						break;
					}
				}
			}
		}
	}
	initPage();
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
