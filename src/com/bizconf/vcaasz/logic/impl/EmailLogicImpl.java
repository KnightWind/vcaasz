package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LicenseConstant;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.EmailConfig;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.EmailLogic;
import com.bizconf.vcaasz.service.impl.BaseService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
@Service
public class EmailLogicImpl extends BaseService implements EmailLogic{
	
	@Override
	public SiteBase getSiteBaseById(Integer id) {
		SiteBase siteBase = null;
		if(id != null && id.intValue()>0){
			StringBuffer strSql = new StringBuffer(" select * from t_site_base where id = ? ");
			Object[] values = new Object[1];
			try {
				values[0] = id;
				siteBase = libernate.getEntityCustomized(SiteBase.class, strSql.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return siteBase;
	}
	
	
	@Override
	public UserBase getSiteSupperMasterBySiteId(Integer siteId) {
		UserBase userBase=null;
		if(siteId!=null && siteId.intValue()>0){
			StringBuffer sqlBuffer=new StringBuffer();
			sqlBuffer.append(" SELECT * FROM t_user_base where id > 0 ");
			sqlBuffer.append(" and del_flag = ?");
			sqlBuffer.append(" and user_type = ?");
			sqlBuffer.append(" and site_id = ? ");
			Object[] values=new Object[3];
			values[0]=ConstantUtil.DELFLAG_UNDELETE;
			values[1]=ConstantUtil.USERTYPE_ADMIN_SUPPER;
			values[2]=siteId;
			try {
				userBase= libernate.getEntityCustomized(UserBase.class, sqlBuffer.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				sqlBuffer=null;
				values=null;
			}
		}
		return userBase;
	}

	@Override
	public EmailConfig getSysEmailConfig(int siteId) throws Exception {
		EmailConfig emailConfig = null;
		try {
			emailConfig = libernate
					.getEntityCustomized(EmailConfig.class,
							"select * from t_email_config where del_flag=? and site_id=?",
							new Object[] { ConstantUtil.DELFLAG_UNDELETE, siteId });
			if(emailConfig==null){
				emailConfig = libernate
						.getEntityCustomized(EmailConfig.class,
								"select * from t_email_config where del_flag=? and site_id=?",
								new Object[] { ConstantUtil.DELFLAG_UNDELETE, 0 });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailConfig;
	}
	
	@Override
	public UserBase getUserBaseById(Integer userId) {
		try {
			return libernate.getEntity(UserBase.class, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Date getSiteLocalDate(Date date,Integer siteId){
		//默认为北京时间（GMT +8）
		//System.out.println("date: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		if(date == null){
			return null;
		}
		int offset = 28800000;
		if(siteId!=null && siteId>0){
			SiteBase site = getSiteBaseById(siteId);
			offset = site.getTimeZone();
			if(offset == 0){
				offset = SiteConstant.TIMEZONE_WITH_CITY_MAP.get(site.getTimeZoneId()).getOffset();
			}
		}
		Date localDate = DateUtil.getOffsetDateByGmtDate(date, (long)offset);
		//System.out.println("localDate: "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDate));
		return localDate;
	}
	
	
	@Override
	public int getSiteLicenseNum(Integer siteId) {
		if(siteId==null) siteId =0;
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select sum(lic_num) from t_license where del_flag = ? and site_id=? and effe_flag=? and expire_flag = ?");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(siteId);
		values.add(LicenseConstant.HAS_EFFED);
		values.add(LicenseConstant.HAS_NOT_EFFED);
		int num = 0;
		try {
			num = libernate.countEntityListWithSql(sqlBuilder.toString(), values.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}


	@Override
	public String getQccodeURL(int confId, int hostflag) {
			String tokenStr = confId+ConstantUtil.TOKEN_KEY+hostflag;
			tokenStr = MD5.encodeToken(tokenStr);
			return "http://"+SiteIdentifyUtil.getCurrentDomine()+"/join/gotoQccodePage?confId="+confId+"&hostflag="+hostflag+"&token="+tokenStr;
	}


	@Override
	public String getSiteDomine(int siteId) {
		try {
			SiteBase site = libernate.getEntity(SiteBase.class, siteId);
			if(site!=null){
				return site.getSiteSign()+"."+SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
