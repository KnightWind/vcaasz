package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.component.language.LanguageComponent;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfOuter;
import com.bizconf.vcaasz.entity.JoinConfOuter;
import com.bizconf.vcaasz.entity.JoinRandom;
import com.bizconf.vcaasz.entity.OuterUser;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.exception.OuterConfException;
import com.bizconf.vcaasz.service.ClientAPIService;
import com.bizconf.vcaasz.service.ConfOuterService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.OuterService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.BrowserUtil;
import com.bizconf.vcaasz.util.BrowserUtil.Client;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.Dom4jUtil;
import com.bizconf.vcaasz.util.ExcelUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class OuterServiceImpl extends BaseService implements OuterService {

	@Autowired
	private SiteService siteService;
	@Autowired
	private LicService licService;
	@Autowired
	private ConfOuterService confOuterService;
	@Autowired
	private ClientAPIService clientAPIService;
	@Autowired
	private LanguageComponent languageComponent;
	 
	@Autowired
	private ConfService confService;
	
	/*
	 * 特殊的instance变量
	 * 注：零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：
	 * 生成零长度的byte[]对象只需3条操作码，而Object lock = new Object()则需要7行操作码。
	 */
	private static byte[] lock = new byte[0]; 
	
	@Override
	public String getServerTime(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		Integer siteStatus = getSiteStatusInfo(site);
		if(siteStatus != null && siteStatus.intValue() > 0){
			return returnErrorXmlStr(siteStatus);
		}
		String md5Value = MD5.encodePassword(site.getMd5Key() + siteSign.getText() +  root.element("random").getText(), "MD5");
		Integer authIdStatus = getAuthIdStatus(md5Value, root.element("authId").getText());
		if(authIdStatus != null && authIdStatus.intValue() > 0){
			return returnErrorXmlStr(authIdStatus);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);   
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        Dom4jUtil.elementSetText(rootReturn, "random", root.element("random").getText());
        Dom4jUtil.elementSetText(rootReturn, "timestamp", String.valueOf(DateUtil.getCurrentTimeInMillis()));
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}

	@Override
	public void joinConf(String xmlString, HttpServletRequest request, HttpServletResponse response) throws OuterConfException{
		JoinConfOuter joinConfOuter = getJoinConfOuter(xmlString);   
		SiteBase site = siteService.getSiteBaseBySiteSign(joinConfOuter.getSiteSign());
		getErrorCode(site, joinConfOuter);
		ConfBase conf = null;
		synchronized(lock) {
			conf = confOuterService.getOpeningConfByMtgkey(joinConfOuter.getMtgKey(), site.getSiteSign());
			if(conf==null || conf.getId()==null){
				conf = confOuterService.createOuterConf(joinConfOuter, site);
				if(conf==null || conf.getId()==null){
					logger.info("outer外部接口加入会议失败，会议不存在" +ConfConstant.JOIN_ERROR_CODE_8 +", confBase="+conf );
					request.setAttribute("errorCode", SiteConstant.OUTER_CONF_JOIN_CONF_NOT_EXIST);
					throw new OuterConfException(String.valueOf(SiteConstant.OUTER_CONF_JOIN_CONF_NOT_EXIST));
				}
			}
			//startConf(conf, site, joinConfOuter, request);
		}
		//修改outer用户启动会议客户端语言
		setOuterConfLanguage(joinConfOuter, request, response);
		//判断浏览器版本
		setBrowserVersion(request);
		//根据用户信息，生成JoinRandom对象
		setJoinRandom(conf, joinConfOuter, request);
	}

	@Override
	public String getConfListByMonth(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		int pageNo = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageNo").getText());
		if(pageNo < 1){
			pageNo = 1;
		}
		int pageSize = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageSize").getText());
		if(pageSize > 100 || pageSize < 1){
			pageSize = 20;
		}
		PageBean<ConfOuter> confPage = confOuterService.getConfOuterList(siteSign.getText(), root.element("year").getText(), 
				root.element("month").getText(), pageNo, pageSize);
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + root.element("year").getText()
				+ root.element("month").getText() + root.element("timestamp").getText(), "MD5");
		Integer errorCode = getErrorCode(site, root, md5Value, root.element("year").getText(), root.element("month").getText());
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);   
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        Dom4jUtil.elementSetText(rootReturn, "year", root.element("year").getText());
        Dom4jUtil.elementSetText(rootReturn, "month", root.element("month").getText());
        Dom4jUtil.elementSetText(rootReturn, "pageNo", confPage.getPageNo());
        Dom4jUtil.elementSetText(rootReturn, "pageSize", String.valueOf(confPage.getPageSize()));
        Dom4jUtil.elementSetText(rootReturn, "mtgCount", String.valueOf(confPage.getRowsCount()));
        if(confPage != null && confPage.getDatas() != null && confPage.getDatas().size() > 0){
        	Element mtgListElement = rootReturn.addElement("mtgList"); 
            for(ConfOuter conf : confPage.getDatas()){
            	Element mtgInfoElement = mtgListElement.addElement("mtgInfo");
            	Dom4jUtil.elementSetText(mtgInfoElement, "mtgKey", conf.getMtgKey());
            	Dom4jUtil.elementSetText(mtgInfoElement, "mtgTitle", conf.getMtgTitle());
            	Dom4jUtil.elementSetText(mtgInfoElement, "hostPwd", conf.getHostPwd());
            	Dom4jUtil.elementSetText(mtgInfoElement, "status", String.valueOf(conf.getConfStatus()));
            	Dom4jUtil.elementSetText(mtgInfoElement, "startTime", DateUtil.format(conf.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            	Dom4jUtil.elementSetText(mtgInfoElement, "endTime", DateUtil.format(conf.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}

	@Override
	public String getConfUserLog(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		int pageNo = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageNo").getText());
		if(pageNo < 1){
			pageNo = 1;
		}
		int pageSize = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageSize").getText());
		if(pageSize > 100 || pageSize < 1){
			pageSize = 20;
		}
		ConfBase conf = confOuterService.getConfByMtgkey(root.element("mtgKey").getText(), siteSign.getText());
		if(conf != null && conf.getConfStatus().intValue() != ConfConstant.CONF_STATUS_FINISHED){
			return returnErrorXmlStr(SiteConstant.OUTER_CONF_NOT_FINISHED);
		}
		PageBean<ConfLog> confPage = confOuterService.getConfLogList(siteSign.getText(), root.element("mtgKey").getText(), 
				pageNo, pageSize);
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + root.element("mtgKey").getText()
				+ root.element("timestamp").getText(), "MD5");
		Integer errorCode = getErrorCode(site, root, md5Value);
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);   
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        Dom4jUtil.elementSetText(rootReturn, "mtgKey", root.element("mtgKey").getText());
        Dom4jUtil.elementSetText(rootReturn, "pageNo", confPage.getPageNo());
        Dom4jUtil.elementSetText(rootReturn, "pageSize", String.valueOf(confPage.getPageSize()));
        Dom4jUtil.elementSetText(rootReturn, "logCount", String.valueOf(confPage.getRowsCount()));
        if(confPage != null && confPage.getDatas() != null && confPage.getDatas().size() > 0){
        	Element mtgListElement = rootReturn.addElement("logList"); 
            for(ConfLog log : confPage.getDatas()){
            	Element mtgInfoElement = mtgListElement.addElement("logInfo"); 
            	Dom4jUtil.elementSetText(mtgInfoElement, "userId", String.valueOf(log.getOuterUserId()));
            	Dom4jUtil.elementSetText(mtgInfoElement, "userName", log.getUserName());
            	Dom4jUtil.elementSetText(mtgInfoElement, "userRole", String.valueOf(log.getUserRole()));
            	Dom4jUtil.elementSetText(mtgInfoElement, "joinTime", DateUtil.format(log.getJoinTime(), "yyyy-MM-dd HH:mm:ss"));
            	Dom4jUtil.elementSetText(mtgInfoElement, "exitTime", DateUtil.format(log.getExitTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}
	
	@Override
	public String getLicenceNum(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		String md5Value = MD5.encodePassword(site.getMd5Key() + siteSign.getText() +  root.element("timestamp").getText(), "MD5");
		Integer errorCode = getErrorCode(site, root, md5Value);
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);   
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        int totalLic = licService.getSiteLicenseNum(site.getId());
        Dom4jUtil.elementSetText(rootReturn, "totalLicense", String.valueOf(totalLic));     //站点的总License数
        Dom4jUtil.elementSetText(rootReturn, "surplus_license", 
        		String.valueOf(totalLic - licService.getGoingConfLicense(site.getId())));   //剩余的License数
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}
	
	@Override
	public String getConfStatus(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		ConfBase conf = confOuterService.getConfByMtgkey(root.element("mtgKey").getText(), siteSign.getText());
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + root.element("mtgKey").getText()
				+ root.element("timestamp").getText(), "MD5");
		Integer errorCode = getErrorCode(site, root, conf, md5Value);
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);   
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        Dom4jUtil.elementSetText(rootReturn, "mtgKey", root.element("mtgKey").getText());
        Dom4jUtil.elementSetText(rootReturn, "status", String.valueOf(conf.getConfStatus()));    //活动的状态
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}

	@Override
	public String getUserNumOL(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		ConfBase conf = confOuterService.getConfByMtgkey(root.element("mtgKey").getText(), siteSign.getText());
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + root.element("mtgKey").getText()
				+ root.element("timestamp").getText(), "MD5");
		Integer errorCode = getErrorCode(site, root, conf, md5Value);
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
        Element rootReturn = DocumentHelper.createElement("result");   
        document.setRootElement(rootReturn);  
        Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
        Dom4jUtil.elementSetText(rootReturn, "mtgKey", root.element("mtgKey").getText());
        Dom4jUtil.elementSetText(rootReturn, "online", 
        		String.valueOf(confOuterService.getUserNumByMtgkey(root.element("mtgKey").getText(), siteSign.getText())));
        return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}
	 
	@Override
	public String getUserOLList(String xmlString) {
		if(StringUtil.isEmpty(xmlString)){
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			return "";
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		ConfBase conf = confOuterService.getConfByMtgkey(root.element("mtgKey").getText(), siteSign.getText());
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + root.element("mtgKey").getText()
				+ root.element("timestamp").getText(), "MD5");
		int pageNo = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageNo").getText());
		if(pageNo < 1){
			pageNo = 1;
		}
		int pageSize = IntegerUtil.parseIntegerWithDefaultZero(root.element("pageSize").getText());
		if(pageSize > 100 || pageSize < 1){
			pageSize = 20;
		}
		Integer errorCode = getErrorCode(site, root, conf, md5Value);
		if(errorCode != null && errorCode.intValue() > 0){
			return returnErrorXmlStr(errorCode);
		}
		PageBean<ConfLog> confPage = confOuterService.getConfLogOLList(site, conf, 
				pageNo, pageSize);
		//以下为拼装返回正确结果的xml字符串
		Document document = DocumentHelper.createDocument();   
		Element rootReturn = DocumentHelper.createElement("result");   
		document.setRootElement(rootReturn);  
		Dom4jUtil.elementSetText(rootReturn, "errorCode", String.valueOf(SiteConstant.OUTER_PARAM_NORMAL));
		Dom4jUtil.elementSetText(rootReturn, "mtgKey", root.element("mtgKey").getText());
//		Dom4jUtil.elementSetText(rootReturn, "online", 
//				String.valueOf(confOuterService.getUserNumByMtgkey(root.element("mtgKey").getText(), siteSign.getText())));
		Dom4jUtil.elementSetText(rootReturn, "online", String.valueOf(
				confPage == null || confPage.getDatas() == null ? 0 :confPage.getRowsCount()));
		if(confPage != null && confPage.getDatas() != null && confPage.getDatas().size() > 0){
			Element mtgListElement = rootReturn.addElement("logList"); 
			for(ConfLog log : confPage.getDatas()){
					Element mtgInfoElement = mtgListElement.addElement("logInfo"); 
				Dom4jUtil.elementSetText(mtgInfoElement, "userId", String.valueOf(log.getOuterUserId()));
				Dom4jUtil.elementSetText(mtgInfoElement, "userName", log.getUserName());
				Dom4jUtil.elementSetText(mtgInfoElement, "userRole", String.valueOf(log.getUserRole()));
				Dom4jUtil.elementSetText(mtgInfoElement, "joinTime", DateUtil.format(log.getJoinTime(), "yyyy-MM-dd HH:mm:ss"));
		    }
		}
		return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}
	 
	
	public String getExportExcelPath(String xmlString){
		
		//参数数据验证
		if (StringUtil.isEmpty(xmlString)) {
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}

		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if (doc == null) {
			return returnErrorXmlStr(SiteConstant.OUTER_PARAMS_EMPTY);
		}
		// 获取根元素
		Element root = doc.getRootElement();
		// 获取名字为指定名称的第一个子元素
		Element siteSign = root.element("siteId");
		SiteBase site = siteService.getSiteBaseBySiteSign(siteSign.getText());
		if(site==null){
			return returnErrorXmlStr(SiteConstant.OUTER_SITE_NOT_EXIST);
		}
		Date nowGmtDate=DateUtil.getGmtDate(null);
		Date effeDate = site.getEffeDate();
		if(nowGmtDate.before(effeDate)){
			return returnErrorXmlStr(SiteConstant.OUTER_SITE_NOT_EFFECT);
		}
		
		Date expireDate=site.getExpireDate();
		if(nowGmtDate.after(expireDate)){
			return returnErrorXmlStr(SiteConstant.OUTER_SITE_EXPIRE);
		}
		Element authIdElement=root.element("authId");
		if(authIdElement==null){
			return returnErrorXmlStr(SiteConstant.OUTER_WRONG_AUTHID);
		}
		String authId=authIdElement.getTextTrim();

		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign()
								+  root.element("timestamp").getText(), "MD5");
		
		
		if(authId==null || "".equals(authId)  || !authId.equalsIgnoreCase(md5Value)){
			return returnErrorXmlStr(SiteConstant.OUTER_WRONG_AUTHID);
		}
		String beginTimeStr=root.element("beginTime").getTextTrim();
		if(!DateUtil.isDate(beginTimeStr)){
			return returnErrorXmlStr(SiteConstant.OUTER_CONF_DATEERROR_BEGINTIME);
		}

		String endTimeStr=root.element("endTime").getTextTrim();
		if(!DateUtil.isDate(endTimeStr)){
			return returnErrorXmlStr(SiteConstant.OUTER_CONF_DATEERROR_ENDTIME);
		}
		Date beginTime=DateUtil.parseDate(beginTimeStr);
		Date endTime=DateUtil.parseDate(endTimeStr);
		if(beginTime.after(endTime)){
			return returnErrorXmlStr(SiteConstant.OUTER_CONF_DATEERROR_BEFORE);
		}
		Date beginTime12=DateUtil.addDate(endTime, 365);
		if(beginTime12.after(endTime)){
			return returnErrorXmlStr(SiteConstant.OUTER_CONF_DATEERROR_MORE12);
		}
		
		//查询已经结束的会议
		
		
		
		Integer timeZone=site.getTimeZone();
		Date beginGmtTime=DateUtil.getGmtDateByTimeZone(beginTime, timeZone);
		Date endGmtTime=DateUtil.getGmtDateByTimeZone(endTime, timeZone);
		List<Object> paramList=new ArrayList<Object>();
		StringBuffer sqlBuffer=new StringBuffer();
		List<ConfOuter> outerList=null;
		Object[] values=null;
		sqlBuffer.append("select tco.* from t_conf_base tcb,t_conf_outer tco  ");
		sqlBuffer.append("	 where tcb.del_flag=? ");
		paramList.add(ConstantUtil.DELFLAG_UNDELETE);
		sqlBuffer.append("			and tcb.conf_status=? ");
		paramList.add(ConfConstant.CONF_STATUS_FINISHED);
		sqlBuffer.append("			and tcb.site_id=? ");
		paramList.add(site.getId());
		sqlBuffer.append("			and (tcb.end_time`>=? and tcb.end_time`<=? )");
		paramList.add(beginGmtTime);
		paramList.add(endGmtTime);
		sqlBuffer.append("			and tco.conf_id=tcb.id ");
		values=(paramList==null || paramList.isEmpty())?null:paramList.toArray(new Object[]{});
		Hashtable<Integer,String> confKeyTable=new Hashtable<Integer, String>();

		try {
			outerList= libernate.getEntityListBase(ConfOuter.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(outerList!=null && outerList.size()>0){
			for(ConfOuter outer:outerList){
				if(outer!=null){
					confKeyTable.put(outer.getConfId(), outer.getMtgKey());
				}
			}
		}
		outerList=null;
		
		
		paramList=new ArrayList<Object>();
		sqlBuffer=new StringBuffer();
		
		sqlBuffer.append("select tcl.* from t_conf_base tcb,t_conf_log tcl  ");
		sqlBuffer.append("	 where tcb.del_flag=? ");
		paramList.add(ConstantUtil.DELFLAG_UNDELETE);
		sqlBuffer.append("			and tcb.conf_status=? ");
		paramList.add(ConfConstant.CONF_STATUS_FINISHED);
		sqlBuffer.append("			and tcb.site_id=? ");
		paramList.add(site.getId());
		sqlBuffer.append("			and (tcb.end_time`>=? and tcb.end_time`<=? )");
		paramList.add(beginGmtTime);
		paramList.add(endGmtTime);
		sqlBuffer.append("			and tcl.conf_id=tcb.id ");
		values=(paramList==null || paramList.isEmpty())?null:paramList.toArray(new Object[]{});
		
		List<ConfLog> logList=null;
		
		try {
			logList= libernate.getEntityListBase(ConfLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] excelTitle=new String[]{"MtgKey","userId","userName","userRole","joinTime","exitTime"};
		
		List<Object[]> exportList=new ArrayList<Object[]>();
		Object[] confLogArray=null;
		if(logList!=null && logList.size()>0){
			for(ConfLog confLog:logList){
				if(confLog!=null){
					confLogArray=new Object[6];
					confLogArray[0]=confKeyTable.get(confLog.getConfId());
					confLogArray[1]=confLog.getUserId();
					confLogArray[2]=confLog.getUserName();
					confLogArray[3]=confLog.getUserRole();
					confLogArray[4]=DateUtil.format(confLog.getJoinTime(), "");
					confLogArray[5]=DateUtil.format(confLog.getExitTime(), "");
					exportList.add(confLogArray);
				}
			}
		}

//		String excelName = siteSign + ".xlsx";
//		String savePath = this.getClass().getResource("/").getPath();
//		String domain = "http://" + siteSign + ".confcloud.cn";
//		String httpUrl = domain + "/excel/"+siteSign+"/export";
//		
		String httpUrl="";
		try {
			String excelName = beginTimeStr+"_"+endTimeStr + ".xlsx";
			String classPath = this.getClass().getClassLoader().getResource("/").getPath();
			String webRootPath=classPath.substring(0,classPath.indexOf("WEB-INF"));
			String relaPath="excel/"+siteSign+"/export";
			String savePath=webRootPath+relaPath+"/"+excelName;
			String domain = "http://" + siteSign + ".confcloud.cn";
			httpUrl = domain + "/"+relaPath +"/"+excelName;
			ExcelUtil.writeExcel(savePath, exportList, excelTitle, 50000);
		} catch (Exception e) {
//			e.printStackTrace();
			httpUrl="";
		}
				
/*
 * 	<errorCode>0</errorCode>
<beginTime>2013-12-01</beginTime>
	<endTime>2013-12-31</endTime>
	<excelUrl >http://domain/..../joinLog.xls</ excelUrl>

 * */
		Document retDoc = DocumentHelper.createDocument();   
        Element retRoot = DocumentHelper.createElement("result");  
        
        retRoot.addElement("errorCode").addCDATA("0");
        retRoot.addElement("beginTime").addCDATA(beginTimeStr);
        retRoot.addElement("endTime").addCDATA(endTimeStr);
        retRoot.addElement("excelUrl").addCDATA(httpUrl);
        
        retDoc.setRootElement(retRoot);  
		return  Dom4jUtil.getXmlStrWithoutHead(retDoc, true);
	}
	
	 
	private Integer getErrorCode(SiteBase site, Element root, ConfBase conf, String md5Value){
		if(conf == null){
			return SiteConstant.OUTER_MTGKEY_NOT_EXIST;
		}
		return getErrorCode(site, root, md5Value);
	}
	
	private Integer getErrorCode(SiteBase site, Element root, String md5Value, String year, String month){
		Integer yearValue = IntegerUtil.parseIntegerWithDefaultZero(year);
		if(yearValue.intValue() < 2000 || yearValue.intValue() > 2100){
			return SiteConstant.OUTER_CONFLIST_PARAM_YEAR;
		}
		Integer monthValue = IntegerUtil.parseIntegerWithDefaultZero(month);
		if(monthValue.intValue() < 1 || monthValue.intValue() > 12){
			return SiteConstant.OUTER_CONFLIST_PARAM_MONTH;
		}
		return getErrorCode(site, root, md5Value);
	}
	
	private Integer getErrorCode(SiteBase site, Element root, String md5Value){
		Integer status = getSiteStatusInfo(site);
		if(status != null && status.intValue() > 0){
			return status;
		}
		status = getAuthIdStatus(md5Value, root.element("authId").getText());
		if(status != null && status.intValue() > 0){
			return status;
		}
		status = getTimeStampStatus(root.element("timestamp").getText());
		if(status != null && status.intValue() > 0){
			return status;
		}
		return null;
	}
	
	private void getErrorCode(SiteBase site, JoinConfOuter joinConfOuter){
		Integer status = getSiteStatusInfo(site);
		if(status != null && status.intValue() > 0){
			throw new OuterConfException(String.valueOf(status));
		}
		if(StringUtil.isEmpty(joinConfOuter.getMtgKey()) || 
				StringUtil.isEmpty(joinConfOuter.getMtgTitle()) || StringUtil.isEmpty(joinConfOuter.getHostPwd())){
			throw new OuterConfException(String.valueOf(SiteConstant.OUTER_CONF_JOIN_CONFINFO_EMPTY));    //会议信息为空
		}
		if(StringUtil.isEmpty(joinConfOuter.getUserName()) || 
				StringUtil.isEmpty(joinConfOuter.getUserId()) || 
				joinConfOuter.getUserType() == null || joinConfOuter.getUserType().intValue() <= 0){
			throw new OuterConfException(String.valueOf(SiteConstant.OUTER_CONF_JOIN_USERINFO_EMPTY));    //用户信息不能为空
		}
		status = getTimeStampStatus(joinConfOuter.getTimestamp());
		if(status != null && status.intValue() > 0){
			throw new OuterConfException(String.valueOf(status));
		}
		String md5Value = MD5.encodePassword(site.getMd5Key() + site.getSiteSign() + joinConfOuter.getMtgKey()
				+ joinConfOuter.getXmlUserIdCopy() + joinConfOuter.getUserType() + joinConfOuter.getTimestamp(), "MD5");
		status = getAuthIdStatus(md5Value, joinConfOuter.getAuthId());
		if(status != null && status.intValue() > 0){
			throw new OuterConfException(String.valueOf(status));
		}
	}

	/**
	 * 验证站点状态
	 * return 
		1：siteId错误、或siteId不存在
		2：站点未到生效日期
		3、站点过期，不能使用
	 * jack
	 * 2013-12-10
	 */
	private Integer getSiteStatusInfo(SiteBase site) {
		if(site == null){
			return SiteConstant.OUTER_SITE_NOT_EXIST;
		}
		if(!site.isEffect()){
			return SiteConstant.OUTER_SITE_NOT_EFFECT;
		}
		if(site.isExpire()){
			return SiteConstant.OUTER_SITE_EXPIRE;
		}
		return null;
	}
	
	/**
	 * 验证timeStamp
	 * return 6、TimeStamp超时，不能超过一分钟
	 * jack
	 * 2013-12-11
	 */
	private Integer getTimeStampStatus(String timeStamp){
		Long timeDiff = (DateUtil.getCurrentTimeInMillis() - Long.valueOf(timeStamp));
		if(timeDiff > 1000*60 || timeDiff < 0){
			return SiteConstant.OUTER_EXPIRE_TIMESTAMP;
		}
		return null;
	}
	
	/**
	 * 验证authId
	 * return 4、authId参数不正确
	 * jack
	 * 2013-12-11
	 */
	private Integer getAuthIdStatus(String md5Value, String authId){
		if(!authId.equalsIgnoreCase(md5Value)){
			System.out.println("md5Value:" + md5Value);
			return SiteConstant.OUTER_WRONG_AUTHID;
		}
		return null;
	}
	
	/**
	 * 返回错误XML文件的字符串
	 * jack
	 * 2013-12-10
	 */
	private String returnErrorXmlStr(Integer errorCode){
		// 创建文档并设置文档的根元素节点   
        Element root = DocumentHelper.createElement("result");   
        Document document = DocumentHelper.createDocument(root); 
        Element errorCodeElement = root.addElement("errorCode");
        errorCodeElement.setText(String.valueOf(errorCode));
		return Dom4jUtil.getXmlStrWithoutHead(document, true);
	}
	
	
	private void setOuterConfLanguage(JoinConfOuter joinConfOuter, HttpServletRequest request, HttpServletResponse response){
		if (joinConfOuter.getLanguage() != null) {
			String lang = "zh-cn";
			if(joinConfOuter.getLanguage().intValue() == 1){
				lang = "en-us";
			}
			languageComponent.setDefaultLanguage(request, response, lang);
		}
	}
	
	 
	private void setBrowserVersion(HttpServletRequest request){
		String userAgent = request.getHeader("USER-AGENT");
		String ieBit = "Win32";
		Client client = BrowserUtil.getClient(userAgent);
		if (!StringUtil.isEmpty(userAgent)) {
			userAgent = userAgent.toLowerCase();
			if (userAgent.indexOf("msie") > 0) {
				if (userAgent.indexOf("win64") > 0) {
					ieBit = "Win64";
				}
			} else {
				ieBit = "";
			}
		}
		request.setAttribute("ieBit", ieBit);
		request.setAttribute("client", client);
	}
	
	private void setJoinRandom(ConfBase conf, JoinConfOuter joinConfOuter, HttpServletRequest request){
		JoinRandom joinRandom = new JoinRandom();
		joinRandom.setConfId(conf.getId());
		joinRandom.setUserId(Integer.parseInt(joinConfOuter.getUserId()));
		joinRandom.setUserName(joinConfOuter.getUserName());
		joinRandom.setUserEmail("");
		joinRandom.setUserRole(joinConfOuter.getUserType());
		joinRandom.setCreateTime(DateUtil.getGmtDate(null));
		joinRandom.setLanguage(LanguageHolder.getCurrentLanguage());    //设置进入会议客户端语言
		joinRandom.setClientIp(StringUtil.getIpAddr(request));
		joinRandom = clientAPIService.saveRandom(joinRandom);
		if(joinRandom==null || joinRandom.getId()<= 0){
			logger.info("根据用户信息ID号，生成JoinRandom对象错误, joinRandom = "+joinRandom);
			request.setAttribute("errorCode", SiteConstant.OUTER_CONF_JOIN_RANDOM);
			throw new OuterConfException(String.valueOf(SiteConstant.OUTER_CONF_JOIN_RANDOM));
		}
		//根据JoinRandom对象生成启动会议的小参数：preParam
		String preParam = clientAPIService.makePreParam(joinRandom, StringUtil.getIpAddr(request));
		request.setAttribute("preParam", preParam);
		request.setAttribute("cId", conf.getId());
		request.setAttribute("rId", joinRandom.getId());
	}
	
	private JoinConfOuter getJoinConfOuter(String xmlString) throws OuterConfException{
		JoinConfOuter joinConfOuter = new JoinConfOuter();
		if(StringUtil.isEmpty(xmlString)){
			throw new OuterConfException(String.valueOf(SiteConstant.OUTER_PARAMS_EMPTY));
		}
		Document doc = Dom4jUtil.getDocumentByXmlString(xmlString, "utf-8");
		if(doc == null){
			throw new OuterConfException(String.valueOf(SiteConstant.OUTER_PARAMS_EMPTY));
		}
		// 获取根元素
		Element root = doc.getRootElement();   
		joinConfOuter.setAuthId(root.element("authId").getText());
		joinConfOuter.setDuration(IntegerUtil.parseIntegerWithDefaultZero(root.element("duration").getText()));
//		joinConfOuter.setHostPwd(Base64.decode(root.element("hostPwd").getText()));   //主持人密码，原密码长度 6到8：通过Base64加密后
		joinConfOuter.setHostPwd(root.element("hostPwd").getText());   //测试时无需解密
		joinConfOuter.setLanguage(IntegerUtil.parseIntegerWithDefaultZero(root.element("language").getText()));
		joinConfOuter.setMtgKey(root.element("mtgKey").getText());
		joinConfOuter.setMtgPwd(root.element("mtgPwd") == null ? "" :root.element("mtgPwd").getText());
		joinConfOuter.setMtgTitle(root.element("mtgTitle").getText());
		String pageType = root.element("pageType") == null ? "true" :root.element("pageType").getText();
		if("1".equals(pageType)|| "true".equalsIgnoreCase(pageType)){
			joinConfOuter.setPageType(true);
		}else{
			joinConfOuter.setPageType(false);
		}
		joinConfOuter.setProductFlag(IntegerUtil.parseIntegerWithDefaultZero(root.element("productFlag") == null ? "1" : root.element("productFlag").getText()));
		String repeatFlag = root.element("repeatFlag") == null ? "1" :root.element("repeatFlag").getText();
		if("1".equals(repeatFlag)|| "true".equalsIgnoreCase(repeatFlag)){
			joinConfOuter.setRepeatFlag(true);
		}else{
			joinConfOuter.setRepeatFlag(false);
		}
		joinConfOuter.setSiteSign(root.element("siteId").getText());
		joinConfOuter.setTimestamp(root.element("timestamp").getText());
		joinConfOuter.setUserType(IntegerUtil.parseIntegerWithDefaultZero(root.element("userType").getText()));
		joinConfOuter.setUserName(root.element("userName").getText());
		String userId = root.element("userId").getText();
		joinConfOuter.setXmlUserIdCopy(userId);
		joinConfOuter.setUserId(userId);
		//根据joinConfOuter中的userId查询外部系统用户对应本系统的userid
		OuterUser outerUser = getUserByOuterUserId(joinConfOuter.getUserId(), joinConfOuter.getSiteSign());
		if(outerUser == null){
			outerUser = createOuterUser(joinConfOuter);
		}
		joinConfOuter.setUserId(outerUser != null ? String.valueOf(outerUser.getUserId()) : userId);
		return joinConfOuter;
	}
	
	private OuterUser getUserByOuterUserId(String outerUserId, String siteSign){
		OuterUser user = null;
		if(!StringUtil.isEmpty(outerUserId)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_outer_user ");
			sqlBuffer.append(" WHERE outer_user_id = ? ");
			sqlBuffer.append(" AND site_sign = ?");
			sqlBuffer.append(" order by user_id desc");
			try {
				user = libernate.getEntityCustomized(OuterUser.class, sqlBuffer.toString(), new Object[]{outerUserId, siteSign});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	private OuterUser createOuterUser(JoinConfOuter joinConfOuter){
		OuterUser user = new OuterUser();
		user.setSiteSign(joinConfOuter.getSiteSign());
		user.setOuterUserId(joinConfOuter.getUserId());
		user.setUserName(joinConfOuter.getUserName());
		try {
			UserBase userBase = new UserBase();
			userBase.init();
			userBase.setSiteId(siteService.getSiteBaseBySiteSign(joinConfOuter.getSiteSign()).getId());
			userBase = libernate.saveEntity(userBase);
			if(userBase != null){
				user.setUserId(userBase.getId());
				user = libernate.saveEntity(user);
				logger.info("加入会议后，保存用户对象，以供在线人数查询" + user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
