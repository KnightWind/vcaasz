<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.help.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>
<link rel="stylesheet" href="${baseUrlStatic}/css/user/zTreeStyle/zTreeStyle.css?ver=${version}" type="text/css">
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.ztree.core-3.5.min.js"></script>
<SCRIPT type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"${LANG['bizconf.jsp.help.res2']}", open:true, iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png", url:"/static/html/help/help1.html", target:"mainFrame"},
			{ id:11, pId:1, name:"${LANG['bizconf.jsp.help.res3']}", open:true, iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:111, pId:11, name:"${LANG['bizconf.jsp.help.res4']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help2.html", target:"mainFrame"},
			{ id:112, pId:11, name:"${LANG['bizconf.jsp.help.res5']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1121, pId:112, name:"${LANG['bizconf.jsp.help.res6']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help4.html", target:"mainFrame"},
			{ id:1122, pId:112, name:"${LANG['bizconf.jsp.conf_list_index.res39']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help5.html", target:"mainFrame"},
			{ id:1123, pId:112, name:"${LANG['bizconf.jsp.help.res7']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help6.html", target:"mainFrame"},
			{ id:1124, pId:112, name:"${LANG['bizconf.jsp.help.res8']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help7.html", target:"mainFrame"},
			{ id:113, pId:11, name:"${LANG['bizconf.jsp.help.res9']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help8.html", target:"mainFrame"},
			{ id:114, pId:11, name:"${LANG['bizconf.jsp.help.res10']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1141, pId:114, name:"${LANG['bizconf.jsp.contacts_main.res1']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help9.html", target:"mainFrame"},
			{ id:1142, pId:114, name:"${LANG['bizconf.jsp.contacts_main.res2']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help10.html", target:"mainFrame"},
			{ id:115, pId:11, name:"${LANG['bizconf.jsp.help.res11']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help11.html", target:"mainFrame"},
			{ id:12, pId:1, name:"${LANG['bizconf.jsp.help.res12']}", open:true, iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:121, pId:12, name:"${LANG['bizconf.jsp.conf_list_index.res48']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1211, pId:121, name:"${LANG['bizconf.jsp.help.res13']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help12.html", target:"mainFrame"},
			{ id:1212, pId:121, name:"${LANG['bizconf.jsp.help.res14']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help13.html", target:"mainFrame"},
			{ id:122, pId:12, name:"${LANG['bizconf.jsp.help.res15']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help14.html", target:"mainFrame"},
			{ id:123, pId:12, name:"${LANG['bizconf.jsp.help.res16']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1231, pId:123, name:"${LANG['bizconf.jsp.help.res17']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help15.html", target:"mainFrame"},
			{ id:1232, pId:123, name:"${LANG['bizconf.jsp.help.res18']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help16.html", target:"mainFrame"},
			{ id:1233, pId:123, name:"${LANG['bizconf.jsp.conf_default_setup.res44']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help17.html", target:"mainFrame"},
			{ id:124, pId:12, name:"${LANG['bizconf.jsp.help.res19']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1241, pId:124, name:"${LANG['bizconf.jsp.help.res20']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help18.html", target:"mainFrame"},
			{ id:125, pId:12, name:"${LANG['bizconf.jsp.help.res21']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1251, pId:125, name:"${LANG['bizconf.jsp.help.res22']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help19.html", target:"mainFrame"},
			{ id:126, pId:12, name:"${LANG['bizconf.jsp.help.res23']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1261, pId:126, name:"${LANG['bizconf.jsp.help.res24']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help20.html", target:"mainFrame"},
			{ id:1262, pId:126, name:"${LANG['bizconf.jsp.help.res25']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help21.html", target:"mainFrame"},
			{ id:1263, pId:126, name:"${LANG['bizconf.jsp.help.res26']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help22.html", target:"mainFrame"},
			{ id:1264, pId:126, name:"${LANG['bizconf.jsp.conf_default_setup.res41']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help23.html", target:"mainFrame"},
			{ id:1265, pId:126, name:"${LANG['bizconf.jsp.help.res27']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help24.html", target:"mainFrame"},
			{ id:127, pId:12, name:"${LANG['bizconf.jsp.help.res28']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1271, pId:127, name:"${LANG['bizconf.jsp.help.res29']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help25.html", target:"mainFrame"},
			{ id:128, pId:12, name:"${LANG['bizconf.jsp.help.res30']}", iconOpen:"/static/images/help_ico01.png", iconClose:"/static/images/help_ico01.png"},
			{ id:1281, pId:128, name:"${LANG['bizconf.jsp.help.res31']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help26.html", target:"mainFrame"},
			{ id:1281, pId:128, name:"${LANG['bizconf.jsp.help.res32']}", icon:"/static/images/help_ico02.png", url:"/static/html/help/help27.html", target:"mainFrame"}
		];

		function onClick(e,treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode);
		}

		$(document).ready(function(){
			if ($.browser.msie && $.browser.version<7) {//for ie6 png
				$(".png").each(function() {
					$(this).attr('width',$(this).width());
					$(this).attr('height',$(this).height());
					var imgSrc = $(this).attr('src');
					$(this).get(0).runtimeStyle.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader' + '(src=\'' + imgSrc + '\', sizingMethod=\'scale\');';
					$(this).attr('src', '/static/images/blank.gif');
				});		
			}
			$("#mainFrame").load(function(){
				resizeHeight();  
		    });
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function resizeHeight() {
			var height = $(window).height()-140;
			if(height<300){
				height = 300;
			}
			$(".main_right").height(height);
			$("#mainFrame").height(height);
			$(".help_left").height(height);
		}

	</SCRIPT>
	<style type="text/css">
	html{
		overflow: hidden;
	}
	body {
		overflow: hidden;
	}
		.ztree li a{
			overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		    width: 120px;
		    word-break: keep-all;
		    padding: 2px 3px 2px 1px;
		}
	</style>	
</head>
<body>
<!--${LANG['bizconf.jsp.conf_invite_recv.res4']}-->
<jsp:include page="header.jsp" />
<div id="head_bar">
  <div class="help_subnav"><img class="png" src="/static/images/help_ico07.png" width="18" height="19" align="absmiddle" /><span style="position: relative;top: 8px;left: 10px;">${LANG['bizconf.jsp.help.res33']}</span></div>
</div>
<div class="help_left" style="min-height: 550px;overflow-y: auto;padding-top: 10px;">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<div class="main_right" style="height: 500px;">
	<iframe width="100%" height="500" frameborder="0" id="mainFrame" name="mainFrame" scrolling="auto" src="/static/html/help/help1.html"></iframe>
</div>
<!--${LANG['bizconf.jsp.conf_invite_recv.res15']}-->
<div id="copy_close_downhelp">
<jsp:include page="footer.jsp" />
</div>
</body>
</html>
