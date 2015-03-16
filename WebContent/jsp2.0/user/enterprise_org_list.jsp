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
<form id="query" method="post" action="/user/contact/showEnterpriseOrgContacts">
	<input type="hidden"  name="showAll" value="${showAll}"/>
	<input type="hidden"  name="keyword" value="${keyword}"/>
	<input type="hidden" id="orgId"  name="orgId" value="${orgId}"/>
	
	
		<div class="choose-dialog">
			<div class="wrapper">
				<div class="choose-panel biz-import-contacts-bulk">
					<div class="tabview">
						<div class="view active">
							<div class="render">
								<c:if test="${fn:length(pageModel.datas)>0}">
									<table class="common-table">
										<tbody>
											<tr>
												<th class="check"><input type="checkbox" id="checkAll" /></th>
												<th class="user-name">${LANG['website.user.org.regsiter.list.name']}</th>
												<th class="user-enname">${LANG['website.user.org.regsiter.list.enname']}</th>
												<th class="user-email">${LANG['website.user.org.regsiter.list.email']}</th>
												<th class="user-tel">${LANG['website.user.org.regsiter.list.phone']}</th>
												<th class="user-call">${LANG['website.user.org.regsiter.list.mobile']}</th>
											</tr>
										</tbody>
									</table>
									<div class="panel">
										<table class="common-table">
											<tbody>
											<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
												<tr>
													<td class="first-child check"><input name="id" type="checkbox"  value="${contact.id}"/></td>
													<td class="user-name" name="contactName${contact.id}" title="${contact.contactName}">${contact.contactName}</td>
													<td class="user-enname" title="${contact.contactNameEn}">${contact.contactNameEn}</td>
													<td class="user-email" name="contactEmail${contact.id}" title="${contact.contactEmail}">${contact.contactEmail}</td>
													<td class="user-tel" name="contactPhone${contact.id}" title="${contact.contactPhone}">${contact.contactPhone}</td>
													<td class="last-child user-call" name="contactMobile${contact.id}" title="${contact.contactMobile}">${contact.contactMobile}</td>
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
										<div class="common-empty">${LANG['website.user.org.regsiter.list.nodata']}</div>
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
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
function getContactsData(){
	var datas = new Array();
	$("input[name=id]:checked").each(function(){
		var item = new Object();
		var id = $(this).val();
		var name= $("td[name=contactName"+id+"]").html();
		var email= $("td[name=contactEmail"+id+"]").html();
		var phone= $("td[name=contactPhone"+id+"]").html();
		var mobile= $("td[name=contactMobile"+id+"]").html();
		item.name = name;
		item.email = email;
		item.phone = phone||mobile;
		item.userId = "e"+id;
		datas.push(item);
	});
	return datas;
}

function getContactsIds() {
	if($("input[name=id]:checked").length<1) return null;
	var parms = "&userImportFlag=true";
	$("input[name=id]:checked").each(function(){
		parms += "&id="+$(this).val();
	});
	return parms;
}
//
function importContacts(){
	if($("input[name=id]:checked").length>0){
		$("#query").attr("action","/user/contact/importBatchByContacts");
		$("#query").submit();
	}else{
		top.errorDialog("${LANG['bizconf.jsp.contacts_import_main.res10']}");
	}
}

$(document).ready(function(){
	parent.$("input[name=keyword]").val("${keyword}");
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
</script>
</body>
</html>