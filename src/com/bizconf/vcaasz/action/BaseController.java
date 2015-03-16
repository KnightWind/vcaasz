package com.bizconf.vcaasz.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.OrgService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.libernate.liberc.annotation.Disabled;

@Disabled
public abstract class BaseController {

	@Autowired
	EventLogService eventLogService;
	@Autowired
	OrgService orgService;

	/**
	 * 设置提示信息
	 * 
	 * @param request
	 * @param message
	 */

	protected void setInfoMessage(HttpServletRequest request, String message) {
		if (request.getAttribute("errorMessage") != null) {
			throw new IllegalStateException("errorMessage is set before.");
		}
		request.setAttribute("infoMessage", message);
	}

	/**
	 * 设置错误信息
	 * 
	 * @param request
	 * @param message
	 */
	protected void setErrMessage(HttpServletRequest request, String message) {
		if (request.getAttribute("infoMessage") != null) {
			throw new IllegalStateException("infoMessage is set before.");
		}
		request.setAttribute("errorMessage", message);
	}

	protected String getLang(HttpServletRequest request) {
		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		String lang = CookieUtil.getCookieByDomain(request,
				ConstantUtil.COOKIE_LANG_KEY, domain);
		if (lang != null && !lang.equals("")) {
			return lang;
		} else {
			return request.getLocale().getLanguage();
		}

	}

	protected void setEmpowerFlag(HttpServletRequest request,
			EmpowerConfig userEmpower) {
		if (userEmpower != null) {
			if (userEmpower.getShareMediaFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isShareMediaFlag",
						SiteConstant.EMPOWER_ENABLED);
			}
			if (userEmpower.getRecordFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isRecordFlag",
						SiteConstant.EMPOWER_ENABLED);
			}
			if (userEmpower.getPhoneFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isPhoneFlag",
						SiteConstant.EMPOWER_ENABLED);
			}
			if (userEmpower.getOutCallFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isOutCallFlag", SiteConstant.EMPOWER_ENABLED);
			}
			if (userEmpower.getVideoFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isVideoFlag",
						SiteConstant.EMPOWER_ENABLED);
			}
			if (userEmpower.getAudioFlag().intValue() == SiteConstant.EMPOWER_ENABLED) {
				request.setAttribute("isAudioFlag",
						SiteConstant.EMPOWER_ENABLED);
			}
			request.setAttribute("videoNumber", userEmpower.getVideoNumber());
			request.setAttribute("audioNumber", userEmpower.getAudioNumber());
		}
	}

	/**
	 * 系统管理员操作日志
	 * 
	 * @param updateFlag
	 *            成功或失败
	 * @param sysUser
	 * @param optDesc
	 *            操作描述
	 * @param optObject
	 *            操作对象
	 * @param request
	 *            Request对象
	 * @return
	 */
	protected boolean saveSystemEventLog(boolean updateFlag,
			SystemUser systemUser, Integer funcModule, String optDesc,
			Object object, HttpServletRequest request) {
		boolean flag = false;
		if (updateFlag) {
			flag = eventLogService.saveSystemEventLog(systemUser, funcModule,
					optDesc, EventLogConstants.EVENTLOG_SECCEED, object,
					request);
		} else {
			flag = eventLogService.saveSystemEventLog(systemUser, funcModule,
					optDesc, EventLogConstants.EVENTLOG_FAIL, object, request);
		}
		return flag;
	}

	/**
	 * 站点管理员操作日志
	 * 
	 * @param updateFlag
	 *            成功或失败
	 * @param userBase
	 * @param module
	 *            日志模块,ConstantUtil中的funcModule
	 * @param optDesc
	 *            操作描述
	 * @param optObject
	 *            操作对象
	 * @param request
	 *            Request对象
	 * @return
	 */

	protected boolean saveAdminEventLog(boolean updateFlag, UserBase user,
			Integer funcModule, String optDesc, Object object,
			HttpServletRequest request) {
		boolean flag = false;
		if (updateFlag) {
			flag = eventLogService.saveAdminEventLog(user, funcModule, optDesc,
					EventLogConstants.EVENTLOG_SECCEED, object, request);
		} else {
			flag = eventLogService.saveAdminEventLog(user, funcModule, optDesc,
					EventLogConstants.EVENTLOG_FAIL, object, request);
		}
		return flag;
	}

	/**
	 * 系统管理员管理用户站点记录操作日志 1. 站点管理员下记录操作日志时调用 2. 站点管理时，有可能是系统管理员在系统管理员页面管理站点 3.
	 * 系统管理员为空，则代表该操作为站点管理员的操作
	 * 
	 * @param updateFlag
	 *            成功或失败
	 * @param userBase
	 *            站点管理员
	 * @param systemUser
	 *            系统管理员
	 * @param module
	 *            日志模块,ConstantUtil中的funcModule
	 * @param optDesc
	 *            操作描述
	 * @param object
	 *            操作对象
	 * @param request
	 *            Request对象
	 * @return
	 */

	protected boolean sysHelpAdminEventLog(boolean updateFlag,
			SystemUser systemUser, UserBase user, Integer sysUserFuncModule, Integer userFuncModule,
			String optDesc, Object object, HttpServletRequest request) {
		boolean flag = false;
		 //20130515 先暂不记录系统管理员管理用户站点记录操作日志
		 if(systemUser != null){
			 flag = saveSystemEventLog(updateFlag, systemUser, sysUserFuncModule,
			 "系统管理员管理用户站点，" + optDesc, object, request);
		 }else{
			 flag = saveAdminEventLog(updateFlag, user, userFuncModule, optDesc, object,
				request);
		 }
		return flag;
	}
	
	
//	/**
//	 * 创建根组织机构
//	 * 1.创建站点成功后调用，为站点创建根组织机构
//	 * 2.当站点无根组织机构时调用
//	 * shhc
//	 * 2013-5-20
//	 */
//	protected boolean saveFirstSiteOrg(SiteBase site){
//		SiteOrg siteOrg = new SiteOrg();
//		siteOrg.setOrgName(site.getSiteName());
//		siteOrg.setOrgDesc("");
//		siteOrg.setParentId(0);
//		UserBase currentSiteAdmin = new UserBase();
//		currentSiteAdmin.setId(0);
//		currentSiteAdmin.setSiteId(site.getId());
//		SiteOrg org = orgService.addSiteOrg(siteOrg, currentSiteAdmin);
//		if(org != null && org.getId() > 0){
//			return true;
//		}
//		return false;
//	}
}
