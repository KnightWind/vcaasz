<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link rel="stylesheet" type="text/css" href="/assets/css/biz.base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.meeting.css?var=1" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/tipsy.css" />
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
<cc:confList var="CONF_PERMANENT_ENABLED_MAIN"/>
<cc:confList var="CONF_PERMANENT_UNABLE"/>
<cc:confList var="CONF_PERMANENT_ENABLED_CHILD"/>
<c:if test="${!empty user}">
	<c:set var="pageSize" value="${user.pageSize}"></c:set>
</c:if>
<c:if test="${empty user}">
	<c:set var="pageSize" value="10"></c:set>
</c:if>
<script type="text/javascript" src="/assets/js/apps/biz.join.common.js"> </script>
</head>
<body>
<div style="">
	<!-- 正在进行的会议 -->
	<div class="meeting-module meeting-module-run">
		<div class="module-head">
			<i class="icon icon-expand"></i>
			<span class="runnig-title">${LANG['website.user.conf.list.opening.title'] }</span>
		</div>
		<div class="module-body">
			<!-- 会议列表 -->
			<ul class="clearfix">
			<c:if test="${!empty pmi}">
				<li class="module-item">
						<div class="item-info clearfix" style="80px;background-color: #e6f5de">
							<div class="module-text" style="background-color: #e6f5de">
								<a class="pmisetting" style="" onclick="updatePMI(${pmi.id});">
									<i class="icon icon-settings"></i>
									<i>${LANG['website.user.conf.list.setting.name'] }</i>
								</a>
								<span class="actions">
									<a class="input-green" href="javascript:joinMeeting(1,'${pmi.id}','${dringConf.confZoomId}','${user.zoomId}','${user.trueName}','${user.zoomToken}');">${LANG['website.user.conf.list.enter.meeting'] }</a>
								</span>
							</div>
							<div class="module-date">
								<div style="float:left;">
									<span class="time" style="padding-left:10px; line-height: 56px; font-size: 12px;color: #878787">${LANG['website.user.conf.userconfid']}${LANG['website.common.symbol.colon'] }</span>
								</div>
								<div class="title">
									<span style="font-size: 26px;color: #519b00; cursor: pointer;"  title="${pmi.confZoomId}" onclick="viewConf(${pmi.id})">${pmi.confZoomId}</span>
									<!--会议室状态 -->
									<c:choose>
										<c:when test="${pmi.confStatus eq 2}">
											<span style="color: red; font-size: 12px;margin-left: 16px;">${LANG['website.user.conf.userconfbusy'] }</span>
										</c:when>
										<c:otherwise>
											<span style="color: green; font-size: 12px;margin-left: 16px;">${LANG['website.user.conf.userconfleisure'] }</span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</li>
			</c:if>
			<!-- 正在开始的会议 -->
			<c:if test="${fn:length(dringConfList)>0 }">
			<c:forEach var="dringConf" items="${dringConfList}" varStatus="dringStatus">
				<li class="module-item">
					<div class="item-info clearfix">
						<div class="module-text">
							<c:if test="${dringConf.permanentConf eq CONF_PERMANENT_UNABLE  and !dringConf.clientCycleConf}">
								<span class="duration" duration="${dringConf.duration}">
									<i class="duration-tipsy">
										<i class="icon icon-duration"></i>
										<i class="duration-text">${dringConf.duration}</i>
									</i>
								</span>
							</c:if>
							<c:if test="${!dringConf.clientCycleConf}">
								<c:if test="${user.id eq dringConf.compereUser}">
									<a class="pmisetting" style="cursor: pointer;text-decoration: none;" href="javascript:viewInviteUser('${dringConf.id}');">
										<i class="icon icon-invite"></i>
										<i>${LANG['website.user.conf.list.invite.name']}</i>
									</a>
								</c:if>																							
							</c:if>

							<!-- 发送短信邀请 -->
							<c:if test="${user.id eq dringConf.compereUser}">
								<a class="invite" style="cursor: pointer;width: 90px;" href="javascript:top.inventContactByMsg('${dringConf.id}',false,1);">
									<i class="icon icon-invite"></i>
									<i>短信邀请</i>
								</a>
							</c:if>
							
						<%--
						 <!-- 正在进行的会议--设置功能 -->
							<a class="settings">
								<i class="icon icon-settings"></i>
								<i>${LANG['website.user.conf.list.setting.name'] }</i>
							</a>
						--%>

							<span class="actions">
								<c:if test="${user.id != dringConf.compereUser}">
									<a class="input-green" href="javascript:joinMeeting(1,'${dringConf.id}','${dringConf.confZoomId}','${user.zoomId}','${user.trueName}','${user.zoomToken}');">${LANG['website.user.conf.list.join.meeting'] }</a>
								</c:if>
								<c:if test="${user.id eq dringConf.compereUser}">
									<a class="input-green" href="javascript:joinMeeting(1,'${dringConf.id}','${dringConf.confZoomId}','${user.zoomId}','${user.trueName}','${user.zoomToken}');">${LANG['website.user.conf.list.enter.meeting'] }</a>
								</c:if>
							</span>
						</div>
						<div class="module-date">
							<fmt:formatDate var="runStartTime" value="${dringConf.startTime}" pattern="yyyy-MM-dd HH:mm" />
	      	  				<fmt:formatDate var="runEndTime" value="${dringConf.endTime}" pattern="yyyy-MM-dd HH:mm" />
							<c:choose>
								<c:when test="${dringConf.clientCycleConf }">
									<div class="calendar_cr" original-title="${runStartTime}" startTime="${runStartTime}" endTime="${runEndTime}" status="run">
										<span class="date showCalendarForDate">${LANG['website.user.conf.list.Cycle.conf'] }</span>
										<span class="time"></span>
									</div>
								</c:when>
								<c:when test="${dringConf.portalCycleConf }">
									<div class="calendar_cg" original-title="${runStartTime}" startTime="${runStartTime}" endTime="${runEndTime}" status="run">
										<span class="date showCalendarForDate"><fmt:formatDate value="${dringConf.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${dringConf.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="calendar" original-title="${runStartTime}" startTime="${runStartTime}" endTime="${runEndTime}" status="run">
										<span class="date showCalendarForDate"><fmt:formatDate value="${dringConf.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${dringConf.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:otherwise>
							</c:choose>
							
							<div class="title">
								<a href="javascript:;" onclick="viewConf(${dringConf.id})" title="${dringConf.confName}">${dringConf.confName}</a>
							</div>
						</div>
					</div>
					<div class="item-actions clearfix">
						<div class="lside" style="display: none;">
							<button class="input-gray" onclick="sendNoticeEmail(${dringConf.id});">${LANG['website.user.conf.list.remind.outlook'] }</button>
							<button class="input-gray" onclick="sendNoticeEmail(${dringConf.id});">${LANG['website.user.conf.list.remind.gmail'] }</button>
						</div>
						<div class="rside"><!-- 结束会议功能 -->
						${confbase.id}
							<c:if test="${user.id eq dringConf.createUser}">
								<button class="input-gray" onclick="delConf(${dringConf.id},true);">${LANG['website.common.option.endMeeting']}</button>
						   	</c:if>
						</div>
					</div>
					<c:set var="drDesc" value="${fn:replace(dringConf.confDesc,' ','&nbsp;')}" />
					<c:if test="${drDesc!=null && drDesc!='' }"> 
					<div class="item-desc clearfix">
						${fn:replace(drDesc,vEnter,"<br>")}
					</div>
					</c:if>
				</li>
			</c:forEach>
		</c:if>
			</ul>

			<!-- 加载更多会议 -->
			<c:if test="${fn:length(dringConfList)==pageSize }">
				<div class="has_more_container clearfix">
					<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
						<img src="/assets/images/animate/loading-mini.gif" alt="" style="margin-top: 8px;"/>
						<span style="position: absolute;left: 35px;">${LANG['website.common.loading.message']}</span>
					</div>
					<a class="has_more" href="javascript:;" status="1" pageNo="2">${LANG['website.user.conf.list.show.more']}</a>
				</div>
			</c:if>
		</div>
	</div>

	<!-- 即将开始的会议 -->
	<div class="meeting-module meeting-module-come">
		<div class="module-head">
			<i class="icon icon-expand"></i>
			<span class="comming-title">${LANG['website.user.conf.list.future.title']}</span>
		</div>
		<div class="module-body">
			<!-- 会议列表 -->
			<c:if test="${fn:length(upcomingConfList)>0 }">
			<ul class="clearfix">
			<c:forEach var="confbase" items="${upcomingConfList}" varStatus="upcomingStatus">
				<li class="module-item">
					<div class="item-info clearfix">
						<div class="module-text">
							<c:if test="${confbase.permanentConf eq CONF_PERMANENT_UNABLE  and !confbase.clientCycleConf}">
								<span class="duration" duration="${confbase.duration}">
									<i class="duration-tipsy">
										<i class="icon icon-duration"></i>
										<i class="duration-text">${confbase.duration}</i>
									</i>
								</span>
							</c:if>
							<c:if test="${confbase.permanentConf eq CONF_PERMANENT_ENABLED_MAIN}">
								<fmt:formatDate var="endTimeDuration" value="${confbase.endTime}" pattern="yyyy-MM-dd HH:mm" />
								<span class="duration" endTime="${endTimeDuration}">
									<i class="duration-tipsy">
										<i class="icon icon-duration"></i>
										<i class="duration-text">${endTimeDuration}</i>
									</i>
								</span>
							</c:if>
<!-- 							<span class="compere"> -->
<!-- 								<i class="compere-tipsy nobr" original-title="${LANG['website.user.conf.list.host.name']}${LANG['website.common.symbol.colon']}${confbase.compereName}"> -->
<!-- 									<i class="icon icon-compere"></i> -->
<!-- 									<i class="compere-name">${confbase.compereName}</i> -->
<!-- 								</i> -->
<!-- 							</span> -->
							
							<!-- 发送短信邀请 -->
							<c:if test="${user.id eq confbase.compereUser}">
								<a class="invite" style="cursor: pointer;" href="javascript:top.inventContactByMsg('${confbase.id}',false,2);">
									<i class="icon icon-invite"></i>
									<i>短信邀请</i>
								</a>
							</c:if>
							
							<c:if test="${user.id eq confbase.compereUser}">
								<a class="invite" style="cursor: pointer;" href="javascript:top.inventContact('${confbase.id}');">
									<i class="icon icon-invite"></i>
									<i>${LANG['website.user.conf.list.invite.name']}</i>
								</a>
							</c:if>
							<span style="display: none;" class="attendance inviters" confId="${confbase.id}" <c:if test="${user.id eq confbase.compereUser}">style="cursor: pointer;" onclick="viewInviteUser('${confbase.id}');"</c:if>>
								<i class="attendance-tipsy">
									<i class="icon icon-attendance"></i>
									<i class="attendCount" id="attendCount_${confbase.id}">0</i>
								</i>
							</span>
							<a class="settings" >
								<i class="icon icon-settings"></i>
								<i>${LANG['website.user.conf.list.setting.name'] }</i>
							</a>
							<span class="actions">
<%--								<c:if test="${confbase.joinTimeFlag}">--%>
								<a class="input-blue" href="javascript:joinMeeting(1,'${confbase.id}','${dringConf.confZoomId}','${user.zoomId}','${user.trueName}','${user.zoomToken}');">${LANG['website.user.conf.list.start.meeting'] }</a>
<%--								</c:if>--%>
<%--								<c:if test="${!confbase.joinTimeFlag}">--%>
<%--								<a class="input-disable" href="javascript:;">${LANG['website.user.conf.list.notstart.meeting'] }</a>--%>
<%--								</c:if>--%>
							</span>
						</div>
						<div class="module-date">
							<fmt:formatDate var="comingStartTime" value="${confbase.startTime}" pattern="yyyy-MM-dd HH:mm" />
							<fmt:formatDate var="comingEndTime" value="${confbase.endTime}" pattern="yyyy-MM-dd HH:mm" />
							<c:choose>
								<c:when test="${confbase.clientCycleConf }">
									<div class="calendar_cr" original-title="${comingStartTime}" startTime="${comingStartTime}" endTime="${comingEndTime}" status="come">
										<span class="date showCalendarForDate">${LANG['website.user.conf.list.Cycle.conf'] }</span>
										<span class="time"></span>
									</div>
								</c:when>
								<c:when test="${confbase.portalCycleConf }">
									<div class="calendar_cg" original-title="${comingStartTime}" startTime="${comingStartTime}" endTime="${comingEndTime}" status="come">
										<span class="date showCalendarForDate"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="calendar" original-title="${comingStartTime}" startTime="${comingStartTime}" endTime="${comingEndTime}" status="come">
										<span class="date showCalendarForDate"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="title">
								<a href="javascript:;"  onclick="viewConf(${confbase.id})"  title="${confbase.confName}">${confbase.confName}</a>
							</div>
						</div>
					</div>
					<div class="item-actions clearfix">
						<div class="lside" style="display: none;">
							<button class="input-gray" onclick="sendNoticeEmail(${confbase.id})">${LANG['website.user.conf.list.remind.outlook'] }</button>
							<button class="input-gray" onclick="sendNoticeEmail(${confbase.id})">${LANG['website.user.conf.list.remind.gmail'] }</button>
						</div>
						<div class="rside"><!-- 取消会议和修改会议功能 -->
							<c:if test="${user.id eq confbase.createUser}">
								<c:choose>
								 	<c:when test="${confbase.cycleId!=0}">
								 		<button class="input-gray" onclick="updateNowCycleBookMeetingInfo(${confbase.id})">修改本次</button>
 								 		<button class="input-gray" onclick="delNowCycleBookMeetingInfo(${confbase.id})">取消本次</button>
								 		<button class="input-gray" onclick="updateCycleBookMeetingInfo(${confbase.id})">${LANG['website.common.option.modify.all'] }</button>
 								 		<button class="input-gray" onclick="delConf(${confbase.id});">${LANG['website.common.option.cancel.all']}</button> 
								 		
<%--								 		<button class="input-gray" onclick="updateCycleBookMeetingInfo(${confbase.id})">${LANG['website.common.option.modify'] }</button>--%>
<%--								 		<button class="input-gray" onclick="delConf(${confbase.id});">${LANG['website.common.option.cancel']}</button>--%>
								 	</c:when>
								 	<c:when test="${confbase.clientCycleConf }">
								 		<button class="input-gray" onclick="updateRecurr(${confbase.id})">${LANG['website.common.option.modify'] }</button>
								 		<button class="input-gray" onclick="delConf(${confbase.id});">${LANG['website.common.option.cancel']}</button>
								 	</c:when>
								 	<c:otherwise>
								 		<button class="input-gray" onclick="updateBookMeeting(${confbase.id})">${LANG['website.common.option.modify'] }</button>
								 		<button class="input-gray" onclick="delConf(${confbase.id});">${LANG['website.common.option.cancel']}</button>
								 	</c:otherwise>
							 	</c:choose>
						   	</c:if>
						</div>
					</div>
					<!-- 显示描述信息 -->
					<c:set var="drDesc" value="${fn:replace(confbase.confDesc,' ','&nbsp;')}" />
					<c:if test="${drDesc!=null && drDesc!='' }"> 
					<div class="item-desc clearfix">
							${fn:replace(drDesc,vEnter,"<br>")}
					</div>
					</c:if>
				</li>
			</c:forEach>
			</ul>
			</c:if>
			<!-- 没有会议 -->
			<c:if test="${fn:length(upcomingConfList)<=0}">
				<div class="module-empty">
					<i class="icon icon-nothing"></i>
					<span>${LANG['website.user.conf.list.future.noconf']}</span>
				</div>	
			</c:if>
			<!-- 加载更多会议 -->
			<c:if test="${fn:length(upcomingConfList)==pageSize }">
				<div class="has_more_container clearfix">
					<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
						<img src="/assets/images/animate/loading-mini.gif" alt="" style="margin-top: 8px;"/>
						<span style="position: absolute;left: 35px;">${LANG['website.common.loading.message']}</span>
					</div>
					<a class="has_more" href="javascript:;" status="2" pageNo="2">${LANG['website.user.conf.list.show.more']}</a>
				</div>
			</c:if>	
		</div>
	</div>
	
	<!-- 已经参加的会议 -->
	<div id="passConfPanel" class="meeting-module meeting-module-past" style="display: none;">
		<div class="module-head">
			<i class="icon icon-expand"></i>
			<span id="attendConfTitle" class="comming-title">${LANG['website.user.conf.list.attend.title']}</span>
		</div>
		<div class="module-body">
			<!-- 会议列表 -->
			<c:if test="${fn:length(attendedConfList)>0 }">
			<ul class="clearfix">
			 <c:forEach var="confbase" items="${attendedConfList}" varStatus="attendedStatus">
				<li class="module-item">
					<div class="item-info clearfix">
						<div class="module-text">
							<c:if test="${confbase.permanentConf eq CONF_PERMANENT_UNABLE  and !confbase.clientCycleConf}">
								<span class="duration" duration="${confbase.duration}">
									<i class="duration-tipsy">
										<i class="icon icon-duration"></i>
										<i class="duration-text">${confbase.duration}</i>
									</i>
								</span>
							</c:if>
							<c:if test="${confbase.permanentConf eq CONF_PERMANENT_ENABLED_CHILD}">
								<fmt:formatDate var="endTimeDuration" value="${confbase.endTime}" pattern="yyyy-MM-dd HH:mm" />
								<span class="duration" endTime="${endTimeDuration}">
									<i class="duration-tipsy">
										<i class="icon icon-duration"></i>
										<i class="duration-text">${endTimeDuration}</i>
									</i>
								</span>
							</c:if>
<!-- 							<span class="compere"> -->
<!-- 								<i class="compere-tipsy nobr" original-title="${LANG['website.user.conf.list.host.name']}${LANG['website.common.symbol.colon']}${confbase.compereName}"> -->
<!-- 									<i class="icon icon-compere"></i> -->
<!-- 									<i class="compere-name">${confbase.compereName}</i> -->
<!-- 								</i> -->
<!-- 							</span> -->
							<span class="attendance inviters" confId="${confbase.id}">
								<i class="attendance-tipsy">
									<i class="icon icon-attendance"></i>
									<i class="attendCount" id="attendCount_${confbase.id}">0</i>
								</i>
							</span>
							<a class="settings">
								<i class="icon icon-settings"></i>
								<i>${LANG['website.user.conf.list.setting.name'] }</i>
							</a>
							<!-- 重新创建按钮 -->
							<span class="actions">
								<c:if test="${user.id!=confbase.createUser }">
								<a class="input-disable">${LANG['website.user.conf.list.attend.title.action']}</a>
								</c:if>
								<c:if test="${user.id==confbase.createUser }">
									<!--周期会议 或者  即时会议 -->
									<c:if test="${confbase.confType gt 0 }">
										<a class="input-disable">${LANG['website.user.conf.list.attend.title.action']}</a>
									</c:if>
									<!-- 预约会议 -->
									<c:if test="${confbase.confType eq 0 }">
										<a class="input-blue" href="javascript:reCreateReservationConf(${confbase.id});">${LANG['website.user.conf.list.recreate']}</a>
									</c:if>
								</c:if>
							</span>
						</div>
						<div class="module-date">
							<fmt:formatDate var="passStartTime" value="${confbase.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							<fmt:formatDate var="passEndTime" value="${confbase.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							<c:choose>
								<c:when test="${confbase.clientCycleConf }">
									<div class="calendar_cr" original-title="${passStartTime}" startTime="${passStartTime}" endTime="${passEndTime}" status="pass">
										<span class="date showCalendarForDate">${LANG['website.user.conf.list.Cycle.conf']}</span>
										<span class="time"></span>
									</div>
								</c:when>
								<c:when test="${confbase.portalCycleConf}">
									<div class="calendar_cg" original-title="${passStartTime}" startTime="${passStartTime}" endTime="${passEndTime}" status="pass">
										<span class="date showCalendarForDate"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="calendar" original-title="${passStartTime}" startTime="${passStartTime}" endTime="${passEndTime}" status="pass">
										<span class="date showCalendarForDate"><fmt:formatDate value="${confbase.startTime}" pattern="yyyy-MM-dd" /></span>
										<span class="time"><fmt:formatDate value="${confbase.startTime}" pattern="HH:mm"/></span>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="title">
								<a href="javascript:;" onclick="viewConf(${confbase.id})"  title="${confbase.confName}">${confbase.confName}</a>
							</div>
						</div>
					</div>
					<div class="item-actions clearfix">
 						<!-- outlook and Gmail 日历提醒-->
						<div class="lside" style="display: none;">
							<button class="input-gray" onclick="sendNoticeEmail(${confbase.id});">${LANG['website.user.conf.list.remind.outlook']}</button>
							<button class="input-gray" onclick="sendNoticeEmail(${confbase.id});">${LANG['website.user.conf.list.remind.gmail']}</button>
						</div>
 
						<div class="rside">
							<button class="input-gray"  onclick="viewConf(${confbase.id})"  >${LANG['website.user.conf.list.show.confinfo']}</button>
						</div>
					</div>
					<!-- 显示描述信息 -->
					<c:set var="drDesc" value="${fn:replace(confbase.confDesc,' ','&nbsp;')}" />
					<c:if test="${drDesc!=null && drDesc!='' }"> 
					<div class="item-desc clearfix">
							${fn:replace(drDesc,vEnter,"<br>")}
					</div>
					</c:if>
				</li>
			</c:forEach>
			</ul>
			</c:if>
			<!-- 没有会议 -->
			<c:if test="${fn:length(attendedConfList)<=0}">
				<div class="module-empty">
					<i class="icon icon-nothing"></i>
					<span>${LANG['website.user.conf.list.attend.nohost']}</span>
				</div>	
			</c:if>
			<!-- 加载更多会议 -->
			<c:if test="${fn:length(attendedConfList)==pageSize }">
				<div class="has_more_container clearfix">
					<div style="display: none;width: 180px;margin: 0 auto;position: relative;text-align: left;">
						<img src="/assets/images/animate/loading-mini.gif" alt="" style="margin-top: 8px;"/>
						<span style="position: absolute;left: 35px;">${LANG['website.common.loading.message']}</span>
					</div>
					<a class="has_more" href="javascript:;" status="3" pageNo="2">${LANG['website.user.conf.list.show.more']}</a>
				</div>
			</c:if>	
		</div>	
	</div>
	<div class="empty-space">&nbsp;</div>
</div>

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

<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.tipsy.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.date.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.meeting.list.js?var=12"></script>
<script type="text/javascript">
	function sendNoticeEmail(id) {
		var userId = "${user.id}";
		if(userId){
			var data = {};
			data.confId = id;
			app.sendNoticeEmail(data, function(result) {
				if(result){
					if(result.status && result.status==1){
						parent.parent.successDialog(result.message);
					} else {
						parent.parent.errorDialog(result.message);
					}
				}
			},{message:CONFLIST_CONSTANT.caladdinf, ui:top.window});		
		} else {
			parent.parent.addCalendar(id);
		}
	}
	function viewInviteUser(confId){
		top.editInventContact(confId);
	}
	function viewConf(id){
		parent.parent.viewConf(id);
	}
	function updateBookMeeting(id) {
		parent.parent.createReservationConf(id);
	}
	
	function updatePMI(id) {
		parent.parent.updateMeetingRoom(id);
	}
	
	function updateRecurr(id) {
		top.updateRecurrConf(id);
	}
	/** 修改周期全部 */
	function updateCycleBookMeetingInfo(id) {
		parent.parent.updateCycleMeetingInfo(id,1);
	}
	function reCreateReservationConf(id) {
		parent.parent.reCreateReservationConf(id);
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
	function delAllConf(cycId,confId){
		parent.parent.promptDialog(CONFLIST_CONSTANT.delconfirmall,function(){
			$.ajax({
		      	type: "POST",
		      	url:"/user/conf/deleteCycleConfInfo/"+cycId+"?confId="+confId,
		      	dataType:"json",
		      	success:function(data){
					if(data){
						parent.parent.successDialog(CONFLIST_CONSTANT.delsucceed);
						window.location.reload(true);
					}else{
						parent.parent.errorDialog(CONFLIST_CONSTANT.delexceptionall);
					}
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		        }
			}); 
		});
	}
	function delConfForPerConf(confId,isEnded){
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
	
	<%--
	// 邮件提醒
	function sendNoticeEmail(id) {
		var userId = "${user.id}";
		if(userId){
			var data = {};
			data.confId = id;
			app.sendNoticeEmail(data, function(result) {
				if(result){
					if(result.status && result.status==1){
						top.successDialog(result.message);
					} else {
						top.errorDialog(result.message);
					}
				}
			},{message:"${LANG['bizconf.jsp.add_calendar_notice.res9']}...", ui:top.window});		
		} else {
			top.addCalendar(id);
		}
	}
	--%>
	
	function initPage(){
		top.joinMeetingReload();
	}
	initPage();
	
</script>
</body>
</html>
