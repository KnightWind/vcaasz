<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_invite_user_list.res1']}</title>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript">
function closeDialog(){
	var frame = parent.$("#importDiv");
	frame.trigger("closeDialog");
}

function loaded(){
	var frame = parent.$("#importDiv");
	frame.trigger("loaded");
}

$(document).ready(function(){
	$("#btn_cancel").click(function(){
		closeDialog();
	});
	 
});

function showInfo(info,statu){
	parent.showImportInfo(info,statu);
	closeDialog();
}

function importContacts(){
	 var file_input = document.getElementById("fileipt");
	 var suffix = "";
	 if(!file_input.value){
		// parent.warningDialog("${LANG['site.user.import.warninfo1']}");
		parent.parent.errorDialog("${LANG['site.user.import.warninfo1']}");
		return false;
	 }else{
		 suffix = file_input.value.substring(file_input.value.lastIndexOf("."));
		 if(suffix!=".xls" && suffix!=".xlsx"){
			 //parent.warningDialog("${LANG['site.user.import.warninfo2']}");
			 parent.parent.errorDialog("${LANG['site.user.import.warninfo2']}");
			 return false;
		 }
	 }
	 document.forms[0].submit();
}
</script>
</head>
<body onload="loaded()">
<%--<div class="popup_box">--%>
<!--<div class="popup_box_head">
  <div class="popup_box_top_left"></div>
  <div class="popup_box_top_center">
    <h3>${LANG['bizconf.jsp.import.res1']}</h3>
    <a href="#"><img class="close_bg" src="/static/images/jz_out_bg.gif" width="18" height="18" /></a> </div>
  <div class="popup_box_top_right"></div>
</div>-->
<%--<div class="popup_box_main_a">--%>
<%--<div class="popup_box_main_left_a"></div>--%>
<%--<div class="popup_box_main_center_a">--%>
<%--  <div class="daoru_m">--%>
<%--	<form id="uploadForm" action="/user/contact/importBatchByContacts" method="post"--%>
<%--						enctype="multipart/form-data">--%>
<%--		<div style="margin-left: 30px;">--%>
<%--			<input type="file" id="fileipt" name="excelfile" />--%>
<%--		</div>--%>
<%--	</form>--%>
<%--	<a href="/user/contact/downloadContactsTemplate">${LANG['site.admin.import.downloadtemp']}</a>--%>
<%--  </div>--%>
<%--  <div class="popup_box_main_right_a"></div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<div class="hand_movement" ><img class="toa_quick_invite_a" src="/static/images/min.jpg" width="730" height="1" /></div>
       <form id="uploadForm" action="/user/contact/importBatchByContacts" method="post"
						enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="0" class="make_new">
        	<tr>
            	<td><h3 class="leading_in_mould">${LANG['bizconf.jsp.import.res2']}</h3></td>
                <td><input id="fileipt" name="excelfile" type="file"/></td>
                <td><a class="down_mould" href="/user/contact/downloadContactsTemplate" style="font-size: 12px; font-family: "${LANG['bizconf.jsp.conf_list_index.res2']}", Arial, Helvetica, sans-serif">${LANG['bizconf.jsp.import.res3']}</a></td>
                <td></td>
            </tr>
        </table>
 </form>
<%-- <div class="First_Steps_bottom_s" >--%>
<%--          <div class="but44"><a href="#"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px;"/>${LANG['bizconf.jsp.enContacts_list.res8']}</a></div>--%>
<%--          <div class="but09"><a href="#"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px; margin-left:10px;" />${LANG['bizconf.jsp.enContacts_list.res7']}</a></div>--%>
<%-- </div>--%>
</body>
</html>

