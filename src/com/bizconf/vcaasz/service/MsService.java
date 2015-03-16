package com.bizconf.vcaasz.service;

import java.util.List;
import java.util.Map;

import com.bizconf.vcaasz.entity.MSGroup;
import com.bizconf.vcaasz.entity.MSGroupMap;
import com.bizconf.vcaasz.entity.MSSiteMap;
import com.bizconf.vcaasz.entity.MsServer;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;

/**
 * @desc 该接口主要用于服务器分组-站点配置
 * @author martin
 * @date 2013-8-8
 */
public interface MsService {
	
	//保存ms
	public boolean saveMs(MsServer ms);
	
	//删除MS
	public boolean delMs(int id,int delUser);
	
	//获取所有可用的MS
	public List<MsServer> getAvailableMSes();
	
	//获取所有的MS信息
	public List<MsServer> getAllMSes();
	
	//分页获取所有MS信息
	public PageBean<MsServer> getAllMSesPage(int pageNo,int pageSize);
	
	//通过MS的ID获取MS
	public MsServer getMSbyId(int msId);
	
	//通过ID获取msGroup信息
	public MSGroup getMSGroupById(int groupId);
	
	//根据群组获取该群组下的MS
	public List<MsServer> getMSesByGroup(int groupId);
	
	//根据群组获取该群组下可用的MS
	public List<MsServer> getAvailableMSByGroup(int groupId);
	
	//获取该站点所分配的所有MS
	public List<MsServer> getMSesBySite(int siteId);
	
	//获取MS群组
	public PageBean<MSGroup> getMsGroupsPage(int pageNo,int pageSize);
	
	//保存或者修改MSgroup
	public MSGroup saveOrUpdateMsGroup(MSGroup msGroup);
	
	//保存ms服务器和服务器组之间的关系
	public boolean saveMsGroupMap(MSGroupMap msGroupMap);
	
	//保存MS服务器组合站点之间的关系
	public boolean saveMSGroupSiteMap(MSSiteMap msSiteMap);
	
	//清除原站点的MS配置信息
	public boolean clearSiteMsConfig(int siteId);
	
	//清除服务器群组的MS服务器配置信息
	public boolean clearMsGroupConfig(int groupId);
	
	//删除某个MS群组
	public boolean delMsGroup(int groupId,int delUser);
	
	//修改是否可用状态
	public boolean setInUseState(int msId,int stateCode);
	
	//获取所属MS服务器信息
	public Map<Integer, String> getMsinfos(List<MSGroup> groups);
	
	//查询该在使用服务器组的站点
	public PageBean<SiteBase> getMSGroupUsingSites(int groupId,int pageNo,int pageSize);

	//查询所有的MSGroup对象
	public List<MSGroup> getAllMsGroups();
	
	
	//清楚MS群组和站点之间的关系
	public boolean clearSiteGroupSetting(int groupId);
	
	//MSgroup是否被使用
	public boolean MSGroupBeUsed(int groupId);
}
