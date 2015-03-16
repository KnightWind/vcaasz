<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理员登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script> 
	</head>
	<body>
	<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:500px">
	<form name="sysLoginForm" id="sysLoginForm" action="${ctx}/system/login/check" method="post">
		<tr>
			<td colspan="2" align="center"  >用户登录</td>
		</tr>
		 
		<tr  >
			<td   align="right" style="width:180px;">&nbsp;登 录 名：</td>
			<td> <input type="text" name="loginName" id="loginName" value="输入用户名" /></td>
		 </tr>
		<tr  >
			<td   align="right"  >&nbsp;登录密码：</td>
			<td><input type="password" name="loginPass" id="loginPass" value=""/></td>
		 </tr>
		<tr  >
			<td  align="right"   >验 证 码：</td>
			<td> <input type="text" name="authCode" id="authCode" value=""/></td>
		 </tr>
		 <tr>
			<td colspan="2"   align="center"  style="padding-bottom:10px"><input type="button" name="loginBtn" onclick="javascript:sysLogin();" value="登录"/></td>
		</tr>
		</form>
	</table>
</body>
</html>
<script type="text/javascript">


	$(document).ready(function() {
		$("#loginName").focus(function() {
			var tmpValue = $.trim($(this).val());
			if (tmpValue == "输入用户名") {
				$(this).val("");
			}
		}).blur(function() {
			//$(this).val("");//("focus")
			var tmpValue = $.trim($(this).val());
			if (tmpValue == "") {
				$(this).val("输入用户名");
			}
		});
	});
	
	function sysLogin(){
		$("#sysLoginForm").submit();
	}
</script>
