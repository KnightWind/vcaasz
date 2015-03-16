package com.bizconf.vcaasz.logic;

import java.util.Date;

public interface NoticeLogic {

	/**
	 * 发布系统公告前台数据校验
	 * wangyong
	 * 2013-1-22
	 */
	public boolean publishNoticeValidate(String title, String content, Date expireTime);
	
	/**
	 * 修改系统公告前台数据校验
	 * wangyong
	 * 2013-1-22
	 */
	public boolean updateNoticeValidate(String title, String content, Date expireTime);
}
