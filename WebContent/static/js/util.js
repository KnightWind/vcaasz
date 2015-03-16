var LOGDEBUG = false;
var ISFASTIP = false;
var EVENT_CREATE = "eventCreate";
var EVENT_UPDATE = "eventUpdate";
var EVENT_DELETE  = "eventDelete";
var EVENT_ERROR  = "eventError";
var EVENT_SEND_EMAIL  = "sendEmail";

var VIEW_TYPE = {
	login: "login",
	sysAdminUser: "sysAdminUser",
	siteAdminUser: "siteAdminUser",
	siteUser: "siteUser",
	hostUser: "hostUser",
	notice: "notice",
	site: "site",
	organiz: "organiz",
	billing: "billing",
	relateOrg: "relateOrg",
	assignUser: "assignUser",
	tempMeeting: "tempMeeting",
	linkTempMeeting: "linkTempMeeting",
	bookMeeting: "bookMeeting",
	quickMeeting: "accessMeeting",
	joinMeeting: "joinMeeting",
	contact: "contact",
	attendee: "attendee",
	group: "group",
	calendar: "calendar"
};

var ACTION = {
	view: "view",
	create: "create",
	update: "update",
	del: "delete",
	join: "join"
};

/* Debug Utility */
function logDebug(message) {
	if (window.console && window.console.debug) {
        window.console.log(message);
    }
}

function formatIpUrl(param) {
	var url = "http://"+param+".confcloud.cn:80/test/logo.png";
	url = addT(url);
	return url;
}

function addUrlParam(url, paramName, paramValue, encode) {
    if (paramName && paramValue && url.indexOf(paramName+"=")<0) {
        url += url.indexOf("?")>=0?"&":"?";
        if(encode){
            url += paramName + "=" + encodeURIComponent(encodeURIComponent(paramValue));	
        } else {
        	url += paramName + "=" + paramValue;
        }
    }
    return url;
}

function addT(url, time) {
    if (url && time!=-1) {
        var t = time;
        if (!t) {
            t = new Date().getTime();
        }
        url = addUrlParam(url, "t", t);
    }
    return url;
}
/**
 * 日期格式化
 * @param new Date()
 * @returns 2012-12-12
 */
function formatDate(date) {
	var year = date.getFullYear();
	var month = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
	var day = date.getDate()<10?"0"+date.getDate():date.getDate();
	return year+"-"+month+"-"+day;
}

/**
 * 日期字符串比较大小
 * @param startDate 2012-12-12 9:20:00
 * @param nowDate
 * @returns {Boolean}
 */
function compareNow(startDate, currentDate) {
	var startDate = startDate.replace(/-/g,"/");
	var startDate = new Date(startDate).getTime();
	var nowDate = currentDate.getTime();
	if(startDate<nowDate){
		return false;
	}
	return true;
};
/**
 * 
 * 比较日期字符串大小
 * @param date1 2012-12-12 9:20:20
 * @param date2 2013-12-12 9:30:30
 */
function compareDate(date1, date2) {
	date1 = date1.replace(/-/g, "/");
	date2 = date2.replace(/-/g, "/");
	var startDate = new Date(date1).getTime();
	var endDate = new Date(date2).getTime();
	if(endDate>=startDate){
		return true;
	}
	return false;
}

/**
 * 
 * 替换字符
 * @param strObj
 * @param pos
 * @param replacetext
 * @returns
 */
function replacePos(strObj, pos, replacetext) {
   var str = strObj.substr(0, pos-1) + replacetext + strObj.substring(pos, strObj.length);
   return str;
}
/**
 * 
 * 切换面板
 * @param selector
 */
function togglePanel(selector, icon, callback) {
    var panel = $(selector);
    if (panel.is(":visible")) {
        hidePanel(selector, icon, callback);
    }
    else if(panel.is(":hidden")){
        showPanel(selector, icon, callback);
    }
}
function showPanel(selector, icon ,callback) {
	try {
	    var panel = $(selector);
	    icon.removeClass("panel_icon_on");
	    icon.addClass("panel_icon_off");
	    if($.browser.msie && $.browser.version<=7){
//		    panel.show("fast", callback);
		    panel.show();
		    callback();
	    } else {
		    panel.slideDown(callback);	
	    }

	}
	catch (e) {
	}
}
function hidePanel(selector, icon, callback) {
	try {
	    var panel = $(selector);
	    icon.removeClass("panel_icon_off");
	    icon.addClass("panel_icon_on");
	    if($.browser.msie && $.browser.version<=7){
//		    panel.hide("fast", callback);
	    	panel.hide();
	    	callback();
	    } else {
		    panel.slideUp(callback);	
	    }		

	} catch (e) {
	}
}

/* Ajax Methods */
function ajaxGet(url, data, sucessCallback, failureCallback, options) {
    ajaxLoad("get", url, data, sucessCallback, failureCallback, options);
}
function ajaxPost(url, data, sucessCallback, failureCallback, options) {
    ajaxLoad("post", url, data, sucessCallback, failureCallback, options);
}
function ajaxLoad(type, url, data, sucessCallback, failureCallback, options) {
    var loading = null;
    var postloading = null;
    var dataType = "text";
    if(options && options.dataType){
    	dataType = options.dataType;
    }
    var contentType = 'application/x-www-form-urlencoded';
    if(options && options.contentType) {
    	contentType = options.contentType;
    }
    $.ajax({
        type: type,
        url: url,
        data: data,
        dataType: dataType,
        contentType: contentType,
        beforeSend: function(XMLHttpRequest) {
            if (type=="post" && options && options.message) {
                postloading = showPostLoading(options.message, options.ui);
            }
        },
        success: function(data, textStatus, XmlHttpRequest) {
            if (type=="post") {
                hidePostLoading(postloading);
            }
        	if (sucessCallback) {
    			if(dataType=="text" || dataType=="json"){
    				try {
        				data = JSON.parse(data);	
					} catch (e) {
//						sucessCallback(data);
					}
    			}	
                sucessCallback(data);
            }
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            if (type=="post") {
                hidePostLoading(postloading);
            }
        }
    });
}

function showPostLoading(message, ui) {
	var document = ui.document;
	var body = ui.document.body;
    var loadContainer = null;
    if (message != ""){
    	var showLoading = true;
    	//ie6,7 
    	if ($.browser.msie && $.browser.version<=7) {
    		showLoading = false;
    	} 
    	if(showLoading){
    	  	loadContainer = document.getElementById("load-container");
        	if (!loadContainer || loadContainer.length==0) {
                var top = $(document).height();
                var left = $(document).width();
        		loadContainer = $('<div id="load-container"></div>').appendTo(body);
        		var loadingback = $('<div class="postloadingback ui-widget-overlay" style="z-index:2000"></div>').appendTo(loadContainer);
                loadingback.css({height:top,width:left});
                var loadingDisplay = $('<div class="postloading" style="z-index:2002;position:absolute"><div style="text-align:center;font-size:15px;opacity:1;font-weight:bold"><image style="align:center" src="/static/images/loading.gif">'+
                		message+'</div></div>').appendTo(loadContainer);
                var mtop = $(ui).height()/2;
                var mleft = left/2;
                loadingDisplay.css({top:mtop,left:mleft});
        	}	
    	}
    }
    return loadContainer;
}
function hidePostLoading(loading) {
    if (!loading) {
    	loading = $(".load-container");
    }
    loading.remove();
}
var app = {
	userLogin: function(data, callback, options) {
		var url = "/user/login/check";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);			
	},
	createSysUser: function(sysUser, callback, options) {
        var url = "/system/sysUser/create";
        var param = [];
        param[0] = sysUser;
        ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	updateSysUser: function(sysUser, callback, options) {
		var url = "/system/sysUser/update";
		var param = [];
		param[0] = sysUser;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	createAdminUser: function(siteUser, callback, options) {
        var url = "/admin/entUser/saveSiteAdmin";
        var param = [];
        param[0] = siteUser;
        param[1] = "";
        ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	saveHost: function(host, callback, options) {
        var url = "/system/lic/saveHost";
        ajaxPost(url, host,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	maSaveHost: function(host, callback, options) {
		var url = "/system/lic/maSaveHost";
		ajaxPost(url, host,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	updateAdminUser: function(siteUser,orgPass, callback, options) {
		var url = "/admin/entUser/saveSiteAdmin";
		var param = [];
		param[0] = siteUser;
		param[1] = orgPass;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},	
	createSiteUser: function(siteUser,func,callback, options) {
        var url = "/admin/entUser/saveSiteUser";
        var param = [];
        param[0] = siteUser;
        param[1] = func;
        ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	updateSiteUser: function(siteUser,func, callback, options) {
		var url = "/admin/entUser/saveSiteUser";
		var param = [];
		param[0] = siteUser;
		param[1] = func;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	saveOrUpdateMS: function(ms,callback,options) {
        var url = "/system/ms/savemsinfo";
        ajaxPost(url, ms,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	delMS: function(msId,callback,options) {
        var url = "/system/ms/delms?msId="+msId;
        ajaxPost(url, {msId:msId},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	lockMS: function(msId,callback,options) {
		var url = "/system/ms/msState";
		ajaxPost(url, {"msId":msId,"state":0},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	unlockMS: function(msId,callback,options) {
		var url = "/system/ms/msState";
		ajaxPost(url, {"msId":msId,"state":1},
				function(result) { 
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	saveOrUpdateMSGroup: function(group,callback,options) {
        var url = "/system/ms/savemsgroup";
        ajaxPost(url, group,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	delMSGroup: function(groupId,callback,options) {
        var url = "/system/ms/delgroup";
        ajaxPost(url, {groupId:groupId},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	createOrganiz: function(organiz, callback, options) {
		var url = "/admin/org/create";
		ajaxPost(url, organiz,
				function(result) {
					if (callback) {
						callback(result);
					}
				}, null, options);
	},
	updateOrganiz: function(organiz, callback, options) {
		var url = "/admin/org/update";
        ajaxPost(url, organiz,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);		
	},
	assignUser: function(data,callback, options) {
		var url = "/admin/org/updateOrgUserBatch";
        ajaxPost(url, data,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
	},
	createNotice: function(notice, callback, options) {
		var url = "/system/notice/go";
		var param = [];
		param[0] = notice;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
					if (callback) {
						callback(result);
					}
				}, null, options);
	},
	updateNotice: function(notice, callback, options) {
		var url = "/system/notice/update";
		var param = [];
	    param[0] = notice;
        ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);		
	},
	createSiteNotice: function(notice, callback, options) {
		var url = "/admin/notice/go";
		var param = [];
		param[0] = notice;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	updateSiteNotice: function(notice, callback, options) {
		var url = "/admin/notice/update";
		var param = [];
		param[0] = notice;
		ajaxPost(url, {data:JSON.stringify(param)},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);		
	},
    createSite: function(siteInfo, userInfo, config, callback, options) {
        var url = "/system/site/create";
        var param = [];
        param[0] = siteInfo;
        param[1] = userInfo;
        config.data = JSON.stringify(param);
        ajaxPost(url, config,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);  
    },
    updateSite: function(siteInfo, userInfo, config, callback, options) {
    	var url = "/system/site/update";
        var param = [];
        param[0] = siteInfo;
        param[1] = userInfo;
        config.data = JSON.stringify(param);
        ajaxPost(url, config,
                function(result) {
                    if (callback) {
                        callback(result);
                    }
                }, null, options);
    },
    sendSiteEmail: function(siteInfo, userInfo) {
    	var param = [];
    	param[0] = siteInfo;
    	param[1] = userInfo;
		var url = "/system/site/sendEmail";
		ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
                    
                }, null, null);
	},
	updateSysEmailTemplate: function(type, callback) {
		var url = "/system/email/viewSysTemplate";
		ajaxPost(url, {tempType:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	saveSysEmailTemplate: function(id, emailContent, type, callback) {
		var url = "/system/email/updateSysTemplate";
		ajaxPost(url, {id:id, emailContent:emailContent, emailType:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);	
	},
	resetSysEmailTemplate: function(type, callback) {
		var url = "/system/email/recoverSysTemplate";
		ajaxPost(url, {type:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);	
	},
	updateAdminEmailTemplate: function(type, callback) {
		var url = "/admin/email/viewSiteTemplate";
		ajaxPost(url, {tempType:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	saveAdminEmailTemplate: function(id, emailContent, type, callback) {
		var url = "/admin/email/updateSiteTemplate";
		ajaxPost(url, {id:id, emailContent:emailContent, emailType:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);	
	},
	resetAdminEmailTemplate: function(type, callback) {
		var url = "/admin/email/recoverSiteTemplate";
		ajaxPost(url, {type:type},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);	
	},
	forgotPass: function(authCode, type, random, systemEmail, callback) {
		var url = "/system/password/sendEmail";
		ajaxPost(url, {authCode:authCode, type: type, random:random, systemEmail:systemEmail},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	resetPass: function(params, callback) {
		var url = "/system/password/save";
		ajaxPost(url, params,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	forceResetUserPass: function(data, callback, options) { //重置企业用户密码
		var url = "/user/password/resetPass";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);			
	},
	forceResetAdminPass: function(data, callback, options) { //重置站点管理员密码
		var url = "/admin/password/resetPass";
		ajaxPost(url, data,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);			
	},
	forceResetSysPass: function(data, callback, options) {    //重置系统管理员密码
		var url = "/system/password/resetPass";
		ajaxPost(url, data,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);			
	},
	forgotAdminPass: function(authCode, type, random, systemEmail, callback) {
		var url = "/admin/password/sendEmail";
		ajaxPost(url, {authCode:authCode, type: type, random:random, systemEmail:systemEmail},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	resetAdminPass: function(params, callback) {
		var url = "/admin/password/save";
		ajaxPost(url, params,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	forgotUserPass: function(authCode, type, random, userEmail, callback) {
		var url = "/user/password/sendEmail";
		ajaxPost(url, {authCode:authCode, type: type, random:random, userEmail:userEmail},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	resetUserPass: function(params, callback) {
		var url = "/user/password/save";
		ajaxPost(url, params,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	bookMeeting: function(data, callback, options) {
		var url = "/user/conf/createNewReservationConf";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	updateBookMeeting: function(data, callback, options) {
		var url = "/user/conf/updateConfInfo";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	updateCycleMeeting: function(data, callback, options) {
		var url = "/user/conf/updateCycleConfInfo";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	loadMoreConf: function(data, callback, options) {
		var url = "/user/conf/getMoreControlPadConf";
		if(options){
			if(options.beginTime){
				url = addUrlParam(url, "beginTime", options.beginTime);
			}
			if(options.endTime){
				url = addUrlParam(url, "endTime", options.endTime);
			}
			if(options.confName){
				url = addUrlParam(url, "confName", options.confName, true);
			}
		}
		url = addUrlParam(url, "t", (new Date()).getTime());
		ajaxGet(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);	
	},
	/*
	 * 重新创建会议
	 */
	reCreateConf: function(data, callback, options) {
		var url = "/user/conf/reCreateconfInfo";
		ajaxPost(url, data,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);		
	},
	delConf: function(confId, callback, options) {
		var url = "/user/conf/delete/"+confId;
		ajaxPost(url, null,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	delAllConf: function() {
		
	},
	updateCycleOneMeeting: function(data, callback, options) {
		var url = "/user/conf/updateSingleCycleConfInfo";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	bookMeetingSuccess: function(id, callback) {
		var url = "/user/conf/returnNewConf/"+id;
		ajaxGet(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	tempMeeting: function(data, callback, options) {
		var url = "/user/conf/createNewImmediatelyConf";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	joinImmediaMeeting: function(cId, callback) {
		var url = "/join?cId="+cId;
		ajaxPost(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},	
	quickMeeting: function(data, callback) {
		var url = "";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	addContact: function(data, callback) {
		var url = "/user/contact/saveSingle";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	addContacts: function(data, callback, options) {
		var url = "/user/contact/importBatchByContacts";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	addGroup: function(data, callback) {
		var url = "/user/group/save";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);		
	},
	inventContact: function(param, callback, options) {
		var url = "/user/contact/sendinvites";
		ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);
	},
	remindConfUser: function(confId, callback, options) {
		var url = "/user/contact/remindConfUser";
		ajaxPost(url, {confId:confId},
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	},
	importContact: function(data, callback) {
		var url = "/user/group/doImport";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	importContactForGroup: function(data, callback, options) {
		var url = "/user/group/doImportSyn";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);
	},
	delContactFromGroup: function(id, groupId, callback, options) {
		var url = "/user/group/delContactsFormGroup?id="+id+"&group_id="+groupId;
		ajaxPost(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	sendNoticeEmail: function(data, callback, options) {
		var url = "/user/conf/addReminds";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	addSiteOrg: function(data, callback, options) {
		var url = "/admin/org/create";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);		
	},
	loadSiteOrg: function(callback) {
		var url = "/admin/org/orgListIndex";
		ajaxGet(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	delSiteOrg: function(id, callback, options) {
		var url = "/admin/org/delete/"+id;
		ajaxPost(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);			
	},
	//移除该组织机构的用户
	delUserFromOrg: function(id, callback, options) {
		var url = "/admin/org/removeUserFromOrg/"+id;
		ajaxPost(url, null,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);			
	},
	getLevelOrg: function(id, callback) {
		var url = "/user/contact/orgListForJson/"+id;
		ajaxGet(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	},
	getAdminLevelOrg: function(id, callback) {
		var url = "/admin/org/orgListForJson/"+id;
		ajaxGet(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
	}
};

/*!
 * jQuery Cookie Plugin v1.3.0
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function(g,b,h){var a=/\+/g;
function e(i){return i;}function c(i){return f(decodeURIComponent(i.replace(a," ")));}function f(i){if(i.indexOf('"')===0){i=i.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\");
}return i;}var d=g.cookie=function(r,q,w){if(q!==h){w=g.extend({},d.defaults,w);if(q===null){w.expires=-1;}if(typeof w.expires==="number"){var s=w.expires,v=w.expires=new Date();
v.setDate(v.getDate()+s);}q=d.json?JSON.stringify(q):String(q);return(b.cookie=[encodeURIComponent(r),"=",d.raw?q:encodeURIComponent(q),w.expires?"; expires="+w.expires.toUTCString():"",w.path?"; path="+w.path:"",w.domain?"; domain="+w.domain:"",w.secure?"; secure":""].join(""));
}var j=d.raw?e:c;var u=b.cookie.split("; ");var x=r?null:{};for(var p=0,n=u.length;p<n;p++){var o=u[p].split("=");var k=j(o.shift());var m=j(o.join("="));
if(d.json){m=JSON.parse(m);}if(r&&r===k){x=m;break;}if(!r){x[k]=m;}}return x;};d.defaults={};g.removeCookie=function(j,i){if(g.cookie(j)!==null){g.cookie(j,null,i);
return true;}return false;};})(jQuery,document);

/*
Watermark v2.0 (June 2, 2009) plugin for jQuery
Copyright (c) 2009 Todd Northrop
http://www.speednet.biz/
Licensed under GPL 3, see  <http://www.gnu.org/licenses/>
*/
(function(e){var c,j="watermark",g="watermarkClass",b="watermarkFocus",h="watermarkSubmit",d="watermarkMaxLength",f="watermarkPassword",k="watermarkText",a=":data("+j+")",i=":text,:password,textarea";e.extend(e.expr[":"],{data:function(m,l,o,q){var n,p=/^((?:[^=!^$*]|[!^$*](?!=))+)(?:([!^$*]?=)(.*))?$/.exec(o[3]);if(p){n=e(m).data(p[1]);if(n!==c){if(p[2]){n=""+n;switch(p[2]){case"=":return(n==p[3]);case"!=":return(n!=p[3]);case"^=":return(n.slice(0,p[3].length)==p[3]);case"$=":return(n.slice(-p[3].length)==p[3]);case"*=":return(n.indexOf(p[3])!==-1)}}return true}}return false}});e.watermark={className:"watermark",hide:function(l){e(l).filter(a).each(function(){e.watermark._hide(e(this))})},_hide:function(o,m){if(o.val()==o.data(k)){o.val("");if(o.data(f)){if(o.attr("type")==="text"){var n=o.data(f),l=o.parent();l[0].removeChild(o[0]);l[0].appendChild(n[0]);o=n}}if(o.data(d)){o.attr("maxLength",o.data(d));o.removeData(d)}if(m){o.attr("autocomplete","off");window.setTimeout(function(){o.select()},0)}}o.removeClass(o.data(g))},show:function(l){e(l).filter(a).each(function(){e.watermark._show(e(this))})},_show:function(q){var p=q.val(),o=q.data(k);if(((p.length==0)||(p==o))&&(!q.data(b))){if(q.data(f)){if(q.attr("type")==="password"){var n=q.data(f),m=q.parent();m[0].removeChild(q[0]);m[0].appendChild(n[0]);q=n}}if(q.attr("type")==="text"){var l=q.attr("maxLength");if((l>0)&&(o.length>l)){q.data(d,l);q.attr("maxLength",o.length)}}q.val(o);q.addClass(q.data(g))}else{e.watermark._hide(q)}},hideAll:function(){e.watermark.hide(i)},showAll:function(){e.watermark.show(i)}};e.fn.watermark=function(o,n){var m=(typeof(o)==="string"),l=(typeof(n)==="string");return this.filter(i).each(function(){var s=e(this);if(s.data(j)){if(m||l){e.watermark._hide(s);if(m){s.data(k,o)}if(l){s.data(g,n)}}}else{s.data(k,m?o:"");s.data(g,l?n:e.watermark.className);s.data(j,1);if(s.attr("type")==="password"){var p=s.wrap("<span>").parent();var r=e(p.html().replace(/type=["']?password["']?/i,'type="text"'));r.data(k,s.data(k));r.data(g,s.data(g));r.data(j,1);r.focus(function(){e.watermark._hide(r,true)});s.blur(function(){e.watermark._show(s)});r.data(f,s);s.data(f,r)}else{s.focus(function(){s.data(b,1);e.watermark._hide(s,true)}).blur(function(){s.data(b,0);e.watermark._show(s)})}var q=e(this.form);if(!q.data(h)){q.data(h,this.form.submit);q.submit(e.watermark.hideAll);this.form.submit=function(){e.watermark.hideAll();q.data(h).apply(q[0],arguments)}}}e.watermark._show(s)}).end()}})(jQuery);
