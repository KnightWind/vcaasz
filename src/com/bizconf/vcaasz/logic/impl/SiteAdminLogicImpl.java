package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.SiteAdminLogic;
import com.bizconf.vcaasz.util.StringUtil;

@Component
public class SiteAdminLogicImpl extends BaseLogic implements SiteAdminLogic {

	@Override
	public UserBase getSiteAdmin(int siteId, String loginName) {
		try {
			return libernate
					.getEntityWithCondition(
							UserBase.class,
							"site_id=? and login_name=? and (user_type = ? or user_type = ?) and del_flag=?",
							new Object[] { siteId, loginName,
									ConstantUtil.USERTYPE_ADMIN,
									ConstantUtil.USERTYPE_ADMIN_SUPPER,
									ConstantUtil.DELFLAG_UNDELETE});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据email，获取站点管理员信息
	 * wangyong
	 * 2013-2-28
	 */
	public UserBase getSiteAdminByEmail(int siteId, String Email){
		UserBase userBase = null;
		if(StringUtil.isNotBlank(Email)){
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and user_email = ?");
			sqlBuffer.append(" and site_id = ? ");
			sqlBuffer.append(" and (user_type = ? or user_type = ?) ");
			Object[] values=new Object[5];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = Email.trim();
			values[2] = siteId;
			values[3] = ConstantUtil.USERTYPE_ADMIN;
			values[4] = ConstantUtil.USERTYPE_ADMIN_SUPPER;
			try {
				userBase = libernate.getEntityCustomized(UserBase.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				logger.error("根据邮箱名获取站点管理员出错",e);
			}finally{
				sqlBuffer = null;
				values = null;
			}
		}
		return userBase;
	}
}
