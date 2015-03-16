<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.conf_invite_user_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<c:set var="acceptFlag" value="false"/>
</head>

<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        <td class="overlay-content">
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      	<div class="First_Steps_invite_first01" >
        <div class="First_Steps_title_invite01"> <a href="javascript:closeDialog();"></a>
          <h3 class="tit_a_invite">${LANG['bizconf.jsp.conf_invite_user_list.res2']}</h3>
          <p class="tit_b_invite">${LANG['bizconf.jsp.conf_invite_user_list.res3']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick_invite" src="${baseUrlStatic}/images/min.jpg" width="550" height="1" /></div>
        <table style="width: 100%;">
        	<tr>
        		<td align="left"><a class="invent_user_default" href="#">${LANG['bizconf.jsp.inviteContactsSelect.before']}
        		<c:set var="inviteCount" value="${fn:length(confUserList)}" />
        		<c:if test="${inviteCount>1}">
        		<c:out value="${inviteCount}"/> ${LANG['bizconf.jsp.inviteContactsSelect.after']}
        		</c:if>
        		<c:if test="${inviteCount<2}">
        		<c:out value="${inviteCount}"/>${LANG['bizconf.jsp.inviteContactsSelect.after2']}
        		</c:if>
        		</a></td>
        		<td align="right">
        			<c:if test="${user.id eq conf.createUser and (conf.confStatus lt 3 or conf.confStatus eq 8)}">
	        			<span class="button_common" style="float: right;margin-right: 20px;margin-top: 5px;">
		              		<a href="javascript:parent.inventContact('${conf.id}');">
		              			<img width="16" height="16" align="absmiddle" src="/static/images/add_16.png" style=" margin-left:5px; margin-right:5px;">${LANG['bizconf.jsp.inviteFirst.res14']}
		              		</a>
	              		</span>
	              		<c:if test="${conf.confStatus eq 2  and (conf.confType  eq 1 or conf.confType eq 3) and (1 eq conf.allowCall) }"><!--正在开始的会议 -->
	        				<span class="button_common" style="float: right;margin-right: 20px;margin-top: 5px;">
	        					<a href="javascript:parent.inventContact('${conf.id}',true);">
			              			<img width="16" height="16" align="absmiddle" src="/static/images/add_16.png" style=" margin-left:5px; margin-right:5px;">${LANG['bizconf.jsp.user.confuser.batchcall']}
			              		</a>
	        				</span>
		              	</c:if>
              		</c:if>
        		</td>
        	</tr>
        </table>
        <div style="width: 550px;height:335px;margin: 0 auto;">
        <table cellpadding="0" cellspacing="0">
        	<tr>
        		<td align="center" width="128" height="25" style="border-bottom: 1px solid #ADADAD;">${LANG['bizconf.jsp.add_contacts.res7']}</td>
        		<td align="center" width="160" height="25" style="border-bottom: 1px solid #ADADAD;">${LANG['bizconf.jsp.add_contacts.res9']}</td>
        		<td align="center" width="160" height="25" style="border-bottom: 1px solid #ADADAD;">${LANG['bizconf.jsp.conflogs.res4']}</td>
        		<td align="center" width="80" height="25" style="border-bottom: 1px solid #ADADAD;">${LANG['bizconf.jsp.conf_invite_user_list.res4']}</td>
        	</tr>
        	<tr>
        		<td colspan="4">
        			<div style="width: 100%;height: 300px;overflow-y: auto;">
        				<table cellpadding="0" cellspacing="0">
        					<c:forEach var="confUser" items="${confUserList}">
				        		<tr>
				        			<td width="128" align="center" height="25" style="border-bottom: 1px solid #E1E1E1;color: #666666">
				        				<div style="width: 128px;overflow: hidden;">
				        					${confUser.userName}
				        				</div>
				        			</td>
				        			<td width="160" align="center" height="25" style="border-bottom: 1px solid #E1E1E1;color: #666666">
				        				<div style="width: 128px;overflow: hidden;">
				        					${confUser.userEmail}
				        				</div>
				        			</td>
				        			<td width="160" align="center" height="25" style="border-bottom: 1px solid #E1E1E1;color: #666666">${confUser.telephone}&nbsp;</td>
				        			<td width="80" align="center" height="25" style="border-bottom: 1px solid #E1E1E1;color: #666666">
				        				<c:choose>
											<c:when test="${confUser.acceptFlag eq 1}">
												 ${LANG['bizconf.jsp.conf_invite_user_list.res5']}
											</c:when>
											<c:when test="${confUser.acceptFlag eq 0}">
												<c:choose>
													<c:when test="${not empty confUser.userEmail and confUser.userEmail !=''}">
													 	${LANG['bizconf.jsp.conf_invite_user_list.res6']}
													 	<c:set var="acceptFlag" value="true"></c:set>	
													</c:when>
													<c:otherwise>
										<!--	                				<c:set var="acceptFlag" value="true"></c:set>	-->
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
													 ${LANG['bizconf.jsp.conf_invite_user_list.res7']}
											</c:otherwise>
										</c:choose>
				        			</td>
				        		</tr>
				        	</c:forEach>
        				</table>
        			</div>
        		</td>
        	</tr>
        </table>
        </div>
        <div class="First_Steps_bottom_b" style="padding-bottom: 0px;height: 40px;">
          <div class="but101">
	          <span class="button_common"><a href="javascript:closeDialog();"><img src="${baseUrlStatic}/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.conf_invite_user_list.res8000']}</a>
	          </span>
	          <c:if test="${acceptFlag =='true' and (user.id eq conf.createUser)}">
	          <span class="button_common" style="margin-left: 10px;" id="remindUser"><a href="javascript:remindConfUser();"><img src="${baseUrlStatic}/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.user.conf.invite.remind'] }</a>
	          </span>
	          </c:if>
	          <c:if test="${acceptFlag =='false' and (user.id eq conf.createUser)}">
	          <span class="disable_common" style="margin-left: 10px;opacity:0.5;filter:alpha(opacity=50);" id="remindUser"><a href="javascript:;"><img src="${baseUrlStatic}/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.user.conf.invite.remind'] }</a>
	          </span>
	          </c:if>
          </div>
          <div class="but111"></div>
        </div>
      </div>
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
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
function loaded() {
	var frame = parent.$("#eidtInventContact");
	frame.trigger("loaded");
}
function closeDialog(){
	var frame = parent.$("#eidtInventContact");
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
