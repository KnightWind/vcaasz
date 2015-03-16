<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<title>${LANG['website.user.conflogs.joinlist.headtitle']}</title>
		<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
		<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
		<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
		<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
		<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
		<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	</head>
	<body style="padding: 0px;">
		<form id="query" name="query" action="/user/conflog/loglist" method="post">
			<input type="hidden" value="${sortField}" id="sortField" name="sortField" />
			<input type="hidden" value="${sortRule}" id="sortRule" name="sortRule" />
			<input type="hidden" value="${confId}" name="confId" />
			<input type="hidden" value="true" name="userPage" />
			<div class="party-dialog">
				<div class="wrapper">
					<section>
						<div class="party-panel biz-join-conf-log">
							<div class="actions">
								<button onclick="exports('${confId}')" class="input-gray export">${LANG['website.user.conflogs.operation.export']}</button>
							</div>
							<div class="details">
								<c:if test="${fn:length(pageModel.datas)>0}">
									<table class="common-table">
										<tbody>
											<tr>
												<th class="user-name">${LANG['website.user.conflogs.title.name']}</th>
												<th class="user-type">${LANG['website.user.conflogs.title.usertype']}</th>
												<th class="join-time">${LANG['website.user.conflogs.title.jointime']}</th>
												<th class="exit-time">${LANG['website.user.conflogs.title.leavetime']}</th>
											</tr>
										</tbody>
									</table>
									<div class="user-conf-details-panel">
										<table class="common-table">
											<tbody>
												<c:forEach var="log" items="${pageModel.datas}" varStatus="status">
													<tr>
														<td class="user-name first-child" title="${log.userName}">${log.userName}</td>
														<td class="user-type">
															<c:choose>
										                		<c:when test="${log.termType eq '3'}">
										                			${LANG['bizconf.jsp.conflogs.res3']}
										                		</c:when>
										                		<c:when test="${log.termType eq '1'}">
										                			${LANG['bizconf.jsp.conflogs.res4']}
										                		</c:when>
										                		<c:otherwise>
										                			${LANG['bizconf.jsp.conflogs.res5']}
										                		</c:otherwise>
										                	</c:choose>
														</td>
														<td class="join-time" title="${myfn:fmtDate('yyyy-MM-dd HH:mm',log.joinTime,timezone)}">${myfn:fmtDate('yyyy-MM-dd HH:mm',log.joinTime,timezone)}</td>
														<td class="last-child exit-time">
															<c:choose>
											                	<c:when test="${log.exitTime != null}">
												                	${myfn:fmtDate('yyyy-MM-dd HH:mm',log.exitTime,timezone)}
											                	</c:when>
											                	<c:otherwise>
											                		--
											                	</c:otherwise>
										                	</c:choose>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="pagebar clearfix">
										<jsp:include page="page.jsp" />
									</div>
								</c:if>
								<c:if test="${fn:length(pageModel.datas)<=0}">
									<div class="common-empty">
										${LANG['website.user.conflogs.title.nodata']}
									</div>
								</c:if>
							</div>
						</div>						
					</section>
				</div>
				<div class="form-buttons">
					<input type="button" value="${LANG['website.common.option.confirm']}" class="button input-gray" onclick="closeDialog()">
				</div>	
			</div>
		<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
		<script type="text/javascript">
			function closeDialog(result) {
				var dialog = parent.$("#meetingSchedule");
				if(result){
					dialog.trigger("closeDialog", [result]);
				} else {
					dialog.trigger("closeDialog");	
				}
			}
			function exports(confId){
				window.open("<%=request.getContextPath()%>/user/conflog/exportLogs?sortField=${sortField}&sortRule=${sortRule}&confId="+confId);
			}
		</script>
	</form>
	</body>
</html>