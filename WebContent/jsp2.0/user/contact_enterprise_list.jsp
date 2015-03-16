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
<input type="hidden" id="orgId"  name="orgId" value="${orgId}"/>

	<div class="choose-dialog">
		<div class="wrapper">
			<div class="choose-panel">
				<div class="tabview">
					<div class="view active">
						<div class="filter clearfix">
							<div class="search">
								<input type="text" class="input-text register-search" name="keyword" value="${keyword}" placeholder="${LANG['website.user.contact.list.placeholder1']}">
								<button class="submit-search" type="button" onclick="refreshList();">${LANG['swebsite.common.search.name']}</button>
							</div>
							<div class="groups">
								 <c:if test="${!empty orgList }">
					                <select id="orgLevel1" name="">
					                	<option value="0">${LANG['website.user.regsiter.show.selectdept']}</option>
					                	<c:forEach var="org" items="${orgList}" >
					              			<c:if test="${org.orgLevel==1}">
					              				<option level="${org.orgLevel }" value="${org.id}" <c:if test="${org.id eq orgId}">selected="selected"</c:if> >${org.orgName}</option>
					              			</c:if>
							            </c:forEach>
					              	</select>
					            </c:if>
								<select id="orgLevel2" style="display: none;">
									<option value="0">${LANG['website.user.regsiter.show.pleaseselect']}</option>
								</select>
								<select id="orgLevel3" style="display: none;">
									<option value="0">${LANG['website.user.regsiter.show.pleaseselect']}</option>
								</select>
								<select id="orgLevel4" style="display: none;">
									<option value="0">${LANG['website.user.regsiter.show.pleaseselect']}</option>
								</select>
							</div>
						</div>
						<div class="render">
							<iframe frameborder="0" width="100%" style="height: 379px;overflow-x: hidden;" id="orgListFrame" name="orgListFrame" scrolling="no" src="/user/contact/showEnterpriseOrgContacts?showAll=${showAll}"></iframe>
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
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
function getContactsData(){
	return $("#orgListFrame")[0].contentWindow.getContactsData();
}

function importContacts(){
	$("#orgListFrame")[0].contentWindow.importContacts();
}
function getContactsIds() {
	return $("#orgListFrame")[0].contentWindow.getContactsIds();
}

$(document).ready(function(){
	if ($.browser.msie && $.browser.version<10) {
		$("input[name=keyword]").watermark("${LANG['website.user.contact.list.placeholder1']}");
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
	
	
	$("#orgLevel1").change(function() {
		var level4 = $("#orgLevel4");
		var level3 = $("#orgLevel3");
		var level2 = $("#orgLevel2");
		level4.hide().find(".org-option").remove();
		level3.hide().find(".org-option").remove();
		level2.find(".org-option").remove();
		var id = $(this).val();
		if(id==0){
			id = $(this).val();
			level2.hide();
		} else {
			initLevelOrg(id, level2);
		}
		refreshList(id);
    });
	
	$("#orgLevel2").change(function() {
		var level4 = $("#orgLevel4");
		var level3 = $("#orgLevel3");
		var level1 = $("#orgLevel1");
		level4.hide().find(".org-option").remove();
		level3.find(".org-option").remove();
		var id = $(this).val();
		if(id==0){
			id = level1.val();
			level3.hide();
		} else {
			initLevelOrg(id, level3);
		}
		refreshList(id);
    });
	
	$("#orgLevel3").change(function() {
		var level4 = $("#orgLevel4");
		var level2 = $("#orgLevel2");
		level4.find(".org-option").remove();
		var id = $(this).val();
		if(id==0){
			id = level2.val();
			level4.hide();
		} else {
			initLevelOrg(id, level4);
		}
		refreshList(id);
    });
	
	$("#orgLevel4").change(function() {
		var level3 = $("#orgLevel3");
		var id = $(this).val();
		if(id==0){
			id = level3.val();
		}
		refreshList(id);
    });
	$("input[name=keyword]").keypress(function(e){
		if(e.keyCode == 13){
			refreshList();
			return false;
		}
	});	
});

function initLevelOrg(id, elem){
	app.getLevelOrg(id, function(result) {
		if(result && result.status==1){
			var orgUserList = result.orgUserList;
			if(orgUserList && orgUserList.length>0){
				for ( var i = 0; i < orgUserList.length; i++) {
					var org = orgUserList[i];
					if(org){
						$("<option class='org-option' value="+org.id+">"+org.orgName+"</option>").appendTo(elem);	
					}	
				}
				elem.show();	
			}
		}
	});
}


function refreshIHeight() {
	var height = $("#orgListFrame").contents().find("body").height() + 50;
	$("#orgListFrame").height(height);  
}

function refreshList(id) {
	var url = "/user/contact/showEnterpriseOrgContacts?showAll=${showAll}";
	var orgId = $("#orgId").val();
	if(id){
		orgId = id;
		$("#orgId").val(id);
	}
	if(orgId){
		url += "&orgId="+orgId;
	}
	var keyword = $("input[name=keyword]").val();
	if(keyword && keyword!="${LANG['website.user.contact.list.placeholder1']}"){
		url += "&keyword="+encodeURIComponent(encodeURIComponent(keyword));
	}
	$("#orgListFrame").attr("src", url);
	//$("#query").attr("target","orgListFrame");
	//$("#query").submit();
}
</script>
</body>
</html>