<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['system.site.list.create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Css -->	
	<style>
	
	</style>
	<!-- Javascript -->
	<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
</head>
<body onload="loaded()">
<table>
<tr>
	<td>${LANG['bizconf.jsp.admin.arrange_org_user.res7']}</td>
	<td><input type="text" name="loginName" id="loginName" value="test user"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.user_info.res3']}</td>
	<td><input type="password" name="loginPass" id="loginPass" value="asdasd"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.resetPass.res7']}</td>
	<td><input type="password" name="" id=""/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
	<td><input type="text" name="trueName" id="trueName" value="test user"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.user_info.res2']}</td>
	<td><input type="text" name="enName" id="enName" value="test"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.arrange_org_user.res9']}</td>
	<td><input type="text" name="userEmail" id="userEmail" value="alan@bizconf.cn"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.conf_list.res9']}</td>
	<td><input type="text" name="phone" id="phone" value="123456879"/></td>
</tr>
<tr>
	<td>${LANG['bizconf.jsp.admin.createOrg.res3']}</td>
	<td><input type="text" name="remark" id="remark" value="test"/></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td>
		<input type="button" name="" id="" value="${LANG['bizconf.jsp.common.bill_detaillist.res10']}" onclick="saveHost()"/>
		<input type="button" name="" id="" value="${LANG['bizconf.jsp.admin.createOrg.res4']}" onclick="closeDialog()"/>
	</td>
</tr>
</table>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#hostDiv");
	frame.trigger("loaded");
	var data = frame.data("data");
	if(data) {
		//console.log(data.loginName);	
	}
}
function saveHost() {
	var host = {};
	host.loginName = $("#loginName").val();
	host.loginPass = $("#loginPass").val();
	host.trueName = $("#trueName").val();
	host.enName = $("#enName").val();
	host.userEmail = $("#userEmail").val();
	host.phone = $("#phone").val();
	host.remark = $("#remark").val();
	
	closeDialog();
}
function closeDialog() {
	var frame = top.$("#hostDiv");
	frame.trigger("closeDialog");
}
</script>
