<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.invite_group_list.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>		
<script type="text/javascript" src="${baseUrlStatic}/js/common.js?ver=${version}"></script>	
<script type="text/javascript">
function viewContacts(id){
// 	var url = "/user/group/showContacts?viewOnly=1&group_id="+id;
	parent.parent.viewContact(id);
}

//${LANG['bizconf.jsp.invite_group_list.res2']}
function getContactsData(){
	var ids = "";
	$("input[name=id]:checked").each(function(){
		ids += ",";
		ids += $(this).val();
	});
	var data = new Object();
	data.ids = ids;
	return data;
}

$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$(".group_search").watermark('${LANG['bizconf.jsp.add_group.res3']}');
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
	
// 	remindInfo($("input[name=keyword]").get(0),"${LANG['bizconf.jsp.invite_group_list.res3']}\"${LANG['bizconf.jsp.add_group.res3']}\"${LANG['bizconf.jsp.invite_group_list.res4']}");
});

function submitForm(){
	$('input').trigger('submitForm');
	query.submit();
}
</script>
</head>
<body style="height: 550px;">
<form id="query" name="query" action="/user/group/invitelist" method="post" onsubmit="javascript:$('input').trigger('submitForm');">
	 <div class="First_Steps_main_invite">
          <table width="730" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
            <tr>
              <td height="40" colspan="4" bgcolor="#EAF4FC" class="tr_top"><input  name="keyword" value="${keyword}" type="text" value="" class="meeting_ss group_search" />
                <input class="meeting_but" type="button" onclick="javscript:submitForm();"/></td>
            </tr>
            <tr align="center" height="35" class="tr_center" bgcolor="#000066">
              <td width="10%" class="tr_center"><input name="" type="checkbox" id="checkAll" value="" /></td>
              <td width="25%" class="tr_center">${LANG['bizconf.jsp.add_group.res3']}</td>
              <td width="50%" class="tr_center">${LANG['bizconf.jsp.add_contacts.res12']}</td>
              <td width="15%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.invite_group_list.res5']}</td>
            </tr>
            <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr align="center" bgcolor="#FFFFFF" height="30">
			            <td class="STYLE19" height="32" colspan="4" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
			<c:forEach var="group" items="${pageModel.datas}" varStatus="status">
	            <tr align="center" bgcolor="#FFFFFF" height="30">
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid"><input name="id" type="checkbox" value="${group.id }" /></td>
	              <td class="tr_main" style=" border-left:#D2D8DB 1px solid">${group.groupName}&nbsp;</td>
	              <td class="tr_main"><div style="display:block; width:20em;word-break:keep-all;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${group.groupDesc}&nbsp;</div></td>
	              <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center"><a href="#" onclick="viewContacts('${group.id}');">${LANG['bizconf.jsp.invite_group_list.res6']}</a></td>
	            </tr>
           	</c:forEach>
            <tr>
              <td height="35" colspan="4" class="tr_bottom table_bottom"> 
              	<jsp:include page="/jsp/common/page_info.jsp" />
              </td>
            </tr>
          </table>
        </div>
	</form>
</body>
</html>

