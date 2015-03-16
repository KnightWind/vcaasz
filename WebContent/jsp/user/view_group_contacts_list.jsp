<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>
<title>${LANG['bizconf.jsp.group_contacts_list.res1']}</title>
<style>

</style>

</head>
<body onload="loaded()">
  <div class="add06_main">
    <div class="add06_top">
    <div class="add06_top_left"></div>
    <div class="add06_top_center"></div>
    <div class="add06_top_right"></div>
  </div>
  <div class="add06_center">
    <div class="add06_center_left"></div>
    <div class="add06_center_center">
<div class="First_Steps_invite" style=" background:#FFF; border-radius:3px;">
  <div class="First_Steps_title_invite"> <a href="javascript:closeDialog();"></a>
    <h3 class="tit_a_invite">${LANG['bizconf.jsp.group_contacts_list.res4']}</h3>
    <p class="tit_b_invite"></p>
  </div>
  <div style=" background:#fff"><img class="toa_quick_invite" src="/static/images/min.jpg" width="730" height="1" /></div>
  <div class="jianju"></div>
 
  <!--${LANG['bizconf.jsp.attendConfloglist.res2']}-->
  <div class="First_Steps_main_invite" style=" width:96%;height: 430px;overflow-y: auto;margin: 5px auto;">
  <form id="query" name="query" action="/user/group/showContacts" method="post">
  	<input type="hidden" name="viewOnly" value="1"/>
  	<input type="hidden" name="group_id" value="${group_id}"/>
    <table width="730" align="center" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td height="40" colspan="6" class="tr_top" style="background: #FFF"><input name="keyword" type="text" class="meeting_ss group_search" value="${keyword}"/>
          <input class="meeting_but" type="button" onclick="query.submit();" /></td>
      </tr>
      <tr align="center" height="35" class="tr_center" bgcolor="#000066">
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res7']}</td>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res8']}</td>
        <td width="30%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res9']}</td>
        <td width="20%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res10']}</td>
        <td width="20%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.add_contacts.res11']}</td>
      </tr>
      <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
	</c:if>
	<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
	      <tr align="center" bgcolor="#FFFFFF" height="30">
	        
	        <td class="tr_main" style=" border-left:#D2D8DB 1px solid">${contact.contactName}&nbsp;</td>
	        <td class="tr_main">${contact.contactNameEn}&nbsp;</td>
	        <td class="tr_main">${contact.contactEmail}&nbsp;</td>
	        <td class="tr_main">${contact.contactPhone}&nbsp;</td>
	        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center"><a href="#">${contact.contactMobile}&nbsp;</a></td>
	      </tr>
      </c:forEach>
      <tr>
        <td height="35" colspan="6" class="tr_bottom table_bottom"> 
        	 <jsp:include page="/jsp/common/page_info.jsp" />
        </td>
      </tr>
    </table>
    </form>
  </div>
  <!--${LANG['bizconf.jsp.group_contacts_list.res9']}--> 
  <div class="First_Steps_bottom_b">
    <div class="but99"><span class="button_common"><a  href="javascript:closeDialog();"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:8px;"/>${LANG['bizconf.jsp.enContacts_list.res8']}</a></span></div>
  </div>
</div>
</div>
<div class="add06_center_right"></div>
</div>
<div class="add06_bottom">
<div class="add06_bottom_left"></div>
<div class="add06_bottom_center"></div>
<div class="add06_bottom_right"></div>
</div>
</div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$(".group_search").watermark('${LANG['bizconf.jsp.view_group_contacts_list.res1']}');
	}
});
function loaded() {
	var frame = parent.parent.$("#viewContact");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.parent.$("#viewContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>

