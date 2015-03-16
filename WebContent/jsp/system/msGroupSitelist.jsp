<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/Popup.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<title>群组使用站点列表</title>
</head>
<body onload="loaded()">

 <div class="h_main" style="overflow-y: auto;overflow-x:hidden;height: 360px">
<table border="0" cellpadding="0" cellspacing="0" class="host_top" style="width: 480px">
	<tr height="30" class="host01">
    	<td width="100" align="center">站点名称</td>
        <td width="150" align="center">站点标识</td>
    </tr>
 <c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr  height="36" class="host01">
           <td colspan="4" width="540" align="center">
        		${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
  </c:if>
    <c:forEach var="site" items = "${pageModel.datas}"  varStatus="status">
	    <tr height="26" class="host01">
	    	<td width="100" align="center" onclick="viewSite(${site.id})" ><span style="text-decoration: underline;cursor: pointer;">${site.siteName}</span></td>
	        <td width="150" align="center">${site.siteSign}</td>
	    </tr>
    </c:forEach>    
</table>
</div>
<%--<a href="#" class="fh_btn Public_button" onclick="closeDialog();">确定</a>--%>
</div>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#showSitesDiv");
	frame.trigger("loaded");
}
function closeDialog() {
	var frame = parent.$("#showSitesDiv");
	frame.trigger("closeDialog");
}

//${LANG['bizconf.jsp.system.hostlist.res5']}
function toLicenseManage(userId,siteId){
	parent.licenseManage(userId,siteId);
}

function viewSite(id) {
	parent.viewSite(id);
}
</script>
