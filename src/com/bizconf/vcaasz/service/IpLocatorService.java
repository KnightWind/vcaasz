package com.bizconf.vcaasz.service;

public interface IpLocatorService {
 

	/**
	 * 根据IP地址获取ISP的Code，通过client IP
	 * @param ip
	 * @return
	 */
	public String getISPCodeByIP(String ip);
	
	/**
	 * 通过ISP信息获取下载地址
	 * 
	 * @param ispCode
	 * @return
	 */
	public String getDownloadURL(String ispCode);
	
	/**
	 * 获取最优下载路径
	 * 
	 * @param ip
	 * @return
	 */
	public String getBestDownloadURL(String ip);
	
	/**
	 * 获取下载服务器组
	 * 
	 * @param ip
	 * @return
	 */
	public String [] getDownloadServers(String ip);
	
	/**
	 * 通过clientIP 获取到可用的服务器组合
	 * 
	 * @param clientIp
	 * @return
	 */
	public String getMsServers(String clientIp);
}
