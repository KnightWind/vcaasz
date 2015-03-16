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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
</head>
<body style="padding: 0px;">
<div class="tabview">
		<div class="view active">
		<form id="query" name="query" action="/user/group/list" method="post">
			<input name="id" value="" type="hidden"/>
			<div class="summary">
			<ul class="actions">
				<li class=""><a onclick="return toEditGroup('');"><i class="icon icon-add"></i>${LANG['website.user.group.list.addgroup']}</a></li>
			</ul>
			<div class="search">
					<input type="text" class="input-text" name="keyword" value="${keyword}"  placeholder="${LANG['website.user.group.list.groupname']}" />
					<button class="submit-search"  type="button">${LANG['system.general.search']}</button>
			</div>
		</div>
		<div class="view" data-view="groups">
			<c:if test="${fn:length(pageModel.datas) gt 0}">
				<table class="common-table group-list-table" style="table-layout:fixed;">
					<tbody>
						<tr>
							<th class="name">${LANG['website.user.group.list.titlename']}</td>
	        				<th class="email">${LANG['website.user.group.list.titledesc']}</td>
	         				<th class="actions">${LANG['website.user.group.list.titleaction']}</td>
						</tr>
						<c:forEach var="group" items="${pageModel.datas}" varStatus="status">
						<tr>
							<td class="first-child name" title="${group.groupName}"><a onclick="return viewContacts('${group.id }','${group.groupName}')">${group.groupName}</a></td>
							<td class="email" title="${group.groupDesc}">${group.groupDesc}</td>
							<td class="last-child actions">
								<a class="member-view" onclick="return viewContacts('${group.id }','${group.groupName}')">${LANG['website.user.group.list.viewmembers']}&nbsp;<span groupId="${group.id}" name="contactnum"></span></a>
								<a class="member-modify" onclick="return toEditGroup('${group.id }')"><i class="icon icon-modify"></i>${LANG['website.common.option.modify']}</a> 
								<a class="remove" onclick="return delGroup('${group.id }')"><i class="icon icon-remove"></i>${LANG['website.common.option.delete']}</a>
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
					<div class="nodata">${LANG['website.user.group.list.nodata']}</div>
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
	function delGroup(id){
		parent.parent.promptDialog("${LANG['website.user.group.list.deleteconfirm']}", function() {
			query.action="/user/group/delGroup";
			$("input[name=id]").val(id);
			query.submit();
			top.successDialog("${LANG['website.user.group.list.warminfo1']}");
		});
	}
	
	function toEditUser(id){
		parent.parent.createOrUpdateAdminUser(id);
	}
	function toEditGroup(id){
		parent.parent.addGroup(id);
	}
	function viewContacts(id,name){
		top.viewGroupMember(id,'',name, true);
	}
	$(document).ready(function(){
		parent.refreshIHeight();
		if ($.browser.msie && $.browser.version<10) {
			$("input[name=keyword]").watermark("${LANG['website.user.group.list.groupname']}");
		}
		$("span[name=contactnum]").each(function(){
			var countdata = $.parseJSON('${countJson}') || {};
			var group_id = $(this).attr("groupId");
			if(countdata[group_id] || countdata[group_id]>=0 ){
				$(this).empty().html("("+countdata[group_id]+")");
			}else{
				$(this).empty().html("(0)");
			}
		});
		
		$(".submit-search").click(function(){
			$("#pageNo").val("");
			$("#query").attr("action","/user/group/list");
			$("#query").submit();
		});
		
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").attr("action","/user/group/list");
				$("#query").submit();
			}
		});
	});
</script>
</body>
</html>