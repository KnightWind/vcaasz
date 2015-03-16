package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.OrgUserCount;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteOrg;
import com.bizconf.vcaasz.entity.UserBase;

public interface OrgService {
	
	/**
	 * 查询站点下的组织机构
	 * 1.可根据组织机构名称模糊查询
	 * @param siteId 站点ID
	 * @param orgName 组织机构名称
	 * @param pageNo 页数
	 * @param sortField 排序字段
	 * @param sortord 排序方式
	 */
	public PageBean<SiteOrg> getSiteOrgList(int siteId, String orgName, String pageNo, String sortField, String sortord);
	
	/**
	 * 查询站点下的组织机构
	 * 1.查出该组织机构以及下属组织机构
	 * 2.有分页的
	 */ 
	public PageBean<SiteOrg> getSiteSubOrgList(int siteId, int orgId, String orgName,
			String pageNo, String sortField, String sortord);
	
	/**
	 * 查询站点下的组织机构
	 * 1.查出该组织机构以及下属组织机构
	 * 2.无分页的
	 */ 
	public PageBean<SiteOrg> getSiteSubOrgList(int siteId, int orgId);
	
	/**
	 * 查询站点下的组织机构
	 * 1.查出该组织机构的下一级组织机构（不包括自己）
	 */ 
	public PageBean<SiteOrg> getSubOrgOnlyList(int siteId, int orgId);
	
	/**
	 * 查询站点下的所有组织机构(无分页)
	 */ 
	public PageBean<SiteOrg> getSiteOrgList(int siteId);
	
	/**
	 * 获取未分配组织机构的用户列表
	 * @param 
	 */
	public PageBean<UserBase> getNoOrgUserList(String orgName, String sortField,
			String sortord, Integer siteId,Integer creator, String pageNo, int pageSize, List<SiteOrg> orgList);
	
	/**
	 * 获取选定组织机构及下属组织机构的用户列表
	 * 1.邀请用户参会功能中，选择组织机构时调用
	 * @param 
	 */
	public PageBean<UserBase> getOrgSubUserList(String orgName, String sortField,
			String sortord, Integer siteId, String pageNo, int pageSize, List<SiteOrg> orgList);
	
	/**
	 * 获取选定组织机构及下属组织机构的用户列表(所有用户，无分页)
	 * 1.删除组织机构后，关联移除用户时调用
	 * @param 
	 */
	public PageBean<UserBase> getOrgSubUserList(Integer siteId, List<SiteOrg> orgList);
	
	/**
	 * 为用户批量分配机构
	 */
	public boolean updateOrgUserBatch(String orgId, String[] ids, UserBase currentUser);
	
	/**
	 * 新增站点下的组织机构
	 *  
	 */
	public SiteOrg addSiteOrg(SiteOrg siteOrg, UserBase currentSiteAdmin);
	
	/**
	 * 修改站点下的组织机构 
	 */
	public SiteOrg updateSiteOrg(SiteOrg siteOrg);
	
	/**
	 * 删除站点下的组织机构 
	 */
	public boolean deleteSiteOrg(int orgId, UserBase currentSiteAdmin);
	
	/**
	 * 组织机构详细信息 
	 */
	public SiteOrg getSiteOrgById(int orgId);
	
	/**
	 * 通过机构名称查询组织机构详细信息 
	 * 1.同一级别下查询
	 */
	public SiteOrg getSiteOrgByName(String orgName, int parentId, int siteId);
	
	/**
	 * 根据机构ID获取机构人员(包括下属机构人员)
	 * wangyong
	 * 2013-5-7
	 */
	public List<UserBase> getOrgUserList(SiteOrg siteOrg);
	
	public List<OrgUserCount> getUserCountListBySiteId(Integer siteId);
	
	/**
	 * 从组织机构中移除用户
	 * wangyong
	 * 2013-5-22
	 */
	public boolean delUserFromOrg(int userId);
	
	/**
	 * 从组织机构中批量移除用户
	 * wangyong
	 * 2013-5-22
	 */
	public boolean delUserFromOrgBatch(String[] ids);
}
