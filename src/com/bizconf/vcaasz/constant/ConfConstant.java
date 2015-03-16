package com.bizconf.vcaasz.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.bizconf.vcaasz.entity.DefaultConfig;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;

public class ConfConstant {
	
	public static boolean SYS_ENABLE_PHONE;
	//服务器混音
	private static boolean AUDIO_SERVER_MIX;
	
	public static boolean HUGE_MEETING_ENABLE;
	
	public static boolean CHECK_CONCURRENT_CONF_SCHEDULE;
	
	public static boolean APPLY_AS_NEW_FEATRUE = false;
	
	//会议状态
	public static final Integer[] CONF_STATUS=new Integer[]{
		-1,//全部         
		0,//预约成功                
		2,//正在召开              
		3,//已结束 
	//	4,//已过期
		9,//取消的会议
	//	11,//锁定
//		99,//会议异常状态
	};
/*
 *
 0  预约成功
• 1 : 正在创建
• 2 : 正在召开
• 3 : 结束
• 8 : 创建失败
• 9 : 会议取消
• 11 : 会议被锁定
 */
	//无效会议
	public static final Integer CONF_STATUS_INVALID = 99;
	//预约成功
	public static final Integer CONF_STATUS_SUCCESS = 0;
	//正在召开
	public static final Integer CONF_STATUS_OPENING = 2;
	//结束的会议
	public static final Integer CONF_STATUS_FINISHED = 3;
	//过期会议
	public static final Integer CONF_STATUS_EXPIRED = 4;
	//AS启动会议时创建会议失败
	public static final Integer CONF_STATUS_CREATE_FAILED = 8;
	//取消的会议
	public static final Integer CONF_STATUS_CANCELED = 9;
	//锁定的会议
	public static final Integer CONF_STATUS_LOCKED = 11;

	//非永久会议
	public static final Integer CONF_PERMANENT_UNABLE=0;
	//永久会议主会议
	public static final Integer CONF_PERMANENT_ENABLED_MAIN=1;
	//永久会议子会议
	public static final Integer CONF_PERMANENT_ENABLED_CHILD=2;
	
	
	/*
	 * 会议功能：
	 * 		0、数据会议功能（页面什么都不勾选，也不展示）
	 * 		1 、电话功能
	 * 		2、视频功能 
	 * 		3、视频功能 + 电话功能
	 * */
	public static final String[] CONF_TYPES=new String[]{
		"0",//全部
		"1",//电话功能
		"2",//视频功能
		"3",//视频功能 + 电话功能
	};
	
	//循环会议
	public static final Integer CONF_CYCLE_TRUE = 1;
	//非循环会议
	public static final Integer CONF_CYCLE_FALSE = 2;
	
	//无限期循环
	public static final int CONF_CYCLE_INFINITE_TRUE = 1;
	//非无限期循环
	public static final int CONF_CYCLE_INFINITE_FALSE = 0;
	
	
	//日会议标志
	public static final Integer CONF_CYCLE_DAILY = 1;
	
	//周会议标志
	public static final Integer CONF_CYCLE_WEEKLY = 2;
	
	//月会议标志
	public static final Integer CONF_CYCLE_MONTHLY = 3;

	//年会议标志
	public static final Integer CONF_CYCLE_YEARLY = 4;
	
	
	
	public static final String CONF_CYCLE_VALUE_MONTH_SPLIT = ";";
	
	public static final String CONF_CYCLE_VALUE_DAYS_SPLIT = ",";
	

	public static final Integer CONF_CONFIG_ALLOW_PHONE_ENABLED=2;
	public static final Integer CONF_CONFIG_ALLOW_PHONE_DISABLED=1;
	public static final Integer CONF_CONFIG_ALLOW_PHONE_AUTOABLED=3;
	
	public static final Integer CONF_CONFIG_AUTO_CALL_ENABLED=2;
	public static final Integer CONF_CONFIG_AUTO_CALL_DISABLED=1;
	public static final Integer CONF_CONFIG_AUTO_CALL_AUTOABLED=3;
	public static final Integer CONF_CONFIG_VOIP_ENABLED=2;
	public static final Integer CONF_CONFIG_VOIP_DISABLED=1;
	public static final Integer CONF_CONFIG_VOIP_AUTOABLED=3;
	
	
	
	public static final Integer[] WEEK_DAYS=new Integer[]{
		1,//周日
		2,//周一
		3,//周二
		4,//周三
		5,//周三
		6,//周五
		7//周六
	};
	
	public static final Map<String,String> WEEK_DAYS_MAP = new HashMap<String,String>();
	static{
		WEEK_DAYS_MAP.put("1","周日");
		WEEK_DAYS_MAP.put("2","周一");
		WEEK_DAYS_MAP.put("3","周二");
		WEEK_DAYS_MAP.put("4","周三");
		WEEK_DAYS_MAP.put("5","周四");
		WEEK_DAYS_MAP.put("6","周五");
		WEEK_DAYS_MAP.put("7","周六");
	}
	
	
	//周日
	public static final Integer WEEK_SUNDAY =1;
	//周一
	public static final Integer WEEK_MONDAY=2;
	//周二
	public static final Integer WEEK_TUESDAY=3;
	//周三
	public static final Integer WEEK_WENDESDAY=4;
	//周四
	public static final Integer WEEK_THURSDAY=5;
	//周五
	public static final Integer WEEK_FRIDAY=6;
	//周六
	public static final Integer WEEK_SATURDAY=7;
	
	//创建即时会议默认会议时间长度，分钟 为单位
	public static final Integer CONF_DEFAULT_DURATION = 60;
	
	//参会人员：主持人
	public static final Integer CONF_USER_HOST = 1;
	//参会人员：参会人
	public static final Integer CONF_USER_PARTICIPANT = 2;
	
	//用户未接受也未谢绝，处于idle状态
	public static final Integer CONF_USER_IDLE = 0;
	
	//用户接受参会
	public static final Integer CONF_USER_ACCEPT = 1;
	//用户拒绝参会
	public static final Integer CONF_USER_REFUSE = 2;
	//通过会议ID获取前几个邀请人信息
	public static final Integer CONF_INVITE_USER_LIMIT = 5;
	
	/**
	 * 是否已经调用SOAP创建
	 * 0:无效数据
	 * 1：未创建
	 * 2：已创建
	 * 如果对会议进行修改，则将此值改成 1；创建会议任务只取本字段为1 的会议信息，其它的不做处理。
	 */
	//是否已经调用SOAP创建：未调用SOAP创建
	public static final Integer CONF_SOAP_STATUS_FASLE = 1;
	//是否已经调用SOAP创建: 已调用SOAP创建
	public static final Integer CONF_SOAP_STATUS_TRUE = 2;
	
	/*
	 * 查询与自己有关的会议，包括4种状态，每种状态默认展示条数：
	 * 1.与自己有关的正在进行中的会议
	 * 2.与自己有关的即将开始的会议
	 * 3.自己错过的会议
	 * 4.自己参加过的的会议
	 */
	public static final Integer CONF_LIST_DEFALT_ROWS = 200;
	
	/*
	 * 会议控制板（默认为一周内的会议，即7天内的会议）:
	 * 1.与自己有关的正在进行中的会议
	 * 2.与自己有关的即将开始的会议
	 * 3.自己错过的会议
	 * 4.自己参加过的的会议
	 */
	public static final Integer CONF_LIST_DASHBOARD_DAYS = 7;
	
	//公开会议
	public static final Integer CONF_PUBLIC_FLAG_TRUE = 1;
	//不公开会议
	public static final Integer CONF_PUBLIC_FLAG_FALSE = 2;
	//对所有同站点下注册公开
	public static final Integer CONF_PUBLIC_FLAG_USER = 3;
	
	//未隐藏的错过会议
	public static final Integer CONF_HIDE_FLAG_FALSE = 0;
	//隐藏的错过会议
	public static final Integer CONF_HIDE_FLAG_TRUE = 1;
	
	//默认的最大并发会场数
	public static final int EMPOWER_CONF_SYNC_DEFALT_NUM = 1;
	
	
	/*
	 * 创建会议出错信息配置：（需加中英文资源）
	 * 参会人数大于站点当前所剩参会人数值，错误码为-1
	 */
	
	//参会人数大于站点当前所剩参会人数值
	public static final Integer CONF_CREATE_ERROR_LICENCE = -1;
	//创建会议时站点同一时间内超过了最大并发会议数
	public static final Integer CONF_CREATE_ERROR_SYNC_LICENCE = -2;
	//创建会议时MRS资源分配异常
	public static final Integer CONF_CREATE_ERROR_MRS = -3;
	
	
	/**
	 * 查看邀请人的失败提示
	 * */
	public static final Integer INVITE_ERROR_CODE=108;
	
	/*
	 *加入会议的方式
	 *
	 *		通过会议ID号进入会议
	 *		通过会议安全码进入 会议
	 *		通过外部URL连接进入会议 
	 */

	public static final Integer JOIN_TYPE_CONFID=1;//加入会议的方式：通过会议ID号
	public static final Integer JOIN_TYPE_SECURE_CODE=2;//加入会议的方式 ：通过会议安全码
	public static final Integer HOST_JOIN_TYPE_EMAIL=3;//加入会议的方式 ：主持人通过邮件中URL地址
	public static final Integer USER_JOIN_TYPE_EMAIL=4;//加入会议的方式 ：被邀请的参会者通过邮件中URL地址
	public static final Integer JOIN_TYPE_OURURL=6;//加入会议的方式 ：外部通过JoinMtg方式进入会议
	public static final Integer JOIN_TYPE_RELOAD=5;//刷新页面时加载层
	
	
	

	public static final String JOIN_ERROR_CODE_0="0";//进入会议方式错误，请按正常方式进入会议
	public static final String JOIN_ERROR_CODE_1="1";//会议ID号、安全会议码不能同时为空
	public static final String JOIN_ERROR_CODE_2="2";//会议ID号不正确，找不到相应的会议
	public static final String JOIN_ERROR_CODE_3="3";//安全会议码不正确，找不到相应的会议
	public static final String JOIN_ERROR_CODE_4="4";//会议状态不正确
	public static final String JOIN_ERROR_CODE_5="5";//会议密码不正确
	public static final String JOIN_ERROR_CODE_6="6";//通过Email进入会议时参数不正确
	public static final String JOIN_ERROR_CODE_7="7";//会议未到开始时间
	public static final String JOIN_ERROR_CODE_8="8";//启动会议失败
	public static final String JOIN_ERROR_CODE_9="9";//站点不存在
	public static final String JOIN_ERROR_CODE_10="10";//启动永久会议失败，站点已经过到最大并发会议数。
	public static final String JOIN_ERROR_CODE_11="11";//会议状态设置失败
	public static final String JOIN_ERROR_CODE_12="12";//会议被取消或者会议已提前结束
	public static final String JOIN_ERROR_CODE_107="107";//Token加密错误不可以入会
	
	
	public static final Integer CONF_REMIND_MINUTES=1440;//邮件提醒的提前时间，会议开始前多少时间提醒，单位：分钟
	public static final Integer CONF_REMIND_FLAG_REMINDED= 1;//已经发送过邮件提醒
	public static final Integer CONF_REMIND_FLAG_UNREMIND= 2;//未经发送过邮件提醒
	
	public static final Integer CONF_REMIND_AHEAD_MINUTES_DEFAULT= 20;//会议开始前多少时间发送邮件提醒，单位 ：分钟
	
	public static final Integer CONF_REMIND_MINUTE_SCOPE_DEFAULT= 5;//邮件提醒的时间范围，单位 ：分钟
	
	public static final Integer CONF_REMIND_TASK_SLEEP_MINUTE_DEFAULT= 5;//Task任务中间隔的时间，单位 ：分钟
	
	/**************************************************************/
	
	public static final String SYSTEM_ADMIN_RETPASS_URL_ERROR = "1";//重置密码链接地址错误,
	
	public static final String SYSTEM_ADMIN_RETPASS_EXPIRE_ERROR = "2";//已经过期
	
	public static final String SYSTEM_ADMIN_RETPASS_AUTOCODE_ERROR = "3";//autoCode错误
	/**************************************************************/
	
	
	
//	public static final Integer CONF_REMIND_

	
	/*
	 * 会议功能：
	 * 		0、数据会议功能（页面什么都不勾选）
	 * 		1 、电话功能
	 * 		2、视频功能 
	 * 		3、视频功能 + 电话功能
	 * */
	public static final Integer CONF_TYPE_NULL_FUNC = 0;  	 		//数据会议功能
	public static final Integer CONF_TYPE_PHONE_FUNC = 1;  	 		//电话功能
	public static final Integer CONF_TYPE_VIDEO_FUNC = 2;   	    //视频功能
	public static final Integer CONF_TYPE_PHONE_VIDEO_FUNC = 3;     //视频功能 + 电话功能
	
	public static final Map<Integer,String> CLIENT_CONFIG_MAP = new HashMap<Integer,String>();
	static{
		CLIENT_CONFIG_MAP.put(1,"文档共享");
		CLIENT_CONFIG_MAP.put(2,"屏幕共享");
		CLIENT_CONFIG_MAP.put(21,"媒体共享");
		CLIENT_CONFIG_MAP.put(3,"白板");
		CLIENT_CONFIG_MAP.put(11,"文件传输");
		CLIENT_CONFIG_MAP.put(12,"录制");
	}
	
	/**
	 * Client功能配置,配置功能所在位数
	 * 共34位，0是不启用，1是启用 
	 *  第2位	文档共享
		第3位	屏幕共享
		第4位	白板
		第5位	笔记
		第6位         投票
		第8位	视频
		第9位	音频
		第10位	聊天
		第11位	公告
		第12位	文件传输
		第13位	录制
		第15位	会议助理
		第16位	私聊
		第17位	组聊
		第22位	媒体共享
	 */
	public static final Integer CLIENT_CONFIG_SHARE_DOCS = 1;//文档共享排clientConfig字段第2位，顺序编号为1     
	public static final Integer CLIENT_CONFIG_SHARE_SCREEN = 2;//屏幕共享
	public static final Integer CLIENT_CONFIG_WHITE_BOARD = 3;//白板
	public static final Integer CLIENT_CONFIG_NOTE = 4;//笔记
	public static final Integer CLIENT_CONFIG_VOTE = 5;//投票
	public static final Integer CLIENT_CONFIG_VIDEO = 7 ;//视频
	public static final Integer CLIENT_CONFIG_AUDIO = 8;//音频
	public static final Integer CLIENT_CONFIG_CHAT = 9;//聊天
	public static final Integer CLIENT_CONFIG_NOTICE = 10;//公告
	public static final Integer CLIENT_CONFIG_FILE_TRANS = 11;  //文件传输
	public static final Integer CLIENT_CONFIG_RECORD = 12;  //录制
	public static final Integer CLIENT_CONFIG_ASSISTANT = 14;//会议助理
	public static final Integer CLIENT_CONFIG_CONF_LOCK = 13;//锁定会议
	public static final Integer CLIENT_CONFIG_PRIVATE_CHAT = 15;  //私聊
	public static final Integer CLIENT_CONFIG_GROUP_CHAT = 16;  //组聊
	
	public static final Integer CLIENT_CONFIG_RECONNECT_MENU = 17;  //重连菜单
	
	public static final Integer CLIENT_CONFIG_SHARE_MEDIA = 21;//媒体共享
	
	/**
	 * 权限配置管理：共32位，（1：使用，0：不使用）
	 *  第1位：换页 
		第2位：批注 
		第3位：与所有人聊天 
		第4位：与主持人聊天 
		第5位：与参会人聊天 
	 *  其它全为0
	 */
	public static final Integer PRIVIBITS_CONFIG_CHANGEPAGE = 0;  //换页
	public static final Integer PRIVIBITS_CONFIG_ANNOTATE = 1;  //批注
	public static final Integer PRIVIBITS_CONFIG_CHAT_ANYONE = 2;  //与所有人聊天
	public static final Integer PRIVIBITS_CONFIG_CHAT_COMPERE = 3;  //与主持人聊天
	public static final Integer PRIVIBITS_CONFIG_CHAT_PARTICIPANTS = 4;  //与参会人聊天 
	
	
	/**
	 * 会议的功能位由01 字符串组成, 32 位, 每一位表示一个特定的功能
		• 第1 位表示该会议会控时, 是否需要对操作者鉴权
		• 第2 位表示服务器混音
		• 第3 位表示自动外邀
		• 第4 位表示是否录制
		• 第5 位表示标清或高清会议
		• 第6 位表示是否智真会议
		• 第7 位表示是否使用安全会议号
		• 第8 位表示会议模式(0 : 自由模式; 1 : 主持人模式)
		• 第9 位表示是否为安全会议
		• 第10 位表示该会议是否通过用户pin 码入会
		• 第11 位表示麦克风状态是否关闭(0 : 关闭; 1 : 打开)
		• 第12 位表示是否是UC 会议(0: 否; 1: 是)
		• 第13 位表示是否启用会场放音(0: 否; 1: 是)
		• 第14 位表示是否启用录制会场录音(0: 否; 1: 是)
		• 第15 位表示创建语音会议时是否需要预留多媒体资源(0: 否;1: 是)
		• 第16 位表示创建会议时IVR 的放音类型(0: 标识播放预配置的语音; 1: 标识静默方式，即不放音)
		• 第17 位表示是否启用入会过滤功能(0：否，1：是)
		• 第18 位表示是否支持超大会场(0：否，1：是)
		• 第19 位表示预约会议时是否屏蔽创建会场(0：不屏蔽；1：屏蔽)
		• 第20 位表示该预约会议是否加密会议(0：非加密会议；1：加密会议)
		• 第21 位表示是否勾选Internet Lock-Out Controls(0 表示禁用涉外会议,Internet 用户可接入
		• 其他用0 补全
	 */
	public static final Integer FUNCBITS_CONFIG_AUDIO_MIX = 1;    //服务器混音
	public static final Integer FUNCBITS_CONFIG_AUTO_INVITE = 2;  //自动外邀（页面为启动外呼）
	public static final Integer FUNCBITS_CONFIG_CONF_MODEL = 7;  //会议模式
	public static final Integer FUNCBITS_CONFIG_MIC_STATUS = 10;  //麦克风状态
	public static final Integer FUNCBITS_CONFIG_SUPER_MEETING = 17;  //超大会场
	
	/**
	 * 会议默认设置，提前入会时间
	 */
	public static final Integer CONF_CONFIG_AHEADTIMES = 10;
	
	/**
	 * 视频设置类型
	 * 01.优先保证网络带宽(默认分辨率是0，最大分辨率是1)
	 * 23.优先保证视频流畅(默认分辨率是2，最大分辨率是3)
	 * 44.优先保证画质清晰(默认分辨率是4，最大分辨率是4)
	 * */
	public static final String CONF_VIDEO_TYPE_WEBBAND = "01";     //优先保证网络带宽
	public static final String CONF_VIDEO_TYPE_FLUENCY = "23";     //优先保证视频流畅
	public static final String CONF_VIDEO_TYPE_DISTINCT = "44";  	 //优先保证画质清晰
	public static final String CONF_VIDEO_TYPE_BEST = "47";  	 //2013.7.8 新增 （最高）
	
	public static final int CONF_VIDEO_TYPE_WEBBAND_CODE = 1;     //优先保证网络带宽(低)
	public static final int CONF_VIDEO_TYPE_FLUENCY_CODE = 2;     //优先保证视频流畅(中)
	public static final int CONF_VIDEO_TYPE_DISTINCT_CODE = 3;  	 //优先保证画质清晰(高)
	public static final int CONF_VIDEO_TYPE_BEST_CODE = 4;  	 //2013.7.8 新增 （最高）
	/**
	 * 会议默认设置
	 * 1.网络带宽（最大分辨率：176*144，默认为：160*120）
	 * 2.视频流畅（最大分辨率352*288，默认为：320*240）
	 * 3.画质清晰（最大分辨率为：640*480，默认为：640*480）
	 * 值在0-8之间，0：160*120；1：176*144；2：320*240；3：352*288；4：640*480；5：704*576；6：960*720；7：1280*720；8：1920*1080
	 * 目前默认值设置为23，代表视频流畅（最大分辨率176*144（1），默认为：160*120（0））
	 */
	public static final String CONF_CONFIG_VIDEO_TYPE = CONF_VIDEO_TYPE_FLUENCY;
	public static final Map<String,String> CONF_CONFIG_VIDEO_RESOLUTION_MAP = new HashMap<String,String>();
	static{
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("0", "160*120");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("1", "176*144");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("2", "320*240");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("3", "352*288");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("4", "640*480");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("5", "704*576");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("6", "960*720");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("7", "1280*720");
		CONF_CONFIG_VIDEO_RESOLUTION_MAP.put("8", "1920*1080");
	}
	
	
	/**
	 * 最大音频数
	 */
	public static final Integer CONF_CONFIG_MAX_AUDIO = 16;
	/**
	 * 最大视频数
	 */
	public static final Integer CONF_CONFIG_MAX_VEDIO = 6;
	/**
	 * 最大参会人数
	 */
	public static final Integer CONF_CONFIG_MAX_USER = 5;
	
	
	
	
	public static final DefaultConfig DEFAULT_CONFIG_FOR_OUTER = new DefaultConfig(
			16,
			6,
			CONF_CONFIG_VIDEO_TYPE,
			"5",
			"4",
			20,
			CONF_CONFIG_AHEADTIMES,
			"01111000000000000000000000000000",
			"0011100111111011100001000000000000",
			"00000000000000000000000000000000");
	
	//电话会议接入号
	public static final String ACCESSCODE = "01058214900";
	
	
	/*
	 * 用户终端类型  
	 * 0 表示未初始化终端类型, 创会传入用户终端类型  1 表示普通手机用户  2 表示PAD 终端用户  3 表示PC 终端用户 
	 * 4 表示智真级联用户  5 表示智真TP 终端用户  6 表示用于会场间进行会场绑定的实现  7 表示UC 用户
	 */
	
	public static final int CONF_USER_TERM_TYPE_MOBILE = 1;
	public static final int CONF_USER_TERM_TYPE_PAD = 2;
	public static final int CONF_USER_TERM_TYPE_PC = 3;
	public static final int CONF_USER_TERM_TYPE_ZHIZHEN = 4;
	public static final int CONF_USER_TERM_TYPE_ZHIZHENTP = 5;
	public static final int CONF_USER_TERM_TYPE_HALL = 6;
	public static final int CONF_USER_TERM_TYPE_UC = 7;
	
	
	
	
//
//	//confbase中priviBits位值的对应的关系
//	public static final int CONF_PRIVILEGE_PAGING=0;//翻页
//	public static final int CONF_PRIVILEGE_COMMENT=1;
//	public static final int CONF_PRIVILEGE_CHAT_ALL=2;
//	public static final int CONF_PRIVILEGE_CHAT_HOST=3;
//	public static final int CONF_PRIVILEGE_CHAT_USER=4;
//	
//	
	
	//会议主控板查询会议时间范围：1.今天；2.本周；3.本月；4.所有
	public static final int CONF_DATE_SCOPE_FLAG_TODAY = 1;    //今天
	public static final int CONF_DATE_SCOPE_FLAG_WEEK = 2;	   //本周
	public static final int CONF_DATE_SCOPE_FLAG_MONTH = 3;    //本月
	public static final int CONF_DATE_SCOPE_FLAG_ANY = 4;	   //所有即将开始的
	
	//会议主控板查询会议会议状态：1.正在进行的；2.即将开始的；3.参加过的
	public static final int CONF_PAD_STATUS_OPENING = 1;       //正在进行的
	public static final int CONF_PAD_STATUS_COMING = 2;		   //即将开始的
	public static final int CONF_PAD_STATUS_ATTENDED = 3;	   //参加过的
	
	
	
	public static final String CLIENT_WEB_URL = "http://%s.confcloud.cn:80/openapi/ClientAPI";
	public static final String CLIENT_DOWNLOAD_URL_DEFAULT="http://"+SiteIdentifyUtil.getCurrentBrand()+".confcloud.cn:80/download";
	
	
	public static String CLIENT_DOWNLOAD_URL = null;//"http://%s.confcloud.cn:80/download";
	public static String CLIENT_DOWNLOAD_URL_FOR_SETTING = "";//"http://%s.confcloud.cn:80/download";
	
	public static String SERVER_IP = null;
	public static String CLUSTER_ID = null;
	public static String DOWNLOAD_DEFAULT=null;
	public static String DOWNLOAD_SERVERS=null;
	
	static {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config.ms");
			SERVER_IP = rb.getString("server_ip");
			CLUSTER_ID = rb.getString("cluster_id");
			CLIENT_DOWNLOAD_URL=CLIENT_DOWNLOAD_URL_DEFAULT;
			SYS_ENABLE_PHONE = "TRUE".equalsIgnoreCase(rb.getString("sys_phone_enabled"));
			HUGE_MEETING_ENABLE = "TRUE".equalsIgnoreCase(rb.getString("huge_meeting_enabled"));
			CLIENT_DOWNLOAD_URL_FOR_SETTING=rb.getString("client_download_url_for_setting");
			CHECK_CONCURRENT_CONF_SCHEDULE = "TRUE".equalsIgnoreCase(rb.getString("check_concurrent_conf_schedule"));
			if (rb.containsKey("apply_as_new_feature")) {
				APPLY_AS_NEW_FEATRUE = 
					"TRUE".equalsIgnoreCase(rb.getString("apply_as_new_feature"));
			}
			if (SYS_ENABLE_PHONE) {
				AUDIO_SERVER_MIX = true;
			}
			else {
				AUDIO_SERVER_MIX = "TRUE".equalsIgnoreCase(rb.getString("audio_server_mix"));
			}
//			DOWNLOAD_DEFAULT = rb.getString("download_servers_default");
//			DOWNLOAD_SERVERS = rb.getString("download_servers");
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
//		
//		if(DOWNLOAD_SERVERS==null && DOWNLOAD_DEFAULT==null){
//			CLIENT_DOWNLOAD_URL=CLIENT_DOWNLOAD_URL_DEFAULT;
//		}
//		
//		if(DOWNLOAD_DEFAULT!=null && DOWNLOAD_DEFAULT.length() > 0){
//			CLIENT_DOWNLOAD_URL=DOWNLOAD_DEFAULT;
//		}
//		
//		if(DOWNLOAD_SERVERS!=null && DOWNLOAD_SERVERS.length() > 0){
//			String[] server_array=null;
//			server_array=DOWNLOAD_SERVERS.split(",");
//			if(server_array!=null && server_array.length >0){
//				if(server_array.length==1){
//					CLIENT_DOWNLOAD_URL=DOWNLOAD_SERVERS;
//				}else{
//					int serverCount=server_array.length;
//					int randomNum=(int)Math.round(serverCount * Math.random());
//					if(randomNum>=serverCount){
//						randomNum=serverCount-1;
//					}
//					CLIENT_DOWNLOAD_URL=server_array[randomNum];
//				}
//			}
//			server_array=null;
//			
//		}
//		System.out.println("CLIENT_DOWNLOAD_URL=="+CLIENT_DOWNLOAD_URL);
	}
	
	public static boolean isAudioServreMix(SiteBase siteBase) {
		if (siteBase == null) {
			return AUDIO_SERVER_MIX;
		}
		return siteBase.getAudioServerMix() == 1;
	}
	
	//可以先于主持人入会
	public static  final int ALLOW_JBH=1;
	
	//不允许先于主持人入会
	public static  final int NOT_ALLOW_JBH=1;
	
	
	//按周查询
	public static final int FREQ_WEEKLY = 1;
	
	//按月查询
	public static final int FREQ_MONTHLY = 2;
}
