package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.constant.ConstantUtil;

public class SystemUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259845024718346105L;
	private Integer id;
	//登录名
	private String loginName;
	//登录密码
	private String loginPass;
	
	/*系统管理员类别
	*		0     无效的管理员
	*		999   supper Master
	*		6  系统客服
	*		1  普通管理员
	*/
	private Integer sysType;
	
	/*
	 * 真实姓名
	 */
	private String trueName;
	
	/*
	 * 英文名
	 */
	private String enName;
	
	/*
	 * 电话
	 */
	private String telephone;
	/*
	 * 手机
	 */
	private String mobile;
	/*
	 * 邮箱 
	 */
	private String email;
	/*
	 * 备注
	 */
	private String remark;
	
	/**
	 * 偏好设置每页显示条数
	 */
	private int pageSize = ConstantUtil.PAGESIZE_DEFAULT;
	/*
	 * 错误登录时间
	 */
	private Integer errorCount;
	private Date lastErrorTime;
	private Integer createUser;
	private Date createTime;
	private Integer delFlag;
	private Date delTime;
	private Integer delUser;
	/**
	 * 最后一次修改密码者的用户ID
	 */
	private Integer passEditor;

	// Constructors

	/** default constructor */
	public SystemUser() {
	}

	public SystemUser(Integer id, String loginName, String loginPass,
			Integer sysType, String trueName, String enName, String telephone,
			String mobile, String email, String remark, Integer errorCount,
			Date lastErrorTime, Integer createUser, Date createTime,
			Integer delFlag, Date delTime, Integer delUser, Integer passEditor) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.sysType = sysType;
		this.trueName = trueName;
		this.enName = enName;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.remark = remark;
		this.errorCount = errorCount;
		this.lastErrorTime = lastErrorTime;
		this.createUser = createUser;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.passEditor = passEditor;
	}
	
	// Property accessors

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public Integer getPassEditor() {
		return passEditor;
	}

	public void setPassEditor(Integer passEditor) {
		this.passEditor = passEditor;
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
		SystemUser other = (SystemUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean isSuperSystemAdmin() {
		return this.getSysType().intValue() == ConstantUtil.USERTYPE_SYSTEM_SUPPER.intValue();
	}
	
	public boolean isSystemClientServer() {
		return this.getSysType().intValue() == ConstantUtil.USERTYPE_SYS_SERVER.intValue();
	}
	@Override
	public String toString() {
		return "SystemUser [id=" + id + ", loginName=" + loginName
				+ ", loginPass=" + loginPass + ", sysType=" + sysType
				+ ", trueName=" + trueName + ", enName=" + enName
				+ ", telephone=" + telephone + ", mobile=" + mobile
				+ ", email=" + email + ", remark=" + remark + ", errorCount="
				+ errorCount + ", lastErrorTime=" + lastErrorTime
				+ ", createUser=" + createUser + ", createTime=" + createTime
				+ ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + ", passEditor=" + passEditor + "]";
	}
	
}