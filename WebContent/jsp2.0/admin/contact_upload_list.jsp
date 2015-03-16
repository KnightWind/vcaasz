<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
	<style>
		#ui-upload-holder {
			position: relative;
			width: 80px;
			height: 28px;
			border: 1px solid silver;
			overflow: hidden;
			border-radius: 3px;
			display: inline-block;
			vertical-align: middle;
		}
		
		#ui-upload-txt {
			position: absolute;
			top: 0px;
			left: 0px;
			width: 100%;
			height: 100%;
			line-height: 28px;
			text-align: center;
			font-size: 12px;
			color: #333333;
			font-family: "Microsoft Yahei", "微软雅黑", "宋体"
		}
		
		.ui-upload-button {
			background: #FFFFFF;
			background: linear-gradient(top, #FFFFFF, #ECECEC);
			background: -moz-linear-gradient(top, #FFFFFF, #ECECEC);
			background: -webkit-linear-gradient(top, #FFFFFF, #ECECEC);
			border-color: #CCCCCC;
			color: #666666;
			box-shadow: 0 1px #F7F7F7 inset;
			filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
				startColorstr=#FFFFFF, endColorstr=#ECECEC);
		}
		
		.ui-upload-button:hover {
			background: #FDFDFD;
			background: linear-gradient(top, #FFFFFF, #F7F7F7);
			background: -moz-linear-gradient(top, #FFFFFF, #F7F7F7);
			background: -webkit-linear-gradient(top, #FFFFFF, #F7F7F7);
			text-decoration: none;
			box-shadow: 0 -1px #F0F0F0 inset;
			filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,
				startColorstr=#FFFFFF, endColorstr=#F7F7F7);
		}
		
		.ui-upload-input {
			position: absolute;
			top: 0px;
			right: 0px;
			height: 100%;
			cursor: pointer;
			opacity: 0;
			filter: alpha(opacity : 0);
			z-index: 999;
		}	
	</style>
</head>
<body style="padding: 0px;">
<div class="import-dialog">
<div class="upload-contact" style="height:170px;padding-left: 4px;margin-top:0px; ; margin-left:0px;margin-right:0; width: 99%;border:0px solid #cccccc;position: center;">
	<form id="uploadForm" action="/admin/contact/importBatchByContacts" method="post" enctype="multipart/form-data" style="padding-top: 20px;">
		<label class="title" style="padding-left: 20px;margin-bottom: 20px;margin-top: 20px;">${LANG['website.user.contact.import.excelfile']}</label>
		<div class="widget" style="padding-left: 20px;margin-bottom: 20px;margin-top: 20px;">
			<input type="text" readonly="readonly" id="showfile" class="input-text" />
			<a id="ui-upload-holder" class="ui-upload-button"> 
				<div id="ui-upload-txt">${LANG['website.user.contact.import.select.excelfile']}</div>
				<input class="ui-upload-input" type="file" name="excelfile" id="fileselect" title="${LANG['website.user.contact.import.select.excelfile']}"/>
			</a>
			<a class="download" href="/admin/contact/downloadContactsTemplate">${LANG['website.user.contact.import.downloadfile']}</a>
		</div>
		<div class="form-buttons" style="margin-top: 70px;margin-bottom: 10px;padding-top: 10px;"> 
			<input type="button" class="button input-gray" onclick="importContacts();" value="${LANG['website.common.option.confirm']}">
			<a  class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
		</div>
	</form>
</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<script type="text/javascript">
	 	$(document).ready(function(){
			/**$("#selectbtn").click(function(){
				$("#fileselect").trigger("click");
			});
			$("#showfile").click(function(){
				$("#fileselect").trigger("click");
			});
			*/
			$("#fileselect").change(function(){
				$("#showfile").val($(this).val());
			});
			
	 		/**if($.browser.msie){
	 			var html = "<input type=\"file\" accept=\"application/vnd.ms-excel\"  id=\"iefileselect\"  name=\"excelfile\" class=\"input-text\" />";
	 				html += "<a class=\"download\" href=\"/user/contact/downloadContactsTemplate\">${LANG['website.user.contact.import.downloadfile']}</a>";
	 			$(".aaaaa").empty().html(html);
	 		}*/
	 	});
	  
	function importContacts(){
		 var file_input = document.getElementById("fileselect");
		 var suffix = "";
		 if(!file_input.value){
			top.errorDialog("${LANG['website.user.contact.importbyfile.warninfo1']}");
			return false;
		 }else{
			 suffix = file_input.value.substring(file_input.value.lastIndexOf("."));
			 if(suffix!=".xls" && suffix!=".xlsx"){
				 top.errorDialog("${LANG['website.user.contact.importbyfile.warninfo2']}");
				 return false;
			 }
		 }
		 //$("#uploadForm").submit();
		 document.getElementById("uploadForm").submit();
	}
</script>
</body>
</html>