package com.bizconf.vcaasz.task.meeting;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 会议任务执行器
 * <p>
 * 
 * @Author Chris Gao [gaohl81@gmail.com]
 */
public class ConfTaskExecutor {


	private static final Log logger = LogFactory.getLog(ConfTaskExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final int FETCH_SIZE = 100;

	private static final long REST_INTERVAL = 10 * 1000L;

	private static long restTimes = 0;

	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);


	public static void main(String[] args) throws IOException {
		
		int minutes = 240;
		
		if (args == null || args.length == 0) {
			System.out.println("Usage: java " + ConfTaskExecutor.class.getName() + " MINUTES");
			return;
		}
		
		try {
			minutes = Integer.parseInt(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		long border = 0;

		ConfService confService = AppContextFactory.getAppContext().getBean(ConfService.class);
		while (true) {
			try {
				/*
				 * 这里获取一个会议列表去构造会议状态同步的任务，包括：
				 * 1、正在进行的会议
				 * 2、开始时间在当前时间点之前，但会议状态是未开始的
				 * 3、开始时间在当前时间点之后一段时间内的会议
				 */
				long time1=System.currentTimeMillis();
				List<ConfBase> confs = confService.getListForStatusMonitor(minutes * 60 * 1000L, FETCH_SIZE);
				long time2=System.currentTimeMillis();
				logger.info("Get Successed And  Opening  Conf List  Use Time :" +(time2-time1) +" ms");
				// 运行索引任务
				if (confs != null && !confs.isEmpty()) {
					restTimes = 0;
					for (ConfBase conf : confs) {
						if (conf == null) {
							border = 0;// indexLog.getId(); // 跳过，继续
							continue;
						}
						Runnable task = new ConfTask(conf);
						
						logger.info("Now to do conf status check and sync for id:" + conf.getId());
						executor.execute(task);
						border = conf.getId();
					}
					//continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("System run exception:" + e.getLocalizedMessage());
			}

			// no task need to handle
			restTimes++;
			System.out.println(DateUtil.formatDate(new Date())
					+ "Sleep 10 seconds for next status checking");
			
			try {
				Thread.sleep(REST_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
