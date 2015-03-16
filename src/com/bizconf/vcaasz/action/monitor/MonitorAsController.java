package com.bizconf.vcaasz.action.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Head;
import com.libernate.liberd.Libernate;

@ReqPath("")
public class MonitorAsController extends BaseController{
	private final Logger logger=Logger.getLogger(MonitorAsController.class);
	private static boolean DEBUG = false;
	private static Libernate libernate = DAOProxy.getLibernate();
	private final int ERROR_CODE_500=500;
	private final int AFTER_MINUTE=2;
	private final int CHECK_CONF__MINUTE=1;
	private static Date LAST_SUCCESSED_DATE=null;
	

	@Autowired
	UserService userService;
	@Autowired
	ConfService confService;
	@Autowired
	SiteService siteService;
	
	
	@Autowired
	ConfUserService confUserService;
	
	
	@AsController(path = "checkas")
	@Get
	@Head
	public Object monitor( HttpServletRequest request,HttpServletResponse response){
		
		logger.info("HttpMethod:"+request.getMethod()+" ；ip="+StringUtil.getIpAddr(request)+"");
		PrintWriter pw;
		if(LAST_SUCCESSED_DATE != null && DateUtil.diffSecond(LAST_SUCCESSED_DATE,new Date()) < 170){
			
			logger.info(" 3分钟内重复CheckAs ， LAST_SUCCESSED_DATE  ="+LAST_SUCCESSED_DATE+" ; new Date()  =" + new Date());
			try {
				pw = response.getWriter();
				pw.println(200);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ActionForward.Error(200);
		}
		int userId = 1003676;     //go 的dick用户
		int siteId = 26;		  //go站点
//		int userId = 1000003;     //240 的jack用户id
//		int siteId = 1;			  //240 的meet站点
		UserBase userBase=userService.getUserBaseById(userId);
		SiteBase siteBase=siteService.getSiteBaseById(siteId);
		String debug=request.getParameter("debug");
		
		if("true".equalsIgnoreCase(debug) || "1".equalsIgnoreCase(debug)){
			DEBUG = true;
		}
		else if("flase".equalsIgnoreCase(debug)){
			DEBUG = false;
		}
		
		if(userBase==null){
			logger.info("userBase is null ");
			return new ActionForward.Error(ERROR_CODE_500);
			//return ERROR_CODE_500;
		}
		if(siteBase==null){
			logger.info("siteBase is null ");
			return new ActionForward.Error(ERROR_CODE_500);
		}
		ConfBase confBase = new ConfBase();
		initConf(confBase, siteBase, userBase);
		logger.info(siteBase);
		logger.info(userBase);
		 
		
		 
		confBase = saveConf(confBase);
		if(confBase != null && confBase.getId() != null && confBase.getId().intValue() > 0){
				confUserService.fillConfUserForCreate(confBase, userBase);
		}
		 
		logger.info("启动会议成功");
		//Calcel Conf
//		if(!confManagementService.cancelConf(confBase.getConfHwid(), siteBase, userBase)){
//			logger.error("结束会议失败");
//			return 3;
//		}
//		logger.info("结束会议成功");
		if(DEBUG){
			return new ActionForward.Error(ERROR_CODE_500);
		}
		
		try {
			pw = response.getWriter();
			pw.println(200);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LAST_SUCCESSED_DATE=new Date();
		return new ActionForward.Error(200);
	}
	
	/**
	 * 保存会议时，初始化会议信息
	 * wangyong
	 * 2013-3-4
	 */
	private void initConf(ConfBase confBase, SiteBase siteBase, UserBase user){
		if(confBase != null && siteBase != null && user != null){
			DefaultConfig defaultConfig = confService.getDefaultConfig(user);
			confBase.setConfName("验证AS的会议-" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			confBase.setSiteId(siteBase.getId());
			confBase.setCycleId(0);   //此处全部初始化为预约会议，周期会议在创建周期会议方法中初始化完毕后设置	
			if(!StringUtil.isNotBlank(confBase.getConfDesc())){
				confBase.setConfDesc("");
			}
			Integer timeZone = 0;
			Integer timeZoneId = 0;
			if(user.getTimeZone() != null){
				timeZone = user.getTimeZone();
				timeZoneId = user.getTimeZoneId();
			}else if(siteBase.getTimeZone() != null){
				timeZone = siteBase.getTimeZone();
				timeZoneId = siteBase.getTimeZoneId();
			}
			confBase.setStartTime(DateUtil.addDateMinutes(DateUtil.getGmtDate(null),AFTER_MINUTE));
			confBase.setDuration(CHECK_CONF__MINUTE);
			confBase.setEndTime(DateUtil.addDateMinutes(confBase.getStartTime(),CHECK_CONF__MINUTE));
			confBase.setTimeZone(timeZone);
			confBase.setTimeZoneId(timeZoneId);
			confBase.setCompereUser(user.getId());
			confBase.setCompereName(user.getTrueName());
			confBase.setMaxUser(2);
			 
			 
			confBase.setCreateTime(DateUtil.getGmtDate(null));   //创建时间初始化为GMT时间
			confBase.setCreateUser(user.getId());
			confBase.setCreateType(user.getUserType());
			try {
				confBase.setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
			} catch (ParseException e) {
				logger.error("保存会议时，初始化会议信息,转换删除时间出错！"+e);
			}
			 
		}
	}
	
	
	/**
	 * 操作数据库，新建会议信息
	 * wangyong
	 * 2013-3-5
	 */
	private ConfBase saveConf(ConfBase confBase){
		ConfBase conf = new ConfBase();
		try {
			conf = libernate.saveEntity(confBase);
		} catch (Exception e) {
			logger.error("保存会议信息出错！",e);
		}
		return conf;
	}
	
}
