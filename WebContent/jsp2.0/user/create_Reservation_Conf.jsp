<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>${siteBase.siteName} - ${LANG['website.common.index.title.base.1'] }</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css?var=10" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />

<cc:confList var="WEEK_DAYS"/>
<cc:confList var="CONF_VIDEO_TYPE_FLUENCY"/>
<cc:confList var="CONF_VIDEO_TYPE_DISTINCT"/>
<cc:confList var="CONF_VIDEO_TYPE_WEBBAND"/>
<cc:confList var="CONF_VIDEO_TYPE_BEST"/>
<cc:confList var="CONF_VIDEO_TYPE_FLUENCY_CODE"/>
<cc:confList var="CONF_VIDEO_TYPE_DISTINCT_CODE"/>
<cc:confList var="CONF_VIDEO_TYPE_WEBBAND_CODE"/>
<cc:confList var="CONF_VIDEO_TYPE_BEST_CODE"/>
<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
<cc:siteList var="USER_FAVOR_LANGUAGE"/>
<fmt:formatDate var="beijingDate" value="${defaultDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
</head>
<body>
<div id="showStepDiv">
</div>
	<div class="meeting-dialog">
		<!-- 预约会议第一步   -->  
		<div class="step1-panel valid-group">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting"></i><span class="confOperate">${LANG['website.user.create.reser.page.divtitle'] }</span><!-- <span class="step fix-png"> -->
				<!--
				<ul class="numbers">
					<li class="active">1</li><li>2</li><li>3</li><li>4</li>
				</ul> 
				<ul class="desc">
					<li class="fix-png">${LANG['website.user.create.reser.page.step.confinfo']}</li>
					<li class="fix-png">${LANG['website.user.create.reser.page.step.timeinfo']}</li>
					<li class="fix-png">${LANG['website.user.create.reser.page.step.paraminfo']}</li>
					<li class="fix-png">${LANG['website.common.option.finish']}</li>
				</ul></span>
				-->
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<form id="scheduleForm1" method="post">
			<div class="step-content" style="padding-left: 9px;padding-right: 9px;">
				<h4 class="title" style="padding-left: 25px;">${LANG['website.user.create.reser.page.step.one.title']}</h4>
				<div class="form-panel" style="margin-left: 1px;margin-right: 1px;">
						<div class="form-item" style="margin-bottom:7px;">
							<label class="title">${LANG['website.user.create.reser.page.confname']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<div style="display:none" id="confNameDiv">${conf.confName}</div>
								<input id="confName" name="confName" type="text" class="input-text" placeholder="${LANG['website.user.create.reser.page.confname']}"  value=""/> 
								<span class="form-required"></span> 
<%-- 							<span class="tips">1-32个任意字符</span> --%>
								<span class="form-tip"><span id="confNameTip"></span></span>
							</div>
						</div>
						<div class="form-item" style="margin-bottom:7px;">
							<label class="title">${LANG['website.user.create.reser.page.public.password.set']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input id="hostKey" name="hostKey" type="text" class="input-text" placeholder="${LANG['website.user.create.reser.page.public.password']}" />
<%--								<span class="form-required"></span> --%>
 									<span class="tips">${LANG['website.user.create.reser.js.message.number.length10']}
<%--								<span class="form-tip"><span id="hostKeyTip"></span></span>--%>
									</span>
							</div>
						</div>
						<div class="form-item voice-widget" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.video.model']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input class="allowCallRadio" type="radio" name="voice" id="voiceNet" value="0"/> 
								<label for="voiceNet">${LANG['website.user.create.reser.page.video.model.net']}</label> 
								<input class="allowCallRadio" type="radio" name="voice" id="netAndPhone" checked="checked"  value="1"/> 
								<label for="netAndPhone">${LANG['website.user.create.reser.page.video.model.phone']}</label>
							</div>
						</div>
						<div class="form-item public-widget" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.public']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input class="allowPublic" type="radio" name="allowPublic" id="publicYes" value="1"/> 
									<label for="publicYes">${LANG['website.common.item.yes']}</label> 
								<input class="allowPublic" type="radio" name="allowPublic" id="publicNot" value="2" checked="checked"/> 
								<label for="publicNot">${LANG['website.common.item.no']}</label>
							</div>
						</div>
						<div class="form-item publicPassSet-widget" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.public.password.set']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input class="publicPass" type="radio" name="publicPass" id="publicYesPass" value="1"/> 
								<label for="publicYesPass">${LANG['website.common.item.yes']}</label> 
								<input class="publicPass" type="radio" name="publicPass" id="publicNotPass" value="2" checked="checked"/> 
								<label for="publicNotPass">${LANG['website.common.item.no']}</label>
							</div>
						</div>
						<div class="form-item publicPass-widget confPassTR" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.public.password']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input id="confPass" name="confPass" type="password" class="input-text" placeholder="${LANG['website.user.create.reser.page.public.password']}" />
								<span class="form-required"></span> 
<!--								<span class="tips">6-12个数字</span>-->
								<span class="form-tip"><span id="confPassTip"></span></span>
							</div>
						</div>
						<div class="form-item publicPass-widget confirmPassTR" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.public.password.confirm']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input id="confirmPass" name="confirmPass" type="password" class="input-text" placeholder="${LANG['website.user.create.reser.page.public.password.confirm']}" />
								<span class="form-required"></span> 
<!--								<span class="tips">6-12个数字</span>-->
								<span class="form-tip"><span id="confirmPassTip"></span></span>
							</div>
						</div>
						<div class="form-item assign-widget confInviterTR" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.page.apply.attendee']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input type="radio" name="confInviterRadio" id="assignAll"  value="0" /> 
									<label for="assignAll">${LANG['website.user.create.reser.page.all.user']}</label> 
								<input type="radio" name="confInviterRadio" id="assignSome" class="assign-some" checked="checked"  value="1" /> 
							<label for="assignSome">${LANG['website.user.create.reser.page.only.invite']}</label>
							<span class="gray">${LANG['website.user.create.reser.page.only.invite.desc']}</span>
							</div>
						</div>
						<!-- 开始时间 -->
						<!-- 持续时间 -->
						<!-- 会议缺省设置 -->
			<div class="form-item confitem_jbh" style="margin-bottom:7px;">
				<label class="title">${LANG['website.user.view.conf.conf.preHostor'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					<input id="compereType" type="radio" name="optionJbh" value="0" checked="checked"/>
					<label for="compereType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.no'] }</label>

					<input id="freeType" type="radio" name="optionJbh" value="1"/>
					<label for="freeType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.yes'] }</label>
				</div>
			</div>
			<div class="form-item" style="margin-bottom:7px;">
				<label class="title">${LANG['user.menu.conf.pad.videooption'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					 <select id="optionStartType" name="optionStartType">
					 	<option value="1">${LANG['user.menu.conf.pad.videooption.on'] }</option>
					 	<option value="2">${LANG['user.menu.conf.pad.videooption.off'] }</option>
					 </select>
				</div>
			</div>
			<!-- 会议描述 -->
			<div class="form-item describe-widget" style="margin-bottom:7px;">
				<label class="title">${LANG['website.user.create.reser.conf.desc']}${LANG['website.common.symbol.colon']}</label>
				<div class="widget">
					<textarea id="confDesc" class="input-area" placeholder="${LANG['website.user.create.reser.conf.desc']}">${conf.confDesc}</textarea>
					<span class="count"><span id="nowCountSpan">0</span>/256</span>
				</div>
			</div>
				</div>
			</div>
			 
			<div class="form-buttons">
				<input id="submit1" page-index="1" type="submit" class="button input-gray next-button" value="${LANG['website.common.option.step.next']}">
				<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
			</div>
			<!-- 
			<div class="form-buttons">
<%--				<input page-index="0" type="button" class="button input-gray prev-button" value="${LANG['website.common.option.step.last']}">--%>
				<input page-index="2" type="submit" class="button input-gray next-button" value="${LANG['website.common.option.finish']}">
				<a class="button anchor-cancel">${LANH['website.common.option.cancel']}</a>
			</div>-->
			</form>
		</div>
		
		<!-- 预约会议第二步 -->
		<div class="step2-panel valid-group" style="display: none;">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting"></i><span class="confOperate">${LANG['website.user.create.reser.page.divtitle'] }</span><!-- <span class="step fix-png"> -->
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<form id="scheduleForm2" method="post">
			<div class="step-content" style="height: 375px">
						<!-- 实时时钟 -->
					<h4 class="title">${LANG['website.user.create.reser.page.step.two.title']}
						<span class="time">
							<c:if test="${conf == null}">
			                	<c:set var="timeZone" value="website.timezone.city.${currentUser.timeZoneId}"/>
			                	${LANG[timeZone]} ${LANG['website.common.time.name']}&nbsp;
			                </c:if> 
			                <c:if test="${conf != null}">
			                	<c:set var="timeZoneDesc" value="website.timezone.city.${conf.timeZoneId}"/>
			                	${LANG[timeZoneDesc]} ${LANG['website.common.time.name']}&nbsp;
			                </c:if>
							<span id="site_time">${beijingDate}</span>
						</span>
					</h4>
					<div class="form-panel">
						<!-- 重复周期 -->
						<div class="form-item public-widget">
							<div style="text-align: left;margin-left: -35px;">
							<label class="title">${LANG['website.user.create.reser.repeat.cycle']}${LANG['website.common.symbol.colon']}</label>
							</div>
							<div class="widget">
								<input class="repeat-cycle" type="radio" name="repeat" id="repeatYes" value="1"/>
								<label for="repeatYes">${LANG['website.common.item.yes']}</label>
								<input class="repeat-cycle" type="radio" name="repeat" id="repeatNot" checked="checked" value="2"/>
								<label for="repeatNot">${LANG['website.common.item.no']}</label>
							</div>
						</div>
						<!-- 开始时间-->
						<div class="form-item time-widget starttime-widget">
							<div style="text-align: left;margin-left: -35px;">
							<label class="title">${LANG['website.user.create.reser.conf.start.time']}${LANG['website.common.symbol.colon']}</label>
							</div>
							<div class="widget">
								<!-- 开始时间 -->
								<div style="position: relative;">
									<input id="startTime" type="text" name="year" class="year input-text startDateDiv" readonly="readonly"/>
									<select id="startTimeH" class="hours">
										<c:forEach var="h" begin="00" end="23" step="1">
											<option value="${h}" >${h} ${LANG['website.common.time.hour.name.dot']}</option>
										</c:forEach>
									</select>
									<select id="startTimeM" class="minute">
										<c:forEach var="m" begin="00" end="55" step="5">
											<option value="${m}">${m} ${LANG['website.common.time.minute.name']}</option>
										</c:forEach>
									</select>
									<span class="form-required"></span>
									<span class="form-tip"><span id="startTimeTip"></span></span>
								</div>
<!-- 								<div class="clearfix"></div> -->
								
								<!-- 持续时间 （ 默认选择）-->
								<input style="display: none;" class="bytype last-time" type="radio" name="bytype" id="lastTime" value="2" checked="checked"/>
								
								<!-- 
									持续时间和结束时间
									<div class="type-group duration-group" id="bytype">
									<input class="bytype" type="radio" name="bytype" id="overTime" value="1"/>
									<label for="overTime">${LANG['website.user.create.reser.conf.by.endtime']}</label>
									<input class="bytype last-time" type="radio" name="bytype" id="lastTime" value="2" checked="checked"/>
									<label for="lastTime">${LANG['website.user.create.reser.conf.by.endurance']}</label>
								</div>
								 -->
							</div>
						</div>
						<!-- 结束时间-->
						<div class="form-item time-widget endtime-widget">
							<div style="text-align: left;margin-left: -35px;">
							<label class="title">${LANG['website.user.create.reser.conf.end.time']}${LANG['website.common.symbol.colon']}</label>
							</div>
							<div class="widget">
								<input type="hidden" id="permanentConf_id" value="${conf.permanentConf}"/>
								<input id="endTime" type="text" name="year" class="year input-text" value="" readonly="readonly"/>
								<select id="endTimeH" class="hours">
									<c:forEach var="h" begin="00" end="23" step="1">
										<option value="${h}" >${h} ${LANG['website.common.time.hour.name.dot']}</option>
									</c:forEach>
								</select>
								<select id="endTimeM" class="minute">
									<c:forEach var="m" begin="00" end="55" step="5">
										<option value="${m}">${m} ${LANG['website.common.time.minute.name']}</option>
									</c:forEach>
								</select>
								<span class="form-required"></span>
								<span class="form-tip"><span id="endTimeTip"></span></span>
							</div>
						</div>
						<!-- 持续时间 -->
						<div class="form-item duration-widget">
							<div style="text-align: left;margin-left: -35px;">
							<label class="title">${LANG['website.user.create.reser.conf.endurance.time']}${LANG['website.common.symbol.colon']}</label>
							</div>
							<div class="widget">
								<select id="durationH" class="hours">
									<c:forEach var="h" begin="00" end="23" step="1">
										<option value="${h}" <c:if test="${h==1}">selected="selected"</c:if>>${h}
											<c:if test="${h==1 || h==0}">
											${LANG['website.common.hour.name']}
											</c:if>
											<c:if test="${h!=1 && h!=0}">
											${LANG['website.common.hour.name']}
											</c:if> 
										</option>
									</c:forEach>
								</select>
								<select id="durationM" class="minute">
									<c:forEach var="m" begin="00" end="55" step="5">
										<option value="${m}">${m} 
											<c:if test="${m==0}">
											${LANG['website.common.minute.name']}
											</c:if>
											<c:if test="${m!=0}">
											${LANG['website.common.minute.name']}
											</c:if>
										</option>
									</c:forEach>
								</select>
								<span class="form-required"></span>
								<span class="form-tip"><span id="durationTip"></span></span>
							</div>
						</div>
						
						<!-- 默认时区 -->
						<div class="form-item">
							<div style="text-align: left;margin-left: -35px;">
							<label class="title">${LANG['website.user.conf.default.timezone']}${LANG['website.common.symbol.colon']}</label>
							</div>
							<div class="widget">
								<select id="timeZoneId" name="timeZoneId">
									<c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}">
										<c:set var="eachLang" value="website.timezone.city.zone.${eachTimeZone.timeZoneId}"/>
										<c:choose>
											<c:when test="${empty conf }">
												<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId eq currentUser.timeZoneId}">selected="selected"</c:if> >${LANG[eachLang]} </option>
											</c:when>
											<c:otherwise>
												<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId eq conf.timeZoneId}">selected="selected"</c:if> >${LANG[eachLang]} </option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<!-- 定期模式-->
						<div class="repeat-widget cycleTypeTR" style="display: none;">
							<div class="form-item">
								<p class="legend">${LANG['website.user.create.reser.cycle.model.recurrence']}</p>
								<div class="groups tabview clearfix">
									<ul class="ontime tabs" style="width:55px;padding-right: 20px;padding-left: 20px;">
										<li class="tab" data-tab="day"><label for="cycleTypeDay"><input id="cycleTypeDay" class="cycleType" type="radio" name="ontime" value="1"/><span>${LANG['website.user.create.reser.cycle.model.recurrence.daily']}</span></label></li>
										<li class="tab active" data-tab="week"><label for="cycleTypeWeek"><input id="cycleTypeWeek" class="cycleType" type="radio" name="ontime" value="2" checked="checked" /><span>${LANG['website.user.create.reser.cycle.model.recurrence.weekly']}</span></label></li>
										<li class="tab" data-tab="month"><label for="cycleTypeMonth"><input id="cycleTypeMonth" class="cycleType" type="radio" name="ontime" value="3"/><span>${LANG['website.user.create.reser.cycle.model.recurrence.monthly']}</span></label></li>
									</ul>
									<div class="timemode views">
										<div class="view" data-view="day">
											<p>${LANG['website.user.create.reser.cycle.daily.write']}${LANG['website.common.symbol.colon']}</p>
<!--											<div class="bydate error-item">-->
											<div class="bydate">
												${LANG['website.user.create.reser.cycle.daily.adj.every']} <input id="cycleDayFlag" type="text" class="input-text days" name="day" value="1"/> ${LANG['website.user.create.reser.cycle.daily.name']}
<!-- 												<span class="error"><i class="icon"></i>请输入正确的时间间隔</span> -->
												<div class="cycleDayFlag-tip">
													<span class="form-tip"><span id="cycleDayFlagTip"></span></span>
												</div>
											</div>
										</div>
										<div class="view active" data-view="week">
											<p>${LANG['website.user.create.reser.cycle.choose.week']}${LANG['website.common.symbol.colon']}</p>
											<div class="dayofweek">
												<label><input type="checkbox" name="weekday" value="1"/><span>${LANG['website.common.week.name.1']}</span></label>
												<label><input type="checkbox" name="weekday" value="2" /><span>${LANG['website.common.week.name.2']}</span></label>
												<label><input type="checkbox" name="weekday" value="3" /><span>${LANG['website.common.week.name.3']}</span></label>
												<label><input type="checkbox" name="weekday" value="4" /><span>${LANG['website.common.week.name.4']}</span></label>
												<label><input type="checkbox" name="weekday" value="5" /><span>${LANG['website.common.week.name.5']}</span></label>
												<label><input type="checkbox" name="weekday" value="6" /><span>${LANG['website.common.week.name.6']}</span></label>
												<label><input type="checkbox" name="weekday" value="7" /><span>${LANG['website.common.week.name.7']}</span></label>
											</div>
											<div class="weekday-tip">
												<span class="form-tip"><span id="weekdayTip"></span></span>
											</div>
										</div>
										<div class="view" data-view="month">
											<p>${LANG['website.user.create.reser.cycle.choose.date']}${LANG['website.common.symbol.colon']}</p>
											<div class="bydate eachmonth-tip">
												<input type="radio" name="month-type" value="1"/>
												${LANG['website.user.create.reser.cycle.choose.week.for.daily.begin']} <input id="eachMonthDay" type="text" class="input-text days" name="day" value="1"/> ${LANG['website.user.create.reser.cycle.daily.name']}${LANG['website.user.create.reser.cycle.choose.week.for.daily.end']}
<!-- 												<span class="error"><i class="icon"></i>请输入正确的时间间隔</span> -->
												<span class="form-tip"><span id="eachMonthDayTip"></span></span>
											</div>
											<div class="byweek">
												<input type="radio" name="month-type" value="2" checked="checked"/>
<!--													${LANG['website.user.create.reser.cycle.choose.week.for.daily']}-->
													${LANG['website.user.create.reser.cycle.choose.week.for.daily.begin']}
												<select id="weekFlag">
													<c:forEach var="eachTime" begin="1" end="5" step="1">
														<c:set var="day_lang" value="website.common.day.name.${eachTime}"/>
														<option value="${eachTime}">${LANG[day_lang]}</option>
													</c:forEach>
												</select>
<!--												${LANG['website.user.create.reser.cycle.choose.week.for.count']}-->
												<select id="weekDay">
													<c:forEach var="eachDay" items="${WEEK_DAYS}">
														<c:set var="week_day_lang" value="website.common.week.name.${eachDay}"/>
														<option value="${eachDay}">${LANG[week_day_lang]}</option>
													</c:forEach>
												</select>
												${LANG['website.user.create.reser.cycle.choose.week.for.daily.end']}
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 重复范围 -->
							<div class="form-item repeat-item">
								<p class="legend">${LANG['website.user.create.reser.cycle.repeat.range']}</p>
								<div class="groups clearfix">
									<ul class="ontime" style="padding-right: 20px;padding-left: 20px;">
										<li>
											<input class="repeat-scope" id="endLess" type="radio" name="repeatScope" value="1"/>
											<label for="endLess"><span>${LANG['website.user.create.reser.cycle.repeat.range.no.enddate']}</span></label>
										</li>
										<li class="endRepeat-tip">
											<input class="repeat-scope" id="endRepeat" type="radio" name="repeatScope" value="2"/>
											<label for="endRepeat" style="padding-right: 20px"><span class="equal-width">${LANG['website.user.create.reser.cycle.repeat.name']}</span></label><input type="text" id="repeatCount" class="input-text times" value="30"/>
											<label for="endRepeat" style="margin-left: 60px;">${LANG['website.user.create.reser.cycle.repeat.count.name']}</label>
											<span class="form-tip"><span id="repeatCountTip"></span></span>
										</li>
										<li class="endRepeat-foreEver-tip">
											<input class="repeat-scope" id="endOneDate" type="radio" name="repeatScope" value="3" checked="checked"/>
											<label for="endOneDate" style="padding-right: 20px"><span class="equal-width">${LANG['website.user.create.reser.conf.end.time']}</span></label>
											<span class="end-time-forever">
												<input id="endTimeForEver" type="text" class="input-text date" value="" readonly="readonly"/>
											</span>
											<span class="form-tip"><span id="endTimeForEverTip"></span></span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					
				</div>
			</div>
			<!-- 
				<div class="form-buttons">
					<input page-index="0" type="button" class="button input-gray prev-button" value="${LANG['website.common.option.step.last']}">
					<input id="submit2" page-index="2" type="submit" class="button input-gray next-button" value="${LANG['website.common.option.step.next']}">
					<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
				</div>
			 -->
			<div class="form-buttons">
				<input page-index="0" type="button" class="button input-gray prev-button" value="${LANG['website.common.option.step.last']}">
				<input page-index="2" type="submit" class="button input-gray next-button" value="${LANG['website.common.option.finish']}">
				<a class="button anchor-cancel">${LANH['website.common.option.cancel']}</a>
			</div>
			</form>	
		</div>
		
		<!-- 预约会议第三步 -->
		<div class="step3-panel valid-group" style="display: none;">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting fix-png"></i><span class="confOperate">${LANG['website.user.create.reser.page.divtitle']}</span><span class="step">
				<ul class="numbers">
					<li class="active">1</li><li class="active">2</li><li class="active">3</li>
				</ul>
				<ul class="desc">
					<li>${LANG['website.user.create.reser.page.step.confinfo']}</li>
					<li>${LANG['website.user.create.reser.page.step.timeinfo']}</li>
					<!-- <li>${LANG['website.user.create.reser.page.step.paraminfo']}</li>-->
					<li>${LANG['website.common.option.finish']}</li>
				</ul></span>
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<form  id="scheduleForm3" method="post" >
			<div class="step-content">
				<h4 class="title">${LANG['website.user.create.reser.page.step.three.title']}</h4>
				<div class="form-panel">
						<div id="confVideoDiv" class="form-item videoFuncWidget">
							<label class="title">${LANG['website.user.create.reser.param.video.types']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<span id="videoFunc">
									<input type="checkbox" name="meetType" id="meetTypeVideo" value="2" checked="checked"/>
									<label for="meetTypeVideo">${LANG['website.user.create.reser.param.video.types.videoname']}</label>
								</span>
							</div>
						</div>
						<div id="allowCallTr" class="form-item public-widget" style="display: none;">
							<label class="title">${LANG['website.user.create.reser.param.outcall']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input type="radio" name="allowCall" id="allowCallRadio" value="1" checked="checked" />
								<label for="allowCallRadio">${LANG['website.common.option.open']}</label>
								<input type="radio" name="allowCall" id="noCallRadio" value="0" />
								<label for="noCallRadio">${LANG['website.common.option.close']}</label>
							</div>
						</div>
						<div class="form-item public-widget">
							<label class="title">${LANG['website.user.create.reser.param.conf.model']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input type="radio" name="meetMode" id="compereMode" value="1" checked="checked" />
								<label for="compereMode">${LANG['website.user.create.reser.param.conf.model.compere']}</label>
								<input type="radio" name="meetMode" id="freeMode" value="0" />
								<label for="freeMode">${LANG['website.user.create.reser.param.conf.model.free']}</label>
							</div>
						</div>
						<div class="form-item public-widget">
							<label class="title">${LANG['website.user.create.reser.param.default.mic']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input type="radio" name="mikeMode" id="enableMike" value="1"/>
								<label for="enableMike">${LANG['website.common.option.on']}</label>
								<input type="radio" name="mikeMode" id="disableMike" value="0" checked="checked"/>
								<label for="disableMike">${LANG['website.common.option.off']}</label>
							</div>
						</div>
						<div class="form-item aheadtime-widget">
							<label class="title">${LANG['website.user.create.reser.param.advanced.ahead']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input id="aheadTime" type="text" class="input-text short-text" />
								${LANG['website.common.minute.name']}
								<span class="form-tip"><span id="aheadTimeTip"></span></span>
							</div>
						</div>
						<div class="regular">
							<p class="legend" style="margin-bottom: 10px;"><a class="config">${LANG['website.user.create.reser.param.advanced.name']}</a></p>
							<div class="config-details">
							<div class="form-item max-audio-widget">
								<label class="title nobr" title="${LANG['website.user.create.reser.param.advanced.max.audio']}">${LANG['website.user.create.reser.param.advanced.max.audio']}${LANG['website.common.symbol.colon']}</label>
								<div class="widget">
									<select id="maxAudio" class="short-select">
										<c:forEach var="h" begin="0" end="${audioNumber }" step="1">
											<option value="${h}">${h} ${LANG['website.common.noun.way.unit.name']}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div id="confMaxVideoNumDiv" class="form-item max-video-widget">
								<label class="title nobr" title="${LANG['website.user.create.reser.param.advanced.max.video']}">${LANG['website.user.create.reser.param.advanced.max.video']}${LANG['website.common.symbol.colon']}</label>
								<div class="widget">
									<select id="maxVideo" class="short-select">
										<c:forEach var="h" begin="0" end="${videoNumber }" step="1">
							        		<c:if test="${h<7 || h==16}">
							        			<option value="${h}">${h} ${LANG['website.common.noun.way.unit.name']}</option>
							        		</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-item public-widget video-quality-widget">
								<label class="title nobr" title="${LANG['website.user.create.reser.param.advanced.video.quality']}">${LANG['website.user.create.reser.param.advanced.video.quality']}${LANG['website.common.symbol.colon']}</label>
								<div class="widget">
									<input id="lowMode" name="videoQuality" type="radio" value="${CONF_VIDEO_TYPE_WEBBAND }" <c:if test="${userEmpower.dpiNumber==CONF_VIDEO_TYPE_WEBBAND_CODE }"> checked="checked" </c:if> /><label for="lowMode">${LANG['website.common.option.low']}</label>
						            <c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_FLUENCY_CODE }">
						          	<input id="middleMode"  name="videoQuality" type="radio" value="${CONF_VIDEO_TYPE_FLUENCY }" checked="checked"/><label for="middleMode">${LANG['website.common.option.medium']}</label>
						          	</c:if>
						          	<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_DISTINCT_CODE }">
						          	<input id="highMode"  name="videoQuality" type="radio" value="${CONF_VIDEO_TYPE_DISTINCT }" /><label for="highMode">${LANG['website.common.option.high']}</label>
						          	</c:if>
						          	<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_BEST_CODE }">
						          	<input id="topMode"  name="videoQuality" type="radio" value="${CONF_VIDEO_TYPE_BEST }" /><label for="topMode">${LANG['website.common.option.best']}</label>
						          	</c:if>
								</div>
							</div>
							<div class="form-item public-widget permisson-widget">
								<label class="title">${LANG['website.user.create.reser.param.advanced.privilege']}${LANG['website.common.symbol.colon']}</label>
								<div class="widget nobr">
									<input class="extraPermission" type="checkbox" name="changePage" id="turnPage" value="1"/>
									<label for="turnPage" title="${LANG['website.user.create.reser.param.advanced.privilege.paging']}">${LANG['website.user.create.reser.param.advanced.privilege.paging']}</label>
									<input class="extraPermission" type="checkbox" name="annotate" id="comment" value="1"/>
									<label for="comment" title="${LANG['website.user.create.reser.param.advanced.privilege.remark']}">${LANG['website.user.create.reser.param.advanced.privilege.remark']}</label>
									<input class="extraPermission" type="checkbox" name="chatAnyOne" id="toAll" value="1"/>
									<label for="toAll" title="${LANG['website.user.create.reser.param.advanced.privilege.chat.all']}">${LANG['website.user.create.reser.param.advanced.privilege.chat.all']}</label>
									<input class="extraPermission" type="checkbox" name="chatCompere" id="toCompere" value="1"/>
									<label for="toCompere" title="${LANG['website.user.create.reser.param.advanced.privilege.chat.host']}">${LANG['website.user.create.reser.param.advanced.privilege.chat.host']}</label>
									<input class="extraPermission" type="checkbox" name="chatParticipants" id="toParty" value="1"/>
									<label for="toParty" title="${LANG['website.user.create.reser.param.advanced.privilege.chat.actor']}">${LANG['website.user.create.reser.param.advanced.privilege.chat.actor']}</label>
								</div>
							</div>
							<div class="form-item public-widget client-option-widget">
								<label class="title">${LANG['website.user.create.reser.param.advanced.func']}${LANG['website.common.symbol.colon']}</label>
								<div class="widget nobr">
									<input class="clientOption" type="checkbox" name="shareDocs" id="shareDoc" value="1"/>
									<label for="shareDoc" title="${LANG['website.user.create.reser.param.advanced.func.docshare']}">${LANG['website.user.create.reser.param.advanced.func.docshare']}</label>
									<input class="clientOption" type="checkbox" name="shareScreen" id="shareWin" value="1"/>
									<label for="shareWin" title="${LANG['website.user.create.reser.param.advanced.func.screenshare']}">${LANG['website.user.create.reser.param.advanced.func.screenshare']}</label>
									<span id="share-media-widget">
										<input class="clientOption" type="checkbox" name="shareMedia" id="shareMedia" value="1"/>
										<label for="shareMedia" title="${LANG['website.user.create.reser.param.advanced.func.mediashare']}">${LANG['website.user.create.reser.param.advanced.func.mediashare']}</label>
									</span>
									<input class="clientOption" type="checkbox" name="whiteBoard" id="whiteBoard" value="1"/>
									<label for="whiteBoard" title="${LANG['website.user.create.reser.param.advanced.func.whiteboard']}">${LANG['website.user.create.reser.param.advanced.func.whiteboard']}</label>
									<input class="clientOption" type="checkbox" name="fileTrans" id="transmitFile" value="1"/>
									<label for="transmitFile" title="${LANG['website.user.create.reser.param.advanced.func.transmitfile']}">${LANG['website.user.create.reser.param.advanced.func.transmitfile']}</label>
									<span id="record-widget">
										<input class="clientOption" type="checkbox" name="record" id="record" value="1"/>
										<label for="record" title="${LANG['website.user.create.reser.param.advanced.func.record']}">${LANG['website.user.create.reser.param.advanced.func.record']}</label>
									</span>
								</div>
							</div>							
							</div>
						</div>
				</div>
			</div>
			<div class="form-buttons">
				<input page-index="1" type="button" class="button input-gray prev-button" value="${LANG['website.common.option.step.last']}">
				<input page-index="3" type="submit" class="button input-gray next-button" value="${LANG['website.common.option.finish']}">
				<a class="button anchor-cancel">${LANH['website.common.option.cancel']}</a>
			</div>	
			</form>
		</div>
		
		<!-- 预约会议第四步 -->
		<div class="step4-panel valid-group" style="display: none;">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting fix-png"></i><span class="confOperate">${LANG['website.user.create.reser.page.divtitle']}</span><!-- <span class="step"> -->
				<!-- 
					<ul class="numbers">
					<li class="active">1</li><li class="active">2</li><li class="active">3</li>
				</ul>
				<ul class="desc">
					<li>${LANG['website.user.create.reser.page.step.confinfo']}</li>
					<li>${LANG['website.user.create.reser.page.step.timeinfo']}</li>
					<li>${LANG['website.user.create.reser.page.step.paraminfo']}</li> 
					<li>${LANG['website.common.option.finish']}</li>
				</ul></span>
				 -->
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<div class="step-content">
				<div class="success-panel" style="height: 208px;">
					<div class="face-success">${LANG['website.user.create.reser.page.step.four.title']}</div>
					<ul class="details" style="margin-left: 315px;">
						<li>${LANG['website.user.create.reser.page.confname']}${LANG['website.common.symbol.colon']}<span id="confTitle" style="overflow:hidden;white-space:nowrap;-o-text-overflow:ellipsis;text-overflow:ellipsis;"></span></li>
						<li>${LANG['website.user.create.reser.conf.start.time']}${LANG['website.common.symbol.colon']}<span id="confStartTime"></span><span id="confTimeZoneDesc"></span></li>
						<li id="durationConsole">${LANG['website.user.create.reser.conf.endurance.time']}${LANG['website.common.symbol.colon']}<span id="confDuration"></span></li>
						<li id="confZoomIdli">${LANG['website.user.view.conf.conf.confCode']}${LANG['website.common.symbol.colon']}<span id="confZoomId"></span></li>
						<li id="confpwdTR">${LANG['website.user.join.page.cpass']}${LANG['website.common.symbol.colon']}<span id="confpwd"></span></li>
<%--						<li id="conffuntypeTR">${LANG['user.menu.conf.pad.videooption']}${LANG['website.common.symbol.colon']}<span id="conffuntype"></span></li>--%>
						<li id="pairingCode">${LANG['website.user.join.page.cpass.h323orsip']}${LANG['website.common.symbol.colon']}<span id="pairingCodeSIP"></span></li>
						<li id="conffuntypeTR">${LANG['user.menu.conf.pad.videooption']}${LANG['website.common.symbol.colon']}<span id="conffuntype"></span></li>
						<li id="optionjbhTR">${LANG['website.user.view.conf.conf.preHostor']}${LANG['website.common.symbol.colon']}<span id="optionjbh"></span></li>
						<li id="confTypeTR" style="display: none;">${LANG['user.menu.conf.pad.videooption']}${LANG['website.common.symbol.colon']}<span id="confType"></span></li>
					</ul>
				</div>
				<div class="remark clearfix">
					<input type="hidden" id="confIdForEmail" value=""/>
					<div class="notes" style="display: none;">
						<h4 class="nobr">${LANG['website.user.create.reser.finish.calendar.title']}</h4>
						<p style="margin-top: 0px;margin-left: 0px;" class="gray" title="${LANG['website.user.create.reser.finish.appendto.calendar']}">${LANG['website.user.create.reser.finish.appendto.calendar']}</p>
						<p><a style="padding: 0px 10px" class="input-gray nobr" onclick="sendNoticeEmail();" title="${LANG['website.user.create.reser.finish.calendar.title.outlook']}">${LANG['website.user.create.reser.finish.calendar.title.outlook']}</a></p>
						<p><a style="padding: 0px 10px" class="input-gray nobr" onclick="sendNoticeEmail();" title="${LANG['website.user.create.reser.finish.calendar.title.gmail']}">${LANG['website.user.create.reser.finish.calendar.title.gmail']}</a></p>
					</div>
					<div class="invites invite-area" style="float:left;width:652px;padding-left: 0px; ">
					<div align="center">
							<button class="input-button nobr" onclick="goInviteUsers();" style="width: 250px;">${LANG['user.menu.conf.invite.email'] }</button>
							<button class="input-button nobr" onclick="goInviteUsersByMsg();" style="width: 250px; margin-left: 139px;">${LANG['website.user.index.js.message.title.invite.confuser.msg'] }</button>
					</div>
					<p class="">
						${LANG['website.user.create.reser.finish.invite.desc.row.2']}
					<i class="icon invite"></i> &nbsp; <i class="icon notice"></i>
						${LANG['website.user.create.reser.finish.invite.desc.row.3']}${LANG['website.common.symbol.period']}
						<br>
						${LANG['website.user.create.reser.finish.inviteinfo.row01']} &nbsp; <i class="icon copy"></i>
						&nbsp; ${LANG['website.user.create.reser.finish.inviteinfo.row02']}
					</p>
					<%-- <p>邀请后将以邮件的形式通知受邀人参与此次会议，<br />您也可以在会议列表中点击<i class="icon"></i>图标来邀请与会者。</p> --%>
					</div>
				</div>				
			</div>
			<div class="form-buttons">
				<input type="button" class="button input-gray button-ok"  value="${LANG['website.common.option.confirm']}">
			</div>	
		</div>
		<!-- 预约会议失败-->
		<div class="step5-panel valid-group" style="display: none;">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting fix-png"></i><span class="confOperate">${LANG['website.user.create.reser.page.divtitle']}</span><span class="step">
				<ul class="numbers">
					<li class="active">1</li><li class="active">2</li><li class="active">3</li><li>4</li>
				</ul>
				<ul class="desc">
					<li>${LANG['website.user.create.reser.page.step.confinfo']}</li>
					<li>${LANG['website.user.create.reser.page.step.timeinfo']}</li>
					<li>${LANG['website.user.create.reser.page.step.paraminfo']}</li>
					<li>${LANG['website.common.option.failure']}</li>
				</ul></span>
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<div class="step-content">
				<div class="schedule-failure">
					<div>${LANG['website.user.create.reser.failure']}</div>
				</div>
			</div>
			<div class="form-buttons">
				<input id="schedultFailButton" type="button" class="button input-gray" value="${LANG['website.common.option.confirm']}">
			</div>
		</div>				
	</div>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.date.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></script>
	<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<script type="text/javascript">
	//初始化的时候进行这三项的默认初始化:默认时区,先于主持人入会,开始会议模式
	$(document).ready(function(){
		if ($.browser.msie && $.browser.version < 10) {
    		$("#confName").watermark("${LANG['website.user.create.reser.page.confname']}");
			$("#hostKey").watermark("${LANG['website.user.create.reser.page.public.password']}");
			$("#confDesc").watermark("${LANG['website.user.create.reser.conf.desc']}");
    	}
		//var timezone = '${conf.timeZoneId}' || '${currentUser.timeZoneId}';
		var optionStartType = '${conf.optionStartType}' || '${config.optionStartType}';
		var optionJbh = '${conf.optionJbh}' || '${config.optionJbh}';
		 //$("#timeZoneSecond option[value='"+timezone+"']").attr("selected",true);
		 $("#optionStartType option[value='"+optionStartType+"']").attr("selected",true);
		 $("input[name=optionJbh]").each(function(){
			 if($(this).val()==optionJbh)$(this).attr("checked",true);
		 });
		 
		//如果为会议修改 不显示先于主持人入会项
		//var confId = '${conf.id}';
		//if(confId){
		//	 $(".confitem_jbh").hide();
		//}
	 })
	
	function showStep(message){
		var oldMsg=$("#showStepDiv").html();
		oldMsg+="<br/>"+message;
		$("#showStepDiv").html(oldMsg);
	}
	
	//授权
	var isOpenPhone="${userEmpower.phoneFlag}";
	var isOpenOutCall="${userEmpower.outCallFlag}";
	var isOpenVideo="${userEmpower.videoFlag}";
	var maxVideoNum="${userEmpower.videoNumber}";
	if(isOpenVideo==0){
		maxVideoNum=0;
	}
	
	//默认设置
	
	var defaultConfType="${defaultConfig.confType}";
	var defaultOpenPhone="0";
	var defaultOpenOutCall="${defaultConfig.allowCall}";
	var defaultOpenVideo="0";
	var defaultMaxVideoNum="${defaultConfig.maxVideo}";
	if(defaultConfType==1 || defaultConfType==3){
		defaultOpenPhone="1";
	}
	if(defaultConfType==2 || defaultConfType==3 || parseInt(defaultMaxVideoNum,10)>0){
		defaultOpenVideo="1";
	}
	if(defaultOpenPhone==0){
		defaultOpenOutCall="0";
	}
	//if(defaultOpenVideo==0){
	//	defaultMaxVideoNum="0";
	//}
	if(parseInt(defaultMaxVideoNum,10)>parseInt(maxVideoNum,10)){
		defaultMaxVideoNum=maxVideoNum;
	}
	

	
	
	var isOutCallFlag = "${isOutCallFlag}";
	
	var serverDate = new Date("${serverDate}");
	var confID = "${conf.id}";
	var confHwid = "";
	var timeZone = "${conf.timeZone}";
	var cycleID = "${conf.cycleId}";
	var timer=null;
	var nextDateTime;
	var dateTimeStamp = serverDate.getTime();
	var allowCall=0;
	function showTimeZone(){
		if(dateTimeStamp<=0){
			window.clearInterval(timer);
		}
		nextDateTime=new Date(dateTimeStamp);
		$("#site_time").html(nextDateTime.format("yyyy-MM-dd hh:mm:ss"));
		dateTimeStamp+=1000;
	}
	//预约会议时，初始化会议时间信息
	jQuery(function($) {
		if (serverDate) {
			var tempDate = new Date(serverDate.getTime());
			tempDate = tempDate.addMinute(10);
			var tempHours = tempDate.getHours();
			var tempMins = tempDate.getMinutes();
			if(tempMins>=55){
				tempDate = tempDate.addHour(1);
				tempHours = tempDate.getHours();
				tempMins = 0;
			}
			var startTimeStr = tempDate.format("yyyy-MM-dd");
			var tempEndDate = new Date(tempDate.getTime());
			tempEndDate = tempEndDate.addMonth(1);
			var endTimeStr = tempEndDate.format("yyyy-MM-dd");
			
			//$(".starttime-widget").css("display","none");// 隐藏开始时间 add
			//设置分钟
			$("#startTimeM option").each(function() {
 				var cValue = $(this).val();
 				if(tempMins<=cValue) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#endTimeM option").each(function() {
 				var cValue = $(this).val();
 				if(tempMins<=cValue) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			//设置小时
			$("#startTimeH option").each(function() {
 				var cValue = $(this).val();
 				if(cValue==tempHours) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#endTimeH option").each(function() {
 				var cValue = $(this).val();
 				if(cValue==tempHours) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#startTime").val(startTimeStr);
			$("#endTime").val(endTimeStr);
			$("#endTimeForEver").val(endTimeStr);
			//选择星期几
			var weekday = serverDate.getDay()+1;
			$("#weekDay").attr("value",weekday);
			$("input[name=weekday]").each(function(n){
				if($(this).val()==weekday){
					$(this).attr("checked",'checked');
				} else {
					$(this).removeAttr("checked");
				}
			});
		}
		
		//设置电话与外呼
		if(isOpenPhone=="1"){//如果已经对电话授权，则打开“网络语音+电话语音”、“外呼”标签
			if(defaultOpenPhone=="1"){//如果默认设置中的电话是打开状态，则打开“外呼”标签
				$("input[name=voice]:eq(0)").removeAttr("checked");
				$("input[name=voice]:eq(1)").attr("checked",'checked');
				if(isOpenOutCall=="1"){
					$("#allowCallTr").show();
					if(defaultOpenOutCall=="1"){//如果默认设置外呼是开启，则设置外呼的值
						$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
						$("input:radio[name=allowCall]:eq(1)").removeAttr("checked");
					}else{
						$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
						$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
					}
				}else{
					$("#allowCallTr").hide();
					$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
					$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
				}
				
			
			}else{//如果默认配置中电话是关闭的，
				$("input[name=voice]:eq(0)").attr("checked",'checked');
				$("input[name=voice]:eq(1)").removeAttr("checked");
				$("#allowCallTr").hide();
				$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
				$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
			}
			
			
		}else{//未授权，则关闭“网络语音+电话语音”、“外呼”标签,并设置为网络语音、外呼关闭
			$("input[name=voice]:eq(0)").attr("checked",'checked');
			$("input[name=voice]:eq(1)").removeAttr("checked");
			$("label[for=netAndPhone]").hide();
			$("#netAndPhone").hide();
			$("#allowCallTr").hide();
			$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
			$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
		}
		
		 
		//设置视频控制
		if(isOpenVideo=="1" && maxVideoNum > 0){//如果授权视频是开放、并且最大视频数大于0，则显示视频标签与最大视频标签 
			$("#confVideoDiv").show();
			if(defaultOpenVideo=="1"){//默认视频是开启状态
				$("input[name=meetType]:eq(0)").attr("checked",'checked');
				$("#confMaxVideoNumDiv").show();
				$("#maxVideo").val(defaultMaxVideoNum);
			}else{
				$("#confMaxVideoNumDiv").hide();
				$("input[name=meetType]:eq(0)").removeAttr("checked");
				$("#maxVideo").val("0");
				$(".video-quality-widget").hide();
			}
			
		}else{//否则显示视频标签与最大视频标签 
			$("#confVideoDiv").hide();
			$("#confMaxVideoNumDiv").hide();
			$("input[name=meetType]:eq(0)").removeAttr("checked");
			$("#maxVideo").val("0");
			$(".video-quality-widget").hide();
		}
		
		
		//设置视频画质
		var videoType = "${defaultConfig.videoType}";
		if(videoType=="${CONF_VIDEO_TYPE_WEBBAND}"){
			$("input:radio[name=videoQuality]:eq(0)").attr("checked",'checked');
		} else if(videoType=="${CONF_VIDEO_TYPE_FLUENCY}"){
			$("input:radio[name=videoQuality]:eq(1)").attr("checked",'checked');
		} else if(videoType=="${CONF_VIDEO_TYPE_DISTINCT}"){
			$("input:radio[name=videoQuality]:eq(2)").attr("checked",'checked');
		} else if(videoType=="${CONF_VIDEO_TYPE_BEST}"){
			$("input:radio[name=videoQuality]:eq(3)").attr("checked",'checked');
		}
		
		//设置音频功能
		var isAudioFlag = "${isAudioFlag}"; 
		if(!isAudioFlag){
			$("#phoneFunc").hide();
			$("#max-audio-widget").hide();
		}
		
		/*
		//设置视频功能
		var isVideoFlag = "${isVideoFlag}"; 
		showStep(" create Conf isVideoFlag=="+isVideoFlag);
		if(!isVideoFlag){
			$("#videoFunc").hide();
			$("#max-video-widget").hide();
			$("input[name=meetType]:eq(0)").removeAttr("checked");
		}
		*/
		//设置会议模式
		var confModel = "${confModel}";
		if(confModel==1){
			$("input:radio[name=meetMode]:eq(0)").attr("checked",'checked');
		} else {
			$("input:radio[name=meetMode]:eq(1)").attr("checked",'checked');
		}
		//设置麦克风
		var micStatus = "${micStatus}";
		if(micStatus==1){
			$("input:radio[name=mikeMode]:eq(0)").attr("checked",'checked');
		} else {
			$("input:radio[name=mikeMode]:eq(1)").attr("checked",'checked');
		}
		//设置最大音视频路数
		//var videoNumber = parseInt("${videoNumber}", 10);
		var audioNumber = parseInt("${audioNumber}", 10);

	//	showStep(" create Conf videoNumber=="+videoNumber);
		//var defaultMaxVideo = parseInt("${defaultConfig.maxVideo}", 10);
		var defaultMaxAudio = parseInt("${defaultConfig.maxAudio}", 10);
		//if(defaultMaxVideo>videoNumber){
		//	defaultMaxVideo = videoNumber;
		//}
		if(defaultMaxAudio>audioNumber){
			defaultMaxAudio = audioNumber;
		}
		//$("#maxVideo").attr("value",defaultMaxVideo);
		$("#maxAudio").attr("value",defaultMaxAudio);
		//设置提前时间
		$("#aheadTime").attr("value","${defaultConfig.aheadTimes}");
		//设置参会人权限
		if("${changePage}"==1){
			$("input:checkbox[name=changePage]:eq(0)").attr("checked",'checked');
		}
		if("${annotate}"==1){
			$("input:checkbox[name=annotate]:eq(0)").attr("checked",'checked');
		}
		if("${chatAnyOne}"==1){
			$("input:checkbox[name=chatAnyOne]:eq(0)").attr("checked",'checked');
		}
		if("${chatCompere}"==1){
			$("input:checkbox[name=chatCompere]:eq(0)").attr("checked",'checked');
		}
		if("${chatParticipants}"==1){
			$("input:checkbox[name=chatParticipants]:eq(0)").attr("checked",'checked');
		}
		//设置会议功能
		if("${shareDocs}"==1){
			$("input:checkbox[name=shareDocs]:eq(0)").attr("checked",'checked');
		}
		if("${shareScreen}"==1){
			$("input:checkbox[name=shareScreen]:eq(0)").attr("checked",'checked');
		}
		if("${shareMedia}"==1){
			$("input:checkbox[name=shareMedia]:eq(0)").attr("checked",'checked');
		}
		var isShareMediaFlag = "${isShareMediaFlag}";
		if(!isShareMediaFlag) {
			$("#share-media-widget").hide();
			$("input:checkbox[name=shareMedia]:eq(0)").attr("disabled",'disabled');
			$("input:checkbox[name=shareMedia]:eq(0)").removeAttr("checked");
		}
		
		if("${whiteBoard}"==1){
			$("input:checkbox[name=whiteBoard]:eq(0)").attr("checked",'checked');
		}
		if("${fileTrans}"==1){
			$("input:checkbox[name=fileTrans]:eq(0)").attr("checked",'checked');
		}
		if("${record}"==1){
			$("input:checkbox[name=record]:eq(0)").attr("checked",'checked');
		}
		var isRecordFlag = "${isRecordFlag}";
		if(!isRecordFlag) {
			$("#record-widget").hide();
			$("input:checkbox[name=record]:eq(0)").attr("disabled",'disabled');
			$("input:checkbox[name=record]:eq(0)").removeAttr("checked");
		}
	});
	
	function sendNoticeEmail() {
		var id = $("#confIdForEmail").val();
		if(id){
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
			},{message:"${LANG['website.user.conf.create.info.addingremind']}", ui:top.window});		
		} else {
			top.errorDialog("${LANG['website.user.conf.create.info.nomeetingno']}");
		}
	}
	
	function goInviteUsers(){
		var id = $("#confIdForEmail").val();
		//top.editInventContact(id);
		top.inventContact(id);
		
	}
	
	function goInviteUsersByMsg(){
		var id = $("#confIdForEmail").val();
		//top.editInventContact(id);
		top.inventContactByMsg(id,false,2);
		
	}
	</script>
	<!-- 修改会议 -->
	<c:if test="${conf != null}">
	<fmt:formatDate var="confStartTime" value="${conf.startTime}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<fmt:formatDate var="confEndTime" value="${conf.endTime}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<fmt:formatDate var="confStartDate" value="${conf.startTime}" type="date" pattern="yyyy-MM-dd"/>
	<fmt:formatDate var="confEndDate" value="${conf.endTime}" type="date" pattern="yyyy-MM-dd"/>
	<fmt:formatDate var="confCycleEndDate" value="${confCycle.endDate}" type="date" pattern="yyyy-MM-dd"/>
	
		<script type="text/javascript">
			$(".confOperate").text("${LANG['website.user.conf.create.info.modifymeeting']}");
			
			//当前会议中要修改的电话、外呼、视频的设置
			var curConfType="${conf.confType}";
			var confOpenPhone="0";
			var confOpenOutCall="0";
			var confOpenVideo="0";
			var confMaxVideoNum="0";
			if(curConfType==1 || curConfType==3){
				confOpenPhone="1";
			}
			if(curConfType==2 || curConfType==3){
				confOpenVideo="1";
			}
			if(confOpenPhone==0){
				confOpenOutCall="0";
			}
			if(confOpenVideo==0){
				confMaxVideoNum="0";
			}
			if(parseInt(confMaxVideoNum,10)>parseInt(maxVideoNum,10)){
				confMaxVideoNum=maxVideoNum;
			}
			
			/*
			  *****修改会议的部分*******
			 * cycleConfType:
			 * 值1：修改循环会议中的所有会议 ;
			 * 值2：重新创建会议
			 * 值3：修改周期会议单次
			 */
			var cycleConfType = "${cycleConfType}";
			jQuery(function($) {
				var confNameDivHtml=$("#confNameDiv").html();
				$("#confName").val(confNameDivHtml);
				var isPublic = "3";
				if(isPublic=="1"){
					$("input:radio[name=allowPublic]:eq(0)").attr("checked",'checked');
					$(".publicPassSet-widget").show();
					var publicPass = "";
					if(publicPass){
						$("input:radio[name=publicPass]:eq(0)").attr("checked",'checked');
						$("#confPass").val(publicPass);
						$("#confirmPass").val(publicPass);
						$(".publicPass-widget").show();
					}
					$(".confInviterTR").hide();
				} else if(isPublic=="3"){
					$("input:radio[name=confInviterRadio]:eq(0)").attr("checked",'checked');
				}
				var hostKey = "${conf.hostKey}";
				if(hostKey){
					$("#hostKey").val(hostKey);
				}
				var confDesc = $("#confDesc").val();
				<%--"${conf.confDesc}";--%>
				if (confDesc) {
					$("#nowCountSpan").text(confDesc.Lenb());
				}
				var startTime = new Date("${confStartTime}");
				if(startTime && cycleConfType != "2"){
					$("#startTime").val("${confStartDate}");
					var hour = startTime.getHours();
					$("#startTimeH").attr("value",hour);
					var mis = startTime.getMinutes(); 
					$("#startTimeM").attr("value",mis);
				}
				var endTime = "${confEndTime}";
				if(endTime){
					endTime = new Date(endTime);
					$("#endTime").val("${confEndDate}");
					var ehour = endTime.getHours();
					$("#endTimeH").attr("value",ehour);
					var emis = endTime.getMinutes(); 
					if(emis % 5>0){
						emis=(parseInt(emis/5) +1) * 5 ;
						if(emis>=60){
							emis-=60;
						}
					}
					$("#endTimeM").attr("value",emis);
					///alert("emis="+emis+"\nendTimeM="+$("#endTimeM").val());
				}
				$(".repeat-cycle").attr("disabled",'disabled');
				$("#bytype").hide();   //修改会议时，不可以修改“按结束时间” “按持续时间”
				if(cycleConfType == "1"){        //修改周期会议中所有会议的cycleConfType字段值为1
					$(".startDateDiv").hide();	 //修改周期会议中所有会议，则不可修改开始时间的日期字段，只可修改小时与分钟!
					var required = $(".starttime-widget").find(".form-required");
					required.addClass("required-startTime");   //将红星挨着输入框后边
					$("input[name=repeat]:eq(0)").attr("checked",'checked');
					$(".cycleTypeTR").show();
					var cycleType = "${cycleType}";   //"${cycleType}";
					
					var infiniteFlag = "${confCycle.infiniteFlag}";    //是否无限期循环
					var repeatCount = "${confCycle.repeatCount}";      //重复次数
					if(repeatCount && repeatCount > 0){
						$("input[name=repeatScope]:eq(1)").attr("checked",'checked');     //重复x次后结束
						$("#repeatCount").val(repeatCount);
					}
					if(infiniteFlag && infiniteFlag == 1){     //无结束日期
						$("input[name=repeatScope]:eq(0)").attr("checked",'checked');
					}else if(infiniteFlag && infiniteFlag == 0 && repeatCount == 0){	//按结束日期
						$("input[name=repeatScope]:eq(2)").attr("checked",'checked');
						$("#endTimeForEver").val("${confCycleEndDate}");
					}
					//$("input[name=repeatScope]").attr("disabled",'disabled'); //重复范围可以活动   add
					//$("#repeatCount").attr("disabled",'disabled');	//重复次数范围
					 
					//$("#endTimeForEver").attr("disabled",'disabled');
					
					if(cycleType=="1"){
						$("input[name=ontime]:eq(0)").attr("checked",'checked');     //按日
						//$(".cycleType").attr("disabled",'disabled');
						var cycleDayValue = "${cycleDayValue}";
						$("#cycleDayFlag").val(cycleDayValue);      //"${cycleDayValue}"
						//$("#cycleDayFlag").attr("disabled",'disabled');
						$("div[data-view=week]").removeClass("active");
						$("div[data-view=day]").addClass("active");
					} else if (cycleType=="2") {									 //按周
						
						$("input[name=ontime]:eq(1)").attr("checked",'checked');   
						//$(".cycleType").attr("disabled",'disabled');
						var cycleWeekValue = "${cycleWeekValue}";
						cycleWeekValue = cycleWeekValue.split(",");
						$("input[name=weekday]").removeAttr("checked");
						for ( var i = 0; i < cycleWeekValue.length; i++) {
							var indexWeek = cycleWeekValue[i]-1;
							$("input[name=weekday]:eq("+indexWeek+")").attr("checked",'checked');	
						}
						//$("input[name=weekday]").attr("disabled",'disabled');
					} else if (cycleType=="3"){										//按月
						$("input[name=ontime]:eq(2)").attr("checked",'checked');   
						//$(".cycleType").attr("disabled",'disabled');
						var monthCycleOption = "${monthCycleOption}";
						if(monthCycleOption=="1"){
							$("input[name=month-type]:eq(0)").attr("checked",'checked');
							$("#eachMonthDay").val("${eachMonthDay}");    //每月第x天 
						} else if(monthCycleOption=="2"){
							$("input[name=month-type]:eq(1)").attr("checked",'checked');
							$("#weekFlag").attr("value","${weekFlag}");   //每月第x个
							$("#weekDay").attr("value","${weekDay}");     //周几
						}
						//$("input[name=month-type]").attr("disabled",'disabled');
						//$("#eachMonthDay").attr("disabled",'disabled');
						//$("#weekFlag").attr("disabled",'disabled');
						//$("#weekDay").attr("disabled",'disabled');
						$("div[data-view=week]").removeClass("active");
						$("div[data-view=month]").addClass("active");
					}
				}else if(cycleConfType == "2"){			//重新创建会议
					$(".confOperate").text("${LANG['website.user.conf.create.info.recreateconf']}");
					
					// 添加周期会议处理和一些预约会议处理
					var conftype = "${conf.confType}"; //0 预约会议 1 即时会议 2 周期会议
					$(".repeat-cycle").removeAttr("disabled");
					if(conftype == "0" ){				
						$("input:radio[name=repeat]:eq(0)").attr("disabled",'disabled');
						$("input:radio[name=repeat]:eq(1)").attr("checked",'checked');
					}else if(conftype == "2"){
						$("input:radio[name=repeat]:eq(0)").attr("checked",'checked');
						$("input:radio[name=repeat]:eq(1)").attr("disabled",'disabled');
						$(".cycleTypeTR").show();
					}
				}else if(cycleConfType == "3"){		//3:修改周期会议单次
					
					$("input[name=repeat]:eq(0)").attr("checked",'checked');//重复周期
					
					//$(".startDateDiv").hide();	 //修改周期会议中所有会议，则不可修改开始时间的日期字段，只可修改小时与分钟!
					//var required = $(".starttime-widget").find(".form-required");
					//required.addClass("required-startTime");   //将红星挨着输入框后边
					$("input[name=repeat]:eq(0)").attr("checked",'checked');
					$(".cycleTypeTR").show();
					
					var conftype = "${conf.confType}"; //0 预约会议 1 即时会议 2 周期会议
					var infiniteFlag = "${confCycle.infiniteFlag}";    //是否无限期循环
					var repeatCount = "${confCycle.repeatCount}";      //重复次数
					var cycleType = "${cycleType}";   //"${cycleType}";
					
					if(repeatCount && repeatCount > 0){
						$("input[name=repeatScope]:eq(1)").attr("checked",'checked');     //重复x次后结束
						$("#repeatCount").val(repeatCount);
					}
					if(infiniteFlag && infiniteFlag == 1){     //无结束日期
						$("input[name=repeatScope]:eq(0)").attr("checked",'checked');
					}else if(infiniteFlag && infiniteFlag == 0 && repeatCount == 0){	//按结束日期
						$("input[name=repeatScope]:eq(2)").attr("checked",'checked');
						$("#endTimeForEver").val("${confCycleEndDate}");
					}
					$("input[name=repeatScope]").attr("disabled",'disabled'); //重复范围可以活动   add
					$("#repeatCount").attr("disabled",'disabled');	//重复次数范围
					 
					$("#endTimeForEver").attr("disabled",'disabled');
					
					if(cycleType=="1"){
						$("input[name=ontime]:eq(0)").attr("checked",'checked');     //按日
						$(".cycleType").attr("disabled",'disabled');
						var cycleDayValue = "${cycleDayValue}";
						$("#cycleDayFlag").val(cycleDayValue);      //"${cycleDayValue}"
						$("#cycleDayFlag").attr("disabled",'disabled');
						$("div[data-view=week]").removeClass("active");
						$("div[data-view=day]").addClass("active");
					} else if (cycleType=="2") {									 //按周
						
						$("input[name=ontime]:eq(1)").attr("checked",'checked');   
						$(".cycleType").attr("disabled",'disabled');
						var cycleWeekValue = "${cycleWeekValue}";
						cycleWeekValue = cycleWeekValue.split(",");
						$("input[name=weekday]").removeAttr("checked");
						for ( var i = 0; i < cycleWeekValue.length; i++) {
							var indexWeek = cycleWeekValue[i]-1;
							$("input[name=weekday]:eq("+indexWeek+")").attr("checked",'checked');	
						}
						$("input[name=weekday]").attr("disabled",'disabled');
					} else if (cycleType=="3"){										//按月
						$("input[name=ontime]:eq(2)").attr("checked",'checked');   
						$(".cycleType").attr("disabled",'disabled');
						var monthCycleOption = "${monthCycleOption}";
						if(monthCycleOption=="1"){
							$("input[name=month-type]:eq(0)").attr("checked",'checked');
							$("#eachMonthDay").val("${eachMonthDay}");    //每月第x天 
						} else if(monthCycleOption=="2"){
							$("input[name=month-type]:eq(1)").attr("checked",'checked');
							$("#weekFlag").attr("value","${weekFlag}");   //每月第x个
							$("#weekDay").attr("value","${weekDay}");     //周几
						}
						$("input[name=month-type]").attr("disabled",'disabled');
						$("#eachMonthDay").attr("disabled",'disabled');
						$("#weekFlag").attr("disabled",'disabled');
						$("#weekDay").attr("disabled",'disabled');
						$("div[data-view=week]").removeClass("active");
						$("div[data-view=month]").addClass("active");
					}
				}
				
				var durationValue = "${conf.duration}";
				if(durationValue){
					if (durationValue<1440) {
						var duration = parseInt("${conf.duration}", 10);
						
						var duraHour = duration/60>>0;
						var duraMis = duration%60;
						duraMis=parseInt(duraMis/5,10)  *5;
						if(duraHour==0 && duraMis==0){
							duraMis=10;
						}
						$("#durationH").attr("value",duraHour);
						$("#durationM").attr("value",duraMis);
						//if(duraMis%5!=0 || duraMis){
						//	$("#durationM").attr("value","10");
						//}
					}
					//永久会议
					if(durationValue>1440){
						$(".endtime-widget").show();
						$(".duration-widget").hide();
						$("input:radio[name=bytype]:eq(0)").attr("checked",'checked');
					} else {
						$(".endtime-widget").hide();
						$(".duration-widget").show();
					}
				}
				var confType = "${conf.confType}";
				 

				//设置电话与外呼
				if(isOpenPhone=="1"){//如果已经对电话授权，则打开“网络语音+电话语音”、“外呼”标签
					if(confOpenPhone=="1"){//如果会议的电话是开启状态
						$("input[name=voice]:eq(0)").removeAttr("checked");
						$("input[name=voice]:eq(1)").attr("checked",'checked');
						if(isOpenOutCall=="1"){
							$("#allowCallTr").show();
							if(confOpenOutCall=="1"){//如果外呼是开启状态 
								$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
								$("input:radio[name=allowCall]:eq(1)").removeAttr("checked");
							}else{//如果外呼是关闭状态 
								$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
								$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
							}
						}else{
							$("#allowCallTr").hide();
							$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
							$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
						}
					}else{//会议的电话功能是关闭状态
						$("input[name=voice]:eq(0)").attr("checked",'checked');
						$("input[name=voice]:eq(1)").removeAttr("checked");
						$("#allowCallTr").hide();
						$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
						$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
					}
					
				}else{//未授权，则关闭“网络语音+电话语音”、“外呼”标签,并设置为网络语音、外呼关闭
					$("input[name=voice]:eq(0)").attr("checked",'checked');
					$("input[name=voice]:eq(1)").removeAttr("checked");
					$("label[for=netAndPhone]").hide();
					$("#netAndPhone").hide();
					$("#allowCallTr").hide();
					$("input:radio[name=allowCall]:eq(0)").removeAttr("checked");
					$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
				}
				
				//视频控制
				if(isOpenVideo=="1" && maxVideoNum > 0){//如果授权视频是开放、并且最大视频数大于0，则显示视频标签与最大视频标签 
					$("#confVideoDiv").show();
					if(confOpenVideo=="1"){//视频是开启状态
						$("input[name=meetType]:eq(0)").attr("checked",'checked');
						$("#confMaxVideoNumDiv").show();
						$("#maxVideo").val(confMaxVideoNum);
						$(".video-quality-widget").show();
					}else{
						$("#confMaxVideoNumDiv").hide();
						$("input[name=meetType]:eq(0)").removeAttr("checked");
						$("#maxVideo").val(0);
						$(".video-quality-widget").hide();
					}
					
				}else{//否则显示视频标签与最大视频标签 
					$("#confVideoDiv").hide();
					$("#confMaxVideoNumDiv").hide();
					$("input[name=meetType]:eq(0)").removeAttr("checked");
					$("#maxVideo").val("0");
					$(".video-quality-widget").hide();
				}
						
				
				$("#maxUser").val("${conf.maxUser}");
				//${LANG['bizconf.jsp.create_Reservation_Conf.res55']}
				//设置会议模式
				var confModel = "${confModel}";
				if(confModel==1){
					$("input:radio[name=meetMode]:eq(0)").attr("checked",'checked');
				} else {
					$("input:radio[name=meetMode]:eq(1)").attr("checked",'checked');
				}
				
				
				//设置麦克风
				var micStatus = "${micStatus}";
				if(micStatus==1){
					$("input:radio[name=mikeMode]:eq(0)").attr("checked",'checked');
				} else {
					$("input:radio[name=mikeMode]:eq(1)").attr("checked",'checked');
				}
			 
				
				var audioNumber = parseInt("0", 10);
				var confMaxAudio = parseInt("0", 10);
				$("#maxAudio").attr("value",confMaxAudio);
				
				 
				var videoType = "1";
				if(videoType=="${CONF_VIDEO_TYPE_WEBBAND}"){
					$("input:radio[name=videoQuality]:eq(0)").attr("checked",'checked');
				} else if(videoType=="${CONF_VIDEO_TYPE_FLUENCY}"){
					$("input:radio[name=videoQuality]:eq(1)").attr("checked",'checked');
				} else if(videoType=="${CONF_VIDEO_TYPE_DISTINCT}"){
					$("input:radio[name=videoQuality]:eq(2)").attr("checked",'checked');
				} else if(videoType=="${CONF_VIDEO_TYPE_BEST}"){
					$("input:radio[name=videoQuality]:eq(3)").attr("checked",'checked');
				}
				
				$("#aheadTime").val("0");
				//permission
				if("${changePage}"==1){
					$("input:checkbox[name=changePage]:eq(0)").attr("checked",'checked');
				}
				if("${annotate}"==1){
					$("input:checkbox[name=annotate]:eq(0)").attr("checked",'checked');
				}
				if("${chatAnyOne}"==1){
					$("input:checkbox[name=chatAnyOne]:eq(0)").attr("checked",'checked');
				}
				if("${chatCompere}"==1){
					$("input:checkbox[name=chatCompere]:eq(0)").attr("checked",'checked');
				}
				if("${chatParticipants}"==1){
					$("input:checkbox[name=chatParticipants]:eq(0)").attr("checked",'checked');
				}
				
				//${LANG['bizconf.jsp.conf_default_setup.res9']}
				if("${shareDocs}"==1){
					$("input:checkbox[name=shareDocs]:eq(0)").attr("checked",'checked');
				}
				if("${shareScreen}"==1){
					$("input:checkbox[name=shareScreen]:eq(0)").attr("checked",'checked');
				}
				if("${shareMedia}"==1){
					$("input:checkbox[name=shareMedia]:eq(0)").attr("checked",'checked');
				}
				var isShareMediaFlag = "${isShareMediaFlag}";
				if(!isShareMediaFlag) {
					$("#shareMedia").hide();
					$("#shareMediaM").hide();
					$("input:checkbox[name=shareMedia]:eq(0)").attr("disabled",'disabled');
					$("input:checkbox[name=shareMedia]:eq(0)").removeAttr("checked");
				}
				if("${whiteBoard}"==1){
					$("input:checkbox[name=whiteBoard]:eq(0)").attr("checked",'checked');
				}
				if("${fileTrans}"==1){
					$("input:checkbox[name=fileTrans]:eq(0)").attr("checked",'checked');
				}
				if("${record}"==1){
					$("input:checkbox[name=record]:eq(0)").attr("checked",'checked');
				}
				var isRecordFlag = "${isRecordFlag}";
				if(!isRecordFlag) {
					$("#record").hide();
					$("#recordM").hide();
					$("input:checkbox[name=record]:eq(0)").attr("disabled",'disabled');
					$("input:checkbox[name=record]:eq(0)").removeAttr("checked");
				}	
			});
		</script>
	</c:if>
	<script type="text/javascript">
		var SCHEDULE_CONSTANT={
			confname_empty:"${LANG['website.user.create.reser.js.message.confname.empty']}",//请输入会议主题！
			confname_length:"${LANG['website.user.create.reser.js.message.confname.length']}",//主题长度为1~32个任意字符！
			hostpass_empty:"${LANG['website.user.create.reser.js.message.hostpass']}",//请输入主持人密码！
			hostpass_number:"${LANG['website.user.create.reser.js.message.hostpass.neednumber']}",//主持人密码必须是数字！
			hostpass_length:"${LANG['website.user.create.reser.js.message.hostpass.length']}",//主持人密码长度为6~12个字符！
			confpass_empty:"${LANG['website.user.create.reser.js.message.confpass.empty']}",//请输入会议密码！
			confpass_length:"${LANG['website.user.create.reser.js.message.confpass.length']}",//会议密码长度为6~16个字符！
			confpass_confirm_empty:"${LANG['website.user.create.reser.js.message.confpass.confirm.empty']}",//请输入确认密码！
			confpass_confirm_length:"${LANG['website.user.create.reser.js.message.confpass.confirm.length']}",//确认密码的长度为6~16个字符！
			confpass_confirm_diff:"${LANG['website.user.create.reser.js.message.confpass.confirm.diff']}",//两次密码不一致！
			confdesc_length:"${LANG['website.user.create.reser.js.message.confdesc.length']}",//会议描述的长度为1~150个字符！
			interval_days:"${LANG['website.user.create.reser.js.message.interval.days']}",//请输入间隔天数！
			interval_days_integer:"${LANG['website.user.create.reser.js.message.interval.days.integer']}",//间隔天数必须是整数
			interval_days_soap:"${LANG['website.user.create.reser.js.message.interval.days.soap']}",//间隔天数范围最少为一天, 最多31天
			least_week:"${LANG['website.user.create.reser.js.message.least.oneweek']}",//请至少选择一个星期
			loop_date_error:"${LANG['website.user.create.reser.js.message.loop.date.error']}",//循环的日期不正确
			start_cantless_now:"${LANG['website.user.create.reser.js.message.startdate.cantless.now']}",//开始时间不能早于当前时间
			end_cantless_start:"${LANG['website.user.create.reser.js.message.enddate.cantless.startdate']}",//结束时间不能早于开始时间
			conf_least_five:"${LANG['website.user.create.reser.js.message.conf.least.five']}",//会议时长至少为5分钟
			duration_least_five:"${LANG['website.user.create.reser.js.message.duration.least.five']}",//持续时间最少5分钟
			aheadtime:"${LANG['website.user.create.reser.js.message.aheadtime']}",//请输入会议提前时间
			aheadtime_integer:"${LANG['website.user.create.reser.js.message.aheadtime.integer']}",//会议提前时间必须是整数
			aheadtime_soap:"${LANG['website.user.create.reser.js.message.aheadtime.soap']}",//会议提前时间范围为5~60分钟
			aheadtime_scope:"${LANG['website.user.create.reser.js.message.aheadtime.scope']}",
			minute_name:"${LANG['website.common.minute.name']}",//分钟
			hour_name:"${LANG['website.common.hour.name']}",//小时
			cycle_date_error:"${LANG['website.user.create.reser.js.message.cycle.date.error']}",//日循环会议
			cycle_daily:"${LANG['website.user.create.reser.js.message.cycle.daily']}",//日循环会议
			cycle_weekly:"${LANG['website.user.create.reser.js.message.cycle.weekly']}",//周循环会议
			cycle_monthly:"${LANG['website.user.create.reser.js.message.cycle.monthly']}",//月循环会议
			cycle_none:"${LANG['website.user.create.reser.js.message.cycle.none']}",//非周期循环会议
			repeat_desc:"${LANG['website.user.create.reser.js.message.repeat.desc']}",//重复%s次后结束
			repeat_range_noenddate:"${LANG['website.user.create.reser.cycle.repeat.range.no.enddate']}",//无结束日期
			end_time:"${LANG['website.user.create.reser.conf.end.time']}",//结束日期
			funcs_phone:"${LANG['website.user.create.reser.js.message.funcs.phone']}",//电话功能
			funcs_video:"${LANG['website.user.create.reser.js.message.funcs.video']}",//视频功能
			funcs_phoneandvideo:"${LANG['website.user.create.reser.js.message.funcs.phoneandvideo']}",//视频功能、电话功能
			audio_net:"${LANG['website.user.create.reser.js.message.audio.net']}",//网络语音
			audio_netandphone:"${LANG['website.user.create.reser.js.message.audio.netandphone']}",//网络语音+电话语音
			modify_succeed:"${LANG['website.user.create.reser.js.message.modify.succeed']}",//修改会议成功
			create_info:"${LANG['website.user.create.reser.js.message.create.info']}",//正在创建会议, 请稍等...
			recreate_succeed:"${LANG['website.user.create.reser.js.message.recreate.succeed']}",//重新创建会议成功
			recreate_info:"${LANG['website.user.create.reser.js.message.recreate.info']}",//正在重新创建会议, 请稍等...
			modify_info:"${LANG['website.user.create.reser.js.message.modify.info']}",//正在修改会议, 请稍等...
			everychar_length32:"${LANG['website.user.create.reser.js.message.everychar.length128']}",//1-128个任意字符
			confname_unblank:"${LANG['website.user.create.reser.js.message.confname.unblank']}",//会议主题两边不能有空符号
			number_length12:"${LANG['website.user.create.reser.js.message.number.length12']}",//6-12个数字
			hostpass_unblank:"${LANG['website.user.create.reser.js.message.hostpass.unblank']}",//主持人密码两边不能有空符号
			everychar_length16:"${LANG['website.user.create.reser.js.message.everychar.length16']}",//1-16个任意字符
			pass_confirm_diff:"${LANG['website.user.create.reser.js.message.pass.confirm.diff']}",//2次密码不一致,请确认
			duration_zero:"${LANG['website.user.create.reser.js.message.duration.cant.zero']}",//会议时长不能为0
			interval_unblank:"${LANG['website.user.create.reser.js.message.interval.unblank']}",//间隔天数两边不能有空符号
			interval_error:"${LANG['website.user.create.reser.js.message.interval.inputerror']}",//请输入正确的间隔天数
			repeat_times:"${LANG['website.user.create.reser.js.message.repeat.times']}",//请输入正确的重复次数间
			ahead_time_error:"${LANG['website.user.create.reser.js.message.aheat.times.error']}",	//请输入正确的会议提前时间
			
			zone_time:"${LANG['website.common.time.name']}",//时间
			meeting_pass_no:"${LANG['bizconf.jsp.admin.edit_userbase.res5']}",//密码
			meeting_type_share:"${LANG['com.vcaas.portal.confstarttype.screen_share']}",//会议类型
			meeting_type_video:"${LANG['com.vcaas.portal.confstarttype.video']}",//会议类型
			meeting_type_yes:"${LANG['website.common.option.yes']}",//先于主持人入会
			meeting_type_no:"${LANG['website.common.option.no']}",//先于主持人入会
			meeting_jbh_yes:"${LANG['website.user.view.conf.conf.preHostor.yes']}",//先于主持人入会
			meeting_jbh_no:"${LANG['website.user.view.conf.conf.preHostor.no']}",//先于主持人入会
			video_option_on:"${LANG['user.menu.conf.pad.videooption.on']}",
			video_option_off:"${LANG['user.menu.conf.pad.videooption.off']}"
		};
		
		$(document).ready(function(){
			var hostKey = "${conf.hostKey}";
			if(hostKey){
				$("#hostKey").val(hostKey);
			}else{
				$("#hostKey").val("");
			}
		});
	</script>
	<script type="text/javascript" src="/assets/js/apps/biz.meeting.schedule.js?v=16"></script>
</body>
</html>