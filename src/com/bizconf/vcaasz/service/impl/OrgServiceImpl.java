package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SortConstant;
import com.bizconf.vcaasz.entity.OrgUserCount;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteOrg;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.OrgService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class OrgServiceImpl extends BaseService implements OrgService {

	@Override
	public PageBean<SiteOrg> getSiteOrgList(int siteId, String orgName,
			String pageNo, String sortField, String sortord) {
		if(pageNo==null || pageNo.equals("")){
			pageNo = "1";
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId > 0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		if(StringUtil.isNotBlank(orgName)){
			sqlBuilder.append("and org_name like ?");
			values.add("%"+ orgName +"%");
		}
		if(StringUtil.isNotBlank(sortField)){
			sqlBuilder.append(" order by " + initSort(sortField));
			if(SortConstant.SORT_ASC.equals(sortord)){
				sqlBuilder.append(" asc ");
			}else{
				sqlBuilder.append(" desc ");
			}
		}else{
			sqlBuilder.append(" order by org_code ASC  ");
		}
		return getPageBeans(SiteOrg.class, sqlBuilder.toString(), Integer.parseInt(pageNo), values.toArray(new Object[values.size()]));
	}
	
	@Override
	public PageBean<SiteOrg> getSiteSubOrgList(int siteId, int orgId, String orgName,
			String pageNo, String sortField, String sortord){
		SiteOrg org = getSiteOrgById(orgId);
		if(pageNo==null || pageNo.equals("")){
			pageNo = "1";
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId > 0) {
			sqlBuilder.append(" and site_id = ? ");
			values.add(siteId);
		}
		if(StringUtil.isNotBlank(orgName)){
			sqlBuilder.append("and org_name like ?");
			values.add("%"+ orgName +"%");
		}
		if(org != null && org.getId() > 0){
			sqlBuilder.append(" and org_code like ? ");
			values.add(org.getOrgCode() +"%");
		}
		if(StringUtil.isNotBlank(sortField)){
			sqlBuilder.append(" order by " + initSort(sortField));
			if(SortConstant.SORT_ASC.equals(sortord)){
				sqlBuilder.append(" asc ");
			}else{
				sqlBuilder.append(" desc ");
			}
		}else{
			sqlBuilder.append(" order by org_code ASC  ");
		}
		return getPageBeans(SiteOrg.class, sqlBuilder.toString(), Integer.parseInt(pageNo), values.toArray(new Object[values.size()]));
	}
	
	@Override
	public PageBean<SiteOrg> getSiteSubOrgList(int siteId, int orgId){
		SiteOrg org = getSiteOrgById(orgId);
		if(org == null){
			return null;
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId > 0) {
			sqlBuilder.append(" and site_id = ? ");
			values.add(siteId);
		}
		if(org != null && org.getId() > 0){
			sqlBuilder.append(" and org_code like ? ");
			values.add(org.getOrgCode() +"%");
		}
		sqlBuilder.append(" order by org_code ASC  ");
		return getPageBeans(SiteOrg.class, sqlBuilder.toString(), 1, 1000, values.toArray(new Object[values.size()]));
	}
	
	@Override
	public PageBean<SiteOrg> getSubOrgOnlyList(int siteId, int orgId){
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId > 0) {
			sqlBuilder.append(" and site_id = ? AND  parent_id = ? ");
			values.add(siteId);
			values.add(orgId);
		}
		sqlBuilder.append(" order by org_code ASC  ");
		return getPageBeans(SiteOrg.class, sqlBuilder.toString(), 1, 1000, values.toArray(new Object[values.size()]));
	}
	
	@Override
	public PageBean<SiteOrg> getSiteOrgList(int siteId) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId > 0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		sqlBuilder.append(" order by org_code ASC  ");
		return getPageBeans(SiteOrg.class, sqlBuilder.toString(), 1, 1000, values.toArray());
	}
	
	@Override
	public PageBean<UserBase> getOrgSubUserList(Integer siteId, List<SiteOrg> orgList){
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_user_base where del_flag = ? and user_type = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(ConstantUtil.USERTYPE_USERS);
		if (siteId != null && siteId > 0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		if(orgList != null && orgList.size() > 0){
			sqlBuilder.append(" and org_id in ( -1 ");
			for(SiteOrg org:orgList){
				sqlBuilder.append(",");
				sqlBuilder.append(" ? ");
				values.add(org.getId());
			}
			sqlBuilder.append(" ) ");
		}
		sqlBuilder.append(" order by id desc ");
		return getPageBeans(UserBase.class, sqlBuilder.toString(), 1, 1000, values.toArray());
	}
	
	@Override
	public PageBean<UserBase> getOrgSubUserList(String orgName, String sortField,
			String sortord, Integer siteId, String pageNo, int pageSize, List<SiteOrg> orgList){
		return getOrgUserList(orgName, sortField, sortord, siteId, 0, pageNo, pageSize, orgList, true);
	}
	
	@Override
	public PageBean<UserBase> getNoOrgUserList(String orgName, String sortField,
			String sortord, Integer siteId,Integer creator, String pageNo, int pageSize, List<SiteOrg> orgList) {
		return getOrgUserList(orgName, sortField, sortord, siteId, creator, pageNo, pageSize, orgList, false);
	}
	
	/**
	 * @param assignFlag 是否为用户分配过机构
	 * wangyong
	 * 2013-5-20
	 */
	private PageBean<UserBase> getOrgUserList(String orgName, String sortField,
			String sortord, Integer siteId,Integer creator, String pageNo, int pageSize, List<SiteOrg> orgList, boolean assignFlag) {
		if(pageNo==null || pageNo.equals("")){
			pageNo = "1";
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_user_base where del_flag = ? and user_type = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(ConstantUtil.USERTYPE_USERS);
		if (siteId != null && siteId > 0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		
		if(creator != null && creator.intValue()>0){
			sqlBuilder.append(" and create_user =? ");
			values.add(creator);
		}
		
		if(StringUtil.isNotBlank(orgName)){
			sqlBuilder.append("and (login_name like ? or true_name like ? or user_email like ?)");
			values.add("%"+ orgName +"%");
			values.add("%"+ orgName +"%");
			values.add("%"+ orgName +"%");
		}
		if(orgList != null && orgList.size() > 0){
			if(assignFlag){
				sqlBuilder.append(" and org_id in ( -1 ");
			}else{
				sqlBuilder.append(" and org_id not in ( -1 ");
			}
			for(SiteOrg org:orgList){
				sqlBuilder.append(",");
				sqlBuilder.append(" ? ");
				values.add(org.getId());
			}
			sqlBuilder.append(" ) ");
		}
		
		if(StringUtil.isNotBlank(sortField)){
			sqlBuilder.append(" order by "+initSort(sortField));
			if(SortConstant.SORT_ASC.equals(sortord)){
				sqlBuilder.append(" asc ");
			}else{
				sqlBuilder.append(" desc ");
			}
		}else{
			sqlBuilder.append(" order by id desc ");
		}
		PageBean<UserBase> pageModel = getPageBeans(UserBase.class, sqlBuilder.toString(), Integer.parseInt(pageNo), pageSize, values.toArray(new Object[values.size()]));
		return pageModel;
	}
	
	/**
	 * 保存新机构时调用
	 * 1.为新机构生成orgCode
	 * 2.为新机构生成orgLevel
	 * wangyong
	 * 2013-5-20
	 */
	private void setNewOrgCode(SiteOrg siteOrg){
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder(" select * from t_site_org where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteOrg != null) {
			sqlBuilder.append(" and site_id = ? AND parent_id = ? ORDER BY org_code DESC ");
			values.add(siteOrg.getSiteId());
			values.add(siteOrg.getParentId());
		}
		PageBean<SiteOrg> pageModel = getPageBeans(SiteOrg.class, sqlBuilder.toString(), 1, 1000, values.toArray(new Object[values.size()]));
		List<SiteOrg> orgList = pageModel.getDatas();
		if(orgList != null){  //当前级别有机构时，新建机构
			String oldOrgCode = orgList.get(0).getOrgCode();
			siteOrg.setOrgCode(String.valueOf(Long.parseLong(oldOrgCode) + 1));
			siteOrg.setOrgLevel(orgList.get(0).getOrgLevel());
		}else{     //当前级别无机构时，新建机构
			sqlBuilder.setLength(0);
			values.clear();
			sqlBuilder.append(" select * from t_site_org where del_flag = ? ");
			values.add(ConstantUtil.DELFLAG_UNDELETE);
			if (siteOrg != null) {
				sqlBuilder.append(" and site_id = ? AND id = ? ORDER BY org_code DESC ");
				values.add(siteOrg.getSiteId());
				values.add(siteOrg.getParentId());
			}
			PageBean<SiteOrg> page = getPageBeans(SiteOrg.class, sqlBuilder.toString(), 1, 1000, values.toArray(new Object[values.size()]));
			orgList = page.getDatas();
			if(orgList != null){
				String oldOrgCode = orgList.get(0).getOrgCode();
				siteOrg.setOrgCode(oldOrgCode + "0001");
				siteOrg.setOrgLevel(orgList.get(0).getOrgLevel().intValue() + 1);
			}else{    //此时为创建站点的根节点机构
				siteOrg.setOrgCode("1111");
				siteOrg.setOrgLevel(1);
			}
		}
	}

	@Override
	public SiteOrg addSiteOrg(SiteOrg siteOrg, UserBase currentSiteAdmin) {
		if(siteOrg == null){
			return null;
		}
		siteOrg.init(currentSiteAdmin, siteOrg.getParentId());
		setNewOrgCode(siteOrg);   //添加新机构时，生成新机构的code
		try {
			siteOrg = libernate.saveEntity(siteOrg);
			if(siteOrg != null && siteOrg.getId() > 0){
				return siteOrg;
			}
		} catch (Exception e) {
			logger.error("新增站点下的组织机构错误！" + e);
		}
		return null;
	}

	@Override
	public SiteOrg updateSiteOrg(SiteOrg siteOrg) {
		try {
			siteOrg = libernate.updateEntity(siteOrg, "orgName", "orgDesc");
			if(siteOrg != null && siteOrg.getId() > 0){
				return siteOrg;
			}
		} catch (Exception e) {
			logger.error("修改站点下的组织机构错误！" + e);
		}
		return null;
	}

	@Override
	public boolean deleteSiteOrg(int orgId, UserBase currentSiteAdmin) {
		List<SiteOrg> siteOrgList = getSiteSubOrgList(currentSiteAdmin.getSiteId(), orgId).getDatas();
		if(siteOrgList == null || siteOrgList.size() <= 0){
			logger.info("欲删除的机构不存在！");
			return false;
		}
		for(SiteOrg siteOrg : siteOrgList){
			siteOrg.setDelFlag(ConstantUtil.DELFLAG_DELETED);
			siteOrg.setDelTime(DateUtil.getGmtDate(null));
			siteOrg.setDelUser(currentSiteAdmin.getId());
			try {
				siteOrg = libernate.updateEntity(siteOrg, "delFlag", "delTime", "delUser");
			} catch (Exception e) {
				logger.error("删除站点下的组织机构错误！" + e);
				return false;
			}
		}
		return true;
	}

	@Override
	public SiteOrg getSiteOrgById(int orgId) {
		SiteOrg siteOrg = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_site_org WHERE del_flag = ? AND id = ? ");
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(orgId);
		try {
			siteOrg = libernate.getEntityCustomized(SiteOrg.class, strSql.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("根据组织机构ID号获取组织机构出错！",e);
		}
		return siteOrg;
	}
	
	@Override
	public SiteOrg getSiteOrgByName(String orgName, int parentId, int siteId) {
		SiteOrg siteOrg = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_site_org WHERE del_flag = ? AND org_name = ? and parent_id = ? and site_id = ? ");
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(orgName);
		valueList.add(parentId);
		valueList.add(siteId);
		try {
			siteOrg = libernate.getEntityCustomized(SiteOrg.class, strSql.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("根据组织机构ID号获取组织机构出错！",e);
		}
		return siteOrg;
	}
	
	@Override
	public List<UserBase> getOrgUserList(SiteOrg siteOrg) {
		List<UserBase> orgUserList = null;
		String orgCode = "";
		if(siteOrg != null && siteOrg.getId() > 0){
			orgCode = siteOrg.getOrgCode() +"%";
		}
		try {
//			orgUserList = libernate.getEntityListWithCondition(" org_id = ? and del_flag = ? and site_id = ? and org_code like ? " ,
					orgUserList = libernate.getEntityListWithCondition(" del_flag = ? and site_id = ? and org_code like ? " ,
					 new Object[]{ ConstantUtil.DELFLAG_UNDELETE, siteOrg.getSiteId(), orgCode}, 
					 UserBase.class);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return orgUserList;
	}
	
	
	/**
	 * 根据站点取该 站点下所有 的机构对应的人数
	 * @param siteId
	 * @return
	 */
	public List<OrgUserCount> getUserCountListBySiteId(Integer siteId){
		List<OrgUserCount> countList=null;
		StringBuffer sqlBuffer=new StringBuffer();
		List<Object> valueList=new ArrayList<Object>();
		sqlBuffer.append(" SELECT CONCAT(tso.`id`,\"\")  as org_id,tso.`org_code`,CONCAT(COUNT(tub.`id`),\"\")  AS  user_count  ");
		sqlBuffer.append(" FROM t_site_org tso,t_user_base tub  ");
		sqlBuffer.append(" WHERE  tso.`site_id`=? AND tso.`del_flag`=?");
		valueList.add(siteId);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		
		sqlBuffer.append("		AND tub.`site_id`=? AND tub.`del_flag`=? ");
		valueList.add(siteId);
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		
		sqlBuffer.append("		AND tub.`org_code` LIKE CONCAT(tso.`org_code`,'%')");
		sqlBuffer.append("		AND tub.`org_code` IS NOT NULL  AND tub.`org_code`!=''");
		sqlBuffer.append(" GROUP BY tso.`id`,tso.`org_code`");
		sqlBuffer.append(" ORDER BY tso.`org_code` ASC");
		try {
			countList=libernate.getEntityListBase(OrgUserCount.class, sqlBuffer.toString(), valueList.toArray(new Object[]{}));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return countList;
	}
	
	/**
	 * 为用户批量分配机构
	 * wangyong
	 * 2013-5-2
	 */
	@Override
	public boolean updateOrgUserBatch(String orgId, String[] ids, UserBase currentUser){
		boolean updateFlag = true;
		SiteOrg org = getSiteOrgById(IntegerUtil.parseIntegerWithDefaultZero(orgId));
		String orgCode = "";
		if(org != null && org.getId() > 0){
			orgCode = org.getOrgCode();
		}
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("UPDATE t_user_base SET org_id = ? , org_code = ? ");
		valueList.add(orgId);
		valueList.add(orgCode);
		sqlBuilder.append(" WHERE site_id = ? AND id in (0 ");
		valueList.add(currentUser.getSiteId());
		for(String id:ids){
			sqlBuilder.append(",");
			sqlBuilder.append(" ? ");
			valueList.add(id);
		}
		sqlBuilder.append(")");
		try {
			updateFlag = libernate.executeSql(sqlBuilder.toString(), valueList.toArray());
		} catch (Exception e) {
			logger.error("为用户批量分配机构出错！" + e);
			updateFlag = false;
		}
		return updateFlag;
	}
	
	/**
	 * 获取页面传递来的排序参数
	 * wangyong
	 * 2013-5-6
	 */
	private String initSort(String field){
		String sortField = SortConstant.SITE_ORG_FIELDS[0][1];
		for (String[] eachField : SortConstant.SITE_ORG_FIELDS) {
			if (eachField != null && eachField[0].equals(field)) {
				sortField = BeanUtil.att2Field(eachField[1]);
				break;
			}
		}
		return sortField;
	}

	@Override
	public boolean delUserFromOrg(int userId){
		boolean delFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("UPDATE t_user_base SET org_id = ? , org_code = ? ");
		valueList.add(0);
		valueList.add("");
		sqlBuilder.append(" WHERE id = ? ");
		valueList.add(userId);
		try {
			delFlag = libernate.executeSql(sqlBuilder.toString(), valueList.toArray());
		} catch (Exception e) {
			logger.error("从组织机构中移除用户出错！" + e);
			delFlag = false;
		}
		return delFlag;
	}
	
	@Override
	public boolean delUserFromOrgBatch(String[] ids){
		boolean delFlag = false;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("UPDATE t_user_base SET org_id = ? , org_code = ? ");
		valueList.add(0);
		valueList.add("");
		
		sqlBuilder.append(" WHERE id in (0 ");
		for(String id:ids){
			sqlBuilder.append(",");
			sqlBuilder.append(" ? ");
			valueList.add(id);
		}
		sqlBuilder.append(")");
		try {
			delFlag = libernate.executeSql(sqlBuilder.toString(), valueList.toArray());
		} catch (Exception e) {
			logger.error("从组织机构中移除用户出错！" + e);
			delFlag = false;
		}
		return delFlag;
	}
}
