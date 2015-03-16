package com.bizconf.vcaasz.action.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.constant.UserConstant;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EnterpriseAdminService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.DownLoadUtil;
import com.bizconf.vcaasz.util.ExcelUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Post;

/**
 * 企业站点信息
 * @author wangyong
 * 2013/2/23
 */
@ReqPath("site")
@Interceptors({SiteAdminInterceptor.class})
public class SiteController extends BaseController {
	private final Logger logger = Logger.getLogger(SiteController.class);
	
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	LicService licService;
	
	@Autowired
	SiteLogic siteLogic;
	
	@Autowired
	UserLogic userLogic;
	
	@Autowired 
	ZoomUserOperationComponent zoomUserOperator;
	
	@Autowired 
	EnterpriseAdminService enterpriseAdminService;
	
	@Autowired
	DataCenterService centerService;
	/**
	 * 获取当前站点时区的站点信息
	 * shhc
	 * 2013-6-3
	 */
	private void getOffsetSiteBase(SiteBase siteBase){
		String[] fields = new String[]{"effeDate", "expireDate"};
		long offset = 0 ;
		if(siteBase != null){
			offset = siteBase.getTimeZone();
		}else{
			offset = DateUtil.getDateOffset();
		}
		siteBase = (SiteBase) ObjectUtil.offsetDate(siteBase, offset, fields);
	}
	
	/**
	 * 获取企业站点信息（超级企业管理员）
	 * wangyong
	 * 2013-2-23
	 */
	@AsController(path = "info")
	public Object siteInfo(HttpServletRequest request){
		UserBase siteAdmin = userService.getCurrentSiteAdmin(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		currentSite.setLicense(licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());
		getOffsetSiteBase(currentSite);
		request.setAttribute("siteBase", currentSite);
		request.setAttribute("siteAdmin", siteAdmin);
		PageBean<UserBase> pageModel = licService.getHostsBySite(null,currentSite.getId(), 1,200);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("siteId", currentSite.getId());
		//request.setAttribute("licnums", licService.getHostsLienseDatas(pageModel.getDatas()));
		request.setAttribute("effeDates", licService.getHostsLienseEffeDates(pageModel.getDatas(), currentSite.getTimeZone()));
		return new ActionForward.Forward("/jsp/admin/siteBase_info.jsp");
	}
	
	/**
	 * 修改企业站点信息（超级企业管理员）
	 * wangyong
	 * return msgCode 1,成功  2,失败
	 * 2013-2-23
	 */
	@AsController(path = "update")
	public Object updateSiteInfo(SiteBase siteBase, HttpServletRequest request){
		boolean flag = false;
		SiteBase site = null;
		Integer msgCode = 0;
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		SiteBase oldSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		if(siteBase != null){
			siteBase = (SiteBase)ObjectUtil.parseHtml(siteBase, "siteName", "enName");	//字符转义
			try {
//				Integer timeZone=SiteConstant.TIMEZONE_WITH_CITY_MAP.get(siteBase.getTimeZoneId()).getOffset();
//				site = siteService.updateSiteBaseForSiteAdmin(siteBase.getId(), siteBase.getSiteName(), 
//						siteBase.getEnName(), siteBase.getSiteLogo(),siteBase.getSiteBanner(), 
//						siteBase.getTimeZoneId(),timeZone);
				//2013.10.28  新版本中不修改站点时区
				site = siteService.updateSiteBaseForSiteAdmin(siteBase.getId(), siteBase.getSiteName(), 
						siteBase.getEnName(), siteBase.getSiteLogo(), siteBase.getSiteBanner(), 
						oldSite.getTimeZoneId(), oldSite.getTimeZone());
			}catch (Exception e){
				logger.error("修改站点信息失败");
			}
			try{
				if(site != null && site.getId() > 0){
					flag = true;
//					eventLogService.saveAdminEventLog(currentSiteAdmin, EventLogConstants.SITE_INFO_UPDATE, ResourceHolder.getInstance().getResource("system.notice.list.Create.1"),ResourceHolder.getInstance().getResource("system.notice.list.Create.1"), EventLogConstants.EVENTLOG_SECCEED, siteBase, request);   //创建成功后写EventLog
					msgCode = 1;
				}else{
					msgCode = 2;
					logger.error("记录修改站点信息失败");
				}
			} catch (Exception e) {
				msgCode = 2;
				logger.error("记录修改站点信息失败" + e);
			} finally{
				if(msgCode == 1){
					sysHelpAdminEventLog(flag, null, currentSiteAdmin, 
							EventLogConstants.SYSTEM_INFO_UPDATE, EventLogConstants.SITE_INFO_UPDATE, ResourceHolder.getInstance().getResource("system.site.meaasge.update.succeed"), site, request);
				}else{
					sysHelpAdminEventLog(flag, null, currentSiteAdmin, 
							EventLogConstants.SYSTEM_INFO_UPDATE, EventLogConstants.SITE_INFO_UPDATE, ResourceHolder.getInstance().getResource("system.site.meaasge.update.failed"), site, request);
				}
				
			}
		}
		request.setAttribute("msgCode", msgCode);
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		request.setAttribute("msgCode", msgCode);
		currentSite.setLicense(licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());
		request.setAttribute("siteBase", currentSite);
		request.setAttribute("siteAdmin", currentSiteAdmin);
		return new ActionForward.Forward("/admin/site/info");
	}
	
	/**
	 * 跳转主持人修改编辑页面
	 * */
	@AsController(path = "goEditHost")
	public Object goEditHost(@CParam("hostId") int hostId,@CParam("siteId") int siteId, 
			HttpServletRequest request) throws Exception{
		
		UserBase host = userService.getUserBaseById(hostId);
		request.setAttribute("user", host);
		request.setAttribute("siteId", siteId);
		
		SiteBase site = siteService.getSiteBaseById(siteId);
		request.setAttribute("currentsite", site);
		return new ActionForward.Forward("/jsp2.0/admin/add_site_user.jsp");
	}
	
	/**
	 * 修改企业站点下的主持人信息
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "saveHost")
	@Post
	public Object saveHost(UserBase user, HttpServletRequest request) throws Exception{
		UserBase siteAdmin = userService.getCurrentSiteAdmin(request);
		user.setUserRole(ConstantUtil.USERROLE_HOST);
		if(user ==null || user.getSiteId()==null || user.getSiteId().intValue() == 0){
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "save host user failed. site id must not be empty!");
		}
		String orgPass = user.getLoginPass();
		
		int moudleID = EventLogConstants.SITE_ADMIN_USER_UPDATE;
		if(user.getId()!=null && user.getId()>0){
			UserBase orgUser = userService.getUserBaseById(user.getId()); //原始的用户信息
			orgUser.setLoginName(user.getLoginName());
			orgUser.setTrueName(user.getTrueName());
			orgUser.setEnName(user.getEnName());
			orgUser.setMobile(user.getMobile());
			orgUser.setRemark(user.getRemark());
			orgUser.setNumPartis(user.getNumPartis());
			
			//Add by Darren 2014-12-24
			DataCenter dataCenter = centerService.queryDataCenterById(orgUser.getDataCenterId());
			
			if(StringUtil.isNotBlank(user.getLoginPass()) && zoomUserOperator.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),orgUser.getZoomId(), user.getLoginPass())){
				orgUser.setLoginPassPlain(user.getLoginPass());
				orgUser.setLoginPass(MD5.encodePassword(user.getLoginPass(), "MD5"));
				orgUser.setPassEditor(siteAdmin.getId());
			}
			
			if(zoomUserOperator.update(dataCenter.getApiKey(),dataCenter.getApiToken(),orgUser.getZoomId(), user.getZoomType(), user.getNumPartis(), user.getTrueName(), "", false, false) != UserConstant.UPDATE_USER_SUCCESS){
				//记录日志
				eventLogService.saveAdminEventLog(
						siteAdmin, moudleID,
						ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.2"), EventLogConstants.EVENTLOG_FAIL, orgUser,
						request);
				
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, "update user info to zoom data center failed !");
			
			}
			
			if(userService.siteUserSaveable(orgUser)){
				boolean flag = enterpriseAdminService.updateUserBase(orgUser);
				if(flag){
					orgUser.setLoginPass(orgPass);
				}
			}else{
				eventLogService.saveAdminEventLog(
						siteAdmin, moudleID,
						ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.2"), EventLogConstants.EVENTLOG_FAIL, orgUser,
						request);
				return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
			}
			
			eventLogService.saveAdminEventLog(
					siteAdmin, moudleID,
					ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.1"), EventLogConstants.EVENTLOG_SECCEED, orgUser,
					request);
			return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_SUCCEED, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.update.1"));
		}
		return StringUtil.returnJsonStr(ConstantUtil.CREATESITEUSER_FAIL, ResourceHolder.getInstance().getResource("siteAdmin.siteUser.failinfo.reparted"));
	}
	
	/**
	 * 获取企业站点的主持人列表
	 * darren
	 * 2014-08-19
	 */
	@AsController(path = "listHosts")
	public Object listHosts(@CParam("keyword") String keyword,
			@CParam("pageNo") Integer pageNo, HttpServletRequest request){
		UserBase siteAdmin = userService.getCurrentSiteAdmin(request);
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		currentSite.setLicense(licService.getSiteLicenseNum(currentSite.getId()) + currentSite.getLicense().intValue());
		getOffsetSiteBase(currentSite);
		request.setAttribute("siteBase", currentSite);
		request.setAttribute("siteAdmin", siteAdmin);
		PageBean<UserBase> pageModel = licService.getHostsBySite(keyword,currentSite.getId(), pageNo,siteAdmin.getPageSize());
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("siteId", currentSite.getId());
		request.setAttribute("keyword", keyword);
		return new ActionForward.Forward("/jsp/admin/host_list.jsp");
	}
	
	/**
	 * 导出主持人到Excel
	 *	@since 2014-08-20
	 * @author Darren
	 */
	@AsController(path = "exportHosts")
	public void exportContacts(@CParam("keyword") String keyword,HttpServletRequest request,HttpServletResponse response) {

		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		List<UserBase> userBases = userService.exportHosts(keyword, null, currentUser.getSiteId(), currentUser.getId());
		List<Object[]> exportList = new ArrayList<Object[]>();
		
		//表格头部
		Object[] headers = new Object[8];
		headers[0] = ResourceHolder.getInstance().getResource("bizconf.jsp.enContacts_list.res6");//"姓名";
		headers[1] = ResourceHolder.getInstance().getResource("system.sysUser.list.enName");//"英文名";
		headers[2] = ResourceHolder.getInstance().getResource("system.sysUser.list.email");//"邮箱";
		headers[3] = ResourceHolder.getInstance().getResource("system.sysUser.list.telephone");//"电话";
		headers[4] = ResourceHolder.getInstance().getResource("system.sysUser.list.mobile");//"手机";
		
		headers[5] = ResourceHolder.getInstance().getResource("website.user.login.loginpass");//"密码";
		headers[6] = ResourceHolder.getInstance().getResource("system.site.list.License");//"最大方数";
		headers[7] = ResourceHolder.getInstance().getResource("system.site.list.Remark");//"备注";
		
		exportList.add(headers);
		//表格内容
		if(userBases!=null && userBases.size()>0){
			for (Iterator<UserBase> userBase = userBases.iterator(); userBase.hasNext();) {
				UserBase user =  userBase.next();
				Object[] userObjs = new Object[8];
				
				userObjs[0] = user.getTrueName()==null?"":user.getTrueName();
				userObjs[1] = user.getEnName()==null?"":user.getEnName();
				userObjs[2] = user.getUserEmail()==null?"":user.getUserEmail();
				userObjs[3] = user.getPhone()==null?"":user.getPhone();
				userObjs[4] = user.getMobile()==null?"":user.getMobile();
				userObjs[5] = user.getLoginPassPlain()==null?"":user.getLoginPassPlain();
				userObjs[6] = user.getNumPartis();
				userObjs[7] = user.getRemark()==null?"":user.getRemark();
				
				exportList.add(userObjs);
			}
		}
		String exportPath=request.getSession().getServletContext().getRealPath("/");
		String exportFileName="export/export_contacts"+Math.random()+".xls";
		String filePathName=exportPath+""+exportFileName;
		System.out.println("request.getSession().getServletContext().getRealPath(\"/\")=="+request.getSession().getServletContext().getRealPath("/"));
		 try {
			ExcelUtil.createExcel(filePathName, exportList);
			DownLoadUtil.downloadFile(response, filePathName, true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
}
