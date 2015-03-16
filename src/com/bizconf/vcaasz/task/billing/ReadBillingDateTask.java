package com.bizconf.vcaasz.task.billing;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.component.sms.SMSUtils;
import com.bizconf.vcaasz.service.BillingService;
import com.bizconf.vcaasz.task.AppContextFactory;

/**
 * 
 * @author
 *
 */
public class ReadBillingDateTask extends Thread implements Runnable {
	
	private static final Log logger = LogFactory.getLog(ReadBillingDateTask.class);
	
	File cdrFile;
	
	static final BillingService billService = AppContextFactory.getAppContext().getBean(BillingService.class);
	
	public ReadBillingDateTask (File cdrFile) {
		this.cdrFile = cdrFile;
	}
	
	public void run() {
		try {
			// 这里需要在配置文件中设置账单文件的转存路径
			File bakupDir = new File(BaseConfig.getInstance().getString("billstorepath",""));
//			File bakupDir = new File("D:/test/billing/backup");
			if(!bakupDir.exists() || !bakupDir.isDirectory()){
				bakupDir.mkdir();
			}
			
			if(this.cdrFile!=null){
				logger.info("find the billing file! ");
				String storePath = BaseConfig.getInstance().getString("billstorepath","")+File.separator+cdrFile.getName();
//				String storePath = "D:/test/billing/backup"+File.separator+cdrFile.getName();
				logger.info("get billing store path on "+storePath);
				storeFile(cdrFile, storePath);
				if(!billService.readCdrBillFile(cdrFile)){
					System.out.println("reading cdr data have exception happen, It's will store the cdr data file to:"+storePath);
//					TODO  短信通知
					SMSUtils.sendSMS("TASK读取账单数据失败", BaseConfig.getInstance().getString("warnnumber", ""));
				}
			}
			
			 
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("read billing data exception happened! attention!");
		}finally{
			//删除原文件
			if(this.cdrFile!=null){
				this.cdrFile.delete();
			}
		}
	}
	
	
	private void storeFile(File file, String storePath){
		BufferedInputStream in = null;
	    BufferedOutputStream out = null; 
		try{
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(new FileOutputStream(new File(storePath)));
			int temp=0;
			byte[] bts = new byte[1024*20];
        	while((temp = in.read(bts))!=-1){
        		out.write(bts,0,temp);
        	}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if (out!=null) {
					out.close();
				}
				if (in!=null) {
					in.close();
				}
			}catch (Exception e) {
				System.out.println("close buffer failed! ");
			}
		}
	}
}
