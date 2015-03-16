package com.bizconf.vcaasz.task.user;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.task.AppContextFactory;

/**
 * 站点到期处理任务
 * 
 * @author Chris Gao [gaohl81@gmail.com]
 * 
 */
public class UserExpiredTask extends Thread implements Runnable {

	UserBase userBase;
	
	static final UserService userService = AppContextFactory.getAppContext()
			.getBean(UserService.class);

	public UserExpiredTask(UserBase userBase) {
		this.userBase = userBase;
	}

	public void run() {
		try {
			boolean result = false;

			if (userBase != null) {
				result = userService.lockUserBaseById(userBase.getId());
			}
			if (result) {
				System.out.println("site expired task for :" + userBase
						+ " success.");
			} else {
				System.out.println("site expired task for :" + userBase
						+ " failed, waiting for retry.");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
