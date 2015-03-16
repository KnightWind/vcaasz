package com.bizconf.vcaasz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LoginConstants;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.entity.LoginLog;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.service.LoginLogService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class LoginLogServiceImpl extends BaseService implements LoginLogService {

	@Override
	public PageBean<LoginLog> getLoginLog(String siteName, String loginStatus,
			String userType, String loginTimeBegin, String loginTimeEnd,
			String loginName, Integer pageNo,int pageSize, String sortField, String sortord) {
		
		if(pageSize<1){
			pageSize = ConstantUtil.PAGESIZE_DEFAULT;
		}
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder strSql = new StringBuilder();
		if (StringUtil.isNotBlank(siteName)) {
			strSql.append(" SELECT loginLog.* FROM t_login_log loginLog, t_site_base site WHERE 1=1 AND loginLog.`site_id` = site.`id` ");
			strSql.append("     AND site.`site_name` LIKE ? ");
			valueList.add("%" + siteName + "%");
		} else {
			strSql.append(" SELECT loginLog.* FROM t_login_log loginLog WHERE 1=1 ");
		}
		if (StringUtil.isNotBlank(loginStatus)) {
			if("1".equals(loginStatus)){
				strSql.append(" 	AND loginLog.login_status = ? ");
				valueList.add(LoginConstants.LOGIN_ERROR_SUCCESS);
			}else if("2".equals(loginStatus)){
				strSql.append(" 	AND loginLog.login_status != ? ");
				valueList.add(LoginConstants.LOGIN_ERROR_SUCCESS);
			}
		}
		if(("0").equals(userType)){
			userType = "";
		}
		if (StringUtil.isNotBlank(userType)) {
			strSql.append("		AND loginLog.`user_type` = ? ");
			valueList.add(IntegerUtil.parseIntegerWithDefaultZero(userType));
		}
		if (StringUtil.isNotBlank(loginName)) {
			strSql.append("     AND loginLog.login_name LIKE ? ");
			if("_".equals(loginName)){
				loginName = "\\_";
			}
			valueList.add("%" + loginName + "%");
		}
		if(StringUtil.isNotBlank(loginTimeBegin)){
			Date beginTime = DateUtil.StringToDate(loginTimeBegin, "yyyy-MM-dd");
			beginTime = DateUtil.getGmtDate(beginTime);
			strSql.append("     AND loginLog.login_time > ? ");
			valueList.add(DateUtil.getDateStrCompact(beginTime, "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtil.isNotBlank(loginTimeEnd)){
			Date endTime = DateUtil.StringToDate(loginTimeEnd, "yyyy-MM-dd");
			endTime = DateUtil.addDate(DateUtil.getGmtDate(endTime), 1);
			strSql.append("     AND loginLog.login_time < ? ");
			valueList.add(DateUtil.getDateStrCompact(endTime, "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtil.isNotBlank(sortField)) {
			String[][] loginLogFields = SortConstant.LOGIN_LOG_SORT_FIELDS;
			for (String[] eachField : loginLogFields) {
				if (eachField[0].equalsIgnoreCase(sortField)) {
					strSql.append(" order by loginLog."
							+ BeanUtil.att2Field(eachField[1]));
				}
			}
		} else {
			strSql.append(" order by loginLog."
					+ BeanUtil
							.att2Field(SortConstant.LOGIN_LOG_SORT_FIELDS[0][1]));
		}
		if (SortConstant.SORT_ASC.equalsIgnoreCase(sortord)) {
			strSql.append(" asc ");
		} else {
			strSql.append(" desc ");
		}
		PageBean<LoginLog> LoginLogPage = getPageBeans(LoginLog.class,
				strSql.toString(), pageNo != null ? pageNo : 0,pageSize,
				valueList.toArray());
		return LoginLogPage;
	}

	@Override
	public boolean savaUserLoginLog(LoginLog loginLog) {
		try {
			libernate.saveEntity(loginLog);
		} catch (Exception e) {
			logger.error("保存用户登录日志失败！" + e);
			return false;
		}
		return false;
	}

	@Override
	public boolean savaSysLoginLog(LoginLog loginLog) {
		try {
			libernate.saveEntity(loginLog);
		} catch (Exception e) {
			logger.error("保存系统管理员登录日志失败！" + e);
			return false;
		}
		return false;
	}

	@Override
	public PageBean<LoginLog> adminGetLoginLog(String nameOrEmail,int siteId, Integer pageNo, int pageSize,
			String sortField, String sortord) {
		if(pageSize<1){
			pageSize = ConstantUtil.PAGESIZE_DEFAULT;
		}
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder strSql = new StringBuilder();
		if(StringUtil.isNotBlank(nameOrEmail)){
			strSql.append(" SELECT loginLog.* FROM t_login_log loginLog, t_user_base user WHERE 1=1 AND loginLog.`site_id` =? AND loginLog.`user_id` = user.`id` ");
			strSql.append("   AND (user.`true_name` LIKE ? OR user.`user_email` LIKE ?) ");
			valueList.add(siteId);
			valueList.add("%" + nameOrEmail + "%");
			valueList.add("%" + nameOrEmail + "%");
		}else{
			strSql.append(" SELECT loginLog.* FROM t_login_log loginLog WHERE 1=1 AND loginLog.`site_id` =?");
			valueList.add(siteId);
		}
		if(!StringUtil.isNotBlank(sortField)&&!StringUtil.isNotBlank(sortord)){
			strSql.append(" order by loginLog.`login_time` desc");
		}else{
			if (StringUtil.isNotBlank(sortField)) {
				String[][] loginLogFields = SortConstant.LOGIN_LOG_SORT_FIELDS;
				for (String[] eachField : loginLogFields) {
					if (eachField[0].equalsIgnoreCase(sortField)) {
						strSql.append(" order by loginLog."
								+ BeanUtil.att2Field(eachField[1]));
					}
				}
			} else {
				strSql.append(" order by loginLog."
						+ BeanUtil
								.att2Field(SortConstant.LOGIN_LOG_SORT_FIELDS[0][1]));
			}
			if (SortConstant.SORT_ASC.equalsIgnoreCase(sortord)) {
				strSql.append(" asc ");
			} else {
				strSql.append(" desc ");
			}
		}
	
		PageBean<LoginLog> LoginLogPage = getPageBeans(LoginLog.class,
				strSql.toString(), pageNo != null ? pageNo : 0,pageSize,
				valueList.toArray());
		
		return LoginLogPage;
	}
}
