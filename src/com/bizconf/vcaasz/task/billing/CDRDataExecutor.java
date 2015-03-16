package com.bizconf.vcaasz.task.billing;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.bizconf.vcaasz.component.CDRPerProcessor;

/**
 * @Author 
 */
public class CDRDataExecutor {

	private static final Log logger = LogFactory.getLog(CDRDataExecutor.class);
 

	private static final long REST_INTERVAL = 3600*1000L;// 1h 

 
	public static void main(String[] args) throws IOException {
		while (true) {
			try {
				
				CDRPerProcessor.getInstance().PerProcess();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("CDRPerProcessor run exception:" + e.getLocalizedMessage());
			}
			try {
				Thread.sleep(REST_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.info("sleep thread error ");
			}
		}
	}
}
