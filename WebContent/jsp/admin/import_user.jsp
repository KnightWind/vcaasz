<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<script type="text/javascript">
	function submitForm(){
		document.forms[0].submit();
	}
</script>
<body style="margin:0px;">
<form id="uploadForm" action="/admin/entUser/importUser" method="post"
						enctype="multipart/form-data">
<div style="margin-left: 30px;">
	<input type="file" id="fileipt" name="excelfile" />
</div>
</form>
</body>
</html>
