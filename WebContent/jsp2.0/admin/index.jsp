<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${siteBase.siteName}--${LANG['bizconf.jsp.admin.index.res1']}</title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/assets/css/biz.adm.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.adm.index.css" />

<script type="text/javascript">
	var noticeId = "${sysNotice.id}";
	var loginUser = "${user.id}"; 
	var needResetPass = "${needResetPass}";
	
	var LOADING_CONSTANT={
			viewConfInfo:"${LANG['website.user.index.js.message.title.view.confinfo']}",//查看会议详情
			addUser:"${LANG['website.admin.user.list.page.btn.add']}",//添加用户
			updateUser:"${LANG['website.admin.user.list.page.btn.update']}",//修改用户
			remindCommon:"${LANG['website.user.index.js.message.title.remind.common']}",//提醒
			confirm:"${LANG['website.common.option.confirm']}",//确定
			cancel:"${LANG['website.common.option.cancel']}",//取消
			noticeAlert:"${LANG['website.user.index.js.message.title.system.notice']}",//公告通知
			loadingMessage: "${LANG['website.common.loading.message']}"
	};
	
	var INDEX_CONSTANT = {
			changeSkin:"${LANG['website.user.index.js.message.title.change.skin']}",//切换主题
			userLogin:"${LANG['website.user.index.js.message.title.user.login']}",//用户登录
			joinedInfo:"${LANG['website.user.index.js.message.title.joined.info']}",//参会详情
			viewDetail:"${LANG['bizconf.jsp.conf_list_index.res50']}",//查看详情
			viewConfInfo:"${LANG['website.user.index.js.message.title.view.confinfo']}",//查看会议详情
			modifyAdmin:"${LANG['siteAdmin.eventlog.type.602']}",//修改企业管理员
			addAdmin:"${LANG['website.admin.siteadmin.list.addadmin']}",//add企业管理员
			updateUser:"${LANG['system.admin.index.updateuser']}",//修改用户
			addDept:"${LANG['website.admin.org.list.page.add.btn']}",//添加部门
			modifyDept:"${LANG['bizconf.jsp.admin.index.res8']}",//修改部门
			addUserToDept:"${LANG['website.admin.org.list.page.title.option.ass.user']}",//添加用户
			manageDeptUser:"${LANG['website.admin.org.list.page.title.option.manage.user']}",//管理用户
			userInfo:"${LANG['bizconf.jsp.admin.index.res12']}",//用户信息
			importUser:"${LANG['site.admin.title.importuser']}",//导入用户
			updateNotice:"${LANG['system.notice.list.Update']}",//修改公告
			addNotice:"${LANG['system.notice.list.Create']}",//添加公告
			modifyPassword:"${LANG['website.user.index.js.message.title.modify.password']}",//修改密码
			billingDetail:"${LANG['website.user.index.js.message.title.bill.info']}",//账单详情
			addDeptSuccess:"${LANG['bizconf.jsp.admin.index.res2']}",//添加部门成功
			updateDeptSuccess:"${LANG['bizconf.jsp.admin.index.res4']}",//修改部门成功
			inviteList:"${LANG['website.user.index.js.message.title.invite.list']}",//邀请人列表
			modifyContactPerson:"${LANG['website.user.index.js.message.title.modify.contacts']}",//编辑联系人
			addContactPerson:"${LANG['website.user.contact.list.addcontact']}",//添加联系人
			modidyHost:"${LANG['bizconf.jsp.system.index.res1']}", //修改主持人
			addContactsBatch:"${LANG['website.user.index.js.message.title.modify.contacts.batch']}"//批量添加联系人
	};
</script>

<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.widgets.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<!-- <script type="text/javascript" src="/assets/js/apps/biz.index.js"></script> -->
<script type="text/javascript" src="/assets/js/apps/biz.adm.index.js"></script>
<script type="text/javascript">
function goModifyHost(hostId,siteId) {
	$("<div id='eidtHostDialog'/>").showDialog({
	    "dialogClass": "ui-dialog-smile",
	    "iconClass":"icon-add",
	    "title": INDEX_CONSTANT.modidyHost,
	    "url" : "/admin/site/goEditHost?hostId="+hostId+"&siteId="+siteId,
	    "width" : 694,
	    "height" : "auto"
	});
}

function contactEditSite(id) {
	if (id) {
		//console.info("aaaaaa");
		$("<div id='editcontactDialog'/>").showDialog({
	        "dialogClass": "ui-dialog-smile",
	        "iconClass":"icon-add",
	        "title":INDEX_CONSTANT.modifyContactPerson,//"编辑联系人",
	        "url" : "/admin/contact/editContact?id="+id,
	        "width" : 620,
	        "height" : "auto"
	    });	
	} else {
		$("<div id='editcontactDialog'/>").showDialog({
	        "dialogClass": "ui-dialog-smile",
	        "iconClass":"icon-add",
	        "title": INDEX_CONSTANT.addContactPerson,//"添加联系人",
	        "url" : "/admin/contact/editContact",
	        "width" : 620,
	        "height" : "auto"
	    });
	}
}
</script>
</head>
<body>
    <div class="header">
    	<jsp:include page="header.jsp" />
    </div>
    <div class="nav"></div>
	<table class="main-table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="left-td" valign="top" >
                <jsp:include page="left.jsp" />
            </td>
            <td id="toggelSlide" valign="top" style="cursor:pointer;background: #EEEEEE" >
                <div style="width:5px;">&nbsp;</div>
            </td>
            <td class="right-td" valign="top" width="100%">
            <iframe  allowtransparency='true' frameborder="0" width="100%" id="mainFrame" name="mainFrame" scrolling="no" 
                	src="/admin/site/info"></iframe>
            </td>
        </tr>
	</table>
    <div class="footer">
        <jsp:include page="footer.jsp" /> 
    </div>    
</body>
</html>


<script type="text/javascript">
	/*
	 *页面上动态调整Iframe的宽高
	 */
	function changeIframeSize(frameId,widthNum,heightNum){
		var frameObj=document.getElementById(frameId);
		if (frameObj){
			if (widthNum>0){
				frameObj.style.posWidth=widthNum;
				//frameObj.setAttribute("width",widthNum)
			}
			if (heightNum>0){
				frameObj.style.height=heightNum+"px";
			};
		}
		$("#mainFrame").trigger("resize");
	}
	function changeLang(langVal){
		var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/admin/";
		window.location.href=jumpUrl;
	}
	function initPage(){
		var useLang="${lang}";
		$("#jumpMenu_language").val(useLang); 
	}
	initPage();
</script>