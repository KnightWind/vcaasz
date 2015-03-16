<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css"/>

<%@ include file="/jsp/common/cookie_util.jsp"%>	
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="/static/js/util.js"></script>


<script type="text/javascript"> 
$(function() {
	$('.attend_user').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 'e', opacity: 1 });
	//for search input style
	$("#confForm").find("input, select").not(".skipThese").uniform();
	
	//table tr background highlight
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	//show or hide search input
	$(".gaoji").toggle(function () {
	    $("#search-condition").slideDown(function() {
		    parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res1']}
	    });
	}, function () {
		$("#search-condition").slideUp(function() {
			parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res2']}
		});
	});
	var lang = getBrowserLang();
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$( ".expireDate" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
	$("#advanceSearch").click(function(){
		var dateStart = $("#effeDateStart").val();
		var dateEnd =  $("#effeDateEnd").val();
		if(dateStart && dateEnd && !compareDate(dateStart, dateEnd)){
			parent.errorDialog("会议开始时间应该小于结束时间");
			return;
		}
		resetPageNo();
    	confForm.action="/admin/conf/listWithCondition";
    	confForm.submit();	
	});
	
	$("#exportConflogs").click(function(){
		var title = $("#title").val();
		$("#title").val("${title}");
		var act = confForm.action;
		confForm.action="/admin/conf/exportConfdetails";
    	$("#baseurl").val(act);
		confForm.submit();
    	confForm.action = act;
    	$("#title").val(title);
	});
	
	$("#toSearch").click(function(){
		resetPageNo();
		confForm.action="/admin/conf/list";
    	confForm.submit();	
	});
});

function checkForm(){
	$("#title").val("${title}");
	var title = $("#title").val();
	if(title != null && title != ""){
		confForm.action="/admin/conf/list";
	}
}

function enterSumbit(url){  
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.admin.conf_list.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.admin.conf_list.res4']}
    	resetPageNo();
    	confForm.action=url;
    	confForm.submit();	
    }   
} 

$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$(".search_user").watermark('${LANG['bizconf.jsp.admin.conf_list.res5']}');
	}
	$("#search-condition").find("input[type=text]").each(function(){
		if($(this).val()){
			$("#search-condition").show();
			return false;
		}
	});
	$("#search-condition").find("select").each(function(){
		if($(this).val()>0){
			$("#search-condition").show();
			return false;
		}		
	});
});
</script>



<title>confInfo</title>
</head>
<body>
<!-- <div class="main_right"> -->
<div class="main_content">
<form id="confForm" name="confForm" action="/admin/conf/listWithCondition" method="post" onsubmit="checkForm();">
<input type="hidden" class="skipThese" name="sortField"   id="sortField" value="${sortField}"/>
<input type="hidden" class="skipThese" name="sortord" id="sortord" value="${sortord}"/>
<input type="hidden" class="skipThese" name="baseurl" id="baseurl"/>
  <div class="m_top1"> 
    <input name="title" id="title" class="search_user" type="text" maxlenght="32" value="${title }"  onkeydown='enterSumbit("/admin/conf/list")'/>
    <input name="" class="ss_button searchs_button skipThese" type="button" id="toSearch" />
    <a class="gaoji" title="${LANG['system.advancedSearch']}" href="javascript:;">${LANG['system.advancedSearch']}</a>
    
    <input name="export" id="exportConflogs" type="button" value="导 出" />
  </div>
 <div class="search-condition" id="search-condition" style="display: none; width:100%; height: auto;">
 <div class="gg_none">
  <table id="table_gaoji" cellpadding="0" cellspacing="0" border="0" >
    <tr class="tr_01">
      <td  width="10px" height="35" ></td>
      <td width="auto" height="35" ><div align="right">${LANG['system.list.meeting.title']}${LANG['bizconf.jsp.admin.conf_list.res6']}</div></td>
      <td  width="auto" height="35" ><div align="left">
     	  <input  style="width:262px;position: relative;" type="text" name="confName" id="siteSign" value="${confName}" onkeydown='enterSumbit("/admin/conf/listWithCondition")'/>
        </div></td>
      <td  width="20px" height="35" ></td>
      <td  width="auto" height="35" ><div align="right">${LANG['system.list.meeting.type']}${LANG['bizconf.jsp.admin.conf_list.res6']}</div></td>
      <td  width="auto" height="35" ><div align="left">
           <select name="confType" id="confType">
					<cc:confList var="CONF_TYPES"/>
			   		<c:forEach var="eachType" items="${CONF_TYPES}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="conf.type.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${confType==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
    		</select>
      </div></td>
    </tr>
    <tr class="tr_02">
      <td  width="10px" height="32" ></td>
      <td  width="auto" height="32" ><div align="right">${LANG['system.list.meeting.start.time']}${LANG['bizconf.jsp.admin.conf_list.res6']}</div></td>
      <td  width="auto" height="32" >
      <div style="position: relative;float: left;">
      	<input style="width:102px;" type="text" name="effeDateStart" id="effeDateStart" value="${effeDateStart}" class="expireDate"/>
      </div>
      <label style="margin-top:4px;margin-left: 18px;margin-right: 18px;float: left;">---</label>
      <div style="position: relative;float: left;">
      	<input style="width:102px;"  type="text" name="effeDateEnd" id="effeDateEnd" value="${effeDateEnd}" class="expireDate"/>
      </div>
      </td>
      <td  width="20px" height="32" ></td>
      <td  width="auto" height="32" ><div align="right">${LANG['system.list.meeting.status']}${LANG['bizconf.jsp.admin.conf_list.res6']}</div></td>
      <td  width="auto" height="32" >
		<select name="confStatus" id="confStatus">
			<cc:confList var="CONF_STATUS"/>
	   		<c:forEach var="eachType" items="${CONF_STATUS}"  varStatus="itemStatus">
	   			<c:set var ="typeName" value="conf.status.${eachType }"/>
	   			<option value="${eachType}" <c:if test="${confStatus==eachType}">selected</c:if>>${LANG[typeName]}</option>
	   		</c:forEach>
   		</select>
      <td border="0" align="right" >
      	<input type="button" id="advanceSearch" value="${LANG['system.search']}" class="skipThese huiyi_inquiry" style="cursor: pointer;"/>
      </td>
  </table>
  </div>	
</div>

<!-- <div id="search-condition" style="display: none; width:100%; height: auto;margin-left: 20px;"> -->
<!--     		<div style="height:30px;clear: left"> -->
<%-- 	    		<label>${LANG['system.list.meeting.title']}: </label> --%>
<%-- 	    		<input type="text" name="confName" id="siteSign" value="${confName}" style="width:262px;" onkeydown='enterSumbit("/admin/conf/listWithCondition")'/> --%>
<!--     		</div> -->
<!--     		<div style="height:30px;clear: left"> -->
<%-- 	    		<label>${LANG['system.list.meeting.type']}: </label> --%>
<!-- 	    		<select name="confType" id="confType"> -->
<%-- 					<cc:confList var="CONF_TYPES"/> --%>
<%-- 			   		<c:forEach var="eachType" items="${CONF_TYPES}"  varStatus="itemStatus"> --%>
<%-- 			   			<c:set var ="typeName" value="conf.type.${eachType }"/> --%>
<%-- 			   			<option value="${eachType}" <c:if test="${confType==eachType}">selected</c:if>>${LANG[typeName]}</option> --%>
<%-- 			   		</c:forEach> --%>
<!-- 	    		</select> -->
<%-- 	    		<label style="margin:4px 11px">${LANG['system.list.meeting.status']}: </label> --%>
<!-- 	    		<select name="confStatus" id="confStatus"> -->
<%-- 					<cc:confList var="CONF_STATUS"/> --%>
<%-- 			   		<c:forEach var="eachType" items="${CONF_STATUS}"  varStatus="itemStatus"> --%>
<%-- 			   			<c:set var ="typeName" value="conf.status.${eachType }"/> --%>
<%-- 			   			<option value="${eachType}" <c:if test="${confStatus==eachType}">selected</c:if>>${LANG[typeName]}</option> --%>
<%-- 			   		</c:forEach> --%>
<!-- 	    		</select> -->
<!--     		</div> -->
<!--     		<div style="height:30px;clear: left"> -->
<%-- 	    		<label>${LANG['system.list.meeting.start.time']}: </label> --%>
<%-- 	    		<input type="text" name="effeDateStart" id="effeDateStart" value="${effeDateStart}" style="width:100px;" class="expireDate"/> --%>
<!-- 	    		<label style="margin-top:4px;margin-left: 15px;margin-right: 18px;">---</label> -->
<%-- 	    		<input type="text" name="effeDateEnd" id="effeDateEnd" value="${effeDateEnd}" style="width:100px;" class="expireDate"/> --%>
<!--     		</div> -->
<!--     		<div style="height:30px;clear: left"> -->
<%--     			<input type="button" id="advanceSearch" class='button-small' value="${LANG['system.search']}"/> --%>
<!--     		</div> -->
<!--     	</div> -->
    	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-right:10px; border:#A3C5DE 1px solid; border-top:none; border-bottom:none;">
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="30%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.list.meeting.title']}</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.list.meeting.host']}</b></span></div></td>
<!--        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.list.meeting.meetingNo']}</span></div></td>-->
        <cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/><cc:sort var="CONFBASE_SORT_ENDTIME"/><cc:sort var="CONFBASE_SORT_CONFTYPE"/>
        <cc:sort var="CONFBASE_SORT_STATUS"/><cc:sort var="CONFBASE_SORT_STARTTIME"/>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${CONFBASE_SORT_CONFTYPE}');" style="cursor: pointer;"><div align="center"><span style="text-decoration: underline;"><b>${LANG['system.list.meeting.type']}&nbsp;</b></span>
	        <c:if test="${CONFBASE_SORT_CONFTYPE!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_CONFTYPE==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_CONFTYPE==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
        </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${CONFBASE_SORT_STATUS}');" style="cursor: pointer;"><div align="center"><span style="text-decoration: underline;"><b>${LANG['system.list.meeting.status']}&nbsp;</b></span>
           <c:if test="${CONFBASE_SORT_STATUS!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	       <c:if test="${CONFBASE_SORT_STATUS==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	       <c:if test="${CONFBASE_SORT_STATUS==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
        </div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${CONFBASE_SORT_STARTTIME}');" style="cursor: pointer;"><div align="center"><span style="text-decoration: underline;"><b>${LANG['system.list.meeting.start.time']}&nbsp;</b></span>
         	<c:if test="${CONFBASE_SORT_STARTTIME!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_STARTTIME==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_STARTTIME==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
       </div></td>
       	<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${CONFBASE_SORT_ENDTIME}');" style="cursor: pointer;"><div align="center"><span style="text-decoration: underline;"><b>${LANG['system.list.meeting.stop.time']}&nbsp;</b></span>
		 	<c:if test="${CONFBASE_SORT_ENDTIME!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_ENDTIME==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${CONFBASE_SORT_ENDTIME==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
       
		</div></td>
<!--        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.list.meeting.stop.time']}&nbsp;</span></div></td>-->
        <td width="8%"  height="38" bgcolor="d3eaef" class="STYLE10" style=" border-right:none;"><div align="center" ><span><b>${LANG['system.list.meeting.license']}</b></span></div></td>
      </tr>
      <c:if test="${fn:length(confList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="8" align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
      <c:if test="${fn:length(confList)>0 }">
      <c:forEach var="confInfo" items="${confList}" varStatus="status">
      <tr class="table001" height="32" >
        <td height="32" class="STYLE19"><div style="cursor: pointer;text-decoration: underline;" onclick="parent.viewConf(${confInfo.id});" align="center">${confInfo.confName }
        <c:if test="${confInfo.cycleId!=null && confInfo.cycleId>0 }"><span id="cycleId_${confInfo.cycleId}">${LANG['bizconf.jsp.admin.conf_list.res7']}</span></c:if>
        </div></td>
        <td height="32" class="STYLE19"><div align="center">${confInfo.compereName}</div></td>
<!--        <td height="32" class="STYLE19"><div align="center">${confInfo.confHwid}</div></td>-->
        <c:set var="typeLang" value="conf.type.list.${confInfo.confType}"/>
        <td height="32" class="STYLE19"><div align="center">${LANG[typeLang]}</div></td>
        <c:set var="statusLang" value="conf.status.${confInfo.confStatus}"/>
        <td height="32" class="STYLE19"><div align="center">${LANG[statusLang]}</div></td>
        <td height="32" class="STYLE19"><div align="center"><fmt:formatDate value="${confInfo.startTime}" pattern="yyyy-MM-dd HH:mm"/>
        </div></td>
        <cc:confList var="CONF_STATUS_OPENING"/>
        <cc:confList var="CONF_STATUS_FINISHED"/>
        <c:choose>
         <c:when test="${CONF_STATUS_OPENING == confInfo.confStatus}">
			<td height="32"><div align="center" class="STYLE21">--</div></td>
	        <td height="32" class="STYLE21" ><div onclick="parent.showConflogs('${confInfo.id}');" class="attend_user" align="center" style="cursor: pointer;" class="attend_user" align="center" title="${LANG['bizconf.jsp.admin.conf_list.res8']}:${confInfo.pcNum}<br/>${LANG['bizconf.jsp.admin.conf_list.res9']}:${confInfo.phoneNum}"><a style="text-decoration: underline;" href="javascript:void(0);">${confInfo.pcNum+confInfo.phoneNum} ${LANG['system.conflist.confdetail.online']}</a></div></td>
      	 </c:when>
         <c:when test="${CONF_STATUS_FINISHED == confInfo.confStatus}">
            <td height="32"><div align="center" class="STYLE21"><fmt:formatDate value="${confInfo.endTime}" pattern="yyyy-MM-dd HH:mm"/></div></td>
	        <td height="32" class="STYLE21" ><div onclick="parent.showConflogs('${confInfo.id}');" class="attend_user" align="center" style="cursor: pointer;" title="${LANG['bizconf.jsp.admin.conf_list.res8']}:${terminalPcs[confInfo.id]}<br/>${LANG['bizconf.jsp.admin.conf_list.res9']}:${terminalPhones[confInfo.id]}"><a style="text-decoration: underline;" href="javascript:void(0);">${allTerminals[confInfo.id]} ${LANG['system.conflist.confdetail.members']}</a></div></td>
      	 </c:when>
       	 <c:otherwise>
            <td height="32"><div align="center" class="STYLE21"><fmt:formatDate value="${confInfo.endTime}" pattern="yyyy-MM-dd HH:mm"/></div></td>
	        <td height="32" class="STYLE21" ><div  class="attend_user" align="center"  title="${LANG['bizconf.jsp.admin.conf_list.res8']}:${terminalPcs[confInfo.id]}<br/>${LANG['bizconf.jsp.admin.conf_list.res9']}:${terminalPhones[confInfo.id]}"><a style="text-decoration: underline;" href="javascript:void(0);">${allTerminals[confInfo.id]} ${LANG['system.conflist.confdetail.members']}</a></div></td>
       	 </c:otherwise>
		</c:choose>
<%--     martin modify here   <td height="32" class="STYLE21" ><div class="attend_user" align="center" title="PC:${terminalPcs[confInfo.id]}<br/>phonecall:${terminalPhones[confInfo.id]}">${allTerminals[confInfo.id]}</div></td>--%>
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
</div>
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
</script>
