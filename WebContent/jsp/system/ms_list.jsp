<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<script type="text/javascript"> 
$(function() {
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});

	$("#createMS").click(function() {
		parent.msManger(0);
	});

});

function updateMS(id) {
	parent.msManger(id);
}

function del(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.mslist.warninfo1']}", function() {
		app.delMS(id, function(result){
			if(Object.prototype.toString.call(result) == '[object String]'){
				result = $.parseJSON(result);
			}
			if(result.status == 1){
				parent.successDialog(result.message);
				$("#query").submit();
			}else{
				parent.errorDialog(result.message);
			}
		}, null);
	});
}

function lockMS(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.mslist.warninfo2']}", function() {
		app.lockMS(id, function(result){
			if(Object.prototype.toString.call(result) == '[object String]'){
				result = $.parseJSON(result);
			}
			if(result.status == 1){
				parent.successDialog("${LANG['bizconf.jsp.system.mslist.warninfo4']}");
				$("#query").submit();
			}else{
				parent.errorDialog(result.message);
			}
		}, null);
	});
}

function unlockMS(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.mslist.warninfo3']}", function() {
		app.unlockMS(id, function(result){
			if(Object.prototype.toString.call(result) == '[object String]'){
				result = $.parseJSON(result);
			}
			if(result.status == 1){
				parent.successDialog("${LANG['bizconf.jsp.system.mslist.warninfo5']}");
				$("#query").submit();
			}else{
				parent.errorDialog(result.message);
			}
		}, null);
	});
}
</script>
<title>MS列表</title>
</head>
<body>
<form id="query" name="query" action="/system/ms/showmses" method="post">
<div class="">
  <div class="m_top">
   <div class="">
 	 <a href="javascript:;" class="zengjia" id="createMS"><b>${LANG['bizconf.jsp.system.mslist.showinfo1']}</b></a>
   </div>   
  </div>
    	
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>MS IP</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo2']}</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo3']}</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo4']}</b></span></div></td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>MS ${LANG['system.sysUser.list.remark']}</b></span></div></td>
        <td width="14%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center" class="caozuo" style="border-right:none"><span><b>${LANG['system.sysUser.list.operate']}</b></span></div></td>
     </tr>
      <c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(pageModel.datas)>0 }">
     <c:forEach var="ms" items = "${pageModel.datas}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19">
        	<div align="center"><span>${ms.msIp}</span></div>
        </td>
        <td height="32" class="STYLE19"><div align="center">${ms.msName }</div></td>
        <td height="32" class="STYLE19"><div align="center">${ms.msSupplier }</div></td>
        <td height="32" class="STYLE19"><div align="center">
        	<c:choose>
        		<c:when test="${ms.inUse eq 1 }">
        			 ${LANG['bizconf.jsp.system.mslist.showinfo5']}
        		</c:when>
        		<c:otherwise>
        			${LANG['site.admin.conf.config.option.disable']}
        		</c:otherwise>
        	</c:choose>
        </div> 
        </td>
        <td height="32" class="STYLE19"><div align="center">${ms.msDesc }</div></td>
        <td height="32" class="STYLE19">
        	<div align="center" class="STYLE21">
				<a onclick="updateMS(${ms.id})" href="javascript:;" style=" color:#2b67aa;">${LANG['system.change']}</a>&nbsp;&nbsp;
				<a onclick="del(${ms.id})" href="javascript:;"  style="color:#2b67aa;">${LANG['system.delete']}</a>&nbsp;
				<c:choose>
	        		<c:when test="${ms.inUse eq 1 }">
						<a onclick="lockMS(${ms.id})" href="javascript:;"  style="color:#2b67aa;">${LANG['site.admin.conf.config.option.disable']}</a>
	        		</c:when>
	        		<c:otherwise>
						<a onclick="unlockMS(${ms.id})" href="javascript:;"  style="color:#2b67aa;">${LANG['bizconf.jsp.system.mslist.showinfo6']}</a>
	        		</c:otherwise>
        	</c:choose>
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
