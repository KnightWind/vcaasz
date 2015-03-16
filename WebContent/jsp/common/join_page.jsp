<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script> 
<title>${LANG['bizconf.jsp.common.join_msg.res1']}</title>
</head>
<body>

<cc:confList var="JOIN_TYPE_CONFID"/>
<cc:confList var="JOIN_TYPE_SECURE_CODE"/>
<cc:confList var="HOST_JOIN_TYPE_EMAIL"/>
<cc:confList var="JOIN_TYPE_OURURL"/>
<c:if test="${joinType==JOIN_TYPE_SECURE_CODE}">
<!-- ${LANG['bizconf.jsp.common.join_page.res1']}-->

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinSecureDiv"  style="display:" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_quick_a" style=" background:#FFF;">
          <div class="First_Steps_title_a"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res3']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <table class="box_a_quick02">
             <form name="joinSecureForm" id="joinSecureForm" method="post" action="/join">
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.common.join_page.res4']}</td>
                <td align="left">
<%--                  <c:set var="userName" value="${LANG['bizconf.jsp.common.join_page.res5']}"/> --%>
              <c:if test="${currentUser!=null }">
              	<c:set var="userName" value="${currentUser.trueName }"/>
              </c:if>
              <input class="right_text_a" name="userName" id="userName" type="text"  value="${userName}"  watermark="${LANG['bizconf.jsp.common.join_page.res5']}"/>
                </td>
              </tr>
           
              <tr class="box01">
                <td align="right" class="left_text_a">${LANG['bizconf.jsp.common.join_page.res6']}</td>
                <td align="left">
                  <input class="right_text_a" name="code" id="code"  type="text"  watermark="${LANG['bizconf.jsp.common.join_page.res7']}" />
                </td>
              </tr>
              <tr align="left">
            <td align="left" colspan="3">  
            	<div style="height: 50px;">
            		<div class="but150">
            			<span class="button_common">
            				<a  href="javascript:join();"><img src="/static/images/join.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.common.join_page.res2']}</a>
            			</span>
            		</div>
            	</div>
            </td>
            </tr>
               </form>
            </table>
          </div>
          </div> 
      </td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>

</c:if>
<c:if test="${joinType==JOIN_TYPE_CONFID}">

<!-- ${LANG['bizconf.jsp.common.join_page.res8']}ID${LANG['bizconf.jsp.common.join_page.res9']} -->
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinPublicDiv" style="display:">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->  
		<form name="joinConfIdForm" id="joinConfIdForm" method="post" action="/join">
      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
      	<div class="First_Steps_quick_a" style=" background:#FFF">
	        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
	          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
	          <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res10']}</p>
	        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
          <table class="box_a_quick02">
            <tr class="box01">
              <td align="right" class="left_text_a">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</td>
              <td align="left">
<%--  				<c:set var="userName" value="${LANG['bizconf.jsp.common.join_page.res5']}"/> --%>
              	<c:if test="${currentUser!=null }">
              	<c:set var="userName" value="${currentUser.trueName }"/>
              	</c:if>
              	<input class="right_text_a" name="userName" id="userName" type="text" value="${userName}"/>
              </td>
            </tr>
            <c:if test="${passCheck==1}">
            <tr class="box01">
              <td align="right" class="left_text_a">${LANG['bizconf.jsp.common.join_page.res11']}</td>
              <td align="left">
              <input class="right_text_a" name="cPass" id="cPass"  type="password"   watermark="${LANG['bizconf.jsp.common.join_page.res12']}" />
              </td>
            </tr>
            </c:if>
            <tr align="left">
            <td align="left" colspan="3">  
				<div style="height: 50px;">
	      		<div class="but150">
		          	<span class="button_common" style="margin-right: 5px;">
		          		<a href="javascript:join();">
		          			<img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-left:5px; margin-right:5px;"/>${LANG['bizconf.jsp.common.join_page.res2']}
		          		</a>	
		          	</span>
		          </div>  
	        </div>
            </td>
            </tr>
          </table>
        </div>
      </div>
      </form>
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->      
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

</c:if>

<c:if test="${joinType==HOST_JOIN_TYPE_EMAIL}">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinFromEmailDiv" style="display:">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->  
		<form name="joinEmailForm" id="joinEmailForm" method="post" action="/join">
      <input type="hidden" name="cId" id="cId" value="${cId}"/>
      <input type="hidden" name="uId" id="uId" value="${uId}"/>
      <input type="hidden" name="scode" id="scode" value="${scode}"/>
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
      <div class="First_Steps_quick_a" style=" background:#FFF">
        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
          <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res10']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
          <table class="box_a_quick02">
            <tr class="box01">
              <td align="right" class="left_text_a">${LANG['bizconf.jsp.common.join_page.res13']}</td>
              <td align="left">
 			  	<c:set var="userName" value=""/>
              	<c:if test="${currentUser!=null }"><c:set var="userName" value="${currentUser.trueName }"/></c:if>
                <input class="right_text_a" name="userName" id="userName" type="text" value="${userName}" watermark="${LANG['bizconf.jsp.common.join_page.res5']}"/>
              </td>
            </tr>
            <c:if test="${passCheck==1}">
            <tr class="box01">
              <td align="right" class="left_text_a">${LANG['bizconf.jsp.common.join_page.res11']}</td>
              <td align="left">
              <input class="right_text_a" name="cPass" id="cPass"  type="password"   watermark="${LANG['bizconf.jsp.common.join_page.res12']}" />
              </td>
            </tr>
            </c:if>
            <tr>
            	<td colspan="3">
            		<div style="height: 50px;">
            			<div class="but150">
				          <span class="button_common" style="margin-right: 5px;">
				          	<a href="javascript:join();"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-left:5px; margin-right:5px;"/>${LANG['bizconf.jsp.common.join_page.res2']}</a>
				          </span>
				        </div>
            		</div>
            	</td>
            </tr>
          </table>
        </div>
      </div>
      </form>
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->      
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

</c:if>

<%--


<!-- ${LANG['bizconf.jsp.common.join_page.res14']} -->

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0"  id="installPlugDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_quick_b" style=" background:#FFF">
          <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res15']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <p class="qidong">${LANG['bizconf.jsp.common.join_page.res16']}</p>
          </div>
          <!-- <div class="First_Steps_bottom01">
          <div class="but120"><a href="#"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px;"/>${LANG['bizconf.jsp.admin.arrange_org_user.res10']}</a></div>
          
        </div>--> 
        </div>
      </td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>






<!-- ${LANG['bizconf.jsp.common.join_page.res17']} -->

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="loadPlugDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_quick_b" style=" background:#FFF">
          <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res15']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <p class="qidong_jiazai">${LANG['bizconf.jsp.common.join_page.res18']}...</p>
            <img src="/static/images/loading.gif" width="32" height="32" style=" margin:0px auto; display:block; margin-bottom:30px;" /> </div>
          <!-- <div class="First_Steps_bottom01">
          <div class="but120"><a href="#"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px;"/>${LANG['bizconf.jsp.admin.arrange_org_user.res10']}</a></div>
          
        </div>--> 
        </div>
      </td>
      
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
      
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>





<!-- ${LANG['bizconf.jsp.common.join_page.res19']}Client -->

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="startClientDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->  
		<div class="First_Steps_quick_b" style=" background:#FFF">
        <div class="First_Steps_title_a"> <a   href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
          <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res20']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong">${LANG['bizconf.jsp.common.join_page.res21']}...</p>
        </div>
      </div>
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->      
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
 
 
 <!-- ${LANG['bizconf.jsp.common.join_page.res22']}Client -->
 <table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="downClientDiv" style="display:none">
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        
        <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
        
        <td class="overlay-content"> 

        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
          <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res23']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong_top">${LANG['bizconf.jsp.common.join_page.res24']}</p>
         <a href="javascript:" class="go_down" id="downLoadLink" onclick="">${LANG['bizconf.jsp.common.join_page.res25']}</a>
         <p class="qidong"><a href="#" style=" text-decoration:underline;color:#4B92D1"><img src="/static/images/help_bg.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; " />${LANG['bizconf.jsp.common.join_page.res26']}!</a></p>
        </div>
      
      </div>
    </td>
        
        <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->
        
        <td class="overlay-bdR"></td>
      </tr>
      <tr>
        <td class="overlay-ftL"></td>
        <td class="overlay-ftC"></td>
        <td class="overlay-ftR"></td>
      </tr>
    </tbody>
  </table>
 
 <form name="downForm" id="downForm" method="post" action="/join/download" target="downFrame">
 <input name="userName" type="hidden" value="${userName}"/>
 <input name="cId" type="hidden" value="${cId}"/>
</form>
<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>




<!-- ${LANG['bizconf.jsp.common.join_page.res27']} -->
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinMtgErrorDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->  
		<form name="joinForm" id="joinForm" method="post" action="/join">
	      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
	      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	      	<div class="First_Steps_quick_a" style=" background:#FFF">
		        <div class="First_Steps_title_a" style="height: 52px;"> 
		        	<a href="javascript:"  onclick="javascript:closeDialog();" ></a>
		          	<h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res28']}</h3>
		        </div>
	        	<div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        	<div class="First_Steps_top" style=" background:#FFF"> </div>
		        <div class="First_Steps_main_quick">
		          <table class="box_a_quick02" width="100%">
		            <tr class="box01">
		              <td align="center" colspan="2">
		              <c:set var="msgName" value="website.conf.join.type.${msgFlag}"/>
						<div style="height: 20px;line-height: 20px;">${LANG[msgName]}</div>
		              </td>
		           
		          </table>
		        </div>
		        <div class="First_Steps_bottom01"  style="height: 30px;">
		        	<div class="but150">
		        		<span class="button_common">
		        			<a href="javascript:" onclick="closeDialog();"><img src="/static/images/cross.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.admin.viewNotice.res4']}</a>
		        		</span>
		        	</div>
		        </div>
	      </div>
      </form>
      <!--${LANG['bizconf.jsp.admin.viewNotice.res2']}========================================================================-->      
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

--%>

</body>
</html>


<script type="text/javascript">
$(document).ready(function(){
	$(".right_text_a").keypress(function(e){
		if(e.keyCode == 13){
			join();
			return false;
		}
	});	
	
});

function join(){
	<c:if test="${joinType!=null}">
	var chkStatus=true;
	var isTip=false;
	<c:if test="${joinType==JOIN_TYPE_CONFID}">
	
	var userName=$("#userName").val();
	var cPass=$("#cPass").val();
	if(userName==null || userName==""  ){
		parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res5']}");
		isTip=true;
		chkStatus=false;
	}
	<c:if test="${passCheck==1}">
	if(cPass==null || $.trim(cPass)==""  ){
		if(!isTip){
			parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res12']}");
		}
		isTip=true;
		chkStatus=false;
	}
	</c:if>
	if(chkStatus==true){
		$("#joinConfIdForm").submit();
	}
	</c:if>
	<c:if test="${joinType==JOIN_TYPE_SECURE_CODE}">
	
	var userName=$("#userName").val();
	var code=$("#code").val();
	if(userName==null || userName=="" ){
		parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res5']}");
		isTip=true;
		chkStatus=false;
	}
	if(code==null || $.trim(code)=="" ){
		if(!isTip){
			parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res7']}");
		}
		isTip=true;
		chkStatus=false;
	}
	if(chkStatus==true){
		$("#joinSecureForm").submit();
	}
//	$("#joinSecureForm").submit();
	</c:if>
	<c:if test="${joinType==HOST_JOIN_TYPE_EMAIL}">
	var userName=$("#userName").val();
	var code=$("#code").val();
	if(userName==null || userName==""){
		parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res5']}");
		isTip=true;
		chkStatus=false;
	}
	<c:if test="${passCheck==1}">
	var cPass=$("#cPass").val();
	if(cPass==null || $.trim(cPass)==""  ){
		if(!isTip){
			parent.errorDialog("${LANG['bizconf.jsp.common.join_page.res12']}");
		}
		isTip=true;
		chkStatus=false;
	}
	</c:if>
	if(chkStatus==true){
		$("#joinEmailForm").submit();
	}
//	$("#joinEmailForm").submit();
	//$("#joinSecureDiv").hide();
	//$("#joinFromEmailDiv").show();
	//$("#joinPublicDiv").hide();
	//$("#joinEmailForm").submit();
	</c:if>
	
	</c:if>
	
	
	
	
	
}

<%--	//${LANG['bizconf.jsp.common.join_page.res29']}
function showError(){
	$("#joinSecureDiv").hide();
	$("#joinPublicDiv").hide();
	
	$("#installPlugDiv").hide();
	$("#loadPlugDiv").hide();
	$("#startClientDiv").hide();
	$("#downClientDiv").hide();

	$("#joinMtgErrorDiv").show();
}
		--%>
			
	function resetFrameHeight(){
	    var pageHeight=document.body.scrollHeight;
	    var pageHeight=pageHeight+5;
	    parent.changeIframeSize("dialogFrame",0,pageHeight);
	}
	
	
	
	//${LANG['bizconf.jsp.common.join_page.res30']}
	window.onload=	function() {
		var frame = parent.$("#joinMeeting");
		frame.trigger("loaded");
		resetFrameHeight();
	}
	
	//${LANG['bizconf.jsp.common.join_page.res31']}
	function closeDialog() {
		var dialog = parent.$("#joinMeeting");
		dialog.trigger("closeDialog");
	}
	
	
	


	
</script>

<%--
function showJoinDiv(){
		
		resetFrameHeight();
	}
	function initPage(){
		showJoinDiv();
	}
	initPage();
		<c:if test="${joinType!=null}">
		$("#joinMtgErrorDiv").hide();
		<c:if test="${joinType==JOIN_TYPE_CONFID}">
		$("#joinSecureDiv").hide();
		$("#joinFromEmailDiv").hide();
		$("#joinPublicDiv").show();
		</c:if>
		<c:if test="${joinType==JOIN_TYPE_SECURE_CODE}">
		$("#joinSecureDiv").show();
		$("#joinFromEmailDiv").hide();
		$("#joinPublicDiv").hide();
		</c:if>
		<c:if test="${joinType==HOST_JOIN_TYPE_EMAIL}">
		$("#joinSecureDiv").hide();
		$("#joinFromEmailDiv").show();
		$("#joinPublicDiv").hide();
		</c:if>
		</c:if>
/*
	*  ${LANG['bizconf.jsp.common.join_page.res22']}Client${LANG['bizconf.jsp.common.join_page.res32']}
	*/
	function download(){
		//${LANG['bizconf.jsp.common.join_page.res33']}URL${LANG['bizconf.jsp.common.join_page.res34']}Client
		//$("#downClientDiv").show();
		//window.open("/join/download?cId="+cId);
		$("#downForm").submit();
		closeDialogForDelay();
		//alert("${LANG['bizconf.jsp.common.join_page.res35']}");
	}
	
	//${LANG['bizconf.jsp.common.join_page.res36']}
	function closeDialogForDelay(){
		window.setTimeout(closeDialog,2000);
	}
function initPage(){
			//var msgFlag="${msgFlag}";
			<c:set var="msgName" value="website.conf.joinouter.erroecode.${errorCode}"/>
			var msgFlag="${LANG[msgName]}";
			alert(msgFlag);
		}
		initPage();
 --%>
