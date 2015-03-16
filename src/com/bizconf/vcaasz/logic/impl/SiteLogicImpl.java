package com.bizconf.vcaasz.logic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.UserSiteMap;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.RegexUtil;
import com.bizconf.vcaasz.util.StringUtil;
@Service
public class SiteLogicImpl extends BaseLogic implements SiteLogic {
	

	/**
	 * 创建站点验证前台表单数据
	 * wangyong
	 * 2013-1-22
	 */
	@Override
	public boolean createSiteValidate(SiteBase siteBase, UserBase siteAdmin) throws Exception {
		if(!siteValidate(siteBase)){
			return false;
		}
		if(!adminValidate(siteAdmin)){
			return false;
		}
		return true;
	}

	/**
	 * 修改站点验证前台表单数据
	 * wangyong
	 * 2013-1-22
	 */
	@Override
	public boolean updateSiteValidate(SiteBase siteBase) throws Exception {
		if(!siteValidate(siteBase)){
			return false;
		}
		return true;
	}
	
	/**
	 * 创建站点时，新增站点Supper管理员
	 * @param userBase
	 * @return
	 */
	@Override
	public boolean saveSiteSupperAdmin(UserBase userBase){
		boolean saveStatus = false;
		if(userBase != null){
			try {
				userBase.setUserType(ConstantUtil.USERTYPE_ADMIN_SUPPER);
				libernate.saveEntity(userBase);
				saveStatus = true;
			} catch (Exception e) {
				logger.error("创建站点新增supper管理员出错",e);
			}
		}
		return saveStatus;
	}
	
	/**
	 * 修改站点时，修改站点Supper管理员
	 * @param userBase
	 * @return
	 */
	@Override
	public boolean updateSiteSupperAdmin(UserBase userBase,String... updateFields ){
		boolean saveStatus=false;
		
		if(userBase!=null){
			try {
				libernate.updateEntity(userBase, updateFields);
				saveStatus=true;
			} catch (Exception e) {
				logger.error("修改站点时修改supper管理员出错",e);
			}
		}
		return saveStatus;
	}
	
	/**
	 * 验证站点对象数据格式
	 * wangyong
	 * 2013-1-22
	 */
	private boolean siteValidate(SiteBase siteBase){
		ObjectUtil.parseChar(siteBase, "siteName", "siteSign");
		String siteName = siteBase.getSiteName();
		String enName = siteBase.getEnName();
		String siteSign = siteBase.getSiteSign();
		int siteFlag = siteBase.getSiteFlag();
		if(siteBase.getLicense() != null && siteBase.getLicense().intValue() > 0){
			if(siteBase.getChargeMode() != SiteConstant.SITE_CHARGEMODE_NAMEHOST && siteBase.getLicense().intValue() < 1){
				logger.error("站点最大点数错误");
				return false;
			}
		}
		if(StringUtil.isNotBlank(siteName)){
			if(!RegexUtil.accordStringByPattern(siteName, ConstantUtil.SITE_NAME_REG)){
				logger.error("站点名称错误");
				return false;
			}
		}else{
			return false;
		}
		if(StringUtil.isNotBlank(enName)){
			if(!RegexUtil.accordStringByPattern(enName, ConstantUtil.SITE_ENNAME_REG)){
				logger.error("站点英文名称错误");
				return false;
			}
		}
		if(StringUtil.isNotBlank(siteSign)){
			if(!RegexUtil.accordStringByPattern(siteSign, ConstantUtil.SITE_SIGN_REG)){
				logger.error("站点标识错误");
				return false;
			}
		}else{
			return false;
		}
//		if(!StringUtil.isNotBlank(siteBase.getSiteLogo())){
//			logger.error("站点logo错误");
//			return false;
//		}
		if(siteFlag != ConstantUtil.SITEFLAG_FORMAL && siteFlag != ConstantUtil.SITEFLAG_TRY){
			logger.error("站点类型错误");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证站点管理员数据格式
	 * wangyong
	 * 2013-1-22
	 */
	private boolean adminValidate(UserBase siteAdmin){
		String trueName = siteAdmin.getTrueName();
		String loginName = siteAdmin.getLoginName();
		if(StringUtil.isNotBlank(trueName)){
			if(!RegexUtil.accordStringByPattern(trueName, ConstantUtil.SITE_ADMIN_USERNAME_REG)){
				logger.error("站点管理员用户名错误");
				return false;
			}
		}else{
			return false;
		}
		if(StringUtil.isNotBlank(loginName)){
			if(!RegexUtil.accordStringByPattern(loginName, ConstantUtil.SITE_ADMIN_LOGINNAME_REG)){
				logger.error("站点管理员登录名错误");
				return false;
			}
		}else{
			return false;
		}
		return true;
	}

	/**
	 * 保存临时目录的站点logo到logo图片目录
	 * 无需删除临时目录图片，每日有定时任务删除临时图片
	 * wangyong
	 * 2013-2-4
	 */
	@Override
	public boolean saveSiteLogo(String siteLogoPath) {
		/*boolean flag = true;
		StringBuffer logoPath = new StringBuffer(ConstantUtil.TEMPSITELOGOPATH).append("/").append(siteLogoPath);
		StringBuffer desFilePath = new StringBuffer(ConstantUtil.SITELOGOPATH);
		desFilePath.append("/").append(SiteIdentifyUtil.getCurrentBrand()).append("/");
		FileUtil.createDirs(desFilePath.toString());
		desFilePath.append(siteLogoPath);
		flag = FileUtil.copyFile(logoPath.toString(), desFilePath.toString());
		return flag;*/
		return true;
	}

	/**
	 * 创建站点失败时，删除站点Supper管理员
	 * @param userBase
	 * @return
	 */
	@Override
	public boolean deleteSiteSupperAdmin(int siteId, UserBase userBase) {
		boolean delFlag = false;
		if(siteId > 0 && userBase != null){
			String delSql = "DELETE FROM t_user_base WHERE site_id = ? AND login_name = ? AND user_type = ?";
			Object[] values = new Object[3];
			values[0] = siteId;
			values[1] = userBase.getLoginName();
			values[2] = ConstantUtil.USERTYPE_ADMIN_SUPPER;
			try{
				delFlag = DAOProxy.getLibernate().executeSql(delSql, values);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return delFlag;
	}
	
	@Override
	public SiteBase getVirtualSubSite(Integer userId) {
		if(userId==null){
			return null;
		}
		SiteBase siteBase = null;
		String sql = "select * from  t_user_site_map where user_id = ?";
		try {
			UserSiteMap userSiteMap = libernate.getEntityCustomized(UserSiteMap.class, sql, new Object[]{userId});
			if(userSiteMap==null){
				return null;
			}
			siteBase = libernate.getEntity(SiteBase.class, userSiteMap.getSiteId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return siteBase;
	}
	
	
	@Override
	public SiteBase getParentSite(SiteBase site) {
		if(site.getIsVirtualSite().intValue()!=1) return site;
		String sql = "select * from t_user_site_map where site_id = ?";
		try {
			UserSiteMap userSiteMap = libernate.getEntityCustomized(UserSiteMap.class, sql, new Object[]{site.getId()});
			if(userSiteMap==null){
				return null;
			}
			UserBase ub  = libernate.getEntity(UserBase.class, userSiteMap.getUserId());
			if(ub==null) return null;
			return libernate.getEntity(SiteBase.class, ub.getSiteId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public boolean delHostLicenses(Integer userId) {
		boolean flag = true;
		StringBuilder sqlBuilder = new StringBuilder("update t_license  set del_flag = ? where user_id =?");
		List<Object> values = new ArrayList<Object>();
		values.add(ConstantUtil.DELFLAG_DELETED);
		values.add(userId);
		try{
			libernate.executeSql(sqlBuilder.toString(), values.toArray());
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public SiteBase getSiteBaseById(Integer siteId){
		SiteBase siteBase=null;
		try{
			siteBase=libernate.getEntity(SiteBase.class, siteId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return siteBase;
	}
}
