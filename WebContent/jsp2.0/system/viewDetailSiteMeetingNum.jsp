<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<!--[if lte IE 6]>  
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></SCRIPT>  
<![endif]-->
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<title>正在召开的会议</title>
</head>
<body style="min-width:1002px;">
<form id="query" name="query" action="/system/statistical/viewDetailMeetingNum" method="post">
<div class="">
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
     	<td width="100%" colspan="3" height="38" bgcolor="d3eaef"><div align="left" style="padding: 10px;">
     	<span><b>
     	<c:choose>
     		<c:when test="${!empty year}">
     			${year }/${month}
     		</c:when>
     		<c:otherwise>
     			<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd"/>
     		</c:otherwise>
     	</c:choose>
     	召开过的会场数</b></span></div></td>
     </tr>
     <tr class="table003" height="38" >
        <td width="30%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>企业名称</b></span></div></td>
        <td width="32%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>企业标志</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>会场数量</b></span></div></td>
     </tr>
      <c:if test="${fn:length(siteMeetingNums)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        		${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(siteMeetingNums)>0 }">
     <c:forEach var="siteMeetingNum" items = "${siteMeetingNums}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19"><div align="center"><a href="javascript:parent.viewSite(${siteMeetingNum.siteId});" >${myfn:getSiteName(siteMeetingNum.siteId) }</div></td>
        <td height="32" class="STYLE19"><div align="center">${myfn:getSiteSign(siteMeetingNum.siteId) } </td>
        <td height="32" class="STYLE19"><div align="center">${siteMeetingNum.meetingNum }</div></td>
      </tr>
     </c:forEach>
     </c:if>
    </table>
  </tr>
</table>

</div>
</form>
</body>
</html>
