<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
function fileUpload(value) {
	//${LANG['bizconf.jsp.common.upload_common.res1']}...
	var typeAllow = {jpg:true,bmp:true,png:true,gif:true,JPG:true,BMP:true,PNG:true,GIF:true};
	suffix = value.substring(value.lastIndexOf(".")+1);
	if(typeAllow[suffix]){
		var uploadBanner = '<%=request.getParameter("type")%>';
		if(uploadBanner && uploadBanner!="null"){
			parent.document.getElementById("loadImgBanner").style.display="block";
		} else {
			parent.document.getElementById("previewLoadImg").style.display="block";	
		}
		document.getElementById("uploadForm").submit();
	}else{
		parent.parent.errorDialog("${LANG['bizconf.jsp.common.upload_common.res2']}");
	}
}
</script>
<title></title>
<style type="text/css">
#ui-upload-holder{ position:relative;width:60px;height:20px;border:1px solid silver; overflow:hidden;border-radius:3px;} 
#ui-upload-txt{ position:absolute;top:0px;left:0px;width:100%;height:100%;line-height:20px;text-align:center;font-size: 12px;}
.ui-upload-input{ position:absolute;top:0px;right:0px;height:100%;cursor:pointer; opacity:0;filter:alpha(opacity:0);z-index:999;}
</style>
</head>
<body style="margin:0px;">
<form id="uploadForm" action="/common/upload/common?type=<%=request.getParameter("type")%>" method="post" enctype="multipart/form-data">
<div id="ui-upload-holder"> 
	<div id="ui-upload-txt">上传</div>
	<input class="ui-upload-input" type="file" name="commonFile" onchange="fileUpload(this.value);"/><%--<input type="submit" value="${LANG['bizconf.jsp.common.upload_common.res3']}"> --%>
</div>	
</form>
</body>
</html>
