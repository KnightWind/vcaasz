<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>${LANG['bizconf.jsp.download_center.res1'] }</title>
<link type="text/css" rel="stylesheet" href="/assets/css/download.css?var=0" />
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/product.css" />
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
</head>
<body style="padding: 0px;background-color:#F8F8F8;">
<%--<body>--%>
	<jsp:include page="/jsp2.0/user/loginHead.jsp" />
	<%--    <div id="pageheader"  style="margin-top: 0px;padding: 0px; border: 1px solid #DADADA; ">--%>
	<%--      <div class="wrapper">--%>
	<%--        <a href="" id="logo">--%>
	<%--          <img src="/assets/images/login/logo.png" alt="logo" />--%>
	<%--          <img src="/assets/images/login/brand.png" alt="brand" />--%>
	<%--        </a>--%>
	<%--        <div class="infomation" style="width: 365px;">--%>
	<%--          <span class="message">${LANG['website.user.footer.info.hotline']}</span>--%>
	<%--          <div class="help">--%>
	<%--            <ul class="clearfix">--%>
	<%--              <li><a href="/help" target="_blank">${LANG['website.user.leftmenu.support.help'] }</a></li>--%>
	<%--              <li><a class="divider">|</a></li>--%>
	<%--<!--          <li><a href="downCenter/downClient" target="_blank">下载中心</a></li> -->--%>
	<%--            </ul>--%>
	<%--            <select name="jumpMenu_language" id="jumpMenu_language" >--%>
	<%--            <option>${LANG['bizconf.jsp.download_center.simplifiedchinese'] }</option>--%>
	<%--				<option value="zh-cn" ${currentLanguage == 'zh-cn' ? 'selected' : ''}> ${LANG['website.common.language.zh']}</option>--%>
	<%--				<option value="en-us" ${currentLanguage == 'en-us' ? 'selected' : ''}> ${LANG['website.common.language.en']}</option>--%>
	<%--			</select>--%>
	<%--          </div>--%>
	<%--        </div>--%>
	<%--      </div>--%>
	<%--    </div>--%>
	<!-- PC终端的显示页面 -->
	<!--     <DIV ID="CONTENT"> -->
	<!--       <DIV CLASS="WRAPPER"> -->
	<div class="box"
		style="width:960px;margin-left: auto;margin-right:auto">
		<div class="body">
			<div class="product">
				<figure> <img src="/assets/images/01.png"
					alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.iosclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.iosconfclient']
						}${LANG['website.common.symbol.period'] } <br />7.5M <br />${LANG['bizconf.jsp.download_center.iossystemrequirements']
						}
					</p>
					<a href="https://itunes.apple.com/cn/app/id546505307"
						target="_blank" class="input-button">${LANG['website.user.download.center.page.download.now']
						} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					<div alt=""
						style="margin-left:200px;margin-top:-150px;text-align:center">
						<img height="130px;" src="${baseUrlStatic}/images/ios.png">
							<p>${LANG['bizconf.jsp.download_center.scanningdownload'] }</p>
					</div>
				</div>
			</div>

			<div class="product">
				<figure> <img src="/assets/images/02.png"
					alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.androidclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.androidconfclient']
						}${LANG['website.common.symbol.period'] } <br /> 7.5M <br />
						${LANG['bizconf.jsp.download_center.androidsystemrequirements'] }
					</p>
					<a href="/download/zoom.apk" class="input-button">${LANG['website.user.download.center.page.download.now']
						}</a>
					<div alt=""
						style="margin-left:200px;margin-top:-150px;text-align:center">
						<img height="130px;" src="${baseUrlStatic}/images/android.png">
							<p>${LANG['bizconf.jsp.download_center.scanningdownload'] }</p>
					</div>
				</div>
			</div>

			<div class="product">
				<figure> <img src="/assets/images/03.png"
					alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>

				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.win78client'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.win78confclient']
						}${LANG['website.common.symbol.period'] } <br /> 5.9M <br />
						${LANG['bizconf.jsp.download_center.win78systemrequirements'] }
					</p>
					<a href="/download/ZoomInstaller.exe" class="input-button">${LANG['website.user.download.center.page.download.now']
						}</a>
				</div>
			</div>
			<div class="product">
				<figure> <img src="/assets/images/04.png"
					alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.winxpclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.winxpconfclient']
						}${LANG['website.common.symbol.period'] } <br /> 9.7M <br />
						${LANG['bizconf.jsp.download_center.winxpsystemrequirements'] }
					</p>
					<a href="/download/ZoomInstallerXP.exe" class="input-button">${LANG['website.user.download.center.page.download.now']
						}</a>
				</div>
			</div>
			<div class="product">
				<figure> <img src="/assets/images/05.png"
					alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.macosxclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.macosxconfclient']
						}${LANG['website.common.symbol.period'] } <br />7.5M <br />${LANG['bizconf.jsp.download_center.macosxsystemrequirements']
						}
					</p>
					<a href="/download/zoomusInstaller.pkg" class="input-button">${LANG['website.user.download.center.page.download.now']
						}</a>
				</div>
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
		function changeLang(langVal) {
			var jumpUrl = "/changeLang?lang=" + langVal + "&returnURL=/downCenter/goDownload";
			window.location.href = jumpUrl;
		}
	</script>
</body>
</html>