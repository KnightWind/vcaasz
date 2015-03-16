package com.bizconf.vcaasz.action.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfNotification;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ConfReportService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * @desc 
 * @author Administrator
 * @date 2014-6-24
 */
@ReqPath("")
public class PushNotificationController extends BaseController{

	private final Logger logger = Logger.getLogger(PushNotificationController.class);
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ConfService confService;
	
	@Autowired
	private ConfReportService confReportService;
	
	//meeting.vcaas.cn/common/getNotification?id=1590628190&status=ENDED&host_id=zuDHxhSOQQuXXD8VSK-0XA
	/**
	 * @param id : zoom会议id
	 * @param uuid ：zoom会议uuid
	 * @param status：zoom会议状态, STARTED/ENDED
	 * @param hostId：主持人id
	 * */
	@AsController(path = "getNotification")
	public void pushNotification(@CParam("id") String zoomId,@CParam("uuid") String uuid,
			@CParam("status") String status,@CParam("host_id") String hostId,
			HttpServletRequest request){
		//"Authorization": "Basic " + base64_encode(username + ":" + password)
		String Authorization = request.getHeader("Authorization");
		logger.info("Authorization==>"+Authorization);
		
		//首先将接收到的参数进行保存，保存到t_conf_notification
		ConfNotification confNotification = new ConfNotification();
		confNotification.setHostId(hostId);
		confNotification.setStatus(status);
		confNotification.setUuid(uuid);
		confNotification.setZoomId(zoomId);
		confNotification.setRecordDate(new Date());
		ConfNotification falg = confReportService.saveNotification(confNotification);
		logger.info("confNotification save() falg==>"+falg);
		
		Integer confstatus = null;
		if(status!=null && "STARTED".equals(status)){
			confstatus = new Integer(ConfConstant.CONF_STATUS_OPENING);
		}else if(status!=null && "ENDED".equals(status)){
			confstatus = new Integer(ConfConstant.CONF_STATUS_FINISHED);
		}else{
			logger.info("invliad status:" + status);
			return;
		}
		
		UserBase userBase = userService.getUserBaseByZoomUserId(hostId);
		
		
		ConfBase confBase = confService.getConfBaseByZoomId(zoomId);
		if(confBase == null){//会议不存在说明为客户端创建的会议，需要保存
			ConfBase zoomconf = confService.getConfFromZoom(zoomId, hostId);
			if(userBase!=null && zoomconf!=null){
				zoomconf.setSiteId(userBase.getSiteId());
				zoomconf.setConfZoomId(zoomId);
				zoomconf.setConfStatus(confstatus);
				zoomconf.setCompereName(String.valueOf(userBase.getTrueName()));
				zoomconf.setCreateUser(userBase.getId());
				zoomconf.setCompereUser(userBase.getId());
				zoomconf.setDelTime(DateUtil.getGmtDate(null));
				zoomconf.setTimeZone(userBase.getTimeZone());
				zoomconf.setTimeZoneId(userBase.getTimeZoneId());
				confService.saveNewConfBase(zoomconf);//只是单纯的保存到数据库
			}
		}else{//会议存在,修改会议状态
				boolean updateStatus = confService.updateConfStatusForNotify(confBase, confstatus);
				logger.info("update meeting updateStatus = " + updateStatus);
			
				//修改会议的实际开始时间和实际结束时间
				confService.updateConfActulTime(confBase.getId(),confstatus);
		}
		
		//如果会议结束，请求zoomAPI，将会议报告存入数据库一次 status == 3
		if(confstatus!=null && confstatus.equals(ConfConstant.CONF_STATUS_FINISHED)){//表示会已经结束
			boolean saveFlag = confReportService.saveConfReportInfo(zoomId,hostId);
			logger.info("save meeting report info to database = " + saveFlag);
		}
	}
	
}
