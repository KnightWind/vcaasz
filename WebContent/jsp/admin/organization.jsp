<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.admin.organization.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>
<style type="text/css">
	.add_org {
	    background: url("/static/images/button01.jpg") no-repeat scroll 0 0 transparent;
	    cursor: pointer;
	    height: 24px;
	    line-height: 24px;
	    text-align: center;
	    width: 72px;
	    margin-left: 10px;
	    margin-top: 20px;
	    margin-bottom: 15px;
   	}		
</style>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript">
	

	function showURL(url) {
		url+="?t="+ Math.random();
		$('#orgListForm').attr("src", url);
	}
	
	function createOrg(id, level) {
		if(level==4){
			parent.errorDialog("${LANG['bizconf.jsp.admin.organization.res2']}");
		} else {
			parent.createOrg(id);	
		}
	}
	
	function assignUser() {
		var url = $("#orgListForm").attr("src");
		showURL(url);
	}
	
	function refreshIframe() {
		var url = $("#orgListForm").attr("src");
		showURL(url);
	}
	
	function refreshIHeight() {
		var height = $("#orgListForm").contents().find("body").height() + 50;
		$("#orgListForm").height(height);
		var pHeight = parent.$("#mainFrame").height();
	}

	</SCRIPT>
</head>
<body>
<div style="width: 100%;height:50px;">
	<input class="add_org" id="addOrg" type="button" value="${LANG['bizconf.jsp.admin.index.res9']}" onclick="createOrg(0)"/>
</div>

<div class="main_right">
	<iframe width="100%" height="100%" frameborder="0" id="orgListForm" name="orgListForm" scrolling="auto" src="/admin/org/orgList"></iframe>
</div>
</body>
</html>
