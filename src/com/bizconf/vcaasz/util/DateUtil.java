package com.bizconf.vcaasz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.management.monitor.Monitor;

import org.apache.axis.types.Year;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfCycle;

public class DateUtil {
	
	public static final long BJ_TIME_OFFSET = 28800000l; 
	
	public static void mains(String[] args) {
		
		
	}
	
	/**
	 * 计算两个时间点相差的分钟数
	 * @param join 入会时间
	 * @param leave 离会时间
	 * */
	public static int scoreTime(Date join,Date leave){
		
		Calendar c0 = Calendar.getInstance();
		c0.setTime(join);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(leave);
		
		long seconds = (c1.getTimeInMillis() - c0.getTimeInMillis())/(1000);
		int durationMinutes  = (int) (seconds / 60);
		if(seconds % 60 != 0){
			durationMinutes++;
		}
		//要求注释掉 ，按绝对时间来进行计算
//		int sec = c1.get(Calendar.SECOND) - c0.get(Calendar.SECOND);
//		if (sec < 0) {
//			durationMinutes ++;
//		}
		return durationMinutes;
	}
	
	/**
	 * 获取所在时区的Offset
	 * @return
	 */
	public static long  getDateOffset() {
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar=null;
		return offset;
	}
	
	
	
	/**
	 * 获取GMT时间
	 * @param date:如果Date 为空，则取当前时间的GMT时间
	 * @return
	 */
	public static Date getGmtDate(Date date){
		Date gmtDate=null;
		if(date==null){
			date=new Date();
		}
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 根据当前时间与TimeZone取GMT时间
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getGmtDateByTimeZone(Date date,Integer offset){
		Date gmtDate=null;
		if(date==null){
			date=new Date();
		}
		if( offset==null){
			offset=0;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 时间增加多少分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addDateMinutes(Date date,Integer minute){
		return addDateField(date, Calendar.MINUTE, minute);
	}
	
	/**
	 * 时间增加多少秒
	 * second 可以为负数，相当于减了多少秒
	 * wangyong
	 * 2013-6-4
	 */
	public static Date addDateSecond(Date date, Integer second){
		return addDateField(date, Calendar.SECOND, second);
	}
	
	private static Date addDateField(Date date, int calendarField, Integer fieldValue){
		if(date==null){
			date=new Date();
		}
		if( fieldValue==null){
			fieldValue=0;
		}
		Date retDate=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarField, fieldValue);
		retDate=calendar.getTime();
		calendar=null;
		return retDate;
	}
	
	/**
	 * GMT时间转换为北京时间
	 * @param date:如果Date 为空，则取当前时间的GMT时间
	 * @return
	 */
	public static Date getBejingDateByGmtDate(Date gmtDate){
		Date bjDate=null;
		if(gmtDate==null){
			gmtDate=new Date();
		}
		long offset = 28800000;    //北京时区
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(gmtDate);
		calendar.add(Calendar.HOUR, (int)(offset/(1000*3600)));
		bjDate=calendar.getTime();
		calendar=null;
		return bjDate;
	}

	/**
	 * 以当前时间为准，取多少小时    前    的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByBeforeMinute(Integer minute){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1 * offset/(1000*3600)));
		calendar.add(Calendar.MINUTE, -1 * minute );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 * 以当前时间为准，取多少小时    前    的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByBeforeHour(Integer hour){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.HOUR, -1*hour );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 *以当前时间为准， 取多少小时    后      的GMT时间
	 * @param hour
	 * @return
	 */
	public static Date getGmtDateByAfterHour(Integer hour){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.HOUR, hour );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	/**
	 *以当前时间为准， 取多少ms 后的GMT时间
	 * @param ms
	 * @return
	 */
	public static Date getGmtDateByAfterMs(long ms){
		Date gmtDate=null;
		long offset=0;
		Calendar calendar=Calendar.getInstance();
		offset=calendar.getTimeZone().getOffset(calendar.getTime().getTime());
		calendar.add(Calendar.HOUR, (int)(-1*offset/(1000*3600)));
		calendar.add(Calendar.MILLISECOND, (int)ms );
		gmtDate=calendar.getTime();
		calendar=null;
		return gmtDate;
	}
	
	

	/**
	 * 格式 化日期，返回符合格式的字符串
	 * @param date
	 * @param formater   如:yyyy-MM-dd HH:mm:ss
	 * @return
	 * 
	 */
	public static String getDateStrCompact(Date date, String formater) {
		if (date == null){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		String str = format.format(date);
		return str;
	}

	public static String dateToString(Date date,String formatStr) {
		if(formatStr==null || "".equals(formatStr.trim())){
			formatStr="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatDate = new SimpleDateFormat(formatStr);
//		String str = formatDate.format(date);
		String datestr = formatDate.format(date);
		return datestr;
	}
	
	/**
	 * 格式化日期字符串，返回符合格式的date
	 * @param dateStr
	 * @param formater   如:yyyy-MM-dd HH:mm:ss
	 * @return
	 * 
	 */
	public static Date StringToDate(String dateStr, String formater){
		Date date = null;
		if(formater==null || "".equals(formater.trim())){
			formater = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		if(!StringUtil.isNotBlank(dateStr)){
			return date;
		}
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static long dateDiff(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000)/60;
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}
	

	public static long diffMinute(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000)/60;
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}

	public static long diffSecond(Date date1,Date date2){
		long diff=0;
		if(date1.before(date2)){
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(date1);
			Calendar calendar2=Calendar.getInstance();
			calendar2.setTime(date2);
			long diffMillSeconds=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
			if(diffMillSeconds>0){
				diff=(diffMillSeconds/1000);
			}
			calendar2=null;
			calendar1=null;
		}
		
		return diff;
	}
	
	/**
	 * 根据GMT时间与时区生成当前时间
	 * @param gmtDate
	 * @param offset
	 * @return
	 */
	public static Date getOffsetDateByGmtDate(Date gmtDate,Long offset){
		Date offsetDate=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(gmtDate);
		calendar.add(Calendar.HOUR, (int) (offset/(1000*3600)));
		offsetDate=calendar.getTime();
		calendar=null;
		return offsetDate;
	}

	/**
	 * 将日期增减 n 天
	 * @param date  参照日期,如果为null则取当前日期
	 * @param days  增减的天数 (为正数则增加，为负数则减少)
	 * @return Date
	 */
	public static Date addDate(Date date, int days) {
		return addDate(date, days, Calendar.DATE);
	}
	
	/**
	 * 取某一日期增减 n 值后的日期, n 由 dateField 决定是年、月、日 根据增加or减少的时间得到新的日期
	 * @param date 参照日期
	 * @param counts 增减的数值
	 * @param dateField
	 *            int 需操作的日期字段, 取值范围如下 Calendar.YEAY 年 Calendar.MONTH 月
	 *            Calendar.DATE 日 .... Calendar.SECOND 秒
	 * @return Date
	 */
	private static Date addDate(Date date, int counts, int dateField) {
		GregorianCalendar curGc = new GregorianCalendar();
		if (date != null)
			curGc.setTime(date);
		curGc.add(dateField, counts);
		return curGc.getTime();
	}
	
	
	
	
	/**
	 * 根据周期范围与类型取出周期中的所有日期
	 * @param beginDate
	 * @param endDate
	 * @param cycleType
	 * @param cycleValue
	 * @return
	 */
	public static List<Date> getCycleDateFromScope(Date beginDate,Date endDate,Integer cycleType,String cycleValue, int cycleDateLimit){
		List<Date> cycleDateList=null;
		if(beginDate!=null && endDate!=null && cycleType!=null && cycleType.intValue() > 0){
			if(cycleDateLimit == 0){
				cycleDateLimit = ConstantUtil.CYCLE_CONF_DATE_LIMIT;
			}
//			cycleDateList=new ArrayList<Date>();
			if(cycleType.intValue() == ConfConstant.CONF_CYCLE_DAILY.intValue()){
				if(!StringUtil.isEmpty(cycleValue)){
//					String[] valueArray=cycleValue.split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT);
					Integer diffDays = 0;
//					if(valueArray!=null && valueArray.length > 0){
						diffDays=IntegerUtil.parseIntegerWithDefaultZero(cycleValue);
//					}
//					if(diffDays > 0){
					cycleDateList=getDailyDays(beginDate,endDate,diffDays,false, cycleDateLimit);
//					}else{
//						cycleDateList=new ArrayList<Date>();
//						cycleDateList.add(beginDate);
//					}
				}
					
			}else if (cycleType.intValue() == ConfConstant.CONF_CYCLE_WEEKLY.intValue()){
				cycleDateList=getWeeklyDays(beginDate,endDate,cycleValue, cycleDateLimit);
				
			}else if (cycleType.intValue() == ConfConstant.CONF_CYCLE_MONTHLY.intValue()){
				cycleDateList=getMonthlyDays(beginDate,endDate,cycleValue, cycleDateLimit);
//				cycleDateList=getMonthlyDays(beginDate,endDate,cycleValue,false);
				
			}else if (cycleType.intValue() == ConfConstant.CONF_CYCLE_YEARLY.intValue()){
				
			}
		}
		return cycleDateList;
	} 
	
	
	
	/**
	 * 
	 * 按天循环时
	 * @param beginDate
	 * @param endDate    
	 * @param diffDays   每几天
	 * @param isWorkDay  是否只工作日    true:只工作日；False ：所有 工作日
	 * @param cycleDateLimit  最多获取多少个符合条件的循环日期
	 * @return
	 */
	public static List<Date> getDailyDays(Date beginDate,Date endDate,Integer diffDays,boolean isWorkDay, int cycleDateLimit){
		List<Date> dailyList=null;
		if(beginDate==null || endDate==null ){
			return dailyList;
		}
		Calendar beginCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		endCalendar.setTime(endDate);
		dailyList=new ArrayList<Date>();
		if(diffDays<=0){
			dailyList.add(beginDate);
		}else{
			long dateDiff=(endCalendar.getTimeInMillis()-beginCalendar.getTimeInMillis())/(24*3600*1000);
			if(dateDiff < diffDays){
				dailyList.add(beginDate);
			}else{
				Calendar eachDailyCalendar=beginCalendar;
				int eachWeekCode=-1;
				int cycleDays = 1;
				while(eachDailyCalendar.getTimeInMillis()<=endCalendar.getTimeInMillis()){
					if(cycleDays > cycleDateLimit){    //循环会议不可大于30天
						break;
					}
					if(isWorkDay){//如果只是工作日
						eachWeekCode=-1;
						eachWeekCode=eachDailyCalendar.get(Calendar.DAY_OF_WEEK);
						if(eachWeekCode>=ConfConstant.WEEK_MONDAY && eachWeekCode<=ConfConstant.WEEK_FRIDAY){
							if(eachDailyCalendar.getTime().after(getGmtDate(null))){
								dailyList.add(eachDailyCalendar.getTime());
							}
						}
					}else{//所有 日期
						if(eachDailyCalendar.getTime().after(getGmtDate(null))){
							dailyList.add(eachDailyCalendar.getTime());
						}
					}
					cycleDays++; 
					eachDailyCalendar.setTimeInMillis(eachDailyCalendar.getTimeInMillis()+(diffDays*24*3600*1000));
				}
				eachDailyCalendar=null;
			}
		}
		beginCalendar=null;
		endCalendar=null;
		return dailyList;
	}
	
	

	/*
	 * cycleValue:  
	 * 按周循环
	 * 复选：2,3,4(周三、周四、周五)
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static List<Date> getWeeklyDays(Date beginDate, Date endDate, String weeklyValue, int cycleDateLimit){
		List<Date> dailyList = null;
		if(beginDate==null || endDate == null || StringUtil.isEmpty(weeklyValue)){
			return dailyList;
		}
		Integer[] loopDayArray = null;
		if(weeklyValue.indexOf(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)>0){
			String[] weekDayArray = weeklyValue.split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT);
			if(weekDayArray!=null && weekDayArray.length >0){
				loopDayArray=new Integer[weekDayArray.length];
			}
			int ii=0;
			for(String eachDay:weekDayArray){
				if(!StringUtil.isEmpty(eachDay)){
					loopDayArray[ii]=IntegerUtil.parseInteger(eachDay);
					ii++;
				}
			}
		}else{
			loopDayArray=new Integer[1];	
			loopDayArray[0]=IntegerUtil.parseInteger(weeklyValue);
		}
		
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		Date eachDate=beginDate;
		Calendar eachCalendar=Calendar.getInstance();
		dailyList=new ArrayList<Date>();
		int eachWeekDay=0;
		int cycleDays = 1;
		while (eachDate.compareTo(endDate)<=0) {
			if(cycleDays > cycleDateLimit){    //循环会议不可大于30天
				break;
			}
			eachWeekDay=0;
			eachCalendar.setTime(eachDate);
			eachWeekDay = eachCalendar.get(Calendar.DAY_OF_WEEK);
			for(Integer eachDay:loopDayArray) {
				if(eachDay!=null && eachDay.intValue() >0 && eachWeekDay==eachDay) {
					if(eachCalendar.getTime().after(getGmtDate(null))){
						dailyList.add(eachCalendar.getTime());
						cycleDays++;
					}
				}
			}
			eachCalendar.setTimeInMillis(eachCalendar.getTimeInMillis()+(24*3600*1000));
			eachDate=eachCalendar.getTime();
		}
		beginCalendar=null;
		eachCalendar=null;
		eachDate=null;
		loopDayArray=null;
		
		return dailyList;
	}
	

	
//	
//	/*
//	 * cycleValue:   2
//	 * 每月第几天 ：一个单独的数值数据     如：5,7,30或者  30
//	 * 
//	 * 每个月的第几周的周几：2;3,4,5(第二周的周三、周四、周五)
//	 * 
//	 * 
//	 */
//	
//	public static List<Date> getMonthlyDays(Date beginDate,Date endDate,String monthlyValue,boolean isWeekDay){
//		long time1=System.currentTimeMillis();
//		List<Date> dailyList = null;
//		if(beginDate==null || endDate == null || StringUtil.isEmpty(monthlyValue)){
//			return dailyList;
//		}
//		boolean isWeekLoop=false;
//		if(monthlyValue.indexOf(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT)>0){
//			isWeekLoop=true;
//		}
//		Integer loopWeek=0;
//		Integer[] loopDayArray=null;
//
//		String[] monthValueArray=null;
//		if(isWeekLoop){//按每月中的第几周的周几循环的情况下
//			monthValueArray=monthlyValue.split(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT);
//			if(monthValueArray.length==2){
//				loopWeek=IntegerUtil.parseInteger(monthValueArray[0]);
//				if(loopWeek < 1  || loopWeek > 5){
//					loopWeek=1;
//				}
//				if(!StringUtil.isEmpty(monthValueArray[1])){
//					if( monthValueArray[1].indexOf(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)>0){
//						String[] tmpDayArray=monthValueArray[1].split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT);
//						if(tmpDayArray!=null){
//							loopDayArray=new Integer[tmpDayArray.length];
//							int ii=0;
//							for(String eachDay:tmpDayArray){
//								if(!StringUtil.isEmpty(eachDay)){
//									loopDayArray[ii]=IntegerUtil.parseInteger(eachDay);
//									ii++;
//								}
//							}
//						}
//						tmpDayArray=null;
//					}else{
//						loopDayArray=new Integer[1];
//						loopDayArray[0]=IntegerUtil.parseInteger(monthValueArray[1]);
//					}
//				}
//			}
//		}else{//只按每月几号循环的情况
//			if(monthlyValue.indexOf(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)>0){
//				monthValueArray=monthlyValue.split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT);
//				if(monthValueArray!=null && monthValueArray.length >0){
//					loopDayArray=new Integer[monthValueArray.length];
//				}
//				int ii=0;
//				for(String eachDay:monthValueArray){
//					if(!StringUtil.isEmpty(eachDay)){
//						loopDayArray[ii]=IntegerUtil.parseInteger(eachDay);
//						ii++;
//					}
//				}
//			}else{
//				loopDayArray=new Integer[1];	
//				loopDayArray[0]=IntegerUtil.parseInteger(monthlyValue);
//			}
//		}
//		monthValueArray=null;
//		
//		Calendar beginCalendar = Calendar.getInstance();
//		beginCalendar.setTime(beginDate);
//		Date eachDate=beginDate;
//		Calendar eachCalendar=Calendar.getInstance();
//		dailyList=new ArrayList<Date>();
//		int eachMonthDay=0;
//		int eachWeekDay=0;
//		int eachWeekNum=0;
//		while (eachDate.compareTo(endDate)<=0) {
//			eachMonthDay=0;
//			eachWeekDay=0;
//			eachCalendar.setTime(eachDate);
//			if(loopWeek>0){//按每月 第几周的同几循环的情况 \
//				eachWeekNum=eachCalendar.get(Calendar.WEEK_OF_MONTH);
//				if(loopWeek.intValue()==eachWeekNum){
//					eachWeekDay=eachCalendar.get(Calendar.DAY_OF_WEEK);
//					for(Integer eachLoopDay:loopDayArray){
//						if(eachLoopDay!=null && eachLoopDay.intValue()>0){
//							if(eachWeekDay==eachLoopDay.intValue()){
//								dailyList.add(eachCalendar.getTime());
//								System.out.println("day of week"+eachCalendar.getTime());
//							}
//						}
//					}
//				}
//				
//			}else{//按每月 几号循环的情况 
//				eachMonthDay=eachCalendar.get(Calendar.DAY_OF_MONTH);
//				for(Integer eachLoopDay:loopDayArray){
//					if(eachLoopDay!=null && eachLoopDay.intValue()>0){
//						if(eachMonthDay==eachLoopDay.intValue()){
//							if(isWeekDay){
//								if(eachCalendar.get(Calendar.DAY_OF_WEEK) >= Calendar.MONDAY 
//									&& eachCalendar.get(Calendar.DAY_OF_WEEK)<= Calendar.FRIDAY){
//									dailyList.add(eachCalendar.getTime());
////									System.out.println("day of month"+eachCalendar.getTime() +"  WeekNum="+eachCalendar.get(Calendar.DAY_OF_WEEK));
//								}
//							}
//							else{
//								dailyList.add(eachCalendar.getTime());
////								System.out.println("day of month"+eachCalendar.getTime() +"  WeekNum="+eachCalendar.get(Calendar.DAY_OF_WEEK));
//							}
//							
//						}
//					}
//				}
//			}
//			eachCalendar.setTimeInMillis(eachCalendar.getTimeInMillis()+(24*3600*1000));
//			eachDate=eachCalendar.getTime();
//		}
//		long time2=System.currentTimeMillis();
//		beginCalendar=null;
//		eachDate=null;
//		eachCalendar=null;
//		loopDayArray=null;
//		System.out.println(" use Time: "+(time2-time1)+" ms");
//		return dailyList;
//	}
//	

	public static List<Date> getYearlyDays(Date beginDate,Date endDate,String yearlyValue){
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getMaxDayFromDate(Date date){
		Integer maxDay=0;
		if(date==null){
			return maxDay;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		if(calendar!=null){
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,1);
			calendar.setTimeInMillis(calendar.getTimeInMillis()-(24*3600*1000));
			maxDay=calendar.get(Calendar.DAY_OF_MONTH);
		}
		calendar=null;
		return maxDay;
	}
	/*
	 *
		字母		  表示日期或时间元素			
		y  		年 
		M  		年中的月份
		w  		年中的周数  
		W  		月份中的周数  
		D  		年中的天数  
		d  		月份中的天数  
		F  		月份中的星期
		E  		星期中的天数  
		a  		Am/pm      
		H  		一天中的小时数（0-23）  
		k  		一天中的小时数（1-24）  
		K  		am/pm 中的小时数（0-11）  
		h  		am/pm 中的小时数（1-12）  
		m  		小时中的分钟数      
		s  		分钟中的秒数  
		S  		毫秒数978  
		z  		时区  General time zone  Pacific Standard Time; PST; GMT-08:00  
		Z  		时区  RFC 822 time zone  -0800 
	 * 
	 * */
	
	
	
	
	/*
	 * 创建循环会议：按月循环
	 * 1.每月第几天 ：一个单独的数值数据     如：5,7,30或者  30
	 * 2.每个月的第几个周几：2;3,4,5(第二个周三、周四、周五)
	 * 
	 */
	private static List<Date> getMonthlyDays(Date beginDate, Date endDate, String monthlyValue , int cycleDateLimit){
		List<Date> dailyList = new ArrayList<Date>();
		if(monthlyValue.indexOf(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT)>0){
			dailyList = getDaysByMonth(beginDate,endDate, monthlyValue, cycleDateLimit);
		}else{
			dailyList = getSequenceDay(beginDate, endDate, monthlyValue, cycleDateLimit);
		}
		return dailyList;
	}
	
	/**
	 * 每个月的第几个周几：2;3,4,5(第二个周三、周四、周五)
	 */
	private static List<Date> getDaysByMonth(Date beginDate,Date endDate,String monthlyValue, int cycleDateLimit){
		List<Date> dailyList = new ArrayList<Date>();
		List<Date> dateList = new ArrayList<Date>();        //从开始日期取到结束日期的list，每月一天
		if(beginDate == null || endDate == null || StringUtil.isEmpty(monthlyValue)){
			return dailyList;
		}
		String[] monthValueArray = monthlyValue.split(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT);
		//每月第几个，本例中为第二个
		int sequenceInMonth = Integer.parseInt(monthValueArray[0]);
		if(sequenceInMonth < 1 || sequenceInMonth > 5){
			sequenceInMonth = 1;     //每个月最多第5个
		}
		//周几，本例中为周三
		int week = Integer.parseInt(monthValueArray[1]);
		Calendar eachCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		eachCalendar.setTime(beginDate);
		endCalendar.setTime(endDate);
		endCalendar.set(Calendar.DATE, 1);
//		dateList.add(beginDate);
		
		
		
		eachCalendar.set(Calendar.DATE, 1);
		while(!eachCalendar.after(endCalendar)){
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.MONTH, +1);
		}
		for(Date date : dateList){
			if(dailyList.size() == cycleDateLimit){     //最多获取30个有效日期
				break;
			}
			Date rightDate = getDaysByDate(date, sequenceInMonth, week);
			if(rightDate != null && !rightDate.before(beginDate)){
				dailyList.add(rightDate);
			}
		}
		
		
		
//		for(int i=0;i<cycleDateLimit;i++){    //最多获取30个有效日期
//			eachCalendar.set(Calendar.DATE, 1);
//			//if(eachCalendar.get(Calendar.MONTH) <= endDate.getMonth()){
//			if(!eachCalendar.after(endCalendar)){
//				dateList.add(eachCalendar.getTime());
//			}else{
//				break;
//			}
//			eachCalendar.add(Calendar.MONTH, +1);
//		}
//		for(Date date : dateList){
//			Date rightDate = getDaysByDate(date, sequenceInMonth, week);
//			if(!rightDate.before(beginDate)){
//				dailyList.add(rightDate);
//			}
//		}
		return dailyList;
	}
	
	/**
	 * 获取给定日期所在月份的第几个周几的日期
	 * @param beginDate      日期
	 * @param sequence       第几个
	 * @param weekValue      周几
	 * wangyong
	 */
	private static Date getDaysByDate(Date beginDate,int sequenceValue, int weekValue){
		Calendar beginCalendar = Calendar.getInstance();
//		beginCalendar.set(2013,3,24);     //设置日期2013-4-24
		beginCalendar.setTime(beginDate);
		int dateInWhichMonth = beginCalendar.get(Calendar.MONTH);   //beginDate所在月份,java月份是从0-11,月份设置时要减1    1:2月份;2:3月份;3:4月份;4:5月份,以此类推
		int dateInWhichYear = beginCalendar.get(Calendar.YEAR);     //beginDate所在年 
		//得到beginDate所在月份的第1日的日期 
		Calendar firstDayCalendar = new GregorianCalendar(dateInWhichYear, dateInWhichMonth, 1,beginCalendar.get(Calendar.HOUR_OF_DAY),beginCalendar.get(Calendar.MINUTE),beginCalendar.get(Calendar.SECOND)); 
		Calendar firstSeqCalendar = null;     //第一个周几
		//从该月1日开始遍历找到第一个指定“星期几”
		int dayInWeek = 0;     //dayInWeek:     1:周日；2：周一；3：周二；4：周三；5：周四；6：周五；7：周六
		for(int i=0;i<7;i++){
			Calendar eachDayCalendar = firstDayCalendar;
			dayInWeek = eachDayCalendar.get(Calendar.DAY_OF_WEEK);   //当天是一周中的第几天
			if(dayInWeek == weekValue){
				firstSeqCalendar = eachDayCalendar;
				break;
			}
			eachDayCalendar.add(Calendar.DAY_OF_MONTH, +1);    //取当前日期的后一天
		}
		if(sequenceValue >= 1){
			//sequenceValue如果为3，即第三个周几，那么就是第一个周几+（3-1）*7,即加上14天
			firstSeqCalendar.add(Calendar.DAY_OF_MONTH, +((sequenceValue - 1) * 7));
			if(dateInWhichMonth == firstSeqCalendar.get(Calendar.MONTH)){
				return firstSeqCalendar.getTime();
			}
		}
		return null;
	}
	
	/**
	 * 获取每月第几天
	 * 每月第几天 ：一个单独的数值数据     如：5,7,30或者  30
	 * wangyong
	 * 2013-3-27
	 */
	private static List<Date> getSequenceDay(Date beginDate, Date endDate, String dayValue, int cycleDateLimit){
		int day = IntegerUtil.parseIntegerWithDefaultZero(dayValue); 
		List<Date> dailyList = new ArrayList<Date>();
		List<Date> dateList = new ArrayList<Date>();
		Calendar eachCalendar = Calendar.getInstance();
		eachCalendar.setTime(beginDate);
		
		
//		Calendar eachCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
//		eachCalendar.setTime(beginDate);
		endCalendar.setTime(endDate);
		
		
		
		eachCalendar.set(Calendar.DATE, 1);
		while(!eachCalendar.after(endCalendar)){
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.MONTH, +1);
		}
		
		
		int dateSize = dateList.size();
		for(int i=0;i<dateSize;i++){
			if(dailyList.size() == cycleDateLimit){   //最多获取30个有效日期
				break;
			}
			eachCalendar.setTime(dateList.get(i));
			int dateInWhichMonth = eachCalendar.get(Calendar.MONTH);   //beginDate所在月份,java月份是从0-11,月份设置时要减1    1:2月份;2:3月份;3:4月份;4:5月份,以此类推
			int dateInWhichYear = eachCalendar.get(Calendar.YEAR);     //beginDate所在年 
			//得到date所在月份的第1天的日期 
			Calendar firstDayCalendar = new GregorianCalendar(dateInWhichYear, dateInWhichMonth, 1,eachCalendar.get(Calendar.HOUR_OF_DAY),eachCalendar.get(Calendar.MINUTE),eachCalendar.get(Calendar.SECOND));
//			Calendar firstDayCalendar = new GregorianCalendar(dateInWhichYear, dateInWhichMonth, 1,0,0,0);
			//取月份要加1.判断当前月份的最大天数: 
			int maxDay = firstDayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
			if(maxDay >= (day-1)){      //每月第几天必须小于等于当月最大天数
				firstDayCalendar.add(Calendar.DAY_OF_MONTH, (day-1)<0 ? 0 : (day-1));
				if(firstDayCalendar.getTime().before(beginDate)){
					continue;
				}
				
//				if(i == 0){
//					if(firstDayCalendar.getTime().compareTo(dateList.get(i)) >= 0){
//						dailyList.add(firstDayCalendar.getTime());
//					}
//				}else 
				if(i == (dateSize-1)){
					if(firstDayCalendar.getTime().compareTo(endDate) <= 0){
						dailyList.add(firstDayCalendar.getTime());
						break;
					}
				}else{
					dailyList.add(firstDayCalendar.getTime());
				}
			}
		}
		return dailyList;
	}
	
 
	/**
	 * 
	 * @param offsetmill 时区值
	 * @return
	 */
	public static Date getTodayStartDate(long offsetmill){
		Date now = getGmtDate(null);
		Date localDate = getOffsetDateByGmtDate(now, offsetmill);//获取此时的当地时间
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			localDate = sdfp.parse(sdf.format(localDate)+" 00:00:00");//当地“今天” 0点0分0秒时间点
			
			return getGmtDateByTimeZone(localDate, new Integer((int)offsetmill)); //获取当地 0点0分0秒所对应的GMT时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getTodayEndDate(Date datastart){
		long timemill = datastart.getTime()+1*24*3600000l;
		return new Date(timemill);
	}
	
	public static Date getWeekStartDate(long offsetmill){
		//获取localtime
		Date date = getOffsetDateByGmtDate(getGmtDate(null),offsetmill);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		return getGmtDateByTimeZone(calendar.getTime(), (int)offsetmill);
	}
	
	/**
	 * 获取一周的结束日期
	 * @param offsetmill
	 * @return
	 */
	public static Date getWeekEndDate(Date weekstart){
		long timemill = weekstart.getTime()+7*24*3600000l;
		return new Date(timemill);
	}
	
	public static Date getMonthStartDate(long offsetmill){
		//获取localtime
		Date date = getOffsetDateByGmtDate(getGmtDate(null),offsetmill);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);	
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return getGmtDateByTimeZone(calendar.getTime(), (int)offsetmill);
		
	}
	
	
	public static Date getMonthEndDate(Date monthstart){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthstart);
		calendar.add(Calendar.MONTH, 1);
		int mon = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(mon==1 || mon ==3 || mon ==5 || mon==7 ||mon ==8 ||mon ==10 || mon ==12){
			if(day>27){
				calendar.set(Calendar.DAY_OF_MONTH, 31);
			}
		}
		return calendar.getTime();
	}
	
	public static Date getMonthStratDate(String year,String month,Integer offsetmill){
		long mills = DateUtil.BJ_TIME_OFFSET;
		if(offsetmill!=null){
			mills = offsetmill.longValue();
		}
		
		if(StringUtil.isEmpty(month)){
			return getMonthStartDate(mills); 
		}
		Calendar calendar = Calendar.getInstance();
		int y =  calendar.get(Calendar.YEAR);
		int M = calendar.get(Calendar.MONTH);
		try{
			y = Integer.parseInt(year);
			M = Integer.parseInt(month)-1;
		}catch (Exception e) {
			y =  calendar.get(Calendar.YEAR);
			M = calendar.get(Calendar.MONTH);
		}
		calendar.set(Calendar.YEAR, y);
		calendar.set(Calendar.MONTH, M);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return getGmtDateByTimeZone(calendar.getTime(), (int)mills);
	}
	
	
	public static int getDateDiff(Date date1,Date date2){
		long diffDays=0;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(date2);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		long time2 = calendar.getTimeInMillis();
		diffDays = (time2 - time1) / (1000 * 3600 * 24);
		calendar.clear();
		calendar=null;
		return Integer.parseInt(String.valueOf(diffDays));            

	}
	
	
	
	
	

	public static boolean isTime(String str){
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("(([0-1]?\\d)|(2[0-3]))(:([0-5]?\\d)){2}");
			flag = pattern.matcher(str).matches();
			pattern = null;
		}
		return flag;
	}

	/**
	 * 验证是否日期
	 * @param str 格式 ："yyyy-MM-dd"  或者  “yyyy-MM-dd hh:mm:ss" 或者  “yyyy-MM-dd h:m:s" 
	 * @return
	 */
	public static boolean isDate(String str) {
		boolean flag = false;
		if (!StringUtil.isEmpty(str)) {
			Pattern pattern = Pattern.compile("((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-" +
					"(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-" +
					"(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|" +
					"(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|" +
					"((16|[2468][048]|[3579][26])00))-0?2-29-))" +
					"( (([0-1]?\\d)|(2[0-3]))(:([0-5]?\\d)){2})?");
			flag = pattern.matcher(str).matches();
			pattern = null;

		}
		return flag;
	}
	
	/**
	 * 将日期格式的字符转转成日期类型
	  * @param str 格式 ："yyyy-MM-dd"  或者  “yyyy-MM-dd hh:mm:ss" 或者  “yyyy-MM-dd h:m:s" 
	 * @return
	 */
	public static Date parseDate(String str){
		Date date=null;
		if(isDate(str)){
			String[] tmpArray=str.split("[-\\s:]");
			if(tmpArray!=null && (tmpArray.length==3 ||tmpArray.length==6)){
				Calendar calendar=Calendar.getInstance();
				calendar.clear();
				calendar.set(Calendar.YEAR, NumberUtil.parseInteger(tmpArray[0]));
				calendar.set(Calendar.MONTH, NumberUtil.parseInteger(tmpArray[1])-1);
				calendar.set(Calendar.DAY_OF_MONTH, NumberUtil.parseInteger(tmpArray[2]));
				if(tmpArray.length==6){
					calendar.set(Calendar.HOUR, NumberUtil.parseInteger(tmpArray[3]));
					calendar.set(Calendar.MINUTE, NumberUtil.parseInteger(tmpArray[4]));
					calendar.set(Calendar.SECOND, NumberUtil.parseInteger(tmpArray[5]));
				}
				date=calendar.getTime();
				calendar=null;
			}
			tmpArray=null;
		}
		return date;
	}


	/**
	 * 格式化日期，将日期转成符合格式的字符串
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String format(Date date, String formatStr) {
		if (date == null) {
			return null;
		}
		if (formatStr == null || "".equals(formatStr.trim())) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String dateStr = format.format(date);
		format = null;
		return dateStr;
	}
	
	
	public static int getBeijingBillCurrentYear(){
		Date beijingDate = getBejingDateByGmtDate(getGmtDate(new Date()));
		Calendar c = Calendar.getInstance();
		c.setTime(beijingDate);
		int year = c.get(Calendar.YEAR);
//		if(c.get(Calendar.MONTH) == 0) year--;
		return year;
	}
	
	public static int getBeijingBillCurrentMonth(){
		Date beijingDate = getBejingDateByGmtDate(getGmtDate(new Date()));
		Calendar c = Calendar.getInstance();
		c.setTime(beijingDate);
		int month = c.get(Calendar.MONTH)+1;
		if(month == 1) month = 12;
		return month;
	}
	
	
	public static Date getTadayGMTDate(long timeoffset){
		
		 
		Date localDate = getOffsetDateByGmtDate(DateUtil.getGmtDate(null), timeoffset);
		
		Calendar c = Calendar.getInstance();
		c.setTime(localDate);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		
		int offset = Long.valueOf(timeoffset).intValue();
		Date localGmtDate = getGmtDateByTimeZone(c.getTime(), offset);
		
		return localGmtDate;
	}
	
	/**
	 * 根据指定的一个时间 获取这个时间7天前的日期
	 * @param startDate
	 * @return
	 */
	public static Date getRecentWeekEndDate(long timeoffset){
		Date startDate = getTadayGMTDate(timeoffset);
		Date endDate = new Date(startDate.getTime()- 7*24*3600*1000l);
		 
		return endDate;
	}
	
	
	
	public static Date getRecentMonthEndDate(long timeoffset){
		
		Date startDate = getTadayGMTDate(timeoffset);
		return new Date(startDate.getTime()- 30*24*3600*1000l);
	}
	
	
	/**
	 * 获取当前时间的时间戳
	 * jack
	 * 2013-12-10
	 */
	public static long getCurrentTimeInMillis(){
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}
	
	/**
	 * 获取上一个月的日期
	 * @param date
	 * @return
	 */
	public static Date getPreMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int mon = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(mon == 0){
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, Calendar.DECEMBER);
		}else if(mon == 1 && day == maxday){
			c.add(Calendar.MONTH, -1);
			int cuurentmaxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, cuurentmaxday);
		}else{
			c.add(Calendar.MONTH, -1);
		}
		return c.getTime();
	}
	
	/**
	 * 获取当前月份的最后一天
	 * @param date
	 * @return
	 */
	public static Date getCurrentMonthEnd(Date date){
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxday);
		return c.getTime();
	}
	
	public static Date getMonthStartTime(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getMonthEndTime(Date date){
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxday);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	
	public static void main(String ...strings) throws Exception{
	 
//		getSequenceDay(, endDate, dayValue, 3);
//		System.out.println(getTadayGMTDate(28800000));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a = sdf.parse("2014-07-31 16:00:00");

		Date b = getCurrentMonthEnd(a);
//		Date b = getPreMonthEnd(a);
		
		System.out.println(sdf.format(b));
	}
	
	
	
	
	
}
