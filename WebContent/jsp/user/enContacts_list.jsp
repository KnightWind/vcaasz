<!--${LANG['bizconf.jsp.enContacts_list.res1']} -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.enContacts_list.res1']}</title>
<link rel="stylesheet" type="text/css"
	href="${baseUrlStatic}/css/enterprise/rightbox.css?ver=${version}" />
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript">
	function delUser(id){
		if(confirm("${LANG['bizconf.jsp.enContacts_list.res2']}")){
			//query.action="/user/group/delContactsFormGroup";
			//$("input[name=id]").val(id);
			//query.submit();
			window.location = "/user/contact/deleteContact?id="+id;
		}
	}

	function toEditContact(id){
		var url = "/user/contact/editContact?id="+id;
		var features = "height=470, width=390, top=300, left=450,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=n o, status=no";
		window.open(url,"editContact",features);		
	}
	
	function doImport(){
		var url = "/user/contact/importBatchByContacts";
		query.action = url;
		query.submit();
	}
	
	function showArea(sid,hid){
		document.getElementById(sid).style.display = "";
		document.getElementById(hid).style.display = "none";
	}
</script>
</head>
<body>
<form id="query" name="query" action="/user/contact/showEnterpriseContacts" method="post">
	<div>
		<input type="button" value="${LANG['bizconf.jsp.enContacts_list.res3']}" onclick="showArea('importFromContacts','importFromFile');" /> &nbsp; <input type="button" value="${LANG['bizconf.jsp.enContacts_list.res4']}" onclick="showArea('importFromFile','importFromContacts');" />
	</div>
	<div id="importFromContacts" class="main_content">
		<div class="m_top">
		    <input name="keyword" class="ss_text" type="text" maxlenght="32" value="${keyword}" />
		    <input name="" class="ss_button" type="button" id="btn_search" />
	 	 </div>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" id="table_box">
			<tr class="table003" height="38">
				<td width="5%" height="38" bgcolor="d3eaef" class="STYLE10">
					<div align="center"><input type="checkbox" id="checkAll"/></div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span>${LANG['bizconf.jsp.enContacts_list.res5']}</span>
					</div>
				</td>
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span>${LANG['bizconf.jsp.enContacts_list.res6']}</span>
					</div>
				</td>
				
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span>${LANG['bizconf.jsp.add_calendar_notice.res7']}</span>
					</div>
				</td>
				
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span>${LANG['bizconf.jsp.add_contacts.res11']}</span>
					</div>
				</td>
				
				<td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div
						align="center">
						<span>${LANG['bizconf.jsp.add_contacts.res10']}</span>
					</div>
				</td>
			</tr>
			<c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19"  height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
			<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
				<tr class="table001" height="32">
					<td height="32">
						<div align="center"><input name="id" type="checkbox" value="${contact.id }" /></div>
					</td>
					<td height="32"><div align="center">
							<span>${contact.loginName}</span>
						</div>
					</td>
					<td height="32"><div align="center">
							<span>${contact.trueName}</span>
						</div>
					</td>
					<td height="32"><div align="center">
							<span>${contact.userEmail}</span>
						</div>
					</td>
					<td height="32"><div align="center">
							<span>${contact.phone}</span>
						</div>
					</td>
					<td height="32"><div align="center">
							<span>${contact.mobile}</span>
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
	
	<div id="importFromFile" style="display: none;">
		<input type="file" /><a href="/user/contact/downloadContactsTemplate">${LANG['site.admin.import.downloadtemp']}</a>
	</div>
	<div style="margin-top: 30px;">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="right">
						<input type="button" onclick="doImport();" style="background: url('/static/images/button01.jpg') no-repeat scroll 0 0 transparent;
									    cursor: pointer;
									    height: 24px;
									    line-height: 24px;
									    text-align: center;
									    width: 72px;" value="${LANG['bizconf.jsp.enContacts_list.res7']}" />&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="javascript:window.close();" style="background: url('/static/images/button01.jpg') no-repeat scroll 0 0 transparent;
					    cursor: pointer;
					    height: 24px;
					    line-height: 24px;
					    text-align: center;
					    width: 72px;" value="${LANG['bizconf.jsp.enContacts_list.res8']}"  />
				</td>
			</tr>	
		</table>
	</div>
	</form>
</body>
</html>

