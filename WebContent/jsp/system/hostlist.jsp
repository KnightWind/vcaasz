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
<title>${LANG['bizconf.jsp.system.hostlist.res1']}</title>
</head>
<body onload="loaded()">
<div class="n_title"><a href="#" onclick="popUpHost('${siteId}','');">${LANG['bizconf.jsp.system.add_site_user.res1']}</a></div>
<table border="0" cellpadding="0" cellspacing="0" class="host_top">
	<tr height="30" class="host01">
    	<td width="100" align="center">${LANG['bizconf.jsp.admin.arrange_org_user.res7']}</td>
        <td width="150" align="center">${LANG['bizconf.jsp.admin.arrange_org_user.res9']}</td>
        <td width="80" align="center">${LANG['bizconf.jsp.system.hostlist.res2']}</td>
        <td width="170" align="center">${LANG['bizconf.jsp.admin.site_org_list.res6']}</td>  
    </tr>
</table>
 <div class="h_main" style="overflow-y: auto;height: 185px">
<table border="0" cellpadding="0" cellspacing="0" class="host_center" style="overflow:auto;">
	 <c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr  height="36" class="host02">
           <td colspan="4" width="540" align="center">
        		${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
    <c:forEach var="host" items = "${pageModel.datas}"  varStatus="status">
	    <tr height="26" class="host02">
	    	<td width="100" align="center" >${host.loginName}</td>
	        <td width="150" align="center">${host.userEmail}</td>
	        <td width="80" align="center" cla>${licnums[host.id]}</td>
	        <td width="230" align="center">
		        <a href="#" onclick="toLicenseManage(${host.id},${siteId})">${LANG['bizconf.jsp.system.hostlist.res3']}</a>
		        <a href="#" onclick="popUpHost(${siteId},${host.id})">${LANG['bizconf.jsp.system.email_template_list.res7']}</a>
		        <a href="#" onclick="del(${host.id});">${LANG['bizconf.jsp.system.email_template_list.res8']}</a>
	        </td>  
	    </tr>
    </c:forEach>
</table>  
</div>
<a href="#" class="fh_btn Public_button" onclick="closeDialog();">${LANG['bizconf.jsp.admin.createOrg.res4']}</a>
</div>
</body>
</html>
<script type="text/javascript">
function refreshPage(){
	window.location.reload(true);
}

function popUpCount() {
	parent.countManager();
}

function popUpHost(siteId,userId) {
		parent.createOrUpdateHost(siteId,userId);
}

function popUpdateHost(elem) {
	var data = $(elem).closest("tr").data("data");
	parent.createOrUpdateHost(data);	
}

function del(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.hostlist.res4']}",function(){
		window.location = "/system/lic/delHost?id="+id;
	});
}

function loaded() {
	var frame = parent.$("#hostManagerDiv");
	frame.trigger("loaded");
}
function closeDialog() {
	var frame = parent.$("#hostManagerDiv");
	frame.trigger("closeDialog");
}

//${LANG['bizconf.jsp.system.hostlist.res5']}
function toLicenseManage(userId,siteId){
	parent.licenseManage(userId,siteId);
}
</script>
