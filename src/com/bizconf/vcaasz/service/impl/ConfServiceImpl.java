package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.constant.APIReturnCode;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.MeetingStartType;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.constant.ZoomConfStatus;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.Condition;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfBaseSimple;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.ConfUserCount;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.service.ConfLogService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class ConfServiceImpl extends BaseService implements ConfService {

	@Autowired
	private ConfLogic confLogic;
	 
	@Autowired
	private EmailService emailService;

	@Autowired
	private ConfUserService confUserService;

	@Autowired
	private UserService userService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private ConfLogService confLogService;

	@Autowired
	private EmpowerConfigService empowerConfigService;

	@Autowired
	private ConfUserLogic confUserLogic;

	@Autowired
	private ZoomMeetingOperationComponent meetingOperationComponent;
	
	@Autowired
	private DataCenterService dataCenterService;

	/**
	 * 统计站点用户查询与自己相关正在进行中会议的条数
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public Integer countDuringConfList(String titleOrHostName,
			UserBase currentUser) {
		Integer rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			 

			sqlStr.append(" SELECT count(1) FROM t_conf_base  tcb WHERE tcb.id IN( ");
			sqlStr.append(" SELECT tmp.conf_id FROM ( ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,tcu.`start_time` FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` =? AND (tcu.`user_id`=? OR tcu.`user_id`=? ) AND site_id=? AND tcu.`conf_status`=? AND del_flag = ?");
			sqlStr.append(" UNION ALL ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,MIN(tcu.`start_time`) AS start_time FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` >? AND (tcu.`user_id`=? OR tcu.`user_id`=?) AND site_id=? AND tcu.`conf_status`=? AND del_flag = ?");
			sqlStr.append(" GROUP BY tcu.`cycle_id`");
			sqlStr.append(" ) tmp ");
			sqlStr.append(" ) ");
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_OPENING);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_OPENING);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			sqlStr.append(" AND tcb.start_time < ? ");
			Date now_time = DateUtil.getGmtDate(null);
			valueList.add(now_time);
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			Object[] values = valueList.toArray();
			try {
				rows = libernate.countEntityListWithSql(sqlStr.toString(),
						values);
			} catch (SQLException e) {
				logger.error("统计站点用户查询与自己相关正在进行中会议的条数出错！" + e);
			}
		}
		return rows;
	}

	/**
	 * 站点用户查询与自己相关正在进行中会议
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式
	 * @param pageModel
	 *            分页对象
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public List<ConfBase> listDuringConfList(String titleOrHostName,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser, SiteBase currentSite) {
		List<ConfBase> confList = new ArrayList<ConfBase>();
		if (currentUser != null) {
			List<Object> valueList = new ArrayList<Object>();
			StringBuffer sqlStr = new StringBuffer();
			 

			sqlStr.append(" SELECT * FROM t_conf_base  tcb WHERE tcb.id IN( ");
			sqlStr.append(" SELECT tmp.conf_id FROM ( ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,tcu.`start_time` FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` =? AND (tcu.`user_id`=? OR tcu.`user_id`=? ) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" UNION ALL ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,MIN(tcu.`start_time`) AS start_time FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` >? AND (tcu.`user_id`=? OR tcu.`user_id`=?) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" GROUP BY tcu.`cycle_id`");
			sqlStr.append(" ) tmp ");
			sqlStr.append(" ) ");
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_OPENING);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_OPENING);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			sqlStr.append(" AND tcb.start_time < ? ");
			Date now_time = DateUtil.getGmtDate(null);
			valueList.add(now_time);
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}

			if (StringUtil.isNotBlank(sortField)) {
				String sortFieldValue = initSort(sortField); // 获取页面传递的排序参数
				String sortordValue = "desc";
				if (SortConstant.SORT_ASC.equals(sortord)) {
					sortordValue = "asc";
				}
				if (StringUtil.isNotBlank(sortFieldValue)) {
					sqlStr.append(" order by ").append("tcb.")
							.append(sortFieldValue).append(sortordValue);
				}
			} else {
				sqlStr.append(" order by tcb.start_time DESC "); // 查出列表无排序条件则为默认逆序
			}
			if (pageModel != null) {
				int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
						* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
				sqlStr.append(" limit ? , ?  ");
				valueList.add(recordNo);
				valueList.add(pageModel.getPageSize());
			}
			Object[] values = valueList.toArray();
			try {
				confList = libernate.getEntityListBase(ConfBase.class,
						sqlStr.toString(), values);
				if (confList != null && confList.size() > 0) {
					confList = getOffsetConfList(currentUser, confList);
				}
			} catch (SQLException e) {
				logger.error("站点用户查询与自己相关正在进行中会议出错！" + e);
			}
		}
		return confList;
	}

	/**
	 * 统计站点用户查询与自己相关即将开始会议的条数
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public Integer countUpcomingConfList(String titleOrHostName,
			UserBase currentUser, Integer days) {
		Integer rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT count(1) FROM t_conf_base  tcb WHERE tcb.id IN( ");
			sqlStr.append(" SELECT tmp.conf_id FROM ( ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,tcu.`start_time` FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` =? AND (tcu.`user_id`=? OR tcu.`user_id`=? ) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" UNION ALL ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,MIN(tcu.`start_time`) AS start_time FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` >? AND (tcu.`user_id`=? OR tcu.`user_id`=?) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" GROUP BY tcu.`cycle_id`");
			sqlStr.append(" ) tmp ");
			sqlStr.append(" ) ");
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			Date now_time = DateUtil.getGmtDate(null);
			// sqlStr.append(" AND tcb.start_time > ? ");
			// valueList.add(now_time);
			if (days != null && days.intValue() != 0) {
				sqlStr.append(" AND tcb.start_time < ? ");
				Date end_time = DateUtil.addDate(now_time, days.intValue());
				valueList.add(end_time);
			}
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			Object[] values = valueList.toArray();
			try {
				rows = libernate.countEntityListWithSql(sqlStr.toString(),
						values);
			} catch (SQLException e) {
				logger.error("统计站点用户查询与自己相关即将开始会议的条数出错！" + e);
			}
		}
		return rows;
	}

	/**
	 * 站点用户查询与自己相关即将开始会议
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式
	 * @param pageModel
	 *            分页对象
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public List<ConfBase> listUpcomingConfList(String titleOrHostName,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser, SiteBase currentSite, Integer days) {
		List<ConfBase> confList = new ArrayList<ConfBase>();
		if (currentUser != null) {
			List<Object> valueList = new ArrayList<Object>();
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT * FROM t_conf_base  tcb WHERE tcb.id IN( ");
			sqlStr.append(" SELECT tmp.conf_id FROM ( ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,tcu.`start_time` FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` =? AND (tcu.`user_id`=? OR tcu.`user_id`=? ) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" UNION ALL ");
			sqlStr.append(" SELECT tcu.`conf_id`,tcu.`cycle_id`,MIN(tcu.`start_time`) AS start_time FROM t_conf_user  tcu ");
			sqlStr.append(" WHERE tcu.`cycle_id` >? AND (tcu.`user_id`=? OR tcu.`user_id`=?) AND site_id=? AND tcu.`conf_status`=?  AND del_flag = ?");
			sqlStr.append(" GROUP BY tcu.`cycle_id`");
			sqlStr.append(" ) tmp ");
			sqlStr.append(" ) ");
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(0);
			valueList.add(currentUser.getId());
			valueList.add(-1);
			valueList.add(currentUser.getSiteId().intValue());
			valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			Date now_time = DateUtil.getGmtDate(null);
			// sqlStr.append(" AND tcb.start_time > ? ");
			// valueList.add(now_time);
			if (days != null && days.intValue() != 0) {
				// sqlStr.append(" AND tcb.start_time > ? ");
				sqlStr.append(" AND tcb.start_time < ? ");
				Date end_time = DateUtil.addDate(now_time, days.intValue());
				// valueList.add(DateUtil.getGmtDate(null));
				valueList.add(end_time);
			}
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			if (StringUtil.isNotBlank(sortField)) {
				String sortFieldValue = initSort(sortField); // 获取页面传递的排序参数
				String sortordValue = "desc";
				if (SortConstant.SORT_ASC.equals(sortord)) {
					sortordValue = "asc";
				}
				if (StringUtil.isNotBlank(sortFieldValue)) {
					sqlStr.append(" order by ").append("tcb.")
							.append(sortFieldValue).append(sortordValue);
				}
			} else {
				sqlStr.append(" order by tcb.start_time ASC "); // 查出列表无排序条件则为默认逆序
			}
			if (pageModel != null) {
				int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
						* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
				sqlStr.append(" limit ? , ?  ");
				valueList.add(recordNo);
				valueList.add(pageModel.getPageSize());
			}
			 
			Object[] values = valueList.toArray();
			try {
				confList = libernate.getEntityListBase(ConfBase.class,
						sqlStr.toString(), values);
				if (confList != null && confList.size() > 0) {
					confList = getOffsetConfList(currentUser, confList);
				}
			} catch (SQLException e) {
				logger.error("站点用户查询与自己相关即将开始会议出错！" + e);
			}
		}
		return confList;
	}

	/**
	 * 统计站点用户查询与自己相关错过的会议的条数
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public Integer countMissConfList(String titleOrHostName,
			UserBase currentUser, Integer days, Integer hideFlag) {
		Integer rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT count(1) FROM t_conf_base  tcb ,t_conf_user tcup WHERE 1=1 ");
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			if (days != null && days.intValue() != 0) {
				sqlStr.append(" AND tcb.start_time > ? ");
				Date start_time = DateUtil.addDate(DateUtil.getGmtDate(null),
						-days.intValue());
				valueList.add(start_time);
			}
			Integer userId = currentUser.getId();
			if (userId != null && userId.intValue() > 0) {
				sqlStr.append(" AND tcb.conf_status= ? AND tcb.del_flag = ? AND tcb.id=tcup.conf_id AND tcup.user_id=? ");
				valueList.add(ConfConstant.CONF_STATUS_FINISHED);
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(userId);
				if (hideFlag != null) {
					sqlStr.append(" AND tcup.hide_flag = ?");
					valueList.add(hideFlag);
				}
				sqlStr.append(" AND NOT EXISTS( SELECT DISTINCT tcu.conf_id FROM   t_conf_user tcu, t_conf_log tcl ");
				sqlStr.append(" WHERE 1=1 And tcu.user_id=? ");
				sqlStr.append(" AND tcu.conf_id=tcl.conf_id AND tcl.user_id=tcu.user_id AND tcb.id=tcl.conf_id ) ");
				valueList.add(userId);
			}
			Object[] values = valueList.toArray();
			try {
				rows = libernate.countEntityListWithSql(sqlStr.toString(),
						values);
			} catch (SQLException e) {
				logger.error("统计站点用户查询与自己相关错过会议的条数出错！" + e);
			}
		}
		return rows;
	}

	/**
	 * 站点用户查询与自己相关错过的会议
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式
	 * @param pageModel
	 *            分页对象
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public List<ConfBase> listMissConfList(String titleOrHostName,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser, SiteBase currentSite, Integer days,
			Integer hideFlag) {
		List<ConfBase> confList = new ArrayList<ConfBase>();
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT tcb.* FROM t_conf_base  tcb ,t_conf_user tcup WHERE 1=1 ");
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			if (days != null && days.intValue() != 0) {
				sqlStr.append(" AND tcb.start_time > ? ");
				Date start_time = DateUtil.addDate(DateUtil.getGmtDate(null),
						-days.intValue());
				valueList.add(start_time);
			}
			Integer userId = currentUser.getId();
			if (userId != null && userId.intValue() > 0) {
				sqlStr.append(" AND tcb.conf_status= ? AND tcb.del_flag = ? AND tcb.id=tcup.conf_id AND tcup.user_id=? ");
				valueList.add(ConfConstant.CONF_STATUS_FINISHED);
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(userId);
				if (hideFlag != null) {
					sqlStr.append(" AND tcup.hide_flag = ?");
					valueList.add(hideFlag);
				}
				sqlStr.append(" AND NOT EXISTS( SELECT DISTINCT tcu.conf_id FROM   t_conf_user tcu, t_conf_log tcl ");
				sqlStr.append(" WHERE 1=1 And tcu.user_id=? ");
				sqlStr.append(" AND tcu.conf_id=tcl.conf_id AND tcl.user_id=tcu.user_id AND tcb.id=tcl.conf_id ) ");
				valueList.add(userId);
			}
			if (StringUtil.isNotBlank(sortField)) {
				String sortFieldValue = initSort(sortField); // 获取页面传递的排序参数
				String sortordValue = "desc";
				if (SortConstant.SORT_ASC.equals(sortord)) {
					sortordValue = "asc";
				}
				if (StringUtil.isNotBlank(sortFieldValue)) {
					sqlStr.append(" order by ").append("tcb.")
							.append(sortFieldValue).append(sortordValue);
				}
			} else {
				sqlStr.append(" order by tcb.start_time DESC "); // 查出列表无排序条件则为默认逆序
			}
			if (pageModel != null) {
				int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
						* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
				sqlStr.append(" limit ? , ?  ");
				valueList.add(recordNo);
				valueList.add(pageModel.getPageSize());
			}
			Object[] values = valueList.toArray();
			try {
				confList = libernate.getEntityListBase(ConfBase.class,
						sqlStr.toString(), values);
				if (confList != null && confList.size() > 0) {
					confList = getOffsetConfList(currentUser, confList);
				}
			} catch (SQLException e) {
				logger.error("站点用户查询与自己相关错过会议出错！" + e);
			}
		}
		return confList;
	}

	/**
	 * 统计站点用户查询与自己相关已经加入过会议的条数
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public Integer countAttendedConfList(String titleOrHostName,
			UserBase currentUser, Integer days) {
		Integer rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT count(1) FROM t_conf_base  tcb ,t_conf_user tcup WHERE 1=1 ");
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			if (days != null && days.intValue() != 0) {
				sqlStr.append(" AND tcb.start_time > ? ");
				Date start_time = DateUtil.addDate(DateUtil.getGmtDate(null),
						-days.intValue());
				valueList.add(start_time);
			}
			Integer userId = currentUser.getId();
			if (userId != null && userId.intValue() > 0) {
				sqlStr.append(" AND tcb.conf_status= ? AND tcb.del_flag = ? AND tcb.id=tcup.conf_id AND tcup.user_id=? ");
				valueList.add(ConfConstant.CONF_STATUS_FINISHED);
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(userId);
				sqlStr.append(" AND EXISTS( SELECT DISTINCT tcu.conf_id FROM   t_conf_user tcu, t_conf_log tcl ");
				sqlStr.append(" WHERE 1=1 And tcu.user_id=? ");
				sqlStr.append(" AND tcu.conf_id=tcl.conf_id AND tcl.user_id=tcu.user_id AND tcb.id=tcl.conf_id ) ");
				valueList.add(userId);
			}
			Object[] values = valueList.toArray();
			try {
				rows = libernate.countEntityListWithSql(sqlStr.toString(),
						values);
			} catch (SQLException e) {
				logger.error("统计站点用户查询与自己相关已经加入过会议的条数出错！" + e);
			}
		}
		return rows;
	}

	/**
	 * 站点用户查询与自己相关已经加入过会议
	 * 
	 * @param titleOrHostName
	 *            会议主题或主持人
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式
	 * @param pageModel
	 *            分页对象
	 * @param currentUser
	 *            当前站点用户 wangyong 2013.3.5
	 */
	@Override
	public List<ConfBase> listAttendedConfList(String titleOrHostName,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser, SiteBase currentSite, Integer days) {
		List<ConfBase> confList = new ArrayList<ConfBase>();
		List<Object> valueList = new ArrayList<Object>();
		if (currentUser != null) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append(" SELECT tcb.* FROM t_conf_base  tcb ,t_conf_user tcup WHERE 1=1 ");
			if (StringUtil.isNotBlank(titleOrHostName)) {
				sqlStr.append(" AND (tcb.conf_name like ? or tcb.compere_name like ? )");
				valueList.add("%" + titleOrHostName + "%");
				valueList.add("%" + titleOrHostName + "%");
			}
			if (days != null && days.intValue() != 0) {
				sqlStr.append(" AND tcb.start_time > ? ");
				Date start_time = DateUtil.addDate(DateUtil.getGmtDate(null),
						-days.intValue());
				valueList.add(start_time);
			}
			Integer userId = currentUser.getId();
			if (userId != null && userId.intValue() > 0) {
				sqlStr.append(" AND tcb.conf_status= ? AND tcb.del_flag = ? AND tcb.id=tcup.conf_id AND tcup.user_id=? ");
				valueList.add(ConfConstant.CONF_STATUS_FINISHED);
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(userId);
				sqlStr.append(" AND EXISTS( SELECT DISTINCT tcu.conf_id FROM   t_conf_user tcu, t_conf_log tcl ");
				sqlStr.append(" WHERE 1=1 And tcu.user_id=? ");
				sqlStr.append(" AND tcu.conf_id=tcl.conf_id AND tcl.user_id=tcu.user_id AND tcb.id=tcl.conf_id ) ");
				valueList.add(userId);
			}
			if (StringUtil.isNotBlank(sortField)) {
				String sortFieldValue = initSort(sortField); // 获取页面传递的排序参数
				String sortordValue = "desc";
				if (SortConstant.SORT_ASC.equals(sortord)) {
					sortordValue = "asc";
				}
				if (StringUtil.isNotBlank(sortFieldValue)) {
					sqlStr.append(" order by ").append("tcb.")
							.append(sortFieldValue).append(sortordValue);
				}
			} else {
				sqlStr.append(" order by tcb.start_time DESC "); // 查出列表无排序条件则为默认逆序
			}
			if (pageModel != null) {
				int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
						* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
				sqlStr.append(" limit ? , ?  ");
				valueList.add(recordNo);
				valueList.add(pageModel.getPageSize());
			}
			Object[] values = valueList.toArray();
			try {
				confList = libernate.getEntityListBase(ConfBase.class,
						sqlStr.toString(), values);
				if (confList != null && confList.size() > 0) {
					confList = getOffsetConfList(currentUser, confList);
				}
			} catch (SQLException e) {
				logger.error("站点用户查询与自己相关已经加入过会议出错！" + e);
			}
		}
		return confList;
	}

	/**
	 * 操作数据库，新建会议信息
	 * 
	 * @author Administrator darren 2014-6-16
	 */
	private ConfBase saveConf(ConfBase confBase) {
		ConfBase conf = new ConfBase();
		try {
			conf = libernate.saveEntity(confBase);
		} catch (Exception e) {
			logger.error("保存会议信息出错！", e);
		}
		return conf;
	}

	/**
	 * 创建一个即时会议 即时会议只有会议名称，其他字段均为默认配置
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param siteBase
	 *            站点基本信息
	 * @param user
	 *            创建会议用户基本信息 wangyong 2013.3.5
	 */
	@Override
	public ConfBase createImmediatelyConf(ConfBase conf, SiteBase siteBase,
			UserBase user) {
		ConfBase confBase = new ConfBase();
		if (conf != null) {
			boolean flag = confLogic.saveConfValidate(conf, null, siteBase);
			if (flag) {
				 
				conf.initImmediaConf(null, siteBase, user);// 2013.9.3
																	// 修复即时会议bug
				// 保存之前进行初始化数据
				conf = initConf(conf, siteBase, user, null);

				// 即时会议类型
				conf.setConfType(ConfType.INSTANT);
				boolean allow_jbh = (conf.getOptionJbh() == ConfConstant.ALLOW_JBH);
				String start_type = MeetingStartType.setStatusCode(conf
						.getOptionStartType());
				
				//add by Darren 2014-12-24
				DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
				
				Map<String, Object> retMap = meetingOperationComponent
						.createInstantMeeting(center.getApiKey(),center.getApiToken(), user.getZoomId(),
								conf.getConfName(), conf.getHostKey(),
								allow_jbh, start_type);
				if (!retMap.isEmpty() && retMap.get("id") != null) {
					try {
						conf.setConfType(ConfType.INSTANT);
						// 如果是立即会议需要让主持人加入后才显示。
						conf.setConfStatus(ConfStatus.INVALID.getStatus());

						conf.setConfZoomId(retMap.get("id").toString());// zoomId
						conf.setJoinUrl((String) retMap.get("join_url"));
						conf.setStartUrl((String) retMap.get("start_url"));

						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String createdAt = (String) retMap.get("created_at");// 2014-06-16T08:19:07Z
						createdAt = createdAt.replace("T", " ")
								.replace("Z", "");
						Date startDate = format.parse(createdAt);

						// Calendar calendar = Calendar.getInstance();
						// calendar.setTime(startDate);
						// calendar.add(Calendar.HOUR, +8);
						// conf.setStartTime(calendar.getTime());

						conf.setStartTime(startDate);
						conf.setCompereUser(user.getId());// 主持人id

						// int du = (Integer)retMap.get("duration");
						// 即时会议默认60分钟
						conf.setDuration(60);

						// 新添加会议的uuid
						conf.setUuid((String) retMap.get("uuid"));
						//H323password
						if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
							conf.setPhonePass((String)retMap.get("h323Password"));
						}else{
							conf.setPhonePass("");
						}
						
						confBase = saveConf(conf);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					return getErrorConf(confBase, "在zoom上创建会议失败");
				}

				if (confBase != null && confBase.getId() != null
						&& confBase.getId().intValue() > 0) {
					confUserService.fillConfUserForCreate(confBase, user);
				}
			}
		}
		return confBase;
	}

	/**
	 * 创建一个单次预约会议
	 * 
	 * @param conf
	 *             
	 */
	@Override
	public ConfBase createSingleReservationConf(ConfBase conf,
			SiteBase siteBase, UserBase user,boolean isSingleRes) {
		ConfBase confBase = new ConfBase();
		if (conf != null) {
			 
			boolean dataFlag = confLogic.saveConfValidate(conf, null, siteBase);
			if (dataFlag) {
				// 保存之前进行一次初始化信息保存
				conf = initConf(conf, siteBase, user, null);
				
				//add by Darren 2014-12-24
				DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
				
				Map<String, Object> retMap = meetingOperationComponent
						.createScheduleMeeting(center.getApiKey(),center.getApiToken(),user.getZoomId(),
								conf.getConfName(), conf.getZoomStartTime(),
								conf.getDuration(), conf.getTimeZoneSecond(),
								conf.getHostKey(), conf.getBooleanOptionJbh(),
								conf.getOptionStartTypeStr());
				if (retMap.isEmpty() || retMap.get("error") != null) {
					return getErrorConf(conf, "在zoom上创建会议失败");
				}
				
				// -----
				conf.setConfType(ConfType.SCHEDULE);
//				if(isSingleRes){
//				}
				
				try {
					conf.setConfZoomId(retMap.get("id").toString());// zoomId
					conf.setJoinUrl((String) retMap.get("join_url"));
					conf.setStartUrl((String) retMap.get("start_url"));

					String start_time = (String) retMap.get("start_time");// 2014-06-16T08:19:07Z
					start_time = start_time.replace("T", " ").replace("Z", "");
					
					conf.setCompereUser(user.getId());// 主持人id
					conf.setDuration((Integer) retMap.get("duration"));
					// 新添加会议的uuid
					conf.setUuid((String) retMap.get("uuid"));
					//H323password
					if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
						conf.setPhonePass((String)retMap.get("h323Password"));
					}else{
						conf.setPhonePass("");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				confBase = saveConf(conf);
				Integer confId = confBase.getId();
				if (confBase != null && confId != null && confId.intValue() > 0) {
					// boolean createCompere =
					confUserService.fillConfUserForCreate(confBase, user);
					// if(createCompere){
					// logger.info("创建会议后保存主持人信息成功");
					// }else{
					// logger.info("创建会议后保存主持人信息失败");
					// }
					// 发送单次预约会议信息到主持人邮箱
					
					if(isSingleRes){		//是普通预约会议
						boolean sendEmail = emailService.createConfEmail(confBase,
								null, user);
						if (sendEmail) {
							logger.info("发送会议信息到主持人邮箱成功");
						} else {
							logger.info("发送会议信息到主持人邮箱失败");
						}
					}else{					//
						logger.info("不是普通周期会议，不发送预约会议邮件");
					}
					
					// 由于emailService.createConfEmail(confBase, null,
					// user)修改了StartTime，因此此处把时间设置为数据库中的GMT时间
//					confBase.setStartTime(confBase.getStartTimeGmt());
//					confBase = getOffsetConf(user, confBase);
				}
			} else {
				// 针对不法方式绕过前台用户，该错误不可以返回到页面，不可让客户知道该类错误，只记录错误到logger文件
				logger.debug("正则表达式验证页面输入数据");
				return confBase;
			}
		}
		return confBase;
	}

	/**
	 * 重新创建一个单次预约会议 1.只能创建单次预约会议 2.保留非周期会议信息 3.copy参会人，主持人与新会议的关联 4.不是修改，是新建
	 * 5.重新给参会人发送邮件
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param siteBase
	 *            站点基本信息
	 * @param user
	 *            创建会议用户基本信息 wangyong 2013.3.5
	 */
	@Override
	public ConfBase reCreateconf(ConfBase conf, SiteBase siteBase, UserBase user) {
		ConfBase confBase = new ConfBase();
		if (conf != null) {
			// //先检测参会人数是否大于站点当前所剩参会人数值
			// boolean licenceFlag = confLogic.createConfLicenseVali(conf, null,
			// siteBase, user);
			// if(!licenceFlag){
			// confBase.setId(ConfConstant.CONF_CREATE_ERROR_LICENCE);
			// return confBase;
			// }
			// 再检测在正则表达式验证页面输入数据
			boolean dataFlag = confLogic.saveConfValidate(conf, null, siteBase);
			if (dataFlag) {
				conf = initConf(conf, siteBase, user, null);

				//add by Darren 2014-12-24
				DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
				
				Map<String, Object> retMap = meetingOperationComponent
						.createScheduleMeeting(center.getApiKey(),center.getApiToken(), user.getZoomId(),
								conf.getConfName(), conf.getZoomStartTime(),
								conf.getDuration(), conf.getTimeZoneSecond(),
								conf.getHostKey(), conf.getBooleanOptionJbh(),
								conf.getOptionStartTypeStr());
				try {

					if (!retMap.isEmpty() && retMap.get("error") == null) {
						conf.setConfZoomId(retMap.get("id").toString());// zoomId
						conf.setJoinUrl((String) retMap.get("join_url"));
						conf.setStartUrl((String) retMap.get("start_url"));

						String start_time = (String) retMap.get("start_time");// 2014-06-16T08:19:07Z
						start_time = start_time.replace("T", " ").replace("Z",
								"");
						Date startDate = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").parse(start_time);

						conf.setStartTime(startDate);
						conf.setCompereUser(user.getId());// 主持人id
						//H323password
						if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
							conf.setPhonePass((String)retMap.get("h323Password"));
						}else{
							conf.setPhonePass("");
						}
					} else {
						logger.error("create conf fialed  msg:"
								+ retMap.toString());
					}
					confBase = saveConf(conf);

				} catch (Exception e) {

					e.printStackTrace();
				}
				Integer confId = confBase.getId();
				if (confBase != null && confId != null && confId.intValue() > 0) {
					confUserService.fillConfUserForCreate(confBase, user); // 创建会议完成后调用保存主持人信息
					boolean createCompere = copyConfUser(confBase, conf.getId()); // 拷贝一份原会议的参会人用户到重新创建后的参会用户表
					if (createCompere) {
						logger.info("重新创建会议后拷贝一份参会人用户到参会用户表成功");
					} else {
						logger.info("重新创建会议后拷贝一份参会人用户到参会用户表失败");
					}
					// 发送单次预约会议信息到主持人邮箱
					boolean sendEmail = emailService.createConfEmail(confBase,
							null, user);
					if (sendEmail) {
						logger.info("发送会议信息到主持人邮箱成功");
					} else {
						logger.info("发送会议信息到主持人邮箱失败");
					}
					List<ConfUser> confUsers = confUserService
							.getConfInviteUserList(confId);
					boolean sendConfUsers = emailService.sendInviteToConfUser(
							confUsers, confBase);
					if (sendConfUsers) {
						logger.info("邀请联系人参会成功");
					} else {
						logger.info("邀请联系人参会失败");
					}
					// 由于emailService.createConfEmail(confBase, null,
					// user)修改了StartTime，因此此处把时间设置为数据库中的GMT时间
					confBase.setStartTime(confBase.getStartTimeGmt());
					confBase = getOffsetConf(user, confBase);
				}
			} else {
				// 针对不法方式绕过前台用户，该错误不可以返回到页面，不可让客户知道该类错误，只记录错误到logger文件
				logger.debug("正则表达式验证页面输入数据");
				return confBase;
			}
		}
		return confBase;
	}

	/**
	 * 修改单次预约会议
	 * 
	 * @param conf
	 *            会议基本信息 wangyong 2013.3.5
	 */
	@Override
	public ConfBase updateSingleReservationConf(ConfBase conf,
			SiteBase siteBase, UserBase user) {
		ConfBase confBase = new ConfBase();
		Integer timeZone = conf.getTimeZone();
		if (timeZone == null) {
			timeZone = 0;
		}
		conf.setStartTime(DateUtil.getGmtDateByTimeZone(conf.getStartTime(),
				timeZone));
		conf.setEndTime(DateUtil.addDateMinutes(conf.getStartTime(),
				conf.getDuration()));
		String[] passArray = new String[] { "id", "confName", "confDesc",
				"hostKey", "confType", "startTime", "duration", "endTime",
				"timeZoneId", "timeZone", "optionJbh", "optionStartType" };
		if (conf != null && conf.getId() > 0) {
			// 再检测在正则表达式验证页面输入数据
			boolean flag = confLogic.saveConfValidate(conf, null, siteBase);
			// //2013.7.23 超过最大并发会议数
			// if(!checkSiteMaxConfCount(conf, siteBase)){
			// conf.setId(ConfConstant.CONF_CREATE_ERROR_SYNC_LICENCE);
			// //超过最大并发会议数
			// logger.info("超过最大并发会议数，创建会议失败");
			// return conf;
			// }
			// 2013.7.23
			if (flag) {
				try {
					// String retInfo = confManagementService.updateConf(conf,
					// siteBase, user);
					/**
					 * 在zoom上创建会议
					 * **/
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String startTime = sdf.format(conf.getStartTime());
					startTime = startTime.replace(" ", "T") + "Z";

					ConfBase tempConfBase = getConfBasebyConfId(conf.getId());// 获取到该条数据的zoomId

					boolean optionJbhTemp = (conf.getOptionJbh() == 0) ? false
							: true;
					String optionStartType = "video";
					if (conf.getOptionStartType() == 2) {
						optionStartType = "screen_share";
					}

					//add by Darren 2014-12-24
					DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
					
					int ret = meetingOperationComponent.updateMeeting(center.getApiKey(),center.getApiToken(),
							tempConfBase.getConfZoomId(),
							user.getZoomId(), conf.getConfName(), startTime,
							conf.getDuration(), conf.getTimeZoneSecond(),
							conf.getHostKey(), optionJbhTemp, optionStartType);
					if (ret == 0) {
						return getErrorConf(conf, "在zoom上修改会议失败");
					} else {
						
						//get H323 password ,add by darren 2015-01-30 
						Map<String, Object> retMap = meetingOperationComponent.getMeeting(center.getApiKey(), center.getApiToken(), tempConfBase.getConfZoomId(), user.getZoomId());
						if(retMap.get("error") == null){
							if(!StringUtil.isEmpty((String)retMap.get("h323Password")) && 
									!StringUtil.isEmpty(conf.getHostKey())){
								//passArray[passArray.length + 1] = "phone_pass";
								conf.setPhonePass((String)retMap.get("h323Password"));
							}else{
								conf.setPhonePass("");
							}
							libernate.updateEntity(conf, "phonePass");
						}
						
						confBase = libernate.updateEntity(conf, passArray);
						confBase = getConfBasebyConfId(confBase.getId());
						if (confBase != null && confBase.getId() != null
								&& confBase.getId().intValue() > 0) {
							// 修改会议状态
							// confUserService.fillConfUserForModify(confBase,
							// user);
							// 修改之后发送邮件
							List<ConfUser> confUsers = confUserService
									.getAllConfUserList(confBase.getId()
											.intValue(),1);
							emailService.confModifyEmail(confUsers, confBase);
						}
					}
				} catch (Exception e) {
					logger.error("修改单次预约会议出错！", e);
				}
			}
		}
		return confBase;
	}

	/**
	 * 主持人删除(取消)周期预约会议
	 * 
	 * @param cycleId
	 *            周期会议id
	 * @param currentSite
	 *            当前站点
	 * @param currentUser
	 *            当前登录用户
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean cancleCycleConfInfo(Integer cycleId, SiteBase currentSite,
			UserBase currentUser) {
		boolean cancleHwFlag = true;
		boolean cancleFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		List<ConfBase> confs = getConfWithHwIdByCycleId(cycleId);
		try {
			for (Iterator it = confs.iterator(); it.hasNext();) {
				ConfBase conf = (ConfBase) it.next();
				if (conf != null && conf.getId() != null
						&& conf.getId().intValue() > 0) {
					if (!cancleHwFlag) {
						logger.info("主持人删除(取消)周期预约会议:华为接口取消一个预约会议失败！");
						return false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("主持人删除(取消)周期预约会议:取消一个预约会议出错！", e);
		}
		try {
			StringBuilder sqlBuilder = new StringBuilder(
					"UPDATE t_conf_base SET ");
			// sqlBuilder.append(" conf_status=?, del_flag=?, del_time=?, del_user=? ");
			sqlBuilder.append(" conf_status=?,  del_time=?, del_user=? ");
			valueList.add(ConfConstant.CONF_STATUS_CANCELED);
			// valueList.add(ConstantUtil.DELFLAG_DELETED);
			valueList.add(DateUtil.getGmtDate(null));
			valueList.add(currentUser.getId());
			sqlBuilder
					.append(" WHERE cycle_id = ? and del_flag = ? and conf_status=? ");
			valueList.add(cycleId);
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
			Object[] values = valueList.toArray();
			cancleFlag = libernate.executeSql(sqlBuilder.toString(), values);
			if (cancleFlag) {
				boolean createCompere = confUserService
						.fillConfUserForCancelCycle(cycleId, currentUser);
				if (createCompere) {
					logger.info("取消会议后保存主持人信息成功");
				} else {
					logger.info("取消会议后保存主持人信息失败");
				}

			}
		} catch (Exception e) {
			logger.error("主持人删除(取消)周期预约会议出错！", e);
		}
		if (cancleHwFlag && cancleFlag) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 主持人删除(取消)周期预约会议中的一条会议
	 * 
	 * @param confId
	 *            会议id
	 * @param currentSite
	 *            当前站点
	 * @param currentUser
	 *            当前登录用户
	 */
	@Override
	public boolean cancleSingleCycleConfInfo(Integer confId,
			SiteBase currentSite, UserBase currentUser) {
		boolean cancleHwFlag = true;
		boolean cancleFlag = false;
		ConfBase confBase = null;
		ConfBase conf = getConfBasebyConfId(confId);
		try {
			if (conf != null && conf.getId() != null
					&& conf.getId().intValue() > 0) {
				// 因目前正在进行的会议也可以取消 如果是正在进行的会议，状态设置为结束而非取消
				if (!ConfConstant.CONF_STATUS_OPENING.equals(conf
						.getConfStatus())) {
					conf.setConfStatus(ConfConstant.CONF_STATUS_CANCELED);
					conf.setDelTime(DateUtil.getGmtDate(null));
					conf.setDelUser(currentUser.getId());
				} else {
					conf.setConfStatus(ConfConstant.CONF_STATUS_INVALID);
				}

				confBase = libernate.updateEntity(conf, "id", "confStatus",
						"delTime", "delUser");

//				List<ConfUser> confUsers = confUserLogic.getConfUserList(conf
//						.getId());
				List<ConfUser> confUsers = confUserService.getAllConfUserList(confId,1);
				emailService.confCancelEmail(confUsers, conf);
			}
		} catch (Exception e) {
			logger.error("取消一个预约会议出错！", e);
		}
		if (confBase != null && confBase.getId() != null
				&& confBase.getId().intValue() > 0) {
			cancleFlag = true;
			boolean createCompere = confUserService.fillConfUserForCancel(
					confBase.getId(), currentUser);
			if (createCompere) {
				logger.info("取消会议后保存主持人信息成功");
			} else {
				logger.info("取消会议后保存主持人信息失败");
			}
		}
		if (cancleHwFlag && cancleFlag) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 取消一个会议
	 */
	@Override
	public boolean cancleSingleReservationConf(Integer confId,
			SiteBase currentSite, UserBase currentUser) {

		ConfBase conf = getConfBasebyConfId(confId);
		//add by Darren 2014-12-24
		DataCenter center = dataCenterService.queryDataCenterById(currentUser.getDataCenterId());
		
		try {
			if (ConfConstant.CONF_STATUS_SUCCESS.equals(conf.getConfStatus())) {
				
				if(conf.getActulStartTime()!=null){
					conf.setConfStatus(ConfConstant.CONF_STATUS_FINISHED);
					
					if(conf.isClientCycleConf()){
						conf.setEndTime(DateUtil.getGmtDate(null));
					}
				}else{
					conf.setConfStatus(ConfConstant.CONF_STATUS_CANCELED);
				}
				if (meetingOperationComponent.deleteMeeting(center.getApiKey(),center.getApiToken(),
						conf.getConfZoomId(), currentUser.getZoomId()) != ZoomConfStatus.CONF_DELETE_STATU_SUCCESS) {
					logger.error("delete conf in zoom system fail!");
				}
				
				
				// 只有预约成功的会议才发送会议取消邮件通知
				List<ConfUser> confUsers = confUserService.getAllConfUserList(confId,1);
				emailService.confCancelEmail(confUsers, conf.clone());

			} else if (ConfConstant.CONF_STATUS_OPENING.equals(conf
					.getConfStatus())) {
				
				//结束会议
				if (!meetingOperationComponent.endMeeting(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(),
						currentUser.getZoomId())) {
					logger.error("end meeting in zoom fail!! ");
				}
				//删除会议
				if (meetingOperationComponent.deleteMeeting(center.getApiKey(),center.getApiToken(),
						conf.getConfZoomId(), currentUser.getZoomId()) != ZoomConfStatus.CONF_DELETE_STATU_SUCCESS) {
					logger.error("delete conf in zoom system fail!");
				}
				
				if(conf.isClientCycleConf()){
					conf.setEndTime(DateUtil.getGmtDate(null));
				}
				conf.setConfStatus(ConfConstant.CONF_STATUS_FINISHED);
				//conf.setEndTime(DateUtil.getGmtDate(null));
			} else {
				conf.setDelFlag(ConstantUtil.DELFLAG_DELETED);
				conf.setDelTime(DateUtil.getGmtDate(null));
				conf.setDelUser(currentUser.getId());
			}
			libernate.updateEntity(conf, "id", "confStatus", "end_time",
					"delTime", "delUser");

		} catch (Exception e) {
			logger.error("取消一个预约会议出错！", e);
			return false;
		}
		return true;
	}
 
	private ConfCycle createCycleConf(ConfCycle confCycle,
			SiteBase currentSite, UserBase currentUser, Integer timeZone) {
		confCycle.setBeginDate(DateUtil.getGmtDateByTimeZone(
				confCycle.getBeginDate(), timeZone));
		confCycle.setCreateUser(currentUser.getId());
		confCycle.setEndDate(DateUtil.getGmtDateByTimeZone(
				confCycle.getEndDate(), timeZone));
		confCycle.setSiteId(currentSite.getId());
		try {
			confCycle = libernate.saveEntity(confCycle);
		} catch (Exception e) {
			logger.error("保存 会议信息--循环会议设置出错！" + confCycle, e);
		}
		return confCycle;
	}

	private ConfBase createCycleReservationConf(ConfBase conf,
			ConfCycle confCycleBase, SiteBase currentSite,
			UserBase currentUser, List<Date> confDateList) {
		
		ConfBase confBase = new ConfBase();
		
		conf = initConf(conf, currentSite, currentUser, confCycleBase);
		if (confDateList == null || confDateList.size() <= 0) {
			logger.info("周期会议获取周期时间范围出错！" + confDateList);
			return confBase;
			// Martin 周期会议只创建一条永久会议记录
		} else {
			Collections.sort(confDateList);
			Date firstDate = confDateList.get(0);
			
			conf.setStartTime(DateUtil.getGmtDateByTimeZone(
					firstDate, conf.getTimeZone()));
			conf.setEndTime(new Date(conf.getStartTime().getTime()+
						conf.getDuration()*60*1000l));
		}
		Integer cycleId = confCycleBase.getId();
		// 初始化会议信息
		conf.setConfType(ConfType.RECURR);
		conf.setCycleId(cycleId);
		try {
			//保存周期会议
			confBase = libernate.saveEntity(conf);
			// 创建周期会议完成后，调用保存主持人信息
			if (!confUserService.fillConfUserForCreateCycle(
					getCycleConfDate(cycleId), currentUser)) {
				logger.info("创建会议后保存主持人信息失败");
			}
			
			// 发送周期会议信息到主持人邮箱,会议的时间应为gmt时间，发送邮件接口会转换gmt到站点时区时间
			if (!emailService.createConfEmail(confBase, confCycleBase,
					currentUser)) {
				logger.info("发送周期会议信息到主持人邮箱失败");
			}
			
//			confBase.setStartTime(confBase.getStartTimeGmt());
			// 返回到controller的会议时间从gmt转换为偏好设置时区的时间
//			getOffsetConf(currentUser, confBase);
		} catch (Exception e) {
			logger.error("创建周期预约会议失败！" + conf, e);
		}
		return confBase;
	}

	
	/**
	 * 创建周期预约会议
	 * 
	 * @param conf
	 *            会议基本信息
	 */
	@Override
	public ConfBase createCycleReservationConf(ConfBase conf,
			ConfCycle confCycle, SiteBase currentSite, UserBase currentUser) {
			
		ConfBase firstCycleConf = new ConfBase();
	
		ConfCycle confCycleBase = null;
		if (confCycle != null && currentUser != null && currentSite != null) {
			// 先检测参会人数是否大于站点当前所剩参会人数值
			// boolean licenceFlag = confLogic.createConfLicenseVali(conf,
			// confCycle, currentSite, currentUser);
			// if(!licenceFlag){
			// firstCycleConf.setId(ConfConstant.CONF_CREATE_ERROR_LICENCE);
			// return firstCycleConf;
			// }

			// 设置会议类型为周期会议
			conf.setConfType(ConfType.RECURR);
			/**
			 * 在zoom上创建永久会议
			 * **/
			// 组织参数
			boolean optionJbhTemp = (conf.getOptionJbh() == 0) ? false : true;
			String optionStartType = "video";
			if (conf.getOptionStartType() == 2) {
				optionStartType = "screen_share";
			}

			//add by Darren 2014-12-24
			DataCenter center = dataCenterService.queryDataCenterById(currentUser.getDataCenterId());
			
			Map<String, Object> retMap = meetingOperationComponent
					.createPermanentMeeting(center.getApiKey(),center.getApiToken(),currentUser.getZoomId(),
							conf.getConfName(), conf.getHostKey(),
							optionJbhTemp, optionStartType);
			if (retMap.isEmpty() || retMap.get("error") != null) {
				return getErrorConf(conf, "在zoom上创建永久会议失败");
			}
			try {
				conf.setConfZoomId(retMap.get("id").toString());// zoomId
				conf.setJoinUrl((String) retMap.get("join_url"));
				conf.setStartUrl((String) retMap.get("start_url"));
				conf.setCompereUser(currentUser.getId());// 主持人id
				// 新添加会议的uuid
				conf.setUuid((String) retMap.get("uuid"));
				//H323password
				if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
					conf.setPhonePass((String)retMap.get("h323Password"));
				}else{
					conf.setPhonePass("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 时区设置
			Integer timeZone = 0;
			if(conf.getTimeZone() != null){
				
				timeZone = conf.getTimeZone();
			}
			else if (currentUser.getTimeZone() != null) {
				timeZone = currentUser.getTimeZone();
			} else if (currentSite.getTimeZone() != null) {
				timeZone = currentSite.getTimeZone();
			}
			// 用户所设置时区的周期开始时间
			Date localBeginDate = confCycle.getBeginDate();
			Date localEndDate = confCycle.getEndDate();
			/**
			 * 保存会议的周期属性到数据库confCycle对象中
			 * */
			confCycleBase = createCycleConf(confCycle, currentSite,
					currentUser, timeZone);
			if (confCycleBase != null && confCycleBase.getId() != null
					&& confCycleBase.getId().intValue() > 0) {
				// 会议的重复次数
				int cycleLimit = confCycleBase.getRepeatCount().intValue() < 1000
						&& confCycleBase.getRepeatCount().intValue() != 0 ? confCycleBase
						.getRepeatCount().intValue()
						: ConstantUtil.CYCLE_CONF_DATE_LIMIT;
				// 自动生成预约会议的日期集合（根据周期范围和类型取出周期中的所有循环日期）
				List<Date> confDateList = DateUtil.getCycleDateFromScope(
						localBeginDate, localEndDate,
						confCycleBase.getCycleType(),
						confCycleBase.getCycleValue(), cycleLimit);
				if (confDateList == null || confDateList.size() == 0) {
					logger.info("未获得有效循环周期日期");
					//设置返回信息的key值 虽然方式很SB  whatever
					firstCycleConf.setId(-4);
					return firstCycleConf;
				}
				// 若周期时间早于当前时间，则移除该时间
				for (int i = 0; i < confDateList.size(); i++) {
					if (!DateUtil.getGmtDateByTimeZone(confDateList.get(i),
							timeZone).after(DateUtil.getGmtDate(null))) {
						confDateList.remove(i);
					}
				}
				/**
				 * 保存周期会议 到ConfBase并且和confCycleBase设置关联 并且会在这里发送周期邮件
				 * */
				firstCycleConf = createCycleReservationConf(conf,
						confCycleBase, currentSite, currentUser, confDateList);
			} else {
				logger.info("创建周期预约会议出错！" + confCycle);
			}
		}
		return firstCycleConf;
	}


	/**
	 * 修改周期预约会议中的一条会议 流程： 1.将该次周期会议改为单次预约会议，即cycleId改为0
	 * 2.copy一份参会用户（包括主持人）到参会用户表中，并且conf_id设置为本次会议的confId，cycle_id设置为0
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentSite
	 *            当前站点
	 * @param currentUser
	 *            当前用户
	 */
	@Override
	public ConfBase updateSingleCycleConfInfo(ConfBase conf,
			SiteBase currentSite, UserBase currentUser) {
		ConfBase confBase = new ConfBase();
		// ConfBase hwConf = null;
		Integer timeZone = conf.getTimeZone();
		if (timeZone == null) {
			timeZone = 0;
		}
		 
		conf.setStartTime(DateUtil.getGmtDateByTimeZone(conf.getStartTime(),
				timeZone));
		conf.setEndTime(DateUtil.addDateMinutes(conf.getStartTime(),
				conf.getDuration()));
		String[] passArray = new String[] { "id", "cycleId", "confName",
				"hostKey", "confDesc", "confType", "startTime", "duration",
				"endTime"};
		if (conf != null && conf.getId() > 0) {

			// 再检测在正则表达式验证页面输入数据
			boolean flag = confLogic.saveConfValidate(conf, null, currentSite);
			// 2013.7.23 超过最大并发会议数
			if (!checkSiteMaxConfCount(conf, currentSite)) {
				conf.setId(ConfConstant.CONF_CREATE_ERROR_SYNC_LICENCE); // 超过最大并发会议数
				logger.info("超过最大并发会议数，创建会议失败");
				return conf;
			}
			// 2013.7.23
			if (flag) {
				conf.setCycleId(0);
				try {
//					String retInfo = confManagementService.updateConf(conf,
//							currentSite, currentUser);
					// if(retInfo.equals(ConstantUtil.AS_FAILED_LICENSE_CODE) ||
					// retInfo.equals(ConstantUtil.AS_FAILED_LICENSE_CODE_1)){
					// confBase.setId(ConfConstant.CONF_CREATE_ERROR_LICENCE);
					// //参会人数大于站点当前所剩参会人数值
					// logger.info("参会人数大于站点当前所剩参会人数值");
					// return confBase;
					// }else if(!retInfo.equals(ConstantUtil.AS_SUCCESS_CODE)){
					// //该错误不可以返回到页面，不可让客户知道该类错误
					// logger.info("华为返回错误码：" + retInfo);
					// return confBase;
					// }
//					if (!retInfo.equals(ConstantUtil.AS_SUCCESS_CODE)) {
//						return getErrorConf(confBase, retInfo);
//					}
					confBase = libernate.updateEntity(conf, passArray);
					if (confBase != null && confBase.getId() != null
							&& confBase.getId().intValue() > 0) {
						boolean createCompere = confUserService
								.fillConfUserForModify(confBase, currentUser);
						if (createCompere) {
							logger.info("修改会议后保存主持人信息成功");
						} else {
							logger.info("修改会议后保存主持人信息失败");
						}
						List<ConfUser> confUsers = confUserService
								.getAllConfUserList(confBase.getId().intValue(),1);
						boolean sendConfUsers = emailService.confModifyEmail(
								confUsers, confBase);
						if (sendConfUsers) {
							logger.info("修改会议后通知联系人成功");
						} else {
							logger.info("修改会议后通知联系人失败");
						}
					}
				} catch (Exception e) {
					logger.error("修改单次预约会议出错！", e);
				}
			}
		}
		return confBase;
	}

	/**
	 * 修改周期预约会议 注意：不可修改循环设置部分
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentSite
	 *            当前站点
	 * @param currentUser
	 *            当前用户 
	 */
	@Override
	public ConfBase updateCycleConfInfo(ConfBase conf, SiteBase siteBase,
			UserBase user) {
		boolean updateFlag = false;
		ConfBase returnConf = new ConfBase();
		List<ConfBase> confList = getCycleConfDate(conf.getCycleId(), siteBase);// 现在只返回一条
		if (confList.isEmpty()) {
			return returnConf;
		}
		ConfBase confBase = confList.get(0);

		Integer timeZone = conf.getTimeZone();
		if (timeZone == null) {
			timeZone = 0;
		}
		conf.setStartTime(DateUtil.getGmtDateByTimeZone(conf.getStartTime(),
				timeZone));
		Calendar calendarConf = Calendar.getInstance();
		Calendar calendarConfBase = Calendar.getInstance();
		calendarConf.setTime(conf.getStartTime());

		if (conf.getId().intValue() == confBase.getId().intValue()) {
			calendarConfBase.setTime(DateUtil.getGmtDateByTimeZone(
					confBase.getStartTime(), timeZone));
		}
		long confTime = calendarConf.getTimeInMillis();
		long confBaseTime = calendarConfBase.getTimeInMillis();
		long missMinutes = (confTime - confBaseTime) / 60000; // 相差多少分钟

		confBase.setStartTime(DateUtil.addDateMinutes(DateUtil
				.getGmtDateByTimeZone(confBase.getStartTime(), timeZone),
				(int) missMinutes));
		confBase.setEndTime(DateUtil.addDateMinutes(confBase.getStartTime(),
				conf.getDuration()));
		String updateFileds = "host_key=?, conf_name=?, conf_desc=?, start_time=?, duration=?, end_time=?,time_zone = ?,option_jbh = ? , option_start_type = ? ,time_zone_id = ?,phone_pass = ?";

		boolean allow_jbh = (conf.getOptionJbh() == ConfConstant.ALLOW_JBH);
		String start_type = MeetingStartType.setStatusCode(conf
				.getOptionStartType());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(conf.getStartTime());
		
		//add by Darren 2014-12-24
		DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
		
		int retsult = meetingOperationComponent.updateMeeting(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(),
				String.valueOf(user.getZoomId()), conf.getConfName(),
				startTime, conf.getDuration(), String.valueOf(timeZone),
				conf.getHostKey(), allow_jbh, start_type);
		if (retsult == 0) {
			return getErrorConf(conf, "在zoom上修改会议失败");
		}
		try {
			
			StringBuilder sqlBuilder = new StringBuilder(
					"UPDATE t_conf_base SET ");
			sqlBuilder.append(updateFileds);
			List<Object> valueList = new ArrayList<Object>();
			valueList.add(conf.getHostKey());
			valueList.add(conf.getConfName());
			valueList.add(conf.getConfDesc());
			valueList.add(confBase.getStartTime());
			valueList.add(conf.getDuration());
			valueList.add(confBase.getEndTime());
			 

			valueList.add(conf.getTimeZone());
			valueList.add(conf.getOptionJbh());
			valueList.add(conf.getOptionStartType());
			valueList.add(conf.getTimeZoneId());
			
			//get H323 password ,add by darren 2015-01-30 
			Map<String, Object> retMap = meetingOperationComponent.getMeeting(center.getApiKey(), center.getApiToken(), conf.getConfZoomId(), user.getZoomId());
			if(retMap.get("error") == null){
				if(!StringUtil.isEmpty((String)retMap.get("h323Password"))){
					valueList.add((String)retMap.get("h323Password"));
				}else{
					valueList.add("");
				}
			}
			
			sqlBuilder.append(" WHERE del_flag = ? and id = ?");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(confBase.getId());
			
			// 修改周期会议
			updateFlag = libernate.executeSql(sqlBuilder.toString(),
					valueList.toArray());
		} catch (Exception e) {
			logger.error("修改周期预约会议出错！", e);
		}
		returnConf = confBase;
		if (updateFlag) {
			List<ConfBase> newConfList = getCycleConfDate(conf.getCycleId(),
					siteBase);
			boolean createCompere = confUserService.fillConfUserForModifyCycle(
					newConfList, user);
			if (createCompere) {
				logger.info("修改会议后保存主持人信息成功");
			} else {
				logger.info("修改会议后保存主持人信息失败");
			}
			if (newConfList != null && newConfList.size() > 0) {
				returnConf = newConfList.get(0);
			}
		}
		return returnConf;
	}

	/**
	 * 主持人邀请参会人
	 * 
	 * @param participantsList
	 *            参会人
	 * @param currentUser
	 *            当前登录用户 wangyong 2013-3-6
	 */
	@Override
	public boolean inviteParticipants(Integer confId, Integer cycleId,
			List<UserBase> participantsList, UserBase currentUser) {
		boolean saveFlag = false;
		ConfBase conf = getConfBasebyConfId(confId);
		if (conf != null) {
			// 调用发邮件接口通知参会人
			// emailService.sendConfInvite(participantsList, conf);
			// 保存参会人到数据库中
			StringBuilder sqlBuilder = new StringBuilder(
					"INSERT INTO t_conf_user ");
			sqlBuilder
					.append(" ( conf_id, cycle_id, user_id, user_name, user_email, telephone, host_flag, accept_flag, ");
			sqlBuilder
					.append(" create_time, create_user, creater_user_type, del_flag, del_time, del_user, del_user_type ) ");
			sqlBuilder.append(" VALUES ");
			int userSize = participantsList.size();
			for (int i = 1; i < userSize + 1; i++) {
				if (i == userSize) {
					sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?)");
				} else {
					sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?),");
				}
			}
			List<Object> valueList = getParticipantsList(participantsList,
					confId, cycleId, currentUser);
			try {
				Object[] values = valueList.toArray();
				saveFlag = libernate.executeSql(sqlBuilder.toString(), values);
			} catch (Exception e) {
				logger.error("主持人邀请参会人失败！", e);
				saveFlag = false;
			}
		}
		return saveFlag;
	}

	/**
	 * 获得联系人list wangyong 2013-3-6
	 */
	private List<Object> getParticipantsList(List<UserBase> participantsList,
			Integer confId, Integer cycleId, UserBase currentUser) {
		List<Object> valueList = new ArrayList<Object>();
		for (UserBase user : participantsList) {
			if (cycleId != null && cycleId.intValue() > 0) {
				valueList.add(0); // 若为周期会议用户，则confid为0，cycleid不为0
				valueList.add(cycleId);
			} else {
				valueList.add(confId); // 若为单次会议用户，则confid不为0，cycleid为0
				valueList.add(0);
			}
			Integer userId = user.getId();
			if (userId != null) {
				valueList.add(userId);
			} else {
				valueList.add(0);
			}
			if (StringUtil.isNotBlank(user.getTrueName())) {
				valueList.add(user.getTrueName());
			} else {
				valueList.add("");
			}
			if (StringUtil.isNotBlank(user.getUserEmail())) {
				valueList.add(user.getUserEmail());
			} else {
				valueList.add("");
			}
			if (StringUtil.isNotBlank(user.getMobile())) {
				valueList.add(user.getMobile());
			} else {
				valueList.add("");
			}
			valueList.add(ConfConstant.CONF_USER_PARTICIPANT);
			valueList.add(0);
			valueList.add(DateUtil.getGmtDate(null));
			valueList.add(currentUser.getId());
			valueList.add(currentUser.getUserType());
			valueList.add(0);
			try {
				valueList.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse("1970-01-01 00:00:00"));
			} catch (ParseException e) {
				logger.error("主持人邀请参会人转换删除时间出错！" + e);
			}
			valueList.add(0);
			valueList.add(0);
		}
		return valueList;
	}

	/**
	 * 重新创建会议后拷贝一份参会人用户到参会用户表
	 * 
	 * @param conf
	 *            重新创建后的会议
	 * @param oldConfId
	 *            原会议ID wangyong 2013-3-18
	 */
	private boolean copyConfUser(ConfBase conf, Integer oldConfId) {
		boolean copyFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		String countsql = "select count(*) from t_conf_user where conf_id = ? ";
		if (conf != null && conf.getId() > 0) {
			try {
				int count = libernate.countEntityListWithSql(countsql,
						new Object[] { oldConfId });
				if (count == 0)
					return true;

				StringBuilder sqlBuilder = new StringBuilder(
						" INSERT INTO t_conf_user ");
				sqlBuilder
						.append(" (conf_id, cycle_id, conf_status, user_id, site_id, user_name, user_email, telephone, host_flag, accept_flag, ");
				sqlBuilder
						.append("  create_time, create_user, creater_user_type, del_flag, del_time, del_user, del_user_type ) ");
				sqlBuilder
						.append(" SELECT ?, cycle_id, user_id, user_name, user_email, telephone, host_flag, accept_flag, create_time, ");
				sqlBuilder
						.append("  create_user, creater_user_type, del_flag, del_time, del_user, del_user_type ");
				sqlBuilder.append("  FROM t_conf_user where conf_id = ? ");
				valueList.add(conf.getId().intValue());
				valueList.add(oldConfId.intValue());
				Object[] values = valueList.toArray();
				copyFlag = libernate.executeSql(sqlBuilder.toString(), values);
			} catch (Exception e) {
				logger.error("拷贝一份参会人用户到参会用户表出错！", e);
			}
		}
		return copyFlag;
	}

	/**
	 * 保存会议时，初始化会议信息 wangyong 2013-3-4
	 */
	private ConfBase initConf(ConfBase confBase, SiteBase siteBase,
			UserBase user, ConfCycle confCycle) {
		if (confBase != null && siteBase != null && user != null) {
			 
			confBase.setSiteId(siteBase.getId());
			confBase.setCycleId(0); // 此处全部初始化为预约会议，周期会议在创建周期会议方法中初始化完毕后设置
			if (!StringUtil.isNotBlank(confBase.getConfDesc())) {
				confBase.setConfDesc("");
			}
			Integer timeZone = 0;
			Integer timeZoneId = 0;

			if (confBase.getTimeZone() != null) {// 2014-07-01 add
				timeZone = confBase.getTimeZone();
				timeZoneId = confBase.getTimeZoneId();
			} else if (user.getTimeZone() != null) {
				timeZone = user.getTimeZone();
				timeZoneId = user.getTimeZoneId();
			} else if (siteBase.getTimeZone() != null) {
				timeZone = siteBase.getTimeZone();
				timeZoneId = siteBase.getTimeZoneId();
			}
			confBase.setTimeZone(timeZone);
			confBase.setTimeZoneId(timeZoneId);
			confBase.setStartTime(DateUtil.getGmtDateByTimeZone(
					confBase.getStartTime(), timeZone));
			if (confBase.getDuration() != null
					&& confBase.getDuration().intValue() > 0) {
				confBase.setDuration(confBase.getDuration());// 转换为分钟为单位的值
			} else {
				confBase.setDuration(ConfConstant.CONF_DEFAULT_DURATION);
			}

			if (confBase.getEndTime() == null) {
				confBase.setEndTime(DateUtil.addDateMinutes(
						confBase.getStartTime(), confBase.getDuration()));
			} else {
				confBase.setEndTime(DateUtil.getGmtDateByTimeZone(
						confBase.getEndTime(), timeZone));
			}
			confBase.setCompereUser(user.getId());
			confBase.setCompereName(user.getTrueName());
			confBase.setMaxUser(2);
			 
			confBase.setCreateTime(DateUtil.getGmtDate(null)); // 创建时间初始化为GMT时间
			confBase.setCreateUser(user.getId());
			confBase.setCreateType(user.getUserType());
			try {
				confBase.setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse("1970-01-01 00:00:00"));
			} catch (ParseException e) {
				logger.error("保存会议时，初始化会议信息,转换删除时间出错！" + e);
			}
			 
		}
		return confBase;
	}


	/**
	 * 站点管理员根据会议主题统计会议总数
	 * 
	 * @param subject
	 *            会议主题
	 * @param siteId
	 *            站点ID
	 * @param siteUserId
	 *            普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * 
	 */
	@Override
	public int countConfListBySubject(String subject, Integer siteId,
			Integer siteUserId) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" SELECT count(1) FROM  t_conf_base a, t_user_base b WHERE 1=1 ");
		if (subject != null) {
			strSql.append(" AND ( a.conf_name LIKE ? OR a.conf_hwid = ? )");
			valueList.add("%" + subject + "%");
			valueList.add(subject);
		}
		if (siteId != null && siteId.intValue() > 0) {
			strSql.append(" AND a.site_id = ? ");
			valueList.add(siteId.intValue());
		}
		strSql.append(" AND a.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND a.create_user=b.id ");
		if (siteUserId != null && siteUserId.intValue() > 0) {
			strSql.append(" AND b.create_user = ? ");
			valueList.add(siteUserId.intValue());
		}
		Object[] values = valueList.toArray();
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(
					strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 站点管理员根据高级搜索条件查询会议列表(权限控制条件可以放在condition中)
	 * 
	 * @param condition
	 *            高级搜索条件
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式
	 * @param pageModel
	 *            分页信息
	 * 
	 */
	@Override
	public List<ConfBase> getConfListByCondition(SiteBase currentSite,
			Condition condition, String sortField, String sortord,
			PageModel pageModel) {
		List<ConfBase> confList = new ArrayList<ConfBase>();
		List<ConfBaseSimple> confBaseSimpleList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" SELECT id, conf_name, create_user, compere_user,compere_name ,conf_hwid, conf_type, start_time, end_time, ");
		strSql.append(" (CASE WHEN  conf.conf_status =6 THEN 0 WHEN conf.conf_status =5 THEN 2 WHEN conf.conf_status =7 THEN 3 ELSE conf.conf_status END) conf_status ");
		strSql.append(" from ( ");
		strSql.append("select * from (");
		strSql.append(" SELECT id, conf_name, create_user, compere_user,compere_name ,conf_hwid, conf_type, start_time, end_time,");
		strSql.append(" (CASE WHEN  tmp.conf_status =20 THEN 6 WHEN tmp.conf_status =200 THEN 5 WHEN tmp.conf_status =2000 THEN 7 ELSE tmp.conf_status END) conf_status ");
		strSql.append(" FROM ( ");
		strSql.append(" SELECT a.id, a.conf_name, a.create_user, a.compere_user,a.compere_name ,a.conf_hwid, a.conf_type, a.start_time, a.end_time,");
		strSql.append(" (CASE WHEN  a.conf_status =0 THEN 20 WHEN a.conf_status =2 THEN 200 WHEN a.conf_status =3 THEN 2000 ELSE a.conf_status END) conf_status ");
		strSql.append(" FROM  t_conf_base a, t_user_base b WHERE 1=1 ");
		if (condition != null
				&& StringUtil.isNotBlank(condition.getConditionSql())) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		strSql.append(" AND a.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND a.create_user=b.id ");
		strSql.append(" AND a.site_id = b.site_id ");
		strSql.append(" and a.site_id = ? ");
		valueList.add(currentSite.getId());
		strSql.append(" ) tmp ");
		strSql.append(" ) confBase ");
		strSql.append("ORDER BY confBase.conf_status ASC, confBase.start_time DESC, confBase.id ASC ");
		strSql.append(" ) conf ");
		if (StringUtil.isNotBlank(sortField)) {
			strSql.append(" order by ").append("conf.").append(sortField);
		}
		if (StringUtil.isNotBlank(sortField) && StringUtil.isNotBlank(sortord)
				&& "desc".equals(sortord.trim().toLowerCase())) {
			strSql.append(" DESC");
		}
		if (pageModel != null) {
			int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
					* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}

		try {
			confBaseSimpleList = libernate.getEntityListBase(
					ConfBaseSimple.class, strSql.toString(),
					valueList.toArray());
		} catch (SQLException e) {
			logger.error("站点管理员根据高级搜索条件查询会议列表(权限控制条件可以放在condition中)出错！" + e);
		}
		if (confBaseSimpleList != null && confBaseSimpleList.size() > 0) {
			String[] filedArray = ObjectUtil
					.getFieldFromObject(confBaseSimpleList.get(0));
			String copyField = "";
			for (String filed : filedArray) {
				if (!"confStatus".equals(filed)) {
					copyField = copyField + "," + filed;
				}
			}
			logger.info("copyField:" + copyField);
			for (ConfBaseSimple conf : confBaseSimpleList) {
				ConfBase confBase = new ConfBase();
				try {
					confBase = (ConfBase) ObjectUtil.copyObject(conf, confBase,
							copyField);
					confBase.setConfStatus(Integer.valueOf(conf.getConfStatus()
							.toString()));
				} catch (Exception e) {
					logger.error("转换conf出错！" + e);
				}
				confList.add(confBase);
			}
			confList = getOffsetConfList(currentSite, confList);
		}
		// try{
		// Object[] values = valueList.toArray();
		// confList = DAOProxy.getLibernate().getEntityListBase(ConfBase.class,
		// strSql.toString(), values);
		// if(confList != null && confList.size() > 0){
		// confList = getOffsetConfList(currentSite, confList);
		// }
		// }catch (Exception e){
		// e.printStackTrace();
		// }
		return confList;
	}

	/**
	 * 站点管理员根据高级搜索条件统计会议总数(权限控制条件可以放在condition中)
	 * 
	 * @param condition
	 *            高级搜索条件
	 * 
	 */
	@Override
	public int countConfListByCondition(Condition condition) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" SELECT count(1) FROM  t_conf_base a, t_user_base b WHERE 1=1 ");
		if (condition != null
				&& StringUtil.isNotBlank(condition.getConditionSql())) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		strSql.append(" AND a.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND a.create_user=b.id ");
		Object[] values = valueList.toArray();
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(
					strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfBase> getListForStatusMonitor(long period, int fetchSize) {
		// Date endDate = DateUtil.getGmtDateByAfterMs(period);
		Date currentDate = DateUtil.getGmtDate(null);
		List<ConfBase> confList = null;
		try {
			//查询预约成功   正在开始  结束时间查过规定范围的会议
			confList = DAOProxy
					.getLibernate()
					.getEntityListWithCondition(
							"conf_status in(?,?) and  permanent_conf = ? and end_time < ? and del_flag = ? ",
							new Object[] { ConfStatus.SCHEDULED.getStatus(),
									ConfStatus.LIVING.getStatus(),
									ConfType.PERMANENT_CONF_FALSE,
									new Date(currentDate.getTime() - period),
									ConstantUtil.DELFLAG_UNDELETE },
							ConfBase.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<ConfBase>) (confList == null ? Collections.emptyList()
				: confList);
	}

	@Override
	public boolean syncConfStatus(ConfBase conf) {
		
		try {
			UserBase host = userService.getUserBaseById(conf.getCompereUser());
			//add by Darren 2014-12-24
			DataCenter center = dataCenterService.queryDataCenterById(host.getDataCenterId());
			
			//说明是普通会议
			if(conf.getCycleId().equals(0) && !conf.isClientCycleConf() && conf.getPermanentConf().equals(ConfType.PERMANENT_CONF_FALSE)){
				//如果是已预约的会议 但是又没有开始则直接删除掉该会议
				if(conf.getConfStatus().equals(ConfStatus.SCHEDULED.getStatus())){
					if(conf.getActulStartTime() != null){
						conf.setConfStatus(ConfStatus.FINISHED.getStatus());
					}else{
						meetingOperationComponent.deleteMeeting(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(), host.getZoomId());
						conf.setDelTime(DateUtil.getGmtDate(null));
						conf.setDelUser(99999);
						conf.setDelFlag(ConstantUtil.DELFLAG_DELETED);
					}
					libernate.updateEntity(conf);
				//正在召开的状态
				}else{
					int status = meetingOperationComponent.getMeetingStatus(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(), host.getZoomId());
					//说明会议正在召开中
					if(status == ZoomConfStatus.CONF_RUNING){
						return true;
					}else{
//						meetingOperationComponent.endMeeting(conf.getConfZoomId(), host.getZoomId());
//						meetingOperationComponent.deleteMeeting(conf.getConfZoomId(), host.getZoomId());
						conf.setConfStatus(ConfStatus.FINISHED.getStatus());
						libernate.updateEntity(conf);
						return true;
					}
				}
				
			}else if(conf.isPortalCycleConf()){
				ConfCycle cycle = getConfCyclebyConfId(conf.getCycleId());
				//confLogic.cloneAndSaveConf(conf);
				if(conf.getConfStatus().equals(ConfStatus.SCHEDULED.getStatus())){
					//cycle.getOffsetConf(cycle, conf.getTimeZone());
					Date nextStartTime = confLogic.getNextConfStartTime(cycle,conf.getTimeZone());
					if(nextStartTime!=null){
						conf.setNextStartTime(nextStartTime);
						if(conf.getDuration()<1){
							conf.setDuration(60);
						}
						conf.setEndTime(new Date(conf.getStartTime().getTime()+conf.getDuration()*60000l));
					}else{
						conf.setConfStatus(ConfStatus.FINISHED.getStatus());
					}
					//ConfBase confBase = (ConfBase)conf.clone();
					libernate.updateEntity(conf);
				}else{//正在召开的状态
					
					int status = meetingOperationComponent.getMeetingStatus(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(), host.getZoomId());
					if(status == ZoomConfStatus.CONF_RUNING){
						return true;
					}else{
						//confLogic.cloneAndSaveConf(conf);
						//cycle.getOffsetConf(cycle, conf.getTimeZone());
						Date nextStartTime = confLogic.getNextConfStartTime(cycle,conf.getTimeZone());
						if(nextStartTime!=null){
							conf.setNextStartTime(nextStartTime);
							if(conf.getDuration()<1){
								conf.setDuration(60);
							}
							conf.setEndTime(new Date(conf.getStartTime().getTime()+conf.getDuration()*60000l));
							conf.setConfStatus(ConfStatus.SCHEDULED.getStatus());
						}else{
							conf.setConfStatus(ConfStatus.FINISHED.getStatus());
							meetingOperationComponent.deleteMeeting(center.getApiKey(),center.getApiToken(),conf.getConfZoomId(), host.getZoomId());
						}
						libernate.updateEntity(conf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	
	@Override
	public boolean updateConfStatusForNotify(ConfBase conf, int status) {
		return confLogic.updateConfStatus(conf, status);
	}

	public boolean updateConfActulTime(int confId, int status) {
		try {
			ConfBase conf = getConfBasebyConfId(confId);
			if (ConfConstant.CONF_STATUS_OPENING.equals(status)) {
				if(conf.isPmi() || conf.isClientCycleConf() || conf.getActulStartTime()==null){
					
					conf.setActulStartTime(DateUtil.getGmtDate(null));
					libernate.updateEntity(conf,"actulStartTime");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("update conf status error", e);
			return false;
		}
		return true;
	}

	 

	/**
	 * 测试用 shhc 2013-3-20
	 */
	public ConfBase createConf(ConfBase conf) {

		// check conf basic info here, including name,desc,start_time,duration
		// and etc.
		if (conf == null) {
			throw new IllegalArgumentException("conf can not be null.");
		}

		try {
			conf = DAOProxy.getLibernate().saveEntity(conf);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}

		return conf;
	}

	@Override
	public Integer countConfListByBaseCondition(String titleOrSiteSign,
			Integer sysUserId) {
		List<Object> valueList = new ArrayList<Object>();
		Integer confCount = 0;
		StringBuilder strSql = new StringBuilder();
		strSql.append(" SELECT count(1) ");
		strSql.append(" FROM ( ");
		strSql.append("     SELECT conf.id,conf.site_id,conf.cycle_id,conf.start_time,conf.end_time,conf.conf_type,conf.conf_name, ");
		strSql.append("     (CASE WHEN  conf.conf_status =0 THEN 20 WHEN conf.conf_status =2 THEN 10 WHEN conf.conf_status =3 THEN 30 ELSE conf.conf_status END) conf_status ");
		strSql.append("     FROM t_conf_base conf WHERE conf.id IN( ");
		strSql.append(" 	SELECT tmp.id FROM ( ");
		strSql.append(" 	SELECT tcb.`id`,tcb.start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` = ? AND del_flag = ? AND tcb.`conf_status` < ? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_CANCELED);
		strSql.append(" 	UNION ALL ");
		strSql.append(" 	SELECT tcb.`id`,MIN(tcb.`start_time`) AS start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND (tcb.`conf_status`=? OR tcb.`conf_status`=?) GROUP BY tcb.cycle_id ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		strSql.append(" 	UNION ALL     ");
		strSql.append(" 	SELECT tcb.`id`, start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND tcb.`conf_status`=? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		strSql.append("        ) tmp ");
		strSql.append("     )");
		// if(StringUtil.isNotBlank(titleOrSiteSign)){
		// strSql.append(" AND conf.conf_name LIKE ? ");
		// valueList.add("%" + titleOrSiteSign + "%");
		// }
		strSql.append("     ORDER BY conf.conf_status ASC,conf.start_time DESC,conf.id ASC ");
		strSql.append(" )confbase ,  t_site_base sitebase ");
		strSql.append(" WHERE confbase.site_id = sitebase.id AND sitebase.`del_flag` = ? AND (confbase.conf_name LIKE ? OR sitebase.`site_name` LIKE ? OR sitebase.site_sign LIKE ? )  ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add("%" + titleOrSiteSign + "%");
		valueList.add("%" + titleOrSiteSign + "%");
		valueList.add("%" + titleOrSiteSign + "%");
		if (sysUserId != null && sysUserId.intValue() > 0) {
			strSql.append(" AND sitebase.create_user = ?");
			valueList.add(sysUserId.intValue());
		}
		 
		try {
			confCount = libernate.countEntityListWithSql(strSql.toString(),
					valueList.toArray());
		} catch (SQLException e) {
			logger.error("系统管理员根据站点标识、会议主题或站点名称查询会议出错！" + e);
		}
		return confCount;
	}

	@Override
	public List<ConfBase> getConfListByBaseCondition(String titleOrSiteSign,
			String sortField, String sortord, PageModel pageModel,
			Integer sysUserId) {
		List<Object> valueList = new ArrayList<Object>();
		List<ConfBase> confList = new ArrayList<ConfBase>();
		List<ConfBaseSimple> confBaseSimpleList = null;
		StringBuilder strSql = new StringBuilder(); // 这里添加了pc_num 和phone_num字段
		strSql.append(" SELECT confbase.id,confbase.cycle_id,confbase.conf_name, confbase.site_id,confbase.conf_type,confbase.start_time,confbase.end_time, ");
		strSql.append(" (CASE WHEN  confbase.conf_status =20 THEN 2 WHEN confbase.conf_status =10 THEN 0 WHEN confbase.conf_status =30 THEN 3 ELSE confbase.conf_status END) as conf_status ");
		strSql.append(" FROM ( ");
		strSql.append("     SELECT conf.id,conf.site_id,conf.cycle_id,conf.start_time,conf.end_time,conf.conf_type,conf.conf_name, ");// martin
																																												// 添加了pc_num
																																												// and
																																												// phone_num
		strSql.append("     (CASE WHEN  conf.conf_status =0 THEN 20 WHEN conf.conf_status =2 THEN 10 WHEN conf.conf_status =3 THEN 30 ELSE conf.conf_status END) conf_status ");
		strSql.append("     FROM t_conf_base conf WHERE conf.id IN( ");
		strSql.append(" 	SELECT tmp.id FROM ( ");
		strSql.append(" 	SELECT tcb.`id`,tcb.start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` = ? AND del_flag = ? AND tcb.`conf_status` < ? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_CANCELED);
		strSql.append(" 	UNION ALL ");
		strSql.append(" 	SELECT tcb.`id`,MIN(tcb.`start_time`) AS start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND (tcb.`conf_status`=? OR tcb.`conf_status`=?) GROUP BY tcb.cycle_id ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		strSql.append(" 	UNION ALL     ");
		strSql.append(" 	SELECT tcb.`id`, start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND tcb.`conf_status`=? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		strSql.append("        ) tmp ");
		strSql.append("     )");
		// if(StringUtil.isNotBlank(titleOrSiteSign)){
		// strSql.append(" AND conf.conf_name LIKE ? ");
		// valueList.add("%" + titleOrSiteSign + "%");
		// }
		strSql.append("     ORDER BY conf.conf_status ASC,conf.start_time DESC,conf.id ASC ");
		strSql.append(" )confbase ,  t_site_base sitebase ");
		strSql.append(" WHERE confbase.site_id = sitebase.id  AND sitebase.`del_flag` = ?  AND (confbase.conf_name LIKE ? OR sitebase.`site_name` LIKE ? OR sitebase.site_sign LIKE ? ) ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add("%" + titleOrSiteSign + "%");
		valueList.add("%" + titleOrSiteSign + "%");
		valueList.add("%" + titleOrSiteSign + "%");
		if (sysUserId != null && sysUserId.intValue() > 0) {
			strSql.append(" AND sitebase.create_user = ?");
			valueList.add(sysUserId.intValue());
		}
		if (sortField != null && !"".equals(sortField.trim())
				&& !"null".equals(sortField.toLowerCase().trim())) {
			strSql.append(" order by ");
			for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
				if (eachField != null && eachField[0].equals(sortField)) {
					strSql.append("confbase.");
					strSql.append(BeanUtil.att2Field(eachField[1]));
					break;
				}
			}
			if (SortConstant.SORT_ASC.equals(sortord)) {
				strSql.append(" asc ");
			}

			if (SortConstant.SORT_DESC.equals(sortord) || sortord == null
					|| "".equals(sortord.trim())
					|| "null".equals(sortord.trim().toLowerCase())) {
				strSql.append(" desc ");
			}
		}
		if (pageModel != null) {
			strSql.append(" limit  ");
			strSql.append(" "
					+ ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel
							.getPageSize()));
			strSql.append(" , " + pageModel.getPageSize());
		}
		 
		try {
			confBaseSimpleList = libernate.getEntityListBase(
					ConfBaseSimple.class, strSql.toString(),
					valueList.toArray());
		} catch (SQLException e) {
			logger.error("根据站点标识、会议主题或站点名称查询会议出错！" + e);
		}
		if (confBaseSimpleList != null && confBaseSimpleList.size() > 0) {
			String[] filedArray = ObjectUtil
					.getFieldFromObject(confBaseSimpleList.get(0));
			String copyField = "";
			for (String filed : filedArray) {
				if (!"confStatus".equals(filed)) {
					copyField = copyField + "," + filed;
				}
			}
			logger.info("copyField:" + copyField);
			for (ConfBaseSimple conf : confBaseSimpleList) {
				ConfBase confBase = new ConfBase();
				try {
					confBase = (ConfBase) ObjectUtil.copyObject(conf, confBase,
							copyField);
					confBase.setConfStatus(Integer.valueOf(conf.getConfStatus()
							.toString()));
				} catch (Exception e) {
					logger.error("转换conf出错！" + e);
				}
				confList.add(confBase);
			}
		}
		return confList;
	}

	/**
	 * 系统管理员会议的高级搜索
	 * 
	 * @param siteName
	 * @param siteSign
	 * @param beginTime
	 *            会议开始时间开始于
	 * @param endTime
	 *            会议开始时间结束于
	 * @param sortField
	 * @param sortord
	 * @param pageModel
	 * @param sysUserId
	 *            系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	@Override
	public List<ConfBase> getConfListByAdvanceCondition(ConfBase confBase,
			String siteName, String siteSign, Date beginTime, Date endTime,
			String sortField, String sortord, PageModel pageModel,
			Integer sysUserId) {
		List<ConfBase> confBaseList = new ArrayList<ConfBase>();
		List<ConfBaseSimple> confBaseSimpleList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder strSql = new StringBuilder();
		strSql.append(" SELECT * from ( "); 
		strSql.append(" SELECT confbase.id,confbase.cycle_id,confbase.conf_name,confbase.site_id,confbase.conf_type,confbase.start_time,confbase.end_time, ");
		strSql.append(" (CASE WHEN  confbase.conf_status =20 THEN 2 WHEN confbase.conf_status =10 THEN 0 WHEN confbase.conf_status =30 THEN 3 ELSE confbase.conf_status END) conf_status ");
		strSql.append(" FROM ( ");
		strSql.append("     SELECT conf.id,conf.site_id,conf.cycle_id,conf.start_time,conf.end_time,conf.conf_type,conf.conf_name,");
		strSql.append("     (CASE WHEN  conf.conf_status =0 THEN 20 WHEN conf.conf_status =2 THEN 10 WHEN conf.conf_status =3 THEN 30 ELSE conf.conf_status END) conf_status ");
		strSql.append("     FROM t_conf_base conf WHERE conf.id IN( ");
		strSql.append(" 	SELECT tmp.id FROM ( ");
		strSql.append(" 	SELECT tcb.`id`,tcb.start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` = ? AND del_flag = ? AND tcb.`conf_status` < ? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_CANCELED);
		strSql.append(" 	UNION ALL ");
		strSql.append(" 	SELECT tcb.`id`,MIN(tcb.`start_time`) AS start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND (tcb.`conf_status`=? OR tcb.`conf_status`=?) GROUP BY tcb.cycle_id ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		strSql.append(" 	UNION ALL     ");
		strSql.append(" 	SELECT tcb.`id`, start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND tcb.`conf_status`=? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		strSql.append("        ) tmp ");
		strSql.append("     )");
		strSql.append("     ORDER BY conf.conf_status ASC,conf.start_time DESC,conf.id ASC ");
		strSql.append(" )confbase ,  t_site_base sitebase ");
		strSql.append(" WHERE confbase.site_id = sitebase.id AND sitebase.`del_flag` = ? AND confbase.conf_name LIKE ? AND sitebase.`site_name` LIKE ? AND sitebase.site_sign LIKE ?   ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add("%" + confBase.getConfName() + "%");
		valueList.add("%" + siteName + "%");
		valueList.add("%" + siteSign + "%");
		if (sysUserId != null && sysUserId.intValue() > 0) {
			strSql.append(" AND sitebase.create_user = ?");
			valueList.add(sysUserId.intValue());
		}
		if (beginTime != null) {
			beginTime = DateUtil.getGmtDate(beginTime);
			strSql.append(" AND confbase.start_time >= ? ");
			valueList.add(beginTime);
		}
		if (endTime != null) {
			endTime = DateUtil.getGmtDate(DateUtil.addDate(endTime, 1));
			strSql.append(" AND confbase.start_time <= ? ");
			valueList.add(endTime);
		}
		Integer confType = confBase.getConfType();
		Integer confStatus = confBase.getConfStatus();
		if (confType != null && confType.intValue() > 0) {
			strSql.append(" AND confbase.conf_type = ? ");
			valueList.add(confType);
		}
		strSql.append(" ) con ");
		if (confStatus != null && confStatus.intValue() >= 0
				&& confStatus.intValue() < 99) {
			strSql.append(" where con.conf_status = ? ");
			valueList.add(confStatus);
		}

		if (StringUtil.isNotBlank(sortField)) {
			strSql.append(" order by ").append(" con.").append(sortField);
			if (StringUtil.isNotBlank(sortField)
					&& StringUtil.isNotBlank(sortord)
					&& "desc".equals(sortord.trim().toLowerCase())) {
				strSql.append(" DESC");
			}
		}
		if (pageModel != null) {
			int recordNo = (Integer.parseInt(pageModel.getPageNo()) - 1)
					* pageModel.getPageSize(); // 当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		
		try {
			confBaseSimpleList = libernate.getEntityListBase(
					ConfBaseSimple.class, strSql.toString(),
					valueList.toArray());
		} catch (SQLException e) {
			logger.error("根据站点标识、会议主题或站点名称查询会议出错！" + e);
		}
		if (confBaseSimpleList != null && confBaseSimpleList.size() > 0) {
			String[] filedArray = ObjectUtil
					.getFieldFromObject(confBaseSimpleList.get(0));
			String copyField = "";
			for (String filed : filedArray) {
				if (!"confStatus".equals(filed)) {
					copyField = copyField + "," + filed;
				}
			}
			logger.info("copyField:" + copyField);
			for (ConfBaseSimple conf : confBaseSimpleList) {
				ConfBase confBaseObject = new ConfBase();
				try {
					confBaseObject = (ConfBase) ObjectUtil.copyObject(conf,
							confBaseObject, copyField);
					confBaseObject.setConfStatus(Integer.valueOf(conf
							.getConfStatus().toString()));
				} catch (Exception e) {
					logger.error("转换conf出错！" + e);
				}
				confBaseList.add(confBaseObject);
			}
		}
		return confBaseList;
	}

	/**
	 * 系统管理员下对会议高级搜索时统计会议总数
	 * 
	 * @param siteName
	 * @param siteSign
	 * @param beginTime
	 *            会议开始时间开始于
	 * @param endTime
	 *            会议开始时间结束于
	 * @param sysUserId
	 *            系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	@Override
	public Integer countConfListByAdvanceCondition(ConfBase confBase,
			String siteName, String siteSign, Date beginTime, Date endTime,
			Integer sysUserId) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder strSql = new StringBuilder();
		strSql.append(" SELECT count(1) from (");
		strSql.append(" SELECT confbase.id,confbase.cycle_id,confbase.conf_name,confbase.site_id,confbase.conf_type,confbase.start_time,confbase.end_time, ");
		strSql.append(" (CASE WHEN  confbase.conf_status =20 THEN 0 WHEN confbase.conf_status =10 THEN 2 WHEN confbase.conf_status =30 THEN 3 ELSE confbase.conf_status END) conf_status ");
		strSql.append(" FROM ( ");
		strSql.append("     SELECT conf.id,conf.site_id,conf.cycle_id,conf.start_time,conf.end_time,conf.conf_type,conf.conf_name, ");
		strSql.append("     (CASE WHEN  conf.conf_status =0 THEN 20 WHEN conf.conf_status =2 THEN 10 WHEN conf.conf_status =3 THEN 30 ELSE conf.conf_status END) conf_status ");
		strSql.append("     FROM t_conf_base conf WHERE conf.id IN( ");
		strSql.append(" 	SELECT tmp.id FROM ( ");
		strSql.append(" 	SELECT tcb.`id`,tcb.start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` = ? AND del_flag = ? AND tcb.`conf_status` < ? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_CANCELED);
		strSql.append(" 	UNION ALL ");
		strSql.append(" 	SELECT tcb.`id`,MIN(tcb.`start_time`) AS start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND (tcb.`conf_status`=? OR tcb.`conf_status`=?) GROUP BY tcb.cycle_id ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		strSql.append(" 	UNION ALL     ");
		strSql.append(" 	SELECT tcb.`id`, start_time FROM t_conf_base tcb WHERE tcb.`cycle_id` > ? AND tcb.del_flag = ? AND tcb.`conf_status`=? ");
		valueList.add(0);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		strSql.append("        ) tmp ");
		strSql.append("     )");
		// if(StringUtil.isNotBlank(titleOrSiteSign)){
		// strSql.append(" AND conf.conf_name LIKE ? ");
		// valueList.add("%" + titleOrSiteSign + "%");
		// }
		strSql.append("     ORDER BY conf.conf_status ASC,conf.start_time DESC,conf.id ASC ");
		strSql.append(" )confbase ,  t_site_base sitebase ");
		strSql.append(" WHERE confbase.site_id = sitebase.id AND sitebase.`del_flag` = ? AND confbase.conf_name LIKE ? AND sitebase.`site_name` LIKE ? AND sitebase.site_sign LIKE ?   ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add("%" + confBase.getConfName() + "%");
		valueList.add("%" + siteName + "%");
		valueList.add("%" + siteSign + "%");
		if (sysUserId != null && sysUserId.intValue() > 0) {
			strSql.append(" AND sitebase.create_user = ?");
			valueList.add(sysUserId.intValue());
		}
		if (beginTime != null) {
			beginTime = DateUtil.getGmtDate(beginTime);
			strSql.append(" AND confbase.start_time >= ? ");
			valueList.add(beginTime);
		}
		if (endTime != null) {
			endTime = DateUtil.getGmtDate(DateUtil.addDate(endTime, 1));
			strSql.append(" AND confbase.start_time <= ? ");
			valueList.add(endTime);
		}
		Integer confType = confBase.getConfType();
		Integer confStatus = confBase.getConfStatus();
		if (confType != null && confType.intValue() > 0) {
			strSql.append(" AND confbase.conf_type = ? ");
			valueList.add(confType);
		}
		strSql.append(" ) con ");
		if (confStatus != null && confStatus.intValue() >= 0
				&& confStatus.intValue() < 99) {
			strSql.append(" where con.conf_status = ? ");
			valueList.add(confStatus);
		}
		 
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(
					strSql.toString(), valueList.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<ConfUser> getConfUserListByConfId(Integer confId) {
		return null;
	}

	/**
	 * 根据会议cycleId号获取预约成功状态，并且通过华为接口获得了confHwid的会议
	 * 
	 * @param cycleId
	 *            周期会议ID号 2013-2-21
	 */
	// List<
	private List<ConfBase> getConfWithHwIdByCycleId(int cycleId) {
		List<ConfBase> confs = null;
		// ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE cycle_id = ? AND conf_status = ?  AND del_flag = ? ");//
		Object[] values = new Object[3];
		values[0] = cycleId;
		values[1] = ConfConstant.CONF_STATUS_SUCCESS;
		values[2] = ConstantUtil.DELFLAG_UNDELETE;
		
		try {
			// confBase = libernate.getEntityCustomized(ConfBase.class,
			// strSql.toString(), values);
			confs = libernate.getEntityListBase(ConfBase.class,
					strSql.toString(), values);
			if (confs == null)
				confs = new ArrayList<ConfBase>();
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return confs;
	}

	/**
	 * 根据会议ID号获取会议信息
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	@Override
	public ConfBase getConfBasebyConfId(int confId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND id = ? ");
		Object[] values = new Object[1];
		values[0] = confId;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 根据周期会议ID号获取周期会议周期信息
	 * 
	 * @param cycleId
	 *            周期会议ID号 2013-2-21
	 */
	public ConfCycle getConfCyclebyConfId(int cycleId) {
		ConfCycle confCycle = new ConfCycle();
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_cycle WHERE 1=1 AND id = ? ");
		Object[] values = new Object[] { cycleId };
		try {
			confCycle = libernate.getEntityCustomized(ConfCycle.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取周期会议周期信息出错！", e);
		}
		return confCycle;
	}

	/**
	 * 根据会议ID号获取会议信息(可获得日期为站点所设时区的会议信息)
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	@Override
	public ConfBase getConfBasebyConfId(int confId, SiteBase currentSite) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND id = ? ");
		Object[] values = new Object[1];
		values[0] = confId;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
			if (confBase != null && confBase.getId() != null
					&& confBase.getId().intValue() > 0) {
				confBase = getOffsetConf(currentSite, confBase);
			}
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 获取所有周期预约会议的日期
	 * 
	 * @param cycleId
	 *            循环会议ID号 2013-2-21
	 */
	@Override
	public List<ConfBase> getCycleConfDate(int cycleId, SiteBase currentSite) {
		List<ConfBase> confList = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? AND del_flag = ? ");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS, ConstantUtil.DELFLAG_UNDELETE };
		try {
			confList = libernate.getEntityListBase(ConfBase.class,
					strSql.toString(), values);
			if (confList != null && confList.size() > 0) {
				confList = getOffsetConfList(currentSite, confList);
			}
		} catch (SQLException e) {
			logger.error("根据循环会议ID号获取所有周期预约会议的日期出错！", e);
		}
		return confList;
	}

	/**
	 * 获取所有周期预约会议的日期
	 * 
	 * @param cycleId
	 *            循环会议ID号 2013-2-21
	 */
	private List<ConfBase> getCycleConfDate(int cycleId) {
		List<ConfBase> confList = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? AND del_flag = ? ");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS, ConstantUtil.DELFLAG_UNDELETE };
		try {
			confList = libernate.getEntityListBase(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据循环会议ID号获取所有周期预约会议的日期出错！", e);
		}
		return confList;
	}

	/**
	 * 根据会议ID号获取会议信息(可获得日期为用户喜好设置时区的会议信息)
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	@Override
	public ConfBase getConfBasebyConfId(int confId, UserBase currentUser) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND id = ? ");
		Object[] values = new Object[1];
		values[0] = confId;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
			if (confBase != null && confBase.getId() != null
					&& confBase.getId().intValue() > 0) {
				confBase = getOffsetConf(currentUser, confBase);
			}
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 获取用户喜好设置时区所有周期预约会议的日期
	 * 
	 * @param cycleId
	 *            循环会议ID号 2013-2-21
	 */
	@Override
	public List<ConfBase> getCycleConfDate(int cycleId, UserBase currentUser) {
		List<ConfBase> confList = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? AND del_flag = ? ");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS, ConstantUtil.DELFLAG_UNDELETE };
		try {
			confList = libernate.getEntityListBase(ConfBase.class,
					strSql.toString(), values);
			if (confList != null && confList.size() > 0) {
				confList = getOffsetConfList(currentUser, confList);
			}
		} catch (SQLException e) {
			logger.error("根据循环会议ID号获取所有周期预约会议的日期出错！", e);
		}
		return confList;
	}

	/**
	 * 根据cycleId号获取预约成功的会议信息(站点时区时间)
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	@Override
	public ConfBase getConfBasebyCycleId(int cycleId, SiteBase currentSite) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? ");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS.intValue() };
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
			if (confBase != null && confBase.getId() != null
					&& confBase.getId().intValue() > 0) {
				confBase = getOffsetConf(currentSite, confBase);
			}
		} catch (SQLException e) {
			logger.error("根据cycleId号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 根据cycleId号获取预约成功的会议信息(gmt时区时间)
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	@Override
	public ConfBase getConfBasebyCycleId(int cycleId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? ");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS.intValue() };
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据cycleId号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 根据cycleId号获取周期会议的最后一条记录
	 * 
	 * @param cycleId
	 *            周期ID号 2013-2-21
	 */
	@Override
	public ConfBase getLastConfBasebyCycleId(int cycleId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND conf_status = ? order by id desc limit 1");
		Object[] values = new Object[] { cycleId,
				ConfConstant.CONF_STATUS_SUCCESS.intValue() };
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据cycleId号获取会议信息出错！", e);
		}
		return confBase;
	}

	/**
	 * 通过会议ID获取邀请人信息
	 * 
	 * @param confId
	 *            会议ID号 2013-2-21
	 */
	public List<ConfUser> getConfInviteUser(int confId) {
		List<ConfUser> userList = null;// AND accept_flag != ?
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_user WHERE conf_id = ? AND host_flag = ?  ORDER BY id ASC ");
		Object[] values = new Object[] { confId,
				ConfConstant.CONF_USER_PARTICIPANT.intValue()
		// ConfConstant.CONF_USER_REFUSE.intValue()
		};
		try {
			userList = libernate.getEntityListBase(ConfUser.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("通过会议ID获取前几个邀请人信息出错！", e);
		}
		return userList;
	}

	@Override
	public Integer countConfUserByConfId(Integer confId) {
		if (confId == null) {
			return 0;
		}
		int count = 0;
		StringBuffer strSql = new StringBuffer(
				" SELECT count(*) FROM t_conf_user WHERE conf_id = ? AND host_flag = ?");
		Object[] values = new Object[] { confId,
				ConfConstant.CONF_USER_PARTICIPANT.intValue() };
		try {
			count = libernate.countEntityListWithSql(strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("统计邀请人数", e);
		}
		return count;
	}

	@Override
	public List<ConfUser> getConfUserListByCycleId(Integer cycleId) {
		return null;
	}

	@Override
	public Integer countConfUserByCycleId(Integer cycleId) {
		return null;
	}

	@Override
	public List<ConfUserCount> countConfUserByConfIds(Integer[] confIds) {
		List<ConfUserCount> confUserCount = null;
		if (confIds != null && confIds.length > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			List<Object> valueList = new ArrayList<Object>();
			sqlBuffer
					.append("select  conf_id,COUNT(id) as user_count from t_conf_user where conf_id > 0 and del_flag = ? ");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			sqlBuffer.append(" and  (");
			int ii = 0;
			for (Integer confId : confIds) {
				if (ii > 0) {
					sqlBuffer.append(" or ");
				}
				sqlBuffer.append(" conf_id = ?");
				valueList.add(confId);
				ii++;
			}
			sqlBuffer.append(" )");
			sqlBuffer.append(" group by conf_id ");
			Object[] values = new Object[valueList.size()];
			for (ii = 0; ii < valueList.size(); ii++) {
				values[ii] = valueList.get(ii);
			}

			try {
				confUserCount = libernate.getEntityListBase(
						ConfUserCount.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return confUserCount;
	}

	@Override
	public List<ConfUserCount> countConfUserByCycleIds(Integer[] cycleIds) {
		List<ConfUserCount> confUserCount = null;
		if (cycleIds != null && cycleIds.length > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			List<Object> valueList = new ArrayList<Object>();
			sqlBuffer
					.append("select cycle_id,COUNT(id) as user_count from t_conf_user where cycle_id > 0 and del_flag = ? ");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			sqlBuffer.append(" and  (");
			int ii = 0;
			for (Integer cycleId : cycleIds) {
				if (ii > 0) {
					sqlBuffer.append(" or ");
				}
				sqlBuffer.append(" cycle_id = ?");
				valueList.add(cycleId);
				ii++;
			}
			sqlBuffer.append(" )");// cycleId cycle_id
			sqlBuffer.append(" group by cycle_id  ");
			Object[] values = new Object[valueList.size()];
			for (ii = 0; ii < valueList.size(); ii++) {
				values[ii] = valueList.get(ii);
			}
			logger.info("sqlBuffer sql ==" + sqlBuffer.toString());
			try {
				confUserCount = libernate.getEntityListBase(
						ConfUserCount.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return confUserCount;
	}

	/**
	 * 获取排序参数 wangyong 2013-1-22
	 */
	private String initSort(String field) {
		String sortField = SortConstant.CONFBASE_FIELDS[0][1];
		for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
			if (eachField != null && eachField[0].equals(field)) {
				sortField = BeanUtil.att2Field(eachField[1]);
				break;
			}
		}
		return sortField;
	}

	/**
	 * 站点用户获取当前站点时区的会议 wangyong 2013-3-8
	 */
	private ConfBase getOffsetConf(SiteBase currentSite, ConfBase conf) {
		if (conf != null) {
			String[] fields = new String[] { "startTime", "endTime" };
			long offset = 0;
			if (currentSite != null) {
				offset = currentSite.getTimeZone();
			} else {
				offset = DateUtil.getDateOffset();
			}
			logger.info("当前访问的站点时区" + offset);
			conf = (ConfBase) ObjectUtil.offsetDate(conf, offset, fields);
		}
		return conf;
	}

	/**
	 * 站点用户获取当前站点时区的会议列表 wangyong 2013-3-8
	 */
	@SuppressWarnings("unchecked")
	private List<ConfBase> getOffsetConfList(SiteBase currentSite,
			List<ConfBase> confList) {
		List<ConfBase> confBaseList = new ArrayList<ConfBase>();
		String[] fields = new String[] { "startTime", "endTime" };
		long offset = 0;
		if (currentSite != null) {
			offset = currentSite.getTimeZone();
		} else {
			offset = DateUtil.getDateOffset();
		}
		logger.info("当前访问的站点时区" + offset);
		confBaseList = ObjectUtil.offsetDateWithList(confList, offset, fields);
		return confBaseList;
	}

	/**
	 * 站点用户获取用户喜好设置时区的会议 wangyong 2013-3-8
	 */
	private ConfBase getOffsetConf(UserBase currentUser, ConfBase conf) {
		if (conf != null) {
			String[] fields = new String[] { "startTime", "endTime" };
			long offset = 0;
			if (currentUser != null) {
				offset = currentUser.getTimeZone();
			} else {
				offset = DateUtil.getDateOffset();
			}
			logger.info("当前访问的站点时区" + offset);
			conf = (ConfBase) ObjectUtil.offsetDate(conf, offset, fields);
		}
		return conf;
	}

	/**
	 * 站点用户获取用户喜好设置时区的会议列表 wangyong 2013-3-8
	 */
	@SuppressWarnings("unchecked")
	private List<ConfBase> getOffsetConfList(UserBase currentUser,
			List<ConfBase> confList) {
		List<ConfBase> confBaseList = new ArrayList<ConfBase>();
		String[] fields = new String[] { "startTime", "endTime" };
		long offset = 0;
		if (currentUser != null) {
			offset = currentUser.getTimeZone();
		} else {
			offset = DateUtil.getDateOffset();
		}
		logger.info("当前访问的站点时区" + offset);
		confBaseList = ObjectUtil.offsetDateWithList(confList, offset, fields);
		return confBaseList;
	}

	@Override
	public boolean saveConfUser(ConfUser confUser) {
		try {
			libernate.saveEntity(confUser);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 更新企业用户会议设置 wangyong 2013-3-20
	 */
	@Override
	public boolean updateConfConfig(DefaultConfig confConfig) {
		try {
			libernate.updateEntity(confConfig);
		} catch (Exception e) {
			logger.error("更新企业用户会议设置" + e);
			return false;
		}
		return true;
	}

	/**
	 * 当前登录用户是否有权限修改一个预约会议
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentUser
	 *            当前登录用户 wangyong 2013.3.6
	 */
	@Override
	public boolean updateConfAuthority(Integer confId, UserBase currentUser) {
		boolean authorityFlag = false;
		Integer createConfUser = null;
		ConfBase conf = getConfBasebyConfId(confId);
		if (conf != null && conf.getCreateUser() != null) {
			createConfUser = conf.getCreateUser();
		}
		Integer currentUserId = currentUser.getId();
		Integer confStatus = conf.getConfStatus();
		if (createConfUser != null && currentUserId != null
				&& createConfUser.intValue() == currentUserId.intValue()) { // 自己创建的会议可以修改
			if (confStatus != null
					&& confStatus.intValue() != ConfConstant.CONF_STATUS_OPENING
							.intValue()) { // 正在进行的会议不可修改
				authorityFlag = true;
			}
		}
		return authorityFlag;
	}

	/**
	 * 当前登录用户是否有权限重新创建一个预约会议
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentUser
	 *            当前登录用户 wangyong 2013.3.6
	 */
	@Override
	public boolean recreateConfAuthority(Integer confId, UserBase currentUser) {
		boolean authorityFlag = false;
		Integer createConfUser = null;
		ConfBase conf = getConfBasebyConfId(confId);
		if (conf != null && conf.getCreateUser() != null) {
			createConfUser = conf.getCreateUser();
		}
		Integer currentUserId = currentUser.getId();
		Integer confStatus = conf.getConfStatus();
		if (createConfUser != null && currentUserId != null
				&& createConfUser.intValue() == currentUserId.intValue()) { // 自己创建的会议可以修改
			if (confStatus != null
					&& confStatus.intValue() != ConfConstant.CONF_STATUS_OPENING
							.intValue()) { // 正在进行的会议不可修改
				authorityFlag = true;
			}
		}
		return authorityFlag;
	}

	/**
	 * 当前登录用户是否有权限取消一个预约会议
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentUser
	 *            当前登录用户 wangyong 2013.3.6
	 */
	@Override
	public boolean cancleConfAuthority(Integer confId, UserBase currentUser) {
		boolean authorityFlag = false;
		Integer createConfUser = null;
		ConfBase conf = getConfBasebyConfId(confId);
		if (conf != null && conf.getCreateUser() != null) {
			createConfUser = conf.getCreateUser();
		}
		Integer currentUserId = currentUser.getId();
		Integer confStatus = conf.getConfStatus();
		if (createConfUser != null && currentUserId != null
				&& createConfUser.intValue() == currentUserId.intValue()) { // 自己创建的会议可以删除
			if (ConfConstant.CONF_PERMANENT_UNABLE.equals(conf
					.getPermanentConf())) {
				// 原需求不能取消永久会议
				// if(confStatus != null && confStatus.intValue() !=
				// ConfConstant.CONF_STATUS_OPENING.intValue() ){ //正在进行的会议不可删除
				// authorityFlag = true;
				// }

				authorityFlag = true;

			} else if (ConfConstant.CONF_PERMANENT_ENABLED_MAIN.equals(conf
					.getPermanentConf())) {
				authorityFlag = true;
			}

		}
		return authorityFlag;
	}

	/**
	 * 当前登录用户是否有权限邀请参会人
	 * 
	 * @param conf
	 *            会议基本信息
	 * @param currentUser
	 *            当前登录用户 wangyong 2013.3.6
	 */
	@Override
	public boolean inviteConfAuthority(Integer confId, UserBase currentUser) {
		boolean authorityFlag = false;
		Integer createConfUser = null;
		ConfBase conf = getConfBasebyConfId(confId);
		if (conf != null && conf.getCreateUser() != null) {
			createConfUser = conf.getCreateUser();
		}
		Integer currentUserId = currentUser.getId();
		Integer confStatus = conf.getConfStatus();
		if (createConfUser != null && currentUserId != null
				&& createConfUser.intValue() == currentUserId.intValue()) { // 自己创建的会议可以邀请
			if (confStatus != null
					&& confStatus.intValue() == ConfConstant.CONF_STATUS_SUCCESS
							.intValue()) { // 预约成功的会议可以邀请
				authorityFlag = true;
			}
		}
		return authorityFlag;
	}

	/**
	 * 获取企业用户默认会议设置 wangyong 2013-3-20
	 */
	@Override
	public DefaultConfig getDefaultConfig(UserBase currentUser) {
		DefaultConfig mtgParam = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_default_config WHERE 1=1 AND site_id = ? AND user_id = ? ");
		Object[] values = new Object[2];
		values[0] = currentUser.getSiteId();
		values[1] = currentUser.getId();
		try {
			mtgParam = libernate.getEntityCustomized(DefaultConfig.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("获取会议参数设置出错！", e);
		}
		return mtgParam;
	}

	 
 


	 

	public ConfBase saveConfBaseForOuter(ConfBase confBase, SiteBase siteBase,
			UserBase userBase) {
		try {
				confBase = libernate.saveEntity(confBase);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confBase;
	}

	/**
	 * 获取周期会议的会议列表
	 */
	@Override
	public List<ConfBase> getCycConfBases(int cycId) {
		List<ConfBase> confs = new ArrayList<ConfBase>();
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE 1=1 AND cycle_id = ? AND del_flag = ? AND (conf_status = ? OR conf_status=?) ");
		Object[] values = new Object[] { cycId, ConstantUtil.DELFLAG_UNDELETE,
				ConfConstant.CONF_STATUS_SUCCESS,
				ConfConstant.CONF_STATUS_OPENING };

		try {
			confs = libernate.getEntityListBase(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据cycleId号获取会议信息出错！", e);
		}
		return confs;
	}

	@Override
	public List<ConfBase> getConfListForTask() {
		List<ConfBase> confList = null;
		Date nowGmtDate = DateUtil.getGmtDate(null);
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT  *  FROM t_conf_base tcb ");
		sqlBuffer.append("	WHERE");
		sqlBuffer.append("		tcb.`del_flag` = 1 ");
		sqlBuffer.append("		and (");
		sqlBuffer.append("				tcb.`conf_status` = 2 ");
		sqlBuffer.append("				or ");
		sqlBuffer.append("				(");
		sqlBuffer.append("					tcb.`conf_status` = 1 ");
		sqlBuffer
				.append("				AND DATE_ADD( tcb.`start_time`, INTERVAL - 1 * tcb.`ahead_time` MINUTE) <= ?");
		sqlBuffer.append("				)");
		sqlBuffer.append("		)");
		sqlBuffer.append("");
		Object[] values = new Object[] { nowGmtDate };
		try {
			confList = libernate.getEntityListBase(ConfBase.class,
					sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return confList;
	}

	@Override
	public boolean updateStartTime(ConfBase confBase, Date startTime) {
		if (confBase != null) {
			if (startTime == null) {
				startTime = DateUtil.getGmtDate(null);
			}
			confBase.setStartTime(startTime);
			try {
				confBase = libernate.updateEntity(confBase, "id", "startTime");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			// 更新 Conf user表记录
			confUserService.updateStartTime(confBase.getId(), startTime);
		}
		return true;
	}

	@Override
	public boolean updateEndTime(ConfBase confBase, Date endTime) {
		if (confBase != null) {
			if (endTime == null) {
				endTime = DateUtil.getGmtDate(null);
			}
			Date startTime = confBase.getStartTime();
			if (startTime.before(endTime)) {
				long duration = DateUtil.dateDiff(startTime, endTime);
				confBase.setEndTime(endTime);
				confBase.setDuration(IntegerUtil.parseInteger("" + duration));
				try {
					confBase = libernate.updateEntity(confBase, "id",
							"endTime", "duration");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			startTime = null;
		}
		return true;
	}

	/**
	 * 根据AS的记录同步结束时间 用于portal显示
	 * 
	 * @param confBase
	 *            bizconf数据库中的会议记录
	 * @param confFromAs
	 *            as查询出来的会议信息
	 * @return
	 */
	public boolean updateEndTime(ConfBase confBase, ConfBase confFromAs) {
		if (confBase != null) {
			Date startTime = confBase.getStartTime();
			Date endTime = DateUtil.getGmtDate(null);// 默认情况下为当前时间
			if (confFromAs != null) {
				Date asBegin = confFromAs.getStartTime();
				Date asEnd = confFromAs.getEndTime();
				if (asEnd.after(asBegin)) {

					long gap = asEnd.getTime() - asBegin.getTime();

					endTime = new Date(startTime.getTime() + gap);
				}
			}

			if (startTime.after(endTime)) { // 如果系统当前时间小于数据库中的会议开始时间
				endTime = new Date(startTime.getTime() + 60 * 1000L);// 算一分钟吧
			}

			long duration = DateUtil.dateDiff(startTime, endTime);
			confBase.setEndTime(endTime);
			confBase.setDuration(IntegerUtil.parseInteger("" + duration));
			try {
				confBase = libernate.updateEntity(confBase, "id", "endTime",
						"duration");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			startTime = null;
		}
		return true;
	}

	@Override
	public ConfBase getConfBaseByHwId(String hwId) {

		if (!StringUtil.isEmpty(hwId)) {
			try {
				return libernate.getEntity(ConfBase.class, "conf_hwid", hwId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}



	 
	public static void main(String[] args) throws Exception {
		
//		ConfBase conf = new ConfBase();
//		
//		conf.setConfName("测试克隆");
//		
//		try {
//			ConfBase cloneConf = (ConfBase) conf.clone();
//			System.out.println(" 我是克隆会议 ："+cloneConf.getConfName());
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * 查询进行中的会议和已经结束会议参会人信息
	 */
	@Override
	public PageBean<ConfLog> getConflogsByConf(Integer confId, Integer pageNo,
			Integer pageSize) {
		String sql = "select * from t_conf_log where conf_id = ?";
		return getPageBeans(ConfLog.class, sql, pageNo, pageSize,
				new Object[] { confId });
	}

	@Override
	public int getTerminalNum(Integer confId, Integer terminalType) {

		List<Object> values = new ArrayList<Object>();
		// String sql =
		// "select count(distinct user_name) from t_conf_log where conf_id = ? ";
		// //去重
		String sql = "select count(*) from t_conf_report_info where conf_log_id in (select id from t_zoom_conf_record where conf_id = ? ) ";
		int count = 0;
		try {
			if (confId == null) {
				throw new RuntimeException("the confId can not be null!");
			}
			values.add(confId);
			if (terminalType != null && terminalType.intValue() > 0) {
				// sql += " and term_type=? ";
				// values.add(terminalType);
			}
			count = libernate.countEntityListWithSql(sql, values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Map<Integer, Integer> getConfsTerminalNums(
			Collection<ConfBase> confs, Integer terminalType) {

		Map<Integer, Integer> datas = new HashMap<Integer, Integer>();
		if (confs != null && confs.size() > 0) {
			for (Iterator<ConfBase> itr = confs.iterator(); itr.hasNext();) {
				ConfBase conf = itr.next();
				datas.put(conf.getId(),
						getTerminalNum(conf.getId(), terminalType));
			}
		}
		return datas;
	}

	@Override
	public PageBean<ConfBase> getDailyOpeningConfListForHost(String confName,
			UserBase userBase, Integer pageNo) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_OPENING,
				null, null, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getDailyComingConfListForHost(String confName,
			UserBase userBase, Integer pageNo) {
		Date startDate = DateUtil.getTodayStartDate(userBase.getTimeZone());
		Date endDate = DateUtil.getTodayEndDate(startDate);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_SUCCESS,
				startDate, endDate, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getDailyJoinedConfListForHost(String confName,
			UserBase userBase, Integer pageNo) {

		Date startDate = DateUtil.getTodayStartDate(userBase.getTimeZone());
		Date endDate = DateUtil.getTodayEndDate(startDate);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_FINISHED,
				startDate, endDate, pageNo, true);
	}

	@Override
	public PageBean<ConfBase> getWeeklyComingConfListForHost(String confName,
			UserBase userBase, Integer pageNo) {

		Date startDate = DateUtil.getWeekStartDate(userBase.getTimeZone());
		Date endDate = DateUtil.getWeekEndDate(startDate);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_SUCCESS,
				startDate, endDate, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getWeeklyJoinedConfListForHost(String confName,
			UserBase userBase, Integer pageNo) {
		Date startDate = DateUtil.getWeekStartDate(userBase.getTimeZone());
		Date endDate = DateUtil.getWeekEndDate(startDate);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_FINISHED,
				startDate, endDate, pageNo, true);
	}

	@Override
	public PageBean<ConfBase> getMonthlyComingConfListForHost(String confName,
			UserBase userBase, Integer pageNo, Date beginTime, Date endTime) {
		Date monthBeginTime = DateUtil
				.getMonthStartDate(userBase.getTimeZone());
		Date monthEndTime = DateUtil.getMonthEndDate(monthBeginTime);
		// 如果没有传入时间，或者传入时间范围超出本月
		if (beginTime == null || beginTime.before(monthBeginTime)) {
			beginTime = monthBeginTime;
		}
		if (endTime == null || endTime.after(monthEndTime)) {
			endTime = monthEndTime;
		}
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_SUCCESS,
				beginTime, endTime, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getMonthlyJoinedConfListForHost(String confName,
			UserBase userBase, Integer pageNo, Date beginTime, Date endTime) {
		Date monthBeginTime = DateUtil
				.getMonthStartDate(userBase.getTimeZone());
		Date monthEndTime = DateUtil.getMonthEndDate(monthBeginTime);
		// 如果没有传入时间，或者传入时间范围超出本月
		if (beginTime == null || beginTime.before(monthBeginTime)) {
			beginTime = monthBeginTime;
		}
		if (endTime == null || endTime.after(monthEndTime)) {
			endTime = monthEndTime;
		}

		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_FINISHED,
				beginTime, endTime, pageNo, true);
	}

	@Override
	public PageBean<ConfBase> getFullOpeningConfListForHost(String confName,
			UserBase userBase, Integer pageNo, Date beginTime, Date endTime) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_OPENING,
				beginTime, endTime, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getFullComingConfListForHost(String confName,
			UserBase userBase, Integer pageNo, Date beginTime, Date endTime) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_SUCCESS,
				beginTime, endTime, pageNo, false);
	}

	@Override
	public PageBean<ConfBase> getFullJoinedConfListForHost(String confName,
			UserBase userBase, Integer pageNo, Date beginTime, Date endTime) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_HOST, ConfConstant.CONF_STATUS_FINISHED,
				beginTime, endTime, pageNo, true);
	}

	/**
	 * 我参与的会议 获取今天正在召开的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getDailyOpeningConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_OPENING, null, null, pageNo, false);
	}

	/**
	 * 我参与的会议 获取今天即将开始的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getDailyComingConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		Date beginTime = DateUtil.getTodayStartDate(userBase.getTimeZone());
		Date endTime = DateUtil.getTodayEndDate(beginTime);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_SUCCESS, beginTime, endTime, pageNo,
				false);
	}

	/**
	 * 我参与的会议 获取今天已经加入过的的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getDailyJoinedConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		Date beginTime = DateUtil.getTodayStartDate(userBase.getTimeZone());
		Date endTime = DateUtil.getTodayEndDate(beginTime);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_FINISHED, beginTime, endTime, pageNo,
				true);
	}

	/**
	 * 我参与的会议 获取本周的正在召开的会议
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getWeeklyOpeningConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_OPENING, null, null, pageNo, false);
	}

	/**
	 * 我参与的会议 获取本周的即将开始的会议
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getWeeklyComingConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		Date beginTime = DateUtil.getWeekStartDate(userBase.getTimeZone());
		Date endTime = DateUtil.getWeekEndDate(beginTime);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_SUCCESS, beginTime, endTime, pageNo,
				false);
	}

	/**
	 * 我参与的会议 获取本周的已经加入过的会议
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getWeeklyJoinedConfListForActor(String confName,
			UserBase userBase, int pageNo) {
		Date beginTime = DateUtil.getWeekStartDate(userBase.getTimeZone());
		Date endTime = DateUtil.getWeekEndDate(beginTime);
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_FINISHED, beginTime, endTime, pageNo,
				true);
	}

	/**
	 * 我参与的会议 取本月的即将开始的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getMonthlyComingConfListForActor(String confName,
			UserBase userBase, int pageNo, Date beginTime, Date endTime) {

		Date monthBeginTime = DateUtil
				.getMonthStartDate(userBase.getTimeZone());
		Date monthEndTime = DateUtil.getMonthEndDate(monthBeginTime);
		// 如果没有传入时间，或者传入时间范围超出本月
		if (beginTime == null || beginTime.before(monthBeginTime)) {
			beginTime = monthBeginTime;
		}
		if (endTime == null || endTime.after(monthEndTime)) {
			endTime = monthEndTime;
		}
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_SUCCESS, beginTime, endTime, pageNo,
				false);
	}

	/**
	 * 我参与的会议 取本月的已经加入过的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getMonthlyJoinedConfListForActor(String confName,
			UserBase userBase, int pageNo, Date beginTime, Date endTime) {
		Date monthBeginTime = DateUtil
				.getMonthStartDate(userBase.getTimeZone());
		Date monthEndTime = DateUtil.getMonthEndDate(monthBeginTime);
		// 如果没有传入时间，或者传入时间范围超出本月
		if (beginTime == null || beginTime.before(monthBeginTime)) {
			beginTime = monthBeginTime;
		}
		if (endTime == null || endTime.after(monthEndTime)) {
			endTime = monthEndTime;
		}
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_FINISHED, beginTime, endTime, pageNo,
				true);
	}

	/**
	 * 我参与的会议 所有的正在开始的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullOpeningConfListForActor(String confName,
			UserBase userBase, int pageNo, Date beginTime, Date endTime) {
		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_OPENING, beginTime, endTime, pageNo,
				false);
	}

	/**
	 * 我参与的会议 所有的即将开始的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getFullComingConfListForActor(String confName,
			UserBase userBase, int pageNo, Date beginTime, Date endTime) {

		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_SUCCESS, beginTime, endTime, pageNo,
				false);

	}

	/**
	 * 我参与的会议 所有的加入过的会议列表
	 * 
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getFullJoinedConfListForActor(String confName,
			UserBase userBase, int pageNo, Date beginTime, Date endTime) {

		return getConfListForCurrentUser(confName, userBase,
				ConfConstant.CONF_USER_PARTICIPANT,
				ConfConstant.CONF_STATUS_FINISHED, beginTime, endTime, pageNo,
				true);

	}


	private PageBean<ConfBase> getConfListForCurrentUser(String confName,
			UserBase userBase, int hostFlag, int confStatus, Date beginDate,
			Date endDate, int pageNo, boolean joinFlag) {
		logger.info("will getConfListForCurrentUser the conf status==== "
				+ confStatus);
		long starttime = System.currentTimeMillis();
		logger.info("getConfListForCurrentUser:  beginDate-->>" + beginDate
				+ "; endDate-->>" + endDate);
		if (userBase == null) {
			return null;
		}
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		if (joinFlag) {// 查找已经参加的

			if (ConfConstant.CONF_USER_HOST.equals(hostFlag)) {// 自己主持的会议
				sqlBuffer
						.append(" SELECT distinct tcb.* FROM t_conf_base tcb  ");// ,t_conf_log
																					// tcl
				sqlBuffer.append(" WHERE tcb.`del_flag`=?  ");// AND
																// tcl.`conf_id`=tcb.`id`
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				sqlBuffer.append(" AND tcb.`conf_status`=? ");
				valueList.add(confStatus);
				sqlBuffer.append(" AND tcb.`compere_user`=? ");
				valueList.add(userBase.getId());
				if (!StringUtil.isEmpty(confName)) {
					sqlBuffer.append(" AND tcb.conf_name like ?");
					valueList.add("%" + confName.trim() + "%");
				}
				if (beginDate != null) {
					sqlBuffer.append(" AND tcb.end_time >= ?");
					valueList.add(beginDate);
				}
				if (endDate != null) {
					sqlBuffer.append(" AND tcb.end_time < ?");
					valueList.add(endDate);
				}
				sqlBuffer.append(" ORDER BY tcb.`end_time` DESC");

			}
		} else {// 查找预约成功 + 正在召开的会议
			if (ConfConstant.CONF_USER_HOST.equals(hostFlag)) {// 自己主持的会议
				logger.info("我主持的的会议的confStatus==" + confStatus);
				logger.info("我主持的会议的beginDate==" + beginDate);
				logger.info("我主持的会议的endDate==" + endDate);
				sqlBuffer.append(" SELECT tcb.* FROM t_conf_base tcb ");
				sqlBuffer.append(" WHERE tcb.`del_flag`=? ");
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				sqlBuffer.append(" AND tcb.`compere_user`=?");
				valueList.add(userBase.getId());

				// 会议状态条件开始
				if (ConfConstant.CONF_STATUS_SUCCESS.equals(confStatus)) {
					sqlBuffer
							.append(" AND tcb.`conf_status`=?");
					valueList.add(confStatus);
					
					sqlBuffer
					.append(" AND NOT (tcb.`conf_type` = ? and  tcb.create_type = ?)");
					
					valueList.add(ConfType.RECURR);
					valueList.add(ConfType.CREATE_TYPE_CLIENT);
					
				} else if (ConfConstant.CONF_STATUS_OPENING.equals(confStatus)) {
					sqlBuffer.append(" AND tcb.`conf_status`=? ");
					valueList.add(confStatus);
				}
				// 会议状态条件结束

				sqlBuffer.append(" AND tcb.`permanent_conf`< ? ");
				valueList.add(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);

				if (!StringUtil.isEmpty(confName)) {
					sqlBuffer.append(" AND tcb.conf_name like ?");
					valueList.add("%" + confName.trim() + "%");
				}
				if (ConfConstant.CONF_STATUS_SUCCESS.equals(confStatus)) {// 即将开始的会议
					if (beginDate != null) {
						sqlBuffer.append(" AND tcb.start_time >= ?");
						valueList.add(beginDate);
					}
					if (endDate != null) {
						sqlBuffer.append(" AND tcb.start_time < ?");
						valueList.add(endDate);
					}
				}

				sqlBuffer
						.append(" ORDER BY tcb.`start_time` ASC ");

			}
		}
		logger.info("sqlBuffer==" + sqlBuffer.toString());

		// PageBean<ConfBase> page = getPageBeans(ConfBase.class,
		// sqlBuffer.toString(), pageNo, valueList.toArray());
		PageBean<ConfBase> page = getPageBeans(ConfBase.class,
				sqlBuffer.toString(), pageNo, userBase.getPageSize(), true,
				valueList.toArray()); // 按用户偏好设置每页显示条数
		if (page != null && page.getDatas() != null
				&& page.getDatas().size() > 0) {
			page.setDatas(getOffsetConfList(userBase, page.getDatas()));//按用户偏好设置
		}

		long endtime = System.currentTimeMillis();
		logger.info("getConfListForCurrentUser total use time = "
				+ (endtime - starttime));
		return page;
	}

	@Override
	public PageBean<ConfBase> getConfBasePage(int pageNo, int pageSize,
			UserBase user, Date startTime, Date endTime, String theme,
			boolean isCreator) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" SELECT * FROM t_conf_base tcb WHERE 1=1 AND tcb.conf_status = ? AND tcb.del_flag = ? and tcb.permanent_conf != ? ");
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);// 不显示主会议

		if (startTime != null && endTime != null) {
			sqlBuilder.append(" and tcb.start_time between ? and ? ");
			valueList.add(startTime);
			valueList.add(endTime);
		}

		if (theme != null) {
			theme = StringUtil.escapeStr(theme.trim());
			sqlBuilder.append(" and tcb.conf_name like ? ");
			valueList.add("%" + theme + "%");
		}

		if (!isCreator) {
			sqlBuilder.append(" and tcb.compere_user != ? ");
			valueList.add(user.getId());
			sqlBuilder.append(" AND EXISTS ( ");
			sqlBuilder.append(" 	SELECT * FROM  t_conf_log tcl  ");
			sqlBuilder
					.append(" 	WHERE tcl.user_role = ? and tcl.user_id =? and  tcb.id = tcl.conf_id )");
			valueList.add(ConfConstant.CONF_USER_PARTICIPANT);
			valueList.add(user.getId());
		} else {
			// @TODO if table conf_log 's data be maintained we can user query
			// sql like that:
			// sqlBuilder.append(" AND EXISTS ( ");
			// sqlBuilder.append(" 	SELECT * FROM  t_conf_log tcl ");
			// sqlBuilder.append(" 	WHERE tcl.user_id= ? AND tcl.user_role = ?  AND  tcl.conf_id = tcb.id )");
			// valueList.add(user.getId());
			// valueList.add(ConfConstant.CONF_USER_HOST);

			// 现用
			sqlBuilder.append(" AND  tcb.compere_user = ? ");
			sqlBuilder.append(" AND EXISTS ( ");
			sqlBuilder.append(" 	SELECT * FROM  t_conf_log tcl ");
			sqlBuilder.append(" 	WHERE  tcl.conf_id = tcb.id )");
			valueList.add(user.getId());
		}
		sqlBuilder.append("  ORDER BY tcb.start_time desc ");
		return getPageBeans(ConfBase.class, sqlBuilder.toString(), pageNo,
				pageSize, valueList.toArray());
	}


	 
	@Override
	public ConfBase getPermanentChildConf(int belongConfId) {
		ConfBase childConf = null;
		if (belongConfId > 0) {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select tcb.* from t_conf_base tcb ");
			sqlBuffer.append(" where tcb.del_flag=? and tcb.permanent_conf=? ");
			sqlBuffer.append("  and belong_confId=? ");
			sqlBuffer.append("  and (tcb.conf_status=? or tcb.conf_status=?) ");
			sqlBuffer.append(" order by conf_status desc,tcb.id desc");
			Object[] values = new Object[] { ConstantUtil.DELFLAG_UNDELETE,
					ConfConstant.CONF_PERMANENT_ENABLED_CHILD, belongConfId,
					ConfConstant.CONF_STATUS_OPENING,
					ConfConstant.CONF_STATUS_SUCCESS };
			try {
				childConf = libernate.getEntityCustomized(ConfBase.class,
						sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return childConf;

	}

	/**
	 * 查询永久会议（主会议）
	 */
	@Override
	public PageBean<ConfBase> getPermanentConfPage(int pageNo, int pageSize,
			Integer siteId, Integer userId, Integer confStatus,
			boolean publicOnly) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder
				.append(" SELECT * FROM t_conf_base tcb WHERE 1=1 AND tcb.del_flag = ? ");
		valueList.add(ConfConstant.CONF_STATUS_FINISHED);
		if (siteId != null) {
			sqlBuilder.append(" and site_id = ? ");
			valueList.add(siteId);
		}
		if (confStatus != null) {
			sqlBuilder.append(" and  conf_status = ? ");
			valueList.add(confStatus);
		}
		if (userId != null && userId.intValue() > 0) {
			sqlBuilder.append(" and compere_user = ? ");
			valueList.add(userId);
		}
		if (publicOnly) {
			sqlBuilder.append(" and public_flag = ?");
			valueList.add(ConfConstant.CONF_PUBLIC_FLAG_FALSE);
		}
		sqlBuilder.append("  ORDER BY tcb.start_time desc ");
		return getPageBeans(ConfBase.class, sqlBuilder.toString(), pageNo,
				valueList.toArray());
	}


	/**
	 * 修改永久会议
	 */
	@Override
	public ConfBase updatePermanentConf(ConfBase conf, SiteBase currentSite,
			UserBase currUser) {
		 
		// 2013.7.17
		String[] passArray = new String[] { "id", "confName", "confDesc",
				"hostKey", "confType", "startTime", "endTime" };
		ConfBase updatedconf = null;
		try {
			if (conf.getEndTime() != null) {
				conf.setEndTime(DateUtil.getGmtDateByTimeZone(
						conf.getEndTime(), currUser.getTimeZone()));
			}
			if (conf.getStartTime() != null) {
				conf.setStartTime(DateUtil.getGmtDateByTimeZone(
						conf.getStartTime(), currUser.getTimeZone()));
			}
			// 2013.7.23 超过最大并发会议数
			if (!checkSiteMaxConfCount(conf, currentSite)) {
				conf.setId(ConfConstant.CONF_CREATE_ERROR_SYNC_LICENCE); // 超过最大并发会议数
				logger.info("超过最大并发会议数，创建会议失败");
				return conf;
			}
			// 2013.7.23

			updatedconf = libernate.updateEntity(conf, passArray);

			if (updatedconf != null) {
				List<ConfUser> confUsers = confUserService
						.getAllConfUserList(updatedconf.getId().intValue(),1);
				boolean sendConfUsers = emailService.confModifyEmail(confUsers,
						updatedconf);
				if (sendConfUsers) {
					logger.info("send update PermanentConf email  ok!");
				} else {
					logger.info("send update PermanentConf email  failed!");
				}
				updatedconf = libernate.getEntity(ConfBase.class,
						updatedconf.getId());
				// 2013.9.2 修复bug 1541
				updatedconf.getOffsetConf(currUser, updatedconf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedconf;
	}
  
	/**
	 * 系统管理员根据会议主题、企业名称、企业标识模糊查询所有站点的会议列表
	 * 
	 * @param TitleOrNameOrSign
	 *            会议主题、企业名称、企业标识 wangyong 2013-5-29
	 */
	@Override
	public PageBean<ConfBase> getSysConfByName(String titleOrNameOrSign,
			String sortField, String sortord, PageModel pageModel,
			Integer sysUserId) {
		return getSysConf(null, titleOrNameOrSign, sortField, sortord,
				pageModel, sysUserId);
	}

	/**
	 * 系统管理员高级搜索查询会议列表
	 * 
	 * @param conf
	 *            会议名称、会议功能、会议状态等信息
	 * @param siteName
	 *            站点名称
	 * @param siteSign
	 *            站点标识
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式，正序逆序
	 * @param pageModel
	 *            页数信息
	 * @param sysUserId
	 *            不为空，则只查出该普通系统管理员创建的站点下的所有会议；为空，则查询所有站点下的会议 wangyong 2013-5-29
	 */
	@Override
	public PageBean<ConfBase> getSysConfByCondition(ConfBase conf,
			String siteName, String siteSign, String titleOrSiteSign,
			Date beginTime, Date endTime, String sortField, String sortord,
			PageModel pageModel, Integer sysUserId) {
		Condition condition = initCondition(conf, siteName, siteSign,
				beginTime, endTime);
		return getSysConf(condition, titleOrSiteSign, sortField, sortord,
				pageModel, sysUserId);
	}

	/**
	 * 站点管理员根据会议主题或会议号模糊查询所有站点的会议列表
	 * 
	 * @param subject
	 *            会议主题或会议号 wangyong 2013-5-29
	 */
	@Override
	public PageBean<ConfBase> getAdminConfByName(String subject,
			SiteBase currentSite, String sortField, String sortord,
			PageModel pageModel, Integer adminId) {
		return getAdminConf(null, subject, currentSite, sortField, sortord,
				pageModel, adminId);
	}

	/**
	 * 站点管理员高级搜索查询会议列表
	 * 
	 * @param conf
	 *            会议名称、会议功能、会议状态等信息
	 * @param siteId
	 *            站点id
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param sortField
	 *            排序字段
	 * @param sortord
	 *            排序方式，正序逆序
	 * @param pageModel
	 *            页数信息
	 * @param adminId
	 *            不为空，则只查出该普通系统管理员创建的站点下的所有会议；为空，则查询所有站点下的会议 wangyong 2013-5-29
	 */
	@Override
	public PageBean<ConfBase> getAdminConfByCondition(ConfBase conf,
			SiteBase currentSite, Date beginTime, Date endTime,
			String sortField, String sortord, PageModel pageModel,
			Integer adminId) {
		Condition condition = initCondition(conf, null, null, beginTime,
				endTime);
		return getAdminConf(condition, null, currentSite, sortField, sortord,
				pageModel, adminId);
	}

	private PageBean<ConfBase> getSysConf(Condition condition,
			String titleOrNameOrSign, String sortField, String sortord,
			PageModel pageModel, Integer sysUserId) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" SELECT id, site_id, conf_name, create_user, compere_user,compere_name , conf_type, start_time, end_time,conf_zoom_id, permanent_conf,create_type, ");
		strSql.append(" (CASE WHEN  conf.conf_status =6 THEN 0 WHEN conf.conf_status =5 THEN 2 WHEN conf.conf_status =7 THEN 3 ELSE conf.conf_status END) conf_status ");
		strSql.append(" from ( ");
		strSql.append("select * from (");
		strSql.append(" SELECT id, site_id, conf_name, create_user, compere_user,compere_name , conf_type, start_time, end_time,conf_zoom_id,permanent_conf,create_type,");
		strSql.append(" (CASE WHEN  tmp.conf_status =20 THEN 6 WHEN tmp.conf_status =200 THEN 5 WHEN tmp.conf_status =2000 THEN 7 ELSE tmp.conf_status END) conf_status ");
		strSql.append(" FROM ( ");
		strSql.append(" SELECT confBase.id, confBase.site_id, confBase.conf_name, confBase.create_user, confBase.compere_user, confBase.compere_name , confBase.conf_type, confBase.start_time, confBase.end_time,confBase.conf_zoom_id, confBase.permanent_conf,confBase.create_type, ");
		strSql.append(" (CASE WHEN  confBase.conf_status =0 THEN 20 WHEN confBase.conf_status =2 THEN 200 WHEN confBase.conf_status =3 THEN 2000 ELSE confBase.conf_status END) conf_status ");
		strSql.append(" FROM  t_conf_base confBase, t_site_base siteBase WHERE 1=1");
		if (condition != null
				&& StringUtil.isNotBlank(condition.getConditionSql())) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		strSql.append(" AND confBase.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND siteBase.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND confBase.site_id = siteBase.id ");
		if (StringUtil.isNotBlank(titleOrNameOrSign)) {
			strSql.append(" AND (confBase.conf_name LIKE ? OR confBase.compere_name LIKE ? OR siteBase.`site_name` LIKE ? OR siteBase.site_sign LIKE ? ) ");
			valueList.add("%" + titleOrNameOrSign + "%");
			valueList.add("%" + titleOrNameOrSign + "%");
			valueList.add("%" + titleOrNameOrSign + "%");
			valueList.add("%" + titleOrNameOrSign + "%");
		}
		if (sysUserId != null && sysUserId.intValue() > 0) {
			strSql.append(" AND siteBase.create_user = ?");
			valueList.add(sysUserId.intValue());
		}
		strSql.append(" ) tmp ");
		strSql.append(" ) base ");
		strSql.append("ORDER BY base.conf_status ASC, base.start_time ASC, base.id ASC ");
		strSql.append(" ) conf ");
		if (StringUtil.isNotBlank(sortField)) {
			strSql.append(" order by ");
			for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
				if (eachField != null && eachField[0].equals(sortField)) {
					strSql.append("conf.");
					strSql.append(BeanUtil.att2Field(eachField[1]));
					break;
				}
			}
			if (SortConstant.SORT_ASC.equals(sortord)) {
				strSql.append(" asc ");
			}

			if (SortConstant.SORT_DESC.equals(sortord) || sortord == null
					|| "".equals(sortord.trim())
					|| "null".equals(sortord.trim().toLowerCase())) {
				strSql.append(" desc ");
			}
		}

		return getConfPageModule(null, pageModel, strSql, valueList);
	}

	/**
	 * 查询会议列表的一个方法，用于站点管理员或系统管理员查看会议列表
	 * 
	 * @param keyword
	 *            会议主题
	 * @param confStatus
	 *            会议状态
	 * @param startDate
	 *            时间段的开始
	 * @param endDate
	 *            时间段结束
	 * @param currentSite
	 *            当前的站点
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            每页显示多少条数据
	 * @return PageBean<ConfBase>
	 */
	public PageBean<ConfBase> getConfPageForAdmin(String keyword,
			int confStatus, Date startDate, Date endDate, SiteBase currentSite,
			int pageNo, int pageSize, String sortField, String sortRule) {

		StringBuilder sqlbuilder = new StringBuilder();
		ArrayList<Object> parms = new ArrayList<Object>();
		sqlbuilder.append(" SELECT distinct t.* FROM t_conf_base t  ");// ,t_conf_log
		sqlbuilder.append(" WHERE t.del_flag=? and t.conf_status != 99 ");
		parms.add(ConstantUtil.DELFLAG_UNDELETE);

		// 非永久会议
		sqlbuilder.append(" and t.permanent_conf=?  ");
		parms.add(ConfType.PERMANENT_CONF_FALSE);

		if (confStatus > -1) {
			sqlbuilder.append(" AND t.conf_status=? ");
			parms.add(confStatus);
		}

		if (currentSite != null) {
			sqlbuilder.append(" AND t.site_id=? ");
			parms.add(currentSite.getId());
		}

		if (!StringUtil.isEmpty(keyword)) {
			sqlbuilder.append(" AND t.conf_name like ?");
			parms.add("%" + keyword.trim() + "%");
		}

		if (startDate != null) {
			sqlbuilder.append(" AND t.end_time >= ?");
			parms.add(startDate);
		}

		if (endDate != null) {
			sqlbuilder.append(" AND t.end_time < ?");
			parms.add(endDate);
		}

		if (!StringUtil.isEmpty(sortField)) {
			for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
				if (eachField != null && eachField[0].equals(sortField)) {
					sqlbuilder.append(" ORDER BY t.");
					sqlbuilder.append(BeanUtil.att2Field(eachField[1]));
					break;
				}
			}
			if (SortConstant.SORT_DESC.equals(sortRule)) { // 修改排序问题
				sqlbuilder.append(" desc ");
			}
		} else {
			sqlbuilder.append(" ORDER BY t.end_time DESC");
		}
		return getPageBeans(ConfBase.class, sqlbuilder.toString(), pageNo,
				pageSize, parms.toArray());

	}

	public PageBean<ConfBase> getConfPageForSysAdmin(String keyword,
			int confStatus, Date startDate, Date endDate, int pageNo,
			int pageSize, String sortField, String sortRule) {

		StringBuilder sqlbuilder = new StringBuilder();
		ArrayList<Object> parms = new ArrayList<Object>();
		sqlbuilder.append(" SELECT distinct t.* FROM t_conf_base t  ");// ,t_conf_log
		sqlbuilder.append(" WHERE t.del_flag=?  ");
		parms.add(ConstantUtil.DELFLAG_UNDELETE);

		// 非永久会议
		sqlbuilder.append(" and t.permanent_conf=?  ");
		parms.add(ConfType.PERMANENT_CONF_FALSE);

		if (confStatus > -1) {
			sqlbuilder.append(" AND t.conf_status=? ");
			parms.add(confStatus);
		}

		if (!StringUtil.isEmpty(keyword)) {
			sqlbuilder.append(" AND t.conf_name like ?");
			parms.add("%" + keyword.trim() + "%");
		}

		if (startDate != null) {
			sqlbuilder.append(" AND t.end_time >= ?");
			parms.add(startDate);
		}

		if (endDate != null) {
			sqlbuilder.append(" AND t.end_time < ?");
			parms.add(endDate);
		}

		if (!StringUtil.isEmpty(sortField)) {
			for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
				if (eachField != null && eachField[0].equals(sortField)) {
					sqlbuilder.append(" ORDER BY t. ");
					sqlbuilder.append(BeanUtil.att2Field(eachField[1]));
					break;
				}
			}
			if (SortConstant.SORT_ASC.equals(sortRule)) {
				sqlbuilder.append(" asc ");
			}
		} else {
			sqlbuilder.append(" ORDER BY t.end_time DESC");
		}
		return getPageBeans(ConfBase.class, sqlbuilder.toString(), pageNo,
				pageSize, parms.toArray());

	}

	/*
	 * this is a very stupid method here.please see getConfPageForAdmin
	 */
	@Deprecated
	private PageBean<ConfBase> getAdminConf(Condition condition,
			String subject, SiteBase currentSite, String sortField,
			String sortord, PageModel pageModel, Integer adminId) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(
				" SELECT id, conf_name, create_user, compere_user,compere_name , conf_type, start_time, end_time, actul_start_time,permanent_conf,create_type, ");
		strSql.append(" (CASE WHEN  conf.conf_status =6 THEN 0 WHEN conf.conf_status =5 THEN 2 WHEN conf.conf_status =7 THEN 3 ELSE conf.conf_status END) conf_status ");
		strSql.append(" from ( ");
		strSql.append("select * from (");
		strSql.append(" SELECT id, conf_name, create_user, compere_user,compere_name , conf_type, start_time, end_time, actul_start_time,permanent_conf,create_type, ");
		strSql.append(" (CASE WHEN  tmp.conf_status =20 THEN 6 WHEN tmp.conf_status =200 THEN 5 WHEN tmp.conf_status =2000 THEN 7 ELSE tmp.conf_status END) conf_status ");
		strSql.append(" FROM ( ");
		strSql.append(" SELECT confBase.id, confBase.conf_name, confBase.create_user, confBase.compere_user, b.true_name compere_name, confBase.conf_type, confBase.start_time, confBase.end_time, confBase.actul_start_time, confBase.permanent_conf, confBase.create_type,");
		strSql.append(" (CASE WHEN  confBase.conf_status =0 THEN 20 WHEN confBase.conf_status =2 THEN 200 WHEN confBase.conf_status =3 THEN 2000 ELSE confBase.conf_status END) conf_status ");
		strSql.append(" FROM  t_conf_base confBase, t_user_base b WHERE 1=1 ");
		if (condition != null
				&& StringUtil.isNotBlank(condition.getConditionSql())) {
			strSql.append(" and ").append(condition.getConditionSql());
		}
		strSql.append(" AND confBase.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND confBase.create_user = b.id ");
		strSql.append(" AND confBase.site_id = b.site_id ");
		strSql.append(" and confBase.site_id = ? ");
		valueList.add(currentSite.getId());
		if (StringUtil.isNotBlank(subject)) {
			strSql.append(" AND confBase.conf_name LIKE ?  ");
			valueList.add("%" + subject + "%");
		}
		if (adminId != null && adminId.intValue() > 0) {
			strSql.append(" AND b.create_user = ? ");
			valueList.add(adminId);
		}
		strSql.append(" ) tmp ");
		strSql.append(" ) base ");
		strSql.append("ORDER BY base.conf_status ASC, base.start_time ASC, base.id ASC ");
		strSql.append(" ) conf ");
		if (StringUtil.isNotBlank(sortField)) {
			strSql.append(" order by ");
			for (String[] eachField : SortConstant.CONFBASE_FIELDS) {
				if (eachField != null && eachField[0].equals(sortField)) {
					strSql.append("conf.");
					strSql.append(BeanUtil.att2Field(eachField[1]));
					break;
				}
			}
			if (SortConstant.SORT_ASC.equals(sortord)) {
				strSql.append(" asc ");
			}

			if (SortConstant.SORT_DESC.equals(sortord) || sortord == null
					|| "".equals(sortord.trim())
					|| "null".equals(sortord.trim().toLowerCase())) {
				strSql.append(" desc ");
			}
		}
		return getConfPageModule(currentSite, pageModel, strSql, valueList);
	}

	private PageBean<ConfBase> getConfPageModule(SiteBase currentSite,
			PageModel pageModel, StringBuffer strSql, List<Object> valueList) {
		List<ConfBase> confList = null;
		List<ConfBaseSimple> confBaseSimpleList = null;
		PageBean<ConfBase> page = new PageBean<ConfBase>();

		try {
			String sql = strSql.toString().toLowerCase();
			String countsql = " select count(*) "
					+ sql.substring(sql.indexOf("from"));
			int count = libernate.countEntityListWithSql(countsql,
					valueList.toArray());
			page.setRowsCount(count);

			pageModel.setRowsCount(count);
		} catch (SQLException e) {
			logger.error("站点管理员根据高级搜索条件查询会议列表条数出错！" + e);
			return null;
		}
		try {
			// 如果要查询所有信息可设置 pageModel.pageSize == 0
			String limitsql = strSql.toString();
			if (pageModel.getPageSize() > 0) {

				limitsql = strSql.toString() + " limit "
						+ (Integer.parseInt(pageModel.getPageNo()) - 1)
						* pageModel.getPageSize() + ","
						+ pageModel.getPageSize();
			}

			confBaseSimpleList = libernate.getEntityListBase(
					ConfBaseSimple.class, limitsql, valueList.toArray());
		} catch (SQLException e) {
			logger.error("站点管理员根据高级搜索条件查询会议列表(权限控制条件可以放在condition中)出错！" + e);
		}
		if (confBaseSimpleList != null && confBaseSimpleList.size() > 0) {
			confList = new ArrayList<ConfBase>();
			String[] filedArray = ObjectUtil
					.getFieldFromObject(confBaseSimpleList.get(0));
			String copyField = "";
			for (String filed : filedArray) {
				if (!"confStatus".equals(filed)) {
					copyField = copyField + "," + filed;
				}
			}
			logger.info("copyField:" + copyField);
			for (ConfBaseSimple conf : confBaseSimpleList) {
				ConfBase confBase = new ConfBase();
				try {
					confBase = (ConfBase) ObjectUtil.copyObject(conf, confBase,
							copyField);
					confBase.setConfStatus(Integer.valueOf(conf.getConfStatus()
							.toString()));
				} catch (Exception e) {
					logger.error("转换conf出错！" + e);
				}
				confList.add(confBase);
			}
		}
		if (currentSite != null && confList != null) {
			confList = getOffsetConfList(currentSite, confList);
		}
		page.setDatas(confList);
		page.setPageNo(pageModel.getPageNo());
		page.setPageSize(pageModel.getPageSize());
		return page;
	}

	/**
	 * 初始化高级搜索Condition条件 wangyong 2013-1-23
	 */
	private Condition initCondition(ConfBase confBase, String siteName,
			String siteSign, Date beginTime, Date endTime) {
		Condition condition = new Condition();
		if (confBase != null) {
			logger.info("confBase-----" + confBase);
			if (StringUtil.isNotBlank(confBase.getConfName())) {
				condition.like("confBase.conf_name", confBase.getConfName()
						.trim());
			}
			if (confBase.getConfType() != null
					&& confBase.getConfType().intValue() > 0) {
				condition.equal("confBase.conf_type", confBase.getConfType()); // 会议类型：
																				// 1
																				// 、电话会议
																				// 2、视频
																				// 3、视频+网络电话
																				// 4、视频+传统电话
			}
			if (confBase.getConfStatus() != null
					&& confBase.getConfStatus().intValue() != -1) {
				condition.equal("confBase.conf_status",
						confBase.getConfStatus()); // 会议状态：-1：全部会议 0、预约成功 2、正在召开
													// 3、已结束 9、取消的会议
				if (confBase.getConfStatus().intValue() != ConfConstant.CONF_STATUS_CANCELED) {
					condition.equal("confBase.del_flag",
							ConstantUtil.DELFLAG_UNDELETE);
				}
			}
			if (StringUtil.isNotBlank(siteName)) {
				condition.like("siteBase.site_name", siteName);
			}
			if (StringUtil.isNotBlank(siteSign)) {
				condition.like("siteBase.site_sign", siteSign);
			}
		}
		if (beginTime != null) {
			beginTime = DateUtil.StringToDate(
					DateUtil.dateToString(beginTime, null), "yyyy-MM-dd");
			beginTime = DateUtil.getGmtDate(beginTime);
			condition.greaterEqual("confBase.start_time", DateUtil
					.getDateStrCompact(beginTime, "yyyy-MM-dd HH:mm:ss"));
		}
		if (endTime != null) {
			endTime = DateUtil.StringToDate(
					DateUtil.dateToString(endTime, null), "yyyy-MM-dd");
			endTime = DateUtil.getGmtDate(DateUtil.addDate(endTime, 1));
			condition.lessEqual("confBase.start_time", DateUtil
					.getDateStrCompact(DateUtil.addDateSecond(endTime, -1),
							"yyyy-MM-dd HH:mm:ss"));
		}
		return condition;
	}

	public boolean checkSiteMaxConfCount(ConfBase confBase, SiteBase siteBase) {
		if (!ConfConstant.CHECK_CONCURRENT_CONF_SCHEDULE) {
			return true;
		}
		if (confBase == null || siteBase == null) {
			return false;
		}
		List<Object> valueList = new ArrayList<Object>();
		int nowConfCount = 0;
		// 站点最大并发量
		int siteMaxConf = siteBase.getSyncConfNum();
		// 站点最大并发量不做限制的话，直接返回true
		if (siteMaxConf <= 0) {
			return true;
		}

		// 取当前 站点下时间段内的会议数量
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer
				.append(" SELECT COUNT(id) AS confCount FROM t_conf_base tcb ");

		sqlBuffer.append(" WHERE 	tcb.`del_flag`=? ");
		sqlBuffer.append("			AND tcb.`site_id`=?");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(siteBase.getId());

		if (confBase.getId() != null && confBase.getId().intValue() > 0) {
			sqlBuffer.append("		AND tcb.id != ?");
			valueList.add(confBase.getId());
		}

		sqlBuffer.append("			AND (tcb.`conf_status`=? OR tcb.`conf_status`=?)");
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		sqlBuffer.append("			AND (tcb.`permanent_conf` <= ?)");
		valueList.add(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);
		sqlBuffer.append("			AND ");
		sqlBuffer.append("			(");
		sqlBuffer.append("				(tcb.`start_time`<=? AND tcb.`end_time`>=?)");
		valueList.add(confBase.getStartTime());
		valueList.add(confBase.getStartTime());
		sqlBuffer.append("				OR");
		sqlBuffer.append("				( tcb.`start_time`>=? AND tcb.`end_time`<=?)");
		valueList.add(confBase.getStartTime());
		valueList.add(confBase.getEndTime());
		sqlBuffer.append("				OR");
		sqlBuffer.append("				( tcb.`start_time`<=? AND tcb.`end_time`>=?)");
		valueList.add(confBase.getEndTime());
		valueList.add(confBase.getEndTime());
		sqlBuffer.append("			)");

		try {
			nowConfCount = libernate.countEntityListWithSql(
					sqlBuffer.toString(), valueList.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return siteMaxConf > nowConfCount ? true : false;
	}

	/**
	 * 创建、修改会议调用AS失败，返回错误码时调用
	 * 
	 * @param errorCode
	 *            调用华为AS创建、修改会议返回的错误码（非0） wangyong 2013-7-24
	 */
	private ConfBase getErrorConf(ConfBase confBase, String errorCode) {
		if (ConstantUtil.AS_FAILED_LICENSE_SET.contains(errorCode)) {
			confBase.setId(ConfConstant.CONF_CREATE_ERROR_LICENCE); // 参会人数大于站点当前所剩参会人数值
			logger.error("参会人数大于站点当前所剩参会人数值");
			return confBase;
		} else if (errorCode.equals(ConstantUtil.AS_FAILED_MRS_INVALID)) {
			confBase.setId(ConfConstant.CONF_CREATE_ERROR_MRS); // MRS资源分配异常
			logger.error("MRS资源分配异常");
			return confBase;
		} else if (ConstantUtil.AS_ERROR_START_FAILED
				.equalsIgnoreCase(errorCode)) {
			confBase.setId(ConfConstant.CONF_CREATE_ERROR_SYNC_LICENCE); // 超过最大并发会议数
			logger.info("超过最大并发会议数，创建会议失败");
			return confBase;
		} else {
			logger.error("华为返回错误码：" + errorCode);
			return null;
		}
	}

	/**
	 * 
	 */
	public ConfBase getConfBaseByZoomId(String zoomId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE conf_zoom_id = ? and del_flag = ? ");
		Object[] values = new Object[2];
		values[0] = zoomId;
		values[1] = ConstantUtil.DELFLAG_UNDELETE;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return confBase;
	}

	public ConfBase getConfBaseByZoomPMI(String pmi) {
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_user_base WHERE pmi_id = ? ");
		Object[] values = new Object[1];
		values[0] = pmi;

		try {
			UserBase user = libernate.getEntityCustomized(UserBase.class,
					strSql.toString(), values);
			if (user != null) {
				
				//add by Darren 2014-12-24
				DataCenter center = dataCenterService.queryDataCenterById(user.getDataCenterId());
				
				Map<String, Object> dataMap = meetingOperationComponent
						.getMeeting(center.getApiKey(),center.getApiToken(),pmi, user.getZoomId());
				ConfBase conf = new ConfBase();
				conf.setConfName(dataMap.get("topic").toString());
				conf.setConfZoomId(pmi);
				conf.setJoinUrl(dataMap.get("join_url").toString());
				conf.setStartUrl(dataMap.get("start_url").toString());
				conf.setHostKey(dataMap.get("password").toString());
				conf.setCreateUser(user.getId());
				return conf;
			}
		} catch (Exception e) {
			logger.error("根据会议ID号获取会议信息出错！", e);
		}
		return null;
	}

	@Override
	public ConfBase saveNewConfBase(ConfBase newConfBase) {

		try {
			return libernate.saveEntity(newConfBase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算参会人数
	 * */
	@Override
	public Map<Integer, Integer> getConfsNums(List<ConfBase> confList) {

		Map<Integer, Integer> datas = new HashMap<Integer, Integer>();
		if (confList != null && confList.size() > 0) {
			for (Iterator<ConfBase> itr = confList.iterator(); itr.hasNext();) {
				ConfBase conf = itr.next();
				datas.put(conf.getId(), sumparticipants(conf.getId()));
			}
		}
		return datas;
	}

	public int sumparticipants(Integer id) {
		StringBuilder builder = new StringBuilder(
				"select count(id) from t_conf_report_info where conf_log_id in (select id from t_zoom_conf_record where  conf_id = ?)");
		int count = 0;
		try {
			count = libernate.countEntityListWithSql(builder.toString(),
					new Object[] { id });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		;
		return count;
	}

	@Override
	public ConfBase getConfBase(Integer siteId, String code) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(
				" SELECT * FROM t_conf_base WHERE conf_zoom_id = ? ");
		Object[] values = new Object[2];
		values[0] = code;
		if (siteId != null && siteId > 0) {// 站点存在表示有用户已经登录
			strSql.append(" and site_id = ?");
			values[1] = siteId;
		}

		try {
			confBase = libernate.getEntityCustomized(ConfBase.class,
					strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议号码查询站点下某个会议！", e);
		}
		return confBase;
	}

	@Override
	public ConfCycle updateCycleByCycleId(ConfCycle confCycle,
			ConfBase confBase, UserBase userBase, SiteBase currentSite,
			Integer timeZone) {

		confCycle.setBeginDate(DateUtil.getGmtDateByTimeZone(
				confCycle.getBeginDate(), timeZone));
		confCycle.setCreateUser(userBase.getId());
		confCycle.setEndDate(DateUtil.getGmtDateByTimeZone(
				confCycle.getEndDate(), timeZone));
		confCycle.setSiteId(currentSite.getId());

		try {
			confCycle = libernate.updateEntity(confCycle, "infiniteFlag",
					"repeatCount", "cycleType", "cycleValue", "beginDate",
					"endDate");
		} catch (Exception e) {
			logger.error("保存 会议信息--循环会议设置出错！" + confCycle, e);
		}
		return confCycle;

	}

	/**
	 * 更新用户PMI
	 */
	@Override
	public boolean updateUserPMI(ConfBase conf, UserBase currentUser) {
		try {

			ConfBase dbPMI = getConfBasebyConfId(conf.getId());
			dbPMI.setOptionJbh(conf.getOptionJbh());
			dbPMI.setOptionStartType(conf.getOptionStartType());
			dbPMI.setConfName(conf.getConfName());
			dbPMI.setHostKey(conf.getHostKey());
			//dbPMI.setConfDesc(conf.getConfDesc());

			boolean jbh = dbPMI.getOptionJbh() == 0 ? false : true;
			String start_type = MeetingStartType.setStatusCode(dbPMI
					.getOptionStartType());
			
			//add by Darren 2014-12-24
			DataCenter center = dataCenterService.queryDataCenterById(currentUser.getDataCenterId());
			
			int status = meetingOperationComponent.updateMeeting(center.getApiKey(),center.getApiToken(),
					dbPMI.getConfZoomId(),
					currentUser.getZoomId(), dbPMI.getConfName(), null, 0,
					null, dbPMI.getHostKey(), jbh, start_type);
			if (status == ZoomConfStatus.UPDATE_SUCCESS) {
				
				//get H323 password ,add by darren 2015-01-30 
				Map<String, Object> retMap = meetingOperationComponent.getMeeting(center.getApiKey(), center.getApiToken(), dbPMI.getConfZoomId(), currentUser.getZoomId());
				if(retMap.get("error") == null){
					if(!StringUtil.isEmpty((String)retMap.get("h323Password")) && 
							!StringUtil.isEmpty(dbPMI.getHostKey())){
						dbPMI.setPhonePass((String)retMap.get("h323Password"));
					}else {
						dbPMI.setPhonePass("");
					}
				}
				libernate.updateEntity(dbPMI);
			}

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * 从ZOOM服务器中获取会议的信息
	 */
	public ConfBase getConfFromZoom(String zoomId, String hostId) {
		
		try{
			//add by Darren 2014-12-24
			UserBase base = userService.getUserBaseByZoomUserId(hostId);
			if(base==null){
				return null;
			}
			DataCenter center = dataCenterService.queryDataCenterById(base.getDataCenterId());
			Map<String, Object> meeting = meetingOperationComponent.getMeeting(center.getApiKey(),center.getApiToken(),
					zoomId, hostId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (meeting != null && meeting.get("id") != null) {
					ConfBase newConf = new ConfBase();
					
					//会议开始时间
					String startTime = meeting.get("start_time").toString();
					if(!StringUtil.isEmpty(startTime)){
						startTime = startTime.replace("T", " ").replace("Z","");
						
						Date sDate = sdf.parse(startTime);
						newConf.setStartTime(sDate);
					}else {
						newConf.setStartTime(DateUtil.getGmtDate(null));
					}
					
					//会议的类型
					int type = (Integer)meeting.get("type");
					if(type == 1){
						newConf.setConfType(ConfType.INSTANT);
					}else if(type == 2){
						newConf.setConfType(ConfType.SCHEDULE);
					}else if(type == 3){
						newConf.setConfType(ConfType.RECURR);
						
						// 周期会议的开始时间
						startTime = meeting.get("created_at").toString();
						startTime = startTime.replace("T", " ").replace("Z","");
						
						Date sDate = sdf.parse(startTime);
						sDate = DateUtil.getGmtDateByTimeZone(sDate, 28800000);
						newConf.setStartTime(sDate);
						
					}
					
					//会议状态
					int confStatus = (Integer)meeting.get("status");
					if(confStatus == ZoomConfStatus.CONF_RUNING){
						newConf.setConfStatus(ConfConstant.CONF_STATUS_OPENING);
					}else{
						newConf.setConfStatus(ConfConstant.CONF_STATUS_SUCCESS);
					}
					
					//说明该会议开始时间是在24小时之前 并且不是周期会议,则剔除该会议
					//if((DateUtil.getGmtDate(null).getTime() - newConf.getStartTime().getTime()) >24*3600*1000l && newConf.getConfType() != ConfType.RECURR) continue;
					
					//会议创建时间
					newConf.setCreateTime(DateUtil.getGmtDate(null));
					
					//会议的时长和会议结束时间,只有非周期会议才设置
					if(newConf.getConfType() != ConfType.RECURR){
						int duration = (Integer)meeting.get("duration");
						if(duration>0){
							newConf.setEndTime(new Date(newConf.getStartTime().getTime()+duration*60*1000l));
							newConf.setDuration(duration);
						}else{
							newConf.setEndTime(new Date(newConf.getStartTime().getTime()+60*60*1000l));
							newConf.setDuration(60);
						}
					}else{
						newConf.setEndTime(new Date(newConf.getStartTime().getTime()+20*365*24*3600000l));
					}
					
					//会议的主题
					newConf.setConfName(String.valueOf(meeting.get("topic")));
					
					// 会议的删除时间
					newConf.setDelTime(DateUtil.getGmtDate(null));
					
					//会议的zoomID
					newConf.setConfZoomId(meeting.get("id").toString());
					
					 //会议原始的开始连接地址
					newConf.setStartUrl(String.valueOf(meeting.get("start_url")));
					newConf.setJoinUrl(String.valueOf(meeting.get("join_url")));
					boolean jbh = Boolean.valueOf(meeting.get("option_jbh")
							.toString());
					if (jbh) {
						newConf.setOptionJbh(1);
					}else{
						newConf.setOptionJbh(0);
					}
					String option_start_type = String.valueOf(meeting
							.get("option_host_video"));
					if (option_start_type.equals("true")) {
						newConf.setOptionStartType(MeetingStartType.VIDEO.getStatus());
					}else{
						newConf.setOptionStartType(MeetingStartType.SCREEN_SHARE.getStatus());
					}
					newConf.setHostKey(String.valueOf(meeting.get("password")));
					
				return newConf;
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 这个方法更新开始超过24小时的会议的状态
	 * 
	 * @return
	 */
	public boolean fixConfStatus() {
		/*String sql = "select count(*) from t_conf_base where conf_status = ? and permanent_conf = ? and conf_type=? and  actul_start_time < now()-interval 24 HOUR";
		String sqlupdate = "update t_conf_base set conf_status = ? where conf_status = ? and permanent_conf = ? and conf_type=? and actul_start_time < now()-interval 24 HOUR ";
		try {
			int count = libernate.countEntityListWithSql(sql, new Object[] {
					ConfConstant.CONF_STATUS_OPENING,
					ConfType.PERMANENT_CONF_FALSE, ConfType.SCHEDULE });
			if (count > 0) {
				return libernate.executeSql(sqlupdate, new Object[] {
						ConfConstant.CONF_STATUS_FINISHED,
						ConfConstant.CONF_STATUS_OPENING,
						ConfType.PERMANENT_CONF_FALSE, ConfType.SCHEDULE });
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
	
	
	/**
	 * 该方法获取即将开始的客户端创建的即将开始的周期会议
	 */
	@Override
	public List<ConfBase> getClientWaitRurrConfByHost(UserBase host,String confName) {
		if(host == null) return null;
		
		List<Object> parms = new ArrayList<Object>();
		
		String sql = "select * from t_conf_base where compere_user = ? "
				+ "and conf_type = ? and create_type = ? and conf_status = ? ";
		parms.add(host.getId());
		parms.add(ConfType.RECURR);
		parms.add(ConfType.CREATE_TYPE_CLIENT);
		parms.add(ConfConstant.CONF_STATUS_SUCCESS);
		if(!StringUtil.isEmpty(confName)){
			sql += "and conf_name like ?";
			parms.add("%" + confName.trim() + "%");
		}
		try {
			return libernate.getEntityListCustomized(ConfBase.class, sql, parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ConfBase saveOrUpdateConf(ConfBase confBase) {
		try{
			if(confBase.getId()!=null && confBase.getId().intValue()>0){
				return libernate.updateEntity(confBase);
			}else{
				return libernate.saveEntity(confBase);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int joinByH323OrSip(String confNo, String pairCode) {
		
		ConfBase conf = confLogic.getConfByZoomId(confNo);
		try {
			UserBase host = libernate.getEntity(UserBase.class, conf.getCreateUser());
			DataCenter dc = libernate.getEntity(DataCenter.class, host.getDataCenterId());
			
			return meetingOperationComponent.pairH323OrSipTerminal(dc.getApiKey(), dc.getApiToken(),
					confNo, host.getZoomId(), pairCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return APIReturnCode.CALLING_FAILED;
	}



	
//	public static void main(String[] args){
//		
//		
//	}
}
