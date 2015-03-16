<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>${LANG['bizconf.jsp.download_center.bizconf.downloadtitle']}</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet"
	href="/assets/css/apps/product.css" />
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/base.js"></script>
<script type="text/javascript">
	$(function(){
		//说明是在最外层打开的
		if(window.top == window.self){
			//alert("hey");
			window.location = "/downCenter/goDownload";
		}
	});
	</script>
</head>
<body>
	<div class="box">

		<div class="head">
			<span class="title">${LANG['website.user.download.center.page.title']}</span> <span class="desc">${LANG['website.user.download.center.page.desc']}</span>
		</div>

		<div class="body">
			<div class="product">
				<figure>
					<img src="/assets/images/01.png" alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.iosclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.iosconfclient'] }${LANG['website.common.symbol.period'] }
						<br />7.5M <br /> ${LANG['bizconf.jsp.download_center.iossystemrequirements'] }
					</p>
					<a href="https://itunes.apple.com/cn/app/id546505307" target="_blank" class="input-button">${LANG['website.user.download.center.page.download.now'] } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					<div alt="" style="margin-left:200px;margin-top:-150px;text-align:center" >
					<img height="130px;" src="${baseUrlStatic}/images/ios.png">
					<p>${LANG['bizconf.jsp.download_center.scanningdownload'] }</p>
					</div>
				</div>
			</div>
			
			<div class="product">
				<figure>
					<img src="/assets/images/02.png" alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
						<h4>${LANG['bizconf.jsp.download_center.androidclient'] }</h4>
						<p class="intro">
							${LANG['bizconf.jsp.download_center.androidconfclient'] }${LANG['website.common.symbol.period'] }
							<br /> 7.5M <br /> ${LANG['bizconf.jsp.download_center.androidsystemrequirements'] }
						</p>
						<a href="/download/zoom.apk" class="input-button">${LANG['website.user.download.center.page.download.now'] }</a>
						<div alt="" style="margin-left:200px;margin-top:-150px;text-align:center" >
							<img height="130px;" src="${baseUrlStatic}/images/android.png">
					   <p>${LANG['bizconf.jsp.download_center.scanningdownload'] }</p>
					</div>
			</div>
		</div>
		
		<div class="product">
				<figure>
					<img src="/assets/images/03.png" alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>

				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.win78client'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.win78confclient'] }${LANG['website.common.symbol.period'] }
						<br /> 5.9M <br />${LANG['bizconf.jsp.download_center.win78systemrequirements'] }
					</p>
					<a href="/download/ZoomInstaller.exe" class="input-button">${LANG['website.user.download.center.page.download.now'] }</a>
				</div>
			</div>
		
			<div class="product">
				<figure>
					<img src="/assets/images/04.png" alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.winxpclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.winxpconfclient'] }${LANG['website.common.symbol.period'] }
						<br /> 9.7M <br /> ${LANG['bizconf.jsp.download_center.winxpsystemrequirements'] }
					</p>
					<a href="/download/ZoomInstallerXP.exe" class="input-button">${LANG['website.user.download.center.page.download.now'] }</a>
				</div>
			</div>
			<div class="product">
				<figure>
					<img src="/assets/images/05.png" alt="${LANG['website.user.download.center.page.client.name'] }" />
				</figure>
				<div class="information">
					<h4>${LANG['bizconf.jsp.download_center.macosxclient'] }</h4>
					<p class="intro">
						${LANG['bizconf.jsp.download_center.macosxconfclient'] }${LANG['website.common.symbol.period'] }
						<br />7.5M <br /> ${LANG['bizconf.jsp.download_center.macosxsystemrequirements'] }
					</p>
					<a href="/download/zoomusInstaller.pkg" class="input-button">${LANG['website.user.download.center.page.download.now'] }</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>