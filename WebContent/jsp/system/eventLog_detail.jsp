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
<!--			<input type="button" class="create_system_user_button" value="${LANG['bizconf.jsp.conflogs.res1']}" onclick="exports('${confId}');" style="margin-left: 25px;margin-top: 20px;"/>-->
          <div class="jianju"></div>
          <!--${LANG['bizconf.jsp.attendConfloglist.res2']}-->
          <div style="margin:10px auto;width: 650px;height: 405px;overflow-y: auto;">
            <table width="630" align="center" cellpadding="0" cellspacing="0" border="0">
              <tr align="center" height="35" class="tr_center" bgcolor="#000066">
                <td width="20%" class="tr_center" style="border-left: 1px solid #B5D7E6;">
                	${LANG['event.log.detail.update.type']}
                </td>
                <td width="40%" class="tr_center">${LANG['event.log.detail.update.before']}</td>
                <td width="40%" class="tr_center" style=" border-right:#B5D7E6 1px solid">${LANG['event.log.detail.update.after']}</td>
              </tr>
	    <c:if test="${fn:length(optDescList)<=0}">
			<tr class="table001" height="32"  >
				<td class="STYLE19" height="32" colspan="11" align="center" style="border-left:#D2D8DB 1px solid;border-right:#D2D8DB 1px solid"> ${LANG['website.common.msg.list.nodata']} </td>
			</tr>
		</c:if>
		<c:forEach var="optDesc" items="${optDescList}" varStatus="status">
              <tr align="center" bgcolor="#FFFFFF" height="30">
              	<c:set var="fieldName" value="${optDesc[0]}.${optDesc[1]}"></c:set>
                <td class="tr_main"  style="border-left:#D2D8DB 1px solid">
                	${LANG[fieldName]}
<!--					${fieldName }-->
                </td>
                <td class="tr_main" align="center">
                	<div style="width: 250px;text-align: center;overflow: hidden;">
	                	<c:if test="${!empty  optDesc[2]}">
	                		${optDesc[2]}
	                	</c:if>
	                	<c:if test="${empty  optDesc[2]}">
	                		&nbsp;
	                	</c:if>
                	</div>
                </td>
                <td class="tr_main" align="center" style="border-right:#D2D8DB 1px solid">
                	<div style="width: 250px;text-align: center;overflow: hidden;">
	                	<c:if test="${!empty  optDesc[3]}">
	                		${optDesc[3]}
	                	</c:if>
	                	<c:if test="${empty  optDesc[3]}">
	                		&nbsp;
	                	</c:if>
	                </div>
                </td>
              </tr>
        </c:forEach>
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
	var frame = parent.$("#optDescDiv");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.parent.$("#optDescDiv");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
