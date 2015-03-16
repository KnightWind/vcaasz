package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SiteContacts;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.ContactLogic;
import com.bizconf.vcaasz.service.ContactService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.NumberUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class ContactServiceImpl extends BaseService implements ContactService {

	@Autowired
	ContactLogic contactLogic;

	/**
	 * 根据姓名、手机或邮箱地址模糊查询联系人列表统计条数
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Integer countContactsList(String nameOrEmailOrMobile,
			UserBase currentUser) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		if(currentUser != null && currentUser.getId() != null && currentUser.getId().intValue() > 0){
			//user_id为主人
			StringBuffer strSql = new StringBuffer(" select count(1) from t_contacts where del_flag != ? and user_id = ? ");
			valueList.add(ConstantUtil.DELFLAG_DELETED);
			valueList.add(currentUser.getId().intValue());
			if(StringUtil.isNotBlank(nameOrEmailOrMobile)){
				strSql.append(" and (contact_name like ? or contact_email like ? or contact_mobile like ? ) ");
				valueList.add("%"+ nameOrEmailOrMobile +"%");
				valueList.add("%"+ nameOrEmailOrMobile +"%");
				valueList.add("%"+ nameOrEmailOrMobile +"%");
			} 
			Object[] values = valueList.toArray();
			try {
				rows = libernate.countEntityListWithSql(strSql.toString(), values);
			} catch (SQLException e) {
				logger.error("根据姓名、手机或邮箱地址模糊查询联系人列表统计条数出错！"+e);
			}
		}
		return rows;
	}



	/**
	 * 根据姓名、手机或邮箱地址模糊查询联系人列表
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public List<Contacts> listContactsList(String nameOrEmailOrMobile,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser) {
		List<Contacts> contactList = new ArrayList<Contacts>();
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" select * from t_contacts where del_flag != ? and user_id = ?  ");
		valueList.add(ConstantUtil.DELFLAG_DELETED);
		valueList.add(currentUser.getId().intValue());
		if(StringUtil.isNotBlank(nameOrEmailOrMobile)){
			strSql.append(" and (contact_name like ? or contact_email like ? or contact_mobile like ? ) ");
			valueList.add("%"+ nameOrEmailOrMobile +"%");
			valueList.add("%"+ nameOrEmailOrMobile +"%");
			valueList.add("%"+ nameOrEmailOrMobile +"%");
		} 
		String sortFieldValue = initSort(sortField);     //获取页面传递的排序参数
		String sortordValue = "desc";
		if(SortConstant.SORT_ASC.equals(sortord)){
			sortordValue = "asc";
		}
		if(StringUtil.isNotBlank(sortFieldValue)){
			strSql.append(" order by ").append(sortFieldValue);
		}else{ 
			strSql.append(" order by id DESC ");   //查出列表无排序条件则为默认逆序
		}
		if(StringUtil.isNotBlank(sortFieldValue) && StringUtil.isNotBlank(sortordValue) && "desc".equals(sortordValue.trim().toLowerCase())){
			strSql.append(" DESC");
		}
		if(pageModel != null){
			int recordNo = (Integer.parseInt(pageModel.getPageNo())-1) * pageModel.getPageSize();   //当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		try{
			Object[] values = valueList.toArray(); 
			contactList = libernate.getEntityListBase(Contacts.class, strSql.toString(), values);
		}catch (Exception e){
			logger.error("根据姓名、手机或邮箱地址模糊查询联系人列表出错！"+e);
		}
		return contactList;
	}

	/**
	 * 获取企业通讯录条数
	 * 站点下的企业用户：
	 * 1.如果该企业用户为超级企业管理员创建，则可以看到站点下的所有企业用户；
	 * 2.如果该企业用户为普通企业管理员创建，则只能看到该普通企业管理员创建的企业用户名单。
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Integer countSiteContactsList(String nameOrEmailOrMobile,
			UserBase currentUser) {
		int rows = 0;
		UserBase createUser = new UserBase();
		List<Object> valueList = new ArrayList<Object>();
		if(currentUser != null && currentUser.getId() != null && currentUser.getId().intValue() > 0){
			//user_id为主人
			StringBuffer strSql = new StringBuffer(" select * from t_user_base where del_flag = ? and id = ? ");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(currentUser.getCreateUser().intValue());
			Object[] values = valueList.toArray();
			try {
				createUser = libernate.getEntityCustomized(UserBase.class, strSql.toString(), values);
			} catch (SQLException e1) {
				logger.error("获取企业通讯录条数出错，获取用户创建者信息出错！"+e1);
			}
			if(createUser != null && createUser.getId() != null && createUser.getId().intValue() > 0){
				values = null;
				strSql = new StringBuffer("SELECT * FROM t_user_base WHERE del_flag = ? AND user_type = ? AND site_id = ? ");
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(ConstantUtil.USERTYPE_USERS);
				if(currentUser.getSiteId() != null){
					valueList.add(currentUser.getSiteId().intValue());
				}else{
					valueList.add(0);
				}
				if(createUser.isSiteAdmin()){    //如果该企业用户为普通企业管理员创建
					strSql.append(" AND create_user = ?");
					valueList.add(currentUser.getCreateUser().intValue());
				}
				if(StringUtil.isNotBlank(nameOrEmailOrMobile)){
					strSql.append(" and (contact_name like ? or contact_email like ? or contact_mobile like ? ) ");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
				} 
				values = valueList.toArray();
				try {
					rows = libernate.countEntityListWithSql(strSql.toString(), values);
				} catch (SQLException e) {
					logger.error("获取企业通讯录条数出错！"+e);
				}
			}
		}
		return rows;
	}

	/**
	 * 获取企业通讯录
	 * 站点下的企业用户：
	 * 1.如果该企业用户为超级企业管理员创建，则可以看到站点下的所有企业用户；
	 * 2.如果该企业用户为企业管理员创建，则只能看到企业管理员创建的企业用户名单。
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public List<Contacts> listSiteContactsList(String nameOrEmailOrMobile,
			PageModel pageModel, String sortField, String sortord,
			UserBase currentUser) {
		List<Contacts> contactList = new ArrayList<Contacts>();
		UserBase createUser = new UserBase();
		List<Object> valueList = new ArrayList<Object>();
		if(currentUser != null && currentUser.getId() != null && currentUser.getId().intValue() > 0){
			//user_id为主人
			StringBuffer strSql = new StringBuffer(" select * from t_user_base where del_flag = ? and id = ? ");
			valueList.add(ConstantUtil.DELFLAG_UNDELETE);
			valueList.add(currentUser.getCreateUser().intValue());
			Object[] values = valueList.toArray();
			try {
				createUser = libernate.getEntityCustomized(UserBase.class, strSql.toString(), values);
			} catch (SQLException e1) {
				logger.error("获取企业通讯录出错，获取用户创建者信息出错！"+e1);
			}
			if(createUser != null && createUser.getId() != null && createUser.getId().intValue() > 0){
				values = null;
				strSql = new StringBuffer("SELECT * FROM t_user_base WHERE del_flag = ? AND user_type = ? AND site_id = ? ");
				valueList.add(ConstantUtil.DELFLAG_UNDELETE);
				valueList.add(ConstantUtil.USERTYPE_USERS);
				if(currentUser.getSiteId() != null){
					valueList.add(currentUser.getSiteId().intValue());
				}else{
					valueList.add(0);
				}
				if(createUser.isSiteAdmin()){    //如果该企业用户为普通企业管理员创建
					strSql.append(" AND create_user = ?");
					valueList.add(currentUser.getCreateUser().intValue());
				}
				if(StringUtil.isNotBlank(nameOrEmailOrMobile)){
					strSql.append(" and (contact_name like ? or contact_email like ? or contact_mobile like ? ) ");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
					valueList.add("%"+ nameOrEmailOrMobile +"%");
				} 
				String sortFieldValue = initSort(sortField);     //获取页面传递的排序参数
				String sortordValue = "desc";
				if(SortConstant.SORT_ASC.equals(sortord)){
					sortordValue = "asc";
				}
				if(StringUtil.isNotBlank(sortFieldValue)){
					strSql.append(" order by ").append(sortFieldValue);
				}else{ 
					strSql.append(" order by id DESC ");   //查出列表无排序条件则为默认逆序
				}
				if(StringUtil.isNotBlank(sortFieldValue) && StringUtil.isNotBlank(sortordValue) && "desc".equals(sortordValue.trim().toLowerCase())){
					strSql.append(" DESC");
				}
				if(pageModel != null){
					int recordNo = (Integer.parseInt(pageModel.getPageNo())-1) * pageModel.getPageSize();   //当前页第一条记录在数据库中位置
					strSql.append(" limit ? , ?  ");
					valueList.add(recordNo);
					valueList.add(pageModel.getPageSize());
				}
				try {
					values = valueList.toArray();
					contactList = libernate.getEntityListBase(Contacts.class, strSql.toString(), values);
				} catch (SQLException e) {
					logger.error("获取企业通讯录出错！"+e);
				}
			}
		}
		return contactList;
	}

	/**
	 * 新建联系人（单个）
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Contacts createContactSingle(Contacts contact, UserBase currentUser) {

		Contacts contactInfo = null;
		if(contact != null && currentUser != null && currentUser.getId() != null){
			//			if(!contactLogic.createContactSingleValidate(contact)){
			//				logger.error("新建联系人（单个）验证前台表单数据出错！");
			//				return contactInfo;
			//			}
			contact = (Contacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");
			try {
				contact.initContact();
				contact.setUserId(currentUser.getId());
				contact.setSiteId(currentUser.getSiteId());
				contact.setCreateUser(currentUser.getId());
				contactInfo = libernate.saveEntity(contact);
			} catch (Exception e) {
				logger.error("新建联系人（单个）失败", e);
			}
		}
		return contactInfo;
	}

	/**
	 * 新建联系人（单个）
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public SiteContacts createSiteContactSingle(SiteContacts contact, UserBase currentUser) {

		SiteContacts contactInfo = null;
		if(contact != null && currentUser != null && currentUser.getId() != null){
			//			if(!contactLogic.createContactSingleValidate(contact)){
			//				logger.error("新建联系人（单个）验证前台表单数据出错！");
			//				return contactInfo;
			//			}
			contact = (SiteContacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");
			try {
				contact.initContact();
				contact.setSiteId(currentUser.getSiteId());
				contact.setCreateUser(currentUser.getId());
				contactInfo = libernate.saveEntity(contact);
			} catch (Exception e) {
				logger.error("新建联系人（单个）失败", e);
			}
		}
		return contactInfo;
	}
	/**
	 * 根据联系人ID获取联系人详细信息
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Contacts getContactById(Integer contactId) {
		Contacts contact = new Contacts();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_contacts WHERE 1=1 AND id = ? ");
		Object[] values = new Object[]{
				contactId
		};
		try {
			contact = libernate.getEntityCustomized(Contacts.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据联系人ID获取联系人详细信息出错！",e);
		}
		if(contact!=null){
			contact = (Contacts)ObjectUtil.parseChar(contact, "contactName","contactDesc");
		}
		return contact;
	}

	/**
	 * 根据联系人ID获取联系人详细信息
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public SiteContacts getSiteContactById(Integer contactId) {
		SiteContacts contact = new SiteContacts();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_site_contacts WHERE 1=1 AND id = ? ");
		Object[] values = new Object[]{
				contactId
		};
		try {
			contact = libernate.getEntityCustomized(SiteContacts.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据联系人ID获取联系人详细信息出错！",e);
		}
		if(contact!=null){
			contact = (SiteContacts)ObjectUtil.parseChar(contact, "contactName","contactDesc");
		}
		return contact;
	}

	/**
	 * 修改联系人
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Contacts updateContactInfo(Contacts contact, UserBase currentUser) {
		Contacts contactInfo = new Contacts();
		if(contact != null && contact.getId() != null && contact.getId().intValue() > 0){
			//			if(!contactLogic.createContactSingleValidate(contact)){
			//				logger.error("修改（单个）联系人验证前台表单数据出错！");
			//				return contactInfo;
			//			}
			contact = (Contacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");
			try {
				contactInfo = libernate.updateEntity(contact, "id", "contact_name_en", "contact_name", "contact_phone", "contact_mobile", "contact_email", "contact_desc");
			} catch (Exception e) {
				logger.error("修改联系人出错！",e);
			}
		}
		return contactInfo;
	}

	/**
	 * 修改企业联系人
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public SiteContacts updateSiteContactInfo(SiteContacts contact, UserBase currentUser) {
		SiteContacts contactInfo = new SiteContacts();
		if(contact != null && contact.getId() != null && contact.getId().intValue() > 0){
			//			if(!contactLogic.createContactSingleValidate(contact)){
			//				logger.error("修改（单个）联系人验证前台表单数据出错！");
			//				return contactInfo;
			//			}
			contact = (SiteContacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");
			try {
				contactInfo = libernate.updateEntity(contact, "id", "contact_name_en", "contact_name", "contact_phone", "contact_mobile", "contact_email", "contact_desc");
			} catch (Exception e) {
				logger.error("修改联系人出错！",e);
			}
		}
		return contactInfo;
	}

	/**
	 * 根据联系人ID删除联系人
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public boolean deleteContactInfo(Integer contactId, UserBase currentUser) {
		boolean deleteFlag = false;
		Contacts contact = new Contacts();
		try {
			if(contactId != null && contactId.intValue() > 0){
				Contacts contactInfo = new Contacts();
				contactInfo.setDelFlag(ConstantUtil.DELFLAG_DELETED);
				contactInfo.setDelTime(DateUtil.getGmtDate(null));
				contactInfo.setDelUser(currentUser.getId());
				contactInfo.setId(contactId);
				contact = libernate.updateEntity(contactInfo, "id", "delFlag", "delTime", "delUser");
			}
		} catch (Exception e) {
			logger.error("根据联系人ID删除联系人出错！",e);
		}
		if(contact != null && contact.getId() != null && contact.getId().intValue() > 0){
			deleteFlag = true;
		}
		return deleteFlag;
	}

	/**
	 * 根据联系人ID删除联系人
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public boolean deleteSiteContactInfo(Integer contactId, UserBase currentUser) {
		boolean deleteFlag = false;
		SiteContacts contact = new SiteContacts();
		try {
			if(contactId != null && contactId.intValue() > 0){
				SiteContacts contactInfo = new SiteContacts();
				contactInfo.setDelFlag(ConstantUtil.DELFLAG_DELETED);
				contactInfo.setDelTime(DateUtil.getGmtDate(null));
				contactInfo.setDelUser(currentUser.getId());
				contactInfo.setId(contactId);
				contact = libernate.updateEntity(contactInfo, "id", "delFlag", "delTime", "delUser");
			}
		} catch (Exception e) {
			logger.error("根据联系人ID删除联系人出错！",e);
		}
		if(contact != null && contact.getId() != null && contact.getId().intValue() > 0){
			deleteFlag = true;
		}
		return deleteFlag;
	}

	/**
	 * 批量删除联系人
	 * wangyong
	 * 2013-5-2
	 */
	@Override
	public boolean deleteContactInfoBatch(String[] ids, UserBase currentUser){
		boolean deleteFlag = true;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("update t_site_contacts t set t.del_flag=?,t.del_time=?,t.del_user=? where t.id in (0");
		valueList.add(ConstantUtil.DELFLAG_DELETED);
		valueList.add(DateUtil.getDateStrCompact(DateUtil.getGmtDate(null), "yyyy-MM-dd HH:mm:ss"));
		valueList.add(currentUser.getId());
		for(String id:ids){
			sqlBuilder.append(",");
			sqlBuilder.append(" ? ");
			valueList.add(id);
		}
		sqlBuilder.append(")");
		try {
			deleteFlag = libernate.executeSql(sqlBuilder.toString(), valueList.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			deleteFlag = false;
		}
		return deleteFlag;
	}

	/**
	 * 根据邮箱地址获取联系人详细信息
	 * wangyong
	 * 2013-3-11
	 */
	@Override
	public Contacts getContactByEmail(String email,UserBase currentUser) {
		Contacts contact = new Contacts();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_contacts WHERE del_flag !=? and contact_email = ? and user_id = ?");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = email;
		values[2] = currentUser.getId();
		try {
			contact = libernate.getEntityCustomized(Contacts.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
		}

		if(contact!=null){
			contact = (Contacts)ObjectUtil.parseChar(contact, "contactName","contactDesc");
		}
		return contact;
	}

	/**
	 * 根据邮箱地址获取联系人详细信息
	 */
	@Override
	public SiteContacts getSiteContactByEmail(String email,UserBase currentUser) {
		SiteContacts contact = new SiteContacts();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_site_contacts WHERE del_flag !=? and contact_email = ? and site_id = ?");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = email;
		values[2] = currentUser.getSiteId();
		try {
			contact = libernate.getEntityCustomized(SiteContacts.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据邮箱地址获取联系人详细信息出错！",e);
		}

		if(contact!=null){
			contact = (SiteContacts)ObjectUtil.parseChar(contact, "contactName","contactDesc");
		}
		return contact;
	}
	
	
	/**
	 * 从Excel文件批量导入联系人
	 * wangyong
	 * 2013-3-12
	 */
	@Override
	public boolean importBatchByExcel(List<Contacts> importusers, UserBase currentUser){
		boolean importFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO t_contacts ");
		sqlBuilder.append(" ( user_id, site_id, group_id, group_sort, contact_id, contact_name_en, contact_name, contact_email, ");
		sqlBuilder.append("   contact_phone, contact_mobile, contact_desc, create_time, create_user, del_flag, del_time, del_user) ");
		sqlBuilder.append(" VALUES ");
		int userSize = importusers.size();
		for(int i=1;i<userSize+1;i++){
			if(i == userSize){
				sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			}else{
				sqlBuilder.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
			}
		}
		valueList = getContactsValues(importusers, currentUser);
		try {
			Object[] values = valueList.toArray();
			importFlag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从Excel文件批量导入联系人出错！",e);
			importFlag = false;
		}
		return importFlag;
	}

	/**
	 * 从企业通讯录批量导入联系人
	 * wangyong
	 * 2013-3-12
	 */
	@Override
	public boolean importBatchByContacts(Integer[] ids, UserBase currentUser){
		boolean importFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO t_contacts ");
		sqlBuilder.append(" ( user_id, site_id, group_id, group_sort, contact_id, contact_name_en, contact_name, contact_email, ");
		sqlBuilder.append("   contact_phone, contact_mobile, contact_desc, create_time, create_user, del_flag, del_time, del_user) ");
		sqlBuilder.append(" SELECT ?, ?, ?, ?, id, contact_name_en, contact_name, contact_email, contact_phone, contact_mobile, contact_desc, ?, ?, ?, ?, ? FROM t_site_contacts WHERE 1=1 AND id IN( ");
		sqlBuilder.append(" ");
		if(currentUser != null && currentUser.getId() != null){
			valueList.add(currentUser.getId());
		}else{
			valueList.add(0);
		}
		if(currentUser != null && currentUser.getSiteId() != null){
			valueList.add(currentUser.getSiteId());
		}else{
			valueList.add(0);
		}
		valueList.add(0);
		valueList.add(0);


		valueList.add(DateUtil.getGmtDate(null));
		valueList.add(currentUser.getId());
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		try {
			valueList.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		valueList.add(0);

		int userSize = ids.length;
		for(int i=1;i<userSize+1;i++){
			if(i == userSize){
				sqlBuilder.append(" ? )");
			}else{
				sqlBuilder.append(" ?, ");
			}
		}
		for(int id:ids){
			valueList.add(id);
		}
		try {
			Object[] values = valueList.toArray();
			importFlag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			logger.error("从企业通讯录批量导入联系人出错！",e);
			importFlag = false;
		}
		return importFlag;
	}

	/**
	 * 获取从Excel文件批量导入联系人属性值
	 * wangyong
	 * 2013-3-12
	 */
	private List<Object> getContactsValues(List<Contacts> importusers, UserBase currentUser){
		List<Object> valueList = new ArrayList<Object>();
		for(Contacts contact:importusers){
			contact.initContact();
			contact.setCreateUser(currentUser.getId());
			if(currentUser != null && currentUser.getId() != null){
				valueList.add(currentUser.getId());
			}else{
				valueList.add(0);
			}
			if(currentUser != null && currentUser.getSiteId() != null){
				valueList.add(currentUser.getSiteId());
			}else{
				valueList.add(0);
			}
			valueList.add(contact.getGroupId());
			valueList.add(contact.getGroupSort());
			valueList.add(contact.getContactId());
			valueList.add(contact.getContactNameEn());
			valueList.add(contact.getContactName());
			valueList.add(contact.getContactEmail());
			valueList.add(contact.getContactPhone());
			valueList.add(contact.getContactMobile());
			valueList.add(contact.getContactDesc());
			valueList.add(contact.getCreateTime());
			if(currentUser != null && currentUser.getId() != null){
				valueList.add(currentUser.getId());
			}else{
				valueList.add(0);
			}
			valueList.add(contact.getDelFlag());
			valueList.add(contact.getDelTime());
			valueList.add(contact.getDelUser());
		}
		return valueList;
	}

	/**
	 * 获取排序参数
	 * wangyong
	 * 2013-1-22
	 */
	private String initSort(String field){
		String sortField = SortConstant.CONTACTS_FIELDS[0][1];
		for (String[] eachField : SortConstant.CONTACTS_FIELDS) {
			if (eachField != null && eachField[0].equals(field)) {
				sortField = BeanUtil.att2Field(eachField[1]);
				break;
			}
		}
		return sortField;
	}

	/**
	 *  查询企业联系人
	 */
	@Override
	public PageBean<UserBase> getEnterpriseContacts(String keyword,Integer pageNo,boolean isSupper,
			boolean showAllFlag,UserBase currUser, String orgCode) {
		List<Object> values = new ArrayList<Object>();//不同站点的管理员ID必然不同
		String sql = "";
		if(isSupper){
			sql = "select * from t_user_base t where " ;
			//" not EXISTS(SELECT t1.id from t_contacts t1 where t1.user_id=? and t1.contact_email=t.user_email)" +
			if(showAllFlag){
				sql += " not EXISTS(SELECT t1.id from t_contacts t1 where t1.user_id=? and t1.del_flag != ? and t1.contact_email=t.user_email)  and ";
				values.add(currUser.getId());
				values.add(ConstantUtil.DELFLAG_DELETED);
				//sql += " and ";
			}
			sql +=  " t.del_flag != ? and t.site_id = ? and t.id !=? ";
			values.add(ConstantUtil.DELFLAG_DELETED); 
			values.add(currUser.getSiteId());
			values.add(currUser.getId());
		}else{
			sql = "select * from t_user_base t where" +
					//" not EXISTS(SELECT t1.id from t_contacts t1 where t1.user_id=? and t1.contact_email=t.user_email)" +
					"  t.del_flag != ? and t.create_user = ? and t.id !=? ";
			//values.add(currUser.getId());
			values.add(ConstantUtil.DELFLAG_DELETED); 
			values.add(currUser.getCreateUser());
			values.add(currUser.getId());
		}
		sql+= " and t.user_type = ? ";
		values.add(ConstantUtil.USERTYPE_USERS);
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sql += " and (t.mobile like ? or t.true_name like ? or t.user_email like ? or t.phone like ? or t.en_name like ?)";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		if(StringUtil.isNotBlank(orgCode)){
			sql += " and t.org_code like ? ";
			values.add(orgCode +"%");
		}
		PageBean<UserBase> pageModel = getPageBeans(UserBase.class, sql, pageNo, 
				currUser.getPageSize(), values.toArray(new Object[values.size()])); // 2013.6.24 因客户需求新加常量，部分每页展示用户偏好设置每页显示条数
		return pageModel;
	}

	/**
	 *  查询企业联系人
	 */
	@Override
	public PageBean<SiteContacts> getSiteEnterpriseContacts(String keyword,Integer pageNo, UserBase currUser, Integer userId) {
		String sql = "";
		List<Object> values = new ArrayList<Object>();
		sql = "select * from t_site_contacts t where t.del_flag != ? and t.site_id = ? " +
		"and not exists (select 1 from t_contacts c where t.contact_email=c.contact_email and t.contact_phone=c.contact_phone and t.contact_mobile=c.contact_mobile and t.del_flag=c.del_flag)  ";
		values.add(ConstantUtil.DELFLAG_DELETED); 
		values.add(currUser.getSiteId());	
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sql += " and (t.contact_mobile like ? or t.contact_name like ? or t.contact_email like ? or t.contact_phone like ? or t.contact_name_en like ?)";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		PageBean<SiteContacts> pageModel = getPageBeans(SiteContacts.class, sql, pageNo, currUser.getPageSize()
				, values.toArray(new Object[values.size()])); // 2013.6.24 因客户需求新加常量，部分每页展示用户偏好设置每页显示条数
		return pageModel;
	}

	@Override
	public PageBean<Contacts> getContactsPage(String keyword, Integer siteId,
			Integer userId, Integer pageNo, Integer pageSize,String sortord) {

		String sql = "select * from t_contacts where del_flag !=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(ConstantUtil.DELFLAG_DELETED);
		if(siteId>0){
			sql += " and  site_id = ? ";
			values.add(siteId);
		}
		if(userId>0){
			sql += " and  user_id = ? ";
			values.add(userId);
		}
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sql += " and  (contact_name like ? or contact_email like ? or contact_phone like ? or contact_mobile like ? )";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
//		sql += " order by contact_name asc ";
		sql += " order by contact_name ";
		if(sortord!=null && SortConstant.SORT_DESC.equals(sortord)){
			sql += "desc";
		}else{
			sql += "asc";
		}
		
		PageBean<Contacts> pageModel = getPageBeans(Contacts.class, sql, pageNo,pageSize,values.toArray(new Object[values.size()]));
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseCharWithList(pageModel.getDatas(), "contactName","contactDesc"));
		}

		return pageModel;
	}

	@Override
	public PageBean<Contacts> getSiteContactsPage(String keyword, Integer siteId, 
			Integer pageNo, Integer pageSize) {

		String sql = "select * from t_site_contacts where del_flag !=? ";
		List<Object> values = new ArrayList<Object>();
		values.add(ConstantUtil.DELFLAG_DELETED);
		if(siteId>0){
			sql += " and  site_id = ? ";
			values.add(siteId);
		}
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sql += " and  (contact_name like ? or contact_email like ? or contact_phone like ? or contact_mobile like ? )";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		sql += " order by contact_name asc ";
		PageBean<Contacts> pageModel = getPageBeans(Contacts.class, sql, pageNo,pageSize,values.toArray(new Object[values.size()]));
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseCharWithList(pageModel.getDatas(), "contactName","contactDesc"));
		}
		return pageModel;
	}


	/**
	 * 导出联系人
	 */
	@Override
	public List<Contacts> exportContacts(String keyword, String[] cIds,Integer siteId, Integer userId) {
		List<Contacts> contacts = new ArrayList<Contacts>();
		StringBuffer sqlBuffer=new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sqlBuffer.append(" select * from t_contacts where del_flag !=? ");
		values.add(ConstantUtil.DELFLAG_DELETED);

		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append("");

		if(siteId>0){
			sqlBuffer.append("   and  site_id = ? ");
			values.add(siteId);
		}
		if(userId>0){
			sqlBuffer.append("   and  user_id = ? ");
			values.add(userId);
		}
		if (cIds != null && cIds.length > 0) {

			sqlBuffer.append("  and (  id=? ");
			values.add(0);
			for(String cId:cIds){
				if(!StringUtil.isEmpty(cId) && NumberUtil.isInteger(cId)){
					sqlBuffer.append("  or id = ?  ");
					values.add(NumberUtil.parseInteger(cId));
				}
			}
			sqlBuffer.append(" ) ");
		}
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sqlBuffer.append("  and  (contact_name like ? or contact_email like ? or contact_phone like ? or contact_mobile like ? )");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		sqlBuffer.append(" order by create_time desc ");

		try {
			contacts = libernate.getEntityListCustomized(Contacts.class, sqlBuffer.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}



	/**
	 * 批量添加联系人
	 */
	@Override
	public boolean importContactBatch(List<Contacts> contacts) {
		try {
			for (Iterator<Contacts> iterator = contacts.iterator(); iterator.hasNext();) {
				Contacts contact = iterator.next();
				contact = (Contacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");

				libernate.saveEntity(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 批量添加企业的联系人
	 */
	@Override
	public boolean importSiteContactBatch(List<SiteContacts> contacts) {
		try {
			for (Iterator<SiteContacts> iterator = contacts.iterator(); iterator.hasNext();) {
				SiteContacts contact = iterator.next();
				contact = (SiteContacts)ObjectUtil.parseHtml(contact, "contactName","contactDesc");

				libernate.saveEntity(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean enterUserIsExist(int enterUserId, int currentUserId) {
		String sql = "select count(*) from t_contacts t,t_user_base t1 where t.user_id = ? and t.del_flag !=? and t.contact_email = t1.user_email and t1.id = ?";
		Object[] values = new Object[]{
				currentUserId,
				ConstantUtil.DELFLAG_DELETED,
				enterUserId
		};
		try {
			int count = libernate.countEntityListWithSql(sql, values);
			if(count>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public Map<String, Integer[]> getCanSaveableIds(String[] ids, int currentUserId) {
		Map<String, Integer[]> iddatas = new HashMap<String,Integer[]>();
		List<Integer> saveidlist = new ArrayList<Integer>();
		List<Integer> repeatidlist = new ArrayList<Integer>();
		for (int i = 0; i < ids.length; i++) {
			int id = Integer.parseInt(ids[i]);
			if(!enterUserIsExist(id,currentUserId)){
				saveidlist.add(id);
			}else{
				repeatidlist.add(id);
			}
		}
		iddatas.put("saveable", saveidlist.toArray(new Integer[saveidlist.size()]));
		iddatas.put("unsaveable", repeatidlist.toArray(new Integer[repeatidlist.size()]));

		return iddatas;
	}



	@Override
	public PageBean<UserBase> getEnterpriseUsers(Integer siteId,
			String keyword, int pageNo, int pageSize, boolean isExactQuery) {

		List<Object> values = new ArrayList<Object>();
		String sql = "";
		sql = "select * from t_user_base t where t.del_flag != ? and t.site_id = ? and t.user_type = ?" ;
		values.add(ConstantUtil.DELFLAG_DELETED); 
		values.add(siteId);
		values.add(ConstantUtil.USERTYPE_USERS);
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			if(!isExactQuery){
				sql += " and (t.mobile like ? or t.true_name like ? or t.user_email like ? or t.phone like ? or t.en_name like ?)";
				values.add("%"+ keyword +"%");
				values.add("%"+ keyword +"%");
				values.add("%"+ keyword +"%");
				values.add("%"+ keyword +"%");
				values.add("%"+ keyword +"%");
			}else{
				sql += " and t.true_name = ? ";
				values.add(keyword);
			}
		}
		PageBean<UserBase> pageModel = getPageBeans(UserBase.class, sql, pageNo,pageSize,values.toArray(new Object[values.size()]));
		return pageModel;
	}

	//判断联系人是否已存在
	public boolean emailContactStored(String email,UserBase currentUser){
		StringBuffer strSql = new StringBuffer(" SELECT count(*) FROM t_contacts WHERE del_flag !=? and contact_email = ? and user_id = ?");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = email;
		values[2] = currentUser.getId();
		try {
			int count = libernate.countEntityListWithSql(strSql.toString(), values);
			return count>0;
		} catch (SQLException e) {
			logger.error("emailContactStored method sql exception",e);
		}
		return false;
	}


	@Override
	public boolean telContactStored(String telphone, UserBase currentUser) {

		StringBuffer strSql = new StringBuffer(" SELECT count(*) FROM t_contacts WHERE del_flag !=? and " +
				"(contact_phone = ? or contact_mobile = ?) and user_id = ?");
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = telphone;
		values[2] = telphone;
		values[3] = currentUser.getId();
		try {
			int count = libernate.countEntityListWithSql(strSql.toString(), values);
			return count>0;
		} catch (SQLException e) {
			logger.error("telContactStored method exception ",e);
		}
		return false;
	}


	public Contacts saveContact(Contacts con){
		try {
			return libernate.saveEntity(con);
		} catch (Exception e) {
			logger.error("telContactStored method exception ",e);
		}
		return null;
	}



	@Override
	public boolean saveSiteContactOfUserBase(UserBase userBase) {
		SiteContacts scontact = new SiteContacts();
		scontact.setContactEmail(userBase.getUserEmail());
		scontact.setContactMobile(userBase.getMobile());
		scontact.setContactNameEn(userBase.getEnName());
		scontact.setContactPhone(userBase.getPhone());
		scontact.setContactName(userBase.getTrueName());
		scontact.setSiteId(userBase.getSiteId());
		scontact.setContactDesc(userBase.getRemark());
		scontact.setCreateUser(userBase.getId());

		try {
			libernate.saveEntity(scontact);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public boolean updateContactEmail(int id, String email) {
		try{
			Contacts  contact = libernate.getEntity(Contacts.class, id);
			if(contact!=null){
				contact.setContactEmail(email);
				libernate.updateEntity(contact, "contactEmail");
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}



	@Override
	public boolean updateContactPhone(int id, String phone) {
		try{
			if(StringUtil.isEmpty(phone)) return false;
			phone = phone.trim();
			
			Contacts  contact = libernate.getEntity(Contacts.class, id);
			if(contact!=null && !phone.equals(contact.getContactMobile())&& !phone.equals(contact.getContactPhone()) ){
				if(StringUtil.isEmpty(contact.getContactMobile()) && !StringUtil.isEmpty(contact.getContactPhone())){
					contact.setContactPhone(phone);
				}else if(StringUtil.isEmpty(contact.getContactMobile()) && !StringUtil.isEmpty(contact.getContactPhone())){
					contact.setContactMobile(phone);
				}else{
					contact.setContactMobile(phone);
				}
				libernate.updateEntity(contact, "contactPhone","contactMobile");
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	
	
	@Override
	public boolean siteContactEmailExist(int siteId, String email) {
		String  sql = "select * from  t_site_contacts where  del_flag= ? and site_id = ?  and contact_email = ?";
		if(siteId<1) return false;
		if(StringUtil.isEmpty(email)) return false;
		try {
			SiteContacts sc = libernate.getEntityCustomized(SiteContacts.class, sql, new Object[]{
				ConstantUtil.DELFLAG_UNDELETE,siteId,email.trim()
			});
			if(sc!=null) return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public boolean siteContactPhoneExist(int siteId, String phone) {
		if(siteId<1) return false;
		if(StringUtil.isEmpty(phone)) return false;
		String  sql = "select * from  t_site_contacts where  del_flag= ? and site_id = ?  and contact_phone = ?";
		try {
			SiteContacts sc = libernate.getEntityCustomized(SiteContacts.class, sql, new Object[]{
				ConstantUtil.DELFLAG_UNDELETE,siteId,phone.trim()
			});
			if(sc!=null) return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
