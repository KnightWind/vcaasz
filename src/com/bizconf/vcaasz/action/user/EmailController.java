package com.bizconf.vcaasz.action.user;

import javax.servlet.http.HttpServletRequest;

import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;

@ReqPath("email")
public class EmailController {
	
	
	@AsController(path = "outlook")
	@Get
	public Object emailOutLook(@CParam("confId") String confId,HttpServletRequest request) {
		request.setAttribute("confId", confId);
		return new ActionForward.Forward("/jsp/user/add_calendar_notice.jsp");
	}
}
