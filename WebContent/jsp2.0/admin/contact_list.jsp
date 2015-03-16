<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
</head>
<body style="padding: 0px;">
<div class="tabview">
	<div class="views">
		<form id="query" name="query" action="/admin/contact/list" method="post">
		<input type="hidden"  name="group_id" value="${group_id}"/>
		<div class="summary">
			<ul class="actions">
				<li class=""><a onclick="return contactEditSite();"><i class="icon icon-add"></i>${LANG['website.user.contact.list.addcontact']}</a></li>
				<li class=""><a onclick="return doImport();"><i class="icon icon-imp"></i>${LANG['website.admin.user.list.page.btn.import'] }</a></li>
				<c:if test="${fn:length(pageModel.datas) gt 0}">
					<li class="mr0"><a onclick="return deleteContactBatch();"><i class="icon icon-del"></i>${LANG['website.user.contact.list.batchdel']}</a></li>
				</c:if>
			</ul>
			<div class="search">
					<input type="text" name="keyword"  value="${keyword}" class="input-text" placeholder="${LANG['website.user.contact.list.placeholder1']}" />
					<button class="submit-search" type="submit" onclick="submitForm();" >${LANG['swebsite.common.search.name']}</button>
			</div>
		</div>
		<div class="view active" data-view="users">
			<c:if test="${fn:length(pageModel.datas) gt 0}">
				<table class="common-table contact-list-table" style="table-layout:fixed;">
					<tbody>
						<tr>
							<th class="check"><input id="checkAll" type="checkbox"/></th>
							  <th class="name">${LANG['website.user.contact.list.name']}</th>
						      <th class="email">${LANG['website.user.contact.list.titleemail']}</th>
						      <th class="landline">${LANG['website.user.contact.list.titlephone']}</th>
						      <th class="mobile">${LANG['website.user.contact.list.titlemobile']}</th>
						      <th class="actions">${LANG['website.user.contact.list.titleactions']}</th>
						</tr>
						<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
							<tr>
								<td class="first-child check"><input name="id" value="${contact.id}"  type="checkbox"></td>
								<td class="name" title="${contact.contactName}">${contact.contactName}</td>
						        <td class="email" title="${contact.contactEmail}">${contact.contactEmail}</td>
						        <td class="landline" title="${contact.contactPhone}">${contact.contactPhone}</td>
						        <td class="mobile" title="${contact.contactMobile}">${contact.contactMobile}</td>
								<td class="last-child actions">
									<a class="modify" onclick="return contactEditSite('${contact.id }')"><i class="icon icon-modify"></i>${LANG['website.common.option.modify']}</a>
									<a class="remove" onclick="return del('${contact.id }')"><i class="icon icon-remove"></i>${LANG['website.common.option.delete']}</a>
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
					<div class="nodata">${LANG['website.user.contact.list.nodata']}</div>
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
function contactEditSite(id) {
	top.contactEditSite(id);
}

function del(id){
	parent.parent.promptDialog("${LANG['website.user.contact.list.deleteconfirm']}", function() {
		//alert('call back!');
		window.location = "/admin/contact/deleteContact?id="+id;
		parent.parent.successDialog("${LANG['website.user.contact.list.warninfo1']}");
	});
}
//批量删除
function deleteContactBatch(){
	if($("input[name=id]:checked").length>0){
		parent.parent.promptDialog("${LANG['website.user.contact.list.deleteconfirm']}", function() {
				//$("#query").attr("action","/user/contact/deleteContactBatch");
				//$("#query").submit();
				var temp = new Array();
				$("input[name=id]:checked").each(function(i,val){
					temp[i] = $(this).val();
				});
				$.ajax({
			        type: "POST",
			        url: "/admin/contact/deleteContactBatch",
			        data: {id:temp},
			        success: function(data, textStatus, XmlHttpRequest) {
			        	parent.parent.successDialog("${LANG['website.user.contact.list.deleteconfirm.success.tip']}");
			        	window.location = "/admin/contact/list";
			        },
			        error: function(XmlHttpRequest, textStatus, errorThrown) {
			        	parent.parent.errorDialog("${LANG['website.user.contact.list.deleteconfirm.error.tip']}");
			        	window.location = "/admin/contact/list";
			        }
			    });
		});
	}else{
		parent.parent.errorDialog("${LANG['website.user.contact.list.warninfo2']}");
	}
}

function toExportContact(id){
	$("#query").attr("action","/user/contact/exportContacts");
	//$("#query").attr("target","downFrame");
	$("#query").submit();
	$("#query").attr("action","/user/contact/list");
	//$("#query").removeAttr("target");
}

/** 批量导入 */
function doImport(){
	parent.parent.bulkImportContact();
}

function submitForm(){
	$("#pageNo").val("");
	query.submit();
}

$(document).ready(function(){
	parent.refreshIHeight();
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=keyword]").watermark("${LANG['website.user.contact.list.placeholder1']}");
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
			$("#query").attr("action","/admin/contact/list");
			$("#query").submit();
		}
	});
});
function countLength(str){
	if(str)
	return  str.length;
	else
		return 0 ;
}
</script>
</body>
</html>