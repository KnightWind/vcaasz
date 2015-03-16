package com.bizconf.vcaasz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.MS;
import com.bizconf.vcaasz.entity.MSGroup;
import com.bizconf.vcaasz.entity.MSGroupMap;
import com.bizconf.vcaasz.entity.MSSiteMap;
import com.bizconf.vcaasz.entity.MsServer;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.MsService;
import com.bizconf.vcaasz.util.ObjectUtil;

/**
 * @desc MS服务器群组相关service接口实现
 * @author martin
 * @date 2013-8-9
 */
@Service
public class MsServiceImpl extends BaseService implements MsService {

	@Override
	public boolean saveMs(MsServer ms) {
		try{
			ms = (MsServer)ObjectUtil.parseChar(ms, "msName","msDesc");
			if(ms.getId()>0){
				libernate.updateEntity(ms);
			}else{
				libernate.saveEntity(ms);
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delMs(int id,int delUser) {
		String sql = "update t_ms_server set del_flag = ?,del_user=?,del_time = ? where id = ?";
		try{
			libernate.executeSql(sql, new Object[]{ConstantUtil.DELFLAG_DELETED,delUser,new Date(),id});
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MsServer> getAvailableMSes() {
		String sql = "select * from t_ms_server where del_flag=? and in_use = ?";
		try{
			return libernate.getEntityListBase(MsServer.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,ConstantUtil.MS_INUSE_TRUE});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MsServer getMSbyId(int msId) {
		try {
			return libernate.getEntity(MsServer.class, msId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsServer> getMSesByGroup(int groupId) {
		String sql = "select * from t_ms_server t where del_flag=? and exists(select tmg.id from t_ms_group_map tmg where tmg.ms_id = t.id and tmg.group_id = ?)";
		try{
			return libernate.getEntityListBase(MsServer.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,groupId});
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<MsServer> getMSesBySite(int siteId) {
		logger.info("get mses by site id :"+siteId);
		try{
			SiteBase site = libernate.getEntity(SiteBase.class, siteId);
			if(site!=null && site.getMsGroupId()>0){ //说明该站点有MS群组配置
				return getAvailableMSByGroup(site.getMsGroupId());
			}else{//默认返回所有MS
				return getAvailableMSes();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PageBean<MSGroup> getMsGroupsPage(int pageNo, int pageSize) {
		String sql = "select * from t_ms_group where del_flag = ?";
		return getPageBeans(MSGroup.class, sql, pageNo, pageSize, new Object[]{ConstantUtil.DELFLAG_UNDELETE});
	}

	@Override
	public MSGroup saveOrUpdateMsGroup(MSGroup msGroup) {
		try{
			msGroup = (MSGroup)ObjectUtil.parseChar(msGroup, "groupName","groupDesc");
			if(msGroup.getId()>0){
				return libernate.updateEntity(msGroup);
			}else{
				return libernate.saveEntity(msGroup);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveMsGroupMap(MSGroupMap msGroupMap) {
		try{
			libernate.saveEntity(msGroupMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean saveMSGroupSiteMap(MSSiteMap msSiteMap) {
		try{
			libernate.saveEntity(msSiteMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean clearSiteMsConfig(int siteId) {
		String sql = "delete from t_ms_site_map where site_id = ?";
		try{
			libernate.executeSql(sql, new Object[]{siteId});
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean clearMsGroupConfig(int groupId) {
		String sql = "delete from t_ms_group_map where group_id = ?";
		try{
			libernate.executeSql(sql, new Object[]{groupId});
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean delMsGroup(int groupId,int delUser) {
		String sql = "update t_ms_group set del_flag = ?,del_user=?,del_time = ? where id = ?";
		try{
			libernate.executeSql(sql, new Object[]{ConstantUtil.DELFLAG_DELETED,delUser,new Date(),groupId});
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PageBean<MsServer> getAllMSesPage(int pageNo,int pageSize) {
		String sql = "select * from t_ms_server where del_flag = ?";
		try{
			return getPageBeans(MsServer.class, sql, pageNo, pageSize, new Object[]{ConstantUtil.DELFLAG_UNDELETE});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean setInUseState(int msId, int stateCode) {
		String sql = "update t_ms_server set in_use = ? where id = ?";
		try{
			libernate.executeSql(sql, new Object[]{stateCode,msId});
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<Integer, String> getMsinfos(List<MSGroup> groups) {
		if(groups!=null && !groups.isEmpty()){
			Map<Integer, String> msInfos = new HashMap<Integer, String>(16);
			StringBuilder infoBuilder = null;
			for (Iterator it = groups.iterator(); it.hasNext();) {
				MSGroup msGroup = (MSGroup) it.next();
				List<MsServer> mses = getMSesByGroup(msGroup.getId());
				if(mses!=null && !mses.isEmpty()){
					infoBuilder = new StringBuilder();
					for (Iterator ite = mses.iterator(); ite.hasNext();) {
						MsServer msServer = (MsServer) ite.next();
						infoBuilder.append(msServer.getMsName());
						infoBuilder.append(",");
					}
					if(infoBuilder.toString().endsWith(",")) infoBuilder.deleteCharAt(infoBuilder.length()-1);
					msInfos.put(msGroup.getId(), infoBuilder.toString());
				}
			}
			return msInfos;
		}
		return null;
	}

	@Override
	public MSGroup getMSGroupById(int groupId) {
		try {
			return libernate.getEntity(MSGroup.class, groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsServer> getAllMSes() {
		String sql = "select * from t_ms_server where del_flag = ?";
		try{
			return libernate.getEntityListBase(MsServer.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PageBean<SiteBase> getMSGroupUsingSites(int groupId,int pageNo,int pageSize) {
		String sql = "select * from t_site_base where del_flag=?  and ms_group_id = ?";
		try{
			return getPageBeans(SiteBase.class, sql,pageNo,pageSize, new Object[]{ConstantUtil.DELFLAG_UNDELETE,groupId});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//判断MSgroup是否已经被使用
	@Override
	public boolean MSGroupBeUsed(int groupId){
		String sql = "select count(id) from t_site_base where del_flag=?  and ms_group_id = ?";
		try{
			int count  = libernate.countEntityListWithSql(sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,groupId});
			if(count>0) return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MsServer> getAvailableMSByGroup(int groupId) {
		String sql = "select * from t_ms_server t where t.del_flag =? and  t.in_use=? and  exists(select tmg.id from t_ms_group_map tmg where tmg.ms_id = t.id and tmg.group_id = ?)";
		try{
			return libernate.getEntityListBase(MsServer.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE,ConstantUtil.MS_INUSE_TRUE,groupId});
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<MSGroup> getAllMsGroups() {
		String sql = "select * from t_ms_group where del_flag = ?";
		try{
			return libernate.getEntityListBase(MSGroup.class, sql, new Object[]{ConstantUtil.DELFLAG_UNDELETE});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean clearSiteGroupSetting(int groupId) {
		String sql = "update  t_site_base set ms_group_id = ? where ms_group_id = ?";
		try{
			return libernate.executeSql(sql, new Object[]{0,groupId});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
