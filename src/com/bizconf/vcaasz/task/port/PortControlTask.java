package com.bizconf.vcaasz.task.port;
import com.bizconf.vcaasz.entity.License;
import com.bizconf.vcaasz.service.LicService;
import com.bizconf.vcaasz.task.AppContextFactory;

/**
 * 端口更改到期处理任务
 * 
 * 
 */
public class PortControlTask extends Thread implements Runnable {

	License lic;
	
	static final LicService licService = AppContextFactory.getAppContext()
			.getBean(LicService.class);
	
	public PortControlTask(License lic) {
		this.lic = lic;
	}

	public void run() {
		try {
			boolean result = false;
			if (lic != null) {
				result = licService.appleyLicenses(lic);
			}
			if (result) {
				System.out.println("site expired task for :" + lic
						+ " success.");
			} else {
				System.out.println("site expired task for :" + lic
						+ " failed, waiting for retry.");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
