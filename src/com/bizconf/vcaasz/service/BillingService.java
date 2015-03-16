package com.bizconf.vcaasz.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bizconf.vcaasz.entity.Billing;
import com.bizconf.vcaasz.entity.BizBilling;
import com.bizconf.vcaasz.entity.ConfBilling;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.ServiceItemBilling;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.UserBilling;

/**
 * @desc 主要处理和账单相关的业务
 * @author martin
 * @date 2013-5-21
 */
public interface BillingService {


	//查询所有的系统账单集合
	int BILL_TYPE_PRE_MIN = 1; //视频计时
	
	int BILL_TYPE_FIXED = 2;	// 视频包月
	
	int BILL_TYPE_TEL = 3; //电话即时
	
	int BILL_TYPE_EXTRA = 4; //额外端口
	
	//会议的类型
	int CONF_CALL_TYPE_IN = 1;
	int CONF_CALL_TYPE_OUT = 2;
	int CONF_CALL_TYPE_VIDEO = 3;
	
	
	/**
	 * 获取主持人计时的账单
	 * @param site 
	 * @param portnums 端口数
	 * */
	public List<Billing> getUserBillingPage(int pageNo,int pageSize,UserBase user,SiteBase site, Date startDate,Date endDate,String portnums);
	 
	/**
	 * 获取主持人包月情况下的账单
	 * */
	public PageBean<ConfBilling> getConfBillingPage(int pageNo, int pageSize,UserBase userBase,SiteBase siteBase,Date startDate, Date endDate);
	
	 
	
	
	/**
	 * 查询某个主持人会议账单详情
	 * @param pageNo
	 * @param pageSize
	 * @param hostId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public PageBean<ConfBilling> getConfBillingPage(int pageNo,int pageSize,int hostId,Date startDate, Date endDate);
	
	/**
	 * 根据会议号查找该会议下面的CDR账单
	 * @param confHwid
	 * @return
	 */
	public List<Billing> getCdrBillByConfZoomId(String confZoomId, int billType);
	
	
	/**
	 * 根据站点查询该站点开的用户数 name_host 只查询主持人数量
	 * @param siteId
	 * @return
	 */
	public int getSiteUserNum(int siteId,boolean isNameHost);
	
	/**
	 * 读取CDR账单
	 * @param cdrFile
	 * @return
	 */
	public boolean readCdrBillFile(File cdrFile);
	
	 
	
	/**
	 * 生成各个站点月度总账单
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean genMonthyTotalFee(Date startDate, Date endDate);
	
	
	/**
	 * 获取月度总账单
	 * @param siteId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Billing getMonthlyBill(String siteId,Date startDate, Date endDate);
	/**
	 * 获取某站点用户月通信费用
	 * @param user
	 * @param site 
	 * @param startDate
	 * @param endDate
	 * @param portnums 
	 * @return
	 */
	public Double getUserTotalTelFee(UserBase user,SiteBase site, Date startDate, Date endDate, String portnums);
	


	/**
	 * 获取账单所属站点集合
	 * @param billings
	 * @return
	 */
	Map<String, SiteBase> getBillBelongSite(List<BizBilling> billings);
	
	/**
	 * 查询在某段时间的会议的数据费用
	 * @param site
	 * @param user
	 * @param pageNo
	 * @return
	 */
	public List<Billing> getDataBillings(SiteBase site, UserBase user,int billType,Date starDate, Date endDate);
	
	
	/**
	 * 判断站点标志对应的站点是否存在
	 * @param siteSign
	 * @return
	 */
	public boolean siteExisted(String siteSign);
	
	/**
	 * 获取同一主持人总账单
	 * @param userBase 
	 * @param currentSite 
	 * @param sumCharge 
	 * */
	public UserBilling setUserTotalBilling(List<Billing> datas, UserBase userBase, SiteBase currentSite, Double sumCharge);
	
	/**
	 * 组织站点的总账单
	 * */
	public List<ServiceItemBilling> setSiteTotalBilling(List<Billing> retBillings, SiteBase site,Date startDate,Date endDate);
	
	/**
	 * 获取某个站点下的全部主持人 hostId
	 * */
	public List<UserBase> getHostsBySiteId(Integer siteId);
	
	//获取站点总费用统计
	public double getBillAmant(SystemUser sysAdmin, Date startDate, Date endDate, String keyword,String siteName);

	//用户导出费用明细
	public HSSFWorkbook getUserExcelModel(String userName,String chargModel,float dataFee,float telFee,Date startDate, Date endDate);
	
	
	/**
	 * 获取用户的月固定费用
	 * @param siteId
	 * @param monthStart
	 * @param monthEnd
	 * @return
	 */
	public double getSiteMothlyFixedFee(int siteId, Date monthStart,Date monthEnd);
	
	
	/**
	 * 获取用户的月电话费用
	 * @param siteId
	 * @param monthStart
	 * @param monthEnd
	 * @return
	 */
	public double getSiteMothlyTelFee(int siteId, Date monthStart,Date monthEnd);
	
	
	/**
	 * 获取站点一个月内总计时费用
	 * @param siteId
	 * @param monthStart
	 * @param monthEnd
	 * @return
	 */
	public double getSiteMothlyVedioFee(int siteId, Date monthStart,Date monthEnd);
	
	/**
	 * 获取用户的总额外端口费用  和总的额外端口数
	 * @param siteId
	 * @param monthStart
	 * @param monthEnd
	 * @return
	 */
	public Map<Object, Object> getSiteMothlyExtraPortFee(int siteId, Date monthStart,Date monthEnd);

}
