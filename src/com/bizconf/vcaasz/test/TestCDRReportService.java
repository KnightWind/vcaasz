package com.bizconf.vcaasz.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.component.zoom.HttpURLUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.ConfBase;
import com.libernate.liberd.Libernate;

/**
 * @desc 生成CDR文件
 * @author Darren
 * @date 2014-10-22
 */
public class TestCDRReportService {

	protected static Libernate libernate = DAOProxy.getLibernate();
	private static Logger logger = Logger.getLogger(TestCDRReportService.class);
	

//	final static String ERRORPATH = "/data/billingdata/errorcdr";
//	final static String CDRPATH = "/data/billingdata/cdr";//生成CDR文件保存路径、名字
	final static String CDRPATH = "C:\\cdr";//生成CDR文件保存路径、名字
	final static String ERRORPATH = "C:\\errorcdr";
	final static String SUFFIX_NAME = ".txt";
	
	final static String PREFX_CDR_NAME = "cdr";
	final static String PREFX_ERRORCDR_NAME = "errorcdr";
	
	private File dir = null;
	public File getDir() {
		return dir;
	}
	public void setDir(File dir) {
		this.dir = dir;
	}
	
	public TestCDRReportService() {
		//进行目录创建
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		String cdrName = CDRPATH+File.separator+PREFX_CDR_NAME+now+SUFFIX_NAME;
		try {
			File cdrDir = new File(cdrName);
			File pf = cdrDir.getParentFile();
			if(!pf.exists()){
			 pf.mkdirs();
			}
			if(!cdrDir.exists()){
				cdrDir.createNewFile();
			}
			setDir(cdrDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static TestCDRReportService instance = new TestCDRReportService();
	
	public static synchronized TestCDRReportService getInstance(){
		return instance;
	}
	
	/**
	 * 根据confZoomId获取指定会议信息
	 * */
	public static ConfBase getConfBaseByConfZoomId(String zoomConfId){
		
		StringBuffer buffer = new StringBuffer("select * from t_conf_base where conf_zoom_id = ?");
		Object[] values = new Object[1];
		values[0] = zoomConfId;
		try {
			return libernate.getEntityCustomized(ConfBase.class, buffer.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		return null;
	} 
	
	/**
	 * 获取指定zoomId的某一个月的会议报告
	 * */
	public static Map<String, Object> getZoomCDRByUser(String hostId, String dateFrom, String dateTo,
			int pageSize, int pageNumber) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		params.put("user_id", hostId);
		params.put("from", dateFrom);
		params.put("to", dateTo);
		
		if(pageSize>0){
			params.put("page_size", pageSize);
		}
		if(pageNumber>0){
			params.put("page_number", pageNumber);
		}
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getCDRReport,
				params);
	}
	
	/**
	 * 在zoom数据库中查询出全部主持人
	 * */
	public static Map<String, Object> listAll(int pageSize, int pageNumber) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
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
	 * 根据指定主持人集合生成cdr需要的数据会议报告
	 * */
	@SuppressWarnings("unchecked")
	public boolean PerProcess(String dateFrom,String dateTo,int pageSize, int pageNumber){
		
		File file = getDir();
		
		boolean flag = true;
		int lineNum = 1;
		Map<String, Object> reportMap = listAll(pageSize, pageNumber);
		int pCount = (Integer) reportMap.get("page_count");
		int pNum = (Integer) reportMap.get("page_number");
//		int pSize = (Integer) reportMap.get("page_size");
//		int recordsTotal = (Integer) reportMap.get("total_records");
		List<Map<String, Object>> maps = (List<Map<String, Object>>) reportMap.get("users");
		
		while(pNum < pCount){
			pNum ++;
			Map<String, Object> reportTempMap = listAll(pageSize, pNum);
			List<Map<String, Object>> users = (List<Map<String, Object>>) reportTempMap.get("users");
			maps.addAll(users);
		}
		
//		logger.info("hosts>>>>:"+maps);
		logger.info("users size:"+maps.size());
		List<String> errors = new ArrayList<String>();
		
		for(Map<String, Object> userMap : maps){
			if(userMap.isEmpty()|| userMap.get("error")!=null){
//				//该用户不存在zoomId
//				logger.info("该用户userMap查询不到数据>>>>userMap zoom返回错误原因:"+userMap.get("error"));
				errors.add("该用户userMap查询不到数据>>>>userMap zoom返回错误原因:"+userMap.get("error"));
				continue;
			}
			String zoomId = (String) userMap.get("id");//zoomId
			String zoomEmail = (String) userMap.get("email");//zoomId
			
			Map<String, Object> retMap = getZoomCDRByUser(zoomId, dateFrom, dateTo,0, 0);
			if(retMap.isEmpty() || retMap.get("error")!=null){
				//该用户zoomId查询不到同步到数据
//				logger.info("该用户zoomId查询不到数据>>>>zoomEmail:"+zoomEmail+">>>>>zoomId="+zoomId+"zoom返回错误原因:"+retMap.get("error"));
				errors.add("该用户zoomId查询不到数据>>>>zoomEmail:"+zoomEmail+">>>>>zoomId="+zoomId+"zoom返回错误原因:"+retMap.get("error"));
				continue;
			}
			List<Map<String, Object>> meetings = (List<Map<String, Object>>) retMap.get("meetings");
			
			for(Map<String, Object> map:meetings){
				Map<String, Object> retmap = new HashMap<String, Object>();
				//会议ID
				String zoomConfId = (String) map.get("id").toString();
				//参会时间
				String startTime = (String) map.get("start_time");
				startTime = startTime.replace("T", " ").replace("Z", "");
				//退出会议时间
				String endTime = (String) map.get("end_time");
				endTime = endTime.replace("T", " ").replace("Z", "");
				//会议时长
				int duration = (Integer) map.get("duration");
				//查找会议的参会会议号，即：confbase的id
				ConfBase confBase = getConfBaseByConfZoomId(zoomConfId);
				String confId = (confBase!=null && confBase.getId()!=null)?confBase.getId()+"":"";
				//参会者名字数组
				List<Map<String, String>> participants = (List<Map<String, String>>) map.get("participants");
				int arrayLen = participants.size();
				String[] names = new String[arrayLen];
				int p = 0;
				for(Map<String, String> member:participants){
					names[p] = member.get("name");
					p++;
				}
				retmap.put("zoomConfId", zoomConfId);//会议ID--20
				retmap.put("startTime", startTime);//参会时间--19
				retmap.put("endTime", endTime);//退出会议时间--19
				retmap.put("duration", duration+"");//会议时长--11
				retmap.put("confId", confId);//查找会议的参会会议号，即：confbase的id--20
				retmap.put("phoneNum", "");//电话号码为空--50
				retmap.put("confType", "V");//参会者标识视频为V--1
				retmap.put("callType", "VD");//视频VD--2
				retmap.put("DNIS", "");//接入号为空--20
				retmap.put("memberType", "0");//是否为参会者，均按参会者计算 0--1
				retmap.put("name", names);//参会者名字,以逗号分隔--50
				flag = setterCDR(retmap,file,lineNum++);
			}
		}
		logger.info(">>>>>>>>CDR文件生成完成");
		
		if(!errors.isEmpty()){
			logger.info(">>>>>>>>生成CDR文件错误");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = sdf.format(new Date());
			String errorCdrName = CDRPATH+File.separator+PREFX_ERRORCDR_NAME+now+SUFFIX_NAME;
			try {
				File errorFile = new File(errorCdrName);
				if(!errorFile.exists()){errorFile.createNewFile();}
				for(String s:errors){
					setterFile(errorFile, new StringBuilder(s));
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(">>>>>>>>生成CDR文件错误");
			}
		}
		return flag;
	}
	
	
	/**
	 * 根据指定时间生成cdr文件需要的数据格式
	 * */
	public static boolean setterCDR(Map<String, Object> map,File file,int lineNum){
		
		boolean flag = true;
		
		StringBuilder lineString = new StringBuilder("");
//		lineNum ++;//5位
		String zoomConfId = (String) map.get("zoomConfId");//20位
		String confId = (String) map.get("confId");//20位
		String startTime = (String) map.get("startTime");//19位
		String endTime = (String) map.get("endTime");//19位
		String duration = (String) map.get("duration");//11位
		String phoneNum = (String) map.get("phoneNum");//50位
		String confType = (String) map.get("confType");//1位
		String callType = (String) map.get("callType");//2位
		String DNIS = (String) map.get("DNIS");//20位
		String memberType = (String) map.get("memberType");//1位
		String[] name = (String[]) map.get("name");//50位
		
		String line = String.valueOf(lineNum);
		line = appendSpace(line, 5);
		zoomConfId = appendSpace(zoomConfId, 20);
		confId = appendSpace(confId, 20);
		duration = appendSpace(duration, 11);
		phoneNum = appendSpace(phoneNum, 50);
		DNIS = appendSpace(DNIS, 20);
		
		lineString = lineString.append(line).append(zoomConfId).append(confId).append(startTime).append(endTime)
						.append(duration).append(phoneNum).append(confType).append(callType).append(DNIS).append(memberType);
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i < name.length;i++){
			builder = builder.append(appendSpace(name[i], 50));
		}
		lineString.append(builder);

		//写入文件
		flag = setterFile(file,lineString);
		return flag;
	}
	
	/**
	 * 根据组织好的形式生成CDR文件
	 * @throws IOException 
	 * */
	private static boolean setterFile(File file,StringBuilder lineStr){
		
		try {
			BufferedWriter ow = new BufferedWriter(new FileWriter(file,true));
			String temp = lineStr.toString()==null?"":lineStr.toString();//写入内容
			ow.write(temp);
			ow.newLine();
			ow.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String appendSpace(String string,int limit){
		if(string == null){
			string = "";
		}
		StringBuilder builder = new StringBuilder(string);
		for(int i = builder.length();i < limit;i++){
			builder.append(" ");
		}
		return builder.toString();
	}
	
	
	public static void main(String[] args) throws ParseException {
		
//		new TestCDRReportService();
//		Calendar cal1=Calendar.getInstance();
//		cal1.setTime(new Date());
//		TestCDRReportService.getInstance().PerProcess("2014-01-01", "2014-10-24", 300, 0);
//		Calendar cal2=Calendar.getInstance();
//		cal2.setTime(new Date());
//		long temp=cal2.getTimeInMillis()-cal1.getTimeInMillis();
//		long seconds=temp/1000;
//		System.err.println("共计耗时：》》》》"+seconds+"秒");
		
		//定时任务
//		Calendar calendar = Calendar.getInstance();  
//		calendar.set(Calendar.DATE, 24);
//	    calendar.set(Calendar.HOUR_OF_DAY, 10); // 控制时  
//	    calendar.set(Calendar.MINUTE, 32); // 控制分  
//	    calendar.set(Calendar.SECOND, 0); // 控制秒  
//	    Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00
//	    
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("-----");
//			}
//		}, time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(sdf.parse("2014-3-21"));
		calendar.add(Calendar.MONTH, -1);
		int maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		System.out.println("year ="+year);
		System.out.println("Month ="+month);
		System.out.println("max ="+maxDay);
		
	}
	
}
