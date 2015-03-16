
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>${LANG['bizconf.jsp.conf.bizconf.head.title'] }</title>
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
    <!--[if lt IE 9]><script src="/assets/js/lib/modernizr.js"></script><![endif]-->
    <link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/app/login.css?var=16" />
    <script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
    <script type="text/javascript" src="/Formal/js/app/login.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"></script>
	<style type="text/css">
		.error{
			color: #EF4643; 
			position: absolute; 
			top: 265px; 
			left: 480px; 
			line-height: 16px;
		}
		.error-text {
		    background: none repeat scroll 0 0 #FAE2E2;
		    border-color: #C56360;
		    color: #C51500;
		}
		 html,body{width:100%;height:100%;margin:0px auto;padding:0px auto;}
        .bgdiv{width:100%;height:100%;position:absolute;z-index:-999;}
        .bgdiv img{width:100%;height:100%;border:0px;}
	</style>
  </head>
  <body class="no-rgba">
  <!-- 要实现图片切换TODO -->
  	<div id="imgbox" class="bgdiv">
        <img id="bgimg1" src="/Formal/images/login/bg/01/1920.jpg" />
    </div>
    <div id="meeting" style="position: relative;">
      <img src="/Formal/images/login/logo.png"  />
      <h1 style="margin-top: 15px;">${LANG['bizconf.jsp.conf.bizconf.head.title'] }</h1>
      <div style="width: 550px;margin: 0 auto; height: 60px;">
      	<div id="joinError" style="width:220px;height:20px; margin-left:20px;font-size:14px; font-weight:bolder;color: #E00000;text-align: left;"></div>
      	<div class="form-group" style="position: static;height:40px;">
        	<input type="text" class="form-control" placeholder="${LANG['bizconf.jsp.conf.copy.input.confid']}" id="confNo" name="confNo" />
        	<button type="button" class="btn btn-default" onclick="joinMeeting();">${LANG['bizconf.jsp.conf.copy.join'] }</button>
      	</div>
      </div>
    </div>
    <div id="banner">
      <div class="login" id="login-form" method="post">
        <form autoComplete="off">
          <div class="title" style="margin-bottom: 0px;"><i class="icon camera"></i>${LANG['website.user.conf.list.join.hostmeeting'] }</div>
          <div id="loginError" style="width:258px; height: 18px;font-size:14px; font-weight:400; color: red;background-color:#fff;opacity:0.7;filter:alpha(opacity=70); display:none; z-index: 102;left: 2px;top:20px;position: absolute;"></div>
          <noscript>
          	<div style="width:418px; height: 18px;font-size:14px; font-weight:400; color: red;background-color:#fff;opacity:0.7;filter:alpha(opacity=70);z-index: 102;left: 2px;top:20px;position: absolute;">${LANG['website.user.login.checkenv.jsinfo'] }</div>
          </noscript>
          <div class="form-group" style="margin-top: 18px;">
            <label class="for">${LANG['bizconf.jsp.enContacts_list.res5'] }：</label>
            <input type="text" class="form-control" placeholder="${LANG['bizconf.jsp.enContacts_list.res5'] }" id="loginName" name="loginName" />
            <label class="for">${LANG['system.user.password'] }：</label>
            <input type="password" class="form-control" placeholder="${LANG['system.user.password'] }" id="loginPass" name="loginPass" />
            <button type="button" class="btn btn-primary" onclick="login();" autofocus>${LANG['bizconf.jsp.index.res21'] }</button>
          </div>
          <div class="form-group">
            <label class="holder remember"><input type="checkbox" name="remember" id="remember" />&nbsp;&nbsp;${LANG['website.user.login.loginname.hold'] }</label>
            <label class="holder forget"><a href="/user/password/forget" target="_blank">${LANG['website.user.login.loginpass.forget'] }${LANG['website.common.symbol.quesmark'] }</a></label>
          </div>
        </form>
        <nav id="swipbgnav">
          <a class="left" href="javascript:changeBackgroundImgLeft();"></a>
          <a class="right" href="javascript:changeBackgroundImgRigth();"></a>
        </nav>
      </div>
    </div>
     <jsp:include page="/jsp2.0/user/foot_index.jsp"></jsp:include> 

    <script type="text/javascript">
		$(function() {
	    	if ($.browser.msie && $.browser.version < 10) {
	    		$("#confNo").watermark("${LANG['bizconf.jsp.conf.copy.input.confid']}");
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
			/**
			会议号码回车加入
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
			});*/
			
			
			//监听回车按键进行登录操作
			$("form button").focus();
			$("form button").keydown(function(event){
			    if (event.keyCode == 13) {
			    	login();
			    }
			});
			//监听回车按键进行登录操作和confNo会议加入
			$("input").keydown(function(event){
				if (event.keyCode == 13) {
					if($("#confNo").is(":focus")){
					   /*var meetingNo = $("#confNo").val();
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
						}*/
						joinMeeting();
					}else{
						login();
					}
			    }
			});
			//监听密码输入框回车
			$("input[type='password']").live("keydown",function(event){
				if (event.keyCode == 13) {
					login();
				}
			});
			
			$("input[type=text]").focus(function(){
				clearErrorInfo($(this));
			});
			
			$("#loginPass").live("focus",function(){
				clearErrorInfo($(this));
			});
			
		});	
		
		function joinMeeting(){
			var code = $("#confNo").val();
			/*
			if(code == null || code == undefined || code == ""){
			//$("#joinError").html("${LANG['website.user.conf.notnull.confid'] }");
				showError($("#confNo"),"${LANG['website.user.conf.notnull.confid'] }");
				return false;
			}else{
				var url = "/join/gotojoinPage?joinType=2&code="+code;//blank表示新打开窗口
				window.open(url);
			}
			*/
			/*
			if($.browser.msie){
			if(code == null || code == undefined || code == ""){
				showError($("#confNo"),"${LANG['website.user.conf.notnull.confid'] }");
				return false;
			}
			$.ajax({
				 type: "GET",
			     url: "/join/ie/"+code,
			     async:false,
			     dataType:"json",
			     success: function(data) {
					var status = data.status;
					if(status == 0){
						getJoinMeetingUrl(code,data.UUID);
					}else if(status == 1 || status == 2){
						showError($("#confNo"),"${LANG['website.conf.join.type.2'] }");
					}else{//未知错误
						showError($("#confNo"),"${LANG['website.conf.join.type.8'] }");
					}
			     },
			     error:function(data){
			    	 showError($("#confNo"),"${LANG['website.conf.join.type.2'] }");
			     }
			 });
			}else{
			*/
				if(code == null || code == undefined || code == ""){
					//$("#joinError").html("${LANG['website.user.conf.notnull.confid'] }");
					showError($("#confNo"),"${LANG['website.user.conf.notnull.confid'] }");
					return false;
				}else{
					var url = "/join/gotojoinPage?joinType=2&code="+code;//blank表示新打开窗口
					window.open(url);
				}
			//}
			
		}
		
		function login() {
			var userBase = {};
			userBase.loginName = $("#loginName").val();
			userBase.loginPass = $("#loginPass").val();
			
			if(!cookieEnable()){
				$("#loginError").text("${LANG['website.user.login.checkenv.cookieinfo'] }");
				$("#loginError").css("width","418px");
				$("#loginError").show();
				return;
			} 
			
			if(userBase.loginName == null || userBase.loginName == "" ||userBase.loginName==undefined ){
				//$("#loginError").text("${LANG['website.user.conf.notnull.confaccount'] }");
				showError($("#loginName"),"${LANG['website.user.conf.notnull.confaccount'] }");
				return;
			}	
			if(userBase.loginPass == null || userBase.loginPass == "" ||userBase.loginPass==undefined ){
				//$("#loginError").text("${LANG['website.user.login.js.message.loginpass.nowrite'] }");
				showError($("#loginPass"),"${LANG['website.user.login.js.message.loginpass.nowrite'] }");
				return;
			}
			
			app.userLogin(userBase, function(result) {
				if(result) {
					if(result.status==10){
						rememberMe();
						location.href = "/";
					} else {
						//showError($("#loginName"),result.message,true);
						$("#loginError").text(result.message);
						if(result.message){
							var len = result.message.length;
							var lan = "${currentLanguage}";
							if(lan == 'zh-cn'){
								$("#loginError").css("width",(len*14)+"px");
							}else{
								$("#loginError").css("width",(len*8)+"px");
							}
						}
						$("#loginError").show();
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
		
		function changeBackgroundImgRigth(){
			swpeImg(true);
		}
		function changeBackgroundImgLeft(){
			swpeImg(false);
		}
		
		var imgContainer = new Object();
		imgContainer.regsiter = new Object();
		imgContainer.regArray = [];
		imgContainer.addComplate = function(url){
			if(!this.regsiter[url]){
				this.regsiter[url] = true;
				this.regArray.push(url);		
			}
		}
		imgContainer.completeAll = function(){
			var regsiter = this.regsiter;
			if(this.regArray.length == 8){
				for(var i=0;i<this.regArray.length;i++){
					var url = this.regArray[i];
					if(!regsiter[url]) return false;
				}
				return true;
			}
			return false;
		}
		
		var imgindex = 1;
		function swpeImg(inc){
			$("body").css('background-color','#666');
			$("#bgimg1").fadeOut(0);
			if(inc){
				imgindex++;
			}else{
				imgindex--;
			}
			if(imgindex > 8){
				imgindex = 1;
			}
			
			if(imgindex < 1){
				imgindex = 8;
			}
			var imgNo = '0'+ new String(imgindex);
			var url = '/Formal/images/login/bg/'+imgNo+'/1920.jpg';
			
			if(imgContainer[url]){
				$("body").css('background-color','');
				$("#bgimg1").attr("src",imgContainer[url].src);
		        $("#bgimg1").fadeIn(1000);
		        return;
			}
			var img = new Image(); //创建一个Image对象，实现图片的预下载
			img.src = url;
			
		    if (img.complete) { // 如果图片已经存在于浏览器缓存，直接调用回调函数
		    	$("body").css('background-color','');
		    	$("#bgimg1").attr("src",img.src);
		        $("#bgimg1").fadeIn(1000);
		        
				imgContainer[url] = img;
		    	return; // 直接返回，不用再处理onload事件
		    }
		    
		    img.onload = function () { //图片下载完毕时异步调用callback函数。
		    	$("body").css('background-color','');
		    	$("#bgimg1").attr("src",img.src);
		        $("#bgimg1").fadeIn(1000);
		        
		        imgContainer[url] = img;
		    	return ;
		   };
		}
		
		$(document).ready(function(){
			
			$("#confNo").focus();
			
			var h = $(document).height(); 
			//alert('h=='+h);
			if(h<710) h = 710;//设置一个最小高度 防止chrome不兼容
			$("#bgimg1").height(h);
			
			$("#swipbgnav").hide();
			for(var i =1;i<9;i++){
				var url = '/Formal/images/login/bg/0'+i+'/1920.jpg';
				var img = new Image();
				img.src = url;
				if (img.complete) {
					imgContainer.addComplate(img.src);
					if(imgContainer.completeAll()){
						showSwip();
					}
				}
				img.onload = function () {
					//alert(this.src);
					imgContainer.addComplate(this.src);
					if(imgContainer.completeAll()){
						showSwip();
					}
				}
			}
			
			
			$(window).resize(function(){
				if($(".error-text").length > 0 && $(".error-text").is(":visible")){
					var obj = $(".error-text");
					//alert(obj.attr("id"));
					var info  = newDiv.attr("title");
					showError(obj,info)
				}
			});
			
		});
		
		function showSwip(){
			$("#swipbgnav").show();
		}
		
		var  newDiv=$('<div></div>').css({'overflow':'hidden','border':'0 none','min-height':'20px','background':'#fae2e2','white-space':'nowrap'})
		  .css("z-index","99")
		  .css("color","#EF4643")
		  .css("position","absolute")
		  .attr('title','').appendTo('body');
		
		$(newDiv).bind("click",function(){
			var id = $(this).attr("data");
			//alert(id);
			$("#"+id).trigger("focus");
		});
		
		function showError(obj,info,isLogin){
			$(".error-text").each(function(){
				clearErrorInfo($(this));
			});
			var h = $(obj).height();
			var w = $(obj).width();
			var incheight = h/2;
			var t = $(obj).offset().top;
			var l = $(obj).offset().left;
			var id = $(obj).attr("id");
			newDiv.css("left",(l+5)+ "px").css("top",(t+incheight)+"px")
			.css("min-height",(h-1)+"px").css("width",(w+2)+"px");
			newDiv.attr("data",id);
			if(isLogin){
				newDiv.attr("ajaxlogin","true");
			}
			newDiv.attr("title",info);
			newDiv.html(info);
		    
			$(obj).addClass("error-text");
			newDiv.show();
			
		}
		
		
		function clearErrorInfo(obj){
			var isLogin = $(newDiv).attr("ajaxlogin");
			$("#loginError").hide();
			if(isLogin){
				$(".error-text").removeClass("error-text");
				newDiv.hide();
			}
			if($(obj).is(".error-text")){
				$(obj).removeClass("error-text");	
				//$(obj).val("");
				newDiv.hide();
			}
		}
		
		
		function cookieEnable(){
			var result=false;
			if(navigator.cookiesEnabled)
			{
			 return true;
			}
			document.cookie = "testcookie=yes;";
			var cookieSet = document.cookie;
			if (cookieSet.indexOf("testcookie=yes") > -1) result=true;
			document.cookie = "";
			return result;
		} 
	</script>
  </body>
</html>