package com.bizconf.vcaasz.component.zoom;

import java.util.Map;

/** 
 *   
 * @package com.bizconf.video.component.mcu 
 * @description: zoom 用户相关的操作接口
 * @author Martin
 * @date 2014年6月6日 上午9:57:15 
 * @version 
 */
public interface ZoomCDROperationComponent {
	
	/**
	 * 查询CDR记录 包括会议记录  参会者参会记录等信息
	 * @param userId
	 * @param dateFrom
	 * @param dateTo
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public Map<String, Object> getZoomCDRByUser(String apiKey,String apiToken,String userId,String dateFrom, String dateTo,int pageSize, int pageNumber);
}
