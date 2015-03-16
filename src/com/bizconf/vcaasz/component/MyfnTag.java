package com.bizconf.vcaasz.component;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 用于页面显示数据
 * @author martin
 * @date 2013-3-27
 */
@Component 
public class MyfnTag  {
	
	/**
	 * 计算两个时间点相差的分钟数(不足一分钟按一分钟计算)
	 * @param join 入会时间
	 * @param leave 离会时间
	 * */
	public static int getDurationForConf(Date join,Date leave){
		
		Calendar c0 = Calendar.getInstance();
		c0.setTime(join);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(leave);
		
		long seconds = (c1.getTimeInMillis() - c0.getTimeInMillis())/(1000);
		int durationMinutes  = (int) (seconds / 60);
		if(seconds % 60 != 0){
			durationMinutes++;
		}
		int sec = c1.get(Calendar.SECOND) - c0.get(Calendar.SECOND);
		if (sec < 0) {
			durationMinutes ++;
		}
		return durationMinutes;
	}
	
	
	/**
	 * 通过会议id与会议类型进行加密
	 * @param joinType 会议类型
	 * @param confId 会议id
	 * @return
	 * */
	public static String encryMD5(String joinType,String confId){
	
		String encrypt = "bizconfzoom"+joinType+confId;
		MD5 md5 = new MD5();
		String md5Str = md5.encrypt(encrypt);
		return md5Str;
	}
	
	
	/**
	 * 判断license是否可删除 （是否已生效）
	 * @param date
	 * @return
	 */
	public static boolean licenseDeleteable(Date date){
		Date now = DateUtil.getGmtDate(null);
		if(date.after(now)){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 判断站点7天内是否到期
	 * @param date
	 * @return
	 */
	public static boolean siteExpiredInWeek(Date date){
		Date now = DateUtil.getGmtDate(null);
		long expireTime = date.getTime();
		long nowTime = now.getTime();
		if(date.before(now)){
			return false;
		}
		long gapTime = expireTime-nowTime;
		//System.out.println("expiredate"+date+"expireTime: =="+expireTime+"nowTime"+nowTime+"gapTime =="+gapTime);
		//gapTime<0 说明已过期  
		//&& gapTime>-24*3600000l 
		if(gapTime<(7*24*3600000l)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断站点是否过期
	 * @param date
	 * @return
	 */
	public static boolean siteExpired(Date date){
		Date now = DateUtil.getGmtDate(null);
		long expireTime = date.getTime();
		long nowTime = now.getTime();
		long gapTime = expireTime-nowTime;
		if(gapTime<=0){
			return true;
		}
		return false;
	}
	
	/**
	 * @param date 北京时间
	 * */
	public static String fmtDateByBJ(String parten,Date date,Integer timezone){
		if(date == null){
			return "";
		}
		if(StringUtil.isEmpty(parten)){
			parten = "yyyy-MM-dd HH:mm:ss";
		}
		if(timezone == null){
			timezone = 28800000;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(parten);
		//转换成标准时间
		Date gmtDate = DateUtil.getGmtDate(date);
		//生成指定的时区时间
		Date localDate = DateUtil.getOffsetDateByGmtDate(gmtDate, timezone.longValue());
		return sdf.format(localDate);
	}
	
	
	public static String fmtDate(String parten,Date date,Integer timezone){
		if(date == null){
			return "";
		}
		if(StringUtil.isEmpty(parten)){
			parten = "yyyy-MM-dd HH:mm:ss";
		}
		if(timezone == null){
			timezone = 28800000;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(parten);
		Date localDate = DateUtil.getOffsetDateByGmtDate(date, timezone.longValue());
		return sdf.format(localDate);
	}
	
	
	//通过账单日期确定该账单的开始日期
	public static  String  getMonthStartDateByBillDate(Date billDate,Integer offsetmaill){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(billDate);
			String year = String.valueOf(c.get(Calendar.YEAR));
			String month = String.valueOf(c.get(Calendar.MONTH)+1);
			return sdf.format(DateUtil.getMonthStratDate(year, month, offsetmaill));
	}
	
	//通过账单日期确定该账单的开始日期
	public static  String  getMonthEndDateByBillDate(Date billDate,Integer offsetmaill){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(billDate);
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		Date startDate = DateUtil.getMonthStratDate(year, month, offsetmaill);
		Date endDate = new Date(DateUtil.getMonthEndDate(startDate).getTime()-1000l);
		return sdf.format(endDate);
	}
	
	/**
	 * 通过开始时间 和时长获取结束时间
	 * @param startDate 开始时间
	 * @param duration	时长 单位（s）
	 * @return
	 */
	public static Date getEndDateView(Date startDate,Integer duration){
		return new Date(startDate.getTime()+duration*1000l);
	}
	
	/**
	 * 获取站点标志
	 * @param id
	 * @return
	 */
	public static String getSiteSignById(Integer id){
		if(id==null || id.intValue() < 1) return "";
		try {
			SiteBase site = DAOProxy.getLibernate().getEntity(SiteBase.class, id);
			return site.getSiteSign();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 获取站点名称
	 * @param id
	 * @return
	 */
	public static String getSiteNameById(Integer id){
		if(id==null || id.intValue() < 1) return "";
		try {
			SiteBase site = DAOProxy.getLibernate().getEntity(SiteBase.class, id);
			return site.getSiteName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取用户名
	 * @param id
	 * @return
	 */
	public static String getUserNameById(Integer id){
		if(id==null || id.intValue() < 1) return "";
		try {
			UserBase user = DAOProxy.getLibernate().getEntity(UserBase.class, id);
			return user.getTrueName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	public static String getCurrentLang(){
		return LanguageHolder.getCurrentLanguage();
	}
	
	
	
	
	public static boolean billingAvailable(String siteSign){
		
		if(StringUtil.isEmpty(siteSign))return false;
		
		String siteConfig = BaseConfig.getInstance().getString("billingSites","");
		
		if(siteConfig.isEmpty()){
			return false;
		}else if("all".equals(siteConfig)){
			return true;
		}
		
		String sites[] = siteConfig.split(",");
		
		for (int i = 0; i < sites.length; i++) {
			
			if(sites[i].equals(siteSign))return true;
		}
		return false;
	}
}
