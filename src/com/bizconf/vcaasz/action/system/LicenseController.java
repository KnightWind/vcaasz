package com.bizconf.vcaasz.action.system;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.APIReturnCode;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LicenseConstant;
import com.bizconf.vcaasz.constant.UserConstant;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ContactService;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.EnterpriseAdminService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * 系统管理员操作站点controller
 * @author Martin
 * @date 2013.3.26
 */
@ReqPath("lic")
//@Interceptors({SystemUserInterceptor.class})
public class LicenseController extends BaseController{
	private final  Logger logger = Logger.getLogger(LicenseController.class);
	
	@Autowired
	ContactService contactService;
	@Autowired
	SiteService siteService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	EmailService emailService;
	@Autowired
	UserService userService;

	@Autowired
	LicService licService;
	
	@Autowired 
	EnterpriseAdminService enterpriseAdminService;
	
	@Autowired
	EmpowerConfigService empowerConfigService;
	
	@Autowired
	ConfService confService;
	
	@Autowired
	ConfLogic confLogic;
	
	@Autowired
	ConfUserLogic confUserLogic;
	
	@Autowired
	SiteLogic siteLogic;
	
	@Autowired
	UserLogic userLogic;
	
	@Autowired ZoomUserOperationComponent zoomUserOperator;
	
	@Autowired
	DataCenterService dataCenterService; 
	
	@AsController(path = "list")
	public Object listBySite(@CParam("siteId") int siteId,@CParam("userId") int userId,@CParam("pageNo") int pageNo, HttpServletRequest request) throws Exception{
		
		PageBean<License> pageModel = null;
		pageModel = licService.getLicensePage(siteId, userId, pageNo);
		SiteBase site = siteService.getSiteBaseById(siteId);
		if(pageModel!=null && pageModel.getDatas()!=null){
			for(License lic:pageModel.getDatas()){
				if(site!=null&&site.getTimeZone()!=null){
					lic.transforLocalDate(site.getTimeZone());
				}else{
					lic.setEffeDate(DateUtil.getBejingDateByGmtDate(lic.getEffeDate()));
					lic.setExpireDate(DateUtil.getBejingDateByGmtDate(lic.getExpireDate()));
				}
			}
		}
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("siteId", siteId);
		if(siteId>0){
			int licNum = licService.getSiteLicenseNum(siteId);
			request.setAttribute("site", site);
			request.setAttribute("licNum", site.getLicense()+licNum);
		}
		if(userId > 0){
			request.setAttribute("userId", userId);
			UserBase user = userService.getUserBaseById(userId);
			int licNum = licService.getHostLicenseNum(userId);
			licNum += user.getNumPartis();
			
			logger.info("the db license is "+licNum);
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
			
			Map<String,Object> userInfo =  zoomUserOperator.get(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId());
			int currentPort = (Integer)userInfo.get("meeting_capacity");
			logger.info("the fact license is "+currentPort);
			if(currentPort != licNum){
				if(!zoomUserOperator.modifyPortNum(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId(), licNum)){
					licNum = currentPort;
					logger.info("re-update license failed !");
				}
				
				logger.info("update license success!");
			}
			//添加用户初始化时的端口数
			request.setAttribute("user", user);
			request.setAttribute("licNum", licNum);
		}
		
		return new ActionForward.Forward("/jsp/system/license_list.jsp");
	}
	
	@AsController(path = "save")
	public Object saveLicenseEdit(License license, HttpServletRequest request) throws Exception{
		
//		License oldLicense = null;
//		if(license != null && license.getId() != null && license.getId().intValue() > 0){
//			oldLicense = licService.getLicenseById(license.getId());
//		}
		
//		License oldLicense = licService.getLicenseById(license.getId());
//		oldLicense.setCreateTime(license.getCreateTime());

		SystemUser currentUser = userService.getCurrentSysAdmin(request);
		license.init();
		license.setCreateUser(currentUser.getId());
		SiteBase site = siteService.getSiteBaseById(license.getSiteId());
		license.transforGMTDate(site.getTimeZone()==null?28800000:site.getTimeZone().intValue());
		
		licService.saveOrUpdate(license);
 
		return new ActionForward.Forward("/system/lic/list?siteId"+license.getSiteId());
	}
	
	/**
	 * 进入编辑页面
	 * @param licId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "goEdit")
	public Object toEdit(@CParam("id") int licId, HttpServletRequest request) throws Exception{
		
		return new ActionForward.Forward("/user/lic/list");
	}
	
	/**
	 * 删除license
	 * @param licId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "del")
	public Object delLic(@CParam("id") int licId, HttpServletRequest request) throws Exception{
		
		SystemUser currentUser = userService.getCurrentSysAdmin(request);
		License license  = licService.getLicenseById(licId);
		if(license.getEffeFlag().intValue()!=1){
			licService.delLicense(licId, currentUser.getId());
		}
		return new ActionForward.Forward("/system/lic/list?siteId="+license.getSiteId()+"&userId="+license.getUserId());
	}
	
	
	
	/**
	 * nameHost 模式下显示主持人列表
	 * @param siteId
	 * @param pageNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "listHost")
	public Object listHost(@CParam("siteId") int siteId,@CParam("pageNo") int pageNo, HttpServletRequest request) throws Exception{
		
		SystemUser currentSysUser = userService.getCurrentSysAdmin(request);
		int pageSize = ConstantUtil.PAGESIZE_DEFAULT;
		if(currentSysUser.getPageSize()>0){
			pageSize = currentSysUser.getPageSize();
		} 
		PageBean<UserBase> pageModel = licService.getHostsBySite(null,siteId, pageNo,pageSize);
		SiteBase siteBase = siteService.getSiteBaseById(siteId);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("siteId", siteId);
		request.setAttribute("currentsite", siteBase);
		//request.setAttribute("licnums", licService.getHostsLienseDatas(pageModel.getDatas()));
		return new ActionForward.Forward("/jsp/system/hostlist.jsp");
	}
	
	@AsController(path = "delHost")
	public Object delHostUser(@CParam("id") int id,@CParam("pageNo") int pageNo, HttpServletRequest request) throws Exception{
		UserBase user = userService.getUserBaseById(id);
		SystemUser currentUser = userService.getCurrentSysAdmin(request);
		//删除AS子站点
		//SiteBase mySite = siteLogic.getVirtualSubSite(user.getId());
//		siteService.deleteSiteById(mySite.getId(), currentUser);
			//删除主持人
		 
		if(enterpriseAdminService.deleteUserBase(id, currentUser.getId())){
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
			//zoomUserOperator.delete(user.getZoomId());
			zoomUserOperator.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId(), StringUtil.genRandomPWD());
		}
		
		//======删除该用户创建的会议======
//		List<ConfBase> confs = confLogic.getConfBasesByCreator(id);
//		for (Iterator<ConfBase> it = confs.iterator(); it.hasNext();) {
//			ConfBase conf = it.next();
//			SiteBase site = siteService.getSiteBaseById(conf.getSiteId());
//			if(conf.getConfStatus().intValue()==1){
//				if(conf.getCycleId()!=0){
//					confService.cancleCycleConfInfo(conf.getId(), site, user);
//				}else{
//					confService.cancleSingleReservationConf(conf.getId(), site, user);
//				}
//				List<ConfUser> confUsers = confUserLogic.getConfUserList(conf.getId());
//				emailService.confCancelEmail(confUsers, conf);
//			}
//		}
		return new ActionForward.Redirect("/system/lic/listHost?siteId="+user.getSiteId());
	}
	@AsController(path = "goEditHost")
	public Object goEditHost(@CParam("userId") int userId,@CParam("siteId") int siteId, HttpServletRequest request) throws Exception{
		UserBase host = userService.getUserBaseById(userId);
		
		//修改主持人时候默认添加上PMI
		if(host!=null && (host.getPmiId()==null || "".equals(host.getPmiId()))){
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(host.getDataCenterId());
			
			String pmi = zoomUserOperator.getZoomPMI(dataCenter.getApiKey(),dataCenter.getApiToken(),host.getZoomId());
			if (!StringUtil.isEmpty(pmi) && !"0".equals(pmi)) {
				host.setPmiId(pmi);
			}
		}
		request.setAttribute("user", host);
		request.setAttribute("siteId", siteId);
		
		SiteBase site = siteService.getSiteBaseById(siteId);
		//EmpowerConfig siteConfig = empowerConfigService.getSiteEmpowerConfigBySiteId(site.getId());
		//EmpowerConfig globalConfig = empowerConfigService.getSiteEmpowerGlobalBySiteId(site.getId());
		//empowerConfigService.setConfigValue(siteConfig,globalConfig);
		
		//EmpowerConfig config = empowerConfigService.getUserEmpowerConfigByUserId(userId);
		//request.setAttribute("config", config);
		
		//查询全部数据中心 by Darren at 2014-12-23 add
		List<DataCenter> dataCenters = dataCenterService.queryAll();
		//生成一个八位随机密码
		String randomPwd = StringUtil.genRandomPWD();
		request.setAttribute("randomPwd", randomPwd);
		request.setAttribute("currentsite", site);
		request.setAttribute("dataCenters", dataCenters);
		
		return new ActionForward.Forward("/jsp/system/add_site_user.jsp");
	}
	
	@AsController(path = "goMaEditHost")
	public Object goMaEditHost(@CParam("userId") int userId,@CParam("siteId") int siteId, HttpServletRequest request) throws Exception{
		UserBase host = userService.getUserBaseById(userId);
		
		DataCenter masterCenter = dataCenterService.queryDataCenterById(LicenseConstant.MASTER_ACCOUNT_ID);
		//修改主持人时候默认添加上PMI
		if(host!=null && (host.getPmiId()==null || "".equals(host.getPmiId()))){
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(host.getDataCenterId());
			
			String pmi = zoomUserOperator.maGetZoomPMI(masterCenter.getApiKey(),masterCenter.getApiToken(),dataCenter.getAccountId(),host.getZoomId());
			if (!StringUtil.isEmpty(pmi) && !"0".equals(pmi)) {
				host.setPmiId(pmi);
			}
		}
		request.setAttribute("user", host);
		request.setAttribute("siteId", siteId);
		
		SiteBase site = siteService.getSiteBaseById(siteId);
		
		List<DataCenter> dataCenters = dataCenterService.queryAll();
		//生成一个八位随机密码
		String randomPwd = StringUtil.genRandomPWD();
		request.setAttribute("randomPwd", randomPwd);
		request.setAttribute("currentsite", site);
		request.setAttribute("dataCenters", dataCenters);
		request.setAttribute("ma", true);
		
		return new ActionForward.Forward("/jsp/system/add_site_user.jsp");
	}
	
	/**
	 * 新增或者修改namehost下的主持人信息
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "saveHost")
	public Object saveHost(@CParam("sendEmail")int sendEmail,UserBase user,
			@CParam("exprieDate")String exprieDateStr, HttpServletRequest request) throws Exception{
		
		SystemUser userAdmin = userService.getCurrentSysAdmin(request);
		user.setUserRole(ConstantUtil.USERROLE_HOST);
		if(user ==null || user.getSiteId()==null || user.getSiteId().intValue() == 0){
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "save host user failed. site id must not be empty!");
		}
		SiteBase site = siteLogic.getSiteBaseById(user.getSiteId());
		String orgPass = user.getLoginPass();
//		List<Object[]> objectChangeList = new ArrayList<Object[]>();   //修改用户时保存用户信息、授权信息两类信息的变化。
//		String optDescLog = "";
//		UserBase createAdmin = null;
		
		Date exprieDate = null;
		if(!StringUtil.isEmpty(exprieDateStr)){
			exprieDate = new SimpleDateFormat("yyyy-MM-dd").parse(exprieDateStr);
		}
		if(user.getId()!=null && user.getId()>0){
			UserBase orgUser = userService.getUserBaseById(user.getId()); //原始的用户信息
			//UserBase oldUser = (UserBase) orgUser.clone();
			//EmpowerConfig oldUserPower = new EmpowerConfig();
			orgUser.setLoginName(user.getLoginName());
			orgUser.setTrueName(user.getTrueName());
			orgUser.setEnName(user.getEnName());
			//orgUser.setUserEmail(user.getUserEmail()); email  不允许修改
			orgUser.setMobile(user.getMobile());
			orgUser.setRemark(user.getRemark());
			orgUser.setNumPartis(user.getNumPartis());
			orgUser.setExprieDate(exprieDate);
			
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(orgUser.getDataCenterId());
			DataCenter masterCenter = dataCenterService.queryDataCenterById(LicenseConstant.MASTER_ACCOUNT_ID);
			
			//如果修改了所属account
			if(user.getDataCenterId()!=null && !user.getDataCenterId().equals(0) && !orgUser.getDataCenterId().equals(user.getDataCenterId())){
				DataCenter newDC =  dataCenterService.queryDataCenterById(user.getDataCenterId());
				int ret = zoomUserOperator.changeUserOwnerAccount(masterCenter.getApiKey(),
						masterCenter.getApiToken(), newDC.getAccountId(), orgUser.getZoomId());
				if(ret == APIReturnCode.RET_SUCCESS){
					orgUser.setDataCenterId(user.getDataCenterId());
					//设置原来的dataCenter为
					dataCenter = newDC;
				}
			}
			
			if(StringUtil.isNotBlank(user.getLoginPass()) && zoomUserOperator.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),orgUser.getZoomId(), user.getLoginPass())){
				orgUser.setLoginPassPlain(user.getLoginPass());
				orgUser.setLoginPass(MD5.encodePassword(user.getLoginPass(), "MD5"));
				orgUser.setPassEditor(userAdmin.getId());
			}
			
			//2014-12-08 update
			int licNum = licService.getHostLicenseNum(user.getId());
			//添加用户初始化时的端口数
			licNum += user.getNumPartis();
			if(site.getHireMode() != ConstantUtil.HIRESITE_MINUTES){//非计时模式
				if(licNum<2 || licNum>100){
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "端口设置为非有效数据");
				}
			}
			if(zoomUserOperator.update(dataCenter.getApiKey(),dataCenter.getApiToken(),orgUser.getZoomId(), user.getZoomType(), licNum, user.getTrueName(), "", false, false) != UserConstant.UPDATE_USER_SUCCESS){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "update user info to zoom data center failed !");
			}
			//2014-12-08 update
			
			if(!zoomUserOperator.setZoomPMI(dataCenter.getApiKey(),dataCenter.getApiToken(),orgUser.getZoomId(),user.getPmiId())){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "After you sigin in the system to update manually PMI!");
			}

			orgUser.setPmiId(user.getPmiId());
			if(userService.siteUserSaveable(orgUser)){
				boolean flag = enterpriseAdminService.updateUserBase(orgUser);
				if(flag){
//					optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
//					createAdmin = userService.getSiteSupperMasterBySiteId(orgUser.getSiteId());
//					eventLogService.saveAdminEventLog(createAdmin, EventLogConstants.SITE_ADMIN_USER_UPDATE, optDescLog,
//							EventLogConstants.EVENTLOG_SECCEED, orgUser, request);
					orgUser.setLoginPass(orgPass);
					//修改用户信息的邮件通知
					if(sendEmail==1){
						emailService.updateSiteUserEmail(orgUser);
					}
				}
			}else{
//				objectChangeList.add(new Object[]{oldUser, user, new String[]{"id, trueName"}});
//				optDescLog = ObjectUtil.compareJsonDataArray(objectChangeList);
//				eventLogService.saveAdminEventLog(createAdmin, EventLogConstants.SITE_ADMIN_USER_UPDATE, 
//						optDescLog, 
//						EventLogConstants.EVENTLOG_FAIL, orgUser, request);   //修改失败后写EventLog
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
			}
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_SUCCEED, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.1"));
		}else{			//新增主持人用户
			user.setLoginPassPlain(user.getLoginPass());
			user.init();
			//创建时间------
			user.setCreateTime(new Date());
			
			user.setCreateUser(userAdmin.getId());//
			user.setUserType(ConstantUtil.USERTYPE_USERS);
			user.setPassEditor(userAdmin.getId());
			//设置用户的时区和站点时区同步
			user.setTimeZone(site.getTimeZone());
			user.setTimeZoneId(site.getTimeZoneId());
			user.setExprieDate(exprieDate);
			UserBase userBase = null;
			if(!userService.siteUserSaveable(user)){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
			}
			
			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
			
			//在这之前需要http请求zoom开号
			Map<String, Object> map = zoomUserOperator.create(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getUserEmail(), user.getZoomType(), user.getNumPartis(),
					orgPass, user.getTrueName(), "", false, false);
			
			if(map.get("id")!=null && !"".equals(map.get("id"))){//修改PMI信息
				if(user.getPmiId()!=null && !"".equals(user.getPmiId()) &&
						!zoomUserOperator.setZoomPMI(dataCenter.getApiKey(),dataCenter.getApiToken(),map.get("id").toString(),user.getPmiId())){
					//创建失败之后，在zoom数据中心删除刚才创建的用户
					zoomUserOperator.delete(dataCenter.getApiKey(),dataCenter.getApiToken(),map.get("id").toString());
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.3"));
				}
				
				user.setZoomId(map.get("id").toString());
				userBase = enterpriseAdminService.saveUserBase(user);
				
				//保存用户信息失败
				if(userBase == null || userBase.getId() < 1){
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.2"));
				}
				userBase.setLoginPass(orgPass);
				//创建用户发送邮件
				//List<License> lics = licService.getHostLicenses(userBase.getId());
				if(sendEmail==1){
					emailService.createSiteUser(userBase);
				}
				//将新添加的主持人添加的企业通讯录中
		    	contactService.saveSiteContactOfUserBase(userBase);
					
			}else {
				Map<String, Object> errorMap = (Map<String, Object>)map.get("error");
				logger.error("request zoom to create user failed! the error code is: "
						+ ""+errorMap.get("code").toString()+"  error msg is:"+errorMap.get("message"));
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.2") +" 失败原因："+errorMap.get("message").toString());
			}
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_SUCCEED, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.1"));
		}
	}
	
	@AsController(path = "maSaveHost")
	public Object maSaveHost(@CParam("sendEmail")int sendEmail,UserBase user,
			@CParam("exprieDate")String exprieDateStr, HttpServletRequest request) throws Exception{
		
		SystemUser userAdmin = userService.getCurrentSysAdmin(request);
		user.setUserRole(ConstantUtil.USERROLE_HOST);
		if(user ==null || user.getSiteId()==null || user.getSiteId().intValue() == 0){
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "save host user failed. site id must not be empty!");
		}
		SiteBase site = siteLogic.getSiteBaseById(user.getSiteId());
		String orgPass = user.getLoginPass();
		
		Date exprieDate = null;
		if(!StringUtil.isEmpty(exprieDateStr)){
			exprieDate = new SimpleDateFormat("yyyy-MM-dd").parse(exprieDateStr);
		}
		
		DataCenter masterCenter = dataCenterService.queryDataCenterById(LicenseConstant.MASTER_ACCOUNT_ID);
		if(user.getId()!=null && user.getId()>0){
			UserBase orgUser = userService.getUserBaseById(user.getId()); //原始的用户信息
			//Add by Darren 2014-12-24
			DataCenter dataCenter = dataCenterService.queryDataCenterById(orgUser.getDataCenterId());
			 
			orgUser.setLoginName(user.getLoginName());
			orgUser.setTrueName(user.getTrueName());
			orgUser.setEnName(user.getEnName());
			orgUser.setMobile(user.getMobile());
			orgUser.setRemark(user.getRemark());
			orgUser.setNumPartis(user.getNumPartis());
			orgUser.setExprieDate(exprieDate);
			
			
			//如果修改了所属account
			if(user.getDataCenterId()!=null && !user.getDataCenterId().equals(0) && !orgUser.getDataCenterId().equals(user.getDataCenterId())){
				DataCenter newDC =  dataCenterService.queryDataCenterById(user.getDataCenterId());
				int ret = zoomUserOperator.changeUserOwnerAccount(masterCenter.getApiKey(),
						masterCenter.getApiToken(), newDC.getAccountId(), orgUser.getZoomId());
				if(ret == APIReturnCode.RET_SUCCESS){
					orgUser.setDataCenterId(user.getDataCenterId());
					//设置原来的dataCenter为
					dataCenter = newDC;
				}
			}
			
			if(StringUtil.isNotBlank(user.getLoginPass()) && zoomUserOperator.maModifyPassword(masterCenter.getApiKey(),masterCenter.getApiToken(),
					dataCenter.getAccountId(),orgUser.getZoomId(), user.getLoginPass())){
				orgUser.setLoginPassPlain(user.getLoginPass());
				orgUser.setLoginPass(MD5.encodePassword(user.getLoginPass(), "MD5"));
				orgUser.setPassEditor(userAdmin.getId());
			}
			
			//2014-12-08 update
			int licNum = licService.getHostLicenseNum(user.getId());
			//添加用户初始化时的端口数
			licNum += user.getNumPartis();
			if(site.getHireMode() != ConstantUtil.HIRESITE_MINUTES){//非计时模式
				if(licNum<2 || licNum>100){
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "端口设置为非有效数据");
				}
			}
			if(zoomUserOperator.maUpdate(masterCenter.getApiKey(),masterCenter.getApiToken(),
					dataCenter.getAccountId(),orgUser.getZoomId(), user.getZoomType(), licNum, user.getTrueName(), "", false, false) != UserConstant.UPDATE_USER_SUCCESS){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "update user info to zoom data center failed !");
			}
			//2014-12-08 update
			
			if(!zoomUserOperator.maSetZoomPMI(masterCenter.getApiKey(),masterCenter.getApiToken(),
					dataCenter.getAccountId(),orgUser.getZoomId(),user.getPmiId())){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "After you sigin in the system to update manually PMI!");
			}
			
			orgUser.setPmiId(user.getPmiId());
			if(userService.siteUserSaveable(orgUser)){
				boolean flag = enterpriseAdminService.updateUserBase(orgUser);
				if(flag){

					orgUser.setLoginPass(orgPass);
					//修改用户信息的邮件通知
					if(sendEmail==1){
						emailService.updateSiteUserEmail(orgUser);
					}
				}
			}else{

				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
			}
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_SUCCEED, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.1"));
		}else{			//新增主持人用户
			user.setLoginPassPlain(user.getLoginPass());
			user.init();
			//创建时间------
			user.setCreateTime(new Date());
			
			user.setCreateUser(userAdmin.getId());//
			user.setUserType(ConstantUtil.USERTYPE_USERS);
			user.setPassEditor(userAdmin.getId());
			//设置用户的时区和站点时区同步
			user.setTimeZone(site.getTimeZone());
			user.setTimeZoneId(site.getTimeZoneId());
			user.setExprieDate(exprieDate);
			UserBase userBase = null;
			if(!userService.siteUserSaveable(user)){
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
			}
			
			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
			
			//在这之前需要http请求zoom开号
			Map<String, Object> map = zoomUserOperator.maCreate(masterCenter.getApiKey(),masterCenter.getApiToken(),dataCenter.getAccountId(),user.getUserEmail(), user.getZoomType(), user.getNumPartis(),
					orgPass, user.getTrueName(), "", false, false);
			
			if(map.get("id")!=null && !"".equals(map.get("id"))){//修改PMI信息
				if(user.getPmiId()!=null && !"".equals(user.getPmiId()) &&
						!zoomUserOperator.maSetZoomPMI(masterCenter.getApiKey(),masterCenter.getApiToken(),
								dataCenter.getAccountId(),map.get("id").toString(),user.getPmiId())){
					//创建失败之后，在zoom数据中心删除刚才创建的用户
					zoomUserOperator.maDelete(masterCenter.getApiKey(),masterCenter.getApiToken(),dataCenter.getAccountId(),map.get("id").toString());
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.3"));
				}
				
				user.setZoomId(map.get("id").toString());
				userBase = enterpriseAdminService.saveUserBase(user);
				
				//保存用户信息失败
				if(userBase == null || userBase.getId() < 1){
					return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.2"));
				}
				userBase.setLoginPass(orgPass);
				//创建用户发送邮件
				//List<License> lics = licService.getHostLicenses(userBase.getId());
				if(sendEmail==1){
					emailService.createSiteUser(userBase);
				}
				//将新添加的主持人添加的企业通讯录中
				contactService.saveSiteContactOfUserBase(userBase);
			}else {
				Map<String, Object> errorMap = (Map<String, Object>)map.get("error");
				logger.error("request zoom to create user failed! the error code is: "
						+ ""+errorMap.get("code").toString()+"  error msg is:"+errorMap.get("message"));
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.2") +" 失败原因："+errorMap.get("message").toString());
			}
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_SUCCEED, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.create.1"));
		}
	}
	
	@AsController(path = "sendMail")
	public Object sendRemindMail(@CParam("userId")Integer userId, HttpServletRequest request) throws Exception{
		boolean flag = true;
		int status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
		String msg = "success";
		try{
			UserBase user = userService.getUserBaseById(userId);
			user.setLoginPass("");
			List<License> licenses = licService.getHostLicenses(userId);
			flag = emailService.updateNameHost(user, licenses);
		
		}catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		if(!flag){
			status = ConstantUtil.GLOBAL_FAIL_FLAG;
			msg = "error";
		}
		return StringUtil.returnJsonStr(status, msg);
	}
	
	@AsController(path = "checkEmail")
	public Object checkEmail(@CParam("email")String email, HttpServletRequest request) throws Exception{
//		int status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
//		String msg = "ok";
		try{
			 UserBase user = userLogic.getUserBaseByEmail(email);
			 if(user!=null){
//				 status = ConstantUtil.GLOBAL_FAIL_FLAG;
//				 msg = siteService.getSiteBaseById(user.getSiteId()).getSiteSign();
//				 return StringUtil.returnJsonStr(status, msg);
				 return false;
			 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
//		return StringUtil.returnJsonStr(status, msg);
	}
	
	/**
	 * 检测登录手机号是否存在
	 * @param pmiId 登录手机号(PMI)
	 * @param email 登录邮箱
	 * @author Darren
	 * */
	@AsController(path = "checkPMI")
	public Object checkPMI(@CParam("pmiId")String pmiId,@CParam("email") String email, HttpServletRequest request) throws Exception{
		try{
			 UserBase currentUser = userLogic.getUserPmiByEmail(pmiId);
			 
			 if(currentUser == null){
				 return true;
			 }
			 //PMI号码和email对应的是同一个用户表示可以修改PMI，否则不可以
			 if(currentUser.getUserEmail().equals(email)){
				 return true;
			 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
