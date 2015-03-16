<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>${LANG['outer.conf.join.qccode'] }</title>
    <link type="text/css" rel="stylesheet" href="/assets/css/login.css" />
    <style type="text/css">
   .titleFont{
	    color: #FFFFFF;
	    font-size: 22px;
	    text-align: center;
	    position: relative;  
	    overflow:  hidden; 
        white-space: nowrap; 
        -o-text-overflow: ellipsis; 
        text-overflow:  ellipsis;  
        text-shadow: 0 1px 1px #D4E1F2;
        content: " ";
		display: block;
	}
	.titleFont:hover .hide{display:inline;}
	 .timeFont{
	    color: #FFFFFF;
	    font-size: 13px;
	    text-align: center;
	    position: relative;    
	     margin-top: 15px;
	}
    </style>
  </head>
  <body>
    <jsp:include page="/jsp2.0/user/loginHead.jsp" />
    <div id="#content" style="background-color: #005c9d;">
      <div align="center" style="border-top: solid 1px ; width: 100%;height: 670px"><!-- qccodebg.png -->
        <div  class="box" style="background-image: url(/assets/images/qccodebg.png) ;  height: 485px; width:552px;  margin-top: 60px; padding-top: 20px;" >
        		<div  class="titleFont" title="${conf.confName}">${conf.confName}</div >
        		<div class="timeFont"><c:if test="${conf.permanentConf eq 0}"><c:if test="${!conf.clientCycleConf }">${LANG['website.user.conf.report.conf.time'] }${LANG['website.common.symbol.colon']}<fmt:formatDate value="${conf.startTime}" pattern="MM月dd日 HH点mm分" />(${conf.timeZoneDesc})</c:if><c:if test="${conf.clientCycleConf }">&nbsp;</c:if></c:if><c:if test="${conf.permanentConf eq 1}">&nbsp;</c:if></div>
     		<img style="margin-top: 51px;margin-right: 5px;" src="/join/genQccodeImg?confId=${confId}&hostflag=${hostflag}" width="190px;" height="190" alt="" />
     		<div style="margin-top: 58px;margin-right: 5px;width: 200px;color: #fff;font-size: 14px;">${LANG['website.user.joinmeeting.qccode.scannertojoin'] }</div>
        </div>
      </div>
    </div>
    <div id="footer" style="margin-top: 0px;">
      <div class="wrapper">
        <p>${LANG['website.user.footer.info.rightreserved']}</p>
        <p>${LANG['website.user.footer.info.hotline']}</p>
      </div>
    </div>
  </body>
</html>