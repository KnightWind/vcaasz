package com.bizconf.vcaasz.constant;

import java.util.HashMap;
import java.util.Map;


public class SortConstant {
	

	
	public static final String SORT_ASC="0";//正序排序sortord
	public static final String SORT_DESC="1";//倒序序排序
	
	
	//事件日志
	public static final String[][] EVENTLOG_FIELDS=new String[][]{
		{"0","id"},
		{"1","logStatus"},
		{"2","createTime"}
	} ;

	//默认排序编码
	public static final String EVENTLOG_SORT_DEFAULT="0";
	
	//类型排序编码
	public static final String EVENTLOG_SORT_STATUS="1";
	
	//日志时间编码
	public static final String EVENTLOG_SORT_CREATETIME="2";
	
	
	//站点信息排序
	public static final String[][] SITEBASE_FIELDS=new String[][]{
		{"0","id"},
		{"1","siteSign"},
		{"2","siteFlag"},
		{"3","expireDate"},
		{"4","lockFlag"},
		{"5","effeDate"}
	} ;
	
	//站点信息排序
	public static final String[][] SITE_ORG_FIELDS=new String[][]{
		{"0","id"},
		{"1", "orgName"}
	};
	
	public static final String[][] CONTACTS_FIELDS = new String[][]{
		{"0","id"},
		{"1","contact_name"}
	};
	
	//企业管理员排序
	public static final String[][] USERBASE_FIELDS = new String[][]{
		{"1","userStatus"},
		{"2","trueName"},
		{"3","loginName"}
	};
	//入会详情
	public static final Map<String, String> CONFLOG_FIELDS = new HashMap<String, String>(2);
	static{
		CONFLOG_FIELDS.put("1", "user_name");
		CONFLOG_FIELDS.put("2", "term_type");
	}
	
	
	public static final String SITEBASE_SORT_DEFAULT="0";
	public static final String SITEBASE_SORT_SIGN="1";
	
	
	
	
	//会议排序字段对应关系
	public static final String[][] CONFBASE_FIELDS=new String[][]{
		{"0","id"},
		{"1","endTime"},
		{"2","confType"},
		{"3","confStatus"},
		{"4","startTime"}
	};

	public static final String CONFBASE_SORT_DEFAULT="0";
	public static final String CONFBASE_SORT_ENDTIME="1";
	public static final String CONFBASE_SORT_CONFTYPE="2";
	public static final String CONFBASE_SORT_STATUS="3";
	public static final String CONFBASE_SORT_STARTTIME="4";
	
	/**
	 * 用户登录日志排序字段对应关系
	 * wangyong
	 * 2013.8.9
	 */
	public static final String[][] LOGIN_LOG_SORT_FIELDS = new String[][]{
		{"0","id"},
		{"1","loginStatus"},
		{"2","loginName"},
		{"3","siteId"},
		{"4","userType"}
	};
	public static final String LOGIN_LOG_SORT_DEFAULT = "0";
	public static final String LOGIN_LOG_SORT_LOGINSTATUS = "1";
	public static final String LOGIN_LOG_SORT_LOGINNAME = "2";
	public static final String LOGIN_LOG_SORT_SITEID = "3";
	public static final String LOGIN_LOG_SORT_USERTYPE = "4";
	
	
}
