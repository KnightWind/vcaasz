<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_invite_user_list.res1']}</title>
</head>

<body onload="loaded()">
<!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      	<div class="First_Steps_invite_first01" >
        <div class="join_list">
        	<ul class="boc">
            	<li class="boc01">${LANG['bizconf.jsp.add_contacts.res7']}</li>
            	<li class="boc02">${LANG['bizconf.jsp.add_contacts.res9']}</li>
                <li class="boc03"><!--${LANG['bizconf.jsp.conflogs.res4']}-->&nbsp;</li>
                <li class="boc04">${LANG['bizconf.jsp.conf_invite_user_list.res4']}</li>
            </ul>
            <div class="boc_main">
            <c:forEach var="confUser" items="${confUserList}">
             <ul class="boc_a">
            	<li class="boc01">${confUser.userName}&nbsp;</li>
            	<li class="boc02">${confUser.userEmail}&nbsp;</li>
                <li class="boc03"><!--${confUser.telephone}-->&nbsp;</li>
                <li class="boc04">
                	<c:choose>
                		<c:when test="${confUser.acceptFlag eq 1}">
                			${LANG['bizconf.jsp.conf_invite_user_list.res5']}
                		</c:when>
                		<c:when test="${confUser.acceptFlag eq 0}">
                			${LANG['bizconf.jsp.conf_invite_user_list.res6']}
                		</c:when>
                		<c:otherwise>
                			${LANG['bizconf.jsp.conf_invite_user_list.res7']}
                		</c:otherwise>
                	</c:choose>
                </li>
            </ul>
           </c:forEach>
             
            </div>
        
        </div>
       
       
       
       
        <div style="margin-left: 20px;margin-top: 10px;">
          <input class="create_system_user_button" type="button" value="关闭" onclick="closeDialog()"/>
        </div>
      </div>
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
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
</script>
