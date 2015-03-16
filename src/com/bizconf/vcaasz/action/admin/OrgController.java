package com.bizconf.vcaasz.action.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.OrgUserCount;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteOrg;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.OrgService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;

/**
 * 组织机构controller
 * @author wangyong
 * 2013.5.6
 */
@ReqPath("org")
@Interceptors({SiteAdminInterceptor.class})
public class OrgController extends BaseController{

	private final  Logger logger = Logger.getLogger(NoticeController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	SiteService siteService;
	@Autowired
	OrgService orgService;
	
	/**
	 * 查询站点下的组织机构
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "orgListIndexBak")
	public Object orgListIndex(@CParam("orgName") String orgName, HttpServletRequest request){
		UserBase userBase = userService.getCurrentSiteAdmin(request);
		PageBean<SiteOrg> pageModel = orgService.getSiteOrgList(userBase.getSiteId());
//		if(pageModel != null && pageModel.getDatas() == null){
//			if(!saveFirstSiteOrg(siteService.getCurrentSiteBaseByAdminLogin(request))){  
//				logger.info("查询站点下的组织机构无根机构时，为站点创建根组织机构失败");
//			}
//			pageModel = orgService.getSiteOrgList(userBase.getSiteId());
//		}
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/admin/organization.jsp");
	}
	
	/**
	 * 查询站点下的组织机构
	 * 1.查出该组织机构的下一级组织机构(不包括自己)
	 * 2.组织机构共4层
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "orgListForJson/{id:([0-9]+)}")
	@Get
	public Object orgListForJson(@CParam("id") String id, HttpServletRequest request){
		JSONObject json = new JSONObject();
		UserBase userBase = userService.getCurrentSiteAdmin(request);
		PageBean<SiteOrg> pageModel = orgService.getSubOrgOnlyList(userBase.getSiteId(), Integer.parseInt(id));
		Map<Integer,Integer> participantSizeList = getParticipants(pageModel.getDatas());   //获取每个机构人数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("pageModel", pageModel);
		
		json.put("status", ConstantUtil.CREATE_CONF_SUCCEED);
		JSONArray jsonArrOrgUser = JSONArray.fromObject(pageModel.getDatas());
		json.put("orgUserList", jsonArrOrgUser);
		
		return json.toString();
	}	
	
	/**
	 * 查询站点下的组织机构
	 * 1.查出该组织机构以及下属组织机构
	 * 2.组织机构共4层
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "orgListIndex")
	public Object orgList(@CParam("orgName") String orgName,@CParam("sortField") String sortField,@CParam("sortord") String sortord,@CParam("pageNo") String pageNo, HttpServletRequest request){
		UserBase userBase = userService.getCurrentSiteAdmin(request);
		
		long time1=System.currentTimeMillis();
		
		PageBean<SiteOrg> pageModel = orgService.getSiteOrgList(userBase.getSiteId());

		long time2=System.currentTimeMillis();
		
		System.out.println("PageBean<SiteOrg>  time: "+(time2-time1)+" ms");
		
		
//		PageBean<SiteOrg> pageModel = orgService.getSiteSubOrgList(userBase.getSiteId(), Integer.parseInt(id), orgName, pageNo, sortField, sortord);
//		Map<Integer,Integer> participantSizeList = getParticipants(pageModel.getDatas());   //获取每个机构人数
		time1=System.currentTimeMillis();
		List<OrgUserCount> userCountList=orgService.getUserCountListBySiteId(userBase.getSiteId());
		
		time2=System.currentTimeMillis();
		System.out.println("List<OrgUserCount>   time: "+(time2-time1)+" ms");
		time1=System.currentTimeMillis();
		List<SiteOrg> orgList= pageModel.getDatas();
		HashMap<String, String> countMap=new HashMap<String, String>();
		if (orgList != null && orgList.size() > 0) {
			for (SiteOrg eachOrg : orgList) {
				if (eachOrg != null && userCountList!=null && userCountList.size()>0) {
					for (OrgUserCount eachCount : userCountList) {
						if (eachOrg != null
								&& !StringUtil.isEmpty(eachOrg.getOrgCode())
								&& !StringUtil.isEmpty(eachCount.getOrgCode())
								&& eachOrg.getOrgCode().equals(eachCount.getOrgCode())) {
							
							countMap.put(eachOrg.getOrgCode(), eachCount.getUserCount());
							userCountList.remove(eachCount);
							break;
						}
					}
				}
			}
		}

		time2=System.currentTimeMillis();
		System.out.println("HashMap<String, String>  time: "+(time2-time1)+" ms");
//		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("countMap", countMap);
//		request.setAttribute("orgId", id);
		return new ActionForward.Forward("/jsp/admin/site_org_list.jsp");
	}
	
	/**
	 * 分配用户
	 * 1.获取未分配组织机构的用户列表
	 * wangyong
	 * 2013-5-7
	 */
	@AsController(path = "getOrgUserList/{orgId:([0-9]+)}")
	public Object getOrgUserList(@CParam("orgId") String orgId,@CParam("orgName") String orgName,@CParam("sortField") String sortField,@CParam("sortord") String sortord,@CParam("pageNo") String pageNo, HttpServletRequest request){
		UserBase userBase = userService.getCurrentSiteAdmin(request);
		PageBean<UserBase> pageModel = null;
		List<SiteOrg> orgList = orgService.getSiteOrgList(userBase.getSiteId()).getDatas();
//		for(SiteOrg org : orgList){
//			if(org != null && org.getOrgLevel().intValue() == ConstantUtil.SITE_ORG_LEVEL_FIRST){
//				orgList.remove(org);    //移除根组织机构
//				break;
//			}
//		}
		if(userBase.isSuperSiteAdmin()){
			pageModel = orgService.getNoOrgUserList(orgName, sortField, sortord, userBase.getSiteId(), null, pageNo, userBase.getPageSize(), orgList);
		}else{
			pageModel = orgService.getNoOrgUserList(orgName, sortField, sortord, userBase.getSiteId(), userBase.getId(), pageNo,  userBase.getPageSize(), orgList);
		}
		request.setAttribute("orgId", orgId);
		request.setAttribute("orgName", orgName);
		request.setAttribute("sortField", sortField);
		request.setAttribute("sortord", sortord);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/admin/arrange_org_user.jsp");
	}
	
	/**
	 * 获取选定组织机构及下属组织机构的用户列表
	 * 1.站点管理员机构管理页面，查看机构下的用户列表
	 * @param id 组织机构ID
	 * wangyong
	 * 2013-5-7
	 */
	@AsController(path = "getOrgSubUserList/{id:([0-9]+)}")
	public Object getOrgSubUserList(@CParam("id") String id, @CParam("orgName") String orgName, @CParam("sortField") String sortField, @CParam("sortord") String sortord,@CParam("pageNo") String pageNo, HttpServletRequest request){
		UserBase userBase = userService.getCurrentSiteAdmin(request);
		PageBean<UserBase> pageModel = null;
		List<SiteOrg> orgList = orgService.getSiteSubOrgList(userBase.getSiteId(), Integer.parseInt(id)).getDatas();
		pageModel = orgService.getOrgSubUserList(orgName, sortField, sortord, userBase.getSiteId(), pageNo, userBase.getPageSize(), orgList);
		request.setAttribute("orgId", id);
		request.setAttribute("orgName", orgName);
		request.setAttribute("sortField", sortField);
		request.setAttribute("sortord", sortord);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/admin/site_org_user.jsp");
	}
	
	/**
	 * 为用户批量分配机构
	 * wangyong
	 * 2013-5-7
	 */
	@AsController(path = "updateOrgUserBatch")
	public Object updateOrgUserBatch(HttpServletRequest request){
		boolean updateFlag = false;
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		String orgId = request.getParameter("orgId");
		String[] ids = request.getParameter("id").split(",");
		if(ids != null && ids.length > 0){
			updateFlag = orgService.updateOrgUserBatch(orgId, ids, currentUser);
		}
		sysHelpAdminEventLog(updateFlag, userService.getCurrentSysAdmin(request), currentUser, 
				EventLogConstants.SYSTEM_ORG_BATCH, EventLogConstants.SITE_ORG_BATCH, "为用户批量分配机构成功", null, request);
		if(updateFlag){
			return returnJsonStr(EventLogConstants.EVENTLOG_SECCEED, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.assign.user.success"));
		}else{
			return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.assign.user.failed"));
		}
	}
	
	/**
	 * 新增站点下的组织机构(跳转页面)
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "add")
	@Get
	public Object addOrg(@CParam("pId") String pId, HttpServletRequest request){
		request.setAttribute("pId", pId);
		return new ActionForward.Forward("/jsp/admin/createOrg.jsp");
	}
	
	/**
	 * 新增站点下的组织机构
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "create")
	@Post
	public Object createOrg(SiteOrg siteOrg, HttpServletRequest request){
		boolean createFlag = false;
		if(siteOrg != null && StringUtil.isNotBlank(siteOrg.getOrgName())){
			UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
			if(orgNameValidate(siteOrg, currentSiteAdmin.getSiteId())){
				return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.department.exist"));
			}
			siteOrg = (SiteOrg)ObjectUtil.parseHtml(siteOrg, "orgName", "orgDesc");
			JSONObject json = new JSONObject();
			JSONArray jsonArrOrg = new JSONArray();
			SiteOrg org = orgService.addSiteOrg(siteOrg, currentSiteAdmin);
			if(org != null && org.getId() > 0){
				createFlag = true;
			}
			sysHelpAdminEventLog(createFlag, userService.getCurrentSysAdmin(request), currentSiteAdmin, 
					EventLogConstants.SYSTEM_ORG_CREATE, EventLogConstants.SITE_ORG_CREATE, "新增站点下的组织机构", null, request);
			if(createFlag){
				json.put("status", ConstantUtil.CREATESITE_SUCCEED);
				jsonArrOrg.add(JsonUtil.parseJsonWithFullFieldByObject(org));
				json.put("siteOrg", jsonArrOrg);
				return json.toString();
			}else{
				return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.action.add.failed"));
			}
		}
		return new ActionForward.Forward("/admin/org/orgList");
	}
	
	/**
	 * 修改站点下的组织机构(跳转页面)
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "update/{id:([0-9]+)}")
	@Get
	public Object updateOrg(@CParam("id") Integer id,HttpServletRequest request){
		SiteOrg siteOrg = orgService.getSiteOrgById(id);
		siteOrg = (SiteOrg)ObjectUtil.parseChar(siteOrg, "orgName", "orgDesc");
		request.setAttribute("siteOrg", siteOrg);
		return new ActionForward.Forward("/jsp/admin/createOrg.jsp");
	}
	
	/**
	 * 修改站点下的组织机构
	 * wangyong
	 * 2013-5-6
	 */
	@AsController(path = "update")
	public Object updateOrgInfo(SiteOrg siteOrg, HttpServletRequest request){
		boolean createFlag = false;
		if(siteOrg != null && StringUtil.isNotBlank(siteOrg.getOrgName())){
			UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
			if(orgNameValidate(siteOrg, currentSiteAdmin.getSiteId())){
				return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.department.exist"));
			}
			siteOrg = (SiteOrg)ObjectUtil.parseHtml(siteOrg, "orgName", "orgDesc");
			JSONObject json = new JSONObject();
			JSONArray jsonArrOrg = new JSONArray();
			SiteOrg org = orgService.updateSiteOrg(siteOrg);
			if(org != null && org.getId() > 0){
				createFlag = true;
			}
			sysHelpAdminEventLog(createFlag, userService.getCurrentSysAdmin(request), currentSiteAdmin, 
					EventLogConstants.SYSTEM_ORG_UPDATE, EventLogConstants.SITE_ORG_UPDATE, "修改站点下的组织机构", null, request);
			if(createFlag){
				json.put("status", ConstantUtil.CREATESITE_SUCCEED);
				jsonArrOrg.add(JsonUtil.parseJsonWithFullFieldByObject(org));
				json.put("siteOrg", jsonArrOrg);
				return json.toString();
			}else{
				return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.action.update.failed"));
			}
		}
		return new ActionForward.Forward("/admin/org/orgList");
	}
	
	/**
	 * 删除站点下的组织机构
	 * wangyong
	 * 2013-5-6 
	 */
	@AsController(path = "delete/{id:([0-9]+)}")
	@Post
	public Object deleteNotice(@CParam("id") Integer id, HttpServletRequest request){
		logger.info("id=="+id);
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		List<SiteOrg> orgList = orgService.getSiteSubOrgList(currentSiteAdmin.getSiteId(), id).getDatas();
		boolean deleteFlag = orgService.deleteSiteOrg(id, currentSiteAdmin);
		if(!deleteFlag){
			return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.action.delete.failed"));
		}
		//删除机构成功后，移除相关联的用户
		List<UserBase> userList = orgService.getOrgSubUserList(currentSiteAdmin.getSiteId(), orgList).getDatas();
		if(userList != null && userList.size() > 0){
			String [] ids = new String[userList.size()];
			for(int i=0;i<userList.size();i++){
				ids[i] = String.valueOf(userList.get(i).getId());
			}
			orgService.delUserFromOrgBatch(ids);
		}
		sysHelpAdminEventLog(deleteFlag, userService.getCurrentSysAdmin(request), currentSiteAdmin, 
				EventLogConstants.SYSTEM_ORG_DELETE, EventLogConstants.SITE_ORG_DELETE, "删除站点下的组织机构", null, request);
		return returnJsonStr(EventLogConstants.EVENTLOG_SECCEED, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.action.delete.success"));
	}
	
	/**
	 * 从组织机构中移除用户
	 * wangyong
	 * @param id 用户ID
	 * 2013-5-6 
	 */
	@AsController(path = "removeUserFromOrg/{id:([0-9]+)}")
	@Post
	public Object removeUserFromOrg(@CParam("id") Integer id, HttpServletRequest request){
		logger.info("id=="+id);
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		boolean deleteFlag = orgService.delUserFromOrg(id);
		if(!deleteFlag){
			return returnJsonStr(EventLogConstants.EVENTLOG_FAIL, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.remove.user.failed"));
		}
		sysHelpAdminEventLog(deleteFlag, userService.getCurrentSysAdmin(request), currentSiteAdmin, 
				EventLogConstants.SYSTEM_ORG_USER_REMOVE, EventLogConstants.SITE_ORG_DELETE, "从组织机构中移除用户", null, request);
		return returnJsonStr(EventLogConstants.EVENTLOG_SECCEED, ResourceHolder.getInstance().getResource("bizconf.jsp.admin.remove.user.success"));
	}
	
	/**
	 * 预览站点下的组织机构
	 * wangyong
	 * 2013-5-6 
	 */
	@AsController(path = "view/{id:([0-9]+)}")
	@Get
	public Object viewSiteOrg(@CParam("id") Integer id,HttpServletRequest request){
		SiteOrg siteOrg = orgService.getSiteOrgById(id);
		siteOrg = (SiteOrg)ObjectUtil.parseChar(siteOrg, "orgName", "orgDesc");
		request.setAttribute("siteOrg", siteOrg);
		return new ActionForward.Forward("/jsp/admin/site_org_view.jsp");
	}
	
	/**
	 * 返回json字符串对象(创建，修改公告出错时)
	 * status 失败
	 * object 失败原因
	 * wangyong
	 * 2013-2-22
	 */
	private String returnJsonStr(int status, Object object){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", object.toString());
		return json.toString();
	}
	
	/**
	 * 获取每个组织机构的人数
	 * wangyong
	 * 2013-3-25
	 */
	private Map<Integer,Integer> getParticipants(List<SiteOrg> orgList){
		Map<Integer,Integer> ParticipantsMap = new HashMap<Integer, Integer>();
		if(orgList != null && orgList.size() > 0){
			for(SiteOrg org:orgList){
				List<UserBase> Participants = orgService.getOrgUserList(org);
				ParticipantsMap.put(org.getId(), Participants == null ? 0 : Participants.size());
			}
		}
		return ParticipantsMap;
	}
	
	/**
	 * 添加或修改部门名称是否在同一级别部门已存在
	 * @return true 存在   false 不存在
	 * wangyong
	 * 2013-5-23
	 */
	private boolean orgNameValidate(SiteOrg siteOrg, int siteId){
		if(siteOrg == null){
			return false;
		}
		SiteOrg org = null;
		if(siteOrg != null && siteOrg.getId() != null && siteOrg.getId().intValue() > 0){    //修改部门
			SiteOrg oldSiteOrg = orgService.getSiteOrgById(siteOrg.getId());
			org = orgService.getSiteOrgByName(siteOrg.getOrgName(), oldSiteOrg.getParentId().intValue(), siteId);
			if(org != null && org.getId().intValue() > 0 && org.getId().intValue() != siteOrg.getId().intValue()){
				return true;
			}
		}else{     //添加部门
			org = orgService.getSiteOrgByName(siteOrg.getOrgName(), siteOrg.getParentId().intValue(), siteId);
			if(org != null && org.getId().intValue() > 0){
				return true;
			}
		}
		return false;
	}
	
}
