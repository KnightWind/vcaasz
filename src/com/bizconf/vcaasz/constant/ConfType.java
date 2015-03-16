package com.bizconf.vcaasz.constant;

/** 
 *   
 * @package com.bizconf.vcaasz.constant 
 * @description: 描述会议类型
 * @author Martin
 * @date 2014年7月15日 上午11:36:57 
 * @version 
 */
public interface ConfType {
	
	
	//预约会议
	int SCHEDULE = 0;
	
	//即时会议
	int INSTANT = 1;
	
	//周期会议
	int RECURR = 2;
	
	//周期会议子会议
	int SUB_RECURR = 3;
	
	
	//非永久会议
	int PERMANENT_CONF_FALSE = 0;
	
	
	//永久会议
	int PERMANENT_CONF_TRUE = 1;
	
	
	
	//客户端创建的会议
	int CREATE_TYPE_CLIENT = 1;
	
	//protal端创建的会议
	int CREATE_TYPE_PROTAL = 2;
	
}
