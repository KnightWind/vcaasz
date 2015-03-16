<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.admin.organization.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/popupbox.css"/>
<link rel="stylesheet" href="${baseUrlStatic}/css/user/zTreeStyle/zTreeStyle.css?ver=${version}" type="text/css">
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.ztree.core-3.5.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.ztree.exedit-3.5.min.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></SCRIPT>

<script type="text/javascript">
var zNodes =[];
</script>
<c:forEach var="org" items = "${pageModel.datas}">
<script type="text/javascript">
	var test = "${pageModel.datas}";
	var id = ${org.id};
	var pId = ${org.parentId};
	var name = "${org.orgName}";
	var node = {id: id, pId: pId, name:name, isParent:true};
	if(pId==0){
		node.open = true;
	}
	zNodes.push(node);
</script>	
</c:forEach>
<SCRIPT type="text/javascript">
	<!--
	var setting = {
		view: {
               dblClickExpand: dblClickExpand
        },
	    data: {
	    	keep: {
				parent:true,
				leaf:true
			},
	        simpleData: {
	            enable: true
	        }
	    },
	    callback: {
	        onClick: zTreeOnClick
	    }
	};
	
	function dblClickExpand(treeId, treeNode) {
        return treeNode.level > 0;
    }
	
	function zTreeOnClick(event, treeId, treeNode) {
	}
	
	$(document).ready(function(){
	    $.fn.zTree.init($("#treeOrg"), setting, zNodes);
	});
	
	function selectOrg() {
		var zTree = $.fn.zTree.getZTreeObj("treeOrg"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var frame = parent.$("#relateOrgDiv");
		var result = {};
		result.status = 1;
		result.org = treeNode; 
		frame.trigger("closeDialog", [result]);
	}
	
	function closeDialog() {
		var frame = parent.$("#relateOrgDiv");
		frame.trigger("closeDialog");
	}
	
	//-->
	</SCRIPT>
	<style type="text/css">
		.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
		.ztree li ul.level0 {padding:0; background:none;}
	</style>	
</head>
<body onload="loaded()">
<div style="margin: 20px;height: 260px;overflow-y: auto;">
	<ul id="treeOrg" class="ztree"></ul>
	<div style="width: 260px;margin-left: 25px;height: 30px;position: absolute;bottom: 10px;">
		<input name="emile_button" class="create_system_user_button skipThese" type="submit"  value="${LANG['system.submit']}" onclick="selectOrg()"/>
		<input name="emile_button02"  class="create_system_user_button closeButton skipThese" type="button"  value="${LANG['system.cancel']}" style="margin-left: 100px" onclick="closeDialog()"/>
	</div>
</div>

<script type="text/javascript">
function loaded() {
	var frame = parent.$("#relateOrgDiv");
	frame.trigger("loaded");
}
</script>
</body>
</html>
