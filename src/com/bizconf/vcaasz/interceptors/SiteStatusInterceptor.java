package com.bizconf.vcaasz.interceptors;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.interceptor.SysInterceptorExt;

/**
 * 
 * @author zhaost
 *
 */
@Service
public class SiteStatusInterceptor implements SysInterceptorExt {

	@Autowired 
	SiteService siteService;
	@Override
	public Object doAfter(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws IOException {
		System.out.println(System.currentTimeMillis()+"-after");
		
		return null;
	}

	@Override
	public Object doBefore(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String brand = SiteIdentifyUtil.getCurrentBrand();

		if(!StringUtil.isEmpty(brand)){
			SiteBase siteBase=siteService.getSiteBaseBySiteSign(brand);
			Date effeDate=null;
			Date expireDate=null;
			Integer lockFlag=null;
			Date nowGmtDate=DateUtil.getGmtDate(null);
			if(siteBase!=null && siteBase.getId()!=null && siteBase.getId().intValue() > 0){
				effeDate=siteBase.getEffeDate();
				expireDate=siteBase.getExpireDate();
				expireDate=DateUtil.addDateSecond(expireDate, 24*60 * 60 -1);
				lockFlag=siteBase.getLockFlag();
			}
			if(effeDate!=null && nowGmtDate.before(effeDate)){//未到生效日期
				response.sendRedirect("/site/error/uneffe");
				return null;
			}
			if(expireDate!=null && nowGmtDate.after(expireDate)){//站点已过期
				response.sendRedirect("/site/error/expire");
				return null;
			}
			if(lockFlag!=null && ConstantUtil.LOCKFLAG_LOCKED.equals(lockFlag)){//站点已锁定
				response.sendRedirect("/site/error/locked");
				return null;
			}
		}
		return null;
	}

	@Override
	public int getPriority() {
		return 0;
	}

}
