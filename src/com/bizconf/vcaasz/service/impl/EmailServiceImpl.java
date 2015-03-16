package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.component.email.EmailContentGenerator;
import com.bizconf.vcaasz.component.email.EmailUIDGenerator;
import com.bizconf.vcaasz.component.email.EmailUtil;
import com.bizconf.vcaasz.component.email.model.SendMail;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EmailConstant;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.logic.EmailLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.vcaasz.util.UrlUtils;
@Service
public class EmailServiceImpl extends BaseService implements EmailService{
	
	/**邮件服务获取业务数据接口*/
	@Autowired
	private EmailLogic emailLogic; 
	
	@Autowired
	ConfLogic confLogic;
	
	@Autowired
	private ConfUserLogic confUserLogic;
	@Autowired
	UserService userService;
	
	@Autowired
	DataCenterService dcService;
	
	//display:none;
	public static String CSSHOLDER = "style='display:none;'";
	@Override
	public boolean createSiteEmail(SiteBase site,UserBase admin) {
		boolean createStatus=false;
		if(site!=null && admin!=null){
			try{
				
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(admin.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				if(site!=null && !site.getChargeMode().equals(2)){
					site.setLicense(emailLogic.getSiteLicenseNum(site.getId()));
				}
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine()); //域名的获取
				datas.put("userBase", admin);
				datas.put("siteBase",site);
				datas.put("timezone", site.getTimeZoneDesc());
				datas.put("entimezone", site.getTimeZoneDesc("en-us"));
				
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CRAETE_SITE_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(site.getId());
				emailInfo.setEmailSubject("欢迎您使用商会云会议平台 / Welcome to using confcloud video conference platform");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(admin.getUserEmail());
				
				if(StringUtil.isEmpty(site.getMarketorEmail())){
//					emailInfo.set
				}
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus;
	}
	
	
	/**
	 * 修改站点信息
	 */
	@Override
	public boolean updateSiteEmail(SiteBase site,UserBase admin) {
		boolean updateStatus=false;
		if(site!=null && admin!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(admin.getSiteId());
				if(site!=null && !site.getChargeMode().equals(2)){
					site.setLicense(emailLogic.getSiteLicenseNum(site.getId()));
				}
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine()); //域名的获取
				datas.put("userBase", admin);
				datas.put("siteBase",site);
				datas.put("timezone", site.getTimeZoneDesc());
				datas.put("entimezone", site.getTimeZoneDesc("en-us"));
				
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.UPDATE_SITE_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(site.getId());
				emailInfo.setEmailSubject("企业站点管理平台 /Enterprise Site info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(admin.getUserEmail());
				saveEmailInfo(emailInfo);
				updateStatus = true;
			}catch(Exception e){
				updateStatus = false;
				e.printStackTrace();
			}
		}
		return updateStatus;
	}
	
	
	
	@Override
	public boolean saveEmailInfo(EmailInfo emailInfo){
		boolean saveStatus=false;
		try {
			libernate.saveEntity(emailInfo);
			saveStatus=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveStatus;
	}

	@Override
	public List<EmailInfo> getUnSendEmailListByStartId(Long startId, Integer limit) {
		
		List<EmailInfo> emailList=null;
		if (startId != null && startId.intValue() >= 0) {
			String condition = " id > ?  and (send_flag = ? or send_flag = ?) and send_count <= retry_count";
			Object[] values = new Object[3];
			try {
				if (limit == null || limit.intValue() <= 0) {
					limit = ConstantUtil.PAGESIZE_DEFAULT;
				}
				values[0] = startId;
				values[1] = ConstantUtil.EMAIL_FLAG_UNSEND;
				values[2] = ConstantUtil.EMAIL_FLAG_FAIL;
				emailList = DAOProxy.getLibernate().getEntityListWithCondition(condition, values, EmailInfo.class, 1, limit);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				values = null;
				condition = null;
			}
		}
		return emailList;
	}

	@Override
	public boolean updateSucceedEmailById(Long id) {
		boolean updateFlag=false;
		if (id != null && id.intValue() > 0){
			String updateSql="update t_email_info set send_flag = ? , send_count = send_count + 1 ,send_time = ?  where id = ? ";
			Object[] values = new Object[3];
			try {
				values[0] = ConstantUtil.EMAIL_FLAG_SECCEED;
				values[1] = new Date();
				values[2] = id;
				DAOProxy.getLibernate().executeSql(updateSql, values);
				updateFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				values = null;
				updateSql = null;
			}
		}
		return updateFlag;
	}
	
	@Override
	public boolean updateUnSucceedEmailById(Long id) {
		boolean updateFlag=false;
		if (id != null && id.intValue() > 0){
			String updateSql="update t_email_info set send_flag = ? , send_count = send_count + 1 where id = ? ";
			Object[] values = new Object[2];
			try {
				values[0] = ConstantUtil.EMAIL_FLAG_FAIL;
				values[1] = id;
				DAOProxy.getLibernate().executeSql(updateSql, values);
				updateFlag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				values = null;
				updateSql = null;
			}
		}
		return updateFlag;
	}

	@Override
	public boolean send(Long id) {
		return false;
	}

	@Override
	public boolean send(EmailInfo emailInfo) {
		boolean sendStatus = false;
		if (emailInfo != null) {
			
			
			
			SendMail mailInfo=new SendMail();
			try {
				ConfCycle cycle = null;
				if(emailInfo.getCycleId()>0){
					cycle = libernate.getEntity(ConfCycle.class, emailInfo.getCycleId());
				}
				
				mailInfo.setFromEmail(emailInfo.getFromEmail());
				mailInfo.setFromName(emailInfo.getFromName());
				mailInfo.setServerHost(emailInfo.getServerHost());
				mailInfo.setServerPort(emailInfo.getServerPort());
				mailInfo.setValidate(emailInfo.getValidate());
				mailInfo.setUserName(emailInfo.getUserName());
				mailInfo.setUserPass(emailInfo.getUserPass());

				mailInfo.setToEmail(emailInfo.getEmailAccepter());
				
				mailInfo.setSubject(emailInfo.getEmailSubject());
				mailInfo.setContent(emailInfo.getEmailContent());
				mailInfo.setContentType(emailInfo.getContentType());
				
				
				mailInfo.setCalFlag(emailInfo.getWarnFlag().intValue()==2?true:false);
				mailInfo.setWarnSubject(emailInfo.getWarnSubject());
				mailInfo.setWarnCount(emailInfo.getWarnCount());
				mailInfo.setBeforeMinute(emailInfo.getBeforeMinute());
				mailInfo.setGapMinute(emailInfo.getGapMinute());
				mailInfo.setStartTime(emailInfo.getStratTime());
				mailInfo.setStopTime(emailInfo.getEndTime());
				mailInfo.setTimeZone(emailInfo.getTimeZone());
				
				//设置提醒类型
				mailInfo.setWarnType(emailInfo.getWarnType());
				
				mailInfo.setOrganizerEmail(emailInfo.getOrganizerEmail());
				
				mailInfo.setOrganizerName(emailInfo.getOrganizerName());
				
				mailInfo.setUid(emailInfo.getUid());
				
				
				//mailInfo.setCcEmailList(Arrays.asList(emailInfo.getCcEmails()));
				/*
				
				mailInfo.setAddress(emailInfo.getConfAddress());
				mailInfo.setCalFlag(false);
//				mailInfo.setCcEmailList(ccEmailList)
				
				mailInfo.setContent(mailInfo.getContent());
				mailInfo.setContentType(emailInfo.getContentType());
				mailInfo.setFromEmail(emailInfo.getFromEmail());
				mailInfo.setFromName(emailInfo.getFromName());
				mailInfo.setToEmail(emailInfo.getEmailAccepter());
				
				mailInfo.setGapMinute(emailInfo.getGapMinute());
				mailInfo.setBeforeMinute(emailInfo.getBeforeMinute());
				mailInfo.setServerHost(emailInfo.getServerHost());
				mailInfo.setServerPort(emailInfo.getServerPort());
				*/
//				mail
				sendStatus=EmailUtil.send(mailInfo,cycle);
				if(sendStatus){
//					sendStatus=true;
					
					//将邮件发送标志 改成发送成功标志、同时改一下发送时间 
				}else{
					//将邮件信息的未成功的发送次数据加 1
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				
			}finally{
				
			}
		}
		return sendStatus;
	}
	
	/**
	 * 根据系统邮件设置设置emailInfo对象
	 * @param emailInfo
	 * @param sysConfig
	 */
	private void setSysDefEmailInfo(EmailInfo emailInfo,EmailConfig sysConfig){
	
		emailInfo.setServerHost(sysConfig.getEmailHost());
		emailInfo.setFromEmail(sysConfig.getEmailSender());
		emailInfo.setFromName(sysConfig.getEmailName());
		emailInfo.setUserName(sysConfig.getEmailAccount());
		emailInfo.setUserPass(sysConfig.getEmailPassword());
		emailInfo.setCreateTime(new Date());
		
		emailInfo.setServerPort(EmailConstant.DEF_EMAIL_HOST_PORT);
		emailInfo.setSendFlag(EmailConstant.NOT_SEND_FLAG);
		emailInfo.setValidate(true);
		emailInfo.setContentType(EmailConstant.EMAIL_TYPE_HTML);
		emailInfo.setSendCount(EmailConstant.DEF_SEND_COUNT);
		emailInfo.setRetryCount(EmailConstant.DEF_RETRY_COUNT); //0
		emailInfo.setSendTime(EmailConstant.DEF_SYS_DATE);
		emailInfo.setWarnFlag(EmailConstant.DEF_WARN_FLAG); //0 1 2
		emailInfo.setWarnType(EmailConstant.DEF_WARN_TYPE);
		//emailInfo.setConfAddress("");
		//emailInfo.setWarnSubject("Warn!");
		//emailInfo.setTimeZone("");
		emailInfo.setStratTime(EmailConstant.DEF_SYS_DATE);
		emailInfo.setEndTime(EmailConstant.DEF_SYS_DATE);
		emailInfo.setGapMinute(EmailConstant.DEF_GAP_MINUTE);
		emailInfo.setBeforeMinute(EmailConstant.DEF_BEFORE_MINUTE);
		emailInfo.setWarnCount(EmailConstant.DEF_WARN_COUNT);
	}
	
	/**
	 * 新建系统管理员成功后发送邮件
	 * wangyong
	 * 2013-2-5
	 */
	@Override
	public boolean createSystemUserEmail(SystemUser sysUser) {
		boolean createStatus=false;
		if(sysUser!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(0);
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine()); //域名的获取
				datas.put("user", sysUser);
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CREATE_SYSUSER_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setEmailSubject("系统管理员帐号信息/ System Administrator account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(sysUser.getEmail());
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus; 
	}
	
	/**
	 * 修改系统管理员成功后发送邮件
	 * wangyong
	 * 2013-2-5
	 */
	public boolean updateSystemUserEmail(SystemUser sysUser){
		boolean updateStatus=false;
		if(sysUser!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(0);
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("user", sysUser);
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.UPDATE_SYSUSER_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setEmailSubject("系统管理员帐号修改信息/ System Administrator account fixed info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(sysUser.getEmail());
				saveEmailInfo(emailInfo);
				updateStatus = true;
			}catch(Exception e){
				updateStatus = false;
				e.printStackTrace();
			}
		}
		return updateStatus; 
	}
	
	
	/**
	 * 重设密码
	 */
	@Override
	public void resetPasswordEmail(SystemUser user,String url) {
		try{
			EmailConfig sysConfig = emailLogic.getSysEmailConfig(0);
			EmailInfo emailInfo =new EmailInfo();
			//获取邮件内容
			Map<String, Object> datas = new HashMap<String,Object>();
			datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());
			datas.put("user", user);
			datas.put("url", url);
			datas.put("hours", ConstantUtil.PASSWORD_URL_EXPIRE_HOURS);
			String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.SYSUSER_RESETPWD_TEMP, datas);
			logger.info("the email content:"+emailContent);
			setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
			
			emailInfo.setEmailSubject("ConfCloud-找回密码 /ConfCloud- get my password");
			emailInfo.setEmailContent(emailContent);//设置邮件内容
			emailInfo.setEmailAccepter(user.getEmail());
			saveEmailInfo(emailInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 站点管理员重置密码
	 */
	@Override
	public void resetPasswordEmail(UserBase user,String url) throws Exception{
		
		EmailConfig sysConfig = emailLogic.getSysEmailConfig(user.getSiteId());
		EmailInfo emailInfo =new EmailInfo();
		//获取邮件内容
		Map<String, Object> datas = new HashMap<String,Object>();
		datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());
		datas.put("user", user);
		datas.put("url", url);
		datas.put("hours", ConstantUtil.PASSWORD_URL_EXPIRE_HOURS);
		SiteBase site = new SiteBase();
		site = emailLogic.getSiteBaseById(user.getSiteId());
		String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.SYSUSER_RESETPWD_TEMP, datas);
		logger.info("the email content:"+emailContent);
		setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置

		emailInfo.setEmailSubject("("+site.getSiteName()+")ConfCloud-重置密码/Get back my password");
		emailInfo.setEmailContent(emailContent);//设置邮件内容
		emailInfo.setEmailAccepter(user.getUserEmail());
		saveEmailInfo(emailInfo);
	}
	
	@Override
	public boolean createSiteAdminEmail(UserBase user) {
		boolean createStatus=false;
		if(user!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(user.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine()); //域名的获取
				datas.put("user", user);
				//datas.put("siteBase",site);
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CREATE_SITEADMIN_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(user.getSiteId());
				emailInfo.setEmailSubject("企业管理员帐号信息/Enterprise Administrator account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(user.getUserEmail());
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus;
	}

	@Override
	public Boolean createSiteUser(UserBase user) {
		boolean createStatus=false;
		if(user!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(user.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", emailLogic.getSiteDomine(user.getSiteId())); //域名的获取
				datas.put("user", user);
				
				SiteBase site = emailLogic.getSiteBaseById(user.getSiteId());
				if(site.getHireMode().equals(1)){
					datas.put("capcity", user.getNumPartis());
				}else{
					datas.put("capcity", 0);
				}
				
				
//				datas.put("accessNumber1", BaseConfig.getInstance().getString("access_num1", ""));
//				datas.put("accessNumber2", BaseConfig.getInstance().getString("access_num2", ""));
//				datas.put("accessNumber3", BaseConfig.getInstance().getString("access_num3", ""));
				//datas.put("siteBase",site);
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CREATE_SITEUSER_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(user.getSiteId());
				emailInfo.setEmailSubject("欢迎您使用商会云会议平台/Welcome to using confcloud conference platform");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(user.getUserEmail());
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus;
	}

	@Override
	public boolean updateSiteAdmin(UserBase user) {
		boolean updateStatus=false;
		if(user!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(user.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine()); //域名的获取
				datas.put("user", user);
				//datas.put("siteBase",site);
				String emailContent  =  EmailContentGenerator.getInstance().genContent(EmailConstant.UPDATE_SITEADMIN_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(user.getSiteId());
				emailInfo.setEmailSubject("企业管理员帐号修改信息/Enterprise Administrator account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(user.getUserEmail());
				saveEmailInfo(emailInfo);
				updateStatus = true;
			}catch(Exception e){
				updateStatus = false;
				e.printStackTrace();
			}
		}
		return updateStatus;
	}

	@Override
	public Boolean updateSiteUserEmail(UserBase user) {
		boolean updateStatus=false;
		if(user!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(user.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				
				SiteBase site = emailLogic.getSiteBaseById(user.getSiteId());
				if(site.getHireMode().equals(1)){
					datas.put("capcity", user.getNumPartis());
				}else {
					datas.put("capcity", 0);
				}
				
				datas.put("siteaddress", emailLogic.getSiteDomine(user.getSiteId())); //域名的获取
				datas.put("user", user);
				//datas.put("siteBase",site);
				String emailContent  =  EmailContentGenerator.getInstance().genContent(EmailConstant.UPDATE_SITEUSER_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(user.getSiteId());
				emailInfo.setEmailSubject("企业用户帐号信息/Enterprise user account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(user.getUserEmail());
				saveEmailInfo(emailInfo);
				updateStatus = true;
			}catch(Exception e){
				updateStatus = false;
				e.printStackTrace();
			}
		}
		return updateStatus;
	}
	
	
	/**
	 * 发送会议邀请
	 */
	@Override
	public boolean sendConfInvite(List<UserBase> userBases, ConfBase confInfo) {
		 List<ConfUser> confUsers = new ArrayList<ConfUser>();
		 for (Iterator iterator = userBases.iterator(); iterator.hasNext();) {
			UserBase userBase  = (UserBase) iterator.next();
			ConfUser user = new ConfUser();
			user.setUserEmail(userBase.getUserEmail());
			user.setUserName(userBase.getTrueName());
			user.setHostFlag(ConfConstant.CONF_USER_PARTICIPANT);
			confUsers.add(user);
		}
		return sendInviteToConfUser(confUsers,confInfo);
	}
	
	//加入会议 joinUrl=Base64.encode("join/joinpage?joinType=3&cId=1196&scode=81317079","UTF-8");
	
	
	/**
	 * 发送会议邀请
	 */
	@Override
	public boolean sendInviteToConfUser(List<ConfUser> users, ConfBase confInfo) {
		boolean flag=true;
		if(users!=null && confInfo!=null){
			transferConfDate(confInfo);//将GMT时间转换为当地时间
			 for(ConfUser user:users){
				 confRemind(user, confInfo);
			 }
		}
		return flag;
	}
	 
	
	/**
	 * 创建会议成功后发送会议信息到主持人邮箱
	 * @param user
	 * @param url
	 */
	public boolean createConfEmail(ConfBase conf, ConfCycle confCycle, UserBase currentUser){
		//新需求创建会议只添加日历
		return sendConfRemindEmail(currentUser, conf); 
	}
	
	/**
	 * 设置会议提醒
	 * 添加会议到日历提醒for UserBase
	 */
	@Override
	public boolean sendConfRemindEmail(UserBase user, ConfBase conf) {
		ConfUser confUser = new ConfUser();
		confUser.setUserEmail(user.getUserEmail());
		confUser.setUserName(user.getTrueName());
		confUser.setHostFlag(2);
		confUser.setUserId(user.getId());
		if(user.getId().equals(conf.getCompereUser())){
			confUser.setHostFlag(ConstantUtil.USERROLE_HOST);
		}
		transferConfDate(conf);
		return confRemind(confUser, conf);
	}
	
	
	/**
	 * 
	 * 添加会议到日历提醒for everyBody
	 */
	public boolean remindEmailForAnyOne(String userName,String email,ConfBase conf) {
		ConfUser confUser = new ConfUser();
		confUser.setUserName(userName);
		confUser.setUserEmail(email);
		confUser.setUserId(0);
		confUser.setHostFlag(2);
		
		return confRemind(confUser,conf);
	}
	
	/**
	 * 生成二维码链接
	 * @param confId
	 * @param hostflag
	 * @return
	 */
	private String getQccodeURL(int confId,int hostflag){
		return emailLogic.getQccodeURL(confId, hostflag);
	}
	/**
	 * 会议提醒
	 */
	@Override
	public boolean confRemind(ConfUser confUser, ConfBase conf) {
		boolean flag=false;
		if(confUser!=null && conf!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(conf.getSiteId());
				UserBase creator = emailLogic.getUserBaseById(conf.getCreateUser());
				if(creator == null){
					creator = emailLogic.getUserBaseById(conf.getCompereUser());
				}
				EmailInfo emailInfo =new EmailInfo();
				//System.out.println("systemconfig === "+ sysConfig);
				//String templateContent = getTemplateContentByType(conf.getSiteId(),EmailConstant.TEMPLATE_REMIND);
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				Date confBJStartTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getStartTime(), conf.getTimeZone()));
				Date confBJEndTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getEndTime(), conf.getTimeZone()));
				
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());//域名的获取
				datas.put("user", confUser);
				datas.put("conf", conf);
				datas.put("timezone", conf.getTimeZoneDesc());
				datas.put("entimezone", conf.getTimeZoneDesc("en-us"));
				datas.put("qccodeURL",  getQccodeURL(conf.getId(), confUser.getHostFlag()));
				
				if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
					ConfCycle cycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
					datas.put("cycleMode", confLogic.getCycleMode(cycle, ""));
					datas.put("cycleModeEn", confLogic.getCycleMode(cycle, "en-us"));
					
					datas.put("cycleRepeat", confLogic.getCycleRepeatScope(cycle, ""));
					datas.put("cycleRepeatEn", confLogic.getCycleRepeatScope(cycle, "en-us"));
				}
				datas.put("joinIP1",  BaseConfig.getInstance().getString("access_ip1", ""));
				datas.put("joinIP2",  BaseConfig.getInstance().getString("access_ip2", ""));
				
				datas.put("rcIPs",  dcService.getRCIPs(creator.getDataCenterId()));
				
//				datas.put("accessNumber1", BaseConfig.getInstance().getString("access_num1", ""));
//				datas.put("accessNumber2", BaseConfig.getInstance().getString("access_num2", ""));
//				datas.put("accessNumber3", BaseConfig.getInstance().getString("access_num3", ""));
				
//				datas.put("securityCode", conf.getUserSecure());
//				if(confUser.getHostFlag()!=null && confUser.getHostFlag().intValue() == ConfConstant.CONF_USER_HOST.intValue()){
//					datas.put("securityCode", conf.getCompereSecure());
//				}
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.USER_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}else {
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.HOST_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}
				
				String emailContent = "";
				String currentlang = LanguageHolder.getCurrentLanguage();
				logger.info("current lang is  will gen the email content" + currentlang);
				if(currentlang.startsWith("zh")){
					logger.info("will gen email content with zh-cn lang "); 
					logger.info("will gen email content with zh-cn template name ==  "+EmailConstant.ACCEPT_CONF_REMIND);
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.ACCEPT_CONF_REMIND, datas);
				}else{
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.ACCEPT_CONF_REMIND_EN, datas);
				}
				
				if(emailContent==null){
					emailContent = "会议提醒";
				}	
				logger.info("email content : == "+emailContent);
				
				String mailSubject=conf.getConfName()+"－会议提醒/"+conf.getConfName()+"－Conference Remind";
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					mailSubject = "会议邀请-"+conf.getConfName()+"/"+conf.getConfName()+"－Conference invitation";
				}
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				emailInfo.setSiteId(conf.getSiteId());
				emailInfo.setEmailSubject(mailSubject);
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(confUser.getUserEmail());
				
				if (!confUser.getHostFlag().equals(ConstantUtil.USERROLE_HOST)) {
					//emailInfo.setFromEmail(creator.getUserEmail());
					emailInfo.setFromName(creator.getTrueName());
					emailInfo.setOrganizerEmail(creator.getUserEmail());
					emailInfo.setOrganizerName(creator.getTrueName());
				}
				
				if(!conf.isClientCycleConf() && !conf.isPmi()){
					emailInfo.setUid(EmailUIDGenerator.getUid(conf.getId(),confUser.getUserEmail()));
					
					emailInfo.setWarnFlag(2);
					emailInfo.setWarnSubject(mailSubject);
					emailInfo.setWarnCount(3);
					emailInfo.setBeforeMinute(30);
					emailInfo.setGapMinute(10);
					emailInfo.setStratTime(confBJStartTime);
					emailInfo.setEndTime(confBJEndTime);
					emailInfo.setTimeZone("Asia/Shanghai");
				}
				
				emailInfo.setCycleId(conf.getCycleId());
				saveEmailInfo(emailInfo);
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	/**
	 * 取消会议时给参会者的邮件
	 */
	@Override
	public boolean confCancelEmail(List<ConfUser> confUsers,ConfBase conf) {
		boolean flag=false;
		if(confUsers!=null && conf!=null){
			try{
				transferConfDate(conf);//将GMT时间转换为当地时间
				for(ConfUser user:confUsers){
					cancelConfEmailForConfUser(conf,user);
				}
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 会议修改提醒
	 */
	@Override
	public boolean confModifyEmail(List<ConfUser> confUsers, ConfBase conf) {
		boolean flag=false;
		if(confUsers!=null && conf!=null){
			try{
				transferConfDate(conf);//将GMT时间转换为当地时间
				for(ConfUser user:confUsers){
					modifyConfEmailForConfUser(conf,user);
				}
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	//会议修改提醒
	private boolean  modifyConfEmailForConfUser(ConfBase conf,ConfUser confUser){
		boolean flag=false;
		if(confUser!=null && conf!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(conf.getSiteId());
				UserBase creator = emailLogic.getUserBaseById(conf.getCreateUser());
				if(creator == null){
					creator = emailLogic.getUserBaseById(conf.getCompereUser());
				}
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				Date confBJStartTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getStartTime(), conf.getTimeZone()));
				Date confBJEndTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getEndTime(), conf.getTimeZone()));
				
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());//域名的获取
				datas.put("user", confUser);
				datas.put("conf", conf);
				datas.put("timezone", conf.getTimeZoneDesc());
				datas.put("entimezone", conf.getTimeZoneDesc("en-us"));
				datas.put("qccodeURL",  getQccodeURL(conf.getId(), confUser.getHostFlag()));
				
				if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
					ConfCycle cycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
					datas.put("cycleMode", confLogic.getCycleMode(cycle, ""));
					datas.put("cycleModeEn", confLogic.getCycleMode(cycle, "en-us"));
					
					datas.put("cycleRepeat", confLogic.getCycleRepeatScope(cycle, ""));
					datas.put("cycleRepeatEn", confLogic.getCycleRepeatScope(cycle, "en-us"));
				}
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.USER_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}else {
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.HOST_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}
				datas.put("rcIPs",  dcService.getRCIPs(creator.getDataCenterId()));
				
				String emailContent = "";
				String currentLan = LanguageHolder.getCurrentLanguage();
				if(currentLan.startsWith("zh")){
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.MODIFY_CONF_REMIND, datas);
				}else{
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.MODIFY_CONF_REMIND_EN, datas);
				}
				
				if(emailContent==null){
					emailContent = "会议修改";
				}	
				
				String mailSubject="会议修改通知-"+conf.getConfName()+"/Conference modify remind-"+conf.getConfName();
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					mailSubject = "会议修改通知-"+conf.getConfName()+"/Conference modify remind-"+conf.getConfName();
				}
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				emailInfo.setSiteId(conf.getSiteId());
				emailInfo.setEmailSubject(mailSubject);
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(confUser.getUserEmail());
				
				if (!confUser.getHostFlag().equals(ConstantUtil.USERROLE_HOST)) {
					//emailInfo.setFromEmail(creator.getUserEmail());
					emailInfo.setFromName(creator.getTrueName());
					
					//设置组织者信息
					emailInfo.setOrganizerEmail(creator.getUserEmail());
					emailInfo.setOrganizerName(creator.getTrueName());
				}
				
				if(!conf.isPmi()&&!conf.isClientCycleConf()){
					
					emailInfo.setUid(EmailUIDGenerator.getUid(conf.getId(),confUser.getUserEmail()));
					emailInfo.setWarnFlag(2);
					emailInfo.setWarnSubject(mailSubject);
					emailInfo.setWarnCount(3);
					emailInfo.setBeforeMinute(30);
					emailInfo.setGapMinute(10);
					emailInfo.setStratTime(confBJStartTime);
					emailInfo.setEndTime(confBJEndTime);
					emailInfo.setTimeZone("Asia/Shanghai");
				}
				
				emailInfo.setCycleId(conf.getCycleId());
				saveEmailInfo(emailInfo);
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	//会议修改提醒
	private boolean  cancelConfEmailForConfUser(ConfBase conf,ConfUser confUser){
		boolean flag=false;
		if(confUser!=null && conf!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(conf.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				UserBase creator = emailLogic.getUserBaseById(conf.getCreateUser());
				if(creator == null){
					creator = emailLogic.getUserBaseById(conf.getCompereUser());
				}
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());//域名的获取
				datas.put("user", confUser);
				datas.put("conf", conf);
				datas.put("timezone", conf.getTimeZoneDesc());
				datas.put("entimezone", conf.getTimeZoneDesc("en-us"));
				
				if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
					ConfCycle cycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
					datas.put("cycleMode", confLogic.getCycleMode(cycle, ""));
					datas.put("cycleModeEn", confLogic.getCycleMode(cycle, "en-us"));
					
					datas.put("cycleRepeat", confLogic.getCycleRepeatScope(cycle, ""));
					datas.put("cycleRepeatEn", confLogic.getCycleRepeatScope(cycle, "en-us"));
				}
				
				String emailContent = "";
				String currentLan = LanguageHolder.getCurrentLanguage();
				if(currentLan.startsWith("zh")){
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CANCEL_CONF_REMIND, datas);
				}else{
					emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CANCEL_CONF_REMIND_EN, datas);
				}
				if(emailContent==null){
					emailContent = "取消会议";
				}	
				
				String mailSubject="会议取消通知-"+conf.getConfName()+"/Conference cancel remind-"+conf.getConfName();
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					mailSubject = "会议取消通知-"+conf.getConfName()+"/Conference cancel remind-"+conf.getConfName();
				
					emailInfo.setOrganizerEmail(creator.getUserEmail());
					emailInfo.setOrganizerName(creator.getTrueName());
					emailInfo.setFromName(creator.getUserEmail());
				}
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				emailInfo.setSiteId(conf.getSiteId());
				emailInfo.setEmailSubject(mailSubject);
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(confUser.getUserEmail());
				
				
				emailInfo.setUid(EmailUIDGenerator.getUid(conf.getId(),confUser.getUserEmail()));
				emailInfo.setWarnType(EmailConstant.WARNTYPE_CANCEL);
				
				emailInfo.setWarnFlag(2);
				emailInfo.setWarnSubject(mailSubject);
				emailInfo.setWarnCount(3);
				emailInfo.setBeforeMinute(30);
				emailInfo.setGapMinute(10);
				
				Date confBJStartTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getStartTime(), conf.getTimeZone()));
				Date confBJEndTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getEndTime(), conf.getTimeZone()));
				emailInfo.setStratTime(confBJStartTime);
				emailInfo.setEndTime(confBJEndTime);
				emailInfo.setTimeZone("Asia/Shanghai");
				
				emailInfo.setCycleId(conf.getCycleId());
				
				saveEmailInfo(emailInfo);
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 转化会议时间为站点的当地时间
	 * @param conf
	 */
	private void transferConfDate(ConfBase conf){
		if(conf!=null){
			if(conf.getTimeZone()!=null){
				conf.setStartTime(DateUtil.getOffsetDateByGmtDate(conf.getStartTime(), conf.getTimeZone().longValue()));
				conf.setEndTime(DateUtil.getOffsetDateByGmtDate(conf.getEndTime(), conf.getTimeZone().longValue()));
			}else {
				conf.setStartTime(emailLogic.getSiteLocalDate(conf.getStartTime(), conf.getSiteId()));
				conf.setEndTime(emailLogic.getSiteLocalDate(conf.getEndTime(), conf.getSiteId()));
			}
		}
	}


	@Override
	public boolean createNameHost(UserBase host, List<License> licenses) {
		boolean createStatus=false;
		if(host!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(host.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				SiteBase site = libernate.getEntity(SiteBase.class, host.getSiteId());
				if(licenses!=null && !licenses.isEmpty()){
					for (Iterator it = licenses.iterator(); it
							.hasNext();) {
						License lic = (License) it.next();
						if(site!=null && site.getTimeZone()!=null){
							lic.transforLocalDate(site.getTimeZone());
						}else{
							lic.transforLocalDate(28800000);
						}
					}
				}
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", emailLogic.getSiteDomine(site.getId())); //域名的获取
				datas.put("user", host);
				datas.put("lics", licenses);
				datas.put("site", site);
				
				datas.put("accessNumber1", BaseConfig.getInstance().getString("access_num1", ""));
				datas.put("accessNumber2", BaseConfig.getInstance().getString("access_num2", ""));
				datas.put("accessNumber3", BaseConfig.getInstance().getString("access_num3", ""));
				//datas.put("siteBase",site);
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CREATE_HOST_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(host.getSiteId());
				emailInfo.setEmailSubject("主持人帐号信息/Enterprise host account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(host.getUserEmail());
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus;
	}


	@Override
	public boolean updateNameHost(UserBase host, List<License> licenses) {
		boolean createStatus=false;
		if(host!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(host.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				SiteBase site = libernate.getEntity(SiteBase.class, host.getSiteId());
				if(licenses!=null && !licenses.isEmpty()){
					for (Iterator it = licenses.iterator(); it
							.hasNext();) {
						License lic = (License) it.next();
						if(site!=null && site.getTimeZone()!=null){
							lic.transforLocalDate(site.getTimeZone());
						}else{
							lic.transforLocalDate(28800000);
						}
					}
				}
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", emailLogic.getSiteDomine(site.getId())); //域名的获取
				datas.put("user", host);
				datas.put("lics", licenses);
				//datas.put("siteBase",site);
				String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.UPDATE_HOST_TEMP, datas);
				logger.info("the email content:"+emailContent);
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				
				emailInfo.setSiteId(host.getSiteId());
				emailInfo.setEmailSubject("主持人帐号信息/Enterprise host account info");
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(host.getUserEmail());
				saveEmailInfo(emailInfo);
				createStatus = true;
			}catch(Exception e){
				createStatus = false;
				e.printStackTrace();
			}
		}
		return createStatus;
	}
	
	 
	
//	@Override
//	public String getJionUrl(ConfBase conf,int type) {
//		String joinUrl = "";
//		//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
//		String encrypt = "bizconfzoom";
//		MD5 md5 = new MD5();
//		if(type==ConfConstant.HOST_JOIN_TYPE_EMAIL){//主持人通过email入会
//			joinUrl = "join/gotojoinPage?joinType=" + ConfConstant.HOST_JOIN_TYPE_EMAIL + "&cId=" + conf.getId()+"&token="+md5.encrypt(encrypt + ConfConstant.HOST_JOIN_TYPE_EMAIL + conf.getId());
//		}else if(type==ConfConstant.USER_JOIN_TYPE_EMAIL){//参会者通过email入会
//			joinUrl = "join/gotojoinPage?joinType=" + ConfConstant.USER_JOIN_TYPE_EMAIL + "&cId=" + conf.getId()+"&token="+md5.encrypt(encrypt + ConfConstant.USER_JOIN_TYPE_EMAIL + conf.getId());
//		}
//		joinUrl = "http://"+SiteIdentifyUtil.getCurrentDomine() + "/" + joinUrl;
//		return joinUrl;
//	}
	
 


	@Override
	public List<SiteBase> getExpireRemindSites() {
		StringBuffer sqlBuffer = new StringBuffer();//"select * from t_site_base where del_flag = ? and send_remind_flag < ?  ";// and expire_date < ?
		List<SiteBase> sites = null;
		try {
			List<Object> valueList=new ArrayList<Object>(); 
			Date nowGmtDate = DateUtil.getGmtDate(null);//new Date(new Date().getTime() - SiteConstant.BEFORE_SITE_EXP_REMIND_DATES*3600*24*1000l));
			sqlBuffer.append(" select * from t_site_base where del_flag = ? and send_remind_flag < ?  ");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(SiteConstant.SEND_SITE_EXP_REMIND);
			Date eachExpStartDate=null;
			Date eachExpEndDate=null;
//			sqlBuffer.append(" and (");
//			int ii=0;
//			for(Integer[] eachRemindDays:SiteConstant.SITE_EXPIRE_DAYS){
//				if(ii > 0){
//					sqlBuffer.append(" or ");
//				}
//				eachExpStartDate=DateUtil.addDateMinutes(nowGmtDate, eachRemindDays[1]* 24 * 60);
//				eachExpEndDate=DateUtil.addDateMinutes(nowGmtDate, eachRemindDays[0]* 24 * 60);
//				sqlBuffer.append(" (");
//				sqlBuffer.append(" expire_date >? and expire_date<=?");
//				valueList.add(eachExpStartDate);
//				valueList.add(eachExpEndDate);
//				sqlBuffer.append(")");
//				ii++;
//			}
//			sqlBuffer.append(" )");
			sites = libernate.getEntityListBase(SiteBase.class, sqlBuffer.toString(), valueList.toArray());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sites;
	}


	@Override
	public boolean sendSiteRmindEmail(SiteBase site) {
		boolean flag = false;
		try{
			EmailConfig sysConfig = emailLogic.getSysEmailConfig(site.getId());
			
			UserBase admin = emailLogic.getSiteSupperMasterBySiteId(site.getId());
			//获取邮件内容.
			
			
			
			
		
		Map<String, Object> datas = new HashMap<String,Object>();
			datas.put("user",admin);
//			datas.put("exp_date", SiteConstant.BEFORE_SITE_EXP_REMIND_DATES);
			datas.put("exp_date", site.getExpireDateNumber());
			String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.SITE_EXPIRED_REMIND, datas);
			logger.info("the email content:"+emailContent);
			SendMail mailInfo=new SendMail();
			mailInfo.setFromEmail(sysConfig.getEmailSender());
			mailInfo.setFromName(sysConfig.getEmailName());
			mailInfo.setServerHost(sysConfig.getEmailHost());
			mailInfo.setServerPort("25");
			mailInfo.setUserName(sysConfig.getEmailSender());
			mailInfo.setUserPass(sysConfig.getEmailPassword());
			mailInfo.setSubject("企业站点即将过期/Enterprise will expired");
			mailInfo.setContentType("html");
			mailInfo.setContent(emailContent);
			mailInfo.setValidate(true);
			mailInfo.setToEmail(admin.getUserEmail());
			EmailUtil.send(mailInfo);
			
			site.setSendRemindFlag(site.getSendRemindFlag()+SiteConstant.SEND_SITE_EXP_REMIND);
			libernate.updateEntity(site, "sendRemindFlag");
			flag = true;
			
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
		
	}

	
	public boolean sendEmailForSiteRemind(SiteBase siteBase) throws Exception{
		if(siteBase!=null){
			Date expireDate=siteBase.getExpireDate();
			if(expireDate==null){
				expireDate=DateUtil.getGmtDate(null);
			}
			Integer sendFlag=siteBase.getSendRemindFlag();
			Date nowGmtDate=DateUtil.getGmtDate(null);
			Date localDate=DateUtil.getOffsetDateByGmtDate(nowGmtDate, (long)siteBase.getTimeZone());
			Date localExpireDate=DateUtil.getOffsetDateByGmtDate(expireDate, (long)siteBase.getTimeZone());
			int diffDays=DateUtil.getDateDiff(localDate,localExpireDate);
			
//			System.out.println("site task for : siteId=" + siteBase.getId() + " , siteName=" + siteBase.getSiteName()+ ",diffDays= "+diffDays);
			int ii=0;
			int thisFlag=0;
			for(Integer reminDay:SiteConstant.SITE_REMIND_DAYS){
				ii++;
				thisFlag=Integer.valueOf(Math.round(Math.pow(2d,(ii-1)*1d))+"");
				System.out.println("site task for : siteId=" + siteBase.getId() + " , siteName=" + siteBase.getSiteName()+ ",thisFlag= "+thisFlag+";sendFlag="+sendFlag+ ",diffDays= "+diffDays + ";(thisFlag&sendFlag)="+(thisFlag&sendFlag));
				if(reminDay.intValue()==diffDays && sendFlag < thisFlag && (thisFlag&sendFlag) == 0 ){//?????????
					EmailConfig sysConfig = emailLogic.getSysEmailConfig(siteBase.getId());
					UserBase admin = emailLogic.getSiteSupperMasterBySiteId(siteBase.getId());
					Map<String, Object> datas = new HashMap<String,Object>();
					siteBase.setEffeDate(DateUtil.getOffsetDateByGmtDate(siteBase.getEffeDate(),(long)siteBase.getTimeZone()));
					
					datas.put("user",admin);
					datas.put("siteBase",siteBase);
					datas.put("timezone",siteBase.getTimeZoneDesc());
					
//					datas.put("exp_date", SiteConstant.BEFORE_SITE_EXP_REMIND_DATES);
					datas.put("exp_date", siteBase.getExpireDateNumber());
					String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.SITE_EXPIRED_REMIND, datas);
					logger.info("the email content:"+emailContent);
					EmailInfo emailInfo=new EmailInfo();
					emailInfo.setSiteId(siteBase.getId());
					emailInfo.setServerHost(sysConfig.getEmailHost());
					emailInfo.setServerPort("25");
					//emailInfo.setFromEmail(sysConfig.getEmailSender());
					emailInfo.setFromName(sysConfig.getEmailName());
					emailInfo.setUserName(sysConfig.getEmailSender());
					emailInfo.setUserPass(sysConfig.getEmailPassword());
					emailInfo.setEmailSubject("企业站点即将过期/Enterprise will expired");
					emailInfo.setContentType("html");
					emailInfo.setValidate(true);
					emailInfo.setEmailContent(emailContent);
					if(!StringUtil.isEmpty(admin.getUserEmail())){
						emailInfo.setEmailAccepter(admin.getUserEmail());//发送企业管理员
						libernate.saveEntity(emailInfo);
					}
					
					//发送给站点创建者
					SystemUser systemUser=userService.getSystemUserById(siteBase.getCreateUser());
					systemUser.setEmail("frank_song@bizconf.cn");//发送给Frank
					emailInfo.setEmailAccepter(systemUser.getEmail());
					libernate.saveEntity(emailInfo);
					
					if(!StringUtil.isEmpty(siteBase.getMarketorEmail())){
						emailInfo.setEmailAccepter(siteBase.getMarketorEmail());//发送给销售代表
						libernate.saveEntity(emailInfo);
					}
					
//					SendMail mailInfo=new SendMail();
//					mailInfo.setFromEmail(sysConfig.getEmailSender());
//					mailInfo.setFromName(sysConfig.getEmailName());
//					mailInfo.setServerHost(sysConfig.getEmailHost());
//					mailInfo.setServerPort("25");
//					mailInfo.setUserName(sysConfig.getEmailSender());
//					mailInfo.setUserPass(sysConfig.getEmailPassword());
//					mailInfo.setSubject("企业站点即将过期/Enterprise will expired");
//					mailInfo.setContentType("html");
//					mailInfo.setContent(emailContent);
//					mailInfo.setValidate(true);
//					mailInfo.setToEmail(admin.getUserEmail());
//					EmailUtil.send(mailInfo);
		
					
					siteBase.setSendRemindFlag(Integer.valueOf(Math.round(Math.pow(2d,ii*1d)-1)+""));
					libernate.updateEntity(siteBase, "sendRemindFlag");
					return true;
				}
			}
		
			
		//	siteBase.setSendRemindFlag(siteBase.getSendRemindFlag()+SiteConstant.SEND_SITE_EXP_REMIND);
		//	libernate.updateEntity(siteBase, "sendRemindFlag");
			
			return false;
			
			
			
		}
		return false;
	}

	@Override
	public List<SiteBase> getExpiredSites() {
		String sql = "select * from t_site_base where del_flag = ? and expire_date < ? and send_remind_flag < ?";
		List<SiteBase> sites = null;
		try {
			//站点已经过期
			Date expDate = DateUtil.getGmtDate(null);
			sites = libernate.getEntityListBase(SiteBase.class, sql, new Object[]{
				ConstantUtil.DELFLAG_UNDELETE, expDate,  SiteConstant.SEND_SITE_EXP});
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sites;
	}


	@Override
	public boolean sendSiteExpiredEmail(SiteBase site) {
		boolean flag = false;
		try{
			EmailConfig sysConfig = emailLogic.getSysEmailConfig(site.getId());
			//获取邮件内容
			UserBase admin = emailLogic.getSiteSupperMasterBySiteId(site.getId());
			Map<String, Object> datas = new HashMap<String,Object>();
			datas.put("user",admin);
			
			String emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.SITE_EXPIRED, datas);
			logger.info("the email content:"+emailContent);
			SendMail mailInfo=new SendMail();
			mailInfo.setFromEmail(sysConfig.getEmailSender());
			mailInfo.setFromName(sysConfig.getEmailName());
			mailInfo.setServerHost(sysConfig.getEmailHost());
			mailInfo.setServerPort("25");
			mailInfo.setUserName(sysConfig.getEmailSender());
			mailInfo.setUserPass(sysConfig.getEmailPassword());
			mailInfo.setSubject("企业站点已经过期/Enterprise expired");
			mailInfo.setContentType("html");
			mailInfo.setContent(emailContent);
			mailInfo.setValidate(true);
			mailInfo.setToEmail(admin.getUserEmail());
			EmailUtil.send(mailInfo);
			
			site.setSendRemindFlag(site.getSendRemindFlag()+SiteConstant.SEND_SITE_EXP);
			libernate.updateEntity(site, "sendRemindFlag");
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	@Override
	public EmailInfo getEmailInfoById(int id) {
		try {
			return libernate.getEntity(EmailInfo.class, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean updateNowCycleConfEmail(List<ConfUser> users, ConfBase conf) {
		boolean flag=false;
		if(users!=null && conf!=null){
			try{
				transferConfDate(conf);//将GMT时间转换为当地时间
				for(ConfUser user:users){
					modifyNowCycleConfEmailForConfUser(conf,user);
				}
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 单次周期会议修改提醒 （主持人和参会者）
	 * 
	 * */
	private boolean  modifyNowCycleConfEmailForConfUser(ConfBase conf,ConfUser confUser){
		boolean flag=false;
		if(confUser!=null && conf!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(conf.getSiteId());
				UserBase creator = emailLogic.getUserBaseById(conf.getCreateUser());
				if(creator == null){
					creator = emailLogic.getUserBaseById(conf.getCompereUser());
				}
				EmailInfo emailInfo =new EmailInfo();
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				Date confBJStartTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getStartTime(), conf.getTimeZone()));
				Date confBJEndTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getEndTime(), conf.getTimeZone()));
				
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());//域名的获取
				datas.put("user", confUser);
				datas.put("conf", conf);
				datas.put("timezone", conf.getTimeZoneDesc());
				datas.put("entimezone", conf.getTimeZoneDesc("en-us"));
				datas.put("qccodeURL",  getQccodeURL(conf.getId(), confUser.getHostFlag()));
				datas.put("rcIPs",  dcService.getRCIPs(creator.getDataCenterId()));
//				if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
//					ConfCycle cycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
//					datas.put("cycleMode", confLogic.getCycleMode(cycle, ""));
//					datas.put("cycleModeEn", confLogic.getCycleMode(cycle, "en-us"));
//					
//					datas.put("cycleRepeat", confLogic.getCycleRepeatScope(cycle, ""));
//					datas.put("cycleRepeatEn", confLogic.getCycleRepeatScope(cycle, "en-us"));
//				}
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.USER_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}else {
					datas.put("joinUrl",UrlUtils.getJionUrl(conf, ConfConstant.HOST_JOIN_TYPE_EMAIL));//给主持人发送邮件的加入会议地址
				}
				
				
				String emailContent = "";
				emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.MODIFY_SINGLE_CONF_REMIND, datas);
				if(emailContent==null){
					emailContent = "会议修改";
				}	
				
				String mailSubject="会议修改通知-"+conf.getConfName()+"/Conference modify remind-"+conf.getConfName();
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					mailSubject = "会议修改通知-"+conf.getConfName()+"/Conference modify remind-"+conf.getConfName();
				}
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				emailInfo.setSiteId(conf.getSiteId());
				emailInfo.setEmailSubject(mailSubject);
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(confUser.getUserEmail());
				
				emailInfo.setUid(EmailUIDGenerator.getUid(conf.getId(),confUser.getUserEmail()));
				if (!confUser.getHostFlag().equals(ConstantUtil.USERROLE_HOST)) {
					//emailInfo.setFromEmail(creator.getUserEmail());
					emailInfo.setFromName(creator.getTrueName());
					//设置组织者信息
					emailInfo.setOrganizerEmail(creator.getUserEmail());
					emailInfo.setOrganizerName(creator.getTrueName());
				}
				
				emailInfo.setWarnFlag(2);
				emailInfo.setWarnSubject(mailSubject);
				emailInfo.setWarnCount(3);
				emailInfo.setBeforeMinute(30);
				emailInfo.setGapMinute(10);
				emailInfo.setStratTime(confBJStartTime);
				emailInfo.setEndTime(confBJEndTime);
				emailInfo.setTimeZone("Asia/Shanghai");
				
				emailInfo.setCycleId(conf.getCycleId());
				
				logger.info(emailContent);
				
				saveEmailInfo(emailInfo);
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 *	发送取消单次周期会议的通知邮件 
	 * */
	@Override
	public boolean sendCancelSingleCycleConfEmail(List<ConfUser> confUsers,ConfBase conf) {
		boolean flag=false;
		if(confUsers!=null && conf!=null){
			try{
				transferConfDate(conf);//将GMT时间转换为当地时间
				for(ConfUser user:confUsers){
					cancelSingleCycleEmailForConfUser(conf,user);
				}
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 发送取消单次周期会议提醒
	 * */
	private boolean  cancelSingleCycleEmailForConfUser(ConfBase conf,ConfUser confUser){
		boolean flag=false;
		if(confUser!=null && conf!=null){
			try{
				EmailConfig sysConfig = emailLogic.getSysEmailConfig(conf.getSiteId());
				EmailInfo emailInfo =new EmailInfo();
				UserBase creator = emailLogic.getUserBaseById(conf.getCreateUser());
				if(creator == null){
					creator = emailLogic.getUserBaseById(conf.getCompereUser());
				}
				//获取邮件内容
				Map<String, Object> datas = new HashMap<String,Object>();
				datas.put("siteaddress", SiteIdentifyUtil.getCurrentDomine());//域名的获取
				datas.put("user", confUser);
				datas.put("conf", conf);
				datas.put("timezone", conf.getTimeZoneDesc());
				datas.put("entimezone", conf.getTimeZoneDesc("en-us"));
				
//				if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
//					ConfCycle cycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
//					datas.put("cycleMode", confLogic.getCycleMode(cycle, ""));
//					datas.put("cycleModeEn", confLogic.getCycleMode(cycle, "en-us"));
//					
//					datas.put("cycleRepeat", confLogic.getCycleRepeatScope(cycle, ""));
//					datas.put("cycleRepeatEn", confLogic.getCycleRepeatScope(cycle, "en-us"));
//				}
				
				String emailContent = "";
				emailContent  = EmailContentGenerator.getInstance().genContent(EmailConstant.CANCEL_SINGLE_CONF_REMIND, datas);
				if(emailContent==null){
					emailContent = "取消会议";
				}	
				
				String mailSubject="会议取消通知-"+conf.getConfName()+"/Conference cancel remind-"+conf.getConfName();
				if(confUser.getHostFlag().equals(ConstantUtil.USERROLE_PARTICIPANT)){
					mailSubject = "会议取消通知-"+conf.getConfName()+"/Conference cancel remind-"+conf.getConfName();
				
					emailInfo.setOrganizerEmail(creator.getUserEmail());
					emailInfo.setOrganizerName(creator.getTrueName());
					emailInfo.setFromName(creator.getUserEmail());
				}
				setSysDefEmailInfo(emailInfo,sysConfig);//邮件的基本设置
				emailInfo.setSiteId(conf.getSiteId());
				emailInfo.setEmailSubject(mailSubject);
				emailInfo.setEmailContent(emailContent);//设置邮件内容
				emailInfo.setEmailAccepter(confUser.getUserEmail());
				
				
				emailInfo.setUid(EmailUIDGenerator.getUid(conf.getId(),confUser.getUserEmail()));
				emailInfo.setWarnType(EmailConstant.WARNTYPE_CANCEL);
				
				emailInfo.setWarnFlag(2);
				emailInfo.setWarnSubject(mailSubject);
				emailInfo.setWarnCount(3);
				emailInfo.setBeforeMinute(30);
				emailInfo.setGapMinute(10);
				
				Date confBJStartTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getStartTime(), conf.getTimeZone()));
				Date confBJEndTime = DateUtil.getBejingDateByGmtDate(DateUtil.getGmtDateByTimeZone(conf.getEndTime(), conf.getTimeZone()));
				emailInfo.setStratTime(confBJStartTime);
				emailInfo.setEndTime(confBJEndTime);
				emailInfo.setTimeZone("Asia/Shanghai");
				
				emailInfo.setCycleId(conf.getCycleId());

				logger.info(emailContent);
				
				saveEmailInfo(emailInfo);
				flag = true;
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}


