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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<form id="query" name="query" action="/user/group/invitelist" method="post">
	<div class="choose-dialog">
		<div class="wrapper">
			<div class="choose-panel">
				<div class="tabview">
					<div class="view active">
						<div class="filter clearfix">
							<div class="search">
									<input type="text" class="input-text" name="keyword" value="${keyword}" placeholder="${LANG['website.user.invite.group.groupname']}">
									<button class="submit-search" type="button" onclick="submitForm();">${LANG['swebsite.common.search.name']}</button>
							</div>
						</div>
						<div class="render">
							<c:if test="${fn:length(pageModel.datas)>0}">
								<table class="common-table">
									<tbody>
										<tr>
											<th class="check"><input type="checkbox" id="checkAll"  /></th>
											<th class="group-name">${LANG['website.user.invite.group.groupname']}</th>
											<th class="">${LANG['website.user.group.choose.remark']}</th>
										</tr>
									</tbody>
								</table>
								<div class="panel">
									<table class="common-table">
										<tbody>
											<c:forEach var="group" items="${pageModel.datas}" varStatus="status">
												<tr>
													<td class="first-child check"><input name="id"  type="checkbox" value="${group.id }" /></td>
													<td class="group-name" title="${group.groupName}">
														<a onclick="return viewContacts('${group.id }','${group.groupName}')">${group.groupName}</a>
													</td>
													<td class="last-child" title="${group.groupDesc}">${group.groupDesc}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="pagebar clearfix">
									 <jsp:include page="page.jsp" />
								</div>
							</c:if>
							<c:if test="${fn:length(pageModel.datas)<1}">
									<div class="common-empty">${LANG['website.user.group.choose.nodata']}</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
function viewContacts(id,name){
// 	var url = "/user/group/showContacts?viewOnly=1&group_id="+id;
	top.viewGroupMember(id,1,name, false);
}

function getContactsData(){
	var ids = "";
	$("input[name=id]:checked").each(function(){
		ids += ",";
		ids += $(this).val();
	});
	var data = new Object();
	data.ids = ids;
	return data;
}

$(document).ready(function(){
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=keyword]").watermark("${LANG['website.user.invite.group.groupname']}");
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
			$("#query").submit();
		}
	});
});

function submitForm(){
	$("#pageNo").val("");
	query.submit();
}
</script>
</body>
</html>