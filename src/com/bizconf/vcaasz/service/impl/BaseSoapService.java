package com.bizconf.vcaasz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ConfLogic;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingCmuLocator;

/**
 * @desc
 * @author MARTIN
 * @date 2013-4-1
 */
public class BaseSoapService  extends BaseService{
	
	@Autowired
	SiteLogic siteLogic;
	@Autowired
	ConfLogic confLogic;
	
	
	public static  String  DEFAULT_AREA_ID = "";//默认区域ID
	public static  String  BUSINESS_URL = "http://10.184.130.16:8996/eSpaceMeeting/BusinessService";
	public static  String  BASE_URL = "http://10.184.130.16:8996/eSpaceMeeting/BaseService";
	public static  String  CONF_URL = "http://10.184.130.16:8996/eSpaceMeeting/ConfManagementService";
	static{
		BUSINESS_URL = BaseConfig.getInstance().getString("business_url", "");
		BASE_URL = BaseConfig.getInstance().getString("base_url", "");
		CONF_URL = BaseConfig.getInstance().getString("conf_url", "");
	}
	//appkey 设置
	public  static final String APPKEY = "INTERNAL";
	
	private static ESpaceMeetingCmuLocator locator = null;
	
	private static Object mutex = new Object();
	
	public ESpaceMeetingAsSoapToken genToken(){
		ESpaceMeetingAsSoapToken token = new ESpaceMeetingAsSoapToken();
		token.setAppkey(APPKEY);
		token.setTimestamp(new Date().getTime());
		token.setArgs(new String[]{"test"});
		token.setRequestId("bizconf");
		token.setTimezone("");
		token.setKwargs(new ESpaceMeetingAsStringKV[]{new ESpaceMeetingAsStringKV("c","d")});
	
		return token;
	}
	
	
	public ESpaceMeetingAsSoapScheduledUser[] getScheduledUsers(List<ConfUser> confUsers,String siteSign,ConfBase conf){
		
		List<ESpaceMeetingAsSoapScheduledUser> users = new ArrayList<ESpaceMeetingAsSoapScheduledUser>();
		if (conf!=null) {
			SiteBase site = siteLogic.getVirtualSubSite(conf.getCreateUser());
			if(site!=null){
				siteSign = site.getSiteSign();
			}
		}
		for (Iterator<ConfUser> it = confUsers.iterator(); it.hasNext();) {
			ConfUser user = it.next();
			ESpaceMeetingAsSoapScheduledUser eUser = new ESpaceMeetingAsSoapScheduledUser();
			eUser.setRole(user.getHostFlag()==null?2:user.getHostFlag());
			eUser.setEnterpriseId(siteSign);
			eUser.setUserId(user.getId()==null?"":user.getId().toString());
			eUser.setName(user.getUserName());
			eUser.setUri("tel:"+user.getTelephone()==null?"":user.getTelephone());//sip  
			eUser.setPin("");
			eUser.setAllowCallNum("15652292572");
			eUser.setNotifyTypes(new ESpaceMeetingAsStringKV[]{new ESpaceMeetingAsStringKV("1",user.getUserEmail()),new ESpaceMeetingAsStringKV("2",user.getTelephone())});
			users.add(eUser);
		}
		return users.toArray(new ESpaceMeetingAsSoapScheduledUser[users.size()]);
	}
	
	/**
	 * 生成requester
	 * @param siteSign
	 * @param user
	 * @return
	 */
	public ESpaceMeetingAsSoapRequester getRequester(String siteSign,UserBase user,String confHwId){
		ESpaceMeetingAsSoapRequester requester = new ESpaceMeetingAsSoapRequester();
		requester.setEnterpriseId(siteSign);
		UserBase creator = confLogic.getConfCreator(confHwId);
		
		if(creator!=null)user = creator;
		if (user!=null && user.getId()!=null) {
			SiteBase site = siteLogic.getVirtualSubSite(user.getId());
			if(site!=null){
				requester.setEnterpriseId(site.getSiteSign());
			}
			requester.setUserId(user.getId().toString());
		}else{
			requester.setUserId("0");
		}
		if (user!=null && user.getTrueName()!=null) {
			requester.setUserName(user.getTrueName());
		}else{
			requester.setUserName("unknow");
		}
		return requester;
	}
	
	public ESpaceMeetingAsSoapScheduledUser[] getHostUser(UserBase host,String siteSign){
		ESpaceMeetingAsSoapScheduledUser eUser = new ESpaceMeetingAsSoapScheduledUser();
		eUser.setRole(1);
		if (host!=null && host.getId()!=null) {
			SiteBase site = siteLogic.getVirtualSubSite(host.getId());
			if(site!=null){
				eUser.setEnterpriseId(site.getSiteSign());
			}else{
				eUser.setEnterpriseId(siteSign);
			}
		}else{
			eUser.setEnterpriseId(siteSign);
		}
		eUser.setUserId(host.getId().toString());
		eUser.setName(host.getTrueName());
		eUser.setUri("tel:"+host.getMobile()==null?"":host.getMobile());//sip  
		eUser.setPin(""); 
		eUser.setAllowCallNum("");
		eUser.setNotifyTypes(new ESpaceMeetingAsStringKV[]{new ESpaceMeetingAsStringKV("1",host.getUserEmail()),new ESpaceMeetingAsStringKV("2",host.getMobile())});
		 
		return new ESpaceMeetingAsSoapScheduledUser[]{eUser};
	}
	
	public ESpaceMeetingCmuLocator getLocator() {
		if (locator == null) {
			synchronized (mutex) {
				if (locator == null) {
//					EngineConfiguration conf = new FileProvider(ConfManagementServiceImpl.class.getResourceAsStream("/client-config.wsdd"));
//					locator = new ESpaceMeetingCmuLocator(conf);
				}
			}
		}
		return locator;
	}
}
