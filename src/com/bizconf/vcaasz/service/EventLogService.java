package com.bizconf.vcaasz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bizconf.vcaasz.entity.BizEventObject;
import com.bizconf.vcaasz.entity.EventLog;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;

public interface EventLogService {
	
	/**
	 * 系统管理员操作日志
	 * @param sysUser
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	public boolean saveSystemEventLog(SystemUser sysUser,Integer funcModule,
			String optDesc, Integer logStatus, Object object,
			HttpServletRequest request);
	
	
	/**
	 * 站点管理员操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
 
	public boolean saveAdminEventLog(UserBase userBase,Integer funcModule,

			String optDesc, Integer logStatus, Object object,
			HttpServletRequest request);
	
	/**
	 * 站点管理员批量操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param optDetailInfo	操作对象详情描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param ids           操作对象id数组
	 * @param request       Request对象
	 * @return
	 */
	
	public boolean saveAdminEventLogBatch(UserBase userBase,Integer funcModule,
			
			String optDesc, Integer logStatus, String[] ids,
			HttpServletRequest request);
	

	
	/**
	 * 企业用户操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	public boolean saveUserEventLog(UserBase userBase, Integer funcModule,
			String optDesc, Integer logStatus, Object object,
			HttpServletRequest request);

//	/**
//	 * 企业用户操作会议方法
//	 * @param userBase
//	 * @param module		日志模块,ConstantUtil中的funcModule
//	 * @param optDesc		操作描述
//	 * @param logStatus		操作状态：成功或者是失败
//	 * @param optObject     操作对象
//	 * @param request       Request对象
//	 * @return
//	 */
//	public boolean saveConfEventLog(UserBase userBase,
//			Integer funcModule, String optDesc, Integer logStatus,
//			ConfBase confBase, HttpServletRequest request);
	
	/**
	 * 保存用户操作日志信息对象 
	 * @param userId        操作用户的ID号
	 * @param userType		用户类别：

	 * @return
	 */
	public boolean saveEventLog(Integer userId, Short userType, Short module,
			String optDesc, Short logStatus, String optObject,
			HttpServletRequest request);

	/**
	 * 保存操作日志对象
	 * 
	 * @param bizEventObject
	 * @return
	 */
	public boolean saveEventLog(BizEventObject bizEventObject);
	
	

	/**
	 * 获取系统管理员的事件日志
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSystemLogList(PageModel pageModel);

	/**
	 * 获取系统管理员的事件日志（超级系统管理员）
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSystemLogListByLogType(Integer logType,String userName,String sortField,String sortord,PageModel pageModel);
	
	/**
	 * 获取系统管理员的事件日志（普通系统管理员查询）
	 * 
	 * @param pageModel
	 * @param sysUserId  普通系统管理员id
	 * @return
	 */
	public List<EventLog> getSystemLogListByLogType(Integer logType, String sortField, String sortord, PageModel pageModel, int sysUserId);
	
	/**
	 * 统计系统管理员事件日志的条数（超级系统管理员）
	 * @param logType
	 * @return
	 */
	public Integer countSystemLogByLogType(Integer logType,String userName);
	
	/**
	 * 统计系统管理员事件日志的条数（普通系统管理员查询）
	 * @param logType
	 * @param sysUserId  普通系统管理员id
	 * @return
	 */
	public Integer countSystemLogByLogType(Integer logType,int sysUserId);
	
	/**
	 * 统计站点管理员操作日志的条数（超级系统管理员）
	 * @param  operator 操作员（模糊查询）
	 * wangyong
	 * 2013-2-25
	 */
	public Integer countSiteAdminLog(Integer logType, String operator);
	
	/**
	 * 统计站点管理员操作日志的条数（普通系统管理员）
	 * @param sysUserId  普通站点管理员id
	 * wangyong
	 * 2013-2-25
	 */
	public Integer countSiteAdminLog(Integer logType, int sysUserId);
	
	/**
	 * 获取站点管理员操作日志（超级系统管理员）
	 * @param  operator 操作员（模糊查询）
	 * wangyong
	 * 2013-2-25
	 */
	public List<EventLog> getSiteAdminLogList(Integer logType, String operator, String sortField, String sortord, PageModel pageModel);
	
	/**
	 * 获取站点管理员操作日志（普通系统管理员）
	 * @param sysUserId  普通系统管理员id
	 * wangyong
	 * 2013-2-25
	 */
	public List<EventLog> getSiteAdminLogList(Integer logType, String sortField, String sortord, PageModel pageModel, int sysUserId);
	
	/**
	 * 超级站点管理员根据站点ID号获取站点的LOG
	 * @param siteId
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSiteLogListBySiteId(Integer siteId, String operator, Integer logType, String sortField, String sortord, PageModel pageModel);
	
	/**
	 * 普通站点管理员根据站点ID号获取操作日志log
	 * @param siteId
	 * @param pageModel
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public List<EventLog> getSiteLogListBySiteId(Integer siteId, Integer logType, String sortField, String sortord, PageModel pageModel, int siteAdminId);

	/**
	 * 统计某站点下的超级站点管理员log数量
	 * @param siteId
	 * @param logType
	 * @return
	 */
	public Integer countSiteLogBySiteId(Integer siteId, String operator, Integer logType);
	
	/**
	 * 统计指定站点下的指定普通站点管理员的操作日志log数量
	 * @param siteId
	 * @param logType
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public Integer countSiteLogBySiteId(Integer siteId, Integer logType, int siteAdminId);
	
	/**
	 * 超级站点管理员统计某站点下的企业用户log数量
	 * @param siteId
	 * @param operator 操作员(模糊查询)
	 * @param logType
	 * @return
	 */
	public Integer countSiteUserLogBySiteId(Integer siteId, String operator, Integer logType);
	
	/**
	 * 普通站点管理员统计某站点下的企业用户的操作日志log数量
	 * @param siteId
	 * @param logType
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public Integer countSiteUserLogBySiteId(Integer siteId, Integer logType, int siteAdminId);

	
	/**
	 * 超级站点管理员根据站点ID号获取企业用户操作日志log
	 * @param siteId
	 * @param operator 操作员(模糊查询)
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSiteUserLogListBySiteId(Integer siteId, String operator, Integer logType, String sortField, String sortord, PageModel pageModel);
	
	/**
	 * 普通站点管理员根据站点ID号获取企业用户操作日志log
	 * @param siteId
	 * @param pageModel
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public List<EventLog> getSiteUserLogListBySiteId(Integer siteId, Integer logType, String sortField, String sortord, PageModel pageModel, int siteAdminId);
	
	/**
	 * 根据日志ID获取系统管理员操作日志
	 * wangyong
	 * 2013-8-21
	 */
	public EventLog getEventLogById(Integer logid);
}
