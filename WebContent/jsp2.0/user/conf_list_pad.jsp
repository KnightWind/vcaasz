<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>${siteBase.siteName}--${LANG['website.common.index.title.base.1']}</title>
    <!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
    <!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
    <link type="image/x-icon" rel="shortcut icon" href="/Formal/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/base.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/app/meeting-home.css?t=9" />

	<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
	
	<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"> </script>
    <cc:confList var="CONF_PERMANENT_ENABLED_MAIN"/>
	<cc:confList var="CONF_PERMANENT_UNABLE"/>
	<cc:confList var="CONF_PERMANENT_ENABLED_CHILD"/>
	<c:if test="${!empty user}">
		<c:set var="pageSize" value="${user.pageSize}"></c:set>
	</c:if>
	<c:if test="${empty user}">
		<c:set var="pageSize" value="10"></c:set>
	</c:if>
	<style type="text/css">
		.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year {
			width: 39%;
		}
		.unable-active{
			padding-left: 15px;
			padding-right: 12px;
		}
	</style>
  </head>
  <body>
    <div class="banner">
      <div class="wrapper">
        <div class="user">
          <i class="icon"></i>
          <div class="number">
            <span>${LANG['website.user.conf.userconfid']}</span>
            <div>
              <span>${pmi.confZoomId}</span>
              <c:choose>
				<c:when test="${pmi.confStatus eq 2}">
					<c:if test="${myfn:getCurrentLang() eq 'zh-cn' }"><i class="icon"></i></c:if>
					<c:if test="${myfn:getCurrentLang() != 'zh-cn' }"><i class="iconen"></i></c:if>
				</c:when>
				<c:otherwise>
					<i class=""></i>			
				</c:otherwise>
				</c:choose>
            </div>
          </div>
        </div>
        <ul class="list-actions">
       		 <c:choose>
				<c:when test="${pmi.confStatus eq 2}">
			          <li><a href="javascript:top.inventContact('${pmi.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
			          <li><a href="javascript:top.inventContactByMsg('${pmi.id}',false,1);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
				</c:when>
				<c:otherwise>
			          <li class="disabled"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</li>
			          <li class="disabled"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</li>
				</c:otherwise>
			</c:choose>
          <li><a onclick="copyViewConf(${pmi.id})"><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
          <li class="settings" id="pmiupdater">

            <a><i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}</a>
			<div class="options" style="width:480px;" id="PMISetting">
              <span class="corner"></span>
              <div class="text-overflow" style="">
                <div style="margin: auto;">
                  <div class="form-group" style="text-align: left;margin-right: 42px;padding-right: 0px;width: 40%;">
                    <span style="text-align: left;position: relative;">${LANG['website.admin.conf.list.page.search.tip']}${LANG['website.common.symbol.colon']}</span>
                    <input style="text-align: left;position: relative;" type="text" class="input-text input-lg" value="${pmi.confName}" id="pmiName" />
                  </div>
                  <div class="form-group" style="text-align: left;padding-right: 0px;width: 47%;">
                    <span style="text-align: left;position: relative;">${LANG['user.menu.conf.pad.videooption']}${LANG['website.common.symbol.colon']}</span>
                    <label style="text-align: right;position: relative;"><input type="radio" class="input-radio" name="pmiOptionStartType" <c:if test="${pmi.optionStartType eq 1 }">checked="checked"</c:if> value="1"/>&nbsp; ${LANG['user.menu.conf.pad.videooption.on']}</label>
                    <label style="text-align: right;position: relative;"><input type="radio" class="input-radio" name="pmiOptionStartType" <c:if test="${pmi.optionStartType eq 2 }">checked="checked"</c:if>  value="2"/>&nbsp; ${LANG['user.menu.conf.pad.videooption.off']}</label>
                  </div>
                </div>
                <div style="margin: auto;">
 				<div class="form-group" style="text-align: left;margin-right: 42px;padding-right: 0px;width: 40%;">
                    <span style="text-align: left;position: relative;">${LANG['bizconf.jsp.common.join_page.res11']}${LANG['website.common.symbol.colon']}</span>
                    <input style="text-align: left;position: relative;" placeholder="${LANG['bizconf.jsp.conf_list_index.number.6and10']}" type="text" id="pmiHostKey" value="${pmi.hostKey }" maxlength="10" class="input-text input-lg"/>
<%--                <i style="text-align: right;position: relative;">${LANG['bizconf.jsp.conf_list_index.number.6and10']}</i>--%>
                 </div>
 				<div class="form-group" style="text-align: left;margin-right: 20px;padding-right: 0px;width: 32%;">
                    <span style="text-align: left;position: relative;">${LANG['website.user.view.conf.conf.preHostor'] }${LANG['website.common.symbol.colon'] }</span>
                    <label style="text-align: right;position: relative;"><input type="radio" class="input-radio" name="pmiOptionJbh" <c:if test="${pmi.optionJbh eq 0 }">checked="checked"</c:if> value="0"/> &nbsp; ${LANG['website.user.view.conf.conf.preHostor.no'] }</label>
                    <label style="text-align: right;position: relative;"><input type="radio" class="input-radio" name="pmiOptionJbh" <c:if test="${pmi.optionJbh eq 1 }">checked="checked"</c:if>  value="1"/>&nbsp; ${LANG['website.user.view.conf.conf.preHostor.yes'] }</label>
                 </div>
                 <div class="form-group" style="text-align: right;padding-right: 0px;width: 17%;">
                 	<c:choose>
					<c:when test="${pmi.confStatus eq 2}">
                  		<button disabled="disabled" class="btn disabled"   style="text-align:center; position: relative;" onclick="javascript:void(0);">${LANG['website.common.option.save']}</button>
					</c:when>
					<c:otherwise>
                  		<button class="btn btn-primary"  style="text-align: center;position: relative;" onclick="updatePMI(${pmi.id});">${LANG['website.common.option.save']}</button>
					</c:otherwise>
				</c:choose>
                 </div>
<!--                   <div class="form-group first-child"> -->
<!--                     <span>${LANG['website.user.view.conf.conf.preHostor']}${LANG['website.common.symbol.colon']}</span> -->
<!--                     <label><input type="radio" class="input-radio" name="pmiOptionJbh" <c:if test="${pmi.optionJbh eq 1 }">checked="checked"</c:if> value="1"/>${LANG['website.user.view.conf.conf.preHostor.yes']}</label> -->
<!--                     <label><input type="radio" class="input-radio" name="pmiOptionJbh" <c:if test="${pmi.optionJbh eq 0 }">checked="checked"</c:if> value="0"/>${LANG['website.user.view.conf.conf.preHostor.no']}</label> -->
<!--                   </div> -->
                </div>
              </div>
            </div>
          </li>
          <c:choose>
				<c:when test="${pmi.confStatus eq 2}">
					<li><button style="width:78px;float: right;position: relative;" onclick="joinConference('${pmi.id}','${pmi.confZoomId}');" class="btn btn-success">${LANG['website.user.conf.list.enter.meeting'] }</button></li>
				</c:when>
				<c:otherwise>
					 <li><button style="width:78px;float: right;position: relative;" onclick="joinConference('${pmi.id}','${pmi.confZoomId}');" class="btn btn-primary">${LANG['bizconf.jsp.conf_list_index.res54'] }</button></li>			
				</c:otherwise>
		 </c:choose>
        </ul>
      </div>
    </div>

    <div class="summary">				
      <div class="filter">
        <span class="case time-case">
          <span class="widget">
            <input type="text" class="input-text" value= "${fn:substring(beginTime,0,10) }" id="startTime" readonly="readonly"/>
            <span class="divider">—</span>
            <input type="text" class="input-text" value="${fn:substring(endTime,0,10) }" id="endTime" readonly="readonly"/>
          </span>
        </span>
      </div>
      <div class="search">
<%--        <form method="get">--%>
          <input id="searchTitle" type="text" class="input-text" placeholder="${LANG['website.user.create.immedia.js.message.confname.empty'] }..." value="${seachTitle }"  />
          <button class="submit-search" type="button">${LANG['website.common.search.name'] }</button>
<%--        </form>--%>
      </div>
      
      <div class="refresh" style="float: left;margin-left: 260px;width: 160px;">
        <a href="javascript:top.joinFromSIPor323();"><i class="h323icon"></i>${LANG['website.conf.list.pairing.h323orsip'] }</a>
      </div>
      
      <div id="loadingContainer" style="display: none;position: absolute;">
		<img src='/assets/images/animate/loading.gif'/>
		<span style="position: relative;bottom: 10px;">${LANG['website.common.loading.message'] }</span>
	 </div>
      <div class="refresh">
        <a href="javascript:sycnConf();"><i class="icon"></i>${LANG['bizconf.jsp.login.res8'] }</a>
      </div>
    </div>

    <div class="box">
    	<!-- 正在进行的会议 -->
    	<c:if test="${fn:length(dringConfList)>0 }">
			<c:forEach var="dringConf" items="${dringConfList}" varStatus="dringStatus">
				<c:choose>
					<c:when test="${dringConf.permanentConf eq CONF_PERMANENT_UNABLE  and !dringConf.clientCycleConf and !dringConf.portalCycleConf or dringConf.clientScheduleConf }">
							<div class="item-meeting active clearfix">
							        <div class="calendar">
							          <span class="date"><fmt:formatDate value="${dringConf.startTime}" pattern="yyyy-MM-dd" /></span>
							          <span class="time"><fmt:formatDate value="${dringConf.startTime}" pattern="HH:mm"/></span>
							        </div>
							        <div class="summary">
							          <h4><a onclick="viewConf(${dringConf.id})">${dringConf.confName}</a></h4>
							          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${dringConf.confZoomId}</span>
							        </div>
							        <ul class="list-actions">
							          <li><a href="javascript:top.inventContact('${dringConf.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
				         			  <li><a href="javascript:top.inventContactByMsg('${dringConf.id}',false,1);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
							          <li><a onclick="copyViewConf(${dringConf.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
							          <li class="disabled unable-active" style="padding-right:0px;">
							            <i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}
<!-- 							            <div class="options"> -->
<!-- 							              <span class="corner"></span> -->
<!-- 							              <div class="text-overflow"> -->
<!-- 							                <button class="btn btn-primary" onclick="updateBookMeeting(${dringConf.id})">${LANG['bizconf.jsp.conf_list_index.update.conf']}</button> -->
<!-- 							                <button class="btn btn-primary" onclick="delConf(${dringConf.id});">${LANG['bizconf.jsp.conf_list_index.cancel.conf']}</button> -->
<!-- 							              </div> -->
<!-- 							            </div> -->
							          </li>
							          <li><button style="width:78px" onclick="joinConference('${dringConf.id}','${dringConf.confZoomId}');"  class="btn btn-success">${LANG['website.user.conf.list.enter.meeting'] }</button></li>
							        </ul>
							      </div>
					</c:when>
					<c:when test="${dringConf.clientCycleConf}">
						<div class="item-meeting regular active clearfix"">
					        <div class="calendar">
					           <span class="date">${LANG['website.user.conf.list.Cycle.conf'] }</span>
       					 			<span class="time"></span>
					        </div>
					        <div class="summary">
					          <h4><a onclick="viewConf(${dringConf.id})">${dringConf.confName}</a></h4>
					          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${dringConf.confZoomId}</span>
					        </div>
					        <ul class="list-actions">
					          <li><a href="javascript:top.inventContact('${dringConf.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
		         			  <li><a href="javascript:top.inventContactByMsg('${dringConf.id}',false,1);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
					          <li><a onclick="copyViewConf(${dringConf.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
					          <li class="disabled unable-active">
					            <i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}
<!-- 					            <div class="options"> -->
<!-- 					              <span class="corner"></span> -->
<!-- 					              <div class="text-overflow"> -->
<!-- 					             	  <button class="btn btn-primary" onclick="updateRecurr(${dringConf.id})">${LANG['bizconf.jsp.conf_list_index.update.conf']}</button> -->
<!-- 								      <button class="btn btn-primary"  onclick="delConf(${dringConf.id});">${LANG['bizconf.jsp.conf_list_index.cancel.conf']}</button> -->
<!-- 					              </div> -->
<!-- 					            </div> -->
					          </li>
					          <li><button style="width:78px" onclick="joinConference('${dringConf.id}','${dringConf.confZoomId}');" class="btn btn-success">${LANG['website.user.conf.list.enter.meeting'] }</button></li>
					        </ul>
					     </div>
					</c:when>
					<c:otherwise>
							<div class="item-meeting active clearfix">
							        <div class="calendar">
							          <span class="date"><fmt:formatDate value="${dringConf.startTime}" pattern="yyyy-MM-dd" /></span>
							          <span class="timerc"><fmt:formatDate value="${dringConf.startTime}" pattern="HH:mm"/></span>
							        </div>
							        <div class="summary">
							          <h4><a onclick="viewConf(${dringConf.id})" >${dringConf.confName}</a></h4>
							          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${dringConf.confZoomId}</span>
							        </div>
							        <ul class="list-actions">
							          <li><a href="javascript:top.inventContact('${dringConf.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
				         			  <li><a href="javascript:top.inventContactByMsg('${dringConf.id}',false,1);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
							          <li><a onclick="copyViewConf(${dringConf.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
							          <li class="disabled unable-active">
							            <i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}
<!-- 							            <div class="options"> -->
<!-- 							              <span class="corner"></span> -->
<!-- 							              <div class="text-overflow"> -->
<!-- 							                <button class="btn btn-primary" onclick="updateNowCycleBookMeetingInfo(${dringConf.id})">${LANG['bizconf.jsp.conf_list_index.res56.presenttime']}</button> -->
<!-- 										      <button class="btn btn-primary" onclick="updateCycleBookMeetingInfo(${dringConf.id})">${LANG['bizconf.jsp.conf_list_index.res56']}</button> -->
<!-- 										      <button class="btn btn-primary" onclick="delNowCycleBookMeetingInfo(${dringConf.id})">${LANG['bizconf.jsp.conf_list_index.res55.presenttime']}</button> -->
<!-- 										      <button class="btn btn-primary"  onclick="delConf(${dringConf.id});">${LANG['bizconf.jsp.conf_list_index.res55']}</button> -->
<!-- 							              </div> -->
<!-- 							            </div> -->
							          </li>
							          <li><button style="width:78px" onclick="joinConference('${dringConf.id}','${dringConf.confZoomId}')"  class="btn btn-success">${LANG['website.user.conf.list.enter.meeting'] }</button></li>
							        </ul>
							      </div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
    	<!-- 即将开始的会议 -->
    	<c:if test="${fn:length(upcomingConfList)>0 }">
			<c:forEach var="confbase" items="${upcomingConfList}" varStatus="upcomingStatus">
			<c:choose>
				<c:when test="${confbase.permanentConf eq CONF_PERMANENT_UNABLE  and !confbase.clientCycleConf and !confbase.portalCycleConf }">
						<div class="item-meeting clearfix itemupcoming">
					   <div class="calendar">
				          <span class="date"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
				          <span class="time"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
				        </div>
				        <div class="summary">
				          <h4><a onclick="viewConf(${confbase.id})">${confbase.confName}</a></h4>
				          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${confbase.confZoomId}</span>
				        </div>
			        <ul class="list-actions">
			          <li><a href="javascript:top.inventContact('${confbase.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
				      <li><a href="javascript:top.inventContactByMsg('${confbase.id}',false,2);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
			          <li><a onclick="copyViewConf(${confbase.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
			          <li class="settings">
			            <a><i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}</a>
			            <div class="options">
			              <span class="corner"></span>
			              <div class="text-overflow">
			                 <button class="btn btn-primary" onclick="updateBookMeeting(${confbase.id})">${LANG['bizconf.jsp.conf_list_index.update.conf']}</button>
							 <button class="btn btn-primary" onclick="delConf(${confbase.id});">${LANG['bizconf.jsp.conf_list_index.cancel.conf']}</button>
			              </div>
			            </div>
			          </li>
			          <li><button style="width:78px" onclick="joinConference('${confbase.id}','${confbase.confZoomId}');"  class="btn btn-primary">${LANG['bizconf.jsp.conf_list_index.res54'] }</button></li>
			        </ul>
			      </div>
				</c:when>
				<c:when  test="${confbase.clientCycleConf}">
							<div class="item-meeting regular itemupcoming clearfix">
				 	    <div class="calendar">
         					 <span class="date">${LANG['website.user.conf.list.Cycle.conf'] }</span>
         					 <span class="time"></span>
        				</div>
				        <div class="summary">
				          <h4><a onclick="viewConf(${confbase.id})">${confbase.confName}</a></h4>
				          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${confbase.confZoomId}</span>
				        </div>
				        <ul class="list-actions">
				          <li><a href="javascript:top.inventContact('${confbase.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
				          <li><a href="javascript:top.inventContactByMsg('${confbase.id}',false,2);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
				          <li><a onclick="copyViewConf(${confbase.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
				          <li class="settings">
				            <a><i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}</a>
				            <div class="options">
				              <span class="corner"></span>
				              <div class="text-overflow">
				               <button class="btn btn-primary" onclick="updateRecurr(${confbase.id})">${LANG['bizconf.jsp.conf_list_index.update.conf']}</button>
						      <button class="btn btn-primary"  onclick="delConf(${confbase.id});">${LANG['bizconf.jsp.conf_list_index.cancel.conf']}</button>
				              </div>
				            </div>
				          </li>
				          <li><button style="width:78px" onclick="joinConference('${confbase.id}','${confbase.confZoomId}');" class="btn btn-primary">${LANG['bizconf.jsp.conf_list_index.res54'] }</button></li>
				        </ul>
				      </div>
				</c:when>
				<c:otherwise>
					<div class="item-meeting itemupcoming clearfix">
					   <div class="calendar">
				          <span class="date"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
				          <span class="timerc"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
				        </div>
				        <div class="summary">
				          <h4><a onclick="viewConf(${confbase.id})">${confbase.confName}</a></h4>
				          <span>${LANG['system.list.meeting.meetingNo'] }${LANG['website.common.symbol.colon'] }${confbase.confZoomId}</span>
				        </div>
			        <ul class="list-actions">
			          <li><a href="javascript:top.inventContact('${confbase.id}');"><i class="icon invite"></i>${LANG['user.menu.conf.invite.email']}</a></li>
				      <li><a href="javascript:top.inventContactByMsg('${confbase.id}',false,2);"><i class="icon notice"></i>${LANG['user.menu.conf.invite.msg']}</a></li>
			          <li><a onclick="copyViewConf(${confbase.id})" ><i class="icon copy"></i>${LANG['user.menu.conf.copy.invite.confview']}</a></li>
			          <li class="settings">
			            <a><i class="icon settings"></i>${LANG['website.user.conf.list.setting.name']}</a>
			            <div class="options">
			              <span class="corner"></span>
			              <div class="text-overflow">
			                 <button class="btn btn-primary" onclick="updateNowCycleBookMeetingInfo(${confbase.id})">${LANG['bizconf.jsp.conf_list_index.res56.presenttime']}</button>
						      <button class="btn btn-primary" onclick="updateCycleBookMeetingInfo(${confbase.id})">${LANG['bizconf.jsp.conf_list_index.res56']}</button>
						      <button class="btn btn-primary" onclick="delNowCycleBookMeetingInfo(${confbase.id})">${LANG['bizconf.jsp.conf_list_index.res55.presenttime']}</button>
						      <button class="btn btn-primary"  onclick="delConf(${confbase.id});">${LANG['bizconf.jsp.conf_list_index.res55']}</button>
			              </div>
			            </div>
			          </li>
			          <li ><button style="width:78px" onclick="joinConference('${confbase.id}','${confbase.confZoomId}');"  class="btn btn-primary">${LANG['bizconf.jsp.conf_list_index.res54'] }</button></li>
			        </ul>
			      </div>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</c:if>
		<!-- 没有会议 -->
		<c:if test="${fn:length(upcomingConfList)<=0}">
			<div class="module-empty">
				<i class="icon icon-nothing"></i>
				<span>${LANG['website.user.conf.list.future.noconf']}</span>
			</div>	
		</c:if>
    </div>
    <!-- 加载更多会议 -->
	<c:if test="${fn:length(upcomingConfList) < upcomingNum }">
		<div class="has_more_container clearfix">
			<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
				<img src="/assets/images/animate/loading-mini.gif" alt="" style="margin-top: 8px;"/>
				<span style="position: absolute;left: 35px;">${LANG['website.common.loading.message']}</span>
			</div>
<%--				<a class="has_more" href="javascript:;" status="2" pageNo="2">${LANG['website.user.conf.list.show.more']}</a>--%>
			<a class="has_more" href="javascript:;" pageNo="2">${LANG['website.user.conf.list.show.more']}</a>
		</div>
	</c:if>
    <script type="text/javascript">
	var colon="${LANG['website.common.symbol.colon']}";			//冒号
	var exmark="${LANG['website.common.symbol.exmark']}";		//感叹号
	var minuteUnit="${LANG['website.common.minute.name']}";		//分钟
	var minutesUnit="${LANG['website.common.minutes.name']}";		//分钟
	var hourUnit="${LANG['website.common.hour.name']}";			//小时
	var hoursUnit="${LANG['website.common.hours.name']}";			//小时
	var yestName="${LANG['website.common.yesterday.name']}";	//昨天
	var todayName="${LANG['website.common.today.name']}";		//今天
	var tomoName="${LANG['website.common.tomorrow.name']}";		//明天
	var serverDate = "${serverDate}";
	var timeObj = {
			seconds: "${LANG['website.user.conf.list.js.constant.seconds']}",	//"不到1分钟",
			minute: "${LANG['website.user.conf.list.js.constant.minute']}",		//"大约1分钟",
			minutes: "${LANG['website.user.conf.list.js.constant.minutes']}",	//"%d 分钟",
			hour: "${LANG['website.user.conf.list.js.constant.hour']}",			//"大约1小时",
			hours: "${LANG['website.user.conf.list.js.constant.hours']}",		//"大约%d小时",
			day: "${LANG['website.user.conf.list.js.constant.day']}",			//"1天",
			days: "${LANG['website.user.conf.list.js.constant.days']}",			//"%d天",
			month: "${LANG['website.user.conf.list.js.constant.month']}",		//"大约1个月",
			months: "${LANG['website.user.conf.list.js.constant.months']}",		//"%d 月",
			year: "${LANG['website.user.conf.list.js.constant.year']}",			//"大约1年",
			years: "${LANG['website.user.conf.list.js.constant.years']}"		//"%d 年"
		};
		
	
	var CONFLIST_CONSTANT={
		enahead: "${LANG['website.user.conf.list.js.message.suffix.enahead']}",
		ahead:"${LANG['website.user.conf.list.js.message.suffix.ahead']}",		//前加入的会议
		enlater: "${LANG['website.user.conf.list.js.message.suffix.enlater']}",
		later:"${LANG['website.user.conf.list.js.message.suffix.later']}",		//后开始会议
		opened:"${LANG['website.user.conf.list.js.message.suffix.opened']}",	//会议已开始
		attend:"${LANG['website.user.conf.list.js.message.suffix.attend']}",		//前错过的会议
		delsucceed:"${LANG['website.user.conf.list.js.message.delete.succeed']}",	//删除会议成功！
		delconfirm:"${LANG['website.user.conf.list.js.message.delete.confirm']}",		//确认取消会议？
		delexception:"${LANG['website.user.conf.list.js.message.delete.exception']}",//取消会议出现异常！
		delconfirmall:"${LANG['website.user.conf.list.js.message.delete.confirm.all']}",		//确认取消整个周期会议？
		delexceptionall:"${LANG['website.user.conf.list.js.message.delete.exception.all']}",//取消周期会议失败！
		caladdinf:"${LANG['website.user.conf.list.js.message.calendar.adding']}",			//正在添加日历...
		endtime:"${LANG['website.user.conf.list.js.message.endtime']}",			//结束时间
		confduration:"${LANG['website.user.conf.list.js.message.confduration']}",		//会议时长
		noinviter:"${LANG['website.user.conf.list.js.message.noinviter']}",			//尚未邀请人
		findfail:"${LANG['website.user.conf.list.js.message.findfail']}",			//查询参会人失败
		confname:"${LANG['website.user.conf.list.js.message.confname']}",			//会议主题
		meetingHosted: "${LANG['website.user.conf.list.showhost.title']}",
		meetingAttended: "${LANG['website.user.conf.list.showattend.title']}",
		endmeetingsucc:"${LANG['website.user.conf.list.js.message.ended.succeed']}",
		endmeetingconfirm:"${LANG['website.user.conf.list.js.message.ended.confirm']}"
	};
	
	</script>
    <script type="text/javascript" src="/Formal/js/lib/jquery.js"></script>
    <script type="text/javascript" src="/Formal/js/lib/base.js"></script>
    <script type="text/javascript" src="/Formal/js/app/home.js?t=12"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.tipsy.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.date.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.meeting.list.js?var=11"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>

	<fmt:formatDate var="nowDate" value="${defaultDate}" type="date" pattern="yyyy-MM-dd"/>
    <script type="text/javascript">
   	
    function loadIframe() {
    	var url = "/user/conf/getControlPadConf?userRole=1";
    	//搜索条件
    	var confName = $("#searchTitle").val();
    	if(confName!="" && confName!="${LANG['website.user.create.immedia.js.message.confname.empty'] }..."){
    		url = addUrlParam(url, "confName", confName, true);
    	}
    	//开始时间
    	//所有的会议
    	var startTime = $("#startTime").val();
    	var endTime = $("#endTime").val();

    	if(startTime && endTime){
    		startTime = startTime.parseDate().format("yyyy-MM-dd 00:00:00");
    		endTime = endTime.parseDate().format("yyyy-MM-dd 00:00:00");
    		url = addUrlParam(url, "beginTime", startTime);
    		url = addUrlParam(url, "endTime", endTime);
    	}
    	url = addT(url);
    	parent.$("#mainFrame").attr("src", url);
    }
    
    function hasMore(){
    	$(".has_more").click(function() {
    		var hasMoreBtn = $(this);
    		hasMoreBtn.hide();
    		var loading = $(this).prev();
    		var hasContainer = $(this).closest(".has_more_container");
    		var ulList = hasContainer.prev();
    		loading.show();
    		var data = {};
    		var options = {};
    		
   			var beginTime = $("#startTime").val();
   	    	var endTime = $("#endTime").val();
   			if(beginTime){
   				options.beginTime = beginTime.parseDate().format("yyyy-MM-dd hh:mm:ss");
   			}
   			if(endTime) {
   				options.endTime = endTime.parseDate().add(DATETYPE_CONSTANT.DAY,1).format("yyyy-MM-dd hh:mm:ss");//+" 00:00:00"
   			}

    		var confName =  $("#seachTitle").val();
    		if(confName && confName!= CONFLIST_CONSTANT.confname){
    			options.confName = confName;
    		}
    		data.pageNo = parseInt($(this).attr("pageNo"), 10);
    		
    		app.loadMoreConf(data,function(result) {
    			if(result.status){
    				loading.find("img").hide();
    				loading.find("span").text(result.message);			
    			} else {
    				hasMoreBtn.attr("pageNo", data.pageNo+1);
    				loading.hide();
    				hasMoreBtn.show();
    				ulList.append(result);
    				
    				if($(".itemupcoming").length<parseInt('${upcomingNum}')){
    					hasContainer.show();
    				}else{
    					hasContainer.hide();
    				}
    			}
    			//重设高度
    			var height = parent.$("#mainFrame").contents().find("body").outerHeight();
    			parent.$("#mainFrame").height(height);
    			
    			//refreshTips();
    			//getParticipants();
    		}, options);
    	});
    }
    
    $(function(){
    	/*
    	var startDate = "${beginTime}";
    	var endDate = "${endTime}";
    	if(!startDate || startDate == ""){
    		var nowDate = "${nowDate}"; 
        	nowDate = getMonthStartDate(nowDate.parseDate());
        	$("#startTime").val(nowDate);
    	}
    	if(!endDate || endDate == ""){
    		var nowDate = "${nowDate}"; 
    		var endDate = getMonthEndDate(nowDate.parseDate().addMonth(1));
        	$("#endTime").val(endDate);
    	}
    	*/
    	if ($.browser.msie && $.browser.version < 10) {
    		$("#pmiHostKey").watermark("${LANG['bizconf.jsp.conf_list_index.number.6and10']}");
    	}

    	var lang = getBrowserLang(); 
    	if (lang=="zh-cn") {
    		$("#PMISetting").width("620px");
    		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
    	} else {
    		$("#PMISetting").width("660px");
    		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
    	}
    	
    	$("#startTime, #endTime").datepicker({
    		changeMonth: true,
    		changeYear: true,			
    		dateFormat: "yy-mm-dd",
    		onClose: function() {
    			$(this).blur();
    		}
    	});
    	/*
    	$.formValidator.initConfig({
    		debug : false,
    		onSuccess : function() {
    		},
    		onError : function() {
    		}
    	}); 
    	$("#endTime").formValidator({
    		tipID:"timeTip",
    		onShow : "",
    		onFocus : "",
    		onCorrect : ""
    	}).functionValidator({
   		    fun:function(val,elem){
  		    	//如果是按结束时间 结束日期不能早于开始日期
   		    	var startDateStr=$("#startTime").val();
   		    	var startTimeValue = startDateStr.parseDate();
   		    	var endDateValue = val.parseDate();
   		        if((endDateValue && startTimeValue && endDateValue.equalTo(startTimeValue)) || (endDateValue && startTimeValue && endDateValue.after(startTimeValue))){
   				    return true;
   			    }
   		        return "结束日期不能早于开始日期";
   			}
     	});
    	*/
		/**
		会议号码回车加入
		*/
		$("#searchTitle").keydown(function(event){
			if(event.keyCode == '13'){
				var searchTitle = $("#searchTitle").val();
				loadIframe();
			}
		});
    	$(".submit-search").click(function(){
    		loadIframe();
    	});
    	
    	$("#endTime").change(function(){
    		var startTime = $("#startTime").val();
    		if(startTime){
    			loadIframe();
    		}
    	});
    	$("#startTime").change(function(){
    		var endTime = $("#endTime").val();
    		if(endTime){
    			loadIframe();
    		}
    	});
    	hasMore();
    });
    function showLoading() {
    	var pWidth = parent.$("#mainFrame").width();
    	var pHeight = $(parent.window).height();
    	if(pHeight<450){
    		pHeight = 450;
    	}
    	var left = (pWidth-50)/2;
    	var top = (pHeight-150)/2;
    	$("#loadingContainer").css({left:left,top:top}).show();
    }

    function hideLoading() {
    	$("#loadingContainer").hide();
    }
    
    function sycnConf(){
    	var url = parent.$("#mainFrame").attr("src");
    	//alert(url);
    	showLoading();
    	$.ajax({
          	type: "POST",
          	url:"/user/conf/syncConf/",
          	dataType:"json",
          	success:function(data){
    			if(data.status == 100){
    				//top.successDialog("数据已同步");
    				 parent.$("#mainFrame").attr("src",url);
    			}else{
    				top.errorDialog("${LANG['website.user.conf.Synchronousdata.failure']}");
    			}
    			hideLoading();
          	},
            error:function(XMLHttpRequest, textStatus, errorThrown) {
            	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
            }
    	}); 
    }
	/*
	function updatePMI(id) {
		top.updateMeetingRoom(id);
	}
	*/
	
	function updateBookMeeting(id) {
		parent.parent.createReservationConf(id);
	}
	function updateRecurr(id) {
		top.updateRecurrConf(id);
	}
	/** 修改周期全部 */
	function updateCycleBookMeetingInfo(id) {
		parent.parent.updateCycleMeetingInfo(id,1);
	}
    /***********************修改本次会议 2014-08-25 Darren add  ****************************/
	function updateNowCycleBookMeetingInfo(id) {
		parent.parent.updateCycleMeetingInfo(id,3);
	}
	/***********************修改本次会议 2014-08-25 Darren add  ****************************/
	
	/***********************取消本次会议 2014-08-25 Darren add  ****************************/
	function delNowCycleBookMeetingInfo(confId,isEnded) {
		var msg = CONFLIST_CONSTANT.delsucceed;
		var confirmMsg = CONFLIST_CONSTANT.delconfirm;
		if(isEnded){
			msg =  CONFLIST_CONSTANT.endmeetingsucc;
			confirmMsg = CONFLIST_CONSTANT.endmeetingconfirm
		}
		parent.parent.promptDialog(confirmMsg,function(){
			$.ajax({
		      	type: "POST",
		      	url:"/user/conf/delSingleCycleConf/"+confId,
		      	dataType:"json",
		      	success:function(data){
					if(data){
						parent.parent.successDialog(msg);
						window.location.reload(true);
					}else{
						parent.parent.errorDialog(CONFLIST_CONSTANT.delexception);
					}
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		        }
			}); 
		});	
	}
	/***********************取消本次会议 2014-08-25 Darren add  ****************************/
    /**
	* 结束会议
	*/
	function delConf(confId,isEnded){
		var msg = CONFLIST_CONSTANT.delsucceed;
		var confirmMsg = CONFLIST_CONSTANT.delconfirm;
		if(isEnded){
			msg =  CONFLIST_CONSTANT.endmeetingsucc;
			confirmMsg = CONFLIST_CONSTANT.endmeetingconfirm
		}
		parent.parent.promptDialog(confirmMsg,function(){
			$.ajax({
		      	type: "POST",
		      	url:"/user/conf/delete/"+confId,
		      	dataType:"json",
		      	success:function(data){
					if(data){
						parent.parent.successDialog(msg);
						window.location.reload(true);
					}else{
						parent.parent.errorDialog(CONFLIST_CONSTANT.delexception);
					}
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		        }
			}); 
		});	
	}
	
    
	function viewConf(id){
		parent.parent.viewConf(id);
	}
	function copyViewConf(id){
		parent.parent.copyViewConf(id);
	}
	
	//将PMI编辑框设置回初始值
	function resetPmi(){
		var confName = "${pmi.confName}";
		$("#pmiName").val(confName);
		$("#pmiHostKey").val('${pmi.hostKey }');
		$("input[name=pmiOptionJbh]:eq('${pmi.optionJbh}')").attr("checked",'checked');
		$("input[name=pmiOptionStartType]:eq('${pmi.optionStartType}')").attr("checked",'checked');
	}
	//修改PMI
	function updatePMI(id){
		var conf = new Object();
		conf.id = id;
		conf.confName = $("#pmiName").val();
		conf.hostKey = $("#pmiHostKey").val();
		conf.optionJbh = $("input[name=pmiOptionJbh]:checked").val();
		conf.optionStartType = $("input[name=pmiOptionStartType]:checked").val();
		var pwd = $("#pmiHostKey").val();
		var reg = /^[a-zA-z0-9]+$/;
		if((pwd.length>0 && pwd.length<6) || (pwd.length>0 &&!ValidatorHostKey(pwd))){
			parent.errorDialog("${LANG['website.user.conflist.updatepmi.hostkey.error']}");
			resetPmi();
			return ;
		}
		
		$.ajax({
	      	type: "POST",
	      	url: "/user/conf/updatePmi",
	      	data:conf,
	      	dataType:"json",
	      	success:function(data){
	      		$("#pmiupdater").removeClass("active");
	      		window.location.reload(true);
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	parent.errorDialog("${LANG['bizconf.jsp.conf.personal.updmeetingroom.failure']}${LANG['website.common.symbol.exmark']}");
	        	$("#pmiupdater").removeClass("active");
	      		window.location.reload(true);
	        }
		});  
	}
	
	function joinConference(id,confNo){
		var hostId = '${user.zoomId}';
		var hostName = '${user.trueName}';
		var token= '${user.zoomToken}';
		joinMeeting(1,id,confNo,hostId,hostName,token);
	}
    </script>
  </body>
</html>