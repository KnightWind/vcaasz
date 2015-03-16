<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>${LANG['bizconf.jsp.conf.bizconf.loginpage'] }</title>
    <link type="text/css" rel="stylesheet" href="/assets/css/login.css?vat=00" />
  </head>
  <body>
    <div id="header" style="margin-top: 0px;padding: 0px; border: 1px solid #DADADA; ">
      <div class="wrapper">
        <a href="/user" id="logo">
        <c:choose>
        	<c:when test="${empty currentSite.siteLogo }">
<%--        	<img src="/assets/images/login/logo.png" alt="logo" />--%>
        		<img src="/Formal/images/home/logo_small.png" alt="logo" style="margin-top: 10px;"/>
        	</c:when>
        	<c:otherwise>
        		<img src="${currentSite.siteLogo}" width="120px;" alt="logo" />
        	</c:otherwise>
        </c:choose>
          <img src="/Formal/images/home/brand.png" alt="brand" />
        </a>

        <div class="infomation" style="width:455px;">
          <span class="message">${LANG['website.user.footer.info.hotline']}</span>

          <div class="help">
            <ul class="clearfix">
              <li><a href="/help" target="_blank">${LANG['website.user.leftmenu.support.help'] }</a></li>
              <li><a class="divider">|</a></li>
              <li><a href="/support/download" target="_blank">${LANG['user.menu.conf.down.center'] }</a></li>
            </ul>
<!--             	<select name="jumpMenu_language" id="jumpMenu_language" > -->
<!-- <%--              <option>${LANG['bizconf.jsp.download_center.simplifiedchinese'] }</option>--%> -->
<!-- 					<option value="zh-cn" ${currentLanguage == 'zh-cn' ? 'selected' : ''}> ${LANG['website.common.language.zh']}</option> -->
<!-- 					<option value="en-us" ${currentLanguage == 'en-us' ? 'selected' : ''}> ${LANG['website.common.language.en']}</option> -->
<!-- 				</select> -->
            </select>
          </div>
        </div>
      </div>
    </div>
<script type="text/javascript">
jQuery(function($) {
	$("select").change(function () {
		var lang = $(this).val();
		changeLang(lang);
	});
});
</script>
  </body>
</html>