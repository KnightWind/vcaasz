package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.impl.BaseService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class ConfLogicImpl extends BaseService implements ConfLogic {
	
	 
	@Autowired
	ZoomMeetingOperationComponent zoomMeetingOperationComponent;
	
	/**
	 * 创建或修改会议时获取可用license
	 * wangyong
	 * 2013-3-26
	 */
	@Override
	public HashMap<String, Integer> getEffetLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser){
		HashMap<String, Integer> licenseMap = null;
		if(confBase == null){
			return licenseMap;
		}
		if(siteBase == null){
			return licenseMap;
		}
		if(siteBase.getLicense() == null || siteBase.getLicense().intValue() <= 0){
			return licenseMap;
		}
		Integer siteChargeMode=siteBase.getChargeMode();
		if(siteChargeMode == null || siteChargeMode.intValue() > SiteConstant.SITE_CHARGEMODE_SEATES || siteChargeMode.intValue() < SiteConstant.SITE_CHARGEMODE_NAMEHOST ){
			return licenseMap;
		}
		if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_NAMEHOST) { //NameHost模式
			licenseMap = getNameHostLicense(confBase, confCycle, siteBase, currentUser);
			return licenseMap;
		} else if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_ACTIVEUSER) { //ActiveUser模式
			licenseMap = getActiveUserLicense(confBase, confCycle, siteBase, currentUser);
			return licenseMap;
		} else if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_SEATES) { //seats模式
			licenseMap = getSeatsLicense(confBase, confCycle, siteBase);
			return licenseMap;
		} else if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_TIME){ //time模式
			licenseMap = getTimeModeLicense(confBase, confCycle, siteBase);
			return licenseMap;
		}
		return licenseMap;
	}
	
	/**
	 * NameHost模式下获取可用license数
	 * 1.只需要验证该主持人创建的所有会议license总数 < 创建站点时为该主持人分配的有效license
	 * 2.每个主持人的有效license不一定相同
	 * wangyong
	 * 2013-3-26
	 */
	private HashMap<String, Integer> getNameHostLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser){
		HashMap<String, Integer> licenseMap = new HashMap<String, Integer>();
		Date startGmtTime = DateUtil.getGmtDateByTimeZone(confBase.getStartTime(), siteBase.getTimeZone());
		Date endGmtTime = DateUtil.addDateMinutes(startGmtTime, confBase.getDuration());
		Integer license = 0;       //获取一段时间内的所有会议使用的总点数license
		Integer userLicense = 0;
		if(confCycle == null){       //非周期会议
			license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, currentUser);
			userLicense =  getUserEffetLicense(currentUser, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
			licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (userLicense-license) <= 0? 0 : (userLicense-license));
		}else{   //周期会议
			List<Date> dateList = DateUtil.getCycleDateFromScope(confCycle.getBeginDate(), confCycle.getEndDate(), confCycle.getCycleType(), confCycle.getCycleValue(), ConstantUtil.CYCLE_CONF_DATE_LIMIT);
			if(dateList != null && dateList.size() > 0){
				for(Date eachDate:dateList){
					Date eachStartTime = null;
					if(eachDate != null){
						eachStartTime = eachDate;
						eachStartTime.setHours(confBase.getStartTime().getHours());
						eachStartTime.setMinutes(confBase.getStartTime().getMinutes());
						startGmtTime = DateUtil.getGmtDateByTimeZone(eachStartTime, siteBase.getTimeZone());
						endGmtTime = DateUtil.addDateMinutes(eachStartTime, confBase.getDuration());
						license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, currentUser);
						userLicense =  getUserEffetLicense(currentUser, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
						licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (userLicense-license) <= 0? 0 : (userLicense-license));
					}
				}
			}
			dateList=null;
		}
		return licenseMap;
	}
	
	/**
	 * ActiveUser模式下获取可用license数
	 * 1.创建站点时，设置的站点最大点数即为每个主持人的最大license（包括动态分配的有效license）
	 * 2.只需要验证该主持人创建的所有会议license总数 < 创建站点为该站点分配的有效license
	 * wangyong
	 * 2013-3-26
	 */
	private HashMap<String, Integer> getActiveUserLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser){
		HashMap<String, Integer> licenseMap = new HashMap<String, Integer>();
		Date startGmtTime=DateUtil.getGmtDateByTimeZone(confBase.getStartTime(), siteBase.getTimeZone());
		Date endGmtTime=DateUtil.addDateMinutes(startGmtTime, confBase.getDuration());
		Integer license = 0;
		Integer siteLicense = 0;
		if(confCycle==null){    //非周期会议
			license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, currentUser);
			siteLicense =  getSiteEffetLicense(siteBase, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
			licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (siteLicense-license) <= 0? 0 : (siteLicense-license));
		}else{   //周期会议
			List<Date> dateList=DateUtil.getCycleDateFromScope(confCycle.getBeginDate(), confCycle.getEndDate(), confCycle.getCycleType(), confCycle.getCycleValue(), ConstantUtil.CYCLE_CONF_DATE_LIMIT);
			if(dateList!=null && dateList.size() > 0){
				for(Date eachDate:dateList){
					Date eachStartTime = null;
					if(eachDate != null){
						eachStartTime = eachDate;
						eachStartTime.setHours(confBase.getStartTime().getHours());
						eachStartTime.setMinutes(confBase.getStartTime().getMinutes());
						startGmtTime = DateUtil.getGmtDateByTimeZone(eachStartTime, siteBase.getTimeZone());
						endGmtTime = DateUtil.addDateMinutes(eachStartTime, confBase.getDuration());
						license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, currentUser);
						siteLicense =  getSiteEffetLicense(siteBase, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
						licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (siteLicense-license) <= 0? 0 : (siteLicense-license));
					}
				}
			}
			dateList=null;
		}
		return licenseMap;
	}
	
	/**
	 * seats模式下获取可用license数
	 * 1.只需要验证该站点下所有会议总人数 < 该站点分配到的有效license
	 * wangyong
	 * 2013-3-26
	 */
	@SuppressWarnings("deprecation")
	private HashMap<String, Integer> getSeatsLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase){
		HashMap<String, Integer> licenseMap = new HashMap<String, Integer>();
		Date startGmtTime=DateUtil.getGmtDateByTimeZone(confBase.getStartTime(), siteBase.getTimeZone());
		Date endGmtTime=DateUtil.addDateMinutes(startGmtTime, confBase.getDuration());
		Integer license = 0;
		Integer siteLicense = 0;
		if(confCycle==null){    //非周期会议
			license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, null);
			siteLicense =  getSiteEffetLicense(siteBase, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
			licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (siteLicense-license) <= 0? 0 : (siteLicense-license));
		}else{   //周期会议
			List<Date> dateList=DateUtil.getCycleDateFromScope(confCycle.getBeginDate(), confCycle.getEndDate(), confCycle.getCycleType(), confCycle.getCycleValue(), ConstantUtil.CYCLE_CONF_DATE_LIMIT);
			if(dateList!=null && dateList.size() > 0){
				for(Date eachDate:dateList){
					Date eachStartTime = null;
					if(eachDate != null){
						eachStartTime = eachDate;
						eachStartTime.setHours(confBase.getStartTime().getHours());
						eachStartTime.setMinutes(confBase.getStartTime().getMinutes());
						startGmtTime = DateUtil.getGmtDateByTimeZone(eachStartTime, siteBase.getTimeZone());
						endGmtTime = DateUtil.addDateMinutes(eachStartTime, confBase.getDuration());
						license = getLicenseByTime(siteBase, startGmtTime, endGmtTime, null);
						siteLicense =  getSiteEffetLicense(siteBase, startGmtTime, endGmtTime);   //获取当前用户（主持人）有效license总数
						licenseMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"), (siteLicense-license) <= 0? 0 : (siteLicense-license));
					}
				}
			}
			dateList=null;
		}
		return licenseMap;
	}
	
	/**
	 * time模式下获取可用license数
	 * 1.只需要验证该站点下所有会议总人数 < 该站点分配到的有效license
	 * 2.与seats模式相同，直接引用即可
	 * wangyong
	 * 2013-3-26
	 */
	private HashMap<String, Integer> getTimeModeLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase){
		return getSeatsLicense(confBase, confCycle, siteBase);
	}
	
	/**
	 * 获取一段时间内的所有会议使用的总点数license
	 * 1.若为seats模式，currentUser传null
	 * 2.若为NameHost模式，ActiveUser模式，currentUser传当前用户（主持人）
	 * wangyong
	 * 2013-3-26
	 */
	private Integer getLicenseByTime(SiteBase siteBase, Date startGmtTime, Date endGmtTime, UserBase currentUser){
		Integer license = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT SUM(tcb.`max_user`) AS licCount FROM t_conf_base tcb ");
		sqlBuffer.append(" WHERE 1=1 ");
		sqlBuffer.append("      and   (tcb.conf_status = ? or tcb.conf_status = ?) ");
		sqlBuffer.append("		and tcb.site_id = ? ");
		sqlBuffer.append("		and ( ");
		sqlBuffer.append("			(tcb.`start_time`<=? AND tcb.`end_time`>=?) ");
		sqlBuffer.append("			OR  (tcb.`start_time`<=? AND tcb.`end_time`>=?) ");
		sqlBuffer.append("			OR(tcb.`start_time`>=? AND tcb.`end_time`<=?) ");
		sqlBuffer.append("		)");
		valueList.add(ConfConstant.CONF_STATUS_SUCCESS);
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		valueList.add(siteBase.getId());
		valueList.add(endGmtTime);
		valueList.add(endGmtTime);
		valueList.add(startGmtTime);
		valueList.add(startGmtTime);
		valueList.add(startGmtTime);
		valueList.add(endGmtTime);
		if(currentUser != null && currentUser.getId() != null && currentUser.getId().intValue() > 0){
			sqlBuffer.append("   and create_user = ? ");
			valueList.add(currentUser.getId());
		}
		try {
			license = libernate.countEntityListWithSql(sqlBuffer.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("获取一段时间内的所有会议使用的总点数license错误！" + e);
		}
		return license;
	}
	
	/**
	 * 获取当前时间站点生效的license数
	 * wangyong
	 * 2013-3-26
	 */
	private Integer getSiteEffetLicense(SiteBase currentSite, Date startGmtTime, Date endGmtTime ){
		Integer license = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT SUM(lic.lic_num) AS licCount FROM t_license AS lic ");
		sqlBuffer.append(" WHERE 1=1 ");
		sqlBuffer.append(" AND lic.site_id = ? ");
		sqlBuffer.append(" AND lic.effe_date < ? ");
		sqlBuffer.append(" AND lic.expire_date > ? ");
		sqlBuffer.append(" AND lic.del_flag = ? ");
		valueList.add(currentSite.getId());
		valueList.add(startGmtTime);
		valueList.add(endGmtTime);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		try {
			license = libernate.countEntityListWithSql(sqlBuffer.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("获取当前时间站点生效的license数错误！" + e);
		}
		return license + currentSite.getLicense();
	}
	
	/**
	 * 获取当前时间主持人用户生效的license数
	 * wangyong
	 * 2013-3-26
	 */
	private Integer getUserEffetLicense(UserBase currentUser, Date startGmtTime, Date endGmtTime ){
		Integer license = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT SUM(lic.lic_num) AS licCount FROM t_license AS lic ");
		sqlBuffer.append(" WHERE 1=1 ");
		sqlBuffer.append(" AND lic.user_id = ? ");
		sqlBuffer.append(" AND lic.effe_date < ? ");
		sqlBuffer.append(" AND lic.expire_date > ? ");
		sqlBuffer.append(" AND lic.del_flag = ? ");
		valueList.add(currentUser.getId());
		valueList.add(startGmtTime);
		valueList.add(endGmtTime);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		try {
			license = libernate.countEntityListWithSql(sqlBuffer.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("获取当前时间主持人用户生效的license数错误！" + e);
		}
		return license;
	}
	
	/**
	 * 创建会议时验证站点所剩license（重新创建会议）
	 * wangyong
	 * 2013-3-14
	 */
	@Override
	public boolean createConfLicenseVali(ConfBase conf, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser){
		HashMap<String, Integer> licenseMap = getEffetLicense(conf, confCycle, siteBase, currentUser);
		if(licenseMap != null){
			Iterator<Entry<String, Integer>>  iter = licenseMap.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String, Integer> entry = iter.next();
				if(conf.getMaxUser() != null && entry.getValue() != null){
					if(conf.getMaxUser().intValue() > entry.getValue().intValue()){
						logger.debug("会议最大参会人数大于站点所剩license，无法保存会议");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 修改会议时验证站点所剩license
	 * wangyong
	 * 2013-3-14
	 */
	@Override
	public boolean updateConfLicenseVali(ConfBase conf, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser){
		HashMap<String, Integer> licenseMap = getEffetLicense(conf, confCycle, siteBase, currentUser);
		if(conf != null && conf.getId() != null && conf.getId().intValue() > 0){
			ConfBase confBase = null;
			try {
				confBase = libernate.getEntity(ConfBase.class, conf.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(licenseMap != null){
				Iterator<Entry<String, Integer>>  iter = licenseMap.entrySet().iterator();
				while(iter.hasNext()){
					Map.Entry<String, Integer> entry = iter.next();
					if(conf.getMaxUser() != null && entry.getValue() != null){
						//若为修改会议，则判断修改后的license是否大于当前可用的license+修改前的license
						if(conf.getMaxUser().intValue() > entry.getValue().intValue() + confBase.getMaxUser()){
							logger.debug("会议最大参会人数大于站点所剩license，无法保存会议");
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 保存会议时验证会议数据
	 * wangyong
	 * 2013-3-4
	 */
	@Override
	public boolean saveConfValidate(ConfBase conf, ConfCycle confCycle, SiteBase siteBase) {
		//补充正则表达式验证页面输入数据
		return true;
	}
	
//	
//	@Override
//	public HashMap<String,Integer> getSurplusLicense(ConfBase confBase,ConfCycle confCycle, SiteBase siteBase){
//		Integer surpLicense=0;
//		HashMap<String,Integer> surpMap = null;
//		if(confBase==null){
//			return surpMap;
//		}
//		
//		if(siteBase == null){
//			return surpMap;
//		}
//		Integer siteLicense= siteBase.getLicense();
//		if(siteLicense==null || siteLicense.intValue()<=0){
//			return surpMap;
//		}
//		Integer siteChargeMode=siteBase.getChargeMode();
//		if(siteChargeMode==null || siteChargeMode.intValue()>SiteConstant.SITE_CHARGEMODE_SEATES || siteChargeMode.intValue()< SiteConstant.SITE_CHARGEMODE_NAMEHOST ){
//			return surpMap;
//		}
//		Integer timeZone=siteBase.getTimeZone();
//		if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_NAMEHOST) {
//			//NameHost模式
//			
//			
//
//		} else if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_ACTIVEUSER) {
//			//ActiveUser模式
//			
//
//		} else if (siteChargeMode.intValue() == SiteConstant.SITE_CHARGEMODE_SEATES) {
//			//Seat模式
//			//检测时间范围内所有会议的License总和
//			Date startTime=confBase.getStartTime();
//			Date startGmtTime=DateUtil.getGmtDateByTimeZone(startTime, timeZone);
//			Integer duration =confBase.getDuration();
//			Date endGmtTime=DateUtil.addDateMinutes(startGmtTime, duration);
//			StringBuffer sqlBuffer=new StringBuffer();
//			if(confCycle==null){//非周期会议
//				sqlBuffer.append("SELECT SUM(tcb.`max_user`) AS licCount FROM t_conf_base tcb ");
//				sqlBuffer.append(" WHERE  (tcb.conf_status = ? or tcb.conf_status = ?)");
//				sqlBuffer.append("		and tcb.site_id = ?");
//				sqlBuffer.append("		and ( ");
//				sqlBuffer.append("			(tcb.`start_time`<=? AND tcb.`end_time`>=?)");
//				sqlBuffer.append("			OR  (tcb.`start_time`<=? AND tcb.`end_time`>=?)");
//				sqlBuffer.append("			OR(tcb.`start_time`>=? AND tcb.`end_time`<=?) ");
//				sqlBuffer.append("		)");
//				Object[] values=new Object[9];
//				values[0]=ConfConstant.CONF_STATUS_SUCCESS;
//				values[1]=ConfConstant.CONF_STATUS_OPENING;
//				values[2]=siteBase.getId();
//				values[3]=endGmtTime;
//				values[4]=endGmtTime;
//				values[5]=startGmtTime;
//				values[6]=startGmtTime;
//				values[7]=startGmtTime;
//				values[8]=endGmtTime;
//				Integer license=null;
//				try {
//					System.out.println("sqlBuffer=="+sqlBuffer.toString());
//					license=libernate.countEntityListWithSql(sqlBuffer.toString(), values);
//					System.out.println("license=="+license);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				if(license==null){
//					license=0;
//				}
//				surpLicense=siteLicense-license;
//				if(surpLicense<=0){
//					surpLicense=0;
//				}
//				
////				Decimal d;
////				BigDecimal 
//				surpMap=new HashMap<String, Integer>();
//				surpMap.put(DateUtil.dateToString(startGmtTime,"yyyy-MM-dd"),surpLicense);
//			}else{//周期会议情况下
//				List<Date> dateList=DateUtil.getCycleDateFromScope(confCycle.getBeginDate(), confCycle.getEndDate(), confCycle.getCycleType(), confCycle.getCycleValue());
//				Object[] values=null;
//				if(dateList!=null && dateList.size() > 0){
//					surpMap=new HashMap<String, Integer>();
//					Date eachStartTime=null;
//					Date eachEndTime=null;
//					Integer eachCountLicense=null;
//					Integer eachSurpLicense=0;
//					String eachDateStr="";
//					for(Date eachDate:dateList){
//						if(eachDate!=null){
//							sqlBuffer=new StringBuffer();
//							eachStartTime=eachDate;
//							eachStartTime.setHours(confBase.getStartTime().getHours());
//							eachStartTime.setMinutes(confBase.getStartTime().getMinutes());
//							eachStartTime=DateUtil.getGmtDateByTimeZone(eachStartTime, timeZone);
//							eachEndTime=DateUtil.addDateMinutes(eachStartTime, duration);
//							eachDateStr=DateUtil.dateToString(eachDate,"yyyy-MM-dd");
//							sqlBuffer.append("SELECT SUM(tcb.`max_user`) AS licCount FROM t_conf_base tcb ");
//							sqlBuffer.append(" WHERE   (tcb.conf_status = ? or tcb.conf_status = ?)");
//							sqlBuffer.append("		and tcb.site_id = ?");
//							sqlBuffer.append("		and ( ");
//							sqlBuffer.append("			(tcb.`start_time`<=? AND tcb.`end_time`>=?)");
//							sqlBuffer.append("			OR  (tcb.`start_time`<=? AND tcb.`end_time`>=?)");
//							sqlBuffer.append("			OR(tcb.`start_time`>=? AND tcb.`end_time`<=?) ");
//							sqlBuffer.append("		)");
//							values=new Object[9];
////							paramNum=ii*9;
//							values[0]=ConfConstant.CONF_STATUS_SUCCESS;
//							values[1]=ConfConstant.CONF_STATUS_OPENING;
//							values[2]=siteBase.getId();
//							values[3]=eachEndTime;
//							values[4]=eachEndTime;
//							values[5]=eachStartTime;
//							values[6]=eachStartTime;
//							values[7]=eachStartTime;
//							values[8]=eachEndTime;
//							try {
//								System.out.println("sqlBuffer=="+sqlBuffer.toString());
//								eachCountLicense=libernate.countEntityListWithSql(sqlBuffer.toString(), values);
//								System.out.println("license=="+eachCountLicense);
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//							if(eachCountLicense==null || eachCountLicense.intValue()<=0){
//								eachCountLicense=0;
//							}
//							eachSurpLicense=siteLicense-eachCountLicense;
//							surpMap.put(eachDateStr, eachSurpLicense);
//						}
//					}
//				}
//				dateList=null;
//			}
//		}
//		
//		return surpMap;
//	}

	@Override
	public SiteBase getConfSiteBase(int siteId) {
		SiteBase siteBase = null;
		try{
			siteBase = libernate.getEntity(SiteBase.class, siteId);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return siteBase;
	}
	
	/**
	 * 查询会议周期对象
	 */
	@Override
	public List<ConfCycle> getConfCycles(List<Integer> cycIds) {
		List<ConfCycle> confCycles = new ArrayList<ConfCycle>();
		try{
			for(Integer id:cycIds){
				ConfCycle confCycle = libernate.getEntity(ConfCycle.class, id);
				confCycles.add(confCycle);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return confCycles;
	}
	
	/**
	 * 获取会议的周期ID
	 */
	@Override
	public List<Integer> getCycIds(List<ConfBase> confs) {
		// TODO Auto-generated method stub
		List<Integer> cycIds = new ArrayList<Integer>();
		for(ConfBase conf:confs){
			 if(conf.getCycleId()!=null &&conf.getCycleId()>0){
				 cycIds.add(conf.getCycleId());
			 }
		}
		return cycIds;
	}
	
	@Override
	public List<ConfCycle> getConfCyclesByConf(List<ConfBase> confs) {
		List<ConfCycle> confCycles = new ArrayList<ConfCycle>();
		if(confs==null){
			return confCycles;
		}
		try{
			for(ConfBase conf:confs){
				 if(conf.getCycleId()!=null &&conf.getCycleId()>0){
					 ConfCycle confCycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
					confCycles.add(confCycle);
				 }
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return confCycles;
	}

	@Override
	public ConfCycle getConfCycleByConf(ConfBase conf) {
		ConfCycle confCycle = null;
		try{
			
			if(conf.getCycleId()!=null &&conf.getCycleId()>0){
				  confCycle = libernate.getEntity(ConfCycle.class, conf.getCycleId());
				 
			 }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return confCycle;
	}
	
	@Override
	public List<ConfBase> getConfBasesByCreator(Integer creatorId) {
		List<ConfBase> confs = new ArrayList<ConfBase>();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_base WHERE del_flag = ?  and (create_user = ?  or compere_user=?)");
		Object[] values = new Object[]{
				ConstantUtil.DELFLAG_UNDELETE,
				creatorId,
				creatorId
		};
		
		try {
			confs = libernate.getEntityListBase(ConfBase.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据创建者id号获取会议信息出错！",e);
		}
		return confs;
	}
	
	
	
	/**
	 * 后台配置客户端功能缺省设置，与前台配置无关
	 * 笔记、视频、音频、聊天、公告、私聊、组聊、投票（默认全部开启，但不在页面上配置）
	 * 1.前台配置完成后，调用配置后台客户端默认功能
	 * wangyong
	 * 2013-4-10
	 */
	@Override
	public void setServerClientConfig(char[] clientConfig){
		clientConfig[ConfConstant.CLIENT_CONFIG_NOTE] = '1';            //笔记
		clientConfig[ConfConstant.CLIENT_CONFIG_VIDEO] = '1';           //视频
		clientConfig[ConfConstant.CLIENT_CONFIG_AUDIO] = '1'; 			//音频
		clientConfig[ConfConstant.CLIENT_CONFIG_CHAT] = '1';			//聊天
		clientConfig[ConfConstant.CLIENT_CONFIG_NOTICE] = '1';			//公告
		clientConfig[ConfConstant.CLIENT_CONFIG_PRIVATE_CHAT] = '1';    //私聊
		clientConfig[ConfConstant.CLIENT_CONFIG_GROUP_CHAT] = '1';		//组聊
		clientConfig[ConfConstant.CLIENT_CONFIG_VOTE] = '1';            //投票(问卷调查)
	}

	@Override
	public UserBase getConfCreator(String confHwId) {
		if (!StringUtil.isEmpty(confHwId)) {
			try {
				ConfBase conf =  libernate.getEntity(ConfBase.class, "conf_hwid", confHwId);
				if(conf!=null){
					return libernate.getEntity(UserBase.class, conf.getCreateUser());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public void immediatelyConfAuthority(ConfBase conf, UserBase user){
	 
		conf.setConfType(ConfType.INSTANT);
		conf.setStartTime(DateUtil.addDateMinutes(DateUtil.getGmtDate(null), ConfConstant.CONF_CONFIG_AHEADTIMES));      //即时会议为当前GMT时间+5分钟 
		conf.setEndTime(DateUtil.addDateMinutes(conf.getStartTime(), conf.getDuration()));
		conf.setConfDesc(ResourceHolder.getInstance().getResource("bizconf.jsp.user.immediaconf.desc"));
	}

	
	
	@Override
	public List<ConfBase> getHostActiveMeeting(int userId) {
		
		String sql = "select * from t_conf_base where del_flag=? and conf_type <>? and conf_type <>?  and create_user = ? and (conf_status=? or conf_status=?)";
		List<ConfBase> confs = null;
		try {
			confs = libernate.getEntityListCustomized(ConfBase.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,ConfType.INSTANT,ConfType.SUB_RECURR, userId,
				ConfConstant.CONF_STATUS_SUCCESS,ConfConstant.CONF_STATUS_OPENING});
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confs;
	}

	@Override
	public ConfBase getConfByZoomId(String zoomId) {
		if(StringUtil.isEmpty(zoomId)){
			return null;
		}
		String sql = "select * from t_conf_base where conf_zoom_id = ? ";
		try {
			return libernate.getEntityCustomized(ConfBase.class, sql, new Object[]{zoomId});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ConfBase getConfByZoomIdAndTypeIgnoreDelFlag(String zoomId,int confType) {
		if(StringUtil.isEmpty(zoomId)){
			return null;
		}
		String sql = "select * from t_conf_base where conf_zoom_id = ? and conf_type = ?";
		try {
			return libernate.getEntityCustomized(ConfBase.class, sql, new Object[]{zoomId,confType});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateConfStatus(ConfBase conf, int status) {
		if (conf == null)
			return false;
		if (status == ConfConstant.CONF_STATUS_SUCCESS)
			return false;
		try {
			Date now = DateUtil.getGmtDate(null);
			// 如果这是永久会议 或者是周期会议
			if (conf.isPmi() || conf.isClientCycleConf() || conf.isPortalCycleConf()) {
				if (ConfConstant.CONF_STATUS_OPENING == status) {
					conf.setConfStatus(status);
				} else {
					conf.setConfStatus(ConfConstant.CONF_STATUS_SUCCESS);
				}
			//普通会议
			} else {
				conf.setConfStatus(status);
				// 预约会议但还没到结束时间
				if (conf.getConfType().equals(ConfType.SCHEDULE) && conf.getEndTime().after(now)) {
					if (ConfConstant.CONF_STATUS_FINISHED.equals(status)) {
						conf.setConfStatus(ConfConstant.CONF_STATUS_SUCCESS);
					}
				}
			}
			DAOProxy.getLibernate().updateEntity(conf, "conf_status");
			logger.error("update conf status =" + status);
		} catch (Exception e) {
			logger.error("update conf status error", e);
			return false;
		}
		return true;
	}
	
	
	/**
	 * 获取下一次的会议开始时间
	 */
	@Override
	public Date getNextConfStartTime(ConfCycle cycle,int timezone) {
		
		Date now = DateUtil.getOffsetDateByGmtDate(DateUtil.getGmtDate(null),new Integer(timezone).longValue());
		cycle.getOffsetConf(cycle, timezone);
		
		Calendar c0 = Calendar.getInstance();
		c0.setTime(cycle.getBeginDate());
		int hour = c0.get(Calendar.HOUR_OF_DAY);
		int min = c0.get(Calendar.MINUTE);
		int s = c0.get(Calendar.SECOND);
		
		Calendar calnext = Calendar.getInstance();
		calnext.setTime(now);
		calnext.set(Calendar.HOUR_OF_DAY, hour);
		calnext.set(Calendar.MINUTE, min);
		calnext.set(Calendar.SECOND, s);
		calnext.set(Calendar.MILLISECOND, 0);
		Date cycleBeginDate = calnext.getTime();
		
		Date cycleEndDate = cycle.getEndDate();
		
		try {
			//如没有结束日期即永久周期
			if (cycle.getInfiniteFlag().equals(1)) {
			 
				List<Date> dataList = DateUtil.getCycleDateFromScope(cycleBeginDate,
						cycle.getEndDate(), cycle.getCycleType(),
				cycle.getCycleValue(), 3);
				
				if (dataList != null && !dataList.isEmpty()) {
					Collections.sort(dataList);
					for (Iterator iterator = dataList.iterator(); iterator
							.hasNext();) {
						Date nextDate = (Date) iterator.next();
						
						if(nextDate.after(now))return nextDate;
					}
					return null;
				} else {
					return null;
				}
				
				//按结束时间
			} else if (cycleEndDate != null
					&& cycle.getRepeatCount().equals(0)) {
				// 说明已经周期会议已经过期了
				if (now.after(cycleEndDate)) {
					return null;
				} else {
					List<Date> dataList = DateUtil.getCycleDateFromScope(
							cycleBeginDate, cycleEndDate, cycle.getCycleType(),
							cycle.getCycleValue(), 3);
					if (dataList != null && !dataList.isEmpty()) {
						Collections.sort(dataList);
						for (Iterator iterator = dataList.iterator(); iterator
								.hasNext();) {
							Date nextDate = (Date) iterator.next();
							
							if(nextDate.after(now))return nextDate;
						}
						return null;
					} else {
						return null;
					}
				}
				
				//按次数
			} else if (!cycle.getRepeatCount().equals(0)) {
	
				List<Date> dataList = DateUtil.getCycleDateFromScope(cycle.getBeginDate(),
						cycle.getEndDate(), cycle.getCycleType(),
						cycle.getCycleValue(), cycle.getRepeatCount());
				
				if (dataList != null && !dataList.isEmpty()) {
					Collections.sort(dataList);
					//Collections.reverse(dataList);
					Date lastDate = dataList.get(dataList.size()-1);
					if (lastDate.before(now)) {
							return null;
					} else {
						//遍历查询 找出最近一次周期时间
						for (Iterator iterator = dataList.iterator(); iterator
								.hasNext();) {
							Date date = (Date) iterator.next();
							if(date.after(now)) return date;
						}
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 根据本次开始时间获取下一次的会议开始时间
	 * @param startDate本次会议开始时间
	 * @param cycle 会议循环周期
	 * @param timezone 时区
	 */
	@Override
	public Date getNextConfStartTime(Date startDate,ConfCycle cycle,int timezone) {
		
		Date now = DateUtil.getOffsetDateByGmtDate(startDate,new Integer(timezone).longValue());
		cycle.getOffsetConf(cycle, timezone);
		
		Calendar c0 = Calendar.getInstance();
		c0.setTime(cycle.getBeginDate());
		int hour = c0.get(Calendar.HOUR_OF_DAY);
		int min = c0.get(Calendar.MINUTE);
		int s = c0.get(Calendar.SECOND);
		
		Calendar calnext = Calendar.getInstance();
		calnext.setTime(now);
		calnext.set(Calendar.HOUR_OF_DAY, hour);
		calnext.set(Calendar.MINUTE, min);
		calnext.set(Calendar.SECOND, s);
		calnext.set(Calendar.MILLISECOND, 0);
		Date cycleBeginDate = calnext.getTime();
		
		Date cycleEndDate = cycle.getEndDate();
		
		try {
			//如没有结束日期即永久周期
			if (cycle.getInfiniteFlag().equals(1)) {
			 
				List<Date> dataList = DateUtil.getCycleDateFromScope(cycleBeginDate,
						cycle.getEndDate(), cycle.getCycleType(),
				cycle.getCycleValue(), 3);
				
				if (dataList != null && !dataList.isEmpty()) {
					Collections.sort(dataList);
					for (Iterator iterator = dataList.iterator(); iterator
							.hasNext();) {
						Date nextDate = (Date) iterator.next();
						
						if(nextDate.after(now))return nextDate;
					}
					return null;
				} else {
					return null;
				}
				
				//按结束时间
			} else if (cycleEndDate != null
					&& cycle.getRepeatCount().equals(0)) {
				// 说明已经周期会议已经过期了
				if (now.after(cycleEndDate)) {
					return null;
				} else {
					List<Date> dataList = DateUtil.getCycleDateFromScope(
							cycleBeginDate, cycleEndDate, cycle.getCycleType(),
							cycle.getCycleValue(), 3);
					if (dataList != null && !dataList.isEmpty()) {
						Collections.sort(dataList);
						for (Iterator iterator = dataList.iterator(); iterator
								.hasNext();) {
							Date nextDate = (Date) iterator.next();
							
							if(nextDate.after(now))return nextDate;
						}
						return null;
					} else {
						return null;
					}
				}
				
				//按次数
			} else if (!cycle.getRepeatCount().equals(0)) {
	
				List<Date> dataList = DateUtil.getCycleDateFromScope(cycle.getBeginDate(),
						cycle.getEndDate(), cycle.getCycleType(),
						cycle.getCycleValue(), cycle.getRepeatCount());
				
				if (dataList != null && !dataList.isEmpty()) {
					Collections.sort(dataList);
					//Collections.reverse(dataList);
					Date lastDate = dataList.get(dataList.size()-1);
					if (lastDate.before(now)) {
							return null;
					} else {
						//遍历查询 找出最近一次周期时间
						for (Iterator iterator = dataList.iterator(); iterator
								.hasNext();) {
							Date date = (Date) iterator.next();
							if(date.after(now)) return date;
						}
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public boolean cloneAndSaveConf(ConfBase conf) {
		try {
			ConfBase cloneConf = conf.clone();
			cloneConf.setId(null);
			cloneConf.setConfZoomId("");
			cloneConf.setConfStatus(ConfStatus.FINISHED.getStatus());
			libernate.saveEntity(cloneConf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ConfCycle cycle = new ConfCycle();
		cycle.setCycleType(2);
		cycle.setRepeatCount(30);
		cycle.setCycleValue("6");
		cycle.setBeginDate(sdf.parse("2014-07-23 14:55:00"));
		cycle.setRepeatCount(1);
		cycle.setEndDate(sdf.parse("2100-07-23 14:55:00"));
		cycle.setInfiniteFlag(0);
		
		ConfLogicImpl imple = new ConfLogicImpl();
		Date startDate = imple.getNextConfStartTime(cycle,0);
		
		if(startDate!=null){
			
			System.out.println("下一次会议开始时间："+sdf.format(startDate));
			
		}else{
			
			System.out.println("周期已经结束！");
			
		}
	}

	@Override
	public String getCycleMode(ConfCycle cycle, String lang) {
		if(cycle == null) return "";
		if(StringUtil.isEmpty(lang)){
			lang = "zh-cn";
		}
		StringBuilder cycleMode = new StringBuilder("");
		if(cycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_MONTHLY.intValue()){      //按月循环的
			if(cycle.getCycleValue().indexOf(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT)>0){     //有分号的
				String[] monthValueArray = cycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT);
				String week = ResourceHolder.getInstance().getResource("system.month." + monthValueArray[1]);
				cycleMode.append(String.format(ResourceHolder.getInstance().getResource(lang,"system.by.month.week"), monthValueArray[0], week));   // 格式化字符串，按月(每月第几个周几)
			}else{
				cycleMode.append(String.format(ResourceHolder.getInstance().getResource(lang,"system.by.month.day"), cycle.getCycleValue()));   // 格式化字符串，按月(每月第几天)
			}
		}else if(cycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_DAILY.intValue()){    //按日循环的
			cycleMode.append(String.format(ResourceHolder.getInstance().getResource(lang,"system.by.day"), cycle.getCycleValue()));      // 格式化字符串，按日(每几天)
		}else if(cycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_WEEKLY.intValue()){   //按周循环的
			StringBuilder week = new StringBuilder();
			for(String weekValue : cycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)){
				week.append(ResourceHolder.getInstance().getResource(lang,"system.month." + weekValue)).append(",");
			}
			cycleMode.append(String.format(ResourceHolder.getInstance().getResource(lang,"system.by.week"), 
					week.deleteCharAt(week.lastIndexOf(",")).toString()));      // 格式化字符串，按周（每周几）
		}
		return cycleMode.toString();
	}

	@Override
	public String getCycleRepeatScope(ConfCycle cycle, String lang) {
		// TODO Auto-generated method stub
		if(cycle == null) return "";
		if(StringUtil.isEmpty(lang)){
			lang = "zh-cn";
		}
		StringBuilder repeatScope = new StringBuilder("");    //重复范围
		if(cycle.getRepeatCount() != null && cycle.getRepeatCount().intValue() > 0){
			repeatScope.append(String.format(ResourceHolder.getInstance().getResource(lang,"website.user.conf.info.repeat.time"), cycle.getRepeatCount().intValue()));
		}else if(cycle.getInfiniteFlag() != null && cycle.getInfiniteFlag().intValue() == 1){
			repeatScope.append(ResourceHolder.getInstance().getResource(lang,"website.user.conf.info.no.endtime"));
		}else{
			repeatScope.append(ResourceHolder.getInstance().getResource(lang,"website.user.conf.info.endtime") + " " + DateUtil.getDateStrCompact(cycle.getEndDate(), "yyyy-MM-dd"));
		}
		return repeatScope.toString();
	}

	
	
}
