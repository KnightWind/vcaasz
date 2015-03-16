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
<link href="/assets/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="/assets/css/base.css" rel="stylesheet" type="text/css">
<link href="/assets/css/apps/orders.css" rel="stylesheet" type="text/css">
<style>
	table{
		border-left: solid 1px #C0C0C0; border-top: solid 1px #C0C0C0;
	}
    td{ 
    	border-right: solid 1px #C0C0C0; border-bottom: solid 1px #C0C0C0;
    }
    ul{	
    	line-height:30px;
    }
</style>
</head>
<body>
<form id="query" name="query" action="/admin/billing/listDetailBills" method="post">
    	<div class="box">
			<div class="head">
				<span class="title">${LANG['website.user.billling.list.titleinfo1']}</span>
				<span class="desc">${LANG['website.user.billling.list.titleinfo2']}</span>
				<p class="export"><a class="input-gray" onclick="javascript:viewHostBills('${year}','${month}');">${LANG['website.message.back'] }</a> </p>
			</div>
			<div class="body">
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
						${LANG['website.user.billling.list.cycle']}<c:out value="${myfn:fmtDate('yyyy-MM-dd',startDate,28800000)}"/> <i>${LANG['website.user.billling.list.to']}</i> <fmt:formatDate value="${endDate}" type="date" pattern="yyyy-MM-dd"/>
					</span>
				</div>
				<div class="results">
					<c:choose>
						<c:when test="${not empty userBillings}">
							<div class="reporting" style="width: 100%;">
								<div class="header" style="width: 90%;margin-left: 20px;">
									<span class="user">${LANG['website.user.billling.list.username']} ${user.trueName}</span>
									<span class="mode">${LANG['website.user.billling.list.chargemodel']}
										<c:choose>
									  	 	<c:when test="${site.hireMode eq 1 }">
									  	 		${LANG['website.admin.sitebase.setting.rentmodelmothly']}
									  	 	</c:when>
									  	 	 <c:otherwise>
												${LANG['website.admin.sitebase.setting.rentmodeltime']}						  	 	 
									  	 	 </c:otherwise>
									  	 </c:choose>
									</span>
								</div>
								<div class="results" style="width: 90%;">
									<table class="common-table">
										<tbody>
											<tr>
												<th>${LANG['website.admin.bill.data.title.account'] }</th>
												<th>${LANG['website.admin.bill.data.title.username'] }</th>
												<th>${LANG['website.admin.bill.data.title.service.item'] }</th>
												<c:choose>
											  	 	<c:when test="${site.hireMode eq 1 }">
											  	 		<th>${LANG['website.admin.bill.data.title.count'] }</th>
											  	 	</c:when>
											  	 	 <c:otherwise>
														<th>${LANG['website.admin.bill.data.title.dutation'] }</th>						  	 	 
											  	 	 </c:otherwise>
											  	 </c:choose>
												<th>${LANG['website.admin.bill.data.title.money'] }</th>
												<th>${LANG['website.admin.bill.data.title.meeting.operation'] }</th>
											</tr>
											
											<c:forEach var="userBill" items="${userBillings}" varStatus="statu">
												<tr>
													<td><span class="name"><a href="javascript:viewHostBillDetail('${userBill.hostId}');">${userBill.account }</a></span></td>
													<td><span class="name">${userBill.userName}</span></td>
													<td>
														<c:forEach items="${userBill.serviceItemBillings}" var="itemBill" varStatus="status">
															<c:if test="${itemBill.itemId eq 1}">
																<ul>${LANG['website.user.charge.mode.1']}</ul>
															</c:if>
															<c:if test="${itemBill.itemId eq 2}">
																<ul>${LANG['website.user.charge.mode.2']}</ul>
															</c:if>
															<c:if test="${itemBill.itemId eq 3}">
<!-- 																<ul>${LANG['website.user.charge.mode.3']}</ul> -->
															</c:if>
															<c:if test="${itemBill.itemId eq 4}">
																<ul>${LANG['website.user.charge.mode.4']}</ul>
															</c:if>
														</c:forEach>
													</td>
													<td>
														<c:forEach items="${userBill.serviceItemBillings}" var="itemBill" varStatus="status">
															<ul>
																<c:choose>
														  	 		<c:when test="${itemBill.itemId eq 2}">${itemBill.numPatis}&nbsp;&nbsp;${LANG['website.admin.bill.data.title.pieces.port'] }</c:when>
														  	 		<c:when test="${itemBill.itemId eq 1}">${itemBill.itemCount}&nbsp;&nbsp;${LANG['website.admin.bill.data.title.minutes'] }</c:when>
														  	 		<c:when test="${itemBill.itemId eq 3}">${itemBill.itemCount}&nbsp;&nbsp;${LANG['website.admin.bill.data.title.minutes'] }</c:when>
														  	 		<c:when test="${itemBill.itemId eq 4}">${itemBill.itemCount}&nbsp;&nbsp;${LANG['website.admin.bill.data.title.pieces.port'] }</c:when>
							  	 								</c:choose>
															</ul>
														</c:forEach>
													</td>
													<td>
														<c:forEach var="itemBill" items="${userBill.serviceItemBillings}" varStatus="status">
															<ul><fmt:formatNumber value="${itemBill.itemCharge}" pattern="￥0.00" type="number"/></ul>
														</c:forEach>
													</td>
													<td><span class="name"><a href="javascript:viewHostBillDetail('${userBill.hostId}');">${LANG['website.admin.bill.data.title.meeting.checkout'] }</a></span></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="footer" style="border-bottom:1px #C0C0C0 solid;border-left:1px #C0C0C0 solid;border-right:1px #C0C0C0 solid;">
										<span class="total" style="margin-right: 20px;">${LANG['website.user.billing.datadetail.totalmoney']}<fmt:formatNumber value="${sumCharge}" pattern="￥0.00" type="number"/>${LANG['website.user.billing.datadetail.yuan']}</span>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							${LANG['website.admin.bill.data.no'] }
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
    </form>
</body>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
function viewHostBillDetail(hostId){
	//top.showTelDetail("${user.id}","${year}","${month}");
	var year = $(".year option:selected").val();
	var month = $(".month option:selected").val();
	window.location = "/admin/billing/viewBillDetail?hostId="+hostId+"&year="+year+"&month="+month;
}
function viewHostBills(year,month){
	window.location = "/admin/billing/listTotalBill?year="+year+"&month="+month;
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
}

function exportExcel(){
	var url = "/common/billing/userBillListIndex?isExport=true";
	url +="&year="+$("#yearSelect").val();
	url +="&month="+$("#monthSelect").val();
	window.location = url;
}
</script>
</html>

