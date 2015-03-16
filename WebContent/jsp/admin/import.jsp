<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.import.res1']}</title>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
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
	$("#btn_submit").click(function(){
		 var doc = document.getElementById("frameipt").contentWindow.document||document.getElementById("frameipt").contentDocument || document.frames["frameipt"].document; 
		 var file_input = doc.getElementById("fileipt");
		 var suffix = "";
		 if(!file_input.value){
			 parent.warningDialog("${LANG['site.user.import.warninfo1']}");
			 return false;
		 }else{
			 suffix = file_input.value.substring(file_input.value.lastIndexOf("."));
			 if(suffix!=".xls" && suffix!=".xlsx"){
				 parent.warningDialog("${LANG['site.user.import.warninfo2']}");
				 return false;
			 }
		 }
		 
		 $("#btn_submit").hide();
		 $("#emile_button_load").show();
		 doc.forms[0].submit();
	});
});

function showInfo(info,statu){
	parent.showImportInfo(info,statu);
	closeDialog();
}
</script>
</head>
<style>
* {
	margin:0px;
	padding:0px;
	font-size:12px;
}
ul, li {
	list-style:none;
}
.text_a {
	width:262px;
	height:22px;
	
	margin-left:30px;
	margin-top:10px;
}
.emile_01_bottom_a a {
	line-height:22px;
	margin-left:10px;
	color:#1F6AA1;
	display:block;
}
.emile_01_bottom_a {
	margin-top:10px;
}
.popup_box {
	width:415px;
	height:auto;
	margin:0px auto;
	margin-top:10px;
}
.popup_box_top_left {
	width:4px;
	height:30px;
	background:url(/static/images/Popup_header_left_bg.png) no-repeat;
	float:left;
}
.popup_box_top_center {
	width:407px;
	height:30px;
	background:url(/static/images/Popup_header_center_bg.png) repeat-x;
	float:left;
	position:relative;
}
.popup_box_top_right {
	width:4px;
	height:30px;
	background:url(/static/images/Popup_header_right_bg.png) no-repeat;
	float:left;
}
.popup_box_top_center h3 {
	line-height:30px;
	font-size:12px;
	padding-left:20px;
	color:#3F3F3F;
}
.popup_box_bottom {
	width:415px;
	height:4px;
}
.popup_box_bottom_left {
	width:4px;
	height:4px;
	background:url(/static/images/Popup_bottom_left_bg.png) no-repeat;
	float:left;
}
.popup_box_bottom_center {
	width:407px;
	height:4px;
	background:url(/static/images/Popup_bottom_center_bg.png) repeat-x;
	float:left;
}
.popup_box_bottom_right {
	width:4px;
	height:4px;
	background:url(/static/images/Popup_bottom_right_bg.png) no-repeat;
	float:left;
}
.close_bg {
	position:absolute;
	right:6px;
	float:left;
	top:6px;
}
.emile_01_bottom_a {
	padding-top:10px;
}
.emile_01_bottom_a li span {
	text-align:right;
	display:block;
	float:left;
	width:80px;
	line-height:22px;
}
.emile_01_bottom_a li {
	margin-top:10px;
}
.emile_button_d {
	width:72px;
	height:24px;
	background:url(/static/images/emile_button01.jpg) no-repeat;
	border:none;
	position:absolute;
	margin-top:20px;
	margin-left:30px;
	margin-bottom:15px;
}
#emile_button_load {
	height:24px;
	border:none;
	position:absolute;
	margin-top:20px;
	margin-left:30px;
	margin-bottom:15px;
	display: none;
}
.emile_button_d:hover {
	background:url(/static/images/emile_button_change.jpg) no-repeat;
}
.emile_button_f {
	width:72px;
	height:24px;
	background:url(/static/images/emile_button01.jpg) no-repeat;
	border:none;
	margin-top:20px;
	float:right;
	margin-right:30px;
	margin-bottom:15px;
}
.emile_button_f:hover {
	background:url(/static/images/emile_button_change.jpg) no-repeat;
}
.popup_box_main_a {
	width:415px;
	height:160px;
}
.popup_box_main_a_left {
	width:4px;
	height:160px;
	background:url(/static/images/Popup_main_left_bg.png) repeat-y;
	float:left;
}
.popup_box_main_a_center {
	width:407px;
	height:160px;
	background:#FFF;
	float:left;
}
.popup_box_main_a_right {
	width:4px;
	height:160px;
	background:url(/static/images/Popup_main_right_bg.png) repeat-y;
	float:left;
}
.red_star {
	position: relative;
	right: 5px;
	color: #FF0000
}

</style>

<body onload="loaded()">
<div class="popup_box">
<!--<div class="popup_box_head">
  <div class="popup_box_top_left"></div>
  <div class="popup_box_top_center">
    <h3>${LANG['bizconf.jsp.admin.import.res2']}</h3>
    <a href="#"><img class="close_bg" src="/static/images/jz_out_bg.gif" width="18" height="18" /></a> </div>
  <div class="popup_box_top_right"></div>
</div>-->
<div class="popup_box_main_a">
<div class="popup_box_main_left_a"></div>
<div class="popup_box_main_center_a">
  <div class="daoru_m">
    <iframe id="frameipt" src="/jsp/admin/import_user.jsp" width="65%" height="23px;" frameborder="0" scrolling="no"></iframe>
	<a href="/admin/entUser/downTemp">${LANG['site.admin.import.downloadtemp']}</a>
     <div class="button_submit">
     		<div id="emile_button_load">数据导入中, 请稍后...</div>
          <input name="emile_button_d" class="emile_button_d" type="button" id="btn_submit" value="${LANG['system.submit']}" />
          <input name="emile_button_f" class="emile_button_f" type="button" id="btn_cancel" value="${LANG['system.cancel']}" />
      </div>
  </div>
  <div class="popup_box_main_right_a"></div>
</div>
<!-- <div class="popup_box_bottom">
  <div class="popup_box_bottom_left"></div>
  <div class="popup_box_bottom_center"></div>
  <div class="popup_box_bottom_right"></div>
  </div> -->
</div>
</div>
</body>
</html>

