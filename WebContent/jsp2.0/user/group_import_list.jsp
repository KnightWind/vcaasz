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
<form id="query" name="query" action="/user/group/importContacts" method="post" >
<input type="hidden"  name="group_id" value="${group_id}"/>
<div class="import-dialog">
	<div class="wrapper">
		<section>
			<div class="import-panel"> 
				<div class="tabview">
					<div class="views" style="margin-top: 0px;">
						<div class="filter clearfix">
							<div class="search" style="float: left;">
								<input type="text" name="keyword"  value="${keyword}" class="input-text" placeholder="${LANG['website.user.contact.list.placeholder1']}" />
								<button class="submit-search" type="button" >${LANG['swebsite.common.search.name']}</button>
							</div>
						</div>
						<div class="view active" data-view="users">
							<table class="common-table">
								<tbody>
									<tr>
										<th class="check"><input id="checkAll" type="checkbox"></th>
									  	<th class="name">${LANG['website.user.group.editmember.name']}</th>
								      	<th class="email">${LANG['website.user.contact.list.titleemail']}</th>
								      	<th class="landline">${LANG['website.user.contact.list.titlephone']}</th>
								      	<th class="mobile">${LANG['website.user.contact.list.titlemobile']}</th>
									</tr>
								</tbody>
							</table>
							<c:if test="${fn:length(pageModel.datas)>0 }">
								<div class="import-members-panel">
									<table class="common-table">
										<tbody>
											<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
												<tr>
													<td class="first-child check"><input name="id" value="${contact.id}"  type="checkbox"></td>
													<td class="name">${contact.contactName}</td>
											        <td class="email">${contact.contactEmail}</td>
											        <td class="landline">${contact.contactPhone}</td>
											        <td class="last-child mobile">${contact.contactMobile}</td>
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
								<div class="common-empty">${LANG['website.user.group.importbycontacts.nodata']}</div>
							</c:if>
						</div>
					</div>
				</div>	
			</div>		
		</section>
		<div class="form-buttons import-dialog-buttons">
			<input type="button" class="button input-gray" onclick="importContact();" value="${LANG['website.common.option.confirm']}">
			<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>		
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript">
function importContact() {
	if($("input[name=id]:checked").length==0){
		parent.errorDialog("${LANG['website.user.group.import.warninfo1']}");
		return;
	}
	var parms = "group_id=${group_id}";
	$("input[name=id]:checked").each(function(){
		parms += "&id="+$(this).val();
	});
	$.ajax({
      	type: "POST",
      	url:"/user/group/doImportSyn",
      	data:parms,
      	dataType:"json",
      	success:function(data){
      		parent.successDialog(data.message);
      		var win = parent.$("#viewGroup").find("iframe")[0].contentWindow || parent.$("#viewGroup").find("iframe")[0].window;
      		win.refreshData();
      		top.refreshChildIframe("/user/group/list","contentFrame");
      		closeDialog();
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
        	closeDialog();
        }
	});  
}

function closeDialog(result) {
	var dialog = parent.$("#importContact");
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