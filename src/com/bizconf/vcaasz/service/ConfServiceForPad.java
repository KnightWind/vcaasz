package com.bizconf.vcaasz.service;

import java.util.Date;
import java.util.List;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;

public interface ConfServiceForPad {
	public List<ConfBase> getConfListForPad(UserBase userBase,Date startDate,Date endDate,String queryType,
												int pageNum,int pageSize,String startDateSort,String confType);
	
	public ConfBase getConfBaseByHwId(String hwId);
	
	public ConfBase getConfBaseByCompereSecure(String compereSecure);
	public ConfBase getConfBaseByUserSecure(String userSecure);
	
	public List<ConfUser> getUserListByHwId(String hwId);

}
