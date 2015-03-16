<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bizconf.vcaasz.entity.SiteBase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form name="loginform" action="/system/site/updateSiteBySiteAdmin" method="post">
	
		<table>
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res16']}</td>
				<td><input name="siteName" type="text" value="${siteBase.siteName}"></td>
				<input name="id" type="hidden" value = "${siteBase.id}">
			</tr>
			
			<tr>
				<td>${LANG['bizconf.jsp.system.updateInfoBySiteAdmin.res1']}LOGO</td>
				<td><input name="siteLogo" type="text" value="${siteBase.siteLogo}"></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit"  value="${LANG['bizconf.jsp.common.bill_detaillist.res10']}"></td>
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
