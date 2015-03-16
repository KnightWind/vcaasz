<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.notice_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
</head>
<body>
<form id="queryNotice" name="queryNotice" action="/user/notice/list" method="post">
<div class="main_content">
  <div class="meeting_main" >
  <div class="intercalate_main_top">
	<h3>${LANG['bizconf.jsp.help.res11']}</h3>
    <p>${LANG['bizconf.jsp.notice_list.res2']}</p>
  </div>
    <table width="100%" cellpadding="0" cellspacing="0" border="0"  >
     <c:if test="${fn:length(noticeList)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="8" align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
       <c:if test="${fn:length(noticeList)>0 }">
    <c:forEach var= "notice" items = "${noticeList}"  varStatus="status">	
      <tr align="left" height="35" class="tr_center" bgcolor="#000066" >
        <td class="notice_title" style="border-right:#D2D8DB 1px solid; border-top:none;" colspan="2"><img src="/static/images/gg_left.png" align="absmiddle" style=" margin-left:20px; margin-right:10px;">${notice.title}&nbsp;&nbsp;&nbsp; (<fmt:formatDate value="${notice.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>)</td>
      </tr>
      <tr align="left" bgcolor="#FFFFFF" height="32">
        <td class="notice_message" style="border:#D2D8DB 1px solid;border-top:none;padding: 10px 45px;">
        <div style="word-break:break-all;word-wrap:break-word;">
 			${notice.content}
		</div>
        </td>
      </tr>
       <tr align="left" bgcolor="#FFFFFF" height="32">
        <td class="notice_bottom" style="border:#D2D8DB 1px solid;border-top:none"><img src="/static/images/promulgator.png" align="absmiddle" style=" margin-right:5px;">${LANG['bizconf.jsp.notice_list.res3']}${publishUserList[status.count-1]}</td>
      </tr> 
    </c:forEach>
    </c:if>
    
	  <tr>
	    <td class="table_bottom" height="38">
	     <jsp:include page="/jsp/common/page_info.jsp" />
		</td>
	  </tr>
	  
	  
      </tr>
    </table>
  </div>
</div>
</form>
</body>
</html>
