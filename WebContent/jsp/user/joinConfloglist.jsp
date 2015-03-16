<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/jion_meeting.css?ver=${version}"/>
<link href="${baseUrlStatic}/css/user/common.css?ver=1368412705788" type="text/css" rel="stylesheet">
<link href="${baseUrlStatic}/css/user/page.css?ver=1368412705788" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript">
function sortQuery(sortField,sortord){
	if(!sortord){
		if($("#sortRule").val()=='0'){
			sortord = "1";
		}else{
			sortord = "0";
		}
	}
	$("#sortField").val(sortField);
	$("#sortRule").val(sortord);
	$("#query").attr("action","/user/conflog/loglist");
	$("#query").submit();
}
</script>
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
</head>

<body onload="loaded();">
<form id="query" name="query" action="/user/conflog/loglist" method="post">
	<input type="hidden" value="${sortField}" id="sortField" name="sortField" />
	<input type="hidden" value="${sortRule}" id="sortRule" name="sortRule" />
<input type="hidden" value="${confId}" name="confId" />
<input type="hidden" value="true" name="userPage" />
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_invite" style=" background:#FFF; border-radius:3px;height: 550px;">
          <div class="First_Steps_title_invite"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a_invite">${LANG['bizconf.jsp.attendConfloglist.res1']}</h3>
            <p class="tit_b_invite">${LANG['bizconf.jsp.joinConfloglist.res1']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick_invite" src="/static/images/min.jpg" width="730" height="1" /></div>
          <div style="display: inline-block;height:30px;margin-left:25px;margin-top: 10px;">
          	<span class="button_common">
            	<a href="javascript:exports('${confId}');"><img src="/static/images/back.png" width="17" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;"/>${LANG['bizconf.jsp.conflogs.res1']}</a>
            </span>
          </div>
          <div class="jianju"></div>
          
          <!--${LANG['bizconf.jsp.attendConfloglist.res2']}-->
          <div class="First_Steps_main_invite" style=" background:#FFF;height: 381px;overflow-y: auto;overflow-x:hidden;width:730px;margin: 0 auto;position: relative;">
            <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
              <tr align="center" height="35" class="tr_center" bgcolor="#000066">
                <td width="25%" onclick="sortQuery('1');" style="cursor: pointer;" class="tr_center"><span style="text-decoration: underline;">${LANG['bizconf.jsp.add_contacts.res7']}</span>
	                	<c:if test="${1!=sortField}">
			            	<a class="paixu01" href="javascript:;" onclick="sortQuery('1','1')"><img src="/static/images/paixu_button.png" width="6" height="13"  style="position:relative;top:4px"/></a>
			       		 </c:if>
			       		 <c:if test="${1==sortField && 0==sortRule}">
			        		<a class="paixu01" href="javascript:;" onclick="sortQuery('1','0')"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13"   style="position:relative;top:4px"/></a>
			        	</c:if>
				        <c:if test="${1==sortField  && 1==sortRule}">
				        	<a class="paixu01" href="javascript:;" onclick="sortQuery('1','1')"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13"  style="position:relative;top:4px" /></a>
				     	</c:if>
                </td>
                <td width="25%" onclick="sortQuery('2')" style="cursor: pointer;" class="tr_center">
                	<span style="text-decoration: underline;">${LANG['bizconf.jsp.conflogs.res2']}</span>
                	<c:if test="${2!=sortField}">
			            	<a class="paixu01" href="javascript:;" onclick="sortQuery('2','1');"><img src="/static/images/paixu_button.png" width="6" height="13"   style="position:relative;top:4px"/></a>
			       		 </c:if>
			       		 <c:if test="${2==sortField && 0==sortRule}">
			        		<a class="paixu01" href="javascript:;" onclick="sortQuery('2','0');"><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13"  style="position:relative;top:4px" /></a>
			        	</c:if>
				        <c:if test="${2==sortField  && 1==sortRule}">
				        	<a class="paixu01" href="javascript:;" onclick="sortQuery('2','1');"><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13"  style="position:relative;top:4px" /></a>
				     	</c:if>
                </td>
                <td width="25%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res8']}</td>
                <td width="25%" class="tr_center" style=" border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attendConfloglist.res9']}</td>
              </tr>
	    <c:if test="${fn:length(pageModel.datas)<=0}">
			<tr class="table001" height="32"  >
				<td class="STYLE19" height="32" colspan="11" align="center"> ${LANG['website.common.msg.list.nodata']} </td>
			</tr>
		</c:if>
		<c:forEach var="log" items="${pageModel.datas}" varStatus="status">
              <tr align="center" bgcolor="#FFFFFF" height="30">
                <td class="tr_main" style=" border-left:#D2D8DB 1px solid">${log.userName}</td>
                <td class="tr_main">
                	<c:choose>
                		<c:when test="${log.termType eq '3'}">
                			${LANG['bizconf.jsp.conflogs.res3']}
                		</c:when>
                		<c:when test="${log.termType eq '1'}">
                			${LANG['bizconf.jsp.conflogs.res4']}
                		</c:when>
                		<c:otherwise>
                			${LANG['bizconf.jsp.conflogs.res5']}
                		</c:otherwise>
                	</c:choose>
                </td>
                <td class="tr_main">${myfn:fmtDate('yyyy-MM-dd HH:mm',log.joinTime,timezone)}</td>
                <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center">
                	<c:choose>
	                	<c:when test="${log.exitTime != null}">
		                	${myfn:fmtDate('yyyy-MM-dd HH:mm',log.exitTime,timezone)}
	                	</c:when>
	                	<c:otherwise>
	                		--
	                	</c:otherwise>
                	</c:choose>
                </td>
              </tr>
        </c:forEach>
              <tr>
                <td height="35" colspan="4" class="tr_bottom">
                    <jsp:include page="/jsp/common/page_info.jsp" />
                </td>
              </tr>
            </table>
          </div>
          <!--${LANG['bizconf.jsp.group_contacts_list.res9']}-->
          
          <div class="First_Steps_bottom_b">
            <div class="but99">
            	<span class="button_common">
            	<a href="javascript:closeDialog();"><img src="/static/images/quxiao.png" width="17" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;"/>${LANG['bizconf.jsp.attendConfloglist.res10']}</a>
            	</span>
            </div>
          </div>
        </div>
      </td>
      
      <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>
  </form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#logview");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.parent.$("#logview");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}

function exports(confId){
	window.location = "<%=request.getContextPath()%>/user/conflog/exportLogs?sortField=${sortField}&sortRule=${sortRule}&confId="+confId;
}
</script>
