package com.bizconf.vcaasz.logic;

import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.SiteContacts;
import com.bizconf.vcaasz.entity.UserBase;

public interface ContactLogic {

	/**
	 * 修改联系人（单个）验证前台表单数据
	 * wangyong
	 * 2013-3-11
	 */
	public boolean updateContactSingleValidate(Contacts contact);
	
	/**
	 * 新建联系人（单个）验证前台表单数据
	 * wangyong
	 * 2013-3-11
	 */
	public boolean createContactSingleValidate(Contacts contact);
	
	/**
	 * 检测联系人邮件地址是否可用
	 * @param contact
	 * @return
	 */
	public boolean contactEmailAvailable(Contacts contact);
	
	/**
	 * 检测企业联系人邮件地址是否可用
	 * @param contact
	 * @return
	 */
	public boolean contactEmailAvailableSite(SiteContacts contact);

	public boolean createSiteContactSingleValidate(SiteContacts contact, UserBase currentUser);

	
}
