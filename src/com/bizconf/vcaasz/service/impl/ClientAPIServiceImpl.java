package com.bizconf.vcaasz.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ClientConstant;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfLog;
import com.bizconf.vcaasz.entity.JoinRandom;
import com.bizconf.vcaasz.entity.MsServer;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.SiteLogic;
import com.bizconf.vcaasz.service.ClientAPIService;
import com.bizconf.vcaasz.service.ConfLogService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.IpLocatorService;
import com.bizconf.vcaasz.service.MsService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.Base64;

/**
 * Client启动相关接口的实现
 * 
 * 
 * @author Chris Gao
 *
 */
@Service
public class ClientAPIServiceImpl extends BaseService implements
		ClientAPIService {

//	private static final String CLIENT_WEB_URL = "http://%s.confcloud.cn:80/openapi/ClientAPI";
//
//	private static String CLIENT_DOWNLOAD_URL = null;//"http://%s.confcloud.cn:80/download";
//
//	static String SERVER_IP = null;
//	static String CLUSTER_ID = null;
//	static String DOWNLOAD_DEFAULT=null;
//	static String DOWNLOAD_SERVERS=null;
//	
//	static {
//		try {
//			ResourceBundle rb = ResourceBundle.getBundle("/config/ms");
//			SERVER_IP = rb.getString("server_ip");
//			CLUSTER_ID = rb.getString("cluster_id");
//			DOWNLOAD_DEFAULT = rb.getString("download_default");
//			DOWNLOAD_SERVERS = rb.getString("download_servers");
//		}
//		catch (Exception e) {
//			e.printStackTrace(System.err);
//		}
//	}

	@Autowired
	ConfService confService;
	@Autowired
	SiteLogic siteLogic;

	@Autowired
	ConfLogService confLogService;
	@Autowired
	IpLocatorService ipLocatorService;
	@Autowired
	MsService msService;
	
	@Override
	public void clientInvite(String email, String content, int confId,
			String confPasswd, String lang, String userName) {

	}

	@Override
	public String makeSuffixForSetup(JoinRandom random) {
//		JoinRandom random =null;// makeRandom(conf, user,userRole);
		if (random == null) {
			return null;
		}
		String initParam = buildStartUpParam(random.getId(), random.getLanguage());
		return initParam;
	}

	@Override
	public String makePreParam(JoinRandom random,String ip) {
//		JoinRandom random = makeRandom(conf, user,userRole);
		if (random == null) {
			return null;
		}
		String preParam = buildPreParam(random.getId(), random.getLanguage(), false,ip);
		return preParam;
	}
	
	@Override
	public String getPreParam(long random,String ip) {
		JoinRandom joinRandom;
		try {
			joinRandom = libernate.getEntity(JoinRandom.class, random);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		if (joinRandom == null) {
			return null;
		}
		String preParam = buildPreParam(joinRandom.getId(), joinRandom.getLanguage(), true, ip);
		return preParam;
	}

	@Override
	public String getClientParam(long random) {
		JoinRandom joinRandom;
		try {
			joinRandom = libernate.getEntity(JoinRandom.class, random);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		if (joinRandom == null) {
			return null;
		}
		
		ConfBase confBase = confService.getConfBasebyConfId(joinRandom.getConfId());
		if (confBase == null) {
			return null;
		}
		
		
		
		StringBuilder params = new StringBuilder();
		params.append("<ConfParam>");
//		params.append("<conf_id>" + confBase.getConfHwid() + "</conf_id>");
		if (joinRandom.getUserId() == 0) {
			joinRandom.setUserId(100000000+(int)joinRandom.getId());
		}
		else if (joinRandom.getUserId() < 1000) {
			joinRandom.setUserId(1000000000 + joinRandom.getUserId());
		}
		params.append("<user_id>" + joinRandom.getUserId() + "</user_id>");
		params.append("<user_name>" + string2Unicodes(joinRandom.getUserName()) + "</user_name>");
		params.append("<lang_id>"
				+ (LanguageHolder.getCurrentLanguage().equalsIgnoreCase(LanguageHolder.DEFAULT_LANGUAGE) ? 2052 : 1033)
				+ "</lang_id>");
		params.append("<conf_start_time>" + DateUtil.dateToString(confBase.getStartTime(), null) + "</conf_start_time>");
		params.append("<conf_end_time>" + DateUtil.dateToString(confBase.getEndTime(), null) + "</conf_end_time>");
//		params.append("<mtg_mode>" + getConfigByBitIndex(confBase.getFuncBits(), 8) + "</mtg_mode>");
		params.append("<conf_name>" + this.string2Unicodes(confBase.getConfName()) + "</conf_name>");
		params.append("<user_show>"+ClientConstant.USER_SHOW+"</user_show>");
		params.append("<lock_status>"
						+ (confBase.getConfStatus().intValue() == ConfConstant.CONF_STATUS_LOCKED.intValue() ? 1 : 0) 
						+ "</lock_status>");
		params.append("<user_role>" + joinRandom.getUserRole() + "</user_role>");
		params.append("<client_type>"+ClientConstant.CLIENT_TYPE+"</client_type>");
//		params.append("<conf_pass>" + confBase.getUserSecure()+ "</conf_pass>");
		//params.append("<server_ip>"+ string2Unicodes(ConfConstant.SERVER_IP) + "</server_ip>");
//		msService.getMSesBySite(confBase.getSiteId());
		StringBuffer msIpbuBuffer=new StringBuffer();
		StringBuffer msNameBuffer=new StringBuffer();
		List<MsServer> msList=msService.getMSesBySite(confBase.getSiteId());
		if(msList!=null && msList.size() > 0){
			int ii=0;
			for (MsServer msServer : msList) {
				if (msServer != null) {
					if (ii > 0) {
						msIpbuBuffer.append(";");
						msNameBuffer.append(";");
					}
					msIpbuBuffer.append(msServer.getMsIp());
					msNameBuffer.append(msServer.getMsName());
					ii++;
				}
			}
		}
		
//		params.append("<server_ip>"+ string2Unicodes(ipLocatorService.getMsServers(joinRandom.getClientIp())) + "</server_ip>");
		params.append("<server_ip>"+ string2Unicodes(msIpbuBuffer.toString()) + "</server_ip>");
		params.append("<server_name_map>"+ string2Unicodes(msNameBuffer.toString()) + "</server_name_map>");
		params.append("<site_id>"+ConfConstant.CLUSTER_ID+"</site_id>");
		params.append("<access_code>"+ (ConfConstant.SYS_ENABLE_PHONE ? "01058214900" : "") +"</access_code>");  /////////////////电话接入 号
		params.append("<conf_desp>" + confBase.getConfDesc() + "</conf_desp>");
//		params.append("<conf_key>" + confBase.getConfHwid() + "</conf_key>");
		
		String hostKey=confBase.getHostKey();
		if(hostKey==null || "".equals(hostKey.trim())){
//			hostKey=confBase.getCompereSecure();
		}
	//	hostKey=confBase.getCompereSecure();
		params.append("<host_key>" + Base64.encode(hostKey,"utf8") + "</host_key>");
		params.append("<conf_tag>"+ClientConstant.CONF_TAG+"</conf_tag>");
		params.append("<layout_model>"+ClientConstant.LAYOUT_MODEL+"</layout_model>");
//		params.append("<video_maxnum>" + confBase.getMaxVideo() + "</video_maxnum>");
//		params.append("<audio_maxnum>" + confBase.getMaxAudio() + "</audio_maxnum>");
//		params.append("<video_width>"+(IntegerUtil.parseInteger(confBase.getDefaultDpi())+1)+"</video_width>");//TODO:
//		params.append("<video_max_width>"+(IntegerUtil.parseInteger(confBase.getMaxDpi())+1)+"</video_max_width>");//TODO:
		params.append("<mcu_mode>"+ClientConstant.MCU_MODEL+"</mcu_mode>");
		params.append("<ipad_video_enable>"+ClientConstant.IPAD_VIDEO_ENABLE+"</ipad_video_enable>");
		params.append("<conf_status>" + confBase.getConfStatus() + "</conf_status>");
		params.append("<max_time>" + confBase.getDuration() + "</max_time>");// 会议最大时长，分钟为单位
		// ?
//		params.append("<crypt_key>"+ confBase.getCryptKey() +"</crypt_key>");
		//兼容老会议 - Chris Gao 2013/07/09
		boolean isAudioServerMix = ConfConstant.isAudioServreMix(siteLogic.getSiteBaseById(confBase.getSiteId()));
//		params.append("<mix_method>" + ((isAudioServerMix && confBase.isAudioServerMixed()) ? 2 : 1) + "</mix_method>");///////////混音方式
		//params.append("<mix_method>" + ((ConfConstant.AUDIO_SERVER_MIX && confBase.isAudioServerMixed()) ? 2 : 1) + "</mix_method>");///////////混音方式
		//
		params.append("<frame_len>" + ClientConstant.FRAME_LEN + "</frame_len>");
		//
		params.append("<sample_rate>"+ ClientConstant.SAMPLE_RATE + "</sample_rate>");
		//
		params.append("<codec_name>iLBC</codec_name>");
		// 与会者权限
		params.append("<default_privilege>"+this.makePrivilege(confBase)+"</default_privilege>");
//		params.append("<web_config>" + this.makeWebConfig(confBase) + "</web_config>");
		// params.append("<roll_call>0</roll_call>");
		// params.append("<h323_enable>0</h323_enable>");
		params.append("<invite_type>"+ (ConfConstant.SYS_ENABLE_PHONE ? "11" : "01") +"</invite_type>");//01邮件邀请，10电话邀请，11表示邮件和电话邀请都支持，00都不支持
		params.append("<phone_conf>");
		/**
		 * 0表示纯数据会议，1表示MRS混音下无电话的会议，2表示MRS混音下的有电话的会议，3表示智真会议
		 */
//		params.append("<conf_type>"+ (ConfConstant.AUDIO_SERVER_MIX ? (confBase.hasPhoneFunc() ? 2 : 1) : 0) +"</conf_type>");  ////////////
		params.append("<conf_type>"+ (confBase.hasPhoneFunc() ? 2 : 0) +"</conf_type>");
		params.append("<max_phone_count>"+ confBase.getMaxUser() +"</max_phone_count>");
//		params.append("<pin>" + confBase.getConfHwid() + "</pin>");
//		params.append("<phone_conf_id>"+ (ConfConstant.SYS_ENABLE_PHONE ? confBase.getConfHwid() : "") +"</phone_conf_id>");////////////与会议ID号相同
		params.append("</phone_conf>");
		params.append("<support_info></support_info>");
		params.append("<other_param></other_param>");
		params.append("<time_now>" + DateUtil.dateToString(DateUtil.getGmtDate(null), null) + "</time_now>");
		params.append("<max_user_count>" + confBase.getMaxUser() + "</max_user_count>");
//		params.append("<chairman_secure_conf_num>" + Base64.encode(confBase.getCompereSecure(),"utf8") + "</chairman_secure_conf_num>");
//		params.append("<participant_secure_conf_num>" + confBase.getUserSecure() + "</participant_secure_conf_num>");
//		params.append("<microphoneStatus>"+getConfigByBitIndex(confBase.getFuncBits(),11)+"</microphoneStatus>");
		//hardcode 1 for 29xx  
		params.append("<audio_mrs>"+ (ConfConstant.SYS_ENABLE_PHONE ? 1 : "null") +"</audio_mrs>");//19:1 2; 29//////////////0:表示19系列，1：表示29系列
		String siteSign=SiteIdentifyUtil.getCurrentBrand();
		SiteBase childSiteBase=siteLogic.getVirtualSubSite(confBase.getCreateUser());
		if(childSiteBase!=null){
			siteSign=childSiteBase.getSiteSign();
		}
		childSiteBase=null;
		// ?
		params.append("<enterpriseId>"+ siteSign +"</enterpriseId>");
		params.append("<audio_dscp>"+ClientConstant.AUDIO_DSCP+"</audio_dscp>");
		params.append("<video_dscp>"+ClientConstant.VIDEO_DSCP+"</video_dscp>");
		params.append("<data_dscp>"+ClientConstant.DATA_DSCP+"</data_dscp>");
		params.append("<email>"+(joinRandom.getUserEmail() != null ? joinRandom.getUserEmail() : "")+"</email>");
		params.append("<host_open_video>1</host_open_video>");
		params.append("<large_venue>" + (ConfConstant.HUGE_MEETING_ENABLE ? 1 : 0) + "</large_venue>");
		params.append("<mirage_driver>"+ClientConstant.MIRAGE_DRIVER+"</mirage_driver>");//屏幕共享方式:0、GDI方式 ；1、mirage_driver方式
		params.append("<svn_password>"+ClientConstant.SVN_PASSWORD+"</svn_password>");
		params.append("<as_default_bitrate>"+ClientConstant.AS_DEFAULT_BITRATE+"</as_default_bitrate>");
//		params.append("<disable_call_out_phone>"+(confBase.getAllowCall()==1?0:1)+"</disable_call_out_phone>");
		
		SiteBase siteBase=siteLogic.getSiteBaseById(confBase.getSiteId());
		int timeInterval= (siteBase!=null)? siteBase.getFrameTimeInterval() : 1000;
		params.append("<as_default_frame_time_interval>"+ timeInterval +"</as_default_frame_time_interval>");//
		params.append("<as_default_jpeg_quality>"+ClientConstant.AS_DEFAULT_JPEG_QUALITY+"</as_default_jpeg_quality>");
		params.append(" <conf_flowcontrol_enable>"+ClientConstant.CONF_FLOWCONTROL_ENABLE+"</conf_flowcontrol_enable>");        // 是否启用平滑发送 
		params.append(" <conf_flowcontrol_screen>"+ClientConstant.CONF_FLOWCONTROL_SCREEN+"</conf_flowcontrol_screen>");    // 屏幕共享，单位 B，如果要提升屏幕共享观看者看到的速度，可以提升这个值的大小
		params.append("  <conf_flowcontrol_cache>"+ClientConstant.CONF_FLOWCONTROL_CACHE+"</conf_flowcontrol_cache>");           // 文件传输，单位 B 
		params.append("  <conf_flowcontrol_doc>"+ClientConstant.CONF_FLOWCONTROL_DOC+"</conf_flowcontrol_doc>");               // 文档共享，单位 B 
		params.append("  <as_default_key_frame_delay>"+ClientConstant.AS_DEFAULT_KEY_FRAME_DELAY+"</as_default_key_frame_delay>");  // 关键帧发送的间隔，单位ms，如果要提升屏幕共享观看者看到的速度，可以减小这个值的大小，这个值默认5000，暂时不可修改，后续更新版本后可以设置
		params.append("  <media_share_max_birate>"+ClientConstant.MEDIA_SHARE_MAX_BIRATE+"</media_share_max_birate>"); //媒体共享的码流，单位kbps：
		params.append("  <hostingMode>"+ClientConstant.HOSTINGMODE+"</hostingMode>"); //服务器混音下,音频路数是否受限   ：0不受限制，1、受限制
		
		params.append("</ConfParam>");
//
//		boolean logStatus=confLogService.saveConfLog(getConfLogForStart(confBase,joinRandom));
//		System.out.println("Strart conf  confLog Status="+logStatus);
		
		return params.toString();
	}

	@Override
	public void termConf(int confId) {

	}
	
	
//	private void 
	
	private long makePrivilege(ConfBase confBase){
		if(confBase==null){
			return 0;
		}
		String priviBits="";
		if(StringUtil.isEmpty(priviBits)){
			return 0;
		}
		long privilege=0;
		char[] priviChars=priviBits.toCharArray();
		if(priviChars.length<ConfConstant.PRIVIBITS_CONFIG_CHAT_PARTICIPANTS){
			return 0;
		}
		
//
//数值	说明
//0x00000040==64	白板翻页
//0x00000004==4	文档翻页
//0x00000010==16	白板标注
//0x00000008==8	文档标注
//0x00000080==128	与所有人聊天
//0x00000100==256	与主持人聊天
//0x00000200==512	与主讲人聊天
//0x00000400==1024	与与会者聊天
		
		String eachPriviStr="";
		//翻页；包括   白板翻页、文档翻页
		eachPriviStr=String.valueOf(priviChars[ConfConstant.PRIVIBITS_CONFIG_CHANGEPAGE]);
		if("1".equals(eachPriviStr)){
			privilege+=64;//白板翻页
			privilege+=4;//文档翻页
		}

		//批注；包括   白板批注、文档批注
		eachPriviStr=String.valueOf(priviChars[ConfConstant.PRIVIBITS_CONFIG_ANNOTATE]);
		if("1".equals(eachPriviStr)){
			privilege+=16;//白板标注
			privilege+=8;//文档标注
		}

		//与所有人
		eachPriviStr=String.valueOf(priviChars[ConfConstant.PRIVIBITS_CONFIG_CHAT_ANYONE]);
		if("1".equals(eachPriviStr)){
			privilege+=128;//与所有人聊天
		}

		//与主持人；包括   主持人、主讲人
		eachPriviStr=String.valueOf(priviChars[ConfConstant.PRIVIBITS_CONFIG_CHAT_COMPERE]);
		if("1".equals(eachPriviStr)){
			privilege+=256;//与主持人聊天
			privilege+=512;//与主讲人聊天
		}

		//与参会者
		eachPriviStr=String.valueOf(priviChars[ConfConstant.PRIVIBITS_CONFIG_CHAT_PARTICIPANTS]);
		if("1".equals(eachPriviStr)){
			privilege+=1024;//与与会者聊天
		}
		return privilege;
	}
	
	
	private String string2Unicodes(String str) {
		StringBuilder unicodes = new StringBuilder();
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		for (char c : str.toCharArray()) {
			unicodes.append((int) c + ",");
		}
		return unicodes.deleteCharAt(unicodes.length() - 1).toString();
	}

	private String getConfigByBitIndex(String config, int index) {
		if (StringUtils.isEmpty(config)) {
			return "";
		}

		if (config.length() < index || index <= 0) {
			throw new IllegalArgumentException("'index' is not correct.");
		}

		return String.valueOf(config.charAt(index - 1));
	}

	 

	
	
	private JoinRandom makeRandom(ConfBase conf, UserBase user,int userRole) {
		user.setClientName(user.getTrueName());
		JoinRandom random = new JoinRandom();
		random.setCreateTime(DateUtil.getGmtDate(null));
		random.setConfId(conf.getId());
		random.setLanguage(LanguageHolder.getCurrentLanguage());
		random.setUserId(user.getId());
		random.setUserName(user.getClientName());
		random.setUserEmail(user.getUserEmail());
		random.setUserRole(userRole);
//				.setUserRole(user.getId() != null
//						&& conf.getCreateUser().intValue() == user.getId()
//								.intValue() ? 3 : 8);
		try {
			random = libernate.saveEntity(random);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return random;
	}
	
	public JoinRandom saveRandom(JoinRandom random){
		JoinRandom saveRandom=null;
		if(random!=null){
			try {
				saveRandom=libernate.saveEntity(random);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return saveRandom;
	} 
	public JoinRandom getJoinRandomById(int randomId){
		JoinRandom joinRandom=null;
		if(randomId>0){
			try {
				joinRandom=libernate.getEntity(JoinRandom.class, "id", randomId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return joinRandom;
	}
	
	private String buildPreParam(long random, String language, boolean forStartUp,String ip) {
		String downLoadUrl="";//makeDownUrl(ip);
		JoinRandom joinRandom=getJoinRandomById(IntegerUtil.parseInteger(random+""));
		if(joinRandom!=null){
			SiteBase siteBase=siteLogic.getSiteBaseById(confService.getConfBasebyConfId(joinRandom.getConfId()).getSiteId());
			if(siteBase!=null){
				downLoadUrl=StringUtil.isEmpty(siteBase.getDownloadUrl())? makeDownUrl(ip) :siteBase.getDownloadUrl();
			}
			logger.warn("randomId=="+random+";UserIp=="+joinRandom.getClientIp()+";userId=="+joinRandom.getUserId()+";UserName=="+joinRandom.getUserName()+";downLoadUrl=="+downLoadUrl);
		}
		String preParam =  String.format(ConfConstant.CLIENT_WEB_URL, SiteIdentifyUtil.getCurrentBrand())+"#"//String.format(ConfConstant.CLIENT_WEB_URL, SiteIdentifyUtil.getCurrentBrand()) + "#" 
				+downLoadUrl+"#"
		+ (LanguageHolder.DEFAULT_LANGUAGE.equalsIgnoreCase(language) ? 2052 : 1033) + "#1#" + random;
// 		+ String.format(CLIENT_DOWNLOAD_URL, SiteIdentifyUtil.getCurrentBrand()) + "#"
		logger.info("preParam=="+preParam);
		preParam = Base64.encode(preParam);
		preParam = preParam.replaceAll("/", "_");
		if (!forStartUp) {
			preParam += "_C03B021";
		}
		return preParam;
	}
	
	
	
	private String buildStartUpParam(long random, String language) {
		//"0" + "$" + "dick.confcloud.cn" + "$" + "80" + "$" + random + "/openapi/ClientAPI";
		String startUpParamString = String.format(ConfConstant.CLIENT_WEB_URL, SiteIdentifyUtil.getCurrentBrand()) + "#" + random;
		startUpParamString = Base64.encode(startUpParamString);
		startUpParamString.replaceAll("/", "_");
		try {
			startUpParamString = URLEncoder.encode(startUpParamString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String downDomain=SiteIdentifyUtil.getCurrentBrand() +"."+SiteIdentifyUtil.MEETING_CENTER_DOMAIN;
		
		startUpParamString = "0" + "$" + Base64.encode(downDomain) + "$" + "80$"+random+"$openapi" ;
		
		return startUpParamString;
	}
	


	private String  makeDownUrl(String ip){
		String ispCode=ipLocatorService.getISPCodeByIP(ip);
		String clientDownLoadUrl=ipLocatorService.getDownloadURL(ispCode);
		
//		String clientDownLoadUrl="";
//		if(ConfConstant.DOWNLOAD_SERVERS==null && ConfConstant.DOWNLOAD_DEFAULT==null){
//			
//			clientDownLoadUrl=ConfConstant.CLIENT_DOWNLOAD_URL_DEFAULT;
//		}
//		
//		if(ConfConstant.DOWNLOAD_DEFAULT!=null && ConfConstant.DOWNLOAD_DEFAULT.length() > 0){
//			clientDownLoadUrl=ConfConstant.DOWNLOAD_DEFAULT;
//		}
//		if(ConfConstant.DOWNLOAD_SERVERS!=null && ConfConstant.DOWNLOAD_SERVERS.length() > 0){
//			String[] server_array=null;
//			server_array=ConfConstant.DOWNLOAD_SERVERS.split(",");
//			if(server_array!=null && server_array.length >0){
//				if(server_array.length==1){
//					clientDownLoadUrl=ConfConstant.DOWNLOAD_SERVERS;
//				}else{
//					int serverCount=server_array.length;
//					int randomNum=(int)Math.round(serverCount * Math.random());
//					if(randomNum==serverCount){
//						randomNum=serverCount-1;
//					}
//					clientDownLoadUrl=server_array[randomNum];
//				}
//			}
//			server_array=null;
//			
//		}
		return clientDownLoadUrl;
	}

	
	private  ConfLog getConfLogForStart(ConfBase confBase,JoinRandom joinRandom){
		ConfLog confLog=null;
		confLog=new ConfLog();
		confLog.setSiteId(confBase.getSiteId());
		confLog.setConfId(confBase.getId());
		confLog.setUserId(IntegerUtil.parseInteger(joinRandom.getUserId()));
		confLog.setUserName(joinRandom.getUserName());
		confLog.setUserRole(joinRandom.getUserRole());
		confLog.setTermType(ConfConstant.CONF_USER_TERM_TYPE_PC);
		confLog.setJoinType(1);
		//confLog.setLeaveType(eachSoapuser.getLeaveType());
		confLog.setJoinTime(DateUtil.getGmtDate(null));
		confLog.setExitTime(DateUtil.getGmtDate(null));
	//	confLog.setExitTime(DateUtil.getGmtDateByTimeZone(DateUtil.StringToDate(eachSoapuser.getLeaveDatetime(),"yyyy-MM-dd HH:mm:ss"),28800000));
		confLog.setEmail("");
		confLog.setPhone("");
		
//		confLog=eachSoapuser.getUri();
		return confLog;
	}
	public static void main(String[] args) {
		ClientAPIServiceImpl service = new ClientAPIServiceImpl();
//		System.out.println(service.string2Unicodes("高红亮"));
//		System.out.println(service.getConfigByBitIndex("1234", 3));
//
//		char[] webConfigs = "0000000000000000000000000000000000".toCharArray();
//		StringBuilder configs = new StringBuilder();
//		for (char c : webConfigs) {
//			configs.append(c);
//		}
//		System.out.println(configs);
		UserBase userBase = new UserBase();
		userBase.init();
		List<UserBase> users = new ArrayList<UserBase>();
		users.add(userBase);
		userBase.setId(0);
		String xml = service.transUserToXml(users,"0","success");
		
		System.out.println(xml);
	}
	
	@Override
	public String transUserToXml(List<UserBase> users,String rCode, String rContext) {
		String retInfo = "";
		try{
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element root = DocumentHelper.createElement("message");
			
			//<head>
			Element head=root.addElement("head");
			Element retCode = head.addElement("retcode");
			Element retContext = head.addElement("retcontext");
			retCode.setText(rCode);
			retContext.setText(rContext);
			
			//<body>
			Element body = root.addElement("body");
			
			Element params = body.addElement("params");
			Element total = params.addElement("total");
			
			Element sum = params.addElement("sum");
			total.setText("");
			sum.setText("");
			
			Element paramList = body.addElement("paramlist");
			paramList.addAttribute("type", "employeeList");
			
			if(users!=null && users.size()>0){
				total.setText(String.valueOf(users.size()));
				sum.setText(String.valueOf(users.size()));
				for (Iterator<UserBase> itr = users.iterator(); itr.hasNext();) {
					UserBase user = itr.next();
					Element bean = paramList.addElement("bean");
					
					Element staffId = bean.addElement("staffid");
					staffId.setText(user.getId()==null?"":user.getId().toString());
					
					Element name = bean.addElement("name");
					name.setText(user.getTrueName()==null?"":user.getTrueName());
					
					Element nativenname = bean.addElement("nativenname");
					nativenname.setText(user.getTrueName()==null?"":user.getTrueName());
					
					
					Element staffaccount = bean.addElement("staffaccount");
					staffaccount.setText(user.getLoginName()==null?"":user.getLoginName());

					Element staffno = bean.addElement("staffno");
					staffno.setText(user.getLoginName()==null?"":user.getLoginName());
					
					Element sex = bean.addElement("sex");
					
					Element mobile = bean.addElement("mobile");
					mobile.setText(user.getMobile()==null?"":user.getMobile());
					
					Element homephone = bean.addElement("homephone");
					homephone.setText(user.getPhone()==null?"":user.getPhone());
					
//					Element originalmo
					Element email = bean.addElement("email");
					email.setText(user.getUserEmail()==null?"":user.getUserEmail());
					
					Element bindno = bean.addElement("bindno");
					
					Element shortphone = bean.addElement("shortphone");
					
					Element officephone = bean.addElement("officephone");
					officephone.setText(user.getPhone()==null?"":user.getPhone());
					
					Element credit = bean.addElement("credit");
					
					Element underwrite= bean.addElement("underwrite");
					
					Element voip = bean.addElement("voip");
					
					Element otherphone = bean.addElement("otherphone");
					
					Element addr = bean.addElement("addr");
					
					Element zip = bean.addElement("zip");
					
					Element seat = bean.addElement("seat");
					
					Element picid = bean.addElement("picid");
					
					Element departcn = bean.addElement("departcn");
					
					Element CountryCode = bean.addElement("CountryCode");
					
					Element imstate = bean.addElement("imstate");
				}
			}
			doc.add(root);
			retInfo = doc.asXML();
		}catch (Exception e) {

			e.printStackTrace();
		}
		return retInfo;
	}

	
}
