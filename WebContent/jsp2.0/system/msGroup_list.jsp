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
	//table tr background highlight
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});

	$("#createMSgroup").click(function() {
		parent.msGroupManger(0);
	});
});

function updateMSGroup(id) {
	parent.msGroupManger(id);
}

function del(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.msgrouplist.warninfo1']}", function() {
		app.delMSGroup(id, function(result){
			if(result.status == 1){
				parent.successDialog(result.message);
				$("#query").submit();
			}else{
				parent.errorDialog(result.message);
			}
		}, null);
	});
}

function showSites(id){
	parent.showUsingSites(id);
}
</script>
<title>MS列表</title>
</head>
<body>
<form id="query" name="query" action="/system/ms/showmsgroups" method="post">
<div class="">
  <div class="m_top">
   <div class="">
 	 <a href="javascript:;" class="zengjia" id="createMSgroup"><b>${LANG['bizconf.jsp.system.msgrouplist.showinfo1']}</b></a>
   </div>   
  </div>
    	
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.msgrouplist.showinfo2']}</b></span></div></td>
        <td width="40%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.msgrouplist.showinfo3']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.sysUser.list.remark']}</b></span></div></td>
        <td width="5%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center" class="caozuo" style="border-right:none"><span><b>${LANG['system.sysUser.list.operate']}</b></span></div></td>
     </tr>
      <c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(pageModel.datas)>0 }">
     <c:forEach var="mg" items = "${pageModel.datas}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19">
        	<div align="center" ><span style="text-decoration: underline;cursor: pointer;" onclick="showSites(${mg.id});">${mg.groupName}</span></div>
        </td>
        <td height="32" class="STYLE19"><div align="center" title="${msinfos[mg.id] }">
        <c:choose>
	               <c:when test="${fn:length(msinfos[mg.id])>64}">
	                        ${fn:substring(msinfos[mg.id],0,64) }...
	               </c:when>
	               <c:otherwise>
			               ${msinfos[mg.id] }
	               </c:otherwise>
         </c:choose>
       </div></td>
        <td height="32" class="STYLE19"><div align="center">${mg.groupDesc }</div></td>
        <td height="32" class="STYLE19">
        	<div align="center" class="STYLE21">
				<a onclick="updateMSGroup(${mg.id});" href="javascript:;" style=" color:#2b67aa;">${LANG['system.change']}</a>&nbsp;&nbsp;
				<a onclick="del(${mg.id});" href="javascript:;"  style="color:#2b67aa;">${LANG['system.delete']}</a>&nbsp;
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
