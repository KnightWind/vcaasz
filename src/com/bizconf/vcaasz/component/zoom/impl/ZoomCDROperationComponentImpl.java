package com.bizconf.vcaasz.component.zoom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.component.zoom.HttpURLUtil;
import com.bizconf.vcaasz.component.zoom.ZoomCDROperationComponent;

/**
 * @desc 
 * @author Administrator
 * @date 2014-6-25
 */
@Component
public class ZoomCDROperationComponentImpl implements ZoomCDROperationComponent {
	
	
	private final Logger logger = Logger.getLogger(ZoomCDROperationComponentImpl.class);
	@Override
	public Map<String, Object> getZoomCDRByUser(String apiKey,String apiToken,String hostId, String dateFrom, String dateTo,
			int pageSize, int pageNumber) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
		//2014-12-24 add by Darren
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("user_id", hostId);
		params.put("from", dateFrom);
		params.put("to", dateTo);
		logger.info("get user report from "+dateFrom+" to :"+dateTo);
		//ReportWriter.getInstance().writerData("will get user report from zoom user_id:"+hostId+" from:"+dateFrom+"  to :"+dateTo);
		if(pageSize>0){
			params.put("page_size", pageSize);
		}
		if(pageNumber>0){
			params.put("page_number", pageNumber);
		}
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getCDRReport,
				params);
	}

	public static void main(String[] args) {
		//http://meeting.vcaas.cn/common/getNotification?id=1810859814&status=ENDED&host_id=zuDHxhSOQQuXXD8VSK-0XA
		List<String> list = new ArrayList<String>();
		list.add("b0LGDGi0TPevvAb5OAX1gg");//yamaxun003
		list.add("TQsQqWppTpGYvG2zVzjzJw");//yamaxun002
		
		for(String zoomId :list){
			Map<String, Object> params = HttpReqeustUtil.getAuthMeetMap();
			params.put("api_key", "XEWOUyMioQSPSz9isSlhEHw4n1Tnupok7J21");
			params.put("api_secret", "sAeIJfawLcnSMgggRFguMCNsd2dtEYCn2JtY");
			params.put("user_id", zoomId);
			params.put("from", "2014-01-01");
			params.put("to", "2014-12-31");
			Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getCDRReport,
					params);
			System.out.println("meetings:"+retMap.get("meetings"));
		}
		
	}
	
}
