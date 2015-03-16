package com.bizconf.vcaasz.action.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.Base64;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.User;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteStatusInterceptor;
import com.bizconf.vcaasz.interceptors.UserInterceptor;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.NoticeService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.SyncConfService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.vcaasz.util.UserAgentUtils;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberCFile;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.exception.LiberCFileException;
import com.libernate.liberc.utils.LiberContainer;
import com.libernate.support.lb.filter.impl.HttpWrapperOPImpl;

/**
 * 企业用户入口
 * 
 * @author wangyong
 * 2013/2/28
 */
@ReqPath("")
//@Interceptors(UserInterceptor.class)
@Interceptors(SiteStatusInterceptor.class)
public class IndexController extends BaseController{
	private final  Logger logger=Logger.getLogger(IndexController.class);
	@Autowired
	UserService userService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	SiteService siteService;
	@Autowired
	LoginService loginService;
	@Autowired
	ConfService confService;
	@Autowired
	SyncConfService syncConfService;
	
	@AsController(path = "")
	public Object index(LiberInvocation inv,HttpServletRequest request) {
		UserBase user = userService.getCurrentUser(inv.getRequest());
		
		String loginName = inv.getRequest().getParameter("var");
		if(!StringUtil.isEmpty(loginName)){
			loginName = Base64.decode(loginName);
			logger.info("redrict the loginName is "+loginName);
			if(user == null){
				logger.info("can not find user in cookie, may be user disabled cookie!");
			}
		}
		

		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang = CookieUtil.getCookieByDomain(inv.getRequest(), ConstantUtil.COOKIE_LANG_KEY,domain);
		if(user == null || user.isLocked() || user.isDeled()){
			
			logger.info("find user did not login yet, will going to login page ");
			String UserAgent = request.getHeader("User-Agent");//区分移动端和pc端
			if(UserAgentUtils.isMobile(UserAgent,"mobile") || UserAgentUtils.isMobile(UserAgent,"iphone") 
					|| UserAgentUtils.isMobile(UserAgent,"ipad") || UserAgentUtils.isMobile(UserAgent,"android") ){		//移动客户端成功跳转
				
				return new ActionForward.Forward("/jsp/user/mobileLogin.jsp");
			}
			
			if(currentSite.getSiteSign().equals("kennametal")){
			//if(currentSite.getSiteSign().equals("meeting")){
				logger.info("find out is kennametal site, will use form post login ");
				return new ActionForward.Forward("/jsp/user/formlogin.jsp");
			}else{
				return new ActionForward.Forward("/jsp/user/loginFormal.jsp");
			}
			
		}
		
		
		String joinUrl=inv.getRequest().getParameter("joinUrl");
		boolean joinFlag=false;
		if(joinUrl!=null && joinUrl.trim().length()>0){
			joinFlag=true;
			joinUrl=Base64.decode(joinUrl, "utf8");
			joinUrl=joinUrl.replaceAll("_", "/");
		}
		inv.getRequest().setAttribute("joinFlag",joinFlag);
		inv.getRequest().setAttribute("joinType",ConfConstant.HOST_JOIN_TYPE_EMAIL);
		inv.getRequest().setAttribute("joinUrl",joinUrl);
		logger.info("find out the joinUrl! ");
		
		Notice sysNotice = noticeService.getCurrentSystemNotice();
		if(sysNotice != null && sysNotice.getId() != null && sysNotice.getId().intValue() > 0){
			sysNotice=(Notice)ObjectUtil.parseChar(sysNotice, "content");
			inv.getRequest().setAttribute("sysNotice", sysNotice);
			
			logger.info("find out the sysNotice! ");
		}
		
		if(currentSite != null && currentSite.getId() != null && currentSite.getId().intValue() > 0){
			//当前站点时区的时间
			inv.getRequest().setAttribute("currentSite", currentSite);
			inv.getRequest().setAttribute("defaultDate", currentSite.getSiteLocalTime());
			
			logger.info("find out the currentSite! ");
		}
		if(user != null && user.getId() != null && user.getId().intValue() > 0){
			inv.getRequest().setAttribute("isConfHost", user.isConfHost());
			if(user.getPassEditor() != null && (user.getPassEditor().intValue() == 0 || user.getPassEditor().intValue() != user.getId().intValue())){
				inv.getRequest().setAttribute("needResetPass", "true");      //用户密码被管理员修改后，第一次登陆需重置密码
			}
			if(user!=null && user.getLastLoginDate() == null){
				inv.getRequest().setAttribute("popSeeting", "true");      //用户密码被管理员修改后，第一次登陆需重置密码
			}
			userService.updateLastLoginDate(user.getId());
			//当前用户时区的时间
			inv.getRequest().setAttribute("defaultDate", user.getUserLocalTime());
			currentSite.setTimeZoneId(user.getTimeZoneId());
			//如果此时用户token为空，需要添加用户token
			
			//if(StringUtil.isEmpty(user.getZoomToken())){
				userService.updateUserZoomToken(user);
			//}
			//更新开始24小时仍未结束的会议状态
			//confService.fixConfStatus();
//			if(StringUtil.isEmpty(user.getPmiId())){
//				userService.updateUserPMI(user);
//			}
			
			long stime = System.currentTimeMillis();
			syncConfService.syncHostConfs(user);
			long etime = System.currentTimeMillis();
			System.out.println("total use time :"+(etime-stime));
			
			logger.info("login user finded!, sync Host Confs complated!");
		}
		inv.getRequest().setAttribute("siteBase", currentSite);
		inv.getRequest().setAttribute("user",user);
		inv.getRequest().setAttribute("lang", lang);
		
		if (loginService.isLogined(request)) {
			inv.getRequest().setAttribute("isLogined", true);
			logger.info("login success will going to meetings page!");
//			return new ActionForward.Forward("/jsp/user/index.jsp");
			return new ActionForward.Forward("/jsp/user/default.jsp");
		}
		logger.info("not logined will going to normal login page!");
		return new ActionForward.Forward("/jsp/user/loginFormal.jsp");
	}
	
	
	@AsController(path = "{id:([0-9]+)}")
	@Interceptors({UserInterceptor.class})
	//@Post
	public Object index(@CParam("id") int id, HttpServletRequest request) throws Exception {
		
		logger.info("UserId="+id);
//		request.getInputStream();
		try {
			
			System.out.println("user" + new String(StringUtil.readInputStream(request.getInputStream())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user:" + id + ", site: " + SiteIdentifyUtil.getCurrentBrand();
	}
	
	@AsController(path="resetPass")
	public Object resetPass(@CParam("id") int id, HttpServletRequest request) throws Exception {
		return new ActionForward.Forward("/jsp/user/resetPass.jsp");
	}
	
	
	@AsController(path="faverSetting")
	public Object faverSetting(@CParam("timezone") int timezone,HttpServletRequest request) throws Exception {
		
		int timezoneId = 44;// 默认为北京
		switch (timezone) {
		case 0:
			timezoneId = 22; //伦敦
			break;
		case 3600000:
			timezoneId = 25;//巴黎
			break;
		case 7200000:
			timezoneId = 26;//雅典
			break;
		case 10800000:
			timezoneId = 31;//莫斯科
			break;
		case 12600000:
			timezoneId = 34;//德黑兰
			break;
		case 14400000:
			timezoneId = 35;//阿布扎比
			break;
		case 16200000:
			timezoneId = 37;//喀布尔
			break;
		case 18000000:
			timezoneId = 39;//伊斯兰堡
			break;
		case 19800000:
			timezoneId = 40;//孟买
			break;
		case 21600000:
			timezoneId = 42;//阿拉木图
			break;
		case 25200000:
			timezoneId = 43;//曼谷
			break;
		case 28800000:
			timezoneId = 44;//北京
			break;
		case 32400000:
			timezoneId = 48;//首尔
			break;
		case 36000000:
			timezoneId = 52;//布里斯班
			break;
		case 37800000:
			timezoneId = 55;//阿得莱德
			break;
		case 39600000:
			timezoneId = 58;//悉尼
			break;
		case 43200000:
			timezoneId = 60;//惠灵顿
			break;
		case -9000000:
			timezoneId = 18;//纽芬兰
			break;
		case -10800000:
			timezoneId = 15;//巴西利亚
			break;
		case -14400000:
			timezoneId = 14;//纽约
			break;
		case -18000000:
			timezoneId = 11;//芝加哥
			break;
		case -21600000:
			timezoneId = 7;//丹佛
			break;
		case -25200000:
			timezoneId = 6;//旧金山
			break;
		case -28800000:
			timezoneId = 4;//安克雷奇
			break;
		case -36000000:
			timezoneId = 3;//火奴鲁鲁
			break;
		case -43200000:
			timezoneId = 1;//马歇尔群岛
			break;
		default:
			timezoneId = 44;//北京
			break;
		}
		
		String lang = LanguageHolder.getCurrentLanguage();
		request.setAttribute("lang", lang);
		request.setAttribute("timezoneId", timezoneId);
		return new ActionForward.Forward("/jsp2.0/user/favor_setup_index.jsp");
	}

	
	@AsController
	//@Get
	public Object addUser(User user, @CParam("name2") String name, @CParam("file1") LiberCFile file) {
		try {
//			Condition condition=new Condition();
			file.save("", "png", "jpg");
		} catch (LiberCFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User newUser =new User();// userService.addUser(user);
		return newUser.getName() + "ok";
	}
	
	
	@AsController(path="clientInfo")
	public Object getClientInfo(@CParam("supported") String supported, HttpServletRequest request) throws Exception {
		
		HttpWrapperOPImpl op=new HttpWrapperOPImpl();
		String ip = op.getRemoteAddr(request);
		String supportCookie = supported;
		
		String tempDirPath = LiberContainer.getContainer().getServletContext().getRealPath("test");
		
		File logFile = new File(tempDirPath+File.separator+"log.txt");
		if(!logFile.exists()){
			logFile.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
		bw.write("ip:"+ip+"  support cookie:"+supportCookie);
		bw.newLine();
		bw.close();
		
		return "ok";
	}
	
	
	
}
