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
            <p>通过各种移动设备可方便的加入视频会议，安装${LANG['website.user.header.info.confcloud'] }<br />应用后，您可以：</p>
            <ul>
              <li>通过wifi、3G或4G加入</li>
              <li>邮件、短信邀请用户参会</li>
              <li>共享内容(文字，图片，网页，视频等)</li>
              <li>群组聊天&私聊</li>
            </ul>
            <p>更多精彩期待您的发现。</p>
            <div style="float:right; margin-right: 135px;margin-top: -80px;width: 100px;height: 100px;text-align:center;">
            	<img src="/downCenter/QccodeImg" />
            	<font style="margin:auto;font-weight:bold;">扫描下载</font>
            </div>
            <div class="btn-group">
              <a href="${ipkDownLoadUrl}" class="iphone" target="_blank"> <div class="downtitle-zh">IOS下载</div></a>
              <a href="${apkDownLoadUrl}" class="android" target="_blank"> <div class="downtitle-zh">安卓下载</div></a>
            </div>
          </div>
          <div class="item last-child">
            <h3>${LANG['website.down.load.center.zh.client.title'] }</h3>
            <p>能在您电脑上生成一个快捷方式，安装${LANG['website.user.header.info.confcloud'] }<br />应用后，您可以：</p>
            <ul>
              <li>快速启动会议</li>
              <li>查看您的个人会议列表</li>
              <li>回顾您的会议详情</li>
              <li>设置会议参数</li>
            </ul>
            <p>更多精彩期待您的发现。</p>
            <div class="btn-group">
              <a href="${clientDownLoadUrl}" class="windows" target="_blank"><div class="downtitle-zh" style="margin-left: 49px;">立即下载</div></a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <c:set var="currentLanguage"  value="zh-cn" />
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