package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.util.DateUtil;


/**
 * 站点基本信息表
 * @author shhc
 */
public class SiteBase implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -548700708862480087L;
	
	/*
	 * 主键ID
	 */
	private Integer id;

	/*
	 * 站点名称
	 */
	private String siteName;
	/*
	 * 站点名称英文名称
	 */
	private String enName;
	
	/*
	 * Client显示名称
	 */
	private String clientName;
	
	/*
	 * 站点LOGO
	 * 各种不同规格的图片用逗号分隔
	 */
	private String siteLogo;
	
	/**
	 * 站点banner
	 */
	private String siteBanner = "";
	
	/**
	 * 是否可以自定义站点风格
	 */
	private int siteDiy = 0;
	
	/*
	 * 站点标识
	 * 删除标志为1时，不能重复
	 */
	private String siteSign;
	
	/*
	 * 站点描述
	 */
	private String siteDesc;
	
	/*
	 * 站点时区
	 */
	private Integer timeZone;
	
	/**
	 * 站点时区ID
	 */
	private Integer timeZoneId;
	/*
	 * 站点状态
	 * 试用、正式
	 */
	private Integer siteFlag = 0;
	
	/*
	 * 租用模式
	 * 包月，计时两选项，单选按钮，一个站点只能择其一
	 * 1.包月 2.计时
	 */
	private Integer hireMode;
	
	/*
	 * 计费模式
	 * 选中包月：出现三种计费模式：name host, active user, seats选项，为单选按钮
	 * 1.name host 2.active user 3.seats 4.time
	 */
	private Integer chargeMode;
	
	/*
	 * 站点模式
	 * 站点下的会议模式：0，会议(服务器混音)；1、课堂(Client混音)
	 */
	private Integer siteModel;
	
	/*
	 * 销售代表的邮箱
	 */
	private String marketorEmail;
	
	/*
	 * 授权数
	 * 最大并发人数
	 */
	private Integer license;
	
	/*
	 * 会议类型
	 * 1、纯数据会议、
	 *    数据+HW电话
	 *    数据+会畅电话
	 */
	private Integer mtgType;
	
	/*
	 * 锁定状态：
	 * 0、无效数据
	 * 1、解锁状态
	 * 2、锁定状态
	 */
	private Integer lockFlag = 0;
	
	/*
	 * 生效时间
	 */
	private Date effeDate = new Date();
	
	/*
	 * 过期时间
	 */
	private Date expireDate;
	
	/*
	 * 创建时间
	 */
	private Date createTime;
	
	/*
	 * 创建者ID号
	 */
	private Integer createUser;
	
	/*
	 * 删除标志 ：  0：无效数据  1、正常使用  2、已删除
	 */
	private Integer delFlag = 1;
	
	/*
	 * 删除时间
	 */
	private Date delTime;
	
	/*
	 * 删除者ID号
	 */
	private Integer delUser;
	
	
	private String priseId;
	
	/*
	 * 是否发送到期提醒   0未送过期提醒   >0则表示已发送邮件提醒 
	 */
	private Integer sendRemindFlag = 0;
	// Constructors

	//是否为虚拟站点 0:非虚拟  1:虚拟
	private Integer isVirtualSite = 0;
	/*
	 * 屏幕共享时时间间隔 ，单位毫秒，默认1000
	 */
	private Integer frameTimeInterval = 1000;
	
	/*
	 * 创建（修改）站点权限：新增字段
	 * 最大并发会场数
	 * 该值为0，代表不限制并发会场数
	 * 2013.7.10
	 */
	private int syncConfNum = 0;
	
	// 是否启用服务器混音
	private int audioServerMix = 0;
	
	//使用的服务器分组
	private int msGroupId = 0; 
	
	private String  downloadUrl="";
	
	//对外接口站点提供的key，与系统约定的加密KEY
	private String md5Key = "";
	
	/** default constructor */
	public SiteBase() {
	}

	/** minimal constructor */
	public SiteBase(Date createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public SiteBase(Integer id, String siteName, String enName,
			String clientName, String siteLogo, String siteBanner, int siteDiy,
			String siteSign, String siteDesc, Integer timeZone,
			Integer timeZoneId, Integer siteFlag, Integer hireMode,
			Integer chargeMode, Integer siteModel, String marketorEmail,
			Integer license, Integer mtgType, Integer lockFlag, Date effeDate,
			Date expireDate, Date createTime, Integer createUser,
			Integer delFlag, Date delTime, Integer delUser, String priseId,
			Integer sendRemindFlag, Integer isVirtualSite,
			Integer frameTimeInterval, int syncConfNum, int audioServerMix,
			int msGroupId, String downloadUrl, String md5Key) {
		super();
		this.id = id;
		this.siteName = siteName;
		this.enName = enName;
		this.clientName = clientName;
		this.siteLogo = siteLogo;
		this.siteBanner = siteBanner;
		this.siteDiy = siteDiy;
		this.siteSign = siteSign;
		this.siteDesc = siteDesc;
		this.timeZone = timeZone;
		this.timeZoneId = timeZoneId;
		this.siteFlag = siteFlag;
		this.hireMode = hireMode;
		this.chargeMode = chargeMode;
		this.siteModel = siteModel;
		this.marketorEmail = marketorEmail;
		this.license = license;
		this.mtgType = mtgType;
		this.lockFlag = lockFlag;
		this.effeDate = effeDate;
		this.expireDate = expireDate;
		this.createTime = createTime;
		this.createUser = createUser;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.priseId = priseId;
		this.sendRemindFlag = sendRemindFlag;
		this.isVirtualSite = isVirtualSite;
		this.frameTimeInterval = frameTimeInterval;
		this.syncConfNum = syncConfNum;
		this.audioServerMix = audioServerMix;
		this.msGroupId = msGroupId;
		this.downloadUrl = downloadUrl;
		this.md5Key = md5Key;
	}
	
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getIsVirtualSite() {
		return isVirtualSite;
	}

	public void setIsVirtualSite(Integer isVirtualSite) {
		this.isVirtualSite = isVirtualSite;
		//如果虚拟站点，则不需要过期提醒
		if(isVirtualSite!=null&&isVirtualSite.intValue()==1){
			this.sendRemindFlag = 100;
		}
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSiteLogo() {
		return this.siteLogo;
	}

	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}
	
	public String getSiteBanner() {
		return siteBanner;
	}

	public void setSiteBanner(String siteBanner) {
		this.siteBanner = siteBanner;
	}

	
	public int getSiteDiy() {
		return siteDiy;
	}

	public void setSiteDiy(int siteDiy) {
		this.siteDiy = siteDiy;
	}

	public String getSiteSign() {
		return this.siteSign;
	}

	public void setSiteSign(String siteSign) {
		this.siteSign = siteSign;
	}

	public String getSiteDesc() {
		return this.siteDesc;
	}

	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}
	
	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getSiteFlag() {
		return this.siteFlag;
	}

	public void setSiteFlag(Integer siteFlag) {
		this.siteFlag = siteFlag;
	}

	public Integer getLicense() {
		return this.license;
	}

	public void setLicense(Integer license) {
		this.license = license;
	}

	public Integer getMtgType() {
		return this.mtgType;
	}

	public void setMtgType(Integer mtgType) {
		this.mtgType = mtgType;
	}

	public Integer getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(Integer lockFlag) {
		this.lockFlag = lockFlag;
	}
	
	public Integer getHireMode() {
		return hireMode;
	}

	public void setHireMode(Integer hireMode) {
		this.hireMode = hireMode;
	}

	public Integer getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(Integer chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Integer getSiteModel() {
		return siteModel;
	}

	public void setSiteModel(Integer siteModel) {
		this.siteModel = siteModel;
		if(siteModel!=null){
			this.audioServerMix = siteModel;
		}
	}

	public String getMarketorEmail() {
		return marketorEmail;
	}

	public void setMarketorEmail(String marketorEmail) {
		this.marketorEmail = marketorEmail;
	}

	public Date getEffeDate() {
		return this.effeDate;
	}

	public void setEffeDate(Date effeDate) {
		this.effeDate = effeDate;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	
	
	public Integer getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(Integer timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	
	public String getPriseId() {
		return priseId;
	}

	public void setPriseId(String priseId) {
		this.priseId = priseId;
	}
	
	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}

	//added by Chris Gao
	public String getTimeZoneDesc() {
		return getTimeZoneDesc("zh-cn");
	}
	public String getTimeZoneDesc(String lan) {
		return ResourceHolder.getInstance().getResource(lan,"website.timezone.city." + this.timeZoneId);
	}
	
	//added by jack wang
	public String getFullTimeZoneDesc() {
		return ResourceHolder.getInstance().getResource("website.timezone.city.zone." + this.timeZoneId);
	}
	
	
	public Date getSiteLocalTime() {
		return DateUtil.getOffsetDateByGmtDate(DateUtil.getGmtDate(null), 
				(long)this.getTimeZone().intValue());
	}
	
	public Integer getSendRemindFlag() {
		return sendRemindFlag;
	}

	public void setSendRemindFlag(Integer sendRemindFlag) {
		this.sendRemindFlag = sendRemindFlag;
	}

	public Integer getFrameTimeInterval() {
		return frameTimeInterval;
	}

	public void setFrameTimeInterval(Integer frameTimeInterval) {
		this.frameTimeInterval = frameTimeInterval;
	}
	
	public int getSyncConfNum() {
		return syncConfNum;
	}

	public void setSyncConfNum(int syncConfNum) {
		this.syncConfNum = syncConfNum;
	}

	public int getAudioServerMix() {
		return audioServerMix;
	}

	public void setAudioServerMix(int audioServerMix) {
		this.audioServerMix = audioServerMix;
	}

	public int getMsGroupId() {
		return msGroupId;
	}

	public void setMsGroupId(int msGroupId) {
		this.msGroupId = msGroupId;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	public void init(){
		if(this.siteLogo==null){
			this.siteLogo = "";
		}
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SiteBase other = (SiteBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SiteBase [id=" + id + ", siteName=" + siteName + ", enName="
				+ enName + ", clientName=" + clientName + ", siteLogo="
				+ siteLogo + ", siteBanner=" + siteBanner + ", siteDiy="
				+ siteDiy + ", siteSign=" + siteSign + ", siteDesc=" + siteDesc
				+ ", timeZone=" + timeZone + ", timeZoneId=" + timeZoneId
				+ ", siteFlag=" + siteFlag + ", hireMode=" + hireMode
				+ ", chargeMode=" + chargeMode + ", siteModel=" + siteModel
				+ ", marketorEmail=" + marketorEmail + ", license=" + license
				+ ", mtgType=" + mtgType + ", lockFlag=" + lockFlag
				+ ", effeDate=" + effeDate + ", expireDate=" + expireDate
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + ", priseId=" + priseId
				+ ", sendRemindFlag=" + sendRemindFlag + ", isVirtualSite="
				+ isVirtualSite + ", frameTimeInterval=" + frameTimeInterval
				+ ", syncConfNum=" + syncConfNum + ", audioServerMix="
				+ audioServerMix + ", msGroupId=" + msGroupId
				+ ", downloadUrl=" + downloadUrl + ", md5Key=" + md5Key + "]";
	}

	/**
	 * 获取还有多少天此站点到期
	 * @return
	 */
	public int getExpireDateNumber(){
		Date gmtNow = DateUtil.getGmtDate(null);
		if(expireDate.after(gmtNow)){
			Date localDate=DateUtil.getOffsetDateByGmtDate(gmtNow, (long)timeZone);
			Date localExpireDate=DateUtil.getOffsetDateByGmtDate(expireDate, (long)timeZone);
			return  DateUtil.getDateDiff(localDate,localExpireDate);
			//int i = new Long((expireDate.getTime()-gmtNow.getTime())).intValue()/(24*3600*1000);
			//return i;
		}
		return 0;
	}

	public SiteBase Copy(){
		SiteBase copySite = new SiteBase();
		copySite.id = id;
		copySite.siteName = siteName;
		copySite.enName = enName;
		copySite.clientName = clientName;
		copySite.siteLogo = siteLogo;
		copySite.siteSign = siteSign;
		copySite.siteDesc = siteDesc;
		copySite.timeZone = timeZone;
		copySite.timeZoneId = timeZoneId;
		copySite.siteFlag = siteFlag;
		copySite.hireMode = hireMode;
		copySite.chargeMode = chargeMode;
		copySite.license = license;
		copySite.mtgType = mtgType;
		copySite.lockFlag = lockFlag;
		copySite.effeDate = effeDate;
		copySite.expireDate = expireDate;
		copySite.createTime = createTime;
		copySite.createUser = createUser;
		copySite.delFlag = delFlag;
		copySite.delTime = delTime;
		copySite.delUser = delUser;
		copySite.priseId = priseId;
		copySite.sendRemindFlag = sendRemindFlag;
		copySite.isVirtualSite = isVirtualSite;
		copySite.frameTimeInterval = frameTimeInterval;
		copySite.downloadUrl = downloadUrl;
		return copySite;
	}
	
	public String  getChargeModelName(){
		if(this.chargeMode!=null){
			switch (chargeMode.intValue()) {
				case 1:
					return "name host";
				case 2:
					return "active user";
				case 3:
					return "seats";
				case 4:
					return "meeting time";
				case 5:
					return "user time";
				default:
					return "";
			}
		}
		return "";
	}
	
	/**
	 * 站点是否已生效
	 * return true:已生效        false:未生效
	 * jack
	 * 2013-12-10
	 */
	public boolean isEffect(){
		if(this.effeDate.after(DateUtil.getGmtDate(null))){
			return false;
		}
		return true;
	}
	
	/**
	 * 站点是否已过期
	 * return true:已过期        false:未过期
	 * jack
	 * 2013-12-10
	 */
	public boolean isExpire(){
		if(this.expireDate.after(DateUtil.getGmtDate(null))){
			return false;
		}
		return true;
	}
}