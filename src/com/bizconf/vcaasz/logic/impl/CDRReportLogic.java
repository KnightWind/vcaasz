package com.bizconf.vcaasz.logic.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.component.zoom.HttpURLUtil;
import com.bizconf.vcaasz.component.zoom.ZoomCDROperationComponent;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfReportInfo;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.ZoomConfRecord;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberd.Libernate;

/**
 * @desc 
 * @date 2014-10-23
 */
@Service
public class CDRReportLogic {

	private static Logger logger = Logger.getLogger(CDRReportLogic.class);
	protected static Libernate libernate = DAOProxy.getLibernate();
	private static final ZoomCDROperationComponent cdrOperationComponent = AppContextFactory.getAppContext().getBean(ZoomCDROperationComponent.class);
	private static final ConfService confService = AppContextFactory.getAppContext().getBean(ConfService.class);
	private static final UserLogic userLogic = AppContextFactory.getAppContext().getBean(UserLogic.class);
	private static final DataCenterService dataCenterService = AppContextFactory.getAppContext().getBean(DataCenterService.class);
	
	//CDR存放路径
	static String CDRPATH = "/data/billingdata/cdr";//生成CDR文件保存路径、名字
	static String ERRORPATH = "/data/billingdata/errorcdr";//生成CDR出现错误存放的路径
//	static String CDRPATH = "C:\\cdr";//生成CDR文件保存路径、名字
//	static String ERRORPATH = "C:\\errorcdr";
	
	//CDR生成文件的前缀和后缀
	final static String PREFX_CDR_NAME = "CDR";
	final static String PREFX_ERRORCDR_NAME = "ERRORCDR_";
	final static String SUFFIX_NAME = ".txt";
	
	private File dirFile = null;
	public File getDirFile() {
		return dirFile;
	}
	public void setDirFile(File dirFile) {
		this.dirFile = dirFile;
	}


	public CDRReportLogic() {
		//进行目录创建
		CDRPATH = BaseConfig.getInstance().getString("cdrdatastorepath","/data/billingdata/cdr");//CDR存放路径
		ERRORPATH = BaseConfig.getInstance().getString("cdrdataexception","/data/billingdata/errorcdr");//CDR出现错误存放的路径
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		String cdrDirName = CDRPATH+File.separator;
		String cdrName = PREFX_CDR_NAME+now+SUFFIX_NAME;
		try {
			//创建目录包括父目录
			File cdrDir = new File(CDRPATH + File.separator);
			if(!cdrDir.exists()){
				cdrDir.mkdirs();
			}
			//创建文件
			File cdrFile = new File(cdrDirName+cdrName);
			if(!cdrFile.exists()){
				cdrFile.createNewFile();
			}
			setDirFile(cdrFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CDRReportLogic getInstance(){
		return new CDRReportLogic();
	}
	
	
	/**
	 * 在zoom数据库中查询出全部主持人
	 * */
	public static Map<String, Object> listAll(String apiKey,String apiToken,int pageSize, int pageNumber) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		if(pageSize>0 && pageSize<300){
			params.put("page_size", pageSize);//每页的条数 Defaults to 30.Max of 300 meetings
		}else if(pageSize <= 0){
			params.put("page_size", 30);
		}else if(pageSize >= 300){
			params.put("page_size", 300);
		}
		params.put("page_number", pageNumber>0?pageNumber:1);//页数  Default to 1
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listUser,
				params);
		if(tempMap.isEmpty()){
			return null;
		}
		return tempMap;
	}
	
	/**
	 * 根据指定时间生成cdr文件需要的数据格式
	 * */
	public static boolean setterCDR(Map<String, Object> map,BufferedWriter ow,int lineNum){
		
		boolean flag = true;
		
		StringBuilder lineString = new StringBuilder("");
//		lineNum ++;//5位
		String zoomConfId = (String) map.get("zoomConfId");//20位
		String userEmail = (String) map.get("userEmail");//20位--修改成50位
		String startTime = (String) map.get("startTime");//19位
		String endTime = (String) map.get("endTime");//19位
		String duration = (String) map.get("duration");//11位
		String phoneNum = (String) map.get("phoneNum");//50位
		String confType = (String) map.get("confType");//1位
		String callType = (String) map.get("callType");//2位
		String DNIS = (String) map.get("DNIS");//20位
		String memberType = (String) map.get("memberType");//1位
		String name = (String) map.get("name");//50位
		String recordId = (String) map.get("recordId");//11位
		
		String line = String.valueOf(lineNum);
		line = appendSpace(line, 5);
		zoomConfId = appendSpace(zoomConfId, 20);
		userEmail = appendSpace(userEmail, 50);//20位--修改成50位
		duration = appendSpace(duration, 11);
		phoneNum = appendSpace(phoneNum, 50);
		DNIS = appendSpace(DNIS, 20);
		startTime = appendSpace(startTime, 19);
		endTime = appendSpace(endTime, 19);
		callType = appendSpace(callType, 2);
		name = appendSpace(name, 50);
		//recordId
		recordId = appendSpace(recordId, 11);
		
		lineString = lineString.append(line).append(zoomConfId).append(userEmail).append(startTime).append(endTime)
						.append(duration).append(phoneNum).append(confType).append(callType).append(DNIS).append(memberType);
		lineString = lineString.append(name).append(recordId);
		//写入文件
		flag = setterFile(ow,lineString);
		return flag;
	}
	
	/**
	 * 根据组织好的形式生成CDR文件
	 * @throws IOException 
	 * */
	private static boolean setterFile(BufferedWriter ow,StringBuilder lineStr){
		
		try {
			String temp = lineStr.toString()==null?"":lineStr.toString();//写入内容
			ow.write(temp);
			ow.newLine();
			ow.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取字符串字节长度
	 * */
	public static int getWordCount(String s){
        int length = 0;
        for(int i = 0; i < s.length(); i++){
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)
                length ++;
            else
                length += 2;
        }
        return length;
	        
	}
	
	/**
	 * 向指定的字符串后追加空格
	 * */
	public static String appendSpace(String string,int limit){
		try {
			if(string == null){
				string = "";
			}
			string = string.trim();
			int strLen = string.getBytes("GBK").length;
			
			while (strLen > limit) {
				string = string.substring(0,string.length()-1);
				strLen = string.getBytes("GBK").length;
			}
			
			StringBuilder builder = new StringBuilder(string);
			for(int i = strLen;i < limit;i++){
				builder.append(" ");
			}
			return builder.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public boolean PerProcess(String dateFrom, String dateTo, int pageSize,
			int pageNumber) {

		int lineNum = 1;//行号
		BufferedWriter ow = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<String> errors = new ArrayList<String>();
			File file = getDirFile();
			ow = new BufferedWriter(new FileWriterWithEncoding(file,"GBK",true));
			
			boolean flag = true;
			
			//查询全部数据中心
			List<DataCenter> dataCenters = dataCenterService.queryAll();
			if(dataCenters == null || dataCenters.size()==0){
				logger.info("The Data Center size is 0");
				return false;
			}
			
			//Update by Darren 2014-12-24
			List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
			for(DataCenter center:dataCenters){
				Map<String, Object> reportMap = listAll(center.getApiKey(),center.getApiToken(),pageSize, pageNumber);
				if(reportMap.isEmpty()|| reportMap.get("error")!=null){
					logger.info("The user reportMap doesnot exsit>>>>reportMap zoom return error reason:"+reportMap.get("error"));
					errors.add("The user reportMap doesnot exsit>>>>reportMap zoom return error reason:"+reportMap.get("error"));
					continue;
				}
				int pCount = (Integer) reportMap.get("page_count");
				int pNum = (Integer) reportMap.get("page_number");
				List<Map<String, Object>> reportMaps = (List<Map<String, Object>>) reportMap.get("users");
				
				//如果每页的数量超过指定数目，需要再次请求
				while(pNum < pCount){
					pNum ++;
					Map<String, Object> reportTempMap = listAll(center.getApiKey(),center.getApiToken(),pageSize, pNum);
					List<Map<String, Object>> users = (List<Map<String, Object>>) reportTempMap.get("users");
					reportMaps.addAll(users);
				}
				maps.addAll(reportMaps);
			}
			
			for(Map<String, Object> userMap : maps){
				if(userMap.isEmpty()|| userMap.get("error")!=null){
	//				//该用户不存在zoomId
					logger.info("This userMap data doesnot exsit >>>>return userMap zoom error reason:"+userMap.get("error"));
					errors.add("This userMap data doesnot exsit >>>>return userMap zoom error reason:"+userMap.get("error"));
					continue;
				}
				String zoomUserId = (String) userMap.get("id");//zoomId
				String zoomUserEmail = (String) userMap.get("email");//email
				UserBase host = userLogic.getUserBaseByZoomId(zoomUserId);
				if(host == null){
					logger.info("This Hostor is not found at Bizconf DataBase Vcaasz,the ZoomId is :"+zoomUserId);
					errors.add("This Hostor is not found at Bizconf DataBase Vcaasz,the ZoomId is :"+zoomUserId);
					continue;
				}
				//获取数据中心ApiKey和ApiToken
				DataCenter center = dataCenterService.queryDataCenterById(host.getDataCenterId());
				
				Map<String, Object> retMap = cdrOperationComponent.getZoomCDRByUser(center.getApiKey(),center.getApiToken() ,zoomUserId, dateFrom, dateTo,0, 0);
				if(retMap == null || retMap.isEmpty() || retMap.get("error")!=null){
					//该用户zoomId查询不到同步到数据
					logger.info("This user zoomUserId data doesnot exsit >>zoomUserEmail:"+zoomUserEmail+"	>>zoomUserId="+zoomUserId+"	>>zoom return error reason:"+retMap.get("error"));
					errors.add("This user zoomUserId data doesnot exsit >>zoomUserEmail:"+zoomUserEmail+"	>>zoomUserId="+zoomUserId+"	>>zoom return error reason:"+retMap.get("error"));
					continue;
				}
				List<Map<String, Object>> meetings = (List<Map<String, Object>>) retMap.get("meetings");
				
				for(Map<String, Object> map:meetings){
					//会议ID
					String zoomConfId = (String) map.get("id").toString();
					//查找会议的参会会议号，即：confbase的id
					ConfBase confBase = confService.getConfBase(null, zoomConfId);
					if(confBase==null || confBase.getId()==null){
						logger.info("The confBase data doesnot exsit >>>>zoomConfId:"+zoomConfId);
						errors.add("The confBase data doesnot exsit >>>>zoomConfId:"+zoomConfId);
						continue;
					}
					
					
					/** 保存会议记录 */
					ZoomConfRecord record = new ZoomConfRecord();
					record.setZoomId(zoomConfId);// zoomId
					record.setTopic((String)map.get("topic"));//会议主题
					
					String startTime = (String) map.get("start_time");// 会议开始时间start_time
					String endTime = (String) map.get("end_time");// 会议开始时间end_time
					startTime = startTime.replace("T", " ").replace("Z", "");
					endTime = endTime.replace("T", " ").replace("Z", "");
					int timezone  = 28800000;
					if(confBase!=null){
						timezone = confBase.getTimeZone();
					}
					Date startDate = DateUtil.getGmtDateByTimeZone(sdf2.parse(startTime),timezone);
					Date endDate = DateUtil.getGmtDateByTimeZone(sdf2.parse(endTime), timezone);
					
					record.setDuration(Integer.valueOf(map.get("duration").toString()));//参加会议时长
					record.setStartTime(startDate);//会议开始时间
					record.setEndTime(endDate);//会议结束时间
					record.setConfId(confBase.getId());//设置会议id
					record.setHostId(confBase.getCompereUser());
					record.setSiteId(confBase.getSiteId());
					
					
					
					//recordId:查找刚才结束的会议，数据表中不存在,就存放到数据库中
					long recordId = 0;
					ZoomConfRecord retZoomConfRecord =recordSaveable(zoomConfId, startDate, endDate);
					if(retZoomConfRecord == null){	//会议记录不存在
						ZoomConfRecord saveRecord = libernate.saveEntity(record);//
						recordId = saveRecord.getId();
						//参会者名字数组
						List<Map<String, String>> participants = (List<Map<String, String>>) map.get("participants");
						for (Map<String, String> member : participants) {//获取jointime、leaveTime、confId、用户名
							logger.info("insert into ConfReportInfo "+member.get("name"));
							ConfReportInfo reportInfo = new ConfReportInfo();
							String name = member.get("name");
							
							//GMT时间
							String joinTime = member.get("join_time");
							String leaveTime = member.get("leave_time");
							joinTime = joinTime.replace("T", " ").replace("Z", "");
							leaveTime = leaveTime.replace("T", " ").replace("Z", "");
							Date joinDate = sdf2.parse(joinTime);
							Date leaveDate = sdf2.parse(leaveTime);
							
							reportInfo.setParticipantName(name);// 参会者名字
							reportInfo.setpStartTime(joinDate);// 入会时间
							reportInfo.setpLeaveTime(leaveDate);// 离会时间
							reportInfo.setConfLogId(recordId);//会议记录id
							libernate.saveEntity(reportInfo);
							
							//北京时间
							joinDate = DateUtil.getOffsetDateByGmtDate(joinDate, DateUtil.BJ_TIME_OFFSET);
							leaveDate = DateUtil.getOffsetDateByGmtDate(leaveDate,  DateUtil.BJ_TIME_OFFSET);
							joinTime = sdf.format(joinDate);
							leaveTime = sdf.format(leaveDate);
							//参会者入会时长
							int duration = DateUtil.scoreTime(joinDate, leaveDate);
							
							Map<String, Object> retmap = new HashMap<String, Object>();
							retmap.put("zoomConfId", zoomConfId);//会议ID--20
							retmap.put("startTime", joinTime);//参会时间--19
							retmap.put("endTime", leaveTime);//退出会议时间--19
							retmap.put("duration", duration+"");//会议时长--11
							retmap.put("userEmail", zoomUserEmail);//查找会议的参会会议号，即：confbase的id--20
							retmap.put("phoneNum", "");//电话号码为空--50
							retmap.put("confType", "V");//参会者标识视频为V--1
							retmap.put("callType", "VD");//视频VD--2
							retmap.put("DNIS", "");//接入号为空--20
							retmap.put("memberType", "G");//是否为参会者，均按参会者 "G"计算--1
							retmap.put("name", name);//参会者名字--50
							retmap.put("recordId", recordId+"");//会议记录Id--11位
							setterCDR(retmap,ow,lineNum++);
						}
					}else{
						recordId = retZoomConfRecord.getId();
						String strSql = "select * from t_conf_report_info where conf_log_id = ?";
						List<ConfReportInfo> participantList = libernate.getEntityListCustomized(ConfReportInfo.class,strSql , new Object[]{
							recordId
						});
						if(participantList != null){
							for(ConfReportInfo member:participantList){
								String name = member.getParticipantName();
								//入会时间
								Date joinDate = member.getpStartTime();
								Date leaveDate = member.getpLeaveTime();
								//北京时间
								joinDate = DateUtil.getOffsetDateByGmtDate(joinDate, DateUtil.BJ_TIME_OFFSET);
								leaveDate = DateUtil.getOffsetDateByGmtDate(leaveDate,  DateUtil.BJ_TIME_OFFSET);
								String joinTime = sdf.format(joinDate);
								String leaveTime = sdf.format(leaveDate);
								//参会时长
								int duration = DateUtil.scoreTime(joinDate, leaveDate);
								Map<String, Object> retmap = new HashMap<String, Object>();
								retmap.put("zoomConfId", zoomConfId);//会议ID--20
								retmap.put("startTime", joinTime);//参会时间--19
								retmap.put("endTime", leaveTime);//退出会议时间--19
								retmap.put("duration", duration+"");//会议时长--11
								retmap.put("userEmail", zoomUserEmail);//查找会议的参会会议号，即：confbase的id--20
								retmap.put("phoneNum", "");//电话号码为空--50
								retmap.put("confType", "V");//参会者标识视频为V--1
								retmap.put("callType", "VD");//视频VD--2
								retmap.put("DNIS", "");//接入号为空--20
								retmap.put("memberType", "G");//是否为参会者，均按参会者 "G"计算--1
								retmap.put("name", name);//参会者名字--50
								retmap.put("recordId", recordId+"");//会议记录Id--11位
								setterCDR(retmap,ow,lineNum++);
							}
						}
					}
				}
			}
			logger.info(">>>>>>>>The CDR File has created successfully,The CDR File path is >>> "+getDirFile().getPath());
			
			if(!errors.isEmpty()){
				logger.info(">>>>>>>>The CDR Error File starts creating .....");
				String now = sdf.format(new Date());
				String errorCdrName = ERRORPATH+File.separator+PREFX_ERRORCDR_NAME+now+SUFFIX_NAME;
				BufferedWriter errorOw = null;
				try {
					File errorDir = new File(ERRORPATH);
					if(!errorDir.exists()){errorDir.mkdirs();}
					File errorFile = new File(errorCdrName);
					if(!errorFile.exists()){errorFile.createNewFile();}
					
					errorOw = new BufferedWriter(new FileWriterWithEncoding(errorFile,"UTF-8",true));
					for(String s:errors){
						setterFile(errorOw, new StringBuilder(s));
					}
					logger.info(">>>>>>>>The CDR Error File has created successfully,The CDR Error File path is >>> "+errorFile.getPath());
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(">>>>>>>>The CDR Error File is created failture!!!"+e);
				}finally{
					errorOw.close();
				}
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			BufferedWriter error = null;
			try {
				String now = sdf.format(new Date());
				String errorCdrName = ERRORPATH+File.separator+PREFX_ERRORCDR_NAME+now+SUFFIX_NAME;
				File errorDir = new File(ERRORPATH);
				if(!errorDir.exists()){errorDir.mkdirs();}
				File errorFile = new File(errorCdrName);
				if(!errorFile.exists()){errorFile.createNewFile();}
				error = new BufferedWriter(new FileWriterWithEncoding(errorFile,"UTF-8",true));
				setterFile(error, new StringBuilder(">>>>>>>>The CDR File has created failtrue! The line Number is about "+lineNum));
				logger.info(">>>>>>>>The CDR File has created failtrue! The line Number is about "+lineNum);
			}catch (Exception e1) {
				e1.printStackTrace();
				logger.info(">>>>>>>>The CDR Error File is created failture!!!"+e1);
			}finally{
				try {
					error.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			try {
				ow.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
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
	 * 查看某一条会议的会议报告是否存在
	 * @return ZoomConfRecord
	 */
	public ZoomConfRecord recordSaveable(String zoomId, Date startDate,Date endDate){
		if(StringUtil.isEmpty(zoomId) || startDate == null || endDate == null) return null;
		String sql = "select * from t_zoom_conf_record where zoom_id = ? and start_time = ? and  end_time=?";
		ZoomConfRecord zoomConfRecord = null;
		try {
			zoomConfRecord = libernate.getEntityCustomized(ZoomConfRecord.class, sql, new Object[]{zoomId,startDate,endDate});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zoomConfRecord;
	}
	
	
	
	
	public static void main(String args[]){
		
		String  att = appendSpace("Xxxxxx乌鲁木齐分会场",50);
		
		System.out.println(att);
	}
	
}
