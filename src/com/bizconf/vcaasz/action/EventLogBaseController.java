package com.bizconf.vcaasz.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.EventLog;
import com.bizconf.vcaasz.service.MsService;
import com.bizconf.vcaasz.service.OrgService;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.annotation.Disabled;

/**
 * 系统管理下、站点管理员管理下，查看日志功能的基类
 * @author wangyong
 * 2013.8.26
 */
@Disabled
public abstract class EventLogBaseController {
	
	private final  Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired 
	MsService msService;
	@Autowired
	OrgService orgService;
	
	private static final Set<String> filedNameSet = new HashSet<String>();  //日志记录中，新旧数据需要加资源的值
	static{
		filedNameSet.add("siteDiy");
		filedNameSet.add("siteFlag");
		filedNameSet.add("siteModel");
		filedNameSet.add("phoneFlag");
//		filedNameSet.add("autoFlag");
		filedNameSet.add("outCallFlag");
		filedNameSet.add("allowCall");
		filedNameSet.add("shareMediaFlag");
		filedNameSet.add("recordFlag");
		filedNameSet.add("dpiNumber");
		filedNameSet.add("userRole");
		filedNameSet.add("publicFlag");
		filedNameSet.add("confType");
		filedNameSet.add("videoType");
	}
	
	private static final Set<String> userIgnoreFiledNameSet = new HashSet<String>();   //系统管理员修改站点的日志记录中，指定的这些字段的新旧数据无需显示
	static{
		userIgnoreFiledNameSet.add("orgCode");
		userIgnoreFiledNameSet.add("dpiNumber");
		userIgnoreFiledNameSet.add("passEditor");
	}
	
	private static final Set<String> siteIgnoreFiledNameSet = new HashSet<String>();   //系统管理员修改站点的日志记录中，指定的这些字段的新旧数据无需显示
	static{
		siteIgnoreFiledNameSet.add("timeZone");
		siteIgnoreFiledNameSet.add("emUser");
		siteIgnoreFiledNameSet.add("videoFlag");
		siteIgnoreFiledNameSet.add("audioFlag");
		siteIgnoreFiledNameSet.add("sendRemindFlag");
		siteIgnoreFiledNameSet.add("audioServerMix");
	}
	
	private static final Set<String> confIgnoreFiledNameSet = new HashSet<String>();   //企业用户修改会议的日志记录中，指定的这些字段的新旧数据无需显示
	static{
		confIgnoreFiledNameSet.add("startTimeGmt");
		confIgnoreFiledNameSet.add("cryptKey");
		confIgnoreFiledNameSet.add("siteId");
		confIgnoreFiledNameSet.add("compereUser");
		confIgnoreFiledNameSet.add("compereName");
		confIgnoreFiledNameSet.add("compereSecure");
		confIgnoreFiledNameSet.add("userSecure");
		confIgnoreFiledNameSet.add("maxUser");
		confIgnoreFiledNameSet.add("maxDpi");
		confIgnoreFiledNameSet.add("defaultDpi");
		confIgnoreFiledNameSet.add("createTime");
		confIgnoreFiledNameSet.add("createUser");
		confIgnoreFiledNameSet.add("delTime");
		confIgnoreFiledNameSet.add("timeZoneId");
//		confIgnoreFiledNameSet.add("endTime");
		confIgnoreFiledNameSet.add("cycleId");
	}
	
	private static final int LOG_TYPE_SYSTEM_SITE = 1;    //系统管理员日志中修改站点日志
	private static final int LOG_TYPE_SYSTEM_ADMIN = 2;   //系统管理员日志中站点管理员修改用户日志
	private static final int LOG_TYPE_ADMIN_USER = 3;     //站点管理员日志中站点管理员修改用户日志
	private static final int LOG_TYPE_USER_CONF = 4;     //站点管理员日志中用户操作日志（修改会议）
	
	private static final Set<String> filedTypeCharSet = new HashSet<String>();   //修改会议日志详情中，会议的功能（funcBits）、权限配置管理（priviBits）、Client功能配置（clientConfig）
	static{
		filedTypeCharSet.add("funcBits");
		filedTypeCharSet.add("priviBits");
		filedTypeCharSet.add("clientConfig");
	}
	
	/**
	 * 获取系统管理员日志中,修改站点日志详情（EventLog.optDesc）
	 * wangyong
	 * 2013-8-26
	 */
	protected List<Object[]> getSysOptDescList(EventLog eventLog){
		return getOptDesc(eventLog, LOG_TYPE_SYSTEM_SITE);
	}
	
	/**
	 * 获取系统管理员日志中,站点管理员修改用户日志详情（EventLog.optDesc）
	 * wangyong
	 * 2013-8-26
	 */
	protected List<Object[]> getSysAdminOptDescList(EventLog eventLog){
		return getOptDesc(eventLog, LOG_TYPE_SYSTEM_ADMIN);
	}
	
	/**
	 * 获取站点管理员日志中,站点管理员修改用户日志详情（EventLog.optDesc）
	 * wangyong
	 * 2013-8-26
	 */
	protected List<Object[]> getAdminUserOptDescList(EventLog eventLog){
		return getOptDesc(eventLog, LOG_TYPE_ADMIN_USER);
	}
	
	/**
	 * 获取站点管理员日志中,用户操作日志（修改会议）详情（EventLog.optDesc）
	 * wangyong
	 * 2013-8-27
	 */
	protected List<Object[]> getUserConfOptDescList(EventLog eventLog){
		return getOptDesc(eventLog, LOG_TYPE_USER_CONF);
	}
	
	private List<Object[]> getOptDesc(EventLog eventLog, int logType){
		String jsonStr = eventLog.getOptDesc();
		if(StringUtil.isEmpty(jsonStr)){
			return null;
		}
		JSONArray jsonArr = null;
		try{
			jsonArr = JSONArray.fromObject(jsonStr);
		}catch (JSONException e){
			e.printStackTrace();
			logger.error("A JSONArray text must start with '[' , but JSONArray is '" + jsonStr + "'," + e);
		}
		if(jsonArr == null || jsonArr.size() <= 0){
			return null;
		}
		List<Object[]> optDescList = new ArrayList<Object[]>();
		JSONObject eachJson = null;
		JSONObject oJson = null;
		JSONObject nJson = null;
		String eachFieldName="";
		for(int ii=0; ii<jsonArr.size(); ii++){
			eachJson = JSONObject.fromObject(jsonArr.get(ii));
			if(eachJson != null){
				if(StringUtil.isEmpty(eachJson.getString("beanName"))){
					continue;
				}
				oJson = eachJson.getJSONObject("o");
				nJson = eachJson.getJSONObject("n");
				if(oJson.size() != nJson.size()){
					continue;
				}
				Iterator<?> itt = oJson.keys();  
				if(itt != null){
					while (itt.hasNext()) {
						eachFieldName = itt.next().toString();
						Object[] eachDataArr = null;
						switch(logType){
						case LOG_TYPE_SYSTEM_SITE:   //系统管理员日志中修改站点日志
							eachDataArr = getSysSiteEachData(eachJson.getString("beanName"), eachFieldName, 
									oJson.getString(eachFieldName), nJson.getString(eachFieldName), eachDataArr);
							break;
						case LOG_TYPE_SYSTEM_ADMIN:  //系统管理员日志中站点管理员修改用户日志
							eachDataArr = getSysAdminEachData(eachJson.getString("beanName"), eachFieldName, 
									oJson.getString(eachFieldName), nJson.getString(eachFieldName), eachDataArr);
							break;
						case LOG_TYPE_ADMIN_USER:    //站点管理员日志中站点管理员修改用户日志
							eachDataArr = getAdminUserEachData(eachJson.getString("beanName"), eachFieldName, 
									oJson.getString(eachFieldName), nJson.getString(eachFieldName), eachDataArr);
							break;
						case LOG_TYPE_USER_CONF:    //站点管理员日志中用户操作日志（修改会议）详情
							List<Object[]> dataList = getUserConfEachData(eachJson.getString("beanName"), eachFieldName, 
									oJson.getString(eachFieldName), nJson.getString(eachFieldName), eachDataArr);
							if(dataList != null){
								for(Object[] eachData:dataList){
									optDescList.add(eachData);
								}
							}
							break;
						}
						if(eachDataArr != null){
							optDescList.add(eachDataArr);
						}
					}
				}
			}
		}
		return optDescList;
	}
	
	private Object[] getSysSiteEachData(String beanName, String eachFieldName, String oValue, String nValue, Object[] eachDataArr){
		if(siteIgnoreFiledNameSet.contains(eachFieldName)){
			return null;
		}
		eachDataArr = new Object[4];
		eachDataArr[0] = beanName;
		eachDataArr[1] = eachFieldName;
		eachDataArr[2] = oValue;
		eachDataArr[3] = nValue;
		if(filedNameSet.contains(eachFieldName)){
			eachDataArr[2] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + oValue);
			eachDataArr[3] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + nValue);
		}
		if("loginPass".equals(eachFieldName)){
			eachDataArr[2] = "***";
			eachDataArr[3] = "***";
		}
		if("timeZoneId".equals(eachFieldName)){
			eachDataArr[2] = ResourceHolder.getInstance().getResource("website.timezone.city." + oValue);
			eachDataArr[3] = ResourceHolder.getInstance().getResource("website.timezone.city." + nValue);
		}
		if("msGroupId".equals(eachFieldName)){
			eachDataArr[2] = Integer.parseInt(String.valueOf(oValue)) > 0 ? msService.getMSGroupById(Integer.parseInt(String.valueOf(oValue))).getGroupName() : "默认分组";
			eachDataArr[3] = Integer.parseInt(String.valueOf(nValue)) > 0 ? msService.getMSGroupById(Integer.parseInt(String.valueOf(nValue))).getGroupName() : "默认分组";
		}
		return eachDataArr;
	}
	
	private Object[] getSysAdminEachData(String beanName, String eachFieldName, String oValue, String nValue, Object[] eachDataArr){
		if(userIgnoreFiledNameSet.contains(eachFieldName)){
			return null;
		}
		eachDataArr = new Object[4];
		eachDataArr[0] = beanName;
		eachDataArr[1] = eachFieldName;
		eachDataArr[2] = oValue;
		eachDataArr[3] = nValue;
		if(filedNameSet.contains(eachFieldName)){
			eachDataArr[2] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + oValue);
			eachDataArr[3] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + nValue);
		}
		if("loginPass".equals(eachFieldName)){
			eachDataArr[2] = "***";
			eachDataArr[3] = "***";
		}
		if("orgId".equals(eachFieldName)){
			eachDataArr[2] = Integer.parseInt(String.valueOf(oValue)) > 0 ? orgService.getSiteOrgById(Integer.parseInt(String.valueOf(oValue))).getOrgName() : "";
			eachDataArr[3] = Integer.parseInt(String.valueOf(nValue)) > 0 ? orgService.getSiteOrgById(Integer.parseInt(String.valueOf(nValue))).getOrgName() : "";
		}
		return eachDataArr;
	}
	
	private Object[] getAdminUserEachData(String beanName, String eachFieldName, String oValue, String nValue, Object[] eachDataArr){
		return getSysAdminEachData(beanName, eachFieldName, oValue, nValue, eachDataArr);
	}
	
	private List<Object[]> getUserConfEachData(String beanName, String eachFieldName, String oValue, String nValue, Object[] eachDataArr){
		List<Object[]> dataList = new ArrayList<Object[]>();
		if(confIgnoreFiledNameSet.contains(eachFieldName)){
			return null;
		}
		if(filedTypeCharSet.contains(eachFieldName)){     //修改会议日志详情中，会议的功能（funcBits）、权限配置管理（priviBits）、Client功能配置（clientConfig）
			return getDiffFiled(beanName, eachFieldName, oValue, nValue);
		}
		eachDataArr = new Object[4];
		eachDataArr[0] = beanName;
		eachDataArr[1] = eachFieldName;
		eachDataArr[2] = oValue;
		eachDataArr[3] = nValue;
		if(filedNameSet.contains(eachFieldName)){
			eachDataArr[2] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + oValue);
			eachDataArr[3] = ResourceHolder.getInstance().getResource(beanName + "." + eachFieldName + "." + nValue);
		}
		dataList.add(eachDataArr);
		return dataList;
	}
	
	private List<Object[]> getDiffFiled(String beanName, String eachFieldName, String oValue, String nValue){
		List<Object[]> dataList = new ArrayList<Object[]>();
		char[] oChar = oValue.toCharArray();
		char[] nChar = nValue.toCharArray();
		if(oChar.length != nChar.length){
			return null;
		}
		for(int i=0; i<oChar.length; i++){
			if((i == ConfConstant.CLIENT_CONFIG_VIDEO && "clientConfig".equalsIgnoreCase(eachFieldName))          //视频功能已经在conftype字段展示过新旧数据对比了，因此这里排除。
					|| (i == ConfConstant.FUNCBITS_CONFIG_AUDIO_MIX && "funcBits".equalsIgnoreCase(eachFieldName))){    //会议的混音状态并非用户修改，不展示到会议修改新旧数据
				continue;
			}
			if(oChar[i] != nChar[i]){
				Object[] eachDataArr = new Object[4];
				eachDataArr[0] = beanName;
				eachDataArr[1] = eachFieldName + "." + i;
				eachDataArr[2] = ResourceHolder.getInstance().getResource("com.bizconf.vcaasz.entity.ConfBase.status." + oChar[i]);
				eachDataArr[3] = ResourceHolder.getInstance().getResource("com.bizconf.vcaasz.entity.ConfBase.status." + nChar[i]);
				if(i == ConfConstant.FUNCBITS_CONFIG_CONF_MODEL && "funcBits".equalsIgnoreCase(eachFieldName)){    //会议模式不是开启或关闭（是自由模式、主持人模式），所以不能用上面的资源
					eachDataArr[2] = ResourceHolder.getInstance().getResource("com.bizconf.vcaasz.entity.ConfBase.funcBits.7." + oChar[i]);
					eachDataArr[3] = ResourceHolder.getInstance().getResource("com.bizconf.vcaasz.entity.ConfBase.funcBits.7." + nChar[i]);
				}
				dataList.add(eachDataArr);
			}
		}
		return dataList;
	}
	
}
