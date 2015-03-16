package com.bizconf.vcaasz.action;

import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("site")

public class SiteStatusController {
	

	@AsController(path = "error/locked")
	public Object locked() {
		return new ActionForward.Forward("/jsp/common/site_locked.jsp");
		
	}
	@AsController(path = "error/expire")
	public Object expire() {
		return new ActionForward.Forward("/jsp/common/site_expire.jsp");
		
	}
	@AsController(path = "error/uneffe")
	public Object uneffe() {
		return new ActionForward.Forward("/jsp/common/site_uneffe.jsp");
		
	}

}
