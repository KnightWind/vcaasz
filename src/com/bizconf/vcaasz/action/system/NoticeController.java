package com.bizconf.vcaasz.action.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.NoticeService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;

/**
 * 公告controller
 * @author wangyong
 * @date 2013.1.17
 */
@ReqPath("notice")
@Interceptors({SystemUserInterceptor.class})
public class NoticeController extends BaseController{
	private final  Logger logger = Logger.getLogger(SiteController.class);
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	
	/**
	 * 获取全部系统公告信息列表
	 * wangyong
	 * 2013-1-17
	 */
	@SuppressWarnings("unchecked")
	@AsController(path = "list")
	//@CParam("input.filename") LiberCFile file
	public Object showList(PageModel pageModel, LiberInvocation invocation, HttpServletRequest request){
		List<Notice> noticeList = null;
		Integer rows = 0;
		try {
			rows = noticeService.countSysNotice();
		} catch (Exception e) {
			logger.error("获取页数出错!"+e);
		}
		pageModel.setRowsCount(rows);
		try {
			noticeList = noticeService.getSysNoticeList(pageModel);
			noticeList = ObjectUtil.parseHtmlWithList(noticeList, "title");      //数据库的数据转为实际字符
		} catch (Exception e) {
			logger.error("获取全部系统公告信息列表出错!"+e);
		}
		String[] fields = new String[2];
		fields[0] =  "startTime";
		fields[1] = "stopTime";
		long offset = DateUtil.getDateOffset();    //获取所在时区的Offset
		noticeList = ObjectUtil.offsetDateWithList(noticeList, offset, fields);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/system/noticeList.jsp");
	}
	
	/**
	 * 预览公告
	 * wangyong
	 * 2013-2-18
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
		long offset = DateUtil.getDateOffset();    //获取所在时区的Offset
		notice = (Notice) ObjectUtil.offsetDate(notice, offset, fields);
		request.setAttribute("notice", notice);
		return new ActionForward.Forward("/jsp/system/viewNotice.jsp");
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
		return new ActionForward.Forward("/jsp/system/viewNotice.jsp");
	}	
	/**
	 * 系统管理员发布系统公告
	 * wangyong
	 * 2013-1-17
	 */
	@Post
	@AsController(path = "go")
	//@CParam("name") @Xss String name,CSRF
	public Object createNotice(@CParam("data") String data, HttpServletRequest request){
		Object[] noticeData = JsonUtil.parseObjectArrWithJsonString(data);
		Notice notice = (Notice) JsonUtil.parseObjectWithJsonString(noticeData[0].toString(), Notice.class);
		boolean creatFlag = false;
		if(notice != null){
			SystemUser systemUser = userService.getCurrentSysAdmin(request);
			if(!StringUtil.isNotBlank(notice.getTitle()) || !StringUtil.isNotBlank(notice.getContent()) || notice.getStopTime() == null){
				return ResourceHolder.getInstance().getResource("system.notice.contents.input");
			}else{
				try {
					if(DateUtil.getGmtDate(notice.getStopTime()).compareTo(DateUtil.getGmtDate(null)) <= 0){
						
						return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.date.errorTime"));
					}
					notice = (Notice) ObjectUtil.parseHtml(notice, "title", "content");	//字符转义
					creatFlag = noticeService.publishSysNotice(systemUser, notice.getTitle(), notice.getContent(), notice.getStopTime());
					saveSystemEventLog(creatFlag, systemUser, EventLogConstants.SYSTEM_NOTICE_CREATE, "系统管理员发布系统公告", notice, request);
					if(creatFlag){
						return returnJsonStr(ConstantUtil.CREATENOTICE_SUCCEED, ResourceHolder.getInstance().getResource("system.notice.list.Create.1"));
					}else{
						return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Create.2"));
					}
				} catch (Exception e) {
					saveSystemEventLog(false, systemUser, EventLogConstants.SYSTEM_NOTICE_CREATE, "系统管理员发布系统公告", notice, request);
					logger.error("系统管理员发布系统公告出错!"+e);
					return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Create.2"));
				}
			}
		}
		return creatFlag;
	}
	
	/**
	 * 返回json字符串对象(创建，修改公告出错时)
	 * status 失败
	 * object 失败原因
	 * wangyong
	 * 2013-1-30
	 */
	private String returnJsonStr(int status, Object object){
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("message", object.toString());
		return json.toString();
	}
	
	/**
	 * 系统管理员发布系统公告
	 * wangyong
	 * 2013-1-17
	 */
	@AsController(path = "create")
	@Get
	public Object publishNotice(){
		return new ActionForward.Forward("/jsp/system/createNotice.jsp");
	}
	
	/**
	 * 系统管理员修改系统公告
	 * wangyong
	 * 2013-1-17
	 */
	@Post
	@AsController(path = "update")
	public Object updateNoticeInfo(@CParam("data") String data, HttpServletRequest request){
		Object[] noticeData = JsonUtil.parseObjectArrWithJsonString(data);
		Notice notice = (Notice) JsonUtil.parseObjectWithJsonString(noticeData[0].toString(), Notice.class);
		boolean updateFlag = false;
		SystemUser systemUser = userService.getCurrentSysAdmin(request);
		if(notice != null){
			int noticeId = notice.getId();
			String title = notice.getTitle();
			String content = notice.getContent();
			Date expireTime = notice.getStopTime();
			if(DateUtil.getGmtDate(notice.getStopTime()).compareTo(DateUtil.getGmtDate(null)) <= 0){
				return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.date.errorTime"));
			}
			if(noticeId > 0 && StringUtil.isNotBlank(title) && StringUtil.isNotBlank(content) && expireTime != null){
				updateFlag = noticeService.updateSysNotice(noticeId, title, content, expireTime);
			}
			saveSystemEventLog(updateFlag, systemUser, EventLogConstants.SYSTEM_NOTICE_UPDATE, "系统管理员修改系统公告", notice, request);
			if(updateFlag){
				return returnJsonStr(ConstantUtil.CREATENOTICE_SUCCEED, ResourceHolder.getInstance().getResource("system.notice.list.Update.1"));
			}else{
				return returnJsonStr(ConstantUtil.CREATENOTICE_FAIL, ResourceHolder.getInstance().getResource("system.notice.list.Update.2"));
			}
		}
		return updateFlag;
	}
	
	/**
	 * 系统管理员修改系统公告
	 * wangyong
	 * 2013-1-17
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
		long offset = DateUtil.getDateOffset();    //获取所在时区的Offset
		notice = (Notice) ObjectUtil.offsetDate(notice, offset, fields);
		request.setAttribute("notice", notice);
		return new ActionForward.Forward("/jsp/system/createNotice.jsp");
	}
	
	/**
	 * 系统管理员删除系统公告
	 * wangyong
	 * 2013-1-17
	 */
	@AsController(path = "delete/{id:([0-9]+)}")
	public Object deleteNotice(@CParam("id") Integer id, HttpServletRequest request){
		logger.info("id=="+id);
		SystemUser systemUser = userService.getCurrentSysAdmin(request);
		boolean delFlag = noticeService.deleteSysNoticeById(id, systemUser);
		int delStatus = ConstantUtil.DELSITE_SUCCEED;
		saveSystemEventLog(delFlag, systemUser, EventLogConstants.SYSTEM_NOTICE_DELETE, "系统管理员删除系统公告", null, request);
		if(delFlag){
			setInfoMessage(request, ResourceHolder.getInstance().getResource("system.notice.delete." + delStatus));
		}else{
			delStatus = ConstantUtil.DELSITE_FAIL;
			setErrMessage(request, ResourceHolder.getInstance().getResource("system.notice.delete." + delStatus));
		}
		return new ActionForward.Forward("/system/notice/list");
	}
	
}
