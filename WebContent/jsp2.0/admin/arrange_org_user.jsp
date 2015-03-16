<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<title>会畅通讯 - 会议系统</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;background: #FFFFFF">
<form id="query" name="query" action="/admin/org/getOrgUserList/${orgId}" method="post">
<input id="orgId" name="orgId" type="hidden" value="${orgId}"/>
	<div class="party-dialog">
		<div class="wrapper">
			<section>
				<div class="party-panel biz-arrange-org-user">
					<div class="details">
						<c:if test="${fn:length(pageModel.datas)>0}">
							<table class="common-table">
								<tbody>
									<tr>
										<th class="check"><input id="checkAll" type="checkbox"></th>
										<th class="login-name">${LANG['website.admin.org.user.title.loginname'] }</th>
										<th class="user-name">${LANG['website.admin.org.user.title.truename'] }</th>
										<th class="user-role">${LANG['website.admin.org.user.title.userrole'] }</th>
										<th class="user-email">${LANG['website.admin.org.user.title.email'] }</th>
										<th class="user-tel">${LANG['website.admin.org.user.title.phone'] }</th>
										<th class="user-state">${LANG['website.admin.org.user.title.userstatus'] }</th>
									</tr>
								</tbody>
							</table>
							<div class="assign-user-panel">
								<table class="common-table">
									<tbody>
										<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
											<tr>
												<td class="first-child check"><input name="id" value="${user.id}"  type="checkbox"></td>
												<td class="login-name" title="${user.loginName}">${user.loginName}</td>
									            <td class="user-name" title="${user.trueName }">${user.trueName }</td>
									            <td class="user-role">
									            	<div class="nobr">
										            <c:if test="${user.userRole eq '1'}">
											            ${LANG['site.admin.edituser.role1']}
										            </c:if>
										            <c:if test="${user.userRole eq '2'}">
											            ${LANG['site.admin.edituser.role2']}
										            </c:if>
										           	</div>
									            </td>
									            <td class="user-email" title="${user.userEmail }">
									            	<div class="nobr">${user.userEmail }</div>
									            </td>
									            <td class="user-tel" title="${user.mobile }">
									            	<div class="nobr">${user.mobile }</div>
									            </td>
									            <td class="last-child user-state">
									            	<div class="nobr">
									            	<c:if test="${user.userStatus eq '0'}">${LANG['system.site.list.Status.lock']}</c:if>
									            	<c:if test="${user.userStatus eq '1'}">${LANG['site.admin.userlist.active']}</c:if>
									            	</div>
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
			<div class="form-buttons">
				<c:if test="${fn:length(pageModel.datas)>0}">
					<input type="button" value="${LANG['website.common.option.confirm'] }" class="button input-gray"  onclick="submitBatch()">
					<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
				</c:if>
				<c:if test="${fn:length(pageModel.datas)<=0}">
					<input type="button" value="${LANG['website.common.option.confirm'] }" class="button input-gray"  onclick="closeDialog()">
					<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
				</c:if>
			</div>	
		</div>
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($(this).attr("checked")){
				$("input[name=id]").attr("checked",true);				
			}else{
				$("input[name=id]").attr("checked",false);
			}
		});
		$("input[name=id]").click(function(){
			if($("input[name=id]").length == $("input[name=id]:checked").length){
				$("#checkAll").attr("checked",true);
			}else{
				$("#checkAll").attr("checked",false);
			}
		});
	});
	
	function submitBatch(){
		var data = {};
		data.orgId = $("#orgId").val();
		data.id = [];
		$("input[name=id]:checked").each(function(){
			var id = $(this).val();
			data.id.push(id);
		});
		if(data.id.length==0){	 
			parent.errorDialog("${LANG['bizconf.jsp.admin.arrange_org_user.res3']}");
			return;
		} else {
			data.id = data.id.join(",");
			app.assignUser(data, function(result) {
				if(result) {
					if(result.status==1){
						closeDialog(result);		
					} else {
						parent.errorDialog(result.message);
					}
				}
			}, {message:"${LANG['bizconf.jsp.admin.arrange_org_user.res4']}...", ui:parent});
		}
	}

	function closeDialog(result) {
		var dialog = parent.$("#assignUserDiv");
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
</body>
</html>