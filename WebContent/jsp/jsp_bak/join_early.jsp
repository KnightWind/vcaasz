<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script> 
<title>插件验证</title>
</head>
<body>
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinMtgErrorDiv"  >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--弹出层主题内容区域开始========================================================================-->  
	  <form name="joinForm" id="joinForm" method="post" action="/join">
      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	      <div class="First_Steps_quick_a" style=" background:#FFF">
	        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
	          <h3 class="tit_a">信息提示</h3>
	          <p class="tit_b">&nbsp;</p>
	        </div>
	        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        <div class="First_Steps_top" style=" background:#FFF"> </div>
	        <div class="First_Steps_main_quick">
	          <table class="box_a_quick02" width="100%">
	            <tr class="box01">
	              <td align="center" colspan="2">
	              <c:set var="msgName" value="website.conf.join.type.${msgFlag}"/>
					${LANG[msgName]}
	              </td>
	          </table>
	        </div>
	        <div class="First_Steps_bottom01" style="height: 50px;">
	          <div class="but150">
	          	<span class="button_common" style="margin-right: 5px;">
	          		<a href="javascript:" onclick="closeDialog();"><img src="/static/images/cross.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>关闭</a></div>
	          	</span>
	        </div>
	      </div>
      	</form>
      <!--弹出层主题内容区域开始========================================================================-->      
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

function initPage(){

<c:if test="${msgFlag!=null && msgFlag!=''}">

	$("#joinPublicDiv").hide();
	$("#joinMtgErrorDiv").show();
</c:if>
}

function closeDialog() {
	var dialog = parent.$("#joinMeeting");
	dialog.trigger("closeDialog");
}
window.onload=function(){
	var frame = parent.$("#joinMeeting");
	frame.trigger("loaded");
}
//initPage();
		
</script>
