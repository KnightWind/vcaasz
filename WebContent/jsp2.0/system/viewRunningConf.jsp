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
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<script type="text/javascript"> 
	//查看会议详情
	function viewConf(id) {
		$("<div id=\"viewMeeting\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.index.res14']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/user/conf/view4sys/" + id,
			"type" : VIEW_TYPE.bookMeeting,
			"action" : ACTION.view,
			"width" : 684,
			"height" : 409
		});
	}
	
	function viewSite(id) {
		$("<div id=\"siteDiv\"/>").showDialog({
			"title" : "${LANG['system.site.meaasge.lookup']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/site/view/" + id,
			"type" : VIEW_TYPE.site,
			"action" : ACTION.view,
			"width" : 420,
			"height" : 460
		});
	}

</script>
<title>正在召开的会议</title>
</head>
<body style="min-width:1002px;">
<jsp:include page="header.jsp" />

<form id="query" name="query" action="/system/statistical/viewRunningMeeting" method="post">
<div class="">
  <div class="m_top">
  </div>
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>会议主题</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>主持人</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>企业名称</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>企业标识</b></span></div></td>
        <td width="13%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>会议开始时间</b></span></div></td>
     </tr>
      <c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        		${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(pageModel.datas)>0 }">
     <c:forEach var="conf" items = "${pageModel.datas}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19">
        	<div align="center"><span><a href="javascript:viewConf(${conf.id});">${conf.confName}</a></span></div>
        </td>
        <td height="32" class="STYLE19"><div align="center">${conf.compereName}</div></td>
        <td height="32" class="STYLE19"><div align="center"><a href="javascript:viewSite(${conf.siteId});" >${myfn:getSiteName(conf.siteId) }</a></div></td>
        <td height="32" class="STYLE19">
	        <div align="center">
	        	${myfn:getSiteSign(conf.siteId)}
	        </div> 
        </td>
        <td height="32" class="STYLE19"><div align="center">
        <c:choose>
        	<c:when test="${empty conf.actulStartTime }">
        		 ${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.startTime,conf.timeZone) }
        	</c:when>
        	<c:otherwise>
        		 ${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.actulStartTime,conf.timeZone) }
        	</c:otherwise>
        </c:choose>
        </div></td>
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
</body>
</html>
