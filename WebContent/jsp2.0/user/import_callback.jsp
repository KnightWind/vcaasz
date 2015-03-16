<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
	var url = "/user/contact/showImportInfoDialog";
	var pram_rpt = "";
	var pram_unsave = "";
	var successflag = "?successflag=${statu}";
	var total = "&total=${itemnum}";
	var succnum = "&succnum=${iptitemnum}";
	var excelimp = "&excelimp=${excelimp}";
	
		<c:if test="${repeated != null}">
			<c:forEach var="rpt" items="${repeated}" varStatus="status">
				pram_rpt += "&repeated=";
				pram_rpt += encodeURIComponent('${rpt.contactName}');
			</c:forEach>
		</c:if>
		<c:if test="${dissaveable != null}">
			<c:forEach var="unsave" items="${dissaveable}" varStatus="status">
				pram_unsave += "&unsave=";
				pram_unsave += encodeURIComponent('${unsave.contactName}');
			</c:forEach>
		</c:if>
		
	url += successflag;
	url += total;
	url += succnum;
	url += excelimp;
	url += pram_rpt;
	url += pram_unsave;
	url = encodeURI(url);
	//alert(url);
	//刷新页面
	top.showImportSuccessDialog(url);
	top.refreshChildIframe("/user/contact/list","contentFrame"); 
	
	//关闭窗口
	var dialog = top.$("#importContactDialog");
	dialog.trigger("closeDialog");
</script>

