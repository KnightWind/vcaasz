package com.bizconf.vcaasz.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.LoginLog;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.LoginLogService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("loginLog")
@Interceptors({ SystemUserInterceptor.class })
public class LoginLogController {
	@Autowired
	LoginLogService loginLogService;
	@Autowired
	UserService userService;
	@Autowired
	SiteService siteService;

	/**
	 * 查询登录日志列表（通过登录名简单搜索）
	 * wangyong
	 * 2013-8-9
	 */
	@AsController(path = "getLogList")
	public Object getLogList(PageModel pageModel, HttpServletRequest request,
			@CParam("loginName") String loginName,
			@CParam("sortField") String sortField,
			@CParam("sortord") String sortord) {
		
		
		return getLogs(request, null, null, null, null, null, loginName, sortField, sortord, pageModel);
	}
	
	/**
	 * 查询登录日志列表（高级搜索）
	 * wangyong
	 * 2013-8-9
	 */
	@AsController(path = "getLogListAdvance")
	public Object getLogListAdvance(PageModel pageModel, HttpServletRequest request,
			@CParam("loginStatus") String loginStatus,
			@CParam("siteName") String siteName,
			@CParam("loginName") String loginName,
			@CParam("sortField") String sortField,
			@CParam("loginTimeBegin") String loginTimeBegin,
			@CParam("loginTimeEnd") String loginTimeEnd,
			@CParam("sortord") String sortord,
			@CParam("userType") String userType) {
		return getLogs(request, siteName, loginStatus, userType, loginTimeBegin, loginTimeEnd, loginName, sortField, sortord, pageModel);
	}
	
	@SuppressWarnings("unchecked")
	private Object getLogs(HttpServletRequest request,
			String siteName, String loginStatus,
			String userType, String loginTimeBegin, String loginTimeEnd, String loginName, String sortField,
			String sortord, PageModel pageModel){
		Integer pageNo = IntegerUtil.parseInteger(request.getParameter("pageNo"));
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		if(currentSysUser.getPageSize()>0){
			pageModel.setPageSize(currentSysUser.getPageSize());
		}else{
			pageModel.setPageSize(ConstantUtil.PAGESIZE_DEFAULT);
		}
		PageBean<LoginLog> logPage = loginLogService.getLoginLog(siteName,
				loginStatus, userType, loginTimeBegin, loginTimeEnd, loginName, pageNo,pageModel.getPageSize(), sortField, sortord);
		pageModel.setRowsCount(logPage.getRowsCount());
		List<LoginLog> loginLogList = null;
		if (logPage.getDatas() != null) {
			long offset = DateUtil.getDateOffset(); // 获取所在时区的Offset
			loginLogList = ObjectUtil.offsetDateWithList(logPage.getDatas(),
					offset, new String[] { "loginTime", "logoutTime" });
		}
		setRequestStr(request, loginLogList, siteName, loginStatus, userType, loginTimeBegin, loginTimeEnd,
				loginName, sortField, sortord, pageModel, loginLogList == null ? null : getSiteNameMap(loginLogList));
		return new ActionForward.Forward("/jsp/system/loginlog_list.jsp");
	}
	
	/**
	 * 获取站点名称的map集合
	 * wangyong
	 * 2013-8-13
	 */
	private Map<Integer, String> getSiteNameMap(List<LoginLog> loginLogList){
		Map<Integer, String> siteNameMap = new HashMap<Integer, String>();
		List<SiteBase> siteList = getSiteList(loginLogList);
		if(siteList != null && siteList.size() > 0){
			for(SiteBase site:siteList){
				siteNameMap.put(site.getId(), site.getSiteName());
			}
			siteNameMap.put(0, "- -");
		}else{
			siteNameMap.put(0, "- -");
		}
		return siteNameMap;
	}
	
	/**
	 * 根据站点ID号集合获取站点列表
	 * wangyong
	 * 2013-8-13
	 */
	private List<SiteBase> getSiteList(List<LoginLog> loginLogList){
		List<Integer> siteIdList = new ArrayList<Integer>();
		for(LoginLog log:loginLogList){
			siteIdList.add(log.getSiteId());
		}
		List<SiteBase> siteList = null;
		Integer[] values = null;
		if(siteIdList!=null && siteIdList.size() > 0){
			values=new Integer[siteIdList.size()];
			int ii=0;
			for(Integer siteId:siteIdList){
				values[ii]=siteId;
				ii++;
			}
			siteList = siteService.getSiteListBySiteIds(values);
		}
		return siteList;
	}

	/**
	 * 返回结果到前台
	 * wangyong
	 * 2013-8-9
	 */
	private void setRequestStr(HttpServletRequest request,
			List<LoginLog> loginLogList, String siteName, String loginStatus,
			String userType, String loginTimeBegin, String loginTimeEnd, String loginName, String sortField,
			String sortord, PageModel pageModel, Map<Integer, String> siteNameMap) {
		request.setAttribute("loginLogList", loginLogList);
		request.setAttribute("siteName", siteName);
		request.setAttribute("loginStatus", loginStatus);
		request.setAttribute("userType", userType);
		request.setAttribute("loginTimeBegin", loginTimeBegin);
		request.setAttribute("loginTimeEnd", loginTimeEnd);
		request.setAttribute("loginName", loginName);
		request.setAttribute("sortField", sortField);
		request.setAttribute("sortord", sortord);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("siteNameMap", siteNameMap);
	}

}
