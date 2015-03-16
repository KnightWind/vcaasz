package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteMeetingParticipantsNum;

/** 
 *   
 * @package com.bizconf.vcaasz.service 
 * @description: TODO
 * @author Martin
 * @date 2014年8月25日 上午10:29:51 
 * @version 
 */
public interface StatisticsService {
	
	/**
	 * 按时间段统计会议场次
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int countFlishedMeetingsByDate(Date startDate, Date endDate,int siteId);
	
	
	/**
	 * 获取某站点内正在进行的会议
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int countRunningMeetingsBySite(int siteId);
	
	
	/**
	 * 获取所有的正则进行的会议的总数
	 * @return
	 */
	public int countAllRunningMeetings();
	
	
	
	
	/**
	 * 统计时间段内的会议总时长
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int countMeetingTimesByDate(Date startDate, Date endDate,int siteId);
	
	
	
	/**
	 * 统计时间段内的总参会人次
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int countMeetingParticipartsByDate(Date startDate, Date endDate,int siteId);
	
	
	
	/**
	 * 获取正在进行的会议 包括按站点查询正在进行的会议
	 * @param startDate
	 * @param pageNo
	 * @param pageSize
	 * @param endDate
	 * @param siteId
	 * @return
	 */
	public PageBean<ConfBase> getRunningMeetingsBySite(int pageNo,int pageSize,int siteId);
	
	
	/**
	 * 获取系统所有正在进行的会议列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<ConfBase> getAllRunningMeetings(int pageNo,int pageSize);
	
	
	
	/**
	 * 查询某一时间段内站点会议场数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public PageBean<SiteMeetingParticipantsNum> getSiteMeetingsNumber(Date startDate,Date endDate,int pageNo,int pageSize);

	
	/**
	 * 查询某一时间段内站点会议场数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SiteMeetingParticipantsNum> getSiteMeetingsNumber(Date startDate,
			Date endDate);
	
	/**
	 * 统计某一时间段内某个站点的参会人次
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<SiteMeetingParticipantsNum> getSiteParticipantsNum(Date startDate,
			Date endDate,int pageNo,int pageSize);
	
	
	/**
	 * 统计某一时段内某个站点的参会人次
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<SiteMeetingParticipantsNum> getSiteParticipantsNum(Date startDate,
			Date endDate);
	
	
	
	/**
	 * 查询所有召开过的会议场次
	 * @return
	 */
	public int countAllMeetings();
	
	
	/**
	 * 查询某站点下所有的会议场次
	 * @param siteId
	 * @return
	 */
	public int countSiteAllMeetings(int siteId);
	
	/**
	 * 查询系统所有的参会人次
	 * @return
	 */
	public int countAllParticipants();
	
	/**
	 *  统计站点的总共参会人次
	 * @return
	 */
	public int countSiteAllParticipants(int siteId);
	
	
	/**
	 * 查询系统总共的参会时长
	 * @return
	 */
	public int countAllMeetingsTime();
	
	
	/**
	 * 查询某站点所有的会议时间
	 * @param siteId
	 * @return
	 */
	public int countSiteAllMeetingsTime(int siteId);
	
	
	/**
	 * 分页查询主持人-会场数的统计数据
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<SiteMeetingParticipantsNum> getHostMeetingsNumber(int siteId,Date startDate,
			Date endDate,int pageNo,int pageSize);
	
	
	
	/**
	 * 分页查询主持人-参会人次的统计数据
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<SiteMeetingParticipantsNum> getHostParticipantNumber(int siteId,Date startDate,
			Date endDate,int pageNo,int pageSize);
}
