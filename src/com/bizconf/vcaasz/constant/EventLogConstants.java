package com.bizconf.vcaasz.constant;


public class EventLogConstants {
	
	public static final Integer EVENTLOG_SECCEED = 1;// 操作成功
	public static final Integer EVENTLOG_FAIL = 2;// 操作失败
	
	public static final Integer LOGINLOG_SECCEED = 0;// 登录成功
	
	//超级系统管理员下打开系统日志列表
	public static final Object[] SYSTEM_EVENTLOG_TOTAL=new Object[]{
		0,//全部
		100,//站点
		300,//邮件服务器
		400,//邮件模板
		20100,//系统管理员的管理
		20400 //系统管理员代替站点管理员管理站点
		
	};
	//普通系统管理员下打开系统日志列表
	public static final Object[] SYSTEM_EVENTLOG_COMMON=new Object[]{
		0,//全部
		100//站点
		
	};
	
	//企业管理员日志列表 
	public static final  Object[]  SITE_EVENTLOG_TOTAL = new  Object[] {
		0,//"全部"},
		500,//"企业用户"},
		1200,//"企业管理员"},
		700,//"企业信息"},
		800,//"邮件服务器"},
//		20200,//邮件模板
		900,//偏好设置
//		1000,//公告
		1100 //企业管理员个人设置
//		20300 //站点组织机构
	};
	
	//普通站点管理员下企业管理员日志列表 
	public static final Object[] SITE_USER_EVENTLOG_TOTAL = new  Object[] {
		0,//"全部"},
		500,//企业用户
		1100 //企业管理员个人设置
	};
	
	//站点管理员下用户操作日志列表
	public static final  Object[]  SITEUSER_EVENTLOG_TOTAL = new  Object[] {
		0,//"全部"},
		4100,//"会议"},
		4200,//"联系人"},
		4300 //"用户个人信息"},
	};
	
	//创建、增加即时会议
	public static final Integer SITEUSER_CONF_IMMEDIATELY_CREATE = 4101;
	//创建、增加普通预约会议Reservation
	public static final Integer SITEUSER_CONF_RESERVATION_CREATE = 4102;
	//创建、增加周期会议
	public static final Integer SITEUSER_CONF_CYCLE_CREATE = 4103;
	//修改会议信息
	public static final Integer SITEUSER_CONF_UPDATE = 4104;
	//删除会议
	public static final Integer SITEUSER_CONF_DELETE = 4105;
	//重新创建即时会议
	public static final Integer SITEUSER_CONF_RECREATE = 4106;
	//邀请参会人
	public static final Integer SITEUSER_CONF_INVITE = 4107;
	
	//新增联系人
	public static final Integer SITEUSER_CONF_CONTACT_CREATE = 4201;
	//修改联系人
	public static final Integer SITEUSER_CONF_CONTACT_UPDATE = 4202;
	//删除联系人
	public static final Integer SITEUSER_CONF_CONTACT_DELETE = 4203;
	//批量新增联系人
	public static final Integer SITEUSER_CONF_CONTACTBATCH_CREATE = 4204;
	
	//新增群组
	public static final Integer SITEUSER_CONF_GROUP_CREATE = 4205;
	//修改群组
	public static final Integer SITEUSER_CONF_GROUP_UPDATE = 4206;
	//删除群组
	public static final Integer SITEUSER_CONF_GROUP_DELETE = 4207;
	
	//用户个人信息设置
	public static final Integer SITEUSER_PROFILE_SETUP = 4301;
	
	
	/*
	 * 101到199是系统管理员对站点的操作
	 * */
	
	//系统管理员查询日志时条件选项
	public static final Object[] SYSTEM_SITE = new Object[]{
		101,//"创建站点"},
		102,//"修改站点"},
		103,//"删除站点"},
		104,//"锁定站点"},
		105//,"解锁站点"}
	};
	
	//创建、增加站点
	public static final Integer SYSTEM_SITE_CREATE = 101;
	//管理员修改站点信息
	public static final Integer SYSTEM_SITE_UPDATE = 102;
	//管理员删除 站点信息
	public static final Integer SYSTEM_SITE_DELETE = 103;
	//管理员锁定 站点信息
	public static final Integer SYSTEM_SITE_LOCK = 104;
	//管理员解锁 站点信息
	public static final Integer SYSTEM_SITE_UNLOCK = 105;
	
	
	
	
	/*
	 * 201到299是系统管理员对公告的操作
	 * */


	//系统管理员查询日志时条件选项
	public static final Object[] SYSTEM_NOTICE = new Object[]{
		201,//"新增公告"},
		202,//"修改公告"},
		203//,"删除公告"}
	};
	
	//系统管理员 创建新的公告信息
	public static final Integer SYSTEM_NOTICE_CREATE = 201;
	
	//系统管理员修改公告信息
	public static final Integer SYSTEM_NOTICE_UPDATE = 202;
	
	//系统管理员删除 公告
	public static final Integer SYSTEM_NOTICE_DELETE = 203;
	
	
	
	
	
	/*
	 * 301到399是系统管理员邮件服务器的操作
	 * */


	//系统管理员查询日志时条件选项
	public static final Object[] SYSTEM_HOST = new Object[]{
		301,//"初次设置邮件服务器"},
		302,//"修改邮件服务器"},
		303,//"创建站点时复制新的邮件服务器"},
		304,//"删除站点时删除邮件服务器"},
		305,//"恢复默认邮件服务器"},
		306//,"测试HOST时发送邮件"}
	};
	
	
	//系统管理员设置默认的邮件服务 器信息
	public static final Integer SYSTEM_HOST_CREATE = 301;

	//系统管理员修改默认的邮件服务 器信息
	public static final Integer SYSTEM_HOST_UPDATE = 302;

	//系统管理员创建站点时复制新的邮件服务
	public static final Integer SYSTEM_HOST_FOR_SITE_CREATE = 303;


	//系统管理员删除站点时删除邮件服务器设置
	public static final Integer SYSTEM_HOST_FOR_SITE_DELETE = 304;
	
	//系统管理员对某个站点恢复默认的邮件服务器信息
	public static final Integer SYSTEM_HOST_FOR_SITE_RESTORE = 305;
	
	//系统管理员测试HOST时发送邮件
	public static final Integer SYSTEM_HOST_CHECK = 306;
	
	
	

	/*
	 * 401到499是系统管理员邮件模板的操作
	 * */


	//系统管理员查询日志时条件选项
	public static final  Object[]  SYSTEM_TEMPLATE = new  Object[] {
		401,//"新增模板"},
		402,//"修改模板"},
		403,//"删除模板"},
		404,//"创建站点时复制模板"},
		405,//"删除站点时删除模板"},
		406//"恢复某站点的邮件模板为默认模板"}
		
	};

	// 新增邮件模板
	public static final Integer SYSTEM_TEMPLATE_CREATE = 401;

	// 修改邮件模板
	public static final Integer SYSTEM_TEMPLATE_UPDATE = 402;

	// 删除 邮件模板
	public static final Integer SYSTEM_TEMPLATE_DELETE = 403;

	// 创建站点时复制模板
	public static final Integer SYSTEM_TEMPLATE_FOR_SITE_CREATE = 404;
	
	// 删除站点时删除模板
	public static final Integer SYSTEM_TEMPLATE_FOR_SITE_DELETE = 405;
	
	// 恢复某站点的邮件模板为默认模板
	public static final Integer SYSTEM_TEMPLATE_FOR_SITE_RESTORE = 406;
	
	


	public static final  Object[]  SYSTEM_SYSTEMUSER = new  Object[] {
		20101,//"系统管理员创建"},
		20102,//"系统管理员修改"},
		20103//"系统管理员删除"}
		
	};
	//系统管理员创建
	public static final Integer SYSTEM_SYSTEMUSER_CREATE = 20101;
	//系统管理员修改
	public static final Integer SYSTEM_SYSTEMUSER_UPDATE = 20102;
	//系统管理员删除
	public static final Integer SYSTEM_SYSTEMUSER_DELETE = 20103;
	
	
	//站点管理员操作
	public static final  Object[]  SITE_ADMIN_USER = new  Object[] {
		501,//"站点管理员创建用户"},
		502,//"站点管理员修改用户"},
		503//"站点管理员删除用户"}
		
	};

	//站点管理员创建用户
	public static final Integer SITE_ADMIN_USER_CREATE = 501;
	//系统管理员代替站点管理员管理站点:创建用户
	public static final Integer SYSTEM_ADMIN_USER_CREATE = 20401;
	//站点管理员修改用户
	public static final Integer SITE_ADMIN_USER_UPDATE = 502;
	//系统管理员代替站点管理员管理站点:修改用户
	public static final Integer SYSTEM_ADMIN_USER_UPDATE = 20402;
	//站点管理员删除用户
	public static final Integer SITE_ADMIN_USER_DELETE = 503;
	//系统管理员代替站点管理员管理站点:删除用户
	public static final Integer SYSTEM_ADMIN_USER_DELETE = 20403;
	//站点管理员激活用户
	public static final Integer SITE_ADMIN_USER_ACTIVE = 504;
	//系统管理员代替站点管理员管理站点:激活用户
	public static final Integer SYSTEM_ADMIN_USER_ACTIVE = 20404;
	//站点管理员锁定用户
	public static final Integer SITE_ADMIN_USER_LOCK = 505;
	//系统管理员代替站点管理员管理站点:锁定用户
	public static final Integer SYSTEM_ADMIN_USER_LOCK = 20405;
	//站点管理员批量删除用户
	public static final Integer SITE_ADMIN_USER_DELETE_BATCH = 506;
	//系统管理员代替站点管理员管理站点:批量删除用户
	public static final Integer SYSTEM_ADMIN_USER_DELETE_BATCH = 20406;
	//站点管理员批量激活用户
	public static final Integer SITE_ADMIN_USER_ACTIVE_BATCH = 507;
	//系统管理员代替站点管理员管理站点:批量激活用户
	public static final Integer SYSTEM_ADMIN_USER_ACTIVE_BATCH = 20407;
	//站点管理员批量锁定用户
	public static final Integer SITE_ADMIN_USER_LOCK_BATCH = 508;
	//系统管理员代替站点管理员管理站点:批量锁定用户
	public static final Integer SYSTEM_ADMIN_USER_LOCK_BATCH = 20408;
	
	//超级站点管理员创建普通站点管理员
	public static final Integer SITE_ADMIN_SITEADMIN_CREATE = 601;
	//系统管理员代替站点管理员管理站点:创建普通站点管理员
	public static final Integer SYSTEM_ADMIN_SITEADMIN_CREATE = 20409;
	//超级站点管理员修改普通站点管理员
	public static final Integer SITE_ADMIN_SITEADMIN_UPDATE = 602;
	//系统管理员代替站点管理员管理站点:修改普通站点管理员
	public static final Integer SYSTEM_ADMIN_SITEADMIN_UPDATE = 20410;
	//超级站点管理员删除普通站点管理员
	public static final Integer SITE_ADMIN_SITEADMIN_DELETE = 603;
	//系统管理员代替站点管理员管理站点:删除普通站点管理员
	public static final Integer SYSTEM_ADMIN_SITEADMIN_DELETE = 20411;
	
	
	//站点管理员下企业信息修改
	public static final Integer SITE_INFO_UPDATE = 702;
	//系统管理员代替站点管理员管理站点:企业信息修改
	public static final Integer SYSTEM_INFO_UPDATE = 20412;
	
	//站点管理员下邮件服务器设置
	public static final Integer SITE_ADMIN_EMAILSERVER_SETUP = 801;
	//系统管理员代替站点管理员管理站点:邮件服务器设置
	public static final Integer SYSTEM_ADMIN_EMAILSERVER_SETUP = 20413;
	//站点管理员下邮件服务器测试发送邮件
	public static final Integer SITE_ADMIN_EMAILTEST = 802;
	//系统管理员代替站点管理员管理站点:邮件服务器测试发送邮件
	public static final Integer SYSTEM_ADMIN_EMAILTEST = 20414;
	
	//站点管理员下会议功能设置
	public static final Integer SITE_ADMIN_PREFERENCE_SETUP = 901;
	//系统管理员代替站点管理员管理站点:会议功能设置
	public static final Integer SYSTEM_ADMIN_CONFAUTHORITY_SETUP = 20415;
	//站点管理员下邮件模板
	
	//站点管理员企业管理员个人设置
	public static final Integer SITE_ADMIN_INFO_SETUP = 1101;
	//系统管理员代替站点管理员管理站点:管理员个人设置
	public static final Integer SYSTEM_ADMIN_INFO_SETUP = 20416;
	
	//站点管理员创建公告
	public static final Integer SITE_NOTICE_CREATE = 1001;
	//系统管理员代替站点管理员管理站点:创建公告
	public static final Integer SYSTEM_HELP_NOTICE_CREATE = 20417;
	
	
	//企业管理员修改企业联系人信息
	public static final Integer SITE_ADMIN_UPDATE_CONTACT = 1202;
	
	//企业管理员创建企业联系人信息
	public static final Integer SITE_ADMIN_CREATE_CONTACT = 1203;
	
	//企业管理员删除企业联系人信息
	public static final Integer SITE_ADMIN_DELETE_CONTACT = 1204;
	
	//企业管理员批量添加企业联系人
	public static final Integer SITE_ADMIN_BATCHADD_CONTACT = 1205;
	
	
	//站点管理员修改公告
	public static final Integer SITE_NOTICE_UPDATE = 1002;
	//系统管理员代替站点管理员管理站点:修改公告
	public static final Integer SYSTEM_HELP_NOTICE_UPDATE = 20418;
	//站点管理员删除公告
	public static final Integer SITE_NOTICE_DELETE = 1003;
	//系统管理员代替站点管理员管理站点:删除公告
	public static final Integer SYSTEM_HELP_NOTICE_DELETE = 20419;
	
	//站点管理员下邮件模板设置
	public static final Integer SITE_ADMIN_EMAILMODEL_SETUP = 20201;
	//系统管理员代替站点管理员管理站点:邮件模板设置
	public static final Integer SYSTEM_ADMIN_EMAILMODEL_SETUP = 20420;
	
	//站点管理员创建组织机构
	public static final Integer SITE_ORG_CREATE = 20301;
	//系统管理员代替站点管理员管理站点:创建组织机构
	public static final Integer SYSTEM_ORG_CREATE = 20421;
	//站点管理员修改组织机构
	public static final Integer SITE_ORG_UPDATE = 20302;
	//系统管理员代替站点管理员管理站点:修改组织机构
	public static final Integer SYSTEM_ORG_UPDATE = 20422;
	//站点管理员删除组织机构
	public static final Integer SITE_ORG_DELETE = 20303;
	//系统管理员代替站点管理员管理站点:删除组织机构
	public static final Integer SYSTEM_ORG_DELETE = 20423;
	//站点管理员为用户批量分配机构
	public static final Integer SITE_ORG_BATCH = 20304;
	//系统管理员代替站点管理员管理站点:用户批量分配机构
	public static final Integer SYSTEM_ORG_BATCH = 20424;
	//站点管理员将用户移出组织机构
	public static final Integer SITE_ORG_USER_REMOVE = 20305;
	//系统管理员代替站点管理员管理站点:将用户移出组织机构
	public static final Integer SYSTEM_ORG_USER_REMOVE = 20425;
	
}
