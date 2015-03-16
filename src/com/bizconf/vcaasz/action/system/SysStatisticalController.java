package com.bizconf.vcaasz.action.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteMeetingParticipantsNum;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.StatisticsService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@Interceptors({ SystemUserInterceptor.class })
@ReqPath("statistical")
public class SysStatisticalController extends BaseController {
	private final Logger logger = Logger
			.getLogger(SysStatisticalController.class);

	@Autowired
	StatisticsService statisticsService;

	@Autowired
	UserService userService;

	@AsController(path = "overview")
	public Object overview(HttpServletRequest request) {

		// 获取今日的在线会场数量
		int tadayMeetNum = statisticsService.countAllRunningMeetings();
		request.setAttribute("tadayMeetNum", tadayMeetNum);

		// 获取近一周个项会议数据
		Date startWeekDate = DateUtil.getGmtDate(null);
		Date endWeekDate = DateUtil
				.getRecentWeekEndDate(DateUtil.BJ_TIME_OFFSET);
		// 近一周会议时间
		int weekMeetingTime = statisticsService.countMeetingTimesByDate(
				startWeekDate, endWeekDate, 0);
		request.setAttribute("weekMeetingTime", weekMeetingTime);
		// 近一周会议的场次
		int weekMeetNum = statisticsService.countFlishedMeetingsByDate(
				startWeekDate, endWeekDate, 0);
		request.setAttribute("weekMeetNum", weekMeetNum);
		// 近一周的参会人次
		int weekParticiPantNum = statisticsService
				.countMeetingParticipartsByDate(startWeekDate, endWeekDate, 0);
		request.setAttribute("weekParticiPantNum", weekParticiPantNum);

		// 获取近一月的数据
		Date sratMonthDate = DateUtil.getGmtDate(null);
		Date endMonthDate = DateUtil
				.getRecentMonthEndDate(DateUtil.BJ_TIME_OFFSET);
		// 近一月会议时间
		int monMeetingTime = statisticsService.countMeetingTimesByDate(
				sratMonthDate, endMonthDate, 0);
		request.setAttribute("monMeetingTime", monMeetingTime);
		// 近一月会议的场次
		int monMeetNum = statisticsService.countFlishedMeetingsByDate(
				sratMonthDate, endMonthDate, 0);
		request.setAttribute("monMeetNum", monMeetNum);
		// 近一月的参会人次
		int monParticiPantNum = statisticsService
				.countMeetingParticipartsByDate(sratMonthDate, endMonthDate, 0);
		request.setAttribute("monParticiPantNum", monParticiPantNum);

		// 统计全部数据
		int totalMeetingTime = statisticsService.countAllMeetingsTime();
		request.setAttribute("totalMeetingTime", totalMeetingTime);
		// 会议场次
		int totalMeetingNum = statisticsService.countAllMeetings();
		request.setAttribute("totalMeetingNum", totalMeetingNum);

		// 参会人次
		int totalParticipantNum = statisticsService.countAllParticipants();
		request.setAttribute("totalParticipantNum", totalParticipantNum);

		return new ActionForward.Forward("/jsp2.0/system/sys_statistical.jsp");
	}

	/**
	 * 查看正在进行的会议
	 * 
	 * @param request
	 * @return
	 */
	@AsController(path = "viewRunningMeeting")
	public Object viewRunningMeeting(@CParam("pageNo") int pageNo,
			HttpServletRequest request) {

		SystemUser currentSys = userService.getCurrentSysAdmin(request);
		request.setAttribute("user", currentSys);

		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang = CookieUtil.getCookieByDomain(request,
				ConstantUtil.COOKIE_LANG_KEY, domain);
		request.setAttribute("lang", lang);

		int pageSize = currentSys.getPageSize();
		PageBean<ConfBase> runningConfs = statisticsService
				.getAllRunningMeetings(pageNo, pageSize);

		request.setAttribute("pageModel", runningConfs);
		return new ActionForward.Forward("/jsp2.0/system/viewRunningConf.jsp");
	}

	@AsController(path = "viewMeetingNumChar")
	public Object viewMeetingNumChar(@CParam("freq") int freq,
			HttpServletRequest request) {

		List<Date> dateList = new ArrayList<Date>();
		List<Integer> numList = new ArrayList<Integer>();

		Date startWeekDate = null;
		Date endWeekDate = null;
		if (freq == ConfConstant.FREQ_WEEKLY) {
			startWeekDate = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			endWeekDate = DateUtil
					.getRecentWeekEndDate(DateUtil.BJ_TIME_OFFSET);
		} else {
			startWeekDate = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			endWeekDate = DateUtil
					.getRecentMonthEndDate(DateUtil.BJ_TIME_OFFSET);
		}
		// 最近一周中的某一天
		Date everyDate = startWeekDate;
		while (!everyDate.before(endWeekDate)) {
			// 这一天的结束
			Date everyDateEndTime = new Date(everyDate.getTime()
					+ (24 * 3600 * 1000l - 1000l));
			dateList.add(everyDateEndTime);

			int meetingNum = statisticsService.countFlishedMeetingsByDate(
					everyDateEndTime, everyDate, 0);
			numList.add(meetingNum);

			everyDate = new Date(everyDate.getTime() - 24 * 3600 * 1000l);
		}
		
		Collections.reverse(dateList);
		Collections.reverse(numList);
		request.setAttribute("dateList", dateList);
		request.setAttribute("numList", numList);

		request.setAttribute("freq", freq);

		return new ActionForward.Forward(
				"/jsp2.0/system/viewMeetingNumChart.jsp");
	}

	@AsController(path = "viewAllMeetingNumChar")
	public Object viewAllMeetingNumChar(@CParam("statType") int statType,
			@CParam("year") String year,@CParam("month") String month,
			@CParam("eyear") String eyear,@CParam("emonth") String emonth,
			@CParam("startDate") String iStartDate,@CParam("endDate") String iEndDate,
			 HttpServletRequest request) throws Exception{

		List<Date> dateList = new ArrayList<Date>();
		List<Integer> numList = new ArrayList<Integer>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前的年份和月份
		request.setAttribute("curryear", DateUtil.getBeijingBillCurrentYear());
		request.setAttribute("currmonth", DateUtil.getBeijingBillCurrentMonth());
		
		if(StringUtil.isEmpty(iStartDate)  || StringUtil.isEmpty(iEndDate)){
			
			Date beijingDate = DateUtil.getBejingDateByGmtDate(DateUtil
					.getGmtDate(null));
			Calendar c0 = Calendar.getInstance();
			c0.setTime(beijingDate);
			String selectyear = String.valueOf(c0.get(Calendar.YEAR));
			String selectmonth = String.valueOf((c0.get(Calendar.MONTH)+1));
			Date endDate = DateUtil.getMonthStratDate(selectyear, selectmonth, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date startDate = DateUtil.getPreMonth(endDate);
			endDate = new Date(endDate.getTime() - 1000l);
			
			//TODO 这是一个妥协方法
			startDate = new Date(startDate.getTime() + 24*3600*1000l);
			iStartDate = sdf.format(startDate);
			iEndDate = sdf.format(endDate);
			
		}
		
		//按月统计
		if(StringUtil.isEmpty(year)){
			year = String.valueOf(DateUtil.getBeijingBillCurrentYear());
		}
		if(StringUtil.isEmpty(month)){
			month = "1";
		}
		if(StringUtil.isEmpty(eyear)){
			eyear = year;
		}
		if(StringUtil.isEmpty(emonth)){
			emonth = String.valueOf(DateUtil.getBeijingBillCurrentMonth());
		}
		
		if(statType == 1){
			
			Date startDate = DateUtil.getMonthStratDate(year, month, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date endDate = DateUtil.getMonthStratDate(eyear, emonth, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			if(startDate.after(endDate)){
				Date temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			Date everyDate = endDate;
			while (!everyDate.before(startDate)) {
				Date endMonthDate = DateUtil.getMonthEndDate(everyDate);
				dateList.add(new Date(endMonthDate.getTime() - 48*3600*1000l));

				int meetingNum = statisticsService.countFlishedMeetingsByDate(
						endMonthDate, everyDate, 0);
				numList.add(meetingNum);
				
				everyDate = DateUtil.getPreMonth(everyDate);
				everyDate = DateUtil.getCurrentMonthEnd(everyDate);
			}
		}else{
			Date startDate = DateUtil.getGmtDateByTimeZone(sdf.parse(iStartDate), Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue()) ;
			Date endDate = DateUtil.getGmtDateByTimeZone(sdf.parse(iEndDate), Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue()) ;
			//endDate = new Date(endDate.getTime()+24*3600000l);
			if(startDate.after(endDate)){
				Date temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			Date now = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			if(endDate.after(now)){
				endDate = now;
			}
			// 最近一周中的某一天
			Date everyDate = endDate;
			while (!everyDate.before(startDate)) {
				// 这一天的结束
				Date everyDateEndTime = new Date(everyDate.getTime()
						+ (24 * 3600 * 1000l - 1000l));
				dateList.add(everyDateEndTime);

				int meetingNum = statisticsService.countFlishedMeetingsByDate(
						everyDateEndTime, everyDate, 0);
				numList.add(meetingNum);

				everyDate = new Date(everyDate.getTime() - 24 * 3600 * 1000l);
			}
		}
		
		request.setAttribute("statType", statType);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("eyear", eyear);
		request.setAttribute("emonth", emonth);
		
		request.setAttribute("startDate", iStartDate);
		request.setAttribute("endDate", iEndDate);
		
		
		Collections.reverse(dateList);
		Collections.reverse(numList);
		
		request.setAttribute("dateList", dateList);
		request.setAttribute("numList", numList);

		return new ActionForward.Forward(
				"/jsp2.0/system/viewAllMeetingNumChart.jsp");
	}

	@AsController(path = "viewDetailMeetingNum")
	public Object viewDetailMeetingNum(@CParam("pageNo") int pageNo,
			@CParam("year") String year,@CParam("month") String month,
			@CParam("date") Date date, HttpServletRequest request) {
		
		if(!StringUtil.isEmpty(year) && !StringUtil.isEmpty(month)){
			Date startDate = DateUtil.getMonthStratDate(year, month, 28800000);
			Date endDate = DateUtil.getMonthEndDate(startDate);
			
			List<SiteMeetingParticipantsNum> siteMeetingNums = statisticsService
					.getSiteMeetingsNumber(startDate, endDate);
			request.setAttribute("siteMeetingNums", siteMeetingNums);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
		}else{
			Date startDate = DateUtil.getGmtDateByTimeZone(date,
					Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date endDate = new Date(startDate.getTime() + 24 * 3600 * 1000l);
	
			List<SiteMeetingParticipantsNum> siteMeetingNums = statisticsService
					.getSiteMeetingsNumber(startDate, endDate);
			request.setAttribute("siteMeetingNums", siteMeetingNums);
			request.setAttribute("date", date);
		}
		return new ActionForward.Forward(
				"/jsp2.0/system/viewDetailSiteMeetingNum.jsp");
	}
	
	
	
	@AsController(path = "viewParticipantNumChar")
	public Object viewParticipantNumChar(@CParam("freq") int freq,
			HttpServletRequest request) {
		
		List<Date> dateList = new ArrayList<Date>();
		List<Integer> numList = new ArrayList<Integer>();
		
		Date startWeekDate = null;
		Date endWeekDate = null;
		//近一周
		if (freq == ConfConstant.FREQ_WEEKLY) {
			startWeekDate = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			endWeekDate = DateUtil
					.getRecentWeekEndDate(DateUtil.BJ_TIME_OFFSET);
		//近一月
		} else {
			startWeekDate = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			endWeekDate = DateUtil
					.getRecentMonthEndDate(DateUtil.BJ_TIME_OFFSET);
		}
		// 最近一周中的某一天
		Date everyDate = startWeekDate;
		while (!everyDate.before(endWeekDate)) {
			// 这一天的结束
			Date everyDateEndTime = new Date(everyDate.getTime()
					+ (24 * 3600 * 1000l - 1000l));
			dateList.add(everyDateEndTime);
			
			int meetingNum = statisticsService.countMeetingParticipartsByDate(
					everyDateEndTime, everyDate, 0);
			numList.add(meetingNum);
			
			everyDate = new Date(everyDate.getTime() - 24 * 3600 * 1000l);
		}
		
		Collections.reverse(dateList);
		Collections.reverse(numList);
		
		request.setAttribute("dateList", dateList);
		request.setAttribute("numList", numList);
		
		request.setAttribute("freq", freq);
		
		return new ActionForward.Forward(
				"/jsp2.0/system/viewParticipantNumChart.jsp");
	}
	
	
	@AsController(path = "viewDetailParticipantNum")
	public Object viewDetailParticipantNum(@CParam("pageNo") int pageNo,
			@CParam("year") String year,@CParam("month") String month,
			@CParam("date") Date date, @CParam("byhour") int byhour,
			HttpServletRequest request) {
		
		if(!StringUtil.isEmpty(year) && !StringUtil.isEmpty(month)){
			Date startDate = DateUtil.getMonthStratDate(year, month, 28800000);
			Date endDate = DateUtil.getMonthEndDate(startDate);
			
			List<SiteMeetingParticipantsNum> siteMeetingNums = statisticsService
					.getSiteParticipantsNum(startDate, endDate);
			request.setAttribute("siteMeetingNums", siteMeetingNums);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
		}else{
			Date startDate = DateUtil.getGmtDateByTimeZone(date,
					Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date endDate = new Date(startDate.getTime() + 24 * 3600 * 1000l);
			if(byhour>0){
				endDate = DateUtil.getGmtDateByTimeZone(date, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
				startDate = new Date(endDate.getTime()-3600*1000l);
			}
			List<SiteMeetingParticipantsNum> siteMeetingNums = statisticsService
					.getSiteParticipantsNum(startDate, endDate);
			request.setAttribute("siteMeetingNums", siteMeetingNums);
			request.setAttribute("date", date);
			request.setAttribute("byhour", byhour);
		}
		return new ActionForward.Forward(
				"/jsp2.0/system/viewDetailParticipantNum.jsp");
	}
	
	
	
	@AsController(path = "viewAllParticipantNumChar")
	public Object viewAllParticipantNumChar(@CParam("statType") int statType,
			@CParam("year") String year,@CParam("month") String month,
			@CParam("eyear") String eyear,@CParam("emonth") String emonth,
			@CParam("startDate") String iStartDate,@CParam("endDate") String iEndDate,
			@CParam("selectDate") String selectDate,
			 HttpServletRequest request) throws Exception {

		List<Date> dateList = new ArrayList<Date>();
		List<Integer> numList = new ArrayList<Integer>();
		
		//获取当前的年份和月份
		request.setAttribute("curryear", DateUtil.getBeijingBillCurrentYear());
		request.setAttribute("currmonth", DateUtil.getBeijingBillCurrentMonth());
		
		//初始化按日选择的开始时间结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtil.isEmpty(iStartDate)  || StringUtil.isEmpty(iEndDate)){
			
			Date beijingDate = DateUtil.getBejingDateByGmtDate(DateUtil
					.getGmtDate(null));
			Calendar c0 = Calendar.getInstance();
			c0.setTime(beijingDate);
			String selectyear = String.valueOf(c0.get(Calendar.YEAR));
			String selectmonth = String.valueOf((c0.get(Calendar.MONTH)+1));
			Date endDate = DateUtil.getMonthStratDate(selectyear, selectmonth, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date startDate = DateUtil.getPreMonth(endDate);
			endDate = new Date(endDate.getTime() - 1000l);
			
			startDate = new Date(startDate.getTime() + 24*3600*1000l);
			iStartDate = sdf.format(startDate);
			iEndDate = sdf.format(endDate);
			
		}
		
		if(StringUtil.isEmpty(year)){
			year = String.valueOf(DateUtil.getBeijingBillCurrentYear());
		}
		if(StringUtil.isEmpty(month)){
			month = "1";
		}
		if(StringUtil.isEmpty(eyear)){
			eyear = year;
		}
		if(StringUtil.isEmpty(emonth)){
			emonth = String.valueOf(DateUtil.getBeijingBillCurrentMonth());
		}
		
		//按时间选择
		if(StringUtil.isEmpty(selectDate)){
			Date localDate = DateUtil.getOffsetDateByGmtDate(DateUtil.getGmtDate(null), DateUtil.BJ_TIME_OFFSET);
			Date preDate = new Date(localDate.getTime() - 24*3600*1000l);
			selectDate = sdf.format(preDate);
		}
		
		//按月统计
		if(statType == 1){
			
			Date startDate = DateUtil.getMonthStratDate(year, month, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			Date endDate = DateUtil.getMonthStratDate(eyear, emonth, Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue());
			if(startDate.after(endDate)){
				Date temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			
			Date everyDate = endDate;
			while (!everyDate.before(startDate)) {
				Date endMonthDate = DateUtil.getMonthEndDate(everyDate);
				dateList.add(new Date(endMonthDate.getTime() - 48*3600*1000l));

				int meetingNum = statisticsService.countMeetingParticipartsByDate(
						endMonthDate, everyDate, 0);
				numList.add(meetingNum);
				
				everyDate = DateUtil.getPreMonth(everyDate);
				everyDate = DateUtil.getCurrentMonthEnd(everyDate);
			}
			
		//按天统计
		}else if(statType == 2){
			
			Date startDate = DateUtil.getGmtDateByTimeZone(sdf.parse(iStartDate), Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue()) ;
			Date endDate = DateUtil.getGmtDateByTimeZone(sdf.parse(iEndDate), Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue()) ;
			
			if(startDate.after(endDate)){
				Date temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			
			Date now = DateUtil.getTadayGMTDate(DateUtil.BJ_TIME_OFFSET);
			if(endDate.after(now)){
				endDate = now;
			}
			
			// 最近一周中的某一天
			Date everyDate = endDate;
			while (!everyDate.before(startDate)) {
				// 这一天的结束
				Date everyDateEndTime = new Date(everyDate.getTime()
						+ (24 * 3600 * 1000l - 1000l));
				dateList.add(everyDateEndTime);

				int meetingNum = statisticsService.countMeetingParticipartsByDate(
						everyDateEndTime, everyDate, 0);
				numList.add(meetingNum);

				everyDate = new Date(everyDate.getTime() - 24 * 3600 * 1000l);
			}
			
		//按小时统计
		}else{
			Date startDate = DateUtil.getGmtDateByTimeZone(sdf.parse(selectDate), Long.valueOf(DateUtil.BJ_TIME_OFFSET).intValue()) ;
			Date endDate = new Date(startDate.getTime()+24*3600*1000l);
			
			// 最近一周中的某一天
			Date everyDate = endDate;
			while (!everyDate.before(startDate)) {
							// 这一天的结束
				Date everyDateStartTime = new Date(everyDate.getTime()
									- 3600 * 1000l);
				dateList.add(everyDate);

				int meetingNum = statisticsService.countMeetingParticipartsByDate(
									 everyDate,everyDateStartTime, 0);
				numList.add(meetingNum);

				everyDate = everyDateStartTime;
			}
		}
		
		request.setAttribute("statType", statType);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("eyear", eyear);
		request.setAttribute("emonth", emonth);
		
		request.setAttribute("startDate", iStartDate);
		request.setAttribute("endDate", iEndDate);
		
		request.setAttribute("selectDate", selectDate);
		
		Collections.reverse(dateList);
		Collections.reverse(numList);
		request.setAttribute("dateList", dateList);
		request.setAttribute("numList", numList);

		return new ActionForward.Forward(
				"/jsp2.0/system/viewAllParticipantNumChart.jsp");
	}

}
