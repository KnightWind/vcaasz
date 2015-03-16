package com.bizconf.vcaasz.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.UserConstant;
import com.bizconf.vcaasz.util.DateUtil;

/**
 * 用户基本信息表
 * @author shhc
 *
 */
public class UserBase implements java.io.Serializable, Cloneable {

	// Fields
	private static final long serialVersionUID = -3499083776888043077L;
	
	/**
	 * 主键ID号
	 */
	private Integer id;
	
	
	private String zoomId;
	
	/**
	 * 站点ID号
	 * 0：无效数据
	 * >0：指定站点的用户或者管理员
	 */
	private Integer siteId;
	
	/**
	 * 机构ID
	 * 0：不属于任何站点的机构
	 */
	private Integer orgId = 0;
	
	/**
	 * 机构CODE
	 */
	private String orgCode = "";
	
	/**
	 * 在机构中排序
	 * 如果站点下用户没有组织机构，则此字段值均为0
	 */
	private Integer userSort = 0;
	
	/**
	 * 用户类型
	 * 0：无效用户
	 * 2：站点普通用户
	 * 9：站点管理员
	 * 99:站点SUPPER 管理员
	 */
	private Integer userType;
	/**
	 * 用户角色
	 * 0:无效角色
	 * 1:主持人
	 * 2:参会者
	 */
	private Integer userRole;
	
	/**
	 * 登录名
	 */
	private String loginName = "";
	
	/**
	 * 登录密码
	 */
	private String loginPass = "";
	
	/**
	 * 登录密码明文
	 */
	private String loginPassPlain = "";
	
	/**
	 * Client显示姓名
	 */
	private String clientName="";
	
	/**
	 * 用户名
	 */
	private String trueName = "";
	
	/**
	 * 英文名
	 */
	private String enName = "";
	
	/**
	 * 邮箱
	 */
	private String userEmail = "";
	
	/**
	 * 固定电话
	 */
	private String phone = "";
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * 用户头像
	 */
	private String userLogo = "";
	
	/**
	 * 登录错误次数
	 */
	private Integer errorCount = 0;
	
	/**
	 * 上次错误登录时间
	 */
	private Date lastErrorTime;
	
	/**
	 * 用户状态标识
	 * 锁定/激活
	 */
	private Integer userStatus;
	
	/**
	 * 创建者ID号
	 */
	private Integer createUser = 0;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 删除标志
	 * 为删除标志后，此登录名、EMAIL、电话可以重新使用，否则不能重复使用
	 */
	private Integer delFlag;
	
	/**
	 * 删除时间
	 */
	private Date delTime;
	
	/**
	 * 删除者ID号
	 */
	private Integer delUser;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 
	 */
	private Integer loginCount;
	
	
	/**
	 * 最后一次修改密码者的用户ID
	 */
	private Integer passEditor;
	
	/**
	 * 用户偏好设置时区
	 */
	private Integer timeZone = 28800000;
	
	/**
	 * 用户偏好设置时区ID
	 */
	private Integer timeZoneId = 44;
	
	/**
	 * 用户偏好设置每页显示条数
	 */
	private int pageSize = 15;
	
	/**
	 * 用户偏好设置语言版本
	 */
	private String favorLanguage = "zh-cn";
	
	/**
	 * 最大参会方数 默认30
	 */
	private int numPartis = 30;
	
	/**
	 * zoom 的用户类型  默认是crop user 3
	 */
	private int zoomType = 3;
	
	/**
	 * zoom 用户的Token
	 * */
	private String zoomToken = "";
	
	/**
	 * zoom的个人会议室ID号
	 */
	private String pmiId;
	
	/**
	 * 最后一次登录时间
	 */
	private Date lastLoginDate;
	
	/**
	 * 过期时间
	 */
	private Date exprieDate;//用户过期时间
	
	/** 数据中心Id by 2014-12-23 Darren add*/
	private Integer dataCenterId = 1;
	/** 数据中心名字  非数据库对应字段  by 2015-03-03 Darren add*/
	private String dataCenterName;
	
	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getZoomToken() {
		return zoomToken;
	}

	public void setZoomToken(String zoomToken) {
		this.zoomToken = zoomToken;
	}

	public int getZoomType() {
		return zoomType;
	}

	public void setZoomType(int zoomType) {
		this.zoomType = zoomType;
	}

	//zoom平台的ID
	public String getZoomId() {
		return zoomId;
	}

	public void setZoomId(String zoomId) {
		this.zoomId = zoomId;
	}

	public int getNumPartis() {
		return numPartis;
	}

	public void setNumPartis(int numPartis) {
		this.numPartis = numPartis;
	}
	
	

	// Constructors
	
	
	/** default constructor */
	public UserBase() {
		
	}

	public UserBase(Integer id, Integer siteId, Integer orgId, String orgCode,
			Integer userSort, Integer userType, Integer userRole,
			String loginName, String loginPass, String clientName,
			String trueName, String enName, String userEmail, String phone,
			String mobile, String userLogo, Integer errorCount,
			Date lastErrorTime, Integer userStatus, Integer createUser,
			Date createTime, Integer delFlag, Date delTime, Integer delUser,
			String remark, Integer loginCount, Integer passEditor,
			Integer timeZone, Integer timeZoneId, int pageSize,
			String favorLanguage, Date exprieDate) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.userSort = userSort;
		this.userType = userType;
		this.userRole = userRole;
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.clientName = clientName;
		this.trueName = trueName;
		this.enName = enName;
		this.userEmail = userEmail;
		this.phone = phone;
		this.mobile = mobile;
		this.userLogo = userLogo;
		this.errorCount = errorCount;
		this.lastErrorTime = lastErrorTime;
		this.userStatus = userStatus;
		this.createUser = createUser;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.remark = remark;
		this.loginCount = loginCount;
		this.passEditor = passEditor;
		this.timeZone = timeZone;
		this.timeZoneId = timeZoneId;
		this.pageSize = pageSize;
		this.favorLanguage = favorLanguage;
		this.exprieDate = exprieDate;
	}

	
	// Property accessors

	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getUserSort() {
		return this.userSort;
	}

	public void setUserSort(Integer userSort) {
		this.userSort = userSort;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return this.loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	
	public String getLoginPassPlain() {
		return loginPassPlain;
	}

	public void setLoginPassPlain(String loginPassPlain) {
		this.loginPassPlain = loginPassPlain;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserLogo() {
		return this.userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Date getLastErrorTime() {
		return this.lastErrorTime;
	}

	public void setLastErrorTime(Date lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	public Integer getPassEditor() {
		return passEditor;
	}

	public void setPassEditor(Integer passEditor) {
		this.passEditor = passEditor;
	}
	
	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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
	
	public int getPageSize() {
		if(pageSize < 10 || pageSize > 100){
			return 15;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getFavorLanguage() {
		return favorLanguage;
	}

	public void setFavorLanguage(String favorLanguage) {
		this.favorLanguage = favorLanguage;
	}

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBase other = (UserBase) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getTimeZoneDesc() {
		return ResourceHolder.getInstance().getResource("website.timezone.city." + this.timeZoneId);
	}
	
	/**
	 * 获取当前用户时区的时间
	 * wangyong
	 * 2013-4-15
	 */
	public Date getUserLocalTime() {
		return DateUtil.getOffsetDateByGmtDate(DateUtil.getGmtDate(null), 
				(long)this.getTimeZone().intValue());
	}
	
	/**
	 * 当前用户是否站点SUPPER 管理员
	 * wangyong
	 * 2013-2-21
	 */
	public boolean isSuperSiteAdmin() {
		return this.getUserType().intValue() == ConstantUtil.USERTYPE_ADMIN_SUPPER.intValue();
	}
	
	/**
	 * 用户是否普通站点管理员
	 * wangyong
	 * 2013-3-11
	 */
	public boolean isSiteAdmin(){
		return this.getUserType().intValue() == ConstantUtil.USERTYPE_ADMIN.intValue();
	}
	
	/**
	 * 用户是否为主持人角色
	 * wangyong
	 * 2013-3-11
	 */
	public boolean isConfHost(){
		if (getUserRole() == null) {
			return false;
		}
		return this.getUserRole().intValue() == UserConstant.USER_ROLE_HOST.intValue();
	}
	
	
	public String getPmiId() {
		return pmiId;
	}

	public void setPmiId(String pmiId) {
		this.pmiId = pmiId;
	}

	@Override
	public String toString() {
		return "UserBase [id=" + id + ", siteId=" + siteId + ", orgId=" + orgId
				+ ", orgCode=" + orgCode + ", userSort=" + userSort
				+ ", userType=" + userType + ", userRole=" + userRole
				+ ", loginName=" + loginName + ", loginPass=" + loginPass
				+ ", clientName=" + clientName + ", trueName=" + trueName
				+ ", enName=" + enName + ", userEmail=" + userEmail
				+ ", phone=" + phone + ", mobile=" + mobile + ", userLogo="
				+ userLogo + ", errorCount=" + errorCount + ", lastErrorTime="
				+ lastErrorTime + ", userStatus=" + userStatus
				+ ", createUser=" + createUser + ", createTime=" + createTime
				+ ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + ", remark=" + remark
				+ ", loginCount=" + loginCount + ", passEditor=" + passEditor
				+ ", timeZone=" + timeZone + ", timeZoneId=" + timeZoneId
				+ ", pageSize=" + pageSize + ", favorLanguage=" + favorLanguage
				+ ", exprieDate=" + exprieDate + "]";
	}

	public Date getExprieDate() {
		return exprieDate;
	}


	public void setExprieDate(Date exprieDate) {
		this.exprieDate = exprieDate;
	}
	
	public boolean isExpried(){
		if(this.exprieDate == null){
			return false;
		}else{
			return DateUtil.getGmtDate(null).after(this.exprieDate);
		}
	}
	
	//判断该用户是否为一直有效的用户
	public boolean isPermanentUser(){
		if(this.exprieDate == null){
			return true;
		}else{
			Calendar c = Calendar.getInstance();
			c.setTime(exprieDate);
			return c.get(Calendar.YEAR)>2060;
		}
	}
	
	public boolean isLocked(){
		return this.getUserStatus().equals(UserConstant.USER_LOCKED);
	}
	
	public boolean isDeled(){
		return this.getDelFlag().equals(ConstantUtil.DELFLAG_DELETED);
	}
	
	//克隆对象
	public Object clone(){
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	//初始化默认值
	public void init(){
		if(createTime==null){
			this.setCreateTime(DateUtil.getGmtDate(null));   //创建时间初始化为GMT时间
		}
		
		//this.setCreateUser(creator.getId());
		//this.setSiteId(creator.getSiteId());
		if(delFlag==null){
			this.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
		}
		if(this.errorCount==null){
			this.setErrorCount(0);
		}
		if(this.orgCode==null){
			this.setOrgCode("");
		}
		if(this.orgId==null){
			this.setOrgId(0);
		}
		if(this.userLogo==null){
			this.setUserLogo("");
		}
		if(this.userSort == null){
			this.setUserSort(0);
		}
		if(this.userStatus==null){
			this.setUserStatus(ConstantUtil.LOCKFLAG_UNLOCK);
		}
		if(this.userType==null){
			this.setUserType(ConstantUtil.USERTYPE_ADMIN);
		}
		if(this.loginCount==null){
			this.setLoginCount(0);
		}
		try {
			this.setLastErrorTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00")));
			this.setDelTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00")));
		} catch (ParseException e) {
			 e.printStackTrace();
		}
		this.setDelUser(0);
	}

	
	
	
}