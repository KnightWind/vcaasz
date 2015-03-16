package com.bizconf.vcaasz.logic;

import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;

public interface SiteLogic {
	
	/**
	 * 创建站点验证前台表单数据
	 * wangyong
	 * 2013-1-22
	 */
	public boolean createSiteValidate(SiteBase siteBase, UserBase siteAdmin) throws Exception;

	/**
	 * 修改站点验证前台表单数据
	 * wangyong
	 * 2013-1-22
	 */
	public boolean updateSiteValidate(SiteBase siteBase) throws Exception;
	
	/**
	 * 创建站点时，新增站点Supper管理员
	 * @param userBase
	 * @return
	 */
	public boolean saveSiteSupperAdmin(UserBase userBase);
	
	/**
	 * 创建站点失败时，删除站点Supper管理员
	 * @param userBase
	 * @return
	 */
	public boolean deleteSiteSupperAdmin(int siteId, UserBase userBase);
	

	/**
	 * 修改站点时，修改站点Supper管理员
	 * @param userBase
	 * @return
	 */
	public boolean updateSiteSupperAdmin(UserBase userBase,String... updateFields );
	
	/**
	 * 保存临时目录的站点logo到logo图片目录
	 * 无需删除临时目录图片，每日有定时任务删除临时图片
	 * wangyong
	 * 2013-2-4
	 */
	public boolean saveSiteLogo(String siteLogoPath);
	
	
	/**
	 * 获取host activeUser主持人用户的对应子站点
	 * @param userId
	 * @return
	 */
	public SiteBase getVirtualSubSite(Integer userId);
	
	
	/**
	 * 获取父站点的信息 如果本身为顶级站点则返回本身
	 * @param site
	 * @return
	 */
	public SiteBase getParentSite(SiteBase site);
	
	
	/**
	 * 删除namehost 主持人用户时删除主持人license
	 * @param userId
	 * @return
	 */
	public boolean delHostLicenses(Integer userId);
	
	
	public SiteBase getSiteBaseById(Integer siteId);
}
