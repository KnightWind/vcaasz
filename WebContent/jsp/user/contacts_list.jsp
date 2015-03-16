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
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<title>index</title>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>	
<script type="text/javascript">
	function delUser(id){
		parent.parent.confirmDialog("${LANG['bizconf.jsp.bill_detaillist.res1']}", function() {
			window.location = "/user/contact/deleteContact?id="+id;
		});
	}
	
	//${LANG['bizconf.jsp.bill_detaillist.res2']}
	function deleteContactBatch(){
		parent.parent.confirmDialog("${LANG['bizconf.jsp.bill_detaillist.res1']}", function() {
			$("#query").attr("action","/user/contact/deleteContactBatch");
			$("#query").submit();
		});
	}

	function toEditContact(id){
		parent.parent.addContact(id);
	}
	
	function toExportContact(id){
		$("#query").attr("action","/user/contact/exportContacts");
		$("#query").attr("target","downFrame");
		$("#query").submit();
		$("#query").attr("action","/user/contact/list");
		$("#query").removeAttr("target");
		
	}
	
	function doImport(){
		parent.parent.bulkImportContact();
	}
	
	$(document).ready(function(){
		if (!$.browser.msie || $.browser.version>7) {
			$(".meeting_search").watermark("${LANG['bizconf.jsp.bill_detaillist.res3']}");
		}
	});
	
	function submitForm(){
		$('input').trigger('submitForm');
		query.submit();
	}
	
	$(document).ready(function(){
		parent.refreshIHeight();
		//${LANG['bizconf.jsp.bill_detaillist.res4']}and${LANG['bizconf.jsp.bill_detaillist.res5']}
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
		
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").attr("action","/user/contact/list");
				$("#query").submit();
			}
		});
	});
</script>
</head>
<body>

<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>

  <form id="query" name="query" action="/user/contact/list" method="post">
  	<input type="hidden"  name="group_id" value="${group_id}"/>
  <div class="meeting_main">
      <table  id="t_box" align="center" border="0" cellpadding="0" cellspacing="0" width="100%"><tbody><tr>
        <td height="40" colspan="6" bgcolor="#EAF4FC" class="tr_top">
        	<input class="meeting_ss meeting_search" name="keyword"  value="${keyword}" type="text">
          <input class="meeting_but" type="button" onclick="submitForm();">
          <div class="top_right">
          	<span class="button_common" style="margin-right: 5px;"><a href="javascript:;" onclick="toEditContact('');"><img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/add_16.png">${LANG['bizconf.jsp.add_contacts.res1']}</a></span>
          	<span class="button_common" style="margin-right: 5px;"><a href="javascript:;" onclick="doImport();"><img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/add_16.png">${LANG['bizconf.jsp.contacts_import_main.res3']}</a></span>
          	<span class="button_common" style="margin-right: 5px;"><a href="javascript:toExportContact('');"><img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/export.png">${LANG['bizconf.jsp.user.export.all.contacts']}</a></span>
          	<span class="button_common"><a href="javascript:;" onclick="deleteContactBatch();"><img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/email02.png">${LANG['bizconf.jsp.bill_detaillist.res2']}</a></span>
          </div>
          </td>
      </tr>
      <tr align="center" height="35" class="tr_center" bgcolor="#000066">
        <td width="5%" class="tr_center"><input type="checkbox" id="checkAll"/></td>
        <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res7']}</td>
        <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td>
        <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res11']}</td>
        <td width="15%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attended_conf_list.res9']}</td>
      </tr>
     <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19"  height="32" colspan="6" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
	<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
	      <tr align="center" bgcolor="#FFFFFF" height="32">
	      	<td class="tr_main"  style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${contact.id }" /></td>
	        <td class="tr_main">${contact.contactName}&nbsp;</td>
	        <td class="tr_main">${contact.contactEmail}&nbsp;</td>
	        <td class="tr_main">${contact.contactPhone}&nbsp;</td>
	        <td class="tr_main">${contact.contactMobile}&nbsp;</td>
	        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
	        <table>
	            <tbody><tr>
	              <td class="k_a"><a href="javascript:toEditContact('${contact.id }')"><img style=" margin-right:5px;" src="/static/images/email01.png" align="absmiddle" height="16" width="16">${LANG['bizconf.jsp.conf_list_index.res57']}</a></td>
	              <td class="k_a"><a href="javascript:delUser('${contact.id }')"><img style=" margin-right:5px;" src="/static/images/email02.png" align="absmiddle" height="17" width="12">${LANG['bizconf.jsp.attended_conf_list.res12']}</a></td>
	            </tr>
	          </tbody>
	          </table>
	         </td>
	      </tr>
     </c:forEach>
     <tr>
        <td class="table_bottom" height="38" colspan="6"> 
        	 <jsp:include page="/jsp/common/page_info.jsp" />
        </td>
      </tr>
    </tbody>
    </table>
  </div>
  </form>
</body>
</html>

