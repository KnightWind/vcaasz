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
<cc:logs var="SITE_ADMIN_USER_UPDATE"></cc:logs>
<cc:sort var="SORT_ASC"/><cc:sort var="SORT_DESC"/>
<cc:sort var="EVENTLOG_SORT_DEFAULT"/>
<cc:sort var="EVENTLOG_SORT_STATUS"/>
<cc:sort var="EVENTLOG_SORT_CREATETIME"/>
</head>
<body>
<form id="logsForm" name="logsForm" action="/admin/siteAdminLogs/list" method="post">
<input class="" type="hidden" name="sortField" id="sortField" value="${sortField}"/>
<input class="" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<div class="box">
		<div class="head">
			<span class="title">${LANG['website.admin.adminlog.page.mainname'] }</span>

			<span class="desc">${LANG['website.admin.adminlog.page.maindesc'] }</span>
		</div>
		<div class="body">
			<div class="summary">
				<div class="search" style="float: left;*padding-top:10px;">
					<select name="logType" id="logType" onchange="javascript:btnSearch();" style="float: left;padding:3.5px;border: 1px solid #DADADA;border-right:0px;">
						<c:choose>
							<c:when test="${isSuperSiteAdmin}">
								<cc:logs var="SITE_EVENTLOG_TOTAL"/>
						   		<c:forEach var="eachType" items="${SITE_EVENTLOG_TOTAL}"  varStatus="itemStatus">
						   			<c:set var ="typeName" value="siteAdmin.eventlog.type.${eachType }"/>
						   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
						   		</c:forEach>
							</c:when>
							<c:otherwise>
								<cc:logs var="SITE_USER_EVENTLOG_TOTAL"/>
						   		<c:forEach var="eachType" items="${SITE_USER_EVENTLOG_TOTAL}"  varStatus="itemStatus">
						   			<c:set var ="typeName" value="siteAdmin.eventlog.type.${eachType }"/>
						   			<option value="${eachType}" <c:if test="${logType==eachType}">selected</c:if>>${LANG[typeName]}</option>
						   		</c:forEach>
							</c:otherwise>
						</c:choose>
			    	</select>
			    	<c:if test="${isSuperSiteAdmin}">
						<input id="operator-search-name" name="operator" value="${operator}" onkeydown='enterSumbit("/admin/siteAdminLogs/list")' type="text" class="input-text" placeholder="${LANG['website.admin.adminlog.page.optionor'] }" />
						<button class="submit-search" type="button" onclick="btnSearch()">${LANG['website.common.search.name'] }</button>
					</c:if>
				</div>
			</div>
			<c:if test="${fn:length(logList)>0}">
				<div class="infomation">
					<table class="common-table">
						<tbody>
							<tr>
								<th class="" style="width:60px;">
									<div class="sort-th" onclick="sort('${EVENTLOG_SORT_STATUS}')">
										${LANG['website.admin.adminlog.page.title.typename'] }
										<c:if test="${EVENTLOG_SORT_STATUS!=sortField}"><img src="/static/images/paixu_button.png" style="vertical-align: middle;"/></c:if>
										<c:if test="${EVENTLOG_SORT_STATUS==sortField && SORT_ASC==sortord}"><img src="/static/images/paixu02.png" style="vertical-align: middle;"/></c:if>
										<c:if test="${EVENTLOG_SORT_STATUS==sortField  && SORT_DESC==sortord}"><img src="/static/images/paixu01.png" style="vertical-align: middle;"/></c:if>
									</div>
								</th>
								<th class="" style="width:120px;">
									<div class="sort-th" onclick="sort('${EVENTLOG_SORT_CREATETIME}')">
										${LANG['website.admin.adminlog.page.title.logtime'] }
										<c:if test="${EVENTLOG_SORT_CREATETIME!=sortField}"><img src="/static/images/paixu_button.png" style="vertical-align: middle;"/></c:if>
										<c:if test="${EVENTLOG_SORT_CREATETIME==sortField && SORT_ASC==sortord}"><img src="/static/images/paixu02.png" style="vertical-align: middle;"/></c:if>
										<c:if test="${EVENTLOG_SORT_CREATETIME==sortField  && SORT_DESC==sortord}"><img src="/static/images/paixu01.png" style="vertical-align: middle;"/></c:if>		
									</div>
								</th>
								<th class="">${LANG['website.admin.adminlog.page.title.site.sign'] }</th>
								<th class="" style="width: 200px;">${LANG['website.admin.adminlog.page.title.option'] }</th>
								<th class="">${LANG['website.admin.adminlog.page.title.optionor'] }</th>
								<th class="">${LANG['website.admin.adminlog.page.title.option.object'] }</th>
								<th class="" style="width:100px;">${LANG['website.admin.adminlog.page.title.option.ip'] }</th>
								<th class="details" style="display:none; width:40px;">${LANG['website.admin.adminlog.page.title.desc'] }</th>
							</tr>
							<c:forEach var="eventLog" items="${logList}" varStatus="status">
								<tr>
										<c:if test="${eventLog.logStatus==EVENTLOG_SECCEED}">
											<td class="first-child" style="width:40px;" title="${LANG['website.admin.adminlog.page.item.option.normal'] }">
												${LANG['website.admin.adminlog.page.item.option.normal'] }
											</td>
										</c:if>
										
	       								<c:if test="${eventLog.logStatus==EVENTLOG_FAIL}">
											<td class="first-child" style="width:40px;" title="${LANG['website.admin.adminlog.page.item.option.exception'] }">
	       										${LANG['website.admin.adminlog.page.item.option.exception'] }
	       									</td>
	       								</c:if>
									</td>
									<td class="" style="width:120px;" title='<fmt:formatDate  value="${eventLog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>'>
										<fmt:formatDate  value="${eventLog.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td class="" title="${siteSign}">
										${siteSign}
									</td>
									<c:set var ="typeName"  value="siteAdmin.eventlog.type.${eventLog.funcModule}"/>
									<td class=""  title="${LANG[typeName]}">
										${LANG[typeName]}
									</td>
									<c:set var="optionName" value="${operatorList[status.count-1]}"/>
									<td class="" title="${optionName }">
										${optionName }
									</td>
									<c:set var="eventUser" value="${eventLog.optName}"/>
									<td class="" <c:if test="${not empty eventUser}"> title="${eventUser }" </c:if> >
										
								        <c:if test="${not empty eventUser }">
								      		${eventUser }
								        </c:if>
								        <c:if test="${empty eventUser }">
								      		- -
								        </c:if>
									</td>
									<td class="" style="width:100px;" title="${eventLog.createIp}">${eventLog.createIp}</td>
									<td class="last-child details" style="display:none; width:40px;">
										<c:if test="${SITE_ADMIN_USER_UPDATE == eventLog.funcModule}">
											<a onclick="viewDetails(${eventLog.id})">${LANG['website.admin.userlog.page.desc.name'] }</a>
										</c:if> 
										<c:if test="${SITE_ADMIN_USER_UPDATE != eventLog.funcModule}">
											<div style="color: #CCCCCC">${LANG['website.admin.userlog.page.desc.name'] }</div>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagebar clearfix">
						<jsp:include page="page.jsp" />
					</div>
				</div>
			</c:if>
			<c:if test="${fn:length(logList)<=0}">
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

	function viewDetails(id) {
		parent.viewAdminUserDetails(id);
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
	jQuery(function($) {
		if ($.browser.msie && $.browser.version<10) {
			$("input[name=operator]").watermark("${LANG['website.admin.adminlog.page.optionor'] }");
		}
		setCursor("operator-search-name", $("#operator-search-name").val().length);
	});	
</script>
</body>
</html>