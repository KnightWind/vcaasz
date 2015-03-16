<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>月度报告</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link href="/assets/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="/assets/css/base.css" rel="stylesheet" type="text/css">
<link href="/assets/css/apps/orders.css" rel="stylesheet" type="text/css">
</head>
<body>
<form id="query" name="query" action="/user/conflog/monthlyconf" method="post">
    	<div class="box">
			<div class="head">
				<span class="title">${LANG['bizconf.jsp.conf.bizconf.monthlymeetingreport']}</span>
				<span class="desc">${LANG['website.user.conf.look.choosemonthreport']}</span>
			</div>
			<div class="body">
				<div class="filter">
					<select class="year" name="year" onchange="submitQuery();">
						<option value="${curryear}" <c:if test="${year eq curryear}">selected="selected"</c:if>>${curryear}</option>
            			<option value="${curryear-1}" <c:if test="${year eq (curryear-1)}">selected="selected"</c:if>>${curryear-1}</option>
					</select>
					<select class="month" name="month" onchange="submitQuery();">
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
			           		<c:forEach begin="1" var="index" end="${currmonth}">
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
					<span class="range">
						${LANG['website.user.conf.Statistical.interval']} &nbsp;<c:out value="${myfn:fmtDate('yyyy-MM-dd',startDate,28800000)}"/> <i>${LANG['website.user.billling.list.to']}</i> <fmt:formatDate value="${endDate}" type="date" pattern="yyyy-MM-dd"/>
					</span>
				</div>
				<div class="results">
					<div class="reporting">
						<div class="header">
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
						<table class="common-table">
							<tbody><tr>
								<th>${LANG['website.user.conf.month.conf.report']}</th>
							</tr>
							<tr>
								<td class="first-child">
									<span class="name">${LANG['website.user.conf.confnumber']}</span>
									<span class="money">${report.meetingCount } ${LANG['website.user.conf.confScreening']}</span>
								</td>
							</tr>
							<tr>
								<td class="">
									<span class="name">${LANG['bizconf.jsp.attendConfloglist.res4']}</span>
									<span class="money">${report.totalParticipants } ${LANG['system.conflist.confdetail.members']}</span>
								</td>
							</tr>
							<tr>
								<td class="last-child">
									<span class="name">${LANG['website.user.conf.totaloftime']}</span>
									<c:if test="${report.totalDuration/60<1}">
										<span class="money">${report.totalDuration%60} ${LANG['website.user.billling.teldetail.min']}</span>
									</c:if>
									<c:if test="${report.totalDuration/60>1}">
										<span class="money">${report.showDuration} ${LANG['website.user.billling.teldetail.h']} ${report.totalDuration%60} ${LANG['website.user.billling.teldetail.min']}</span>
									</c:if>
								</td>
							</tr>
						</tbody></table>
					</div>
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

function submitQuery(){
	$("#query").submit();
}

</script>
</html>

