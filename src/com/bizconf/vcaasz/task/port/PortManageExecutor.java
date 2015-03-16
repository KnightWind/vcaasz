package com.bizconf.vcaasz.task.port;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 执行端口修改的任务
 * 
 * <p>
 * 
 */
public class PortManageExecutor {

	private static final Log logger = LogFactory.getLog(PortManageExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final long REST_INTERVAL = 1 * 3600 * 1000L; 


	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		
		LicService licService = AppContextFactory.getAppContext().getBean(LicService.class);
		
		while (true) {
			try {
				
				List<License> licList = licService.getWillAppleyLicenses();
				if(licList!=null && licList.size() > 0){
					for (License lic : licList) {
						Runnable task = new PortControlTask(lic);
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
