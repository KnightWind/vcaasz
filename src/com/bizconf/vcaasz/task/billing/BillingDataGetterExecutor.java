package com.bizconf.vcaasz.task.billing;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.component.sms.SMSUtils;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 
 * 
 * 
 * @Author 
 */
public class BillingDataGetterExecutor {

	private static final Log logger = LogFactory.getLog(BillingDataGetterExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final long REST_INTERVAL = 3*24*3600*1000L;//

	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		while (true) {
			try {
				File cdrFile = null;
				String dirPath = BaseConfig.getInstance().getString("billingpath", "");//TODO 这里需要设置账单文件的路径
//								String dirPath = "D:/test/billing";
				File dir = new File(dirPath);
				logger.info("the billing dirPath is " +dirPath);
				if(dir.exists()){
					File[] files = dir.listFiles();
					for (int i = 0; i < files.length; i++) {
						if(files[i].getName().startsWith("billingdata")){ //TODO 这里需要把CDR账单命名加上去
							cdrFile = files[i];
						}
					}
				}
				if(cdrFile != null){
					logger.info("find billing data file:"+cdrFile.getAbsolutePath());
					Runnable task = new ReadBillingDateTask(cdrFile);
					executor.execute(task);
				}else{
					logger.info("can't find any billing file on"+dirPath);
					// TODO
					Calendar calendar = Calendar.getInstance();
					int dayOfm = calendar.get(Calendar.DAY_OF_MONTH);
					if(dayOfm > 0 && dayOfm<5){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String content = sdf.format(new Date())+" 没有发现任何账单文件！";
						SMSUtils.sendSMS(content, BaseConfig.getInstance().getString("warnnumber", ""));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("System run exception:" + e.getLocalizedMessage());
			}
			try {
				Thread.sleep(REST_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
