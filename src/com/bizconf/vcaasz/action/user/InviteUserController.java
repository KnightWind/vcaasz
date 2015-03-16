package com.bizconf.vcaasz.action.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;


@ReqPath("inviteUser")
public class InviteUserController extends BaseController {

	@Autowired
	ConfUserService confUserService;
	
	@Autowired
	ConfUserLogic confUserLogic;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ConfService confService;
	
	@Autowired
	SiteService siteService;
	
	@Autowired
	EmailService emailService;
	
	public Object doRequest(@CParam("confId") int confId, @CParam("back") int back,LiberInvocation inv) {
		
		UserBase user = userService.getCurrentUser(inv.getRequest());
		if(user == null){
			user = userService.getCurrentSiteAdmin(inv.getRequest());
		}
		
		ConfBase conf = confService.getConfBasebyConfId(confId);
		if (conf == null) {
			inv.getRequest().setAttribute("inviteflag", "1");
			inv.getRequest().setAttribute("errorCode", ConfConstant.INVITE_ERROR_CODE);
			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
		}
		
		//for data security
//		if (user == null && conf.getPublicFlag().intValue() != ConfConstant.CONF_PUBLIC_FLAG_TRUE.intValue()) {
//			inv.getRequest().setAttribute("inviteflag", "1");
//			inv.getRequest().setAttribute("errorCode", ConfConstant.INVITE_ERROR_CODE);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
		if (user == null || user.getSiteId().intValue() != conf.getSiteId().intValue()) {
			inv.getRequest().setAttribute("inviteflag", "1");
			inv.getRequest().setAttribute("errorCode", ConfConstant.INVITE_ERROR_CODE);
			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
		}
		if(back!=0){
			inv.getRequest().setAttribute("isBack", false);
			
		}else{
			inv.getRequest().setAttribute("isBack", true);
		}
		inv.getRequest().setAttribute("user", user);
		inv.getRequest().setAttribute("conf", conf);
		inv.getRequest().setAttribute("confUserList", confUserService.getConfInviteUserList(confId)) ;
		return new ActionForward.Forward("/jsp/user/conf_invite_user_list.jsp");
//		return new ActionForward.Forward("/jsp/user/conf_invite_user_list.jsp");
	}
	
	@AsController
	public Object sysview(@CParam("confId") int confId, LiberInvocation inv) {
		ConfBase conf = confService.getConfBasebyConfId(confId);
		if (conf == null) {
			return null;
		}
		
		inv.getRequest().setAttribute("confUserList", confUserService.getConfInviteUserList(confId)) ;
		return new ActionForward.Forward("/jsp/system/conf_invite_user_list.jsp");
	}
	
	
	@AsController
	public Object recv(@CParam("cuid") int confUserId, @CParam("confId") int confId, LiberInvocation inv) {
		ConfUser confUser = confUserLogic.getConfUser(confUserId);
		int result = 0;
		
		if (confUser == null || confUser.getConfId().intValue() != confId) {
			result = 1;
		}
		result = confUserService.recvInvite(confUserId) ? 0 : 2;
		
		ConfBase confBase = confService.getConfBasebyConfId(confId);
		if (confBase != null) {
			//2013.6.19 验收报告修复 时间为会议时区时间（以前为站点时区时间）
			SiteBase siteBase = siteService.getSiteBaseById(confBase.getSiteId());
			ConfBase conflib = confService.getConfBasebyConfId(confId);
			//提醒中显示安全会议号
			if(confUser.getUserId()==null || confUser.getUserId()<1)confUser.setUserId(-1);
			emailService.confRemind(confUser, conflib);//接受 参加会议后 邮件日历提示
			this.convertConfTime2LocalTime(confBase);
			inv.getRequest().setAttribute("confBase", confBase);
			inv.getRequest().setAttribute("confUser", confUser);
			inv.getRequest().setAttribute("siteBase", siteBase);
//			inv.getRequest().setAttribute("timeZoneDesc", siteBase.getTimeZoneDesc());
			inv.getRequest().setAttribute("timeZoneDesc", confBase.getTimeZoneDesc());
		}
		inv.getRequest().setAttribute("user", userService.getCurrentUser(inv.getRequest()));
		if (result != 0) {
			this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("bizconf.jsp.user.accept.invite.error"));
		}
		return new ActionForward.Forward("/jsp/user/conf_invite_recv.jsp");
	}
	
	@AsController
	public Object refuse(@CParam("cuid") int confUserId, @CParam("confId") int confId, LiberInvocation inv) {
		ConfUser confUser = confUserLogic.getConfUser(confUserId);
		int result = 0;
		if (confUser == null || confUser.getConfId().intValue() != confId) {
			result = 1;
		}
		
		result = confUserService.refuseInvite(confUserId) ? 0 : 2;
		
		ConfBase confBase = confService.getConfBasebyConfId(confId);
		if (confBase != null) {
			//2013.6.19 验收报告修复 时间为会议时区时间（以前为站点时区时间）
			SiteBase siteBase = siteService.getSiteBaseById(confBase.getSiteId());
//			this.convertConfTime2LocalTime(confBase, siteBase);
			this.convertConfTime2LocalTime(confBase);
			inv.getRequest().setAttribute("confBase", confBase);
			inv.getRequest().setAttribute("confUser", confUser);
			inv.getRequest().setAttribute("siteBase", siteBase);
//			inv.getRequest().setAttribute("timeZoneDesc", siteBase.getTimeZoneDesc());
			inv.getRequest().setAttribute("timeZoneDesc", confBase.getTimeZoneDesc());
		}
		
		if (result != 0) {
			this.setErrMessage(inv.getRequest(), ResourceHolder.getInstance().getResource("bizconf.jsp.user.refuse.invite.error"));
		}
		return new ActionForward.Forward("/jsp/user/conf_invite_refuse.jsp");
	}
	
//	private void convertConfTime2LocalTime(ConfBase confBase, SiteBase siteBase) {
//		Date localDate = DateUtil.getOffsetDateByGmtDate(confBase.getStartTime(), 
//				(long)siteBase.getTimeZone().intValue());
//		Date localDate2 = DateUtil.getOffsetDateByGmtDate(confBase.getEndTime(), 
//				(long)siteBase.getTimeZone().intValue());
//		confBase.setStartTime(localDate);
//		confBase.setEndTime(localDate2);
//	}
	
	private void convertConfTime2LocalTime(ConfBase confBase) {
		Date localDate = DateUtil.getOffsetDateByGmtDate(confBase.getStartTime(), 
				(long)confBase.getTimeZone().intValue());
		Date localDate2 = DateUtil.getOffsetDateByGmtDate(confBase.getEndTime(), 
				(long)confBase.getTimeZone().intValue());
		confBase.setStartTime(localDate);
		confBase.setEndTime(localDate2);
	}
}
