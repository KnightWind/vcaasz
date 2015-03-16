package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.exception.ConfUserException;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.util.DateUtil;

@Component
public class ConfUserLogicImpl extends BaseLogic implements ConfUserLogic {

	int publicConfUserId = -1;
	
	@Override
	public ConfUser addConfBaseUser(ConfBase conf, UserBase user,Integer remindFlag) {
		//confUser中已经邀请了该用户
//		if(getConfUser(conf.getId(), user.getId())!=null){
//			return false;
//		}
		if(getConfUserByEmailAndName(conf.getId(),user.getTrueName(),user.getUserEmail())!=null){
			return null;
		}
		ConfUser confUser = new ConfUser();
		confUser.setAcceptFlag(ConfConstant.CONF_USER_IDLE);
		confUser.setConfId(conf.getId());
		confUser.setConfStatus(conf.getConfStatus());
		confUser.setCreaterUserType(0);
		confUser.setCreateTime(DateUtil.getGmtDate(null));
		confUser.setCreateUser(user.getId());
		confUser.setCycleId(conf.getCycleId());
		confUser.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
		confUser.setDelTime(null);
		confUser.setDelUser(0);
		confUser.setDelUserType(0);
		confUser.setHostFlag((user.getId() != null && 
				conf.getCreateUser().intValue() == user.getId().intValue()) ? 
				ConfConstant.CONF_USER_HOST :  ConfConstant.CONF_USER_PARTICIPANT);
		confUser.setStartTime(conf.getStartTime());
		confUser.setTelephone(user.getPhone());
		confUser.setUserEmail(user.getUserEmail());
		confUser.setSiteId(conf.getSiteId());
		confUser.setUserId( user.getId());
		confUser.setUserName(user.getTrueName());
		confUser.setRemindFlag(remindFlag);	//1 邮件 0 短信
		try {
			return libernate.saveEntity(confUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean addConfUser(ConfBase conf, UserBase user,Integer remindFlag) {
		//confUser中已经邀请了该用户
//		if(getConfUser(conf.getId(), user.getId())!=null){
//			return false;
//		}
		if(getConfUserByEmailAndName(conf.getId(),user.getTrueName(),user.getUserEmail())!=null){
			return false;
		}
		ConfUser confUser = new ConfUser();
		confUser.setAcceptFlag(ConfConstant.CONF_USER_IDLE);
		confUser.setConfId(conf.getId());
		confUser.setConfStatus(conf.getConfStatus());
		confUser.setCreaterUserType(0);
		confUser.setCreateTime(DateUtil.getGmtDate(null));
		confUser.setCreateUser(user.getId());
		confUser.setCycleId(conf.getCycleId());
		confUser.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
		confUser.setDelTime(null);
		confUser.setDelUser(0);
		confUser.setDelUserType(0);
		confUser.setHostFlag((user.getId() != null && 
				conf.getCreateUser().intValue() == user.getId().intValue()) ? 
				ConfConstant.CONF_USER_HOST :  ConfConstant.CONF_USER_PARTICIPANT);
		confUser.setStartTime(conf.getStartTime());
		confUser.setTelephone(user.getPhone());
		confUser.setUserEmail(user.getUserEmail());
		confUser.setSiteId(conf.getSiteId());
		confUser.setUserId( user.getId());
		confUser.setUserName(user.getTrueName());
		confUser.setRemindFlag(remindFlag);	//1 邮件 0 短信
		try {
			libernate.saveEntity(confUser);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteConfUser(int confId, int userId) {
		try {
			libernate.executeSql(
					"update t_conf_user set del_flag=? where conf_id=? and user_id=?",
					new Object[] { ConstantUtil.DELFLAG_DELETED, confId, userId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteConfUsersByConfId(int confId) {
		try {
			libernate.executeSql(
					"update t_conf_user set del_flag=? where conf_id=?",
					new Object[] { ConstantUtil.DELFLAG_DELETED, confId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteConfUsersByCycleId(int cycleId) {
		if (cycleId <= 0) {
			throw new ConfUserException(
					"cycleId can not be zero or less then zero.");
		}
		try {
			libernate.executeSql(
					"update t_conf_user set del_flag=? where cycle_id=?",
					new Object[] { ConstantUtil.DELFLAG_DELETED, cycleId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public ConfUser getConfUser(int confId, int userId) {
//		if(userId<=0){
//			return null;
//		}
		try {
			ConfUser confUser = libernate.getEntityWithCondition(
					ConfUser.class, "conf_id=? and user_id=? and del_flag=?", new Object[] {
							confId, userId, ConstantUtil.DELFLAG_UNDELETE});
			if (confUser != null) {
				return confUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	@Override
	public ConfUser getConfUserHosterForPublicConf(int confId) {
		try {
			ConfUser confUser = libernate.getEntityWithCondition(
					ConfUser.class, "conf_id=? and user_id=? and host_flag=? and del_flag=?", new Object[] {
							confId, -1,ConfConstant.CONF_USER_HOST, ConstantUtil.DELFLAG_UNDELETE});
			if (confUser != null) {
				return confUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public ConfUser getConfUserByEmailAndName(int confId, String userName,String email) {
		if(userName==null){
			userName = email;
		}
		if(email==null){
			return null;
		}
		try {
			ConfUser confUser = libernate.getEntityWithCondition(
					ConfUser.class, "conf_id=? and user_name=? and user_email=? and del_flag=?", new Object[] {
							confId, userName, email,ConstantUtil.DELFLAG_UNDELETE});
			if (confUser != null) {
				return confUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 可能存在以下情况：
	 * 1、公开改为非公开
	 * 2、非公开改为公开
	 * 3、保持公开
	 * 4、保持非公开
	 */
	@Override
	public boolean updateConfUser(ConfBase conf, UserBase user) {
		ConfUser confUser = this.getConfUser(conf.getId(), user.getId());
		//之前为公开会议的情况
		if (confUser == null) {
			confUser = this.getConfUser(conf.getId(), publicConfUserId);
			confUser.setUserId(user.getId());
		}
		//之前为非公开会议的情况
		else {
			 
		}
		confUser.setConfStatus(conf.getConfStatus());
		confUser.setStartTime(conf.getStartTime());
		confUser.setCycleId(conf.getCycleId());
		
		try {
			libernate.updateEntity(confUser, "user_id","conf_status", "start_time",
					"cycle_id");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ConfUser getConfUser(int id) {
		try {
			ConfUser confUser = libernate.getEntityWithCondition(
					ConfUser.class, "id=?", new Object[] {id});
			if (confUser != null) {
				return confUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@Override
	public String retrieveAcceptURI(int confId, int userId, String userName, String userEmail) {
		ConfUser confUser = null;
		if (userId <= 0) {
			confUser = getConfUserByEmailAndName(confId, userName, userEmail);
			if (confUser == null) {
				return "";
			}
		}
		else {
			confUser = getConfUser(confId, userId);
		}
		return String.format("user/inviteUser/recv?cuid=%d&confId=%d", 
				confUser == null ? 0 : confUser.getId(), confId);
	}
	
	@Override
	public String retrieveRefuseURI(int confId, int userId, String userName, String userEmail) {
		ConfUser confUser = null;
		if (userId <= 0) {
			confUser = getConfUserByEmailAndName(confId, userName, userEmail);
			if (confUser == null) {
				return "";
			}
		}
		else {
			confUser = getConfUser(confId, userId);
		}
		return String.format("user/inviteUser/refuse?cuid=%d&confId=%d", 
				confUser == null ? 0 : confUser.getId(), confId);
	}
	
	
	@Override
	public List<ConfUser> getConfUserList(int confId) {
		List<ConfUser> confUserList = null;
		try {
			confUserList = libernate.getEntityListWithCondition("conf_id=? and del_flag=? ",
					 new Object[]{confId, ConstantUtil.DELFLAG_UNDELETE}, 
					 ConfUser.class);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return confUserList;
	}
	
	@Override
	public Collection<ConfUser> getCycleConfUsers(int cycId) {
		Set<ConfUser> confUsers = new HashSet<ConfUser>();
		List<ConfUser> confUserList = null;
		try {
			confUserList = libernate.getEntityListWithCondition("cycle_id=? and del_flag=? " ,
					 new Object[]{cycId, ConstantUtil.DELFLAG_UNDELETE}, 
					 ConfUser.class);
			if(confUserList!=null && confUserList.size()>0){
				confUsers.addAll(confUserList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confUsers;
	}

}
