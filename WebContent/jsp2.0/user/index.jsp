<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${siteBase.siteName}--${LANG['website.common.index.title.base.1']}</title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/assets/css/biz.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.index.css" />
<cc:base var="USERROLE_HOST"></cc:base>
<script type="text/javascript">
	var noticeId = "${sysNotice.id}";
	var loginUser = "${user.id}"; 
	var needResetPass = "${needResetPass}";
	var LOADING_CONSTANT={
			loadingMessage: "${LANG['website.common.loading.message']}"
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
<script type="text/javascript" src="/assets/js/module/biz.widgets.js?var=16"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.index.js?var=12"></script>
<cc:confList var="HOST_JOIN_TYPE_EMAIL"/>

<script type="text/javascript">

function joinMtgFromEmail(){

	<c:if test="${joinFlag==true && joinType==HOST_JOIN_TYPE_EMAIL}">

	var  titleName="${LANG['bizconf.jsp.conf_list_index.res48']}";
	var joinUrl="${joinUrl}";
	joinMeetingFromEmail(joinUrl,titleName);
	</c:if>
	
}


var INDEX_CONSTANT={
		changeSkin:"${LANG['website.user.index.js.message.title.change.skin']}",//切换主题
		userLogin:"${LANG['website.user.index.js.message.title.user.login']}",//用户登录
		viewConfInfo:"${LANG['website.user.index.js.message.title.view.confinfo']}",//查看会议详情
		viewConfInfoCopy:"${LANG['website.user.index.js.message.title.view.confinfo.copy']}",//复制会议详情
		addContacts:"${LANG['website.user.index.js.message.title.add.contacts']}",//添加联系人
		joinedInfo:"${LANG['website.user.index.js.message.title.joined.info']}",//参会详情
		modifyConfInfo:"${LANG['website.user.index.js.message.title.modify.confinfo']}",//修改预约会议
		addConfInfo:"${LANG['website.user.index.js.message.title.add.confinfo']}",//预约会议
		recreateConfInfo:"${LANG['website.user.index.js.message.title.recreate.confinfo']}",//重新创建会议
		modifyCycleAll:"${LANG['website.user.index.js.message.title.modify.cycle.all']}",//修改周期会议中所有会议的信息
		systemNotice:"${LANG['website.user.index.js.message.title.system.notice']}",//公告通知(仅系统管理员发布的公告)
		modifyPassword:"${LANG['website.user.index.js.message.title.modify.password']}",//修改密码(登录后的强制修改密码)
		modifyContacts:"${LANG['website.user.index.js.message.title.modify.contacts']}",//编辑联系人
		addContactsBatch:"${LANG['website.user.index.js.message.title.modify.contacts.batch']}",//批量添加联系人
		selectContacts:"${LANG['website.user.index.js.message.title.select.contacts']}",//从通讯录中选择
		importSucceed:"${LANG['website.user.index.js.message.title.import.succeed']}",//批量导入联系人成功
		addGroup:"${LANG['website.user.index.js.message.title.add.group']}",//添加群组
		modifyGroup:"${LANG['website.user.index.js.message.title.modify.group']}",//编辑群组
		groupUsers:"${LANG['website.user.index.js.message.title.group.users']}",//群组成员
		selectContactsForGroup:"${LANG['website.user.index.js.message.title.select.contacts.for.group']}",//添加联系人到群组
		inviteList:"${LANG['website.user.index.js.message.title.invite.list']}",//邀请人列表
		inviteFromContacts:"${LANG['website.user.index.js.message.title.invite.from.contacts']}",//从通讯录中邀请
		inviteConfUser:"${LANG['website.user.index.js.message.title.invite.confuser']}",//邀请与会者
		inviteConfUserMsg:"${LANG['website.user.index.js.message.title.invite.confuser.msg']}",//短信邀请
		billInfo:"${LANG['website.user.index.js.message.title.bill.info']}",//账单详情
		emailRemind:"${LANG['website.user.index.js.message.title.email.remind']}",//邮件提醒
		immeConf:"${LANG['website.user.index.js.message.title.imme.conf']}",//即时会议
		joinFromCode:"${LANG['website.user.index.js.message.title.join.from.code']}",//会议ID加入
		remindCommon:"${LANG['website.user.index.js.message.title.remind.common']}",//提醒
		joinConf:"${LANG['website.user.index.js.message.title.join.conf']}",//加入会议
		confirm:"${LANG['website.common.option.confirm']}",//确定
		cancel:"${LANG['website.common.option.cancel']}",//取消
		faverSetting:"${LANG['website.user.leftmenu.setting']}",//取消
		loginMessage:"${LANG['website.user.js.message.logout.prompt']}"//确定要退出吗？,
};




function joinMeetingReload(){
	
	var domain=getDomain(); 
	var reloadValue=getCookieByDomain("reload",domain);
	if(reloadValue==null || reloadValue==""){
		return null;
	}
	var reloadArr=reloadValue.split(",");
	if(reloadArr.length!=7){
		return null;
	}

	var reload=reloadArr[0];
	var cId=reloadArr[1];
	var userName=reloadArr[2];
	var joinType=reloadArr[3];
	var code=reloadArr[4];
	var cpass=reloadArr[5];
	var rId=reloadArr[6];
	
	//alert("reload=="+reload);
	$("#reload").val(reload);
	$("#cId").val(cId);
	$("#rId").val(rId);
	$("#userName").val(userName);
	$("#joinType").val(joinType);
	$("#code").val(code);
	$("#cPass").val(cpass);
	
	if(joinType==null || joinType==""){
		return false;
	}
	var titleName=INDEX_CONSTANT.joinConf;
	/*
	if(joinType=="${JOIN_TYPE_CONFID}"){
		titleName=INDEX_CONSTANT.joinConf;
	}
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-smile",
		"iconClass":"icon-join",
		"url" : "/join/joinpage?joinType="+joinType+"&cId="+cId,
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" : divHeight
	});
	*/
	
	
	$("<div id=\"joinMeeting\"/>").showDialog({
		"title" : titleName,
		"dialogClass" : "ui-dialog-smile  ui-dialog-notitle",
		"iconClass":"icon-join",
		"url" : "about:blank",
		//"data":{"cId":cId,"cPass":cPass,"code":code,"reLoad":reLoad,"joinType":joinType,"userName":userName},
		"type" : VIEW_TYPE.joinMeeting,
		"action" : ACTION.join,
		"width" : 474,
		"height" : "auto"
	});
	
	var frameObj=document.getElementById("dialogFrame");
	//alert("frameObj=="+frameObj);
	if(frameObj){
		document.getElementById("reLoadJoinConf").submit();
	}
	
}

</script>

</head>
<body>
    <div class="header">
    	<jsp:include page="header.jsp" />
    </div>
    <div class="nav">
    	<div class="profile">
    		<c:if test="${!empty user}">
	    		<i class="icon icon_login">&nbsp;</i>
	    		<span class="profile_name mr5 nobr" title="${LANG['website.user.headmenu.welcome']}, ${user.trueName }">${LANG['website.user.headmenu.welcome']}, ${user.trueName }</span>
	    		<a class="profile_action" href="javascript:confirmLogout()" >${LANG['website.common.logout.name']}</a>
		  	</c:if>
		  	<c:if test="${empty user }">
			  	<i class="icon icon_login">&nbsp;</i>
	    		<a class="profile_action" onclick="return userLogin()">${LANG['website.common.login.name']}</a>
		  	</c:if> 
    	</div>
    	<ul class="shortcut">
    	<c:if test="${user==null ||  (user!=null && user.userRole==USERROLE_HOST)}">
    		<!-- 即时会议 -->
    		<li>
    			<i class="icon icon_now">&nbsp;</i>
    			<a onclick="return intime()">
    				${LANG['website.user.headmenu.instant']}
    			</a>
    		</li>
    		<!-- 预约会议 -->
    		<li>
    			<i class="icon icon_schedule">&nbsp;</i>
    			<a onclick="return createReservationConf()">
    				${LANG['website.user.headmenu.appoint']}
    			</a>    			
    		</li>
    	</c:if>
    	<!-- 通过会议ID加入 -->
    		<li>
    			<i class="icon icon_joinin">&nbsp;</i>
    			<a onclick="return joinByNum()">
    				 ${LANG['website.user.headmenu.join.secure']}
    			</a>
    		</li>
    	</ul>
    	<div class="region"> <form name="langForm" id="langForm">
    		<c:set var="fullTimeZoneDesc" value="website.timezone.city.${siteBase.timeZoneId}"/>
			<a class="timezone" onclick="return jumpToFavor()"><i class="icon icon-zone"></i>${LANG[fullTimeZoneDesc]} ${LANG['website.common.time.name']}</a> 
			<span class="language"> 
			
				<select name="jumpMenu_language" id="jumpMenu_language" >
					<option value="zh-cn" ${currentLanguage == 'zh-cn' ? 'selected' : ''}> ${LANG['website.common.language.zh']}</option>
					<option value="en-us" ${currentLanguage == 'en-us' ? 'selected' : ''}> ${LANG['website.common.language.en']}</option>
				</select>
			</span>    	
			</form>
    	</div>
    </div>
	<table class="main-table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="left-td" valign="top" >
                <jsp:include page="left.jsp" />
            </td>
            <td id="toggelSlide" valign="top" style="cursor:pointer;background: #EEEEEE" >
                <div style="width:5px;">&nbsp;</div>
            </td>
            <td class="right-td" valign="top" width="100%">
            <c:if test="${!isLogined}">
                <iframe allowtransparency='true' frameborder="0" width="100%" id="mainFrame" name="mainFrame" scrolling="no" 
                	src="/user/conf/getPublicControlPadIndex"></iframe>
            </c:if>
            <c:if test="${isLogined}">
           		 <c:if test="${isConfHost}"> 
               		<iframe allowtransparency='true' frameborder="0" width="100%" id="mainFrame" name="mainFrame" scrolling="no" 
                	src="/user/conf/getControlPadIndex?userRole=1#"></iframe>
                </c:if>
           		 <c:if test="${!isConfHost}"> 
               		<iframe allowtransparency='true' frameborder="0" width="100%" id="mainFrame" name="mainFrame" scrolling="no" 
                	src="/user/conf/getControlPadIndex?userRole=2#"></iframe>
                </c:if>
            </c:if>
            </td>
        </tr>
	</table>
    <div class="footer">
        <jsp:include page="footer.jsp" /> 
    </div>
    
    
  <div style="display:none" id="reloadDiv">
  <form name="reLoadJoinConf" id="reLoadJoinConf" action="/join?" method="post" target="dialogFrame">
  	<input type="hidden" name="cId" id="cId" value=""/>
  	<input type="hidden" name="rId" id="rId" value=""/>
  	<input type="hidden" name="cPass" id="cPass" value=""/>
  	<input type="hidden" name="joinType" id="joinType" value=""/>
  	<input type="hidden" name="userName" id="userName" value=""/>
  	<input type="hidden" name="reload" id="reload" value=""/>
  	<input type="hidden" name="code" id="code" value=""/>
  		<!-- setCookie("reload","1,${cId},${userName},${joinType},${code},${cPass}",domain); -->
  </form>
  </div>
</body>
</html>

<script type="text/javascript">
	function changeLang(langVal){
		var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/";
		window.location.href=jumpUrl;
	}
	
	$(document).ready(function(){
		var pop = '${popSeeting}';
		if(pop == 'true'){
			resetFaverSetting();				
		}
	});
</script>