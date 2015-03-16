<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>${LANG['bizconf.jsp.conf.bizconf.loginpage']}</title>
    <link type="text/css" rel="stylesheet" href="/assets/css/login.css?vat=00" />
    <script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
    <script type="text/javascript">
  	//移动客户端判断
	function browser() {	
	    var u = navigator.userAgent.toLowerCase();
	    var app = navigator.appVersion.toLowerCase();
	    return {
	        txt: u, // 浏览器版本信息
	        version: (u.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1], // 版本号       
	        msie: /msie/.test(u) && !/opera/.test(u), // IE内核
	        mozilla: /mozilla/.test(u) && !/(compatible|webkit)/.test(u), // 火狐浏览器
	        safari: /safari/.test(u) && !/chrome/.test(u), //是否为safair
	        chrome: /chrome/.test(u), //是否为chrome
	        opera: /opera/.test(u), //是否为oprea
	        presto: u.indexOf('presto/') > -1, //opera内核
	        webKit: u.indexOf('applewebkit/') > -1, //苹果、谷歌内核
	        gecko: u.indexOf('gecko/') > -1 && u.indexOf('khtml') == -1, //火狐内核
	        mobile: !!u.match(/applewebkit.*mobile.*/), //是否为移动终端
	        ios: !!u.match(/\(i[^;]+;( u;)? cpu.+mac os x/), //ios终端
	        android: u.indexOf('android') > -1, //android终端
	        iPhone: u.indexOf('iphone') > -1, //是否为iPhone
	        iPad: u.indexOf('ipad') > -1, //是否iPad
	        webApp: !!u.match(/applewebkit.*mobile.*/) && u.indexOf('safari/') == -1 //是否web应该程序，没有头部与底部
	    };
	} 
	
    $(function() {
        // 选项卡效果
        $('.tabs li').on('click', function() {
          $(".error").html("");
          var tab = $(this);
          var line = tab.children('a');
          var name = line[0].hash.replace('#', '');
          tab.add('#' + name).addClass('active').siblings().removeClass('active');
          return false;
        });
        if ($.browser.msie && $.browser.version < 10) {
    		$("#confNo").watermark("${LANG['website.user.view.conf.conf.confCode']}");
    	}
        if ($.browser.msie && $.browser.version < 10) {
    		$("#loginName").watermark("${LANG['bizconf.jsp.admin.arrange_org_user.res9']}");
    	}
        
		if($.browser.msie && $.browser.version < 10) {
    		//$("#loginPass").watermark("${LANG['website.user.password.reset.page.password']}");
			//修改在ie的环境下出现明文
    		var pass = $.cookie("loginPass");
			if(pass){
				$("#loginPass").val(pass);
			}else{
				$("#loginPass").watermark("${LANG['website.user.password.reset.page.password']}");
			}
    	}
        
        /** 如果是移动客户端进行页面重新调整 2014-07-10 add 
        var b = browser();
        if(b.mobile || b.android || b.iPad || b.iPhone){
        	window.location.href = "/jsp2.0/user/mobileLogin.jsp";
        }*/
        
        $("div").css("overflow","hidden");
      });
    </script>
  </head>
<jsp:include page="/jsp2.0/user/loginHead.jsp" />
	<!-- PC终端的显示页面 -->
    <div id="content">
      <div class="wrapper">
        <div class="box">
          <div class="login-module" >
            <div class="pull-right">
              <div class="tabset"><!-- 加入会议和主持会议入口table -->
                <ul class="tabs clearfix">
                  <li class="active"><a href="#join">${LANG['bizconf.jsp.conf_list_index.res48']}</a></li>
                  <li><a href="#conduct">${LANG['website.user.conf.list.join.hostmeeting']}</a></li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="join">
                    <form class="form" id="joinMeetingForm">
                      <div class="error" style="display: none;"><i class="icon"></i>${LANG['website.user.conf.input.confidisno']}${LANG['website.common.symbol.exmark']}</div>
                      <div class="form-group">
                        <input id="confNo" type="text" class="form-control" placeholder="${LANG['website.user.view.conf.conf.confCode']}" />
                      </div>
                      <div class="form-group submit-group">
                        <center><button type="button" class="btn-submit" onclick="joinMeeting();">${LANG['website.user.conf.list.enter.meeting']}</button></center>
                      </div>
                    </form>
                  </div>
                  <div class="tab-pane" id="conduct">
                    <form class="form" id="login-form" method="post" style="height:165px;">
                      <div class="error" style="display: none; line-height: 11px;"><i class="icon"></i>${LANG['website.user.conf.input.emailorpassiserror']}${LANG['website.common.symbol.exmark']}</div>
                      <div class="form-group" style="margin-top: 10px">
                        <input id="loginName" type="text" class="form-control" placeholder="${LANG['bizconf.jsp.admin.arrange_org_user.res9']}" />
                      </div>
                      <div class="form-group">
                        <input id="loginPass" type="password" class="form-control" placeholder="${LANG['website.user.password.reset.page.password']}" />
                      </div>
                      <div class="form-item remember-widget" >
						<div class="widget" style="color: white;">
							<span style="margin-left: 20px">
								<input type="checkbox" name="remember" id="remember">
								<label for="remember">${LANG['website.user.login.loginname.hold'] }</label>
							</span>
							<a style="float:right; margin-right:14px; color: white;" href="/user/password/forget" target="_blank">${LANG['website.user.login.loginpass.forget'] }${LANG['website.common.symbol.quesmark'] }</a>
						</div>
					  </div>
                      <div class="form-group submit-group">
                        <center><button type="submit" class="btn-submit">${LANG['website.user.conf.login.blanklogin'] }</button></center>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <ul class="items clearfix">
            <li>
              <img src="/assets/images/login/hd.png" alt="${LANG['website.user.conf.newlogin.Hdvideo'] }" />
              <h3>${LANG['website.user.conf.newlogin.Hdvideo'] }</h3>
              <p>${LANG['website.user.conf.newlogin.Hdvideo1'] }<br/>${LANG['website.user.conf.newlogin.Hdvideo2'] }<br/>${LANG['website.user.conf.newlogin.Hdvideo3'] }</p>
            </li>
            <li>
              <img src="/assets/images/login/terminal.png" alt="${LANG['website.user.conf.newlogin.fusionterminal'] }" />
              <h3>${LANG['website.user.conf.newlogin.fusionterminal'] }</h3>
              <p>${LANG['website.user.conf.newlogin.fusionterminal1'] }<br/>${LANG['website.user.conf.newlogin.fusionterminal2'] }<br/>${LANG['website.user.conf.newlogin.fusionterminal3'] }</p>
            </li>
            <li>
              <img src="/assets/images/login/platform.png" alt="跨平台" />
              <h3>${LANG['website.user.conf.newlogin.cross-platform'] }</h3>
              <p>${LANG['website.user.conf.newlogin.cross-platform1'] }<br/>${LANG['website.user.conf.newlogin.cross-platform2'] }<br/>${LANG['website.user.conf.newlogin.cross-platform3'] }</p>
            </li>
            <li class="last-child">
              <img src="/assets/images/login/simple.png" alt="操作简易" />
              <h3>${LANG['website.user.conf.newlogin.operationsimple'] }</h3>
              <p>${LANG['website.user.conf.newlogin.operationsimple1'] }<br/>${LANG['website.user.conf.newlogin.operationsimple2'] }</p>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div id="footer">
      <div class="wrapper">
        <p>${LANG['website.user.footer.info.rightreserved']}</p>
        <p>${LANG['website.user.footer.info.hotline']}</p>
      </div>
    </div>
 <script type="text/javascript">
	$(function() {
		var sessionFlag = "${userSessionFlag}";
		if(sessionFlag && sessionFlag=="true"){
			resetPage();
		}
		fillInUserName();
		$.formValidator.initConfig({
			oneByOneVerify:true,
			formID : "login-form",
			debug : false,
			onSuccess : function() {
				login();
			},
			onError : function() {
				//alert("具体错误，请看网页上的提示");
			}
		});
		setCursor("loginName", $("#loginName").val().length);
		
		/**
		$("#confNo").keydown(function(event){
			if(event.keyCode == '13'){
				var meetingNo = $("#confNo").val();
				if(meetingNo){
					var url = "/join/gotojoinPage?joinType=2&code="+meetingNo;//blank表示新打开窗口
					
					var tempwindow=window.open('_blank');
					tempwindow.location = url;
					//window.open(url,"newwin","",false);
					return;
				}else{
					$(".error").css("display","block");
					$(".error").html("<i class=\"icon\"></i>${LANG['website.user.conf.notnull.confid'] }").show();
					return;
				}
			}
		});
		*/
	});	
	
	function joinMeeting(){
		var code = $("#confNo").val();
		if(code == null || code == undefined || code == ""){
			$(".error").css("display","block");
			$(".error").html("<i class=\"icon\"></i>${LANG['website.user.conf.notnull.confid'] }").show();
			return false;
		}else{
			var url = "/join/gotojoinPage?joinType=2&code="+code;//blank表示新打开窗口
			window.open(url);
		}
	}
	
	function login() {
		var userBase = {};
		userBase.loginName = $("#loginName").val();
		userBase.loginPass = $("#loginPass").val();
		//userBase.type = $("#type").val();	
		
		if(userBase.loginName == null || userBase.loginName == "" ||userBase.loginName==undefined ){
			$(".error").css("display","block");
			$(".error").html("<i class=\"icon\"></i>${LANG['website.user.conf.notnull.confaccount'] }").show();
			return;
		}	
		if(userBase.loginPass == null || userBase.loginPass == "" ||userBase.loginPass==undefined ){
			$(".error").css("display","block");
			$(".error").html("<i class=\"	\"></i>${LANG['website.user.login.js.message.loginpass.nowrite'] }").show();
			return;
		}
		app.userLogin(userBase, function(result) {
			if(result) {
				if(result.status==10){
					rememberMe();
					location.href = "/";
				} else {
					$(".error").css("display","block");
					$(".error").html("<i class=\"icon\"></i>"+result.message+"").show();
				}
			}
		});
	}
	
	function rememberMe() {				//cookie 页面级别存储
		var checkRemember = $("#remember").attr("checked");
		if(checkRemember){
			var userName = $("#loginName").val();
			var loginPass = $("#loginPass").val();
			if(userName && loginPass){
				$.cookie("userName", userName, { expires: 7});
				$.cookie("loginPass", loginPass, { expires: 7});
				$.cookie("remember", checkRemember, { expires: 7});
			}	
		} else {
			$.cookie("userName", null);
			$.cookie("loginPass", null);
			$.cookie("remember", null);
		}
	}
	
	function fillInUserName() {			//填充账号/密码和记住我
		var userName = $.cookie("userName");
		var loginPass = $.cookie("loginPass");
		var checkRemember = $.cookie("remember");
		if(userName){
			$("#loginName").val(userName);
		}
		if(loginPass){
			$("#loginPass").val(loginPass);
		}
		if(checkRemember){
			$("#remember").attr("checked",checkRemember);
		}
	}
	function resetPage() {
	    window.top.reloadPage();
	}
	function changeLang(langVal){
		var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/";
		window.location.href=jumpUrl;
	}
</script>
    
  </body>
</html>