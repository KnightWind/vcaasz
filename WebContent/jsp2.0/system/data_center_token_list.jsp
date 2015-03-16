<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<%@ include file="/jsp/common/cookie_util.jsp"%>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<title>数据中心管理</title>
</head>
<body>
<form id="query" name="query" action="" method="post"  >
	<div style="margin: 0 10px">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
		  <tr>
		  	<td colspan="6">
		  		<div class="make_new"><a href="javascript:addDataCenter();"><b>添加数据中心</b></a></div>
		  	</td>
		  </tr>
		  <tr class="table002">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
		      <tr class="table002" height="38" >
		       	<td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>数据中心名称</b></span></div></td>
		        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>ApiKey</b></span></div></td>
		        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>ApiToken</b></span></div></td>
		        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>AccountID</b></span></div></td>
		        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>操作</b></span></div></td>
		   	  </tr>
		   	  <c:forEach var="center" items="${centers}" varStatus="status">
		   	  	<tr>
		   	  		<td height="32" style="border: 1px solid #b5d7e6;"><div align="center">${center.name }</div></td>
					<td height="32" style="border: 1px solid #b5d7e6;"><div align="center">${center.apiKey }</div></td>
					<td height="32" style="border: 1px solid #b5d7e6;"><div align="center">${center.apiToken }</div></td>
					<td height="32" style="border: 1px solid #b5d7e6;"><div align="center">${center.accountId }</div></td>
					<td height="32" style="border: 1px solid #b5d7e6;">
						<c:choose>
							<c:when test="${center.isOperation eq 0}">
								<div align="center">
									<a href="javascript:listRCList('${center.id}');" >RC管理</a>
									<a href="javascript:updateDataCenter('${center.id}');" >修改</a>
									<a href="javascript:delDataCenter('${center.id}');">删除</a>
								</div>
							</c:when>
							<c:otherwise>
								<div align="center" style="color: #73798E">
									<a href="javascript:listRCList('${center.id}');" >RC管理</a>
									<a href="javascript:updateDataCenter('${center.id}');" >修改</a>
									删除
								</div>
							</c:otherwise>
						</c:choose>
					</td>
		   	  	</tr>
		   	  </c:forEach>
			</table>
		  </tr>
		</table>
	</div>
</form>
</body>
</html>
<script type="text/javascript">
	function addDataCenter(){//添加数据中心
		parent.addOrUpdateDataCenter();
	}
	
	function updateDataCenter(id){//修改数据中心
		parent.addOrUpdateDataCenter(id);
	}
	
	function delDataCenter(id){
		parent.confirmDialog("确定删除该数据中心",function(){
			var url  = "/system/dataCenter/del";
			var data = {};
			data.id = id;
			
			ajaxGet(url, data, 
					function(result){//删除完成
						if(result.status==0){//删除失败
							parent.errorDialog(result.message);
						}else{//删除成功
							parent.successDialog(result.message);
							$("#query").attr("action","/system/dataCenter/list");
							top.showURL("/system/dataCenter/list");
						}
					}, 
					function(result) {
						parent.errorDialog("保存数据出现问题！");
		    		} , null);
		});
	}
	function listRCList(centerId){//RC 的ip地址管理
		parent.listRCList(centerId);
	}
</script>