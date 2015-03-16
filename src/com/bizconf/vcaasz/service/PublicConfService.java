package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;

/**
 * 公开会议服务
 * 
 * @author Chris
 *
 */
public interface PublicConfService {
	public List<ConfBase> getOnGoingConf(int siteId);
	
	public List<ConfBase> getUpComingConf(int siteId);
	
	public int countOnGoingConf(int siteId);
	
	public int countUpComingConf(int siteId);
	
	/**
	 * 公开会议
	 * 获取正在召开的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getOpeningConfList(String confName, SiteBase currentSite, int pageNo, Date beginTime, Date endTime);
	
	/**
	 * 公开会议
	 * 获取即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getUpComingConfList(String confName, SiteBase currentSite, int pageNo, Date beginTime, Date endTime);
}
