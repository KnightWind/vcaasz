<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统管理</title>
<!-- CSS -->
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/reset.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/jquery.layout.all-1.2.0/jquery.layout.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/js/jquery.stepy-1.1.0/css/jquery.stepy.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/css/system/index.css" />
<!-- Javascript -->
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery-ui-1.9.2.custom/js/jquery-1.8.3.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery.layout.all-1.2.0/jquery.layout-latest.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/jquery.stepy-1.1.0/js/jquery.stepy.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/json2.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/util.js"></script>
<script type="text/javascript" language="javascript" src="${ctx}/static/js/system.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('body').layout({ 
		north: {
		    enableCursorHotkey: false,
		    closable: false,
		    resizable: false,
		    spacing_open: 0,
		    spacing_closed: 0,
		    size: 80
		},
		south: {
		    enableCursorHotkey: false,
		    closable: false,
		    resizable: false,
		    spacing_open: 0,
		    spacing_closed: 0
		},
		west: {
		    enableCursorHotkey: false,
		    closable: true,
		    resizable: false,
		    spacing_open: 6,
		    spacing_closed: 6
		}
	});
	
    $( "#left-menu" ).accordion({
        collapsible: true
    });
    
});
function editSiteDialog() {
	$("<div/>").editSiteDialog();
}
function editNoticeDialog() {
	$("<div/>").editNoticeDialog();
}
function showURL(url){
    document.getElementById('rFrame').src=url;
} 
</script>
</head>
<body>
<div class="ui-layout-center">
    <iframe width="100%" scrolling="auto" height="100%" frameborder="no" src="site_info.jsp" marginheight="0" marginwidth="0" border="0" name="rFrame" id="rFrame"></iframe>
</div>
<div class="ui-layout-north">
	<div class="top-left-panel">
		<div class="logo-image"></div>
		<span class="site-name">上海会畅数据会议管理平台</span>
	</div>
	<div class="top-right-panel">
		<div class="help-action-container" style="position: absolute; top:5px; right: 5px;width:250px;height:40px">
			<form class="language-form" id="language-form" style="float: left;">
				<select>
					<option>中文版</option>
					<option>English</option>
				</select>
			</form>
	
			<ul class="help-action" style="">
				<li><img src="${ctx}/static/image/geli.jpg" width="2" height="24" /></li>
				<li><img src="${ctx}/static/image/help.jpg" width="16" height="16" style="margin-top: 3px"/></li>
				<li><a title="帮助" href="#">帮助</a></li>
				<li><img src="${ctx}/static/image/geli.jpg" width="2" height="24" /></li>
				<li><img src="${ctx}/static/image/go_out.jpg" width="16" height="16"  style="margin-top: 3px"/></li>
				<li><a title="退出" href="#">退出</a></li>
			</ul>		
		</div>
		<div class="user-name" style="position: absolute; top:35px; right: 35px;width:250px;height:40px">
			<img src="${ctx}/static/image/user.jpg" width="15" height="15" /><span>欢迎您，Adman！</span>
		</div>
	</div>
</div>
<div class="ui-layout-south">
	<jsp:include page="/jsp/copyright.jsp" />
</div>
<div class="ui-layout-west">
	<div id="left-menu">
	    <h3>基本配置</h3>
	    <div>
	    	<a href="javascript:showURL('email_config.jsp')">邮件服务器配置</a><br/>
	    </div>
	    <h3>站点管理</h3>
	    <div>
	    	<a href="javascript:showURL('site_info.jsp')">站点信息</a><br/>
	    </div>
	    <h3>公告管理</h3>
	    <div>
	        <a href="javascript:showURL('notice_info.jsp')">公告信息</a><br/>
	    </div>
	    <h3>信息查询</h3>
	    <div>
	        <a href="javascript:showURL('meeting_info.jsp')">会议信息</a><br/>
	        <a href="javascript:showURL('system_admin_log.jsp')">系统管理员日志</a><br/>
	        <a href="javascript:showURL('company_admin_log.jsp')">企业管理员日志</a><br/>
	    </div>	    
	</div>
</div>
</body>
</html>