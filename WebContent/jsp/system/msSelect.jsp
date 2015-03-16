<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<style>
.submitbutton, .closeButton{
		background: url("/static/images/button01.jpg") no-repeat center center;
	    background-color: #663399;
	    border: 0px;
	    color: #333333;
	    cursor: pointer;
	    height: 24px;
	    line-height:24px;
	    margin: 7px;
	    text-align: center;
	    width: 72px;
	}
</style>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<script type="text/javascript"> 
$(function() {
	//table tr background highlight
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	$("#selectAll").click(function(){
		if($(this).attr("checked")){
			$("input[name=msId]").attr("checked",true);				
		}else{
			$("input[name=msId]").attr("checked",false);
		}
	});
});

function selectMSinfo(){
	var text = "";
	var ids = "";
	if($("input[name=msId]:checked").length <1){
		parent.errorDialog("${LANG['bizconf.jsp.system.mslist.warninfo6']}");
		return ;
	}
	$("input[name=msId]:checked").each(function(){
		var id = $(this).val();
		ids += id;
		ids += ",";
		
		var name = $("#msName"+id).text();
		text += name;
		text += ",";
	});
	parent.$("#msgroupDiv").find("iframe")[0].contentWindow.setMsValue(text,ids);
	//alert(text);
	//alert(ids);
	closeDialog();
}
</script>
<title>MS列表</title>
</head>
<body onload="loaded();">
<form id="query" name="query" action="/system/ms/showAllMsSelect" method="post">
<div class="" style="height: 330px; overflow-y: auto; width: 580px;margin-left:10px;overflow-x: hidden">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style="border:#B5D7E6 1px solid; border-top:none; border-bottom:none;margin: 5px auto;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" class="table001" border="0" cellpadding="0" cellspacing="0" id="site-list">
     <tr class="table003" height="38" >
        <td width="5%" height="38" bgcolor="d3eaef" class="STYLE10">
	        <div align="center">
	        	<input type="checkbox" name="selectAll" id="selectAll" />
	        </div>
        </td>
        <td width="10%" height="28" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>MS IP</b></span></div></td>
        <td width="10%" height="28" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo2']}</b></span></div></td>
        <td width="15%" height="28" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo3']}</b></span></div></td>
        <td width="20%" height="28" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.system.mslist.showinfo4']}</b></span></div></td>
        <td width="40%" height="28" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>MS ${LANG['system.sysUser.list.remark']}</b></span></div></td>
     </tr>
      <c:if test="${fn:length(allmes)<=0 }">
         <tr>
           <td height="32" class="STYLE19" colspan="6"  align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(allmes)>0 }">
     <c:forEach var="ms" items = "${allmes}"  varStatus="status">
      <tr>
      	<td width="10%" height="26">
	        <div align="center">
	        	<input type="checkbox" name="msId" value="${ms.id}" <c:if test="${msInfos[ms.id]}">checked = "checked"</c:if> />
	        </div>
        </td>
        <td height="26" class="STYLE19">
        	<div align="center"><span>${ms.msIp}</span></div>
        </td>
        <td height="26" class="STYLE19"><div align="center" id="msName${ms.id}">${ms.msName }</div></td>
        <td height="26" class="STYLE19"><div align="center">${ms.msSupplier }</div></td>
        <td height="26" class="STYLE19"><div align="center">
        	<c:choose>
        		<c:when test="${ms.inUse eq 1 }">
        			${LANG['bizconf.jsp.system.mslist.showinfo5']}
        		</c:when>
        		<c:otherwise>
        			${LANG['site.admin.conf.config.option.disable']}
        		</c:otherwise>
        	</c:choose>
        </div> 
        </td>
        <td height="26" class="STYLE19"><div align="center">${ms.msDesc }</div></td>
      </tr>
     </c:forEach>
     </c:if>
    </table>
    </td>
  </tr>
</table>
</div>
<div style="text-align: right;padding-right: 20px">
		<input class="submitbutton" type="button" name="" id="saveBtn" value="${LANG['bizconf.jsp.system.email_template.res5']}" onclick="selectMSinfo()"/>
		<input class="closeButton" type="button" name="" id="" value="${LANG['system.cancel']}" onclick="closeDialog()"/>
</div> 
</form>
</body>
</html>

<script type="text/javascript"> 
function loaded() {
	var frame = parent.$("#showMSDiv");
	frame.trigger("loaded");
}

function closeDialog() {
	var frame = parent.$("#showMSDiv");
	frame.trigger("closeDialog");
}
</script>