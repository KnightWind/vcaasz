<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.admin.viewNotice.res1']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!-- Css -->
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<!-- Javascript -->
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>

</head>
<body onload="loaded()">
	<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr class="no-header">
				<td class="overlay-hdL"></td>
				<td class="overlay-hdC"></td>
				<td class="overlay-hdR"></td>
			</tr>
			<tr>
				<td class="overlay-bdL"></td>

				<!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->

				<td class="overlay-content">
					<div class="gonggao_main">
						<div class="ggxinxi">
							<a href="javascript:closeDialog();"></a>
						</div>
						<div class="gg_t"></div>
						<div class="gg_c">

							<div class="gg_header">
								<h3 id="title_notice">${notice.title}</h3>
							</div>
							<div class="gg_header_b"></div>
							<div class="">
								<div  id="content_notice" class="gg_main" style="overflow: auto;margin: 10px auto;height: 340px;">
							    	${notice.content}
							     </div>
							</div>
						</div>
						<div class="gg_bottom">
<!-- 							<input type="checkbox" name="CheckboxGroup1" -->
<!-- 								class="CheckboxGroup1_01" /><span class="tixing">${LANG['bizconf.jsp.admin.viewNotice.res3']}</span> -->
							<a href="javascript:closeDialog();" class="gg_button"><img
								src="/static/images/cross.png" width="16" height="16"
								style="margin-left: 10px; margin-right: 5px;" align="absmiddle" />${LANG['bizconf.jsp.admin.viewNotice.res4']}</a>
						</div>
					</div>
				</td>

				<!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->

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
function closeDialog(){
	var frame = parent.$("#previewNoticeDiv");
	frame.trigger("closeDialog");
}
function loaded(){
	var frame = parent.$("#previewNoticeDiv");
	frame.trigger("loaded");
	var data = frame.data("data");
	if (data) {
		$("#title_notice").html(data.title);
		$("#content_notice").html(data.content);
	}
}
</script>
