package com.bizconf.vcaasz.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.Empower;
import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.logic.EmpowerLogic;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.ObjectUtil;

/**
 * @desc 
 * @author Administrator
 * @date 2013-4-27
 */
@Component
public class EmpowerLogicImpl extends BaseLogic implements EmpowerLogic {

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
}
