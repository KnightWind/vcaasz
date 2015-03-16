<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主持人账单详情</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link href="/assets/css/apps/orders.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="query" name="query" action="/user/billing/listDetailBills" method="post">
<%--	<div class="order-dialog" >--%>
	<div class="" >
	<div class="wrapper">
		<section>
<%--			<p class="export">--%>
<%--				<a class="input-gray" onclick="exportExcel();">${LANG['website.user.billing.datadetail.export']}</a>--%>
<%--				<input type="hidden" name="userId" value="${userId}" />--%>
<%--				<input type="hidden" name="id" value="${id}" />--%>
<%--				<input type="hidden" name="year" value="${year}" />--%>
<%--				<input type="hidden" name="month" value="${month}" />--%>
<%--			</p>--%>
			<p class="export"><a class="input-gray" onclick="javascript:viewHostBillList('${year}','${month}');">${LANG['website.message.back'] }</a> </p>
			<div class="filter">
				<select class="year" name="year" onchange="submitQuery('Y');">
					<option value="${curryear}" <c:if test="${year eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['website.user.billling.list.year']}</option>
          			<c:if test="${currmonth - 5 le 0}">
           				<option value="${curryear-1}" <c:if test="${year eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['website.user.billling.list.year']}</option>
          			</c:if>
				</select>
				<select class="month" name="month" onchange="submitQuery();">
	           		<c:if test="${currmonth - 5 le 0}">
	           			<c:if test="${year eq (curryear-1)}">
	           				<c:forEach begin="0" var="index" end="${5 - currmonth}">
		           				<c:if test="${index eq 0}">
			           				<option value="12" <c:if test="${month eq '12'}">selected="selected"</c:if>>${LANG['system.billing.res.moth12']}</option>
			           			</c:if>
		           				<c:if test="${index eq 1}">
			           				<option value="11" <c:if test="${month eq '11'}">selected="selected"</c:if>>${LANG['system.billing.res.moth11']}</option>
			           			</c:if>
		           				<c:if test="${index eq 2}">
			           				<option value="10" <c:if test="${month eq '10'}">selected="selected"</c:if>>${LANG['system.billing.res.moth10']}</option>
			           			</c:if>
		           				<c:if test="${index eq 3}">
			           				<option value="9" <c:if test="${month eq '9'}">selected="selected"</c:if>>${LANG['system.billing.res.moth9']}</option>
			           			</c:if>
		           				<c:if test="${index eq 4}">
			           				<option value="8" <c:if test="${month eq '8'}">selected="selected"</c:if>>${LANG['system.billing.res.moth8']}</option>
			           			</c:if>
		           			</c:forEach>
	           			</c:if>
	           			<c:if test="${year eq curryear}">
	           				<c:forEach begin="1" var="index" end="${currmonth}">
	           					<c:if test="${index eq 5}">
			           				<option value="5" <c:if test="${month eq '5'}">selected="selected"</c:if>>${LANG['system.billing.res.moth5']}</option>
			           			</c:if>
			           			<c:if test="${index eq 4}">
			           				<option value="4" <c:if test="${month eq '4'}">selected="selected"</c:if>>${LANG['system.billing.res.moth4']}</option>
			           			</c:if>
			           			<c:if test="${index eq 3}">
			           				<option value="3" <c:if test="${month eq '3'}">selected="selected"</c:if>>${LANG['system.billing.res.moth3']}</option>
			           			</c:if>
			           			<c:if test="${index eq 2}">
			           				<option value="2" <c:if test="${month eq '2'}">selected="selected"</c:if>>${LANG['system.billing.res.moth2']}</option>
			           			</c:if>
			           			<c:if test="${index eq 1}">
			           				<option value="1" <c:if test="${month eq '1'}">selected="selected"</c:if>>${LANG['system.billing.res.moth1']}</option>
			           			</c:if>
	           				</c:forEach>
	           			</c:if>
	           		</c:if>
	           		<c:if test="${currmonth - 5 gt 0}">
	           			<c:forEach begin="${currmonth - 5}" var="index" end="${currmonth}">
		           			<c:if test="${index eq 1}">
		           				<option value="1" <c:if test="${month eq '1'}">selected="selected"</c:if>>${LANG['system.billing.res.moth1']}</option>
		           			</c:if>
		           			<c:if test="${index eq 2}">
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
				<span class="range">
					${LANG['website.user.billling.list.cycle']}<c:out value="${myfn:fmtDate('yyyy-MM-dd',startDate,user.timeZone)}"/> <i>${LANG['website.user.billling.list.to']}</i> <fmt:formatDate value="${endDate}" type="date" pattern="yyyy-MM-dd"/>
				</span>
			</div>
			<div class="details">
				<c:if test="${fn:length(pageModel.datas) gt 0}">
				<table class="common-table">
					<tbody>
						<c:forEach var="confBill" items="${pageModel.datas }">
							<tr>
								<td colspan="5" class="name">Meeting ID：${confBill.zcr.zoomId }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${LANG['website.user.billling.teldetail.conftheme']}${confBill.zcr.topic } </td>
							</tr>
							<tr>
								<th class="name">${LANG['website.admin.bill.data.title.description'] }</th>
								<th class="type">${LANG['website.admin.bill.data.title.access.way'] }</th>
<!-- 									<th class="number">${LANG['website.admin.bill.data.title.call.number'] }</th> -->
<!-- 									<th class="name">${LANG['website.admin.bill.data.title.event'] }</th> -->
								<th class="start">${LANG['website.admin.bill.data.title.starttime'] }</th>
								<th class="lasted">${LANG['website.admin.bill.data.title.dutation'] }</th>
								<th class="cost">${LANG['website.admin.bill.data.title.money'] }</th>
							</tr>
							<c:forEach var="videoBill" items="${confBill.videoBillings}" varStatus="statusV">
								<tr>
									<td class="first-child name" title="${videoBill.name }">${videoBill.name }</td>
									<td class="type">Client</td>
<!-- 										<td class="number"></td> -->
<!-- 										<td class="name">NameHost</td> -->
									<td class="start">
										<c:if test="${site.hireMode eq 1 }"><!-- 包月入会时间 是zoomConfRecord中的标准时间 -->
											<c:out value="${myfn:fmtDate('yyyy-MM-dd HH:mm',videoBill.startTime,user.timeZone)}"/>
										</c:if>
										<c:if test="${site.hireMode eq 2 }"><!-- 计时入会时间 是billing中的北京时间 -->
											<c:out value="${myfn:fmtDateByBJ('yyyy-MM-dd HH:mm',videoBill.startTime,user.timeZone)}"/>
										</c:if>
									</td>
									<td class="lasted">${videoBill.showDuration }</td>
									<td class="last-child cost"><fmt:formatNumber value="${videoBill.charge}" pattern="￥0.00" type="number"/></td>
								</tr>	
							</c:forEach>
							<c:forEach var="tellBill" items="${confBill.telBillings}" varStatus="statusT">
								<tr>
									<td class="first-child name" title="${tellBill.name }">${tellBill.name }</td>
									<td class="type">Tel</td>
<!-- 										<td class="number">${tellBill.dnis}</td> -->
<!-- 										<td class="name">Audio-Tel</td> -->
									<td class="start"><c:out value="${myfn:fmtDateByBJ('yyyy-MM-dd HH:mm',tellBill.startTime,user.timeZone)}"/></td>

									<td class="lasted">${tellBill.showDuration }</td>

									<td class="last-child cost"><fmt:formatNumber value="${tellBill.charge}" pattern="￥0.00" type="number"/></td>
								</tr>	
							</c:forEach>
							<tr><td colspan="5" style="text-align: right;">${LANG['website.admin.bill.data.title.money'] }：<fmt:formatNumber value="${confBill.sumCharge}" pattern="￥0.00" type="number"/></td></tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
				<c:if test="${fn:length(pageModel.datas) le 0}">
<%--				<tr><td colspan="5" class="name">${LANG['website.admin.bill.data.no'] }</td></tr>--%>
					<table class="common-table">
						<tbody>
							<tr><p style="margin-left: 20px;margin-top: 10px;">${LANG['website.admin.bill.data.no'] }</p></tr>
						</tbody>
					</table>
				</c:if>
				<c:if test="${fn:length(pageModel.datas) gt 0}">
					<div class="pagebar clearfix" style="margin-top: 2px;">
						<jsp:include page="page.jsp" />
					</div>
				</c:if>	
			</div>
		</section>
<%--		<div class="form-buttons">--%>
<%--			<input type="button" class="button input-gray" onclick="closeDialog();" value="${LANG['website.common.option.confirm']}" />--%>
<%--		</div>--%>
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
$(function(){
	//resetFrameHeight();
});
function viewHostBillList(year,month){
	window.location = "/user/billing/listTotalBill?year="+year+"&month="+month;
}
 
function exportExcel(){
	var url = "/common/billing/showTelDetail?isExport=true";
	url +="&year="+$("input[name=year]").val();
	url +="&month="+$("input[name=month]").val();
	url +="&userId="+$("input[name=userId]").val();
	url +="&id="+$("input[name=id]").val();
	window.location = url;
}
function submitQuery(Year){
	if(Year == 'Y'){//表示select，name=‘year’改变
		//alert("year>>>${year}   >>>curryear>>>>${ curryear}");
		if("${year}" == "${(curryear-1)}"){
			$("select[name='month'] option").attr("value","1");
		}else if("${year }" == "${ curryear}"){
			$("select[name='month'] option").attr("value","12");
		}
	}
	$("#query").submit();
	resetFrameHeight();
}
function closeDialog(result) {
	var dialog = parent.$("#billingView");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
function resetFrameHeight(){
    var pageHeight=document.body.scrollHeight;
    var pageHeight=pageHeight+5;
    parent.resizeHeight(749);
}
</script>
</body>
</html>
