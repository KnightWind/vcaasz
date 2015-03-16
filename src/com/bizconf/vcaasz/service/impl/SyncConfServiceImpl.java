package com.bizconf.vcaasz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.zoom.ZoomMeetingOperationComponent;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConfStatus;
import com.bizconf.vcaasz.constant.ConfType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.SyncConfService;
import com.bizconf.vcaasz.util.DateUtil;

/** 
 *   
 * @package com.bizconf.vcaasz.service.impl 
 * @description: TODO
 * @author Martin
 * @date 2014年7月18日 上午11:02:23 
 * @version 
 */
@Service
public class SyncConfServiceImpl extends BaseService implements SyncConfService{
	
	
	@Autowired
	ZoomMeetingOperationComponent zoomMeetingOperationComponent;
	
	@Autowired
	ConfLogic confLogic;
 
	@Autowired
	DataCenterService centerService;
	
	@Override
	public boolean syncHostConfs(UserBase host) {
		
		if(host == null) return false;
		if(host.isLocked() || host.isDeled())return false;
		
		
		try{
			//获取本地DB会议
			Map<String,ConfBase> dbConfMap = new HashMap<String, ConfBase>();
			List<ConfBase> dbConfs = confLogic.getHostActiveMeeting(host.getId());
			if(dbConfs != null){
				for(ConfBase conf : dbConfs){
					dbConfMap.put(conf.getConfZoomId(), conf);
				}
			}
			//Add by Darren 2014-12-24
			DataCenter dataCenter = centerService.queryDataCenterById(host.getDataCenterId());
			//获取zoom的会议列表
			List<ConfBase> zoomConfs = zoomMeetingOperationComponent.getHostMeetinglist(dataCenter.getApiKey(),dataCenter.getApiToken(),host.getZoomId());
			Map<String,ConfBase> zoomConfMap = new HashMap<String, ConfBase>();
			if(zoomConfs != null){
				for(ConfBase zoomConf : zoomConfs){
					zoomConfMap.put(zoomConf.getConfZoomId(), zoomConf);
					//将zoomconf 同步添加到 dbconf
					if(!dbConfMap.containsKey(zoomConf.getConfZoomId()) || dbConfMap.get(zoomConf.getConfZoomId()) == null){
						ConfBase dbConf = confLogic.getConfByZoomIdAndTypeIgnoreDelFlag(zoomConf.getConfZoomId(),zoomConf.getConfType());
//						ConfType.
						if (dbConf == null) {
							zoomConf.setCreateUser(host.getId());
							zoomConf.setCompereUser(host.getId());
							zoomConf.setCompereName(host.getTrueName());
							zoomConf.setPermanentConf(ConfType.PERMANENT_CONF_FALSE);
							zoomConf.setTimeZone(host.getTimeZone());
							zoomConf.setTimeZoneId(host.getTimeZoneId());
							zoomConf.setSiteId(host.getSiteId());
							zoomConf.setCreateType(ConfType.CREATE_TYPE_CLIENT);
							//conf.setConfStatus(ConfConstant.CONF_STATUS_SUCCESS);
							libernate.saveEntity(zoomConf);
						}else if((!dbConf.getConfStatus().equals(ConfStatus.SCHEDULED) &&
								!dbConf.getConfStatus().equals(ConfStatus.LIVING))
								||dbConf.getDelFlag().equals(ConstantUtil.DELFLAG_DELETED)){
							//在portal已经结束 或者删除 取消等等
							UserBase confHost = libernate.getEntity(UserBase.class, dbConf.getCreateUser());
							//Add by Darren 2014-12-24
							DataCenter confCenter = centerService.queryDataCenterById(confHost.getDataCenterId());
							
							zoomMeetingOperationComponent.deleteMeeting(confCenter.getApiKey(),confCenter.getApiToken(),dbConf.getConfZoomId(), confHost.getZoomId());
						}else{
							dbConf.setConfName(zoomConf.getConfName());
							//zoom client从把预约会议改成周期会议
							if(!dbConf.getConfType().equals(zoomConf.getConfType())){
								//为了不影响原来逻辑，如果客户端将会以修改成周期会议将该会议的创建类型修改为 客户端创建
								if(zoomConf.getConfType().equals(ConfType.RECURR)){
									dbConf.setCreateType(ConfType.CREATE_TYPE_CLIENT);
									dbConf.setCycleId(0);
								}
								dbConf.setStartTime(zoomConf.getStartTime());
								dbConf.setDuration(zoomConf.getDuration());
								dbConf.setEndTime(zoomConf.getEndTime());
							}
							else if (zoomConf.getConfType() != ConfType.RECURR) {
								dbConf.setStartTime(zoomConf.getStartTime());
								dbConf.setDuration(zoomConf.getDuration());
								dbConf.setEndTime(zoomConf.getEndTime());
							}
							if(zoomConf.getConfType() == ConfType.SCHEDULE){
								dbConf.setCycleId(0);
							}
							dbConf.setConfType(zoomConf.getConfType());
							dbConf.setOptionJbh(zoomConf.getOptionJbh());
							dbConf.setOptionStartType(zoomConf.getOptionStartType());
							dbConf.setHostKey(zoomConf.getHostKey());
							libernate.updateEntity(dbConf);
						}
					}else{
	
						//将zoomconf 同步更新到 dbconf
						ConfBase dbConf = dbConfMap.get(zoomConf.getConfZoomId());
						dbConf.setConfName(zoomConf.getConfName());
						//zoom client从把预约会议改成周期会议
						if(!dbConf.getConfType().equals(zoomConf.getConfType())){
							//为了不影响原来逻辑，如果客户端将会以修改成周期会议将该会议的创建类型修改为 客户端创建
							if(zoomConf.getConfType().equals(ConfType.RECURR)){
								dbConf.setCreateType(ConfType.CREATE_TYPE_CLIENT);
								dbConf.setCycleId(0);
							}
							dbConf.setStartTime(zoomConf.getStartTime());
							dbConf.setDuration(zoomConf.getDuration());
							dbConf.setEndTime(zoomConf.getEndTime());
						}
						else if (zoomConf.getConfType() != ConfType.RECURR) {
							dbConf.setStartTime(zoomConf.getStartTime());
							dbConf.setDuration(zoomConf.getDuration());
							dbConf.setEndTime(zoomConf.getEndTime());
						}
						if(zoomConf.getConfType() == ConfType.SCHEDULE){
							dbConf.setCycleId(0);
						}
						dbConf.setConfType(zoomConf.getConfType());
						dbConf.setOptionJbh(zoomConf.getOptionJbh());
						dbConf.setOptionStartType(zoomConf.getOptionStartType());
						dbConf.setHostKey(zoomConf.getHostKey());
						libernate.updateEntity(dbConf);
						
						//修改会议状态
						/*if(!zoomConf.getConfStatus().equals(dbConf.getConfStatus())){
							confLogic.updateConfStatus(dbConf, zoomConf.getConfStatus());
						}*/
						
					}
				}
			}
			
			if(dbConfs !=null ){
				//删除zoom中已经删除的会议
				for(ConfBase conf : dbConfs){
					if(!zoomConfMap.containsKey(conf.getConfZoomId()) && !conf.isPmi()){
						if(conf.getConfStatus().equals(ConfConstant.CONF_STATUS_SUCCESS)){
							if(conf.getActulStartTime()!=null){
								conf.setConfStatus(ConfConstant.CONF_STATUS_FINISHED);
								if(conf.isClientCycleConf()){
									conf.setEndTime(DateUtil.getGmtDate(null));
								}
							}else{
								conf.setConfStatus(ConfConstant.CONF_STATUS_CANCELED);
							}
						}else if(conf.getConfStatus().equals(ConfConstant.CONF_STATUS_OPENING)){
							conf.setConfStatus(ConfConstant.CONF_STATUS_FINISHED);
							if(conf.isClientCycleConf()){
								conf.setEndTime(DateUtil.getGmtDate(null));
							}
						}
						else if(conf.getConfStatus().equals(ConfConstant.CONF_STATUS_FINISHED)){
							
						}
						else {
							conf.setDelFlag(ConstantUtil.DELFLAG_DELETED);
						}
						libernate.updateEntity(conf);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

}
