package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteMeetingParticipantsNum;
import com.bizconf.vcaasz.service.StatisticsService;

/** 
 *   
 * @package com.bizconf.vcaasz.service.impl 
 * @description: TODO
 * @author Martin
 * @date 2014年8月25日 上午10:43:39 
 * @version 
 */
@Service
public class StatisticsServiceImpl extends BaseService implements StatisticsService  {
	
	@Override
	public int countFlishedMeetingsByDate(Date startDate, Date endDate, int siteId) {
		 
		ArrayList<Object> parms = new ArrayList<Object>();
		
		String sql = "select count(*) from t_zoom_conf_record conf where conf.duration>0 and conf.start_time between ? and ? ";
		parms.add(endDate);
		parms.add(startDate);
		if(siteId>0){
			sql += " and  conf.site_id = ? ";
			parms.add(siteId);
		}
		try {
			
			return libernate.countEntityListWithSql(sql, parms.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countMeetingTimesByDate(Date startDate, Date endDate,int siteId) {
		 
		ArrayList<Object> parms = new ArrayList<Object>();
		
		String sql = "select sum(duration) from t_zoom_conf_record conf where conf.duration>0 and conf.start_time between ? and ? ";
		parms.add(endDate);
		parms.add(startDate);
		if(siteId>0){
			sql += " and  conf.site_id = ? ";
			parms.add(siteId);
		}
		
		try {
			return libernate.countEntityListWithSql(sql, parms.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countMeetingParticipartsByDate(Date startDate, Date endDate,int siteId) {
		
		String  sql = "select count(*) from t_conf_report_info where conf_log_id in (select id from t_zoom_conf_record conf where conf.duration>0 and conf.start_time between ? and ?";
		ArrayList<Object> parms = new ArrayList<Object>();
		parms.add(endDate);
		parms.add(startDate);
		if(siteId>0){
			sql += " and  conf.site_id = ? ";
			parms.add(siteId);
		}
		sql += ")";
		try {
			return libernate.countEntityListWithSql(sql, parms.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countRunningMeetingsBySite(int siteId) {
		String sql = "select count(*) from t_conf_base where del_flag=? and conf_status=? ";
		ArrayList<Object> parms = new ArrayList<Object>();
		parms.add(ConstantUtil.DELFLAG_UNDELETE);
		parms.add(ConfStatus.LIVING.getStatus());
		
		if(siteId>0){
			sql += " and site_id = ?";
			parms.add(siteId);
		}
		try {
			return libernate.countEntityListWithSql(sql, parms.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取正在进行的会议
	 * @param startDate
	 * @param pageNo
	 * @param pageSize
	 * @param endDate
	 * @param siteId
	 * @return
	 */
	@Override
	public PageBean<ConfBase> getRunningMeetingsBySite(int pageNo,int pageSize,int siteId) {
		String sql = "select * from t_conf_base where del_flag=? and conf_status=?  ";
		ArrayList<Object> parms = new ArrayList<Object>();
		parms.add(ConstantUtil.DELFLAG_UNDELETE);
		parms.add(ConfStatus.LIVING.getStatus());
		
		if(siteId>0){
			sql += " and site_id = ?";
			parms.add(siteId);
		}
		try {
			return getPageBeans(ConfBase.class, sql, pageNo, pageSize, parms.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PageBean<SiteMeetingParticipantsNum> getSiteMeetingsNumber(Date startDate,
			Date endDate,int pageNo,int pageSize) {
		
		String sql  = "select site_id, count(*) meeting_num from t_zoom_conf_record where duration>0 and start_time between ? and ? GROUP BY site_id";
		
		PageBean<SiteMeetingParticipantsNum> page = getPageBeans(SiteMeetingParticipantsNum.class, sql, pageNo, pageSize,new Object[]{startDate,endDate});
		return page;
	}
	
	
	
	 
	public PageBean<SiteMeetingParticipantsNum> getHostMeetingsNumber(int siteId,Date startDate,
			Date endDate,int pageNo,int pageSize) {
		
		String sql  = "select * from (select host_id, count(*) meeting_num from "
				+ "t_zoom_conf_record where duration>0 and site_id = ? and start_time between ? and ? GROUP BY host_id) as temp";
		PageBean<SiteMeetingParticipantsNum> page = getPageBeans(SiteMeetingParticipantsNum.class, sql,
				pageNo, pageSize,new Object[]{siteId,startDate,endDate});
		return page;
	}
	
	
	
	
	
	
	public List<SiteMeetingParticipantsNum> getSiteMeetingsNumber(Date startDate,
			Date endDate) {
		String sql  = "select site_id, count(*) meeting_num from t_zoom_conf_record where duration > 0 and  start_time between ? and ? GROUP BY site_id";
		try {
			List<SiteMeetingParticipantsNum> page = libernate.getEntityListCustomized(SiteMeetingParticipantsNum.class, sql, new Object[]{startDate,endDate});
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<SiteMeetingParticipantsNum> getSiteParticipantsNum(Date startDate,
			Date endDate,int pageNo,int pageSize){
		
		String sql  = "select count(info.id) as participants_num,conf.site_id from t_conf_report_info info, "+
				"(select id ,site_id from t_zoom_conf_record conf where conf.start_time between ? and ? )  as conf  "+
				"where info.conf_log_id = conf.id "+
				"GROUP BY conf.site_id ";
		PageBean<SiteMeetingParticipantsNum> page = getPageBeans(SiteMeetingParticipantsNum.class, sql, pageNo, pageSize,new Object[]{});
		return page;
	}
	
	
	
	
	public int countAllMeetings(){
		String sql = "select count(*) from t_zoom_conf_record conf where conf.duration>0";
		try {
			int count  = libernate.queryForInt(sql);
			return count;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int countAllParticipants(){
		
		String sql = "select count(*) from t_conf_report_info";
		try {
			int count  = libernate.queryForInt(sql);
			return count;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int countAllMeetingsTime(){
		
		String sql = "select sum(duration) from t_zoom_conf_record ";
		try {
			int count  = libernate.queryForInt(sql);
			return count;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countAllRunningMeetings() {
		 
		return countRunningMeetingsBySite(0);
	}
	
	@Override
	public PageBean<ConfBase> getAllRunningMeetings(int pageNo, int pageSize) {
	 
		return getRunningMeetingsBySite(pageNo, pageSize, 0);
	}

	@Override
	public List<SiteMeetingParticipantsNum> getSiteParticipantsNum(
			Date startDate, Date endDate) {
		
		String sql  = "select count(info.id) as participants_num,conf.site_id from t_conf_report_info info, "+
				"(select id ,site_id from t_zoom_conf_record conf where conf.start_time between ? and ? )  as conf  "+
				"where info.conf_log_id = conf.id "+
				"GROUP BY conf.site_id ";
		try {
			return libernate.getEntityListCustomized(SiteMeetingParticipantsNum.class, sql, new Object[]{startDate,endDate});
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return null;
	}

	
	
	@Override
	public PageBean<SiteMeetingParticipantsNum> getHostParticipantNumber(int siteId,
			Date startDate, Date endDate, int pageNo, int pageSize) {
		
		// TODO Auto-generated method stub
		String sql  = "select * from (select count(info.id) as participants_num,conf.host_id from t_conf_report_info info, "+
				"(select id ,host_id from t_zoom_conf_record conf where conf.site_id = ? and conf.start_time between ? and ? )  as conf  "+
				"where info.conf_log_id = conf.id "+
				"GROUP BY conf.host_id ) as temp";
		
		PageBean<SiteMeetingParticipantsNum> page = getPageBeans(SiteMeetingParticipantsNum.class, sql, pageNo, pageSize,new Object[]{siteId,startDate,endDate});
		return page;
	}

	@Override
	public int countSiteAllMeetings(int siteId) {
		String sql = "select count(*) from t_zoom_conf_record conf where conf.duration>0 and conf.site_id ="+siteId;
		try {
			int count  = libernate.queryForInt(sql);
			return count;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countSiteAllMeetingsTime(int siteId) {
		String sql = "select sum(duration) from t_zoom_conf_record where site_id ="+siteId ;
		try {
			int count  = libernate.queryForInt(sql);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countSiteAllParticipants(int siteId) {
		
		String sql = "select count(*) from t_conf_report_info t "
				+ "where exists(select * from t_zoom_conf_record conf where conf.site_id = ? and conf.id=t.conf_log_id)";
		try {
			int count  = libernate.queryForInt(sql,new Object[]{siteId});
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
