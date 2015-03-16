<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/huiyixinxi.css"/>

<%@ include file="/jsp/common/cookie_util.jsp"%>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="/static/js/util.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>

<script type="text/javascript"> 
$(function() {
	
	//for search input style
	$("#logsForm").find("input, select").not(".skipThese").uniform();
	
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
		var dateStart = $("#loginTimeBegin").val();
		var dateEnd =  $("#loginTimeEnd").val();
		if(dateStart && dateEnd && !compareDate(dateStart, dateEnd)){
			parent.errorDialog("开始时间应该小于结束时间");
			return;
		}
		resetPageNo();
		logsForm.action="/system/loginLog/getLogListAdvance";
		logsForm.submit();	
	});

	$("#toSearch").click(function(){
		resetPageNo();
		logsForm.action="/system/loginLog/getLogList";
		logsForm.submit();	
	});
});

$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$("#loginName").watermark("${LANG['system.sysUser.loginName.input']}");
	}
	$("#search-condition").find("input[type=text]").each(function(){
		if($(this).val()){
			$("#search-condition").show();
			return false;
		}
	});
	$("#search-condition").find("select").each(function(){
		if($(this).val()>0 && $(this).val()<99){
			$("#search-condition").show();
			return false;
		}		
	});
});

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
<form id="logsForm" name="logsForm" action="/system/loginLog/getLogListAdvance" method="post">
	<input class="skipThese" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
	<input class="skipThese" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<%--<input type="hidden" name=""--%>
 <div class="m_top">
	 <input name="loginName" id="loginName" class="search_input" type="text" value="${loginName }" onkeydown='enterSumbit("/system/loginLog/getLogList")'/>
	 <input name="" class="searchs_button skipThese" type="button" id="toSearch" />
	  <a class="gaoji" title="${LANG['system.advancedSearch']}" href="javascript:;">${LANG['system.advancedSearch']}</a>
 </div>   	
<div id="search-condition" style="display: none; width:100%; height: auto;margin-left: 20px;">
    		<div style="height:30px;clear: left">
	    		<label>${LANG['bizconf.jsp.system.site.name']}: </label>
	    		<input type="text" name="siteName" id="siteName" value="${siteName}" style="width:262px;" onkeydown='enterSumbit("/system/loginLog/getLogListAdvance")'/>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['bizconf.jsp.system.userType']}: </label>
	    		<select name="userType" id="userType">
					<cc:loginLog var="USER_TYPES"/>
			   		<c:forEach var="eachType" items="${USER_TYPES}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="bizconf.jsp.system.userType.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${userType==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
	    		</select>
	    		<label style="margin:4px 11px">${LANG['system.eventlog.title.status']}: </label>
	    		<select name="loginStatus" id="loginStatus">
					<cc:loginLog var="LOGIN_STATUS"/>
			   		<c:forEach var="eachType" items="${LOGIN_STATUS}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="bizconf.jsp.system.loginStatus.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${loginStatus==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
	    		</select>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['bizconf.jsp.system.loginTime']}: </label>
	    		<input type="text" name="loginTimeBegin" id="loginTimeBegin" value="${loginTimeBegin}" style="width:100px;" class="expireDate"/>
	    		<label style="margin-top:4px;margin-left: 15px;margin-right: 18px;">---</label>
	    		<input type="text" name="loginTimeEnd" id="loginTimeEnd" value="${loginTimeEnd}" style="width:100px;" class="expireDate"/>
    		</div>
    		<div style="height:30px;clear: left">
    			<input type="button" id="advanceSearch" class='button-small skipThese' value="${LANG['system.search']}"/>
    		</div>
    	</div>
    	<div id="search-condition" style="display: none; width:100%; height: 300px;">
    		${LANG['bizconf.jsp.system.eventlog_list.res1']}
    	</div>
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#D6D6D6 1px solid; border-top:none; border-bottom:none;">
  <cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/>
   <cc:sort var="LOGIN_LOG_SORT_LOGINSTATUS"/> 
   <cc:sort var="LOGIN_LOG_SORT_LOGINNAME"/> 
   <cc:sort var="LOGIN_LOG_SORT_SITEID"/> 
   <cc:sort var="LOGIN_LOG_SORT_USERTYPE"/> 
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
        <td width="5%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${LOGIN_LOG_SORT_LOGINSTATUS}');" style="cursor: pointer;">
        <div align="center"><span style="text-decoration: underline;"><b>${LANG['system.eventlog.title.status']}</b></span>
            <c:if test="${LOGIN_LOG_SORT_LOGINSTATUS!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${LOGIN_LOG_SORT_LOGINSTATUS==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${LOGIN_LOG_SORT_LOGINSTATUS==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
        </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10" onclick="javascript:sort('${LOGIN_LOG_SORT_LOGINNAME}');" style="cursor: pointer;">
        <div align="center"><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.login.res1']}</b></span>
        	<c:if test="${LOGIN_LOG_SORT_LOGINNAME!=sortField}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a></c:if>
	        <c:if test="${LOGIN_LOG_SORT_LOGINNAME==sortField && SORT_ASC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a></c:if>
	        <c:if test="${LOGIN_LOG_SORT_LOGINNAME==sortField  && SORT_DESC==sortord}"><a class="paixu01" href="#"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a></c:if>
        </div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"  ><div align="center"><span><b>${LANG['bizconf.jsp.system.loginTime']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"  style="cursor: pointer;" onclick="javascript:sort('${LOGIN_LOG_SORT_SITEID}');">
        <div align="center"><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.system.site.name']}</b></span>
	        <c:if test="${LOGIN_LOG_SORT_SITEID!=sortField}"> <img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></c:if>
	        <c:if test="${LOGIN_LOG_SORT_SITEID==sortField && SORT_ASC==sortord}"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></c:if>
	        <c:if test="${LOGIN_LOG_SORT_SITEID==sortField  && SORT_DESC==sortord}"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></c:if>
        </div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"  style="cursor: pointer;" onclick="javascript:sort('${LOGIN_LOG_SORT_USERTYPE}');">
        <div align="center"><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.system.userType']}</b></span>
	        <c:if test="${LOGIN_LOG_SORT_USERTYPE!=sortField}"> <img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></c:if>
	        <c:if test="${LOGIN_LOG_SORT_USERTYPE==sortField && SORT_ASC==sortord}"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></c:if>
	        <c:if test="${LOGIN_LOG_SORT_USERTYPE==sortField  && SORT_DESC==sortord}"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></c:if>
        </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.login.ip']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.add_group.res4']}</b></span></div></td>
      </tr>
      	<cc:logs var="LOGINLOG_SECCEED"></cc:logs>
       <c:if test="${fn:length(loginLogList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="7"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
      <c:if test="${fn:length(loginLogList)>0 }">
     	<c:forEach var= "loginLog" items = "${loginLogList}"  varStatus="status">
       <tr>
        <td height="32"  class="STYLE19">
        	<div align="center" ><span>
        	<c:if test="${loginLog.loginStatus==LOGINLOG_SECCEED}">${LANG['bizconf.jsp.system.loginStatus.1']}</c:if>
        	<c:if test="${loginLog.loginStatus!=LOGINLOG_SECCEED}">${LANG['bizconf.jsp.system.loginStatus.2']}</c:if>
        	</span></div>
        </td>
        <td height="32" class="STYLE19"><div align="center">${loginLog.loginName}</div></td>
        <td height="32" class="STYLE19" ><div align="center">
        	<fmt:formatDate  value="${loginLog.loginTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></div>
        </td>
        <c:set var="siteName" value="${siteNameMap[loginLog.siteId] }"></c:set>
        <td height="32" class="STYLE19"><div align="center"><a onclick="viewSite(${loginLog.siteId})" href="javascript:;">${siteName}</a></div></td>
        <c:set var ="userType" value="bizconf.jsp.system.userType.${loginLog.userType}"/>
        <td height="32" class="STYLE19"><div align="center">${LANG[userType]}</div></td>
        <td height="32" class="STYLE19"><div align="center">${loginLog.loginIp}</div></td>
        <c:set var="loginResult" value="system.login.log.error.${loginLog.loginStatus}"></c:set>
        <td height="32" class="STYLE19"><div align="center">${LANG[loginResult]}</div></td>
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
		var logSize="${fn:length(loginLogList)}";
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
