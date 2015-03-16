package com.bizconf.vcaasz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bizconf.vcaasz.exception.OuterConfException;

public interface OuterService {
	
	/**
	 * 查询服务器时间
	 * jack
	 * 2013-12-10
	 */
	public String getServerTime(String xmlString);
	
	/**
	 * 通过URL直接入会
	 * jack
	 * 2013-12-17
	 */
	public void joinConf(String xmlString, HttpServletRequest request, HttpServletResponse response) throws OuterConfException;
	
	/**
	 * 按月查询活动记录
	 * jack
	 * 2013-12-10
	 */
	public String getConfListByMonth(String xmlString);
	
	/**
	 * 查询单个活动中用户进出记录
	 * jack
	 * 2013-12-10
	 */
	public String getConfUserLog(String xmlString);
	
	/**
	 * 查询当前时刻剩余的点数
	 * jack
	 * 2013-12-10
	 */
	public String getLicenceNum(String xmlString);
	
	/**
	 * 查询单个活动的状态
	 * jack
	 * 2013-12-10
	 */
	public String getConfStatus(String xmlString);
	
	/**
	 * 查询单个正在进行中活动的在线人数
	 * jack
	 * 2013-12-10
	 */
	public String getUserNumOL(String xmlString);
	 
	/**
	 * 查询单个正在进行中活动的在线人数列表
	 * jack
	 * 2013-12-10
	 */
	public String getUserOLList(String xmlString);
 


	/**
	 * 获取导出Excel的路径
	 * @param xmlString
	 * @return
	 */
	public String getExportExcelPath(String xmlString);
	
	 

}
