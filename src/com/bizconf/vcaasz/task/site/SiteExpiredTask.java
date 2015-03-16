package com.bizconf.vcaasz.task.site;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.task.AppContextFactory;

/**
 * 站点到期处理任务
 * 
 * @author Chris Gao [gaohl81@gmail.com]
 * 
 */
public class SiteExpiredTask extends Thread implements Runnable {

	SiteBase siteBase;

	static final SiteService siteService = AppContextFactory.getAppContext()
			.getBean(SiteService.class);

	public SiteExpiredTask(SiteBase siteBase) {
		this.siteBase = siteBase;
	}

	public void run() {
		try {
			boolean result = false;

			if (siteBase != null) {
				result = siteService.lockSiteById(siteBase.getId());
			}
			if (result) {
				System.out.println("site expired task for :" + siteBase
						+ " success.");
			} else {
				System.out.println("site expired task for :" + siteBase
						+ " failed, waiting for retry.");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
