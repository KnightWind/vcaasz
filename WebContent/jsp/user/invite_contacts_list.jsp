<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.invite_contacts_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<style type="text/css">
*html table {
	border-collapse: collapse;
}

*+html table {
	border-collapse: collapse;
}
</style>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/common.js?ver=${version}"></script>	
<script type="text/javascript">

	function getContactsData(){
		var datas = new Array();
		$("input[name=id]:checked").each(function(){
			var item = new Object();
			var id = $(this).val();
			var name= $("td[name=contactName"+id+"]").html();
			var email= $("td[name=contactEmail"+id+"]").html();
			var phone= $("td[name=contactPhone"+id+"]").html();
			var mobile= $("td[name=contactMobile"+id+"]").html();
			var userId = $("input[name=userId_"+id+"]").val();
			item.name = name;
			item.email = email;
			item.phone = phone || mobile;
			item.userId = userId;
			datas.push(item);
		});
		return datas;
	}
	
	$(document).ready(function(){
		if (!$.browser.msie || $.browser.version>7) {
			$(".contact_search").watermark('${LANG['bizconf.jsp.bill_detaillist.res3']}');
		}
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
// 		remindInfo($("input[name=keyword]").get(0),"${LANG['bizconf.jsp.invite_contacts_list.res2']}");
	});
	
	function submitForm(){
		$('input').trigger('submitForm');
		query.submit();
	}
	
	
	function loaded() {
		var dialog = parent.$("#importContact");
		dialog.trigger("loaded");
	}
	function closeDialog() {
		var dialog = parent.$("#importContact");
		dialog.trigger("closeDialog");
	}
</script>
</head>
<body>
<form id="query" name="query" action="/user/contact/invitelist" method="post">
	 <div class="First_Steps_main_invite" style=" background:#FFF;">
          <table width="730" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
            <tr>
              <td height="40" colspan="6" bgcolor="#EAF4FC" class="tr_top"><input name="keyword" value="${keyword}" type="text" class="meeting_ss contact_search" />
                <input class="meeting_but"  type="button" onclick="javscript:submitForm();"/></td>
            </tr>
            <tr align="center" height="35" class="tr_center" bgcolor="#000066">
              <td width="5%" class="tr_center"><input name="" type="checkbox" id="checkAll" value="" /></td>
              <td width="15%" class="tr_center">${LANG['bizconf.jsp.enContacts_list.res6']}</td>
              <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res8']}</td>
              <td width="25%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td>
              <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td>
              <td width="20%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.add_contacts.res11']}</td>
            </tr>
             <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
			<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
	            <tr name="contactsItem" align="center" bgcolor="#FFFFFF" height="30">
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${contact.id}" /><input name="userId_${contact.id}" type="hidden" value="${contact.contactId}" /></td>
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid" name="contactName${contact.id}">${contact.contactName}</td>
	              <td class="tr_main" >${contact.contactNameEn}</td>
	              <td class="tr_main" name="contactEmail${contact.id}">${contact.contactEmail}</td>
	              <td class="tr_main" name="contactPhone${contact.id}">${contact.contactPhone}</td>
	              <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center" name="contactMobile${contact.id}">${contact.contactMobile}</td>
	            </tr>
            </c:forEach>
            <tr>
            	<td height="35" colspan="6" class="tr_bottom table_bottom"> 
					 <jsp:include page="/jsp/common/page_info.jsp" />
				</td>
            </tr>
          </table>
        </div>
	</form>
</body>
</html>

