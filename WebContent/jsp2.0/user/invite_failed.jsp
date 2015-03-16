<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
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
</head>
  <body>
  <div class="invite-dialog">
  <div class="wrapper">
  	<div style="height: 429px;overflow: hidden;">
		 <div class="invite-failure">
		 	<div class="face-failure">${LANG['website.user.invite.msg.failinfo']}<br/>${result}</div>
<%--			<button class="input-gray" onclick="closeDialog();">${LANG['website.user.invite.invitefail.resend']}</button>--%>
				<c:choose>
					<c:when test="${!empty failNums}">
						${LANG['website.user.invite.msg.failinfo.reason.mobile']}${failNums}
					</c:when>
				</c:choose>
		 </div> 	
  	</div>
	<div class="form-buttons">
		<input type="button" class="button input-gray" value="${LANG['website.common.option.confirm']}" onclick="closeDialog();">
	</input>
  </div>
  </div>
  </body>
  
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js?vc=000"></script>
<script type="text/javascript">
/*$(function(){
		var tels = [];
		<c:forEach var="confUser" items="${users}">
			tels.push('${confUser.telephone}');
		</c:forEach>
		
		
		app.inventContactMsg(data, function(result) {
			if(result) {
				var url = parent.$("#eidtInventContact").find("iframe").attr("src");
				if(url){
					url = addUrlParam(url, "t", Math.random());
					parent.$("#eidtInventContact").find("iframe").attr("src", url);	
				}
				document.write(result);
			}else{
				top.errorDialog(result.message);
			}
		}, {message:"${LANG['bizconf.jsp.inviteFirst.res2']}...", ui:parent});
	});*/
	
	function closeDialog(result) {
		var dialog = parent.$("#inventContact");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
</script>
</html>
