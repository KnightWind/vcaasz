<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>confInfo</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.meeting-info.css" />
<cc:logs var="EVENTLOG_SECCEED"></cc:logs>
<cc:logs var="EVENTLOG_FAIL"></cc:logs>
<cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/>
<cc:logs var="SITEUSER_CONF_UPDATE"></cc:logs>
<cc:sort var="EVENTLOG_SORT_DEFAULT"/>
<cc:sort var="EVENTLOG_SORT_STATUS"/>
<cc:sort var="EVENTLOG_SORT_CREATETIME"/>
</head>
<body>
<form id="logsForm" name="logsForm" action="/admin/siteUserLogs/userLoginList" method="post">
<input class="" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
<input class="" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<div class="box">
		<div class="head">
			<span class="title">${LANG['bizconf.jsp.system.user.login.log'] }</span>
		</div>
		<div class="body">
			<div class="summary">
				<div class="search" style="float: left;*padding-top:10px;">
					<cc:logs var="SITEUSER_EVENTLOG_TOTAL"/>
					<input id="operator-search-name" name="nameOrEmail"  value="${nameOrEmail}" type="text" class="input-text" onkeydown='enterSumbit("/admin/siteUserLogs/userLoginList")' placeholder="${LANG['website.admin.userlog.page.search.optionor.tip'] } / ${LANG['bizconf.jsp.admin.arrange_org_user.res9'] }" />
					<button class="submit-search" type="button" onclick="btnSearch()">${LANG['website.common.search.name'] }</button>
				</div>
			</div>
			<c:if test="${fn:length(pageModel.datas)>0}">
			<div class="infomation">
				<table class="common-table">
					<tbody>
						<tr>
							<th class="" width="15%">${LANG['website.admin.userlog.page.title.logtime'] }</th>
					    	<th class="" width="20%">
								<div class="sort-th" onclick="sort('${EVENTLOG_SORT_CREATETIME}')">
									${LANG['website.admin.userlog.page.title.optionor'] }
									<c:if test="${EVENTLOG_SORT_CREATETIME!=sortField}"><img src="/static/images/paixu_button.png" style="vertical-align: middle;"/></c:if>
									<c:if test="${EVENTLOG_SORT_CREATETIME==sortField && SORT_ASC==sortord}"><img src="/static/images/paixu02.png" style="vertical-align: middle;"/></c:if>
									<c:if test="${EVENTLOG_SORT_CREATETIME==sortField  && SORT_DESC==sortord}"><img src="/static/images/paixu01.png" style="vertical-align: middle;"/></c:if>		
								</div>
							</th>
							<th class="" width="20%">${LANG['bizconf.jsp.admin.arrange_org_user.res9'] }</th>
							<th class="" width="22%">
								<c:choose>
									<c:when test="${EVENTLOG_SORT_STATUS==sortField && SORT_ASC==sortord}">
										<div class="sort-th" onclick="sort('${EVENTLOG_SORT_STATUS}',${SORT_DESC })">
											${LANG['bizconf.jsp.system.site_info.res21'] }
											<img src="/static/images/paixu02.png" style="vertical-align: middle;"/>
										</div>
									</c:when>
									<c:when test="${EVENTLOG_SORT_STATUS==sortField  && SORT_DESC==sortord}">
										<div class="sort-th" onclick="sort('${EVENTLOG_SORT_STATUS}',${SORT_ASC })">
											${LANG['bizconf.jsp.system.site_info.res21'] }
											<img src="/static/images/paixu01.png" style="vertical-align: middle;"/>
										</div>
									</c:when>
									<c:otherwise>
										<div class="sort-th" onclick="sort('${EVENTLOG_SORT_STATUS}',${SORT_ASC })">
											${LANG['bizconf.jsp.system.site_info.res21'] }
											<img src="/static/images/paixu_button.png" style="vertical-align: middle;" />
										</div>
									</c:otherwise>
								</c:choose>
							</th>
							<th class="" 　width="20%">${LANG['website.admin.userlog.page.title.option.ip'] }</th>
						</tr>
						<c:forEach var="log" items="${pageModel.datas}" varStatus="status">
							<tr>
								<td class=""  title='${myfn:fmtDate("yyyy-MM-dd HH:mm:ss",log.loginTime,site.timeZone) }'>
<%--									<fmt:formatDate  value="${log.loginTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
									${myfn:fmtDate("yyyy-MM-dd HH:mm:ss",log.loginTime,site.timeZone) }
								</td>	
								<c:choose>
									<c:when test="${empty userMap[log.userId]}">
										<td class="" title="${log.loginName}">
											${log.loginName}
										</td>
										<td class="" title="${log.loginName}">
											${log.loginName}
										</td>
									</c:when>
									<c:otherwise>
										<td class="" title="${userMap[log.userId].trueName}">
											${userMap[log.userId].trueName}
										</td>
										<td class="" title="${userMap[log.userId].userEmail}">
											${userMap[log.userId].userEmail}
										</td>
									</c:otherwise>
								</c:choose>
									<c:choose>
									<c:when test="${log.loginStatus ==0}">
										<td class="" title="${LANG['system.login.log.error.0'] }">
											${LANG['system.login.log.error.0'] }
										</td>
									</c:when>
       								<c:when test="${log.loginStatus==1}" >
       									<td class="" title="${LANG['system.login.log.error.1'] }">
       										${LANG['system.login.log.error.1'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==2}" >
       									<td class="" title="${LANG['system.login.log.error.2'] }">
       										${LANG['system.login.log.error.2'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==3}" >
       									<td class="" title="${LANG['system.login.log.error.3'] }">
       										${LANG['system.login.log.error.3'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==4}" >
       									<td class="" title="${LANG['system.login.log.error.4'] }">
       										${LANG['system.login.log.error.4'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==5}" >
       									<td class="" title="${LANG['system.login.log.error.5'] }">
       										${LANG['system.login.log.error.5'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==77}" >
       									<td class="" title="${LANG['system.login.log.error.77'] }">
       										${LANG['system.login.log.error.77'] }
       									</td>
       								</c:when>
       								<c:when test="${log.loginStatus==99}" >
       									<td class="" title="${LANG['system.login.log.error.99'] }">
       										${LANG['system.login.log.error.99'] }
       									</td>
       								</c:when>
       								<c:otherwise>
       									<td class="" title="${LANG['system.login.log.error.6'] }">
       										${LANG['system.login.log.error.6'] }
       									</td>
       								</c:otherwise>
       							  </c:choose>
								<td class=""  title="${log.loginIp}">${log.loginIp}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagebar clearfix">
					<jsp:include page="page.jsp" />
				</div>
			</div>
			</c:if>
			<c:if test="${fn:length(pageModel.datas)<=0}">
				<div class="common-empty">
					${LANG['website.user.notice.nodata'] }<!-- 此栏目暂无数据 -->
				</div>
			</c:if>
		</div>
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
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
	if (typeof(resetPageNo) !== "undefined") {
		resetPageNo();	
	}
	$("#"+formId).submit();
	
}
function btnSearch(){
	if (typeof(resetPageNo) !== "undefined") {
		resetPageNo();	
	}
	$("#logsForm").submit(); 
}
function enterSumbit(url){  
    var event=arguments.callee.caller.arguments[0]||window.event;   
    if (event.keyCode == 13){       
		if (typeof(resetPageNo) !== "undefined") {
			resetPageNo();	
		}
    	logsForm.action=url;
    	logsForm.submit();	
    }   
} 
function viewDetails(id) {
	parent.viewUserConfDetails(id);
}
jQuery(function($) {
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=nameOrEmail]").watermark("${LANG['website.admin.userlog.page.search.optionor.tip'] } / ${LANG['bizconf.jsp.admin.arrange_org_user.res9'] }");
	}
	setCursor("operator-search-name", $("#operator-search-name").val().length);
});
</script>
</body>
</html>