<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/jion_meeting.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
</head>
<body onload="loaded();">
<form id="query" name="query" action="/user/conflog/attendConflist" method="post">
<input type="hidden" value="${userId}" name="userId" />
<div class="First_Steps_invite" style=" background:#FFF; border-radius:3px;width: 100%">
          <div class="jianju"></div>
          <!--${LANG['bizconf.jsp.attendConfloglist.res2']}-->
          <div style="position: relative;width: 750px;height: 435px;">
          <div style="width: 780px;height: 435px;overflow-y: auto;position: absolute ;top: 5px;left:15px;overflow-x: hidden;">
            <table width="760" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" style="margin: 0px auto;">
		        <tr align="center" height="35" class="tr_center" bgcolor="#000066">
		        <td width="20%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res3']}</td>
		        <td width="8%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res4']}</td>
		        <td width="8%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res5']}</td>
		        <td width="12%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res6']}</td>
		        <td width="12%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res7']}</td>
		        <td width="12%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res8']}</td>
		        <td  width="12%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attendConfloglist.res9']}</td>
		      </tr>
	    <c:if test="${fn:length(pageModel.datas)<=0}">
			<tr class="table001" height="32"  >
				<td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			</tr>
		</c:if>
		<c:forEach var="conf" items="${pageModel.datas}" varStatus="status">
	        <tr align="center" bgcolor="#FFFFFF" height="32">
	        <td class="tr_main" style=" border-left:#D2D8DB 1px solid">${conf.confName}</td>
	        <td class="tr_main">${numMap[conf.id]}</td>
	        <td class="tr_main">${conf.compereName}</td>
	        <td class="tr_main"> 
	        ${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.startTime,timezone)}
<%--	        <fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm" />--%>
	        </td>
	        <td class="tr_main"> 
	        ${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.endTime,timezone)} 
<%--	        <fmt:formatDate value="${conf.endTime}" pattern="yyyy-MM-dd HH:mm" />--%>
	        </td>
	        
	        <td class="tr_main"> 
	       	 	${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].joinTime,timezone)}
<%--	        	<fmt:formatDate value="${cls[conf.id].joinTime}" pattern="yyyy-MM-dd HH:mm" />--%>
	        </td>
	        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
	        	${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].exitTime,timezone)} 
<%--	        	<fmt:formatDate value="${cls[conf.id].exitTime}" pattern="yyyy-MM-dd HH:mm" />--%>
	        </td>
     	</tr>
        </c:forEach>
              <tr>
                <td height="35" colspan="7" class="tr_bottom">
                    <jsp:include page="/jsp/common/page_info.jsp" />
                </td>
              </tr>
            </table>
          </div>
          </div>
          <div class="First_Steps_bottom_b" style="position: absolute;bottom: 0px;left:20px;">
          	<input type="button" class="create_system_user_button" value="${LANG['bizconf.jsp.attendConfloglist.res10']}" onclick="closeDialog()" />
          </div>
        </div>
  </form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#attendconfs");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.parent.$("#attendconfs");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>

