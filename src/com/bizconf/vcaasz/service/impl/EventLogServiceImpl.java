package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.BizEventObject;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.EventContent;
import com.bizconf.vcaasz.entity.EventLog;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SiteContacts;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberd.LibernateTransaction;
import com.libernate.support.lb.filter.impl.HttpWrapperOPImpl;

@Service
public class EventLogServiceImpl extends BaseService  implements EventLogService  {
	private final Logger logger=Logger.getLogger(EventLogServiceImpl.class);
	
	@Autowired
	SiteService siteService;
	
	/**
	 * 系统管理员操作日志
	 * @param sysUser
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	@Override
	public boolean saveSystemEventLog(SystemUser sysUser, Integer funcModule, String optDesc, Integer logStatus,
			Object object, HttpServletRequest request) {
		boolean saveStatus=false;
		EventLog eventLog=new EventLog();
		if(sysUser!=null){
			HttpWrapperOPImpl op=new HttpWrapperOPImpl();
			eventLog.setCreateIp(op.getRemoteAddr(request));
			eventLog.setCreateTime(DateUtil.getGmtDate(null));
			
			eventLog.setFuncModule(funcModule);
			eventLog.setCreateUser(sysUser.getId());
			eventLog.setLogStatus(logStatus);
			eventLog.setOptDesc(optDesc==null ? "":optDesc);
			eventLog.setOptType(0);
			eventLog.setSiteId(0);
			eventLog.setUserId(0);
			if(object!=null && object.getClass().getName().toLowerCase().indexOf("systemuser")>-1){
				SystemUser user=(SystemUser)object;
//				eventLog.setUserId(user.getId());
				eventLog.setOptId(user.getId());
				eventLog.setOptName(user.getLoginName());
			}
			else if(object!=null && object.getClass().getName().toLowerCase().indexOf("sitebase") > -1){
				SiteBase site = (SiteBase)object;
				eventLog.setOptId(site.getId());
				eventLog.setOptName(site.getSiteName());
			}
			else{
				eventLog.setOptName((String)object);
			}
			
			eventLog.setUserType(ConstantUtil.USERTYPE_SYSTEM);
			try {
				libernate.saveEntity(eventLog);
				saveStatus=true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				eventLog=null;
			}
		}
		return saveStatus;
	}
	
	/**
	 * 站点管理员操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param optDetailInfo 操作对象详情描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	@Override

	public boolean saveAdminEventLog(UserBase userBase,
			Integer funcModule, String optDesc, Integer logStatus,
			Object object, HttpServletRequest request) {
		boolean saveStatus=false;
		EventLog eventLog=new EventLog();
		if(userBase!=null){
			HttpWrapperOPImpl op=new HttpWrapperOPImpl();
			eventLog.setCreateIp(op.getRemoteAddr(request));
			eventLog.setCreateTime(DateUtil.getGmtDate(null));
			eventLog.setFuncModule(funcModule);
			eventLog.setCreateUser(userBase.getId());
			eventLog.setLogStatus(logStatus);
			eventLog.setOptDesc(optDesc==null ? "":optDesc);
			eventLog.setOptType(0);
			eventLog.setSiteId(userBase.getSiteId());
			eventLog.setUserId(0);
			if(object!=null && object.getClass().getName().toLowerCase().indexOf("userbase")>-1){
				UserBase user=(UserBase)object;
//				eventLog.setUserId(user.getId());
				eventLog.setOptId(user.getId());
//				eventLog.setOptName(user.getLoginName());
				eventLog.setOptName((user.getUserEmail()!=null && !"".equals(user.getUserEmail()))?user.getUserEmail():user.getLoginName());
				user=null;
			}
			/****************************************************/
			else if(object!=null && object.getClass().getName().toLowerCase().indexOf("sitebase") > -1){
				SiteBase site = (SiteBase)object;
				eventLog.setOptId(site.getId());
				eventLog.setOptName(site.getSiteName());
			}
			else if(object!=null && object.getClass().getName().toLowerCase().indexOf("notice") > -1){
				Notice notice = (Notice)object;
				eventLog.setOptId(notice.getId());
				eventLog.setOptName(notice.getTitle());
			}
			else if(object!=null && object.getClass().getName().toLowerCase().indexOf("sitecontacts") > -1){
				SiteContacts contacts = (SiteContacts)object;
				eventLog.setOptId(contacts.getId());
				eventLog.setOptName(contacts.getContactName());
			}
			else{
				eventLog.setOptName((String)object);
			}
			/****************************************************/
			
			eventLog.setUserType(ConstantUtil.USERTYPE_ADMIN);
			if(userBase.getUserType().equals(ConstantUtil.USERTYPE_ADMIN_SUPPER)){
				eventLog.setUserType(ConstantUtil.USERTYPE_ADMIN_SUPPER);
			}
			try {
				libernate.saveEntity(eventLog);
				saveStatus=true;
			} catch (Exception e) {

				e.printStackTrace();
			}finally{
				eventLog=null;
			}
		}
		return saveStatus;
	}
	
	/**
	 * 站点管理员批量操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	@Override
	public boolean saveAdminEventLogBatch(UserBase userBase,Integer funcModule,
			String optDesc, Integer logStatus, String[] ids,
			HttpServletRequest request){
		boolean saveFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		HttpWrapperOPImpl httpWrapperOP=new HttpWrapperOPImpl();
		String createIp = httpWrapperOP.getRemoteAddr(request);
		Date nowTime = DateUtil.getGmtDate(null);
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO t_event_log ");
		sqlBuilder.append(" (opt_type,func_module,opt_desc,user_type,create_time,create_user,create_ip,log_status,site_id,user_id) ");
		sqlBuilder.append(" VALUES ");
		int userSize = ids.length;
		for(int i=1;i<userSize+1;i++){
			if(i == userSize){
				sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?)");
			}else{
				sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?),");
			}
		}
		for(String id:ids){
			valueList.add(0);
			valueList.add(funcModule);
			valueList.add(optDesc==null ? "":optDesc);
			if(userBase.getUserType().equals(ConstantUtil.USERTYPE_ADMIN_SUPPER)){
				valueList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
			}else{
				valueList.add(ConstantUtil.USERTYPE_ADMIN);
			}
			valueList.add(nowTime);
			valueList.add(userBase.getId());
			valueList.add(createIp);
			valueList.add(logStatus);
			valueList.add(userBase.getSiteId());
			valueList.add(IntegerUtil.parseIntegerWithDefaultZero(id));
		}
		try {
			Object[] values = valueList.toArray();
			saveFlag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			logger.error("站点管理员批量操作日志",e);
			saveFlag = false;
		}
		return saveFlag;
	}

	/**
	 * 企业用户操作日志
	 * @param userBase
	 * @param module		日志模块,ConstantUtil中的funcModule
	 * @param optDesc		操作描述
	 * @param logStatus		操作状态：成功或者是失败
	 * @param optObject     操作对象
	 * @param request       Request对象
	 * @return
	 */
	@Override
	public boolean saveUserEventLog(UserBase userBase,
			Integer funcModule, String optDesc, Integer logStatus,
			Object object, HttpServletRequest request) {
		boolean saveStatus=false;
		EventLog eventLog=new EventLog();
		
		if(userBase!=null){
			SiteBase currentSite = siteService.getSiteBaseById(userBase.getSiteId());
			Integer timeZone=currentSite.getTimeZone();
			if (timeZone == null) {
				timeZone = 0;
			}
			HttpWrapperOPImpl op=new HttpWrapperOPImpl();
			eventLog.setCreateIp(op.getRemoteAddr(request));
			eventLog.setCreateTime(DateUtil.getGmtDate(null));
			eventLog.setFuncModule(funcModule);
			eventLog.setCreateUser(userBase.getId());
			eventLog.setLogStatus(logStatus);
			eventLog.setOptDesc(optDesc==null ? "":optDesc);
			eventLog.setOptType(0);
			eventLog.setSiteId(userBase.getSiteId());
			eventLog.setUserId(0);
			if(object!=null && object.getClass().getName().toLowerCase().indexOf("userbase")>-1){
				UserBase user=(UserBase)object;
				eventLog.setUserId(user.getId());
				eventLog.setOptId(user.getId());
//				eventLog.setOptName(user.getLoginName());
				eventLog.setOptName(user.getUserEmail());
				user=null;
			}
			if(object!=null && object.getClass().getName().toLowerCase().indexOf("confbase")>-1){
				ConfBase confBase=(ConfBase)object;
				eventLog.setConfId(confBase.getId());
				eventLog.setOptId(confBase.getId());
				eventLog.setOptName(confBase.getConfName());
				confBase=null;
			}
			
			/***********************************************/
			if(object!=null && object.getClass().getName().toLowerCase().indexOf("contact")>-1){
				Contacts contacts=(Contacts)object;
				eventLog.setOptId(contacts.getId());
				eventLog.setOptName(contacts.getContactName());
				contacts=null;
			}
			/***********************************************/
			eventLog.setUserType(ConstantUtil.USERTYPE_USERS);
			try {
				libernate.saveEntity(eventLog);
				saveStatus=true;
			} catch (Exception e) {
				logger.error("保存用户操作日志失败！" + e);
			}finally{
				eventLog=null;
			}
		}
		return saveStatus;
	}


//
//	/**
//	 * 企业用户操作日志
//	 * @param userBase
//	 * @param module		日志模块,ConstantUtil中的funcModule
//	 * @param optDesc		操作描述
//	 * @param logStatus		操作状态：成功或者是失败
//	 * @param optObject     操作对象
//	 * @param request       Request对象
//	 * @return
//	 */
//	@Override
//	public boolean saveConfEventLog(UserBase userBase,
//			Integer funcModule, String optDesc, Integer logStatus,
//			ConfBase confBase, HttpServletRequest request) {
//		boolean saveStatus=false;
//		EventLog eventLog=new EventLog();
//		
//		if(userBase!=null){
//			SiteBase currentSite = siteService.getSiteBaseById(userBase.getSiteId());
//			Integer timeZone=currentSite.getTimeZone();
//			if (timeZone == null) {
//				timeZone = 0;
//			}
//			HttpWrapperOPImpl op=new HttpWrapperOPImpl();
//			eventLog.setCreateIp(op.getRemoteAddr(request));
//			eventLog.setCreateTime(DateUtil.getGmtDateByTimeZone(DateUtil.getGmtDate(null),timeZone));
//			eventLog.setFuncModule(funcModule);
//			eventLog.setCreateUser(userBase.getId());
//			eventLog.setLogStatus(logStatus);
//			eventLog.setOptDesc(optDesc);
//			eventLog.setOptType(0);
//			eventLog.setSiteId(userBase.getSiteId());
//			eventLog.setUserId(0);
//			if(confBase!=null && confBase.getClass().getName().toLowerCase().indexOf("confbase")>-1){
////				ConfBase confb=(ConfBase)confBase;
//				eventLog.setConfId(confBase.getId());
////				user=null;
//			}
//			eventLog.setUserType(ConstantUtil.USERTYPE_USERS);
//			try {
//				libernate.saveEntity(eventLog);
//				saveStatus=true;
//			} catch (Exception e) {
//				logger.error("保存用户操作日志失败！" + e);
//			}finally{
//				eventLog=null;
//			}
//		}
//		return saveStatus;
//	}



	
	public boolean saveEventLog(Integer userId, Short userType, Short module,
			String optDesc, Short logStatus, String optObject,
			HttpServletRequest request) {
		
		
		return true;
	}
	
	/**
	 * 保存操作日志对象
	 *  返回true、false
	 * */ 
	public boolean saveEventLog(BizEventObject bizEventObject){
		
		boolean saveStatus=false;
		if(bizEventObject!=null){
			EventLog eventLog=new EventLog();
			//将bizEventObject对象中的基本信息放到EventLog中
			eventLog.setCreateUser(bizEventObject.getUserId());
			eventLog.setUserType(bizEventObject.getUserType());
			eventLog.setCreateIp(bizEventObject.getUserIp());
			eventLog.setFuncModule(bizEventObject.getModule());
			eventLog.setOptDesc(bizEventObject.getOptDesc());
			eventLog.setSiteId(bizEventObject.getSiteId());
			eventLog.setLogStatus(bizEventObject.getLogStatus());

			List<Object> objectList=bizEventObject.getObjectList();
			StringBuffer logBuffer=new StringBuffer();
			if(objectList != null && objectList.size() > 0){
			
				for(Object eachObject:objectList){
					if(eachObject != null){
						logBuffer.append(eachObject.toString());
					}
				}
			}
			LibernateTransaction tsc=new LibernateTransaction("saveEventLog");
			tsc.begin();
			try {
				//保存EventLog的基本信息
				eventLog =DAOProxy.getLibernate().saveEntity(eventLog);
				tsc.commit();
				try {
					logger.info(eventLog.toString());
					//根据 EventLog的ID号将对象信息内容保存到EventContent对象 中
					if(eventLog!=null && eventLog.getId().intValue() > 0){
						EventContent eachEventContent=null;
						List<String> contentList=StringUtil.splitByLength(logBuffer, 60000)	;	
						if (contentList != null && contentList.size() > 0) {
							logger.info("Content Size====" + contentList.size());
							String eachContent="";
							for (int ii = 0; ii < contentList.size(); ii++) {
								eachContent = contentList.get(ii);
								if (eachContent != null && eachContent.length() > 0) {
									eachEventContent = new EventContent();
									eachEventContent.setLogId(eventLog.getId());
									eachEventContent.setNewInfo(eachContent);
									eachEventContent.setSerialNum((short) (ii + 1));
									DAOProxy.getLibernate().saveEntity(eachContent);
									eachEventContent=null;
								}
							}
						}
						contentList = null;
					}
					tsc.commit();
					saveStatus = true;
				} catch (Exception e) {
					tsc.rollback();
					DAOProxy.getLibernate().deleteEntity(eventLog);
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally{
				tsc=null;
			}
			
		}
		return saveStatus;
	}

	@Override
	public List<EventLog> getSystemLogList(PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取系统管理员的事件日志（超级系统管理员）
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSystemLogListByLogType(Integer logType,String userName,String sortField,String sortord,PageModel pageModel){
		StringBuffer sqlBuffer = new StringBuffer();
		Object[] values = null;
	
		SystemUser systemUser=null;
		if(userName!=null && !"".equals(userName.trim()) && !"null".equals(userName.trim().toLowerCase())){
			sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from t_system_user where id >0 and del_flag = ?");
			sqlBuffer.append(" and true_name like ? ");
			sqlBuffer.append("");
			sqlBuffer.append("");
			sqlBuffer.append("");
			values = new Object[2];
			values[0]=ConstantUtil.DELFLAG_UNDELETE;
			values[1]="%"+userName+"%";
			
			try {
				systemUser=libernate.getEntityCustomized(SystemUser.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from t_event_log ");
		sqlBuffer.append(" where 1=1  and site_id <= 0");
		List<Object> vlaueList=new ArrayList<Object>();
		values=null;//new Object[]{};
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
//			values = new Object[2]{values};
//			values[0] = logType;
//			values[1] = logType + 100;
			vlaueList.add(logType);
			vlaueList.add(logType+100);
		}
		
		if(userName!=null  && !"".equals(userName.trim()) && !"null".equals(userName.trim().toLowerCase())){
			sqlBuffer.append(" and create_user =? ");
			if(systemUser==null ){
				vlaueList.add(0);
			}else{
				vlaueList.add(systemUser.getId());
			}
			
		}
		
		values=vlaueList.toArray();
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by " + BeanUtil.att2Field(eachField[1])+" ");
					break;
				}
			}
		}else{
			sqlBuffer.append(" order by " + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[0][1]));
		}
		
		if(SortConstant.SORT_ASC.equals(sortord)){
			sqlBuffer.append(" asc ");
		}
		
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())  || "null".equals(sortord.trim().toLowerCase())){
			sqlBuffer.append(" desc ");
		}
		
		
		
		if (pageModel != null) {
			
			sqlBuffer.append(" limit  ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}

		logger.info("-------------------------->>"+sqlBuffer.toString());
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			values = null;
		}

		return logList;
	}
	
	
	/**
	 * 获取系统管理员的事件日志（普通系统管理员查询）
	 * 
	 * @param pageModel
	 * @param sysUserId  普通系统管理员id
	 * @return
	 */
	public List<EventLog> getSystemLogListByLogType(Integer logType, String sortField, String sortord, PageModel pageModel, int sysUserId){
		StringBuffer sqlBuffer = new StringBuffer();
		Object[] values = null;
		sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from t_event_log ");
		sqlBuffer.append(" where 1=1  and site_id <= 0");
		List<Object> vlaueList=new ArrayList<Object>();
		values=null;
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
			vlaueList.add(logType);
			vlaueList.add(logType+100);
		}
		if(sysUserId > 0){
			sqlBuffer.append(" and create_user =? ");
			vlaueList.add(sysUserId);
		}
		values=vlaueList.toArray();
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by " + BeanUtil.att2Field(eachField[1])+" ");
					break;
				}
			}
		}else{
			sqlBuffer.append(" order by " + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[0][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord)){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())  || "null".equals(sortord.trim().toLowerCase())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit  ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("-------------------------->>"+sqlBuffer.toString());
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			values = null;
		}
		return logList;
	}
	
	
	/**
	 * 统计系统管理员事件日志的条数（超级系统管理员）
	 * @param logType
	 * @return
	 */
	public Integer countSystemLogByLogType(Integer logType,String userName){
		StringBuffer sqlBuffer = new StringBuffer();
		Object[] values = null;
		SystemUser systemUser=null;
		if(userName!=null && !"".equals(userName.trim()) && !"null".equals(userName.trim().toLowerCase())){
			sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from t_system_user where id >0 and del_flag = ?");
			sqlBuffer.append(" and true_name like ? ");
			sqlBuffer.append("");
			sqlBuffer.append("");
			sqlBuffer.append("");
			values = new Object[2];
			values[0]=ConstantUtil.DELFLAG_UNDELETE;
			values[1]="%"+userName+"%";
			
			try {
				systemUser=libernate.getEntityCustomized(SystemUser.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as rowCount from t_event_log ");
		sqlBuffer.append(" where 1=1 and site_id <= 0 ");
		
		List<Object> vlaueList=new ArrayList<Object>();
		values=null;//new Object[]{};
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
//			values = new Object[2]{values};
//			values[0] = logType;
//			values[1] = logType + 100;
			vlaueList.add(logType);
			vlaueList.add(logType+100);
		}
		
		if(userName!=null  && !"".equals(userName.trim()) && !"null".equals(userName.trim().toLowerCase())){
			sqlBuffer.append(" and create_user =? ");
			if(systemUser==null ){
				vlaueList.add(0);
			}else{
				vlaueList.add(systemUser.getId());
			}
			
		}
		
		values=vlaueList.toArray();
		
		Integer count=0;
//		List countList = null;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
//			if(countList!=null){
//				logger.info("String.valueOf(countList.get(0))=="+String.valueOf(countList.get(0)));
//				count=IntegerUtil.parseInteger(String.valueOf(countList.get(0)));
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			values = null;
		}
		
		return count;
	}
	
	/**
	 * 统计系统管理员事件日志的条数（普通系统管理员查询）
	 * @param logType
	 * @param sysUserId  普通系统管理员id
	 * @return
	 */
	@Override
	public Integer countSystemLogByLogType(Integer logType,int sysUserId){
		StringBuffer sqlBuffer = new StringBuffer();
		Object[] values = null;
		sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as rowCount from t_event_log ");
		sqlBuffer.append(" where 1=1 and site_id <= 0 ");
		List<Object> vlaueList = null;
		if (logType != null && logType.intValue() > 0) {
			if(vlaueList == null ){
				vlaueList = new ArrayList<Object>();
			}
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
			vlaueList.add(logType);
			vlaueList.add(logType+100);
		}
		if(sysUserId > 0){
			if(vlaueList == null ){
				vlaueList = new ArrayList<Object>();
			}
			sqlBuffer.append(" and create_user =? ");
			vlaueList.add(sysUserId);
		}
		if(vlaueList != null ){
			values = vlaueList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			values = null;
		}
		return count;
	}



	/**
	 * 统计站点管理员操作日志的条数（超级系统管理员）
	 * @param  operator 操作员（模糊查询）
	 * wangyong
	 * 2013-2-25
	 */
	public Integer countSiteAdminLog(Integer logType, String operator){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT count(1) FROM t_event_log a,t_user_base b, t_site_base c WHERE 1=1 ");
		sqlBuffer.append(" and a.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND a.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and a.func_module > ? ");
			sqlBuffer.append(" and a.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND a.create_user = b.id ");
		sqlBuffer.append(" AND a.site_id = c.id ");
		if(StringUtil.isNotBlank(operator)){
//			sqlBuffer.append(" and b.true_name LIKE ? ");
//			valList.add("%"+ operator +"%");
			sqlBuffer.append(" and (a.opt_name LIKE ? or b.true_name LIKE ? or c.site_sign LIKE ? )");
			valList.add("%"+ operator +"%");
			valList.add("%"+ operator +"%");
			valList.add("%"+ operator +"%");
		}
		if(valList != null){
			values = valList.toArray();
		}
		Integer count = 0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}
	
	/**
	 * 统计站点管理员操作日志的条数（普通系统管理员）
	 * @param sysUserId  普通站点管理员id
	 * wangyong
	 * 2013-2-25
	 */
	public Integer countSiteAdminLog(Integer logType, int sysUserId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as rowCount from t_event_log ");
		sqlBuffer.append(" where 1=1  and site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(sysUserId > 0){
			sqlBuffer.append(" and create_user = ? ");
			valList.add(sysUserId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}
	
	/**
	 * 获取站点管理员操作日志（超级系统管理员）
	 * @param  operator 操作员（模糊查询）
	 * wangyong
	 * 2013-2-25
	 */
	public List<EventLog> getSiteAdminLogList(Integer logType, String operator, String sortField, String sortord, PageModel pageModel){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT tel.* FROM t_event_log tel,t_user_base b,t_site_base c WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND tel.create_user = b.id ");
		sqlBuffer.append(" AND tel.site_id = c.id ");
		if(StringUtil.isNotBlank(operator)){
//			sqlBuffer.append(" and b.true_name LIKE ? ");
//			valList.add("%"+ operator +"%");
			sqlBuffer.append(" and (tel.opt_name LIKE ? or b.true_name LIKE ? or c.site_sign LIKE ? )");
			valList.add("%"+ operator +"%");
			valList.add("%"+ operator +"%");
			valList.add("%"+ operator +"%");
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[2][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}
	
	/**
	 * 获取站点管理员操作日志（普通系统管理员）
	 * @param sysUserId  普通站点管理员id
	 * wangyong
	 * 2013-2-25
	 */
	public List<EventLog> getSiteAdminLogList(Integer logType, String sortField, String sortord, PageModel pageModel, int sysUserId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tel.* from t_event_log tel");
		sqlBuffer.append(" where  1=1 and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(sysUserId > 0){
			sqlBuffer.append(" and tel.create_user = ? ");
			valList.add(sysUserId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[0][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}
	
	/**
	 * 超级站点管理员根据站点ID号获取站点的LOG
	 * @param siteId
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EventLog> getSiteLogListBySiteId(Integer siteId, String operator, Integer logType,String sortField,String sortord,PageModel pageModel) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tel.* from t_event_log tel,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND tel.create_user = b.id ");
		if(StringUtil.isNotBlank(operator)){
			sqlBuffer.append(" and b.true_name LIKE ? ");
			valList.add("%"+ operator +"%");
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[2][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}

	/**
	 * 统计某站点下的超级站点管理员log数量
	 * @param siteId
	 * @param logType
	 * @return
	 */
	@Override
	public Integer countSiteLogBySiteId(Integer siteId, String operator, Integer logType) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from t_event_log tel,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND tel.create_user = b.id ");
		if(StringUtil.isNotBlank(operator)){
			sqlBuffer.append(" and b.true_name LIKE ? ");
			valList.add("%"+ operator +"%");
		}
		if(valList!=null){
			values=valList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}

	/**
	 * 普通站点管理员根据站点ID号获取站点的LOG
	 * @param siteId
	 * @param pageModel
	 * @param siteAdminId  普通系统管理员id
	 * @return
	 */
	@Override
	public List<EventLog> getSiteLogListBySiteId(Integer siteId,
			Integer logType, String sortField, String sortord,
			PageModel pageModel, int siteAdminId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tel.* from t_event_log tel");
		sqlBuffer.append(" where  1=1 and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(siteAdminId > 0){
			sqlBuffer.append(" and tel.create_user = ? ");
			valList.add(siteAdminId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[2][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}

	/**
	 * 统计某站点下的普通站点管理员log数量
	 * @param siteId
	 * @param logType
	 * @param siteAdminId  普通系统管理员id
	 * @return
	 */
	@Override
	public Integer countSiteLogBySiteId(Integer siteId, Integer logType,
			int siteAdminId) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as rowCount from t_event_log ");
		sqlBuffer.append(" where 1=1  and site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND user_type in (?,?) ");
		valList.add(ConstantUtil.USERTYPE_ADMIN_SUPPER);
		valList.add(ConstantUtil.USERTYPE_ADMIN);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and func_module > ? ");
			sqlBuffer.append(" and func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(siteAdminId > 0){
			sqlBuffer.append(" and create_user = ? ");
			valList.add(siteAdminId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}
	
	/**
	 * 超级站点管理员统计某站点下的企业用户log数量
	 * @param siteId
	 * @param operator 操作员(模糊查询)
	 * @param logType
	 * @return
	 */
	public Integer countSiteUserLogBySiteId(Integer siteId, String operator, Integer logType){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from t_event_log tel,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type = ? ");
		valList.add(ConstantUtil.USERTYPE_USERS);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND tel.create_user = b.id ");
		if(StringUtil.isNotBlank(operator)){
			sqlBuffer.append(" and b.true_name LIKE ? ");
			valList.add("%"+ operator +"%");
		}
		if(valList!=null){
			values=valList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}
	
	/**
	 * 普通站点管理员统计某站点下的企业用户的操作日志log数量
	 * @param siteId
	 * @param logType
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public Integer countSiteUserLogBySiteId(Integer siteId, Integer logType, int siteAdminId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from t_event_log tel,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type = ? ");
		valList.add(ConstantUtil.USERTYPE_USERS);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(siteAdminId > 0){
			sqlBuffer.append(" AND tel.create_user = b.id ");
			sqlBuffer.append(" and b.create_user = ? ");
			valList.add(siteAdminId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		Integer count=0;
		try {
			count = libernate.countEntityListWithSql(sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return count;
	}

	
	/**
	 * 超级站点管理员根据站点ID号获取企业用户操作日志log
	 * @param siteId
	 * @param operator 操作员(模糊查询)
	 * @param pageModel
	 * @return
	 */
	public List<EventLog> getSiteUserLogListBySiteId(Integer siteId, String operator, Integer logType, String sortField, String sortord, PageModel pageModel){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tel.* from t_event_log tel,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type = ? ");
		valList.add(ConstantUtil.USERTYPE_USERS);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		sqlBuffer.append(" AND tel.create_user = b.id ");
		if(StringUtil.isNotBlank(operator)){
			sqlBuffer.append(" and b.true_name LIKE ? ");
			valList.add("%"+ operator +"%");
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[2][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}
	
	/**
	 * 普通站点管理员根据站点ID号获取企业用户操作日志log
	 * @param siteId
	 * @param pageModel
	 * @param siteAdminId  普通站点管理员id
	 * @return
	 */
	public List<EventLog> getSiteUserLogListBySiteId(Integer siteId, Integer logType, String sortField, String sortord, PageModel pageModel, int siteAdminId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tel.* from t_event_log tel ,t_user_base b WHERE 1=1 ");
		sqlBuffer.append(" and tel.site_id > 0 ");
		Object[] values = null;
		List<Object> valList = new ArrayList<Object>();
		sqlBuffer.append(" AND tel.user_type = ? ");
		valList.add(ConstantUtil.USERTYPE_USERS);
		if(siteId!=null && siteId.intValue()>0){
			sqlBuffer.append(" and tel.site_id =? ");
			valList.add(siteId);
		}
		if (logType != null && logType.intValue() > 0) {
			sqlBuffer.append(" and tel.func_module > ? ");
			sqlBuffer.append(" and tel.func_module < ? ");
			valList.add(logType);
			valList.add(logType + 100);
		}
		if(siteAdminId > 0){
			sqlBuffer.append(" AND tel.create_user = b.id ");
			sqlBuffer.append(" and b.create_user = ? ");
			valList.add(siteAdminId);
		}
		if(valList!=null){
			values=valList.toArray();
		}
		if(StringUtil.isNotBlank(sortField)){
			String[][] eventLogFields=SortConstant.EVENTLOG_FIELDS;
			for(String[] eachField:eventLogFields){
				if(eachField[0].equals(sortField)){
					sqlBuffer.append(" order by tel." + BeanUtil.att2Field(eachField[1])+" ");
				}
			}
		}else{
			sqlBuffer.append(" order by tel." + BeanUtil.att2Field(SortConstant.EVENTLOG_FIELDS[2][1]));
		}
		if(SortConstant.SORT_ASC.equals(sortord) ){
			sqlBuffer.append(" asc ");
		}
		if(SortConstant.SORT_DESC.equals(sortord) || sortord==null || "".equals(sortord.trim())){
			sqlBuffer.append(" desc ");
		}
		if (pageModel != null) {
			sqlBuffer.append(" limit ");
			sqlBuffer.append(" " + ((IntegerUtil.parseInteger(pageModel.getPageNo()) - 1) * pageModel.getPageSize()));
			sqlBuffer.append(" , " + pageModel.getPageSize());
		}
		logger.info("sqlBuffer=="+sqlBuffer);
		List<EventLog> logList = null;
		try {
			logList = libernate.getEntityListBase(EventLog.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlBuffer = null;
			valList=null;
			values = null;
		}
		return logList;
	}

	@Override
	public EventLog getEventLogById(Integer logid) {
		String strSql = " select * from t_event_log where 1=1 and id = ? ";
		EventLog eventLog = null;
		try {
			eventLog = libernate.getEntityCustomized(EventLog.class, strSql, new Object[]{logid});
		} catch (SQLException e) {
			logger.error("getSysLogById error");
		}
		return eventLog;
	}
}
