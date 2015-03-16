<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.CopyOfadminIndex.res10']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css?ver=${version}" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css?ver=${version}" />
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript">
	function delUser(id){
		parent.confirmDialog("${LANG['system.siteadmin.delete']}", function() {
			//window.location ="/admin/entUser/delUsers?id="+id;
			query.action="/admin/entUser/delUsers";
			$("input[name=id]").val(id);
			query.submit();
		});
	}
	
	function toEditUser(id){
		parent.createOrUpdateAdminUser(id);
	}
</script>
</head>
<body>
<form id="query" name="query" action="/admin/entUser/list" method="post">
	<div class="main_content">
			<input type="hidden"  name="keyword" value=""/>
			<input type="hidden"  name="id" value=""/>
			
		<div class="m_top1">
			<input name="button_01" class="button_01" onclick="toEditUser();" type="button" value="${LANG['system.sysUser.list.addUser']}"
				onmouseover="this.className='Btn_Hover_01'"
				onmouseout="this.className='Btn_01'" />
		</div>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" id="table_box">
			<tr class="table003" height="38">
				<td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span><b>${LANG['system.login.name']}</b></span>
					</div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span><b>${LANG['system.sysUser.list.userName']}</b></span>
					</div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span><b>${LANG['system.sysUser.list.enName']}</b></span>
					</div>
				</td>
				<td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span><b>${LANG['system.sysUser.list.email']}</b></span>
					</div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span><b>${LANG['system.sysUser.list.telephone']}</b></span>
					</div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"
					style=" border-right:none;"><div align="center">
						<span><b>${LANG['system.sysUser.list.operate']}</b></span>
					</div>
				</td>
			</tr>
			<c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
			<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
				<tr class="table001" height="32">
					<td height="32"><div align="center">
							<span>${user.loginName}</span>
						</div>
					</td>
					<td height="32"><div align="center">
							<span>${user.trueName}</span>
						</div>
					</td>
					<td height="32"><div align="center">${user.enName }</div>
					</td>
					<td height="32"><div align="center">${user.userEmail }</div>
					</td>
					<td height="32"><div align="center">${user.mobile }</div>
					</td>
					<td height="32"><div align="center">
							<a href="javascript:toEditUser('${user.id }')" class="edit">${LANG['system.change']}</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
								href="Javascript:delUser('${user.id }')" class="edit">${LANG['system.delete']}</a>
						</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td class="table_bottom" height="38" colspan="6"> 
					 <jsp:include page="/jsp/common/page_info.jsp" />
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>

