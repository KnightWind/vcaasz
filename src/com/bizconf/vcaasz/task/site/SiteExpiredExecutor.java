package com.bizconf.vcaasz.task.site;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 站点过期处理任务执行器
 * 
 * @author Chris Gao [gaohl81@gmail.com]
 * <p>
 * 
 */
public class SiteExpiredExecutor {

	private static final Log logger = LogFactory.getLog(SiteExpiredExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final long REST_INTERVAL = 10 * 1000L;


	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		
		SiteService siteService = AppContextFactory.getAppContext().getBean(SiteService.class);
		
		while (true) {
			try {
				//处理已过期但还没被锁定的站点
				List<SiteBase> siteList = siteService.getSiteListForExpired();
				if(siteList!=null && siteList.size() > 0){
					for (SiteBase siteBase : siteList) {
						Runnable task = new SiteExpiredTask(siteBase);
						executor.execute(task);
					}
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
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
