package com.bizconf.vcaasz.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.NoticeService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
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
 * 公告controller
 * @author wangyong
 * @date 2013.2.22
 */
@ReqPath("notice")
@Interceptors({SiteAdminInterceptor.class})
public class NoticeController extends BaseController{
	private final  Logger logger = Logger.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	SiteService siteService;
	
	/**
	 * 获取全部站点公告信息列表
	 * wangyong
	 * 2013-2-22
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "list")
	//@CParam("input.filename") LiberCFile file
	public Object list(PageModel pageModel, HttpServletRequest request){
		List<Notice> noticeList = null;
		List<String> publishUserList = new ArrayList<String>();
		Integer rows = 0;
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		try {
			if(currentSiteAdmin.isSuperSiteAdmin()){    //权限控制
				rows = noticeService.countNoticeBySiteId(currentSiteAdmin.getSiteId(), null);
			}else{
				rows = noticeService.countNoticeBySiteId(currentSiteAdmin.getSiteId(), currentSiteAdmin.getId());
			}
		} catch (Exception e) {
			logger.error("获取页数出错!"+e);
		}
		pageModel.setRowsCount(rows);
		pageModel.setPageSize(currentSiteAdmin.getPageSize());    //2013.6.26 修改为按用户设置每页显示条数
		try {
			if(currentSiteAdmin.isSuperSiteAdmin()){    //权限控制
				noticeList = noticeService.getNoticeListBySiteId(currentSiteAdmin.getSiteId(), pageModel, null);
			}else{
				noticeList = noticeService.getNoticeListBySiteId(currentSiteAdmin.getSiteId(), pageModel, currentSiteAdmin.getId());
			}
			noticeList = ObjectUtil.parseHtmlWithList(noticeList, "title", "content");      //数据库的数据转为实际字符
		} catch (Exception e) {
			logger.error("获取全部站点公告信息列表出错!"+e);
		}
		int noticeSize = 0;
		if(noticeList != null){
			noticeSize = noticeList.size();
		}
		Integer[] createIds = null;
		if(noticeSize > 0){
			createIds = new Integer[noticeSize];
			int i = 0;
			for(Notice notice:noticeList){
				createIds[i] = notice.getCreateUser();
				i++;
			}
		}
		if(createIds != null){
			publishUserList = getNameListByid(createIds);
		}
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "stopTime";
		long offset = getSiteOffset(request);    //根据当前访问的站点标识获取站点所在时区
		noticeList = ObjectUtil.offsetDateWithList(noticeList, offset, fields);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("publishUserList", publishUserList);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/admin/noticeList.jsp");
	}
	
	/**
	 * 预览公告
	 * wangyong
	 * 2013-2-22
	 */
	@AsController(path = "view/{id:([0-9]+)}")
	@Get
	public Object viewNotice(@CParam("id") Integer id,HttpServletRequest request) throws Exception{
		logger.info("id=="+id);
		Notice notice = new Notice();
		if (id>0) {
			notice = noticeService.getNoticeById(id);
			notice = (Notice) ObjectUtil.parseChar(notice, "title", "content");	//字符转义	
		} else {
			notice.setTitle(request.getParameter("title"));
			notice.setContent(request.getParameter("content"));
		}
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "stopTime";
		long offset = getSiteOffset(request);    //根据当前访问的站点标识获取站点所在时区
		notice = (Notice) ObjectUtil.offsetDate(notice, offset, fields);
		request.setAttribute("notice", notice);
		return new ActionForward.Forward("/jsp/admin/viewNotice.jsp");
	}
	
	/**
	 * 站点管理员发布站点公告
	 * wangyong
	 * 2013-2-22
	 */
	@Post
	@AsController(path = "go")
	//@CParam("name") @Xss String name,CSRF
	public Object createNotice(@CParam("data") String data, HttpServletRequest request){
		Object[] noticeData = JsonUtil.parseObjectArrWithJsonString(data);
		Notice notice = (Notice) JsonUtil.parseObjectWithJsonString(noticeData[0].toString(), Notice.class);
		logger.info(notice);
		boolean creatFlag = false;
		if(notice != null){
			UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
			if(!StringUtil.isNotBlank(notice.getTitle()) || !StringUtil.isNotBlank(notice.getContent())){
				return ResourceHolder.getInstance().getResource("system.notice.contents.input");
			}else{
				try {
					notice = (Notice) ObjectUtil.parseHtml(notice, "title", "content");	//字符转义
					creatFlag = noticeService.publishNotice(currentSiteAdmin, notice);
					if(creatFlag){
						return returnJsonStr(ConstantUtil.CREATENOTICE_SUCCEED, ResourceHolder.getInstance().getResource("system.notice.list.Create.1"));
					}else{
						return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Create.2"));
					}
				} catch (Exception e) {
					logger.error("站点管理员发布站点公告出错!"+e);
					return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Create.2"));
				} finally {
					sysHelpAdminEventLog(creatFlag, null, currentSiteAdmin, 
							EventLogConstants.SYSTEM_HELP_NOTICE_CREATE, EventLogConstants.SITE_NOTICE_CREATE, "站点管理员发布站点公告", notice.getTitle(), request);
				}
			}
		}
		return creatFlag;
	}
	
	/**
	 * 站点管理员发布站点公告
	 * wangyong
	 * 2013-2-22
	 */
	@AsController(path = "create")
	@Get
	public Object publishNotice(){
		return new ActionForward.Forward("/jsp/admin/createNotice.jsp");
	}
	
	/**
	 * 站点管理员修改站点公告
	 * wangyong
	 * 2013-2-22
	 */
	@Post
	@AsController(path = "update")
	public Object updateNoticeInfo(@CParam("data") String data, HttpServletRequest request){
		Object[] noticeData = JsonUtil.parseObjectArrWithJsonString(data);
		Notice notice = (Notice) JsonUtil.parseObjectWithJsonString(noticeData[0].toString(), Notice.class);
		boolean updateFlag = false;
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		if(notice != null){
			int noticeId = notice.getId();
			String title = notice.getTitle();
			String content = notice.getContent();
			Integer loginedFlag = notice.getLoginedFlag(); 
			if(noticeId > 0 && StringUtil.isNotBlank(title) && StringUtil.isNotBlank(content)){
				updateFlag = noticeService.updateNotice(noticeId, title, content, loginedFlag);
			}
			sysHelpAdminEventLog(updateFlag, null, currentSiteAdmin, 
					EventLogConstants.SYSTEM_HELP_NOTICE_UPDATE, EventLogConstants.SITE_NOTICE_UPDATE, "站点管理员修改站点公告", notice, request);
			if(updateFlag){
				return returnJsonStr(ConstantUtil.CREATENOTICE_SUCCEED,  ResourceHolder.getInstance().getResource("system.notice.list.Update.1"));
			}else{
				return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Update.2"));
			}
		}
		return updateFlag;
	}
	
	/**
	 * 站点管理员修改站点公告
	 * wangyong
	 * 2013-2-22
	 */
	@AsController(path = "update/{id:([0-9]+)}")
	@Get
	public Object updateNotice(@CParam("id") Integer id,HttpServletRequest request){
		logger.info("id=="+id);
		Notice notice = noticeService.getNoticeById(id);
		notice = (Notice) ObjectUtil.parseChar(notice, "title", "content");	//字符转义
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "stopTime";
		long offset = getSiteOffset(request);   //根据当前访问的站点标识获取站点所在时区
		notice = (Notice) ObjectUtil.offsetDate(notice, offset, fields);
		request.setAttribute("notice", notice);
		return new ActionForward.Forward("/jsp/admin/createNotice.jsp");
	}
	
	/**
	 * 站点管理员删除站点公告
	 * wangyong
	 * 2013-2-22
	 */
	@AsController(path = "delete/{id:([0-9]+)}")
	public Object deleteNotice(@CParam("id") Integer id, HttpServletRequest request){
		logger.info("id=="+id);
		UserBase currentSiteAdmin = userService.getCurrentSiteAdmin(request);
		boolean delFlag = noticeService.deleteNoticeById(id, currentSiteAdmin);
		int delStatus = ConstantUtil.DELSITE_SUCCEED;
		if(delFlag){
			//传入参数需修改
			setInfoMessage(request, ResourceHolder.getInstance().getResource("system.notice.delete." + delStatus));
		}else{
			delStatus = ConstantUtil.DELSITE_FAIL;
			//传入参数需修改
			setErrMessage(request, ResourceHolder.getInstance().getResource("system.notice.delete." + delStatus));
		}
		Notice retNotice = null;
		try {
			retNotice = DAOProxy.getLibernate().getEntity(Notice.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sysHelpAdminEventLog(delFlag, null, currentSiteAdmin, 
				EventLogConstants.SYSTEM_HELP_NOTICE_DELETE, EventLogConstants.SITE_NOTICE_DELETE, "站点管理员删除站点公告", retNotice, request);
		return new ActionForward.Forward("/admin/notice/list");
	}
	
	/**
	 * 只是简单的预览公告 不存入数据库
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@AsController(path = "preview")
	@Get
	public Object previewNotice(HttpServletRequest request) throws Exception{
		return new ActionForward.Forward("/jsp/admin/viewNotice.jsp");
	}	
	
	/**
	 * 根据当前访问的站点标识获取站点所在时区
	 * wangyong
	 * 2013-2-22
	 */
	private long getSiteOffset(HttpServletRequest request){
		SiteBase currentSite = siteService.getCurrentSiteBaseByAdminLogin(request);
		long offset = 0 ;
		if(currentSite != null){
			offset = currentSite.getTimeZone();
		}else{
			offset = DateUtil.getDateOffset();
		}
		logger.info("当前访问的站点时区" + offset);
		return offset;
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
	 * 用户表中通过id获取trueName
	 * wangyong
	 * 2013-2-26
	 */
	private List<String> getNameListByid(Integer[] userIds){
		List<String> nameList = new ArrayList<String>();
		if(userIds != null){
			List<UserBase> userList = userService.getUserListByUserIdArray(userIds);
			if(userList != null && userList.size() > 0){
				Map<Integer, String> userMap = new HashMap<Integer, String>();
				for(UserBase user:userList){
					userMap.put(user.getId(), user.getTrueName());
				}
				for(Integer userId:userIds){
					String name = userMap.get(userId);
					if(StringUtil.isNotBlank(name)){
						nameList.add(name);
					}else{
						nameList.add("--");
					}
				}
			}else{
				int userIdSize = 0;
				if(userIds != null){
					userIdSize = userIds.length;
				}
				for(int i=0; i<userIdSize; i++){
					nameList.add("--");
				}
			}
		}
		return nameList;
	}
	
}
