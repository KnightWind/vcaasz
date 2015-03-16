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
  <div class="invite-dialog" >
  <div class="wrapper">
  	<div class="invite-success" style="height: 415px;overflow: hidden;">
	<c:set var="userCount" value="${fn:length(userList)}" />
				<c:choose>
					<c:when test="${userCount gt 0}">
						<div class="list-names">
						 <div class="face-success">
						 	<c:choose>
						 		<c:when test="${isCalling}">
						 			${LANG['website.user.invite.callback.calling']}
						 		</c:when>
						 		<c:otherwise>
						 			${LANG['website.user.invite.invitesuccess.successinfo']}
						 		</c:otherwise>
						 	</c:choose>
						 </div> 	
							<button class="input-gray" onclick="viewInviteUser('${confid}');">${LANG['website.user.invite.invitesuccess.checkinvitelist']}</button>
						</div>
					</c:when>
					<c:otherwise>
						<div style="margin-top: 120px;" class="list-names">
						 <div class="face-success">
						 	<c:choose>
						 		<c:when test="${isCalling}">
						 			${LANG['website.user.invite.callback.calling']}
						 		</c:when>
						 		<c:otherwise>
						 			${LANG['website.user.invite.invitesuccess.successinfo']}
						 		</c:otherwise>
						 	</c:choose>
						 </div> 	
							<button class="input-gray" onclick="viewInviteUser('${confid}');">${LANG['website.user.invite.invitesuccess.checkinvitelist']}</button>
						</div>
					</c:otherwise>
				</c:choose>
		  		<c:if test="${userCount gt 0 }">
				<div class="box">
					<div class="head">
						${LANG['website.user.invite.invitesuccess.headinfo']}
					<%--			<a class="addto" onclick="addToContacts();"><i class="icon icon-add"></i>${LANG['website.user.invite.invitesuccess.addtocontacts']}</a>
				<a class="addto" onclick="addAllToContacts();"><i class="icon icon-add"></i>${LANG['website.user.invite.callback.addalltocontacts']}</a>--%>
					</div>
					<div class="body">
						<ul class="items clearfix">
							<c:forEach var="user" items="${userList}" varStatus="state">
								<c:choose>
									<c:when test="${not empty user.userEmail}">
										<li class="nobr"><input type="checkbox" value="${state.index}" checked="checked" name="user"><input type="hidden" id="username${state.index}" value="${user.userName}"/><span id="userEmail${state.index}">${user.userEmail}</span></li>
									</c:when>
									<c:when test="${empty user.userEmail and user.telephone!=''}">
										<li class="nobr"><input type="checkbox" value="${state.index}" checked="checked" name="user"><input type="hidden" id="username${state.index}" value="${user.userName}"><span id="telephone${state.index}">${user.telephone}</span></li>
									</c:when>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
				</div>
				</c:if>
			
  	</div>
	<div class="form-buttons" style="height: 30px;">
		<input type="button" class="button input-gray" value="${LANG['website.common.option.confirm']}" onclick="addToContacts();" >
	</input>
  </div>
  </div>
  </body>
  
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">

	function viewInviteUser(confId){
		top.editInventContact(confId);
	}
	function closeDialog(result) {
		var dialog = parent.$("#inventContact");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
	function addToContacts(){
		var url = "/user/contact/saveAsContacts";
		var data = new Object();
		data.users = [];
		if($("input[name=user]:checked").length>0){
			$("input[name=user]:checked").each(function(){
				var index = $(this).val();
				var user = new Object();
				user.contactName = $("#username"+index).val();
				/*user.contactPhone = $("#telephone"+index).text();*/
				user.contactMobile = $("#telephone"+index).text();	//将联系方式修改成手机号
				user.contactEmail = $("#userEmail"+index).text();
				data.users.push(user);
			});
			ajaxPost(url, {data:JSON.stringify(data)},
	                function(result) {
						//$("input[name=user]:checked").each(function(){
							top.successDialog("${LANG['website.user.invite.invitesuccess.warninfo1']}");
							var dialog = parent.$("#inventContact");
							dialog.trigger("closeDialog");	
							if($("ul li").length<1){
								$(".box").hide();
							}
						//}); 
	                }, null, {pageLoading: "false"});
		}else{
			var dialog = parent.$("#inventContact");
			dialog.trigger("closeDialog");	
		}	
	}
	
	function addAllToContacts(){
		var url = "/user/contact/saveAsContacts";
		var data = new Object();
		data.users = [];
		$("input[name=user]").each(function(){
				var index = $(this).val();
				var user = new Object();
				user.contactName = $("#username"+index).val();
				user.contactPhone = $("#telephone"+index).text();
				user.contactEmail = $("#userEmail"+index).text();
				data.users.push(user);
		});
		ajaxPost(url, {data:JSON.stringify(data)},
	                function(result) {
						top.successDialog("${LANG['website.user.invite.invitesuccess.warninfo1']}");
						$("li").remove();
						$(".box").hide();
	     }, null, {pageLoading: "false"});
	}
</script>
  
</html>
