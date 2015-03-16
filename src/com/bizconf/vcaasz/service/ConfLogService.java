package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfReportInfo;
import com.bizconf.vcaasz.entity.MonthlyReport;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.ZoomConfRecord;

public interface ConfLogService {
	
	/**
	 * 保存进出会日志
	 * @param confLog
	 * @return
	 */
	public boolean saveConfLog(ConfLog confLog);
	
	/**
	 * 批量保存进出会日志
	 * @param logList
	 * @return
	 */
	public boolean batchSaveConfLog(List<ConfLog> logList);
	
	/**
	 * 删除进会日志
	 * @param confLog
	 * @return
	 */
	public boolean delConfLog(ConfLog confLog);
	
	
	/**
	 * 根据会议ID号查询与会者进出会日志
	 * @param confId
	 * @return
	 */
	public List<ConfLog> getLogListByConfId(Integer confId);
	
	
	/**
	 * 根据用户的ID号查询用户的进出会日志
	 * @param userId
	 * @return
	 */
	public List<ConfLog> getLogListByUserId(Integer userId);
	
	/**
	 * 根据会议ID号统计参会人数
	 * @param confId
	 * @return
	 * 		Integer[0]  会议ID
	 * 		Integer[1]	参会人总人数
	 * 		Integer[2]	PC用户数
	 * 		Integer[3]  电话用户数
	 * 
	 */
	public Integer[] countUserByConfId(Integer confId);

	/**
	 * 根据会议ID数组,统计参会人数
	 * @param confIds
	 * @return
	 * 		Integer[0]  会议ID
	 * 		Integer[1]	参会人总人数
	 * 		Integer[2]	PC用户数
	 * 		Integer[3]  电话用户数
	 * 
	 */
	public List<Integer[]> countUserListByConfId(Integer[] confIds);
	
	
	
	/**
	 * 获取某个用户参加的会议记录
	 * @param confs
	 * @param creatorId
	 * @return
	 */
	Map<Integer, ConfLog> getConfLogDataMap(List<ConfBase> confs,Integer creatorId);
	
	
	/**
	 * 通过会议ID获取会议日志
	 * @param confId
	 * @return
	 */
	PageBean<ConfReportInfo> getLogsByConf(Integer confId, int pageSize, Integer pageNo,String sortField,String sortValue);
	
	/**
	 * 通过会议ID获取会议日志
	 * @param confId
	 * @return
	 */
	List<ConfLog> getAllLogsByConf(Integer confId,String sortField,String sortRule);
	/**
	 * 获取会议-参会人次map
	 * @param list
	 * @return
	 */
	Map<Long, Integer> getConflogNumByConf(List<ZoomConfRecord> list);
	
	/**
	 * 按会议统计会议人次
	 * @param confs
	 * @return
	 */
	int countConfLogsByConfs(List<ConfBase> confs);

	/**
	 * 获取全部会议报告详情
	 * @param userId 
	 * @param pageSize 
	 * */
	public PageBean<ZoomConfRecord> getlogsPageNo(Integer userId, String keyword, Integer pageNo,
			String startTime, String endTime, int pageSize);

	/**
	 * 获取指定会议报告参会者详情 参会人
	 * @param confId 
	 * */
	public PageBean<Object> getLogsByLogId(Integer confLogId,Integer confId, int pageNo,int pageSize);


	public MonthlyReport getMonthlyTotalReport(int userId,Date startDate,Date endDate);
}
