<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#head {
    background: url("/static/images/new_header_bg.jpg") repeat-x scroll left center transparent;
    height: 100px;
    min-width: 1002px;
    overflow: hidden;
    position: relative;
    width: 100%;
}
#head_custom {
	background: url("/static/images/new_header_bg.jpg") repeat-x scroll left center transparent;
    height: 100px;
    min-width: 1002px;
    overflow: hidden;
    position: relative;
    width: 100%;
}
.logo_img {
    float: left;
    margin: 20px 0 0 40px;
}

.header_left h2 {
    bottom: 36px;
    color: #FFFFFF;
    font-family: "${LANG['bizconf.jsp.header.res1']}";
    font-size: 18px;
    left: 180px;
    position: absolute;
}
.header_left h2 strong{
    color: #FFFFFF;
    font-family: "${LANG['bizconf.jsp.header.res1']}";
    font-size: 18px;
}
.header_left {
    background: url("/static/images/new_header_left.jpg") no-repeat scroll left center transparent;
    float: left;
    height: 100px;
    width: 100%;
    position: absolute;
    left: 0px;
}
.header_left_custom {
    float: left;
    height: 100px;
    width: 100%;
    position: absolute;
    left: 0px;
}
.header_left_custom h2 {
    bottom: 36px;
    color: #FFFFFF;
    font-family: "${LANG['bizconf.jsp.header.res1']}";
    font-size: 18px;
    left: 180px;
    position: absolute;
}
.header_left_custom h2 strong{
    color: #FFFFFF;
    font-family: "${LANG['bizconf.jsp.header.res1']}";
    font-size: 18px;
}
.new_li {
    left: 245px;
    position: absolute;
    top: 65px;
}
.new_ico02 {
    left: 288px;
    position: absolute;
    top: 70px;
    z-index: 99;
    display: none;
}

.new_ico {
    left: 295px;
    position: absolute;
    top: -25px;
}

.new_right {
    position: absolute;
    right: 0;
    top: 0;
}

.titile {
    color: #DCDCDC;
    font-family: Arial,Helvetica,sans-serif;
    left: 347px;
    position: absolute;
    top: 72px;
    z-index: 120;
}

.header_left h2 strong {
    font-size: 28px;
}
</style>
<%--
<c:if test="${(empty siteBase.siteBanner) || (!empty siteBase.siteBanner && !empty siteBase.siteDiy && siteBase.siteDiy==0)}">
 --%>
<c:if test="${siteBase.siteDiy==0 || (siteBase.siteDiy==1 && empty siteBase.siteBanner)}">
<div id="head">
  <div class="header_left">
  	<c:choose>
  	<c:when test="${!empty siteBase.siteLogo && siteBase.siteDiy==1}">
  		<img class="logo_img png" onerror="this.src='${baseUrlStatic}/images/logo_bg.png';" src="${siteBase.siteLogo}" height="54" />
  	</c:when>
  	<c:otherwise>
  		<img class="logo_img png" onerror="this.src='${baseUrlStatic}/images/logo_bg.png';" src="${baseUrlStatic}/images/logo_bg.png" height="54" />
  	</c:otherwise>
  	</c:choose>
  <h2 id="siteName" style="margin-left: 60px;"></h2>
  
  </div>
  <img class="new_li png" src="${baseUrlStatic}/images/new_header_li.png" width="413" height="3" />
  <img class="new_ico02" src="${baseUrlStatic}/images/new_ico.png" width="25" height="25" /> 
  <img class="new_ico png" src="${baseUrlStatic}/images/new_xingxing.png" width="102" height="104" />
  <img class="new_right" src="${baseUrlStatic}/images/new_header_right.jpg" width="183" height="100" />
  <h3 class="titile">${siteBase.enName}</h3>
</div>
<script type="text/javascript">
var str = "${siteBase.siteName}";
var word = "${LANG['bizconf.jsp.header.res2']}";
str = str.replace(new RegExp("(" + word + ")","ig"), "<strong>" + word + "</strong>");
$("#siteName").html(str);
</script>
</c:if>
<%--
<c:if test="${!empty siteBase.siteBanner && !empty siteBase.siteDiy && siteBase.siteDiy==1}">
 --%>
<c:if test="${siteBase.siteDiy==1 && !empty siteBase.siteBanner}">
<div id="head_custom">
  <div class="header_left_custom">
  	<c:if test="${!empty siteBase.siteLogo}">
  		<img class="logo_img png" onerror="this.src='${baseUrlStatic}/images/logo_bg.png';" src="${siteBase.siteLogo}" height="54" />
  	</c:if>
  	<c:if test="${empty siteBase.siteLogo}">
  		<img class="logo_img png" onerror="this.src='${baseUrlStatic}/images/logo_bg.png';" src="${baseUrlStatic}/images/logo_bg.png" height="54" />
  	</c:if>
  </div>
</div>
<script type="text/javascript">
var siteBanner = "${siteBase.siteBanner}";
$("#head_custom").css("background", "url("+siteBanner+") no-repeat scroll right center transparent");
</script>
</c:if>