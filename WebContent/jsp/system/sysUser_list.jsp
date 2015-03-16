<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>


<c:if test="${!empty errorMessage}">
	<script type="text/javascript">
		$(function() {
			parent.errorDialog("${errorMessage}");
		});
	</script>
</c:if>
<c:if test="${!empty infoMessage}">
	<script type="text/javascript">
	$(function() {
		parent.successDialog("${infoMessage}");
	});	
	</script>
</c:if>

<script type="text/javascript"> 
$(function() {
	
	//table tr background highlight
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});

	$("#createSysUser").click(function() {
		parent.createOrUpdateUser();
	});

});

function updateSysUser(id) {
	parent.createOrUpdateUser(id);
}

function del(id){
	parent.confirmDialog("${LANG['system.sysUser.list.delete']}", function() {
		query.action="/system/sysUser/delete/"+id;
		query.submit();
	});
}


</script>
<title>${LANG['system.menu.config.sysUser']}</title>
</head>
<body>
<form id="query" name="query" action="/system/sysUser/list/" method="post">
<div class="">
  <div class="m_top">
   <div class="">
 	 <a href="javascript:;" class="zengjia" id="createSysUser"><b>${LANG['system.sysUser.list.addUser']}</b></a>
   </div>   
  </div>
    	
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.loginName']}</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.userName']}</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.enName']}</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.createSystemUser.res2']}</b></span></div></td>
        <td width="13%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.email']}</b></span></div></td>
        <td width="14%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.telephone']}</b></span></div></td>
        <td width="14%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center" class="caozuo" style="border-right:none"><span><b>${LANG['system.sysUser.list.operate']}</b></span></div></td>
     </tr>
      <c:if test="${fn:length(systemUserList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(systemUserList)>0 }">
     <c:forEach var= "systemUser" items = "${systemUserList}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19">
        	<div align="center"><span>${systemUser.loginName}</span></div>
        </td>
        <td height="32" class="STYLE19"><div align="center">${systemUser.trueName }</div></td>
        <td height="32" class="STYLE19"><div align="center">${systemUser.enName }</div></td>
        <td height="32" class="STYLE19"><div align="center">
        	<c:choose>
        		<c:when test="${systemUser.sysType eq 999 }">
        			${LANG['bizconf.jsp.system.createSystemUser.res3']}
        		</c:when>
        		<c:otherwise>
        			${LANG['bizconf.jsp.system.createSystemUser.res4']}
        		</c:otherwise>
        	</c:choose>
        </div> 
        </td>
        <td height="32" class="STYLE19"><div align="center">${systemUser.email }</div></td>
        <td height="32" class="STYLE19"><div align="center">${systemUser.mobile }</div></td>
        <td height="32" class="STYLE19">
        	<div align="center" class="STYLE21">
				<a onclick="updateSysUser(${systemUser.id})" href="javascript:;" style=" color:#2b67aa;">${LANG['system.change']}</a>&nbsp;&nbsp;
				<a onclick="del(${systemUser.id})" href="javascript:;"  style="color:#2b67aa;">${LANG['system.delete']}</a>
			</div>
        </td>
      </tr>
     </c:forEach>
     </c:if>
    </table>
  </tr>
  <tr>
    <td class="table_bottom" height="38">
    <jsp:include page="/jsp/common/page_info.jsp" />
    </td>
  </tr>
</table>

</div>
</form>
</body>
</html>
