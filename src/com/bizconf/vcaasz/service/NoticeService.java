package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;

public interface NoticeService {

	/**
	 * 系统管理员发布系统公告
	 * 
	 * @param sysUser
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
	public boolean publishSysNotice(SystemUser sysUser,String title, String content, Date expireTime);
	
	
	/**
	 * 系统管理员修改公告信息
	 * 
	 * @param noticeId
	 * @param title
	 * @param content
	 * @param expireTime
	 * @param sysUser
	 * @return
	 */
	public boolean updateSysNotice(Integer noticeId, String title, String content, Date expireTime);

	
	/**
	 * 获取系统管理员发布的公告信息
	 * 
	 * @param pageModel
	 * @return
	 */
	public List<Notice> getSysNoticeList(PageModel pageModel);
	


	/**
	 * 系统管理员根据主键ID号删除公告

	 * @param id
	 * @param sysUser
	 * @return
	 */
	public boolean deleteSysNoticeById(Integer id,SystemUser sysUser);

	
	
	
	/**
	 * 统计系统管理员发布的信息数量
	 * @return
	 */
	public Integer countSysNotice();
	

	/**
	 * 获取正在发布的系统公告
	 * 
	 * @return
	 */
	public Notice getCurrentSystemNotice();
	
	
	
	
	
	
	/**
	 * 站点管理员发布公告信息
	 * 
	 * @param userAdmin
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
	public boolean publishNotice(UserBase userAdmin, Notice notice);
	
	

	/**
	 * 站点管理员修改公告信息
	 * @param noticeId
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
	public boolean updateNotice(Integer noticeId, String title, String content, Integer loginedFlag);


	/**
	 * 根据主键ID获取公告信息
	 * 
	 * @param id
	 * @return
	 */
	public Notice getNoticeById(Integer id);
	
	
	/**
	 * 站点管理员根据主键ID号删除公告
	 * @param id
	 * @param siteAdmin   站点管理员
	 * @return
	 */
	public boolean deleteNoticeById(Integer id, UserBase siteAdmin);

	
	
	
	/**
	 * 根据站点ID号获取站点发布的公告信息列表
	 * @param pageModel
	 * @param siteUserId  普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * @return
	 */
	public List<Notice> getNoticeListBySiteId(Integer siteId, PageModel pageModel, Integer siteUserId);
	
	
	/**
	 * 根据站点Id号统计发布的公告总数量
	 * @param siteId
	 * @param siteUserId  普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * @return
	 */
	public Integer countNoticeBySiteId(Integer siteId, Integer siteUserId);
	
	
	/**
	 * 根据站点标识获取站点发布的公告信息列表
	 * @param pageModel
	 * @return
	 */
	public List<Notice> getNoticeListBySiteSign(String siteSign,PageModel pageModel);
	
	
	/**
	 * 根据站点标识统计发布的公告总数量
	 * @param siteId
	 * @return
	 */
	public List<Notice> countNoticeBySiteSign(String siteSign);
	
	
	/**
	 * 企业用户获取公告总数
	 * 用户登录后
	 * @param siteId
	 * @param userIds
	 * @return
	 */
	public Integer countNoticeByUserIds(Integer siteId, Integer[] userIds);
	
	/**
	 * 企业用户获取公告(超级企业管理员和创建该用户的企业管理员发布的)
	 * 用户登录后
	 * @param siteId
	 * @param pageModel
	 * @param userIds
	 * @return
	 */
	public List<Notice> getUserNoticeList(Integer siteId, PageModel pageModel, Integer[] userIds);
	
	/**
	 * 企业用户获取公告(超级企业管理员和创建该用户的企业管理员发布的)
	 * 用户未登录
	 * @param siteId
	 * @param pageModel
	 * @param userIds
	 * @return
	 */
	public PageBean<Notice> getNotLoginedeNoticeList(Integer siteId, int pageNo);
	
	
//	public List<Notice> getSiteNoticeList(HttpServletRequest request);
	
}
