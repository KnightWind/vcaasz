package com.bizconf.vcaasz.component.zoom.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.component.zoom.HttpURLUtil;
import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.constant.APIReturnCode;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.MeetingStartType;
import com.bizconf.vcaasz.constant.ZoomConfStatus;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 对会议的增删改查
 * @author Administrator
 * @date 2014-6-9
 */
@Component
public class ZoomMeetingOperationComponentImpl implements
		ZoomMeetingOperationComponent {
	
	@Override
	public Map<String, Object> createMeeting(String apiKey,String apiToken,String hostId, String topic,
			int type, String startTime, int duration, String timeZone,
			String password, boolean optionJbh, String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", type);
		if (startTime != null) {
			params.put("start_time", startTime);
		}
		if (duration > 0) {
			params.put("duration", duration);// Meeting duration (minutes).
		}
		if (timeZone != null) {
			params.put("timezone", timeZone);
		}
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
			
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
				params.put("option_no_video_host", false);
				params.put("option_no_video_participants", false);
			}else{
				params.put("option_no_video_host", true);
				params.put("option_no_video_participants", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeeting,
				params);
	}
	@Override
	public Map<String, Object> createInstantMeeting(String apiKey,String apiToken,String hostId,
			String topic, String password, boolean optionJbh,
			String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 1);// 1 means instant meeting
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
		
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
					params.put("option_no_video_host", false);
					params.put("option_no_video_participants", false);
			}else{
					params.put("option_no_video_host", true);
					params.put("option_no_video_participants", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeeting,
				params);
	}
	
	@Override
	public Map<String, Object> createInstantMeetingMA(String apiKey,String apiToken,String accountId,String hostId,
			String topic, String password, boolean optionJbh,
			String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 1);// 1 means instant meeting
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
		
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
					params.put("option_no_video_host", false);
					params.put("option_no_video_participants", false);
			}else{
					params.put("option_no_video_host", true);
					params.put("option_no_video_participants", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeetingMA,
				params);
	}
	
	@Override
	public Map<String, Object> createScheduleMeeting(String apiKey,String apiToken,String hostId,
			String topic, String startTime, int duration, String timeZone,
			String password, boolean optionJbh, String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 2);// 2 means scheduled meeting
		if(startTime!=null){
			params.put("start_time", startTime);
		}
		if (duration > 0) {
			params.put("duration", duration);// Meeting duration (minutes).
		}
		if (timeZone != null) {
			params.put("timezone", timeZone);
		}
		params.put("timezone", "Asia/Shanghai");
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
			}else{
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeeting,
				params);
	}
	@Override
	public Map<String, Object> createScheduleMeetingMA(String apiKey,String apiToken,String accountId,String hostId,
			String topic, String startTime, int duration, String timeZone,
			String password, boolean optionJbh, String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 2);// 2 means scheduled meeting
		if(startTime!=null){
			params.put("start_time", startTime);
		}
		if (duration > 0) {
			params.put("duration", duration);// Meeting duration (minutes).
		}
		if (timeZone != null) {
			params.put("timezone", timeZone);
		}
		params.put("timezone", "Asia/Shanghai");
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
			}else{
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeetingMA,
				params);
	}
	@Override
	public Map<String, Object> createPermanentMeeting(String apiKey,String apiToken,String hostId,
			String topic, String password, boolean optionJbh,
			String optionStartType) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 3);// 3 means a recurring meeting
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
			
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
//				params.put("option_no_video_host", false);
//				params.put("option_no_video_participants", false);
				
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
			}else{
				
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
//				params.put("option_no_video_host", true);
//				params.put("option_no_video_participants", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeeting,
				params);
	}
	@Override
	public Map<String, Object> createPermanentMeetingMA(String apiKey,String apiToken,String accountId,String hostId,
			String topic, String password, boolean optionJbh,
			String optionStartType) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("host_id", hostId);
		params.put("topic", topic);
		params.put("type", 3);// 3 means a recurring meeting
		if (password != null) {
			params.put("password", password);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {
			params.put("option_start_type", optionStartType);
			
			// 3.0api修改了设置开始会议类型参数
			if(optionStartType.equalsIgnoreCase("screen_share")){
//				params.put("option_no_video_host", false);
//				params.put("option_no_video_participants", false);
				
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
			}else{
				
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
//				params.put("option_no_video_host", true);
//				params.put("option_no_video_participants", true);
			}
		}

		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createMeetingMA,
				params);
	}
	@Override
	public int updateMeeting(String apiKey,String apiToken,String meetingId, String hostId, String topic,
			String startTime, int duration, String timeZone, String password,
			boolean optionJbh, String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		if(topic!=null){
			params.put("topic", topic);
		}
		if(startTime!=null){
			params.put("start_time", startTime);
		}
		//params.put("type", 3);// 3 means a recurring meeting
		if (password != null) {
			params.put("password", password);
		}
		if(duration > 0){
			params.put("duration", duration);
		}
		if(timeZone!=null){
			params.put("timezone", timeZone);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {//video /screen_share
			params.put("option_start_type", optionStartType);
			if(optionStartType.equalsIgnoreCase("screen_share")){
				
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
				
//				params.put("option_no_video_host", false);
//				params.put("option_no_video_participants", false);
			}else{
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
//				params.put("option_no_video_host", true);
//				params.put("option_no_video_participants", true);
			}
		}
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateMeeting,
				params);
		
		if(retMap.get("error") == null){
			return ZoomConfStatus.UPDATE_SUCCESS;
		}
		return ZoomConfStatus.UPDATE_FAILED;
	}
	@Override
	public int updateMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId, String topic,
			String startTime, int duration, String timeZone, String password,
			boolean optionJbh, String optionStartType) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		if(topic!=null){
			params.put("topic", topic);
		}
		if(startTime!=null){
			params.put("start_time", startTime);
		}
		//params.put("type", 3);// 3 means a recurring meeting
		if (password != null) {
			params.put("password", password);
		}
		if(duration > 0){
			params.put("duration", duration);
		}
		if(timeZone!=null){
			params.put("timezone", timeZone);
		}
		params.put("option_jbh", optionJbh);
		if (optionStartType != null && !"".equals(optionStartType)) {//video /screen_share
			params.put("option_start_type", optionStartType);
			if(optionStartType.equalsIgnoreCase("screen_share")){
				
				params.put("option_host_video", false);
				params.put("option_participants_video", false);
				
//				params.put("option_no_video_host", false);
//				params.put("option_no_video_participants", false);
			}else{
				params.put("option_host_video", true);
				params.put("option_participants_video", true);
//				params.put("option_no_video_host", true);
//				params.put("option_no_video_participants", true);
			}
		}
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateMeetingMA,
				params);
		
		if(retMap.get("error") == null){
			return ZoomConfStatus.UPDATE_SUCCESS;
		}
		return ZoomConfStatus.UPDATE_FAILED;
	}
	
	@Override
	public int deleteMeeting(String apiKey,String apiToken,String meetingId, String hostId) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.deleteMeeting,
				params);
		if(retMap.get("id")!=null && !retMap.get("id").toString().equals("")){
			return ZoomConfStatus.CONF_DELETE_STATU_SUCCESS;
		}
		return ZoomConfStatus.CONF_DELETE_ERROR_UNKNOW;
	}
	@Override
	public int deleteMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.deleteMeetingMA,
				params);
		if(retMap.get("id")!=null && !retMap.get("id").toString().equals("")){
			return ZoomConfStatus.CONF_DELETE_STATU_SUCCESS;
		}
		return ZoomConfStatus.CONF_DELETE_ERROR_UNKNOW;
	}
	@Override
	public Map<String, Object> getMeetingsByHost(String apiKey,String apiToken,String hostId,
			int pageSize, int pageNo) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("host_id", hostId);
		
		if(pageSize>0 && pageSize<300){
			params.put("page_size", pageSize);//每页的条数 Defaults to 30.Max of 300 meetings
		}else if(pageSize <= 0){
			params.put("page_size", 30);
		}else if(pageSize >= 300){
			params.put("page_size", 300);
		}
		params.put("page_number", pageNo>0?pageNo:1);//页数  Default to 1
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listMeeting,
				params);
		if(tempMap.isEmpty()){
			return null;
		}
		return tempMap;
	}
	@Override
	public Map<String, Object> getMeetingsByHostMA(String apiKey,String apiToken,String accountId,String hostId,
			int pageSize, int pageNo) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("host_id", hostId);
		
		if(pageSize>0 && pageSize<300){
			params.put("page_size", pageSize);//每页的条数 Defaults to 30.Max of 300 meetings
		}else if(pageSize <= 0){
			params.put("page_size", 30);
		}else if(pageSize >= 300){
			params.put("page_size", 300);
		}
		params.put("page_number", pageNo>0?pageNo:1);//页数  Default to 1
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listMeetingMA,
				params);
		if(tempMap.isEmpty()){
			return null;
		}
		return tempMap;
	}
	@Override
	public Map<String, Object> getMeeting(String apiKey,String apiToken,String meetingId, String hostId) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getMeeting,
				params);
	}
	@Override
	public Map<String, Object> getMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getMeetingMA,
				params);
	}
	@Override
	public int getMeetingStatus(String apiKey,String apiToken,String meetingId, String hostId) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getMeeting,
				params);
		if(retMap.isEmpty() || retMap.get("status") == null){
			return -1;
		}
		int status = (Integer) retMap.get("status");
		return status;
	}
	@Override
	public int getMeetingStatusMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getMeetingMA,
				params);
		if(retMap.isEmpty() || retMap.get("status") == null){
			return -1;
		}
		int status = (Integer) retMap.get("status");
		return status;
	}

	@Override
	public boolean endMeeting(String apiKey,String apiToken,String meetingId, String hostId) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.endMeeting,
				params);
		if(retMap!=null && retMap.get("id") != null && !retMap.get("id").toString().equals("")){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean endMeetingMA(String apiKey,String apiToken,String accountId,String meetingId, String hostId) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", meetingId);
		params.put("host_id", hostId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.endMeetingMA,
				params);
		if(retMap!=null && retMap.get("id") != null && !retMap.get("id").toString().equals("")){
			return true;
		}
		return false;
	}

	@Override
	public List<ConfBase> getHostMeetinglist(String apiKey,String apiToken,String hostId) {
		List<ConfBase> confs = new ArrayList<ConfBase>();
		try{
			Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
			params.put("api_key", apiKey);
			params.put("api_secret", apiToken);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int pagesize = 50;
			int pageCount = 0;
			int pageNo = 1;
			do {
				params.put("page_size", pagesize);
				params.put("page_number", pageNo);
				params.put("host_id", hostId);
				Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listMeeting,
						params);
				if(retMap.get("page_count")!=null){
					 net.sf.json.JSONArray meets =  (net.sf.json.JSONArray)retMap.get("meetings");
					 
					 for (Iterator iterator = meets.iterator(); iterator
							.hasNext();) {
						 net.sf.json.JSONObject meeting = ( net.sf.json.JSONObject) iterator.next();
						 
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
							
							//说明该会议开始时间是在24小时之前 并且不是周期会议,则剔除该会议
//							if((DateUtil.getGmtDate(null).getTime() - newConf.getStartTime().getTime()) >24*3600*1000l && newConf.getConfType() != ConfType.RECURR){
//								System.out.println( "skip conf == "+newConf.getConfName());
//								continue;
//							} else{
//								System.out.println( "add conf == "+newConf.getConfName());
//							}
							
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
							
							//3.0 api 升级修改
							String option_start_type = String.valueOf(meeting
									.get("option_host_video"));
							if (option_start_type.equals("true")) {
								newConf.setOptionStartType(MeetingStartType.VIDEO.getStatus());
							}else{
								newConf.setOptionStartType(MeetingStartType.SCREEN_SHARE.getStatus());
							}
							newConf.setHostKey(String.valueOf(meeting.get("password")));
							
							
							confs.add(newConf);
							
					}
					pageCount = Integer.parseInt(retMap.get("page_count").toString());
					pageNo ++;
				}
				
			} while (pageCount>(pageNo-1));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return confs;
	}
	@Override
	public List<ConfBase> getHostMeetinglistMA(String apiKey,String apiToken,String accountId,String hostId) {
		List<ConfBase> confs = new ArrayList<ConfBase>();
		try{
			Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
			params.put("api_key", apiKey);
			params.put("api_secret", apiToken);
			params.put("account_id", accountId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int pagesize = 50;
			int pageCount = 0;
			int pageNo = 1;
			do {
				params.put("page_size", pagesize);
				params.put("page_number", pageNo);
				params.put("host_id", hostId);
				Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listMeetingMA,
						params);
				if(retMap.get("page_count")!=null){
					 net.sf.json.JSONArray meets =  (net.sf.json.JSONArray)retMap.get("meetings");
					 
					 for (Iterator iterator = meets.iterator(); iterator.hasNext();) {
						 net.sf.json.JSONObject meeting = ( net.sf.json.JSONObject) iterator.next();
						 
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
							
							//说明该会议开始时间是在24小时之前 并且不是周期会议,则剔除该会议
//							if((DateUtil.getGmtDate(null).getTime() - newConf.getStartTime().getTime()) >24*3600*1000l && newConf.getConfType() != ConfType.RECURR){
//								System.out.println( "skip conf == "+newConf.getConfName());
//								continue;
//							} else{
//								System.out.println( "add conf == "+newConf.getConfName());
//							}
							
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
							
							//3.0 api 升级修改
							String option_start_type = String.valueOf(meeting
									.get("option_host_video"));
							if (option_start_type.equals("true")) {
								newConf.setOptionStartType(MeetingStartType.VIDEO.getStatus());
							}else{
								newConf.setOptionStartType(MeetingStartType.SCREEN_SHARE.getStatus());
							}
							newConf.setHostKey(String.valueOf(meeting.get("password")));
							confs.add(newConf);
					}
					pageCount = Integer.parseInt(retMap.get("page_count").toString());
					pageNo ++;
				}
				
			} while (pageCount>(pageNo-1));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return confs;
	}

	@Override
	public boolean modifyMeetingPwd(String apiKey,String apiToken,String meetingId, String host_id, String pwd) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		if(StringUtil.isEmpty(meetingId)){
			return false;
		}
		if(StringUtil.isEmpty(host_id)){
			return false;
		}
		params.put("id", meetingId);
		params.put("host_id", host_id);
		 
		if (StringUtil.isEmpty(pwd)) {
			params.put("password", "");
		}else{
			params.put("password", pwd);
		}
		 
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateMeeting,
				params);
		
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}
	@Override
	public boolean modifyMeetingPwdMA(String apiKey,String apiToken,String accountId,String meetingId, String host_id, String pwd) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		if(StringUtil.isEmpty(meetingId)){
			return false;
		}
		if(StringUtil.isEmpty(host_id)){
			return false;
		}
		params.put("id", meetingId);
		params.put("host_id", host_id);
		 
		if (StringUtil.isEmpty(pwd)) {
			params.put("password", "");
		}else{
			params.put("password", pwd);
		}
		 
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateMeetingMA,
				params);
		
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}
	
	@Override
	public int pairH323OrSipTerminal(String apiKey,String apiToken,String id, String hostId,
			String pairCode) {
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(hostId) 
				|| StringUtil.isEmpty(pairCode)){
			
			return APIReturnCode.CALLING_FAILED;
		}
			
		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", id);
		params.put("host_id", hostId);
		params.put("pairing_code", pairCode);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.PAIRH323_OR_SIP,
				params);
		if(retMap.get("id")!=null && !"".equals(retMap.get("id").toString())){
			return APIReturnCode.RET_SUCCESS;
		
		}else if(retMap.get("error") !=null ){
			Map<String, Object> errorMap = (Map<String, Object>)retMap.get("error");
			return Integer.parseInt(errorMap.get("code").toString());
		}
		return APIReturnCode.CALLING_FAILED;
	}

}
