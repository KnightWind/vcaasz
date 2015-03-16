<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.help.res33']}</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<link rel="stylesheet" type="text/css" href="/assets/css/biz.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.index.css" />
<style>
html, body {
	overflow-y: hidden;
}
</style>
<script type="text/javascript">
var LOADING_CONSTANT={
		loadingMessage: "${LANG['website.common.loading.message']}"
};
</script>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.widgets.js"></script>
<script type="text/javascript">
function resizeHeight(){
    var navHeight = $(".nav").height();
    var footerHeight = $(".footer").height();
    var screenHeight = $(window).height()-navHeight-footerHeight;
    var height = $("#mainFrame").contents().find("body").outerHeight();
    $(".helpContaner").height(screenHeight);
//  if(height<screenHeight) {
//      height = screenHeight;
//  }
 $("#mainFrame").height(height);
}
function initSidebar() {
	$(".help-menus").slideBar({
        clickCallback: function(elem) {
        	var href = elem.attr("href");
        	$('#mainFrame').attr("src", href);
        }
    });
}
jQuery(function($) {
	initSidebar();
    $("#mainFrame").load(function() {
        resizeHeight();
    });
});
</script>

</head>
<body>
    <nav class="nav" id="navigator">
		<div class="wrapper">
			<div class="dynamic">
				<span class="welcome"><i class="icon icon-support"></i><a>${LANG['bizconf.jsp.help.res34']}</a></span>
			</div>
<!-- 			<div class="region"> -->
<!-- 				<a class="timezone"><i class="icon icon-zone"></i>北京时间</a> -->
<!-- 				<span class="language"> -->
<!-- 					<select> -->
<!-- 						<option>中文</option> -->
<!-- 					</select> -->
<!-- 				</span> -->
<!-- 			</div> -->
		</div>
	</nav>
	<table class="main-table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="left-td" valign="top" style="background: #EEEEEE;border-right:1px solid #CCCCCC;">
            	<div class="helpContaner">
            	<div class="help-slidebar">
            		<div class="help-menu-group">
            			${LANG['bizconf.jsp.help.res35'] }
            		</div>
			    	<ul class="help-menus">
			            <li>
				            <a href="javascript:;"  class="nav-ul-on" >
				                <span class=""></span>
				                <span class="">${LANG['website.admin.left.menu.user.manage'] }</span>
				            </a>
				            <ul class="help-sub-menus">
					            <li class="active">
						            <a href="/assets/help/admin1-2-1.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.user.manage.manager'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/admin1-2-2.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.user.manage.user'] }</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/admin1-2-3.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.user.manage.org'] }</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					        </ul>
			            </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">${LANG['website.admin.left.menu.config'] }</span>
				            </a>
				            <ul class="help-sub-menus" style="display: none">
					            <li>
						            <a href="/assets/help/admin1-3-1.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.config.email.host'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/admin1-3-2.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.config.email.template'] }</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/admin1-3-3.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['bizconf.jsp.help.res36'] }</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					        </ul>
			            </li>
			            <li class="">
				            <a href="/assets/help/admin1-4.html"  class="" >
				                <span class=""></span>
				                <span class="">${LANG['system.menu.site.infoManage'] }</span>
				            </a>
				        </li>
				        <li class="">
				            <a href="/assets/help/admin1-5.html"  class="" >
				                <span class=""></span>
				                <span class="">${LANG['system.menu.notice.manage'] }</span>
				            </a>
				        </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">${LANG['system.menu.info.list'] }</span>
				            </a>
				            <ul class="help-sub-menus" style="display: none">
					            <li>
						            <a href="/assets/help/admin1-6-1.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['bizconf.jsp.help.res37'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/admin1-6-2.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.find.info.mgrlog'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/admin1-6-3.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.find.info.userlog'] }</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					        </ul>
			            </li>
			            <li class="">
				            <a href="/assets/help/admin1-7.html"  class="" >
				                <span class=""></span>
				                <span class="">${LANG['website.admin.left.menu.bill.info'] }</span>
				            </a>
				        </li>
				        <li class="">
				            <a href="/assets/help/admin1-8.html"  class="" >
				                <span class=""></span>
				                <span class="">${LANG['website.admin.left.menu.prefer.setting'] }</span>
				            </a>
				        </li>
				        <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">${LANG['website.admin.left.menu.user.setting'] }</span>
				            </a>
				            <ul class="help-sub-menus" style="display: none">
					            <li>
						            <a href="/assets/help/admin1-9-1.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.user.setting.base'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/admin1-9-2.html"  class="" >
						                <span class=""></span>
						                <span class="">${LANG['website.admin.left.menu.user.setting.password'] }</span>
						                <span class=""></span>
						            </a>
					            </li>
					        </ul>
			            </li>
			        </ul>
			    </div>
			    </div>
            </td>
            <td id="toggelSlide" valign="top" style="background: #FFFFFF" >
                <div style="width:5px;">&nbsp;</div>
            </td>
            <td class="right-td" valign="top"  width="100%" style="background: #FFFFFF" >
            	<div class="helpContaner">
                	<iframe allowtransparency='true' frameborder="0" width="100%" id="mainFrame" name="mainFrame" scrolling="no" 
                	src="/assets/help/admin1-2-1.html"></iframe>
                </div>
            </td>
        </tr>
	</table>
    <div class="footer">
        <jsp:include page="footer.jsp" /> 
    </div>
</body>
</html>