package com.bizconf.vcaasz.component.zoom;
/**
 * @desc 
 * @author Administrator
 * @date 2014-6-9
 */
public class HttpURLUtil {
	
	public static final String ZOOM_DOMINE = "https://www.zoomus.cn";
	
	/*************************** Meeting ********************************************/
	public static String createMeeting = ZOOM_DOMINE+"/v1/meeting/create";
	
	public static String updateMeeting = ZOOM_DOMINE+"/v1/meeting/update";
	
	public static String deleteMeeting = ZOOM_DOMINE+"/v1/meeting/delete";
	
	public static String listMeeting = ZOOM_DOMINE+"/v1/meeting/list";
	
	public static String getMeeting = ZOOM_DOMINE+"/v1/meeting/get";
	
	public static String endMeeting = ZOOM_DOMINE+"/v1/meeting/end";
	
	/*************************** Meeting By MA ********************************************/
	public static String createMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/create";
	
	public static String updateMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/update";
	
	public static String deleteMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/delete";
	
	public static String listMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/list";
	
	public static String getMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/get";
	
	public static String endMeetingMA = ZOOM_DOMINE+"/v1/ma/meeting/end";
	
	
	
	/*************************** Users ********************************************/
	public static String createUser = ZOOM_DOMINE+"/v1/user/autocreate2";
	
	public static String maCreateUser = ZOOM_DOMINE+"/v1/ma/user/autocreate2";
	
	public static String updateUser = ZOOM_DOMINE+"/v1/user/update";
	
	public static String maUpdateUser = ZOOM_DOMINE+"/v1/ma/user/update";
	
	public static String updatePasswordUser = ZOOM_DOMINE+"/v1/user/updatepassword";
	
	public static String maUpdatePasswordUser = ZOOM_DOMINE+"/v1/ma/user/updatepassword";
	
	public static String deleteUser = ZOOM_DOMINE+"/v1/user/delete";
	
	public static String maDeleteUser = ZOOM_DOMINE+"/v1/ma/user/delete";
	
	public static String listUser = ZOOM_DOMINE+"/v1/user/list";
	
	public static String getUser = ZOOM_DOMINE+"/v1/user/get";
	
	
	public static String maGetUser = ZOOM_DOMINE+"/v1/ma/user/get";
	
	
	/***************************** CDR *************************************************/
	public static String getCDRReport = ZOOM_DOMINE+"/v1/report/getuserreport";
	
	
	/**配对H323和sip终端**/
	public static final String PAIRH323_OR_SIP = ZOOM_DOMINE+"/v1/meeting/pairing";
	
	/**修改所属数据中心**/
	public static final String CHANGE_OWNER_ACCOUNT = ZOOM_DOMINE+"/v1/ma/user/changeowner";
}
