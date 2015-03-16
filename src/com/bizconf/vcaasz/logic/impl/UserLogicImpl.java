package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LicenseConstant;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 
 * @author Administrator
 * @date 2013-5-22
 */
@Component
public class UserLogicImpl extends BaseLogic implements UserLogic {

	@Override
	public UserBase getUserBaseByLoginName(String loginName,Integer siteId) {
		UserBase userBase = null;
		if(siteId != null && siteId.intValue() > 0){
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and login_name = ?");
			sqlBuffer.append(" and site_id = ? ");
			sqlBuffer.append(" and user_type = ?");
			Object[] values=new Object[4];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = loginName;
			values[2] = siteId;
			values[3] = ConstantUtil.USERTYPE_USERS;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据登录名获取指定站点下的用户出错",e);
			}finally{
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}

	@Override
	public UserBase getUserBaseByZoomId(String hostId) {
		
		if(StringUtil.isEmpty(hostId)){
			return null;
		}
		
		String sql = "select * from t_user_base where del_flag = ? and zoom_id = ? ";
		try {
			return libernate.getEntityCustomized(UserBase.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,hostId});
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<UserBase> getSiteHosts(int siteId) {
		
		String sql = "select * from t_user_base where site_id = ? and user_type = ? and del_flag = ?";
		try {
			return libernate.getEntityListCustomized(UserBase.class, sql, new Object[]{siteId,ConstantUtil.USERTYPE_USERS, ConstantUtil.DELFLAG_UNDELETE});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean lockSiteHosts(int siteId) {
		
		String sql = "update t_user_base set user_status = ? where site_id = ? and user_type = ? and del_flag = ?";
		try {
			
			return libernate.executeSql(sql, new Object[]{ConstantUtil.USER_STATU_LOCK, siteId, ConstantUtil.USERTYPE_USERS, ConstantUtil.DELFLAG_UNDELETE});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean unLockSiteHosts(int siteId) {
		
		String sql = "update t_user_base set user_status = ? where  site_id = ? and user_type = ? and del_flag = ?";
		try {
			
			return libernate.executeSql(sql, new Object[]{ConstantUtil.USER_STATU_UNLOCK, siteId,ConstantUtil.USERTYPE_USERS, ConstantUtil.DELFLAG_UNDELETE});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserBase getUserBaseByEmail(String email) {
		String sql = "select * from t_user_base where user_type=? and  user_email = ?";
		if(StringUtil.isEmpty(email)){
			return null;
		}
		try {
			return libernate.getEntityCustomized(UserBase.class, sql, new Object[]{ConstantUtil.USERTYPE_USERS,email});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserBase getUserPmiByEmail(String mobile) {
		String sql = "select * from t_user_base where user_type=? and  pmi_id = ?";
		if(StringUtil.isEmpty(mobile)){
			return null;
		}
		try {
			return libernate.getEntityCustomized(UserBase.class, sql, new Object[]{ConstantUtil.USERTYPE_USERS,mobile});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public int findHostExtraPort(int hostId, Date startDate, Date endDate) {
		logger.info("the endDate is: "+endDate);
		List<Object> values = new ArrayList<Object>();// 
		String sql = "select sum(lic_num) from t_license where del_flag = ? "
				+ "and user_id=?  and effe_date < ? and expire_date > ?";
		
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(hostId);
		values.add(endDate);
		values.add(endDate);
		try {
			return  libernate.countEntityListWithSql(sql, values.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
