package com.bizconf.vcaasz.entity;

import java.util.Date;


public class ConfTelConfig  implements java.io.Serializable {



     /**
	 * 
	 */
	private static final long serialVersionUID = -2808233146883551076L;
	private Integer id;
     private Integer siteId;
     private Integer userId;
     private Integer allowTelFlag;
     private Integer autoCallFlag;
     private Integer voipFlag;
     private Date lastModifyTime;


    // Constructors

    /** default constructor */
    public ConfTelConfig() {
    }

    
    /** full constructor */
    public ConfTelConfig(Integer siteId, Integer userId, Integer allowTelFlag, Integer autoCallFlag, Integer voipFlag, Date lastModifyTime) {
        this.siteId = siteId;
        this.userId = userId;
        this.allowTelFlag = allowTelFlag;
        this.autoCallFlag = autoCallFlag;
        this.voipFlag = voipFlag;
        this.lastModifyTime = lastModifyTime;
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

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAllowTelFlag() {
        return this.allowTelFlag;
    }
    
    public void setAllowTelFlag(Integer allowTelFlag) {
        this.allowTelFlag = allowTelFlag;
    }

    public Integer getAutoCallFlag() {
        return this.autoCallFlag;
    }
    
    public void setAutoCallFlag(Integer autoCallFlag) {
        this.autoCallFlag = autoCallFlag;
    }

    public Integer getVoipFlag() {
        return this.voipFlag;
    }
    
    public void setVoipFlag(Integer voipFlag) {
        this.voipFlag = voipFlag;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }
    
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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
		ConfTelConfig other = (ConfTelConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ConfTeleConfig [id=" + id + ", siteId=" + siteId + ", userId="
				+ userId + ", allowTelFlag=" + allowTelFlag
				+ ", autoCallFlag=" + autoCallFlag + ", voipFlag=" + voipFlag
				+ ", lastModifyTime=" + lastModifyTime + "]";
	}
   




}