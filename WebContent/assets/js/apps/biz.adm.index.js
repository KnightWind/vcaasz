/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/

/**
 * [LOGO图片大小自适应]
 * @param ImgD
 * @param iwidth
 * @param iheight
 */
function DrawImage(ImgD,iwidth,iheight){
    var image=new Image();
    image.src=ImgD.src;
    if(image.width>0 && image.height>0){
      if(image.width/image.height>= iwidth/iheight){
          if(image.width>iwidth){
              ImgD.width=iwidth;
              ImgD.height=(image.height*iwidth)/image.width;
          }else{
              ImgD.width=image.width;
              ImgD.height=image.height;
          }
      }else{
          if(image.height>iheight){
              ImgD.height=iheight;
              ImgD.width=(image.width*iheight)/image.height;
          }else{
              ImgD.width=image.width;
              ImgD.height=image.height;
          }
      }
    }
}

/**
 * [初始化皮肤]
 * @return {[type]} [description]
 */
function initSkin() {
	 LOGDEBUG = getUrlParam("debug"); 
	if(LOGDEBUG) {
		$("#skin-switcher").show();
		$('#skin-switcher').themeswitcher({
			initialText: INDEX_CONSTANT.changeSkin,
	        onSelect:function(e){
	           // console.log(e);
	        }
	    });
	}
}
/**
 * [初始化左侧菜单]
 * @return {[type]} [description]
 */
function initSidebar() {
	$(".nav-menu").slideBar({
        clickCallback: function(elem) {
            elem.blur();
        	var isIgnore = elem.hasClass("isParent");
        	if (!isIgnore) {
                var href = elem.attr("href");
                if(loginUser || href=="/downCenter/downClient" || href=="/user/conf/getPublicControlPadIndex" || href=="/user/notice/list") {
                    showURL(href);	
    			} else {
    				userLogin();
    			}
            }
        }
    });
}
/**
 * [刷新IFRAME]
 * @return {[type]} [description]
 */
function showURL(url) {
		url = addUrlParam(url, "t", Math.random());
		$('#mainFrame').attr("src", url);
}
function reloadPage() {
	window.location.href="/admin";
}

function logout(confirminfo){
	promptDialog(confirminfo,function(){
		window.location = "/admin/logout";
	});
}

function userLogin() {
    $("<div id='sysUserDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile ui-dialog-notitle",
        "title": INDEX_CONSTANT.userLogin,
        "url" : "/jsp2.0/user/login.jsp",
        "width" : 500,
        "height" : "auto"
    });
}
//会议信息 查看用户入会详情
function showConflogs(id) {
	$("<div id=\"meetingSchedule\"/>").showDialog({
		"title" : INDEX_CONSTANT.joinedInfo,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-info",
		//"url" : "/user/conflog/loglist?confId="+id,
		"url" : "/user/conflog/reportInfoList?confId="+id,
		"type" : VIEW_TYPE.group,
		"width" : 694,
		"height" : 520
	});
}
//查看站点管理员日志中站点管理员修改用户日志
function viewAdminUserDetails(id) {
	$("<div id=\"optDescDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.viewDetail,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-info",
		"url" : "/admin/siteAdminLogs/viewAdminUserDetails/" + id,
		"type" : VIEW_TYPE.site,
		"width" : 694,
		"height" : 520
	});
}
//查看站点管理员日志中,用户操作日志（修改会议）详情
function viewUserConfDetails(id) {
	$("<div id=\"optDescDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.viewDetail,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-info",
		"url" : "/admin/siteUserLogs/viewUserConfDetails/" + id,
		"type" : VIEW_TYPE.site,
		"width" : 694,
		"height" : 520
	});
}
//conf info
function viewConf(id) {
	$("<div id=\"viewMeeting\"/>").showDialog({
		"dialogClass": "ui-dialog-smile",
		"iconClass":"icon-info",
		"title" : LOADING_CONSTANT.viewConfInfo,//"查看会议详情",
		"url" : "/user/conf/adminViewConf/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"width" : 688,
		"height" : 509
	});
}


//我主持的会议报告：查看参会详情
function showPartConfDetail(id) {
    $("<div id='meetingSchedule'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-group",
        "title": INDEX_CONSTANT.joinedInfo,
        "url" : "/user/conflog/loglist?userPage=true&confId="+id,
        "width" : 694,
        "height" : "auto"
    });	
}
//添加企业管理员
function createOrUpdateAdminUser(id) {
	if(id) {
		$("<div id=\"userDiv\"/>").showDialog({
			"title": INDEX_CONSTANT.modifyAdmin,
			"url" : "/admin/entUser/toEditUser?id=" + id,
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"type" : VIEW_TYPE.siteAdminUser,
			"width" : 694,
			"height" : 520
		});
	} else {
		$("<div id=\"userDiv\"/>").showDialog({
			"title": INDEX_CONSTANT.addAdmin,
			"url" : "/admin/entUser/toEditUser",
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"type" : VIEW_TYPE.siteAdminUser,
			"width" : 694,
			"height" : 520
		});
	}
}	
//添加普通用户
function createOrUpdateSiteUser(id) {
	if(id) {
		$("<div id=\"userDiv\"/>").showDialog({
			"title": LOADING_CONSTANT.updateUser,//"修改用户",
			"url" : "/admin/entUser/toEditUserBase?id=" + id,
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"type" : VIEW_TYPE.siteUser,
			"width" : 694,
			"height" : 520
		});
	} else {
		$("<div id=\"userDiv\"/>").showDialog({
			"title": LOADING_CONSTANT.addUser,//"添加用户",
			"url" : "/admin/entUser/toEditUserBase",
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"type" : VIEW_TYPE.siteUser,
			"width" : 694,
			"height" : 520
		});
	}
}
//添加机构
function createOrg(pid) {
	$("<div id=\"organizDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.addDept,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-add",
		"url" : "/admin/org/add?pId="+pid,
		"type" : VIEW_TYPE.organiz,
		"width" : 480,
		"height" : 220
	});
}
//修改机构
function updateOrg(id) {
	$("<div id=\"organizDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.modifyDept,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-add",
		"url" : "/admin/org/update/" + id,
		"type" : VIEW_TYPE.updateOrganiz,
		"width" : 480,
		"height" : 220
	});
}
//机构分配用户
function getOrgUserList(id) {
	$("<div id=\"assignUserDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.addUserToDept,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-arrange",
		"url" : "/admin/org/getOrgUserList/" + id,
		"type" : VIEW_TYPE.assignUser,
		"width" : 694,
		"height" : 520
	});
}
//机构管理用户
function showOrgUsers(id) {
	$("<div id=\"orgUsersDiv\"/>").showDialog({
		"title" : INDEX_CONSTANT.manageDeptUser,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-manager",
		"url" : "/admin/org/getOrgSubUserList/" +id,
		"type" : VIEW_TYPE.group,
		"width" : 694,
		"height" : 400
	});
}
//企业用户管理 参会详情
function showAttendConfs(id) {
	$("<div id=\"attendconfs\"/>").showDialog({
		"title" : INDEX_CONSTANT.joinedInfo,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-conf-detail",
		"url" : "/user/conflog/attendConflist?userId="+id,
		"type" : VIEW_TYPE.group,
		"width" : 694,
		"height" : 520
	});
}
//企业用户管理 用户详情
function viewUser(id) {
	$("<div id=\"viewUserDiv\"/>").showDialog({
		"title":INDEX_CONSTANT.userInfo,
		"url" : "/admin/entUser/toViewUserBase?id="+id,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-user-info",
		"width" : 450,
		"height" : 475
	});
}

//添加公告
function createOrUpdateNotice(id) {
	if (id) {
		$("<div id=\"noticeDiv\"/>").showDialog({
			"title" : INDEX_CONSTANT.updateNotice,
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"url" : "/admin/notice/update/" + id,
			"type" : VIEW_TYPE.notice,
			"width" : 694,
			"height" : 520
		});
	} else {
		$("<div id=\"noticeDiv\"/>").showDialog({
			"title" : INDEX_CONSTANT.addNotice,
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"url" : "/admin/notice/create",
			"type" : VIEW_TYPE.notice,
			"width" : 694,
			"height" : 520
		});
	}
}
function popupNotice() { 
	if(noticeId){
		var existId = $.cookie("n_pop_id");
		if( !existId || (existId && existId!=noticeId) ){
			$("<div id=\"popupNotice\"/>").showDialog({
				"title": LOADING_CONSTANT.noticeAlert,//"公告通知",
				"iconClass":"icon-notice",
				"dialogClass": "ui-dialog-smile",
				"url" : "/user/notice/popUpNotice/"+noticeId,
				"width" : 600,
				"height" : 564
			});			
		}
	}
}

function resetPass(){
	$("<div id=\"resetPass\"/>").showDialog({
		"title" : INDEX_CONSTANT.modifyPassword,
		"iconClass":"icon-notice",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/admin/resetPass",
		"width" : 500,
		"height" : "auto"
	});
}

function rememberNotice(nId) {
	$.cookie("n_pop_id", nId, { expires: 365 ,domain:getDomain()});
}

//查看数据费用详细
function showDataFeeDetail(id,year,month) {
	$("<div id=\"dataFeeView\"/>").showDialog({
		"title" : INDEX_CONSTANT.billingDetail,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-order",
		"url" : "/common/billing/sysShowDataDetail?id="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});
}

//查看通信费用详细
function showTelDetail(id,year,month) {
	$("<div id=\"billingView\"/>").showDialog({
		"title" : INDEX_CONSTANT.billingDetail,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-order",
		"url" : "/common/billing/sysShowTelDetail?id="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});
}
 
//查看邀请参会人列表
function editInventContact(confId) {
	//alert('kh33');
	$("<div id=\"eidtInventContact\"/>").showDialog({
		"title" : INDEX_CONSTANT.inviteList,//"邀请人列表",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-invent-list",
		"url" : "/user/inviteUser?confId="+confId,
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});	
}

/**
 * 刷新子iframe 
 * @param url iframe将要访问的url
 * @param id #这个参数是子iframe的id
 */
function refreshChildIframe(url,id) {
	var iframe = $("#mainFrame")[0].contentWindow.$("#"+id);
	$(iframe).attr("src", url);
}



function noticePrompt() {
    $("<div id='sysUserDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-numjoin",
        "title": LOADING_CONSTANT.noticeAlert,//"公告通知",
        "url" : "/jsp2.0/user/notice_prompt.jsp",
        "width" : 600,
        "height" : "auto"
    });	
}
function resizeHeight(){
    var headerHeight = $(".header").height();
    var navHeight = $(".nav").height();
    var footerHeight = $(".footer").height();
    var screenHeight = $(window).height()-headerHeight-navHeight-footerHeight;
    var height = $("#mainFrame").contents().find("body").outerHeight();
    if(height<screenHeight) {
        height = screenHeight;
    }
    
    $("#mainFrame").height(height);
}

function successDialog(message) {
	$("<div/>").alertDialog({
		"title": LOADING_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "success",
		"buttonOk": LOADING_CONSTANT.confirm,//"确定",
		"successCallback" : function() {
			
		}
	});
}
function errorDialog(message, callback) {
	$("<div/>").alertDialog({
		"title": LOADING_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "error",
		"buttonOk": LOADING_CONSTANT.confirm,//"确定",
		"successCallback": callback
	});
}
function promptDialog(message,callback) {
	$("<div/>").alertDialog({
		"title": LOADING_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "prompt",
		"buttonOk": LOADING_CONSTANT.confirm,//"确定",
		"buttonCancel": LOADING_CONSTANT.cancel,//"取消",
		"successCallback" : callback 
	});
} 

function commonDialog(message) {
	$("<div id=\"commonsuccess\" />").commonDialog({
		"title": LOADING_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"width": 700
	});
}

function closeCommonDialog(){
	$("#commonsuccess").trigger("closeDialog");
}

jQuery(function($) {
	$('body').bind(EVENT_UPDATE, function(event, result, type) {
		if(type==VIEW_TYPE.bookMeeting) {
//			showURL("/user/conf/getControlPadIndex?userRole=1");
			showURL("/user/conf/getControlPadIndex?userRole=1");
		}
		if (type==VIEW_TYPE.organiz) {
			successDialog(INDEX_CONSTANT.addDeptSuccess);
			showURL("/admin/org/orgListIndex");
		}
		if (type==VIEW_TYPE.updateOrganiz) {
			successDialog(INDEX_CONSTANT.updateDeptSuccess);
			showURL("/admin/org/orgListIndex");
		}
		if (type==VIEW_TYPE.assignUser) {
			showURL("/admin/org/orgListIndex");
		}
		if (type==VIEW_TYPE.siteUser) {
			successDialog(result.userBase[0]);
			showURL("/admin/entUser/listAll");
		}
		if (type==VIEW_TYPE.notice) {
			successDialog(result.message);
			showURL("/admin/notice/list");
		}
	});
	$("body").bind(EVENT_ERROR, function(event, result, type) {
		errorDialog(result.message);
	});
	initSkin();
	initSidebar();
    $("#mainFrame").load(function() {
        resizeHeight();
    });
//    $('#toggelSlide').toggle(function() {
//        $(".left-td").hide();
//    }, function() {
//        $(".left-td").show();
//    });
    $("#langForm select").change(function () {
		var lang = $(this).val();
		changeLang(lang);
	});
    if(needResetPass != null && needResetPass =="true"){
    	resetPass();
    }
    popupNotice();
});

var _timer;
window.onresize = function() {
  window.clearTimeout(_timer);
  _timer = window.setTimeout(function(){
    resizeHeight();
  }, 300);
};

function jumpToFavor() {
	if(loginUser) {
		jumpToFavorLi("/user/favor/getTimeZone");
	} else {
		userLogin();
	}
}

function jumpToFavorLi(url) {
	url+="?t="+ Math.random();
	$(".nav-menu li").removeClass("active");
	var favorLi = $(".nav-menu").find(".ico-favor").parent();
	favorLi.addClass("active");
	$("#mainFrame").attr("src", url); 
}
/** 企业管理员批量添加联系人 */
function bulkImportContact() {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : INDEX_CONSTANT.addContactsBatch,
		"iconClass":"icon-import-more",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/admin/contact/importContactsByExcel",
		"type" : VIEW_TYPE.contact,
		"width" : 694,
		"height" : "auto"
	});	
}