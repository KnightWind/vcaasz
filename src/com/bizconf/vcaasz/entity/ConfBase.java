package com.bizconf.vcaasz.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.MeetingStartType;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.ObjectUtil;

public class ConfBase implements Cloneable, java.io.Serializable{
	// Fields
	private static final long serialVersionUID = -8834082789962143901L;
	private Integer id;
	/*
	 * 站点ID号
	 * */
	private Integer siteId = 0;
	/*
	 * 周期会议设置ID
	 * */
	private Integer cycleId = 0;
	/*
	 * 会议名称
	 * */
	private String confName = "";
	/*
	 * 会议描述
	 * */
	private String confDesc = "";
	
	/*
	 * 会议功能：
	 * 		0、预约会议
	 * 		1 、即时会议
	 * 		2、周期会议
	 * */
	private Integer confType = ConfType.SCHEDULE;
	/*
	 * 会议开始时间
	 * */
	private Date startTime;
	
	/*
	 * 会议开始时间备份（从数据库中取出的GMT时间备份）
	 * */
	private Date startTimeGmt = null;
	/*
	 * 会议时间长度，分钟 为单位
	 * */
	private Integer duration = 0;

	
	/*
	 * 会议结束时间
	 * */
	private Date endTime;
	/*
	 * 主持人ID号
	 * */
	private Integer compereUser;
	
	
	
	/*
	 * 主持人姓名
	 * */
	private String compereName;
 
	 
	
	 
	/*
	 * 电话会议号
	 * */
	private String callPhone = "";
	/*
	 * H323 password
	 * */
	private String phonePass = "";
	/*
	 * 会议最大人数
	 * */
	private Integer maxUser = 0;
	 
	
	/*
	 * 会议状态
	 *  0,//预约成功                
		2,//正在召开              
		3,//已结束 
		9,//取消的会议
		99,//异常会议
	 * */
	private Integer confStatus = ConfStatus.SCHEDULED.getStatus();

	/*
	 * 创建时间
	 * */
	private Date createTime = DateUtil.getGmtDate(null);
	/*
	 * 创建者ID号
	 * */
	private Integer createUser;
	
	/*
	 * 创建会议者类别
	 * 		1 client
	 * 		2 portal
	 * */
	private Integer createType = 2;
	/*
	 * 删除标志 ： 
	 * 		0：无效数据  
	 * 		1、正常使用  
	 * 		2、已删除
	 * */
	private Integer delFlag = 1;
	/*
	 * 删除时间
	 * */
	private Date delTime;
	/*
	 * 删除用户的ID号
	 * */
	private Integer delUser = 0;
	/*
	 * 删除用户的类别
	 * 		0：无效数据
	 * 		1、站点管理员
	 * 		2、普通（企业）用户
	 * 		999、系统管理员
	 * */
	private Integer delType = 2;
	
	
	/*
	 * 站点时区
	 */
	private Integer timeZone;
	
	/**
	 * 站点时区ID
	 */
	private Integer timeZoneId;
	
	/**
	 * 主持人密码
	 */
	private String hostKey = "";
	/**
	 * 是否永久会议
	 */
	private Integer permanentConf = 0; // 0：普通会议  1：个人会议室
	
	
	/**
	 * 主持人开启会议的关系
	 * */
	//是否支持先于主持人入会 0 false 1:TRUE
	private int optionJbh = 0;
	
	/**
	 * 会议开始类型 ：video 或 screen_share
	 * */
	private int optionStartType = MeetingStartType.VIDEO.getStatus();
	
	/**
	 * 加入会议url
	 * */
	private String joinUrl;
	/**
	 * 开始会议url
	 * */
	private String startUrl;
	
	/*
	 * 对应zoom会议ID号
	 * */
	private String confZoomId;
	
	
	/**
	 * 实际会议开始时间
	 */
	private Date actulStartTime;
	
	/**
	 * 会议的UUID
	 * */
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getActulStartTime() {
		return actulStartTime;
	}

	public void setActulStartTime(Date actulStartTime) {
		this.actulStartTime = actulStartTime;
	}

	public String getConfZoomId() {
		return confZoomId;
	}

	public void setConfZoomId(String confZoomId) {
		this.confZoomId = confZoomId;
	}

	public String getJoinUrl() {
		return joinUrl;
	}

	public void setJoinUrl(String joinUrl) {
		this.joinUrl = joinUrl;
	}

	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	/** default constructor */
	public ConfBase() {
	}

	// Property accessors
	
	public ConfBase(Integer id, String confHwid, Integer siteId,
			Integer cycleId, String confName, String confDesc,
			Integer publicFlag, String publicConfPass, Integer confType,
			Date startTime, Date startTimeGmt, Integer duration, Date endTime,
			Integer compereUser, String compereName, String compereSecure,
			String userSecure, String cryptKey, String callPhone,
			String phonePass, Integer maxUser, Integer maxAudio,
			Integer maxVideo, String videoType, String maxDpi,
			String defaultDpi, Integer aheadTime, Integer openIpad,
			String funcBits, String priviBits, String clientConfig,
			Integer confStatus, Integer soapStatus, Integer confVersion,
			Date createTime, Integer createUser, Integer createType,
			Integer delFlag, Date delTime, Integer delUser, Integer delType,
			Integer timeZone, Integer timeZoneId, Integer pcNum,
			Integer phoneNum, String hostKey, Integer permanentConf,
			Integer belongConfId, Integer allowCall, int optionJbh,
			int optionStartType, String joinUrl, String startUrl,
			String confZoomId, String timeZoneSecond, Date actulStartTime,
			String uuid) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.cycleId = cycleId;
		this.confName = confName;
		this.confDesc = confDesc;
		 
		this.confType = confType;
		this.startTime = startTime;
		this.startTimeGmt = startTimeGmt;
		this.duration = duration;
		this.endTime = endTime;
		this.compereUser = compereUser;
		this.compereName = compereName;
		 
		this.callPhone = callPhone;
		this.phonePass = phonePass;
		this.maxUser = maxUser;
		 
		this.confStatus = confStatus;
		 
		this.createTime = createTime;
		this.createUser = createUser;
		this.createType = createType;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.delType = delType;
		this.timeZone = timeZone;
		this.timeZoneId = timeZoneId;
		 
		this.hostKey = hostKey;
		this.permanentConf = permanentConf;
		 
		this.optionJbh = optionJbh;
		this.optionStartType = optionStartType;
		this.joinUrl = joinUrl;
		this.startUrl = startUrl;
		this.confZoomId = confZoomId;
		this.actulStartTime = actulStartTime;
		this.uuid = uuid;
	}

	public int getOptionJbh() {
		return optionJbh;
	}

	public void setOptionJbh(int optionJbh) {
		this.optionJbh = optionJbh;
	}

	public int getOptionStartType() {
		return optionStartType;
	}

	public void setOptionStartType(int optionStartType) {
		this.optionStartType = optionStartType;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getConfHwid() {
//		return this.confHwid;
//	}
//
//	public void setConfHwid(String confHwid) {
//		this.confHwid = confHwid;
//	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getCycleId() {
		return this.cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}
	 

	public String getConfName() {
		return this.confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	public String getConfDesc() {
		return this.confDesc;
	}

	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
	}

	public Integer getConfType() {
		return this.confType;
	}

	public void setConfType(Integer confType) {
		this.confType = confType;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		if(this.startTimeGmt == null){
			this.startTimeGmt = startTime;
		}
	}

	public Date getStartTimeGmt() {
		return startTimeGmt;
	}

	public void setStartTimeGmt(Date startTimeGmt) {
		this.startTimeGmt = startTimeGmt;
	}

	public Integer getDuration() {
		if(this.duration!=null && this.duration.intValue()>0){
			return this.duration;
		}else if(this.endTime!=null){
			Long l = new Long((this.endTime.getTime()-this.startTime.getTime())/60000);
			return l.intValue();
		}else{
			return 0;
		}
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getEndTime() {
//		if(this.startTime==null){
//			this.endTime=null;
//		}else{
//			Calendar calendar=Calendar.getInstance();
//			calendar.setTime(this.startTime);
//			if(duration!=null && duration.intValue() != 0){
//				calendar.add(Calendar.MINUTE, duration);
//			}
//			endTime=calendar.getTime();
//			calendar=null;
//		}
		
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCompereUser() {
		return this.compereUser;
	}

	public void setCompereUser(Integer compereUser) {
		this.compereUser = compereUser;
	}

	public String getCompereName() {
		return this.compereName;
	}

	public void setCompereName(String compereName) {
		this.compereName = compereName;
	}

	public String getCallPhone() {
		return this.callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getPhonePass() {
		return this.phonePass;
	}

	public void setPhonePass(String phonePass) {
		this.phonePass = phonePass;
	}

	public Integer getMaxUser() {
		return this.maxUser;
	}

	public void setMaxUser(Integer maxUser) {
		this.maxUser = maxUser;
	}

	 
 
 
	public Integer getConfStatus() {
		return this.confStatus;
	}

	public void setConfStatus(Integer confStatus) {
		this.confStatus = confStatus;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getCreateType() {
		return this.createType;
	}
	
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public Integer getDelUser() {
		return this.delUser;
	}

	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}

	public Integer getDelType() {
		return this.delType;
	}

	public void setDelType(Integer delType) {
		this.delType = delType;
	}
	
	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getTimeZoneId() {
		return timeZoneId;
	}
	
	public void setTimeZoneId(Integer timeZoneId) {
		this.timeZoneId = timeZoneId;
	}


	public String getHostKey() {
		return hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public Integer getPermanentConf() {
		return permanentConf;
	}

	public void setPermanentConf(Integer permanentConf) {
		this.permanentConf = permanentConf;
	}
	
	//added by Chris Gao
	public String getTimeZoneDesc() {
		String lan = LanguageHolder.getCurrentLanguage();
		return getTimeZoneDesc(lan);
	}
	//add by Martin Wang
	public String getTimeZoneDesc(String lang) {
		int timezone = this.timeZone/3600000;
		String offsetHour = " ";
		if(timezone>0){
			offsetHour += "GMT+" +String.valueOf(timezone) +":00";
		}else {
			offsetHour += "GMT" +String.valueOf(timezone) +":00";
		}
		return ResourceHolder.getInstance().getResource(lang,"website.timezone.city." + this.timeZoneId)+offsetHour;
	}
	
	
	/**
	 * 获取当前会议时区的时间
	 * wangyong
	 * 2013-3-28
	 */
	public Date getConfLocalTime() {
		return DateUtil.getOffsetDateByGmtDate(DateUtil.getGmtDate(null), 
				(long)this.getTimeZone().intValue());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * 掉用华为AS接口创建会议时调用
	 * mediabits媒体类型。01字符串，0表示选取，1不选取
		第1位：音频
		第2位：视频
		第3位：数据如001标识只有数据媒体，111表示多媒体媒体都有
	 */
	public String getMediaBits() {
		if (this.confType.intValue() == 2 || this.confType.intValue() == 3) {
			 return "111";
		}
		return "101";
	}
	
	/**
	 * 是否有电话功能
	 */
	public boolean hasPhoneFunc() {
		if (this.confType.intValue() == 1 || this.confType.intValue() == 3) {
			 return true;
		}
		return false;
	}
	
	/**
	 * 是否有视频功能
	 */
	public boolean hasVideo() {
		if (this.confType.intValue() == 2 || this.confType.intValue() == 3) {
			 return true;
		}
		return false;
	}
	
	/**
	 * 站点用户获取用户喜好设置时区的会议
	 * 1.若currentUser为null，则返回该会议的时区时间
	 * @param currentUser 该user对象拥有用户的喜好时区值
	 * wangyong
	 * 2013-8-29
	 */
	public ConfBase getOffsetConf(UserBase currentUser, ConfBase conf){
		if(conf != null){
			String[] fields = new String[]{"startTime", "endTime"};
			long offset = 0 ;
			if(currentUser != null){
				offset = currentUser.getTimeZone();
			}else{
				offset = this.timeZone;
			}
			conf = (ConfBase)ObjectUtil.offsetDate(conf, offset, fields);
		}
		return conf; 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfBase other = (ConfBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean isBelongPermanentConf(){
		if(this.getPermanentConf()!=null && this.getPermanentConf().intValue() ==1){
			return true;
		}
		else if(getEndTime()!=null && (getEndTime().getTime() - getStartTime().getTime())>24*3600000l){
			setPermanentConf(1);
			return true;
		}
		return false;
	}
	
	/**
	 * 是否是portal端周期会议
	 * @return
	 */
	public boolean isPortalCycleConf(){
		
		return (this.confType.equals(ConfType.RECURR) && this.createType.equals(ConfType.CREATE_TYPE_PROTAL))||this.cycleId>0;
	}
	
	/**
	 * 是否是客户端创建的周期会议
	 * @return
	 */
	public boolean isClientCycleConf(){
		return this.confType.equals(ConfType.RECURR) && this.createType.equals(ConfType.CREATE_TYPE_CLIENT);
	}
	
	/**
	 * 判断该会议是否为 个人会议室
	 * @return
	 */
	public boolean isPmi(){
		return this.permanentConf.equals(ConfType.PERMANENT_CONF_TRUE);
	}
	
	/**
	 *  判断是否为客户端创建的普通预约会议
	 * @return
	 */
	public boolean isClientScheduleConf(){
		
		return this.confType.equals(ConfType.SCHEDULE) && this.createType.equals(ConfType.CREATE_TYPE_CLIENT);
	}
	 

	/**
	 * 创建即时会议时初始化会议信息
	 * wangyong
	 * 2013-9-3
	 */
	public void initImmediaConf(DefaultConfig defaultConfig, SiteBase siteBase, UserBase user){
		this.siteId = siteBase.getId();
		this.cycleId = 0;
		Integer timeZone = 0;
		Integer timeZoneId = 0;
		if(user.getTimeZone() != null){
			timeZone = user.getTimeZone();
			timeZoneId = user.getTimeZoneId();
		}else if(siteBase.getTimeZone() != null){
			timeZone = siteBase.getTimeZone();
			timeZoneId = siteBase.getTimeZoneId();
		}
		this.timeZone = timeZone;
		this.timeZoneId = timeZoneId;
		this.startTime = DateUtil.getGmtDate(null);
		this.duration = ConfConstant.CONF_DEFAULT_DURATION;
		this.endTime = DateUtil.addDateMinutes(this.getStartTime(), this.getDuration());
		this.compereUser = user.getId();
		this.compereName = user.getTrueName();
		this.maxUser = 2;
		 
		this.createTime = DateUtil.getGmtDate(null);   //创建时间初始化为GMT时间
		this.createUser = user.getId();
		try {
			this.delTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过外部outer接口进会，会议不存在时创建会议，初始化confBase对象
	 */
	public void initConfBase(ConfOuter confOuter, SiteBase siteBase, EmpowerConfigService empowerConfigService, ConfService confService){
		if(confOuter!=null && siteBase!=null){
			this.setConfName(confOuter.getMtgTitle());
			this.setConfDesc(confOuter.getMtgTitle());
			this.setConfStatus(ConfConstant.CONF_STATUS_SUCCESS);
			this.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
			 
			this.setCreateTime(DateUtil.getGmtDate(null));
			this.setSiteId(siteBase.getId());
			this.setCreateUser(0);
			if("1".equals(confOuter.getUserType())){
				this.setCreateUser((IntegerUtil.parseIntegerWithDefaultZero(confOuter.getUserId())+200000000));
			}
			this.setCycleId(0);
		 
			this.setConfType(2);
			this.setHostKey(confOuter.getHostPwd());
			Date startTime=DateUtil.addDateMinutes(DateUtil.getGmtDate(null),10);
			Date endTime=DateUtil.addDateMinutes(startTime, ConfConstant.CONF_DEFAULT_DURATION);
			this.setStartTime(startTime);
			this.setDuration(ConfConstant.CONF_DEFAULT_DURATION);
			this.setEndTime(endTime);
			this.setCompereUser(0);
			this.setCompereName("");
			this.setMaxUser(2);
			Calendar calendar=Calendar.getInstance();
			calendar.clear();
			calendar.set(1970, 0, 1);
			this.setDelTime(calendar.getTime());
			 
			 
			this.setTimeZone(siteBase.getTimeZone());
			this.setTimeZoneId(siteBase.getTimeZoneId());
			this.setPermanentConf(ConfConstant.CONF_PERMANENT_UNABLE);
		}
	}

	public String getTimeZoneSecond() {
		int timezone = this.timeZone/3600000;
		if(timezone>0){
			return "GMT+" +String.valueOf(timezone);
		}else {
			return "GMT" +String.valueOf(timezone);
		}
	}

	
	public String getZoomStartTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date starTimeDate = this.getStartTime();
		//Date starTimeDate = DateUtil.getOffsetDateByGmtDate(this.getStartTime(), this.timeZone.longValue());
		String startTime = sdf.format(starTimeDate);
		return startTime.replace(" ", "T")+"Z";
	}
	
	
	public boolean getBooleanOptionJbh(){
		return this.getOptionJbh()==0?false:true;
	}
	
	
	public String getOptionStartTypeStr(){ 
		String type = "video";
		if(this.getOptionStartType() == 2){
			type = "screen_share";
		}
		return type;
	}
	
	
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		
		ConfBase conf = new ConfBase();
		conf.setTimeZone(-7200000);
		conf.setConfName("测试克隆");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			conf.setStartTime(sdf.parse("2014-07-16 12:00:00"));
			
			Date nextDate  = sdf.parse("2014-07-28 14:29:20");
			
			conf.setNextStartTime(nextDate);
			
			System.out.println(sdf.format(conf.getStartTime()));
			//ConfBase cloneConf = (ConfBase) conf.clone();
			//System.out.println(" 我是克隆会议 ："+cloneConf.getConfName());
			//System.out.println(cloneConf.getTimeZoneSecond());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Class<?> clazz = conf.getClass();
//		
//		Field[] fields = clazz.getDeclaredFields();
//		
//		for (int i = 0; i < fields.length; i++) {
//			Field f = fields[i];
//			System.out.println("name: "+f.getName());
//			
//			Deprecated deprecated = f.getAnnotation(Deprecated.class);
//			if(deprecated != null){
//				System.out.println("已经过时");
//			}else{
//				
//				System.out.println("没有过时");
//			}
//			
//		}
	}
	
	@Override
	public ConfBase clone() {
	
		try {
			return (ConfBase)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			
			ConfBase newConf = new ConfBase();
			newConf.setId(this.id);
			newConf.setActulStartTime(this.actulStartTime);
			 
			
			newConf.setCallPhone(this.callPhone);
			 
			newConf.setCompereName(this.compereName);
			newConf.setCompereUser(this.compereUser);
			newConf.setConfDesc(this.confDesc);
			newConf.setConfName(this.confName);
			newConf.setConfStatus(this.confStatus);
			newConf.setConfType(this.confType);
			newConf.setCreateType(this.createType);
			newConf.setCreateUser(this.createUser);
			newConf.setCycleId(this.cycleId);
			
			newConf.setDuration(this.duration);
			newConf.setDelFlag(this.delFlag);
			newConf.setDelTime(this.delTime);
			newConf.setDelUser(this.delUser);
			
			newConf.setEndTime(this.endTime);
			
			newConf.setJoinUrl(this.joinUrl);
		
			
			newConf.setStartTime(this.startTime);
			newConf.setStartTimeGmt(this.startTimeGmt);
			newConf.setSiteId(this.siteId);
			newConf.setStartUrl(this.startUrl);
			
			//newConf.setConfHwid(this.confHwid);
			newConf.setConfZoomId(this.confZoomId);
			newConf.setTimeZone(this.timeZone);
			newConf.setTimeZoneId(this.timeZoneId);
			
			return  newConf;
		}
	}
	
	/**
	 * 设置下一次会议时间
	 * @param nextTime
	 */
	public void setNextStartTime(Date nextTime){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.getStartTime());
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		
		Calendar calnext = Calendar.getInstance();
		calnext.setTime(nextTime);
		calnext.set(Calendar.HOUR_OF_DAY, hour);
		calnext.set(Calendar.MINUTE, min);
		calnext.set(Calendar.SECOND, s);
		
		this.setStartTime(calnext.getTime());
	}

	@Override
	public String toString() {
		return "ConfBase [id=" + id + ", siteId=" + siteId + ", cycleId="
				+ cycleId + ", confName=" + confName + ", confDesc=" + confDesc
				+ ", confType=" + confType + ", startTime=" + startTime
				+ ", startTimeGmt=" + startTimeGmt + ", duration=" + duration
				+ ", endTime=" + endTime + ", compereUser=" + compereUser
				+ ", compereName=" + compereName + ", callPhone=" + callPhone
				+ ", phonePass=" + phonePass + ", maxUser=" + maxUser
				+ ", confStatus=" + confStatus + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", createType=" + createType
				+ ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + ", delType=" + delType
				+ ", timeZone=" + timeZone + ", timeZoneId=" + timeZoneId
				+ ", hostKey=" + hostKey + ", permanentConf=" + permanentConf
				+ ", optionJbh=" + optionJbh + ", optionStartType="
				+ optionStartType + ", joinUrl=" + joinUrl + ", startUrl="
				+ startUrl + ", confZoomId=" + confZoomId + ", actulStartTime="
				+ actulStartTime + ", uuid=" + uuid + "]";
	}
	
	
}