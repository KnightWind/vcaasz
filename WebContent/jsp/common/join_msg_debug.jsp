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
<title>JoinMtg Debug</title>
</head>
<body>
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinMtgErrorDiv" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->  
      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	      <div class="First_Steps_quick_a" style=" background:#FFF">
	        <div class="First_Steps_title_a" style="height: 52px;"> 
	        
	          	<h3 class="tit_a">Debug${LANG['bizconf.jsp.common.join_msg_debug.res1']}</h3>
	        </div>
	        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        <div class="First_Steps_top" style=" background:#FFF"> </div>
	        <div class="First_Steps_main_quick">
	          <div class="prompt_message_main" style="background-color:#FFFFFF;">
  				<dl>
                	<dt></dt>
            		<dd>
            		<%--
            			request.setAttribute("siteSign", siteSign);
			request.setAttribute("language", language);
			request.setAttribute("duration", duration);
			request.setAttribute("mtgTitle", mtgTitle);
			request.setAttribute("mtgKey", mtgKey);
			request.setAttribute("userName", userName);
			request.setAttribute("userId", userId);
			request.setAttribute("userType", userType);
			request.setAttribute("timestamp", timestamp);
			request.setAttribute("hostPwd", hostPwd);
			request.setAttribute("mtgPwd", mtgPwd);
			request.setAttribute("authId", authId);
            		 --%>
            			<p>siteSign=${siteSign}</p>
            			<p>language=${language}</p>
            			<p>duration=${duration}</p>
            			<p>mtgTitle=${mtgTitle}</p>
            			<p>mtgKey=${mtgKey}</p>
            			<p>userName=${userName}</p>
            			<p>userId=${userId}</p>
            			<p>userType=${userType}</p>
            			<p>timestamp=${timestamp}</p>
            			<p>hostPwd=${hostPwd}</p>
            			<p>mtgPwd=${mtgPwd}</p>
            			<p>authId=${authId}</p>
					</dd>
            	</dl>
      		  </div>
	        </div>
	        <a class="prompt_message_btn02" href="javascript:;" onclick="closeDialog()">
	        	<img src="/static/images/ys_r_bg.png" width="16" height="14" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.admin.viewNotice.res4']}
	        </a>
	      </div>
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->      
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


function closeDialog() {
    var topUrl=top.location+"";//.toLowerCase();
    if(topUrl.indexOf("?")>=0){
    	var domain="${domain}";
    	top.location="http://"+domain;
    }
	//var dialog = parent.$("#joinMeeting");
	dialog.trigger("closeDialog");
}
window.onload=function(){
	var frame = parent.$("#joinMeeting");
	frame.trigger("loaded");
	<c:if test="${errorCode!=null && errorCode!=''}">
	$("#joinMtgErrorDiv").show();
	resetFrameHeight();
</c:if>
}
		
</script>
