<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${LANG['bizconf.jsp.system.email_template.res1']}</title>
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
	<form name="templateForm" id="templateForm" action="${ctx}/system/email/saveTemplate" method="post">
	<input type="hidden" name="id" id="id" value="${emailTemplate.id}"/>
	<input type="hidden" name="siteId" id="siteId" value="${emailTemplate.siteId}"/>
		<tr>
			<td colspan="2" align="center"  >${LANG['bizconf.jsp.system.email_template.res1']}</td>
		</tr>
		 
		<tr  >
			<td   align="right" style="width:180px;">&nbsp;${LANG['bizconf.jsp.system.email_template.res2']}</td>
			<td> <input type="text" name="emailTitle" id="emailTitle" value="${emailTemplate.emailTitle}" /></td>
		 </tr> 
		<tr  >
			<td   align="right" style="width:180px;">&nbsp;${LANG['bizconf.jsp.system.email_template.res3']}</td>
			<td> <input type="text" name="emailType" id="emailType" value="${emailTemplate.emailType}" /></td>
		 </tr> 
		<tr  >
			<td   align="right" style="width:180px;">&nbsp;${LANG['bizconf.jsp.system.email_template.res4']}</td>
			<td> <textarea name="emailContent" id="emailContent" >${emailTemplate.emailContent}</textarea></td>
		 </tr> 
		
 
		 
		 <tr>
			<td colspan="2"   align="center"  style="padding-bottom:10px"><input type="button" name="saveBtn" onclick="javascript:saveTempalte();" value="${LANG['bizconf.jsp.system.email_template.res5']}"/></td>
		</tr>
		</form>
		
	</table>
</body>
</html>
<script type="text/javascript">

 
	function saveTempalte(){
		$("#templateForm").submit();
	}
</script>
