package com.bizconf.vcaasz.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUserCount;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
@ReqPath("conf")
public class ConfController extends BaseController{
	private final  Logger logger = Logger.getLogger(ConfController.class);
	
	@Autowired
	ConfService confService;
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	
	/**
	 * 根据站点标识、会议主题或站点名称查询会议
	 * dick
	 * 2013-2-5
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "list")
	public Object list(HttpServletRequest request, PageModel pageModel, HttpServletResponse response) {
		List<ConfBase> confList = null;
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		String pageNo=String.valueOf(request.getParameter("pageNo"));
		String sortField=String.valueOf(request.getParameter("sortField"));
		String titleOrSiteSign=String.valueOf(request.getParameter("titleOrSiteSign"));
		String sortord=String.valueOf(request.getParameter("sortord"));
		if(currentSysUser.getPageSize()>0){
			pageModel.setPageSize(currentSysUser.getPageSize());
		}else{
			pageModel.setPageSize(ConstantUtil.PAGESIZE_DEFAULT);
		}
		if(titleOrSiteSign==null || "".equals(titleOrSiteSign.trim()) || "null".equals(titleOrSiteSign.trim().toLowerCase())){
			titleOrSiteSign="";
		}
		if(pageNo==null || "".equals(pageNo.trim()) || "null".equals(pageNo.trim().toLowerCase())){
			pageNo="1";
		}
		if(sortField==null || "".equals(sortField.trim()) || "null".equals(sortField.trim().toLowerCase())){
			sortField="";
		}
		pageModel.setPageNo(pageNo);
//		int rowsCount = 0;
//		if(currentSysUser.isSuperSystemAdmin()|| currentSysUser.isSystemClientServer()){    //权限控制
//			rowsCount = confService.countConfListByBaseCondition(titleOrSiteSign, null);
//		}else{
//			rowsCount = confService.countConfListByBaseCondition(titleOrSiteSign, currentSysUser.getId());
//		}
//		if(currentSysUser.isSuperSystemAdmin() || currentSysUser.isSystemClientServer()){    //权限控制
//			confList = confService.getConfListByBaseCondition(titleOrSiteSign, sortField, sortord, pageModel, null);
//		}else{
//			confList = confService.getConfListByBaseCondition(titleOrSiteSign, sortField, sortord, pageModel, currentSysUser.getId());
//		}
		PageBean<ConfBase> page = new PageBean<ConfBase>();
		if(currentSysUser.isSuperSystemAdmin() || currentSysUser.isSystemClientServer()){    //权限控制
			page = confService.getSysConfByName(titleOrSiteSign, sortField, sortord, pageModel, null);
		}else{
			page = confService.getSysConfByName(titleOrSiteSign, sortField, sortord, pageModel, currentSysUser.getId());
		}
		if(currentSysUser.isSystemClientServer()){ 
			request.setAttribute("isCS", true);
		}
		confList = page.getDatas();
		if(currentSysUser.isSystemClientServer()){//客服系统
			pageModel = new PageModel();//重新定义一个
		}else{
			pageModel.setRowsCount(page.getRowsCount());
			pageModel.getPageCount();
		}
		
		List<SiteBase> siteList = null;
		List<ConfUserCount> userCountList = null;
		List<Integer> confIdList = new ArrayList<Integer>();
		List<Integer> cycleIdList = new ArrayList<Integer>();
		List<Integer> siteIdList = new ArrayList<Integer>();
		if(confList!=null && confList.size() > 0){
			for(ConfBase confBase:confList){
				if(confBase!=null){
					siteIdList.add(confBase.getSiteId());
					if(confBase.getCycleId()!=null && confBase.getCycleId().intValue() > 0){
						cycleIdList.add(confBase.getCycleId());
					}else{
						confIdList.add(confBase.getId());
					}
				}
			}
		}
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "endTime";
		long offset = DateUtil.getDateOffset();    //获取所在时区的Offset
		confList = ObjectUtil.offsetDateWithList(confList, offset, fields);
		userCountList = getUserCountList(confIdList, cycleIdList);
		siteList = getSiteList(siteIdList);
		
		//这里分别获取PC终端数量和电话终端数量
		Map<Integer,Integer> terminalPcs = confService.getConfsTerminalNums(confList, ConfConstant.CONF_USER_TERM_TYPE_PC);
		request.setAttribute("terminalPcs", terminalPcs);
		Map<Integer,Integer> terminalPhones = confService.getConfsTerminalNums(confList, ConfConstant.CONF_USER_TERM_TYPE_MOBILE);
		request.setAttribute("terminalPhones", terminalPhones);
		Map<Integer,Integer> allTerminals = confService.getConfsTerminalNums(confList, null);//所有参会者总数
		request.setAttribute("allTerminals", allTerminals);
		
		if(currentSysUser.isSystemClientServer()){
			confList = null;
			userCountList = null;
			siteList = null;
			pageModel.setRowsCount(0);
			pageModel.setPageNo("1");
			pageModel.setBeginRow(1);
			pageModel.setPageSize(10);
		}
		request.setAttribute("confList", confList);
		request.setAttribute("titleOrSiteSign", titleOrSiteSign);
		request.setAttribute("userCountList", userCountList);
		request.setAttribute("siteList", siteList);
		request.setAttribute("sortField", sortField);
		request.setAttribute("sortord", sortord);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/system/conf_list.jsp");
	}
	
	/**
	 * 根据站点ID号集合获取站点列表
	 * wangyong
	 * 2013-2-18
	 */
	private List<SiteBase> getSiteList(List<Integer> siteIdList){
		List<SiteBase> siteList=null;
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
	 * 根据会议的ID号统计参会人数
	 * wangyong
	 * 2013-2-18
	 */
	private List<ConfUserCount> getUserCountList(List<Integer> confIdList, List<Integer> cycleIdList){
		List<ConfUserCount> userCountList = null;
		Integer[] values = null;
		if(confIdList!=null && confIdList.size() >0){
			values = new Integer[confIdList.size()];
			int ii=0;
			for(Integer confId:confIdList){
				values[ii]=confId;
				ii++;
			}
			userCountList=confService.countConfUserByConfIds(values);  //根据会议的ID号统计周期会议的参会人数
		}
		if(cycleIdList!=null && cycleIdList.size() >0){
			values=new Integer[cycleIdList.size()];
			int ii=0;
			for(Integer confId:cycleIdList){
				values[ii]=confId;
				ii++;
			}
			List<ConfUserCount> tmpCountList=null;
			tmpCountList=confService.countConfUserByCycleIds(values); //根据周期会议的周期ID号统计周期会议的参会人数
			if(userCountList==null  || userCountList.size()<=0){
				userCountList = new ArrayList<ConfUserCount>();
			}
			if(tmpCountList!=null){
				userCountList.addAll(tmpCountList);
			}
		}
		return userCountList;
	}
	
	/**
	 * 会议高级搜索
	 * wangyong
	 * 2013-2-18
	 * @param titleOrSiteSign 搜索查询的会议标题、企业标识和企业名称
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "listWithCondition")
	public Object listWithCondition(@CParam("titleOrSiteSign") String titleOrSiteSign ,
			SiteBase siteBase, ConfBase confBase, PageModel pageModel, @CParam("confStatus") Integer confStatus ,@CParam("kefu") Integer kefu, HttpServletRequest request) throws Exception{
		
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);

		if(currentSysUser == null){
			return null;
		}

		if(currentSysUser.isSystemClientServer()){ 
			request.setAttribute("isCS", true);
		}
		
		List<ConfBase> confList = null;
		List<ConfUserCount> userCountList = null;
		List<SiteBase> siteList = null;
		
		String siteName = siteBase.getSiteName();
		String siteSign = siteBase.getSiteSign();
		
		String effeDateStart = request.getParameter("effeDateStart");
		String effeDateEnd = request.getParameter("effeDateEnd");
		Date beginTime = DateUtil.StringToDate(effeDateStart, "yyyy-MM-dd");
		Date endTime = DateUtil.StringToDate(effeDateEnd, "yyyy-MM-dd");
		String sortField = request.getParameter("sortField");
		String sortord = request.getParameter("sortord");
		PageBean<ConfBase> page = new PageBean<ConfBase>();
		
		if(currentSysUser.getPageSize()>0){
			pageModel.setPageSize(currentSysUser.getPageSize());
		}else{
			pageModel.setPageSize(ConstantUtil.PAGESIZE_DEFAULT);
		}
		if(currentSysUser.isSuperSystemAdmin() || currentSysUser.isSystemClientServer()){    //权限控制
			page = confService.getSysConfByCondition(confBase, siteName, siteSign,titleOrSiteSign, beginTime, endTime, sortField, sortord, pageModel, null);
		}else{
			page = confService.getSysConfByCondition(confBase, siteName, siteSign,titleOrSiteSign, beginTime, endTime, sortField, sortord, pageModel, currentSysUser.getId());
		}
		confList = page.getDatas();
		pageModel.setRowsCount(page.getRowsCount());
		pageModel.getPageCount();
		
		
		try {
			List<Integer> confIdList = new ArrayList<Integer>();
			List<Integer> cycleIdList = new ArrayList<Integer>();
			List<Integer> siteIdList = new ArrayList<Integer>();
			if(confList!=null && confList.size() > 0){
				for(ConfBase conf:confList){
					if(conf!=null){
						siteIdList.add(conf.getSiteId());
						if(conf.getCycleId()!=null && conf.getCycleId().intValue() > 0){
							cycleIdList.add(conf.getCycleId());
						}else{
							confIdList.add(conf.getId());
						}
					}
				}
			}
			userCountList = getUserCountList(confIdList, cycleIdList);
			siteList = getSiteList(siteIdList);
		} catch (Exception e) {
			logger.error("会议高级搜索获取列表出错!"+e);
		}
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "endTime";
		long offset = DateUtil.getDateOffset();    //获取所在时区的Offset
		confList = ObjectUtil.offsetDateWithList(confList, offset, fields);
		Map<Integer,Integer> allTerminals = confService.getConfsTerminalNums(confList, null);//所有参会者总数
		String cs ="second";
		if(kefu == 1){
			pageModel = new PageModel();
			 cs = "first";
		}
		request.setAttribute("cs", cs);
		request.setAttribute("allTerminals", allTerminals);
		request.setAttribute("confList", confList);
		request.setAttribute("userCountList", userCountList);
		request.setAttribute("siteList", siteList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("sortField", sortField);   //传排序字段的编号
		request.setAttribute("sortord", sortord);       //传排序方式的编号
		
		sortAttribute(siteBase, confBase, request);                    //向前台传递高级搜索表单值
		return new ActionForward.Forward("/jsp/system/conf_list.jsp");
	}
	
	/**
	 * 获取页面传递来的排序参数
	 * wangyong
	 * 2013-2-18
	 */
	private String initSort(String field){
		String sortField = null;
		for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
			if (eachField != null && eachField[0].equals(field)) {
				sortField = BeanUtil.att2Field(eachField[1]);
				break;
			}
		}
		return sortField;
	}
	
	/**
	 * 向前台传递高级搜索表单值
	 * wangyong
	 * 2013-1-23
	 */
	private void sortAttribute(SiteBase siteBase, ConfBase confBase, HttpServletRequest request){
		if(siteBase!=null){
			if(StringUtil.isNotBlank(siteBase.getSiteName())){
				request.setAttribute("siteName", siteBase.getSiteName());
			}
			if(StringUtil.isNotBlank(siteBase.getSiteSign())){
				request.setAttribute("siteSign", siteBase.getSiteSign());
			}
		}
		if(confBase!=null){
			if(StringUtil.isNotBlank(confBase.getConfName())){
				request.setAttribute("confName", confBase.getConfName());
			}
			if(confBase.getConfType() != null && confBase.getConfType().intValue() > 0){
				request.setAttribute("confType", confBase.getConfType().intValue());
			}
			if(confBase.getConfStatus() != null && confBase.getConfStatus().intValue() >= 0){
				request.setAttribute("confStatus", confBase.getConfStatus().intValue());
			}
		}
		if(StringUtil.isNotBlank(request.getParameter("effeDateStart"))){
			request.setAttribute("effeDateStart", request.getParameter("effeDateStart"));
		}
		if(StringUtil.isNotBlank(request.getParameter("effeDateEnd"))){
			request.setAttribute("effeDateEnd", request.getParameter("effeDateEnd"));
		}
	}
	
	/**
	 * 根据主持人ID查询主持人信息
	 * outin_quan
	 * 2013-1-15	
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "viewHost")
	public Object viewSite(@CParam("id") Integer id,HttpServletRequest request) throws Exception{
		UserBase userBase = userService.getUserBaseById(id);
		request.setAttribute("userBase", userBase);
		return new ActionForward.Forward("/jsp/system/viewHost.jsp");
	}
}
