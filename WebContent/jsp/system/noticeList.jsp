<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<jsp:useBean id="nowDate" class="java.util.Date"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
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
		query.action="/system/notice/delete/"+id;
		query.submit();
	});
}
</script>
<title>${LANG['system.menu.notice.list']}</title>
</head>
<body>
<form id="query" name="query" action="/system/notice/list/" method="post">
<div class="main_right">
 <div class="m_top">
 
    <div class="make_new">
    	<a href="javascript:;" style="float:left;" target="mainFrame" id="createNotice"><b>${LANG['system.notice.list.Create']}</b></a>
    	<span style="color:red; position: relative; left: 10px; top: 5px;"> * ${LANG['system.notice.warn']}</span>
    </div>
 
  </div>
<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#D6D6D6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
        <td width="45%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.Title']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.StartTime']}</b></span></div></td>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.notice.list.StopTime']}</b></span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10" class="STYLE_none" style="border-right:none"><div align="center"><span><b>${LANG['system.Operate']}</b></span></div></td>
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
      <tr>
       <td height="32" class="STYLE19">
        	<div align="center">
        		<a onclick="viewNotice(${notice.id})" href="javascript:;"><span>${notice.title}</span></a>
        	</div>
        </td>
<%--         <td height="32"  class="STYLE19"><div align="center"><span>${notice.title}</span></div></td> --%>
        <td height="32" class="STYLE19"><div align="center"><fmt:formatDate  value="${notice.startTime}" type="date" pattern="yyyy-MM-dd"/></div></td>
        <td height="32" class="STYLE19"><div align="center"><fmt:formatDate  value="${notice.stopTime}" pattern="yyyy-MM-dd"/></div></td>
        <td height="32" class="STYLE19"><div align="center" >
		<fmt:formatDate var="stopTime"  value="${notice.stopTime}" pattern="yyyy-MM-dd"/>
		<fmt:formatDate var="nowTime"  value="${nowDate}" pattern="yyyy-MM-dd"/>
        <c:if test="${status.index < 1  && pageModel.pageNo <=1 && stopTime gt nowTime}">
			<a href="javascript:;" onclick="updateNotice(${notice.id})" >${LANG['system.change']}</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
			<a href="javascript:;" onclick="del(${notice.id})" >${LANG['system.delete']}</a>
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
