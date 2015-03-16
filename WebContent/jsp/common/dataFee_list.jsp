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
</head>
<body onload="loaded();">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
        <div class="First_Steps_quick_e" style=" background:#FFF;">
          <div class="First_Steps_title_e"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.common.bill_detaillist.res1']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.bill_detaillist.titleinfo']}</p>
          </div>
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
            <table cellpadding="0" cellspacing="0" border="0" class="chaxun_main">
                <tr class="cx03">
                  <td width="40%">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
                  <td width="40%">${LANG['bizconf.jsp.common.dataFee_list.res1']}</td>
                  <td width="20%">${LANG['bizconf.jsp.common.dataFee_list.res2']}</td>
                </tr></table>
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
<%--            <div class="but160" style=" margin-bottom:70px;"><span class="button_common"><a href="javascript:closeDialog();"><img src="${baseUrlStatic}/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.common.bill_detaillist.res10']}</a></span></div>--%>
          </div>
        </div>
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>
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
