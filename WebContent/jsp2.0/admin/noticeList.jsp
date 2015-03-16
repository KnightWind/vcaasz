<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.concat.css" />
</head>
<body>
<form id="queryNotice" name="queryNotice" action="/admin/notice/list" method="post">
	<div class="box">
		<div class="head">
			<span class="title">${LANG['website.admin.notice.list.mainname'] }</span> <!-- 公告信息 -->
			<span class="desc">${LANG['website.admin.notice.list.headinfo'] } </span><!-- 您可以管理公告信息 -->
		</div>
		<div class="" style="margin: 10px 0px;">
			<button onclick="createNotice()" class="input-gray" type="button">${LANG['website.admin.notice.list.addnotice'] }</button>
		</div>
	</div>
	<c:if test="${fn:length(noticeList)<=0 }">
		<div class="common-empty">${LANG['website.user.notice.nodata'] }</div><!-- 此栏目暂无数据 -->
	</c:if>
	<c:if test="${fn:length(noticeList)>0 }">
		<table class="common-table notice-table">
			<tbody>
				<tr>
					  <th class="name">${LANG['website.admin.notice.common.noticetheme'] }</td>
				      <th class="">${LANG['website.admin.notice.common.noticecontent'] }</td>
				      <th class="" style="width:120px;">${LANG['website.admin.notice.common.noticepublisher'] }</td>
				      <th class="actions">${LANG['website.admin.notice.common.noticepublishtime'] }</td>
				      <th class="actions">${LANG['website.common.option.operation'] }</td>
				</tr>
				<c:forEach var= "notice" items = "${noticeList}"  varStatus="status">
					<tr>
						<td class="first-child name" title="${notice.title}">
							${notice.title}
						</td>
						<td class="" title="${notice.content}">
							${notice.content}
						</td>
						<td class="" title="${publishUserList[status.count-1]}" style="width:120px;">
							${publishUserList[status.count-1]}
						</td>
				        <td class="actions"><fmt:formatDate value="${notice.startTime}" pattern="yyyy-MM-dd"/></td>
						<td class="last-child actions">
							<a href="javascript:updateNotice(${notice.id})">${LANG['website.common.option.modify'] }</a>
							<a href="javascript:del('${notice.id }','${user.userRole}');" style="margin-left:10px;">${LANG['website.common.option.delete'] }</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagebar clearfix">
			<jsp:include page="page.jsp" />
		</div>
	</c:if>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
function createNotice() {
	parent.createOrUpdateNotice();
}
function updateNotice(id) {
	parent.createOrUpdateNotice(id);
}
function del(id){
	parent.promptDialog("${LANG['system.notice.delete']}", function() {
		$("#queryNotice").attr("action","/admin/notice/delete/"+id);
		$("#queryNotice").submit();
	});
}

$(document).ready(function(){
	var errorMess = "${errorMessage}";
	if(errorMess != ""){
		parent.errorDialog("${errorMessage}");
	}
	var sucessMess = "${infoMessage}";
	if(sucessMess != ""){
		parent.successDialog("${infoMessage}");
	}
});

</script>
</body>
</html>