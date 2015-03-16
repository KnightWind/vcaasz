package com.bizconf.vcaasz.entity;



/**
 * outer接口接收加入会议的xml对象
 * @author jack
 *
 */
public class JoinConfOuter implements java.io.Serializable {

	private static final long serialVersionUID = 1824788171437846149L;
	private String siteSign;
	private String mtgKey;
	private String mtgTitle;
	private Integer duration;
	private Integer language;
	private String userName;
	private String userId;
	private Integer userType;
	private String hostPwd;
	private String mtgPwd;
	private String timestamp;
	private String authId;
	private boolean pageType;
	private boolean repeatFlag;
	private Integer productFlag;
	
	private String xmlUserIdCopy;
	
	public String getSiteSign() {
		return siteSign;
	}



	public void setSiteSign(String siteSign) {
		this.siteSign = siteSign;
	}



	public String getMtgKey() {
		return mtgKey;
	}



	public void setMtgKey(String mtgKey) {
		this.mtgKey = mtgKey;
	}



	public String getMtgTitle() {
		return mtgTitle;
	}



	public void setMtgTitle(String mtgTitle) {
		this.mtgTitle = mtgTitle;
	}



	public Integer getDuration() {
		return duration;
	}



	public void setDuration(Integer duration) {
		this.duration = duration;
	}



	public Integer getLanguage() {
		return language;
	}



	public void setLanguage(Integer language) {
		this.language = language;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public Integer getUserType() {
		return userType;
	}



	public void setUserType(Integer userType) {
		this.userType = userType;
	}



	public String getHostPwd() {
		return hostPwd;
	}



	public void setHostPwd(String hostPwd) {
		this.hostPwd = hostPwd;
	}



	public String getMtgPwd() {
		return mtgPwd;
	}



	public void setMtgPwd(String mtgPwd) {
		this.mtgPwd = mtgPwd;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	public String getAuthId() {
		return authId;
	}



	public void setAuthId(String authId) {
		this.authId = authId;
	}



	public boolean isPageType() {
		return pageType;
	}



	public void setPageType(boolean pageType) {
		this.pageType = pageType;
	}



	public boolean isRepeatFlag() {
		return repeatFlag;
	}



	public void setRepeatFlag(boolean repeatFlag) {
		this.repeatFlag = repeatFlag;
	}



	public Integer getProductFlag() {
		return productFlag;
	}



	public void setProductFlag(Integer productFlag) {
		this.productFlag = productFlag;
	}
	
	public String getXmlUserIdCopy() {
		return xmlUserIdCopy;
	}



	public void setXmlUserIdCopy(String xmlUserIdCopy) {
		this.xmlUserIdCopy = xmlUserIdCopy;
	}



	@Override
	public String toString() {
		return "JoinConfOuter [siteSign=" + siteSign + ", mtgKey=" + mtgKey
				+ ", mtgTitle=" + mtgTitle + ", duration=" + duration
				+ ", language=" + language + ", userName=" + userName
				+ ", userId=" + userId + ", userType=" + userType
				+ ", hostPwd=" + hostPwd + ", mtgPwd=" + mtgPwd
				+ ", timestamp=" + timestamp + ", authId=" + authId
				+ ", pageType=" + pageType + ", repeatFlag=" + repeatFlag
				+ ", productFlag=" + productFlag + ", xmlUserIdCopy="
				+ xmlUserIdCopy + "]";
	}
	
}
