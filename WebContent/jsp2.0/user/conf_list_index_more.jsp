<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<cc:confList var="CONF_STATUS_OPENING" />
<cc:confList var="CONF_STATUS_SUCCESS" />
<cc:confList var="CONF_STATUS_FINISHED" />
<cc:confList var="CONF_PERMANENT_ENABLED_MAIN" />
<cc:confList var="CONF_PERMANENT_UNABLE" />
<cc:confList var="CONF_PERMANENT_ENABLED_CHILD" />

<c:forEach var="confbase" items="${confList}">
	<c:choose>
		<c:when test="${confbase.permanentConf eq CONF_PERMANENT_UNABLE  and !confbase.clientCycleConf and !confbase.portalCycleConf or confbase.clientScheduleConf }">
			<div class="item-meeting itemupcoming clearfix">
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
	          <li><button style="width:78px" onclick="joinConference('${confbase.id}','${confbase.confZoomId}');"  class="btn btn-primary">${LANG['bizconf.jsp.conf_list_index.res54'] }</button></li>
	        </ul>
	      </div>
		</c:otherwise>
	</c:choose>
</c:forEach>
