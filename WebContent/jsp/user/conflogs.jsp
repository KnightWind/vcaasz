<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/jion_meeting.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript">
	function sortQuery(sortField,sortord){
		if(!sortord){
			if($("#sortRule").val()=='0'){
				sortord = "1";
			}else{
				sortord = "0";
			}
		}
		$("#sortField").val(sortField);
		$("#sortRule").val(sortord);
		$("#query").attr("action","/user/conflog/loglist");
		$("#query").submit();
	}
</script>
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
</head>

<body onload="loaded();">
<form id="query" name="query" action="/user/conflog/loglist" method="post">
<input type="hidden" value="${confId}" name="confId" />
<div class="First_Steps_invite" style=" background:#FFF; border-radius:3px;width:100%">
			<input type="button" class="create_system_user_button" value="${LANG['bizconf.jsp.conflogs.res1']}" onclick="exports('${confId}');" style="margin-left: 25px;margin-top: 20px;"/>
          <div class="jianju"></div>
          <!--${LANG['bizconf.jsp.attendConfloglist.res2']}-->
          <div style="margin:10px auto;width: 750px;height: 405px;overflow-y: auto;">
            <table width="730" align="center" cellpadding="0" cellspacing="0" border="0">
              <tr align="center" height="35" class="tr_center" bgcolor="#000066">
                <td width="25%" class="tr_center">
                	${LANG['bizconf.jsp.add_contacts.res7']}
                </td>
                <td width="25%" class="tr_center">${LANG['bizconf.jsp.conflogs.res2']}</td>
                <td width="25%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res8']}</td>
                <td width="25%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attendConfloglist.res9']}</td>
              </tr>
	    <c:if test="${fn:length(pageModel.datas)<=0}">
			<tr class="table001" height="32"  >
				<td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			</tr>
		</c:if>
		<c:forEach var="log" items="${pageModel.datas}" varStatus="status">
              <tr align="center" bgcolor="#FFFFFF" height="30">
                <td class="tr_main"  style="border-left:#D2D8DB 1px solid">
                	${log.userName}
                </td>
                <td class="tr_main">
	                	<c:choose>
	                		<c:when test="${log.termType eq '3'}">
	                			${LANG['bizconf.jsp.conflogs.res3']}
	                		</c:when>
	                		<c:when test="${log.termType eq '1'}">
	                			${LANG['bizconf.jsp.conflogs.res4']}
	                		</c:when>
	                		<c:otherwise>
	                			${LANG['bizconf.jsp.conflogs.res5']}
	                		</c:otherwise>
	                	</c:choose>
                </td>
                <td class="tr_main">${myfn:fmtDate('yyyy-MM-dd HH:mm',log.joinTime,timezone)}</td>
                <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
                	<c:choose>
	                	<c:when test="${log.exitTime != null}">
		                	${myfn:fmtDate('yyyy-MM-dd HH:mm',log.exitTime,timezone)}
	                	</c:when>
	                	<c:otherwise>
	                		--
	                	</c:otherwise>
                	</c:choose>
                </td>
              </tr>
        </c:forEach>
              <tr>
                <td height="35" colspan="4" class="tr_bottom">
                    <jsp:include page="/jsp/common/page_info.jsp" />
                </td>
              </tr>
            </table>
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
	var frame = parent.$("#logview");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.parent.$("#logview");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}

function exports(confId){
	window.location = "<%=request.getContextPath()%>/user/conflog/exportLogs?confId="+confId;
}
</script>
