<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.download_center.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/index.css?ver=${version}"/>
</head>
<body>
<!--${LANG['bizconf.jsp.conf_invite_recv.res4']}-->
<jsp:include page="header.jsp" />
<div id="head_bar">
  <div class="nav_profile">
  </div>
  <ul class="nav_help">
    <li class="bar01" align="absmiddle"><a  title="${LANG['bizconf.jsp.download_center.res1']}" href="/downCenter/downClient" target="_blank">${LANG['bizconf.jsp.download_center.res1']}</a></li>
  </ul>
</div>

<!--${LANG['bizconf.jsp.download_help.res1']}-->
<div class="main88">

  <h3>${LANG['bizconf.jsp.download_help.res2']}</h3>
  <div class="fubiaoti">${LANG['bizconf.jsp.download_help.res3']}</div>
  <p>${LANG['bizconf.jsp.download_help.res4000']}<span>${LANG['bizconf.jsp.download_help.res6']}</span></p>
  <p><img src="/static/images/down_help_img01.png" width="555" height="29" /></p>
  <p>${LANG['bizconf.jsp.download_help.res8']}</p>
  <div class="fubiaoti">${LANG['bizconf.jsp.download_help.res9']}</div>
  <p>${LANG['bizconf.jsp.download_help.res10']}</p>
  <p><img src="/static/images/down_help_img02.png" width="459" height="352" /></p>
  <p><img src="/static/images/down_help_img03.png" width="265" height="72" /></p>
  <p>${LANG['bizconf.jsp.download_help.res11']}<span>${LANG['bizconf.jsp.download_help.res12']}</span></p>
  <p>${LANG['bizconf.jsp.download_help.res13']}</p>
  <p><img src="/static/images/down_help_img04.png" width="549" height="487" /></p>
  <p>${LANG['bizconf.jsp.download_help.res14']}</p>
  <p><img src="/static/images/down_help_img05.png" width="176" height="189" /></p>
  <p>${LANG['bizconf.jsp.download_help.res15']}</p>
  <p><img src="/static/images/down_help_img06.png" width="380" height="212" /></p>
</div>

<!--${LANG['bizconf.jsp.conf_invite_recv.res15']}-->
<div id="copy_close_downhelp">
<jsp:include page="footer.jsp" />
</div>
</body>
</html>
