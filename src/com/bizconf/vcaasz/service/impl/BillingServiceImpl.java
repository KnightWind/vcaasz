package com.bizconf.vcaasz.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.constant.CallType;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.Billing;
import com.bizconf.vcaasz.entity.BizBilling;
import com.bizconf.vcaasz.entity.ConfBilling;
import com.bizconf.vcaasz.entity.ConfReportInfo;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.ServiceItemBilling;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SiteHostBilling;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.entity.UserBilling;
import com.bizconf.vcaasz.entity.ZoomConfRecord;
import com.bizconf.vcaasz.logic.UserLogic;
import com.bizconf.vcaasz.service.BillingService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 
 * @author Darren
 * @date 2014-11-03
 */
@Service
public class BillingServiceImpl extends BaseService implements BillingService{
	
	private static final Log logger = LogFactory.getLog(BillingServiceImpl.class);
	
	@Autowired
	UserLogic logic;
	

	 
	@Override
	public List<Billing> getUserBillingPage(int pageNo, int pageSize,
			UserBase user,SiteBase site, Date startDate, Date endDate, String portnums) {
		
		StringBuilder builder = new StringBuilder("select * from t_billing where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if(user!=null){
			builder.append(" and host_id = ? ");
			values.add(user.getId());
		}
		if(site!=null){
			builder.append(" and site_id = ? ");
			values.add(site.getId());
			if(site.getHireMode() == 1){//包月
				builder.append(" and bill_type in (2,3,4)");
			}else{
				builder.append(" and bill_type in ( 1,3 )");
			}
		}
//		if(portnums != null){
//			builder.append(" and num_parts = ?");
//			values.add(portnums);
//		}
		//计费时间均按照开始时间在该月的月初和月末这段时间进行统计
		builder.append(" and start_time BETWEEN ? and ? ");
		values.add(startDate);
		values.add(endDate);
		try {
			return libernate.getEntityListCustomized(Billing.class, builder.toString(), values.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean genMonthyTotalFee(Date startDate, Date endDate) {
		return false;
	}
	
	@Override
	public Billing getMonthlyBill(String siteId,Date startDate, Date endDate){
		String sql = "select * from t_billing where 1=1 and site_id = ? and tb.start_time BETWEEN ? and ?  ";
		try {
			return libernate.getEntityCustomized(Billing.class,sql, new Object[]{siteId,startDate,endDate});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Double getUserTotalTelFee(UserBase user,SiteBase siteBase, Date startDate, Date endDate, String portnums) {
		Double total = 0.00;
		StringBuilder sqlBuilder = new StringBuilder("select Sum(charge) as total from t_billing tb where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		if(user!=null){
			sqlBuilder.append(" and host_id = ?");
			values.add(user.getId());
		}
		if(siteBase != null){
			sqlBuilder.append(" and site_id = ?");
			values.add(siteBase.getId());
			if(siteBase.getHireMode() == 1){
				sqlBuilder.append(" and bill_type in ( 2,3,4 )");
			}else{
				sqlBuilder.append(" and bill_type in ( 1,3 )");
			}
		}
//		if(portnums!=null){
//			sqlBuilder.append(" and num_parts = ?");
//			values.add(portnums);
//		}
		sqlBuilder.append(" and tb.start_time BETWEEN ? and ?  ");
		values.add(startDate);
		values.add(endDate);
		try {
			List<Object> objects = libernate.getObjectList(sqlBuilder.toString(), values.toArray());
			if(objects!=null && !objects.isEmpty() && objects.get(0)!=null){
				Double value = (Double) objects.get(0);
				if(value!=null){
					total = value;
				}
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<Billing> getDataBillings(SiteBase site, UserBase user,int billType,
			Date startDate, Date endDate) {
		StringBuilder sqlBuilder = new StringBuilder("select * from t_billing tb where 1=1 and tb.bill_type = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(billType);
		try {
			if(site!=null){
				sqlBuilder.append(" and tb.site_id = ? ");
				values.add(site.getId());
			}
			if(user!=null){
				sqlBuilder.append(" and tb.host_id = ? ");
				values.add(user.getLoginName());
			} 
			sqlBuilder.append(" and tb.start_time BETWEEN ? and ?  ");
			values.add(startDate);
			values.add(endDate);
			return libernate.getEntityListBase(Billing.class, sqlBuilder.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserBilling setUserTotalBilling(List<Billing> datas, UserBase userBase, SiteBase site, Double sumCharge) {

		List<ServiceItemBilling> serviceItemBillings = new ArrayList<ServiceItemBilling>();
		//项目计数
		int billType1 = 0;
		int billType2 = 0;
		int billType3 = 0;
		int billType4 = 0;
		//项目费用
		Double charge1 = 0.0;
		Double charge2 = 0.0;
		Double charge3 = 0.0;
		Double charge4 = 0.0;
		
		int extPort = 0;
		for(Billing billing : datas){
			if(billing.getBillType()==1){//视频计时
				billType1 += billing.getDuration();
				charge1 += billing.getCharge();
			}else if(billing.getBillType()==2){//包月
				billType2++;
				charge2 += billing.getCharge();
			}else if(billing.getBillType()==3){//电话计时
				billType3 += billing.getDuration();
				charge3 += billing.getCharge();
			}else{//4 namehost额外端口
				billType4++;
				charge4 += billing.getCharge();
				//显示额外端口个数
				extPort += billing.getNumParts();
			}
		}
		
		//包月
		if(site!=null && site.getHireMode()== 1){
			ServiceItemBilling itemBilling2 = new ServiceItemBilling();
			itemBilling2.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_NAMEHOST);
			itemBilling2.setNumPatis(userBase.getNumPartis());//NameHost的主持人的端口数
			itemBilling2.setItemCount(billType2);
			itemBilling2.setItemCharge(0.00f);
			itemBilling2.setItemCharge(charge2.floatValue());//包月情况下金额为0.00
			serviceItemBillings.add(itemBilling2);
			
			//电话费用
//			ServiceItemBilling itemBilling3 = new ServiceItemBilling();
//			itemBilling3.setItemId(ConstantUtil.SERVICE_ITEM_TELL_PERMIN);
//			itemBilling3.setItemCount(billType3);
//			itemBilling3.setItemCharge(charge3.floatValue());
//			serviceItemBillings.add(itemBilling3);
			
			ServiceItemBilling itemBilling4 = new ServiceItemBilling();
			itemBilling4.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_NAMEHOST_EXTEND);
			itemBilling4.setItemCount(extPort);
			itemBilling4.setItemCharge(charge4.floatValue());
			serviceItemBillings.add(itemBilling4);
		}else{
		//计时
			ServiceItemBilling itemBilling1 = new ServiceItemBilling();
			itemBilling1.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_PERMIN);
			itemBilling1.setItemCount(billType1);
			itemBilling1.setItemCharge(charge1.floatValue());
			serviceItemBillings.add(itemBilling1);
		
			//电话费用先不显示
//			ServiceItemBilling itemBilling3 = new ServiceItemBilling();
//			itemBilling3.setItemId(ConstantUtil.SERVICE_ITEM_TELL_PERMIN);
//			itemBilling3.setItemCount(billType3);
//			itemBilling3.setItemCharge(charge3.floatValue());
//			serviceItemBillings.add(itemBilling3);
		}
		
		UserBilling userBilling = new UserBilling();
		userBilling.setAccount(userBase.getUserEmail()+"");
		userBilling.setUserName(userBase.getTrueName()+"");
		userBilling.setHostId(userBase.getId());
		userBilling.setSumCharge(sumCharge.floatValue());
		userBilling.setServiceItemBillings(serviceItemBillings);
		return userBilling;
	}
	
	public Double add(Number value1, Number value2) {  
        BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));  
        BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));  
        return b1.add(b2).doubleValue();  
    }
	@Override
	public List<ServiceItemBilling> setSiteTotalBilling(List<Billing> billings, SiteBase site,Date startDate,Date endDate){
		
		List<ServiceItemBilling> serviceItemBillings = new ArrayList<ServiceItemBilling>();
		//项目计数
		int billType1 = 0;
		int billType2 = 0;
		int billType3 = 0;
		int billType4 = 0;
		
		int extPort = 0; //额外端口统计
		
		//项目费用
		Double charge1 = 0.0;
		Double charge2 = 0.0;
		Double charge3 = 0.0;
		Double charge4 = 0.0;
		
		for(Billing billing : billings){
			if(billing.getBillType()==1){//视频计时
				billType1 += billing.getDuration();
				charge1 += billing.getCharge();
			}else if(billing.getBillType()==2){//包月
				billType2++;
				charge2 += billing.getCharge();
			}else if(billing.getBillType()==3){//电话计时
				billType3 += billing.getDuration();
				charge3 += billing.getCharge();
			}else{//4 namehost额外端口
				billType4++;
				charge4 += billing.getCharge();
				
				extPort += billing.getNumParts();
			}
		}
		
		//获取该站点包月的所有主持人的方数,费用
		List<Map<String, Object>> numparts = getNumParts(site,startDate,endDate);
		//包月
		if(site!=null && site.getHireMode()== ConstantUtil.SERVICE_ITEM_VIDEO_PERMIN){
			for(Map<String, Object> map:numparts){
				ServiceItemBilling itemBilling2 = new ServiceItemBilling();
				itemBilling2.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_NAMEHOST);
				itemBilling2.setNumPatis(Integer.parseInt(map.get("numparts")+""));//NameHost的主持人的端口
				itemBilling2.setItemCount(Integer.parseInt(map.get("count")+""));//端口数目
				itemBilling2.setItemCharge(Float.valueOf(map.get("sumcharge")+""));//计费
				serviceItemBillings.add(itemBilling2);
			}
			
//			ServiceItemBilling itemBilling3 = new ServiceItemBilling();
//			itemBilling3.setItemId(ConstantUtil.SERVICE_ITEM_TELL_PERMIN);
//			itemBilling3.setItemCount(billType3);
//			itemBilling3.setItemCharge(charge3.floatValue());
//			serviceItemBillings.add(itemBilling3);
			
			ServiceItemBilling itemBilling4 = new ServiceItemBilling();
			itemBilling4.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_NAMEHOST_EXTEND);
			itemBilling4.setItemCount(extPort);
			itemBilling4.setItemCharge(charge4.floatValue());
			serviceItemBillings.add(itemBilling4);
		}else{
		//计时 
			ServiceItemBilling itemBilling1 = new ServiceItemBilling();
			itemBilling1.setItemId(ConstantUtil.SERVICE_ITEM_VIDEO_PERMIN);
			itemBilling1.setItemCount(billType1);
			itemBilling1.setItemCharge(charge1.floatValue());
			serviceItemBillings.add(itemBilling1);
			
//			ServiceItemBilling itemBilling3 = new ServiceItemBilling();
//			itemBilling3.setItemId(ConstantUtil.SERVICE_ITEM_TELL_PERMIN);
//			itemBilling3.setItemCount(billType3);
//			itemBilling3.setItemCharge(charge3.floatValue());
//			serviceItemBillings.add(itemBilling3);
		}
		return serviceItemBillings;
	}
	
	
	/**
	 * 获取某个站点下的包月情况下的参会方数
	 * @param endDate 
	 * @param startDate 
	 * */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getNumParts(SiteBase site, Date startDate, Date endDate) {
		
		StringBuffer buffer = new StringBuffer("select t.num_parts numparts,count(id) count,sum(charge) sumcharge from t_billing t where t.site_id = ? and t.bill_type = ? and t.start_time BETWEEN ? and ? group by t.num_parts");
		List<Object> values = new ArrayList<Object>();
		values.add(site.getId());
		values.add(ConstantUtil.SERVICE_ITEM_VIDEO_NAMEHOST);
		values.add(startDate);
		values.add(endDate);
		try {
			return libernate.queryForList(buffer.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserBase> getHostsBySiteId(Integer siteId){
		
		StringBuilder sqlBuilder = new StringBuilder("select * from t_user_base tb where 1=1 and tb.site_id = ?");
		List<Object> values = new ArrayList<Object>();
		values.add(siteId);
		try {
			return libernate.getEntityListCustomized(UserBase.class, sqlBuilder.toString(), values.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getSiteUserNum(int siteId, boolean isNameHost) {
		return 0;
	}

	@Override
	public boolean readCdrBillFile(File cdrFile) {
		
		if(cdrFile == null || !cdrFile.exists()){
			return false;
		}
		
		boolean flag = true;
		BufferedReader reader = null;
		BufferedWriter errorWriter = null;
		SimpleDateFormat nsdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		SimpleDateFormat psdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String logDir = BaseConfig.getInstance().getString("exceptionlog", "");
			
			logger.info("find out logDir on "+logDir);
//			String logDir = "D:/test/billing/exceptionlog";
			File logDirFile = new File(logDir);
			if(!logDirFile.exists() || !logDirFile.isDirectory()){
				logDirFile.mkdir();
			}
			String errorFilePath = logDir+File.separator+nsdf.format(new Date())+"_CDR_exception.log";
			
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(cdrFile),"GBK"));
			String temp = null;
			while((temp=reader.readLine())!=null){
				logger.info("got CDR billing massge: "+temp);
				if(StringUtil.isEmpty(temp)) continue;
				String[] values = temp.split("\\|");
				Billing billing = new Billing();
				try{
					if(values.length < 11){
						throw new RuntimeException("un support cdr record! "
								+ "will write to exception log file!");
					}
					int bill_type = Integer.parseInt(values[0]);
					billing.setBillType(bill_type);
					
					if(!StringUtil.isEmpty(values[1])){
						UserBase host = logic.getUserBaseByEmail(values[1]);
						if(host == null) continue;
						billing.setHostId(host.getId());
						billing.setSiteId(host.getSiteId());
						if(bill_type == 2){
							billing.setNumParts(host.getNumPartis());
						}else if(bill_type == 4){
							Date perDate = DateUtil.getPreMonth(new Date());
							//Date perDate = new Date();//测试是使用
							Date startDate = DateUtil.getMonthStartTime(perDate);
							Date endDate = DateUtil.getMonthEndTime(perDate);
							
							billing.setStartTime(startDate);
							billing.setEndTime(endDate);
							int extPort = logic.findHostExtraPort(host.getId(), startDate, endDate);
							logger.info("find "+host.getId()+" ext port num is: "+extPort);
							billing.setNumParts(extPort);
						}
					}
					
					if(!StringUtil.isEmpty(values[2])){
						billing.setName(values[2]);
					}
					
					if(!StringUtil.isEmpty(values[3])){
						billing.setStartTime(psdf.parse(values[3]));
					}else if(bill_type == 2){
						//billing.setNumParts(host.getNumPartis());
						Date perDate = DateUtil.getPreMonth(new Date());
						//Date perDate = new Date();测试是使用
						billing.setStartTime(DateUtil.getMonthStartTime(perDate));
						billing.setEndTime(DateUtil.getMonthEndTime(perDate));
					}
					
					if(!StringUtil.isEmpty(values[4])){
						billing.setEndTime(psdf.parse(values[4]));
					}
					
					if(!StringUtil.isEmpty(values[5])){
						int duration = Integer.parseInt(values[5]);
						billing.setDuration(duration);
					}
					
					if(!StringUtil.isEmpty(values[6])){
						 
						billing.setPhone(values[6]);
					}
					
					if(!StringUtil.isEmpty(values[7])){
						
						billing.setConfZoomId(values[7]);
					}
					
					if(!StringUtil.isEmpty(values[8])){
						
						billing.setCallType(CallType.getStatu(values[8]));
					}
					
					if(!StringUtil.isEmpty(values[9])){
						
						billing.setDnis(values[9]);
					}
					
					if(!StringUtil.isEmpty(values[10])){
						
						billing.setCharge(Double.parseDouble(values[10]));
					}
					
					if(values.length>11 && !StringUtil.isEmpty(values[11])){
						
						billing.setRecordId(Long.parseLong(values[11]));
					}
					
					libernate.saveEntity(billing);
					
				}catch(Exception e){
					//TODO  这里要记录发生异常的信息
					flag = false;
					if(errorWriter==null){
						errorWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(errorFilePath)),"UTF-8"));
					}
					errorWriter.write(temp);//记录错误行数内容
					errorWriter.newLine(); 
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}finally{
			try{
				if(reader!=null){
					reader.close();
				}
				if(errorWriter!=null){
					errorWriter.flush();
					errorWriter.close();
				}
			}catch (Exception e) {
				logger.error(" when reader CDR billing:close reader and writer failed! ");
				e.printStackTrace();
			}
		}
		return flag;
	}

	
	@Override
	public Map<String, SiteBase> getBillBelongSite(List<BizBilling> billings) {
		return null;
	}


	@Override
	public boolean siteExisted(String siteSign) {
		return false;
	}

	 
	@Override
	public double getBillAmant(SystemUser sysAdmin, Date startDate,
			Date endDate, String keyword, String siteName) {
		return 0;
	}

	@Override
	public HSSFWorkbook getUserExcelModel(String userName, String chargModel,
			float dataFee, float telFee, Date startDate, Date endDate) {
		return null;
	}
	
	/**
	 * 该方法获取企业管理员查看每个主持人账单明细
	 * @param siteId
	 * @param monthStart
	 * @param monthEnd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageBean<SiteHostBilling> getSiteHostsBillingsPage(int siteId,Date monthStart,Date monthEnd,int pageNo,int pageSize){
		
		//1.查询该站点下的主持人分页列表  2.根据主持人列表组装siteHostBilling对象 
		String sql = "select * from t_user_base where del_flag = ? and site_id = ? and user_type = ? ";
		
		PageBean<UserBase> hostPage = getPageBeans(UserBase.class, sql, pageNo, pageSize, new Object[]{
			ConstantUtil.DELFLAG_UNDELETE,
			siteId,
			ConstantUtil.USERTYPE_USERS
		});
		
		PageBean<SiteHostBilling> page = new PageBean<SiteHostBilling>(); 
	
		List<SiteHostBilling> shbDatas = new ArrayList<SiteHostBilling>();
		
		if(hostPage!=null && !hostPage.getDatas().isEmpty()){
			
			List<UserBase> hosts = hostPage.getDatas();
					
			page.setBeginRow(hostPage.getBeginRow());
			page.setPageNo(hostPage.getPageNo());
			page.setPageSize(hostPage.getPageSize());
			page.setRowsCount(hostPage.getRowsCount());
			
			for (UserBase host : hosts) {
				 
				SiteHostBilling shb = new SiteHostBilling();
				
				shb.setHost(host);
				
//				shb.setFixedCharge(fixedCharge);
//				shb.setTelCharge(telCharge);
//				shb.setTelMin(telMin);
//				shb.setVideoCharge(videoCharge);
//				shb.setVideoMin(videoMin);
//				shb.setExtraPortNum(extraPortNum);
				
				shbDatas.add(shb);
			}
		}
		return  page;
	}
	

	@Override
	public PageBean<ConfBilling> getConfBillingPage(int pageNo, int pageSize,
			int hostId, Date startDate, Date endDate) {
		PageBean<ConfBilling> page = new PageBean<ConfBilling>();
//		List<ConfBilling> cbDatas = null; 
		try{
			String  sql = "SELECT * from t_zoom_conf_record where"
					+ " duration > 0 and host_id = ? and start_time BETWEEN ? and ? order by start_time";
			PageBean<ZoomConfRecord>  records = getPageBeans(ZoomConfRecord.class, sql, pageNo, pageSize, new Object[]{
				hostId,
				startDate,
				endDate
			});
			String sqlGetBillings = "select * from t_billing where record_id = ? and bill_type = ? ";
			if(records!=null && records.getDatas()!=null && !records.getDatas().isEmpty()){
				List<ConfBilling> cbDatas = new ArrayList<ConfBilling>();
				List<ZoomConfRecord> zoomRecords = records.getDatas();
//				int sumDuration = 0;//每场会议的总时长
				for (ZoomConfRecord zcr : zoomRecords) {
					ConfBilling cb = new ConfBilling();
					cb.setZcr(zcr);
					//计时费用
					List<Billing> videoBillings = libernate.getEntityListCustomized(Billing.class, sqlGetBillings, new Object[]{
						zcr.getId(),
						BILL_TYPE_PRE_MIN
					});
					//电话费用
					List<Billing> telBillings = libernate.getEntityListCustomized(Billing.class, sqlGetBillings, new Object[]{
						zcr.getId(),
						BILL_TYPE_TEL
					});
					cb.setVideoBillings(videoBillings);
					cb.setTelBillings(telBillings);
					//计算每场会议的费用
					List<Billing> totalBillings = new ArrayList<Billing>();
					if(videoBillings!=null && !videoBillings.isEmpty()){
						totalBillings.addAll(videoBillings);
					}
					if(telBillings!=null && !telBillings.isEmpty()){
						totalBillings.addAll(telBillings);
					}
					double sumCharge = 0;
					for(Billing billing:totalBillings){
						sumCharge += billing.getCharge();
//						sumDuration += billing.getDuration();
					}
//					cb.setDuration(sumDuration);//每场会议的总时长
					cb.setSumCharge(sumCharge);//每场会议的总金额
					cbDatas.add(cb);
				}
				page.setBeginRow(records.getBeginRow());
				page.setPageNo(records.getPageNo());
				page.setPageSize(records.getPageSize());
				page.setRowsCount(records.getRowsCount());//2015-01-12 update By Darren
				page.setDatas(cbDatas);
				return  page;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  page;
	}

	@Override
	public PageBean<ConfBilling> getConfBillingPage(int pageNo, int pageSize,UserBase userBase,SiteBase siteBase,
			Date startDate, Date endDate) {
		PageBean<ConfBilling> page = new PageBean<ConfBilling>();
		try{
			String  sql = "SELECT * from t_zoom_conf_record where"
					+ " duration > 0 and host_id = ? and start_time BETWEEN ? and ? order by start_time ";
			PageBean<ZoomConfRecord>  records = getPageBeans(ZoomConfRecord.class, sql, pageNo, pageSize, new Object[]{
				userBase.getId(),
				startDate,
				endDate
			});
			
			String sqlGetBillings = "select * from t_billing where record_id = ? and bill_type = ? ";
			String sqlReprt = "select * from t_conf_report_info where conf_log_id = ?";
			if(records!=null &&records.getDatas()!=null  && !records.getDatas().isEmpty()){
				List<ZoomConfRecord> zoomRecords = records.getDatas();
				List<ConfBilling> cbDatas = new ArrayList<ConfBilling>(); 
				for(ZoomConfRecord zcr : zoomRecords){
					ConfBilling cb = new ConfBilling();
					cb.setZcr(zcr);
					//NameHost费用
					List<ConfReportInfo> videoReportInfos = libernate.getEntityListCustomized(ConfReportInfo.class, sqlReprt, new Object[]{zcr.getId()});
					if(videoReportInfos!=null && videoReportInfos.size() > 0){
//						int sumDuration = 0;//每场会议的总时长
						List<Billing> videoBillings = new ArrayList<Billing>();
						for(ConfReportInfo reportInfo:videoReportInfos){
							Billing videoBilling = new Billing();
							videoBilling.setCallType(CONF_CALL_TYPE_VIDEO);
							videoBilling.setCharge(0.00f);//NameHost的类型会议计费是0.00
							videoBilling.setName(reportInfo.getParticipantName());
							videoBilling.setStartTime(reportInfo.getpStartTime());
							videoBilling.setEndTime(reportInfo.getpLeaveTime());
//							int duration = (int)(reportInfo.getpLeaveTime().getTime() - reportInfo.getpStartTime().getTime())/(1000 * 60);
//							sumDuration += duration;//NameHost的每次会议时长
							int duration = DateUtil.scoreTime(reportInfo.getpStartTime() ,reportInfo.getpLeaveTime());
							videoBilling.setDuration(duration);
							videoBilling.setSiteId(siteBase.getId());
							videoBilling.setHostId(userBase.getId());
							videoBilling.setRecordId(zcr.getId());
							videoBilling.setNumParts(userBase.getNumPartis());
							videoBilling.setBillType(BILL_TYPE_FIXED);
							videoBillings.add(videoBilling);
						}
						//电话费用
						List<Billing> telBillings = libernate.getEntityListCustomized(Billing.class, sqlGetBillings, new Object[]{
							zcr.getId(),
							BILL_TYPE_TEL
						});
						cb.setVideoBillings(videoBillings);//每场会议的视频费用
						cb.setTelBillings(telBillings);//每场会议的电话费用
						
						//计算每场会议的全部费用
						double sumCharge = 0;
						if(telBillings!=null && !telBillings.isEmpty()){
							for(Billing billing:telBillings){
								sumCharge += billing.getCharge();
//								sumDuration += billing.getDuration();//电话的每次会议时长
							}
						}
//						cb.setDuration(sumDuration);//每场会议的总时长
						cb.setSumCharge(sumCharge);
						cbDatas.add(cb);
					}
				}
				page.setBeginRow(records.getBeginRow());
				page.setPageNo(records.getPageNo());
				page.setPageSize(records.getPageSize());
				page.setRowsCount(records.getRowsCount());
				page.setDatas(cbDatas);
				return  page;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}
	

	
	@Override
	public List<Billing> getCdrBillByConfZoomId(String confZoomId, int billType) {
		return null;
	}


	
	
	@Override
	public double getSiteMothlyFixedFee(int siteId, Date monthStart,
			Date monthEnd) {
		
		String sql = "select sum(charge) where site_id=? and  bill_type=? and start_time > ? and end_time < ?";
		try {
			
			List<Object> objs = libernate.getObjectList(sql, new Object[]{siteId,BILL_TYPE_FIXED,monthStart,monthEnd});
		
			if(objs!=null && !objs.isEmpty()){
				Double value = (Double)objs.get(0);
				
				return value;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0d;
	}


	@Override
	public double getSiteMothlyTelFee(int siteId, Date monthStart, Date monthEnd) {
		
		String sql = "select sum(charge) where site_id=? and  bill_type=? and start_time > ? and end_time < ?";
		try {
			
			List<Object> objs = libernate.getObjectList(sql, new Object[]{siteId,BILL_TYPE_TEL,monthStart,monthEnd});
		
			if(objs!=null && !objs.isEmpty()){
				Double value = (Double)objs.get(0);
				
				return value;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0d;
	}


	@Override
	public double getSiteMothlyVedioFee(int siteId, Date monthStart,
			Date monthEnd) {
		
		String sql = "select sum(charge) where site_id=? and bill_type=? and start_time > ? and end_time < ?";
		try {
			
			List<Object> objs = libernate.getObjectList(sql, new Object[]{siteId,BILL_TYPE_PRE_MIN,monthStart,monthEnd});
		
			if(objs!=null && !objs.isEmpty()){
				Double value = (Double)objs.get(0);
				
				return value;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0d;
	}


	@Override
	public Map<Object, Object> getSiteMothlyExtraPortFee(int siteId,
			Date monthStart, Date monthEnd) {
		
		Map<Object, Object> dataMap = null;
		String sql = "select sum(num_parts) as portNum, sum(charge)as charge where site_id=? and bill_type=? and start_time > ? and end_time < ?";
		try {
			
			 dataMap = libernate.queryForMap(sql, new Object[]{siteId,BILL_TYPE_PRE_MIN,monthStart,monthEnd});
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}

}
