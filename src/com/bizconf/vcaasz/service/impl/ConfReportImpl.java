package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.ReportWriter;
import com.bizconf.vcaasz.component.zoom.ZoomCDROperationComponent;
import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfNotification;
import com.bizconf.vcaasz.entity.ConfReportInfo;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.User;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ZoomConfRecord;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.ConfReportService;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 会议报告处理
 * @author Administrator darren
 * @date 2014-6-25
 */
@Service
public class ConfReportImpl extends BaseService implements ConfReportService {

	private final Logger logger = Logger.getLogger(ConfReportImpl.class);
	
	@Autowired
	ZoomMeetingOperationComponent meetingOperationComponent;
	@Autowired
	ZoomCDROperationComponent cdrOperationComponent;
	
	@Autowired
	UserLogic userLogic;
	
	@Autowired
	DataCenterService dataCenterService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveConfReportInfo(String zoomId, String hostId) {
		try {
			SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 计算开始时间是从当前时间的前一天，截止时间是当天时间
			long currms = System.currentTimeMillis();
			Date dateFromDate = new Date(currms - 24*3600*1000l);
			Date dateToDate = new Date(currms + 24*3600*1000l);

			//记录消息到文件
			ReportWriter.getInstance().writerData("will get report :"+new Date());
			
			UserBase host = userLogic.getUserBaseByZoomId(hostId);
			if(host == null){
				logger.info("The host doestn't exsit-->hostId="+hostId);
				return false;
			}
			DataCenter center = dataCenterService.queryDataCenterById(host.getDataCenterId());
			
			
			//根据用户请求API获取一段时间内的会议报告
			Map<String, Object> retMap = cdrOperationComponent
					.getZoomCDRByUser(center.getApiKey(),center.getApiToken(), hostId, sdfDateFormat.format(dateFromDate), sdfDateFormat.format(dateToDate), 80, 0);// pageSize
			ReportWriter.getInstance().writerData(retMap);
			
			if(retMap.isEmpty() || retMap.get("error") != null) {
				logger.info("请求API返回是空或者错误-->retMap="+retMap);
				return false;
			}

			//判断t_conf_log数据表中是否存在，过滤去重 
//			ConfReportInfo report = getConfLogByZoomId(zoomId);
//			if(report!=null){
//				logger.info("数据表中存在-->zoomId="+zoomId);
//				return false;
//			}
			List<Map<String, Object>> meetings = (List<Map<String, Object>>) retMap
					.get("meetings");
			logger.info("meetings ==>"+meetings);
			
			if(meetings.isEmpty()){
				return false;
			}
			
			
			
			for (Map<String, Object> meet : meetings) {
				
				//查找刚才结束的会议，数据表中不存在,就存放到数据库中
				if(zoomId.equals(meet.get("id").toString())){//zoomId 与请求API的会议id进行比对
					/** 2014-06-30 add */
					//根据zoomId在t_conf_base中取出关于会议的信息(主要是取出主持人的信息)
					ConfBase confBase = getConfBaseInfoByZoomIdAndHost(zoomId, host.getId());
					
					/** 2014-06-30 add */
					ZoomConfRecord record = new ZoomConfRecord();
					
					String startTime = (String) meet.get("start_time");// 会议开始时间start_time
					String endTime = (String) meet.get("end_time");// 会议开始时间end_time
					startTime = startTime.replace("T", " ").replace("Z", "");
					endTime = endTime.replace("T", " ").replace("Z", "");
//					int timezone  = 28800000;
//					if(confBase!=null){
//						timezone = confBase.getTimeZone();
//					}
//					Date startDate = DateUtil.getGmtDateByTimeZone(sdf.parse(startTime),timezone);
//					Date endDate = DateUtil.getGmtDateByTimeZone(sdf.parse(endTime), timezone);
					Date startDate = sdf.parse(startTime);
					Date endDate = sdf.parse(endTime);
						
					record.setZoomId(String.valueOf(meet.get("id")));// zoomId
					record.setTopic((String)meet.get("topic"));//会议主题
					record.setDuration(Integer.valueOf(meet.get("duration").toString()));//会议时长
					record.setStartTime(startDate);//会议开始时间
					record.setEndTime(endDate);//会议结束时间
					/** 2014-06-30 add */
					record.setConfId(confBase.getId());//设置会议id
					//设置主持人ID
					record.setHostId(host.getId());
					
					record.setSiteId(host.getSiteId());
					/** 2014-06-30 add */
					
					if(recordSaveable(record.getZoomId(), startDate, endDate)){
						/** 保存会议记录 */
						ZoomConfRecord saveRecord = libernate.saveEntity(record);//
						// participants与会者集合
						List<Map<String, Object>> participants = (List<Map<String, Object>>) meet
								.get("participants");
						//TODO 获取一下当前用户的默认设置
						for (Map<String, Object> p : participants) {//获取jointime、leaveTime、confId、用户名
							ConfReportInfo reportInfo = new ConfReportInfo();
							reportInfo.setParticipantName(p.get("name").toString());// 参会者名字
	
							String joinTime = (String) p.get("join_time");
							String leaveTime = (String) p.get("leave_time");
							joinTime = joinTime.replace("T", " ").replace("Z", "");
							leaveTime = leaveTime.replace("T", " ").replace("Z", "");
							//入会时间有时差
							Date joinDate = sdf.parse(joinTime);
							Date leaveDate = sdf.parse(leaveTime);
							
//							joinDate = DateUtil.getOffsetDateByGmtDate(joinDate, confBase.getTimeZone().longValue());
//							leaveDate = DateUtil.getOffsetDateByGmtDate(leaveDate, confBase.getTimeZone().longValue());
							reportInfo.setpStartTime(joinDate);// 入会时间
							reportInfo.setpLeaveTime(leaveDate);// 离会时间
							
							reportInfo.setConfLogId(saveRecord.getId());//会议记录id
							libernate.saveEntity(reportInfo);
						}
					}
					
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 通过zoomId取出会议的相关信息
	 * */
	public ConfBase getConfBaseInfoByZoomId(String zoomId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_base where conf_zoom_id = ? ");
		Object[] values = new Object[1];
		values[0] = zoomId;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		
		return confBase;
	}
	
	/**
	 * 通过zoomId取出会议的相关信息
	 * */
	public ConfBase getConfBaseInfoByZoomIdAndHost(String zoomId,int hostId) {
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_base where conf_zoom_id = ? and  compere_user = ? ");
		Object[] values = new Object[2];
		values[0] = zoomId;
		values[1] = hostId; 
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		return confBase;
	}


	/**
	 * 通过zoomId获取会议报告信息
	 * */
	public ConfReportInfo getConfLogByZoomId(String zoomId){
		ConfReportInfo reportInfo = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_log WHERE zoom_id = ? ");
		Object[] values = new Object[1];
		values[0] = zoomId;
		try {
			reportInfo = libernate.getEntityCustomized(ConfReportInfo.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		return reportInfo;
	}


	@Override
	public ConfNotification saveNotification(ConfNotification confNotification) {
		
		try {
			return libernate.saveEntity(confNotification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断该会议的会议报告是否存在
	 * @return
	 */
	public boolean recordSaveable(String zoomId, Date startDate,Date endDate){
		if(StringUtil.isEmpty(zoomId) || startDate == null || endDate == null) return false;
		String sql = "select count(*) from t_zoom_conf_record where zoom_id = ? and start_time = ? and  end_time=?";
		int count = 0;
		try {
			count = libernate.countEntityListWithSql(sql, new Object[]{zoomId,startDate,endDate});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count>0) return false;
		
		return true;
	}
	
}
