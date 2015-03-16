package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.LicenseConstant;
import com.bizconf.vcaasz.entity.BizBilling;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;

/**
 * @desc 
 * @author Administrator
 * @date 2013-3-26
 */
@Service
public class LicServiceImpl extends BaseService implements LicService{
	
	@Autowired
	SiteService siteService;
	@Autowired
	UserLogic userLogic;
	@Autowired
	SiteLogic siteLogic;
	@Autowired
	UserService userService;
	@Autowired
	ZoomUserOperationComponent userOperator;
	
	@Autowired 
	ZoomUserOperationComponent zoomUserOperator;
	
	@Autowired
	DataCenterService dataCenterService;
	/**
	 * 获取License列表  
	 */
	@Override
	public PageBean<License> getLicensePage(int siteId, int userId, int pageNo) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from t_license where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId>0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		if(userId>0){
			sqlBuilder.append(" and user_id =? ");
			values.add(userId);
		}
		sqlBuilder.append(" order by create_time desc ");
		PageBean<License> pageModel = getPageBeans(License.class, sqlBuilder.toString(), pageNo,300,values.toArray(new Object[values.size()]));
		return pageModel;
	}
	
	/**
	 * 保存和修改
	 */
	public boolean saveOrUpdate(License license){
		boolean flag = true;
		try{
			if(license.getId()!=null && license.getId()>0){
				 libernate.updateEntity(license, "licNum", "effeDate", "expireDate", "effeFlag", "expireFlag");
			}else{
				libernate.saveEntity(license);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	/**
	 * 删除
	 */
	@Override
	public boolean delLicense(int licId,int deleter) {
		
		boolean deleteFlag = true;
		StringBuilder sqlBuilder = new StringBuilder("update t_license t set t.del_flag=?,t.del_time=?,t.del_user=? where t.id = ?");
		
		Object[] values = new Object[4];
		values[0] = ConstantUtil.DELFLAG_DELETED;
		values[1] = DateUtil.getDateStrCompact(DateUtil.getGmtDate(null), "yyyy-MM-dd HH:mm:ss");
		values[2] = deleter;
		values[3] = licId;
		try {
			deleteFlag = libernate.executeSql(sqlBuilder.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
			deleteFlag = false;
		}
		return deleteFlag;
	}
	
	/**
	 * 获取name host模式下站点的主持人列表
	 */
	@Override
	public PageBean<UserBase> getHostsBySite(String keyWord ,int siteId, int pageNo,int pagesize) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from t_user_base where del_flag = ? and site_id =? and user_type = ?");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(siteId);
		values.add(ConstantUtil.USERTYPE_USERS);
		if(keyWord!=null && !"".equals(keyWord)){
			sqlBuilder.append(" and (true_name LIKE '%"+keyWord+"%'");
			sqlBuilder.append(" or user_email LIKE '%"+keyWord+"%'");
			sqlBuilder.append(" or mobile LIKE '%"+keyWord+"%' )");
		}
		PageBean<UserBase> pageModel = getPageBeans(UserBase.class, sqlBuilder.toString(), pageNo,pagesize,values.toArray(new Object[values.size()]));
		
		//Add Host License Number by Darren 2015-01-12
		List<UserBase> datas = new ArrayList<UserBase>();
		if(pageModel != null && pageModel.getDatas()!=null && !pageModel.getDatas().isEmpty()){
			for (UserBase base:pageModel.getDatas()) {
	//			UserBase user = userService.getUserBaseById(base.getId());
				int licNum = getHostLicenseNum(base.getId());
				licNum += base.getNumPartis();
				
	//			DataCenter dataCenter = dataCenterService.queryDataCenterById(user.getDataCenterId());
	//			Map<String,Object> userInfo =  zoomUserOperator.get(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId());
	//			int currentPort = (Integer)userInfo.get("meeting_capacity");
	//			logger.info("the fact license is "+currentPort);
	//			if(currentPort != licNum){
	//				if(!zoomUserOperator.modifyPortNum(dataCenter.getApiKey(),dataCenter.getApiToken(),user.getZoomId(), licNum)){
	//					licNum = currentPort;
	//					logger.info("re-update license failed !");
	//				}
	//				logger.info("update license success!");
	//			}
				//添加用户初始化时的端口数
				base.setNumPartis(licNum);
				datas.add(base);
			}
		}
		pageModel.setDatas(datas);
		return pageModel;
	}

	@Override
	public License getLicenseById(int id) {
		License license = null;
		try{
			license = libernate.getEntity(License.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return license;
	}
	
	@Override
	public int getHostLicenseNum(Integer userId) {
		if(userId==null) userId =0;
		List<Object> values = new ArrayList<Object>();// 
		StringBuilder sqlBuilder = new StringBuilder("select sum(lic_num) from t_license where del_flag = ? and user_id=? and effe_flag=? and expire_flag = ?");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(userId);
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
	public Map<Integer, Integer> getHostsLienseDatas(List<UserBase> hosts) {
		Map<Integer, Integer> datas = new HashMap<Integer, Integer>(16);
		if(hosts==null) return datas;
		for (Iterator it = hosts.iterator(); it.hasNext();) {
			UserBase userBase = (UserBase) it.next();
			datas.put(userBase.getId(), getHostLicenseNum(userBase.getId()));
		}
		return datas;
	}
	
	/**
	 * 获取nameHost模式下主持人对应点数的最早生效日期
	 * @param hosts
	 * @return
	 */
	@Override
	public Map<Integer, Date> getHostsLienseEffeDates(List<UserBase> hosts, long offset){
		Map<Integer, Date> datas = new HashMap<Integer, Date>(16);
		if(hosts==null) return datas;
		for (Iterator<UserBase> it = hosts.iterator(); it.hasNext();) {
			UserBase userBase = (UserBase) it.next();
			datas.put(userBase.getId(), getEarlyLienseEffeDate(userBase.getSiteId(), userBase.getId(), offset));
		}
		return datas;
	}
	
	private Date getEarlyLienseEffeDate(int siteId, int userId, long offset) {
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from t_license where del_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		if (siteId>0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		if(userId>0){
			sqlBuilder.append(" and user_id =? ");
			values.add(userId);
		}
		sqlBuilder.append(" order by effe_date asc ");
		PageBean<License> pageModel = getPageBeans(License.class, sqlBuilder.toString(), 1, values.toArray(new Object[values.size()]));
		if(pageModel != null && pageModel.getDatas() != null){
			return DateUtil.getOffsetDateByGmtDate(pageModel.getDatas().get(0).getEffeDate(), offset);
		}
		return null;
	}
	
	@Override
	public Map<String, Integer> getHostsLienseDatasMap(List<BizBilling> hosts,SiteBase site) {
		Map<String, Integer> datas = new HashMap<String, Integer>(16);
		if(hosts==null) return datas;
		for (Iterator it = hosts.iterator(); it.hasNext();) {
			BizBilling bill = (BizBilling) it.next();
			if(bill.getUserId()!=null && !bill.getUserId().equals("")){
				UserBase userBase = userLogic.getUserBaseByLoginName(bill.getUserId(), site.getId());
				if(userBase!=null){
					datas.put(bill.getUserId(), getHostLicenseNum(userBase.getId()));
				}
			}
		}
		return datas;
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
	public List<License> getHostLicenses(Integer hostId) {
		List<Object> values = new ArrayList<Object>();
		List<License> licenses = null;
		try{
			StringBuilder sqlBuilder = new StringBuilder("select * from t_license where del_flag = ? ");
			values.add(ConstantUtil.DELFLAG_UNDELETE);
			if(hostId!=null && hostId>0){
				sqlBuilder.append(" and user_id =? ");
				values.add(hostId);
			}else{
				return null;
			}
			sqlBuilder.append(" order by id desc ");
			licenses = libernate.getEntityListBase(License.class, sqlBuilder.toString(), values.toArray());
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return licenses;
	}
	
	@Override
	public boolean effeLicense(License lic) {
		boolean flag = false;
		try{
			SiteBase site = siteService.getSiteBaseById(lic.getSiteId());
			//判断是否为namehost主持人license
			if(lic.getUserId()!=null && lic.getUserId()>0){
				site = siteLogic.getVirtualSubSite(lic.getUserId());
			}
			SiteBase asSite= siteService.queryASSiteInfo(site.getSiteSign());
			int licNum = asSite.getLicense() + lic.getLicNum();
			site.setLicense(licNum);
				//添加license到as
			boolean addflag = true;
			if(lic.getLicNum()< 0){
				addflag = false;
			}
			String updateInfo = siteService.soapUpdateSite(site,addflag);
			if(updateInfo.equals(ConstantUtil.AS_SUCCESS_CODE)){
				lic.setEffeFlag(LicenseConstant.HAS_EFFED);
			}else{
				logger.error("add license fialed errorcode:"+updateInfo);
				throw new RuntimeException("add license fialed errorcode:"+updateInfo);
			}
			if(saveOrUpdate(lic))flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	
	@Override
	public boolean expiredLicense(License lic) {
		boolean flag = false;
		try{
			SiteBase site = siteService.getSiteBaseById(lic.getSiteId());
			//判断是否为namehost主持人license
			if(lic.getUserId()!=null && lic.getUserId()>0){
				site = siteLogic.getVirtualSubSite(lic.getUserId());
			}
			SiteBase asSite= siteService.queryASSiteInfo(site.getSiteSign());
			int licNum = 0;
			if(asSite.getLicense()>lic.getLicNum()){
				licNum = asSite.getLicense() - lic.getLicNum();
			}
			site.setLicense(licNum);
				//添加license到as
			boolean addflag = true;
			if(lic.getLicNum()> 0){
				addflag = false;
			}
			String updateInfo = siteService.soapUpdateSite(site,addflag);
			if(updateInfo.equals(ConstantUtil.AS_SUCCESS_CODE)){
				lic.setExpireFlag(LicenseConstant.HAS_EFFED);
			}else{
				logger.error("add license fialed errorcode:"+updateInfo);
				throw new RuntimeException("add license fialed errorcode:"+updateInfo);
			}
			if(saveOrUpdate(lic))flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取指定站点的所有点数管理记录
	 * wangyong
	 * 2013-5-3
	 */
	@Override
	public List<License> getSiteLicenseList(int siteId){
		List<License> licenseList = new ArrayList<License>();
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from t_license where del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		sqlBuilder.append(" and site_id =? ");
		valueList.add(siteId);
		sqlBuilder.append(" order by create_time desc ");
		try {
			licenseList = libernate.getEntityListBase(License.class, sqlBuilder.toString(), valueList.toArray());
		} catch (SQLException e) {
			logger.error("获取指定站点的所有点数管理记录出错！" + e);
		}
		return licenseList;
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
	
	/**
	 * 查询seats、time模式下创建站点时的第一条license记录
	 * wangyong
	 * 2013-6-27
	 */
	@Override
	public License getFirstLic(int siteId){
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select * from t_license where del_flag = ? and first_lic_flag = ? ");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(ConstantUtil.FIRST_LICENSE_FLAG);
		if (siteId>0) {
			sqlBuilder.append(" and site_id =? ");
			values.add(siteId);
		}
		sqlBuilder.append(" order by create_time desc ");
		PageBean<License> pageModel = getPageBeans(License.class, sqlBuilder.toString(), 1, values.toArray(new Object[values.size()]));
		if(pageModel.getDatas() != null){
			return pageModel.getDatas().get(0);
		}
		return null;
	}
	
	@Override
	public boolean updateInitLicExpireDate(Integer siteId, Date expiredDate) {
		try{
			String sql = "update t_license set expire_date = ? where del_flag = ? and first_lic_flag = ? and site_id = ?";
			libernate.executeSql(sql, new Object[]{expiredDate,ConstantUtil.DELFLAG_UNDELETE,1,siteId});
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public int getGoingConfLicense(int siteId) {
		if(siteId < 0){
			return 0;
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder("select SUM(pc_num+phone_num) from t_conf_base where del_flag = ? and site_id=? and conf_status=?");
		values.add(ConstantUtil.DELFLAG_UNDELETE);
		values.add(siteId);
		values.add(ConfConstant.CONF_STATUS_OPENING);
		int num = 0;
		try {
			num = libernate.countEntityListWithSql(sqlBuilder.toString(), values.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<License> getWillAppleyLicenses() {
		
		String sql = "select * from t_license where del_flag = ? and "
				+ "((effe_date < ? and effe_flag = ?) or "
				+ "(expire_date < ? and expire_flag = ?))";
		try {
			return libernate.getEntityListCustomized(License.class, sql, new Object[]{
				ConstantUtil.DELFLAG_UNDELETE,
				DateUtil.getGmtDate(null),
				EFFE_FLAG_FALSE,
				DateUtil.getGmtDate(null),
				EFFE_FLAG_FALSE,
			});
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return null;
	}

	
	
	@Override
	public boolean appleyLicenses(License lic) {
		try {
			
			License licBak = lic.clone();
			UserBase host = libernate.getEntity(UserBase.class, lic.getUserId());
			//Add by Darren 2014-12-24 
			DataCenter dataCenter = dataCenterService.queryDataCenterById(host.getDataCenterId());
			
			Map<String,Object> userInfo =  userOperator.get(dataCenter.getApiKey(),dataCenter.getApiToken(),host.getZoomId());
			int currentPort = (Integer)userInfo.get("meeting_capacity");
			Date now = DateUtil.getGmtDate(null);
			int appleyPort = 0;
			if(now.after(lic.getEffeDate()) && lic.getEffeFlag().equals(LicService.EFFE_FLAG_FALSE)){
				appleyPort = currentPort + lic.getLicNum();
				lic.setEffeFlag(LicService.EFFE_FLAG_TRUE);
				
			}else if(now.after(lic.getExpireDate())
					&& lic.getExpireFlag().equals(LicService.EFFE_FLAG_FALSE)
					&& lic.getEffeFlag().equals(LicService.EFFE_FLAG_TRUE)){
				
				appleyPort = currentPort - lic.getLicNum();
				lic.setExpireFlag(LicService.EFFE_FLAG_TRUE);
			}
			
			//不能减少端口到少于两个
			if(appleyPort < 2){
				throw new RuntimeException("the appley port number can not less than 2");
			}
			
			//修改数据库记录
			License updatedLic = libernate.updateEntity(lic,"effeFlag","expireFlag"); 
			if(updatedLic == null ) return false;
			
			boolean flag = userOperator.modifyPortNum(dataCenter.getApiKey(),dataCenter.getApiToken(),host.getZoomId(), appleyPort);
			if(flag){
				return true;
			//zoom修改端口失败
			}else{
				//修改失败 rollback数据库
				libernate.updateEntity(licBak,"effeFlag","expireFlag"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
