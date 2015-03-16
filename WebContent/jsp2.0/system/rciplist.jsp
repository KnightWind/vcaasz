<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/Popup.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<title>${LANG['bizconf.jsp.system.hostlist.res1']}</title>
</head>
<body onload="loaded()">
<div class="n_title"><a href="javascript:void(0);" onclick="popUpRc('${centerId}');">添加IP(RC)</a></div>
<table border="0" cellpadding="0" cellspacing="0" class="host_top">
	<tr height="30" class="host01">
    	<td width="100" align="center">序列</td>
        <td width="150" align="center">IP(RC)地址</td>
        <td width="170" align="center">${LANG['bizconf.jsp.admin.site_org_list.res6']}</td>  
    </tr>
</table>
 <div class="h_main" style="overflow-y: auto;height: 320px;margin-top: 0px;">
 <form id="query" action="/system/dataCenter/listIp" method="post">
 	<input type="hidden" value="${centerId }" name="centerId" />
	<table border="0" cellpadding="0" cellspacing="0" class="host_center" style="overflow:auto;">
		 <c:if test="${fn:length(pageModel.datas)<=0 }">
	         <tr height="36" class="host02">
	           <td colspan="4" width="540" align="center">
	        	${LANG['website.common.msg.list.nodata']}
	           </td>
	         </tr>
	     </c:if>
	    <c:forEach var="rcIp" items="${pageModel.datas}" varStatus="status">
		    <tr height="30" class="host02">
		    	<td width="100" align="center" >${status.index + 1 }</td>
		        <td width="150" align="center">${rcIp.ipAddress}</td>
		        <td width="170" align="center">
			        <a href="javascript:popUpRc('${centerId }','${rcIp.id}');">${LANG['bizconf.jsp.system.email_template_list.res7']}</a> 
			        <a href="javascript:del(${rcIp.id});" onclick="">${LANG['bizconf.jsp.system.email_template_list.res8']}</a>
		        </td> 
		    </tr>
	    </c:forEach>
	    <tr height="36" class="host02">
           <td colspan="4" width="540" align="center">
        		 <jsp:include page="/jsp/common/page_info.jsp" />
           </td>
	    </tr>
	</table>  
 </form>
</div>
<a href="javascript:closeDialog();" class="Public_button" style="margin-left:30px;" onclick="">${LANG['bizconf.jsp.admin.createOrg.res4']}</a>
</div>
</body>
</html>
<script type="text/javascript">
function refreshPage(){
	window.location.reload(true);
}

function popUpRc(centerId,id) {
	parent.addOrUpdateRCIpAddress(centerId,id);
}

function del(id){
	parent.confirmDialog("${LANG['确定删除该IP地址']}",function(){
		var url = "/system/dataCenter/delRcIp?id="+id;
		
		ajaxGet(url, null, 
				function(result){//删除完成
					if(result.status==0){//删除失败
						parent.errorDialog(result.message);
					}else{//删除成功
						parent.successDialog(result.message);
						if(parent.$("#listRCIpDiv").find("#dialogFrame")[0]){
							parent.$("#listRCIpDiv").find("#dialogFrame")[0].src = "/system/dataCenter/listIp?centerId="+${centerId};
						}
					}
				}, 
				function(result) {
					parent.errorDialog("保存数据出现问题！");
	    		} , null);
		
	});
}

function loaded() {
	var frame = parent.$("#listRCIpDiv");
	frame.trigger("loaded");
}
function closeDialog() {
	var frame = parent.$("#listRCIpDiv");
	frame.trigger("closeDialog");
}
</script>
