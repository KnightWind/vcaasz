package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.entity.IpLocator;
import com.bizconf.vcaasz.entity.MS;
import com.bizconf.vcaasz.service.IpLocatorService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;


@Service
public class IpLocatorServiceImpl extends BaseService implements IpLocatorService{
	private static final String SERVERS_PREFIX = "download_servers_";
	
	private static final String SERVERS_DEFAULT = SERVERS_PREFIX + "default";
	
	static final String BRAND_GO = "go";
	
	static ResourceBundle rb = null;
	
	static List<MS> MS_LIST = null;
	
	static long lastCheckTime = 0L;
	
	static long checkInterval = 30 * 1000L;
	
	static Object mutex = new Object();
	
	@Override
	public String getDownloadURL(String ispCode) {
		/*
		reloadServers();
		if (!rb.containsKey(SERVERS_PREFIX + ispCode)) {
			return rb.getString(SERVERS_DEFAULT);
		}
		
		String [] servers = rb.getString(SERVERS_PREFIX + ispCode).split(",");
		if (servers.length == 0) {
			return rb.getString(SERVERS_DEFAULT);
		}
		
		int index = (int) (System.currentTimeMillis() % servers.length);
		return servers[index];
		*/
		/*if (!StringUtils.isEmpty(IPLocatorUtil.getCurrentDownloadServer())) {
			logger.info("by net speed check, getCurrentDownloadServer: " + IPLocatorUtil.getCurrentDownloadServer());
			return this.getFormatedDownloadUrl(IPLocatorUtil.getCurrentDownloadServer());
		}*/
		reloadServers();
		List<MS> downloadMS = new ArrayList<MS>();
		if (MS_LIST != null) {
			for (MS ms : MS_LIST) {
				if (ms.isServeForIspDownload(ispCode)) {
					downloadMS.add(ms);
				}
			}
		}
		if (downloadMS.size() == 0) {
			if (logger.isWarnEnabled()) {
				logger.warn("getDownloadURL by:" + ispCode +", not hit any server now to get all in use servers.");
			}
			if (MS_LIST != null) {
				for (MS ms : MS_LIST) {
					if (ms.isInUse()) {
						downloadMS.add(ms);
					}
				}
			}
		}
		if (downloadMS.size() == 0) {
			if (logger.isWarnEnabled()) {
				logger.warn("getDownloadURL by:" + ispCode +", not hit any server now to get from default.");
			}
			return this.getFormatedDownloadUrl("www");
		}
		int index = (int) (System.currentTimeMillis() % downloadMS.size());
		MS downloadRandomMS = downloadMS.get(index);
		String downloadUrl = this.getFormatedDownloadUrl(downloadRandomMS.getDomainName());
		if (logger.isInfoEnabled()) {
			logger.info("getDownloadURL by:" + ispCode + ", downloadURL:" + downloadUrl);
		}
		return downloadUrl;
	}

	@Override
	public String getISPCodeByIP(String ip) {
		if(StringUtil.isEmpty(ip)){
			return MS.OTHERS;
		}
		String ispCode="";
		long ipNum=StringUtil.ipToNumber(ip);
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select * from t_ip_locator where start_num<=? and end_num>=? ORDER BY end_num ASC  LIMIT 1 ");
		Object[] values=new Object[]{ipNum,ipNum};
		IpLocator ipLocator=null;
		try {
			ipLocator =libernate.getEntityCustomized(IpLocator.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(ipLocator!=null){
			ispCode=ipLocator.getIspCode();
		}
		ipLocator=null;
		if(ispCode==null || ispCode.trim().length()<=0){
			ispCode = MS.OTHERS;
		}
		return ispCode;
	}
	
	private void reloadServers() {
		if (MS_LIST == null || rb == null || (System.currentTimeMillis() - lastCheckTime) > checkInterval) {
			synchronized (mutex) {
				if (MS_LIST == null || rb == null || (System.currentTimeMillis() - lastCheckTime) > checkInterval) {
					//reload download servers
					try {
						rb = ResourceBundle.getBundle("config.ms");
					}
					catch (Exception e) {
						e.printStackTrace(System.err);
					}
					
					//reload ms servers
					try {
						MS_LIST = libernate.getEntityList(MS.class);
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
					finally {
						lastCheckTime = System.currentTimeMillis();
					}
				}
			}
		}
	}
	
	public static void main(String [] args) {
		IpLocatorServiceImpl instance = new IpLocatorServiceImpl();
//		System.out.println(instance.getMsServers("202.152.177.187"));
//		System.out.println(instance.getMsServers("180.96.23.69"));
//		System.out.println(instance.getMsServersByIspCode("telecom"));
//		System.out.println(instance.getMsServersByIspCode("unicom"));
//		System.out.println(instance.getMsServersByIspCode("a"));
//		System.out.println(instance.getMsServersByIspCode("cernet"));
		String servers = StringUtils.join(instance.getDownloadServers("202.152.177.187"),",");
		System.out.println(servers);
	}

	@Override
	public String getBestDownloadURL(String ip) {
		return getDownloadURL(getISPCodeByIP(ip));
	}

	@Override
	public String getMsServers(String clientIp) {
		String ispCode = this.getISPCodeByIP(clientIp);
		String msServers = this.getMsServersByIspCode(ispCode);
		if (logger.isInfoEnabled()) {
			logger.info("getMsServers clientIp:" + clientIp + ", ispCode:" + 
					ispCode + ", msServers:" + msServers);
		}
		return msServers;
	}
	
	private String getMsServersByIspCode(String ispCode) {
		reloadServers();
		StringBuilder msServers = new StringBuilder("");
		if (MS_LIST != null) {
			for (MS ms : MS_LIST) {
				if (ms.isServeForIsp(ispCode)) {
					msServers.append(ms.getServerIp()+";");
				}
			}
		}
		if (msServers.length() == 0) {
			logger.warn("No available ms servers, get all inUse servers....");
			return getAllInUseServersForException();
		}
		msServers.deleteCharAt(msServers.length()-1);
		if (logger.isInfoEnabled()) {
			logger.info("getMsServersByIspCode ispCode:" + 
					ispCode + ", msServers:" + msServers);
		}
		return msServers.toString();
	}
	
	private String getAllInUseServersForException(){
		reloadServers();
		StringBuilder msServers = new StringBuilder("");
		if (MS_LIST != null) {
			for (MS ms : MS_LIST) {
				if (ms.isInUse()) {
					msServers.append(ms.getServerIp()+";");
				}
			}
		}
		msServers.deleteCharAt(msServers.length()-1);
		return msServers.toString();
	}
	
	private String getFormatedDownloadUrl(String domainName) {
		if (domainName.startsWith("http")) {
			if (BRAND_GO.equalsIgnoreCase(SiteIdentifyUtil.getCurrentBrand())) {
				domainName += "-" + BRAND_GO;
			}
			logger.info("get download url with CDN config: " + domainName);
			return domainName;
		}
		String downloadUrl = "http://%s:80/download" + (BRAND_GO.equalsIgnoreCase(SiteIdentifyUtil.getCurrentBrand()) ? "-" + BRAND_GO : "");
		return String.format(downloadUrl, domainName+".confcloud.cn");
	}

	@Override
	public String [] getDownloadServers(String ip) {
		String ispCode = this.getISPCodeByIP(ip);
		reloadServers();
		List<MS> downloadMS = new ArrayList<MS>();
		
		//获取可用的服务器组
		if (MS_LIST != null) {
			for (MS ms : MS_LIST) {
				if (ms.isServeForIspDownload(ispCode)) {
					downloadMS.add(ms);
				}
			}
		}
		
		//获取所有在用的服务器
		if (downloadMS.size() == 0) {
			if (logger.isWarnEnabled()) {
				logger.warn("getDownloadServers by:" + ispCode +", not hit any server now to get all in use servers.");
			}
			if (MS_LIST != null) {
				for (MS ms : MS_LIST) {
					if (ms.isInUse()) {
						downloadMS.add(ms);
					}
				}
			}
		}
		
		//默认
		if (downloadMS.size() == 0) {
			return new String [] {"www"};
		}
		
		String [] servers = new String[downloadMS.size()];
		for (int i=0; i<downloadMS.size(); i++) {
			servers[i] = downloadMS.get(i).getDomainName();
			if (logger.isInfoEnabled()) {
				logger.info(servers[i]);
			}
		}
		return servers;
	}

}
