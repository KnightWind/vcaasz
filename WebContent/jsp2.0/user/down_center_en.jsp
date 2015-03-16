<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>${LANG['bizconf.jsp.download_center.bizconf.downloadtitle']}</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
    <!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
    <!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
    <link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/app/download.css?var=12" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<%--	<script type="text/javascript" src="/assets/js/lib/base.js"></script>--%>
  </head>
  <body>
    <div id="content">
      <div class="wrapper">
        <div class="download">
          <div class="item first-child">
            <h3>${LANG['website.down.load.center.zh.application.title'] }</h3>
            <p>You can join the meeting easily by a variety of mobile devices,<br/>
             after installing ${LANG['website.user.header.info.confcloud'] } App, you can:</p>
            <ul>
              <li>Join by Wifi, 3G or 4G</li>
              <li>Invited by email and SMS</li>
              <li>Sharing (text, pics, web pages, video, etc. )</li>
              <li>By group & privately</li>
            </ul>
            <p>You can find a lot more…</p>
            <div style="float:right; margin-right: 135px;margin-top: -80px;width: 100px;height: 100px;text-align:center;">
            	<img src="/downCenter/QccodeImg" />
            	<font style="margin:auto;font-weight:bold;">Scan Download</font>
            </div>
            <div class="btn-group">
              <a href="${ipkDownLoadUrl}" class="iphone" target="_blank"><div class="downtitle-en">IOS</div></a>
              <a href="${apkDownLoadUrl}" class="android" target="_blank"><div class="downtitle-en">Android</div></a>
            </div>
          </div>
          <div class="item last-child">
            <h3>${LANG['website.down.load.center.zh.client.title'] }</h3>
            <p>You can generate a shortcut on your computer，after installing <br/>${LANG['website.user.header.info.confcloud'] } App ,you can：</p>
            <ul>
              <li>Quick start meeting</li>
              <li>View your personal meeting list</li>
              <li>Review your details of the meeting</li>
              <li>Setting up the meeting parameters</li>
            </ul>
            <p>More looking forward to your findings.</p>
            <div class="btn-group">
              <a href="${clientDownLoadUrl}" class="windows" target="_blank"><div class="downtitle-en" style="margin-left: 36px;">Download</div></a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <c:set var="currentLanguage" value="en-us" />
    <c:choose>
	    <c:when test="${isSupport != 1}">
	    	<jsp:include page="/jsp2.0/user/footer_down_center.jsp" ></jsp:include>
	    </c:when>
    </c:choose>
    
    <script type="text/javascript">
		$(document).ready(function(){
			var os = "${system}";//系统
			if(os == "android"){
				window.location = "${apkDownLoadUrl}";
			}else if(os == "iphone" || os == "ipad"){
				window.location = "${ipkDownLoadUrl}";
			}
		});
		function changeLang(langVal){
			var jumpUrl="/changeLang?lang="+langVal+"&returnURL=/";
			window.location.href=jumpUrl;
		}
	</script>

</body>
<head>
</html>