package com.bizconf.vcaasz.task.meeting;

import java.util.List;
import java.util.concurrent.Executor;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 会议提醒任务
 * @author zhaost
 *
 */
public class ConfRemindExecutor {
	
	//每多少毫秒执行一次，
	private static final long SLEEP_SECONDS = ConfConstant.CONF_REMIND_TASK_SLEEP_MINUTE_DEFAULT * 60 * 1000;

	private static final int THREAD_POOL_SIZE = 2;
	
	private static final Executor executor = TaskExecutorFactory.newExecutor(THREAD_POOL_SIZE);

	@SuppressWarnings("unused")
	public static void main(String[] args){
		ConfUserService confUserService=AppContextFactory.getAppContext().getBean(ConfUserService.class);
		List<ConfUser> confUserList=null;
		boolean isFrist=true;
		Integer aheadMinute=ConfConstant.CONF_REMIND_AHEAD_MINUTES_DEFAULT;
		Integer scopeMinute;
		while (true) {
			scopeMinute=ConfConstant.CONF_REMIND_MINUTE_SCOPE_DEFAULT;
			if(isFrist){
				scopeMinute=aheadMinute;
			}
			
			confUserList=confUserService.getConfUserListForRemind(aheadMinute, scopeMinute);
			System.out.println("ConfRemindExecutor.confUserList.size=="+confUserList);
			if(confUserList != null && confUserList.size() >0){
				Runnable remindTask = new ConfRemindTask(confUserList);
				if(remindTask==null){
					continue;
				}
				executor.execute(remindTask);
			}
			confUserList=null;
			isFrist=false;
			try {
				Thread.sleep(SLEEP_SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
