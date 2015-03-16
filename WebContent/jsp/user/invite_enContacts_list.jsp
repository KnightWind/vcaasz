<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.enContacts_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></SCRIPT>
<script type="text/javascript">

	function getContactsData(){
		return $("#orgListFrame")[0].contentWindow.getContactsData();
	}
	//${LANG['bizconf.jsp.invite_enContacts_list.res1']}
	function importContacts(){
		$("#orgListFrame")[0].contentWindow.importContacts();
// 		if($("input[name=id]:checked").length>0){
// 			$("#query").attr("action","/user/contact/importBatchByContacts");
// 			$("#query").submit();
// 		}else{
// 			alert("${LANG['bizconf.jsp.contacts_import_main.res10']}");
// 		}
	}
	function getContactsIds() {
		return $("#orgListFrame")[0].contentWindow.getContactsIds();
	}
	$(document).ready(function(){
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
		if (!$.browser.msie || $.browser.version>7) {
			$(".contact_search").watermark("${LANG['bizconf.jsp.group_contacts_list.res3']}");
		}
		
		$("#orgLevel1").change(function() {
			var level4 = $("#orgLevel4");
			var level3 = $("#orgLevel3");
			var level2 = $("#orgLevel2");
			level4.hide().find(".org-option").remove();
			level3.hide().find(".org-option").remove();
			level2.find(".org-option").remove();
			var id = $(this).val();
			if(id==0){
				id = $(this).val();
				level2.hide();
			} else {
				initLevelOrg(id, level2);
			}
			$("#orgId").val(id);
			refreshList(id);
        });
		
		$("#orgLevel2").change(function() {
			var level4 = $("#orgLevel4");
			var level3 = $("#orgLevel3");
			var level1 = $("#orgLevel1");
			level4.hide().find(".org-option").remove();
			level3.find(".org-option").remove();
			var id = $(this).val();
			if(id==0){
				id = level1.val();
				level3.hide();
			} else {
				initLevelOrg(id, level3);
			}
			$("#orgId").val(id);
			refreshList(id);
        });
		
		$("#orgLevel3").change(function() {
			var level4 = $("#orgLevel4");
			var level2 = $("#orgLevel2");
			level4.find(".org-option").remove();
			var id = $(this).val();
			if(id==0){
				id = level2.val();
				level4.hide();
			} else {
				initLevelOrg(id, level4);
			}
			$("#orgId").val(id);
			refreshList(id);
        });
		
		$("#orgLevel4").change(function() {
			var level3 = $("#orgLevel3");
			var id = $(this).val();
			if(id==0){
				id = level3.val();
			}
			$("#orgId").val(id);
			refreshList(id);
        });
		$(".meeting_ss").keypress(function(e){
			if(e.keyCode == 13){
				refreshList();
				return false;
			}
		});	
	});
	
	function initLevelOrg(id, elem){
		app.getLevelOrg(id, function(result) {
			if(result && result.status==1){
				var orgUserList = result.orgUserList;
				if(orgUserList && orgUserList.length>0){
					for ( var i = 0; i < orgUserList.length; i++) {
						var org = orgUserList[i];
						if(org){
							$("<option class='org-option' value="+org.id+">"+org.orgName+"</option>").appendTo(elem);	
						}	
					}
					elem.show();	
				}
			}
		});
	}
	
	function refreshIHeight() {
		var height = $("#orgListFrame").contents().find("body").height() + 50;
		$("#orgListFrame").height(height);  
	}
	
	function refreshList(id) {
		var url = "/user/contact/showEnterpriseOrgContacts?showAll=${showAll}";
		var orgId = $("#orgId").val();
		if(id){
			orgId = id;
		}
		if(orgId){
			url += "&orgId="+orgId;
		}
		var keyword = $(".contact_search").val();
		if(keyword && keyword!="${LANG['bizconf.jsp.group_contacts_list.res3']}"){
			url += "&keyword="+encodeURIComponent(encodeURIComponent(keyword));
		}
		$("#orgListFrame").attr("src", url);
		
		//$("#query").attr("target","orgListFrame");
		//$("#query").submit();
	}
</script>
</head>
<body>
<form id="query" name="query" action="/user/contact/showEnterpriseContacts" method="post" onsubmit="javascript:$('input').trigger('submitForm');">
	  <input type="hidden"  name="showAll" value="${showAll}"/>
	  <input type="hidden" id="orgId"  name="orgId" value="${orgId}"/>
	  <!--${LANG['bizconf.jsp.invite_enContacts_list.res2']}-->
        <div class="First_Steps_main_invite" style=" background:#FFF;">
          <table width="730" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
            <tr>
              <td height="40" colspan="6" bgcolor="#EAF4FC" class="tr_top">
              	<input name="keyword" type="text" value="${keyword}" class="meeting_ss contact_search" />
                <input class="meeting_but" type="button" onclick="javscript:refreshList();" />
                <c:if test="${!empty orgList }">
                <select id="orgLevel1" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 10px;width:120px;">
                	<option value="0">${LANG['bizconf.jsp.invite_enContacts_list.res3']}</option>
                	<c:forEach var="org" items="${orgList}" >
              			<c:if test="${org.orgLevel==1}">
              				<option level="${org.orgLevel }" value="${org.id}">${org.orgName}</option>
              			</c:if>
		            </c:forEach>
              	</select>
                </c:if>
              	<select id="orgLevel2" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
              		<option value="0">${LANG['bizconf.jsp.invite_enContacts_list.res4']}</option>
              	</select>
              	<select id="orgLevel3" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
              		<option value="0">${LANG['bizconf.jsp.invite_enContacts_list.res4']}</option>
              	</select>
              	<select id="orgLevel4" name="" style="padding: 3px; border: 1px solid #ABADB3;margin-left: 5px;display: none;width:80px;">
              		<option value="0">${LANG['bizconf.jsp.invite_enContacts_list.res4']}</option>
              	</select>
              </td>
            </tr>
            <tr>
            	<td colspan="6">
            		<iframe frameborder="0" width="100%" height="385px;" id="orgListFrame" name="orgListFrame" scrolling="no" src="/user/contact/showEnterpriseOrgContacts?showAll=${showAll}"></iframe>
            	</td>
            </tr>
<!--             <tr align="center" height="35" class="tr_center" bgcolor="#000066"> -->
<!--               <td width="5%" class="tr_center"><input name="" id="checkAll" type="checkbox" value="" /></td> -->
<!--               <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res7']}</td> -->
<!--               <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res8']}</td> -->
<!--               <td width="25%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td> -->
<!--               <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td> -->
<!--               <td width="20%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.add_contacts.res11']}</td> -->
<!--             </tr> -->
<%--             <c:if test="${fn:length(pageModel.datas)<=0}"> --%>
<!-- 				<tr class="table001" height="32"  > -->
<!-- 			            <td class="STYLE19" height="32" colspan="11" align="center"> -->
<%-- 			            	<c:if test="${keyword !=null}"> --%>
<!-- 			            		${LANG['bizconf.jsp.invite_enContacts_list.res5']} -->
<%-- 			            	</c:if> --%>
<%-- 			            	<c:if test="${empty keyword}"> --%>
<!-- 			            		${LANG['bizconf.jsp.invite_enContacts_list.res6']} -->
<%-- 			            	</c:if> --%>
<!-- 			            </td> -->
<!-- 			     </tr> -->
<%-- 			</c:if> --%>
<%-- 			<c:forEach var="contact" items="${pageModel.datas}" varStatus="status"> --%>
<!-- 	            <tr name="contactsItem" align="center" bgcolor="#FFFFFF" height="30"> -->
<%-- 	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${contact.id}" /></td> --%>
<%-- 	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid" name="contactName${contact.id}">${contact.trueName}</td> --%>
<%-- 	              <td class="tr_main" >${contact.enName}</td> --%>
<%-- 	              <td class="tr_main" name="contactEmail${contact.id}">${contact.userEmail}</td> --%>
<%-- 	              <td class="tr_main" name="contactPhone${contact.id}">${contact.phone}</td> --%>
<%-- 	              <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center" name="contactMobile${contact.id}">${contact.mobile}</td> --%>
<!-- 	            </tr> -->
<%--             </c:forEach> --%>
<!-- 			<tr> -->
<!-- 				<td height="35" colspan="6" class="tr_bottom table_bottom"> -->
<%-- 					 <jsp:include page="/jsp/common/page_info.jsp" /> --%>
<!-- 				</td> -->
<!-- 			</tr>             -->
          </table>
        </div>
	</form>
</body>
</html>

