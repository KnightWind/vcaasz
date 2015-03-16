<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构管理</title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/organization.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/module/tipsy.css" />
<cc:confList var="CONF_STATUS_OPENING"/>
<cc:confList var="CONF_STATUS_FINISHED"/>
</head>
<body>
	<form id="query" name="query" action="/admin/org/orgListIndex/${orgId }" method="post">
		<div class="box">
			<div class="head">
				<span class="title">${LANG['website.admin.org.list.page.mianname01'] }</span>
				<span class="desc">${LANG['website.admin.org.list.page.mianname'] }</span>
			</div>
			<div class="body">
				<div class="summary">
					<span class="date">
						<a class="input-gray" onclick="return createOrg(0)">${LANG['website.admin.org.list.page.add.btn'] }</a>
					</span>
				</div>
				<div class="infomation">
					<c:if test="${fn:length(pageModel.datas)>0}">
					<table class="common-table">
						<tbody>
							<tr>
								<th class="name">${LANG['website.admin.org.list.page.title.orgname'] }</th>
								<th class="desc">${LANG['website.admin.org.list.page.title.orgdesc'] }</th>
								<th class="count">${LANG['website.admin.org.list.page.title.usercount'] }</th>
								<th class="actions">${LANG['website.admin.org.list.page.title.option'] }</th>
							</tr>
							<c:forEach var= "org" items = "${pageModel.datas}"  varStatus="status">
								<tr>
									<td class="first-child name" title="${org.orgName}">
										<c:if test="${org.orgLevel == 1 }">
								        	<i class="icon-depart"></i>
								        </c:if>
								        <c:if test="${org.orgLevel == 2 }">
								        	<i class="icon-blank"></i>
								            <i class="icon-under"></i>
								        </c:if>
								        <c:if test="${org.orgLevel == 3 }">
								            <i class="icon-blank"></i>
											<i class="icon-blank"></i>
											<i class="icon-under"></i>
								        </c:if>
								        <c:if test="${org.orgLevel == 4 }">
								        	<i class="icon-blank"></i>
								        	<i class="icon-blank"></i>
											<i class="icon-blank"></i>
											<i class="icon-under"></i>
								        </c:if>
							        	${org.orgName}
									</td>
									<td class="desc" title="${org.orgDesc}">${org.orgDesc}</td>
									<td class="count">
										<c:set var="userCount" value="${countMap[org.orgCode]}"></c:set>
										<c:if test="${ userCount == '' || empty userCount}"><c:set var="userCount" value="0"></c:set></c:if>
										<a onclick="return showOrgUsers('${org.id}');">${userCount}</a>
									</td>
									<td class="last-child actions" style="text-align: right;">
										<c:if test="${org.orgLevel<4}">
        									<a class="action append" onclick='return createSubOrg("${org.id}", "${org.orgLevel}")'>
        										${LANG['website.admin.org.list.page.title.option.add.child'] }
        									</a>
        								</c:if>
										<a class="action modify" onclick='return updateOrg("${org.id}")'>
											${LANG['website.common.option.modify'] }
										</a>
										<a class="action distri" onclick='return arrangeUser("${org.id}")'>
											${LANG['website.admin.org.list.page.title.option.ass.user'] }
										</a>
										<a class="action manage" onclick="return showOrgUsers('${org.id}')">
											${LANG['website.admin.org.list.page.title.option.manage.user'] }
										</a>
										<a class="action remove" onclick='return del("${org.id}")'>
											${LANG['website.common.option.delete'] }
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</c:if>
					<c:if test="${fn:length(pageModel.datas)<=0}">
						<div class="common-empty">${LANG['website.user.notice.nodata'] }</div>
					</c:if>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.tipsy.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript">
	var ORG_LIST_CONSTANT={
			org_max_tip:"${LANG['website.admin.org.list.js.message.org.delete.confirm']}",//最多支持4级部门
			del_confirm:"${LANG['website.admin.org.list.js.message.org.delete.confirm']}"//确认删除该部门吗？
			
	};
	
		//添加部门
		function createOrg(id, level) {
			if(level==4){
				parent.errorDialog(ORG_LIST_CONSTANT.org_max_tip);
			} else {
				parent.createOrg(id);	
			}
		}
		//添加子部门
		function createSubOrg(id, level) {
			createOrg(id, level);
		}
		//修改部门
		function updateOrg(id) {
			parent.updateOrg(id);
		}
		//分配用户
		function arrangeUser(id) {
			parent.getOrgUserList(id);
		}
		//管理用户
		function showOrgUsers(id) {
			parent.showOrgUsers(id);
		}
		
		function del(id){
			parent.promptDialog(ORG_LIST_CONSTANT.del_confirm, function() {
				app.delSiteOrg(id, function(result) {
					if(result){
						if(result.status=="1"){
							refreshIframe();
							parent.successDialog(result.message);
						} else {
							parent.errorDialog(result.message);
						}
					}
				});
			});
		}
		function refreshIframe() {
			$("#query").submit();
		}
	</script>
</body>
</html>