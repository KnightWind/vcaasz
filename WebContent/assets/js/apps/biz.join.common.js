var SB = {
	version: '3.5.8857.0207',
	contextPath: '',
	baseUrl: 'https://bizconf.zoomus.cn',
	loggedIn: false,
	fbLoggedIn: false,
	fbAppkey: '113289095462482',
	fbAppPage: 'https://apps.facebook.com/zoomvideocall/',
	fbScope: 'email,xmpp_login,user_birthday,user_education_history,user_location,user_relationships'
};
//IE 协议入会
var baseZoomURL = "http://www.zoomus.cn/static/";

/** (IE)主持人开启会议url */
function getStartMeetingUrl(confNo,hostId,uname,token){
	/**
	confNo:zoom会议号
	sid：主持人ID
	stype:默认设置100
	uid：主持人ID
	uname：主场人名称
	token：主持人Token
	*/
	var strs= new Array(); 
	strs = SB.version.split(".");
	var zoomurl = baseZoomURL+strs[2]+"/ClickOnce/BizconfCO.application?dr="+SB.baseUrl
				+"/client&action=start&confno="+confNo+"&sid="+hostId+"&stype=100&uid="+hostId+"&uname="+uname+"&token="+token;
//	var zoomurl = baseZoomURL+strs[2]+"/ClickOnce/ZoomClickOnce.application?dr="+SB.baseUrl
//				+"/client&action=start&confno="+confNo+"&sid="+hostId+"&stype=100&uid="+hostId+"&uname="+uname+"&token="+token;
	window.location = zoomurl;
}


/** (IE)邀请参会者入会url */
function getJoinMeetingUrl(confNo,uuid,pwd){
	/**
	confno:zoom会议号
	confid：会议zoom的id
	pwd：会议密码
	*/
	var strs= new Array();
	strs = SB.version.split(".");
	var zoomurl = baseZoomURL+strs[2]+"/ClickOnce/BizconfCO.application?dr="+SB.baseUrl
				+"/client&action=join&confno="+confNo+"&confid="+uuid+"&pwd="+pwd;
//	var zoomurl = baseZoomURL+strs[2]+"/ClickOnce/ZoomClickOnce.application?dr="+SB.baseUrl
//				+"/client&action=join&confno="+confNo+"&confid="+uuid+"&pwd="+pwd;
	window.location = zoomurl;
}

//IE协议启动会议
function stratMeetingByClickOnce(clickOnceUrl,confNo,hostId,uname,token){
	var zoomurl = clickOnceUrl+"?dr="+SB.baseUrl
	+"/client&action=start&confno="+confNo+"&sid="+hostId+"&stype=100&uid="+hostId+"&uname="+uname+"&token="+token;
	window.location = zoomurl;
}

//IE协议加入会议
function joinMeetingByClickOnce(clickOnceUrl,confNo,uuid,pwd){
	var zoomurl = clickOnceUrl+"?dr="+SB.baseUrl
				+"/client&action=join&confno="+confNo+"&confid="+uuid+"&pwd="+pwd;
	window.location = zoomurl;
}