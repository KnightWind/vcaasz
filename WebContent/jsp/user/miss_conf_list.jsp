<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.conf_list_index.res31']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.plugin.js?ver=${version}"></script>

<script type="text/javascript">
/*
 * ${LANG['bizconf.jsp.conf_list_index.res3']}
 */
function viewConf(id){
	parent.viewConf(id);
}
/*
 * ${LANG['bizconf.jsp.attended_conf_list.res2']}
 */
function reCreateReservationConf(id) {
	parent.reCreateReservationConf(id);
}

function delConf(confId){
	parent.parent.confirmDialog("${LANG['bizconf.jsp.attended_conf_list.res3']}",function(){
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/delete/"+confId,
	      	dataType:"json",
	      	success:function(data){
				if(data){
					 window.location.reload(true);
				}else{
					parent.errorDialog('${LANG['bizconf.jsp.attended_conf_list.res4']}');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		});  
	});	
}

function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.attendConf.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.attendConf.res4']}
    	resetPageNo();
    	queryConf.action = "/user/conf/listWithMissConf";
    	queryConf.submit();	
    }   
} 

$(function() {
	$("#toSearch").click(function(){
		resetPageNo();
		queryConf.action = "/user/conf/listWithMissConf";
		queryConf.submit();	
	});
	$(".miss_conf_search").watermark('${LANG['bizconf.jsp.attended_conf_list.res5']}');
});
</script>

</head>
<body>
<form id="queryConf" name="queryConf" action="" method="post">
  <div class="meeting_main" >
  <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
  <tr>
     <td height="40" colspan="6" bgcolor="#EAF4FC" class="tr_top">
 	 <input name="titleOrHostName" id="titleOrHostName" class="meeting_ss miss_conf_search" type="text" value="${titleOrHostName }" onkeydown='enterSumbit()'/>
     <input class="meeting_but" type="button" id="toSearch"/></td>
  </tr>
   <tr align="center" height="35" class="tr_center" bgcolor="#000066">
        <td width="25%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res3']}</td>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.attendConfloglist.res5']}</td>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.attended_conf_list.res6']}</td>
        <td width="15%" class="tr_center">${LANG['bizconf.jsp.attended_conf_list.res7']}</td>
        <td width="10%" class="tr_center">${LANG['bizconf.jsp.attended_conf_list.res8']}</td>
        <td width="20%" class="tr_center" style="border-right:#D2D8DB 1px solid">${LANG['bizconf.jsp.attended_conf_list.res9']}</td>
    </tr>
  
  <c:if test="${fn:length(confList)<=0 }">
      <tr>
        <td height="32" class="STYLE19" colspan="6" align="center">
     		${LANG['website.common.msg.list.nodata']}
        </td>
      </tr>
   </c:if>
   
   
   <c:if test="${fn:length(confList)>0 }">
    <c:forEach var= "conf" items = "${confList}"  varStatus="status">	
      <tr align="center" bgcolor="#FFFFFF" height="32">
        <td class="tr_main" style=" border-left:#D2D8DB 1px solid">
        	<a href="javascript:;" onclick="viewConf(${conf.id})" style="text-decoration:underline"><span style="color:#73798E">${conf.confName}</span></a>
       	</td>
        <td class="tr_main">${conf.compereName}</td>
        <td class="tr_main"><fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
        <td class="tr_main">${conf.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}</td>
        <td class="tr_main">${participantSizeList[conf.id]}</td>
        <td class="tr_main" style=" border-right:1px solid #D2D8DB" align="center"><table>
            <tr>
              <c:if test="${user.id eq conf.createUser}">
	              <td class="k_a">
	              	 <a href="javascript:;" onclick="reCreateReservationConf(${conf.id})"><img src="/static/images/email01.png" width="16" height="15" align="absmiddle" style=" margin-right:5px;" />
	              	  <span style="color:#73798E">${LANG['bizconf.jsp.attended_conf_list.res11']}</span></a>
	              </td>
              </c:if>
<!--               <td class="k_a"><a href="#"><img src="/static/images/email01.png" width="16" height="16" align="absmiddle" style=" margin-right:5px;"  />${LANG['bizconf.jsp.attended_conf_list.res11']}</a></td> -->
              <td class="k_a"><a href="javascript:;" onclick="delConf(${conf.id});">
              	<img src="${baseUrlStatic}/images/email02.png" width="12" height="17" align="absmiddle" style=" margin-right:5px;"  /><span style="color:#73798E">${LANG['bizconf.jsp.attended_conf_list.res12']}</span></a>
              </td>
            </tr>
          </table></td>
      </tr>
      
    </c:forEach>
    </c:if>
    
	  <tr>
	    <td class="table_bottom" height="38" colspan="6">
	     <jsp:include page="/jsp/common/page_info.jsp" />
		</td>
	  </tr>
	  
	  
    </table>
  </div>
<!-- </div> -->
</form>
</body>
</html>
