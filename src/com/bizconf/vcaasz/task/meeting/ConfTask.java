package com.bizconf.vcaasz.task.meeting;

import java.util.List;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.task.AppContextFactory;

/**
 * 同步会议状态的任务
 * 
 * @author Chris Gao
 *
 */
public class ConfTask extends Thread implements Runnable {
	private final Logger logger=Logger.getLogger(ConfTask.class);

	// ConfBase conf;
	private List<ConfBase> confList;
	ConfBase conf;

	public ConfTask (ConfBase conf) {
		this.conf = conf;
	}
	
	public ConfTask(List<ConfBase> confList) {
		this.confList = confList;
	}

	public void run() {
		ConfService confService = AppContextFactory.getAppContext().getBean(ConfService.class);
		try {
			long time1=System.currentTimeMillis();
			boolean result = confService.syncConfStatus(conf);
			long time2=System.currentTimeMillis();
			logger.info("ConfServer syncConfStatus Use Time:" +(time2-time1)+" ms");
			if (result) {
				logger.info("Conf status task for :" + conf.getId() + " success.");
			}
			else{
				logger.info("Conf status task for :" + conf.getId() + " failed, waiting for next sync.");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}



}
























/*

ConfBase conf;

public ConfTask (ConfBase conf) {
	this.conf = conf;
}

public void run() {
	ConfService confService = AppContextFactory.getAppContext().getBean(ConfService.class);
	try {
		boolean result = confService.syncConfStatus(conf);
		if (result) {
			System.out.println("Conf status task for :" + conf.getId() + " success.");
		}
		else{
			System.out.println("Conf status task for :" + conf.getId() + " failed, waiting for next sync.");
		}
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
*/
