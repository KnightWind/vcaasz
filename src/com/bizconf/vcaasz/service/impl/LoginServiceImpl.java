package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.entity.LoginLog;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.SiteAdminLogic;
import com.bizconf.vcaasz.service.LoginLogService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.MD5;
/**
 * 
 * 登录服务
 * 
 * @author Chris Gao
 *
 */
@Service
public class LoginServiceImpl  extends BaseService implements LoginService{
	
	@Autowired
	SiteAdminLogic siteAdminLogic;
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	UserService userService;
	@Autowired
	LoginLogService loginLogService;
	public  final int PAD_ERROR_USER_SUCCESS=0;
	public  final int PAD_ERROR_USER_EXPRIED=1;
	public  final int PAD_ERROR_USER_NAME_OR_PASSWORD=2;
	public  final int PAD_ERROR_USER_LOAKED=3;
	
	public int loginForPad(String loginName, String loginPass,SiteBase siteBase,HttpServletRequest request){
		if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(loginPass) || siteBase==null) {
			return PAD_ERROR_USER_SUCCESS;
		}
		UserBase userBase = userService.getSiteUserByLoginName(siteBase.getId(), loginName);
		if(userBase==null){
			saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_NOT_EXIST, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_NAME_OR_PASSWORD;
		}
		if(userBase.getUserStatus()==0){
			saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_LOAKED;
		}
		if(userBase.isExpried()){
			saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_EXPRIED, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_EXPRIED;
		}
		if (userBase.getErrorCount().intValue() >= ConstantUtil.MAX_ERROR_COUNT_USER.intValue()) {
			if (DateUtil.getGmtDate(null).getTime() < userBase.getLastErrorTime().getTime()
					+ ConstantUtil.LIMIT_LOGIN_HOUR_USER * 3600 * 1000L) {
				saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
				return PAD_ERROR_USER_LOAKED;
			}
			else {
				userBase.setErrorCount(0);
				userBase.setUserStatus(ConstantUtil.USER_STATU_UNLOCK);
				this.updateErrorCount(userBase);
			}
		}
		if (!MD5.encodePassword(loginPass, "MD5").equalsIgnoreCase(userBase.getLoginPass())) {
			userBase.setErrorCount(userBase.getErrorCount() + 1);
			userBase.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(userBase);
			saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_PASSWORD, loginName);    //added by jack 08/09/13
//			boolean loginLogStatus = saveSiteAdminOrUserLoginLog(userBase,request);
//			logger.info("Ipad loginName="+loginName+"; user loginLogStatus----->>"+loginLogStatus);
			return PAD_ERROR_USER_NAME_OR_PASSWORD;
		}
		if(userBase.getErrorCount().intValue()>0){
			userBase.setErrorCount(0);
			this.updateErrorCount(userBase);
			
		}
		saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_SUCCESS, loginName);    //added by jack 08/09/13
		return PAD_ERROR_USER_SUCCESS;
	}
	
	@Override
	public int login(String loginName, String loginPass, String authCode,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		SiteBase site = siteService.getSiteBaseBySiteSign(siteBrand);
		if (site == null) {
			return LoginConstants.LOGIN_ERROR_USER_NOT_EXIST;
		}
		//UserBase userBase = userService.getSiteUserByLoginNameOrEmail(site.getId(), loginName);
		UserBase userBase = userService.getSiteUserByEmail(site.getId(), loginName);
		if (userBase == null) {
			saveUserLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_NOT_EXIST, loginName);    //added by jack 08/09/13
			return LoginConstants.LOGIN_ERROR_USER_NOT_EXIST;
		}
		if(userBase.getUserStatus()==0 && userBase.getErrorCount().intValue() < ConstantUtil.MAX_ERROR_COUNT_USER.intValue()){
			saveUserLoginLog(site, userBase, request, LoginConstants.USER_STATU_LOCK, loginName);    //added by jack 08/09/13
			return LoginConstants.USER_STATU_LOCK;
		}
		
		if(userBase.isExpried()){
			saveUserLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_EXPRIED, loginName);    //added by jack 08/09/13
			return LoginConstants.LOGIN_ERROR_USER_EXPRIED;
		}
		
		if (userBase.getErrorCount().intValue() >= ConstantUtil.MAX_ERROR_COUNT_USER.intValue() && userBase.getUserStatus()==0) {
			//modified by Chris Gao 06/13/13
			//Date autoUnlockDate = DateUtil.getGmtDateByAfterHour(ConstantUtil.LIMIT_LOGIN_HOUR_USER);
			//if (DateUtil.getGmtDate(null).before(autoUnlockDate)) {
			if (DateUtil.getGmtDate(null).getTime() < userBase.getLastErrorTime().getTime()
					+ ConstantUtil.LIMIT_LOGIN_HOUR_USER * 3600 * 1000L) {
				saveUserLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
				return LoginConstants.LOGIN_ERROR_USER_LOCKED;
			}
			else {
				userBase.setErrorCount(0);
				userBase.setUserStatus(ConstantUtil.USER_STATU_UNLOCK);
				this.updateErrorCount(userBase);
			}
		}
		
		if (!MD5.encodePassword(loginPass, "MD5").equalsIgnoreCase(userBase.getLoginPass())) {
			userBase.setErrorCount(userBase.getErrorCount() + 1);
			userBase.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(userBase);
			saveUserLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_PASSWORD, loginName);    //added by jack 08/09/13
//			boolean loginLogStatus = saveSiteAdminOrUserLoginLog(userBase,request);
//			logger.info("site user loginLogStatus----->>"+loginLogStatus);
			return LoginConstants.LOGIN_ERROR_PASSWORD;
		}
		
		// login successfully, to update user password for site expired (lock) logic
		if (userBase.getLoginPassPlain() == null || userBase.getLoginPassPlain().length() == 0) {
			userBase.setLoginPassPlain(loginPass);
			libernate.updateEntity(userBase, "loginPassPlain");
		}
		
		//授权
		String sessionId = getSessionIdForUserBase(userBase);
		CookieUtil.setPageCookie(response, LoginConstants.SESSION_ID_NAME, sessionId, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.setPageCookie(response, LoginConstants.USER_SESSION_ID_NAME, 
				String.valueOf(userBase.getId()), siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		//removed by Chris Gao 06/13/13
		if(userBase.getErrorCount().intValue()>0){
			userBase.setErrorCount(0);
			this.updateErrorCount(userBase);
		}
		
		saveUserLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_SUCCESS, loginName);  //added by jack 08/09/13
		return LoginConstants.LOGIN_ERROR_SUCCESS;
	
	}

	@Override
	public int loginSiteAdmin(String loginName, String loginPass,
			String authCode, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		SiteBase site = siteService.getSiteBaseBySiteSign(siteBrand);
		
		logger.info("==siteBrand="+siteBrand);
		if (site == null) {
			return LoginConstants.LOGIN_ERROR_USER_NOT_EXIST;
		}
		UserBase userBase = siteAdminLogic.getSiteAdmin(site.getId(), loginName);
		if (userBase == null) {
			saveSiteAdminLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_NOT_EXIST, loginName);    //added by jack 08/09/13
			return LoginConstants.LOGIN_ERROR_USER_NOT_EXIST;
		}
		if(userBase.getUserStatus()==0){
			saveSiteAdminLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
			return LoginConstants.LOGIN_ERROR_USER_LOCKED;
		}
		if (userBase.getErrorCount().intValue() >= ConstantUtil.MAX_ERROR_COUNT_ADMIN.intValue()) {
			//modified by Chris Gao 06/20/13
			//Date autoUnlockDate = DateUtil.getGmtDateByAfterHour(ConstantUtil.LIMIT_LOGIN_HOUR_ADMIN);
			//if (DateUtil.getGmtDate(null).before(autoUnlockDate)) {
			if (DateUtil.getGmtDate(null).getTime() < userBase.getLastErrorTime().getTime()
					+ ConstantUtil.LIMIT_LOGIN_HOUR_ADMIN * 3600 * 1000L) {
				saveSiteAdminLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
				return LoginConstants.LOGIN_ERROR_USER_LOCKED;
			}
			else {
				userBase.setUserStatus(ConstantUtil.USER_STATU_UNLOCK);
				userBase.setErrorCount(0);
				this.updateErrorCount(userBase);
			}
		}

		logger.info("md5 pass="+MD5.encodePassword(loginPass, "MD5"));
		logger.info("db pass="+userBase.getLoginPass());
		
		if (!MD5.encodePassword(loginPass, "MD5").equalsIgnoreCase(userBase.getLoginPass())) {
			userBase.setErrorCount(userBase.getErrorCount() + 1);
			userBase.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(userBase);
			saveSiteAdminLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_PASSWORD, loginName);    //added by jack 08/09/13
//			boolean loginLogStatus = saveSiteAdminOrUserLoginLog(userBase,request);
//			logger.info("site admin loginLogStatus----->>"+loginLogStatus);
			return LoginConstants.LOGIN_ERROR_PASSWORD;
		}
		
		//授权
		String sessionId = getSessionIdForSiteAdmin(userBase.getId()+"");
		logger.info("loginSiteAdmin  setCookie cookieName="+LoginConstants.SESSION_ID_NAME+ "; domain="+(siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN)+" ; sessionId="+sessionId);
		logger.info("loginSiteAdmin  setCookie cookieName="+LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME+ "; domain="+(siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN)+" ; userBase.getId()="+userBase.getId());
		
		
		CookieUtil.setPageCookie(response, LoginConstants.SESSION_ID_NAME, sessionId, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.setPageCookie(response, LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME, 
				String.valueOf(userBase.getId()), siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		//modified by Chris Gao 06/20/13
		if(userBase.getErrorCount().intValue()>0){
			userBase.setErrorCount(0);
			this.updateErrorCount(userBase);
		}
		saveSiteAdminLoginLog(site, userBase, request, LoginConstants.LOGIN_ERROR_SUCCESS, loginName);    //added by jack 08/09/13
		return LoginConstants.LOGIN_ERROR_SUCCESS;
	}
	
	@Override
	public int loginSiteAdmin(SiteBase site, UserBase userBase, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String siteBrand = site.getSiteSign();
		//授权
		String sessionId = getSessionIdForSiteAdmin(userBase.getId()+"");
		logger.info("loginSiteAdmin  setCookie cookieName="+LoginConstants.SESSION_ID_NAME+ "; domain="+(siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN)+" ; sessionId="+sessionId);
		logger.info("loginSiteAdmin  setCookie cookieName="+LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME+ "; domain="+(siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN)+" ; userBase.getId()="+userBase.getId());
		
		
		CookieUtil.setPageCookie(response, LoginConstants.SESSION_ID_NAME, sessionId, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.setPageCookie(response, LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME, 
				String.valueOf(userBase.getId()), siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		if(userBase.getErrorCount().intValue()>0){
			userBase.setErrorCount(0);
			userBase.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(userBase);
		}
		return LoginConstants.LOGIN_ERROR_SUCCESS;
	}

	@Override
	public int loginSysAdmin(String loginName, String loginPass,
			String authCode, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		SystemUser systemUser = this.getSystemUser(loginName);
		if (systemUser == null) {
			saveSystemLoginLog(systemUser, request, LoginConstants.LOGIN_ERROR_USER_NOT_EXIST, loginName);   //added by jack 08/09/2013
			return LoginConstants.LOGIN_ERROR_USER_NOT_EXIST;
		}
		
		if (systemUser.getErrorCount().intValue() >= ConstantUtil.MAX_ERROR_COUNT_SYSTEM.intValue()) {
			//modified by Chris Gao 06/20/13
			//Date autoUnlockDate = DateUtil.getGmtDateByAfterHour(ConstantUtil.LIMIT_LOGIN_HOUR_SYSTEM);
			//if (DateUtil.getGmtDate(null).before(autoUnlockDate)) {
			if (DateUtil.getGmtDate(null).getTime() < systemUser.getLastErrorTime().getTime()
					+ ConstantUtil.LIMIT_LOGIN_HOUR_SYSTEM * 3600 * 1000L) {
				saveSystemLoginLog(systemUser, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);   //added by jack 08/09/2013
				return LoginConstants.LOGIN_ERROR_USER_LOCKED;
			}
			else {
				systemUser.setErrorCount(0);
				this.updateErrorCount(systemUser);
			}
		}
		
		if (!MD5.encodePassword(loginPass, "MD5").equalsIgnoreCase(systemUser.getLoginPass())) {
			systemUser.setErrorCount(systemUser.getErrorCount() + 1);
			systemUser.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(systemUser);
			saveSystemLoginLog(systemUser, request, LoginConstants.LOGIN_ERROR_PASSWORD, loginName);   //added by jack 08/09/2013
//			boolean loginLogStatus=saveSystemLoginLog(systemUser,request);
//			logger.info("system user loginLogStatus----->>"+loginLogStatus);
			return LoginConstants.LOGIN_ERROR_PASSWORD;
		}
		
		//授权
		String sessionId = getSessionIdForSystemUser(systemUser);
		CookieUtil.setPageCookie(response, LoginConstants.SESSION_ID_NAME, sessionId, 
				SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.setPageCookie(response, LoginConstants.SYSTEM_USER_SESSION_ID_NAME, 
				String.valueOf(systemUser.getId()), SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		saveSystemLoginLog(systemUser, request, LoginConstants.LOGIN_ERROR_SUCCESS, loginName);   //added by jack 08/09/2013
		return LoginConstants.LOGIN_ERROR_SUCCESS;
	}

	@Override
	public boolean logout(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		CookieUtil.deleteCookie(response, LoginConstants.USER_SESSION_ID_NAME, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.deleteCookie(response, LoginConstants.SESSION_ID_NAME, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		return true;
	}

	@Override
	public boolean logoutSiteAdmin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String siteBrand = SiteIdentifyUtil.getCurrentBrand();
		CookieUtil.deleteCookie(response, LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.deleteCookie(response, LoginConstants.SESSION_ID_NAME, 
				siteBrand + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		return true;
	}

	@Override
	public boolean logoutSysAdmin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		CookieUtil.deleteCookie(response, LoginConstants.SYSTEM_USER_SESSION_ID_NAME, 
				SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		CookieUtil.deleteCookie(response, LoginConstants.SESSION_ID_NAME, 
				SiteIdentifyUtil.MEETING_CENTER_DOMAIN);
		return true;
	}

	@Override
	public boolean isLogined(HttpServletRequest request) {
		String domain = SiteIdentifyUtil.getCurrentBrand()+ "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
//		String sessionId = CookieUtil.getCookie(request, LoginConstants.SESSION_ID_NAME);
		String sessionId = CookieUtil.getCookieByDomain(request, LoginConstants.SESSION_ID_NAME,domain);
		if (sessionId == null) {
			return false;
		}
		

//		String suid = CookieUtil.getCookie(request, LoginConstants.USER_SESSION_ID_NAME);
		String uid = CookieUtil.getCookieByDomain(request, LoginConstants.USER_SESSION_ID_NAME,domain);
		if (uid == null) {
			return false;
		}
		
		UserBase userBase=userService.getUserBaseById(IntegerUtil.parseInteger(uid));
		if(userBase==null){
			return false;
		}
		if(!ConstantUtil.USERTYPE_USERS.equals(userBase.getUserType())){
			return false;
		}
		String validSessionId = this.getSessionIdForUserBase(userBase);
		if (!sessionId.equalsIgnoreCase(validSessionId)) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isSiteAdminLogined(HttpServletRequest request) {

		String domain = SiteIdentifyUtil.getCurrentBrand()+ "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN;

//		String sessionId = CookieUtil.getCookie(request, LoginConstants.SESSION_ID_NAME);
		String sessionId = CookieUtil.getCookieByDomain(request, LoginConstants.SESSION_ID_NAME,domain);

		logger.info("isSiteAdminLogined  getCookie cookieName="+LoginConstants.SESSION_ID_NAME+ "; domain="+domain+" ; sessionId="+sessionId);
		
		if (sessionId == null) {
			return false;
		}

//		String suid = CookieUtil.getCookie(request, LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME);

		String suid = CookieUtil.getCookieByDomain(request, LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME,domain);

		logger.info("isSiteAdminLogined getCookie  cookieName="+LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME+ "; domain="+domain+" ; suid="+suid);
		if (suid == null) {
			return false;
		}
		String validSessionId = this.getSessionIdForSiteAdmin(suid);
//		if (!sessionId.equalsIgnoreCase(validSessionId)) {
//			return false;
//		}
		//暂用cookie检验方法
		if(!CookieUtil.cookieExistByDomain(request,domain, LoginConstants.SESSION_ID_NAME, validSessionId)){
			return false;
		}
		return true;
	}

	@Override
	public boolean isSysAdminLogined(HttpServletRequest request) {

		String domain =  SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
//		String sessionId = CookieUtil.getCookie(request, LoginConstants.SESSION_ID_NAME);
		String sessionId = CookieUtil.getCookieByDomain(request, LoginConstants.SESSION_ID_NAME,domain);
		if (sessionId == null) {
			return false;
		}

//		String said = CookieUtil.getCookie(request, LoginConstants.SYSTEM_USER_SESSION_ID_NAME);
		String said = CookieUtil.getCookieByDomain(request, LoginConstants.SYSTEM_USER_SESSION_ID_NAME,domain);
		if (said == null) {
			return false;
		}

		//added by Chris Gao 06/13/13
		SystemUser systemUser = userService.getSystemUserById(IntegerUtil.parseInteger(said));
		if (systemUser == null) {
			return false;
		}

		String validSessionId = this.getSessionIdForSystemUser(systemUser);
		if (!sessionId.equalsIgnoreCase(validSessionId)) {
			return false;
		}


		return true;
	}

	private SystemUser getSystemUser(String loginName){
		SystemUser user = null;
		try {
			user = libernate.getEntityCustomized(SystemUser.class, 
					"select * from t_system_user where del_flag=? and login_name=?", 
					new Object[]{ConstantUtil.DELFLAG_UNDELETE, loginName});
		} catch (SQLException e) {
			logger.error("getSystemUser error", e);
		}
		return user;
	}
	
	private void updateErrorCount(SystemUser user) {
		try {
			libernate.updateEntity(user, "error_count", "last_error_time");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateErrorCount(UserBase user) {
		try {
			if (user.getErrorCount() >= ConstantUtil.MAX_ERROR_COUNT_USER) {
				user.setUserStatus(ConstantUtil.USER_STATU_LOCK);
			}
			libernate.updateEntity(user, "error_count", "last_error_time", "user_status");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//modified by Chris Gao 06/13/13
	//private String getSessionIdForSystemUser(String id) {
	private String getSessionIdForSystemUser(SystemUser systemUser) {
		return MD5.encodePassword(systemUser.getId() + "-systemAdmin-" + 
				LoginConstants.SYSTEM_USER_SESSION_ID_KEY + "-" + systemUser.getLoginPass(), "MD5");
	}
	
	private String getSessionIdForSiteAdmin(String id) {
		return MD5.encodePassword(id + "-"+ SiteIdentifyUtil.getCurrentBrand() + "-" +
				LoginConstants.SITE_ADMIN_SESSION_ID_KEY, "MD5");
	}
	
	//modified by Chris Gao 06/13/13
	//private String getSessionIdForUserBase(String id) {
	private String getSessionIdForUserBase(UserBase userBase) {
		return MD5.encodePassword(userBase.getId() + "-"+ SiteIdentifyUtil.getCurrentBrand() + "-" +
				LoginConstants.USER_SESSION_ID_KEY + "-" + userBase.getLoginPass(), "MD5");
	}
	
//	private boolean saveSystemLoginLog(SystemUser systemUser,HttpServletRequest request){
//		if(systemUser==null){
//			return false;
//		}
//		LoginLog loginLog=new LoginLog();
//		loginLog.setSiteId(0);
//		loginLog.setUserId(systemUser.getId());
//		loginLog.setUserType(systemUser.getSysType());
//		loginLog.setLoginTime(DateUtil.getGmtDate(null));
//		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
//		loginLog.setLoginIp(StringUtil.getIpAddr(request));
//		try {
//			libernate.saveEntity(loginLog);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//	
//	private boolean saveSiteAdminOrUserLoginLog(UserBase userBase,HttpServletRequest request){
//		if(userBase==null){
//			return false;
//		}
//		LoginLog loginLog=new LoginLog();
//		loginLog.setSiteId(userBase.getSiteId());
//		loginLog.setUserId(userBase.getId());
//		loginLog.setUserType(userBase.getUserType());
//		loginLog.setLoginTime(DateUtil.getGmtDate(null));
//		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
//		loginLog.setLoginIp(StringUtil.getIpAddr(request));
//		try {
//			libernate.saveEntity(loginLog);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 保存系统管理员登录日志
	 * wangyong
	 * 2013-8-9
	 */
	private boolean saveSystemLoginLog(SystemUser systemUser,HttpServletRequest request, int errorCode, String loginName){
//		LoginLog loginLog = new LoginLog();
//		loginLog.setSiteId(0);
//		loginLog.setUserId(systemUser == null ? 0 : systemUser.getId());
//		loginLog.setUserType(systemUser == null ? ConstantUtil.USERTYPE_SYSTEM : systemUser.getSysType());
//		loginLog.setLoginTime(DateUtil.getGmtDate(null));
//		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
//		loginLog.setLoginIp(StringUtil.getIpAddr(request));
//		loginLog.setLoginName(loginName);
//		loginLog.setLoginStatus(errorCode);
//		return loginLogService.savaSysLoginLog(loginLog);
		return saveLoginLog(request, errorCode, loginName, 0, 
				systemUser == null ? 0 : systemUser.getId(), 
						systemUser == null ? ConstantUtil.USERTYPE_SYSTEM : systemUser.getSysType());
	}
	
	/**
	 * 保存企业用户登录日志
	 * wangyong
	 * 2013-8-9
	 */
	private boolean saveUserLoginLog(SiteBase site, UserBase userBase, HttpServletRequest request, int errorCode, String loginName){
//		LoginLog loginLog = new LoginLog();
//		loginLog.setSiteId(site == null ? 0 : site.getId());
//		loginLog.setUserId(userBase == null ? 0 : userBase.getId());
//		loginLog.setUserType(userBase == null ? ConstantUtil.USERTYPE_USERS : userBase.getUserType());
//		loginLog.setLoginTime(DateUtil.getGmtDate(null));
//		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
//		loginLog.setLoginIp(StringUtil.getIpAddr(request));
//		loginLog.setLoginName(loginName);
//		loginLog.setLoginStatus(errorCode);
//		return loginLogService.savaUserLoginLog(loginLog);
		return saveLoginLog(request, errorCode, userBase==null?loginName:userBase.getTrueName(), 
				site == null ? 0 : site.getId(),
						userBase == null ? 0 : userBase.getId(),
								userBase == null ? ConstantUtil.USERTYPE_USERS : userBase.getUserType());
	}
	
	/**
	 * 保存站点管理员登录日志
	 * wangyong
	 * 2013-8-9
	 */
	private boolean saveSiteAdminLoginLog(SiteBase site, UserBase userBase, HttpServletRequest request, int errorCode, String loginName){
//		LoginLog loginLog = new LoginLog();
//		loginLog.setSiteId(site == null ? 0 : site.getId());
//		loginLog.setUserId(userBase == null ? 0 : userBase.getId());
//		loginLog.setUserType(userBase == null ? ConstantUtil.USERTYPE_ADMIN : userBase.getUserType());
//		loginLog.setLoginTime(DateUtil.getGmtDate(null));
//		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
//		loginLog.setLoginIp(StringUtil.getIpAddr(request));
//		loginLog.setLoginName(loginName);
//		loginLog.setLoginStatus(errorCode);
//		return loginLogService.savaUserLoginLog(loginLog);
		return saveLoginLog(request, errorCode, loginName, 
				site == null ? 0 : site.getId(), 
						userBase == null ? 0 : userBase.getId(), 
								userBase == null ? ConstantUtil.USERTYPE_ADMIN : userBase.getUserType());
	}
	
	/**
	 * 保存登录日志
	 * wangyong
	 * 2013-8-12
	 */
	private boolean saveLoginLog(HttpServletRequest request, int errorCode, String loginName, int siteId, int userId, int userType){
		LoginLog loginLog = new LoginLog();
		loginLog.setSiteId(siteId);
		loginLog.setUserId(userId);
		loginLog.setUserType(userType);
		loginLog.setLoginTime(DateUtil.getGmtDate(null));
		loginLog.setLogoutTime(DateUtil.getGmtDate(null));
		loginLog.setLoginIp(StringUtil.getIpAddr(request));
		loginLog.setLoginName(loginName);
		loginLog.setLoginStatus(errorCode);
		return loginLogService.savaSysLoginLog(loginLog);
	}
	
	@Override
	public int loginForMobile(String loginName, String loginPass,
			SiteBase siteBase, HttpServletRequest request) {
		
		if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(loginPass) || siteBase==null) {
			return PAD_ERROR_USER_SUCCESS;
		}
		UserBase userBase = userService.getSiteUserByLoginName(siteBase.getId(), loginName);
		if(userBase==null){
			//saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_NOT_EXIST, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_NAME_OR_PASSWORD;
		}
		if(userBase.getUserStatus()==0){
			//saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_LOAKED;
		}
		if(userBase.isExpried()){
			//saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_EXPRIED, loginName);    //added by jack 08/09/13
			return PAD_ERROR_USER_EXPRIED;
		}
		if (userBase.getErrorCount().intValue() >= ConstantUtil.MAX_ERROR_COUNT_USER.intValue()) {
			if (DateUtil.getGmtDate(null).getTime() < userBase.getLastErrorTime().getTime()
					+ ConstantUtil.LIMIT_LOGIN_HOUR_USER * 3600 * 1000L) {
				//saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_USER_LOCKED, loginName);    //added by jack 08/09/13
				return PAD_ERROR_USER_LOAKED;
			}
			else {
				userBase.setErrorCount(0);
				userBase.setUserStatus(ConstantUtil.USER_STATU_UNLOCK);
				this.updateErrorCount(userBase);
			}
		}
		if (!MD5.encodePassword(loginPass, "MD5").equalsIgnoreCase(userBase.getLoginPass())) {
			userBase.setErrorCount(userBase.getErrorCount() + 1);
			userBase.setLastErrorTime(DateUtil.getGmtDate(null));
			this.updateErrorCount(userBase);
			return PAD_ERROR_USER_NAME_OR_PASSWORD;
		}
		if(userBase.getErrorCount().intValue()>0){
			userBase.setErrorCount(0);
			this.updateErrorCount(userBase);
		}
		//saveUserLoginLog(siteBase, userBase, request, LoginConstants.LOGIN_ERROR_SUCCESS, loginName);    //added by jack 08/09/13
		return PAD_ERROR_USER_SUCCESS;
	}

//	private boolean saveSiteUserLoginLog(UserBase userBase,HttpServletRequest request){
//		return false;
//	}
	
}
