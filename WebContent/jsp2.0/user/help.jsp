<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助文档</title>
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
//     if(height<screenHeight) {
//         height = screenHeight;
//     }
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
				<span class="welcome"><i class="icon icon-support"></i><a>帮助中心</a></span>
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
            			会议管理系统
            		</div>
			    	<ul class="help-menus">
			    		<c:if test="${empty userBase }">
			    		<ul class="help-sub-menus"> 
							<li class="active">
						            <a href="/assets/help/1-2-1.html"  class="" >
						                <span class=""></span>
						                <span class="">会议管理界面</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/1-2-2.html"  class="" >
						                <span class=""></span>
						                <span class="">公开会议</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/1-3-4.html"  class="" >
						                <span class=""></span>
						                <span class="">会议ID加入</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/1-3-6.html"  class="" >
						                <span class=""></span>
						                <span class="">加入会议</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/1-2-3.html"  class="" >
						                <span class=""></span>
						                <span class="">公告信息</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/1-2-4.html"  class="noborder" >
						                <span class=""></span>
						                <span class="">支持</span>
						                <span class=""></span>
						            </a>
						            
					            </li>			    		
			    		</ul>
			    		</c:if>
			    		
			    		<c:if test="${!empty userBase && userBase.userRole eq 1}">
			            <ul class="help-sub-menus">
				            <li>
					            <a href="/assets/help/1-3-1.html"  class="" >
					                <span class=""></span>
					                <span class="">主持人登录后界面</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-2.html"  class="" >
					                <span class=""></span>
					                <span class="">即时会议</span>
					                <span class=""></span>
					            </a>
					            
				            </li>
				            <li>
					            <a href="/assets/help/1-3-3.html"  class="" >
					                <span class=""></span>
					                <span class="">预约会议</span>
					                <span class=""></span>
					            </a>
					            
				            </li>
				            <li>
					            <a href="/assets/help/1-3-4.html"  class="" >
					                <span class=""></span>
					                <span class="">会议ID加入</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-5.html"  class="" >
					                <span class=""></span>
					                <span class="">邀请与会者</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-6.html"  class="" >
					                <span class=""></span>
					                <span class="">加入会议</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-7.html"  class="" >
					                <span class=""></span>
					                <span class="">我的会议</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-8.html"  class="" >
					                <span class=""></span>
					                <span class="">通讯录</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-9.html"  class="" >
					                <span class=""></span>
					                <span class="">公告信息</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-10.html"  class="" >
					                <span class=""></span>
					                <span class="">账单查询</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-11.html"  class="" >
					                <span class=""></span>
					                <span class="">会议报告</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-12.html"  class="" >
					                <span class=""></span>
					                <span class="">会议缺省设置</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-13.html"  class="" >
					                <span class=""></span>
					                <span class="">偏好设置</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-14.html"  class="" >
					                <span class=""></span>
					                <span class="">个人信息管理</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-3-15.html"  class="" >
					                <span class=""></span>
					                <span class="">下载中心</span>
					                <span class=""></span>
					            </a>
				            </li>
			            </ul>
			            </c:if>
			            <c:if test="${!empty userBase && userBase.userRole eq 2}">
			            <ul class="help-sub-menus">
				            <li>
					            <a href="/assets/help/1-4-1.html"  class="" >
					                <span class=""></span>
					                <span class="">用户登录后界面</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-2.html"  class="" >
					                <span class=""></span>
					                <span class="">会议ID加入</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-3.html"  class="" >
					                <span class=""></span>
					                <span class="">加入会议</span>
					                <span class=""></span>
					            </a>
					            
				            </li>
				            <li>
					            <a href="/assets/help/1-4-4.html"  class="" >
					                <span class=""></span>
					                <span class="">我的会议</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-5.html"  class="" >
					                <span class=""></span>
					                <span class="">公告信息</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-6.html"  class="" >
					                <span class=""></span>
					                <span class="">账单查询</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-7.html"  class="" >
					                <span class=""></span>
					                <span class="">会议报告</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-8.html"  class="" >
					                <span class=""></span>
					                <span class="">偏好设置</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-9.html"  class="" >
					                <span class=""></span>
					                <span class="">个人信息管理</span>
					                <span class=""></span>
					            </a>
				            </li>
				            <li>
					            <a href="/assets/help/1-4-10.html"  class="" >
					                <span class=""></span>
					                <span class="">下载中心</span>
					                <span class=""></span>
					            </a>
				            </li>
			            </ul>
			            </c:if>
			        </ul>
			        <div class="help-menu-group menu-client">
            			会议客户端
            		</div>
            		<ul class="help-menus">
			            <li class="">
				            <a href="/assets/help/C-1-1.html"  class="" >
				                <span class=""></span>
				                <span class="">了解会议窗口</span>
				                <span class=""></span>
				            </a>
			            </li>
			            <li>
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">管理会议</span>
				            </a>
				            <ul class="help-sub-menus" style="display: none;">
					            <li>
						            <a href="/assets/help/C-1-2-1.html"  class="" >
						                <span class=""></span>
						                <span class="">设置主讲人</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-2.html"  class="" >
						                <span class=""></span>
						                <span class="">邀请用户入会</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-3.html"  class="" >
						                <span class=""></span>
						                <span class="">移除与会者</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-4.html"  class="" >
						                <span class=""></span>
						                <span class="">设置与会者权限</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-5.html"  class="" >
						                <span class=""></span>
						                <span class="">传递主持人角色</span>
						                <span class=""></span>
						            </a>
						            
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-6.html"  class="" >
						                <span class=""></span>
						                <span class="">放下手</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-7.html"  class="" >
						                <span class=""></span>
						                <span class="">设置提示信息</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-8.html"  class="" >
						                <span class=""></span>
						                <span class="">修改与会者名称</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-9.html"  class="" >
						                <span class=""></span>
						                <span class="">发起语音通话</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-10.html"  class="" >
						                <span class=""></span>
						                <span class="">搜索与会者</span>
						                <span class=""></span>
						            </a>
					            </li>
					            <li>
						            <a href="/assets/help/C-1-2-11.html"  class="" >
						                <span class=""></span>
						                <span class="">语音转接</span>
						                <span class=""></span>
						            </a>
					            </li>
					        </ul>
			            </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">共享会议内容</span>
				                <span class=""></span>
				            </a>
				            <ul class="help-sub-menus" style="display: none;">
				            	<li class="">
						            <a href="/assets/help/C-1-3-1.html">
						                <span class=""></span>
						                <span class="">共享文档</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-3-2.html">
						                <span class=""></span>
						                <span class="">共享桌面</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-3-3.html">
						                <span class=""></span>
						                <span class="">共享白板</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-3-4.html">
						                <span class=""></span>
						                <span class="">共享媒体</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            </ul>
			            </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">会议工具</span>
				                <span class=""></span>
				            </a>
				            <ul class="help-sub-menus" style="display: none;">
				            	<li class="">
						            <a href="/assets/help/C-1-4-1.html" >
						                <span class=""></span>
						                <span class="">问卷调查</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-4-2.html" >
						                <span class=""></span>
						                <span class="">笔记</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-4-3.html" >
						                <span class=""></span>
						                <span class="">公告</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-4-4.html" >
						                <span class=""></span>
						                <span class="">文件传输</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-4-5.html" >
						                <span class=""></span>
						                <span class="">会议录制</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            </ul>
			            </li>
			            <li class="">
				            <a href="/assets/help/C-1-5.html">
				                <span class=""></span>
				                <span class="">设置界面布局</span>
				                <span class=""></span>
				            </a>
			            </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off" >
				                <span class=""></span>
				                <span class="">音频设置</span>
				                <span class=""></span>
				            </a>
				            <ul class="help-sub-menus" style="display: none;">
				            	<li class="">
						            <a href="/assets/help/C-1-6-1.html" >
						                <span class=""></span>
						                <span class="">设置音频</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-6-2.html" >
						                <span class=""></span>
						                <span class="">会议中静音</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            </ul>
			            </li>
			            <li class="">
				            <a href="javascript:;"  class="nav-ul-off">
				                <span class=""></span>
				                <span class="">视频设置</span>
				                <span class=""></span>
				            </a>
				            <ul class="help-sub-menus" style="display: none;">
				            	<li class="">
						            <a href="/assets/help/C-1-7-1.html" >
						                <span class=""></span>
						                <span class="">设置视频</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-7-2.html" >
						                <span class=""></span>
						                <span class="">查看视频</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            	<li class="">
						            <a href="/assets/help/C-1-7-3.html" >
						                <span class=""></span>
						                <span class="">设置双屏显示</span>
						                <span class=""></span>
						            </a>
				            	</li>
				            </ul>
			            </li>
			            <li class="">
				            <a href="/assets/help/C-1-8.html">
				                <span class=""></span>
				                <span class="">结束和退出会议</span>
				                <span class=""></span>
				            </a>
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
                	src="/assets/help/1-2-1.html"></iframe>
            	</div>
            </td>
        </tr>
	</table>
    <div class="footer">
        <jsp:include page="footer.jsp" /> 
    </div>
</body>
</html>