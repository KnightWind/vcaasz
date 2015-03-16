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
		var datas = new Array();
		$("input[name=id]:checked").each(function(){
			var item = new Object();
			var id = $(this).val();
			var name= $("td[name=contactName"+id+"]").html();
			var email= $("td[name=contactEmail"+id+"]").html();
			var phone= $("td[name=contactPhone"+id+"]").html();
			var mobile= $("td[name=contactMobile"+id+"]").html();
			item.name = name;
			item.email = email;
			item.phone = phone||mobile;
			item.userId = id;
			datas.push(item);
		});
		return datas;
	}
	//${LANG['bizconf.jsp.invite_enContacts_list.res1']}
	function importContacts(){
		if($("input[name=id]:checked").length>0){
			$("#query").attr("action","/user/contact/importBatchByContacts");
			$("#query").submit();
		}else{
			alert("${LANG['bizconf.jsp.contacts_import_main.res10']}");
		}
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
			$(".contact_search").watermark('${LANG['bizconf.jsp.group_contacts_list.res3']}');
		}
		
		$("#orgLevel2").change(function() {
			$("#orgLevel4").empty().hide();
			$("#orgLevel3").empty();
			var id = $(this).val();
			var elem = $("#orgLevel3");
			initLevelOrg(id, elem); 
        });
		
		$("#orgLevel3").change(function() {
			$("#orgLevel4").empty();
			var id = $(this).val();
			var elem = $("#orgLevel4");
			initLevelOrg(id, elem); 
        });
		
		$("#orgLevel4").change(function() {
			var id = $(this).val();
        });		
		var secondLevel = $("#orgLevel2");
		initLevelOrg(1, secondLevel);
	});
	
	function initLevelOrg(id, elem){
		app.getLevelOrg(id, function(result) {
			if(result && result.status==1){
				var orgUserList = result.orgUserList;
				for ( var i = 0; i < orgUserList.length; i++) {
					var org = orgUserList[i];
					$("<option value="+org.id+">"+org.orgName+"</option>").appendTo(elem);	
				}
				elem.show();
			}
		});
	}
	
	function refreshList(id) {
		$("#query").submit();
	}
	
	function submitForm(){
		$('input').trigger('submitForm');
		query.submit();
	}
</script>
</head>
<body style="height: 550px;">
<form id="query" name="query" action="/user/contact/showEnterpriseContacts" method="post" onsubmit="javascript:$('input').trigger('submitForm');">
	  <input type="hidden"  name="showAll" value="${showAll}"/>
	  <!--${LANG['bizconf.jsp.invite_enContacts_list.res2']}-->
        <div class="First_Steps_main_invite" style=" background:#FFF;">
          <table width="730" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
            <tr>
              <td height="40" colspan="6" bgcolor="#EAF4FC" class="tr_top">
              	<select id="orgLevel1" name="" style="padding: 3px; border: 1px solid #666;margin-left: 10px;">
              		<option value="0">${LANG['bizconf.jsp.org_user_list.res1']}</option>
              	</select>
              	<select id="orgLevel2" name="" style="padding: 3px; border: 1px solid #666;display: none;">
              	</select>
              	<select id="orgLevel3" name="" style="padding: 3px; border: 1px solid #666;display: none;">
              	</select>
              	<select id="orgLevel4" name="" style="padding: 3px; border: 1px solid #666;display: none;">
              	</select>
              </td>
            </tr>
            <tr align="center" height="35" class="tr_center" bgcolor="#000066">
              <td width="5%" class="tr_center"><input name="" id="checkAll" type="checkbox" value="" /></td>
              <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res7']}</td>
              <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res8']}</td>
              <td width="25%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td>
              <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td>
              <td width="20%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.add_contacts.res11']}</td>
            </tr>
            <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19" height="32" colspan="11" align="center">
			            	<c:if test="${keyword !=null}">
			            		${LANG['bizconf.jsp.invite_enContacts_list.res5']}
			            	</c:if>
			            	<c:if test="${empty keyword}">
			            		${LANG['bizconf.jsp.invite_enContacts_list.res6']}
			            	</c:if>
			            </td>
			     </tr>
			</c:if>
			<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
	            <tr name="contactsItem" align="center" bgcolor="#FFFFFF" height="30">
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${contact.id}" /></td>
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid" name="contactName${contact.id}">${contact.trueName}</td>
	              <td class="tr_main" >${contact.enName}</td>
	              <td class="tr_main" name="contactEmail${contact.id}">${contact.userEmail}</td>
	              <td class="tr_main" name="contactPhone${contact.id}">${contact.phone}</td>
	              <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center" name="contactMobile${contact.id}">${contact.mobile}</td>
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

