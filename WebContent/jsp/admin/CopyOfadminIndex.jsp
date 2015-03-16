<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<TITLE>${LANG['bizconf.jsp.admin.CopyOfadminIndex.res1']}</TITLE>

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jqtransform/jqtransformplugin/jqtransform.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jqtransform/jqtransformplugin/jquery.jqtransform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/system.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/style.css"/>
</HEAD>


<body>
<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res2']}-->
<div id="head">
    <div class="header_left">
    <img class="logo" src="${baseUrlStatic}/images/logo.gif" width="180" height="80" />
    <span class="name">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res1']}</span>
    </div>
    <div class="header_right">
     	<div class="header_right01">
          <form name="form" id="form">
            <select name="jumpMenu" id="jumpMenu" onchange="Javascript:changeLang(this.value)">
<!--               <option>${LANG['bizconf.jsp.admin.CopyOfadminIndex.res3']}</option> -->
<!--               <option>English</option> -->
              <option value="zh-cn">${LANG['website.lang.zh']}</option>
	          <option value="en-us">${LANG['website.lang.en']}</option>
            </select>
          </form>
           <ul class="head_right">
    <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li>
    <li><img class="help" src="${baseUrlStatic}/images/help.jpg" width="16" height="16" /></li>
    <li><a  title="${LANG['bizconf.jsp.admin.CopyOfadminIndex.res4']}" href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res4']}</a></li>
    <li><img class="geli" src="${baseUrlStatic}/images/geli.jpg" width="2" height="24" /></li>
    <li><img class="go_out" src="${baseUrlStatic}/images/go_out.jpg" width="16" height="16" /></li>
    <li><a  title="${LANG['bizconf.jsp.admin.CopyOfadminIndex.res5']}" href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res5']}</a></li>
    </ul> 
          
     	</div>
        <div class="header_right02">
        <div class="aname"><img src="${baseUrlStatic}/images/user.jpg" width="15" height="15" /><span>${LANG['bizconf.jsp.admin.CopyOfadminIndex.res6']}${user.trueName }${LANG['bizconf.jsp.admin.CopyOfadminIndex.res7']}</span></div>
        </div>
        
  </div>
</div>    
<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res8']}-->
<div class="main_left" >
 <ul class="nav">
 		<li><span class="nav_top05"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res9']}</a></span>
          <ul>
            
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res10']}</a></li>
            <li><a href="#">${LANG['bizconf.jsp.admin.arrange_org_user.res1']}</a></li>
          </ul>
        </li>
        <li><span class="nav_top01"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res11']}</a></span>
          <ul>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res12']}</a></li>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res13']}</a></li>
            <li><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res14']}</a></li>
          </ul>
        </li>
        <li><span class="nav_top02"><a  href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res15']}</a></span>
          <ul>
            <li><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res15']}</a></li>
          </ul>
        </li>
        <li><span class="nav_top03"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res16']}</a></span>
          <ul>
            <li><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res17']}</a></li>
          </ul>
        </li>
       
        <li><span class="nav_top04"><a  href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res18']}</a></span>
          <ul>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res19']}</a></li>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res20']}</a></li>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res21']}</a></li>
            <li><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res22']}</a></li>
          </ul>
        </li>
         <li><span class="nav_top06"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res23']}</a></span>
          <ul>
            <li class="b_line"><a href="#">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res23']}</a></li>
            
          </ul>
        </li>
       </ul>    
</div> 
<div class="main_right">
<iframe frameborder="0" width="100%" height="100%" id="mainFrame" name="mainFrame" scrolling="no" src="/admin/profile"></iframe>
</div>
<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res24']}-->
 <div id="copy">
<span class="copy_text" >Copyright © 2003-2012 Shanghai Shrine Telecom Co., Ltd. 2012. All rights reserved.Version:eMeeting V5.0</span>
</div>


</body>
</HTML>
<script type="text/javascript">
	function changeLang(langVal){
		var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/system/";
		window.location.href=jumpUrl;
	}
	function initPage(){
		var useLang="${lang}";
		//$("#lang option[value='"+useLang+"']").
		$("#lang").val(useLang); 
		
	}
	initPage();
//zh-cn,lang_zh.xml
//en-us,lang_en.xml
/////changeLang?lang=&returnURL=
</script>

