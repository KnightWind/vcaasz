package com.bizconf.vcaasz.entity;

/**
 * 日志详情信息描述
 * @author shhc
 *
 */
public class EventContent  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 8750851012019276567L;
	
	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 日志ID
	 */
	private Long logId;
	
	/**
	 * 源数据
	 */
	private String sourceInfo;
	
	/**
	 * 新数据
	 */
	private String newInfo;
	
	/**
	 * 日志中序列编号
	 */
	private Short serialNum;


    // Constructors

    /** default constructor */
    public EventContent() {
    }

    
    /** full constructor */
    public EventContent(Long logId, String sourceInfo, String newInfo, Short serialNum) {
        this.logId = logId;
        this.sourceInfo = sourceInfo;
        this.newInfo = newInfo;
        this.serialNum = serialNum;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getSourceInfo() {
        return this.sourceInfo;
    }
    
    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public String getNewInfo() {
        return this.newInfo;
    }
    
    public void setNewInfo(String newInfo) {
        this.newInfo = newInfo;
    }

    public Short getSerialNum() {
        return this.serialNum;
    }
    
    public void setSerialNum(Short serialNum) {
        this.serialNum = serialNum;
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
		EventContent other = (EventContent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EventContent [id=" + id + ", logId=" + logId + ", sourceInfo="
				+ sourceInfo + ", newInfo=" + newInfo + ", serialNum="
				+ serialNum + "]";
	}
   








}