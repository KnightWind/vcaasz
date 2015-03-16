package com.bizconf.vcaasz.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.NoticeService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;

/**
 * 企业用户公告
 * 
 * @author alan
 * @date 2013.3.6
 */

@ReqPath("notice")
public class NoticeController extends BaseController {
	private final Logger logger = Logger.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;
	@Autowired
	UserService userService;
	@Autowired
	SiteService siteService;
	@Autowired
	LoginService loginService;
	
	/**
	 * 获取公告信息
	 * 1.站点用户可以查看创建自己的企业管理员发布的公告
	 * 2.站点用户可以查看超级企业管理员发布的公告
	 * @param pageModel
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@AsController(path="list")
	public Object showList(PageModel pageModel, HttpServletRequest request){
		List<Notice> noticeList = null;
		List<String> publishUserList = null;
		Integer rows = 0;
		//2013.8.8用户未登录时，查看公告
		if (!loginService.isLogined(request)) {
			SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
			String pageNo = request.getParameter("pageNo");
			PageBean<Notice> noticePage = noticeService.getNotLoginedeNoticeList(currentSite.getId(), StringUtil.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo));
			pageModel.setRowsCount(noticePage.getRowsCount());
			pageModel.setPageSize(noticePage.getPageSize());
			noticeList = noticePage.getDatas();
		}else{
			UserBase currentLoginUser = userService.getCurrentUser(request);
			UserBase superAdmin = userService.getSiteSupperMasterBySiteId(currentLoginUser.getSiteId());//.getCurrentSiteAdmin(request);
			if (currentLoginUser!=null) {
				rows = noticeService.countNoticeByUserIds(currentLoginUser.getSiteId(), new Integer[]{currentLoginUser.getCreateUser(), superAdmin.getId()});	
			}
			pageModel.setRowsCount(rows);
			pageModel.setPageSize(currentLoginUser.getPageSize());     //// 2013.6.24 因客户需求新加常量，部分每页展示用户偏好设置每页显示条数
			noticeList = noticeService.getUserNoticeList(currentLoginUser.getSiteId(), pageModel, new Integer[]{currentLoginUser.getCreateUser(), superAdmin.getId()});
		}
		
		Integer[] createIds = null;
		if(noticeList != null){
			if(noticeList.size() > 0){
				createIds = new Integer[noticeList.size()];
				for (int i = 0; i < noticeList.size(); i++) {
					createIds[i] = noticeList.get(i).getCreateUser();
				}
			}
			if (createIds!=null) {
				publishUserList = getNameListByid(createIds);
			}
			String[] fields = new String[]{
					"startTime",
					"stopTime"
			};
			noticeList = ObjectUtil.parseCharWithList(noticeList, "title", "content");      //数据库的数据转为实际字符
			long offset = getSiteOffset(request);    //根据当前访问的站点标识获取站点所在时区
			noticeList = ObjectUtil.offsetDateWithList(noticeList, offset, fields);
			request.setAttribute("publishUserList", publishUserList);
		}
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/user/notice_list.jsp");
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
	
	/**
	 * 弹出公告
	 * alan
	 * 2013-5-30
	 */
	@AsController(path = "popUpNotice/{id:([0-9]+)}")
	@Get
	public Object popUpNotice(@CParam("id") Integer id, HttpServletRequest request) throws Exception{
		Notice notice = new Notice();
		if (id>0) {
			notice = noticeService.getNoticeById(id);
			notice = (Notice) ObjectUtil.parseChar(notice, "title", "content");	//字符转义	
		} else {
			notice.setTitle(request.getParameter("title"));
			notice.setContent(request.getParameter("content"));
		}

		request.setAttribute("notice", notice);
		return new ActionForward.Forward("/jsp/user/viewNotice.jsp");
	}
	
	/**
	 * 根据当前访问的站点标识获取站点所在时区
	 * wangyong
	 * 2013-2-22
	 */
	private long getSiteOffset(HttpServletRequest request){
		SiteBase currentSite = siteService.getSiteBaseBySiteSign(SiteIdentifyUtil.getCurrentBrand());
//		SiteBase currentSite = siteService.getCurrentSiteBaseByUserLogin(request);
		long offset = 0 ;
		if(currentSite != null){
			offset = currentSite.getTimeZone();
		}else{
			offset = DateUtil.getDateOffset();
		}
		logger.info("当前访问的站点时区" + offset);
		return offset;
	}
}
