package com.bizconf.vcaasz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class BrowserUtil {
	private static Logger logger=Logger.getLogger(BrowserUtil.class);

	private static class  REGEX_OS{
		public static String WINDOWS="Windows NT ([\\d\\.]+)";
		public static String WINBIT64="(WOW64)|(Win64)";
		public static String LINUX="Linux (i686)";
//		public static String LUXBIT32="";
//		public static String LUXBIT64="";
		public static String MACOS="Mac OS (X [\\d_]+)";//Intel Mac OS X 10_9_0
//		public static String MACBIT64="";
	}
	
	private static class  REGEX_BROWSER{
		public static String IE="(MSIE\\s|Trident.*rv:)([\\w.]+)";
		public static String IEBIT64="[Xx]64";
		public static String FIREFOX="(Firefox)/([\\w.]+)";
		public static String CHROME="(Chrome)/([\\w.]+)";
		public static String SAFARI="Version\\/([\\w.]+).*(Safari)";
		public static String OPERA="(Opera).+Version\\/([\\w.]+)";
		
	}
//	private static final String BROWSER_REGEX_IE="(MSIE\\s|Trident.*rv:)([\\w.]+)";
//	private static final String BROWSER_REGEX_FIREFOX="(Firefox)/([\\w.]+)";
//	private static final String BROWSER_REGEX_CHROME="(Chrome)/([\\w.]+)";
//	private static final String BROWSER_REGEX_SAFARI="Version\\/([\\w.]+).*(Safari)";
//	private static final String BROWSER_REGEX_OPERA="(Opera).+Version\\/([\\w.]+)";
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0 ";
////		userAgent="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
////		userAgent="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";
////		userAgent="Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0";
////		userAgent="Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko";
////		userAgent="Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36";
////		userAgent="Opera/9.80 (Windows NT 5.1; U; zh-tw) Presto/2.8.131 Version/11.10";
//		userAgent="Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5";
		//userAgnet=Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; MDDSJS) 
		String userAgent="";
		
		//WINXP  IE6
		//WINXP  IE7
		//WINXP  IE8
//		userAgent="=Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; InfoPath.2; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)";
		//WIN2003 IE6
		//WIN2003 IE7
		//WIN2003 IE8
		
		
		//WIN7(32bit) IE8
//		userAgent="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; Alexa Toolbar) ";
		//WIN7(32bit) IE9
		//WIN7(32bit) IE10
		//WIN7(32bit) IE11
		
//		userAgent="Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko ";
		//WIN8(32bit) IE10
//		userAgent="Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; Touch)  ";
		//WIN8(32bit) IE11
		
		
		
		//WIN7(64bit) IE8(32bit)
//		userAgent="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E) ";
		//WIN7(64bit) IE8(64bit)
		userAgent="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E) ";
		//WIN7(64bit) IE9(32bit)
		userAgent="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; MDDSJS) ";
		//WIN7(64bit) IE9(64bit)
		userAgent="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; MDDSJS)";
		//WIN7(64bit) IE10
		//WIN7(64bit) IE11 
//		userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko ";
		//WIN8(64bit) IE10
//		userAgent="Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0; Touch) ";
		//WIN8(64bit) IE11
		
		//WINXP  FireFox
//		userAgent="=Mozilla/5.0 (Windows NT 5.1; rv:24.0) Gecko/20100101 Firefox/24.0";
		//WIN2003  FireFox
		//WIN7(32bit)  FireFox
//		userAgent="Mozilla/5.0 (Windows NT 6.1; rv:25.0) Gecko/20100101 Firefox/25.0";
		//WIN7(64bit)  FireFox
//		userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0 ";
		//WIN8(32bit)  FireFox
		//WIN8(64bit)  FireFox
		
		//WINXP  Chrome
//		userAgent="Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1  ";
		//WIN2003  Chrome
		//WIN7(32bit)  Chrome
//		userAgent="Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36 ";
		//WIN7(64bit)  Chrome
//		userAgent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11 ";
		//WIN8(32bit)  Chrome
		//WIN8(64bit)  Chrome
		
		//WINXP  Safari
		//WIN2003  Safari
		//WIN7(32bit)  Safari
		//WIN7(64bit)  Safari
		//WIN8(32bit)  Safari
		//WIN8(64bit)  Safari
		
		//WINXP  Opera
		//WIN2003  Opera
		//WIN7(32bit)  Opera
		//WIN7(64bit)  Opera
		//WIN8(32bit)  Opera
		//WIN8(64bit)  Opera
		
		//CentOs  FireFox
//		userAgent="Mozilla/5.0 (X11; Linux i686; rv:10.0.5) Gecko/20120606 Firefox/10.0.5 ";
		//CentOs  Chrome
//		userAgent="";
		
		//RedHad  FireFox
		
		//RedHad  Chrome
		
		//MacOs	Safari
		
		//MacOs	Chrome
//		userAgent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";

		long time1 = System.currentTimeMillis();
		Client client = getClient(userAgent);
		logger.error("getClient(userAgent)=" + client);
		long time2 = System.currentTimeMillis();
		logger.error("Use Time = " + (time2 - time1) + "ms");
		client = null;
	}
	
	
	
	
//	private static boolean isOSRight(String userAgent, String osName) {
//		if (StringUtil.isEmpty(userAgent)) {
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		long time1 = 0;
//		long time2 = 0;
//		Pattern pattern = null;
//		Matcher matcher = null;
//		try {
//			time1 = System.currentTimeMillis();
//			if ("Windows".equals(osName)) {
//				pattern = Pattern.compile(REGEX_OS.WINDOWS);
//			} else if ("MacOS".equals(osName)) {
//				pattern = Pattern.compile(REGEX_OS.MACOS);
//			} else if ("Linux".equals(osName)) {
//				pattern = Pattern.compile(REGEX_OS.LINUX);
//			}
//			matcher = pattern.matcher(userAgent);
//			return matcher.find();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pattern = null;
//			matcher = null;
//			time2 = System.currentTimeMillis();
//			logger.info("isOSRight  Create pattern Use Time :" + (time2 - time1) + " ms");
//		}
//		return false;
//	}

	public static boolean isWindows(String userAgent) {
		if (StringUtil.isEmpty(userAgent)) {
			logger.error("Param Error :userAgent is Empty.");
			return false;
		}
		long time1 = 0;
		long time2 = 0;
		Pattern pattern = null;
		Matcher matcher = null;
		try {
			time1 = System.currentTimeMillis();
			pattern = Pattern.compile(REGEX_OS.WINDOWS);
			matcher = pattern.matcher(userAgent);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
			time2 = System.currentTimeMillis();
			logger.info("isWindows Use Time :" + (time2 - time1) + " ms");
		}
		return false;
	}

	public static boolean isMac(String userAgent) {
		if (StringUtil.isEmpty(userAgent)) {
			logger.error("Param Error :userAgent is Empty.");
			return false;
		}
		long time1 = 0;
		long time2 = 0;
		Pattern pattern = null;
		Matcher matcher = null;
		try {
			time1 = System.currentTimeMillis();
			pattern = Pattern.compile(REGEX_OS.MACOS);
			matcher = pattern.matcher(userAgent);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
			time2 = System.currentTimeMillis();
			logger.info("isMac Use Time :" + (time2 - time1) + " ms");
		}
		return false;

	}

	public static boolean isLinux(String userAgent) {
		if (StringUtil.isEmpty(userAgent)) {
			logger.error("Param Error :userAgent is Empty.");
			return false;
		}
		long time1 = 0;
		long time2 = 0;
		Pattern pattern = null;
		Matcher matcher = null;
		try {
			time1 = System.currentTimeMillis();
			pattern = Pattern.compile(REGEX_OS.LINUX);
			matcher = pattern.matcher(userAgent);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
			time2 = System.currentTimeMillis();
			logger.info("isLinux Use Time :" + (time2 - time1) + " ms");
		}
		return false;

		
	}
	
	public static boolean isUbuntu(String userAgent){
		
		return false;
		
	}
	
	private static boolean isBrowseRight(String userAgent, String browserName) {
		if (StringUtil.isEmpty(userAgent)) {
			logger.error("Param Error :userAgent is Empty.");
			return false;
		}
		Pattern pattern = null;
		Matcher matcher = null;
		try {
			if ("IE".equals(browserName)) {
				pattern = Pattern.compile(REGEX_BROWSER.IE);
			} else if ("FireFox".equals(browserName)) {
				pattern = Pattern.compile(REGEX_BROWSER.FIREFOX);
			} else if ("Chrome".equals(browserName)) {
				pattern = Pattern.compile(REGEX_BROWSER.CHROME);
			} else if ("Safari".equals(browserName)) {
				pattern = Pattern.compile(REGEX_BROWSER.SAFARI);
			} else if ("Opera".equals(browserName)) {
				pattern = Pattern.compile(REGEX_BROWSER.OPERA);
			}
			matcher = pattern.matcher(userAgent);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
		}
		return false;
	}
	
	public static boolean isIE(String userAgent) {
		return isBrowseRight(userAgent, "IE");
	}

	public static boolean isFireFox(String userAgent) {
		return isBrowseRight(userAgent, "FireFox");
	}

	public static boolean isChrome(String userAgent) {
		return isBrowseRight(userAgent, "Chrome");
	}

	public static boolean isSafari(String userAgent) {
		return isBrowseRight(userAgent, "Safari");
	}

	public static boolean isOpera(String userAgent) {
		return isBrowseRight(userAgent, "Opera");
	}
	
	
	
	
	/**
	 * 获取Windows系统是32位还是64位系统
	 * @param userAgent
	 * @return
	 * 		32表示 32位操作系统
	 * 		64表示 64位操作系统
	 * 		0表示不是Windows系统
	 * 
	 */
	public static int getWindowsBit(String userAgent) {
		if (isWindows(userAgent)) {
			Pattern pattern = null;
			Matcher matcher = null;
			boolean isBit64 = false;
			try {
				pattern = Pattern.compile(REGEX_OS.WINBIT64);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					isBit64 = true;
				}
				return isBit64 ? 64 : 32;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pattern = null;
				matcher = null;
			}
		}
		return 0;
	}
	
	
	/**
	 * 获取Windows系统是32位还是64位系统
	 * @param userAgent
	 * @return
	 * 		32表示 32位操作系统
	 * 		64表示 64位操作系统
	 * 		0表示不是Windows系统
	 * 
	 */
	private static int getWindowsBitUnCheck(String userAgent) {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean isBit64 = false;
		long time1=System.currentTimeMillis();
		try {
			pattern = Pattern.compile(REGEX_OS.WINBIT64);
			matcher = pattern.matcher(userAgent);
			if (matcher.find()) {
				isBit64 = true;
			}
			return isBit64 ? 64 : 32;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
			long time2=System.currentTimeMillis();
			logger.info(" getWindowsBitUnCheck  use Time="+(time2-time1)+" ms");
		}
		return 0;
	}
	
	/**
	 * 获取Windows系统版本
	 * @param userAgent
	 * @return
	 * 		2000\XP\Server 2003\Vista\Server 2008\Win7\Server 2008 R2\Win8\ Server 2012 \Windows 8.1\Server 2012 R2
	 * 		null 表示不是Windows系统
	 * 
	 * 
	 * 
	 */
	public static String getWindowsVersion(String userAgent){
		if(isWindows(userAgent)){
			Pattern pattern = null;
			Matcher matcher = null;
			try {
				pattern = Pattern.compile(REGEX_OS.WINDOWS);
				matcher = pattern.matcher(userAgent);
				String version="";
				if (matcher.find()) {
					version=matcher.group(1);
				}
				if(!StringUtil.isEmpty(version)){
					if("5.0".equalsIgnoreCase(version.trim())){
						return "2000";
					}else if("5.1".equalsIgnoreCase(version.trim())){
						return "XP";
					}else if("5.2".equalsIgnoreCase(version.trim())){
						return "XP64(Server2003)";
					}else if("6.0".equalsIgnoreCase(version.trim())){
						return "Vista";
					}else if("6.1".equalsIgnoreCase(version.trim())){
						return "7";
					}else if("6.2".equalsIgnoreCase(version.trim())){
						return "8";
					}else if("6.3".equalsIgnoreCase(version.trim())){
						return "8.1";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//			finally {
//				pattern = null;
//				matcher = null;
//			}
		}
		return null;
	}
	/**
	 * 获取Windows系统版本
	 * @param userAgent
	 * @return
	 * 		2000\XP\Server 2003\Vista\Server 2008\Win7\Server 2008 R2\Win8\ Server 2012 \Windows 8.1\Server 2012 R2
	 * 		null 表示不是Windows系统
	 * 
	 * 
	 * 
	 */
	public static String getWindowsVersionUnCheck(String userAgent) {
		Pattern pattern = null;
		Matcher matcher = null;
		long time1=0;
		long time2=0;
		try {
			time1=System.currentTimeMillis();
			pattern = Pattern.compile(REGEX_OS.WINDOWS);
			matcher = pattern.matcher(userAgent);
			String version = "";
			if (matcher.find()) {
				version = matcher.group(1);
			}
			if (!StringUtil.isEmpty(version)) {
				if ("5.0".equalsIgnoreCase(version.trim())) {
					return "2000";
				} else if ("5.1".equalsIgnoreCase(version.trim())) {
					return "XP";
				} else if ("5.2".equalsIgnoreCase(version.trim())) {
					return "XP64(Server2003)";
				} else if ("6.0".equalsIgnoreCase(version.trim())) {
					return "Vista";
				} else if ("6.1".equalsIgnoreCase(version.trim())) {
					return "7";
				} else if ("6.2".equalsIgnoreCase(version.trim())) {
					return "8";
				} else if ("6.3".equalsIgnoreCase(version.trim())) {
					return "8.1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
			time2=System.currentTimeMillis();
			logger.info(" getWindowsVersionUnCheck  use Time="+(time2-time1)+" ms");

		}
		return null;
	}
	
	/**
	 * 获取IE浏览器系统是32位还是64位系统
	 * @param userAgent
	 * @return
	 * 		32表示 32位操作系统
	 * 		64表示 64位操作系统
	 * 		0表示不是Windows系统
	 */
	public static Integer getIEBits(String userAgent){
		if(isIE(userAgent)){
			Pattern pattern = null;
			Matcher matcher = null;
			try {
				pattern = Pattern.compile(REGEX_BROWSER.IEBIT64);
				matcher = pattern.matcher(userAgent);
				boolean isBit64 = false;
				if (matcher.find()) {
					isBit64 = true;
				}
				return isBit64 ? 64 : 32;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
	/**
	 * 获取IE浏览器系统是32位还是64位系统
	 * @param userAgent
	 * @return
	 * 		32表示 32位操作系统
	 * 		64表示 64位操作系统
	 * 		0表示不是Windows系统
	 */
	private static Integer getIEBitsUnCheck(String userAgent) {
		Pattern pattern = null;
		Matcher matcher = null;
		try {
			pattern = Pattern.compile(REGEX_BROWSER.IEBIT64);
			matcher = pattern.matcher(userAgent);
			boolean isBit64 = false;
			if (matcher.find()) {
				isBit64 = true;
			}
			return isBit64 ? 64 : 32;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pattern = null;
			matcher = null;
		}
		return null;
	}

	public static Client getClient(String userAgent) {
		if (StringUtil.isEmpty(userAgent)) {
			logger.error("Param Error :userAgent is Empty.");
			return null;
		}
		Pattern pattern = null;
		Matcher matcher = null;
		Client client = null;
		if (isWindows(userAgent)) {
			client = new Client();
			client.setOSName("Windows");
			// 取操作系统信息
			client.setOSVersion(getWindowsVersionUnCheck(userAgent));
			client.setOSBits(getWindowsBitUnCheck(userAgent));
			if (isIE(userAgent)) {
				client.setBrowserName("IE");
				pattern = Pattern.compile(REGEX_BROWSER.IE);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					client.setBrowserVersion(matcher.group(2));
				}
				client.setBrowserBits(getIEBitsUnCheck(userAgent));
				return client;
			}
			if (isFireFox(userAgent)) {
				client.setBrowserName("FireFox");
				pattern = Pattern.compile(REGEX_BROWSER.FIREFOX);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					client.setBrowserVersion(matcher.group(2));
				}
				return client;
			}
			if (isChrome(userAgent)) {
				client.setBrowserName("Chrome");
				pattern = Pattern.compile(REGEX_BROWSER.CHROME);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					client.setBrowserVersion(matcher.group(2));
				}
				return client;
			}
			if (isSafari(userAgent)) {
				client.setBrowserName("Safari");
				pattern = Pattern.compile(REGEX_BROWSER.SAFARI);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					client.setBrowserVersion(matcher.group(1));
				}
				return client;
			}
			if (isOpera(userAgent)) {
				client.setBrowserName("Opera");
				pattern = Pattern.compile(REGEX_BROWSER.OPERA);
				matcher = pattern.matcher(userAgent);
				if (matcher.find()) {
					client.setBrowserVersion(matcher.group(2));
				}
				return client;
			}
		}
		pattern = null;
		matcher = null;
		return null;
	}
	
	
	public static class Client implements java.io.Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3027165146887817450L;
		private String OSName;
		private String OSVersion;
		private Integer OSBits;
		private String browserName;
		private String browserVersion;
		private Integer browserBits;
		public Client(){
			
		}
		
		public String getOSName() {
			return OSName;
		}
		public void setOSName(String oSName) {
			OSName = oSName;
		}
		public String getOSVersion() {
			return OSVersion;
		}
		public void setOSVersion(String oSVersion) {
			OSVersion = oSVersion;
		}
		public Integer getOSBits() {
			return OSBits;
		}
		public void setOSBits(Integer oSBits) {
			OSBits = oSBits;
		}

		public String getBrowserName() {
			return browserName;
		}

		public void setBrowserName(String browserName) {
			this.browserName = browserName;
		}

		public String getBrowserVersion() {
			return browserVersion;
		}

		public void setBrowserVersion(String browserVersion) {
			this.browserVersion = browserVersion;
		}

		public Integer getBrowserBits() {
			return browserBits;
		}

		public void setBrowserBits(Integer browserBits) {
			this.browserBits = browserBits;
		}
		
		@Override
		public String toString() {
			return "Client [OSName=" + OSName + ", OSVersion=" + OSVersion + ", OSBits=" + OSBits + ", browserName="
					+ browserName + ", browserVersion=" + browserVersion + ", browserBits=" + browserBits + "]";
		}
		
	}
	
	
/*
	
	

*/
	
//	
//	public static String getIEDigits(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return null;
//		}
//		Pattern pattern=null;
//		Matcher matcher=null;
//		String tmpRegex="([Ww]in64)";
//		if(isIE(userAgent)){
//			pattern = Pattern.compile(BROWSER_REGEX_IE);
//			if(pattern==null){
//				logger.error("Error :Check IE Version pattern is null.");
//				return null;
//			}
//			matcher = pattern.matcher(userAgent);
//			matcher = pattern.matcher(userAgent);
//			if(matcher==null){
//				logger.error("Error :Check IE Version matcher is null.");
//				return null;
//			}
//			double version=0;
//			if (matcher.find()) {
//				String versionStr=matcher.group(2);
//				version=Math.round((Double.parseDouble(versionStr)*100))/100;
//			}
//			pattern = null;
//			matcher = null;
//			if(version>=11){
//				tmpRegex="WOW64";
//			}
//		}
//		pattern=Pattern.compile(tmpRegex);
//		if(pattern==null){
//			logger.error("Error :pattern is null.");
//			return null;
//		}
//		matcher = pattern.matcher(userAgent);
//		if(matcher==null){
//			logger.error("Error :matcher is null.");
//			return null;
//		}
//		String digitstr="Win32";
//		if(matcher.find()){
//			digitstr="Win64";
//		}
//
//		pattern = null;
//		matcher = null;
//		return digitstr;
//		
//	}
	
//	
//	public static boolean isIE(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		Pattern pattern=Pattern.compile(BROWSER_REGEX_IE);
//		Matcher matcher = pattern.matcher(userAgent);
//		return matcher.find();
////		boolean isExist=matcher.find();
////		int groupCount=matcher.groupCount();
////		logger.error("matcher.groupCount()="+matcher.groupCount());
////		for(int ii=0;ii<=groupCount;ii++){
////			logger.error("matcher.group("+ii+")="+matcher.group(ii));
////		}
////		return isExist;
//	}
//	
//	public static boolean isFireFox(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		Pattern pattern=Pattern.compile(BROWSER_REGEX_FIREFOX);
//		Matcher matcher = pattern.matcher(userAgent);
//		
//
//		return matcher.find(); 
////		boolean isExist=matcher.find();
////		int groupCount=matcher.groupCount();
////		logger.error("matcher.groupCount()="+matcher.groupCount());
////		for(int ii=0;ii<=groupCount;ii++){
////			logger.error("matcher.group("+ii+")="+matcher.group(ii));
////		}
////		return isExist;
//	}
//	
//
//	public static boolean isChrome(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		Pattern pattern=Pattern.compile(BROWSER_REGEX_CHROME);
//		Matcher matcher = pattern.matcher(userAgent);
//		return matcher.find(); 
////		boolean isExist=matcher.find();
////		int groupCount=matcher.groupCount();
////		logger.error("matcher.groupCount()="+matcher.groupCount());
////		for(int ii=0;ii<=groupCount;ii++){
////			logger.error("matcher.group("+ii+")="+matcher.group(ii));
////		}
////		return isExist;
//	}
//	
//	
//	public static boolean isSafari(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		Pattern pattern=Pattern.compile(BROWSER_REGEX_SAFARI);
//		Matcher matcher = pattern.matcher(userAgent);
//		return matcher.find(); 
////		boolean isExist=matcher.find();
////		int groupCount=matcher.groupCount();
////		logger.error("matcher.groupCount()="+matcher.groupCount());
////		for(int ii=0;ii<=groupCount;ii++){
////			logger.error("matcher.group("+ii+")="+matcher.group(ii));
////		}
////		return isExist;
//	}
//	
//	public static boolean isOpera(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return false;
//		}
//		Pattern pattern=Pattern.compile(BROWSER_REGEX_OPERA);
//		Matcher matcher = pattern.matcher(userAgent);
//		return matcher.find(); 
////		boolean isExist=matcher.find();
////		int groupCount=matcher.groupCount();
////		logger.error("matcher.groupCount()="+matcher.groupCount());
////		for(int ii=0;ii<=groupCount;ii++){
////			logger.error("matcher.group("+ii+")="+matcher.group(ii));
////		}
////		return isExist;
//	}
//	
//	
//	//)|((Chrome)(\\/)([\\w.]+))
//	public static Browser getBrowser(String userAgent){
//		if(StringUtil.isEmpty(userAgent)){
//			logger.error("Param Error :userAgent is Empty.");
//			return null;
//		}
//		Browser browser=null;
//		if (isIE(userAgent)) {
//			browser = new Browser();
//			browser.setDigits(getIEDigits(userAgent));
//			Pattern pattern = Pattern.compile(BROWSER_REGEX_IE);
//			Matcher matcher = pattern.matcher(userAgent);
//			browser.setName("IE");
//			if (matcher.find()) {
//				browser.setVersion(matcher.group(2));
//			}
//			pattern = null;
//			matcher = null;
//		}
//		if(isFireFox(userAgent) || isChrome(userAgent) || isSafari(userAgent) || isOpera(userAgent)){
//			String tmpRegex=BROWSER_REGEX_FIREFOX;
//			if(isChrome(userAgent) ){
//				tmpRegex=BROWSER_REGEX_CHROME;
//			}
//			if(isSafari(userAgent) ){
//				tmpRegex=BROWSER_REGEX_SAFARI;
//			}
//			if(isOpera(userAgent) ){
//				tmpRegex=BROWSER_REGEX_OPERA;
//			}
//			browser = new Browser();
//			Pattern pattern = Pattern.compile(tmpRegex);
//			Matcher matcher = pattern.matcher(userAgent);
//			if (matcher.find()) {
//				browser.setName(matcher.group(1));
//				browser.setVersion(matcher.group(2));
//			}
//			pattern = null;
//			matcher = null;
//		}
//		
//		return browser;
//	}
//	
//	
//	public static class Browser{
//		private String name;
//		private String version;
//		private String digits;
//		public Browser(){
//			
//		}
//		
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getVersion() {
//			return version;
//		}
//		public void setVersion(String version) {
//			this.version = version;
//		}
//		public String getDigits() {
//			return digits;
//		}
//		public void setDigits(String digits) {
//			this.digits = digits;
//		}
//
//		@Override
//		public String toString() {
//			return "Browser [name=" + name + ", version=" + version + ", digits=" + digits + "]";
//		}
//	}

}

/*
 * •32-bit IE10 on 32-bit Windows:
Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0) 
•32-bit IE10 on 64-bit Windows:
Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0) 
•64-bit IE10 on 64-bit Windows:
Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0) 

 * */
 