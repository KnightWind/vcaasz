<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bizconf.vcaasz.entity.SiteBase"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.system.createSite.res12']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/script/jquery-1.8.2.js"></script> 
</head>
<body>
	<form name="loginform" action="/system/site/update" method="post">
		<table align = "center" style="width:500px">
			<tr>
				<td colspan="2" align="center">${LANG['bizconf.jsp.system.createSite.res12']}</td>
			</tr>
		</table>
		<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:500px">
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res16']}</td>
				<td><input name="siteName" type="text" value="${siteBase.siteName}"></td>
				<input name="id" type="hidden" value = "${siteBase.id}">
			</tr>
			
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res17']}</td>
				<td><input name="siteSign" type="text" value="${siteBase.siteSign}"></td>
			</tr>
<%-- 			<tr>
				<td>${LANG['bizconf.jsp.system.updateInfoBySiteAdmin.res1']}LOGO</td>
				<td><input name="siteLogo" type="text" value="${siteBase.siteLogo}"></td>
			</tr> --%>
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res18']}</td>
				<td>
					<input name="siteFlag" type="radio" value="1" checked="checked">${LANG['bizconf.jsp.system.updateSite.res1']}
					<input name="siteFlag" type="radio" value="2">${LANG['bizconf.jsp.system.updateSite.res2']}
				</td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res19']}</td>
				<td><input name="license" type="text" value="${siteBase.license}"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.updateSite.res3']}</td>
				<td>
					<input name="mtgType" type="radio" value="1" checked="checked">${LANG['bizconf.jsp.system.updateSite.res4']}
					<input name="mtgType" type="radio" value="2">${LANG['bizconf.jsp.system.updateSite.res4']}+${LANG['bizconf.jsp.system.updateSite.res5']}
					<input name="mtgType" type="radio" value="3">${LANG['bizconf.jsp.system.updateSite.res4']}+${LANG['bizconf.jsp.system.updateSite.res6']}
				
				</td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.site_info.res20']}</td>
				<td><input name="expireDate" type="text" value="${siteBase.expireDate}"></td>
			</tr>
			
			
			<tr>
				<td>${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
				<td><input name="clientName" type="text"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.updateSite.res7']}</td>
				<td><input name="loginName" type="text"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.admin.user_info.res3']}</td>
				<td><input name="loginPass" type="password"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.admin.arrange_org_user.res9']}</td>
				<td><input name="userEmail" type="text"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.system.updateSite.res8']}</td>
				<td><input name="mobile" type="text"></td>
			</tr>
			<tr>
				<td>${LANG['bizconf.jsp.admin.createOrg.res3']}</td>
				<td><input name="remark" type="text"></td>
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
