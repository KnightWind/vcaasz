<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${LANG['bizconf.jsp.system.add_site_user.res1']}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/Popup.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/json2.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></script>
</head>
<body>
<form id="saveDataCenterForm" action="" method="post">
	<input type="hidden" id="id" name="id" value="${rcIP.id}" />
	<input type="hidden" id="centerId" name="centerId" value="${centerId}" />
	<table border="0" cellpadding="0" cellspacing="0" class="host_main" style="margin-top: 0px;">
	    <tr>
	    	<td class="host_left" align="right"><font color="red">*</font>IP地址:</td>
	        <td class="host_right"><input id="ipAddress" name="ipAddress" type="text" value="${rcIP.ipAddress}" ></td>
	    </tr>
	</table>
	<input type="button" style="margin-top: 5px;" onclick="javascript:save();" class="summit_btn Public_button" value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}"><a style="margin-top: 5px;" class="close_btn Public_button" href="#" onclick="closeDiaglog();">${LANG['bizconf.jsp.admin.createOrg.res4']}</a>
</form>
</body>
</html>
<script type="text/javascript">
function save(){
	var url = "/system/dataCenter/saveRcIp";
	var data = {};
	data.id = $("#id").val();
	data.centerId = $("#centerId").val();
	data.ipAddress = $("#ipAddress").val();
	ajaxPost(url, data, 
			function(result){//保存完成
				if(result.status==0){//保存失败
					parent.errorDialog(result.message);
				}else{//保存成功
					parent.successDialog(result.message);
					if(parent.$("#listRCIpDiv").find("#dialogFrame")[0]){
						parent.$("#listRCIpDiv").find("#dialogFrame")[0].src = "/system/dataCenter/listIp?centerId="+${centerId};
					}
					closeDiaglog();
				}
			},null, null);
}

function closeDiaglog(result){
	var frame = parent.$("#addRCIpDiv");
	frame.trigger("closeDialog", result);
}
</script>