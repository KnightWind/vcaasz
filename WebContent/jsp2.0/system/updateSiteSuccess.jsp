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
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<style>
body{ margin:0; border:0; padding:0; }
.zd_end{ margin-left:114px; float:left; padding-top:10px;}
.zd_top h1{ margin-left:30px; font-size:20px; color:#74B72C; float:left; padding-top:20px;}
.xinxi{ clear:both; padding-left:130px; }
.xinxi li{ list-style:none; font-size:12px; line-height:18px;margin-bottom: 3px;}
.zd span{ float:left; display:block; clear:left;}
.zdxinxi{ float:left;}
.xx{ padding-left:60px;}
.button-small {
    background: url("/static/images/button01.jpg") no-repeat scroll center center #663399;
    border: 0 none;
    color: #333333;
    cursor: pointer;
    height: 24px;
    line-height: 24px;
    margin: 7px;
    text-align: center;
    width: 72px;
}
span.jqTransformCheckboxWrapper {
margin: 0px;
}
.right-td {
	padding-left: 5px;
	width:50%;
}
.left-td {
	width:50%;
}
</style>
<!-- Javascript -->
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
</head>
<body onload="loaded()">
	<form id="siteSuccessForm" action="">
		<div class="zd">
			<div class="zd_top">
				<img class="zd_end" src="/static/images/change_01.jpg" width="49"
					height="52" />
				<h1>${LANG['system.site.update.success']}</h1>
			</div>
			<table style="clear: both;font-size: 12px;margin: 0 auto;width: 380px;height:230px;">
				<tr>
					<td align="right" valign="top" class="left-td">${LANG['system.menu.site.list']}: ${LANG['system.site.list.CompanyName']}:</td>
					<td align="left" class="right-td">${siteBase.siteName}</td>
				</tr>
				<tr>
					<td align="right" valign="top" class="left-td">${LANG['system.site.list.EnglishName']}:</td>
					<td align="left" class="right-td">${siteBase.enName}</td>
				</tr>
				<tr>
					<td align="right" class="left-td">${LANG['system.site.list.SiteSign']}:</td>
					<td align="left" class="right-td">${siteBase.siteSign}</td>
				</tr>
				<c:if test="${siteBase.siteFlag == 1 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.SiteFlag']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.SiteFlag.official']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.siteFlag == 2 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.SiteFlag']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.SiteFlag.trial']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.hireMode == 1 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.hireMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.hireMode.month']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.hireMode == 2 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.hireMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.hireMode.minutes']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.chargeMode == 1 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.chargeMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.chargeMode.Name Host']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.chargeMode == 2 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.chargeMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.chargeMode.Active User']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.chargeMode == 3 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.chargeMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.chargeMode.Seats']}</td>
					</tr>
				</c:if>
				<c:if test="${siteBase.chargeMode == 4 }">
					<tr>
						<td align="right" class="left-td">${LANG['system.site.list.chargeMode']}:</td>
						<td align="left" class="right-td">${LANG['system.site.list.chargeMode.time']}</td>
					</tr>
				</c:if>
				<tr>
					<td align="right" class="left-td">${LANG['system.site.list.effedate']}:</td>
					<td align="left" class="right-td"><fmt:formatDate  value="${siteBase.effeDate}" type="date" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right" class="left-td">${LANG['system.site.list.ExpireDate']}:</td>
					<td align="left" class="right-td"><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td align="right" class="left-td">${LANG['company.menu.admin.user']}:</td>
					<td align="left" class="right-td">${siteAdmin.trueName}</td>
				</tr>
				<tr>
					<td align="right" class="left-td"><input type="checkbox" id="sendEmail"/>&nbsp;${LANG['system.site.update.sendemail']}:</td>
					<td align="left" class="right-td">${siteAdmin.trueName}</td>
				</tr>
			</table>
		</div>
		<div style="text-align: center">
			<input  class="button-small closeButton skipThese" type="button" value="${LANG['system.ok']}">
		</div>		
	</form>	
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#siteSuccessForm").find("input").not(".skipThese").uniform();
	$(".closeButton").click(function() {
		var checked = $("#sendEmail").attr("checked");
		if (checked) {
			var siteInfo = {};
			var userInfo = {};
			siteInfo.id = ${siteBase.id};
    		var Pwd = parent.$("#mainFrame").data("PWD");
    		if (Pwd && Pwd.length>0) {
    			userInfo.loginPass = Pwd;
    		}
    		parent.$("body").trigger("sendEmail", [siteInfo, userInfo]);
		}
		var frame = parent.$("#siteSuccessDiv");
		frame.trigger("closeDialog");
	});
});
</script>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#siteSuccessDiv");
	frame.trigger("loaded");
}
</script>
