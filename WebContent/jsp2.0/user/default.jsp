<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${siteBase.siteName}--${LANG['website.common.index.title.base.1']}</title>
    <!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
    <!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
    <!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
    <link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/app/default.css?var=11" />
	<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.index.css" />
	<link rel="stylesheet" type="text/css" href="/Formal/css/jquery.qtip.min.css" />
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <cc:base var="USERROLE_HOST"></cc:base>
	<script type="text/javascript">
		var noticeId = "${sysNotice.id}";
		var loginUser = "${user.id}"; 
		//var needResetPass = "${needResetPass}";
		var needResetPass = "false";
		var LOADING_CONSTANT={
				loadingMessage: "${LANG['website.common.loading.message']}"
		};
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
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/Formal/js/tip/jquery.qtip.min.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.widgets.js?var=16"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.index.js"></script>
	<cc:confList var="HOST_JOIN_TYPE_EMAIL"/>
  </head>
  <body>
    <header id="header">
      <div class="wrapper">
<%--        <div id="logo">--%>
<%--          <img alt="${LANG['bizconf.jsp.conf.bizconf.head.title.alt'] }" src="/Formal/images/home/logo.png" />--%>
<%--        </div>--%>
        
        <div id="logo">
			<c:if test="${!empty siteBase.siteLogo}">
    			<p><img class="fix-png" height="37px;" src="${siteBase.siteLogo}" onerror="this.src='/Formal/images/home/logo.png';" /></p>
  			</c:if>
  			<c:if test="${empty siteBase.siteLogo}">
	  			<p><img class="fix-png" src="/Formal/images/home/logo.png" onload="javascript:DrawImage(this,120,60)"/></p>
  			</c:if>
		</div>
        <c:choose>
        	<c:when test="${empty siteBase.siteName}">
       			 <div class="name">${LANG['bizconf.jsp.conf.bizconf.head.title'] }</div>
        	</c:when>
        	<c:otherwise>
       			 <div class="name">${siteBase.siteName}</div>
        	</c:otherwise>
        </c:choose>

        <nav>
          <a class="red" onclick="return createReservationConf();"><i class="icon"></i>${LANG['website.user.headmenu.appoint'] }</a>
          <a class="green" onclick="return joinByNum();"><i class="icon"></i>${LANG['website.user.index.js.message.title.join.conf'] }</a>
          <a style="width: 124px;" class="blue" onclick="return jumpToFavor();">
          	<c:set var="fullTimeZoneDesc" value="website.timezone.city.${siteBase.timeZoneId}"/>
          	<i class="icon"></i>${LANG[fullTimeZoneDesc]} ${LANG['website.common.time.name']}
          </a>
          <c:choose>
          		<c:when test="${currentLanguage eq 'en-us'}">
          			<a class="cyan" data="zh" name="jumpMenu_language" id="jumpMenu_language"><i class="icon"></i></a>
          		</c:when>
          		<c:otherwise>
          			<a class="cyan english" data="en" name="jumpMenu_language" id="jumpMenu_language" ><i class="icon"></i></a>
          		</c:otherwise>
          </c:choose>
        </nav>
      </div>
    </header>
    
    <div id="content">
      <div class="wrapper">
        <aside id="lside">
          <div class="personal"  >
            <a class="user" href="" >
              <i class="icon"></i>
              <span title="${LANG['website.user.headmenu.welcome']}, ${user.trueName }">${LANG['website.user.headmenu.welcome']}, 
			  	<c:choose>
			  		<c:when test="${fn:length(user.trueName)<10}">${user.trueName}</c:when>
			  		<c:otherwise>${fn:substring(user.trueName,0,7)}...</c:otherwise>
			  	</c:choose>
			  </span>
            </a>
            <nav>
              <a class="archives" title="${LANG['website.user.leftmenu.setting'] }"><i class="icon"></i></a>
              <a class="safety" title="${LANG['website.user.leftmenu.setting.password'] }"><i class="icon"></i></a>
              <a class="likes" title="${LANG['website.user.leftmenu.preferences'] }"><i class="icon"></i></a>
              <a class="logout" title="${LANG['website.common.logout.name'] }" href="javascript:confirmLogout();"><i class="icon"></i></a>              
            </nav>
          </div>
          <ul class="list">
            <li class="meetings active">
              <a id="myMeetings">
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['user.menu.conf.myconf'] }</span>
              </a>
            </li>
            <li class="contacts">
              <a id="contacts">
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['bizconf.jsp.help.res10'] }</span>
              </a>
            </li>
            <li class="reports">
              <a>
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['bizconf.jsp.hostConfloglist.res2'] }</span>
                <span class="more"></span>
              </a>
              <ul>
                <li>
                  <a id="allReports" class="total-report">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['bizconf.jsp.conf.bizconf.allmeetingreport'] }</span>
                  </a>
                </li>
                <li>
                  <a id="monthReports" class="month-report">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['bizconf.jsp.conf.bizconf.monthlymeetingreport'] }</span>
                  </a>
                </li>
              </ul>
            </li>
            
            <!-- 先判断是否显示账单  -->
            <c:if test="${myfn:billingAvailable(siteBase.siteSign)}">
            <li class="orders" style="">
              <a>
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['website.admin.bill.left.menu.name.1'] }</span>
                <span class="more"></span>
              </a>
              <ul>
                <li>
                  <a class="help" id="myBillTotal">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['website.admin.bill.left.menu.name.2'] }</span>
                  </a>
                </li>
                <li>
                  <a class="download" id="myBillDetail" style="display: none;">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['website.admin.bill.left.menu.name.3'] }</span>
                  </a>
                </li>
              </ul>
            </li>
            </c:if>
            <li class="settings">
              <a id="settings">
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['website.user.leftmenu.config.default'] }</span>
              </a>
            </li>
            <li class="supports">
              <a href="">
                <span class="mark"><i class="icon"></i></span>
                <span>${LANG['website.user.leftmenu.support'] }</span>
                <span class="more"></span>
              </a>
              <ul>
                <li>
                  <a class="help" id="help" href="/help" target="_blank">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['website.user.leftmenu.support.help'] }</span>
                  </a>
                </li>
                <li>
                  <a class="download" id="download">
                    <span class="mark"><i class="icon"></i></span>
                    <span>${LANG['website.user.leftmenu.support.download'] }</span>
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </aside>
        <div id="mainbox">
          <iframe frameborder="0" width="100%" height="600" scrolling="no" src="/user/conf/getControlPadConf?userRole=1" name="mainFrame" id="mainFrame" ></iframe>
        </div>
      </div>
    </div>

    <jsp:include page="/jsp2.0/user/footer.jsp" ></jsp:include>

<%--    <script type="text/javascript" src="/Formal/js/lib/jquery.js"></script>--%>
    <script type="text/javascript" src="/Formal/js/app/default.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.date.js"></script>
	<fmt:formatDate var="nowDate" value="${defaultDate}" type="date" pattern="yyyy-MM-dd"/>
    <script type="text/javascript">
    	function initFrame(){
    		var nowDate = "${nowDate}"; 
			var startDate = getMonthStartDate(nowDate.parseDate()) + " 00:00:00";
			var endDate = getMonthEndDate(nowDate.parseDate().addMonth(1)) + " 00:00:00";
			var timestemp = new Date().getTime();
			var url = "/user/conf/getControlPadConf?userRole=1";
			url = addUrlParam(url, "beginTime", startDate);
			url = addUrlParam(url, "endTime", endDate);
			url = addUrlParam(url, "timestemp", timestemp);
			//alert(url);
			$("#mainFrame").attr("src",url);
    	}
	    $(function(){
	    	//首次登陆设置时区
	    	//var pop = '${popSeeting}';
			//if(pop == 'true'){
			//	resetFaverSetting();				
			//}
	    	initFrame();
	    });
    
    	$(".archives").click(function(){
    		$("li").removeClass("active");
    		$("#mainFrame").attr("src","/user/profile");
    	});
    	$(".safety").click(function(){
    		$("li").removeClass("active");
    		$("#mainFrame").attr("src","/user/profile/passPage");
    	});
    	$(".likes").click(function(){
    		$("li").removeClass("active");
    		$("#mainFrame").attr("src","/user/favor/getTimeZone");
    	});
    	$("#myMeetings").click(function(){
    		initFrame();
    	});
    	$("#contacts").click(function(){
    		$("#mainFrame").attr("src","/user/contact/goContacts");
    	});
    	$("#reports").click(function(){
    		$("#mainFrame").attr("src","");
    	});
    	$("#settings").click(function(){
    		$("#mainFrame").attr("src","/user/confConfig/getConfConfig");
    	});
    	$("#allReports").click(function(){
    		$("#mainFrame").attr("src","/user/conflog/logsList");
    	});
    	$("#monthReports").click(function(){
    		$("#mainFrame").attr("src","/user/conflog/monthlyconf");
    	});
    	$("#help").click(function(){
    		window.open("/help");
    	});
    	$("#download").click(function(){
    		$("#mainFrame").attr("src","/support/download?s=1");
    	});
    	
    	//账单
    	$("#myBillTotal").click(function(){
    		$("#mainFrame").attr("src","/user/billing/listTotalBill");
    	});
    	$("#myBillDetail").click(function(){
    		$("#mainFrame").attr("src","/user/billing/listDetailBills");
    	});
    	
	    function changeLang(langVal){
	    	var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/";
			window.location=jumpUrl;
		}
	    
	    function refreshIHeight(){
	    	
	    }
	    /*
		 *页面上动态调整Iframe的宽高
		 */
		 function resizeHeight(subHigth) {
			var height = $("#mainFrame").contents().find("body").height()+50;
			if(height<710){
				height = 710;
			}
			if(!subHigth){
				$("#mainFrame").height(height);  
				$("#mainbox").height(height);
			}else{
				$("#mainFrame").height(subHigth);  
				$("#mainbox").height(subHigth);
			}
		}
	    
	    $(document).ready(function(){
			$(".archives").qtip();
			$(".safety").qtip();
			$(".likes").qtip();
			$(".logout").qtip();
			//重新定义iframe的高度
			$("#mainFrame").load(function(){
				resizeHeight();  
		    });
		});
    </script>
  </body>
</html>