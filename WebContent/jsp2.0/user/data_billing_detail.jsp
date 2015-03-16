<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
 <body>
   <div class="order-dialog"  >	
   	<div class="wrapper">
   		<section>
		   	<p class="export">
				<a class="input-gray" onclick="exportExcel();">${LANG['website.user.billing.datadetail.export']}</a>
				<input type="hidden" name="userId" value="${userId}" />
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="year" value="${year}" />
				<input type="hidden" name="month" value="${month}" />
			</p>
			<div class="panel data-billing-panel">
					<c:forEach var="confBill" items="${pageModel.datas}" varStatus="status">
					<div class="order-item">
						<div class="title">
							${LANG['website.user.billing.datadetail.theme']} ${confBill.conf.confName}
						</div>
						<div class="info">
							<span class="number">${LANG['website.user.billing.datadetail.meetingno']}${confBill.confZoomId}</span>
							<span class="compere">${LANG['website.user.billing.datadetail.host']}${confBill.conf.compereName}</span>
							<span class="lasted">${LANG['website.user.billing.datadetail.meetingtime']} ${confBill.showDuration }</span>
							<span class="total">${LANG['website.user.billing.datadetail.totalmoney']}<fmt:formatNumber value="${confBill.sum}" pattern="0.00" type="number"/>${LANG['website.user.billing.datadetail.yuan']}</span>
						</div>
						<div class="details">
							<table class="common-table">
								<tbody>
									<tr>
										<th style="width:30%" class="name">${LANG['website.user.billing.datadetail.name']}</th>
										<th class="start">${LANG['website.user.billing.datadetail.starttime']}</th>
			<%--							<th class="end">${LANG['website.user.billing.datadetail.endtime']}</th>--%>
										<th class="lasted">${LANG['website.user.billing.datadetail.attendtime']}</th>
										<th class="cost">${LANG['website.user.billing.datadetail.fee']}</th>
									</tr>
									<c:forEach  var="bill" items="${confBill.billings}" varStatus="status1">
										<tr>
											<td class="first-child name">${bill.userName}</td>
											<td class="start"><fmt:formatDate value="${bill.startDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<%--								<td class="end"><fmt:formatDate value="${myfn:getEndDate(bill.startDate,bill.duration)}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
											<td class="lasted">${bill.showDuration}</td>
											<td class="last-child cost"><fmt:formatNumber value="${bill.totalFee}" pattern="0.00" type="number"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					</c:forEach>
				</div>			   		
   		</section>
		<div class="form-buttons" >
			<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.close']}</a>
		</div>   		
   	</div>
</div>
</body>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">

function exportExcel(){
	var url = "/common/billing/showDataDetail?isExport=true";
	url +="&year="+$("input[name=year]").val();
	url +="&month="+$("input[name=month]").val();
	url +="&userId="+$("input[name=userId]").val();
	url +="&id="+$("input[name=id]").val();
	window.location = url;
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
</html>
