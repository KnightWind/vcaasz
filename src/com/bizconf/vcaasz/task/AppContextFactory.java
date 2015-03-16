package com.bizconf.vcaasz.task;

import com.libernate.support.spring.LiberAppContext;

/**
 * 
 * @author Chris Gao [gaohl81@gmail.com]
 *
 */
public class AppContextFactory {
	
	static LiberAppContext appContext = null;
	
	static Object lock = new Object();
	
	public static LiberAppContext getAppContext() {
		if (appContext == null) {
			synchronized (lock) {
				if (appContext == null) {
					appContext = new LiberAppContext();
					appContext.refresh();
				}
			}
		}
		return appContext;
	}
}
