package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.constant.ZoomConfStatus;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.Condition;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.LoginService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.CookieUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	LoginService loginService;
	
	@Autowired
	ConfLogic confLogic;

	@Autowired
	ZoomUserOperationComponent userOperationComponent;

	@Autowired
	ZoomMeetingOperationComponent meetingOperationComponent;
	
	@Autowired
	DataCenterService centerService;

	@Override
	public boolean deleteUserBaseByIds(Integer... ids) {
		return false;
	}

	@Override
	public UserBase getCurrentSiteAdmin(HttpServletRequest request) {
		if (!loginService.isSiteAdminLogined(request)) {
			return null;
		}
		String domain = SiteIdentifyUtil.getCurrentBrand() + "."
				+ SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		// String suid = CookieUtil.getCookie(request,
		// LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME);
		String suid = CookieUtil.getCookieByDomain(request,
				LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME, domain);
		int userId = Integer.parseInt(suid);
		try {
			UserBase user = libernate.getEntity(UserBase.class, userId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SystemUser getCurrentSysAdmin(HttpServletRequest request) {
		if (!loginService.isSysAdminLogined(request)) {
			return null;
		}

		String domain = SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		// String said = CookieUtil.getCookie(request,
		// LoginConstants.SYSTEM_USER_SESSION_ID_NAME);
		String said = CookieUtil.getCookieByDomain(request,
				LoginConstants.SYSTEM_USER_SESSION_ID_NAME, domain);
		int userId = Integer.parseInt(said);
		try {
			// request.getServletContext().getRealPath("");
			SystemUser user = libernate.getEntity(SystemUser.class, userId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SystemUser getSystemUserById(Integer sysUserId) {
		SystemUser sysUser = null;
		if (sysUserId != null && sysUserId.intValue() > 0) {
			try {
				sysUser = libernate.getEntity(SystemUser.class, sysUserId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sysUser;
	}

	@Override
	public List<SystemUser> getSysUserListByIds(Integer[] ids) {
		List<SystemUser> list = null;
		if (ids != null && ids.length > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			List<Object> valueList = new ArrayList<Object>();
			sqlBuffer.append("SELECT * FROM  t_system_user where id > 0 ");
			sqlBuffer.append(" and ( ");
			int ii = 0;
			for (Integer id : ids) {
				if (id != null && id.intValue() > 0) {
					if (ii > 0) {
						sqlBuffer.append("  or ");
					}
					sqlBuffer.append("  id = ?");
					valueList.add(id);
					ii++;
				}
			}
			sqlBuffer.append(" )");
			try {
				logger.info("sqlBuffer--->>" + sqlBuffer.toString());
				list = libernate.getEntityListBase(SystemUser.class,
						sqlBuffer.toString(), valueList.toArray());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				sqlBuffer = null;
				valueList = null;
			}
		}

		return list;

	}

	@Override
	public UserBase getSiteSupperMasterBySiteId(Integer siteId) {
		UserBase userBase = null;
		if (siteId != null && siteId.intValue() > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and user_type = ?");
			sqlBuffer.append(" and site_id = ? ");
			Object[] values = new Object[3];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = ConstantUtil.USERTYPE_ADMIN_SUPPER;
			values[2] = siteId;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	@Override
	public List<UserBase> getSiteSupperMasterBySiteIdArray(Integer[] siteIds) {
		List<UserBase> userList = null;
		if (siteIds != null && siteIds.length > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			Object[] values = new Object[siteIds.length + 2];
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and user_type = ?");
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = ConstantUtil.USERTYPE_ADMIN_SUPPER;
			int ii = 2;
			sqlBuffer.append("  and (");
			for (Integer siteId : siteIds) {
				if (siteId != null && siteId.intValue() > 0) {
					if (ii > 2) {
						sqlBuffer.append(" or ");
					}
					sqlBuffer.append("  site_id = ? ");
					values[ii] = siteId;
					ii++;
				}

			}
			sqlBuffer.append("  )");
			try {
				userList = libernate.getEntityListBase(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}

		return userList;
	}

	public List<UserBase> getUserListByUserIdArray(Integer[] userIds) {
		List<UserBase> userList = null;
		if (userIds != null && userIds.length > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			Object[] values = new Object[userIds.length];
			// Object[] values=new Object[3+userIds.length];
			sqlBuffer.append("select * from t_user_base where id >0 ");
			// sqlBuffer.append(" and del_flag = ?");
			// sqlBuffer.append(" and (");
			// sqlBuffer.append(" user_type =?");
			// sqlBuffer.append(" or  user_type =?");
			// sqlBuffer.append(")");
			// values[0]=ConstantUtil.DELFLAG_UNDELETE;
			// values[0]=ConstantUtil.USERTYPE_ADMIN;
			// values[1]=ConstantUtil.USERTYPE_ADMIN_SUPPER;
			// int ii=3;
			// sqlBuffer.append(" and ( ");
			// for(Integer userId:userIds){
			// if(ii>3){
			// sqlBuffer.append(" or");
			// }
			// sqlBuffer.append(" id = ?");
			// values[ii]=userId;
			// ii++;
			// }
			int ii = 0;
			sqlBuffer.append(" and ( ");
			for (Integer userId : userIds) {
				if (ii > 0) {
					sqlBuffer.append(" or");
				}
				sqlBuffer.append(" id = ?");
				values[ii] = userId;
				ii++;
			}
			sqlBuffer.append(" ) ");
			logger.info("---->>>SqlBuffer>" + sqlBuffer.toString());
			try {
				userList = libernate.getEntityListBase(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	@Override
	public UserBase getCurrentUser(HttpServletRequest request) {

		if (!loginService.isLogined(request)) {
			return null;
		}
		String domain = SiteIdentifyUtil.getCurrentBrand() + "."
				+ SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		// String suid = CookieUtil.getCookie(request,
		// LoginConstants.SITE_ADMIN_USER_SESSION_ID_NAME);
		String uid = CookieUtil.getCookieByDomain(request,
				LoginConstants.USER_SESSION_ID_NAME, domain);
		int userId = Integer.parseInt(uid);
		try {
			UserBase user = libernate.getEntity(UserBase.class, userId);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBase getUserBaseById(Integer userId) {
		try {
			if (userId != null && userId.intValue() > 0) {
				return libernate.getEntity(UserBase.class, userId);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteUserBaseById(Integer id) {
		return false;
	}

	@Override
	public List<UserBase> getUserListByNameOrEmail(String nameOrEmail,
			String sortField, String sortord, PageModel pageModel) {
		return null;
	}

	@Override
	public List<UserBase> getUserListByNameOrEmail(String nameOrEmail) {
		return null;
	}

	@Override
	public boolean importUserByExcelFileName(String excelFileName) {
		return false;
	}

	@Override
	public boolean lockUserBaseById(Integer id) {
		boolean lock = false;
		UserBase user = getUserBaseById(id);
		
		if(!lockUserMeeting(user)){
			return false;
		}
		
		if(id!=null && id.intValue() > 0){
			String sql = "update t_user_base set user_status = ? where id = ?";
			Object[] values = new Object[2];
			values[0] = ConstantUtil.USER_STATU_LOCK;
			values[1] = id;
			try{
				DAOProxy.getLibernate().executeSql(sql, values);
				 
			}catch (Exception e){
				e.printStackTrace();
				
				return false;
			}
		}
		
		if(user!=null){
			//Add by Darren 2014-12-24 
			DataCenter dataCenter = centerService.queryDataCenterById(user.getDataCenterId());
			userOperationComponent.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId(), StringUtil.genRandomPWD());
		}
		return true;
	}

	@Override
	public boolean lockUserBaseByIds(Integer... ids) {
		return false;
	}

	@Override
	public boolean saveUserBase(UserBase userBase) {
		try {
			if (userBase != null) {
				if (userBase.getId() != null && userBase.getId().intValue() > 0) {
					String[] fields = ObjectUtil.getFieldFromObject(userBase);
					libernate.updateEntity(userBase, fields);
					fields = null;
				} else {
					libernate.saveEntity(userBase);
				}
			}
			// libernate.saveEntity(userBase);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean unlockUserBaseById(Integer id) {
		boolean lock = false;
		UserBase user = getUserBaseById(id);
		if(!unLockUserMeeting(user)){
			return false;
		}
		if(id!=null && id.intValue() > 0){
			String sql = "update t_user_base set error_count = 0, user_status = ? where id = ?";
			Object[] values = new Object[2];
			values[0] = ConstantUtil.USER_STATU_UNLOCK;
			values[1] = id;
			try{
				DAOProxy.getLibernate().executeSql(sql, values);
				lock = true;
			}catch (Exception e){
				e.printStackTrace();
				lock = false;
			}
		}
		if(user!=null){
			//Add by Darren 2014-12-24 
			DataCenter dataCenter = centerService.queryDataCenterById(user.getDataCenterId());
			userOperationComponent.modifyPassword(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId(), user.getLoginPassPlain());
		}
		return lock;
	}

	@Override
	public boolean unlockUserBaseByIds(Integer... ids) {
		return false;
	}

	/**
	 * 根据登录名获取指定站点下的站点管理员 wangyong 2013-1-31
	 */
	@Override
	public UserBase getSiteAdminByLoginName(Integer siteId, String loginName) {
		UserBase userBase = null;
		if (siteId != null && siteId.intValue() > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and login_name = ?");
			sqlBuffer.append(" and site_id = ? ");
			sqlBuffer.append(" and (user_type = ? or user_type = ?)");
			Object[] values = new Object[] { ConstantUtil.DELFLAG_UNDELETE,
					loginName, siteId, ConstantUtil.USERTYPE_ADMIN,
					ConstantUtil.USERTYPE_ADMIN_SUPPER };
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据登录名获取指定站点下的站点管理员出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	/**
	 * 根据登录名获取指定站点下的用户 wangyong 2013-1-31
	 */
	@Override
	public UserBase getSiteUserByLoginName(Integer siteId, String loginName) {
		UserBase userBase = null;
		if (siteId != null && siteId.intValue() > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			// sqlBuffer.append(" and (login_name = ? or user_email = ?)");
			// sqlBuffer.append(" and login_name = ?");
			sqlBuffer.append(" and user_email = ?");// 修改成邮箱登陆
			sqlBuffer.append(" and site_id = ? ");
			sqlBuffer.append(" and user_type = ?");
			Object[] values = new Object[4];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = loginName;
			values[2] = siteId;
			values[3] = ConstantUtil.USERTYPE_USERS;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据登录名获取指定站点下的用户出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	/**
	 * 通过Email或者登录名获取该站点下的用户
	 */
	public UserBase getSiteUserByLoginNameOrEmail(Integer siteId,
			String emailOrName) {

		UserBase userBase = null;
		if (siteId != null && siteId.intValue() > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and (login_name = ? or user_email = ?)");
			sqlBuffer.append(" and site_id = ? ");
			sqlBuffer.append(" and user_type = ?");
			Object[] values = new Object[5];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = emailOrName;
			values[2] = emailOrName;
			values[3] = siteId;
			values[4] = ConstantUtil.USERTYPE_USERS;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据登录名获取指定站点下的用户出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	/**
	 * 根据邮箱名获取指定站点下的用户 wangyong 2013-1-31
	 */
	@Override
	public UserBase getSiteUserByEmail(Integer siteId, String email) {
		UserBase userBase = null;
		if (StringUtil.isNotBlank(email)) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and user_type = ?");
//			sqlBuffer.append(" and user_email = ?");
			sqlBuffer.append(" and (user_email = ? or pmi_id = ?)");
			sqlBuffer.append(" and site_id = ? ");
			Object[] values = new Object[5];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = ConstantUtil.USERTYPE_USERS;
			values[2] = email.trim();
			values[3] = email.trim();
			values[4] = siteId;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据邮箱名获取指定站点下的用户出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	/**
	 * 根据邮箱获取站点下的管理员或超级管理员信息 alan 2013-3-7
	 */
	@Override
	public UserBase getSiteAdminByEmail(Integer siteId, String email) {
		UserBase userBase = null;
		if (StringUtil.isNotBlank(email)) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and (user_type = ? or user_type = ?)");
			sqlBuffer.append(" and user_email = ?");
			sqlBuffer.append(" and site_id = ? ");
			Object[] values = new Object[5];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = ConstantUtil.USERTYPE_ADMIN;
			values[2] = ConstantUtil.USERTYPE_ADMIN_SUPPER;
			values[3] = email.trim();
			values[4] = siteId;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据邮箱名获取指定站点下的用户出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	/**
	 * 后台校验是否可以保存
	 * 
	 * @param user
	 * @return
	 */
	public boolean siteUserSaveable(UserBase user) {
		if (user == null) {
			return false;
		}
		// 修改
		UserBase libUser = null;
		if (user.getId() != null && user.getId() > 0) {
			// 判断用户名
			// 判断邮箱
			libUser = getSiteUserByEmail(user.getSiteId(), user.getUserEmail());
			if (null != libUser && !libUser.getId().equals(user.getId())
					&& libUser.getUserType().equals(user.getUserType())) {
				return false;
			}
			// 创建
		} else {
			libUser = getSiteUserByEmail(user.getSiteId(), user.getUserEmail());
			if (null != libUser
					&& libUser.getUserType().equals(user.getUserType())) {
				return false;
			}
		}
		return true;
	}

	public boolean updateUserZoomToken(UserBase user) {
		//Add by Darren 2014-12-24 
		DataCenter dataCenter = centerService.queryDataCenterById(user.getDataCenterId());
		String token = userOperationComponent.getZoomToken(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId());

		String sql = "update t_user_base set zoom_token = ? where id = ?";
		try {
			if (!StringUtil.isEmpty(token)) {

				return libernate.executeSql(sql,
						new Object[] { token, user.getId() });
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 更新用户的PMI ID
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUserPMI(UserBase user) {
		//Add by Darren 2014-12-24 
		DataCenter dataCenter = centerService.queryDataCenterById(user.getDataCenterId());
		String pmi = userOperationComponent.getZoomPMI(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId());
		
		//说明目前还没有pmi会议室
		if("0".equals(pmi)) return false;
		try {
			
			String sql = "update t_user_base set pmi_id = ? where id = ?";
			
			//查询用户中有没有相同的个人永久会议室会议号
			String sqlupdateduplicat = "select * from t_user_base where pmi_id = ? and id != ? ";
			
			//更新个人会议室 zoomID sql
			String sqlupdatepmi = "update t_conf_base set conf_zoom_id = ? where conf_zoom_id = ? and compere_user = ?";
					
			//如果存在重复的PMI会议号，则需要更改
			UserBase duplicatUser = libernate.getEntityCustomized(UserBase.class, sqlupdateduplicat, new Object[]{pmi,user.getId()});
			if(duplicatUser!=null){
				//Add by Darren 2014-12-24 
				DataCenter center = centerService.queryDataCenterById(duplicatUser.getDataCenterId());
				String duplicatPmi = userOperationComponent.getZoomPMI(center.getApiKey(),center.getApiToken(),duplicatUser.getZoomId());
				//if (!StringUtil.isEmpty(duplicatPmi)) {
					
					//更新user base  中pmi   
					 libernate.executeSql(sql,
							new Object[] { duplicatPmi, duplicatUser.getId() });
				
					 // 更新conf base 中的PMI zoom id
					 libernate.executeSql(sqlupdatepmi, 
							 new Object[] { duplicatPmi, pmi, duplicatUser.getId() });
				//}
			}
			
			if (!StringUtil.isEmpty(pmi)) {

				return libernate.executeSql(sql,
						new Object[] { pmi, user.getId() });
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 统计系统管理员总记录数 wangyong 2013-2-5
	 */
	@Override
	public int CountSystemUser(Condition condition) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" select count(1) from t_system_user where del_flag = ? and (sys_type = ? or sys_type = ?) ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConstantUtil.USERTYPE_SYSTEM);
		valueList.add(ConstantUtil.USERTYPE_SYS_SERVER);
		if (condition != null) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(
					strSql.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("统计系统管理员总记录数出错" + e);
		}
		return rows;
	}

	/**
	 * 根据条件获取系统管理员列表 wangyong 2013-2-5
	 */
	@Override
	public List<SystemUser> getSystemUserList(Condition condition,
			PageModel pageModel) {
		List<SystemUser> systemUserList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" select * from t_system_user where del_flag = ? and (sys_type = ? or sys_type = ?)  ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConstantUtil.USERTYPE_SYSTEM);
		valueList.add(ConstantUtil.USERTYPE_SYS_SERVER);
		if (condition != null) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		strSql.append(" order by id DESC "); // 查出列表无排序条件则为默认逆序
		if (pageModel != null) {
			int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
					* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		try {
			systemUserList = DAOProxy.getLibernate().getEntityListBase(
					SystemUser.class, strSql.toString(), valueList.toArray());
		} catch (Exception e) {
			logger.error("根据条件获取系统管理员列表出错" + e);
		}
		return systemUserList;
	}

	/**
	 * 新建系统管理员 wangyong 2013-2-5
	 */
	@Override
	public SystemUser createSystemUser(SystemUser systemUser) {
		SystemUser sysUser = null;
		if (systemUser != null) {
			// if(!userLogic.createSysUserValidate(systemUser)){
			// return null;
			// }
			try {
				sysUser = DAOProxy.getLibernate().saveEntity(systemUser); // 首先保存站点的基本信息
			} catch (Exception e) {
				logger.error("创建系统管理员失败", e);
				sysUser = null;
			}
		}
		return sysUser;
	}

	/**
	 * 删除系统管理员 wangyong 2013-2-5
	 */
	@Override
	public boolean delSystemUser(int id, SystemUser currentSystemUser) {
		boolean updateFlag = false;
		if (id > 0 && currentSystemUser != null) {
			String updateSql = "update t_system_user set del_flag = ?,del_time = ?,del_user = ? where id = ? ";
			Object[] values = new Object[4];
			values[0] = ConstantUtil.DELFLAG_DELETED;
			values[1] = DateUtil.getDateStrCompact(DateUtil.getGmtDate(null),
					"yyyy-MM-dd HH:mm:ss");
			values[2] = currentSystemUser.getId();
			values[3] = id;
			try {
				updateFlag = DAOProxy.getLibernate().executeSql(updateSql,
						values);
			} catch (Exception e) {
				logger.error("删除系统管理员出错" + e);
			}
		}
		return updateFlag;
	}

	/**
	 * 修改系统管理员 wangyong 2013-2-5
	 */
	@Override
	public SystemUser updateSystemUser(SystemUser systemUser) {
		SystemUser sysUser = null;
		if (systemUser != null) {
			// if(!userLogic.updateSysUserValidate(systemUser)){
			// return null;
			// }
			try {
				if (StringUtil.isNotBlank(systemUser.getLoginPass())) {
					sysUser = DAOProxy.getLibernate().updateEntity(systemUser,
							"id", "loginName", "loginPass", "trueName",
							"enName", "email", "mobile", "remark",
							"pass_editor");
				} else {
					sysUser = DAOProxy.getLibernate().updateEntity(systemUser,
							"id", "loginName", "trueName", "enName", "email",
							"mobile", "remark");
				}
				if (sysUser != null && sysUser.getId().intValue() > 0) {
					logger.info("修改系统管理员成功");
				} else {
					logger.error("修改系统管理员失败");
				}
			} catch (Exception e) {
				sysUser = null;
				logger.error("修改系统管理员失败", e);
			}
		}
		return sysUser;
	}

	public boolean updatePassWord(SystemUser systemUser) {
		if (systemUser == null) {
			return false;
		}
		boolean saveStatus = false;
		if (StringUtil.isNotBlank(systemUser.getLoginPass())) {
			try {
				systemUser = DAOProxy.getLibernate().updateEntity(systemUser,
						"id", "loginPass");
				saveStatus = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;

	}

	@Override
	public boolean updateUserPassWord(UserBase userBase,String pwd) {
		if (userBase == null) {
			return false;
		}
		boolean saveStatus = false;
		if (StringUtil.isNotBlank(userBase.getLoginPass())) {
			try {
				userBase.setErrorCount(0);
				userBase.setUserStatus(ConstantUtil.USER_STATU_UNLOCK);
				userBase.setLoginPassPlain(pwd);		
				userBase = DAOProxy.getLibernate().updateEntity(userBase, "id",
						"loginPass", "errorCount", "userStatus","loginPassPlain");
				
				if(userBase==null){
					return false;
				}
				if(userBase.getUserType()!=null && ConstantUtil.USERTYPE_USERS.equals(userBase.getUserType())){
					
					//Add by Darren 2014-12-24 
					DataCenter center = centerService.queryDataCenterById(userBase.getDataCenterId());
					//修改完zoom的密码
					if(userOperationComponent.modifyPassword(center.getApiKey(),center.getApiToken(),userBase.getZoomId(), pwd)){
						saveStatus = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;

	}

	/**
	 * 根据系统管理员登录名获取系统管理员信息 wangyong 2013-2-5
	 */
	public SystemUser getSystemUserByLoginName(String loginName) {
		SystemUser sysUser = null;
		if (StringUtil.isNotBlank(loginName)) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_system_user where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and login_name = ?");
			Object[] values = new Object[3];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = loginName;
			try {
				sysUser = libernate.getEntityCustomized(SystemUser.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据登录名获取系统管理员出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return sysUser;
	}

	/**
	 * 根据系统管理员邮箱获取系统管理员信息 wangyong 2013-2-5
	 */
	@Override
	public SystemUser getSystemUserByEmail(String userEmail) {
		SystemUser sysUser = null;
		if (StringUtil.isNotBlank(userEmail)) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_system_user where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and email = ?");
			Object[] values = new Object[3];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = userEmail.trim();
			try {
				sysUser = libernate.getEntityCustomized(SystemUser.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据邮箱名获取系统管理员出错", e);
			} finally {
				sqlBuffer = null;
				values = null;
			}
		}
		return sysUser;
	}

	@Override
	public UserBase getUserBaseByZoomUserId(String hostId) {
		try {
			String sql = "select * from t_user_base where zoom_id = ?";
			Object[] values = new Object[1];
			values[0] = hostId;
			return libernate.getEntityCustomized(UserBase.class, sql, values);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ConfBase getUserMeetingRoom(UserBase user) {
		try {
			//Add by Darren 2014-12-24 
			DataCenter center = centerService.queryDataCenterById(user.getDataCenterId());
			//updateUserPMI(user);
			String pmi = userOperationComponent.getZoomPMI(center.getApiKey(),center.getApiToken(),user.getZoomId());
			if (StringUtil.isEmpty(pmi) || "0".equals(pmi)) {
				return null;
			}
			if(user!=null && !pmi.equals(user.getPmiId())){
				user.setPmiId(pmi);
				libernate.updateEntity(user, "pmiId");
			}
			
			ConfBase conf = confLogic.getConfByZoomId(user.getPmiId());
			//Add by Darren 2014-12-24
			DataCenter dataCenter = centerService.queryDataCenterById(user.getDataCenterId());
			
			Map<String, Object> meeting = meetingOperationComponent.getMeeting(dataCenter.getApiKey(),dataCenter.getApiToken(),
					user.getPmiId(), user.getZoomId());
			
			//如果没有查询到zoom数据中心的数据 则直接返回数据库中的记录
			if (meeting == null || meeting.get("id") == null
					|| StringUtil.isEmpty(meeting.get("id").toString())) {
				return conf;
			}
			if (conf != null) {
				conf.setStartUrl(String.valueOf(meeting.get("start_url")));
				conf.setJoinUrl(String.valueOf(meeting.get("join_url")));
				if(StringUtil.isEmpty(conf.getConfName())){
					conf.setConfName(String.valueOf(meeting.get("topic")));
				}
				boolean jbh = Boolean.valueOf(meeting.get("option_jbh")
						.toString());
				if (jbh){
					conf.setOptionJbh(1);
				}else{
					conf.setOptionJbh(0);
				}
				
				String option_start_type = String.valueOf(meeting
						.get("option_host_video")).toString();
				if (option_start_type.equals("true")) {
					conf.setOptionStartType(1);
				}else {
					conf.setOptionStartType(2);
				}
				
				int status = (Integer)meeting.get("status");
				if(status == ZoomConfStatus.CONF_RUNING){
					conf.setConfStatus(ConfStatus.LIVING.getStatus());
				}else{
					conf.setConfStatus(ConfStatus.SCHEDULED.getStatus());
				}
				conf.setPermanentConf(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);
				conf.setHostKey(String.valueOf(meeting.get("password")));
				libernate.updateEntity(conf);

				return conf;
			} else {
				ConfBase newConf = new ConfBase();
				newConf.setCreateUser(user.getId());
				newConf.setCompereUser(user.getId());
				newConf.setCompereName(user.getTrueName());
				newConf.setPermanentConf(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);
				newConf.setCreateTime(DateUtil.getGmtDate(null));
				newConf.setStartTime(DateUtil.getGmtDate(null));
				newConf.setConfZoomId(pmi);
				newConf.setTimeZone(user.getTimeZone());
				newConf.setTimeZoneId(user.getTimeZoneId());
				newConf.setSiteId(user.getSiteId());
				newConf.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
				
				newConf.setEndTime(new Date(System.currentTimeMillis() + 20
						* 365 * 24 * 3600000l));
				newConf.setDelTime(new Date(System.currentTimeMillis() + 20
						* 365 * 24 * 3600000l));
				
				int status = (Integer)meeting.get("status");
				if(status == ZoomConfStatus.CONF_RUNING){
					newConf.setConfStatus(ConfStatus.LIVING.getStatus());
				}else{
					newConf.setConfStatus(ConfStatus.SCHEDULED.getStatus());
				}
				newConf.setStartUrl(String.valueOf(meeting.get("start_url")));
				newConf.setJoinUrl(String.valueOf(meeting.get("join_url")));
				newConf.setConfName(String.valueOf(meeting.get("topic")));
				boolean jbh = Boolean.valueOf(meeting.get("option_jbh")
						.toString());
				if (jbh) {
					newConf.setOptionJbh(1);
				}else{
					newConf.setOptionJbh(0);
				}
				//api更新修改  视频选项
				String option_start_type = String.valueOf(meeting
						.get("option_host_video")).toString();
				if (option_start_type.equals("true")) {
					newConf.setOptionStartType(1);
				}else{
					newConf.setOptionStartType(2);
				}
				
				
				newConf.setHostKey(String.valueOf(meeting.get("password")));

				newConf = libernate.saveEntity(newConf);
				return newConf;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询个人永久会议室会议
	 */
	@Override
	public ConfBase getUserPMIformDB(UserBase user) {
		if (user == null || StringUtil.isEmpty(user.getPmiId()))
			return null;
		String sql = "select * from t_conf_base where conf_zoom_id = ? and permanent_conf = ? and compere_user=?";
		try {
			return libernate.getEntityCustomized(ConfBase.class, sql,
					new Object[] { user.getPmiId(), ConfConstant.CONF_PERMANENT_ENABLED_MAIN, user.getId() });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserBase> getSiteUserBySiteId(Integer siteId) {
		
		StringBuffer buffer = new StringBuffer("select * from t_conf_base where del_flag = ? and site_id = ? ");
		
		Object[] values = new Object[2];
		values[0] = ConstantUtil.DELFLAG_UNDELETE;
		values[1] = siteId;
		
		try {
			return libernate.getEntityListBase(UserBase.class, buffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserBase> exportHosts(String keyWord, Object object,
			Integer siteId, Integer userId) {
		
		List<UserBase> userBases = new ArrayList<UserBase>();
		StringBuffer sqlBuffer=new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sqlBuffer.append(" select * from t_user_base where del_flag !=? and user_type = ?");
		values.add(ConstantUtil.DELFLAG_DELETED);
		values.add(ConstantUtil.USERTYPE_USERS);

		if(siteId>0){
			sqlBuffer.append(" and site_id = ?");
			values.add(siteId);
		}
		
		if(keyWord!=null && !"".equals(keyWord)){
			sqlBuffer.append(" and (true_name LIKE '%"+keyWord+"%'");
			sqlBuffer.append(" or user_email LIKE '%"+keyWord+"%'");
			sqlBuffer.append(" or mobile LIKE '%"+keyWord+"%' )");
		}
		sqlBuffer.append(" order by true_name asc ");
		try {
			userBases = libernate.getEntityListCustomized(UserBase.class, sqlBuffer.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBases;
		
	}
	
	
	public void updateLastLoginDate(int userId) {
		String sql = "update t_user_base set last_login_date = ? where id = ?";
		Object[] parms = new Object[2];
		parms[0] = DateUtil.getGmtDate(null);
		parms[1] = userId;
		try{
			libernate.executeSql(sql, parms);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveOrUpdateSysUser(SystemUser sysUser) {
		try{
			if(sysUser.getId()>0){
				libernate.updateEntity(sysUser);
			}else{
				libernate.saveEntity(sysUser);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserBase getSiteUserByMobile(String mobile) {
		if(StringUtil.isEmpty(mobile)){
			return null;
		}
		UserBase userBase = null;
	
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
		sqlBuffer.append(" and del_flag = ?");
		sqlBuffer.append(" and pmi_id = ?");
		sqlBuffer.append(" and user_type = ?");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_UNDELETE;
		values[1] = mobile;
		values[2] = ConstantUtil.USERTYPE_USERS;
		try {
				userBase = libernate.getEntityCustomized(UserBase.class,
						sqlBuffer.toString(), values);
		} catch (SQLException e) {
				 e.printStackTrace();
		}  
		
		return userBase;
	}
	
	@Override
	public UserBase getSiteUserByEmail(String email) {
		if(StringUtil.isEmpty(email)){
			return null;
		}
		UserBase userBase = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
		sqlBuffer.append(" and del_flag = ?");
		sqlBuffer.append(" and user_email = ?");
		sqlBuffer.append(" and user_type = ?");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_UNDELETE;
		values[1] = email;
		values[2] = ConstantUtil.USERTYPE_USERS;
		try {
			userBase = libernate.getEntityCustomized(UserBase.class,
					sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		return userBase;
	}
	
	
	
	public boolean lockUserMeeting(UserBase user){
		//Add by Darren 2014-12-24
		DataCenter confCenter = centerService.queryDataCenterById(user.getDataCenterId());
		
		List<ConfBase> confs =  meetingOperationComponent.getHostMeetinglist(confCenter.getApiKey(),confCenter.getApiToken(),user.getZoomId());
		if(confs!=null && !confs.isEmpty()){
			for(ConfBase conf:confs){
				meetingOperationComponent.modifyMeetingPwd(confCenter.getApiKey(),confCenter.getApiToken(),conf.getConfZoomId(),user.getZoomId(), StringUtil.genRandomNumberPWD());
			}
		}
		if(!StringUtil.isEmpty(user.getPmiId())){
			meetingOperationComponent.modifyMeetingPwd(confCenter.getApiKey(),confCenter.getApiToken(),user.getPmiId(),user.getZoomId(), StringUtil.genRandomNumberPWD());
		}
		return true;
	}
	
	
	public boolean unLockUserMeeting(UserBase user){
		
		//Add by Darren 2014-12-24
		DataCenter confCenter = centerService.queryDataCenterById(user.getDataCenterId());
		
		List<ConfBase> confs =  meetingOperationComponent.getHostMeetinglist(confCenter.getApiKey(),confCenter.getApiToken(),user.getZoomId());
		if(confs!=null && !confs.isEmpty()){
			for(ConfBase conf:confs){
				ConfBase dbConf = confLogic.getConfByZoomId(conf.getConfZoomId());
				if(dbConf!=null){
					meetingOperationComponent.modifyMeetingPwd(confCenter.getApiKey(),confCenter.getApiToken(),conf.getConfZoomId(),user.getZoomId(), dbConf.getHostKey());
				}
			}
		}
		if(!StringUtil.isEmpty(user.getPmiId())){
			ConfBase dbConf = confLogic.getConfByZoomId(user.getPmiId());
			if(dbConf != null && dbConf.getHostKey() !=null)
				meetingOperationComponent.modifyMeetingPwd(confCenter.getApiKey(),confCenter.getApiToken(),user.getPmiId(),user.getZoomId(),dbConf.getHostKey());
			else
				meetingOperationComponent.modifyMeetingPwd(confCenter.getApiKey(),confCenter.getApiToken(),user.getPmiId(),user.getZoomId(), "");
		}
		return true;
	}

	@Override
	public List<UserBase> findExpireUsers() {
		try{
			String sql = "select * from t_user_base where del_flag=? and user_type = ? and exprie_date < ? ";
			
			return libernate.getEntityListCustomized(UserBase.class, sql, new Object[]{
				ConstantUtil.DELFLAG_UNDELETE,
				ConstantUtil.USERTYPE_USERS,
				DateUtil.getGmtDate(null)
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserBase> getuserBaseListForFixedPort(SiteBase currentSite, Date startDate,
			Date endDate, String portnums){
		
		StringBuffer buffer = 
				new StringBuffer("select * from t_user_base where num_partis = ? and site_id = ? ");
		List<Object> values = new ArrayList<Object>();
		values.add(portnums);
		values.add(currentSite.getId());
		
		try {
			return libernate.getEntityListCustomized(UserBase.class, buffer.toString(), values.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
