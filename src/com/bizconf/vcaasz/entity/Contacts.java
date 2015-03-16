package com.bizconf.vcaasz.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.util.DateUtil;

/**
 * 用户通讯录
 * @author shhc
 *
 */
public class Contacts implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1702353396450858534L;
	
	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 用户ID号
	 */
	private Integer userId;
	
	/**
	 * 站点ID号
	 */
	private Integer siteId = 0;
	
	/**
	 * 群组ID号
	 */
	private Integer groupId = 0;
	
	/**
	 * 在分组中的排序
	 */
	private Integer groupSort = 0;
	
	/**
	 * 本站用户ID号
	 */
	private Integer contactId = 0;
	
	/**
	 * 英文名
	 */
	private String contactNameEn = "";
	
	/**
	 * 姓名
	 */
	private String contactName="";
	
	/**
	 * 邮箱
	 */
	private String contactEmail="";
	
	/**
	 * 电话
	 */
	private String contactPhone="";
	
	/**
	 * 手机
	 */
	private String contactMobile="";
	
	/**
	 * 描述
	 */
	private String contactDesc="";
	
	/**
	 * 描述
	 */
	private Date createTime = new Date();
	
	/**
	 * 创建者ID号
	 */
	private Integer createUser = 0;
	
	/**
	 * 删除标志
	 */
	private Integer delFlag = ConstantUtil.DELFLAG_UNDELETE;
	
	/**
	 * 删除时间
	 */
	private Date delTime = new Date(0l);
	
	/**
	 * 删除者ID号
	 */
	private Integer delUser = 0;

	// Constructors

	/** default constructor */
	public Contacts() {
	}

	public Contacts(Integer id, Integer userId, Integer siteId,
			Integer groupId, Integer groupSort, Integer contactId,
			String contactNameEn, String contactName, String contactEmail,
			String contactPhone, String contactMobile, String contactDesc,
			Date createTime, Integer createUser, Integer delFlag, Date delTime,
			Integer delUser) {
		super();
		this.id = id;
		this.userId = userId;
		this.siteId = siteId;
		this.groupId = groupId;
		this.groupSort = groupSort;
		this.contactId = contactId;
		this.contactNameEn = contactNameEn;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.contactMobile = contactMobile;
		this.contactDesc = contactDesc;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupSort() {
		return this.groupSort;
	}

	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	
	public String getContactNameEn() {
		return contactNameEn;
	}

	public void setContactNameEn(String contactNameEn) {
		this.contactNameEn = contactNameEn;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactDesc() {
		return this.contactDesc;
	}

	public void setContactDesc(String contactDesc) {
		this.contactDesc = contactDesc;
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
		Contacts other = (Contacts) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contacts [id=" + id + ", userId=" + userId + ", siteId="
				+ siteId + ", groupId=" + groupId + ", groupSort=" + groupSort
				+ ", contactId=" + contactId + ", contactNameEn="
				+ contactNameEn + ", contactName=" + contactName
				+ ", contactEmail=" + contactEmail + ", contactPhone="
				+ contactPhone + ", contactMobile=" + contactMobile
				+ ", contactDesc=" + contactDesc + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", delFlag=" + delFlag
				+ ", delTime=" + delTime + ", delUser=" + delUser + "]";
	}
	
	/**
	 * 创建联系人时，初始化联系人
	 * （未初始化完全，需补充）
	 * wangyong
	 * 2013-3-11
	 */
	public void initContact(){
		if(this.groupId==null){
			setGroupId(0);
		}
		if(this.groupSort==null){
			setGroupSort(0);
		}
		if(this.contactId==null){
			setContactId(0);
		}
		if(this.contactDesc==null){
			setContactDesc("");
		}
		setCreateTime(DateUtil.getGmtDate(null));
		setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
		if(this.contactMobile==null){
			this.contactMobile="";
		}
		if(this.contactPhone == null){
			this.contactPhone= "";
		}
		if(this.contactName == null){
			this.contactName = "";
		}
		if(this.contactEmail == null){
			this.contactEmail = "";
		}
		if(this.contactNameEn == null){
			this.contactNameEn = "";
		}
//		if(this.)
		try {
			setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setDelUser(0);
	}

}