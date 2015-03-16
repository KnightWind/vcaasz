<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/users-manage.css" />
</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.admin.user.list.page.mainname'] }</span>
		<span class="desc">${LANG['website.admin.user.list.page.maindesc'] }</span>
	</div>
</div>
<form id="query" name="query" action="/admin/entUser/listAll" method="post">
<div class="tabview">
	<div class="views">
		<input type="hidden" value="${sortField}" id="sortField" name="sortField" />
		<input type="hidden" value="${sortRule}" id="sortRule" name="sortRule" />
		<div class="summary" style="position: relative;">
			<ul class="actions">
<!-- 				<li class=""><a onclick="return toEditUser();"><i class="icon icon-add"></i>${LANG['website.admin.user.list.page.btn.add'] }</a></li> -->
<!-- 				<li class=""><a onclick="return unlockUser();"><i class="icon icon-act"></i>${LANG['website.admin.user.list.page.btn.unlock'] }</a></li> -->
<!-- 				<li class=""><a onclick="return lockUser();"><i class="icon icon-loc"></i>${LANG['website.admin.user.list.page.btn.lock'] }</a></li> -->
<!-- 				<li class=""><a onclick="return delUser();"><i class="icon icon-del"></i>${LANG['website.admin.user.list.page.btn.delete'] }</a></li> -->
<!-- 				<li class=""><a onclick="return doImport();"><i class="icon icon-imp"></i>${LANG['website.admin.user.list.page.btn.import'] }</a></li> -->
				<li class=""><a onclick="return exportExcel();"><i class="icon icon-exp"></i>${LANG['website.admin.user.list.page.btn.export'] }</a></li>
			</ul>
			<div class="search" style="position: absolute;right: 0px;">
					<input class="input-text" name="keyword" type="text"  value="${keyword}" placeholder="${LANG['website.admin.siteadmin.placeholder']}" />
					<button class="submit-search" type="button" id="btn_search" >${LANG['system.general.search']}</button>
			</div>
		</div>
		<div class="view active user-list-view" data-view="users">
			<c:if test="${fn:length(pageModel.datas)<=0}">
				<div class="module">
					<div class="nodata">${LANG['website.user.notice.nodata'] }</div><!-- 此栏目暂无数据 -->
				</div>
			</c:if>
			<c:if test="${fn:length(pageModel.datas)>0}">
			<table class="common-table">
				<tbody>
					<tr>
						<th class="check"><input id="checkAll" type="checkbox"></th>
						  <th>
							<c:if test="${3!=sortField}">
								      	<div onclick="sortQuery('3','1');">
								            <span>${LANG['website.admin.user.list.page.title.loginname'] }</span>
							            	<a class="paixu01" href="javascript:;" ><img style="vertical-align: middle;" src="/static/images/paixu_button.png" width="6" height="13" /></a>
						           		 </div>
						       		 </c:if>
						       		 <c:if test="${3==sortField && 0==sortRule}">
								      	<div onclick="sortQuery('3','1')">
								            <span>${LANG['website.admin.user.list.page.title.loginname'] }</span>
						        			<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu02.png" width="6" height="13" /></a>
						           		 </div>
						        	</c:if>
							        <c:if test="${3==sortField  && 1==sortRule}">
								      	<div onclick="sortQuery('3','0')">
								            <span>${LANG['website.admin.user.list.page.title.loginname'] }</span>
							        		<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu01.png" width="6" height="13" /></a>
						           		 </div>
							 </c:if>
						  </th>
					      <th>
						            <c:if test="${2!=sortField}">
								      	<div onclick="sortQuery('2','1')">
								            <span>${LANG['website.admin.userbase.title.name'] }</span>
							            	<a class="paixu01" href="javascript:;" ><img style="vertical-align: middle;" src="/static/images/paixu_button.png" width="6" height="13" /></a>
						           		 </div>
						       		 </c:if>
						       		 <c:if test="${2==sortField && 0==sortRule}">
								      	<div onclick="sortQuery('2','1')">
								            <span>${LANG['website.admin.userbase.title.name'] }</span>
						        			<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu02.png" width="6" height="13" /></a>
						           		 </div>
						        	</c:if>
							        <c:if test="${2==sortField  && 1==sortRule}">
								      	<div onclick="sortQuery('2','0')">
								            <span>${LANG['website.admin.userbase.title.name'] }</span>
							        		<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu01.png" width="6" height="13" /></a>
						           		 </div>
							        </c:if>
					      </th>
					      <th class="">${LANG['website.admin.user.list.page.title.userrole'] }</th>
					      <th class="">${LANG['website.admin.user.list.page.title.email'] }</th>
					      <th class="">${LANG['website.admin.user.list.page.title.phone'] }</th>
					      <th class="" style="cursor: pointer;">
							<c:if test="${1!=sortField}">
								      	<div onclick="sortQuery('1','1')">
								            <span>${LANG['website.admin.user.list.page.title.userstatus'] }</span>
							            	<a class="paixu01" href="javascript:;" ><img style="vertical-align: middle;" src="/static/images/paixu_button.png" width="6" height="13" /></a>
						           		 </div>
						       		 </c:if>
						       		 <c:if test="${1==sortField && 0==sortRule}">
								      	<div onclick="sortQuery('1','1')">
								            <span>${LANG['website.admin.user.list.page.title.userstatus'] }</span>
						        			<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu02.png" width="6" height="13" /></a>
						           		 </div>
						        	</c:if>
							        <c:if test="${1==sortField  && 1==sortRule}">
								      	<div onclick="sortQuery('1','0')">
								            <span>${LANG['website.admin.user.list.page.title.userstatus'] }</span>
							        		<a class="paixu01" href="javascript:;"><img style="vertical-align: middle;" src="/static/images/paixu01.png" width="6" height="13" /></a>
						           		 </div>
							    </c:if>
						  </th>
<!-- 					      <th class="">${LANG['website.admin.user.list.page.title.orgname'] }</th> -->
<!-- 					      <th class="actions">${LANG['website.common.option.operation'] }</th> -->
					</tr>
					<cc:siteList var="SITE_CHARGEMODE_NAMEHOST"/>
					<cc:base var="USERROLE_PARTICIPANT"/>
					<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
						<tr>
							<td class="first-child check">
							<c:choose>
								<c:when test="${SITE_CHARGEMODE_NAMEHOST!=site.chargeMode || (SITE_CHARGEMODE_NAMEHOST==site.chargeMode && USERROLE_PARTICIPANT==user.userRole) }">
									<input name="id" value="${user.id}"  type="checkbox"/>
								</c:when>
								<c:otherwise>
									<input name="id" value="${user.id}"  type="checkbox" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
							</td>
							<td class="" title="${user.loginName}"><a onclick="return toViewUser('${user.id}')">${user.loginName}</a></td>
							<td class=""  title="${user.trueName}">${user.trueName}</td>
							<td class="">
								<c:if test="${user.userRole eq '1'}">
					            	${LANG['site.admin.edituser.role1']}
					            </c:if>
					            <c:if test="${user.userRole eq '2'}">
						            ${LANG['site.admin.edituser.role2']}
					            </c:if>
							</td>
					        <td class="" title="${user.userEmail}">${user.userEmail}</td>
					        <td class="" title="${user.mobile}">${user.mobile}</td>
					        <c:if test="${user.userStatus eq '1'}">
					       		<td class="status">${LANG['site.admin.userlist.active']}</td>
					       	</c:if>
					        <c:if test="${user.userStatus eq '0'}">
					        	<td class="status lock">${LANG['system.site.list.Status.lock']}</td>
					        </c:if>
<!-- 							<c:set var="orgName" value="${orgNamesMap[user.id]}"/> -->
<!-- 					        <td class="" title="${orgName }">${orgName }</td> -->
<!-- 							<td class="last-child actions"> -->
<!-- 								<a onclick="return toEditUser('${user.id }')">${LANG['website.common.option.modify'] }</a> -->
<!-- 								<a onclick="return parent.showAttendConfs('${user.id }')" style="margin-left:10px;">${LANG['website.admin.user.list.page.title.join.confinfo'] }</a> -->
<!-- 								<c:if test="${SITE_CHARGEMODE_NAMEHOST!=site.chargeMode || (SITE_CHARGEMODE_NAMEHOST==site.chargeMode && USERROLE_PARTICIPANT==user.userRole) }"> -->
<!-- 									<a onclick="return delUser('${user.id }','${user.userRole}');" style="margin-left:10px;">${LANG['website.common.option.delete'] }</a> -->
<!-- 								</c:if> -->
<!-- 							</td> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagebar clearfix">
				<jsp:include page="page.jsp" />
			</div>
			</c:if>
		</div>
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/static/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript">
//查看用户
function toViewUser(id){
	parent.viewUser(id);
}
//删除用户，id，role 参数为空时，批量删除勾选用户
function delUser(id,role){
	//alert("AAAA");
	var chargeModel = '${site.chargeMode}';
	if(!id){
		if($("input[name=id]:checked").length<=0){
			parent.parent.promptDialog("${LANG['site.admin.userlist.info1']}");
			return;
		}
		
		if(chargeModel=='1' && $("input[role=1]:checked").length>0){
			parent.parent.promptDialog("${LANG['admin.site.userlist.namehostundelete']}");
			$("input[role=1]:checked").each(function(){
				$(this).attr("checked",false);
			});
			$("#checkAll").attr("checked",false);
			return;
		}
		parent.parent.promptDialog("${LANG['site.admin.userlist.info2']}", function() {
				parent.successDialog("${LANG['bizconf.jsp.admin.action.delete.success']}");
				$("#query").attr("action","/admin/entUser/delSiteUsers");
				$("#query").submit();
		});
	}else{
		if(chargeModel=='1' && role =='1'){
			parent.parent.promptDialog("${LANG['admin.site.userlist.namehostundelete']}");	
			return;
		}
		parent.parent.promptDialog("${LANG['site.admin.userlist.info2']}", function() {
			parent.successDialog("${LANG['bizconf.jsp.admin.action.delete.success']}");
			$("input[name=id]").each(function(){

				if($(this).val()==id){
					$(this).attr("checked",true);
				}else{
					$(this).attr("checked",false);
				}
			});

			$("#query").attr("action","/admin/entUser/delSiteUsers");
			$("#query").submit();
		});
	}
}
//添加用户
function toEditUser(id){
	parent.createOrUpdateSiteUser(id);
}
//批量导入用户
function doImport() {
	parent.importUser();
}
//批量导出用户
function exportExcel(){
	$("#query").attr("action","/admin/entUser/exportUser");
	$("#query").submit();
	$("#query").attr("action","/admin/entUser/listAll");
}
//锁定用户
function lockUser(){
	if($("input[name=id]:checked").length<=0){
		parent.parent.promptDialog("${LANG['site.admin.userlist.info3']}");
		return;
	}
	parent.parent.promptDialog("${LANG['site.admin.userlist.info4']}", function(){
			$("#query").attr("action","/admin/entUser/lockSiteUsers");
			$("#query").submit();
			parent.successDialog("${LANG['bizconf.jsp.admin.site_user_list.res1']}");
	});
}
//解锁用户
function unlockUser(){
	if($("input[name=id]:checked").length<=0){
		parent.parent.promptDialog("${LANG['site.admin.userlist.info5']}");
		return;
	}
	parent.parent.promptDialog("${LANG['site.admin.userlist.info6']}", function() {
			$("#query").attr("action","/admin/entUser/unlockSiteUsers");
			$("#query").submit();
			parent.successDialog("${LANG['bizconf.jsp.admin.site_user_list.res2']}");
	});
}

$(document).ready(function(){
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=keyword]").watermark("${LANG['bizconf.jsp.bill_detaillist.res3']}");
	}
	//全选非全选
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
			$("#query").attr("action","/admin/entUser/listAll");
			$("#query").submit();
		}
	});
	
	$("#btn_search").click(function(){
		$("#pageNo").val("");
		$("#query").attr("action","/admin/entUser/listAll");
		$("#query").submit();
	});
});

function sortQuery(sortField,sortord){
	//alert('aaa');
	if(!sortord){
		if($("#sortRule").val()=='0'){
			sortord = "1";
		}else{
			sortord = "0";
		}
	}
	$("#sortField").val(sortField);
	$("#sortRule").val(sortord);
	$("#query").attr("action","/admin/entUser/listAll");
	$("#query").submit();
}
</script>
</body>
</html>