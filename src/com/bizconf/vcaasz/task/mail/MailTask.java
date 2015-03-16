package com.bizconf.vcaasz.task.mail;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.entity.EmailInfo;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.task.AppContextFactory;
/**
 * 发送邮件任务
 * 
 * @author Chris Gao
 *
 */
public class MailTask extends Thread implements Runnable {
	private final Logger logger=Logger.getLogger(MailTask.class);
	
	EmailInfo mail;
	
	static final EmailService emailService = AppContextFactory.getAppContext().getBean(EmailService.class);
	
	public MailTask (EmailInfo mail) {
		this.mail = mail;
	}
	
	public void run() {
		try {
			long time1=System.currentTimeMillis();
			boolean result = emailService.send(mail);
			long time2=System.currentTimeMillis();
			logger.info("Send Email Use Time : " +(time2-time1)+" ms" );
			if (result) {
				emailService.updateSucceedEmailById(mail.getId());
				logger.info("Mail task for :" + mail.getId() + " success.");
			}
			else{
				emailService.updateUnSucceedEmailById(mail.getId());
				logger.info("Mail task for :" + mail.getId() + " failed, waiting for retry.");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
