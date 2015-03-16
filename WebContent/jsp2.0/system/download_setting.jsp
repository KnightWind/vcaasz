<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${LANG['system.notice.list.Create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />

	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>	
	<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/kindeditor-4.1.5/kindeditor.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
	
	<script type="text/javascript">
	
	</script>
</head>
<body onload="loaded();" style="font-size: 12px;padding: 10px 15px; margin: 0px;">
	<form id="settingForm" name="settingForm" action="/system/site/settingsave/${siteBase.id }" method="post">
		<table align="center" cellpadding="4" cellspacing="0" border="0px" border="1">
			<tr>
				<td height="30"  align="right">
						<span>系统默认地址：</span>
				</td>
				<td>&nbsp;${dofaultDownLoad }
				</td>
			</tr>
			<tr>
				<td height="30"  align="right">
						<span>原下载地址：</span>
				</td>
				<td>&nbsp;
				<c:set var="downloadUrl" value="${dofaultDownLoad }"/>
				<c:if test="${!empty siteBase.downloadUrl }">
				<c:set var="downloadUrl" value="${siteBase.downloadUrl }"/>
				</c:if>
				${downloadUrl }
				</td>
			</tr>
			<tr>
				<td  height="40" align="right" width="80px"><span class='red_star'>*</span>新下载地址：</td>
				<td style="line-height: 25px"><input id="downloadUrl" name="downloadUrl" type="text" class="input-sw form-uni-input" style="width:400px;"/><br/>
				为空时，则为系统默认的下载地址。<br/>格式：http://域名:端口号/download ，域名与端口号必填
				</td>
			</tr>
			
		
			<td colspan="2" align="center">
				<input type="button"  value="保存"  onclick="javascript:saveDownLoad();" class="form-button closeButton"/>
			</td>
		
		</table>
	
	
	</form>
			
</body>
</html>


<script type="text/javascript">
function loaded() {
	var frame = parent.$("#settingDiv");
	frame.trigger("loaded");
}

function saveDownLoad(){
	
	$("#settingForm").submit();
	
}
</script>