package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ContactGroup;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ContactGroupService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 
 * @author Administrator
 * @date 2013-3-1
 */
@Service
public class ContactGroupServiceImpl extends BaseService implements ContactGroupService{

	@Override
	public PageBean<ContactGroup> getGroupPageModel(String keyword, Integer siteId,Integer userId,
			Integer pageNo, Integer pageSize) {
		String sql = "select * from t_contact_group where del_flag !=? ";
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
			sql += " and  group_name like ? ";
			values.add("%"+ keyword +"%");
		}
		sql += " order by create_time desc";
		PageBean<ContactGroup> pageModel = getPageBeans(ContactGroup.class, sql, pageNo,pageSize,values.toArray(new Object[values.size()]));
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseHtmlWithList(pageModel.getDatas(), "groupName","groupDesc"));
		}
		
		return pageModel;
	}
	
	@Override
	public boolean saveOrUpdateGroup(ContactGroup group) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try{
			group = (ContactGroup)ObjectUtil.parseChar(group, "groupName","groupDesc");
			if(group.getId()!=null && group.getId()>0){
				libernate.updateEntity(group,"groupName","groupDesc");
			}else{
				libernate.saveEntity(group);
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	
	@Override
	public boolean deleteGroupById(Integer id,UserBase deler) {
		boolean deleteFlag = true;
		StringBuilder sqlBuilder = new StringBuilder("update t_contact_group t set t.del_flag=?,t.del_time=?,t.del_user=? where t.id = ? ");
		
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = DateUtil.getDateStrCompact(DateUtil.getGmtDate(null), "yyyy-MM-dd HH:mm:ss");
		values[2] = deler.getId();
		values[3] = id;
		String sql = "update t_contacts t set t.group_id=0 where t.group_id=?";
		try {
			deleteFlag = libernate.executeSql(sqlBuilder.toString(), values);
			//更新联系人群组信息
			deleteFlag = libernate.executeSql(sql, new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
			deleteFlag = false;
		}
		return deleteFlag;
	}
	
	@Override
	public ContactGroup getGroupById(Integer id) {
		ContactGroup group = null;
		// TODO Auto-generated method stub
		try {
			group =  libernate.getEntity(ContactGroup.class, id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(group!=null){
			group = (ContactGroup)ObjectUtil.parseHtml(group, "groupName","groupDesc");
		}
		return group;
	}

	@Override
	public PageBean<Contacts> getImorpContectsList(Integer siteId, UserBase currUser ,Integer group_id,String keyword,
			Integer pageNo) {
		// TODO Auto-generated method stub
		String sql = "select * from t_contacts where del_flag != ? and group_id =?  ";
		List<Object> values = new ArrayList<Object>();
		values.add(ConstantUtil.DELFLAG_DELETED);
		values.add(0);
		if(siteId>0){
			sql += " and  site_id = ? ";
			values.add(siteId);
		}
		if(currUser != null && currUser.getId() != null && currUser.getId().intValue() > 0){
			sql += " and user_id = ? ";
			values.add(currUser.getId());
		}
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();//or contact_name_en like ?
			keyword = StringUtil.escapeStr(keyword);
			sql += " and  (contact_name like ? or contact_mobile like ?  or contact_email like ? or  contact_phone like ?) ";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		PageBean<Contacts> pageModel = getPageBeans(Contacts.class, sql, pageNo, currUser.getPageSize(), values.toArray(new Object[values.size()]));
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseHtmlWithList(pageModel.getDatas(), "contactName","contactDesc"));
		}
		return pageModel;
	}
	
	/**
	 * 批量添加联系人到某个群组
	 */
	@Override
	public boolean addContactsToGroup(String[] contactIds, Integer groupId) {
		boolean flag = true;
		if(contactIds==null){
			return false;
		}
		StringBuilder sqlBuilder = new StringBuilder("update t_contacts t set t.group_id=? where t.id in (0");
		for(String id:contactIds){
			sqlBuilder.append(",");
			sqlBuilder.append(id);
		}
		sqlBuilder.append(")");
		Object[] values = new Object[1];
		values[0] = groupId;
		try {
			flag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	
	@Override
	public boolean delContactFromGroup(Integer contactId) {
		boolean flag = true;
		StringBuilder sqlBuilder = new StringBuilder("update t_contacts t set t.group_id=0 where t.id = ? ");
		 
		Object[] values = new Object[1];
		values[0] = contactId;
		try {
			flag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public PageBean<Contacts> getContactsByGroup(Integer siteId,
			UserBase currUser, Integer groupId, String keyword, Integer pageNo) {
		List<Object> values = new ArrayList<Object>();
		String sql = "select * from t_contacts where del_flag != ? and group_id = ? ";
		values.add(ConstantUtil.DELFLAG_DELETED);
		values.add(groupId);
		if(siteId>0){
			sql += " and  site_id = ? ";
			values.add(siteId);
		}
		if(currUser != null && currUser.getId() != null && currUser.getId().intValue() > 0){
			sql += " and user_id = ? ";
			values.add(currUser.getId());
		}
		if(keyword!=null && !keyword.trim().equals("")){
			keyword = keyword.trim();
			keyword = StringUtil.escapeStr(keyword);
			sql += " and  (contact_name like ? or contact_mobile like ? or contact_name_en like ? or contact_email like ? or contact_phone like ?) ";
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
			values.add("%"+ keyword +"%");
		}
		PageBean<Contacts> pageModel = getPageBeans(Contacts.class, sql, pageNo, currUser.getPageSize(), values.toArray(new Object[values.size()]));
		if(pageModel!=null && pageModel.getDatas()!=null){
			pageModel.setDatas(ObjectUtil.parseHtmlWithList(pageModel.getDatas(), "contactName","contactDesc"));
		}
		return pageModel;
	}
	
	
	@Override
	public List<Contacts> getContactsByGroups(Integer siteId,
			Integer userId, String[] ids) {
		List<Contacts> contacts= null;
		List<Object> values = new ArrayList<Object>();
		String sql = "select * from t_contacts where del_flag != ? and group_id in( ";
		String gids = "";
		for(String id:ids){
			if(!id.trim().equals("")){
				gids += id;
				gids += ",";
			}
		}
		if(gids.endsWith(",")){
			gids = gids.substring(0, gids.length()-1);
		}else{
			return contacts;
		}
		sql += gids;
		sql += ")";
		
		values.add(ConstantUtil.DELFLAG_DELETED);
		if(siteId>0){
			sql += " and  site_id = ? ";
			values.add(siteId);
		}
		if(userId>0){
			sql += " and user_id = ? ";
			values.add(userId);
		}
		//System.out.println("sql == " +sql);
		try {
			
			contacts = libernate.getEntityListBase(Contacts.class,sql,values.toArray());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(contacts!=null){
			contacts = ObjectUtil.parseHtmlWithList(contacts, "contactName","contactDesc");
		}
		return contacts;
	}
	
	/**
	 * 获取群组的人数
	 */
	@Override
	public int getGroupMemberNum(int group_id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select count(*) from t_contacts where del_flag != ? and group_id =?";
			Object[] objs = new Object[] {
				ConstantUtil.DELFLAG_DELETED,
				group_id
			};
			return libernate.countEntityListWithSql(sql, objs);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
}
