/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	        
		
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
	            console.log(e);
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
                if(loginUser || href=="/downCenter/downClient" || href=="/user/conf/getControlPadConf?userRole=1" || href=="/user/notice/list") {
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
	window.location.href="/";
}
function userLogin() {
    $("<div id='sysUserDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile ui-dialog-notitle",
        "title": INDEX_CONSTANT.userLogin,//"用户登录",
        "url" : "/user/login",
        "width" : 500,
        "height" : "auto"
    });
}
//"查看会议详情"
function viewConf(id) {
	$("<div id=\"viewMeeting\"/>").showDialog({
		"dialogClass": "ui-dialog-smile",
		"iconClass":"icon-info",
		"title" : INDEX_CONSTANT.viewConfInfo,//"查看会议详情",
		"url" : "/user/conf/view/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"width" : 694,
		"height" : "auto"
	});
}
//"复制会议详情"
function copyViewConf(id) {
	$("<div id=\"viewMeeting\"/>").showDialog({
		"dialogClass": "ui-dialog-smile",
		"iconClass":"icon-info",
		"title" : INDEX_CONSTANT.viewConfInfoCopy,//"复制会议详情",
		"url" : "/user/conf/copyView/" + id,
		"type" : VIEW_TYPE.bookMeeting,
		"width" : 694,
		"height" : "auto"
	});
}
function meetingSchedule() {
    $("<div id='meetingSchedule'/>").showDialog({
        "dialogClass": "ui-dialog-smile ui-dialog-notitle",
        "iconClass":"icon-join",
        "title": INDEX_CONSTANT.addContacts,//"添加联系人",
        "url" : "/jsp2.0/user/meeting_schedule.jsp",
        "width" : 700,
        "height" : "auto"
    });	
}

//我主持的会议报告：查看参会详情
function showPartConfDetail(id) {
    $("<div id='meetingSchedule'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-info",
        "title": INDEX_CONSTANT.joinedInfo,
        //"url" : "/user/conflog/loglist?userPage=true&confId="+id,
        "url" : "/user/conflog/reportInfoList?confLogId="+id,
        "width" : 694,
        "height" : "auto"
    });	
}
/**
 * 预约会议页面跳转
 * */
function createReservationConf(id) {
	if(loginUser){
		if (id) {
			$("<div id=\"bookMeeting\"/>").showDialog({
				"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
				"iconClass":"icon-join",
				"title" : INDEX_CONSTANT.modifyConfInfo,//"修改预约会议",
				"type" : VIEW_TYPE.bookMeeting,
				"url" : "/user/conf/update/" + id,
				"width" : 694,
		        "height" : "auto"
			});
		} else {
			$("<div id=\"bookMeeting\"/>").showDialog({
				"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
				"iconClass":"icon-join", 
				"title" : INDEX_CONSTANT.addConfInfo,//"预约会议",
				"type" : VIEW_TYPE.bookMeeting,
				"url" : "/user/conf/createReservationConf",
				"width" : 694,
		        "height" : "auto"
			});
		}
		
	} else {
		userLogin();	
	}
}

//重新创建会议
function reCreateReservationConf(id) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
		"iconClass":"icon-join",
		"title" : INDEX_CONSTANT.recreateConfInfo,//"重新创建会议",
		"type" : VIEW_TYPE.bookMeeting,
		"url" : "/user/conf/reCreateconf/" + id,
		"width" : 694,
		"height" : "auto"
	});	
}

//修改个人会议室信息
function updateMeetingRoom(id) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
		"iconClass":"icon-join",
		"title" : INDEX_CONSTANT.modifyConfInfo,//修改个人会议室信息
		"type" : VIEW_TYPE.bookMeeting,
		"url" : "/user/conf/toUpdatePmi/" + id,
		"width" : 694,
		"height" : "auto"
	});	
}


function updateRecurrConf(id) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
		"iconClass":"icon-join",
		"title" : "修改周期会议",//"重新创建会议",
		"type" : VIEW_TYPE.bookMeeting,
		"url" : "/user/conf/toUpdateRecurrConf/" + id,
		"width" : 694,
		"height" : "auto"
	});	
}

//修改周期会议中的信息
function updateCycleMeetingInfo(id,cycleType) {
	$("<div id=\"bookMeeting\"/>").showDialog({
		"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
		"iconClass":"icon-join",
		"title" : INDEX_CONSTANT.modifyCycleAll,//"修改周期会议中所有会议的信息",
		"type" : VIEW_TYPE.bookMeeting,
		"url" : "/user/conf/updateCycleConfInfo/" + id+"?cycleConfType="+cycleType,
		"width" : 694,
        "height" : "auto"
	});	
}


function popupNotice() { 
	if(noticeId){
		var existId = $.cookie("n_pop_id");
		if( !existId || (existId && existId!=noticeId) ){
			$("<div id=\"popupNotice\"/>").showDialog({
				"title" : INDEX_CONSTANT.systemNotice,//"公告通知",
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
		"title" : INDEX_CONSTANT.modifyPassword,//"修改密码",
		"iconClass":"icon-notice",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/user/resetPass",
		"width" : 500,
		"height" : "auto"
	});
}

function resetFaverSetting(){
	var date = new Date();
	var offsetmin = date.getTimezoneOffset();
	var timezone = -1*(offsetmin*60000);
	$("<div id=\"faverSetting\"/>").showDialog({
		"title" : INDEX_CONSTANT.faverSetting,//"个人设置",
		"iconClass":"icon-notice",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/user/faverSetting?timezone="+timezone,
		"width" : 520,
		"height" : "auto"
	});
}

function rememberNotice(nId) {
	$.cookie("n_pop_id", nId, { expires: 365 ,domain:getDomain()});
}

function contactEdit(id) {
	if (id) {
		$("<div id='editcontactDialog'/>").showDialog({
	        "dialogClass": "ui-dialog-smile",
	        "iconClass":"icon-add",
	        "title": INDEX_CONSTANT.modifyContacts,//"编辑联系人",
	        "url" : "/user/contact/editContact?id="+id,
	        "width" : 620,
	        "height" : "auto"
	    });	
	} else {
		$("<div id='editcontactDialog'/>").showDialog({
	        "dialogClass": "ui-dialog-smile",
	        "iconClass":"icon-add",
	        "title": INDEX_CONSTANT.addContacts,//"添加联系人",
	        "url" : "/user/contact/editContact",
	        "width" : 620,
	        "height" : "auto"
	    });
	}
    	
}

function contactImport() {
    $("<div id='importContactDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-import-more",
        "title": INDEX_CONSTANT.addContactsBatch,//"批量添加联系人",
        "url" : "/user/contact/importPop",
        "width" : 694,
        "height" : 520
    });	
}
function contactChoose() {
    $("<div id='contactChooseDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-join",
        "title": INDEX_CONSTANT.selectContacts,//"从通讯录中选择",
        "url" : "/jsp2.0/user/contact_choose.jsp",
        "width" : 700,
        "height" : "auto"
    });	
}

function showImportSuccessDialog(url) {
    $("<div id='impSuccessDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-group",
        "title": INDEX_CONSTANT.importSucceed,//"批量导入联系人成功",
        "url" : url,
        "width" : 700,
        "height" : "auto"
    });	
}
function addGroup(id) {
	if (id) {
		$("<div id='editGroupDiv'/>").showDialog({
			"title" : INDEX_CONSTANT.modifyGroup,//"编辑群组",
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"url" : "/user/group/editGroup?id="+id,
			"type" : VIEW_TYPE.group,
			"action" : ACTION.create,
			"width" : 480,
			"height" : 279
		});		
	} else {
		$("<div id='editGroupDiv'/>").showDialog({
			"title" : INDEX_CONSTANT.addGroup,//"添加群组",
			"dialogClass" : "ui-dialog-smile",
			"iconClass":"icon-add",
			"url" : "/user/group/editGroup",
			"type" : VIEW_TYPE.group,
			"action" : ACTION.create,
			"width" : 480,
			"height" : 279
		});
	}
}

//查看群组联系人列表
function viewGroupMember(id,viewOnly,name, model) {
	if(!viewOnly){
		viewOnly = "";
	}
	if(!name){
		name = "";
	}
	$("<div id=\"viewGroup\"/>").showDialog({
		"model": model,
		"title" : name+" "+INDEX_CONSTANT.groupUsers,//"群组成员",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-group",
		"url" : "/user/group/showContacts?viewOnly="+viewOnly+"&group_id=" + id,
		"type" : VIEW_TYPE.group,
		"action" : ACTION.view,
		"width" : 694,
		"height" : 520
	});
}

//将联系人加入群组
function addContactToGroup(id) {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : INDEX_CONSTANT.selectContactsForGroup,//"添加联系人到群组",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-group",
		"url" : "/user/group/importContacts?group_id="+id,
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});	
}

//查看邀请参会人列表
function editInventContact(confId,back) {
	$("<div id=\"eidtInventContact\"/>").showDialog({
		"title" : INDEX_CONSTANT.inviteList,//"邀请人列表",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-invent-list",
		"url" : "/user/inviteUser?confId="+confId+"&back=1",
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});	
}

/*
function testContactInvite() {
	$("<div id=\"inventContact\"/>").showDialog({
		"title" : "邀请参会人 异常显示",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-join",
		"url" : "/jsp2.0/user/invite_failed.jsp",
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 700,
		"height" : 539
	});	
}
*/ 


//从通讯录中邀请参会人
function inventFromContacts() {
	$("<div id=\"importContact\"/>").showDialog({
		"model": false,
		"title" : INDEX_CONSTANT.inviteFromContacts,//"从通讯录中邀请",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-group",
		"url" : "/user/contact/goContactsSelect",
		"type" : VIEW_TYPE.contact,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});	
}

//进入邀请与会者页面
function inventContact(confId,isTel) {
	var telCall = "false";
	if(!!isTel){telCall = "true";}
	$("<div id=\"inventContact\"/>").showDialog({
		"model": false,
		"title" :INDEX_CONSTANT.inviteConfUser,// "邀请与会者"
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-invent-list",
		"url" : "/user/contact/goInviteContacts?confId="+confId+"&isTelCall="+telCall,
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 510
	});	
}

//短信邀请
function inventContactByMsg(confId,isTel,sendType) {
	var telCall = "false";
	if(!!isTel){telCall = "true";}
	$("<div id=\"inventContact\"/>").showDialog({
		"model": false,
		"title" :INDEX_CONSTANT.inviteConfUserMsg,// "邀请与会者"
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-invent-list",
		"url" : "/user/contact/goInviteContactsMsg?confId="+confId+"&isTelCall="+telCall+"&sendType="+sendType,
		"type" : VIEW_TYPE.attendee,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});	
}

//查看数据费用详细
function showDataFeeDetail(id,year,month) {
	$("<div id=\"dataFeeView\"/>").showDialog({
		"title" : INDEX_CONSTANT.billInfo,//"账单详情",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-order",
		"url" : "/common/billing/showDataDetail?userId="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});
}

//查看通信费用详细
function showTelDetail(id,year,month) {
	$("<div id=\"billingView\"/>").showDialog({
		"title" : INDEX_CONSTANT.billInfo,//"账单详情",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-order",
		"url" : "/common/billing/showTelDetail?userId="+id+"&year="+year+"&month="+month,
		"type" : VIEW_TYPE.billing,
		"action" : ACTION.create,
		"width" : 694,
		"height" : 520
	});
}

//弹出邮件提醒弹窗
function addCalendar(id) {
	$("<div id=\"addCalendar\"/>").showDialog({
		"title" : INDEX_CONSTANT.emailRemind,//"邮件提醒",
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-group",
		"url" : "/user/email/outlook?confId="+id,
		"data": id,
		"type" : VIEW_TYPE.calendar,
		"action" : ACTION.create,
		"width" : 520,
		"height" : "auto"
	});	
}
/*
function viewContacts(id) {
	$("<div id=\"viewGroup\"/>").showDialog({
		"title" : "查看群组成员",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/jsp2.0/user/contact_group_view_success.jsp",
		"type" : VIEW_TYPE.group,
		"action" : ACTION.view,
		"width" : 700,
		"height" : 500
	});
}
function importContactSuccess() {
	$("<div id=\"importContact\"/>").showDialog({
		"title" : "批量添加联系人",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/jsp2.0/user/contact_add_success.jsp",
		"type" : VIEW_TYPE.group,
		"action" : ACTION.view,
		"width" : 700,
		"height" : 525
	});	
}
*/
/**
 * 刷新子iframe 
 * @param url iframe将要访问的url
 * @param id #这个参数是子iframe的id
 */
function refreshChildIframe(url,id) {
	var iframe = $("#mainFrame")[0].contentWindow.$("#"+id);
	$(iframe).attr("src", url);
}

function intime() {
	if(loginUser){
	    $("<div id='immediateConf'/>").showDialog({
	        "dialogClass": "ui-dialog-smile",
	        "iconClass":"icon-join",
	        "title": INDEX_CONSTANT.immeConf,//"即时会议",
	        "url" : "/user/conf/createImmediatelyConf",
	        "width" : 450,
	        "height" : "auto"
	    });	
	}
	else{
		userLogin();
	}
}
function joinByNum() {
    $("<div id='joinMeeting'/>").showDialog({
        "dialogClass": "ui-dialog-smile ui-dialog-notitle",
        "iconClass":"icon-numjoin",
        "title": INDEX_CONSTANT.joinFromCode,//"会议号加入",
        "url" : "/join/joinpage?joinType=2&cId=&userName=",
        "width" : 474,
        "height" : "auto"
    });	
}
function noticePrompt() {
    $("<div id='sysUserDialog'/>").showDialog({
        "dialogClass": "ui-dialog-smile",
        "iconClass":"icon-numjoin",
        "title": INDEX_CONSTANT.systemNotice,//"公告通知",
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

function commonDialog(message) {
	$("<div/>").commonDialog({
		"title": INDEX_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"width": 700
	});
}
function successDialog(message, callback) {
	$("<div/>").alertDialog({
		"title": INDEX_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "success",
		"buttonOk": INDEX_CONSTANT.confirm,//"确定",
		"successCallback" : function() {
			if(callback) {
				callback();
			}
		}
	});
}
function errorDialog(message, callback) {
	$("<div/>").alertDialog({
		"title": INDEX_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "error",
		"buttonOk": INDEX_CONSTANT.confirm,//"确定",
		"successCallback": callback
	});
}


function promptDialog(message,callback) {
	$("<div/>").alertDialog({
		"title": INDEX_CONSTANT.remindCommon,//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "prompt",
		"buttonOk": INDEX_CONSTANT.confirm,//"确定",
		"buttonCancel": INDEX_CONSTANT.cancel,//"取消",
		"successCallback" : callback 
	});
} 

function sleep(millSeconds, callBack) {
    if (millSeconds > 0) {
        setTimeout(callBack, millSeconds);
    } else {
        callBack();
    }
}

function joinDelay(id) {
	sleep(5000, function(){joinMeeting(1, id);});
}

//主持人加入会议
function joinMeeting(joinType,cId){
	if(joinType==null || joinType==""){
		return false;
	}
	var titleName=INDEX_CONSTANT.joinConf;//"加入会议";

	var divHeight="auto";
	if ($.browser.msie && $.browser.version<7) {
		divHeight=230;
	}
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-smile  ui-dialog-notitle",
		"iconClass":"icon-join",
//		"url" : "/join/joinpage?joinType="+joinType+"&cId="+cId,
		"url" : "/join/gotojoinPage?joinType="+joinType+"&cId="+cId+"&blank=0",
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		//"height" : divHeight
		"height" : "auto"
	});
}



function joinMeetingFromEmail(joinMtgUrl,titleName){
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-smile  ui-dialog-notitle",
		"url" : joinMtgUrl,//"/join/joinpage?joinType="+joinType+"&cId="+cId,
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" :"auto"
	});
}

jQuery(function($) {
	$('body').bind(EVENT_UPDATE, function(event, result, type) {
		if(type==VIEW_TYPE.bookMeeting) {
//			showURL("/user/conf/getControlPadIndex?userRole=1");
			showURL("/user/conf/getControlPadConf?userRole=1");
		}
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
//    $("#langForm select").change(function () {
//		var lang = $(this).val();
//		changeLang(lang);
//	});
//	$('#jumpMenu_language').toggle(function() {
//		$(this).toggleClass('english');
//		$(this).val('en-us');
//	}, function() {
//		$(this).toggleClass('cyan');
//		$(this).val('zh-cn');
//	});
    $("#jumpMenu_language").click(function() {
		var lang = $(this).attr("data");
		$(this).toggleClass('english');
		if(lang == 'en'){
			lang = 'en-us';
		}else{
			lang = 'zh-cn';//切换成中文
		}
		changeLang(lang);
	});
    
    if(needResetPass != null && needResetPass =="true"){
    	resetPass();
    }
    popupNotice();
    //joinMtgFromEmail();
});
//window.setInterval(resizeHeight, 1000);

var _timer;
window.onresize = function() {
  window.clearTimeout(_timer);
  _timer = window.setTimeout(function(){
    resizeHeight();
  }, 300);
};

function jumpToFavor() {
	if(loginUser) {
		jumpToLi("/user/favor/getTimeZone", "ico-favor");
	} else {
		userLogin();
	}
}

function jumpToLi(url, name) {
	url+="?t="+ Math.random();
	$(".nav-menu li").removeClass("active");
	var favorLi = $(".nav-menu").find("."+name).parent();
	favorLi.addClass("active");
	$("#mainFrame").attr("src", url); 
}



function confirmLogout(){
	 promptDialog(INDEX_CONSTANT.loginMessage,function(){
		 window.location.href="/user/logout";
	 });
}

function joinFromSIPor323() {
	$("<div id='joinfromsipor323'/>").showDialog({
		"title" : "H323/SIP终端入会",//"配对接入",
		"dialogClass" : "ui-dialog-smile ui-dialog-notitle",
		"iconClass":"icon-pairing",
		"url" : "/join/gotoPairingMeeting",
		"action" : ACTION.create,
		"width" : 474,
		"height" : "auto"
	});
}