<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.favor_setup.res1']}</title>


<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css?ver=${version}">

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
<cc:siteList var="USER_FAVOR_LANGUAGE"/>
<c:set var="TIMEZONE_WITH_CITY_LIST" value="${TIMEZONE_WITH_CITY_LIST }"></c:set>
<script type="text/javascript">
function saveInfo() {
	document.getElementsByName("timeForm")[0].submit();	
}
function loaded() {
	var frame = parent.$("#updateTimeZone");
	frame.trigger("loaded");
}
function closeDialog() {
	var frame = parent.$("#updateTimeZone");
	frame.trigger("closeDialog");
}
</script>
<script type="text/javascript">
		$(function() {
			$("#timeZoneId").val("${user.timeZoneId}");
			$("#timeForm").find("select").not(".skipThese").uniform();
		});
	</script>	
</head>
<body onload="loaded()">
<div class="main_content">
<form name="timeForm" id="timeForm" action="/user/favor/updateFavor" method="post" style="background: #FFF">
  <div class="intercalate_main_top">
    <h3>${LANG['bizconf.jsp.favor_setup.res2']}</h3>
    <p>${LANG['bizconf.jsp.favor_setup.res3']}</p>
  </div>
  
  <table class="Personal_settings_main" cellpadding="0" cellspacing="0" border="0" >
    <tr height="40">
      <td align="right" width="160">${LANG['bizconf.jsp.favor_setup.res4']}</td>
      <td align="left" class="person_settings_td">
        <select id="timeZoneId" name="timeZoneId" class="s01">
         <c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}">
			<c:set var="eachLang" value="website.timezone.city.zone.${eachTimeZone.timeZoneId}"/>
			<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId == user.timeZoneId }">selected="selected"</c:if> >${LANG[eachLang]} </option>
		 </c:forEach>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right" width="160">${LANG['bizconf.jsp.favor_setup.res5']}</td>
      <td align="left" class="person_settings_td">
        <select id="favorLanguage" name="favorLanguage" class="s02">
          <c:forEach var="eachLanguage" items="${USER_FAVOR_LANGUAGE}">
				<c:set var="language" value="system.language.${eachLanguage}"/>
				<option value="${eachLanguage}" <c:if test="${eachLanguage == user.favorLanguage }">selected="selected"</c:if> >${LANG[language]}</option>
		  </c:forEach>
          
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right" width="160">${LANG['user.favor.page.size']}</td>
      <td align="left" class="person_settings_td">
        <select id="pageSize" name="pageSize" class="s02">
	         <c:forEach var="eachPageSize" items="${USER_FAVOR_PAGE_SIZE}">
				<c:set var="eachPage" value="${eachPageSize}"/>
				<option value="${eachPageSize}" <c:if test="${eachPageSize == user.pageSize }">selected="selected"</c:if> >${eachPageSize} ${LANG['user.favor.page.records']}</option>
			 </c:forEach>
        </select>
        ${LANG['user.favor.page.size.info']}
      </td>
       <td>
		      	<div style="margin-left: 10px;">
			      	<label class='red_star'> * ${LANG['user.favor.page.default.records']}</label>
		      	</div>
		      </td>
    </tr>    
    <tr height="40">
      <td align="right" width="160"></td>
      <td align="left" class="person_settings_td">
        	<span style="color:#ec6e00">${LANG['bizconf.jsp.favor_setup.res6']}:${LANG['bizconf.jsp.favor_setup.res7']}</span>
      </td>
    </tr>
    <tr>
      <td></td>
      <td>
      <div style="margin-top: 20px;">
      <a class="Personal_settings_main_hold" style="float:left;margin-top:0px;" href="javascript:;" onclick="saveInfo()">
          <img src="/static/images/ys_r_bg.png" width="16" height="14" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.conf_default_setup.res45']}</a>
	      <c:if test="${!empty infoMessage}">
				<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
		  </c:if>
		  <c:if test="${!empty errorMessage}">
	       		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
	      </c:if>
      </div>
  	  </td>
      
      <td></td>
    </tr>
  </table>
</form>
</body>
</html>
