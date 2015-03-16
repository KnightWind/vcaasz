<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.system.updateNotice.res1']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/script/jquery-1.8.2.js"></script> 
</head>
<body>


	<form name="loginform" action="/system/notice/update" method="post">
		<table align = "center" style="width:500px">
			<tr>
				<td colspan="2" align="center">${LANG['bizconf.jsp.system.updateNotice.res1']}</td>
			</tr>
		</table>
		<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:500px">
			<tr>
				<td>${LANG['bizconf.jsp.system.updateNotice.res2']}</td>
				<td><input name="title" type="text" value="${notice.title}"></td>
				<input name="id" type="hidden" value = "${notice.id}">
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.updateNotice.res3']}</td>
				<td><input name="content" type="text" value="${notice.content}"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res20']}</td>
				<td><input name="stopTime" type="text" value="${notice.stopTime}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input name="submit" type="submit"  value="${LANG['bizconf.jsp.common.bill_detaillist.res10']}"></td>
			</tr>
		
		</table>
	
	
	</form>
	
	<script type="text/javascript">
		function saveSiteInfo(){
			
			document.getElementsByName("loginform")[0].submit();
		}
	</script>
</body>
</html>
