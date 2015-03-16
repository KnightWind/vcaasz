package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfOuter;
import com.bizconf.vcaasz.entity.JoinConfOuter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;

public interface ConfOuterService {
	

	/**
	 * 根据MtgKey获取会议基本信息
	 * @param mtgKey
	 * @return
	 */
	public ConfOuter getConfBaseByMeyKey(String mtgKey);
	
	/**
	 * 根据 MtgKey与站点Id号获取会议信息
	 * @param mtgKey
	 * @param siteId
	 * @return
	 */
	public ConfOuter getConfBaseByMeyKeyAndSiteId(String mtgKey,String  siteSign);
	
	/**
	 * 保存外部Conf信息
	 * @param confOuter
	 */
	public ConfOuter saveConfOuter(ConfOuter confOuter);
	
	/**
	 * 根据MtgKey与站点标识获取会议信息
	 * jack
	 * 2013-12-11
	 */
	public ConfBase getConfByMtgkey(String mtgKey, String siteSign);
	
	/**
	 * 根据MtgKey与站点标识获取会议信息
	 * jack
	 * 2013-12-11
	 */
	public List<ConfBase> getConfListByMtgkey(String mtgKey, String siteSign);
	
	/**
	 * 根据MtgKey与站点标识获取当前会议在线人数
	 * jack
	 * 2013-12-11
	 */
	public int getUserNumByMtgkey(String mtgKey, String siteSign);
	
	/**
	 * 根据MtgKey与站点标识获取当前会议在线人数列表
	 * jack
	 * 2013-12-12
	 */
	public PageBean<ConfLog> getConfLogOLList(SiteBase site, ConfBase conf,
			int pageNo, int pageSize);
	
	/**
	 * 按月查询活动记录
	 * jack
	 * 2013-12-12
	 */
	public PageBean<ConfOuter> getConfOuterList(String siteSign, String year, String month, int pageNo, int pageSize);
	
	/**
	 * 查询单个活动中用户进出记录
	 * jack
	 * 2013-12-12
	 */
	public PageBean<ConfLog> getConfLogList(String siteSign, String mtgKey, int pageNo, int pageSize);
	
	/**
	 * 根据MtgKey与站点标识获取正在进行的会议信息
	 * jack
	 * 2013-12-18
	 */
	public ConfBase getOpeningConfByMtgkey(String mtgKey, String siteSign);
	
	/**
	 * 通过接口加入会议时，若会议不存在，则新建会议
	 * jack
	 * 2013-12-18
	 */
	public ConfBase createOuterConf(JoinConfOuter joinConfOuter, SiteBase site);
}
