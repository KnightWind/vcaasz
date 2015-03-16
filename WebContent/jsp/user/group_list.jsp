<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></script>	
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>	
<script type="text/javascript">
	function delUser(id){
		parent.parent.confirmDialog("${LANG['bizconf.jsp.group_list.res1']}", function() {
			//window.location ="/admin/entUser/delUsers?id="+id;
			query.action="/user/group/delGroup";
			$("input[name=id]").val(id);
			query.submit();
		});
	}
	
	function toEditUser(id){
		parent.parent.createOrUpdateAdminUser(id);
	}
	function toEditGroup(id){
		parent.parent.addGroup(id);
	}
	function viewContacts(id){
		parent.parent.viewGroup(id);
	}
	$(document).ready(function(){
		parent.refreshIHeight();
		$("span[name=contactnum]").each(function(){
			var countdata = $.parseJSON('${countJson}') || {};
			var group_id = $(this).attr("groupId");
			if(countdata[group_id] || countdata[group_id]>=0 ){
				$(this).empty().html("("+countdata[group_id]+")");
			}else{
				$(this).empty().html("(0)");
			}
		});
		
		$("#btn_search").click(function(){
			$("#pageNo").val("");
			$("#query").attr("action","/user/group/list");
			$("#query").submit();
		});
		
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").attr("action","/user/group/list");
				$("#query").submit();
			}
		});
		if (!$.browser.msie || $.browser.version>7) {
			$(".group_search").watermark('${LANG['bizconf.jsp.add_group.res3']}');
		}
	});
	
	
	
</script>
</head>
<body>
<form id="query" name="query" action="/user/group/list" method="post" onsubmit="javascript:$('input').trigger('submitForm');">
	<input type="hidden" name="id" value="" />
	<div class="meeting_main">
      <table id="t_box" align="center" border="0" cellpadding="0" cellspacing="0" width="100%"><tbody><tr>
        <td height="40" colspan="4" bgcolor="#EAF4FC" class="tr_top">
        	<input class="meeting_ss group_search" name="keyword" value="${keyword}" type="text">
            <input class="meeting_but" type="button" id="btn_search">
            <div class="top_right"><span class="button_common"><a href="javascript:;" onclick="toEditGroup('');"><img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/add_16.png">${LANG['bizconf.jsp.add_group.res1']}</a></span></div>
        </td>
      </tr>
     
      <tr align="center" height="35" class="tr_center" bgcolor="#000066">
<%--        <td width="5%" class="tr_center"><input value="" name="" type="checkbox"></td>--%>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.add_group.res3']}</td>
        <td width="40%" class="tr_center">${LANG['bizconf.jsp.add_group.res4']}</td>
        <td width="40%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attended_conf_list.res9']}</td>
      </tr>
	 <c:if test="${fn:length(pageModel.datas)<=0}">
				<tr class="table001" height="32"  >
			            <td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			     </tr>
			</c:if>
	<c:forEach var="group" items="${pageModel.datas}" varStatus="status">
	      <tr align="center" bgcolor="#FFFFFF">
	        <td class="tr_main" style="border-left:#D2D8DB 1px solid"><a href="javascript:viewContacts('${group.id }')">${group.groupName}</a></td>
	        <td class="tr_main">
	        	<c:if test="${!empty group.groupDesc}">
	        		${group.groupDesc}
	        	</c:if>
	        	<c:if test="${empty group.groupDesc}">
	        		&nbsp;
	        	</c:if>
	        </td>
	        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
	       	 <table>
	            <tbody><tr>
	              <td class="k_a"><a href="javascript:viewContacts('${group.id }')"><img style=" margin-right:5px;" src="/static/images/f04.jpg" align="absmiddle" width="16" height="16" />${LANG['bizconf.jsp.group_list.res2']}</a>&nbsp;<span groupId="${group.id}" name="contactnum"><span></td>
	              <td class="k_a"><a href="javascript:toEditGroup('${group.id }')"><img style=" margin-right:5px;" src="/static/images/email01.png" align="absmiddle" height="16" width="16">${LANG['bizconf.jsp.conf_list_index.res57']}</a></td>
	              <td class="k_a"><a href="javascript:delUser('${group.id }')"><img style=" margin-right:5px;" src="/static/images/email02.png" align="absmiddle" height="17" width="12">${LANG['bizconf.jsp.attended_conf_list.res12']}</a></td>
	            </tr>
	          </tbody>
	         </table>
	        </td>
	      </tr>
     </c:forEach>
     <tr>
        <td height="35" colspan="4" class="tr_bottom table_bottom"> 
         <jsp:include page="/jsp/common/page_info.jsp" />
        </td>
      </tr>
    </tbody>
    </table>
  </div> 
	</form>
</body>
</html>

