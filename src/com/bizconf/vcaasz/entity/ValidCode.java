package com.bizconf.vcaasz.entity;

import java.util.Date;

public class ValidCode {
	
	//验证码标示符
	private String codeKey;
	
	//验证码
	private String code;
	
	//创建时间，根据这个标识，可以删除一些过期的数据
	private Date createTime;

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
