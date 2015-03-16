package com.bizconf.vcaasz.entity;



public class IpLocator implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322927430061512543L;
	private Integer id;
	private String startIp;
	private String endIp;
	private Long startNum;
	private Long endNum;
	private String province;
	private String city;
	private String ispName;
	private String ispCode;

	// Constructors

	/** default constructor */
	public IpLocator() {
	}

	/** full constructor */
	public IpLocator(String startIp, String endIp, Long startNum, Long endNum,
			String province, String city, String ispName, String ispCode) {
		this.startIp = startIp;
		this.endIp = endIp;
		this.startNum = startNum;
		this.endNum = endNum;
		this.province = province;
		this.city = city;
		this.ispName = ispName;
		this.ispCode = ispCode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartIp() {
		return this.startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return this.endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public Long getStartNum() {
		return this.startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getEndNum() {
		return this.endNum;
	}

	public void setEndNum(Long endNum) {
		this.endNum = endNum;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIspName() {
		return this.ispName;
	}

	public void setIspName(String ispName) {
		this.ispName = ispName;
	}

	public String getIspCode() {
		return this.ispCode;
	}

	public void setIspCode(String ispCode) {
		this.ispCode = ispCode;
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
		IpLocator other = (IpLocator) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IpLocator [id=" + id + ", startIp=" + startIp + ", endIp="
				+ endIp + ", startNum=" + startNum + ", endNum=" + endNum
				+ ", province=" + province + ", city=" + city + ", ispName="
				+ ispName + ", ispCode=" + ispCode + "]";
	}

}