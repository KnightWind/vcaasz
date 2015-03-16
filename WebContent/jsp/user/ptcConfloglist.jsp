<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/page.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
<style>
.tr_center {
	border:#D2D8DB 1px solid;
	border-right:none;
}
.tr_main {
	border-bottom:#D2D8DB 1px solid;
	color:#666666;
}
.tr_top {
	border:#D2D8DB 1px solid;
	border-bottom:none;
	margin-top:26px;
}
.tr_bottom {
	border:#D2D8DB 1px solid;
	border-top:none
}
</style>

<%@ include file="/jsp/common/cookie_util.jsp"%>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js?ver=${version}"></SCRIPT>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchbtn").click(function(){
		$("#pageNo").val("");
		$("#query").submit();
	});
});
</script>
</head>
<body>
<div class="main_content">
<form id="query" name="query" action="/user/conflog/list" method="post">
<input type="hidden" name="isCreator" value="false" />
<div class="intercalate_main_top">
	<h3>${LANG['bizconf.jsp.hostConfloglist.res2']}</h3>
    <p>${LANG['bizconf.jsp.ptcConfloglist.res1']}</p>
</div> 
<div class="meeting_main" style=" margin-top:15px;" >
    <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
    	<tr>
        <td height="40" colspan="7" bgcolor="#EAF4FC" class="tr_top">
        	<table style="width: 100%">
        		<tr>
        			<td width="100" align="right">${LANG['system.list.meeting.title']}：</td>
        			<td width="140"><input type="text" class="t01" name="theme" value="${theme }" /></td>
        			<td width="100" align="right">${LANG['bizconf.jsp.user.conf.starttime']}：</td>
        			<td width="265">
        				<input id="confStart" name="startTime" value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>" type="text" readonly="readonly" style="width:100px;border: 1px solid #A8A8A8;height: 22px;"/>
				        <span> — </span>
				        <input id="confEnd" name="endTime" value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>" type="text" readonly="readonly" style="width:100px;border: 1px solid #A8A8A8;height: 22px;" />
        			</td>
        			<td width="80">
        				<span style="margin-right: 5px;_margin-top:5px;" class="button_common">
        				<a href="javascript:;" id="searchbtn">
        				<img width="16" align="absmiddle" height="16" src="/static/images/find.png" style=" margin-left:5px; margin-right:5px;">${LANG['system.search']}</a>
        				</span>
        			</td>
        			<td>
        				<span style="margin-right: 5px;_margin-top:5px;float: right;" class="button_common">
        				<a href="javascript:window.print();">
        				<img width="16" align="absmiddle" height="16" src="/static/images/print.png" style=" margin-left:5px; margin-right:5px;">${LANG['system.print']}</a>
        				</span>
        			</td>
        		</tr>
        	</table>
        </td>
      </tr>
    
      <tr align="center" height="35" class="tr_center" bgcolor="#000066">
        <td width="20%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res3']}</td>
        <td width="6%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res4']}</td>
        <td width="8%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res5']}</td>
        <td width="14%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res6']}</td>
        <td width="14%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res7']}</td>
        <td width="14%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res8']}</td>
        <td  width="14%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attendConfloglist.res9']}</td>
      </tr>
    <c:if test="${fn:length(pageModel.datas)<=0}">
		<tr class="table001" height="32"  >
			<td class="STYLE19" height="32" colspan="7" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
		</tr>
	</c:if>
	<c:forEach var="conf" items="${pageModel.datas}" varStatus="status">
      <tr align="center" bgcolor="#FFFFFF" height="32">
        <td class="tr_main" style=" border-left:#D2D8DB 1px solid">${conf.confName}</td>
        <td class="tr_main">${numMap[conf.id]}</td>
        <td class="tr_main">${conf.compereName}</td>
        <td class="tr_main">${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.startTime,currUser.timeZone)}</td>
        <td class="tr_main">${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.endTime,currUser.timeZone)} </td>
        
        <td class="tr_main">${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].joinTime,currUser.timeZone)}</td>
        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
        	${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].exitTime,currUser.timeZone)}
        </td>
      </tr>
     </c:forEach>
      <tr>
        <td height="35" colspan="7" class="tr_bottom">
           <jsp:include page="/jsp/common/page_info.jsp" />
        </td>
      </tr>
    </table>
  </div>
</form>
</div>
<script type="text/javascript">
jQuery(function($) {
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	
	$( "#confStart, #confEnd" ).datepicker({
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/new03.png",
		buttonImageOnly: true,
		onClose: function() {

		}
	});	
});
</script>
</body>
</html>
