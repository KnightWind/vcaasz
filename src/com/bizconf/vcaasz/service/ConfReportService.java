package com.bizconf.vcaasz.service;

import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfNotification;

/**
 * @desc 会议报告处理
 * @author Administrator darren
 * @date 2014-6-25
 */
public interface ConfReportService {

	
	/**
	 * @desc 针对结束的会议，请求zoomAPI接口并且将其保存到数据库表中
	 * @param zoomId zoom的会议id 唯一性
	 * @param hostId 该会议的主持人zoom上的用户id
	 * @return boolean
	 * */
	public boolean saveConfReportInfo(String zoomId, String hostId);

	/**
	 * 对接收的参数进行保存
	 * */
	public ConfNotification saveNotification(ConfNotification confNotification);
	
	
	
	
	/**
	 * 根据会议主持人ID和会议zoomID获取会议的信息
	 * @param zoomId
	 * @param hostId
	 * @return
	 */
	public ConfBase getConfBaseInfoByZoomIdAndHost(String zoomId,int hostId);

	
	
}
