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
<link href="/assets/css/apps/orders.css?var=19" rel="stylesheet" type="text/css">
</head>
<body>
<form id="query" name="query" action="/admin/billing/listTotalBill" method="post">
  	<div class="box">
	<div class="head">
		<span class="title">${LANG['website.user.billling.list.titleinfo1']}</span>
		<span class="desc">${LANG['website.user.billling.list.titleinfo2']}</span>
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
<%--		<a style="margin-left: 150px;" onclick="exportExcel();" id="searchbtn" class="input-gray">${LANG['website.user.billing.datadetail.export']}</a>--%>
		</div>
		<div class="results">
			<c:choose>
				<c:when test="${not empty itemsBill}">
					<div class="reporting" style="width: 100%;">
							<table class="common-table" style="width: 60%;border:1px #C0C0C0 solid;">
							<tbody>
								<tr>
									<th>${LANG['website.admin.bill.data.title.service.item'] }</th>
								  	<th>${LANG['website.admin.bill.data.title.count'] }</th>
									<th>${LANG['website.admin.bill.data.title.money'] }</th>
								</tr>
								<c:forEach var="item" items="${itemsBill}" varStatus="statu">
									<tr style="border:1px #C0C0C0 solid;">
									  	 <c:choose>
									  	 	<c:when test="${item.itemId eq 2}">
									  	 		<td><span class="name"><a href="javascript:viewBillDetail('${year}','${month}','${item.numPatis}');">${LANG['website.user.charge.mode.2']}-${item.numPatis}</a></span> </td>
									  	 	</c:when>
									  	 	<c:when test="${item.itemId eq 1}">
									  	 		<td><span class="name">${LANG['website.user.charge.mode.1']}</span></td>
									  	 	</c:when>
									  	 	<c:when test="${item.itemId eq 3}">
												<!--<td><span class="name">${LANG['website.user.charge.mode.3']}</span></td> -->
									  	 	</c:when>
									  	 	<c:when test="${item.itemId eq 4}">
									  	 		<td><span class="name">${LANG['website.user.charge.mode.4']}</span></td>
									  	 	</c:when>
									  	 </c:choose>
									  	 <td>
									  	 	<span class="name">
									  	 	${item.itemCount }
									  	 	<c:choose>
									  	 		<c:when test="${item.itemId eq 2}">${LANG['website.admin.bill.data.title.pieces'] }</c:when>
									  	 		<c:when test="${item.itemId eq 1}">${LANG['website.admin.bill.data.title.minutes'] }</c:when>
												<c:when test="${item.itemId eq 3}"><!--${LANG['website.admin.bill.data.title.minutes'] } --></c:when>
									  	 		<c:when test="${item.itemId eq 4}">${LANG['website.admin.bill.data.title.pieces.port'] }</c:when>
									  	 	</c:choose>
									  	 	</span>
									  	 </td>
									  	 <td><span class="name"><fmt:formatNumber value="${item.itemCharge}" pattern="￥0.00" type="number"/></span></td>
									</tr>
								</c:forEach>
								<tr style="border:1px #C0C0C0 solid;">
									<td colspan="2" align="right">
										<span class="name"><a href="javascript:viewBillDetail('${year}','${month}','');">${LANG['website.admin.bill.data.title.detail.info'] }</a></span>
									</td>
									<td>
										<span class="total" >${LANG['website.user.billing.datadetail.totalmoney']}<fmt:formatNumber value="${sumCharge}" pattern="￥0.00" type="number"/>${LANG['website.user.billing.datadetail.yuan']}</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
<!-- 				<div class="footer" style="border:1px #C0C0C0 solid;line-height: 30px;text-align: right;width: 60%;"></div> -->
				</c:when>
				<c:otherwise>
					${LANG['website.admin.bill.data.no'] }
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.common.js"></script>
<script type="text/javascript">
function viewBillDetail(year,month,numpatis){
	
	//左边菜单选中 class="active"
	//$("#billList",parent.document).parent().removeClass("active");
	//$("#billDetail",parent.document).parent().addClass("active");
	var url = "/admin/billing/listDetailBills?year="+year+"&month="+month;
	if(numpatis != null && numpatis != ''){
		url += "&portnums="+numpatis;
	}
	window.location = url;
}
function viewDetail(id){
		parent.parent.showTelDetail(id,"${year}","${month}");
}

function submitQuery(Year){
	//$("#ipt_isExport").val("false");
	//alert($("form select[name='month'] option:selected").val());
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
</body>
</html>

