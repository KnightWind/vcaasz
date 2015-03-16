package com.bizconf.vcaasz.task.support;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务执行器构造工厂
 * 
 * @author Chris Gao
 * 
 */
public class TaskExecutorFactory {
	public static Executor newExecutor(int poolSize) {
		Executor executor = new ThreadPoolExecutor(poolSize, poolSize, 0L,
				TimeUnit.MILLISECONDS, new FixedBlockingQueue<Runnable>(
						poolSize));
		return executor;

	}
}
