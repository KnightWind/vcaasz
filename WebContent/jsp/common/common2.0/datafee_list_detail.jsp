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
   <div class="common-dialog order-dialog"  >
   <div class="wrapper">
	    <header>
		   <h3 class="title">
		   <i class="icon icon-order"></i>账单详情
		   </h3>
		   <a title="关闭" class="close"></a>
		</header>
   <section>	
   	<p class="export">
		<a class="input-gray" onclick="exportExcel();">导出</a>
	</p>
	<div class="order-item">
		<div class="details">
			<table class="common-table">
				<tbody>
					<tr>
						<th class="name">用户标识</th>
						<th class="start">最大方数</th>
						<th class="cost">费用（元）</th>
					</tr>
					<c:forEach var="bill" items="${billings}" varStatus="status">
						<tr>
							<td class="name">
								<c:choose>
				                  	<c:when test="${bill.userId != ''}">
				                  		${bill.userId}
				                  	</c:when>
				                 	 <c:otherwise>
										${bill.siteSign}	                 	 
				                 	 </c:otherwise>
				                 </c:choose>
							</td>
							<td class="start">
								<c:choose>
			                  		<c:when test="${bill.userId != '' }">
			                  			${licMap[bill.userId]}
			                  		</c:when>
			                  		<c:otherwise>
			                  			${sitelic}
			                  		</c:otherwise>
			                  	</c:choose>
	                  		</td>
							<td class="cost">
								<fmt:formatNumber value="${bill.dataFee}" pattern="0.00" type="number"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
<footer><input type="button" class="button input-gray" onclick="closeDialog();" value="确定"></footer>
</div>
</div>
</body>
<script type="text/javascript">

function exportExcel(){
	var url = "/common/billing/sysShowDataDetail?isExport=true";
	url +="&year="+$("input[name=year]").val();
	url +="&month="+$("input[name=month]").val();
	url +="&userId="+$("input[name=userId]").val();
	url +="&id="+$("input[name=id]").val();
	window.location = url;
}

function closeDialog(result) {
	var dialog = parent.$("#billingView");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
</html>
