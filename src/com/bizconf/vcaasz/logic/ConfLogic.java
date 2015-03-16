package com.bizconf.vcaasz.logic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;

public interface ConfLogic {
	
	/**
	 * 创建会议时验证站点所剩license（重新创建会议）
	 * wangyong
	 * 2013-3-14
	 */
	public boolean createConfLicenseVali(ConfBase conf, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser);
	
	/**
	 * 修改会议时验证站点所剩license
	 * wangyong
	 * 2013-3-14
	 */
	public boolean updateConfLicenseVali(ConfBase conf, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser);
	
	/**
	 * 保存会议时验证会议数据
	 * wangyong
	 * 2013-3-4
	 */
	public boolean saveConfValidate(ConfBase conf, ConfCycle confCycle, SiteBase siteBase);
	
//
//	/**
//	 * 根据时间范围取剩余的License数
//	 * @param request
//	 * @param beginTime
//	 * @param duration
//	 * @return  String 日期：格式 2013-01-01,Integer  剩余 的License数
//	 * 
//	 */
//	public HashMap<String,Integer> getSurplusLicense(ConfBase confBase,ConfCycle confCycle, SiteBase siteBase);
	
	/**
	 * 根据时间范围取剩余的License数
	 * @param confBase
	 * @param confCycle
	 * @param siteBase
	 * @param currentUser
	 * @return  String 日期：格式 2013-01-01,Integer  剩余的License数
	 * 
	 */
	public HashMap<String, Integer> getEffetLicense(ConfBase confBase, ConfCycle confCycle, SiteBase siteBase, UserBase currentUser);
	
	/**
	 * 获取当前会议的所属站点
	 * @return
	 */
	public SiteBase getConfSiteBase(int siteId);
	
	
	/**
	 * 获取当前周期会议的所属周期类型
	 * @param cycIds
	 * @return
	 */
	List<ConfCycle> getConfCycles(List<Integer> cycIds);
	
	/**
	 * 获取会议的周期ID（如果是周期会议的话）
	 * @param confs
	 * @return
	 */
	List<Integer> getCycIds(List<ConfBase> confs);
	
	/**
	 * 获取会议周期对象如果会议时周期会议的话
	 * @param confs
	 * @return
	 */
	List<ConfCycle> getConfCyclesByConf(List<ConfBase> confs);
	
	/**
	 * 获取会议周期
	 * @param conf
	 * @return
	 */
	ConfCycle getConfCycleByConf(ConfBase conf);
	
	
	/**
	 * 获取该用户创建的会议列表
	 * @param creatorId
	 * @return
	 */
	List<ConfBase> getConfBasesByCreator(Integer creatorId);
	
	
	/**
	 * 后台配置客户端功能缺省设置，与前台配置无关
	 * 1.前台配置完成后，调用配置后台客户端默认功能
	 * 笔记、视频、音频、聊天、公告、私聊、组聊、投票（默认全部开启，但不在页面上配置）
	 * wangyong
	 * 2013-4-10
	 */
	public void setServerClientConfig(char[] clientConfig);
	
	
	/**
	 * 根据华为ID查询会议的创建者
	 * @param confHwId
	 * @return
	 */
	public UserBase getConfCreator(String confHwId);
	
	/**
	 * 创建即时会议的权限控制以及特殊变量赋值
	 * 1. 创建即时会议，initConf()初始化会议信息后调用
	 * wangyong
	 * 2013-7-4
	 */
	public void immediatelyConfAuthority(ConfBase conf, UserBase user);
	
	
	/**
	 * 统计某个主持人正在开始和预约成功的会议
	 * @param userId
	 * @return
	 */
	public List<ConfBase> getHostActiveMeeting(int userId);
	
	
	/**
	 * 根据zoomID获取会议
	 * @param zoomId
	 * @return
	 */
	public ConfBase getConfByZoomId(String zoomId);
	
	
	
	
	/**
	 * 更改会议状态
	 * @param conf
	 * @param status
	 * @return
	 */
	public boolean updateConfStatus(ConfBase conf, int status);
	
	
	
	
	/**
	 * 根据会议周期获取周期会议的下一次开始时间  如果周期已结束，则返回null
	 * @param cycle
	 * @return
	 */
	public Date getNextConfStartTime(ConfCycle cycle,int timezone);
	
	
	
	/**
	 * 克隆并且保存一条会议信息
	 * @param conf
	 * @return
	 */
	public boolean cloneAndSaveConf(ConfBase conf);
	
	
	
	/**
	 * 根据一个周期获取对该周期模式的描述
	 * @param cycle
	 * @return
	 */
	public String getCycleMode(ConfCycle cycle,String lang);
	
	/**
	 * 根据一个周期对象获取周期范围描述
	 * @param cycle
	 * @return
	 */
	public String getCycleRepeatScope(ConfCycle cycle,String lang);

	/**
	 * 根据本次开始时间获取下次开始时间
	 * */
	public Date getNextConfStartTime(Date startDate, ConfCycle cycle, int timezone);
	
	
	/**
	 * 根据zoomID 和会议类型 查询某个会议 confType默认为预约会议 0
	 * @param zoomId
	 * @return
	 */
	public ConfBase getConfByZoomIdAndTypeIgnoreDelFlag(String zoomId,int conf_type);
		
		
		
}
