package com.bizconf.vcaasz.action.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.entity.MSGroup;
import com.bizconf.vcaasz.entity.MSGroupMap;
import com.bizconf.vcaasz.entity.MSSiteMap;
import com.bizconf.vcaasz.entity.MsServer;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.MsService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("ms")
@Interceptors({SystemUserInterceptor.class})
public class MsManageController {
	
	private final  Logger logger = Logger.getLogger(MsManageController.class);
	
	@Autowired
	MsService msService;
	@Autowired 
	UserService userService;
	
	/**
	 *显示所有的ms
	 * @return
	 */
	@AsController(path = "showmses")
	public Object showMSes(@CParam("pageNo")int pageNo,HttpServletRequest request,HttpServletResponse response){
		PageBean<MsServer> pageModel = msService.getAllMSesPage(pageNo,10);
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseHtmlWithList(pageModel.getDatas(), "msName","msDesc"));
		}
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/system/ms_list.jsp");
	}
	
	@AsController(path = "toEditeMs")
	public Object toEditeMs(@CParam("msId")int msId,HttpServletRequest request,HttpServletResponse response){
		MsServer ms = msService.getMSbyId(msId);
		if(ms!=null){
			ms = (MsServer)ObjectUtil.parseHtml(ms, "msName","msDesc");
		}
		request.setAttribute("ms", ms);
		return new ActionForward.Forward("/jsp/system/editMS.jsp");
	}
	
	/**
	 * 保存MS信息
	 * @param ms
	 * @param request
	 * @param response
	 * @return
	 */
	@AsController(path = "savemsinfo")
	public Object saveMS(MsServer ms,HttpServletRequest request,HttpServletResponse response){
		if(msService.saveMs(ms)){
			// 成功标志 1
			return StringUtil.returnJsonStr(1,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo1"));
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo2"));
	}
	
	//删除某个ms信息记录
	@AsController(path = "delms")
	public Object delMS(@CParam("msId")int msId,HttpServletRequest request,HttpServletResponse response){
		SystemUser systemUser = userService.getCurrentSysAdmin(request);
		int userId = systemUser==null?0:systemUser.getId();	
		if(msService.delMs(msId,userId)){
				// 成功标志 1
				return StringUtil.returnJsonStr(1, ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo3"));
		}
			//失败标志 2
			return StringUtil.returnJsonStr(2,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo4"));
	}
	
	@AsController(path = "msState")
	public Object setMSUseState(@CParam("msId")int msId,@CParam("state")int stateCode,HttpServletRequest request,HttpServletResponse response){
		if(stateCode!=1){
			stateCode = 0;
		}
		if(msService.setInUseState(msId, stateCode)){
			// 成功标志 1
			return StringUtil.returnJsonStr(1, ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo5"));
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2, ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo6"));
	}
	
	/**
	 * 显示所有的MS群组信息
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 */
	@AsController(path = "showmsgroups")
	public Object showMSGroups(@CParam("pageNo")int pageNo,HttpServletRequest request,HttpServletResponse response){
		PageBean<MSGroup> page = msService.getMsGroupsPage(pageNo, 10);
		if(page!=null && page.getDatas()!=null){
			page.setDatas(ObjectUtil.parseHtmlWithList(page.getDatas(), "groupName","groupDesc"));
			Map<Integer, String> msinfos = msService.getMsinfos(page.getDatas());
			request.setAttribute("msinfos", msinfos);
		}
		request.setAttribute("pageModel", page);
		return new ActionForward.Forward("/jsp/system/msGroup_list.jsp");
	}
	
	//编辑MS群组信息
	@AsController(path = "toEditeMsGroup")
	public Object toEditeMsGroup(@CParam("groupId")int groupId,HttpServletRequest request,HttpServletResponse response){
		MSGroup mgGroup = msService.getMSGroupById(groupId);
		if(mgGroup!=null){
			mgGroup = (MSGroup)ObjectUtil.parseHtml(mgGroup, "groupName","groupDesc");
		}
		request.setAttribute("mg", mgGroup);
		//当前的下属MS
		List<MsServer> mses = msService.getMSesByGroup(groupId);
		if(mses!=null && !mses.isEmpty()){
			StringBuffer msinfosBuilder = new StringBuffer();
			StringBuffer msidsBuilder = new StringBuffer();
			for (Iterator iterator = mses.iterator(); iterator.hasNext();) {
				MsServer msServer = (MsServer) iterator.next();
				if(msServer!=null){
					msServer = (MsServer)ObjectUtil.parseHtml(msServer, "msName","msDesc");
				}
				msinfosBuilder.append(msServer.getMsName());
				msinfosBuilder.append(",");
				msidsBuilder.append(msServer.getId());
				msidsBuilder.append(",");
			}
			request.setAttribute("msinfo", msinfosBuilder.toString());
			request.setAttribute("msids", msidsBuilder.toString());
		}
		return new ActionForward.Forward("/jsp/system/editMSGroup.jsp");
	}
	
	//显示MS选择页面
	@AsController(path = "showAllMsSelect")
	public Object showAllMsSelect(@CParam("groupId")int groupId,@CParam("selectedIds")String selectedIds,HttpServletRequest request,HttpServletResponse response){
			MSGroup mgGroup = msService.getMSGroupById(groupId);
			if(mgGroup!=null){
				mgGroup = (MSGroup)ObjectUtil.parseHtml(mgGroup, "groupName","groupDesc");
			}
			request.setAttribute("mg", mgGroup);
//			List<MsServer> mses = msService.getMSesByGroup(groupId);
//			if(mses!=null && !mses.isEmpty()){
//				Map<Integer, Boolean> msInfos = new HashMap<Integer, Boolean>();
//				for (Iterator it = mses.iterator(); it.hasNext();) {
//					MsServer msServer = (MsServer) it.next();
//					msInfos.put(msServer.getId(), true);
//				}
//				request.setAttribute("msInfos", msInfos);
//			}
			
			if(!StringUtil.isEmpty(selectedIds)){
				Map<Integer, Boolean> msInfos = new HashMap<Integer, Boolean>();
				String[] ids = selectedIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					msInfos.put(Integer.valueOf(ids[i]), true);
				}
				request.setAttribute("msInfos", msInfos);
			}
			//所有的MS服务器
			List<MsServer> allmes = msService.getAllMSes();
			if(allmes!=null && !allmes.isEmpty()){
				allmes = ObjectUtil.parseHtmlWithList(allmes, "msName","msDesc");
			}
			request.setAttribute("allmes", allmes);
			return new ActionForward.Forward("/jsp/system/msSelect.jsp");
	}
	/**
	 * 修改或者保存ms群组
	 * @param msGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@AsController(path = "savemsgroup")
	public Object saveMSGroup(@CParam("msIds")String msIdstr,MSGroup msGroup,HttpServletRequest request,HttpServletResponse response){
		MSGroup mGroup = msService.saveOrUpdateMsGroup(msGroup);
		if(mGroup!=null){
			// 先清除原来的ms服务器绑定
			msService.clearMsGroupConfig(mGroup.getId());
			if(msIdstr!=null && !msIdstr.equals("")){
				String[] msIds = msIdstr.split(",");
				for (int i = 0; i < msIds.length; i++) {
					MSGroupMap msGroupMap = new MSGroupMap();
					msGroupMap.setGroupId(mGroup.getId());
					msGroupMap.setMsId(Integer.parseInt(msIds[i].trim()));
					
					msService.saveMsGroupMap(msGroupMap);
				}
			}
			// 成功标志 1
			return StringUtil.returnJsonStr(1, ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo7"));
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo8"));
	}
	
	//删除某个群组
	@AsController(path = "delgroup")
	public Object delMSGroup(@CParam("groupId")int groupId,HttpServletRequest request,HttpServletResponse response){
		SystemUser systemUser = userService.getCurrentSysAdmin(request);
		int userId = systemUser==null?0:systemUser.getId();
		if(msService.MSGroupBeUsed(groupId)){
			return StringUtil.returnJsonStr(2,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.msgcannotdel"));
		}
		if(msService.clearSiteGroupSetting(groupId)&&msService.clearMsGroupConfig(groupId)&&msService.delMsGroup(groupId,userId)){
			// 成功标志 1
			return StringUtil.returnJsonStr(1,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo9"));
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2,ResourceHolder.getInstance().getResource("bizconf.jsp.system.mscontroller.warninfo10"));
	}
	
	@AsController(path = "showMGSites")
	public Object showMGSites(@CParam("pageNo")int pageNo,@CParam("groupId")int groupId,HttpServletRequest request,HttpServletResponse response){
		PageBean<SiteBase> page = msService.getMSGroupUsingSites(groupId, pageNo, 200);//分页预留
		request.setAttribute("pageModel", page);
		return new ActionForward.Forward("/jsp/system/msGroupSitelist.jsp");
	}
	
	/**
	 * 配置MS服务器群组
	 * @param groupId
	 * @param request
	 * @param response
	 * @return
	 */
	@AsController(path = "configmsgroup")
	public Object configMSGroup(int groupId,HttpServletRequest request,HttpServletResponse response){
		String[] msIds = request.getParameterValues("msId");
		if(groupId>0&&msIds!=null&&msIds.length>0 && msService.clearMsGroupConfig(groupId)){
			for (int i = 0; i < msIds.length; i++) {
				MSGroupMap msGroupMap = new MSGroupMap();
				msGroupMap.setGroupId(groupId);
				msGroupMap.setMsId(Integer.parseInt(msIds[i]));
				
				msService.saveMsGroupMap(msGroupMap);
			}
			//成功标志 1
			return StringUtil.returnJsonStr(1, "配置MS群组成功！");
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2, "配置MS群组失败！");
	}
	
	/**
	 * 站点的服务器群组配置
	 * @param groupId
	 * @param siteId
	 * @param request
	 * @param response
	 * @return
	 */
	@AsController(path = "configsitems")
	public Object configSiteMSGroup(int groupId,int siteId,HttpServletRequest request,HttpServletResponse response){
		
		if(groupId>0&&siteId>0 && msService.clearSiteMsConfig(siteId)){
			MSSiteMap msSiteMap = new MSSiteMap();
			msSiteMap.setMsGroupId(groupId);
			msSiteMap.setSiteId(siteId);
			
			msService.saveMSGroupSiteMap(msSiteMap);
			//成功标志 1
			return StringUtil.returnJsonStr(1, "配置MS群组成功！");
		}
		//失败标志 2
		return StringUtil.returnJsonStr(2, "配置MS群组失败！");
	}
}
