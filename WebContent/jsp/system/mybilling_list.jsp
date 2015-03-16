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
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css?ver=${version}"/>
<title>index</title>
<style>
.tr_center {
	border:#D2D8DB 1px solid;
	border-right:none;
}
.tr_main {
	border-bottom:#D2D8DB 1px solid;
	color:#666666;
}
.tr_top {
	border:#D2D8DB 1px solid;
	border-bottom:none;
	margin-top:26px;
	background: url("/static/images/table_head_bg.jpg") repeat-x scroll 0 0 transparent;
	border-top: 0px;
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
}
.zd01, .zd02 {
    border: 1px solid #D2D8DB;
    margin: 5px;
    padding: 2px;
}
</style>
<script type="text/javascript">
function viewDetail(id){
		parent.parent.showTelDetail(id,'${year}','${month}');
}

function submitQuery(){
	var startyear =  $("#yearSelect").val(); 
	var startmonth =  $("#monthSelect").val(); 
	var endyear =  $("#yearSelecte").val();
	var endmonth =  $("#monthSelecte").val();
	if(startyear>endyear){
		parent.parent.errorDialog("${LANG['bizconf.jsp.create_Reservation_Conf.afterStart']}");
		return;
	}else if(startyear==endyear && startmonth>endmonth){
		parent.parent.errorDialog("${LANG['bizconf.jsp.create_Reservation_Conf.afterStart']}");
		return;
	}
	$("#isExport").val("false");
	$("#query").submit();
}

function exportExcel(){
	$("#isExport").val("true");
	//$("#query").attr("action","/common/billing/sysBillListExp");
	$("#query").submit();
	$("#isExport").val("false");
	//$("#query").attr("action","/common/billing/sysBillList");
}

function testExp(){
	$("#query").attr("action","/common/billing/sysBillListExp");
	$("#query").submit();
	$("#query").attr("action","/common/billing/sysBillList");
}

var currMonthStr = "<option value=\"1\" <c:if test="${month eq '1'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth1']}</option>";
	currMonthStr += "<option value=\"2\" <c:if test="${month eq '2'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth2']}</option>";
	currMonthStr += "<option value=\"3\" <c:if test="${month eq '3'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth3']}</option>";
	currMonthStr += "<option value=\"4\" <c:if test="${month eq '4'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth4']}</option>";
	currMonthStr += "<option value=\"5\" <c:if test="${month eq '5'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth5']}</option>";
	currMonthStr += "<option value=\"6\" <c:if test="${month eq '6'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth6']}</option>";
	currMonthStr += "<option value=\"7\" <c:if test="${month eq '7'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth7']}</option>";
	currMonthStr += "<option value=\"8\" <c:if test="${month eq '8'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth8']}</option>";
	currMonthStr += "<option value=\"9\" <c:if test="${month eq '9'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth9']}</option>";
	currMonthStr += "<option value=\"10\" <c:if test="${month eq '10'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth10']}</option>";
	currMonthStr += "<option value=\"11\" <c:if test="${month eq '11'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth11']}</option>";
	currMonthStr += "<option value=\"12\" <c:if test="${month eq '12'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth12']}</option>";

var selMonthStr = "";
	<c:forEach begin="1" var="index" end="${currmonth-1}">
		<c:if test="${index eq 1}">
			selMonthStr += "<option value=\"1\" <c:if test="${month eq '1'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth1']}</option>";
		</c:if>
		<c:if test="${index eq 1}">
			selMonthStr += "<option value=\"2\" <c:if test="${month eq '2'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth2']}</option>";
		</c:if>
		<c:if test="${index eq 3}">
			selMonthStr += "<option value=\"3\" <c:if test="${month eq '3'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth3']}</option>";
		</c:if>
		<c:if test="${index eq 4}">
			selMonthStr += "<option value=\"4\" <c:if test="${month eq '4'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth4']}</option>";
		</c:if>
		<c:if test="${index eq 5}">
			selMonthStr += "<option value=\"5\" <c:if test="${month eq '5'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth5']}</option>";
		</c:if>
		<c:if test="${index eq 6}">
			selMonthStr += "<option value=\"6\" <c:if test="${month eq '6'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth6']}</option>";
		</c:if>
		<c:if test="${index eq 7}">
			selMonthStr += "<option value=\"7\" <c:if test="${month eq '7'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth7']}</option>";
		</c:if>
		<c:if test="${index eq 8}">
			selMonthStr += "<option value=\"8\" <c:if test="${month eq '8'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth8']}</option>";
		</c:if>
		<c:if test="${index eq 9}">
			selMonthStr += "<option value=\"9\" <c:if test="${month eq '9'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth9']}</option>";
		</c:if>
		<c:if test="${index eq 10}">
			selMonthStr += "<option value=\"10\" <c:if test="${month eq '10'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth10']}</option>";
		</c:if>
		<c:if test="${index eq 11}">
			selMonthStr += "<option value=\"11\" <c:if test="${month eq '11'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth11']}</option>";
		</c:if>
		<c:if test="${index eq 12}">
			selMonthStr += "<option value=\"12\" <c:if test="${month eq '12'}">selected=\"selected\"</c:if>>${LANG['system.billing.res.moth12']}</option>";
		</c:if>
	</c:forEach>
	
	
	$(document).ready(function(){
		
		
		if($("#yearSelect").val()=='${curryear}'){
			$("#monthSelect").html(selMonthStr);
		}else{
			$("#monthSelect").html(currMonthStr);
		}	
		if($("#yearSelecte").val()=='${curryear}'){
			$("#monthSelecte").html(selMonthStr);
		}else{
			$("#monthSelecte").html(currMonthStr);
		}	

		$("#yearSelect").change(function(){
			if($(this).val()=='${curryear}'){
				$("#monthSelect").html(selMonthStr);
			}else{
				$("#monthSelect").html(currMonthStr);
			}			
		});
		
		$("#yearSelecte").change(function(){
			if($(this).val()=='${curryear}'){
				$("#monthSelecte").html(selMonthStr);
			}else{
				$("#monthSelecte").html(currMonthStr);
			}				
		});
		
		$("input[name=siteName]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#isExport").val("false");
				$("#pageNo").val("");
				$("#query").submit();
			}
		});
		
		$("input[name=siteSign]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#isExport").val("false");
				$("#pageNo").val("");
				$("#query").submit();
			}
		});
		resetHeight();

		$("#monthSelecte").val('${emonth}');
	});
	
	function resetHeight() {
		parent.getHeight();
	}
	
	function exportExcel_new(){
		var url = "/common/billing/sysBillList?isExport=true";
			url +="&year="+$("#yearSelect").val();
			url +="&month="+$("#monthSelect").val();
			url +="&eyear="+$("#yearSelecte").val();
			url +="&emonth="+$("#monthSelecte").val();
			url +="&siteName="+encodeURIComponent($("input[name=siteName]").val());
			url +="&siteSign="+encodeURIComponent($("input[name=siteSign]").val());
			window.location = url;
	}
</script>
</head>
<body>
<form id="query" name="query" action="/common/billing/sysBillList" method="post">
    <input type="hidden" id="isExport" name="isExport" value="false" />
   <div class="m_top" style="margin-bottom: 10px;height: 60px;">
    <div>
    	 <select class="zd01" name="year" id="yearSelect" >
            <option value="${curryear}" <c:if test="${year eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
            <option value="${curryear-1}" <c:if test="${year eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
          </select>
          <select class="zd02" name="month" id="monthSelect">
          </select>
          --
           <select class="zd01" name="eyear" id="yearSelecte" >
            <option value="${curryear}" <c:if test="${eyear eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
            <option value="${curryear-1}" <c:if test="${eyear eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
          </select>
          <select class="zd02" name="emonth" id="monthSelecte">
          </select>
    </div>
    <div style="padding-left: 5px;">
    	${LANG['bizconf.jsp.system.site.name']}：<input name="siteName" type="text" style="border:#D2D8DB 1px solid;height: 22px;" value="${siteName}"> &nbsp; ${LANG['system.eventlog.title.site.sign']}：<input name="siteSign" style="border:#D2D8DB 1px solid;height: 22px;" type="text" value="${siteSign}" >
         <a href="javascript:submitQuery();" class="zengjia" style="display: inline-block;margin-bottom: 3px;" ><b>${LANG['system.search']}</b></a> 
		 <a href="javascript:exportExcel_new();" class="zengjia" style="display: inline-block;margin-bottom: 3px;" ><b>${LANG['bizconf.jsp.conflogs.res1']}</b></a> 
<%--		 <a href="javascript:testExp();" class="zengjia" style="display: inline-block;margin-bottom: 3px;" ><b>${LANG['bizconf.jsp.conflogs.res1']}-test</b></a> --%>
    </div>
  	</div>
    <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
      <tr>
        <td height="40" colspan="4" bgcolor="#EAF4FC" class="tr_top" align="right">
           	${LANG['bizconf.jsp.common.bill_detaillist.res9']}<fmt:formatNumber value=" ${amount}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}
        </td>
      </tr>
      	<c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="no-data" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
		</c:if>
	  <c:forEach var="bill" items="${pageModel.datas}" varStatus="status">
      <tr align="right" bgcolor="#FFFFFF" height="32">
        <td class="tr_main" colspan="4" style=" border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;padding-right:10px; border-bottom:none"> 
        	${LANG['bizconf.jsp.admin.mybilling_list.res3']}<c:out value="${myfn:showBillStartDate(bill.billDate,0) }"/> &nbsp; ${LANG['bizconf.jsp.admin.mybilling_list.res4']} &nbsp; <c:out value="${myfn:showBillEndDate(bill.billDate,0) }"/>
        </td>
      </tr>
      
      <tr align="right" bgcolor="#FFFFFF" height="32">
        <td width="50%" colspan="2" class="tr_main" style=" border-left:#D2D8DB 1px solid; padding-left:10px; " align="left"><strong>${LANG['bizconf.jsp.admin.mybilling_list.res5']}${siteMap[bill.siteSign].siteName}</strong> </td>
        <td width="50%" colspan="2" class="tr_main" style="  border-right:#D2D8DB 1px solid;padding-right:10px;">${LANG['bizconf.jsp.admin.mybilling_list.res6']}
        <c:choose>
	  	 	<c:when test="${siteMap[bill.siteSign].chargeMode eq 1 }">
	  	 		name host
	  	 	</c:when>
	  	 	<c:when test="${siteMap[bill.siteSign].chargeMode eq 2 }">
	  	 		active users
	  	 	</c:when>
	  	 	<c:when test="${siteMap[bill.siteSign].chargeMode eq 3 }">
	  	 		seats
	  	 	</c:when>
	  	 	<c:when test="${siteMap[bill.siteSign].chargeMode eq 4 }">
			  	 meeting time
			</c:when>
			<c:when test="${siteMap[bill.siteSign].chargeMode eq 5 }">
			  	 user time
			</c:when>
	  	 </c:choose>
	  </td>
	     </tr>
	        <tr align="center" height="35" class="tr_center" bgcolor="#000066" >
	         <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; padding-right:10px; padding-left:55px;" align="left"><strong>${LANG['bizconf.jsp.admin.mybilling_list.res7']}</strong></td>
	        <td class="tr_center" colspan="2" style="border-right:#D2D8DB 1px solid; padding-right:10px; color:#996600; border-left:none;border-top:none" align="right">
	        <strong>
	        	${LANG['bizconf.jsp.admin.mybilling_list.res8']}<fmt:formatNumber value="${bill.totalFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}
	        </strong>
	        </td>
		  </tr>
		  	<c:choose>
		  		<c:when test="${bill.dataFee gt 0}">
		  			 <tr style="cursor:pointer;" align="left" bgcolor="#FFFFFF" height="32" class="tongxin" onclick="parent.parent.showDataFeeDetail(${bill.id},'${year}','${month}');">
		  		</c:when>
		  		<c:otherwise>
		  			 <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
		  		</c:otherwise>
		  	</c:choose>
		    <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; cursor:pointer; padding-left:100px; border-bottom:dashed 1px #D2D8DB;" >${LANG['bizconf.jsp.admin.mybilling_list.res10']}</td>
	        <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid; cursor:pointer; border-right:#D2D8DB 1px solid;padding-right:50px;border-left:none;border-bottom:dashed 1px #D2D8DB; " align="right">
	        	<span><fmt:formatNumber value=" ${bill.dataFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}</span>
	        </td>
	      </tr>
	      
	      <c:choose>
		  		<c:when test="${bill.telFee gt 0}">
		  			 <tr style="cursor:pointer;" align="left" bgcolor="#FFFFFF" height="32" class="tongxin" onclick="viewDetail(${bill.id});">
		  		</c:when>
		  		<c:otherwise>
		  			 <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
		  		</c:otherwise>
		  	</c:choose>
	        <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid;  padding-left:100px;">${LANG['bizconf.jsp.admin.mybilling_list.res11']}</td>
	        <td class="tr_main" colspan="2" style=" border-left:#D2D8DB 1px solid;  border-right:#D2D8DB 1px solid;padding-right:50px;border-left:none; " align="right">
	        <span>
	        	<fmt:formatNumber value="${bill.telFee}" pattern="0.00" type="number"/>${LANG['bizconf.jsp.admin.mybilling_list.res9']}
	        </span>
	        </td>
	      </tr>
	      <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
	        	<td class="tr_main" colspan="4" style=" border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid; padding-left:100px;">&nbsp;</td>
	      </tr>
	  </c:forEach>
	      <tr align="left" bgcolor="#FFFFFF" height="32" class="tongxin">
	        	<td class="tr_main" colspan="4" style=" border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid; padding-left:100px;">
	        		 <jsp:include page="/jsp/common/page_info.jsp"/>
	        	</td>
	      </tr>
	      <tr>
	      	<td style="height: 50px;" colspan="4">
	      	</td>
	      </tr>
    </table>
    </form>
</body>
</html>

