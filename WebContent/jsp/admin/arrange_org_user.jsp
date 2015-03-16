<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.arrange_org_user.res1']}</title>

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<c:if test="${!empty errorMessage}">
	<script type="text/javascript">
		$(function() {
			parent.errorDialog("${errorMessage}");
		});
	</script>
</c:if>
<c:if test="${!empty infoMessage}">
	<script type="text/javascript">
	$(function() {
		parent.successDialog("${infoMessage}");
	});	
	</script>
</c:if>
<script type="text/javascript">
	
	//${LANG['bizconf.jsp.admin.arrange_org_user.res2']}
	function submitBatch(){
		var data = {};
		data.orgId = $("#orgId").val();
		data.id = [];
		$("input[name=id]:checked").each(function(){
			var id = $(this).val();
			data.id.push(id);
		});
		if(data.id.length==0){	 
			parent.errorDialog("${LANG['bizconf.jsp.admin.arrange_org_user.res3']}");
			return;
		} else {
			data.id = data.id.join(",");
			app.assignUser(data, function(result) {
				if(result) {
					if(result.status==1){
						closeDialog(result);		
					} else {
						parent.errorDialog(result.message);
					}
				}
			}, {message:"${LANG['bizconf.jsp.admin.arrange_org_user.res4']}...", ui:parent});
		}
	}
	
	function sortQuery(sortField,sortord){
		if(!sortord){
			if($("#sortRule").val()=='0'){
				sortord = "1";
			}else{
				sortord = "0";
			}
		}
		$("#sortField").val(sortField);
		$("#sortRule").val(sortord);
		$("#query").attr("action","/admin/entUser/listAll");
		$("#query").submit();
	}
	
	$(document).ready(function(){
		//${LANG['bizconf.jsp.admin.arrange_org_user.res5']}and${LANG['bizconf.jsp.admin.arrange_org_user.res6']}
		$("#checkAll").click(function(){
			if($(this).attr("checked")){
				$("input[name=id]").attr("checked",true);				
			}else{
				$("input[name=id]").attr("checked",false);
			}
		});
		$("input[name=id]").click(function(){
			if($("input[name=id]").length == $("input[name=id]:checked").length){
				$("#checkAll").attr("checked",true);
			}else{
				$("#checkAll").attr("checked",false);
			}
		});
	});
</script>
</head>
<body onload="loaded()">
<form id="query" name="query" action="/admin/org/getOrgUserList/${orgId}" method="post">
		<div style="margin:10px auto;width: 710px;height: 405px;overflow-y: auto;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list" style="border:#A3C5DE 1px solid;border-top:none;border-bottom:none;color:#73798E;">

              <tr class="" height="38" >
                <td width="5%" height="38" bgcolor="d3eaef" class="STYLE10">
                  <div align="center"><span><input type="checkbox" id="checkAll"/></span></div>
                </td>
                <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.login.name']}</span></div></td>
	            <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.sysUser.list.userName']}</span></div></td>
	            <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['site.admin.edituser.userrole']}</span></div></td>
	            <td width="20%"  height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.sysUser.list.email']}</span></div></td>
	            <td width="16%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.sysUser.list.telephone']}</span></div></td>
	            <td width="7%" height="38" bgcolor="d3eaef" class="STYLE10">
	            <div align="center">
		            <span>${LANG['site.admin.userlist.userstatu']}</span>
	            </div>
	            </td>
              </tr>

              <c:if test="${fn:length(pageModel.datas)<=0 }">
		        <tr>
		          <td height="32" class="STYLE19" colspan="4"  align="center">
		       		${LANG['website.common.msg.list.nodata']}
		          </td>
		        </tr>
		      </c:if>
              
              <c:if test="${fn:length(pageModel.datas)>0 }">
              	 <input id="orgId" name="orgId" type="hidden" value="${orgId}"
			     <c:forEach var= "user" items = "${pageModel.datas}"  varStatus="status">
			      <tr class="table001" height="32" >
			        <td height="32">
	                  <div align="center"><span><input name="id" type="checkbox" value="${user.id }" /></span></div>
	                </td>
					<td height="32"><div align="center"><span>${user.loginName }</span></div></td>
		            <td height="32"><div align="center"><span>${user.trueName }</span></div></td>
		            <td height="32">
		            <div align="center">
			            <c:if test="${user.userRole eq '1'}">
				            ${LANG['site.admin.edituser.role1']}
			            </c:if>
			            <c:if test="${user.userRole eq '2'}">
				            ${LANG['site.admin.edituser.role2']}
			            </c:if>
		            </div></td>
		            <td height="32"><div align="center">${user.userEmail }</div></td>
		            <td height="32"><div align="center">${user.mobile }</div></td>
		            <td height="32">
			            <div align="center">
			            	<c:if test="${user.userStatus eq '0'}">${LANG['system.site.list.Status.lock']}</c:if>
			            	<c:if test="${user.userStatus eq '1'}">${LANG['site.admin.userlist.active']}</c:if>
			            </div>
		            </td>
			        
			      </tr>
			     </c:forEach>
		      </c:if>
				<tr>
		          <td class="table_bottom" height="38" colspan="7">
					<jsp:include page="/jsp/common/page_info.jsp" />
		          </td>
		        </tr>      
            </table>
		</div>
      <input name="emile_btn01" class="emile_btn01_PP" type="button"  value="${LANG['bizconf.jsp.admin.arrange_org_user.res10']}" onclick="submitBatch()" style=" margin-right:30px;margin-left: 20px;"/>
      <input name="emile_btn01" class="emile_btn01_PP" type="button"  value="${LANG['bizconf.jsp.admin.arrange_org_user.res11']}" onclick="closeDialog()"/> 
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#assignUserDiv");
	frame.trigger("loaded");
}

function closeDialog(result) {
	var dialog = parent.$("#assignUserDiv");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
