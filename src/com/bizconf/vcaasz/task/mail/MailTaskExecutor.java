package com.bizconf.vcaasz.task.mail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.task.AppContextFactory;
import com.bizconf.vcaasz.task.support.TaskExecutorFactory;

/**
 * 邮件发送任务执行器
 * <p>
 * 
 * @Author Chris Gao [gaohl81@gmail.com]
 */
public class MailTaskExecutor {

	private static final Log logger = LogFactory.getLog(MailTaskExecutor.class);

	private static final int THREAD_POOL_SIZE = 2;

	private static final int FETCH_SIZE = 100;

	private static final long REST_INTERVAL = 10 * 1000L;

	private static long restTimes = 0;

	private static final Executor executor = TaskExecutorFactory
			.newExecutor(THREAD_POOL_SIZE);

	public static void main(String[] args) throws IOException {
		long border = 0;
		
		EmailService emailService = AppContextFactory.getAppContext().getBean(EmailService.class);
		
		while (true) {
			try {
				logger.info("begin to fetch mail info, border:" + border + ", fetch size:" + FETCH_SIZE);
				long time1=System.currentTimeMillis();
				List<EmailInfo> emails = emailService.getUnSendEmailListByStartId(border, FETCH_SIZE);
				long time2=System.currentTimeMillis();
				
				logger.info("GetUnSendEmail Use Time" +(time2-time1)+" ms" );
				logger.info("fetch mail info finished, size:" + (emails == null ? "null" : emails.size()));
				
				// 运行索引任务
				if (emails != null && !emails.isEmpty()) {
					restTimes = 0;
					for (EmailInfo email : emails) {
						Runnable task = new MailTask(email);
						if (task == null) {
							logger.info("No task found for:" + email);
							border = 0;// indexLog.getId(); // 跳过，继续
							continue;
						}
						logger.info("Now to do mail task for id:" + email.getId());
						executor.execute(task);
						border = email.getId();
					}
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("System run exception:" + e.getLocalizedMessage());
			}

			// no task need to handle
			restTimes++;
			logger.info(DateUtil.formatDate(new Date())
					+ ": There is no mail task, sleep 10 seconds. Last task Id: "
					+ border);
			
			// rest time exceed 3, resend failed emails
			/*if (restTimes > 3) {
				System.out.println("Now to begin task from initial position for failed mails.");
				restTimes = 0;
				border = 0;
				continue;
			}*/
			try {
				Thread.sleep(REST_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
