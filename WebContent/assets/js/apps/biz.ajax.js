/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/
var LOGDEBUG = false;
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
	updateOrganiz: "updateOrganiz",
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
var COOKIE_SPLIT="##########";//COOKIE  Value的分割符


/**
 * JavaScript 计算字符串长度
 * 		一个中文按两个算
 */
String.prototype.Lenb = function() {
    return this.replace(/[^\x00-\xff]/g, "**").length;
};
/**
 * 字符串替换   替换所有的符合条件的字符串 新关键字
 */
String.prototype.replaceAll = function(oldStr, newStr) {
    var regex = new RegExp(oldStr, "g");
    return this.replace(regex, newStr);
};

/* Debug Utility */
function logDebug(message) {
	if (window.console && window.console.debug) {
        window.console.log(message);
    }
}

function setCursor(id, position) {
	var txtFocus = document.getElementById(id);
    if ($.browser.msie) {
        var range = txtFocus.createTextRange();
        range.move("character", position);
        range.select();
    } else {
       txtFocus.setSelectionRange(position, position);
       txtFocus.focus();
   }
}

function formatIpUrl(param) {
	var url = "http://"+param+".confcloud.cn:80/test/logo.png";
	url = addT(url);
	return url;
}
function getUrlParams(){
  var params = [], hash;
  var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
  for(var i = 0; i < hashes.length; i++)
  {
    hash = hashes[i].split('=');
    params.push(hash[0]);
    var value = hash[1];
    if (value && value.indexOf('#')>=0) {
    	value = value.substring(0, value.indexOf('#'));
    }
    params[hash[0]] = decodeURIComponent(value);
  }
  return params;
}
function getUrlParam(name){
  return getUrlParams()[name];
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
/* Ajax Methods */
function ajaxGet(url, data, sucessCallback, failureCallback, options) {
    ajaxLoad("get", url, data, sucessCallback, failureCallback, options);
}
function ajaxPost(url, data, sucessCallback, failureCallback, options) {
    ajaxLoad("post", url, data, sucessCallback, failureCallback, options);
}
function ajaxLoad(type, url, data, sucessCallback, failureCallback, options) {
    var postloading = null;
    var pageLoading = "true";
    if(options && options.pageLoading && options.pageLoading == "false") {
    	pageLoading = "false";
    }
    var dataType = "text";
    $.ajax({
        type: type,
        url: url,
        data: data,
        beforeSend: function(XMLHttpRequest) {
            if (pageLoading=="true" && options && options.message) {
                postloading = showPostLoading(options.message, options.ui);
            }
        },
        success: function(data, textStatus, XmlHttpRequest) {
            if (pageLoading=="true" && postloading) {
                hidePostLoading(postloading);
            }
            if (sucessCallback) {
    			if(dataType=="text" || dataType=="json"){
    				try {
        				data = JSON.parse(data);	
					} catch (e) {
						
					}
    			}	
                sucessCallback(data);
            }
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            if (pageLoading=="true") {
                hidePostLoading(postloading);
            }
            //alert('请求出现错误，请检查网络');
            if(failureCallback){
            	failureCallback();
            }
        }
    });
}

/*获取最上层document*/
function getTopDoc() {
  if (window.top!=window.self){
  	return window.top.document;
  } else {
  	return window.document;
  }
}
/*获取最上层窗口可见宽度和高度*/
function getTopScrSize() {
  return {
    width: getTopDoc().documentElement.clientWidth,
    height: getTopDoc().documentElement.clientHeight
  };
}
/*获取最上层文档的宽度和高度*/
function getTopDocSize() {
  return {
    width : getTopDoc().body.clientWidth,
    height: getTopDoc().body.clientHeight
  };
}
/*获取滚动条的高度*/
function getScrollTop()
{
    var scrollTop=0;
    if(getTopDoc().documentElement && getTopDoc().documentElement.scrollTop)
    {
        scrollTop=getTopDoc().documentElement.scrollTop;
    }
    else if(getTopDoc().body)
    {
        scrollTop=getTopDoc().body.scrollTop;
    }
    return scrollTop;
}
/*显示加载数据提示*/
function showPostLoading(message, ui) {
    var doc = ui.document;
    var containerId = "load-container";
    var container = doc.getElementById(containerId);
    if (!container) {
        container = doc.createElement("div");
        container.id = containerId;
        var docWdith = getTopDocSize().width;
        var docHeight = getTopDocSize().height;
        var screenHeight = getTopScrSize().height;
        var scrollTop = getScrollTop();
//        alert("docHeight="+docHeight+ " screenHeight="+screenHeight + " scrollTop="+scrollTop);
        //遮罩
        var overLay = doc.createElement("div");
        overLay.style.position = "absolute";
        overLay.style.zIndex = "2000";
        overLay.style.width =  docWdith + "px";
        overLay.style.height =  docHeight + "px";
        overLay.style.top = "0px";
        overLay.style.left = "0px";
        overLay.style.background = "#000000";
        overLay.style.filter = "alpha(opacity=50)";
        overLay.style.opacity = "0.5";
        container.appendChild(overLay);
        //内容
        var content = doc.createElement("div");
        content.style.position = "absolute";
        content.style.zIndex = "2002";
        content.style.left =  docWdith/2 + "px";
        content.style.top = scrollTop + screenHeight/2 + "px";
        var loadingDiv = doc.createElement("div");
        loadingDiv.style.width = "32px";
        loadingDiv.style.height = "32px";
        loadingDiv.style.background = "#FFFFFF";
        loadingDiv.style.cssFloat = "left";//styleFloat for ie
        var loadingImg = doc.createElement("img");
        loadingImg.src = "/assets/images/animate/loading.gif";
        var contentText = doc.createElement("div");
        contentText.id = "loadMsg";
        contentText.innerHTML = message;
        contentText.style.height = "32px";
        contentText.style.lineHeight = "32px";
        contentText.style.cssFloat = "left";//styleFloat for ie
        contentText.style.marginLeft = "15px";
        loadingDiv.appendChild(loadingImg);
        content.appendChild(loadingDiv);
        content.appendChild(contentText);
        container.appendChild(content);
        ui.document.body.appendChild(container);
    } else {
    	var loadMsg = doc.getElementById("loadMsg");
    	loadMsg.innerHTML = message;
    }
    container.style.display = "block";
    return container;
}

/*隐藏加载数据提示*/
function hidePostLoading(loading) {
	if (loading!=null) {
//		loading.style.display = "none";
		loading.parentNode.removeChild(loading);
	}
}
var app = {
	/*
	 * 企业用户登录
	 */
	userLogin: function(data, callback, options) {
		var url = "/user/login/check";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, {pageLoading: "false"});			
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
                }, null, {pageLoading: "false"});		
	},
	resetUserPass: function(params, callback) {
		var url = "/user/password/save";
		ajaxPost(url, params,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, {pageLoading: "false"});		
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
	/*
	 * 系统前缀sysXXX 
	 * 预约会议
	 */
	bookMeeting: function(data, callback, options) {
		var url = "/user/conf/createNewReservationConf";
		ajaxPost(url, data,
        function(result) {
			if (callback) {
                callback(result);
            }
        }, null, options);	
	},
	//修改预约会议
	updateBookMeeting: function(data, callback, options) {
		var url = "/user/conf/updateConfInfo";
		ajaxPost(url, data,
        function(result) {
			if (callback) {
                callback(result);
            }
        }, null, options);		
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
	//修改循环会议中的所有会议
	updateCycleMeeting: function(data, callback, options) {
		var url = "/user/conf/updateCycleConfInfo";
		ajaxPost(url, data,
	    function(result) {
			if (callback) {
	            callback(result);
	        }
	    }, null, options);		
	},
	//修改循环中的一条
	updateCycleOneMeeting: function(data, callback, options) {
//		var url = "/user/conf/updateSingleCycleConfInfo";
		var url = "/user/conf/updateSingleCycleConf";
		ajaxPost(url, data,
        function(result) {
			if (callback) {
                callback(result);
            }
        }, null, options);		
	},
	sysUserLogin: function(data, callback, options) {
		var url = "/system/login";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);			
	},
	sysForgotPass: function(data, callback, options) {
		var url = "/system/forgotpass";
		ajaxPost(url, data,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);
	},
	testJson: function() {
    	var url = "/system/thumbnail";
        ajaxGet(url, null,
                function(result) {
        			//console.log(result);
        			if(callback) {
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
	saveSiteContact: function(data, callback) {
		var url = "/admin/contact/saveSingle";
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
	delContactFromGroup: function(id, groupId, callback, options) {
		var url = "/user/group/delContactsFormGroup?id="+id+"&group_id="+groupId;
		ajaxPost(url, null,
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
	inventContact: function(param, callback, options) {
		var url = "/user/contact/sendinvites";
		ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);
	},
	inventContactMsg: function(param, callback, options,failcallback) {
		var url = "/user/conf/inviteMeetingNotice";
		ajaxPost(url, {data:JSON.stringify(param)},
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, failcallback, options);
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
	getLevelOrg: function(id, callback) {
		var url = "/user/contact/orgListForJson/"+id;
		ajaxGet(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, null);
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
	tempMeeting: function(data, callback, options) {
		var url = "/user/conf/createNewImmediatelyConf";
		ajaxPost(url, data,
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
	//删除部门
	delSiteOrg: function(id, callback, options) {
		var url = "/admin/org/delete/"+id;
		ajaxPost(url, null,
                function(result) {
					if (callback) {
		                callback(result);
		            }
                }, null, options);			
	},
	//分配用户部门
	assignUser: function(data,callback, options) {
		var url = "/admin/org/updateOrgUserBatch";
        ajaxPost(url, data,
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
	/** 更新主持人信息 darren */
	updateHost: function(userBase,callback, options) {
		var url = "/admin/site/saveHost";
		ajaxPost(url,userBase,
				function(result) {
			if (callback) {
				callback(result);
			}
		}, null, options);
	}
};


String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

String.prototype.Trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");
};
String.prototype.isEmpty = function() {
	if (this == null || this.trim().length == 0) {
		return true;
	} else {
		return false;
	}
};
String.prototype.isDigit = function() {
    if (this.isEmpty()) {
        return false;
    }
    var regex = /^\d+$/;
    return regex.test(this);
};

function getMainDomain(){
	var domain=document.domain;
	if(domain!=null && domain.length>0){
		var firstDotIndex=domain.indexOf(".");
		domain=domain.substr(firstDotIndex+1);
	}
	return domain;
}

function getDomain(){
	return document.domain;
}
function getCookieByDomain(cName,domain){
	var valueSplit=COOKIE_SPLIT;
	var cookie=null;
	var cValue=null;
	if(cName!=null && cName.trim()!=""){
		cookie=document.cookie;
		var cookieArray=cookie.split(";");
		if(cookieArray!=null && cookieArray.length>0){
			var eachCookie=null;
			var eachCookieArray=null;
			var eachName=null;
			var eachValue=null;
			var eachDomain=null;
			var eachTrueValue=null;
			var eachValueArray=null;
			for(var ii=0;ii<cookieArray.length;ii++){
				if(cValue!=null && cValue.trim()!=""){
					break;
				}
				eachCookie=cookieArray[ii];
				if(eachCookie!=null && eachCookie.trim()!=""){
					eachCookieArray=eachCookie.split("=");
					if(eachCookieArray!=null && eachCookieArray.length==2){
						eachName=eachCookieArray[0];
						if(eachName.trim()==cName){
							eachValue=eachCookieArray[1];
							if(domain==null && domain.trim()==""){
								cValue=eachValue;
							}else{
								if(eachValue!=null && eachValue.trim()!=""){
									 eachValueArray=eachValue.split(valueSplit);
								}
								if(eachValueArray!=null && eachValueArray.length==2){
									eachTrueValue=eachValueArray[0];
									eachDomain=eachValueArray[1];
									if(domain==eachDomain.trim()){
										cValue=eachTrueValue;
									}
								}
							}
						}
					}
				}
				eachCookie=null;
				eachCookieArray=null;
				eachName=null;
				eachValue=null;
				eachDomain=null;
				eachTrueValue=null;
				eachValueArray=null;
			}
		}
		
	}
	
	return cValue;
}



function setCookie(cName, cValue, domain) {
	if (!cName.isEmpty() && !cValue.isEmpty()) {
		var cookieSplit=COOKIE_SPLIT;
		//var timeStamp = (new Date()).getTime();
		//timeStamp += (hours * 3600 * 1000);
		//var expireDate = new Date(timeStamp).toGMTString();
		var saveValue = cValue;
		if (!domain.isEmpty()) {
			saveValue = cValue + cookieSplit + domain;
		}
		document.cookie = cName + "=" +  saveValue  + ";path=/;domain=" + domain + ";expires=";// + expireDate;
		timeStamp = null;
		saveValue = null;
		//expireDate = null;
	}
}
function clearCookie(cName, domain) {
	if (!cName.isEmpty()) {
		var timeStamp = (new Date()).getTime();
		timeStamp -= 1000;
		var expireDate = new Date(timeStamp).toGMTString();
		var saveValue = "";
		if (!domain.isEmpty()) {
			document.cookie = cName + "=" + saveValue + ";path=/;domain=" + domain + ";expires=" + expireDate;
		} else {
			document.cookie = cName + "=" + saveValue + ";path=/;expires=" + expireDate;
		}
		timeStamp = null;
		saveValue = null;
		expireDate = null;
	}
}


function getBrowserLang() {
	var domain = getMainDomain();
	var baseLang = getCookieByDomain("LANG",domain);
	if (!baseLang) {
		if (navigator.userLanguage) {
			baseLang = navigator.userLanguage.substring(0,2).toLowerCase();
		} else {
			baseLang = navigator.language.substring(0,2).toLowerCase();
		}
		/* language match */
		switch(baseLang)
		{
			case "en":
				/* english */
				baseLang = "en-us";
				break;
			case "zh":
				/* ${LANG['bizconf.jsp.common.cookie_util.res3']} */
				baseLang = "zh-cn";
				break;
			default:
				/* default no match */
		}
	}
	return baseLang;
}	


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