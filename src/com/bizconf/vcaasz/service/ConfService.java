package com.bizconf.vcaasz.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bizconf.vcaasz.entity.Condition;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.ConfUserCount;
import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;


public interface ConfService {

	/**
	 * 创建一个即时会议
	 * @param conf 会议基本信息
	 * @param siteBase 站点基本信息
	 * @param user 创建会议用户基本信息
	 */
	public ConfBase createImmediatelyConf(ConfBase conf,SiteBase siteBase, UserBase user);
	
	/**
	 * 创建一个单次预约会议
	 * @param conf 会议基本信息
	 * @param siteBase 站点基本信息
	 * @param user 创建会议用户基本信息
	 * @param isSingleRes  是否是单次预约会议
	 */
	public ConfBase createSingleReservationConf(ConfBase conf,SiteBase siteBase, UserBase user, boolean isSingleRes);
	
	/**
	 * 重新创建一个单次预约会议
	 * 1.只能创建单次预约会议
	 * 2.保留非周期会议信息
	 * 3.copy参会人，主持人与新会议的关联
	 * 4.不是修改，是新建
	 * 5.重新给参会人发送邮件
	 * @param conf 会议基本信息
	 * @param siteBase 站点基本信息
	 * @param user 创建会议用户基本信息
	 */
	public ConfBase reCreateconf(ConfBase conf,SiteBase siteBase, UserBase user);
	
	/**
	 * 修改单次预约会议
	 * @param conf 会议基本信息
	 * @param siteBase 站点基本信息
	 * @param user 创建会议用户基本信息
	 */
	public ConfBase updateSingleReservationConf(ConfBase conf,SiteBase siteBase, UserBase user);
	
	/**
	 * 当前登录用户是否有权限修改一个预约会议
	 * @param conf 会议基本信息
	 * @param currentUser 当前登录用户
	 */
	public boolean updateConfAuthority(Integer confId, UserBase currentUser);
	
	/**
	 * 当前登录用户是否有权限重新创建一个预约会议
	 * @param conf 会议基本信息
	 * @param currentUser 当前登录用户
	 */
	public boolean recreateConfAuthority(Integer confId, UserBase currentUser);
	
	/**
	 * 当前登录用户是否有权限取消一个预约会议
	 * @param conf 会议基本信息
	 * @param currentUser 当前登录用户
	 */
	public boolean cancleConfAuthority(Integer confId, UserBase currentUser);
	
	/**
	 * 当前登录用户是否有权限邀请参会人
	 * @param conf 会议基本信息
	 * @param currentUser 当前登录用户
	 */
	public boolean inviteConfAuthority(Integer confId, UserBase currentUser);
	
	/**
	 * 主持人删除(取消)整个周期预约会议
	 * @param cycleId 周期会议id
	 * @param currentSite 当前站点
	 * @param currentUser 当前登录用户
	 */
	public boolean cancleCycleConfInfo(Integer cycleId, SiteBase currentSite, UserBase currentUser);
	
	/**
	 * 主持人删除(取消)周期预约会议中的一条会议
	 * @param confId 会议id
	 * @param currentSite 当前站点
	 * @param currentUser 当前登录用户
	 */
	public boolean cancleSingleCycleConfInfo(Integer confId, SiteBase currentSite, UserBase currentUser);
	
	/**
	 * 取消一个预约会议
	 * @param confId 会议id
	 * @param currentSite 当前站点
	 * @param currentUser 当前登录用户
	 */
	public boolean cancleSingleReservationConf(Integer confId, SiteBase currentSite, UserBase currentUser);
	
	 
	
	/**
	 * 创建周期预约会议
	 * @param conf 会议基本信息
	 * @param currentSite 当前站点
	 * @param currentUser 当前用户
	 */
	public ConfBase createCycleReservationConf(ConfBase conf, ConfCycle confCycle, SiteBase currentSite, UserBase currentUser);

	/**
	 * 修改周期预约会议中的一条会议
	 * 流程：
	 * 1.将该次周期会议改为单次预约会议，即cycleId改为0
	 * 2.copy一份参会用户（包括主持人）到参会用户表中，并且conf_id设置为本次会议的confId，cycle_id设置为0
	 * @param conf 会议基本信息
	 * @param currentSite 当前站点
	 * @param currentUser 当前用户
	 */
	public ConfBase updateSingleCycleConfInfo(ConfBase conf,SiteBase currentSite, UserBase currentUser);
	
	/**
	 * 修改周期预约会议
	 * 注意：不可修改循环设置部分
	 * @param conf 会议基本信息
	 * @param currentSite 当前站点
	 * @param currentUser 当前用户
	 */
	public ConfBase updateCycleConfInfo(ConfBase conf,SiteBase currentSite, UserBase currentUser);
	
	//查询周期会议信息
	
	/**
	 * 主持人邀请参会人
	 * @param participantsList 参会人
	 * @param currentUser 当前登录用户
	 */
	public boolean inviteParticipants(Integer confId, Integer cycleId, List<UserBase> participantsList, UserBase currentUser);
	
	
	/**
	 * 站点管理员根据会议主题统计会议总数
	 * @param subject 会议主题
	 * @param siteId  站点ID
	 * @param siteUserId  普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * 
	 */
	public int countConfListBySubject(String subject, Integer siteId, Integer siteUserId);
	
	/**
	 * 站点管理员根据高级搜索条件查询会议列表(权限控制条件可以放在condition中)
	 * @param condition  高级搜索条件
	 * @param sortField  排序字段
	 * @param sortord    排序方式
	 * @param pageModel  分页信息
	 * 
	 */
	public List<ConfBase> getConfListByCondition(SiteBase currentSite, Condition condition,String sortField, String sortord, PageModel pageModel);
	
	/**
	 * 站点管理员根据高级搜索条件统计会议总数(权限控制条件可以放在condition中)
	 * @param condition  高级搜索条件
	 * 
	 */
	public int countConfListByCondition(Condition condition);
	
	
	//加入会议
	

	
	/**
	 * 系统管理员对会议的基本查询
	 * @param titleOrSiteSign
	 * @param sortField
	 * @param sortord
	 * @param pageModel
	 * @param sysUserId  系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	public List<ConfBase> getConfListByBaseCondition(String titleOrSiteSign,String sortField,String sortord,PageModel pageModel, Integer sysUserId);
	
	/**
	 * 系统统计符合条件 的会议总数(基本搜索情况下)
	 * @param titleOrSiteSign
	 * @param sysUserId  系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	public Integer countConfListByBaseCondition(String titleOrSiteSign, Integer sysUserId);
	
	
	/**
	 * 系统管理员会议的高级搜索
	 * @param siteName
	 * @param siteSign
	 * @param beginTime  会议开始时间开始于
	 * @param endTime    会议开始时间结束于
	 * @param sortField
	 * @param sortord
	 * @param pageModel
	 * @param sysUserId  系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	public List<ConfBase> getConfListByAdvanceCondition(ConfBase confBase,String siteName,String siteSign,
			Date beginTime, Date endTime, String sortField, String sortord, PageModel pageModel, Integer sysUserId);
	
	/**
	 * 系统管理员下对会议高级搜索时统计会议总数
	 * @param siteName
	 * @param siteSign
	 * @param beginTime  会议开始时间开始于
	 * @param endTime    会议开始时间结束于
	 * @param sysUserId  系统管理员id(当超级管理员查询时，传入null即可)
	 * @return
	 */
	public Integer countConfListByAdvanceCondition(ConfBase confBase,String siteName,String siteSign,Date beginTime, Date endTime, Integer sysUserId);

	
	/**
	 * 根据cycleId号获取预约成功的会议信息(站点时区时间)
	 * @param   cycleId  周期ID号
	 * @param currentSite 
	 * @return
	 * 2013-2-21
	 */
	public ConfBase getConfBasebyCycleId(int cycleId, SiteBase currentSite);
	
	/**
	 * 根据cycleId号获取预约成功的会议信息(gmt时区时间)
	 * @param  cycleId  周期ID号
	 * 2013-2-21
	 */
	public ConfBase getConfBasebyCycleId(int cycleId);
	
	
	/**
	 * 根据cycleId号获取周期会议的最后一条记录
	 * @param cycleId  周期ID号
	 * 2013-8-22
	 */
	
	public ConfBase getLastConfBasebyCycleId(int cycleId);
	
	/**
	 * 根据会议ID号获取会议信息
	 * @param confId  会议ID号
	 * 2013-2-21
	 */
	public ConfBase getConfBasebyConfId(int confId);
	
	/**
	 * 根据周期会议ID号获取周期会议周期信息
	 * @param cycleId  周期会议ID号
	 * 2013-2-21
	 */
	public ConfCycle getConfCyclebyConfId(int cycleId);
	
	/**
	 * 通过会议ID获取邀请人信息
	 * @param confId  会议ID号
	 * 2013-2-21
	 */
	public List<ConfUser> getConfInviteUser(int confId);
	
	/**
	 * 根据会议ID号获取会议信息(可获得日期为站点所设时区的会议信息)
	 * @param confId  会议ID号
	 * 2013-2-21
	 */
	public ConfBase getConfBasebyConfId(int confId, SiteBase currentSite);
	
	/**
	 * 获取所有周期预约会议的日期
	 * @param cycleId  循环会议ID号
	 * 2013-2-21
	 */
	public List<ConfBase> getCycleConfDate(int cycleId, SiteBase currentSite);
	
	/**
	 * 根据会议ID号获取会议信息(可获得日期为用户喜好设置时区的会议信息)
	 * @param confId  会议ID号
	 * 2013-2-21
	 */
	public ConfBase getConfBasebyConfId(int confId, UserBase currentUser);
	
	/**
	 * 获取用户喜好设置时区所有周期预约会议的日期
	 * @param cycleId  循环会议ID号
	 * 2013-2-21
	 */
	public List<ConfBase> getCycleConfDate(int cycleId, UserBase currentUser);
	
	/**
	 * 根据会议ID号获取参加会议人数列表
	 * @param confId
	 * @return
	 */
	public List<ConfUser> getConfUserListByConfId(Integer confId);
	
	/**
	 * 根据会议的ID号获取参加会议的总人数
	 * @param confId
	 * @return
	 */
	public Integer countConfUserByConfId(Integer confId);
	

	/**
	 * 根据会议的周期ID号获取参加会议人数列表
	 * @param confId
	 * @return
	 */
	public List<ConfUser> getConfUserListByCycleId(Integer cycleId);
	
	/**
	 * 根据周期会议的周期ID号获取会议的总人数
	 * @param cycleId
	 * @return
	 */
	public Integer countConfUserByCycleId(Integer cycleId);
	
	
	
	
	/**
	 * 根据会议的ID号统计周期会议的参会人数
	 * @param ids
	 * @return  Object[]  0：会议的ID号     1:会议人数
	 */
	
	public List<ConfUserCount> countConfUserByConfIds(Integer[] confIds);
	
	/**
	 * 根据周期会议的周期ID号统计周期会议的参会人数
	 * @param cycleIds
	 * @return  Object[]  0：Cycle ID号     1:会议人数
	 */
	public List<ConfUserCount> countConfUserByCycleIds(Integer[] cycleIds);
	
	
	
	//站点管理员查询会议列表
	
	
	//站点用户查询自己创建的与被邀请的所有 会议
	
	/**
	 * 统计站点用户查询与自己相关正在进行中会议的条数
	 * @param titleOrHostName 会议主题或主持人
	 * @param currentUser 当前站点用户
	 */
	public Integer countDuringConfList(String titleOrHostName, UserBase currentUser);
	
	/**
	 * 站点用户查询与自己相关正在进行中会议
	 * @param titleOrHostName 会议主题或主持人
	 * @param sortField   排序字段
	 * @param sortord     排序方式
	 * @param pageModel   分页对象
	 * @param currentUser 当前站点用户
	 */
	public List<ConfBase> listDuringConfList(String titleOrHostName, PageModel pageModel, String sortField, String sortord, UserBase currentUser, SiteBase currentSite);
	
	/**
	 * 统计站点用户查询与自己相关即将开始会议的条数
	 * @param titleOrHostName 会议主题或主持人
	 * @param currentUser 当前站点用户
	 * @param days 几天内即将开始会议,null代表所有会议
	 */
	public Integer countUpcomingConfList(String titleOrHostName, UserBase currentUser, Integer days);
	
	/**
	 * 站点用户查询与自己相关即将开始会议
	 * @param titleOrHostName 会议主题或主持人
	 * @param sortField   排序字段
	 * @param sortord     排序方式
	 * @param pageModel   分页对象
	 * @param currentUser 当前站点用户
	 * @param days 几天内即将开始会议,null代表所有会议
	 */
	public List<ConfBase> listUpcomingConfList(String titleOrHostName, PageModel pageModel, String sortField, String sortord, UserBase currentUser, SiteBase currentSite, Integer days);
	
	
	/**
	 * 统计站点用户查询与自己相关错过的会议的条数
	 * @param titleOrHostName 会议主题或主持人
	 * @param currentUser 当前站点用户
	 * @param days 几天内错过的会议,null代表所有会议
	 * @param hideFlag 查询隐藏的会议（主控面板查询未隐藏的会议，不关心隐藏则传null）
	 */
	public Integer countMissConfList(String titleOrHostName, UserBase currentUser, Integer days, Integer hideFlag);
	
	/**
	 * 站点用户查询与自己相关错过的会议
	 * @param titleOrHostName 会议主题或主持人
	 * @param sortField   排序字段
	 * @param sortord     排序方式
	 * @param pageModel   分页对象
	 * @param currentUser 当前站点用户
	 * @param days 几天内错过的会议,null代表所有会议
	 * @param hideFlag 查询隐藏的会议（主控面板查询未隐藏的会议，不关心隐藏则传null）
	 */
	public List<ConfBase> listMissConfList(String titleOrHostName, PageModel pageModel, String sortField, String sortord, UserBase currentUser, SiteBase currentSite, Integer days, Integer hideFlag);
	
	
	/**
	 * 统计站点用户查询与自己相关已经加入过会议的条数
	 * @param titleOrHostName 会议主题或主持人
	 * @param currentUser 当前站点用户
	 * @param days 几天内已经加入过的会议,null代表所有会议
	 */
	public Integer countAttendedConfList(String titleOrHostName, UserBase currentUser, Integer days);
	
	/**
	 * 站点用户查询与自己相关已经加入过会议
	 * @param titleOrHostName 会议主题或主持人
	 * @param sortField   排序字段
	 * @param sortord     排序方式
	 * @param pageModel   分页对象
	 * @param currentUser 当前站点用户
	 * @param days 几天内已经加入过的会议,null代表所有会议
	 */
	public List<ConfBase> listAttendedConfList(String titleOrHostName, PageModel pageModel, String sortField, String sortord, UserBase currentUser, SiteBase currentSite, Integer days);
	
	//站点用户根据会议ID号重新创建相同配置的会议
	
	/**
	 * 这里获取一个会议列表去构造会议状态同步的任务，获取逻辑：<br>
	 * 
	 * 会议结束时间 < (now() - period)，并且会议状态不是已结束的会议列表
	 * 
	 * @param period 获取的时间段，单位：ms
	 * 
	 * @author Chris Gao [gaohl81@gmail.com]
	 * @Date 2014/7/22
	 * 
	 * */
	public List<ConfBase> getListForStatusMonitor(long period, int fetchSize);
	
	/**
	 * 矫正与维护会议状态
	 * 
	 * <p>
	 * 分以下几种情况：<p>
	 * 1、正常的预约会议，直接将会议状态改为 [结束] 即可 <br>
	 * 2、 portal创建的周期会议，将开始时间改为下次周期的开始时间，会议状态改为 [即将开始]<br>
	 * 
	 * @param conf 
	 * @author Chris Gao [gaohl81@gmail.com]
	 * @Date 2014/7/22
	 * 
	 * @return
	 */
	public boolean syncConfStatus(ConfBase conf);
	
	/**
	 * 更新会议状态为指定状态
	 * 
	 * @param conf
	 * @param status
	 * @return
	 */
	public boolean updateConfStatusForNotify(ConfBase conf, int status);
	
	
	/**
	 * 根据会议安全码取会议信息
	 * @param secureCode
	 * @return
	 */
//	public ConfBase geConfBaseBySecureCode(String secureCode);
	
	boolean saveConfUser(ConfUser confUser);
	
	/**
	 * 更新企业用户会议设置
	 * wangyong
	 * 2013-3-20
	 */
	public boolean updateConfConfig(DefaultConfig confConfig);
	
	/**
	 * 获取企业用户默认会议设置
	 * wangyong
	 * 2013-3-20
	 */
	public DefaultConfig getDefaultConfig(UserBase currentUser);
	
	
	
	
	
	List<ConfBase> getCycConfBases(int cycId);
	
	
	
	public ConfBase saveConfBaseForOuter(ConfBase confBase,SiteBase siteBase,UserBase userBase );
	
//	/**
//	 *
//	 * 取出提前多少分钟开始会议的会议列表，默认为24小时
//	 * 
//	 * 该方法不分站点，整个系统下的所有站点
//	 * @param minutes,可以为空，为空时默认为24小时
//	 * @return
//	 */
//	public List<ConfBase> getConfListForRemind(Integer minutes);

	/**
	 *
	 * 取出会议列表，为定时任务使用
	 * 
	 * @return
	 */
	public List<ConfBase> getConfListForTask();
	
	
	

	/**
	 * 更新会议的开始时间
	 * @param confBase
	 * @param startTime
	 * @return
	 */
	public boolean updateStartTime(ConfBase confBase,Date startTime);
	
	/**
	 * 更新会议的结束时间
	 * @param confBase
	 * @param startTime
	 * @return
	 */
	public boolean updateEndTime(ConfBase confBase,Date startTime);
	
	/**
	 * 根据华为ID号获取会议信息
	 * @param hwId
	 * @return
	 */
	public ConfBase getConfBaseByHwId(String hwId);
	
	
	/**
	 * 获取conflogs (参会人信息)
	 * @param conf
	 * @return
	 */
	public PageBean<ConfLog> getConflogsByConf(Integer confId,Integer pageNo,Integer pageSize);
	
	
	/**
	 * 查询会议各种终端数量
	 * @param confId
	 * @param terminalType 终端类型：PC OR telephone 如果查询所有终端总数直接传null
	 * @return
	 */
	public int getTerminalNum(Integer confId,Integer terminalType);
	
	
	/**
	 * 获取会议的种终端数量
	 * @return
	 */
	public Map<Integer,Integer> getConfsTerminalNums(Collection<ConfBase> confs,Integer terminalType);
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 我主持的会议
	 * 获取今天正在召开的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyOpeningConfListForHost(String confName, UserBase userBase,Integer pageNo);
	

	/**
	 * 我主持的会议
	 * 获取今天即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyComingConfListForHost(String confName, UserBase userBase,Integer pageNo);
	
	/**
	 * 我主持的会议
	 * 获取今天已经加入过的的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyJoinedConfListForHost(String confName, UserBase userBase,Integer pageNo);
	
	

	/**
	 * 我主持的会议
	 * 获取本周的即将开始的会议
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getWeeklyComingConfListForHost(String confName, UserBase userBase,Integer pageNo);
	
	

	/**
	 * 我主持的会议
	 * 获取本周的已经加入过的会议
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getWeeklyJoinedConfListForHost(String confName, UserBase userBase,Integer pageNo);


	/**
	 * 我主持的会议
	 * 取本月的即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getMonthlyComingConfListForHost(String confName, UserBase userBase,Integer pageNo,Date beginTime, Date endTime);
	


	/**
	 * 我主持的会议
	 * 取本月的已经加入过的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getMonthlyJoinedConfListForHost(String confName, UserBase userBase,Integer pageNo,Date beginTime, Date endTime);
	
	

	/**
	 * 我主持的会议
	 * 所有的正在开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullOpeningConfListForHost(String confName, UserBase userBase,Integer pageNo,Date beginTime, Date endTime);
	
	/**
	 * 我主持的会议
	 * 所有的即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullComingConfListForHost(String confName, UserBase userBase,Integer pageNo,Date beginTime, Date endTime);
	
	/**
	 * 我主持的会议
	 * 所有的加入过的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullJoinedConfListForHost(String confName, UserBase userBase,Integer pageNo,Date beginTime, Date endTime);
	
	
	/***************************************************************************/
	
	

	
	
	/**
	 * 我参与的会议
	 * 获取今天正在召开的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyOpeningConfListForActor(String confName, UserBase userBase, int pageNo);
	

	/**
	 * 我参与的会议
	 * 获取今天即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyComingConfListForActor(String confName, UserBase userBase, int pageNo);
	
	/**
	 * 我参与的会议
	 * 获取今天已经加入过的的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getDailyJoinedConfListForActor(String confName, UserBase userBase, int pageNo);
	
	

	/**
	 * 我参与的会议
	 * 获取本周的正在召开的会议
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getWeeklyOpeningConfListForActor(String confName, UserBase userBase, int pageNo);
	
	
	/**
	 * 我参与的会议
	 * 获取本周的即将开始的会议
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getWeeklyComingConfListForActor(String confName, UserBase userBase, int pageNo);
	
	

	/**
	 * 我参与的会议
	 * 获取本周的已经加入过的会议
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getWeeklyJoinedConfListForActor(String confName, UserBase userBase, int pageNo);


	/**
	 * 我参与的会议
	 * 取本月的即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getMonthlyComingConfListForActor(String confName, UserBase userBase, int pageNo,Date beginTime, Date endTime);
	


	/**
	 * 我参与的会议
	 * 取本月的已经加入过的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getMonthlyJoinedConfListForActor(String confName, UserBase userBase, int pageNo,Date beginTime, Date endTime);
	
	
	
	/**
	 * 我参与的会议
	 * 所有的正在开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullOpeningConfListForActor(String confName, UserBase userBase, int pageNo, Date beginTime, Date endTime);
	
	
	/**
	 * 我参与的会议
	 * 所有的即将开始的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullComingConfListForActor(String confName, UserBase userBase, int pageNo, Date beginTime, Date endTime);
	
	/**
	 * 我参与的会议
	 * 所有的已经加入过的会议列表
	 * @param userBase
	 * @return
	 */
	public PageBean<ConfBase> getFullJoinedConfListForActor(String confName, UserBase userBase, int pageNo, Date beginTime, Date endTime);
	
	/**
	 * 查询我创建或者参加的会议
	 * @param pageNo
	 * @param pageSize
	 * @param isHost
	 * @return
	 */
	public PageBean<ConfBase> getConfBasePage(int pageNo,int pageSize,UserBase user,Date startTime,Date endTime,String theme,boolean isCreator);

	
	/**
	 * 根据永久会议的主会议ID号查找已经创建好预约的或者是正在召开子会议信息
	 * @param belongConfId  主会议ID号
	 * @return
	 */
	public ConfBase getPermanentChildConf(int belongConfId);
	
	
	
	/**
	 * 
	 * @param pageNo 当前页
	 * @param pageSize 每页显示条数
	 * @param siteId 所属站点
	 * @param userId	主持人
	 * @param publicOnly
	 * @return
	 */
	public PageBean<ConfBase> getPermanentConfPage(int pageNo,int pageSize,Integer siteId, Integer userId,Integer confStatus,boolean publicOnly);

	
	/**
	 * 修改永久会议
	 * @param conf
	 * @return
	 */
	public ConfBase updatePermanentConf(ConfBase conf, SiteBase currentSite, UserBase currUser);
	
	/**
	 * 系统管理员根据会议主题、企业名称、企业标识模糊查询所有站点的会议列表
	 * @param subject  会议主题或会议号
	 * wangyong
	 * 2013-5-29
	 */
	public PageBean<ConfBase> getSysConfByName(String subject, String sortField, String sortord, PageModel pageModel, Integer sysUserId);
	
	/**
	 * 系统管理员高级搜索查询会议列表
	 * @param conf 会议名称、会议功能、会议状态等信息
	 * @param siteName 站点名称
	 * @param siteSign 站点标识
	 * @param titleOrSiteSign 
	 * @param beginTime 开始时间
	 * @param endTime	结束时间
	 * @param sortField 排序字段
	 * @param sortord 排序方式，正序逆序
	 * @param pageModel 页数信息
	 * @param sysUserId 不为空，则只查出该普通系统管理员创建的站点下的所有会议；为空，则查询所有站点下的会议
	 * wangyong
	 * 2013-5-29
	 */
	public PageBean<ConfBase> getSysConfByCondition(ConfBase conf, String siteName, String siteSign, 
			String titleOrSiteSign, Date beginTime, Date endTime, String sortField, String sortord, PageModel pageModel, Integer sysUserId);
	
	/**
	 * 站点管理员根据会议主题或会议号模糊查询所有站点的会议列表
	 * @param TitleOrNameOrSign  会议主题、企业名称、企业标识
	 * wangyong
	 * 2013-5-29
	 */
	public PageBean<ConfBase> getAdminConfByName(String confName, SiteBase currentSite, String sortField, String sortord, PageModel pageModel, Integer adminId);
	
	/**
	 * 站点管理员高级搜索查询会议列表
	 * @param conf 会议名称、会议功能、会议状态等信息
	 * @param siteId 站点id
	 * @param beginTime 开始时间
	 * @param endTime	结束时间
	 * @param sortField 排序字段
	 * @param sortord 排序方式，正序逆序
	 * @param pageModel 页数信息
	 * @param adminId 不为空，则只查出该普通系统管理员创建的站点下的所有会议；为空，则查询所有站点下的会议
	 * wangyong
	 * 2013-5-29
	 */
	public PageBean<ConfBase> getAdminConfByCondition(ConfBase conf, SiteBase currentSite,  
			Date beginTime, Date endTime, String sortField, String sortord, PageModel pageModel, Integer adminId);
	
	/**
	 * 验证是否允许创建、修改会议(创建、修改会议时调用)
	 * 1.根据会议的的开始时间、站点信息验证该站点同一时间内是否超过了最大并发会议数
	 * @param confBase
	 * @param siteBase
	 * @return true 允许  false 不允许
	 */
	public boolean checkSiteMaxConfCount(ConfBase confBase, SiteBase siteBase);

	/**
	 * 通过创建会议之后zoom返回的zoomId进行入会操作
	 * */
	public ConfBase getConfBaseByZoomId(String zoomId);
	
	/**
	 * 通过PMI获取PMI会议信息
	 * @param pmi
	 * @return
	 */
	public ConfBase getConfBaseByZoomPMI(String pmi);

	public ConfBase saveNewConfBase(ConfBase newConfBase);

	public Map<Integer, Integer> getConfsNums(
			java.util.List<ConfBase> confList);

	/**
	 * 根据会议号码查询站点下某个会议
	 * */
	public ConfBase getConfBase(Integer siteId, String code);
	
	/**
	 * 修改会议的实际开始 结束时间
	 * @param confId   会议ID
	 * @param status   会议状态
	 * @return  是否修改成功
	 */
	public boolean updateConfActulTime(int confId, int status);

	public ConfCycle updateCycleByCycleId(ConfCycle confCycle, ConfBase confBase,
			UserBase userBase, SiteBase currentSite, Integer timeZone);
	
	/**
	 * 更新个人永久会议室
	 * @param conf
	 * @param currentUser
	 * @return
	 */
	public boolean updateUserPMI(ConfBase conf,UserBase currentUser);
	
	
	/**
	 * 从zoom获取会议信息
	 * @param zoomId
	 * @param hostId
	 * @return
	 */
	public ConfBase getConfFromZoom(String zoomId,String hostId);
	
	
	
	
	/**
	 * martin add here 
	 * 查询会议列表的一个方法，用于站点管理员或系统管理员查看会议列表
	 * @param keyword 会议主题
	 * @param confStatus 会议状态
	 * @param startDate 时间段的开始
	 * @param endDate	时间段结束
	 * @param currentSite 当前的站点
	 * @param pageNo	当前页
	 * @param pageSize  每页显示多少条数据
	 * @return PageBean<ConfBase> 
	 */
	public PageBean<ConfBase> getConfPageForAdmin(String keyword,int confStatus,Date startDate,Date endDate, 
			SiteBase currentSite,int pageNo,int pageSize,String sortField,String sortRule);
	
	
	
	/**
	 * 这个方法修复会议开始24小时未结束的会议
	 * @return
	 */
	public boolean fixConfStatus();
	
	
	
	/**
	 * 获取即将开始的客户端周期会议
	 * @param host
	 * @return
	 */
	public List<ConfBase> getClientWaitRurrConfByHost(UserBase host,String confName);
	
	
	
	
	/**
	 * 修改或者保存一个会议对象
	 * @param confBase
	 * @return
	 */
	public ConfBase saveOrUpdateConf(ConfBase confBase);
	
	
	
	
	
	/**
	 * 配对MCU
	 * @param confNo
	 * @param pairCode
	 * @return
	 */
	public int joinByH323OrSip(String confNo,String pairCode);
}
