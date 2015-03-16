package com.bizconf.vcaasz.action.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.sms.SMSComponent;
import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.constant.ZoomConfStatus;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.ContactGroup;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ZoomDefaultConfig;
import com.bizconf.vcaasz.interceptors.UserInterceptor;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.logic.EmailLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.service.ContactGroupService;
import com.bizconf.vcaasz.service.ContactService;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.PublicConfService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.SyncConfService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.service.ZoomDefaultConfigService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.vcaasz.util.UrlUtils;
import com.bizconf.vcaasz.util.ValidateUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;

/**
 * 企业用户会议controller
 * @author wangyong
 * 2013/3/5
 */
@ReqPath("conf")
public class ConfController extends BaseController {
	private final Logger logger = Logger.getLogger(ConfController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	ConfService confService;
	@Autowired
	SiteService siteService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	private ContactGroupService groupService;
	@Autowired
	ConfLogic confLogic;
	@Autowired
	EmailService emailService;
	@Autowired
	LoginService loginService;
	@Autowired
	PublicConfService publicConfService;
	@Autowired
	ConfUserService confUserService;
	@Autowired
	EmpowerConfigService empowerConfigService;
	@Autowired
	LicService licService;
	@Autowired
	ZoomDefaultConfigService zoomDefaultConfigService;
	
	@Autowired
	SyncConfService syncConfService;
	
	@Autowired
	EmailLogic emailLogic;
	
	@Autowired
	ZoomMeetingOperationComponent zoomMeetingOperator;
	
	@Autowired
	private ConfUserLogic confUserLogic;
	
	@Autowired
	private SMSComponent smsComponent;
	@Autowired
	ContactService contactService;
	
	@Autowired
	DataCenterService centerService;
	
	/**
	 * 查询与自己有关的会议，包括4种状态：
	 * 1.与自己有关的正在进行中的会议
	 * 2.与自己有关的即将开始的会议
	 * 3.自己错过的会议
	 * 4.自己参加过的的会议
	 * wangyong
	 * 2013-3-8
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listConf")
	public Object listConf(PageModel pageModel, HttpServletRequest request){
		if (!loginService.isLogined(request)) {
			return new ActionForward.Redirect("/user/conf/listPubConf");
		}
		List<ConfBase> dringConfList = new ArrayList<ConfBase>();
		List<ConfBase> upcomingConfList = new ArrayList<ConfBase>();
		List<ConfBase> missConfList = new ArrayList<ConfBase>();
		List<ConfBase> attendedConfList = new ArrayList<ConfBase>();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		Integer rows = 0;
		rows = confService.countDuringConfList(null, currentUser);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows.intValue());
			request.setAttribute("dringConfRows", rows.intValue());
			pageModel.setPageSize(ConfConstant.CONF_LIST_DEFALT_ROWS);
			dringConfList = confService.listDuringConfList(null, pageModel, null, null, currentUser, currentSite);
			dringConfList = ObjectUtil.parseHtmlWithList(dringConfList, "confName");     //数据库的数据转为实际字符
		}
		rows = confService.countUpcomingConfList(null, currentUser, ConfConstant.CONF_LIST_DASHBOARD_DAYS);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows.intValue());
			request.setAttribute("UpcomingConfRows", rows.intValue());
			pageModel.setPageSize(ConfConstant.CONF_LIST_DEFALT_ROWS);
			upcomingConfList = confService.listUpcomingConfList(null, pageModel, null, null, currentUser, currentSite, ConfConstant.CONF_LIST_DASHBOARD_DAYS);
			upcomingConfList = ObjectUtil.parseHtmlWithList(upcomingConfList, "confName");     //数据库的数据转为实际字符
		}
		rows = confService.countMissConfList(null, currentUser, ConfConstant.CONF_LIST_DASHBOARD_DAYS, ConfConstant.CONF_HIDE_FLAG_FALSE);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows.intValue());
			request.setAttribute("MissConfRows", rows.intValue());
			pageModel.setPageSize(ConfConstant.CONF_LIST_DEFALT_ROWS);
			missConfList = confService.listMissConfList(null, pageModel, null, null, currentUser, currentSite, ConfConstant.CONF_LIST_DASHBOARD_DAYS, ConfConstant.CONF_HIDE_FLAG_FALSE);
			missConfList = ObjectUtil.parseHtmlWithList(missConfList, "confName");     //数据库的数据转为实际字符
		}
		rows = confService.countAttendedConfList(null, currentUser, ConfConstant.CONF_LIST_DASHBOARD_DAYS);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows.intValue());
			request.setAttribute("AttendedConfRows", rows.intValue());
			pageModel.setPageSize(ConfConstant.CONF_LIST_DEFALT_ROWS);
			attendedConfList = confService.listAttendedConfList(null, pageModel, null, null, currentUser, currentSite, ConfConstant.CONF_LIST_DASHBOARD_DAYS);
			attendedConfList = ObjectUtil.parseHtmlWithList(attendedConfList, "confName");     //数据库的数据转为实际字符
		}
		//获取会议周期
		Set<ConfCycle> cycs = new HashSet<ConfCycle>();
		cycs.addAll(confLogic.getConfCyclesByConf(dringConfList));
		cycs.addAll(confLogic.getConfCyclesByConf(upcomingConfList));
		cycs.addAll(confLogic.getConfCyclesByConf(missConfList));
		cycs.addAll(confLogic.getConfCyclesByConf(attendedConfList));
		request.setAttribute("cycs", cycs);
		//当前用户喜好设置时区的时间
		Date curDate=currentUser.getUserLocalTime();
//		Date curDate=currentSite.getSiteLocalTime();
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("user", currentUser); 
		request.setAttribute("dringConfList", dringConfList);
		request.setAttribute("upcomingConfList", upcomingConfList);
		request.setAttribute("missConfList", missConfList);
		request.setAttribute("attendedConfList", attendedConfList);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/user/conf_list_index.jsp");
	}
	
	/**
	 * 进入我的会议页面
	 * @param request
	 * @return
	 */
	@AsController(path = "myMeeting")
	public Object myMeeting(HttpServletRequest request){
		return new ActionForward.Forward("/jsp/user/conf_list_main.jsp");
	}
	
	/**
	 * 查询与自己有关的正在进行中的会议
	 * @param titleOrHostName 会议主题或主持人
	 * wangyong
	 * 2013-3-5
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listWithDuringConf")
	public Object listWithDuringConf(@CParam("titleOrHostName")  String titleOrHostName, PageModel pageModel, HttpServletRequest request){
		List<ConfBase> confList = new ArrayList<ConfBase>();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		String sortField = request.getParameter("sortField");
		String sortord = request.getParameter("sortord");
		Integer rows = 0;
		rows = confService.countDuringConfList(titleOrHostName, currentUser);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows);
			confList = confService.listDuringConfList(titleOrHostName, pageModel, sortField, sortord, currentUser, currentSite);
			confList = ObjectUtil.parseHtmlWithList(confList, "confName");     //数据库的数据转为实际字符
		}else{
			pageModel.setRowsCount(0);
		}
		Map<Integer,Integer> participantSizeList = getParticipants(confList);   //获取每个会议的参会人个数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("user", currentUser); 
		request.setAttribute("confList", confList);
		request.setAttribute("titleOrHostName", titleOrHostName);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sortField", sortField);   //传排序字段的编号
		request.setAttribute("sortord", sortord);       //传排序方式的编号
		return new ActionForward.Forward("/jsp/user/during_conf_list.jsp");
	}
	
	/**
	 * 查询与自己有关的即将开始的会议
	 * @param titleOrHostName 会议主题或主持人
	 * wangyong
	 * 2013-3-5
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listWithUpcomingConf")
	public Object listWithUpcomingConf(@CParam("titleOrHostName")  String titleOrHostName, PageModel pageModel, HttpServletRequest request){
		List<ConfBase> confList = new ArrayList<ConfBase>();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		String sortField = request.getParameter("sortField");
		String sortord = request.getParameter("sortord");
		Integer rows = 0;
		rows = confService.countUpcomingConfList(titleOrHostName, currentUser, null);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows);
			confList = confService.listUpcomingConfList(titleOrHostName, pageModel, sortField, sortord, currentUser, currentSite, null);
			confList = ObjectUtil.parseHtmlWithList(confList, "confName");     //数据库的数据转为实际字符
		}else{
			pageModel.setRowsCount(0);
		}
		Map<Integer,Integer> participantSizeList = getParticipants(confList);   //获取每个会议的参会人个数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("user", currentUser); 
		request.setAttribute("confList", confList);
		request.setAttribute("titleOrHostName", titleOrHostName);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sortField", sortField);   //传排序字段的编号
		request.setAttribute("sortord", sortord);       //传排序方式的编号
		return new ActionForward.Forward("/jsp/user/upcoming_conf_list.jsp");
	}
	/**
	 * 查询自己错过的会议
	 * @param titleOrHostName 会议主题或主持人
	 * wangyong
	 * 2013-3-5
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listWithMissConf")
	public Object listWithMissConf(@CParam("titleOrHostName")  String titleOrHostName, PageModel pageModel, HttpServletRequest request){
		List<ConfBase> confList = new ArrayList<ConfBase>();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		String sortField = request.getParameter("sortField");
		String sortord = request.getParameter("sortord");
		Integer rows = 0;
		rows = confService.countMissConfList(titleOrHostName, currentUser, null, null);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows);
			confList = confService.listMissConfList(titleOrHostName, pageModel, sortField, sortord, currentUser, currentSite, null, null);
			confList = ObjectUtil.parseHtmlWithList(confList, "confName");     //数据库的数据转为实际字符
		}else{
			pageModel.setRowsCount(0);
		}
		Map<Integer,Integer> participantSizeList = getParticipants(confList);   //获取每个会议的参会人个数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("user", currentUser); 
		request.setAttribute("confList", confList);
		request.setAttribute("titleOrHostName", titleOrHostName);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sortField", sortField);   //传排序字段的编号
		request.setAttribute("sortord", sortord);       //传排序方式的编号
		return new ActionForward.Forward("/jsp/user/miss_conf_list.jsp");
	}
	/**
	 * 查询自己参加过的的会议
	 * @param titleOrHostName 会议主题或主持人
	 * wangyong
	 * 2013-3-5
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listWithAttendedConf")
	public Object listWithAttendedConf(@CParam("titleOrHostName")  String titleOrHostName, PageModel pageModel, HttpServletRequest request){
		List<ConfBase> confList = new ArrayList<ConfBase>();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		String sortField = request.getParameter("sortField");
		String sortord = request.getParameter("sortord");
		Integer rows = 0;
		rows = confService.countAttendedConfList(titleOrHostName, currentUser, null);
		if(rows != null && rows.intValue() > 0){
			pageModel.setRowsCount(rows);
			confList = confService.listAttendedConfList(titleOrHostName, pageModel, sortField, sortord, currentUser, currentSite, null);
			confList = ObjectUtil.parseHtmlWithList(confList, "confName");     //数据库的数据转为实际字符
		}else{
			pageModel.setRowsCount(0);
		}
		Map<Integer,Integer> participantSizeList = getParticipants(confList);   //获取每个会议的参会人个数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("user", currentUser); 
		request.setAttribute("confList", confList);
		request.setAttribute("titleOrHostName", titleOrHostName);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sortField", sortField);   //传排序字段的编号
		request.setAttribute("sortord", sortord);       //传排序方式的编号
		return new ActionForward.Forward("/jsp/user/attended_conf_list.jsp");
	}
	
	/**
	 * 查询会议详情
	 * @author Administrator darren
	 * @param id 会议id号
	 * 2013-3-5
	 */
	@AsController(path = "view/{confId:([0-9]+)}")
	@Get
	public Object viewConf(@CParam("confId") Integer confId,HttpServletRequest request){
		
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		UserBase currentUser = userService.getCurrentUser(request);
		ConfBase conf = null;
//		if(currentUser != null){
//			conf = confService.getConfBasebyConfId(confId, currentUser);
//		}else{
//			conf = confService.getConfBasebyConfId(confId, currentSite);
//		}
		
		conf = confService.getConfBasebyConfId(confId);
		if(currentUser == null){
			currentUser = userService.getUserBaseById(conf.getCreateUser());
		}
		conf.setStartTime(DateUtil.getOffsetDateByGmtDate(conf.getStartTime(), conf.getTimeZone().longValue()));
		conf.setEndTime(DateUtil.getOffsetDateByGmtDate(conf.getEndTime(), conf.getTimeZone().longValue()));
		confResourceAppend(conf, currentUser, currentSite, request);    //查询会议详情时拼接会议的资源
		
		String siteUrl =SiteIdentifyUtil.getCurrentDomine();    //获取当前域名
		//String tdcUrl = "http://"+SiteIdentifyUtil.getCurrentDomine()+"/join/gotoQccodePage?confId="+confId+"&hostflag=2";
		String tdcUrl = emailLogic.getQccodeURL(confId, ConfConstant.CONF_USER_PARTICIPANT);
		request.setAttribute("siteUrl", siteUrl);
		request.setAttribute("conf", conf);
		request.setAttribute("site", currentSite);
		request.setAttribute("user", currentUser);
		request.setAttribute("tdcUrl", tdcUrl);
		request.setAttribute("rcips", centerService.getRCIPs(currentUser.getDataCenterId()));
		return new ActionForward.Forward("/jsp/user/viewConf.jsp");
	}
	
	/**
	 * 复制会议详情
	 * 2014-9-10  oustin
	 */
	@AsController(path = "copyView/{confId:([0-9]+)}")
	@Get
	public Object copyViewConf(@CParam("confId") Integer confId,HttpServletRequest request){
		
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		UserBase currentUser = userService.getCurrentUser(request);
		ConfBase conf = null;
		
		conf = confService.getConfBasebyConfId(confId);
		conf.setStartTime(DateUtil.getOffsetDateByGmtDate(conf.getStartTime(), conf.getTimeZone().longValue()));
		conf.setEndTime(DateUtil.getOffsetDateByGmtDate(conf.getEndTime(), conf.getTimeZone().longValue()));
		
		confResourceAppend(conf, currentUser, currentSite, request);    //查询会议详情时拼接会议的资源
		
		String siteUrl =SiteIdentifyUtil.getCurrentDomine();    //获取当前域名
	
		String tdcUrl = emailLogic.getQccodeURL(confId, ConfConstant.CONF_USER_PARTICIPANT);
		request.setAttribute("siteUrl", siteUrl);
		request.setAttribute("conf", conf);
		request.setAttribute("site", currentSite);
		request.setAttribute("user", currentUser);
		request.setAttribute("tdcUrl", tdcUrl);
		request.setAttribute("rcips", centerService.getRCIPs(currentUser.getDataCenterId()));
		return new ActionForward.Forward("/jsp/user/copyViewConf.jsp");
	}

	
	/**
	 * 查询会议详情(站点管理员查看会议详情，所有数据均显示)
	 * wangyong
	 * @param id 会议id号
	 * 2013-11-15
	 */
	@AsController(path = "adminViewConf/{confId:([0-9]+)}")
	@Get
	public Object adminViewConf(@CParam("confId") Integer confId,HttpServletRequest request){
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		ConfBase conf = null;
		conf = confService.getConfBasebyConfId(confId, currentSite);
		if(conf == null){
			logger.info("查询会议详情(站点管理员查看会议详情，所有数据均显示),会议不存在,confid:" + confId);
			return null;
		}
		UserBase createUser = userService.getUserBaseById(conf.getCreateUser());
		confResourceAppend(conf, createUser, currentSite, request);    //查询会议详情时拼接会议的资源
		request.setAttribute("conf", conf);
		request.setAttribute("site", currentSite);
		request.setAttribute("user", createUser);
		//request.setAttribute("rcips", centerService.getRCIPs(createUser.getDataCenterId()));
		return new ActionForward.Forward("/jsp/admin/viewConf.jsp");
	}
	
	@AsController(path = "view4sys/{confId:([0-9]+)}")
	@Get
	public Object sysviewConf(@CParam("confId") Integer confId,HttpServletRequest request){
		logger.info("confId=="+confId);
		ConfBase conf = confService.getConfBasebyConfId(confId);
		SiteBase realSite = siteService.getSiteBaseById(conf.getSiteId());
		UserBase realUser = userService.getUserBaseById(conf.getCreateUser());
		String[] fields = new String[]{"startTime","endTime"};
		long offset = 0 ;
		if(realSite != null){
			offset = realSite.getTimeZone();
			realUser.setTimeZone(new Long(offset).intValue());
		}else{
			offset = DateUtil.getDateOffset();
		}
		conf = (ConfBase)ObjectUtil.offsetDate(conf, offset, fields);
		confResourceAppend(conf, realUser, realSite, request);    //查询会议详情时拼接会议的资源
		request.setAttribute("conf", conf);
		request.setAttribute("site", realSite);
		request.setAttribute("user", realUser);
		//request.setAttribute("rcips", centerService.getRCIPs(realUser.getDataCenterId()));
		return new ActionForward.Forward("/jsp/system/viewConf.jsp");
	}
	
	/**
	 * 查询会议详情时拼接会议周期信息的资源
	 */
	private void confResourceAppend(ConfBase conf, UserBase currentUser, SiteBase currentSite, HttpServletRequest request){
		if(conf.getCycleId() != null && conf.getCycleId().intValue() > 0){
			ConfCycle confCycle = confService.getConfCyclebyConfId(conf.getCycleId().intValue());
			long offset = 0 ;
			if(currentUser != null){
				offset = currentUser.getTimeZone();
			}else{
				offset = currentSite.getTimeZone();
			}
			//定期模式：按月（6号；第2个周一）
			StringBuilder cycleMode = new StringBuilder("");
			if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_MONTHLY.intValue()){      //按月循环的
				if(confCycle.getCycleValue().indexOf(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT)>0){     //有分号的
					String[] monthValueArray = confCycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT);
					String week = ResourceHolder.getInstance().getResource("system.month." + monthValueArray[1]);
					cycleMode.append(String.format(ResourceHolder.getInstance().getResource("system.by.month.week"), monthValueArray[0], week));   // 格式化字符串，按月(每月第几个周几)
				}else{
					cycleMode.append(String.format(ResourceHolder.getInstance().getResource("system.by.month.day"), confCycle.getCycleValue()));   // 格式化字符串，按月(每月第几天)
				}
			}else if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_DAILY.intValue()){    //按日循环的
				cycleMode.append(String.format(ResourceHolder.getInstance().getResource("system.by.day"), confCycle.getCycleValue()));      // 格式化字符串，按日(每几天)
			}else if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_WEEKLY.intValue()){   //按周循环的
				StringBuilder week = new StringBuilder();
				String lang = LanguageHolder.getCurrentLanguage();
				if("zh-cn".equals(lang)){
					for(String weekValue : confCycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)){
						week.append(ResourceHolder.getInstance().getResource("system.month." + weekValue)).append(",");
					}
				}else{
					for(String weekValue : confCycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)){
						week.append(ResourceHolder.getInstance().getResource("system.month." + weekValue));
					}
				}
				cycleMode.append(String.format(ResourceHolder.getInstance().getResource("system.by.week"), 
						week.toString()));      // 格式化字符串，按周（每周几）
			}
			StringBuilder repeatScope = new StringBuilder("");    //重复范围
			if(confCycle.getRepeatCount() != null && confCycle.getRepeatCount().intValue() > 0){
//				repeatScope.append(String.format("重复%s次后结束", confCycle.getRepeatCount().intValue()));
				repeatScope.append(String.format(ResourceHolder.getInstance().getResource("website.user.conf.info.repeat.time"), confCycle.getRepeatCount().intValue()));
			}else if(confCycle.getInfiniteFlag() != null && confCycle.getInfiniteFlag().intValue() == 1){
//				repeatScope.append("无结束日期");
				repeatScope.append(ResourceHolder.getInstance().getResource("website.user.conf.info.no.endtime"));
			}else{
//				repeatScope.append("结束日期" + " " + DateUtil.getDateStrCompact(confCycle.getEndDate(), "yyyy-MM-dd"));
				repeatScope.append(ResourceHolder.getInstance().getResource("website.user.conf.info.endtime") + " " + DateUtil.getDateStrCompact(confCycle.getEndDate(), "yyyy-MM-dd"));
			}
			request.setAttribute("repeatScope", repeatScope);
			request.setAttribute("cycleMode", cycleMode.toString());
			request.setAttribute("confCycle", (ConfCycle)ObjectUtil.offsetDate(confCycle, offset, new String[]{"beginDate","endDate"}));
		}
		int duraH = conf.getDuration()/60;
		int duraM = conf.getDuration()%60;
		String duration = "";
		if(duraH >= 1){
			duration = String.valueOf(duraH) + ResourceHolder.getInstance().getResource("user.menu.conf.hour");
			if(duraH > 1){
				duration = String.valueOf(duraH) + ResourceHolder.getInstance().getResource("user.menu.conf.hours");
			}
		}
		if(duraM > 0){
			duration += " " + String.valueOf(duraM) + ResourceHolder.getInstance().getResource("user.menu.conf.minute");
		}
		 
		request.setAttribute("inviteUserCount", confService.countConfUserByConfId(conf.getId()));
		
		//主持人加入地址
		String hostUrl = UrlUtils.getJionUrl(conf, ConfConstant.HOST_JOIN_TYPE_EMAIL);
		//参会者用户加入地址
		String userUrl = UrlUtils.getJionUrl(conf, ConfConstant.USER_JOIN_TYPE_EMAIL);
		
		request.setAttribute("hostUrl",hostUrl);
		request.setAttribute("userUrl", userUrl);
//		request.setAttribute("hostUrl", emailService.getJoinUrlForHost(conf));
//		request.setAttribute("userUrl", emailService.getJoinUrlForUser(conf));
		request.setAttribute("duration", duration);
	}
	
	/**
	 * 加入会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "attendConf")
	public Object attendConf(@CParam("confId") Integer confId,HttpServletRequest request){
		return new ActionForward.Forward("/jsp/user/attendConf.jsp");
	}
	
	/**
	 * 主持人创建即时会议(跳转页面)
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "createImmediatelyConf")
	@Interceptors({UserInterceptor.class})
	@Get
	public Object createImmediatelyConf(HttpServletRequest request) throws Exception{
		UserBase currentUser = userService.getCurrentUser(request);
//		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		request.setAttribute("confNameDefault", new StringBuilder(currentUser.getTrueName()).append(ResourceHolder.getInstance().getResource("user.menu.conf.who.conf")).toString());
		return new ActionForward.Forward("/jsp/user/create_Immediately_Conf.jsp");
	}
	
	/**
	 * 主持人创建即时会议
	 * 
	 * @param 	conf 包含会议主题、密码、主持人加入会议选项、视频开启
	 * @author 	darren
	 * @date  	2014-6-16
	 */
	@AsController(path = "createNewImmediatelyConf")
	@Post
	public Object createNewImmediatelyConf(ConfBase conf, HttpServletRequest request){
		ConfBase confBase = null;
		//取到当前用户和当前用户所在的站点信息
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		if(conf != null){//书写了会议主题
			//判断当前企业用户是否为主持人角色
			if(currentUser.isConfHost()){
				
				ZoomDefaultConfig config = zoomDefaultConfigService.getUserDefaultConfig(currentUser.getId());
				conf.setOptionJbh(config.getOptionJbh());
				conf.setOptionStartType(config.getOptionStartType());
				conf.setTimeZone(currentUser.getTimeZone());
				conf.setTimeZoneId(currentUser.getTimeZoneId());
				conf.setCompereUser(currentUser.getId());
				
				confBase = confService.createImmediatelyConf(conf, currentSite, currentUser);
				//创会成功
				if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_IMMEDIATELY_CREATE, "当前企业用户创建即时会议成功",
							EventLogConstants.EVENTLOG_SECCEED, confBase, request);   //创建成功后写EventLog
					JSONObject json = new JSONObject();
					json.put("status", ConstantUtil.CREATE_CONF_SUCCEED);
					json.put("id", confBase.getId().intValue());
					return json.toString();
				}
				//创会失败
				else{
					String errorMessage = getConfErrorMessage(confBase);
					 //ResourceHolder.getInstance().getResource("system.site.meaasge.create.succeed"), 
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_IMMEDIATELY_CREATE, "创建即时会议失败"+ ":" + errorMessage,
							EventLogConstants.EVENTLOG_FAIL, confBase, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.confnow.failed")+ errorMessage);
				}
			}else{//非主持人不可以创会
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_IMMEDIATELY_CREATE, "当前企业用户无权限创建即时会议",
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.confnow.create.not"));
			}
		}else{//会议主题空创建失败
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_IMMEDIATELY_CREATE, "当前企业用户创建即时会议失败",
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.confnow.failed"));
		}
	}
	
	/**
	 * 主持人创建预约会议(跳转页面)
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "createReservationConf")
	@Interceptors({UserInterceptor.class})
	@Get
	public Object createReservationConf(HttpServletRequest request) throws Exception{
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		//当前用户喜好设置时区的时间
		Date curDate=currentUser.getUserLocalTime();
//		Date curDate=currentSite.getSiteLocalTime();
		 
 
		//
		ZoomDefaultConfig config = zoomDefaultConfigService.getUserDefaultConfig(currentUser.getId());
		request.setAttribute("config", config);
		request.setAttribute("currentUser", currentUser);
		
//		EmpowerConfig userEmpower = empowerConfigService.makeEmpowerForConf(currentUser);   //获取用户创建会议，缺省会议设置的权限
//		setEmpowerFlag(request, userEmpower);    //将用户权限flag传递到前台
//		request.setAttribute("userEmpower", userEmpower);
//		request = confService.confConfigAttr(confConfig, request);
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("siteBase", currentSite);
//		request.setAttribute("defaultLicence", licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());    //最大参会人数
		
		return new ActionForward.Forward("/jsp/user/create_Reservation_Conf.jsp");
	}

	
	/**
	 * 主持人创建预约会议
	 * @author 	Administrator darren
	 * @date 	2014-06-17
	 */
	@AsController(path = "createNewReservationConf")
	@Post
	public Object createNewReservationConf(ConfBase conf, UserBase userBase, ConfCycle confCycle, HttpServletRequest request){
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		ConfBase confBase = null;
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		JSONArray jsonArrConfCycle = new JSONArray();
		String errorMessage = "";
		
		/** userBase是为了获取时区的相关参数 */
//		conf.setTimeZone(getTimeZone(userBase.getTimeZoneId()));
//		conf.setTimeZoneId(userBase.getTimeZoneId());
//		conf.setTimeZoneSecond(getTimeZoneGMT(userBase.getTimeZoneId()));
		
		if(conf != null){
			
			String isCycleConf = request.getParameter("cycleOption");
			
			//判断当前企业用户是否为主持人角色
			if(currentUser.isConfHost()){
				//循环    	isCycleConf = 1(周期会议)
				if(StringUtil.isNotBlank(isCycleConf) && IntegerUtil.parseIntegerWithDefaultZero(isCycleConf).intValue() == ConfConstant.CONF_CYCLE_TRUE.intValue()){
					//预约周期会议
					confBase = confService.createCycleReservationConf(conf, confCycle, currentSite, currentUser);
					if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
						eventLogService.saveUserEventLog(currentUser, 
								EventLogConstants.SITEUSER_CONF_CYCLE_CREATE, "创建周期预约会议成功",
								EventLogConstants.EVENTLOG_SECCEED, confBase, request);   //创建成功后写EventLog
					}else{
						errorMessage = getConfErrorMessage(confBase);
						eventLogService.saveUserEventLog(currentUser, 
								EventLogConstants.SITEUSER_CONF_CYCLE_CREATE, "创建周期预约会议失败" + errorMessage,
								EventLogConstants.EVENTLOG_FAIL, confBase, request);   //创建失败后写EventLog
						return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.confcycle.failed") + errorMessage);
					}
				}else{//不循环	isCycleConf = 2
					 
					conf.setDuration(conf.getDuration());
					confBase = confService.createSingleReservationConf(conf, currentSite, currentUser,true);
					
					if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
						eventLogService.saveUserEventLog(currentUser, 
								EventLogConstants.SITEUSER_CONF_RESERVATION_CREATE, "创建预约会议成功",
								EventLogConstants.EVENTLOG_SECCEED, confBase, request);   //创建成功后写EventLog
					}else{
						errorMessage = getConfErrorMessage(confBase);
						eventLogService.saveUserEventLog(currentUser, 
								EventLogConstants.SITEUSER_CONF_RESERVATION_CREATE, "创建预约会议失败" + errorMessage,
								EventLogConstants.EVENTLOG_FAIL, confBase, request);   //创建失败后写EventLog
						return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.conf.failed") + errorMessage);
					}
				}
			}else{//非主持人
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_RESERVATION_CREATE, "当前企业用户无权限创建预约会议",
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.conf.failed"));
			}
		}else{//创建预约会议失败
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_RESERVATION_CREATE, "创建预约会议失败",
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.CREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.create.conf.failed"));
		}
		json.put("status", ConstantUtil.CREATE_CONF_SUCCEED);
		jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
									//返回字段中添加时区
		jsonArrConfCycle.add(JsonUtil.parseJsonWithFullFieldByObject(confCycle));
		json.put("timeZoneDesc", confBase.getTimeZoneDesc());
		json.put("confBase", jsonArrConf);
		json.put("confCycle", jsonArrConfCycle);
		
		return json.toString();
	}
	

	/**
	 * 主持人修改预约会议(跳转页面)
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "update/{confId:([0-9]+)}")
	@Get
	public Object updateConf(@CParam("confId") Integer confId, HttpServletRequest request) throws Exception{
		logger.info("confId=="+confId);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		ConfBase conf = confService.getConfBasebyConfId(confId);
		if(conf != null){
			String[] fields = new String[]{"startTime","endTime"};
			long offset = 0 ;
			if(conf.getTimeZone() != null){
				offset = conf.getTimeZone();
			}else{
				offset = currentSite.getTimeZone();
			}
			logger.info("当前访问的站点时区" + offset);
			conf = (ConfBase)ObjectUtil.offsetDate(conf, offset, fields);
		}
		//当前站点时区的时间
		Date curDate=conf.getConfLocalTime();
//		Date curDate=currentSite.getSiteLocalTime();
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("siteBase", currentSite);
		request.setAttribute("defaultLicence", licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());    //最大参会人数
		
		
//		DefaultConfig confConfig = new DefaultConfig();
//		DefaultConfig curDefaultConfig = confService.getDefaultConfig(currentUser);
//		request.setAttribute("defaultConfig", curDefaultConfig);
//		confConfig.setPriviBits(conf.getPriviBits());
//		confConfig.setClientConfig(conf.getClientConfig());
//		confConfig.setFuncBits(conf.getFuncBits());
//		conf = confService.checkConfFunc(conf, currentUser);
		//request = confService.confConfigAttr(confConfig, request);
 
		request.setAttribute("conf", conf);
		return new ActionForward.Forward("/jsp/user/create_Reservation_Conf.jsp");
	}
	
	@AsController(path = "toUpdatePmi/{confId:([0-9]+)}")
	public Object toUpdatePmi(@CParam("confId") Integer confId, HttpServletRequest request) throws Exception{
		//logger.info("confId=="+confId);
		ConfBase conf = confService.getConfBasebyConfId(confId);
		if(conf==null){
			UserBase currentUser = userService.getCurrentUser(request);
			conf = userService.getUserMeetingRoom(currentUser);
		}
		request.setAttribute("conf", conf);
		return new ActionForward.Forward("/jsp2.0/user/update_pmi.jsp");
	}
	
	
	@AsController(path = "updatePmi")
	public Object updatePmi(ConfBase conf, HttpServletRequest request) throws Exception{
		//logger.info("confId=="+confId);
		int status = 10;
		UserBase currentUser = userService.getCurrentUser(request);
		if(!confService.updateUserPMI(conf, currentUser)){
			 status = 11;
		}
		return returnJsonStr(status, "");
	}
	
	@AsController(path = "toUpdateRecurrConf/{confId:([0-9]+)}")
	public Object toUpdateRecurrConf(@CParam("confId") Integer confId, HttpServletRequest request) throws Exception{
		//logger.info("confId=="+confId);
		ConfBase conf = confService.getConfBasebyConfId(confId);
		if(conf==null){
			UserBase currentUser = userService.getCurrentUser(request);
			conf = userService.getUserMeetingRoom(currentUser);
		}
		request.setAttribute("conf", conf);
		return new ActionForward.Forward("/jsp2.0/user/update_recurr.jsp");
	}
	
	@AsController(path = "updateRecurrConf")
	public Object updateRecurrConf(ConfBase conf, HttpServletRequest request) throws Exception{
		int status = 10;
		UserBase currentUser = userService.getCurrentUser(request);
		if(!confService.updateUserPMI(conf, currentUser)){
			 status = 11;
		}
		return returnJsonStr(status, "");
	}
	
	/**
	 * 主持人修改周期预约会议中所有会议的信息(跳转页面)
	 * @param confId 会议Id
	 * @param cycleConfType 修改周期会议的标记：1是修改周期会议全部 2是重新创建 3是修改周期会议单次
	 * 2013-3-5
	 */
	@AsController(path = "updateCycleConfInfo/{confId:([0-9]+)}")
	@Get
	public Object toUpdateCycleConfInfo(@CParam("confId") Integer confId,@CParam("cycleConfType") Integer type,
			HttpServletRequest request) throws Exception{
		logger.info("confId=="+confId);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		UserBase currentUser = userService.getCurrentUser(request);
		ConfBase conf = confService.getConfBasebyConfId(confId);
		ConfCycle confCycle = confService.getConfCyclebyConfId(conf.getCycleId());
		if(conf != null){
			String[] fields = new String[]{"startTime","endTime"};
			long offset = 0 ;
			if(conf.getTimeZone() != null){
				offset = conf.getTimeZone();
			}else{
				offset = currentSite.getTimeZone();
			}
			logger.info("当前访问的站点时区" + offset);
			conf = (ConfBase)ObjectUtil.offsetDate(conf, offset, fields);
		}
		//当前站点时区的时间
		Date curDate = conf.getConfLocalTime();
//		Date curDate=currentSite.getSiteLocalTime();
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("siteBase", currentSite);
//		request.setAttribute("cycleConfType", 1);
		request.setAttribute("cycleConfType", type);
		request.setAttribute("defaultLicence", licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());    //最大参会人数
		setConfCycleRequest(request, confCycle);
		
//		DefaultConfig confConfig = new DefaultConfig();
//		confConfig.setPriviBits(conf.getPriviBits());
//		confConfig.setClientConfig(conf.getClientConfig());
//		confConfig.setFuncBits(conf.getFuncBits());
//		request = confService.confConfigAttr(confConfig, request);
//		DefaultConfig curDefaultConfig = confService.getDefaultConfig(currentUser);
//		request.setAttribute("defaultConfig", curDefaultConfig);
		
//		//根据站点ID号取站点的全局变量设置
//		EmpowerConfig sitePower  = empowerConfigService.getSiteEmpowerGlobalBySiteId(currentUser.getSiteId());
//		//根据用户的ID号取用户的授权配置(站点管理员创建企业用户时的授权配置)
//		EmpowerConfig userPower  = empowerConfigService.getUserEmpowerConfigByUserId(currentUser.getId());
//		if(sitePower != null && userPower != null){
//			//站点全局变量“媒体共享”启用并且用户“媒体共享”授权启用后，创建会议才可以选择“媒体共享”项
//			if(sitePower.getShareMediaFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getShareMediaFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isShareMediaFlag", SiteConstant.EMPOWER_ENABLED);      
//			}
//			if(sitePower.getRecordFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getRecordFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isRecordFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//			if(sitePower.getPhoneFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getPhoneFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isPhoneFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//			if(sitePower.getAutoFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getAutoFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isAutoFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//		}
	//	EmpowerConfig userEmpower = empowerConfigService.makeEmpowerForConf(currentUser);   //获取用户创建会议，缺省会议设置的权限
		//setEmpowerFlag(request, userEmpower);    //将用户权限flag传递到前台
		request.setAttribute("conf", conf);
		request.setAttribute("confCycle", confCycle);
		//request.setAttribute("userEmpower", userEmpower);
		return new ActionForward.Forward("/jsp/user/create_Reservation_Conf.jsp");
	}
	
	/**
	 * 传给前台周期循环规则参数
	 * shhc
	 * 2013-4-17
	 */
	private void setConfCycleRequest(HttpServletRequest request, ConfCycle confCycle){
		int cycleType = confCycle.getCycleType();
		String cycleValue = confCycle.getCycleValue();
		if(ConfConstant.CONF_CYCLE_DAILY.intValue() == cycleType){
			request.setAttribute("cycleDayValue", cycleValue);
		}
		if(ConfConstant.CONF_CYCLE_WEEKLY.intValue() == cycleType){
			request.setAttribute("cycleWeekValue", cycleValue);
		}
		if(ConfConstant.CONF_CYCLE_MONTHLY.intValue() == cycleType){
			if(cycleValue.indexOf(";") == -1){
				request.setAttribute("monthCycleOption", 1);
				request.setAttribute("eachMonthDay", cycleValue);
			}else{
				request.setAttribute("monthCycleOption", 2);
				String[] cycleValueArray = cycleValue.split(";"); 
				request.setAttribute("weekFlag", cycleValueArray[0]);
				request.setAttribute("weekDay", cycleValueArray[1]);
			}
		}
		request.setAttribute("cycleType", cycleType);
	}
	
	
	/**
	 * 主持人修改周期预约会议
	 * 
	 * 2013-3-5
	 */
	@AsController(path = "updateCycleConfInfo")
	public Object updateCycleConfInfo(ConfBase conf, UserBase userBase, ConfCycle cycle,  HttpServletRequest request){
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		Integer confId = conf.getId();
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		JSONArray jsonArrConfCycle = new JSONArray();
		String optDescLog = "";
		ConfBase confBase = null;
		ConfBase confBaseNew = null;
		ConfCycle confCycle = null;
		//获取修改后的周期
		ConfCycle confCycleNew = null;
		//未更改前的会议数据
		ConfBase oldConf = null;
		if(conf != null && confId != null){
			//判断当前企业用户是否有权限修改预约会议
			boolean userRole = confService.updateConfAuthority(confId, currentUser);
			if(userRole){
				List<Object[]> objectChangeList = new ArrayList<Object[]>();   //修改会议时保存会议信息的变化。
				ConfBase dbConf = confService.getConfBasebyConfId(confId);
				
				//未更改前的会议数据
				oldConf = dbConf.clone();
				
				dbConf.setConfName(conf.getConfName());
				dbConf.setConfDesc(conf.getConfDesc());
				dbConf.setOptionJbh(conf.getOptionJbh());
				dbConf.setOptionStartType(conf.getOptionStartType());
				dbConf.setHostKey(conf.getHostKey());
				dbConf.setTimeZone(conf.getTimeZone());
				dbConf.setTimeZoneId(conf.getTimeZoneId());
				dbConf.setDuration(conf.getDuration());
				
				if(dbConf != null && dbConf.getCycleId() != null){//查询周期会议要修改的会议周期
					confCycle = confService.getConfCyclebyConfId(dbConf.getCycleId());
					if(confCycle == null) confCycle = new ConfCycle();
					// 时区设置
					Integer timeZone = dbConf.getTimeZone();
					confCycle.setInfiniteFlag(cycle.getInfiniteFlag());
					confCycle.setRepeatCount(cycle.getRepeatCount());
					confCycle.setCycleType(cycle.getCycleType());
					confCycle.setCycleValue(cycle.getCycleValue());
					confCycle.setBeginDate(cycle.getBeginDate());
					confCycle.setEndDate(cycle.getEndDate());
					confCycle.setCreateUser(cycle.getCreateUser());
					confCycleNew = confService.updateCycleByCycleId(confCycle,dbConf,userBase,currentSite,timeZone);
					//confCycle.getOffsetConf(confCycle, timeZone);
				}
				//修改全部会议的时间根据，开始时间进行调整下次会议时间
				Date nextStartDate = confLogic.getNextConfStartTime(confCycleNew,dbConf.getTimeZone());
//				Date nextStartDate = confLogic.getNextConfStartTime(dbConf.getStartTime(),confCycleNew,dbConf.getTimeZone());
				if(nextStartDate == null){
					json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
					//"周期范围内没有有效的会议时间"
					json.put("message", ResourceHolder.getInstance().getResource("com.vcaas.portal.user.cycleconf.noyfoundcycle"));
					return json.toString();
				}
				nextStartDate = DateUtil.getGmtDateByTimeZone(nextStartDate, dbConf.getTimeZone());
				dbConf.setStartTime(nextStartDate);
				dbConf.setEndTime(new Date(nextStartDate.getTime()+dbConf.getDuration()*60*1000l));
				
				//修改zoom中的会议信息
				boolean optionJbh = (dbConf.getOptionJbh() == 0) ? false: true;
				String optionStartType = "video";
				if (dbConf.getOptionStartType() == 2) {
					optionStartType = "screen_share";
				}
				
				//Add by Darren 2014-12-24
				DataCenter dataCenter = centerService.queryDataCenterById(currentUser.getDataCenterId());
				
				int status = zoomMeetingOperator.updateMeeting(dataCenter.getApiKey(),dataCenter.getApiToken(),dbConf.getConfZoomId(), currentUser.getZoomId(), 
						dbConf.getConfName(), dbConf.getZoomStartTime(), dbConf.getDuration(), dbConf.getTimeZoneSecond(),
						dbConf.getHostKey(), optionJbh, optionStartType);
				if(status == ZoomConfStatus.UPDATE_SUCCESS){
					//get H323 password ,add by darren 2015-01-30 
					Map<String, Object> retMap = zoomMeetingOperator.getMeeting(dataCenter.getApiKey(), dataCenter.getApiToken(), dbConf.getConfZoomId(), currentUser.getZoomId());
					if(retMap.get("error") == null){
						if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
							dbConf.setPhonePass((String)retMap.get("h323Password"));
						}
					}
					confBase = confService.saveOrUpdateConf(dbConf);
					confBaseNew = confBase.clone();
				}
				
				//修改会议成功
				if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
					ConfBase conf4Email = confBase.clone();
					confBase.setStartTime(DateUtil.getOffsetDateByGmtDate(confBase.getStartTime(), confBase.getTimeZone().longValue()));
					json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
					jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
					
					
					jsonArrConfCycle.add(JsonUtil.parseJsonWithFullFieldByObject(confCycleNew));
					json.put("confBase", jsonArrConf);
					json.put("timeZoneDesc", confBase.getTimeZoneDesc());//创建会议返回时区
					json.put("confCycle", jsonArrConfCycle);
				
					//发送修改提醒
					List<ConfUser> users = confUserService.getAllConfUserList(confBase.getId(),1);
					emailService.confModifyEmail(users, conf4Email);
					
					
					ConfBase newConf = confService.getConfBasebyConfId(confId, currentUser);
					objectChangeList.add(new Object[]{oldConf, newConf, new String[]{"id, siteName"}});   
					optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_SECCEED, conf, request);   //创建成功后写EventLog
				}else{
					json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
					
					String errorMessage = getConfErrorMessage(confBase);
					objectChangeList.add(new Object[]{oldConf, conf.getOffsetConf(currentUser, conf), new String[]{"id, siteName"}});   
					optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed") + errorMessage);
				}
			}else{
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_UPDATE, "当前企业用户无权限修改预约会议",
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
			}
		}else{
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_UPDATE, "当前企业用户修改预约会议失败",
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
		}
		
		//判断周期会议的重复周期、周期范围和会议时间是否被修改
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String beginD = sdf.format(confCycle.getBeginDate());
		String endD = sdf.format(confCycle.getEndDate());
		if(!beginD.equals(sdf.format(confCycleNew.getBeginDate())) || !endD.equals(sdf.format(confCycleNew.getEndDate()))
				|| confCycle.getCycleType().intValue() != confCycleNew.getCycleType().intValue()
				|| !confCycle.getCycleValue().equals(confCycleNew.getCycleValue()) 
				|| confCycle.getRepeatCount().intValue() != confCycleNew.getRepeatCount().intValue()
				|| confCycle.getInfiniteFlag().intValue() != confCycleNew.getInfiniteFlag().intValue()
				|| !sdf.format(oldConf.getStartTime()).equals(sdf.format(confBaseNew.getStartTime()))
				|| oldConf.getTimeZoneId().intValue() != confBaseNew.getTimeZoneId().intValue()){
			
			
			List<ConfUser> users = confUserService.getAllConfUserList(conf.getId(),0);// 0 是已发送短信邀请的人
			confBase.setStartTime(DateUtil.getGmtDate(confBase.getStartTime()));
			smsComponent.sendMsgInviteToConfUser(users, confBase,3,currentUser);//3 表示修改会议 
		}
		
		return json.toString();
	}
	
	/**
	 * 主持人修改周期预约会议中的一条会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "updateSingleCycleConfInfo")
	public Object updateSingleCycleConfInfo(ConfBase conf, HttpServletRequest request){
		ConfBase confBase = null;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		Integer confId = conf.getId();
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		String optDescLog = "";
		if(conf != null && confId != null){
			//判断当前企业用户是否有权限修改即时会议
			boolean userRole = confService.updateConfAuthority(confId, currentUser);
			if(userRole){
				List<Object[]> objectChangeList = new ArrayList<Object[]>();   //修改会议时保存会议信息的变化。
				ConfBase oldConf = confService.getConfBasebyConfId(confId, currentUser);
				 
				
				confBase = confService.updateSingleCycleConfInfo(conf, currentSite, currentUser);
				confBase.getOffsetConf(currentUser, confBase);
				if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
					ConfBase newConf = confService.getConfBasebyConfId(confId, currentUser);
					objectChangeList.add(new Object[]{oldConf, newConf, new String[]{"id, siteName"}});   
					optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_SECCEED, conf, request);   //创建成功后写EventLog
				}else{
					String errorMessage = getConfErrorMessage(confBase);
					objectChangeList.add(new Object[]{oldConf, conf.getOffsetConf(currentUser, conf), new String[]{"id, siteName"}});   
					optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed") + errorMessage);
				}
			}else{
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_UPDATE, "当前企业用户无权限修改预约会议",
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
			}
		}else{
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_UPDATE, "当前企业用户修改预约会议失败",
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
		}
		json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
		jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
		json.put("confBase", jsonArrConf);
		return json.toString();
	}
	
	/**
	 * 主持人修改单次预约会议
	 * @author Administrator darren
	 * 2014-06-18
	 */
	@AsController(path = "updateConfInfo")
	public Object updateConfInfo(ConfBase conf, HttpServletRequest request){
		ConfBase confBase = null;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		Integer confId = conf.getId();
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		String optDescLog = "";
		ConfBase oldConf = null;
		if(conf != null && confId != null){
//			oldConf = confService.getConfBasebyConfId(confId, currentUser);
			oldConf = confService.getConfBasebyConfId(confId);
			List<Object[]> objectChangeList = new ArrayList<Object[]>();   //修改会议时保存会议信息的变化。
			//判断当前企业用户是否有权限修改即时会议
			boolean userRole = confService.updateConfAuthority(confId, currentUser);
			if(userRole){
//				DefaultConfig confConfig = confService.getDefaultConfig(currentUser);
//				if(confConfig == null || confConfig.getId() == null){    //若当前用户无默认会议设置，则新建一条会议设置
//					confConfig = confService.saveDefaultConfig(currentUser);
//				}
//				String funcBits = confService.getFuncBits(conf, request, confConfig);
//				String priviBits = confService.getPriviBits(request, confConfig);
//				String clientConfig = confService.getClientConfig(request, confConfig);
//				conf.setFuncBits(funcBits);
//				conf.setPriviBits(priviBits);
//				conf.setClientConfig(clientConfig);
//				conf = confService.checkConfFunc(conf, currentUser);
//				if(conf.isBelongPermanentConf()){
//					confBase = confService.updatePermanentConf(conf, currentSite, currentUser);
//				}else{
					//修改单次次预约会议(返回的StartTimeGmt是标准的GMT时间)
					confBase = confService.updateSingleReservationConf(conf, currentSite, currentUser);
					//根据当前会议时区设置当前时间
//					confBase.setStartTime(DateUtil.getOffsetDateByGmtDate(confBase.getStartTime(), confBase.getTimeZone().longValue()));
//				}
				objectChangeList.add(new Object[]{oldConf, confBase, new String[]{"id, siteName"}});   //confBase是newConf
				optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
				
				logger.info("what the optDescLog content is ? "+optDescLog);
				if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_SECCEED, conf, request);   //创建成功后写EventLog
				}else{
					String errorMessage = getConfErrorMessage(confBase);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
							EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed") + errorMessage);
				}
			}else{
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
			}
		}else{
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_UPDATE, optDescLog,
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.UPDATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.update.confcycle.failed"));
		}
		
		//判断会议的时间是否被修改
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String NewTime = sdf.format(confBase.getStartTime());
		//原来会议的GMT开始时间
//		Date oldDate = DateUtil.getGmtDateByTimeZone(oldConf.getStartTime(), currentUser.getTimeZone());
		Date oldDate = DateUtil.getOffsetDateByGmtDate(oldConf.getStartTime(), oldConf.getTimeZone().longValue());
		
		//将会议设置成当前会议时区的开始时间
		String oldTime = sdf.format(oldDate);
		
		if(!oldTime.equals(NewTime)){//被修改发送短信通知
			ConfBase smsConfBase = confBase.clone();
			smsConfBase.setStartTime(DateUtil.getGmtDateByTimeZone(smsConfBase.getStartTime(), smsConfBase.getTimeZone()));
			List<ConfUser> users = confUserService.getAllConfUserList(conf.getId(),0);// 0 是已发送短信邀请的人
			smsComponent.sendMsgInviteToConfUser(users, smsConfBase,3,currentUser);//3 表示修改会议 
		}
		json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
		jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
		json.put("confBase", jsonArrConf);
		//创建会议返回时区
		json.put("timeZoneDesc", confBase.getTimeZoneDesc());
		return json.toString();
	}
	
	/**
	 * 重新创建会议(跳转页面)
	 * 1.如果已错过的会议为自己创建的，可以重新创建该会议
	 * 2.如果已参加过的会议为自己创建的，可以重新创建该会议
	 * 3.只能创建单次会议，不可创建周期会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "reCreateconf/{confId:([0-9]+)}")
	@Get
	public Object reCreateconf(@CParam("confId") Integer confId, HttpServletRequest request){
		logger.info("confId=="+confId);
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		ConfBase conf = confService.getConfBasebyConfId(confId, currentUser);
		if(conf != null && currentUser != null){
			if(conf.getCreateUser() != null && conf.getCreateUser().intValue() != currentUser.getId()){
				setErrMessage(request,  ResourceHolder.getInstance().getResource("bizconf.jsp.user.conf.recreate.error"));
				return false;
			}
		}
		//当前用户喜好设置时区的时间
		Date curDate=currentUser.getUserLocalTime();
//		Date curDate=currentSite.getSiteLocalTime();
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("siteBase", currentSite);
		
		/*
		 * cycleConfType:
		 * 值1：修改循环会议中的所有会议 ;
		 * 值2：重新创建会议
		 */
		request.setAttribute("cycleConfType", 2);
		request.setAttribute("defaultLicence", licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());    //最大参会人数
		
//		DefaultConfig confConfig = new DefaultConfig();
//		confConfig.setPriviBits(conf.getPriviBits());
//		confConfig.setClientConfig(conf.getClientConfig());
//		confConfig.setFuncBits(conf.getFuncBits());
//		request = confService.confConfigAttr(confConfig, request);
//		DefaultConfig curDefaultConfig = confService.getDefaultConfig(currentUser);
//		request.setAttribute("defaultConfig", curDefaultConfig);
		
		
		request.setAttribute("conf", conf);
//		//根据站点ID号取站点的全局变量设置
//		EmpowerConfig sitePower  = empowerConfigService.getSiteEmpowerGlobalBySiteId(currentUser.getSiteId());
//		//根据用户的ID号取用户的授权配置(站点管理员创建企业用户时的授权配置)
//		EmpowerConfig userPower  = empowerConfigService.getUserEmpowerConfigByUserId(currentUser.getId());
//		if(sitePower != null && userPower != null){
//			//站点全局变量“媒体共享”启用并且用户“媒体共享”授权启用后，创建会议才可以选择“媒体共享”项
//			if(sitePower.getShareMediaFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getShareMediaFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isShareMediaFlag", SiteConstant.EMPOWER_ENABLED);      
//			}
//			if(sitePower.getRecordFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getRecordFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isRecordFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//			if(sitePower.getPhoneFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getPhoneFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isPhoneFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//			if(sitePower.getAutoFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue() && userPower.getAutoFlag().intValue() == SiteConstant.EMPOWER_ENABLED.intValue()){
//				request.setAttribute("isAutoFlag", SiteConstant.EMPOWER_ENABLED);
//			}
//		}
//		EmpowerConfig userEmpower = empowerConfigService.makeEmpowerForConf(currentUser);   //获取用户创建会议，缺省会议设置的权限
//		setEmpowerFlag(request, userEmpower);    //将用户权限flag传递到前台
//		request.setAttribute("userEmpower", userEmpower);
		
		return new ActionForward.Forward("/jsp/user/create_Reservation_Conf.jsp");
	}
	
	/**
	 * 重新创建会议
	 * 1.如果已错过的会议为自己创建的，可以重新创建该会议
	 * 2.如果已参加过的会议为自己创建的，可以重新创建该会议
	 * 3.只能创建单次会议，不可创建周期会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "reCreateconfInfo")
	@Post
	public Object reCreateconfInfo(ConfBase conf, HttpServletRequest request){
		ConfBase confBase = null;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		Integer confId = conf.getId();
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		if(conf != null && confId != null){
			//判断当前企业用户是否有权限修改即时会议
			boolean userRole = confService.recreateConfAuthority(confId, currentUser);
			if(userRole){
				 
				confBase = confService.reCreateconf(conf, currentSite, currentUser);
				if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_RECREATE, "重新预约会议成功",
							EventLogConstants.EVENTLOG_SECCEED, conf, request);   //创建成功后写EventLog
				}else{
					String errorMessage = getConfErrorMessage(confBase);
					eventLogService.saveUserEventLog(currentUser, 
							EventLogConstants.SITEUSER_CONF_RECREATE, "重新创建会议失败" + errorMessage,
							EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.RECREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.recreate.conf.failed") + errorMessage);
				}
			}else{
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_RECREATE, "当前企业用户无权限重新创建会议",
						EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
				return returnJsonStr(ConstantUtil.RECREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.recreate.conf.failed"));
			}
		}else{
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_RECREATE, "当前企业用户重新创建会议失败",
					EventLogConstants.EVENTLOG_FAIL, conf, request);   //创建失败后写EventLog
			return returnJsonStr(ConstantUtil.RECREATE_CONF_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.user.recreate.conf.failed"));
		}
		json.put("status", ConstantUtil.RECREATE_CONF_SUCCEED);
		jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
		json.put("confBase", jsonArrConf);
		json.put("timeZoneDesc", confBase.getTimeZoneDesc());
		return json.toString();
	}
	
	/**
	 * 主持人删除(取消)周期预约会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "deleteCycleConfInfo/{cycleId:([0-9]+)}")
	public boolean deleteCycleConfInfo(@CParam("confId") Integer confId, @CParam("cycleId") Integer cycleId, HttpServletRequest request){
		int delStatus = ConstantUtil.DELETE_CONF_FAIL; 
		boolean delFlag = false;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		//判断当前企业用户是否有权限删除即时会议
		boolean userRole = confService.cancleConfAuthority(confId, currentUser);
		ConfBase retConfBase = confService.getConfBasebyConfId(confId);
		if(userRole){
			delFlag = confService.cancleCycleConfInfo(cycleId, currentSite, currentUser);
			if(delFlag){
				delStatus = ConstantUtil.DELETE_CONF_SUCCEED;
				setInfoMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.del.confcycle.success"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_DELETE, "删除周期预约会议成功",
						EventLogConstants.EVENTLOG_SECCEED, retConfBase, request);   //删除成功后写EventLog
			}else{
				setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.del.confcycle.failed"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_DELETE, "删除周期预约会议失败",
						EventLogConstants.EVENTLOG_FAIL, retConfBase, request);   //删除失败后写EventLog
			}
		}else{
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_DELETE, "删除周期预约会议成功",
					EventLogConstants.EVENTLOG_FAIL, retConfBase, request);   //删除失败后写EventLog
			setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.confcycle.delete.not"));
		}
		return delFlag;
	}
	
	/**
	 * 主持人删除(取消)单次预约会议
	 * @author Administrator darren
	 * 2014-06-19
	 */
	@AsController(path = "delete/{confId:([0-9]+)}")
	public boolean deleteConf(@CParam("confId") Integer confId, HttpServletRequest request){
		 
		boolean delFlag = false;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		//判断当前企业用户是否有权限删除即时会议
		boolean userRole = confService.cancleConfAuthority(confId, currentUser);
		ConfBase retConfBase = confService.getConfBasebyConfId(confId);
		if(userRole){
			//取消指定的预约会议
			delFlag = confService.cancleSingleReservationConf(confId, currentSite, currentUser);
			if(delFlag){
				
				//发送取消短信
				List<ConfUser> users = confUserService.getAllConfUserList(confId,0);// 0 是已发送短信邀请的人
				smsComponent.sendMsgInviteToConfUser(users, retConfBase,4,currentUser);//4 表示取消会议 
				
				setInfoMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.del.conforder.success"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_DELETE, "删除预约会议成功",
						EventLogConstants.EVENTLOG_SECCEED, retConfBase, request);   //删除失败后写EventLog
			}else{
				setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.del.conforder.failed"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_DELETE, "删除预约会议失败",
						EventLogConstants.EVENTLOG_FAIL, retConfBase, request);   //删除失败后写EventLog
			}
		}else{
			setErrMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.conforder.delete.not"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_DELETE, "当前企业用户无权限删除预约会议",
					EventLogConstants.EVENTLOG_FAIL, retConfBase, request);   //删除失败后写EventLog
		}
		return delFlag;
	}
	
	/**
	 * 企业用户隐藏已错过的会议
	 * 1.所有企业用户都可以隐藏已错过的会议
	 * 2.只是隐藏会议主控面板的已错过的会议
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "hide/{confId:([0-9]+)}")
	public boolean hideMissConf(@CParam("confId") Integer confId, HttpServletRequest request){
		boolean hideFlag = false;
		UserBase currentUser = userService.getCurrentUser(request);
//		hideFlag = confService.hideMissConf(confId, currentUser);
		ConfBase retConfBase = confService.getConfBasebyConfId(confId);
		if(hideFlag){
			setInfoMessage(request, "隐藏已错过的会议成功");
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_DELETE, "隐藏已错过的会议成功",
					EventLogConstants.EVENTLOG_SECCEED, retConfBase, request);   
		}else{
			setErrMessage(request,  "隐藏已错过的会议失败");
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_DELETE, "隐藏已错过的会议失败",
					EventLogConstants.EVENTLOG_FAIL, retConfBase, request);
		}
		return hideFlag;
	}
	
	/**
	 * 主持人邀请参会人(跳转页面)
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "invite")
	public Object invite(){
		return new ActionForward.Forward("/jsp/user/invite_Conf.jsp");
	}
	
	/**
	 * 主持人邀请参会人，导入联系人(跳转页面默认显示群组列表)
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "inviteImportContacts")
	public Object inviteImportContacts(@CParam("nameOrEmailOrMobile") String nameOrEmailOrMobile, PageModel pageModel, HttpServletRequest request){
		UserBase currentUser = userService.getCurrentUser(request);
		PageBean<ContactGroup> page = new PageBean<ContactGroup>();
		if(currentUser != null){
			page = groupService.getGroupPageModel(nameOrEmailOrMobile,currentUser.getSiteId(),currentUser.getId(),IntegerUtil.parseIntegerWithDefaultZero(pageModel.getPageNo()),0);
		}
		request.setAttribute("pageModel", page);
		request.setAttribute("nameOrEmailOrMobile", nameOrEmailOrMobile);
		return new ActionForward.Forward("/jsp/user/invite_import_contact.jsp");
	}
	
	/**
	 * 主持人邀请参会人
	 * @param data 被邀请人信息(用户id，姓名，邮箱，电话)列表，分两种情况：
	 * 1.(用户id，姓名，邮箱，电话)，用户id为空，非本站用户
	 * 2.(用户id，姓名，邮箱，电话)，用户id不为空，本站用户
	 * @param confId
	 * wangyong
	 * 2013-3-5
	 */
	@AsController(path = "inviteParticipants")
	public boolean inviteParticipants(@CParam("data") String data, @CParam("confId") Integer confId, @CParam("cycleId") Integer cycleId, HttpServletRequest request){
		Object[] inviteUser = JsonUtil.parseObjectArrWithJsonString(data);
		List<UserBase> participantsList = new ArrayList<UserBase>();
		for(Object user:inviteUser){
			UserBase userBase = (UserBase) JsonUtil.parseObjectWithJsonString(user.toString(), UserBase.class);
			participantsList.add(userBase);
		}
		boolean participantsFlag = false;
		int participantsStatus = ConstantUtil.INVITE_CONF_USER_FAIL;
		UserBase currentUser = userService.getCurrentUser(request);
		//判断当前企业用户是否有权限邀请参会人
		boolean userRole = confService.inviteConfAuthority(confId, currentUser);
		
		if(userRole){
			participantsFlag = confService.inviteParticipants(confId, cycleId, participantsList, currentUser);
			if(participantsFlag){
				participantsStatus = ConstantUtil.INVITE_CONF_USER_SUCCEED;
				setInfoMessage(request, ResourceHolder.getInstance().getResource("bizconf.jsp.user.conf.invite.success"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_INVITE, "主持人邀请参会人成功",
						EventLogConstants.EVENTLOG_SECCEED, null, request);   //邀请成功后写EventLog
			}else{
				setErrMessage(request,  ResourceHolder.getInstance().getResource("bizconf.jsp.user.conf.invite.failed"));
				eventLogService.saveUserEventLog(currentUser, 
						EventLogConstants.SITEUSER_CONF_INVITE, "主持人邀请参会人失败",
						EventLogConstants.EVENTLOG_FAIL, null, request);   //邀请失败后写EventLog
			}
		}else{
			setErrMessage(request,  ResourceHolder.getInstance().getResource("bizconf.jsp.user.conf.invite.not"));
			eventLogService.saveUserEventLog(currentUser, 
					EventLogConstants.SITEUSER_CONF_INVITE, "当前企业用户无权限邀请参会人",
					EventLogConstants.EVENTLOG_FAIL, null, request);   //邀请失败后写EventLog
		}
		return participantsFlag;
	}
	
	/**
	 * 通过会议ID获取前几个邀请人信息
	 * wangyong
	 * 2013-3-14
	 */
	@AsController(path = "getConfInviteUser")
	public Object getConfInviteUser(@CParam("confId") Integer confId, HttpServletRequest request){
		List<ConfUser> userBaseList = null;
		userBaseList = confService.getConfInviteUser(confId);
		userBaseList = (userBaseList==null)?new ArrayList<ConfUser>():userBaseList;
		JSONArray jsonArray = new JSONArray();
		String name = "";
		for (Iterator<ConfUser> it = userBaseList.iterator(); it.hasNext();) {
			ConfUser user = (ConfUser) it.next();
			JSONObject obj = new JSONObject();
			//先取用户名 如果没有输入则依次取邮箱，电话
			if(user.getUserName()!=null && !user.getUserName().trim().equals("")){
				name = user.getUserName();
			}else if(user.getUserEmail()!=null && !user.getUserEmail().trim().equals("")){
				name = user.getUserEmail();
			}else if(user.getTelephone()!=null && !user.getTelephone().trim().equals("")){
				name = user.getTelephone();
			}else{
				name = "unknow";
			}
			obj.put("name", name);
			jsonArray.add(obj);
		}
		return jsonArray.toString();
	}
	
	/**
	 * 创建会议成功后
	 * wangyong
	 * 2013-3-14
	 */
	@AsController(path = "returnNewConf/{confId:([0-9]+)}")
	@Get
	public Object returnNewConf(@CParam("confId") Integer confId, HttpServletRequest request){
		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		UserBase currentUser = userService.getCurrentUser(request);
//		long offset = 0 ;
//		if(currentSite != null){
//			offset = currentSite.getTimeZone();
//		}else{
//			offset = DateUtil.getDateOffset();
//		}
		ConfBase confBase = confService.getConfBasebyConfId(confId, currentUser);
//		Date dashboardDays = DateUtil.addDate(DateUtil.getGmtDateByTimeZone(null, (int)offset), ConfConstant.CONF_LIST_DASHBOARD_DAYS.intValue());
//		Date confStartTime = DateUtil.getGmtDateByTimeZone(confBase.getStartTime(), (int)offset);
//		if(confStartTime.before(dashboardDays) && confStartTime.after(DateUtil.getGmtDateByTimeZone(null, (int)offset))){
			int rows = confService.countUpcomingConfList(null, currentUser, ConfConstant.CONF_LIST_DASHBOARD_DAYS);
			ConfCycle cycle = confLogic.getConfCycleByConf(confBase);
			
			request.setAttribute("cycle", cycle);
			request.setAttribute("UpcomingConfRows", rows);
			request.setAttribute("newConfBase", confBase);
			
			return new ActionForward.Forward("/jsp/user/conf_list_index_newdata.jsp");
//		}
//		return null;
	}
	
	@AsController(path = "addReminds")
	public Object addReminds(@CParam("confId") Integer confId,@CParam("userName") String userName,
			@CParam("email") String email, HttpServletRequest request){
		boolean flag = false;
		Integer status = ConstantUtil.GLOBAL_FAIL_FLAG;
		String msg = ResourceHolder.getInstance().getResource("bizconf.jsp.user.reminder.email.failed");
		UserBase currUser = userService.getCurrentUser(request);
		ConfBase currConf = confService.getConfBasebyConfId(confId);
		if(currUser!=null && currConf!=null){
			flag = emailService.sendConfRemindEmail(currUser, currConf);
		}else{
			if(email==null) throw new RuntimeException("email can not be null!");
			if(userName==null || userName.equals("")) userName = email.substring(0,email.indexOf("@"));
			flag = emailService.remindEmailForAnyOne(userName,email,currConf);
		}
		
		if(flag){
			status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
			msg = ResourceHolder.getInstance().getResource("bizconf.jsp.user.reminder.email.success");
		}
		return StringUtil.returnJsonStr(status, msg);
	}
	
	/**
	 * 显示参会人列表预留
	 * @param confId
	 * @param keyword
	 * @param pageNo
	 * @param request
	 * @return
	 */
	@AsController(path = "showUsers")
	public Object showConfUsers(@CParam("confId") Integer confId,@CParam("keyword") String keyword,
			@CParam("pageNo") Integer pageNo,HttpServletRequest request){
		 
		PageBean<ConfLog> page = confService.getConflogsByConf(confId, pageNo, 10);
		request.setAttribute("pageModel", page);
		return new ActionForward.Forward("/jsp/user/conf_log_list.jsp");
	}
	
	/**
	 * 调用service保存会议完成失败后，获取失败错误信息
	 * wangyong
	 * 2013-3-15
	 */
	private String getConfErrorMessage(ConfBase confBase){
		String errorMessage = "";
		if(confBase != null && confBase.getId() != null && confBase.getId().intValue() < 0){
			errorMessage = ResourceHolder.getInstance().getResource("conf.create.error." + confBase.getId().intValue());
		}
		return errorMessage;
	}
	
	/**
	 * 返回json字符串对象(创建，修改会议出错时)
	 * status 失败
	 * object 失败原因
	 * wangyong
	 * 2013-1-30
	 */
	private String returnJsonStr(int status, Object object){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", object.toString());
		return json.toString();
	}
	
	/**
	 * 获取每个会议的参会人个数
	 * wangyong
	 * 2013-3-25
	 */
	private Map<Integer,Integer> getParticipants(List<ConfBase> confList){
		Map<Integer,Integer> ParticipantsMap = new HashMap<Integer, Integer>();
		if(confList != null && confList.size() > 0){
			for(ConfBase conf:confList){
				List<ConfUser> Participants = confUserService.getConfInviteUserList(conf.getId());
				ParticipantsMap.put(conf.getId(), Participants == null ? 0 : Participants.size());
			}
		}
		return ParticipantsMap;
	}
	
	/**
	 * 未登录，转到公开会议
	 * @param request
	 * @return
	 */
	@AsController(path = "getPublicControlPadIndex")
	@Deprecated
	public Object getPublicControlPadIndex(HttpServletRequest request){
		boolean isLogined = loginService.isLogined(request);
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		Date curDate=currentSite.getSiteLocalTime();
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("isLogined", isLogined); 
		return new ActionForward.Forward("/jsp/user/conf_list.jsp");
	}	
	
	/**
	 * 登录后，转到我的会议
	 * @param request
	 * @return
	 */
	@AsController(path = "getControlPadIndex")
	@Interceptors({UserInterceptor.class})
	public Object getControlPadIndex(HttpServletRequest request){
		boolean isLogined = loginService.isLogined(request);
		if(isLogined){
			UserBase currentUser = userService.getCurrentUser(request);
			if(currentUser != null){
				Date curDate = currentUser.getUserLocalTime();			
				request.setAttribute("defaultDate", curDate);		//当前用户喜好设置时区的时间
				request.setAttribute("user", currentUser); 
			}
			int userRole = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("userRole")).intValue();              //角色：1.我主持的；2.我参与的（邀请我参加的）
			request.setAttribute("userRole", userRole);
			//int dateScopeFlag = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("dateScopeFlag")).intValue();    //时间范围：1.今天；2.本周；3.本月；4.所有
			//request.setAttribute("dateScopeFlag", dateScopeFlag);
		} 	
		request.setAttribute("isLogined", isLogined); 
		return new ActionForward.Forward("/jsp/user/conf_list.jsp");
	}	
	
	/**
	 * 新控制面板查询会议
	 * 一、查询我主持的会议，包括以下8种：
	 *  1、今天正在进行的
		2、今天即将开始的
		3、今天我参加过的 
		4、本周即将开始的
		5、本周我参加过的
		6、本月即将开始的
		7、本月我参加过的
		8、所有即将开始的
	 * 二、查询我参与的会议（邀请我参加的，我作为参会人的会议），包括以下8种：
	 * 	1、今天正在进行的
		2、今天即将开始的
		3、今天我参加过的 
		4、本周即将开始的
		5、本周我参加过的
		6、本月即将开始的
		7、本月我参加过的
		8、所有即将开始的
	 * wangyong
	 * 2013-3-8
	 */
	@AsController(path = "getControlPadConf")
	@Interceptors({UserInterceptor.class})
	public Object getControlPadConf(HttpServletRequest request){
		logger.info("enter conf index page  ");
		UserBase currentUser = userService.getCurrentUser(request);
		//当前用户喜好设置时区的时间
		Date curDate = currentUser.getUserLocalTime();
		ConfBase pmi = userService.getUserMeetingRoom(currentUser);
		if(pmi!=null){
			pmi.setStartTime(DateUtil.getOffsetDateByGmtDate(pmi.getStartTime(), pmi.getTimeZone().longValue()));
		}
		
		int dateScopeFlag = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("dateScopeFlag")).intValue();    //时间范围：1.今天；2.本周；3.本月；4.所有
		int pageNo = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("pageNo")).intValue();
		String confName = request.getParameter("confName");
		if(StringUtil.isNotBlank(confName)) {
			try {
				confName = URLDecoder.decode(request.getParameter("confName"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("我的会议搜索转码错误"+e);
			}			
		}
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		Date beginDate = null;
		Date endDate = null;
		if(StringUtil.isNotBlank(beginTime)){
			beginDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(beginTime, null), currentUser.getTimeZone());
		}else{
			Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(curDate);  
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        beginTime = sdf.format(calendar.getTime());
			beginDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(beginTime, null), currentUser.getTimeZone());
		}
		if(StringUtil.isNotBlank(endTime)){
			endDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(endTime, null), currentUser.getTimeZone());
		}else{
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(curDate);  
	        calendar.add(Calendar.MONTH, +1);
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        endTime = sdf.format(calendar.getTime()); 
			endDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(endTime, null), currentUser.getTimeZone());
		}
		//永久会议
		PageBean<ConfBase> permanentConfs = null;
		//时间更正
		if(beginDate!=null && endDate!=null && beginDate.after(endDate)){
			Date tempDate = beginDate;
			beginDate = endDate;
			endDate = tempDate;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		endDate = calendar.getTime();
		
		long stime = System.currentTimeMillis();
		getHostConf(confName, dateScopeFlag, beginDate, endDate, pageNo, request, currentUser);
		long totalEtime = System.currentTimeMillis();
		logger.info("total user time = "+(totalEtime-stime));
		
		request.setAttribute("pmi", pmi);
		request.setAttribute("permanentConfs", permanentConfs);
		request.setAttribute("seachTitle", confName);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("defaultDate", curDate);
		request.setAttribute("user", currentUser); 
		request.setAttribute("isLogined", loginService.isLogined(request));
		
		
		
		return new ActionForward.Forward("/jsp/user/conf_list_pad.jsp");
	}
	
	/**
	 * 新控制面板查询会议:查询我主持的会议
	 * 2013-4-22
	 */
	private HttpServletRequest getHostConf(String confName, int dateScopeFlag, Date beginTime, Date endTime, int pageNo, HttpServletRequest request, UserBase currentUser){
		List<ConfBase> dringConfList = new ArrayList<ConfBase>();
		List<ConfBase> upcomingConfList = new ArrayList<ConfBase>();
//		dringConfList = confService.getFullOpeningConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
//		upcomingConfList = confService.getFullComingConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
		
		PageBean<ConfBase> duringConfs = confService.getFullOpeningConfListForHost(confName, currentUser, pageNo, beginTime, endTime);
		PageBean<ConfBase> upcomingConfs = confService.getFullComingConfListForHost(confName, currentUser, pageNo, beginTime, endTime);
		request.setAttribute("upcomingNum", upcomingConfs.getRowsCount());
		
		dringConfList = duringConfs.getDatas();
		upcomingConfList = upcomingConfs.getDatas();
		
		//查询客户端创建的即将召开的会议
		List<ConfBase> clientRcurrConfs = confService.getClientWaitRurrConfByHost(currentUser,confName);
		if(clientRcurrConfs == null || clientRcurrConfs.isEmpty()){
			clientRcurrConfs = new ArrayList<ConfBase>();
		}
		logger.info("will add all the clientRcurrConfs to on comming conf list");
		if(upcomingConfList!=null && !upcomingConfList.isEmpty()){
			logger.info("the clientRcurrConfs must not be null and conf size "+clientRcurrConfs.size());
			clientRcurrConfs.addAll(upcomingConfList);
		}
		logger.info("will add all the clientRcurrConfs to on comming conf list flished");
		
		//增加偏好设置的条数控制
//		int likeSize = currentUser.getPageSize();//偏好设置控制
//		if(likeSize == 0){
//			likeSize = ConstantUtil.PAGESIZE_DEFAULT;
//		}
//		int listSize = clientRcurrConfs.size();//所得集合
//		if(listSize>likeSize){
//			clientRcurrConfs = clientRcurrConfs.subList(0, currentUser.getPageSize()!=0?currentUser.getPageSize():clientRcurrConfs.size());
//		}
		
		request.setAttribute("dringConfList", dringConfList);
		request.setAttribute("upcomingConfList", clientRcurrConfs);
//		request.setAttribute("attendedConfList", attendedConfList);
		//获取会议周期
		Set<ConfCycle> cycs = new HashSet<ConfCycle>();
		cycs.addAll(confLogic.getConfCyclesByConf(dringConfList));
		cycs.addAll(confLogic.getConfCyclesByConf(upcomingConfList));
//		cycs.addAll(confLogic.getConfCyclesByConf(attendedConfList));
		request.setAttribute("cycs", cycs);
		return request;
	}
	
	/**
	 * 新控制面板查询会议，获取更多信息
	 * 1.有更多会议信息时，返回一个jsp
	 * 2.无更多会议信息时，返回json（status,message）
	 * wangyong
	 * 2013-4-22
	 */
	@AsController(path = "getMoreControlPadConf")
	public Object getMoreControlPadConf(HttpServletRequest request){
		List<ConfBase> confList = null;
		int dateScopeFlag = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("dateScopeFlag")).intValue();    //时间范围：1.今天；2.本周；3.本月；4.所有
		int confStatus = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("confStatus")).intValue();    	  //会议状态：0.所有状态；1.正在进行的；2.即将开始的；3.参加过的
		int pageNo = IntegerUtil.parseIntegerWithDefaultZero(request.getParameter("pageNo")).intValue();
		String confName = request.getParameter("confName");
		if(StringUtil.isNotBlank(confName)) {
			try {
				confName = URLDecoder.decode(request.getParameter("confName"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("我的会议搜索转码错误"+e);
			}			
		}
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		Date beginDate = null;
		Date endDate = null;
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		Integer timeZone = currentSite.getTimeZone();
		if(currentUser != null){
			timeZone = currentUser.getTimeZone();
		}
		if(StringUtil.isNotBlank(beginTime)){
			beginDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(beginTime, null), timeZone);
		}
		if(StringUtil.isNotBlank(endTime)){
			endDate = DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(endTime, null), timeZone);
		}
		if (loginService.isLogined(request)) {
				confList = getMoreHostConf(confName, dateScopeFlag, confStatus, beginDate, endDate, pageNo, request, currentUser);
		}
		
//		//当前用户喜好设置时区的时间
		if(currentUser!=null){
			Date curDate = currentUser.getUserLocalTime();
			request.setAttribute("defaultDate", curDate);
			request.setAttribute("user", currentUser); 
		}
		if(confList != null && confList.size() > 0){
			request.setAttribute("confList", confList);
			return new ActionForward.Forward("/jsp/user/conf_list_index_more.jsp");
		}
		return returnJsonStr(ConstantUtil.GLOBAL_FAIL_FLAG, ResourceHolder.getInstance().getResource("bizconf.jsp.user.conf.nomore"));
	}
	
	/**
	 * 新控制面板查询会议,获取更多信息:查询我主持的会议
	 * wangyong
	 * 2013-4-22
	 */
	private List<ConfBase> getMoreHostConf(String confName, int dateScopeFlag, int confStatus, Date beginTime, Date endTime, int pageNo, HttpServletRequest request, UserBase currentUser){
		List<ConfBase> confList = null;
//		if(confStatus == ConfConstant.CONF_PAD_STATUS_OPENING){
//			confList = confService.getDailyOpeningConfListForHost(confName, currentUser, pageNo).getDatas();
//		}
//		if(dateScopeFlag == ConfConstant.CONF_DATE_SCOPE_FLAG_TODAY || dateScopeFlag == 0){      //今天
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_COMING){
//				confList = confService.getDailyComingConfListForHost(confName, currentUser, pageNo).getDatas();
//			}
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_ATTENDED){
//				confList = confService.getDailyJoinedConfListForHost(confName, currentUser, pageNo).getDatas();
//			}
//		}else if(dateScopeFlag == ConfConstant.CONF_DATE_SCOPE_FLAG_WEEK){   //本周
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_COMING){
//				confList = confService.getWeeklyComingConfListForHost(confName, currentUser, pageNo).getDatas();
//			}
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_ATTENDED){
//				confList = confService.getWeeklyJoinedConfListForHost(confName, currentUser, pageNo).getDatas();
//			}
//		}else if(dateScopeFlag == ConfConstant.CONF_DATE_SCOPE_FLAG_MONTH){   //本月
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_COMING){
//				confList = confService.getMonthlyComingConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
//			}
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_ATTENDED){
//				confList = confService.getMonthlyJoinedConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
//			}
//		}else if(dateScopeFlag == ConfConstant.CONF_DATE_SCOPE_FLAG_ANY){     //所有
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_COMING){
				confList = confService.getFullComingConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
//			}
//			if(confStatus == ConfConstant.CONF_PAD_STATUS_ATTENDED){
//				confList = confService.getFullJoinedConfListForHost(confName, currentUser, pageNo, beginTime, endTime).getDatas();
//			}
//		}
		//获取会议周期
		Set<ConfCycle> cycs = new HashSet<ConfCycle>();
		cycs.addAll(confLogic.getConfCyclesByConf(confList));
		request.setAttribute("cycs", cycs);
		return confList;
	}
	
	
	
	/**
	 * 同步客户端的会议
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "syncConf")
	public Object syncZoomConf(HttpServletRequest request) throws Exception{
		
		int status = ZoomConfStatus.SYNC_SUCCESS;
		String msg = "sycn zoom conf success";
		
		UserBase currentUser = userService.getCurrentUser(request);
		if(!syncConfService.syncHostConfs(currentUser)){
			
			status = ZoomConfStatus.SYNC_FAILED;
			msg = "sycn zoom conf failed";
		}
		return returnJsonStr(status, msg);
	}

	/***
	 * 修改单次周期会议
	 * 
	 * @param conf 对应修改的本次周期会议会议
	 * @param cycle 对应修改周期会议的本次开始时间
	 * @param request
	 * @author Darren 
	 * */
	@AsController(path = "updateSingleCycleConf")
	public Object updateSingleCycleConf(ConfBase conf,ConfCycle cycle, HttpServletRequest request){
		
		JSONObject json = new JSONObject();
		JSONArray jsonArrConf = new JSONArray();
		JSONArray jsonArrConfCycle = new JSONArray();
		UserBase currentUser = userService.getCurrentUser(request);
		SiteBase siteBase = siteService.getSiteBaseById(currentUser.getSiteId());
		
		//首先判断开始时间是否修改
		ConfBase oldConfBase = confService.getConfBasebyConfId(conf.getId().intValue());
		cycle = confService.getConfCyclebyConfId(conf.getCycleId());
		
		if(oldConfBase == null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			//"周期范围内没有有效的会议时间"
			json.put("message", "修改的会议不存在");
			return json.toString();
		}
		if(cycle == null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			//"周期范围内没有有效的会议时间"
			json.put("message", "该周期会议循环周期不存在");
			return json.toString();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String oldTime = sdf.format(oldConfBase.getStartTime());
		String NewTime = sdf.format(DateUtil.getGmtDate(conf.getStartTime()));//GMT开始时间
		//if条件这些不改变就不新创建新的会议
		
		if(oldTime.equals(NewTime) && ValidateUtil.isNotUpdate(oldConfBase, conf)){
			oldConfBase.setStartTime(DateUtil.getOffsetDateByGmtDate(oldConfBase.getStartTime(), oldConfBase.getTimeZone().longValue()));
			json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
			jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(oldConfBase));
			jsonArrConfCycle.add(JsonUtil.parseJsonWithFullFieldByObject(cycle));
			json.put("confBase", jsonArrConf);
			json.put("timeZoneDesc", oldConfBase.getTimeZoneDesc());//创建会议返回时区
			json.put("confCycle", jsonArrConfCycle);
			return json.toString();
		}
		
//		conf.setConfType(ConfType.SUB_RECURR);
		//修改单次周期会议(创建一个单次预约会议)
		ConfBase confBase = confService.createSingleReservationConf(conf, siteBase, currentUser,false);
		if(confBase == null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			json.put("message", "修改周期会议本次周期失败");
			return json.toString();
		}
		
		//修改周期会议时间变成下次
		ConfBase dbConf = confService.getConfBasebyConfId(conf.getId());
		//获取下个周期开始时间（GMT）
		Date nextTime = confLogic.getNextConfStartTime(dbConf.getStartTime(), cycle, 0);
		//下个周期不存在，返回上次会议
		if(nextTime == null){
			oldConfBase.setStartTime(DateUtil.getOffsetDateByGmtDate(oldConfBase.getStartTime(), oldConfBase.getTimeZone().longValue()));
			json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
			jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(oldConfBase));
			jsonArrConfCycle.add(JsonUtil.parseJsonWithFullFieldByObject(cycle));
			json.put("confBase", jsonArrConf);
			json.put("timeZoneDesc", oldConfBase.getTimeZoneDesc());//创建会议返回时区
			json.put("confCycle", jsonArrConfCycle);
			return json.toString();
		}
		
//		dbConf.setNextStartTime(nextTime);
		//获取修改后的周期
		dbConf.setStartTime(nextTime);
		dbConf.setEndTime(new Date(nextTime.getTime()+dbConf.getDuration()*60*1000l));
		//修改zoom中的会议信息
		boolean optionJbh = (dbConf.getOptionJbh() == 0) ? false: true;
		String optionStartType = "video";
		if (dbConf.getOptionStartType() == 2) {
			optionStartType = "screen_share";
		}
		
		//Add by Darren 2014-12-24
		DataCenter dataCenter = centerService.queryDataCenterById(currentUser.getDataCenterId());
		
		int status = zoomMeetingOperator.updateMeeting(dataCenter.getApiKey(),dataCenter.getApiToken(),dbConf.getConfZoomId(), currentUser.getZoomId(), 
				dbConf.getConfName(), dbConf.getZoomStartTime(), dbConf.getDuration(), dbConf.getTimeZoneSecond(),
				dbConf.getHostKey(), optionJbh, optionStartType);
		
		ConfBase retConfBase = null;
		if(status == ZoomConfStatus.UPDATE_SUCCESS){
			//get H323 password ,add by darren 2015-01-30 
			Map<String, Object> retMap = zoomMeetingOperator.getMeeting(dataCenter.getApiKey(), dataCenter.getApiToken(), dbConf.getConfZoomId(), currentUser.getZoomId());
			if(retMap.get("error") == null){
				if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
					dbConf.setPhonePass((String)retMap.get("h323Password"));
				}
			}
			retConfBase = confService.saveOrUpdateConf(dbConf);
		}
		
		if(retConfBase == null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			json.put("message", "修改周期会议下一个周期失败");
			return json.toString();
		}
		//判断会议的时间是否被修改
		if(!oldTime.equals(NewTime) || oldConfBase.getTimeZone() != retConfBase.getTimeZone()){//被修改发送短信通知
			List<ConfUser> users = confUserService.getAllConfUserList(conf.getId(),0);// 0 是已发送短信邀请的人
			smsComponent.sendMsgInviteToConfUser(users, confBase.clone(),3,currentUser);//3 表示修改会议 
		}
		confBase.setEndTime(new Date(confBase.getStartTime().getTime() + confBase.getDuration()*60*1000l));
		List<ConfUser> users = confUserService.getAllConfUserList(conf.getId(),1);
		emailService.updateNowCycleConfEmail(users, confBase.clone());
		
		confBase.setStartTime(DateUtil.getOffsetDateByGmtDate(confBase.getStartTime(), confBase.getTimeZone().longValue()));
		confBase.setEndTime(DateUtil.getOffsetDateByGmtDate(confBase.getEndTime(), confBase.getTimeZone().longValue()));
		json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
		jsonArrConf.add(JsonUtil.parseJsonWithFullFieldByObject(confBase));
		
		jsonArrConfCycle.add(JsonUtil.parseJsonWithFullFieldByObject(cycle));
		json.put("confBase", jsonArrConf);
		json.put("timeZoneDesc", confBase.getTimeZoneDesc());//创建会议返回时区
		json.put("confCycle", jsonArrConfCycle);
		return json.toString();
	}
	
	/***
	 * 取消单次周期会议
	 * @param confId 对应修改的本次周期会议会议Id
	 * @param request
	 * @author Darren
	 * */
	@AsController(path = "delSingleCycleConf/{confId:([0-9]+)}")
	public Object delSingleCycleConf(@CParam("confId") Integer confId, HttpServletRequest request){

		JSONObject json = new JSONObject();
		UserBase currentUser = userService.getCurrentUser(request);
		//根据confId 获取周期会议的周期信息
		ConfBase confBase = confService.getConfBasebyConfId(confId.intValue());
		if(confBase==null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			json.put("message", "周期会议不存在");
			return json.toString();
		}
		ConfCycle confCycle = confService.getConfCyclebyConfId(confBase.getCycleId().intValue());
		//获取周期会议的下次开始时间（GMT）
		Date nextDate = confLogic.getNextConfStartTime(confBase.getStartTime(), confCycle, 0);
		/***************darren add 取消本次会议时间问题***********************/
		//获取修改后的周期
		confBase.setStartTime(nextDate);
		confBase.setEndTime(new Date(nextDate.getTime()+confBase.getDuration()*60*1000l));
		/***************darren add 取消本次会议时间问题***********************/
		
		ConfBase retConf = confService.saveOrUpdateConf(confBase);
		if(retConf == null){
			json.put("status", ConstantUtil.UPDATE_CONF_FAIL);
			json.put("message", "取消本次周期会议失败");
			return json.toString();
		}
		
		//发送取消会议邮件
		// 只有预约成功的会议才发送会议取消邮件通知
		List<ConfUser> confUsers = confUserService.getAllConfUserList(confId,1);
		emailService.sendCancelSingleCycleConfEmail(confUsers, confBase.clone());
		
		//发送取消短信
		List<ConfUser> users = confUserService.getAllConfUserList(confId,0);// 0 是已发送短信邀请的人
		smsComponent.sendMsgInviteToConfUser(users, retConf,4,currentUser);//4 表示取消会议 
		
		json.put("status", ConstantUtil.UPDATE_CONF_SUCCEED);
		json.put("message", "取消本次周期会议成功");
		return json.toString();
	}
	
	/**
	 * 发送会议的短信邀请
	 * */
	@AsController(path="inviteMeetingNotice")
	public Object inviteMeetingNotice(@CParam("data") String data , 
			HttpServletRequest request){
		Integer inviteConf;
		UserBase currentUserBase = userService.getCurrentUser(request);
		
		JSONObject jObject = JSONObject.fromObject(data);
		Integer confId = Integer.parseInt(jObject.get("confId").toString());	//会议ID
		inviteConf = confId;
		Boolean isCalling = Boolean.parseBoolean(jObject.get("isCalling").toString());
		Integer sendType = Integer.parseInt(jObject.get("sendType").toString());	//邀请会议类型
		ConfBase confInfo = confService.getConfBasebyConfId(confId);
		JSONArray jsonArray = jObject.getJSONArray("users");
		
		List<ConfUser> users = new ArrayList<ConfUser>();
		List<UserBase> ubs = new ArrayList<UserBase>();
		
		List<ConfUser> unStoredUsers = new ArrayList<ConfUser>();
		//首先组织
		for(int i = 0;i < jsonArray.size(); i++){
			ConfUser cu = (ConfUser) JsonUtil.parseObjectWithJsonString(jsonArray.get(i).toString(), ConfUser.class);
			cu.setHostFlag(ConstantUtil.USERROLE_PARTICIPANT);
			users.add(cu);
			
			if(!StringUtil.isEmpty(cu.getContactId()) && !cu.getContactId().startsWith("e")){
				int cId = Integer.parseInt(cu.getContactId());
				contactService.updateContactPhone(cId, cu.getTelephone());
			}
			
			if(StringUtil.isEmpty(cu.getContactId()) && !StringUtil.isEmpty(cu.getTelephone()) && 
					!contactService.telContactStored(cu.getTelephone(), currentUserBase) &&
					!contactService.siteContactEmailExist(currentUserBase.getSiteId(), cu.getTelephone())){
				unStoredUsers.add(cu);
			}
			
			UserBase ub = new UserBase();
			ub.setId(0);
			if(cu.getUserId()!=null && cu.getUserId()>0){
				ub.setId(cu.getUserId());
			}
			ub.setTrueName(cu.getUserName());
			if(ub.getTrueName()==null || ub.getTrueName().equals("")){
				ub.setTrueName(cu.getUserEmail());
			}
			ub.setUserEmail(cu.getUserEmail());
			ub.setPhone(cu.getTelephone());
			if(ub.getTrueName()==null||ub.getTrueName().equals("")){
				ub.setTrueName(cu.getTelephone());
			}
			ubs.add(ub);
		}

//		boolean flag = confUserService.fillConfUserForInvite(confInfo, ubs,0);
		List<ConfUser> confUsers = confUserService.fillConfUserForSMSInvite(confInfo, ubs,0);
		
		if(confUsers !=null && !confUsers.isEmpty()){
			Map<String, String> retMap = smsComponent.sendMsgInviteToConfUser(users, confInfo,sendType,currentUserBase);
			if(retMap.isEmpty() || !"0".equals(retMap.get("result"))){
				//删除掉添加的联系人
				for(ConfUser confUser:confUsers){
					confUser.setDelFlag(new Integer(2));
					confUserService.delConfUserById(confUser);
				}
				String desc = retMap.get("description");
				if(desc!=null && desc.endsWith(",")){
					desc = desc.substring(0, desc.length()-1);
				}
				request.setAttribute("result", desc);
				request.setAttribute("failNums", retMap.get("faillist"));
				return new ActionForward.Forward("/jsp2.0/user/invite_failed.jsp");
			}
		}else{
			request.setAttribute("result", "发送邀请联系人为空");
			return new ActionForward.Forward("/jsp2.0/user/invite_failed.jsp");
		}
		request.setAttribute("userList", unStoredUsers);
		request.setAttribute("isCalling", isCalling);
		request.setAttribute("confid", inviteConf);
		return new ActionForward.Forward("/jsp2.0/user/invite_success.jsp");
	}
}
