<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<style type="text/css">
html, body {
	height: 100%;
}
</style>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script> 
<%@ include file="/jsp/common/cookie_util.jsp"%>
<title>${LANG['bizconf.jsp.common.join_msg.res1']}</title>
</head>
<body>
<table style="width: 100%;height: 100%;">
<tr>
<td  align="center" valign="middle">


<!-- ${LANG['bizconf.jsp.common.join_page.res14']} -->

<%--

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

--%>



<!-- ${LANG['bizconf.jsp.common.join_page.res14']} -->
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="installPlugUpDiv" style="display:none">
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
            <p class="tit_b">${LANG['bizconf.jsp.common.join_plug.res1']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
          <img class="top_ico" src="/static/images/top_ico.png" width="146" height="143" />
          <p class="qidong_top">${LANG['bizconf.jsp.common.join_page.res16']}</p>
            <p class="tishi01">1.${LANG['bizconf.jsp.common.join_plug.res2fix']}</p>
			<p class="tishi02">2.${LANG['bizconf.jsp.common.join_plug.res4']}
			</p>
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


<!-- ${LANG['bizconf.jsp.common.join_page.res14']} -->

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="installPlugDownDiv" style="display:none" >
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
          <div class="First_Steps_title_a"> <a  href="javascript:"  onclick="javascript:closeDialog();" ></a>
            <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.join_plug.res1']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
          <img class="down_ico" src="/static/images/down_ico.png" width="146" height="142" />
          <p class="qidong_top_down">${LANG['bizconf.jsp.common.join_page.res16']}</p>
            <p class="tishi01_down">1.${LANG['bizconf.jsp.common.join_plug.res2fix']}</p>
			<p class="tishi02_down">2.${LANG['bizconf.jsp.common.join_plug.res4']}</p>
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
          <div class="First_Steps_title_a"> 
            <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
            <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res15']}</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <p class="qidong_jiazai">${LANG['bizconf.jsp.common.join_page.res18']}...</p>
            <img src="/static/images/loading.gif" width="32" height="32" style=" margin:0px auto; display:block; margin-bottom:30px;" /> </div>
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

        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="closeDialog();" ></a>
          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
          <p class="tit_b">${LANG['bizconf.jsp.common.join_page.res23']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong_top99">${LANG['bizconf.jsp.common.join_page.res24']}</p>
         <a href="JavaScript:download();" class="go_down99" id="downLoadLink" >${LANG['bizconf.jsp.common.join_page.res25']}</a>
         <p class="qidong"><a href="/jsp/user/download_help.jsp" style=" text-decoration:underline;color:#4B92D1" target="_blank"><img src="/static/images/help_bg.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; " />${LANG['bizconf.jsp.common.join_page.res26']}!</a></p>
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
 <input name="rId" type="hidden" value="${rId}"/>
</form>
<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>

<cc:confList var="CLIENT_DOWNLOAD_URL"/>
<object id="meetplugin" width="0" height="0" codebase="${CLIENT_DOWNLOAD_URL}/kip/mcieplgmeet.cab"  classid="clsid:DE97A9DE-33E1-4862-AFFD-0579C960B31E" >
</object>


<embed id="joinConf" name="joinConf"  width="0" height="0" type="application/x-vnd-eSpace Meeting-scriptableplugin" >
</embed>
<%--

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
          <p class="tit_b">${LANG['bizconf.jsp.common.join_plug.res5']}</p>
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
 

<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="downClientDiv" style="display:none">
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
        <div class="First_Steps_title_a"> <a  href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">${LANG['bizconf.jsp.common.join_page.res2']}</h3>
          <p class="tit_b">${LANG['bizconf.jsp.common.join_plug.res5']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
         <p class="qidong">${LANG['bizconf.jsp.common.join_plug.res6']}<a  href="javascript:" onclick="download();" style=" color:#03F; text-decoration:underline;">${LANG['bizconf.jsp.common.join_plug.res7']}</a>${LANG['bizconf.jsp.common.join_plug.res8']}</p>
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
	          <p class="tit_b">${LANG['bizconf.jsp.common.join_plug.res5']}</p>
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
 <form name="downForm" id="downForm" method="post" action="/join/download" target="downFrame">
 <input name="userName" type="hidden" value="${userName}"/>
 <input name="cId" type="hidden" value="${cId}"/>
</form>
<iframe frameborder="0" width="0" height="0" scrolling="no" id="downFrame" name="downFrame"></iframe>

<object id="meetplugin" codebase="http://www.confcloud.cn/download/kip/mcieplgmeet.cab" width=0
        height=0 classid="clsid:2BB4DED4-1CF2-4b5f-8345-95D73E4197A1"
        VIEWASTEXT></object>
        
        
        
         	<c:if test="${msgFlag!=null && msgFlag!=''}"> 
		function initPage(){
			//var msgFlag="${msgFlag}";
			<c:set var="msgName" value="website.conf.join.type.${msgFlag}"/>
			var msgFlag="${LANG[msgName]}";
			alert(msgFlag);
		}
		initPage();
		 	</c:if> 
		
--%>
</td>
</tr>	
</table>
</body>
</html>






<script type="text/javascript">

        function initPage(){
                startConf();
        }
        initPage();

        var startStatus=false;


        //${LANG['bizconf.jsp.common.join_plug.res9']}Plugin${LANG['bizconf.jsp.common.join_plug.res10']}
        function showPlugDiv(){
                <c:if test="${reload==null}">
                installPlug();
                </c:if>
                <c:if test="${reload!=null}">
                loadPlug();
                window.setTimeout(startConf,3000);
                </c:if>
        }



        function clearReload(){
                var domain=getDomain();
                clearCookie("reload",domain);
        }

        //${LANG['bizconf.jsp.common.join_plug.res11']}
        function installPlug(){
                var version=$.browser.version;
        //      alert("version="+version);
                var isShowUp=true;
                if($.browser.msie) {
                        if(parseFloat(version,10)>=9){
                                isShowUp=false;
                        }
                }

                // if($.browser.mozilla) {
                         
                 //}
                //alert("isShowUp="+isShowUp);
                $("#installPlugUpDiv").hide();
                $("#installPlugDownDiv").hide();
                if(isShowUp){
                        $("#installPlugUpDiv").show();
                }else{
                        $("#installPlugDownDiv").show();
                }
                $("#loadPlugDiv").hide();
                $("#downClientDiv").hide();
                resetFrameHeight();
                var domain=getDomain();
                setCookie("reload","1,${cId},${userName},${joinType},${code},${cPass},${rId}",domain);
        }
        //${LANG['bizconf.jsp.common.join_plug.res12']}
        function loadPlug(){
                $("#installPlugUpDiv").hide();
                $("#installPlugDownDiv").hide();
                $("#loadPlugDiv").show();
                $("#downClientDiv").hide();
                resetFrameHeight();
                window.setTimeout(downClient,10000);
                
                
        }

        //${LANG['bizconf.jsp.common.join_plug.res13']}
        function downClient(){
                $("#installPlugUpDiv").hide();
                $("#installPlugDownDiv").hide();
                $("#loadPlugDiv").hide();
                $("#downClientDiv").show();
                resetFrameHeight();
        }

        //${LANG['bizconf.jsp.common.join_page.res33']}URL${LANG['bizconf.jsp.common.join_page.res34']}Client
        var downCount=0;
        function download(){
        	if(downCount>0){
        		 $("#downLoadLink").attr("clickCount",1);
                 alert("${LANG['bizconf.jsp.common.join_plug.res14fix']}");
                 return false;
        	}
        	clearReload();
        	$("#downForm").submit();
        	closeDialogForDelay();
        	downCount++;
        }


        //${LANG['bizconf.jsp.common.join_plug.res16']}
        function startConf(){
                var time1=(new Date()).getTime();
                if($.browser.msie) {
                        startConfForIe();
                }else if($.browser.mozilla) {
                        startConfForFireFox();

                }else{
                	/*
                	var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;
                	if (isChrome) {
                		//var downLoadLinkObj=document.getElementById("downLoadLink");
                		//if(downLoadLinkObj){
                		//	downLoadLinkObj.
                		//}
                		$("#downLoadLink").attr('onclick', '');//.unbind('click').click( function () { changeToOpen(suitId, img); }); 
                		$("#downLoadLink").attr('href', 'JavaScript:download();');

                	}
                	*/
                        downClient();//alert("${LANG['bizconf.jsp.common.join_plug.res17']} IE ${LANG['bizconf.jsp.admin.add_site_user.res7']} FireFox ${LANG['bizconf.jsp.common.join_plug.res18']}");//downClient();//
                }
                var time2=(new Date()).getTime();
                //alert(time2-time1);
        }
        /******************************************************************************
                                IE ${LANG['bizconf.jsp.common.join_plug.res19']}
        *******************************************************************************/
        //${LANG['bizconf.jsp.common.join_page.res19']}IE${LANG['bizconf.jsp.common.join_plug.res20']}
        function startConfForIe(){

                //createObjectForIe();
                var mtgObj=document.getElementById("meetplugin");
                try{
                        if(mtgObj){
                                mtgObj.attachEvent("IePluginDownloadComplete", startSucceedForIe);
                                mtgObj.attachEvent("IePluginDownloadException",startFailedForIe);
                                mtgObj.StartConf("${preParam}");
                        }
                }catch(e){
                        showPlugDiv()
                        return null;
                }

        } 
        //${LANG['bizconf.jsp.common.join_plug.res21']}IE${LANG['bizconf.jsp.common.join_plug.res20']}
        function createObjectForIe(){
                var ieObject=document.createElement("object");
                if(ieObject){
                        ieObject.setAttribute("id","meetplugin");
                        ieObject.setAttribute("width","0px");
                        ieObject.setAttribute("height","0px");
                        ieObject.setAttribute("codebase","${CLIENT_DOWNLOAD_URL}/kip/mcieplgmeet.cab");
                        
                        ieObject.setAttribute("classid","clsid:DE97A9DE-33E1-4862-AFFD-0579C960B31E");
                       // ieObject.setAttribute("classid","clsid:2BB4DED4-1CF2-4b5f-8345-95D73E4197A1");
                        document.body.appendChild(ieObject);
                }
        }

        //IE${LANG['bizconf.jsp.common.join_plug.res22']}
        function startSucceedForIe(){
                //alert("IE${LANG['bizconf.jsp.common.join_plug.res23']}");
                //$("#downClientDiv").hide();
                //$("#startClientDiv").show();
                loadPlug();
                startStatus=true;
                closeDialogForDelay();
        }

        //IE${LANG['bizconf.jsp.common.join_plug.res24']} ${LANG['bizconf.jsp.common.join_plug.res25']}
        function startFailedForIe(){
                showPlugDiv()
        }



        /******************************************************************************
                                        FireFox${LANG['bizconf.jsp.common.join_plug.res19']}
        *******************************************************************************/

        function startConfForFireFox(){
                //createObjectForFireFox();
                var plg=document.getElementById("joinConf");  //${LANG['bizconf.jsp.common.join_plug.res26']}
                if(plg){
                        try{
                                plg.StartConf("${preParam}");  //${LANG['bizconf.jsp.common.join_plug.res27']}preparam${LANG['bizconf.jsp.common.join_plug.res28']}
                        }catch(e){
                                downClient();
                                return null;
                        }
                        var startStaus=plg.LoadPlugin; 
                        if(startStaus){
                                startSucceedForFireFox();
                        }else{
                                startFailedForFireFox();
                        }
                }
        }

        function createObjectForFireFox(){
                var firefox=document.createElement("embed");
                if(firefox){
                        firefox.setAttribute("id","joinConf");
                        firefox.setAttribute("name","joinConf");
                        firefox.setAttribute("width","0");
                        firefox.setAttribute("height","0");
                        firefox.setAttribute("type","application/x-vnd-eSpace Meeting-scriptableplugin");
                        document.body.appendChild(firefox);
                }
        }

        function startSucceedForFireFox(){
                loadPlug();
                startStatus=true;
                closeDialogForDelay();

        }

        function startFailedForFireFox(){
                //showPlugDiv()
                downClient();
        }


        function closeDialogForDelay(){
                window.setTimeout(closeDialog,5000);
        }

        window.onload=  function() {
                var frame = parent.$("#joinMeeting");
                frame.trigger("loaded");
        }

        function closeDialog() {
                var topUrl=top.location+"";
	            if(topUrl.indexOf("?")>-1){
	            	var domain="${domain}";
	            	top.location="http://"+domain;
	            }else{
	            	//${LANG['bizconf.jsp.common.join_plug.res29']} ${LANG['bizconf.jsp.common.join_plug.res30']} ${LANG['bizconf.jsp.common.join_plug.res31']} Top${LANG['bizconf.jsp.common.join_plug.res32']}
	            	 top.location=topUrl;
	            }
                clearReload();
                var dialog = parent.$("#joinMeeting");
                dialog.trigger("closeDialog");
              
        }

        function resetFrameHeight(){
            var pageHeight=document.body.scrollHeight;
            var pageHeight=pageHeight+5;
       //     parent.changeIframeSize("dialogFrame",0,pageHeight);
        }

</script>
