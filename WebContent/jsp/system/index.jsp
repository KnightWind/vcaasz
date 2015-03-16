<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<TITLE>${LANG['bizconf.jsp.system.header.res1']}</TITLE>

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<style type="text/css">
body {
	_width:expression((documentElement.clientWidth < 1002) ? "1002px" : "auto");
}

#mainFrame {
	width: 100%;
}

*html #mainFrame {
	width: 98%;
}
</style>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<!--[if lte IE 6]>  
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></SCRIPT>  
<![endif]-->
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		var needResetPass = "${needResetPass}";
		if(needResetPass != null && needResetPass =="true"){
			resetPass();
		}
	});

	$(document).ready(function() {
		$("#langForm").find("select").not(".skipThese").uniform();
		$("#langForm select").change(function () {
			var lang = $(this).val();
			changeLang(lang);
		});
		$('body').bind(EVENT_CREATE, function(event, result, type) {
			if(type==VIEW_TYPE.sysAdminUser) {
				successDialog(result.message);
				showURL("/system/sysUser/list");
			} else if(type==VIEW_TYPE.site){
				siteCreateSuccess(result);
				showURL("/system/site/list");
			} else if(type==VIEW_TYPE.notice) {
				successDialog(result.message);
				showURL("/system/notice/list");
			}
		});
		$('body').bind(EVENT_UPDATE, function(event, result, type) {
			if (type==VIEW_TYPE.sysAdminUser) {
				successDialog(result.message);
				showURL("/system/sysUser/list");
			} else if(type==VIEW_TYPE.site){
				siteUpdateSuccess(result);
				showURL("/system/site/list");
			} else if (type==VIEW_TYPE.notice) {
				successDialog(result.message);
				showURL("/system/notice/list");
			}
		});
		$('body').bind(EVENT_DELETE, function(event, result) {
			//delete success
		});

		$('body').bind(EVENT_ERROR, function(event, result, type) {
			var message = result.message;
			errorDialog(message);
		});
		$('body').bind(EVENT_SEND_EMAIL, function(event, siteInfo, userInfo) {
    		app.sendSiteEmail(siteInfo, userInfo);
		});
		//left menu
		$('.nav span').click(function() {
			var selectText = $(this).text();
			var ul = $(this).next();
			if (ul.is(":visible")) {
				$(this).attr("style", "background:url('/static/images/nav_bg_change.jpg') no-repeat scroll 0 0 transparent");
				ul.slideUp();
			} else {
				$(this).attr("style", "background:url('/static/images/nav_li_bg.jpg') no-repeat scroll 0 0 transparent");
				ul.slideDown();
			}
		});
		//left menu selected
		$('.sub_nav a').click(function() {
			$('.sub_nav').find("a").removeClass("li_actived");
			$(this).addClass("li_actived");
		});
		//left menu hover
		$('.sub_nav a').hover(function() {
			$(this).addClass("li_ahoverd");
		}, function() {
			$(this).removeClass("li_ahoverd");
		});
		
		$("#mainFrame").load(function(){
			resizeHeight();  
	    });
	});
	
	//refresh Main Iframe Content
	function showURL(url) {
		url+="?t="+ Math.random();
		$('#mainFrame').attr("src", url);
	}

	//resize index body height when search div Collapsing 
	function resizeHeight(subHigth) {
		var height = $("#mainFrame").contents().find("body").height()+50;
		if(height<710){
			height = 710;
		}
		if(!subHigth){
			$("#mainFrame").height(height);  
			$(".main_container").height(height);
			$(".main_left").height(height);
		}else{
			$("#mainFrame").height(subHigth);  
			$(".main_container").height(subHigth);
			$(".main_left").height(subHigth);
		}
	}
	
	function createOrUpdateHost(siteId,userId) {
		if(userId) {
			$("<div id=\"hostDiv\"/>").showDialog({
				"title": "${LANG['bizconf.jsp.system.index.res1']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/lic/goEditHost?siteId="+siteId+"&userId="+userId,
				"type" : VIEW_TYPE.hostUser,
				"action" : ACTION.update,
				"width" : 510,
				"height" : 480
			});
		} else {
			$("<div id=\"hostDiv\"/>").showDialog({
				"title" : "${LANG['bizconf.jsp.system.index.res2']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/lic/goEditHost?siteId="+siteId+"&userId="+userId,
				"type" : VIEW_TYPE.hostUser,
				"action" : ACTION.create,
				"width" : 510,
				"height" : 480
			});
		}		
	}
	
	function maCreateOrUpdateHost(siteId,userId) {
		if(userId) {
			$("<div id=\"hostDiv\"/>").showDialog({
				"title": "${LANG['bizconf.jsp.system.index.res1']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/lic/goMaEditHost?siteId="+siteId+"&userId="+userId,
				"type" : VIEW_TYPE.hostUser,
				"action" : ACTION.update,
				"width" : 510,
				"height" : 480
			});
		} else {
			$("<div id=\"hostDiv\"/>").showDialog({
				"title" : "${LANG['bizconf.jsp.system.index.res2']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/lic/goMaEditHost?siteId="+siteId+"&userId="+userId,
				"type" : VIEW_TYPE.hostUser,
				"action" : ACTION.create,
				"width" : 510,
				"height" : 480
			});
		}		
	}
	
	function countManager(id) {
		$("<div id=\"countManagerDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.hostlist.res3']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/lic/list?siteId="+id,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 690,
			"height" : 380
		});			
	}
	//name_host${LANG['bizconf.jsp.system.index.res3']}
	function licenseManage(userId,siteId){
		$("<div id=\"countManagerDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.hostlist.res3']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/lic/list?userId="+userId+"&siteId="+siteId,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 690,
			"height" : 380
		});			
	}
	function hostManger(id) {
		$("<div id=\"hostManagerDiv\"/>").showDialog({
			"title" : "host${LANG['bizconf.jsp.system.index.res4']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/lic/listHost?siteId="+id,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 550,
			"height" : 380
		});			
	}
	
	function msManger(id) {
		$("<div id=\"msDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.index.msinfo1']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/ms/toEditeMs?msId="+id,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 480,
			"height" : 300
		});			
	}
	
	function msGroupManger(id) {
		$("<div id=\"msgroupDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.index.msinfo2']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/ms/toEditeMsGroup?groupId="+id,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 480,
			"height" : 220
		});			
	}
	
	function showMSSelect(id,seletedIds) {
		$("<div id=\"showMSDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.index.msinfo3']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/ms/showAllMsSelect?groupId="+id+"&selectedIds="+seletedIds,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 600,
			"height" : 380
		});			
	}
	
	function showUsingSites(id) {
		$("<div id=\"showSitesDiv\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.system.index.msinfo4']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/ms/showMGSites?groupId="+id,
			"type" : VIEW_TYPE.hostUser,
			"action" : ACTION.create,
			"width" : 550,
			"height" : 380
		});			
	}
	
	//create or update system user
	function createOrUpdateUser(id) {
		if(id) {
			$("<div id=\"userDiv\"/>").showDialog({
				"title": "${LANG['system.sysUser.list.changeUser']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/sysUser/update/" + id,
				"type" : VIEW_TYPE.sysAdminUser,
				"action" : ACTION.update,
				"width" : 450,
				"height" : 430
			});
		} else {
			$("<div id=\"userDiv\"/>").showDialog({
				"title" : "${LANG['system.sysUser.list.addUser']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/sysUser/new",
				"type" : VIEW_TYPE.sysAdminUser,
				"action" : ACTION.create,
				"width" : 450,
				"height" : 430
			});
		}
	}
	//create or update notice
	function createOrUpdateNotice(id) {
		if (id) {
			$("<div id=\"noticeDiv\"/>").showDialog({
				"title" : "${LANG['system.notice.list.Update']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/notice/update/" + id,
				"type" : VIEW_TYPE.notice,
				"action" : ACTION.update,
				"width" : 680,
				"height" : 400
			});
		} else {
			$("<div id=\"noticeDiv\"/>").showDialog({
				"title" : "${LANG['system.notice.list.Create']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/notice/create",
				"type" : VIEW_TYPE.notice,
				"action" : ACTION.create,
				"width" : 680,
				"height" : 400
			});
		}
	}

	//create or update site
	function createOrUpdateSite(id) {
		if (id) {
			$("<div id=\"siteDiv\"/>").showDialog({
				"title" : "${LANG['system.site.list.update']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/site/update/" + id,
				"type" : VIEW_TYPE.site,
				"action" : ACTION.update,
				"width" : 500,
				"height" : 480
			});
		} else {
			$("<div id=\"siteDiv\"/>").showDialog({
				"title" : "${LANG['system.site.list.create']}",
				"dialogClass" : "ui-dialog-smile",
				"url" : "/system/site/new",
				"type" : VIEW_TYPE.site,
				"action" : ACTION.create,
				"width" : 500,
				"height" : 480
			});
		}
	}
	//view site info
	function viewSite(id) {
		$("<div id=\"siteDiv\"/>").showDialog({
			"title" : "${LANG['system.site.meaasge.lookup']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/site/view/" + id,
			"type" : VIEW_TYPE.site,
			"action" : ACTION.view,
			"width" : 420,
			"height" : 460
		});
	}
	//view userhost info
	function viewHost(id) {
		$("<div id=\"siteDiv\"/>").showDialog({
			"title" : "${LANG['system.user.meaasge.lookup']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/conf/viewHost?id="+id,
			"type" : VIEW_TYPE.site,
			"action" : ACTION.view,
			"width" : 420,
			"height" : 460
		});
	}
	//view eventLog optDesc
	function viewSysSiteDetails(id) {
		$("<div id=\"optDescDiv\"/>").showDialog({
			"title" : "查看详情",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/logs/viewSysSiteDetails/" + id,
			"type" : VIEW_TYPE.site,
			"action" : ACTION.view,
			"width" : 700,
			"height" : 534
		});
	}
	//view eventLog optDesc
	function viewSysAdminDetails(id) {
		$("<div id=\"optDescDiv\"/>").showDialog({
			"title" : "查看详情",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/logs/viewSysAdminDetails/" + id,
			"type" : VIEW_TYPE.site,
			"action" : ACTION.view,
			"width" : 700,
			"height" : 534
		});
	}

	//view notice info
	function viewNotice(id) {
		$("<div id=\"previewNoticeDiv\"/>").showDialog({
			"url" : "/system/notice/view/" + id,
			"dialogClass" : "ui-dialog-preview",
			"actions" : ["${LANG['system.close']}"],
			"width" : 677,
			"height" : 564
		});
	}
	//preview notice info
	function previewNotice(data) {
		$("<div id=\"previewNoticeDiv\"/>").showDialog({
			"url" : "/system/notice/preview",
			"dialogClass" : "ui-dialog-preview",
			"data" : data,
			"actions" : ["${LANG['system.close']}"],
			"width" : 677,
			"height" : 564
		});
	}
	function siteCreateSuccess(data) {
		$("<div id=\"siteSuccessDiv\"/>").showDialog({
			"title" : "${LANG['system.site.meaasge.create.succeed']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/site/siteCreateSuccess/" + data.siteBase[0].id,
			"width" : 480,
			"height" : 350			
		});
	}
	function siteUpdateSuccess(data) {
		$("<div id=\"siteSuccessDiv\"/>").showDialog({
			"title" : "${LANG['system.site.meaasge.update.succeed']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/site/siteUpdateSuccess/" + data.siteBase[0].id,
			"width" : 480,
			"height" : 350
		});
	}

	function errorDialog(message) {
		$("<div/>").errorDialog({
			"title": "${LANG['system.tip']}",
			"dialogClass" : "ui-dialog-smile",
			"message" : message,
			"actions": ["${LANG['system.close']}"]
		});
	}
	function successDialog(message) {
		$("<div/>").successDialog({
			"title": "${LANG['system.tip']}",
			"dialogClass" : "ui-dialog-smile",
			"message" : message,
			"actions": ["${LANG['system.close']}"]
		});
	}
	function confirmDialog(message, callback) {
		$("<div/>").confirmDialog({
			"title": "${LANG['system.tip']}",
			"dialogClass" : "ui-dialog-smile",
			"message" : message,
			"actions": ["${LANG['system.ok']}", "${LANG['system.cancel']}"],
			"callback" : callback
		});
	}
	
	function logout() {
		$("<div/>").confirmDialog({
			"title": "${LANG['bizconf.jsp.admin.index.res13']}",
			"dialogClass" : "ui-dialog-smile",
			"message" : "${LANG['bizconf.jsp.admin.index.res14']}?",
			"type": "confirm",
			"actions": ["${LANG['system.ok']}", "${LANG['system.cancel']}"],
			"callback" : function() {
				window.location.href="/system/logout";
			}
		});
	}	
	
	/*
	 * ${LANG['bizconf.jsp.system.index.res5']}
	 */
	function resetPass(){
		$("<div id=\"resetPass\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.admin.index.res7']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/system/resetPass",
			"type" : VIEW_TYPE.notice,
			"action" : ACTION.view,
			"width" : 474,
			"height" : 220,
			"hideClose": true
		});
		
	}
	
	//${LANG['bizconf.jsp.system.index.res6']}
	function showConflogs(id) {
		$("<div id=\"logview\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.admin.index.res15']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "<%=request.getContextPath()%>/user/conflog/loglist?issys=true&confId="+id,
			"type" : VIEW_TYPE.group,
			"action" : ACTION.create,
			"width" : 804,
			"height" : 534
		});
	}
	
	function showTelDetail(id,year,month) {
		$("<div id=\"billingView\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.common.bill_detaillist.res1']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/common/billing/sysShowTelDetail?id="+id+"&year="+year+"&month="+month,
			"type" : VIEW_TYPE.billing,
			"action" : ACTION.create,
			"width" : 624,
			"height" : 500
		});
	}
	
	function showDataFeeDetail(id,year,month) {
		$("<div id=\"dataFeeView\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.common.bill_detaillist.res1']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/common/billing/sysShowDataDetail?id="+id+"&year="+year+"&month="+month,
			"type" : VIEW_TYPE.billing,
			"action" : ACTION.create,
			"width" : 624,
			"height" : 487
		});
	}
	
	
	//查看会议详情
	function viewConf(id) {
		$("<div id=\"viewMeeting\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.index.res14']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/user/conf/view4sys/" + id,
			"type" : VIEW_TYPE.bookMeeting,
			"action" : ACTION.view,
			"width" : 684,
			"height" : 409
		});
	}
	
	function editInventContact(confId) {
		$("<div id=\"eidtInventContact\"/>").showDialog({
			"title" : "${LANG['bizconf.jsp.conf_list_main.res2']}",
			"dialogClass" : "ui-dialog-smile",
			"url" : "/user/inviteUser/sysview?confId="+confId,
			"type" : VIEW_TYPE.attendee,
			"action" : ACTION.create,
			"width" : 624,
			"height" : 504
		});	
	}
</script>

 
</HEAD>
<BODY style="min-width:1002px;">
<!--${LANG['bizconf.jsp.admin.CopyOfadminIndex.res2']}-->
<jsp:include page="header.jsp" />

<div class="main_left" >
 <ul class="nav">
 		<c:if test="${isSuperSystemAdmin}">
	 		<li><span class="nav_top05"><a href="#"><b>${LANG['system.menu.config.user']}</b></a></span>
	          <ul class="sub_nav">
	            <li><a class="li_alink" href="/system/sysUser/list" target="mainFrame">${LANG['system.menu.config.sysUser']}</a></li>
	          </ul>
	        </li>
        <li><span class="nav_top01"><a href="#"><b>${LANG['system.menu.config']}</b></a></span>
          <ul class="sub_nav">
            <li class="b_line"><a class="li_alink" href="/system/email/showhost" target="mainFrame">${LANG['system.menu.config.host']}</a></li>
            <li class="b_line"><a class="li_alink" href="/system/email/goTemplate" target="mainFrame">${LANG['system.menu.config.template']}</a></li>
            <li class="b_line"><a class="li_alink" href="/system/ms/showmses" target="mainFrame">${LANG['bizconf.jsp.system.index.itemms']}</a></li>
            <li><a class="li_alink" href="/system/ms/showmsgroups" target="mainFrame">${LANG['bizconf.jsp.system.index.itemmsgroup']}</a></li>
          </ul>
        </li>
        </c:if>
        <li><span class="nav_top02"><a class="" href="#"><b>${LANG['system.menu.site.manage']}</b></a></span>
          <ul class="sub_nav">
            <li><a class="li_alink li_actived" href="/system/site/list" target="mainFrame">${LANG['system.menu.site.list']}</a></li>
          </ul>
        </li>
        <c:if test="${isSuperSystemAdmin}">
        <li><span class="nav_top03"><a class="" href="#"><b>${LANG['system.menu.notice.manage']}</b></a></span>
          <ul class="sub_nav">
            <li><a class="li_alink" href="/system/notice/list" target="mainFrame">${LANG['system.menu.notice.list']}</a></li>
          </ul>
        </li>
        </c:if>
       
        <li><span class="nav_top04"><a class="" href="#"><b>${LANG['system.menu.info.list']}</b></a></span>
          <ul class="sub_nav">
          <cc:confList var="CONF_STATUS_OPENING"></cc:confList>
            <li class="b_line"><a class="li_alink" href="/system/conf/listWithCondition?confStatus=${CONF_STATUS_OPENING }" target="mainFrame">${LANG['system.menu.info.meeting']}</a></li>
            <c:if test="${user.sysType gt 6}">
	            <li class="b_line"><a class="li_alink" href="/system/logs/syslist" target="mainFrame">${LANG['system.menu.info.log.sysManager']}</a></li>
	            <li class="b_line"><a class="li_alink" href="/system/logs/siteloglist" target="mainFrame">${LANG['system.menu.info.log.userManager']}</a></li>
	            <li><a class="li_alink" href="/system/loginLog/getLogList" target="mainFrame">${LANG['bizconf.jsp.system.user.login.log']}</a></li>
         	</c:if>
          </ul>
        </li>
        <li><span class="nav_top04"><a class="" href="#">${LANG['bizconf.jsp.admin.index.res20']}</a></span>
          <ul class="sub_nav">
            <li class="b_line"><a class="li_alink" href="/common/billing/goSysBilling" target="mainFrame">${LANG['bizconf.jsp.admin.CopyOfadminIndex.res22']}</a></li>
          </ul>
        </li>
        
         <li><span class="nav_top06"><a class="" href="#"><b>${LANG['system.menu.info.user.manage']}</b></a></span>
          <ul class="sub_nav">
            <li class="b_line"><a class="li_alink" href="/system/profile" target="mainFrame">${LANG['system.menu.info.user.manage']}</a></li>
            
          </ul>
        </li>
    </ul>    
</div>
<div class="main_container" style="margin-left: 225px;">
<iframe frameborder="0" height="100%" id="mainFrame" name="mainFrame" scrolling="no" src="/system/site/list"></iframe>
</div>

<!--${LANG['bizconf.jsp.system.index.res7']}-->
<jsp:include page="footer.jsp" />
</BODY>
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

