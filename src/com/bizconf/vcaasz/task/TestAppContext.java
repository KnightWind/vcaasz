package com.bizconf.vcaasz.task;

import com.bizconf.vcaasz.service.EventLogService;
import com.libernate.support.spring.LiberAppContext;

public class TestAppContext {
	public static void main(String []args) {
		LiberAppContext appContext = AppContextFactory.getAppContext();
		EventLogService service = appContext.getBean(EventLogService.class);
		boolean b = service.saveEventLog(1, (short)1,(short)1, "", (short)1, "", null);
		System.out.println(b);
	}
}
