package com.bizconf.vcaasz.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.util.DateUtil;

public class SiteOrg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5968277066642463072L;
	private Integer id;
	private Integer siteId;

	private Integer parentId;
	
	private String orgCode;
	private String orgName;
	private String orgDesc;
	private Integer orgLevel;
	private Integer orgSort;
	private Date createTime;
	private Integer createUser;
	private Integer delFlag;
	private Date delTime;
	private Integer delUser;

	// Constructors

	/** default constructor */
	public SiteOrg() {
	}

	/** full constructor */
	public SiteOrg(Integer id, Integer siteId, Integer parentId,
			String orgCode, String orgName, String orgDesc, Integer orgLevel,
			Integer orgSort, Date createTime, Integer createUser,
			Integer delFlag, Date delTime, Integer delUser) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.parentId = parentId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
		this.orgLevel = orgLevel;
		this.orgSort = orgSort;
		this.createTime = createTime;
		this.createUser = createUser;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
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
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public Integer getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getOrgSort() {
		return this.orgSort;
	}

	public void setOrgSort(Integer orgSort) {
		this.orgSort = orgSort;
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
	
	public void init(UserBase currentSiteAdmin, int parentId){
		this.createUser = currentSiteAdmin.getId();
		this.delUser = 0;
		this.createTime = DateUtil.getGmtDate(null);
		this.delFlag = ConstantUtil.DELFLAG_UNDELETE;
		try {
			this.delTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.orgLevel = 1;
		this.orgSort = 1;
		this.siteId = currentSiteAdmin.getSiteId();
		this.parentId = parentId;
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
		SiteOrg other = (SiteOrg) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SiteOrg [id=" + id + ", siteId=" + siteId + ", parentId="
				+ parentId + ",  orgCode="
				+ orgCode + ", orgName=" + orgName + ", orgDesc=" + orgDesc
				+ ", orgLevel=" + orgLevel + ", orgSort=" + orgSort
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", delFlag=" + delFlag + ", delTime=" + delTime
				+ ", delUser=" + delUser + "]";
	}

}