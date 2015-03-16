<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css?var=22" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<form id="query" name="query" action="/user/group/showContacts" method="post">
	<input type="hidden" name="group_id" value="${group_id}"/>
	<input type="hidden" name="viewOnly" value="0"/>
	<div class="invite-dialog">
		<div class="wrapper">
			<section>
				<div class="invite-panel biz-group-member-view">
					<div class="wrapper">
						<div class="actions">
							<a class="input-gray" onclick="toAddContacts('${group_id}');">${LANG['website.user.group.editmember.addmember']}</a>
						</div>
						<div class="filter clearfix">
							<div class="search">
									<input type="text" name="keyword" value="${keyword}" placeholder="${LANG['website.user.contact.list.placeholder1']}" class="input-text">
									<button type="button" class="submit-search" onclick="submitForm();">${LANG['swebsite.common.search.name']}</button>
							</div>
						</div>
<%--						<table class="common-table">--%>
<%--							<tbody>--%>
<%--								<tr>--%>
<%--									  <th class="first-child user-name">${LANG['website.user.group.editmember.name']}</td>--%>
<%--								      <th class="user-email">${LANG['website.user.contact.list.titleemail']}</td>--%>
<%--								      <th class="user-tel">${LANG['website.user.contact.list.titlephone']}</td>--%>
<%--								      <th class="user-call">${LANG['website.user.contact.list.titlemobile']}</td>--%>
<%--								      <th class="last-child operation">${LANG['website.user.contact.list.titleactions']}</td>--%>
<%--								</tr>--%>
<%--							</tbody>--%>
<%--						</table>--%>
						<c:if test="${fn:length(pageModel.datas)>0 }">
							<div class="panel">
								<table class="common-table titles-table">
											</tbody>
												<tr>
													  <th class="first-child user-name">${LANG['website.user.group.editmember.name']}</td>
												      <th class="user-email">${LANG['website.user.contact.list.titleemail']}</td>
												      <th class="user-tel">${LANG['website.user.contact.list.titlephone']}</td>
												      <th class="user-call">${LANG['website.user.contact.list.titlemobile']}</td>
												      <th class="last-child operation">${LANG['website.user.contact.list.titleactions']}</td>
												</tr>
												<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
													<tr>
														<td class="first-child user-name" title="${contact.contactName}">${contact.contactName}</td>
												        <td class="user-email" title="${contact.contactEmail}">${contact.contactEmail}</td>
												        <td class="user-tel" title="${contact.contactPhone}">${contact.contactPhone}</td>
												        <td class="user-call" title="${contact.contactMobile}">${contact.contactMobile}</td>
														<td class="last-child operation">
															<a onclick="return delFromGroup('${contact.id }')" class="remove"><i class="icon icon-remove"></i>${LANG['website.common.option.delete']}</a>
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
						<c:if test="${fn:length(pageModel.datas)<1 }">
							<div class="common-empty">${LANG['website.user.group.choose.nodata']}</div>
						</c:if>
					</div>
				</div>				
			</section>
			<div class="form-buttons">
				<input type="button" value="${LANG['website.common.option.confirm']}" onclick="closeDialog();" class="button input-gray">
			</div>
		</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript">
	function delFromGroup(id){
		parent.promptDialog("${LANG['website.user.group.editmember.deletemember']}", function() {
			var groupId = "${group_id}";
			app.delContactFromGroup(id, groupId, function() {
				top.refreshChildIframe("/user/group/list","contentFrame");
				query.submit();
			});
		});
	}
	
	function toAddContacts(id){
		top.addContactToGroup(id);
	}
	
	function refreshData(){
		query.submit();
	}
	
	function submitForm(){
		query.submit();
	}
	
	function loaded() {
		var frame = parent.$("#viewGroup");
		frame.trigger("loaded");
	}
	
	function closeDialog(result) {
		var dialog = parent.$("#viewGroup");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
	
	$(document).ready(function(){
		if ($.browser.msie && $.browser.version<10) {
			$("input[name=keyword]").watermark("${LANG['website.user.contact.list.placeholder1']}");
		}
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").submit();
			}
		});	
		
		
	});
</script>
</body>
</html>
