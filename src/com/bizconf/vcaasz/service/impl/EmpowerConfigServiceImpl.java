package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.Empower;
import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class EmpowerConfigServiceImpl extends BaseService implements EmpowerConfigService {

	@Override
	public boolean saveEmpowerConfig(EmpowerConfig empowerConfig) {
		boolean saveStatus=false;
		if(empowerConfig==null){
			return saveStatus;
		}
		List<Empower> emList=getEmpowerListForSave(empowerConfig);
		if(emList != null && emList.size() > 0 ){
			for(Empower empower:emList){
				if(empower!=null){
					try {
						libernate.saveEntity(empower);
						saveStatus = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return saveStatus;
	}

	@Override
	public boolean updateEmpowerConfig(EmpowerConfig empowerConfig) {
		boolean updateStatus=false;
		if(empowerConfig==null){
			return updateStatus;
		}
		List<Empower> emList=getEmpowerListForUpdate(empowerConfig);
		if(emList!=null){
			for(Empower empower:emList){
				if(empower!=null){
					try {
						if(empower.getId()!=null && empower.getId().intValue() >0){
							libernate.updateEntity(empower, "id","siteId","userId","funCode","emFlag","emUser","emUserType");
							updateStatus = true;
						}else{
							libernate.saveEntity(empower);
							updateStatus = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return updateStatus;
	}

	@Override
	public EmpowerConfig getSiteEmpowerConfigBySiteId(Integer siteId) {
		EmpowerConfig empowerConfig=null;
		if(siteId!=null && siteId.intValue() >0 ){
			StringBuffer sqlBuffer=new StringBuffer();
			Object[] values=null;
			//
			List<Empower> emList=null;
			sqlBuffer.append(" select * from t_empower");
			sqlBuffer.append("		where site_id=?");
			sqlBuffer.append("			and user_id=0");
			values=new Object[]{siteId};
			try {
				emList=libernate.getEntityListBase(Empower.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(emList!=null && emList.size()>0){
				empowerConfig=getEmpowerConfigFromEmpowerList(emList);
			}
			emList=null;
			
		}
		return empowerConfig;
	}

	@Override
	public EmpowerConfig getUserEmpowerConfigByUserId(Integer userId) {
		EmpowerConfig empowerConfig=null;
		if(userId!=null && userId.intValue() >0 ){
			StringBuffer sqlBuffer=new StringBuffer();
			Object[] values=null;
			//
			List<Empower> emList=null;
			sqlBuffer.append(" select * from t_empower");
			sqlBuffer.append("		where site_id > 0");
			sqlBuffer.append("			and user_id = ?");
			values=new Object[]{userId};
			try {
				emList=libernate.getEntityListBase(Empower.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(emList!=null && emList.size()>0){
				empowerConfig=getEmpowerConfigFromEmpowerList(emList);
			}
			emList=null;
			
		}
		return empowerConfig;
	}
	
	

	@Override
	public List<EmpowerConfig> getEmpowerListByUserIds(Integer[] userIds){
		List<EmpowerConfig> configList=null;
		if(userIds != null && userIds.length > 0){
			StringBuffer sqlBuffer=new StringBuffer();
			
			List<Object> valueList=new ArrayList<Object>();
			//
			List<Empower> emList=null;
			
			sqlBuffer.append(" select * from t_empower");
			sqlBuffer.append("		where site_id > 0");
			sqlBuffer.append("			and (");
			int ii=0;
			for(Integer userId:userIds){
				if(userId!=null && userId.intValue() > 0){
					if(ii>0){
						sqlBuffer.append("	or ");
					}
					sqlBuffer.append("			 user_id = ?");
					valueList.add(userId);
					ii++;
				}
			}
			sqlBuffer.append("			)");
			
			if(ii<=0){
				return configList;
			}
			
			Object[] values=valueList.toArray();
			try {
				emList=libernate.getEntityListBase(Empower.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(emList!=null && emList.size() > 0){
				HashMap<Integer, EmpowerConfig> userConfigMap=new HashMap<Integer, EmpowerConfig>();
				EmpowerConfig eachConfig=null;
				Date emTime = null;
				Integer siteId = null;
				Integer userId = null;
				Integer emUser = null;
				Integer emUserType = null;
				Integer eachFunCode=null;
				Integer eachEmFlag=null;
				String eachFieldName="";
				for(Empower empower:emList){
					eachConfig=null;
					if(empower!=null){
						emTime=empower.getEmTime();
						emUser=empower.getEmUser();
						emUserType=empower.getEmUserType();
						siteId=empower.getSiteId();
						userId=empower.getUserId();
						eachFunCode=empower.getFunCode();
						eachEmFlag=empower.getEmFlag();
						if(userId!=null && userId.intValue() > 0){
							eachConfig=userConfigMap.get(userId);
						}
						if(eachConfig==null){
							eachConfig=new EmpowerConfig();
							eachConfig.setSiteId(siteId);
							eachConfig.setUserId(userId);
							eachConfig.setEmTime(emTime);
							eachConfig.setEmUser(emUser);
							eachConfig.setEmUserType(emUserType);
						}
						eachFieldName=SiteConstant.EMPOWER_CODE_CODE2FIELD_MAP.get(eachFunCode);
						if(!StringUtil.isEmpty(eachFieldName)){
							ObjectUtil.setFieldValue(eachConfig, eachFieldName, eachEmFlag);
						}
						userConfigMap.put(userId, eachConfig);
					}
				}
				if(userConfigMap!=null && userConfigMap.size() > 0){
					configList=new ArrayList<EmpowerConfig>();
					for(Integer eachKey: userConfigMap.keySet()){
						configList.add(userConfigMap.get(eachKey));
					}
				}
			}
			emList=null;
			valueList=null;
		}
		return configList;
	}
	
	
	

	@Override
	public EmpowerConfig getSiteEmpowerGlobalBySiteId(Integer siteId) {
		EmpowerConfig empowerConfig=null;
		if(siteId!=null && siteId.intValue() >0 ){
			StringBuffer sqlBuffer=new StringBuffer();
			Object[] values=null;
			List<Empower> emList=null;
			sqlBuffer.append(" select * from t_empower");
			sqlBuffer.append("		where site_id=?");
			sqlBuffer.append("			and user_id=-1");
			values=new Object[]{siteId};
			try {
				emList=libernate.getEntityListBase(Empower.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(emList!=null && emList.size()>0){
				empowerConfig=getEmpowerConfigFromEmpowerList(emList);
			}
			emList=null;
			
		}
		return empowerConfig;
	}

	@Override
	public boolean saveSiteEmpowerGlobal(EmpowerConfig empowerConfig) {
		boolean saveStatus=false;
		if(empowerConfig==null){
			return saveStatus;
		}
		empowerConfig.setUserId(-1);
		List<Empower> emList=getEmpowerListForSave(empowerConfig);
		if(emList != null && emList.size() > 0 ){
			for(Empower empower:emList){
				if(empower!=null){
					try {
						libernate.saveEntity(empower);
						saveStatus = true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return saveStatus;
	}

	@Override
	public boolean updateSiteEmpowerGlobal(EmpowerConfig empowerConfig) {
		boolean updateStatus=false;
		if(empowerConfig==null){
			return updateStatus;
		}
		empowerConfig.setUserId(-1);
		List<Empower> emList=getEmpowerListForUpdate(empowerConfig);
		if(emList!=null){
			for(Empower empower:emList){
				if(empower!=null){
					try {
						if(empower.getId()!=null && empower.getId().intValue() >0){
							libernate.updateEntity(empower, "id","siteId","userId","funCode","emFlag","emUser","emUserType");
							updateStatus = true;
						}else{
							libernate.saveEntity(empower);
							updateStatus = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return updateStatus;
	}

	
	private EmpowerConfig getEmpowerConfigFromEmpowerList(List<Empower> emList){
		EmpowerConfig empowerConfig=null;
		if(emList != null  && emList.size() > 0){
			empowerConfig = new EmpowerConfig();
			Date emTime = null;
			Integer siteId = null;
			Integer userId = null;
			Integer emUser = null;
			Integer emUserType = null;
			Integer eachFunCode=null;
			Integer eachEmFlag=null;
			boolean isFirst=true;
			String eachFieldName="";
			for(Empower empower:emList){
				if(empower!=null){
					if(isFirst){
						emTime=empower.getEmTime();
						emUser=empower.getEmUser();
						emUserType=empower.getEmUserType();
						siteId=empower.getSiteId();
						userId=empower.getUserId();
						empowerConfig.setSiteId(siteId);
						empowerConfig.setUserId(userId);
						empowerConfig.setEmTime(emTime);
						empowerConfig.setEmUser(emUser);
						empowerConfig.setEmUserType(emUserType);
					}
					eachFunCode=empower.getFunCode();
					eachEmFlag=empower.getEmFlag();
					eachFieldName = SiteConstant.EMPOWER_CODE_CODE2FIELD_MAP.get(eachFunCode);
					if(!StringUtil.isEmpty(eachFieldName)){
						ObjectUtil.setFieldValue(empowerConfig, SiteConstant.EMPOWER_CODE_CODE2FIELD_MAP.get(eachFunCode), eachEmFlag);
					}
					isFirst=false;
				}
			}
		}
		return empowerConfig;
		
	}
	
	private List<Empower> getEmpowerListForSave(EmpowerConfig empowerConfig){
		List<Empower> emList=null;
		if(empowerConfig!=null){
			Integer siteId=empowerConfig.getSiteId();
			Integer userId=empowerConfig.getUserId();
			if( siteId==null || siteId.intValue() <=0 ||  userId==null) {
				return emList;
			}
			Date emTime=empowerConfig.getEmTime();
			Integer emUser=empowerConfig.getEmUser();
			Integer emUserType=empowerConfig.getEmUserType();
			emList=new ArrayList<Empower>();
			Empower eachEmpower=null;
			Integer eachValue=null;
			for(String[] codeField:SiteConstant.EMPOWER_CODE_FIELD_LIST){
				if(codeField!=null && codeField.length > 0){
					eachValue=IntegerUtil.parseInteger(ObjectUtil.getFieldValue(empowerConfig,codeField[1]));
					if(eachValue!=null && eachValue.intValue()>=0){
						eachEmpower=new Empower();
						eachEmpower.setSiteId(siteId);
						eachEmpower.setUserId(userId);
						eachEmpower.setEmUserType(emUserType);
						eachEmpower.setEmUser(emUser);
						eachEmpower.setFunCode(IntegerUtil.parseInteger(codeField[0]));
						eachEmpower.setEmFlag(eachValue);
						eachEmpower.setEmTime(emTime);
						emList.add(eachEmpower);
					}
				}
			}
			eachEmpower=null;
			eachValue=null;
		}
		
		return emList;
		
	}
	
	
	private List<Empower> getEmpowerListForUpdate(EmpowerConfig empowerConfig){
		List<Empower> emList=null;
		if(empowerConfig!=null){
			Integer siteId=empowerConfig.getSiteId();
			Integer userId=empowerConfig.getUserId();
			if( siteId==null || siteId.intValue() <=0 ||  userId==null) {
				return emList;
			}
			Date emTime=empowerConfig.getEmTime();
			Integer emUser=empowerConfig.getEmUser();
			Integer emUserType=empowerConfig.getEmUserType();
			emList=new ArrayList<Empower>();
			Empower eachEmpower=null;
			Empower eachSavedEmpower=null;//每个数据库中的数据
			Integer eachValue=null;
			StringBuffer sqlBuffer=new StringBuffer();
			Object[] values=null;
			for(String[] codeField:SiteConstant.EMPOWER_CODE_FIELD_LIST){
				if(codeField!=null && codeField.length > 0){
					eachValue=IntegerUtil.parseInteger(ObjectUtil.getFieldValue(empowerConfig,codeField[1]));
					eachSavedEmpower=null;
					sqlBuffer=new StringBuffer();
					sqlBuffer.append("select * from t_empower ");
					sqlBuffer.append("		where  site_id=?");
					sqlBuffer.append("			 and user_id=?");
					sqlBuffer.append("			 and fun_code=?");
					sqlBuffer.append("");
					values=new Object[]{siteId,userId,IntegerUtil.parseInteger(codeField[0])};
					try {
						eachSavedEmpower=libernate.getEntityCustomized(Empower.class, sqlBuffer.toString(), values);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(eachValue!=null && eachValue.intValue()>=0){
						eachEmpower=new Empower();
						eachEmpower.setId(0);
						if(eachSavedEmpower!=null){
							eachEmpower.setId(eachSavedEmpower.getId());
						}
						eachEmpower.setSiteId(siteId);
						eachEmpower.setUserId(userId);
						eachEmpower.setEmUserType(emUserType);
						eachEmpower.setEmUser(emUser);
						eachEmpower.setFunCode(IntegerUtil.parseInteger(codeField[0]));
						eachEmpower.setEmFlag(eachValue);
						eachEmpower.setEmTime(emTime);
						emList.add(eachEmpower);
					}
				}
			}
			eachEmpower=null;
			eachValue=null;
		}
		
		return emList;
		
	}
	
	@Override
	public void setConfigValue(EmpowerConfig config, EmpowerConfig globalConfig) {
		//@TODO EmpowerConfig add a lot of properties,every property should be set!
		if(config!=null&&globalConfig!=null){
			config.setOutCallFlag(config.getOutCallFlag()*globalConfig.getOutCallFlag());
			config.setPhoneFlag(config.getPhoneFlag()*globalConfig.getPhoneFlag());
			config.setRecordFlag(config.getRecordFlag()*globalConfig.getRecordFlag());
			config.setShareMediaFlag(config.getShareMediaFlag()*globalConfig.getShareMediaFlag());
			
		}else if(config!=null && globalConfig == null){
			config.setOutCallFlag(0);
			config.setPhoneFlag(0);
			config.setRecordFlag(0);
			config.setShareMediaFlag(0);
		}
	}
	
	@Override
	public EmpowerConfig getSiteApplyEmpowerBySiteId(Integer siteId) {
		EmpowerConfig globalConfig = getSiteEmpowerGlobalBySiteId(siteId);
		EmpowerConfig siteConfig = getSiteEmpowerConfigBySiteId(siteId);
		setConfigValue(siteConfig, globalConfig);
		
		return siteConfig;
	}
	
	@Override
	public Map<Integer, EmpowerConfig> getUsersPermissions(List<UserBase> users) {
		Map<Integer, EmpowerConfig> dataMap = new HashMap<Integer, EmpowerConfig>();
		if(users!=null && users.size()>0){
			for (Iterator<UserBase> it = users.iterator(); it.hasNext();) {
				UserBase userBase =  it.next();
				dataMap.put(userBase.getId(), getUserEmpowerConfigByUserId(userBase.getId()));
			}
		}
		return dataMap;
	}

	@Override
	public EmpowerConfig makeEmpowerForUser(EmpowerConfig sitePower,EmpowerConfig userPower) {
		
		if(sitePower==null){
			return null;
		}
		EmpowerConfig siteGlobalEmpower=getSiteEmpowerGlobalBySiteId(sitePower.getSiteId());
		if(siteGlobalEmpower==null){
			siteGlobalEmpower=sitePower;
		}
		
		if(userPower==null){
			return siteGlobalEmpower;
		}
		
		EmpowerConfig empowerConfig=new EmpowerConfig();
		empowerConfig.setSiteId(siteGlobalEmpower.getSiteId());
		empowerConfig.setUserId(userPower.getUserId());
		List<String []> empowerFields=SiteConstant.EMPOWER_CODE_FIELD_LIST;//.EMPOWER_CODE_FIELD_ARRAY;
		int eachSiteFlag;
		int eachUserFlag;
		String eachFieldName="";
		for(String[] eachField:empowerFields){
			if(eachField!=null){
				eachFieldName=eachField[1];
				eachSiteFlag=IntegerUtil.parseInteger(ObjectUtil.getFieldValue(siteGlobalEmpower, eachFieldName)).intValue();
				eachUserFlag=IntegerUtil.parseInteger(ObjectUtil.getFieldValue(userPower, eachFieldName)).intValue();
				ObjectUtil.setFieldValue(empowerConfig, eachFieldName, eachSiteFlag & eachUserFlag);
			}
		}
		return empowerConfig;
		
	}

	@Override
	public EmpowerConfig makeEmpowerForConf(UserBase userBase) {
		if (userBase == null) {
			return null;
		}
		Integer siteId = userBase.getSiteId();
		Integer userId = userBase.getId();
		EmpowerConfig siteEmpower = getSiteEmpowerConfigBySiteId(siteId);
		EmpowerConfig globalEmpower = getSiteEmpowerGlobalBySiteId(siteId);
		EmpowerConfig userEmpower = getUserEmpowerConfigByUserId(userId);
		EmpowerConfig empowerConfig = null;
		List<String[]> fieldList = SiteConstant.EMPOWER_CODE_FIELD_LIST;
		String eachFiledName = "";
		if (siteEmpower == null || globalEmpower == null || userEmpower == null) {
			empowerConfig = new EmpowerConfig();
			empowerConfig.setSiteId(siteId);
			empowerConfig.setUserId(userId);
			for (String[] eachField : fieldList) {
				eachFiledName = eachField[1];
				ObjectUtil.setFieldValue(empowerConfig, eachFiledName, 0);
			}
			return empowerConfig;
		}
		int eachSiteFlag;
		int eachGlobalFlag;
		int eachUserFlag;
		empowerConfig = userEmpower;
		for (String[] eachField : fieldList) {
			eachFiledName = eachField[1];
			if (eachFiledName.indexOf("Flag") > -1) {
				eachSiteFlag = IntegerUtil.parseIntegerWithDefaultZero(
						String.valueOf(ObjectUtil.getFieldValue(siteEmpower,
								eachFiledName))).intValue();
				eachGlobalFlag = IntegerUtil.parseIntegerWithDefaultZero(
						String.valueOf(ObjectUtil.getFieldValue(globalEmpower,
								eachFiledName))).intValue();
				eachUserFlag = IntegerUtil.parseIntegerWithDefaultZero(
						String.valueOf(ObjectUtil.getFieldValue(userEmpower,
								eachFiledName))).intValue();
				ObjectUtil.setFieldValue(empowerConfig, eachFiledName,
						eachSiteFlag & eachGlobalFlag & eachUserFlag);
				if ("0".equals(eachField[3])) {
					ObjectUtil.setFieldValue(empowerConfig, eachFiledName,
							eachGlobalFlag);
				}
			} else {
				if ("0".equals(eachField[3])) {
					eachGlobalFlag = IntegerUtil.parseIntegerWithDefaultZero(
							String.valueOf(ObjectUtil.getFieldValue(
									globalEmpower, eachFiledName))).intValue();
					ObjectUtil.setFieldValue(empowerConfig, eachFiledName,
							eachGlobalFlag);
				}
			}
		}

		siteEmpower = null;
		globalEmpower = null;
		userEmpower = null;
		if (empowerConfig.getVideoNumber().intValue() <= 0) {
			empowerConfig.setVideoFlag(SiteConstant.EMPOWER_DISABLED);
		}
		return empowerConfig;
	}

	/**
	 * 生成进入会议的会议对象,主要是根据站点ID号、创建者的ID号获取授权后调整入会的参数
	 * @param confBase
	 * @return
	 */
	public ConfBase makeConfBaseForJoinMeeting(ConfBase confBase){
		if(confBase==null ){
			return null;
		}
		ConfBase confForJoin=confBase;
		
		EmpowerConfig siteEmpower=getSiteEmpowerConfigBySiteId(confBase.getSiteId());
		EmpowerConfig globalEmpower=getSiteEmpowerGlobalBySiteId(confBase.getSiteId());
		List<String[]> fieldList=SiteConstant.EMPOWER_CODE_FIELD_LIST;
		EmpowerConfig empowerForJoin=null;

		String eachFiledName="";
		
		empowerForJoin=new EmpowerConfig();
		empowerForJoin.setSiteId(confBase.getSiteId());
		empowerForJoin.setUserId(confBase.getCreateUser());
		for(String[] eachField:fieldList){
			eachFiledName=eachField[1];
			ObjectUtil.setFieldValue(empowerForJoin, eachFiledName, 0);
		}
		
		int eachSiteFlag;
		int eachGlobalFlag;
		int eachSiteNumber;
		int eachConfBaseNumber;
		for(String[] eachField:fieldList){
			eachFiledName=eachField[1];
			if(eachFiledName.indexOf("Flag")>-1){
				eachSiteFlag = IntegerUtil.parseIntegerWithDefaultZero(String.valueOf(ObjectUtil.getFieldValue(siteEmpower, eachFiledName))).intValue();
				eachGlobalFlag = IntegerUtil.parseIntegerWithDefaultZero(String.valueOf(ObjectUtil.getFieldValue(globalEmpower, eachFiledName))).intValue();
				ObjectUtil.setFieldValue(empowerForJoin, eachFiledName, eachSiteFlag & eachGlobalFlag);
			}
			if(eachFiledName.indexOf("Number")>-1){
				eachSiteNumber = IntegerUtil.parseIntegerWithDefaultZero(
						String.valueOf(ObjectUtil.getFieldValue(siteEmpower, eachFiledName))).intValue();
				eachConfBaseNumber = IntegerUtil.parseIntegerWithDefaultZero(
						String.valueOf(ObjectUtil.getFieldValue(empowerForJoin, eachFiledName))).intValue();
				if (eachConfBaseNumber > eachSiteNumber) {
					eachConfBaseNumber = eachSiteNumber;
				}
				ObjectUtil.setFieldValue(empowerForJoin, eachFiledName, eachConfBaseNumber);
			}
		}
		
		if(empowerForJoin != null){
			
			//会议类型：0，2
			//confForJoin.getConfType();
			
			
			//自动外呼
			
			//媒体共享
			
			//录制
			
			//视频功能  路数
			
			//音频功能 路数
			
			//视频最大画质
			
			
			
		}
		
		
		
		
		return confForJoin;
		
	}

}
