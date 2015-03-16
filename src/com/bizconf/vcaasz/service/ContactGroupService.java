package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.ContactGroup;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.UserBase;

/**
 * @desc 
 * @author martin
 * @date 2013-3-1
 */
public interface ContactGroupService {
	
	/**
	 * 
	 * @param keyword 输入的查询关键字
	 * @param pageNo 页码
	 * @param pageSize 每一页的现实条数
	 * @return
	 */
	PageBean<ContactGroup> getGroupPageModel(String keyword, Integer siteId,Integer userId,Integer pageNo,Integer pageSize);
	
	/**
	 * 保存或者修改联系人群组
	 * @param group
	 * @return
	 */
	boolean saveOrUpdateGroup(ContactGroup group);
	
	/**
	 * 根据群组ID删除群组
	 * @param id
	 * @return
	 */
	boolean deleteGroupById(Integer id,UserBase deler);
	
	
	/**
	 * 根据ID查询群组
	 * @return
	 */
	ContactGroup getGroupById(Integer id);
	
	/**
	 * 获取添加群组的联系人列表
	 * @param siteId
	 * @return
	 */
	PageBean<Contacts> getImorpContectsList(Integer siteId, UserBase currUser, Integer group_id,String keyword,Integer pageNo);
	
	/**
	 * 查询某群组下的联系人
	 * @param siteId
	 * @param userId
	 * @param groupId
	 * @param keyword
	 * @param pageNo
	 * @return
	 */
	PageBean<Contacts> getContactsByGroup(Integer siteId, UserBase currUser, Integer groupId,String keyword,Integer pageNo);
	
	/**
	 * 添加联系人到某一群组
	 * @param contactIds
	 * @param groupId
	 * @return
	 */
	boolean addContactsToGroup(String[] contactIds,Integer groupId);
	
	/**
	 * 从群组删除一个联系人
	 * @param contactId
	 * @param groupId
	 * @return
	 */
	boolean delContactFromGroup(Integer contactId);

	/**
	 * 查询群组ID查询联系人 
	 * @param siteId
	 * @param userId
	 * @param ids
	 * @return
	 */
	List<Contacts> getContactsByGroups(Integer siteId, Integer userId,
			String[] ids);
	
	/**
	 * 查询该群组下游多少联系人
	 * @param group_id
	 * @return
	 */
	int getGroupMemberNum(int group_id);
}
