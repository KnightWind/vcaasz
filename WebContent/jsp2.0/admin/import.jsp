<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
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
<div class="import-dialog" style="height: 168px;overflow: hidden;">
	<div id="importholder" class="upload-contact form-body" style="display:none; margin: 30px auto 20px">${LANG['website.user.contact.import.loading']}<span id="moredot">.....</span></div>
	<form id="uploadForm" action="/admin/entUser/importUser" method="post" enctype="multipart/form-data">
		<div id="uploadbody" class="upload-contact form-body" style="margin: 30px auto 20px">
			<div class="form-item" style="height:35px;">
				<label class="title">${LANG['website.user.contact.import.excelfile']}</label>
				<div class="widget">
					<input type="text" readonly="readonly" id="showfile"   class="input-text" />
					<a id="ui-upload-holder" class="ui-upload-button"> 
						<div id="ui-upload-txt">${LANG['website.user.contact.import.select.excelfile']}</div>
						<input class="ui-upload-input" type="file" name="excelfile" id="fileselect" title="${LANG['website.user.contact.import.select.excelfile']}"/>
					</a>
					
<!-- 					<a id="testselectbtn" class="input-gray" style="position:relative;width:55px;height:28px;text-align:center;">选择文件 -->
<!-- 						<input type="file" name="excelfile" id="fileselect"  style="cursor: pointer;position:absolute;left:0;top:0;width:100%;height:100%;z-index:999;opacity:0;filter:alpha(opacity=0);"/> -->
<!-- 					</a> -->
					<a class="download" href="/admin/entUser/downTemp">${LANG['website.user.contact.import.downloadfile']}</a>
				</div>
			</div>
		</div>
		<div class="form-buttons import-dialog-buttons">
			<input type="button" class="button input-gray" onclick="importUser();" value="${LANG['website.common.option.confirm']}">
			<a  class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
		</div>
	</form>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
 	$(document).ready(function(){
		/**
 		$("#selectbtn").click(function(){
			$("#fileselect").trigger("click");
		});
		$("#showfile").click(function(){
			$("#fileselect").trigger("click");
		});
		
		$("#fileselect").change(function(){
			$("#showfile").val($(this).val());
		});
		
		
 		if($.browser.msie){
 			var html = "<input type=\"file\"  id=\"fileselect\" name=\"excelfile\" class=\"input-text\" />";
 				html += "<a class=\"download\" href=\"/admin/entUser/downTemp\">${LANG['website.user.contact.import.downloadfile']}</a>";
 			$(".widget").empty().html(html);
 		}
 		*/
 		$("#fileselect").change(function(){
			$("#showfile").val($(this).val());
		});
 	});
	 	
	function importUser(){
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
		 importWaiting();
	}
	
	
	function importWaiting(){
		$("#importholder").show();
		$("#uploadbody").hide();
		setInterval(function(){
			var text = $("#moredot").text();
			if(text){
				$("#moredot").text("");
			}else{
				$("#moredot").text(".....");
			}
		},500)
	}
</script>
</body>
</html>