<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.conf_list_index.res29']}</title>
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

function viewConf(id) {
	$("<div id=\"viewMeeting\"/>").showDialog({
		"dialogClass": "ui-dialog-smile",
		"iconClass":"icon-info",
		"title" : "${LANG['website.user.index.js.message.title.view.confinfo']}",//"查看会议详情",
		"url" : "/user/conf/adminViewConf/" + id,
		"type" : "viweConf",
		"width" : 688,
		"height" : 509
	});
}

</script>
</head>
<body>
<!--     <div class="header"> -->
<!--     </div> -->
    <div class="content">
    	<form action="/admin/statistical/viewRunningMeeting" method="post">
    	<table class="common-table">
    	<tbody>
			<tr>
				<th style="text-align: center;">${LANG['website.user.conf.list.js.message.confname']}</th>
        		<th style="text-align: center;">${LANG['website.user.conf.report.host']}</th>
        		<th style="text-align: center;">${LANG['website.admin.conf.list.page.title.starttime']}</th>
			</tr> 
			<c:forEach var="conf" items="${pageModel.datas}" varStatus="status">   	
	    		<tr>
					<td class="first-child"><div align="center"><span><a href="javascript:viewConf(${conf.id});">${conf.confName}</a></span></div></td>
					<td><div align="center">${conf.compereName}</div></td>
					<td class="last-child"><div align="center">
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
 