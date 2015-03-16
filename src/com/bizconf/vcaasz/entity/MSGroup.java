package com.bizconf.vcaasz.entity;

import java.util.Date;

import com.bizconf.vcaasz.constant.ConstantUtil;

/**
 * @desc MS 群组
 * @author martin
 * @date 2013-8-8
 */
public class MSGroup {
	private int id;
	private String groupName; //群组名称
	private String groupDesc;//ms群组备注
	private Date createTime; //创建时间
	/**
	 * 删除标志
	 */
	private Integer delFlag = ConstantUtil.DELFLAG_UNDELETE;
	/**
	 * 删除时间
	 */
	private Date delTime;
	/**
	 * 删除者ID号
	 */
	private Integer delUser;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public Integer getDelUser() {
		return delUser;
	}
	public void setDelUser(Integer delUser) {
		this.delUser = delUser;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
