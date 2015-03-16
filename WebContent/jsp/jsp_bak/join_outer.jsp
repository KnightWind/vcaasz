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
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="startClientDiv" style="display:none">
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
		<div class="First_Steps_quick_b" style=" background:#FFF">
        <div class="First_Steps_title_a"> <a   href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">加入会议</h3>
          <p class="tit_b">请输入你的账号后进入该会议。</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong">请稍后，正在启动客户端...</p>
        </div>
      </div>
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
 <form name="downForm" id="downForm" method="post" action="/join/download" target="downFrame">
 <input name="userName" type="hidden" value="${userName}"/>
 <input name="cId" type="hidden" value="${cId}"/>
</form>
<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>
</body>
</html>



<script type="text/javascript">

 	<c:if test="${errorCode!=null && errorCode!=''}"> 
		function initPage(){
			//var msgFlag="${msgFlag}";
			<c:set var="msgName" value="website.conf.joinouter.erroecode.${errorCode}"/>
			var msgFlag="${LANG[msgName]}";
			alert(msgFlag);
		}
		initPage();
	</c:if> 
		
</script>
