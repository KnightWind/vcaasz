<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Insert title here</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body>
<div class="details-dialog view-conf">
<div class="details-container">
	<div class="details clearfix">
		<c:choose>
	    	<c:when test="${conf.pmi }">
				<div class="basic-info">
						<div class="panel">
								<ul>
									<li class="item clearfix block-layout">
										<span class="key">${LANG['website.user.view.conf.title'] }：</span>
										<span class="val" title="${conf.confName }">${conf.confName }</span>
									</li>
								</ul>
							</div>
						</div>
						<div class="args-info">
							<div class="panel">
								<ul>
									<li class="item clearfix">
									<span class="key">${LANG['website.user.view.conf.invite.number'] }：</span>
									<span class="val">
										<%--<c:if test="${!empty user && inviteUserCount != 0}">
											<a onclick="viewInviteUser('${confId}')">${inviteUserCount} ${LANG['website.user.view.conf.people'] }</a>
										</c:if>
										<c:if test="${empty user || inviteUserCount == 0}">
											${inviteUserCount} ${LANG['website.user.view.conf.people'] }
										</c:if>--%>
										${inviteUserCount} ${LANG['website.user.view.conf.people'] }
									</span>
									</li>
									<li class="item clearfix">
										<span class="key">${LANG['website.user.view.conf.conf.confCode'] }：</span>
										<span class="val">${conf.confZoomId }</span>
									</li>
									<li class="item clearfix">
										<span class="key">${LANG['website.user.join.page.cpass'] }：</span>
										<span class="val">
											<c:if test="${conf.hostKey != '' }">${conf.hostKey }</c:if>
											<c:if test="${conf.hostKey == '' }">${LANG['com.bizconf.vcaasz.entity.ConfBase.confType.0'] }</c:if>
										</span>
									</li>
									
									<li class="item clearfix">
										<span class="key">${LANG['user.menu.conf.pad.videooption'] }：</span>
										<span class="val">
											<c:if test="${conf.optionStartType eq 1 }">${LANG['user.menu.conf.pad.videooption.on'] }</c:if>
									 		<c:if test="${conf.optionStartType eq 2 }">${LANG['user.menu.conf.pad.videooption.off'] }</c:if>
										</span>
									</li>
									<li class="item clearfix">
										<span class="key">${LANG['website.user.view.conf.conf.preHostor'] }：</span>
										<span class="val">
											<c:if test="${conf.optionJbh eq 0 }"> ${LANG['website.common.option.no'] }</c:if>
											<c:if test="${conf.optionJbh eq 1 }">${LANG['website.common.option.yes'] }</c:if>
										</span>
									</li>
								</ul>
							</div>
						</div>
					</c:when>
					<c:when test="${conf.clientCycleConf }">
						<div class="basic-info">
							<div class="panel">
								<ul>
									<li class="item clearfix block-layout"><span class="key">${LANG['website.user.view.conf.title']
											}：</span> <span class="val nobr" title="${conf.confName }">${conf.confName
											}</span></li>
									<li class="item clearfix"><span class="key">${LANG['website.user.view.conf.conf.confCode']
											}：</span> <span class="val">${conf.confZoomId }</span></li>
									<li class="item clearfix"><span class="key">${LANG['website.user.view.conf.conf.preHostor']
											}：</span> <span class="val"> <c:if
												test="${conf.optionJbh eq 0 }">${LANG['website.common.option.no'] }</c:if> <c:if
												test="${conf.optionJbh eq 1 }">${LANG['website.common.option.yes'] }</c:if> </span></li>
								</ul>
							</div>
						</div>
						<div class="args-info" style="height: 90px;">
							<div class="panel">
								<ul>
									<li class="item clearfix"><span class="key">${LANG['website.user.join.page.cpass']
											}：</span> <span class="val"> <c:if
												test="${conf.hostKey != '' }">${conf.hostKey }</c:if> <c:if
												test="${conf.hostKey == '' }">${LANG['com.bizconf.vcaasz.entity.ConfBase.confType.0'] }</c:if> </span></li>
		
									<li class="item clearfix"><span class="key">${LANG['user.menu.conf.pad.videooption']
											}：</span> <span class="val"> <c:if
												test="${conf.optionStartType eq 1 }">${LANG['user.menu.conf.pad.videooption.on'] }</c:if> <c:if
												test="${conf.optionStartType eq 2 }">${LANG['user.menu.conf.pad.videooption.off'] }</c:if> </span></li>
								</ul>
							</div>
						</div>
						<div style="float: left; width: 90%;background-color: #e6f5de;margin-left: 20px; height: 50px; line-height: 50px; text-align: left;" >
							<p></p>
							 <span style="padding-left:20px; margin-top: 20px;line-height:25px;"> &nbsp;${LANG['com.vcaas.portal.clientconf.cycledesc'] }</span>
						</div>
					</c:when>
					<c:otherwise>
		<div class="basic-info">
			<div class="panel">
				<ul>
					<li class="item clearfix block-layout">
						<span class="key">${LANG['website.user.view.conf.title'] }：</span>
						<span class="val" title="${conf.confName }">${conf.confName }</span>
					</li>
					<li class="item clearfix">
						<span class="key">${LANG['website.user.view.conf.starttime'] }：</span>
						<span class="val"><fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm" /></span>
					</li>
					<%--<li class="item clearfix">
						<span class="key">${LANG['website.user.view.conf.cycle.period'] }：</span>
						<span class="val">暂无</span>
					</li>
					--%><li class="item clearfix">
						<span class="key">${LANG['website.user.view.conf.duration'] }：</span>
						<span class="val">${duration}</span>
					</li>
					<c:if test="${!empty confCycle}">
						<li class="item clearfix">
							<span class="key">${LANG['website.user.view.conf.cycle.period'] }：</span>
							<span class="val">${cycleMode }</span>
						</li>
						<li class="item clearfix">
							<span class="key">${LANG['website.user.view.conf.cycle.around'] }：</span>
							<span class="val">${repeatScope }</span>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="args-info">
			<div class="panel">
				<ul>
					<li class="item clearfix">
					<span class="key">${LANG['website.user.view.conf.invite.number'] }：</span>
					<span class="val">
						<%--<c:if test="${!empty user && inviteUserCount != 0}">
							<a onclick="viewInviteUser('${confId}')">${inviteUserCount} ${LANG['website.user.view.conf.people'] }</a>
						</c:if>
						<c:if test="${empty user || inviteUserCount == 0}">
							${inviteUserCount} ${LANG['website.user.view.conf.people'] }
						</c:if>--%>
						
						${inviteUserCount} ${LANG['website.user.view.conf.people'] }
					</span>
					</li>
					<li class="item clearfix">
						<span class="key">${LANG['website.user.view.conf.conf.confCode'] }：</span>
						<span class="val">${conf.confZoomId }</span>
					</li>
					<li class="item clearfix">
						<span class="key">${LANG['website.user.join.page.cpass'] }：</span>
						<span class="val">
							<c:if test="${conf.hostKey != '' }">${conf.hostKey }</c:if>
							<c:if test="${conf.hostKey == '' }">${LANG['com.bizconf.vcaasz.entity.ConfBase.confType.0'] }</c:if>
						</span>
					</li>
					
					<li class="item clearfix">
						<span class="key">${LANG['user.menu.conf.pad.videooption'] }：</span>
						<span class="val">
							<c:if test="${conf.optionStartType eq 1 }">${LANG['user.menu.conf.pad.videooption.on'] }</c:if>
					 		<c:if test="${conf.optionStartType eq 2 }">${LANG['user.menu.conf.pad.videooption.off'] }</c:if>
						</span>
					</li>
					<li class="item clearfix">
						<span class="key">${LANG['website.user.view.conf.conf.preHostor'] }：</span>
						<span class="val">
							<c:if test="${conf.optionJbh eq 0 }"> ${LANG['website.common.option.no'] }</c:if>
							<c:if test="${conf.optionJbh eq 1 }">${LANG['website.common.option.yes'] }</c:if>
						</span>
					</li>
				</ul>
			</div>
		</div>
		</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${conf.confStatus != CONF_STATUS_FINISHED }">
		<div class="addresses">
				<div class="participants">
					${LANG['website.user.view.conf.join.url'] }：
					<input type="text" class="input-text" onmouseover="this.select()" readonly="readonly" value="${userUrl }" />
				</div>
		</div>
	</c:if>
</div>
	<div class="form-buttons">
		<cc:confList var="CONF_STATUS_SUCCESS"></cc:confList>
		<cc:confList var="CONF_STATUS_OPENING"></cc:confList>
		<cc:confList var="CONF_STATUS_CREATE_FAILED"></cc:confList>
		 
		<a class="button anchor-cancel" onclick="javascript:closeDialog();" style="margin-left:0px;">${LANG['website.common.option.cancel'] }</a>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<!-- <script type="text/javascript" src="/assets/js/apps/biz.index.js"></script> -->
<script type="text/javascript">
	function joinMeeting(joinType,cId){//cId,cPass,code){
		top.joinMeeting(joinType,cId);
	} 
	function closeDialog(result) {
		var dialog = parent.$("#viewMeeting");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
	function viewInviteUser(id) {
		//top.editInventContact(id);
	}
	function showCompereSecure(){
		$(".compereUser").show();
	}
		
	$(document).ready(function(){
		$("#compereUserBut").click(function (e) { 
			if ($(".compereUser").css("display") == "none") { 
				$(".compereUser").show();
				$("#compereUserBut").attr("value","${LANG['website.common.option.hide'] }");
			} 
			else { 
				$(".compereUser").hide(); 
				$("#compereUserBut").attr("value","${LANG['website.common.option.look'] }");
			} 
		}); 
		$("#compereBut").click(function (e) { 
			if ($(".compereHostUrl").css("display") == "none") { 
				$(".compereHostUrl").show();
				$("#compereBut").attr("value","${LANG['website.common.option.hide'] }");
			} 
			else { 
				$(".compereHostUrl").hide(); 
				$("#compereBut").attr("value","${LANG['website.common.option.look'] }");
			} 
		}); 
   	});


</script>
</body>
</html>