package com.bizconf.vcaasz.logic.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.logic.NoticeLogic;
@Service
public class NoticeLogicImpl implements NoticeLogic {

	/**
	 * 发布系统公告前台数据校验
	 * wangyong
	 * 2013-1-22
	 */
	@Override
	public boolean publishNoticeValidate(String title, String content, Date expireTime){
		return true;
	}

	/**
	 * 修改系统公告前台数据校验
	 * wangyong
	 * 2013-1-22
	 */
	@Override
	public boolean updateNoticeValidate(String title, String content, Date expireTime){
		return true;
	}

}
