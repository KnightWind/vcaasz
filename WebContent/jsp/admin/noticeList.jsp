<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<jsp:useBean id="nowDate" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css"/>
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
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	$(".gaoji").toggle(function () {
	    $("#search-condition").slideDown(function() {
		    parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res1']}
	    });
	}, function () {
		$("#search-condition").slideUp(function() {
			parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res2']}
		});
	});
	
	$("#createNotice").click(function() {
		parent.createOrUpdateNotice();
	});
});
function updateNotice(id) {
	parent.createOrUpdateNotice(id);
}
function viewNotice(id) {
	parent.viewNotice(id);
}
function del(id){
	parent.confirmDialog("${LANG['system.notice.delete']}", function() {
		query.action="/admin/notice/delete/"+id;
		query.submit();
	});
}
</script>
<title>${LANG['system.menu.notice.list']}</title>
</head>
<body>
<form id="query" name="query" action="/admin/notice/list/" method="post">
<div class="main_content">
<!--  <div class="m_top"> -->
 
<%--     <div class="make_new"><a href="javascript:;" target="mainFrame" id="createNotice">${LANG['system.notice.list.Create']}</a></div> --%>
 
<!--   </div> -->
 <div class="m_top1"> 
	<input name="button_b" class="button_b" type="button" id="createNotice" value="${LANG['system.notice.list.Create']}" onmouseover="this.className='Btn_Hover_b'" onmouseout="this.className='Btn_b'"/>
  </div>
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-right:10px; border:#A3C5DE 1px solid; border-top:none; border-bottom:none;">
    <tr class="table002" height="32" >
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.Title']}</b></span></div></td>
        <td width="25%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.Contents']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.StartTime']}</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.creator']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10" style="border-right:none;"><div align="center" ><span><b>${LANG['system.Operate']}</b></span></div></td>
      </tr>
       <c:if test="${fn:length(noticeList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="4"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
      <c:if test="${fn:length(noticeList)>0 }">
     <c:forEach var= "notice" items = "${noticeList}"  varStatus="status">
      <tr class="table001" height="32" >
        <td height="32"><div align="center"><a onclick="viewNotice(${notice.id})" href="javascript:;"><span style="color:#73798E">${notice.title}</span></a></div></td>
        <td height="32"><div title="${notice.content}" align="center" style="width: 450px;white-space:nowrap;overflow: hidden;">${notice.content }</div></td>
        <td height="32"><div align="center"><fmt:formatDate  value="${notice.startTime}" type="date" pattern="yyyy-MM-dd"/></div></td>
        <td height="32"><div align="center">${publishUserList[status.count-1]}</div></td>
        <td height="32"><div align="center" >
        <fmt:formatDate var="stopTime"  value="${notice.stopTime}" pattern="yyyy-MM-dd"/>
		<fmt:formatDate var="nowTime"  value="${nowDate}" pattern="yyyy-MM-dd"/>
<%-- 		 <c:if test="${stopTime gt nowTime}"> --%>
			<a href="javascript:;" onclick="updateNotice(${notice.id})" class="edit">${LANG['system.change']}</a>&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 		 </c:if> --%>
			<a href="javascript:;" onclick='del("${notice.id}")' class="edit">${LANG['system.delete']}</a>
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
