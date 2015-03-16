package com.bizconf.vcaasz.entity;

public class OuterUser implements java.io.Serializable{

	private static final long serialVersionUID = 3365557961580897406L;

	private int id;
	
	/**
	 * 用户Id，与UserBase中一致
	 */
	private int userId;
	/**
	 * 站点标识
	 */
	private String siteSign;
	/**
	 * 外部用户ID
	 */
	private String outerUserId;
	/**
	 * 用户姓名，第一次创建该用户对照表时保存，以后不再维护
	 */
	private String userName;
	
	@Override
	public String toString() {
		return "OuterUser [id=" + id + ", userId=" + userId + ", siteSign="
				+ siteSign + ", outerUserId=" + outerUserId + ", userName="
				+ userName + "]";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSiteSign() {
		return siteSign;
	}
	public void setSiteSign(String siteSign) {
		this.siteSign = siteSign;
	}

	public String getOuterUserId() {
		return outerUserId;
	}

	public void setOuterUserId(String outerUserId) {
		this.outerUserId = outerUserId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
