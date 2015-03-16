package com.bizconf.vcaasz.constant;

import java.util.Date;

import com.bizconf.vcaasz.util.SiteIdentifyUtil;

public interface EmailConstant {
	
	//创建站点
	String CRAETE_SITE_TEMP = "create_site.ftl";
	
	//删除站点
	String DEL_SITE_TEMP = "del_site.ftl";
	
	//更新站点
	String UPDATE_SITE_TEMP = "update_site.ftl";
	
	//创建系统管理员
	String CREATE_SYSUSER_TEMP = "create_sysuser.ftl";
	
	//修改系统管理员信息
	String UPDATE_SYSUSER_TEMP = "update_sysuser.ftl";
	
	//系统管理员重设密码
	String SYSUSER_RESETPWD_TEMP = "sysuser_resetpwd.ftl";
	
	//创建会议主持人邮件模板
	String CREATE_CONF_TEMP = "create_conf.ftl";
	
	//创建站点管理员用户模板
	String CREATE_SITEADMIN_TEMP = "create_site_admin.ftl";
	
	//修改站点管理员用户
	String UPDATE_SITEADMIN_TEMP = "update_site_admin.ftl";
	
	
	//创建企业用户模板
	String CREATE_SITEUSER_TEMP = "create_site_user.ftl";
	
	//修改企业用户模板
	String UPDATE_SITEUSER_TEMP = "update_site_user.ftl";
	
	//create name host
	String CREATE_HOST_TEMP = "create_namehost.ftl";
	
	//update name host
	String UPDATE_HOST_TEMP = "update_namehost.ftl";
	
	//站点过期
	String SITE_EXPIRED = "site_expired.ftl";
	
	//站点过期提醒
	String SITE_EXPIRED_REMIND = "site_expired_remind.ftl";
	
	//接受邀请提醒
	String ACCEPT_CONF_REMIND = "org_accept_conf_remind.ftl";
	
	String ACCEPT_CONF_REMIND_EN = "add_accept_conf_remind_en.ftl";
	
	//修改会议信息
	String MODIFY_CONF_REMIND = "org_update_conf.ftl";
	
	String MODIFY_CONF_REMIND_EN = "add_update_conf_en.ftl";
	
	
	//修改单次周期会议信息
	String MODIFY_SINGLE_CONF_REMIND = "update_single_conf.ftl";
	
	//取消会议信息
	String CANCEL_CONF_REMIND = "org_cancel_conf.ftl";
	
	String CANCEL_CONF_REMIND_EN = "add_cancel_conf_en.ftl";
	
	//取消单次周期会议信息
	String CANCEL_SINGLE_CONF_REMIND = "cancel_single_conf.ftl";
	
	//对未回应者的邮件提醒
	String CONF_REMIND_NOT_RESPONSE = "conf_remind.ftl";
	
	/**以下是邮件的一些默认设置信息**/
	//默认邮件服务端口
	String DEF_EMAIL_HOST_PORT = "25";
	
	//邮件初始位发送状态
	Integer NOT_SEND_FLAG = 1;
	
	//默认发送次数
	Integer DEF_SEND_COUNT = 0;
	
	//默认重新发送次数
	Integer DEF_RETRY_COUNT = 0;
	
	//默认 是否警告标志
	Integer DEF_WARN_FLAG = 0;
	
	//系统默认警告类型
	Integer DEF_WARN_TYPE = 0;
	
	//系统默认间隔（分）
	Integer DEF_GAP_MINUTE = 5;
	
	//系统默认before时间
	Integer DEF_BEFORE_MINUTE = 5;
	
	//系统默认警告次数
	Integer DEF_WARN_COUNT = 3;
	
	//txt邮件类型
	String EMAIL_TYPE_TXT = "txt";
	
	//html邮件类型
	String EMAIL_TYPE_HTML = "html";
	
	//1970-01-01
	Date DEF_SYS_DATE = new Date(0l);
	
	
	
	//
	String SITE_ADDRESS=SiteIdentifyUtil.getCurrentDomine();
	String SITE_PATH="static";		
			//siteaddress
	
	//会议邀请
	int TEMPLATE_INVITE = 1;
	
	//会议修改
	int TEMPLATE_MODIFY = 2;
	
	//会议取消
	int TEMPLATE_CANCEL = 3;
	
	//会议提醒
	int TEMPLATE_REMIND = 4;
	
	
	//普通会议提醒
	int WARNTYPE_REQUEST = 0;
	
	//提醒取消
	int WARNTYPE_CANCEL = 1;
	

	
	
}
