package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.PublicConfService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class PublicConfServiceImpl extends BaseService implements
		PublicConfService {
	
	@Autowired
	SiteService siteService;

	@Override
	public List<ConfBase> getOnGoingConf(int siteId) {
		return getConfListByStatus(siteId, ConfConstant.CONF_STATUS_OPENING);
	}

	@Override
	public List<ConfBase> getUpComingConf(int siteId) {
		return getConfListByStatus(siteId, ConfConstant.CONF_STATUS_SUCCESS);
	}
	
	private List<ConfBase> getConfListByStatus(int siteId, int status) {
		Object[] conditions = new Object[] { 
				siteId,
				ConfConstant.CONF_PUBLIC_FLAG_TRUE,
				ConstantUtil.DELFLAG_UNDELETE,
				status,
				DateUtils.addDays(DateUtil.getGmtDate(null), 7),
				siteId,
				ConfConstant.CONF_PUBLIC_FLAG_TRUE,
				ConstantUtil.DELFLAG_UNDELETE,
				status,
				DateUtils.addDays(DateUtil.getGmtDate(null), 7) 
		};
		if(ConfConstant.CONF_STATUS_SUCCESS.equals(status)){
			conditions= new Object[] { 
					siteId,
					ConfConstant.CONF_PUBLIC_FLAG_TRUE,
					ConstantUtil.DELFLAG_UNDELETE,
					status,
					DateUtil.getGmtDate(null),
					DateUtils.addDays(DateUtil.getGmtDate(null), 7),
					siteId,
					ConfConstant.CONF_PUBLIC_FLAG_TRUE,
					ConstantUtil.DELFLAG_UNDELETE,
					status,
					DateUtil.getGmtDate(null),
					DateUtils.addDays(DateUtil.getGmtDate(null), 7) 
			};
		}
			
		try {
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT * FROM ( ");
			sqlBuffer.append(" SELECT MIN(start_time) AS start_time2,conbase.* FROM t_conf_base conbase ");
			sqlBuffer.append(" WHERE site_id = ? AND public_flag= ? AND del_flag= ? AND cycle_id > 0  AND conf_status=?  ");
			if(ConfConstant.CONF_STATUS_SUCCESS.equals(status)){
				sqlBuffer.append("and start_time > ?");
			}
			sqlBuffer.append(" and start_time < ?");
			sqlBuffer.append(" GROUP BY cycle_id");
			sqlBuffer.append(" UNION ALL ");
			sqlBuffer.append(" SELECT start_time AS start_time2, conbase.* FROM t_conf_base conbase ");
			sqlBuffer.append(" WHERE site_id= ?  AND public_flag= ? AND del_flag= ? AND cycle_id = 0  AND conf_status=? ");

			if(ConfConstant.CONF_STATUS_SUCCESS.equals(status)){
				sqlBuffer.append("and start_time > ?");
			}
			sqlBuffer.append(" and start_time < ?");
			sqlBuffer.append(" ) tmp ");
			sqlBuffer.append(" order by tmp.start_time ASC");
			sqlBuffer.append("");
//			String sql=""+
//					 +
//					 +
//					  +
//					" SELECT start_time AS start_time2, conbase.* FROM t_conf_base conbase " + 
//					" " +
//					"" +
//					"";
			
			System.out.println("public conf "+status+"  sql--->>"+sqlBuffer.toString());
			List<ConfBase> confList = libernate.getEntityListCustomized(ConfBase.class,sqlBuffer.toString(), conditions);
//					"SELECT cycle_id,MIN(start_time),conbase.* FROM t_conf_base conbase " +
//					"WHERE site_id=? and public_flag=? and del_flag=? and conf_status=? and " +
//					"start_time < ?  GROUP BY cycle_id order by start_time asc", conditions);

			
			confList = this.getOffsetConfList(siteService.getSiteBaseById(siteId), confList);
			return confList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countOnGoingConf(int siteId) {
		return this.countConfListByStatus(siteId, ConfConstant.CONF_STATUS_OPENING);
	}

	@Override
	public int countUpComingConf(int siteId) {
		return this.countConfListByStatus(siteId,ConfConstant.CONF_STATUS_SUCCESS);
	}
	
	@SuppressWarnings("unchecked")
	private List<ConfBase> getOffsetConfList(SiteBase currentSite, List<ConfBase> confList){
		List<ConfBase> confBaseList = new ArrayList<ConfBase>();
		String[] fields = new String[]{"startTime","endTime"};
		long offset = 0 ;
		if(currentSite != null){
			offset = currentSite.getTimeZone();
		}else{
			offset = DateUtil.getDateOffset();
		}
		logger.info("当前访问的站点时区" + offset);
		confBaseList = ObjectUtil.offsetDateWithList(confList, offset, fields);
		return confBaseList;
	}

	private int countConfListByStatus(int siteId, int status) {
		Object[] conditions = new Object[] { siteId,
				ConfConstant.CONF_PUBLIC_FLAG_TRUE,
				ConstantUtil.DELFLAG_UNDELETE,status,DateUtils.addDays(DateUtil.getGmtDate(null), 7) };
		try {
			int count = libernate.countEntityListWithSql(
					"select count(1) from (SELECT MIN(start_time),conbase.* FROM t_conf_base conbase " +
					"WHERE site_id=? and public_flag=? and del_flag=? and conf_status=? and " +
					"start_time < ?  GROUP BY cycle_id) confCount", conditions);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 公开会议
	 * 获取正在召开的会议列表
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getOpeningConfList(String confName, SiteBase currentSite, int pageNo, Date beginTime, Date endTime){
		return getPublicConfList(confName, currentSite, ConfConstant.CONF_STATUS_OPENING, beginTime, endTime, pageNo);
	}
	
	/**
	 * 公开会议
	 * 获取即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getUpComingConfList(String confName, SiteBase currentSite, int pageNo, Date beginTime, Date endTime){
		return getPublicConfList(confName, currentSite, ConfConstant.CONF_STATUS_SUCCESS, beginTime, endTime, pageNo);
	}
	
	private PageBean<ConfBase> getPublicConfList(String confName, SiteBase currentSite, int confStatus, Date beginDate, Date endDate,int pageNo){
		logger.info("will start query the conf status ==== "+confStatus);
		long starttime = System.currentTimeMillis();
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT * FROM t_conf_base ");
		sqlBuilder.append(" WHERE site_id= ?  AND public_flag= ? AND del_flag= ? and (permanent_conf = 0 or permanent_conf = 1)");
		valueList.add(currentSite.getId());
		valueList.add(ConfConstant.CONF_PUBLIC_FLAG_TRUE);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
	
//		原公开会议列表条件 
//		sqlBuilder.append("  AND conf_status = ?  ");
//		valueList.add(confStatus);
		
//		会议状态条件开始	status=8
		if(ConfConstant.CONF_STATUS_SUCCESS.equals(confStatus)){
			sqlBuilder.append(" AND (conf_status=?   or conf_status=?)");
			valueList.add(confStatus);
			valueList.add(ConfConstant.CONF_STATUS_CREATE_FAILED);
		}
		else if(ConfConstant.CONF_STATUS_OPENING.equals(confStatus)) {
			sqlBuilder.append(" AND  conf_status=? ");
			valueList.add(confStatus);
		}
		
//		会议状态条件结束		
		
		
		
		if(StringUtil.isNotBlank(confName)){
			sqlBuilder.append(" AND conf_name LIKE ? ");
			valueList.add("%" + confName + "%");
		}
		if( ConfConstant.CONF_STATUS_SUCCESS.equals(confStatus)){
			if(beginDate != null && endDate != null){
				sqlBuilder.append(" and start_time > ? and start_time < ? ");
				valueList.add(beginDate);
				valueList.add(endDate);
			}else{
				Date startTime = DateUtil.getWeekStartDate(currentSite.getTimeZone());
				Date endTime = DateUtil.getWeekEndDate(startTime);
	//			sqlBuilder.append(" and start_time < ? ");
	//			valueList.add(DateUtils.addDays(DateUtil.getGmtDate(null), 7));
				sqlBuilder.append(" and start_time > ? and start_time < ? ");
				valueList.add(startTime);
				valueList.add(endTime);
			}
		}
		sqlBuilder.append(" ORDER BY start_time ASC ");
		PageBean<ConfBase> page = getPageBeans(ConfBase.class, sqlBuilder.toString(), pageNo, ConstantUtil.PAGESIZE_DEFAULT, true, valueList.toArray());
		if(page != null && page.getDatas() != null && page.getDatas().size() > 0){
			page.setDatas(getOffsetConfList(currentSite, page.getDatas()));
		}
		long endtime = System.currentTimeMillis();
		logger.info("total use time = " +(endtime-starttime));
		return page;
	}
}
