package com.bizconf.vcaasz.entity;

import java.io.Serializable;

/**
 * @desc 
 * @author Administrator
 * @date 2014-7-22
 */
public class ValidateUserTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String loginEmail;//登录邮箱
	
	private Long time;//生成重置密码时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ValidateUserTime [id=" + id + ", loginEmail=" + loginEmail
				+ ", time=" + time + "]";
	}
	
}
