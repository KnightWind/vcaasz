<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
welcome:${currentSystemUser.loginName}


<br>
<br>
<a href="${ctx}/system/email/showhost" target="_blank">邮件服务器设置</a> </br>
<a href="${ctx}/system/site/list" target="_blank">站点信息</a></br>
<a href="${ctx}/system/notice/list" target="_blank">公告信息</a>
<br>
<a href="${ctx}/system/email/templist/0" target="_blank">邮件模板</a>
<br>
<a href="${ctx}/system/logs/list" target="_blank">查看系统管理员日志</a>
<br>
<br>
<br>
<br>
<br>

<a href=/system/logout>logout</a>
</body>
</html>