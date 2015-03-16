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
<c:set var="inviteCount" value="${fn:length(confUserList)}" />
</head>
<body>
<div class="invite-dialog">
	<div class="wrapper">
	  	<section>
			<div class="invite-panel">
				<div class="actions">
						<a class="already" style="text-decoration: none;cursor: default;">${LANG['website.user.invite.list.invited']} ${inviteCount}${LANG['website.user.invite.list.people']}</a>
				</div>
		  		<c:if test="${inviteCount gt 0 }">
					<table class="common-table titles-table"> 
						<tbody>
							<tr>
								<th class="name">
									${LANG['website.user.invite.list.participantname']}
								</th>
								<th class="email">
									 ${LANG['website.user.invite.list.participantemail']}
								</th>
								<th class="phone">
									${LANG['website.user.invite.list.participantphone']}
								</th>
							</tr>
						</tbody>
					</table>
					<div class="userlist">
						<table class="common-table">
							<tbody>
								<c:forEach var="confUser" items="${confUserList}">
									<tr>
										<td class="first-child name" title="${confUser.userName}">
											${confUser.userName}
										</td>
										<td class="email" title="${confUser.userEmail}">
											${confUser.userEmail}
										</td>
										<td class="phone" title="${confUser.telephone}">
											${confUser.telephone}
										</td>
										 	
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${inviteCount eq 0 }">
					<div class="common-empty">
						${LANG['website.user.index.js.message.title.invite.none']}<!-- 此栏目暂无数据 -->
					</div>
				</c:if>
			</div>
	  	</section>
	  	<div class="form-buttons">
	  	<c:set var="acceptFlag" value="true"></c:set>	
	  			<c:if test="${isBack}">
	  					<input type="button" class="button input-gray" onclick="history.back();" value="${LANG['bizconf.jsp.admin.arrange_org_user.res11']}" />
	  			</c:if>
				<c:if test="${!isBack}">
						<input type="button" class="button input-gray" onclick="closeDialog();" value="${LANG['system.close']}" />
				</c:if>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
function closeDialog(){
	var frame = top.$("#eidtInventContact");
	frame.trigger("closeDialog");
}
//提醒与会者
function remindConfUser(){
	var confId = "${conf.id}";
	app.remindConfUser(confId, function(result) {
		if(result) {
			if(result.status==1){
				parent.successDialog(result.message);
			} else {
				parent.errorDialog(result.message);
			}
		}
	}, {message:"${LANG['bizconf.jsp.inviteFirst.res2']}...", ui:parent});
}
</script>
</html>
