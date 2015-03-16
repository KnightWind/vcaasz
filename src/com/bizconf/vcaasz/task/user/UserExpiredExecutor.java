package com.bizconf.vcaasz.task.user;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 用户过期处理任务执行器
 * 
 * <p>
 * 
 */
public class UserExpiredExecutor {

	private static final Log logger = LogFactory.getLog(UserExpiredExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final long REST_INTERVAL = 6 * 3600 * 1000L; 


	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		
		UserService userService = AppContextFactory.getAppContext().getBean(UserService.class);
		
		while (true) {
			try {
				
				List<UserBase> userList = userService.findExpireUsers();
				if(userList!=null && userList.size() > 0){
					for (UserBase userBase : userList) {
						Runnable task = new UserExpiredTask(userBase);
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
