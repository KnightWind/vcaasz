<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<title>${LANG['bizconf.jsp.common.bill_detaillist.res1']}</title>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	//function exportExcel(){
	//		$("#exportform").submit();
	//	}
	
	function exportExcel(){
		var url = "/common/billing/sysShowDataDetail?isExport=true";
		url +="&year="+$("input[name=year]").val();
		url +="&month="+$("input[name=month]").val();
		url +="&userId="+$("input[name=userId]").val();
		url +="&id="+$("input[name=id]").val();
		window.location = url;
	}
</script>
<style type="text/css">
#emile_button {
    background: url("/static/images/emile_button01.jpg") no-repeat scroll 0 0 transparent;
    border: medium none;
    height: 24px;
    margin-left: 180px;
    width: 72px;
    cursor: pointer;
}
</style>
</head>

<body onload="loaded();">
<div class="First_Steps_quick_e" style=" background:#FFF;margin: 0 auto;">
          <p style="margin: 15px 20px;color:#666666;">${LANG['bizconf.jsp.common.bill_detaillist.titleinfo']}</p>
          <div style=" background:#fff"><img class="toa_quick" src="${baseUrlStatic}/images/min.jpg" width="560" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick" >
            <table cellpadding="0" cellspacing="0" border="0" class="chaxun_top">
              <tr class="cx01">
                <td style=" font-weight:bold;">
                	<c:choose>
			  	 	<c:when test="${site.chargeMode eq 1 }">
			  	 		name host
			  	 	</c:when>
			  	 	<c:when test="${site.chargeMode eq 2 }">
			  	 		active users
			  	 	</c:when>
			  	 	<c:when test="${site.chargeMode eq 3 }">
			  	 		seats
			  	 	</c:when>
			  	 	<c:when test="${site.chargeMode eq 4 }">
			  	 		meeting time
			  	 	</c:when>
			  	 	<c:when test="${site.chargeMode eq 5 }">
			  	 		user time
			  	 	</c:when>
			  	 </c:choose>
                </td>
                <td align="right"><strong style=" color:#F00">${LANG['bizconf.jsp.admin.mybilling_list.res8']}<fmt:formatNumber value="${total}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</strong></td>
              </tr>
            </table>
            <div class="chaxun_main" align="right" style="height: 24px;margin-top: 2px;">
				<input type="button" id="emile_button" value="${LANG['bizconf.jsp.conflogs.res1']}" onclick="exportExcel();" style="line-height: 24px;"/>
			</div>
            <table cellpadding="0" cellspacing="0" border="0" class="chaxun_main">
                <tr class="cx03">
                  <td width="40%">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
                  <td width="40%">${LANG['bizconf.jsp.common.dataFee_list.res1']}</td>
                  <td width="20%">${LANG['bizconf.jsp.common.dataFee_list.res2']}</td>
                </tr></table>
			<form id="exportform" action="/common/billing/sysShowDataDetail" method="post">
			<input type="hidden" name="isExport" value="true"  />
			<input type="hidden" name="userId" value="${userId}" />
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="year" value="${year}" />
			<input type="hidden" name="month" value="${month}" />
            <div class="div_overflow" style="width: 600px;height: 400px;overflow-y:auto;overflow-x: hidden">
              <table cellpadding="0" cellspacing="0" border="0" class="chaxun_top">
              </table>
              <table cellpadding="0" cellspacing="0" border="0" class="chaxun_main">
             <c:if test="${fn:length(billings)<=0}">
			           <tr class="cx04"><td width="100%">${LANG['website.common.msg.list.nodata']}</td></tr>
			</c:if>
			<c:forEach var="bill" items="${billings}" varStatus="status">
	                <tr class="cx04">
	                  <td width="40%">
	                  <c:choose>
	                  	<c:when test="${bill.userId != ''}">
	                  		${bill.userId}
	                  	</c:when>
	                 	 <c:otherwise>
							${bill.siteSign}	                 	 
	                 	 </c:otherwise>
	                  </c:choose>
	                  </td>
	                  <td width="40%">
	                  	<c:choose>
		                  	<c:when test="${site.chargeMode eq 2 }">
		                  		${site.license}
		                  	</c:when>
	                  		<c:when test="${bill.userId != '' }">
	                  			${licMap[bill.userId]}
	                  		</c:when>
	                  		<c:otherwise>
	                  			${sitelic}
	                  		</c:otherwise>
	                  	</c:choose>
	                  </td>
	                  <td width="20%"><fmt:formatNumber value=" ${bill.dataFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</td>
	                </tr>
                </c:forEach>
              </table>
            </div>
            </form>
<%--            <div class="but160" style=" margin-bottom:70px;"><span class="button_common"><a href="javascript:closeDialog();"><img src="${baseUrlStatic}/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.common.bill_detaillist.res10']}</a></span></div>--%>
          </div>
        </div>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#dataFeeView");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#dataFeeView");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
