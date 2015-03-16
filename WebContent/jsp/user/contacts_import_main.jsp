<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<style type="text/css">
*html table {
	border-collapse: collapse;
}

*+html table {
	border-collapse: collapse;
}
</style>
<title>${LANG['bizconf.jsp.contacts_import_main.res1']}</title>
<style></style>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>	
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
	 
$(document).ready(function(){
	$(".cluster_link").click(function() {
		$(".cluster_link").removeClass("cluster_active");
		$(this).addClass("cluster_active");
	});
});
	function importByTemplate(){
		$("#importFlag").val("template");
		$("#contactFrame").attr("src","/user/contact/importContactsByExcel");
	}
	
	function importByEnContacts(){
		$("#importFlag").val("enContact");
		$("#contactFrame").attr("src","/user/contact/showEnterpriseContacts");	
	}
	
	function showInfo(info,status){
		if(status==1){
			parent.successDialog(info);
		}else{
			parent.errorDialog(info);
		}
		//${LANG['bizconf.jsp.contacts_import_main.res2']}
		parent.showURL("/user/contact/goContacts");
		closeDialog();
	}
</script>
</head>

<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinPublicDiv">
	<input id="importFlag" type="hidden" value="enContact"/>
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->  
		<div class="First_Steps_invite_m" style=" background:#FFF; border-radius:3px;">
	        <div class="First_Steps_title_invite"> <a href="javascript:;" onclick="closeDialog()"></a>
	          <h3 class="tit_a_invite">${LANG['bizconf.jsp.contacts_import_main.res3']}</h3>
	          <p class="tit_b_invite">${LANG['bizconf.jsp.contacts_import_main.res4']}</p>
	        </div>
	        <div style=" background:#fff"><img class="toa_quick_invite" src="/static/images/min.jpg" width="730" height="1" /></div>
	        <div class="First_Steps_top_invite" style=" background:#FFF;clear: both;height: 35px;">
	          <div class="cluster"><a class="cluster_link cluster_active" href="javascript:;" style="margin-left:10px;" onclick="importByEnContacts();"><img src="/static/images/tongxl.png" width="18" height="20" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.contacts_import_main.res5']}&nbsp;&nbsp;</a></div>
	          <div class="cluster"><a class="cluster_link" href="javascript:;" style="margin-left:10px;" onclick="importByTemplate();"><img src="/static/images/lianxiren.png" width="18" height="20" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.contacts_import_main.res6']}&nbsp;&nbsp;</a></div>
	        </div>
	        <!--${LANG['bizconf.jsp.contacts_import_main.res7']}-->
	        <div class="First_Steps_main_invite" style="width: 100%;height: 495px;">
	        	<iframe frameborder="0" width="100%" style="height: 440px;overflow-x: hidden;overflow-y: auto;" id="contactFrame" name="contactFrame" scrolling="yes" src="/user/contact/showEnterpriseContacts"></iframe>
	        	<!--${LANG['bizconf.jsp.contacts_import_main.res8']}-->
		        <div align="center" class="First_Steps_bottom_s">
		          <div class="but44"><span class="button_common"><a href="javascript:;" onclick="closeDialog()"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a></span></div>
		          <div class="but09"><span class="button_common"><a href="javascript:;" onclick="doImportContacts()"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px; margin-left:10px;" />${LANG['bizconf.jsp.add_contacts.res15']}</a></span></div>
		        </div>
	        </div>
      	</div>		
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->      
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>

</body>
</html>
<script type="text/javascript">
function doImportContacts() {
	var importFlag = $("#importFlag").val();
	if(importFlag == "enContact"){
		var data = window.frames["contactFrame"].getContactsIds();
		if(data && data.length>0){
			app.addContacts(data, function(result) {
				if(result){
					if(result.status==1){
						parent.successDialog(result.message);
						parent.showURL("/user/contact/goContacts");
						closeDialog();
					} else {
						parent.errorDialog(result.message);
					}
				}
			}, {message:"${LANG['bizconf.jsp.contacts_import_main.res9']}...", ui:parent});	
		} else {
			parent.errorDialog("${LANG['bizconf.jsp.contacts_import_main.res10']}");
		}		
	} else {
		window.frames["contactFrame"].importContacts();
	}

	//var data = {"name": "alan", "email": "asd@qq.com", "phone": "12312311"};
	//window.frames["contactFrame"].importContacts();
	//closeDialog();
} 
function loaded() {
	var dialog = parent.$("#importContact");
	dialog.trigger("loaded");
}
function closeDialog() {
	var dialog = parent.$("#importContact");
	dialog.trigger("closeDialog");
}
</script>
