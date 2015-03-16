package com.bizconf.vcaasz.task.site;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.constant.SiteConstant;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 站点到期任务执行器
 * <p>
 * 
 * @Author  
 */
public class SiteRemindExecutor {

	private static final Log logger = LogFactory.getLog(SiteRemindExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final long REST_INTERVAL = 3600 * 1000L;


	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		long border = 0;

	//	EmailService emailService = AppContextFactory.getAppContext().getBean(EmailService.class);
		SiteService siteService = AppContextFactory.getAppContext().getBean(SiteService.class);
		
		while (true) {
			try {
				//需要提醒的站点
				List<SiteBase> siteList = siteService.getSiteListForRemind();
				if(siteList!=null && siteList.size() > 0){
					Runnable task = new SiteRemindTask(siteList);
					executor.execute(task);
				}
				siteList=null;
				
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
