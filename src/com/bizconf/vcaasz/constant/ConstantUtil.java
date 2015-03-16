package com.bizconf.vcaasz.constant;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;


public class ConstantUtil {

	public static final int TIMEOUT = 100000;
	
	//默认站点标识
	public static final String DEFAULT_SITE_BRAND = "meeting";
	
	//2013.6.24 因客户需求新加常量，部分每页展示50条
	public static final Integer PAGESIZE_DEFAULT_CUST = 50;//获取部分列表每页默认记录数
	//2013.6.24 
	
	
	public static final Integer PAGESIZE_DEFAULT = 10;//获取列表时每页默认记录数
	
	public static final Integer INVALID_STATUS =0;//所有数据无效状态
	
	
	public static final Integer EMAIL_FLAG_UNSEND =  1;// 未发送的邮件
	public static final Integer EMAIL_FLAG_SECCEED =  2;// 发送成功的邮件
	public static final Integer EMAIL_FLAG_FAIL =  3;// 发送失败的邮件

	public static final Integer LOCKFLAG_UNLOCK =  1; // 解锁状态
	public static final Integer LOCKFLAG_LOCKED =  2; // 锁定状态

	public static final Integer DELFLAG_UNDELETE =   1;// 正常使用
	public static final Integer DELFLAG_DELETED =   2;// 已删除
	
	/**以下为邮件模板标志**/
	public static final Integer SYS_VERSION =   0;// 系统默认模板版本
	public static final Integer SYS_RESERVE_FLAG_N =  0;//表示非系统保留版本
	public static final Integer SYS_RESERVE_FLAG_Y =  1;//表示非系统保留版本
	
	public static final String SUCCESS_FLAG = "success"; //成功标志
	public static final String ERROR_FLAG = "error"; //系统内部错误标志
	
	//用户角色
	public static final Integer USERROLE_HOST =  1; //主持人
	public static final Integer USERROLE_PARTICIPANT =  2;//参会者
	// 用户标识
	public static final Integer USERTYPE_USERS =  2;// 普通站点用户
	public static final Integer USERTYPE_ADMIN =  9;// 站点管理员
	public static final Integer USERTYPE_ADMIN_SUPPER =  99;// 站点SUPPER 管理员
	public static final Integer USERTYPE_SYSTEM =  999;// 系统管理员
	public static final Integer USERTYPE_SYS_SERVER =  6;// 系统客服
	public static final Integer USERTYPE_SYSTEM_SUPPER =  9999;// 系统SUPPER管理员
	
	public static final Integer USER_STATU_LOCK =  0;//企业用户锁定状态
	public static final Integer USER_STATU_UNLOCK =  1;//企业用户解锁状态
	
	
	public static final Integer MAX_ERROR_COUNT_SYSTEM = 10;// 系统管理员最大错误登录次数
	public static final Integer MAX_ERROR_COUNT_ADMIN = 10;// 站点管理员最大错误登录次数
	public static final Integer MAX_ERROR_COUNT_USER = 10;// 普通用户 最大错误登录次数

	public static final String PASSWORD_KEY_SYSTEM_ADMIN="bizconf_sysadmin_qpskcb";//系统管理员找回密码时，URL地址中加密 KEY
	public static final String PASSWORD_KEY_SITE_ADMIN="bizconf_siteadmin_zmdjty";//站点管理员找回密码时，URL地址中加密 KEY
	public static final String PASSWORD_KEY_USER="bizconf_user_xnghei";//站点普通用户找回密码时，URL地址中加密 KEY
	public static final Integer PASSWORD_URL_EXPIRE_HOURS = 3;//忘记密码时URL地址链接超期时间

	public static final Integer LIMIT_LOGIN_HOUR_SYSTEM = 8;//系统管理员：登录次数达到后不能再次登录小时数
	public static final Integer LIMIT_LOGIN_HOUR_ADMIN = 8;//站点管理员：登录次数达到后不能再次登录小时数
	public static final Integer LIMIT_LOGIN_HOUR_USER = 8;//普通用户：登录次数达到后不能再次登录小时数
	
	
	public static final Integer NOTICE_PUBLISHED =1;//公告：已经发布或者是正在发布中
	public static final Integer NOTICE_UNPUBLISHED =2;//公告：未发布或者是停止发布状态 
	

	
	public static final Integer SITEFLAG_FORMAL = 1; // 站点正式使用
	public static final Integer SITEFLAG_TRY = 2;// 站点试用
	
	public static final String COOKIE_LANG_KEY="LANG";
	
	public static final int CREATENOTICE_SUCCEED = 1;  //创建公告成功
	public static final int CREATENOTICE_FAIL = 2;  //创建公告失败
	
	public static final int CREATESITE_SUCCEED = 1;  //创建站点成功
	public static final int CREATESITE_FAIL = 2;  //创建站点失败
	public static final int UPDATESITE_SUCCEED = 1;  //创建站点成功
	public static final int UPDATESITE_FAIL = 2;  //创建站点失败
	public static final int DELSITE_SUCCEED = 1;	//站点删除成功
	public static final int DELSITE_FAIL = 2; //站点删除失败
	
	public static final int CREATESYSTEMUSER_SUCCEED = 1;  //新建系统管理员成功
	public static final int CREATESYSTEMUSER_FAIL = 2;  //新建系统管理员失败
	public static final int UPDATESYSTEMUSER_SUCCEED = 1; //修改系统管理员成功
	public static final int UPDATESYSTEMUSER_FAIL = 2; //修改系统管理员失败
	public static final int DELETESYSTEMUSER_SUCCEED = 1; //删除系统管理员成功
	public static final int DELETESYSTEMUSER_FAIL = 2; //删除系统管理员失败
	
	
	public static final int CREATESITEUSER_SUCCEED = 1; //添加或修改企业用户成功
	public static final int CREATESITEUSER_FAIL = 2; //创建或修改用户失败
	
	public static final int LOCKSITE_SUCCEED = 1;  //锁定站点成功
	public static final int LOCKSITE_FAIL = 2; //锁定站点失败
	public static final int UNLOCKSITE_SUCCEED = 1; //解锁站点成功
	public static final int UNLOCKSITE_FAIL = 2; //解锁站点失败
	
	public static final int HIRESITE_MONTH = 1; //包月模式
	public static final int HIRESITE_MINUTES = 2; //计费模式
	
	public static final int CHARGESITE_NAMEHOST = 1; //name host
	public static final int CHARGESITE_ACTIVEUSER = 2; //active user
	public static final int CHARGESITE_SEATES = 3; //seats
	
	public static final int SITELOG_LIMIT = 256;
	
	
	public static final String COOKIE_SPLIT="##########";//COOKIE  Value的分割符
	
	
	public static final String LANG_CN="zh";//中文环境
	public static final String LANG_EN="en";//英文环境
	
	
	
	public static final int CREATE_CONF_SUCCEED = 1; //创建会议成功
	public static final int CREATE_CONF_FAIL = 2; //创建会议失败
	
	public static final int RECREATE_CONF_SUCCEED = 1; //重新创建会议成功
	public static final int RECREATE_CONF_FAIL = 2; //重新创建会议失败
	
	public static final int UPDATE_CONF_SUCCEED = 1; //修改会议成功
	public static final int UPDATE_CONF_FAIL = 2; //修改会议失败
	
	public static final int DELETE_CONF_SUCCEED = 1; //删除会议成功
	public static final int DELETE_CONF_FAIL = 2; //删除会议失败
	
	public static final int INVITE_CONF_USER_SUCCEED = 1; //邀请联系人成功
	public static final int INVITE_CONF_USER_FAIL = 2; //邀请联系人失败
	
	
	public static final int GLOBAL_SUCCEED_FLAG = 1; //成功标志
	public static final int GLOBAL_FAIL_FLAG = 2; //失败标志
	
	public static final int AS_COMMON_SUCCESS_CODE = 0;//AS接口成功返回码
	public static final int AS_CANCLE_SUCCESS_CODE = 24413;//AS接口取消会议成功返回码
	
	public static final String AS_SUCCESS_CODE = "0";//AS接口成功返回码
	public static final String AS_FAILED_LICENSE_CODE = "29610";    //AS接口返回码license不足
	public static final String AS_FAILED_LICENSE_CODE_1 = "24404";    //达到数据license最大值
	public static final String AS_MORE_THAN_MAXNUM = "29616";    //超过设置的最大人数
	public static final String AS_ERROR_START_FAILED = "24440";    //启动会议失败,达到并发会场数
	public static final String AS_ERROR_UNKNOWN = "99999";    //未知错误
	
	public static final Set<String> AS_FAILED_LICENSE_SET = new HashSet<String>();
	static{
		AS_FAILED_LICENSE_SET.add("29610");	  // license不足
		AS_FAILED_LICENSE_SET.add("24404");  //达到数据license最大值
		AS_FAILED_LICENSE_SET.add("24403");  //达到视频license最大值
		AS_FAILED_LICENSE_SET.add("24402");  //达到音频license最大值
	};
	
	
	
	public static final String AS_BEGIN_TIME_INVALID = "29507";//会议开始开始时间不对
	
	public static final String AS_FAILED_MRS_INVALID = "26500";//MRS资源分配异常
	
	public static final int CREATE_CONTACT_SUCCEED = 1; //创建联系人成功
	public static final int CREATE_CONTACT_FAIL = 2; //创建联系人失败
	
	public static final int UPDATE_CONTACT_SUCCEED = 1; //修改联系人成功
	public static final int UPDATE_CONTACT_FAIL = 2; //修改联系人失败
	
	public static final int DELETE_CONTACT_SUCCEED = 1; //删除联系人成功
	public static final int DELETE_CONTACT_FAIL = 2; //删除联系人失败
	
	public static final int RESET_PASS_SUCCEED = 1;//重置密码成功
	public static final int RESET_PASS_FAILED = 2; //重置密码失败
	
	public static final int CYCLE_CONF_DATE_LIMIT = 30; //默认每个周期会议最多获取30个有效开始日期
	
	/*
	 * zh-cn,lang_zh.xml
en-us,lang_en.xml
0:Chinese,1:English,2:Big5 
	 */
	public static final String[][] CLIENT_LANGUAGE=new String[][]{
		{"0","zh-cn"},
		{"1","en-us"},
		{"2","zh-cn"}
		
	};
	
	public static final String JOIN_MTG_PUBLIC_KEY="Le5&6y!8oPwI";
	
	/*
	 * 站点下组织机构最高级别
	 */
	public static final int SITE_ORG_LEVEL_FIRST = 1;
	
	//remove following 4 lines by Chris
	//public static final String LOGOIMAGE = LiberContainer.getContainer().getServletContext().getRealPath("/logoImage/");
	//public static final String TEMPLOGOIMAGE = LiberContainer.getContainer().getServletContext().getRealPath("/uploadfiles/site_logo/");
	
	//public static final String SITELOGOPATH = LOGOIMAGE;  //站点logo图片存储目录
	//public static final String TEMPSITELOGOPATH = TEMPLOGOIMAGE;  //站点logo图片临时存储目录
	
//	
//	public static final Integer[] FUNC_GROUPS=new Integer[]{
//		0,//无效操作
//		1,//站点管理
//		2,//邮件管理
//		3
//	};
//	
//	public static final Integer[] FUNC_MODULES = new Integer[] { 
//			0,// 无效操作
//			
//			//系统管理员
//			
//			5,//从本行到到20是为系统管理留的备用数据
//			6,
//			7,
//			8,
//			9,
//			10,
//			11,
//			12,
//			13,
//			14,
//			15,
//			16,
//			17,
//			18,
//			19,
//			20,
//
//			//站点管理员
//			21,//修改站点信息
//			22,// 修改站点的会议参数
//			23,// 设置邮件模板
//			24,// 设置邮件服务器
//			25,// 创建组织机构
//			26,// 修改组织机构
//			27,// 组织机构分配用户
//			28,// 增加企业用户
//			29,// 修改企业用户
//			30,// 删除企业用户
//			31,// 锁定企业用户
//			32,// 激活企业用户
//			33,
//			34,
//			35,
//			36,
//			37,
//			38,
//			39,
//			40,
//			
//			//企业用户
//			41,// 创建会议
//			42,// 修改会议
//			43,// 取消预约会议
//			44,// 进入会议
//			45,// 添加与会人员
//			46,// 用户创建通讯录
//			47,// 通讯录设置分组
//			48,// 用户增加学用联系人
//			49,
//			50,
//			51,
//			52,
//			53,
//			54,
//			55,
//			56,
//			57,
//			58,
//			59,
//			60			 
//			
//	};

	

	
//	//日志模块
//	public static final short[][] FUNC_GROUPS=new short[][]{
//		//无效操作
//		{0},
//		//站点管理
//		{
//			//无效操作
//			0,
//			1,// 创建站点
//			2,// 修改站点
//			3,// 删除站点
//			4,// 新增站点License + 开始、过期日期等
//			5,// 修改License+开始、过期日期等
//			
//		},
//		//邮箱设置
//		{
//			//无效操作
//			0
//		}
//	};
	
	//站点名称正则表达式验证
//	验证长度为3的字符：^.{3}$
	public static final String SITE_NAME_REG = "^.{1,32}$";
	//站点英文名称正则表达式验证
	public static final String SITE_ENNAME_REG = "^.{1,64}$";
	//站点标识正则表达式验证
	public static final String SITE_SIGN_REG = "^[a-zA-Z0-9_\\-&]{1,16}$";
	//站点管理员用户名正则表达式验证
	public static final String SITE_ADMIN_USERNAME_REG = "^[a-zA-Z0-9_\\-&\\u4e00-\\u9fa5]{1,32}$";
	//站点管理员登录名正则表达式验证
	public static final String SITE_ADMIN_LOGINNAME_REG = "^[a-zA-Z0-9_]{4,16}$";
	
	//seats、time模式下创建站点时的第一条license记录标志位为1
	public static final int FIRST_LICENSE_FLAG = 1;
	
	public static final Integer NOTICE_FLAG_ANYONE = 0;// 所有用户可见的公告
	public static final Integer NOTICE_FLAG_LOGINED = 1;// 仅已登录用户可见的公告
	
	
	
	public static final Integer MS_INUSE_TRUE = 1;// MS可用
	public static final Integer MS_INUSE_FALSE = 0;// MS不可用

	
	public static final int BILL_TYPE_TEL = 0;//通信账单
	
	public static final int BILL_TYPE_DATA = 1;//数据账单
	
	public static final int BILL_TYPE_AMOUNT = 2;// 月度总账单
	
	

	public static final String TOKEN_KEY = "bizconftokenkey";//token加密关键字
	
	/******************************************************************************/
	public static final String OS_ANDROID = "android";
	public static final String OS_IPHONE = "iphone";
	public static final String OS_IPAD = "ipad";
	
	public static final String OS_WINXP = "winxp";
	public static final String OS_WINXP_NT_5_1 = "windows nt 5.1";
	public static final String OS_WINDOWS_XP = "windows xp";
	
	public static final String OS_WIN7 = "win7";
	public static final String OS_WIN7_NT_6_1 = "windows nt 6.1";
	public static final String OS_WINDOWS_7 = "windows 7";
	
	
	public static final String OS_MAC = "mac";
	public static final String OS_MAC_MACTH = "mac os";
	public static final String OS_MOBILE = "mobile";
	
	
	public static final String CLICKONCE_URL = "zoom_application";
	/******************************************************************************/

	//zoom客户端下载地址存放路径文件
	public static final String CONFIG_CONF_PROPERTIES = "/config/configURL";
	
	/******************************************************************************/
	public static final int SERVICE_ITEM_VIDEO_PERMIN = 1;
	public static final int SERVICE_ITEM_VIDEO_NAMEHOST = 2;
	public static final int SERVICE_ITEM_TELL_PERMIN = 3;
	public static final int SERVICE_ITEM_VIDEO_NAMEHOST_EXTEND = 4;
	
	/**************************出账单日期*********************************/
	public static final int BILL_DATE = 5;
	public static final int BILL_HOUR = 10;
	
	/**************************出账单错误代号*********************************/
	public static final int BILL_ERROR_NO_USER = 1;//查询指定用户不存在
	public static final int BILL_ERROR_USER_DETAIL_TRY_CATCH = 2;//查询指定用户详细账单try catch报错
	public static final int BILL_ERROR_NO_ADMIN = 3;//当前站点管理员不存在
	public static final int BILL_ERROR_NO_SITE = 4;//当前站点不存在
	public static final int BILL_ERROR_NO_SITE_HOSTOR = 5;//当前站点下主持人不存在
	public static final int BILL_ERROR_NO_HOSTOR = 6;//查询主持人不存在
	public static final int BILL_ERROR_USERS_LIST_TRY_CATCH = 7;//查询全部主持人账单try catch报错
	public static final int BILL_ERROR_SITE_TOTAL_TRY_CATCH = 8;//查询站点总账单try catch报错
	public static final int BILL_ERROR_USER_TOTAL_TRY_CATCH = 9;//查询指定用户总账单try catch报错
	public static final int BILL_ERROR_CURRENTUSER_DETAIL_TRY_CATCH = 10;//查询当前主持人详细账单try catch报错

	
	public static final int SAVE_DATA_CENTER_SUCCESS = 1;//保存数据中心成功
	public static final int SAVE_DATA_CENTER_FAILTRUE = 0;//保存数据中心失败
}
