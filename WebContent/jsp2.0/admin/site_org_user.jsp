<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<title>会畅通讯 - 会议系统</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;background: #FFFFFF">
	<form id="query" name="query" action="/admin/org/getOrgSubUserList/${orgId }" method="post" style="position: relative;">
		<input type="hidden" value="${sortField}" id="sortField" name="sortField" />
		<input type="hidden" value="${sortRule}" id="sortRule" name="sortRule" />
		<input type="hidden" value="${confId}" name="confId" />
		<input type="hidden" value="true" name="userPage" />
			
			<div class="party-dialog">
				<div class="wrapper">
					<section>
						<div class="party-panel biz-site-org-user">
							<div class="details">
								<c:if test="${fn:length(pageModel.datas)>0}">
									<table class="common-table">
										<tbody>
											<tr>
												<th class="login-name">${LANG['website.admin.org.user.title.loginname'] }</th>
												<th class="user-name">${LANG['website.admin.org.user.title.truename'] }</th>
												<th class="user-role">${LANG['website.admin.org.user.title.userrole'] }</th>
												<th class="user-email">${LANG['website.admin.org.user.title.email'] }</th>
												<th class="user-tel">${LANG['website.admin.org.user.title.phone'] }</th>
												<th class="user-state">${LANG['website.admin.org.user.title.userstatus'] }</th>
												<th class="operation">${LANG['website.admin.org.user.title.option'] }</th>
											</tr>
										</tbody>
									</table>
									<div class="manage-users-panel">
										<table class="common-table">
											<tbody>
												<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
													<tr>
														<td class="first-child login-name" title="${user.loginName}">
															<div class="nobr">${user.loginName}</div>
														</td>
											            <td class="user-name" title="${user.trueName }">${user.trueName }</td>
											            <td class="user-role">
												            <c:if test="${user.userRole eq '1'}">
													            ${LANG['site.admin.edituser.role1']}
												            </c:if>
												            <c:if test="${user.userRole eq '2'}">
													            ${LANG['site.admin.edituser.role2']}
												            </c:if>
											            </td>
											            <td class="user-email" title="${user.userEmail }">
											            	<div class="nobr">${user.userEmail }</div>
											            </td>
											            <td class="user-tel" title="${user.mobile }">
											            	${user.mobile }
											            </td>
											            <td class="user-state">
											            	<c:if test="${user.userStatus eq '0'}">${LANG['system.site.list.Status.lock']}</c:if>
											            	<c:if test="${user.userStatus eq '1'}">${LANG['site.admin.userlist.active']}</c:if>
											            </td>
											            <td class="last-child operation">
											            	<a onclick="return delUserFromOrg('${user.id }');">${LANG['bizconf.jsp.admin.site_org_user.res6']}</a>
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
										${LANG['website.user.notice.nodata'] }<!-- 此栏目暂无数据 -->
									</div>
								</c:if>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
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
	
	var ORG_USER_CONSTANT={
			remove_confirm:"${LANG['website.admin.org.list.js.message.org.user.remove']}"//确认移除该用户吗？
	};
	
	function delUserFromOrg(id){
		parent.parent.parent.promptDialog(ORG_USER_CONSTANT.remove_confirm, function() {
			app.delUserFromOrg(id, function(result) {
				if(result){
					if(result.status=="1"){
						parent.parent.successDialog(result.message);
					} else {
						parent.parent.errorDialog(result.message);
					}
				}
				refreashPage();
			});
		});
	}
	
	function refreashPage(){
		$("#pageNo").val("");
		$("#sortField").val("");
		$("#sortRule").val("");
		$("input[name=keyword]").val("");
		$("#query").attr("action","/admin/org/getOrgSubUserList/${orgId}");
		$("#query").submit();
	}
</script>	
</body>
</html>