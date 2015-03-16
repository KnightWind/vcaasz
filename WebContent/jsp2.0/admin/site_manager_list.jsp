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
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.concat.css" />
</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.admin.adminlist.page.mainname'] }</span>
		<span class="desc">${LANG['website.admin.adminlist.page.maindesc'] }</span>
	</div>
</div>
<div class="tabview">
	<div class="views">
		<form id="query" name="query" action="/admin/entUser/list" method="post">
		<input type="hidden" value="${sortField}" id="sortField" name="sortField" />
		<input type="hidden" value="${sortRule}" id="sortRule" name="sortRule" />
		<input type="hidden"  name="id" value=""/>
		<div class="summary">
			<ul class="actions">
				<li class=""><a onclick="return toEditUser();"><i class="icon icon-add"></i>${LANG['website.admin.siteadmin.list.addadmin']}</a></li>
			</ul>
			<div class="search">
					<input type="text" name="keyword"  value="${keyword}" class="input-text" placeholder="${LANG['website.admin.siteadmin.placeholder']}" />
					<button class="submit-search" type="button" onclick="submitForm();" >${LANG['system.general.search']}</button>
			</div>
		</div>
		<div class="view active manager-list-view" data-view="users">
			<c:if test="${fn:length(pageModel.datas) gt 0}">
				<table class="common-table">
					<tbody>
						<tr>
<%--							<th class="check"><input id="checkAll" type="checkbox"></th>--%>
							  <th class="name">${LANG['website.admin.siteadmin.title.loginname']}</td>
						      <th class="name">${LANG['website.admin.userbase.title.name'] }</td>
						      <th class="name">${LANG['website.admin.siteadmin.title.enname']}</td>
						      <th class="email">${LANG['website.admin.userbase.title.emailaddress']}</td>
						      <th class="mobile">${LANG['website.admin.siteadmin.title.phone']}</td>
						      <th class="actions">${LANG['website.common.option.operation']}</td>
						</tr>
						<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
							<tr>
<%--								<td class="first-child check"><input name="id" value="${user.id}"  type="checkbox"></td>--%>
								<td class="first-child name" title="${user.loginName}">${user.loginName}</td>
								<td class="name" title="${user.trueName}">${user.trueName}</td>
								<td class="name" title="${user.enName}">${user.enName}</td>
						        <td class="email" title="${user.userEmail}">${user.userEmail}</td>
						        <td class="landline" title="${user.mobile}">${user.mobile}</td>
								<td class="last-child actions">
									<a onclick="return toEditUser('${user.id }')">${LANG['website.common.option.modify']}</a>
									<a onclick="return delUser('${user.id }');" style="margin-left:10px;">${LANG['website.common.option.delete']}</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagebar clearfix">
					<jsp:include page="page.jsp" />
				</div>
			</c:if>
			<c:if test="${fn:length(pageModel.datas) lt 1}">
				<div class="module">
					<div class="nodata">${LANG['website.admin.siteadmin.list.nodata']}</div>
				</div>
			</c:if>
		</div>
	</form>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=keyword]").watermark("${LANG['website.admin.siteadmin.placeholder']}");
	}
	
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
		
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").attr("action","/admin/entUser/list");
				$("#query").submit();
			}
		});
});

function toEditUser(id){
	top.createOrUpdateAdminUser(id);
}

function delUser(id){
	parent.promptDialog("${LANG['system.siteadmin.delete']}", function() {
		parent.successDialog("${LANG['bizconf.jsp.admin.action.delete.success']}");
		query.action="/admin/entUser/delUsers";
		$("input[name=id]").val(id);
		query.submit();
	});
}

function submitForm(){
	query.action="/admin/entUser/list";
	$("#pageNo").val("");
	query.submit();
}
</script>
</body>
</html>