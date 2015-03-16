<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;" onload="loaded();">
<form id="query" name="query" action="/user/conflog/reportInfoList" method="post">
<input type="hidden" value="${confLogId}" name="confLogId" />
<input type="hidden" value="${confId}" name="confId" />
<div class="party-dialog">
	<div class="wrapper">
		<section>
			<div class="party-panel biz-log-conf-details">
				<div class="actions">
						<!-- 导出按钮 -->
<%--					<c:if test="${fn:length(pageModel.datas)>0}">--%>
<%--						<a onclick="return exports('${confId}')" class="input-gray">${LANG['website.user.conflogs.operation.export']}</a>--%>
<%--					</c:if>--%>
				</div>
				<!-- 参会者列表 -->
				<div class="details">
					<c:if test="${fn:length(pageModel.datas)>0}">
						<table class="common-table" style="width: 674px">
							<thead>
								<tr>
									<th class="user-name">${LANG['website.user.conflogs.title.name']}</th>
									<th class="user-join-time">${LANG['website.user.conflogs.title.jointime']}</th>
									<th class="last-child user-exit-time">${LANG['website.user.conflogs.title.leavetime']}</th>
								</tr>
							</thead>
						</table>
						<div class="user-conf-details-panel" style="width: 674px">
							<table class="common-table">
								<tbody>
									<c:forEach var="log" items="${pageModel.datas}">
										<tr>
											<td class="first-child user-name">
												${log.participantName}
											</td>
											<td class="user-join-time">
												<div class="nobr">${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',log.pStartTime,timezone)}</div>
											</td>
											<td class="last-child user-exit-time">
												<div class="nobr">
												<c:choose>
								                	<c:when test="${log.pLeaveTime != null}">
									                	${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',log.pLeaveTime,timezone)}
								                	</c:when>
								                	<c:otherwise>
								                		--
								                	</c:otherwise>
							                	</c:choose>
							                	</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="pagebar clearfix" style="width: 664px;margin-top: 50px;">
							<jsp:include page="page.jsp" />
						</div>
					</c:if>
					<c:if test="${fn:length(pageModel.datas)<=0}">
						<div class="common-empty">
							${LANG['website.user.notice.nodata'] }<!-- 此栏目暂无数据 -->
						</div>
					</c:if>
				</div>
			</div>
		</section>
		<div class="form-buttons">
		 		<input type="button" class="button input-gray" onclick="closeDialog();" value="${LANG['website.common.option.confirm']}" />
		</div>
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#meetingSchedule");
	frame.trigger("loaded");
}

function closeDialog(result) {
	var dialog = parent.parent.$("#meetingSchedule");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}

function exports(confId){
	window.location = "<%=request.getContextPath()%>/user/conflog/exportLogs?confId="+confId;
}
</script>
</body>
</html>