package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.ConfOuter;
import com.bizconf.vcaasz.entity.JoinConfOuter;
import com.bizconf.vcaasz.entity.OuterUser;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ConfOuterService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapUserStatus;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.StringUtil;


@Service
public class ConfOuterServiceImpl extends BaseService implements ConfOuterService  {

	@Autowired
	EmpowerConfigService empowerConfigService;
	@Autowired
	ConfService confService;

	@Override
	public ConfOuter getConfBaseByMeyKey(String mtgKey) {
		ConfOuter outer=null;
		if(!StringUtil.isEmpty(mtgKey)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append("select * from t_conf_outer tco where tco.mtg_key = ? order by id desc");
			Object[] values=new Object[]{mtgKey};
			try {
				outer=libernate.getEntityCustomized(ConfOuter.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlBuffer=null;
			values=null;
		}
		return outer;
	}

	


	@Override
	public ConfOuter getConfBaseByMeyKeyAndSiteId(String mtgKey,String  siteSign) {
		ConfOuter outer=null;
		if(!StringUtil.isEmpty(mtgKey)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" select tco.* from t_conf_outer tco,t_conf_base tcb");
			sqlBuffer.append(" where tco.mtg_key = ? and tco.site_sign=?");
			sqlBuffer.append(" and tcb.id=tco.conf_id");
			sqlBuffer.append(" and tcb.conf_status<=?");
			sqlBuffer.append(" order by id desc");
			Object[] values=new Object[]{mtgKey,siteSign,ConfConstant.CONF_STATUS_OPENING};
			try {
				outer=libernate.getEntityCustomized(ConfOuter.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlBuffer=null;
			values=null;
		}
		return outer;
	}
	@Override
	public ConfOuter saveConfOuter(ConfOuter confOuter) {
		if(confOuter!=null && confOuter.getMtgTitle() !=null && !"".equals(confOuter.getMtgKey())){
			try {
				confOuter=libernate.saveEntity(confOuter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return confOuter;
		
	}

	@Override
	public ConfBase getConfByMtgkey(String mtgKey, String siteSign) {
		ConfBase conf = null;
		if(!StringUtil.isEmpty(mtgKey)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT a.* FROM t_conf_base a , t_conf_outer b ");
			sqlBuffer.append(" WHERE a.id = b.conf_id AND b.mtg_key = ?");
			sqlBuffer.append(" AND b.site_sign = ?");
			sqlBuffer.append(" order by a.id desc");
			try {
				conf = libernate.getEntityCustomized(ConfBase.class, sqlBuffer.toString(), new Object[]{mtgKey, siteSign});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conf;
	}
	
	@Override
	public List<ConfBase> getConfListByMtgkey(String mtgKey, String siteSign){
		List<ConfBase> confList = new ArrayList<ConfBase>();
		if(!StringUtil.isEmpty(mtgKey)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT a.* FROM t_conf_base a , t_conf_outer b ");
			sqlBuffer.append(" WHERE a.id = b.conf_id AND b.mtg_key = ?");
			sqlBuffer.append(" AND b.site_sign = ?");
			sqlBuffer.append(" order by a.id desc");
			try {
				confList = libernate.getEntityListBase(ConfBase.class, sqlBuffer.toString(), new Object[]{mtgKey, siteSign});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confList;
	}

	@Override
	public int getUserNumByMtgkey(String mtgKey, String siteSign) {
		if(StringUtil.isEmpty(mtgKey)){
			return 0;
		}
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append(" SELECT SUM(a.pc_num + a.phone_num) FROM t_conf_base a , t_conf_outer b ");
		sqlBuffer.append(" WHERE a.id = b.conf_id AND b.mtg_key = ? ");
		sqlBuffer.append(" AND b.site_sign = ?");
		sqlBuffer.append(" AND a.conf_status = ?");
		sqlBuffer.append(" order by a.id desc");
		int num = 0;
		try {
			num = libernate.countEntityListWithSql(sqlBuffer.toString(), new Object[]{mtgKey, siteSign, ConfConstant.CONF_STATUS_OPENING});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public PageBean<ConfLog> getConfLogOLList(SiteBase site, ConfBase conf,
			int pageNo, int pageSize) {
//		ESpaceMeetingAsSoapUserStatus[] soapUserArray = confManagementService.queryConfUserStatus("", pageNo, pageSize, site, null) ;
//		List<ConfLog> confLogList = getOLLogListFromSoap(conf.getId(), site.getId(), soapUserArray);
//		if(confLogList == null || confLogList.size() <= 0){
//			return null;
//		}
//		return getOuterLogList(confLogList, pageNo, pageSize);
		
		return null;
	}
	
	private PageBean<ConfLog> getOuterLogList(List<ConfLog> confLogList, int pageNo, int pageSize){
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM t_outer_user ");
		sqlBuffer.append(" WHERE `user_id` in ( ? ");
		valueList.add(0);
		for(ConfLog log : confLogList){
			sqlBuffer.append(" ,? ");
			valueList.add(log.getUserId());
		}
		sqlBuffer.append(" ) ");
		sqlBuffer.append(" order by id desc");
		PageBean<OuterUser> outerUserPage = getPageBeans(OuterUser.class, sqlBuffer.toString(), pageNo, pageSize, valueList.toArray());
		if(outerUserPage == null || outerUserPage.getDatas() == null){
			return null;
		}
		PageBean<ConfLog> page = new PageBean<ConfLog>();
		Map<Integer, String> outUserMap = new HashMap<Integer, String>();
		for(OuterUser user : outerUserPage.getDatas()){
			outUserMap.put(user.getUserId(), user.getOuterUserId());
		}
		for(int i = 0; i < confLogList.size(); i++){
			if(outUserMap.containsKey(confLogList.get(i).getUserId().intValue())){
				confLogList.get(i).setOuterUserId(outUserMap.get(confLogList.get(i).getUserId().intValue()));
			}
		}
		page.setPageNo(pageNo+"");
		page.setPageSize(pageSize);
		page.setRowsCount(confLogList.size());
		page.setDatas(confLogList);
		return page;
	}
	
	/**
	 * 获取在线用户list
	 * shhc
	 * 2014-1-21
	 */
	private List<ConfLog> getOLLogListFromSoap(Integer confId,Integer siteId,ESpaceMeetingAsSoapUserStatus[] soapUserArray){
		List<ConfLog> logList=null;
		if(soapUserArray != null && soapUserArray.length > 0){
			logList=new ArrayList<ConfLog>();
			ConfLog eachLog=null;
			String eachUri="";
			for(ESpaceMeetingAsSoapUserStatus eachSoapuser:soapUserArray){
				logger.info(" eachAS Soap Log Info:" + eachSoapuser);
				//1在线，0离线
				if(eachSoapuser!=null && "1".equals(eachSoapuser.getUserOnlineStatus())){
					eachLog=new ConfLog();
					eachLog.setSiteId(siteId);
					eachLog.setConfId(confId);
					eachLog.setUserId(IntegerUtil.parseInteger(eachSoapuser.getUserId()));
					eachLog.setUserName(eachSoapuser.getUserName());
					eachLog.setUserRole(eachSoapuser.getRole());
					eachLog.setTermType(eachSoapuser.getTermType());
					eachLog.setJoinType(eachSoapuser.getJoinType());
					eachLog.setLeaveType(eachSoapuser.getLeaveType());
					eachLog.setJoinTime(DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(eachSoapuser.getJoinDatetime(),"yyyy-MM-dd HH:mm:ss"),28800000));
					eachLog.setExitTime(DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(eachSoapuser.getLeaveDatetime(),"yyyy-MM-dd HH:mm:ss"),28800000));
					eachUri=eachSoapuser.getUri();
					eachLog.setPhone("");
					eachLog.setEmail("");
					if(!StringUtil.isEmpty(eachUri)){
						eachUri=eachUri.trim().toLowerCase();
						if(eachUri.indexOf("tel:")>-1){
							eachLog.setPhone(eachUri.substring(4));
						}
						if(eachUri.indexOf("sip:")>-1){
							eachLog.setEmail(eachUri.substring(4));
						}
					}
					logList.add(eachLog);
				}
				
			}
		}
		return logList;
	}

	@Override
	public PageBean<ConfOuter> getConfOuterList(String siteSign, String year,
			String month, int pageNo, int pageSize) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1, 0, 0, 0);
		Date beginDate = cal.getTime();
		cal.add(Calendar.MONTH, 1);
		Date endDate = cal.getTime();
//		System.out.println("开始日期：" + beginDate + "，结束日期：" + endDate);
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append(" SELECT a.*, b.conf_status, b.start_time, b.end_time FROM t_conf_outer a, t_conf_base b ");
		sqlBuffer.append(" WHERE a.`conf_id` = b.`id` ");
		sqlBuffer.append(" AND a.site_sign = ? ");
		sqlBuffer.append(" AND b.`start_time` >= ? ");
		sqlBuffer.append(" AND b.`start_time` < ? ");
		sqlBuffer.append(" order by a.id desc");
		valueList.add(siteSign);
		valueList.add(beginDate);
		valueList.add(endDate);
		return getPageBeans(ConfOuter.class, sqlBuffer.toString(), pageNo, pageSize, valueList.toArray());
	}

	@Override
	public PageBean<ConfLog> getConfLogList(String siteSign, String mtgKey,
			int pageNo, int pageSize) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append(" SELECT b.*, c.outer_user_id FROM t_conf_outer a, t_conf_log b, t_outer_user c ");
		sqlBuffer.append(" WHERE a.`conf_id` = b.`conf_id` ");
		sqlBuffer.append(" AND a.site_sign = ? ");
		sqlBuffer.append(" AND a.site_sign = c.site_sign ");
		sqlBuffer.append(" AND b.user_id = c.user_id ");
		sqlBuffer.append(" AND a.`mtg_key` = ? ");
		sqlBuffer.append(" order by a.id desc");
		valueList.add(siteSign);
		valueList.add(mtgKey);
		return getPageBeans(ConfLog.class, sqlBuffer.toString(), pageNo, pageSize, valueList.toArray());
	}

	@Override
	public ConfBase getOpeningConfByMtgkey(String mtgKey, String siteSign) {
		ConfBase conf = null;
		if(!StringUtil.isEmpty(mtgKey)){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT a.* FROM t_conf_base a , t_conf_outer b ");
			sqlBuffer.append(" WHERE a.id = b.conf_id AND b.mtg_key = ?");
			sqlBuffer.append(" AND b.site_sign = ?");
			sqlBuffer.append(" AND a.conf_status = ?");
			sqlBuffer.append(" order by a.id desc");
			try {
				conf = libernate.getEntityCustomized(ConfBase.class, sqlBuffer.toString(), new Object[]{mtgKey, siteSign, ConfConstant.CONF_STATUS_OPENING});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conf;
	}

	@Override
	public ConfBase createOuterConf(JoinConfOuter joinConfOuter, SiteBase site) {
		ConfBase conf = new ConfBase();
		ConfOuter confOuter = new ConfOuter();
		confOuter.initConf(joinConfOuter);
		conf.initConfBase(confOuter, site, empowerConfigService, confService);
		UserBase userBase=new UserBase();
		userBase.setId(IntegerUtil.parseIntegerWithDefaultZero(joinConfOuter.getUserId()));
		userBase.setTrueName(joinConfOuter.getUserName());
		userBase.setMobile("");
		userBase.setUserEmail("");
		conf = confService.saveConfBaseForOuter(conf, site, userBase);//.saveConfBase(confBase);
		if(conf!=null && conf.getId()!=null && conf.getId().intValue() >0 ){
			confOuter.setConfId(conf.getId());
			confOuter = saveConfOuter(confOuter);
		}
		return conf;
	}
}
