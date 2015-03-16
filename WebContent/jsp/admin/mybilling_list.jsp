<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/page.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<title>index</title>
<style>
.tr_center {
	border:#D2D8DB 1px solid;
	border-right:none;
	background: url("/static/images/table_head_bg.jpg") repeat-x scroll 0 0 transparent;
}
.tr_main {
	border-bottom:#D2D8DB 1px solid;
	color:#666666;
}
.tr_top {
	border:#D2D8DB 1px solid;
	border-top:none;
	border-bottom:none;
	margin-top:26px;
	background: url("/static/images/table_head_bg.jpg") repeat-x scroll 0 0 transparent;
		border-left: 1px solid #B5D7E6;
	border-right: 1px solid #B5D7E6;
}
.tr_bottom {
	border:#D2D8DB 1px solid;
	border-top:none
}
.no-data {
	border-left: 1px solid #B5D7E6;
	border-right: 1px solid #B5D7E6;
	border-bottom: 1px solid #B5D7E6;
}
.zd01, .zd02 {
	margin: 5px;
	padding: 2px;
	border: 1px solid #D2D8DB;
}

#emile_button {
    background: url("/static/images/emile_button01.jpg") no-repeat scroll 0 0 transparent;
    border: medium none;
    cursor: pointer;
    height: 24px;
    margin-right: 10px;
    width: 72px;
}
</style>
<script type="text/javascript">
function viewDetail(id){
		parent.parent.showTelDetail(id,"${year}","${month}");
}

function submitQuery(){
	$("#ipt_isExport").val("false");
	$("#query").submit();
}

function submitToExport(){
	$("#ipt_isExport").val("true");
	$("#query").submit();
	$("#ipt_isExport").val("false");
}
function resetHeight() {
	parent.getHeight();
}

function exportExcel(){
	var url = "/common/billing/siteBillList?isExport=true";
	url +="&year="+$("#yearSelect").val();
	url +="&month="+$("#monthSelect").val();
	window.location = url;
}
</script>
</head>
<body onload="resetHeight()">
<form id="query" name="query" action="/common/billing/siteBillList" method="post">
    <input type="hidden" id="ipt_isExport" name="isExport" value="false" />
    <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
      <tr>
        <td height="40" colspan="3" class="tr_top" style="border-right: 0px;">
           <select class="zd01" name="year" id="yearSelect" onchange="submitQuery();">
            <option value="${curryear}" <c:if test="${year eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
            <option value="${curryear-1}" <c:if test="${year eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
          </select>
          <select class="zd02" name="month" id="monthSelect" onchange="submitQuery();">
           	<c:if test="${year != curryear}">
	            <option value="1" <c:if test="${month eq '1'}">selected="selected"</c:if>>${LANG['system.billing.res.moth1']}</option>
	            <option value="2" <c:if test="${month eq '2'}">selected="selected"</c:if>>${LANG['system.billing.res.moth2']}</option>
	            <option value="3" <c:if test="${month eq '3'}">selected="selected"</c:if>>${LANG['system.billing.res.moth3']}</option>
	            <option value="4" <c:if test="${month eq '4'}">selected="selected"</c:if>>${LANG['system.billing.res.moth4']}</option>
	            <option value="5" <c:if test="${month eq '5'}">selected="selected"</c:if>>${LANG['system.billing.res.moth5']}</option>
	            <option value="6" <c:if test="${month eq '6'}">selected="selected"</c:if>>${LANG['system.billing.res.moth6']}</option>
	            <option value="7" <c:if test="${month eq '7'}">selected="selected"</c:if>>${LANG['system.billing.res.moth7']}</option>
	            <option value="8" <c:if test="${month eq '8'}">selected="selected"</c:if>>${LANG['system.billing.res.moth8']}</option>
	            <option value="9" <c:if test="${month eq '9'}">selected="selected"</c:if>>${LANG['system.billing.res.moth9']}</option>
	            <option value="10" <c:if test="${month eq '10'}">selected="selected"</c:if>>${LANG['system.billing.res.moth10']}</option>
	            <option value="11" <c:if test="${month eq '11'}">selected="selected"</c:if>>${LANG['system.billing.res.moth11']}</option>
	            <option value="12" <c:if test="${month eq '12'}">selected="selected"</c:if>>${LANG['system.billing.res.moth12']}</option>
           	</c:if>
           	<c:if test="${year eq curryear}">
           		<c:forEach begin="1" var="index" end="${currmonth-1}">
           			<c:if test="${index eq 1}">
           				<option value="1" <c:if test="${month eq '1'}">selected="selected"</c:if>>${LANG['system.billing.res.moth1']}</option>
           			</c:if>
           			<c:if test="${index eq 1}">
           				<option value="2" <c:if test="${month eq '2'}">selected="selected"</c:if>>${LANG['system.billing.res.moth2']}</option>
           			</c:if>
           			<c:if test="${index eq 3}">
           				<option value="3" <c:if test="${month eq '3'}">selected="selected"</c:if>>${LANG['system.billing.res.moth3']}</option>
           			</c:if>
           			<c:if test="${index eq 4}">
           				<option value="4" <c:if test="${month eq '4'}">selected="selected"</c:if>>${LANG['system.billing.res.moth4']}</option>
           			</c:if>
           			<c:if test="${index eq 5}">
           				<option value="5" <c:if test="${month eq '5'}">selected="selected"</c:if>>${LANG['system.billing.res.moth5']}</option>
           			</c:if>
           			<c:if test="${index eq 6}">
           				<option value="6" <c:if test="${month eq '6'}">selected="selected"</c:if>>${LANG['system.billing.res.moth6']}</option>
           			</c:if>
           			<c:if test="${index eq 7}">
           				<option value="7" <c:if test="${month eq '7'}">selected="selected"</c:if>>${LANG['system.billing.res.moth7']}</option>
           			</c:if>
           			<c:if test="${index eq 8}">
           				<option value="8" <c:if test="${month eq '8'}">selected="selected"</c:if>>${LANG['system.billing.res.moth8']}</option>
           			</c:if>
           			<c:if test="${index eq 9}">
           				<option value="9" <c:if test="${month eq '9'}">selected="selected"</c:if>>${LANG['system.billing.res.moth9']}</option>
           			</c:if>
           			<c:if test="${index eq 10}">
           				<option value="10" <c:if test="${month eq '10'}">selected="selected"</c:if>>${LANG['system.billing.res.moth10']}</option>
           			</c:if>
           			<c:if test="${index eq 11}">
           				<option value="11" <c:if test="${month eq '11'}">selected="selected"</c:if>>${LANG['system.billing.res.moth11']}</option>
           			</c:if>
           			<c:if test="${index eq 12}">
           				<option value="12" <c:if test="${month eq '12'}">selected="selected"</c:if>>${LANG['system.billing.res.moth12']}</option>
           			</c:if>
           		</c:forEach>
           	</c:if>
          </select>
        </td>
        <td class="tr_top" align="right" style="border-left: 0;">
        	<input type="button" onclick="exportExcel();" value="导出" id="emile_button">
        </td>
      </tr>
      <tr align="right" bgcolor="#FFFFFF" height="32">
        <td class="tr_main" colspan="4" style=" border-left:#B5D7E6 1px solid; border-right:#B5D7E6 1px solid;padding-right:10px; border-bottom:none"> 
        	${LANG['bizconf.jsp.admin.mybilling_list.res3']}<c:out value="${myfn:fmtDate('yyyy-MM-dd',startDate,28800000)}"/>&nbsp; ${LANG['bizconf.jsp.admin.mybilling_list.res4']} &nbsp; <fmt:formatDate value="${endDate}" type="date" pattern="yyyy-MM-dd"/>
        </td>
      </tr>
      <tr align="right" bgcolor="#FFFFFF" height="32">
        <td width="50%" colspan="2" class="tr_main" style=" border-left:#B5D7E6 1px solid; padding-left:10px;border-bottom: 0px; " align="left"><strong>${LANG['bizconf.jsp.admin.mybilling_list.res5']}${site.siteName}</strong> </td>
        <td width="50%" colspan="2" class="tr_main" style="  border-right:#B5D7E6 1px solid;padding-right:10px;border-bottom: 0px;">${LANG['bizconf.jsp.admin.mybilling_list.res6']}
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
	  <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32" style="border-bottom: 0px;" >
			            <td class="no-data" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
	  <c:forEach var="bill" items="${pageModel.datas}" varStatus="status">
	        <tr align="center" height="35" class="tr_center" bgcolor="#000066" >
	         <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; padding-right:10px; padding-left:55px;" align="left"><strong>${LANG['bizconf.jsp.admin.mybilling_list.res7']}</strong></td>
	        <td class="tr_center" colspan="2" style="border-right:#D2D8DB 1px solid; padding-right:10px; color:#996600; border-left:none;border-top:none" align="right"><strong>${LANG['bizconf.jsp.admin.mybilling_list.res8']}<fmt:formatNumber value="${bill.totalFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</strong></td>
	      </tr>
	      <c:choose>
		  		<c:when test="${bill.dataFee gt 0}">
		  			 <tr style="cursor:pointer;" align="left" bgcolor="#FFFFFF" height="32" class="tongxin" onclick="parent.parent.showDataFeeDetail(${bill.id},'${year}','${month}');">
		  		</c:when>
		  		<c:otherwise>
		  			 <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
		  		</c:otherwise>
		  	</c:choose>
	        	<td class="tr_main" colspan="2" style="border-left:#D2D8DB 1px solid;  padding-left:100px; border-bottom:dashed 1px #D2D8DB;">${LANG['bizconf.jsp.admin.mybilling_list.res10']}</td>
	        	<td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid;  border-right:#D2D8DB 1px solid;padding-right:50px;border-left:none;border-bottom:dashed 1px #D2D8DB; " align="right"><span><fmt:formatNumber value="${bill.dataFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</span></td>
	      </tr>
	      
	      <c:choose>
		  		<c:when test="${bill.telFee gt 0}">
		  			 <tr style="cursor:pointer;" align="left" bgcolor="#FFFFFF" height="32" class="tongxin" onclick="viewDetail(${bill.id});">
		  		</c:when>
		  		<c:otherwise>
		  			 <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
		  		</c:otherwise>
		  	</c:choose>
	        <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; padding-left:100px;">${LANG['bizconf.jsp.admin.mybilling_list.res11']}</td>
	        <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;padding-right:50px;border-left:none; " align="right"><span><fmt:formatNumber value="${bill.telFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</span></td>
	      </tr>
	     </tr>
	  </c:forEach>
    </table>
    </form>
</body>
</html>

