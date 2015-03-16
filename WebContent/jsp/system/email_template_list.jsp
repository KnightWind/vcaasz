<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${LANG['bizconf.jsp.system.email_template_list.res1']}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${ctx}/script/jquery-1.8.2.js"></script> 
	</head>
	<body>
	<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:500px">
	<form name="configForm" id="configForm" action="${ctx}/system/email/savehost" method="post">
	<input type="hidden" name="id" id="id" value="${emailConfig.id}"/>
	<input type="hidden" name="siteId" id="siteId" value="${emailConfig.siteId}"/>
		<tr>
			<td colspan="5" align="center"  >${LANG['bizconf.jsp.system.email_template_list.res1']} <input type="button" onclick="javascript:window.open('${ctx}/system/email/template/0-0')" value="${LANG['bizconf.jsp.system.email_template_list.res2']}"/></td>
		</tr>
		 
		<tr>
			<td align="center" style="width:100px;">&nbsp;${LANG['bizconf.jsp.system.email_template_list.res3']}</td>
			<td align="center" style="width:100px;">&nbsp;${LANG['bizconf.jsp.system.email_template_list.res4']}</td>
			<td align="center" style="width:100px;">&nbsp;${LANG['bizconf.jsp.system.email_template_list.res5']}</td>
			<td align="center" style="width:100px;">&nbsp;${LANG['bizconf.jsp.system.email_template_list.res6']}</td>  
			<td align="center" style="width:100px;">&nbsp;${LANG['bizconf.jsp.admin.site_org_list.res6']}</td>  
		 </tr> 
		<tr>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >[${LANG['bizconf.jsp.system.email_template_list.res7']}][${LANG['bizconf.jsp.system.email_template_list.res8']}]</td>
			
		 </tr> 
		 <tr>
			<td colspan="5" align="center"  >&nbsp;</td>
		</tr>
		</form>
	</table>
</body>
</html>
<script type="text/javascript">

 
	function saveHost(){
		$("#configForm").submit();
	}
</script>
