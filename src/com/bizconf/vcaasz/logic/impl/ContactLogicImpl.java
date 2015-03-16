package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.SiteContacts;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ContactLogic;
import com.bizconf.vcaasz.service.impl.BaseService;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class ContactLogicImpl extends BaseService implements ContactLogic {
	
	/**
	 * 修改联系人（单个）验证前台表单数据
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public boolean updateContactSingleValidate(Contacts contact) {
		return true;
	}
	
	/**
	 * 新建联系人（单个）验证前台表单数据
	 * 2013-3-11
	 */
	@Override
	public boolean createContactSingleValidate(Contacts contact) {
		if(contact.getContactName()==null || contact.getContactName().equals("")){
			return false;
		}else if(contact.getContactName().length()<1 || contact.getContactName().length()>32){
			return false;
//		}else{
//			Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5_\\-&]{1,32}$");
//			Matcher matcher = pattern.matcher(contact.getContactName());
//			if(!matcher.matches()){
//				return false;
//			}
		}
		//
		if(StringUtil.isNotBlank(contact.getContactPhone())){
			Pattern pattern = Pattern.compile("^((\\+86)?|\\(\\+86\\)|\\+86\\s|\\+86-)0?([1-9]\\d{1,2}-?\\d{6,8}|[3-9][13579]\\d-?\\d{6,7}|[3-9][24680]\\d{2}-?\\d{6})(#\\d{4})?$");//("^((\\+?[0-9]{2,4}\\-[0-9]{3,4}\\-)|([0-9]{3,4}\\-))?([0-9]{7,8})(\\-[0-9]+)?$");
			Matcher matcher = pattern.matcher(contact.getContactPhone());
			if(!matcher.matches()){
				return false;
			}
		}
		if(StringUtil.isNotBlank(contact.getContactMobile())){
			Pattern patternMobile = Pattern.compile("^((\\+86)?|\\(\\+86\\)|\\+86\\s|\\+86-)0?1[358]\\d{9}$");
			Matcher matcherMobile = patternMobile.matcher(contact.getContactMobile());
			if(!matcherMobile.matches()){
				return false;
			}
		}
		
		if(contact.getContactEmail()==null || contact.getContactEmail().equals("")){
			return false;
		}else{
			Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			Matcher matcher = pattern.matcher(contact.getContactEmail());
			if(!matcher.matches()){
				return false;
			}
		}
		
		StringBuffer strSql = new StringBuffer(" SELECT count(*) FROM t_contacts WHERE del_flag !=? and contact_email = ? and user_id = ? and id!=?");
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = contact.getContactEmail();
		values[2] = contact.getUserId();
		values[3] = contact.getId()==null?0:contact.getId();
		try {
//			int count = libernate.queryForInt(strSql.toString(), values);
//			if(count>0){
//				return false;
//			}
			int count = libernate.countEntityListWithSql(strSql.toString(), values);
			if(count>0){
				return false;
			}
		} catch (SQLException e) {
			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
			return false;
		}
		
		
		return true;
	}
	
	
	@Override
	public boolean contactEmailAvailable(Contacts contact) {
		
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_contacts WHERE del_flag !=? and contact_email = ? and user_id = ? and id!=?");
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = contact.getContactEmail();
		values[2] = contact.getUserId();
		values[3] = contact.getId()==null?0:contact.getId();
		try {
			contact = libernate.getEntityCustomized(Contacts.class, strSql.toString(), values);
			if(contact!=null){
				return false;
			}
		} catch (SQLException e) {
			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean contactEmailAvailableSite(SiteContacts contact) {
		
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_site_contacts WHERE del_flag !=? and contact_email = ? and site_id = ? and id!=?");
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = contact.getContactEmail();
		values[2] = contact.getSiteId();
		values[3] = contact.getId()==null?0:contact.getId();
		try {
			contact = libernate.getEntityCustomized(SiteContacts.class, strSql.toString(), values);
			if(contact!=null){
				return false;
			}
		} catch (SQLException e) {
			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
			return false;
		}
		return true;
	}
	
	/**
	 * 新建联系人（单个）验证前台表单数据
	 * 2014-08-20
	 */
	@Override
	public boolean createSiteContactSingleValidate(SiteContacts contact,UserBase currentUser) {
		if(contact.getContactName()==null || contact.getContactName().equals("")){
			return false;
		}else if(contact.getContactName().length()<1 || contact.getContactName().length()>32){
			return false;
		}
		//
		if(StringUtil.isNotBlank(contact.getContactPhone())){
			Pattern pattern = Pattern.compile("^((\\+86)?|\\(\\+86\\)|\\+86\\s|\\+86-)0?([1-9]\\d{1,2}-?\\d{6,8}|[3-9][13579]\\d-?\\d{6,7}|[3-9][24680]\\d{2}-?\\d{6})(#\\d{4})?$");//("^((\\+?[0-9]{2,4}\\-[0-9]{3,4}\\-)|([0-9]{3,4}\\-))?([0-9]{7,8})(\\-[0-9]+)?$");
			Matcher matcher = pattern.matcher(contact.getContactPhone());
			if(!matcher.matches()){
				return false;
			}
		}
		if(StringUtil.isNotBlank(contact.getContactMobile())){
			Pattern patternMobile = Pattern.compile("^((\\+86)?|\\(\\+86\\)|\\+86\\s|\\+86-)0?1[358]\\d{9}$");
			Matcher matcherMobile = patternMobile.matcher(contact.getContactMobile());
			if(!matcherMobile.matches()){
				return false;
			}
		}
		
		if(contact.getContactEmail()==null || contact.getContactEmail().equals("")){
			return false;
		}else{
			Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			Matcher matcher = pattern.matcher(contact.getContactEmail());
			if(!matcher.matches()){
				return false;
			}
		}
		
		//校验邮箱唯一性
//		StringBuffer strSql = new StringBuffer(" SELECT count(*) FROM t_site_contacts WHERE del_flag !=? and contact_email = ? and id!=? and site_id = ?");
//		Object[] values = new Object[4];
//		values[0] = ConstantUtil.DELFLAG_DELETED;
//		values[1] = contact.getContactEmail();
//		values[2] = contact.getId()==null?0:contact.getId();
//		values[3] = currentUser.getSiteId();
//		
//		try {
//			int count = libernate.countEntityListWithSql(strSql.toString(), values);
//			if(count>0){
//				return false;
//			}
//		} catch (SQLException e) {
//			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
//			return false;
//		}
		return true;
	}
	
}
