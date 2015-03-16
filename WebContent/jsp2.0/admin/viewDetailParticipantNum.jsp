<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['website.site.admin.chart.participant.statis']}</title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/assets/css/biz.adm.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.adm.index.css" />

<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/users-manage.css" />

<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.widgets.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	var contentHeight = $(".content").height();
	var height = contentHeight+25;
	parent.$("#detailFrame").height(height);
});
</script>
</head>
<body>
<!--     <div class="header"> -->
<!--     	<jsp:include page="header.jsp" /> -->
<!--     </div> -->
    <div class="content">
    	<form id="viewDetailParticipantNum" action="/admin/statistical/viewDetailParticipantNum" method="post">
    	<input type="hidden" id="year" name="year" value="${year }" />
    	<input type="hidden" id="month" name="month" value="${month }" />
    	<input type="hidden" id="date" name="date" value="<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd HH:mm:ss"/>" />
    	<table class="common-table">
    	<tbody>
    		<tr>
        		<th colspan="2" align="left">
        		<span><b>
		     	<c:choose>
		     		<c:when test="${byhour gt 0 }">
		     			<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd HH:mm"/>
		     		</c:when>
		     		<c:when test="${!empty year}">
		     			${year}/${month}
		     		</c:when>
		     		<c:otherwise>
		     			<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd"/>
		     		</c:otherwise>
		     	</c:choose>&nbsp;
		     	${LANG['website.user.conf.report.conf.join.number']}</b></span>
        		</th>
			</tr> 
			<tr> 
        		<th align="center" style="text-align: center;">${LANG['website.user.conf.report.host']}</th>
        		<th align="center" style="text-align: center;">${LANG['website.user.conf.report.conf.join.number']}</th>
			</tr> 
			<c:forEach var="data" items="${pageModel.datas}" varStatus="status">   	
	    		<tr>
					<td class="first-child"><div align="center"><span>${myfn:getUserName(data.hostId)}</span></div></td>
					<td class="last-child"><div align="center">${data.participantsNum}</div></td>
				</tr>
			</c:forEach>
    	</tbody>
    	</table>
    	<div class="pagebar clearfix">
				<jsp:include page="page.jsp" />
		</div>
		</form>
    </div>
<!--     <div class="footer"> -->
<!--         <jsp:include page="footer.jsp" />  -->
<!--     </div>     -->
</body>
</html>
