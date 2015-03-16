<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_invite_user_list.res1']}</title>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>	
<script type="text/javascript">
	 var group_id = "${group_id}";
$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$(".group_search").watermark('${LANG['bizconf.jsp.group_contacts_list.res3']}');
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
});
</script>
</head>
<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinPublicDiv">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->  
		<div class="First_Steps_invite" style=" background:#FFF; border-radius:3px;">
	  	<div class="First_Steps_title_invite"> <a href="javascript:closeDialog();"></a>
	    	<h3 class="tit_a_invite">${LANG['bizconf.jsp.group_contacts_list.res1']}</h3>
	    	<p class="tit_b_invite">${LANG['bizconf.jsp.group_imports_list.res1']}</p>
	  	</div>
  		<div style=" background:#fff"><img class="toa_quick_invite" src="${baseUrlStatic}/images/min.jpg" width="730" height="1" /></div>
 
  		<div class="jianju"></div>
	  	<form id="query" name="query" action="/user/group/importContacts" method="post" style="position: relative;">
	  	<div style="width:96%;height: 430px;overflow-y: auto;position: absolute ;top: 5px;left:15px;">
		    <table width="730" align="center" cellpadding="0" cellspacing="0" border="0">
		      <tr>
		        <td height="40" colspan="6" class="tr_top" style="background: #FFF"><input name="keyword" type="text" class="meeting_ss group_search" value="${keyword }"/>
		          <input type="hidden" name="group_id" value="${group_id}" />
		          <input class="meeting_but" type="button" onclick="query.submit();"/>
		          </td>
		      </tr>
		      <tr align="center" height="35" class="tr_center" bgcolor="#000066">
		        <td width="6%" class="tr_center"><input id="checkAll" type="checkbox" value="" /></td>
		        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res7']}</td>
		        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res8']}</td>
		        <td width="24%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td>
		        <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td>
		        <td width="20%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.add_contacts.res11']}</td>
		      </tr>
		      <c:if test="${fn:length(pageModel.datas)<=0}">
						<tr align="center" bgcolor="#FFFFFF" height="30" >
					            <td class="tr_main STYLE19" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
					     </tr>
			</c:if>
			<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
		       <tr align="center" bgcolor="#FFFFFF" height="30">
		        <td class="tr_main" style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${contact.id }" /></td>
		     	<td class="tr_main">${contact.contactName}&nbsp;</td>
			    <td class="tr_main">${contact.contactNameEn}&nbsp;</td>
			    <td class="tr_main">${contact.contactEmail}&nbsp;</td>
			    <td class="tr_main">${contact.contactPhone}&nbsp;</td>
			    <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">${contact.contactMobile}&nbsp;</td>
		      </tr>
		     </c:forEach>
		     <tr align="center" bgcolor="#FFFFFF" height="30">
		        <td height="35" colspan="6" class="tr_bottom table_bottom"> 
		        	 <jsp:include page="/jsp/common/page_info.jsp" />
		        </td>
		      </tr>
		    </table>
	 	</div>
	  	</form>
	  	<div class="First_Steps_bottom_b">
	    <div class="but99"><span class="button_common"><a href="#" onclick="javascript:closeDialog();"><img src="${baseUrlStatic}/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:8px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a></span></div>
	    <div class="but100"><span class="button_common"><a href="javascript:importContact();"><img src="${baseUrlStatic}/images/right.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:8px;"/>${LANG['bizconf.jsp.add_contacts.res14']}</a></span></div>
  </div>
</div>
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->      
      </td>
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>
</body>
</html>

<script type="text/javascript">
function importContact() {
	if($("input[name=id]:checked").length==0){
		parent.errorDialog("${LANG['bizconf.jsp.contacts_import_main.res10']}");
		return;
	}
	var parms = "group_id=${group_id}";
	$("input[name=id]:checked").each(function(){
		parms += "&id="+$(this).val();
	});
	$.ajax({
      	type: "POST",
      	url:"/user/group/doImportSyn",
      	data:parms,
      	dataType:"json",
      	success:function(data){
      		parent.successDialog(data.message);
      		//parent.$("#viewGroup");
      		var win = parent.$("#viewGroup").find("iframe")[0].contentWindow || parent.$("#viewGroup").find("iframe")[0].window;
      		win.refreshData();
      		closeDialog();
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
        	closeDialog();
        }
	});  
	
	
}
function loaded() {
	var frame = parent.$("#importContact");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#importContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
