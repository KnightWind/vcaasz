<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/page.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css?ver=${version}"/>
<title>index</title>
<style>
.tr_center {
	border:#D2D8DB 1px solid;
	border-right:none;
}
.tr_main {
	border-bottom:#D2D8DB 1px solid;
	color:#666666;
}
.tr_top {
	border:#D2D8DB 1px solid;
	border-bottom:none;
	margin-top:26px;
	background: url("/static/images/table_head_bg.jpg") repeat-x scroll 0 0 transparent;
	border-top: 0px;
	border-left: 1px solid #B5D7E6;
	border-right: 1px solid #B5D7E6;
}
.tr_bottom {
	border:#D2D8DB 1px solid;
	border-top:none
}
.no-data {
	border-left: 1px solid #B5D7E6;
	border-right: 1px solid #B5D7E6;
}
.zd01, .zd02 {
    border: 1px solid #D2D8DB;
    margin: 5px;
    padding: 2px;
}
.clickable{
	cursor: pointer;
}
.clickable label {
	cursor: pointer;
}
</style>
<script type="text/javascript">
 
</script>
</head>
<body>
<div>
	<div style="width: 80%" align="center">
   <div class="m_top" style="margin-bottom: 10px;height: 60px;">
    
  	</div>
    <table width="90%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box" >
      <tr>
        <td height="40" colspan="3" bgcolor="#EAF4FC" class="tr_top" align="center">
           <span>今日</span>
        </td>
      </tr>
      <tr>
	        <td class="tr_main" colspan="3" align="center" style="height:45px; border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;padding-right:10px; border-bottom:none">
	           <div class="clickable" style="width: 50px; overflow: hidden;">
		           <a style="font-size: 38px;font-weight: bold;" href="/system/statistical/viewRunningMeeting" target="_blank">${tadayMeetNum }</a> &nbsp;场
		           <p>会议</p>
	           </div>
	        </td>
      </tr>
	
      <tr  bgcolor="#FFFFFF" height="32">
       	<td height="40" colspan="3" bgcolor="#EAF4FC" class="tr_top" align="center">
        	 近一周会议情况
        </td>
      </tr>
      
      <tr  bgcolor="#FFFFFF">
        <td class="tr_main" align="center"  style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 	<span><label style="font-size: 38px;font-weight: bold;">${weekMeetingTime }</label> 分钟</span>
        	 	<p>时长</p>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 	<a style="font-size: 38px;font-weight: bold;" target="_blank" href="/system/statistical/viewMeetingNumChar?freq=1">${weekMeetNum }</a> &nbsp;场
        	 	<p>已结束会议</p>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;border-left:none;border-bottom:dashed 1px #D2D8DB;"> 
        	 	<a target="_blank" href="/system/statistical/viewParticipantNumChar?freq=1" style="font-size: 38px;font-weight: bold;"> ${weekParticiPantNum }</a> &nbsp;人
        	 	<p>已参会</p>
        </td>
      </tr>
      <tr  bgcolor="#FFFFFF" height="32">
        <td height="40" colspan="3" bgcolor="#EAF4FC" class="tr_top" align="center">
        	 近一月会议情况
        </td>
      </tr>
      
      <tr  bgcolor="#FFFFFF">
         <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 <div>
        	 	<span><label style="font-size: 38px;font-weight: bold;">${monMeetingTime }</label> &nbsp;分钟</span>
        	 	<p>时长</p>
        	 </div>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 <div>
        	 	<a target="_blank" href="/system/statistical/viewMeetingNumChar?freq=2" style="font-size: 38px;font-weight: bold;">${monMeetNum }</a> &nbsp;场
        	 	<p>已结束会议</p>
        	 </div>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;border-left:none;border-bottom:dashed 1px #D2D8DB;"> 
        	  <div>
        	 	<a target="_blank" href="/system/statistical/viewParticipantNumChar?freq=2" style="font-size: 38px;font-weight: bold;">${monParticiPantNum }</a> &nbsp;人
        	 	<p>已参会</p>
        	 </div>
        </td>
      </tr>
      
      <tr  bgcolor="#FFFFFF" height="32">
        <td height="40" colspan="3" bgcolor="#EAF4FC" class="tr_top" align="center"> 
        	 全部会议情况
        </td>
      </tr>
      <tr  bgcolor="#FFFFFF">
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 <div>
        	 	<span><label style="font-size: 38px;font-weight: bold;">${totalMeetingTime }</label>&nbsp;分钟</span>
        	 	<p>时长</p>
        	 </div>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;  border-bottom:dashed 1px #D2D8DB;"> 
        	 <div>
        	 	<a target="_blank" href="/system/statistical/viewAllMeetingNumChar?statType=1" style="font-size: 38px;font-weight: bold;">${totalMeetingNum }</a>&nbsp;场
        	 	<p>已结束会议</p>
        	 </div>
        </td>
        <td class="tr_main" align="center" style="border-left:#D2D8DB 1px solid; border-right:#D2D8DB 1px solid;border-left:none;border-bottom:dashed 1px #D2D8DB;"> 
        	  <div>
        	 	<a target="_blank" href="/system/statistical/viewAllParticipantNumChar?statType=1" style="font-size: 38px;font-weight: bold;">${totalParticipantNum }</a>&nbsp;人
        	 	<p>已参会</p>
        	 </div>
        </td>
      </tr>
    </table>
    </div>
	</div>
</body>
</html>
