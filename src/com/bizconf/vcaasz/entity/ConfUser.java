package com.bizconf.vcaasz.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.util.DateUtil;


public class ConfUser  implements java.io.Serializable {



     /**
	 * 
	 */
	private static final long serialVersionUID = 3596819441021333818L;
	private Integer id;
     private Integer confId;
     private Integer cycleId;
     private Integer userId;
     private String userName;
     private String userEmail;
     private String telephone;
     private Integer hostFlag = ConstantUtil.USERROLE_PARTICIPANT;
     private Integer acceptFlag;
     private Date createTime;
     private Integer createUser;
     private Integer createrUserType;
     private Integer delFlag;
     private Date delTime;
     private Integer delUser;
     private Integer delUserType;
     
     private int confStatus;
     private Date startTime;
     private int siteId;
     private Integer remindFlag;/**标记发送通知提醒是短信 0 还是邮件 1 */
     private String contactId; 
     
     
     /*
      * 用户是否隐藏该错过的会议标识
      * 1.会议主控板会议查询时，验证该字段
      * 2.我的会议查询时忽略该字段
      */
     private int hideFlag = 0;


    // Constructors

    /** default constructor */
    public ConfUser() {
    }

    public ConfUser(Integer id, Integer confId, Integer cycleId,
			Integer userId, String userName, String userEmail,
			String telephone, Integer hostFlag, Integer acceptFlag,
			Date createTime, Integer createUser, Integer createrUserType,
			Integer delFlag, Date delTime, Integer delUser,
			Integer delUserType, int confStatus, Date startTime, int siteId,
			Integer remindFlag, int hideFlag) {
		super();
		this.id = id;
		this.confId = confId;
		this.cycleId = cycleId;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.telephone = telephone;
		this.hostFlag = hostFlag;
		this.acceptFlag = acceptFlag;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createrUserType = createrUserType;
		this.delFlag = delFlag;
		this.delTime = delTime;
		this.delUser = delUser;
		this.delUserType = delUserType;
		this.confStatus = confStatus;
		this.startTime = startTime;
		this.siteId = siteId;
		this.remindFlag = remindFlag;
		this.hideFlag = hideFlag;
	}
    
    
	// Property accessors

    public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfId() {
        return this.confId;
    }
    
    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    public Integer getCycleId() {
        return this.cycleId;
    }
    
    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getHostFlag() {
        return this.hostFlag;
    }
    
    public void setHostFlag(Integer hostFlag) {
        this.hostFlag = hostFlag;
    }

    public Integer getAcceptFlag() {
        return this.acceptFlag;
    }
    
    public void setAcceptFlag(Integer acceptFlag) {
        this.acceptFlag = acceptFlag;
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

    public Integer getCreaterUserType() {
        return this.createrUserType;
    }
    
    public void setCreaterUserType(Integer createrUserType) {
        this.createrUserType = createrUserType;
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

    public Integer getDelUserType() {
        return this.delUserType;
    }
    
    public void setDelUserType(Integer delUserType) {
        this.delUserType = delUserType;
    }

	public int getConfStatus() {
		return confStatus;
	}


	public void setConfStatus(int confStatus) {
		this.confStatus = confStatus;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public int getSiteId() {
		return siteId;
	}


	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}


	public Integer getRemindFlag() {
		return remindFlag;
	}


	public void setRemindFlag(Integer remindFlag) {
		this.remindFlag = remindFlag;
	}

	public int getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(int hideFlag) {
		this.hideFlag = hideFlag;
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
		ConfUser other = (ConfUser) obj;
		if(cycleId!=null && !cycleId.equals("") && userEmail!=null && cycleId.equals(other.cycleId) && userEmail.equals(other.userEmail)){
			return true;
		}
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)){
			return false;
		} 
		return true;
	}

	@Override
	public String toString() {
		return "ConfUser [id=" + id + ", confId=" + confId + ", cycleId="
				+ cycleId + ", userId=" + userId + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", telephone=" + telephone
				+ ", hostFlag=" + hostFlag + ", acceptFlag=" + acceptFlag
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", createrUserType=" + createrUserType + ", delFlag="
				+ delFlag + ", delTime=" + delTime + ", delUser=" + delUser
				+ ", delUserType=" + delUserType + ", confStatus=" + confStatus
				+ ", startTime=" + startTime + ", siteId=" + siteId
				+ ", remindFlag=" + remindFlag + ", hideFlag=" + hideFlag
				+ "]";
	}

	public void init(){
		//this.confId = 0;
        this.cycleId = 0;
        this.userId = 0;
        //this.userName = "";
        //this.userEmail = "";
        //this.telephone = "";
        this.hostFlag = 2; //默认为参会者
        this.acceptFlag = 1; //默认为接受
        this.createTime = DateUtil.getGmtDate(null);
        this.createUser = 0;
        this.createrUserType = 2;
        this.delFlag = ConstantUtil.DELFLAG_UNDELETE;
        try{
        	setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
        }catch (Exception e) {
        	e.printStackTrace();
        }
        this.delUser = 0;
        this.delUserType = 0;
        this.remindFlag=2;
	}





}