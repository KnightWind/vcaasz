<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>日志列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.2.js"></script> 
	</head>
	<body>
	<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:600px">
	<form name="configForm" id="configForm" action="${ctx}/system/email/savehost" method="post">
		<tr>
			<td colspan="6" align="center"  ><select name="">
				<option value="0">---</option>
				<option value="0">站点</option>
				<option value="0">公告</option>
				<option value="0">邮件服务器配置</option>
				<option value="0">邮件模板</option>
			</select></td>
		</tr>
		 
		<tr>
			<td align="center" style="width:100px;">&nbsp;类型(<c:if test="${sortField==1}">ASC</c:if><c:if test="${sortField==1}"><c:if test="${sortord==1}">ASC</c:if><c:if test="${sortField!=1}">DESC</c:if></c:if>)</td>
			<td align="center" style="width:100px;">&nbsp;时间(ASC/DESC)</td>
			<td align="center" style="width:100px;">&nbsp;操作模块</td>
			<td align="center" style="width:100px;">&nbsp;操作员</td>  
			<td align="center" style="width:100px;">&nbsp;操作对象</td>  
			<td align="center" style="width:100px;">&nbsp;IP地址</td>  
		 </tr> 
		<tr>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			<td >&nbsp;</td>
			
		 </tr> 
		 <tr>
			<td colspan="6" align="center"  >&nbsp;</td>
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
