package com.bizconf.vcaasz.component.zoom.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.component.zoom.HttpURLUtil;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.APIReturnCode;
import com.bizconf.vcaasz.constant.UserConstant;
import com.bizconf.vcaasz.constant.ZoomConfStatus;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 对用户的增删改查
 * @author Administrator
 * @date 2014-6-9
 */
@Component
public class ZoomUserOperationComponentImpl implements
		ZoomUserOperationComponent {

	public Map<String, Object> create(String apiKey,String apiToken,String email, int type,int meetingCapacity, String password,
			String firstName, String lastName, boolean disableChat,
			boolean enableEncrytion) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("email", email);
		params.put("type", type);
		params.put("password", password);
		params.put("meeting_capacity", meetingCapacity);
		
		if(firstName!=null){
			params.put("first_name", firstName);
		}
		if(lastName!=null){
			params.put("last_name", lastName);
		}
		params.put("disable_chat", disableChat);
		params.put("enable_e2e_encryption", enableEncrytion);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.createUser,params);
	}
	
	
	public Map<String, Object> maCreate(String apiKey,String apiToken,String accountId,
			String email, int type,int meetingCapacity, String password,
			String firstName, String lastName, boolean disableChat,
			boolean enableEncrytion) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		params.put("email", email);
		params.put("type", type);
		params.put("password", password);
		params.put("meeting_capacity", meetingCapacity);
		
		if(firstName!=null){
			params.put("first_name", firstName);
		}
		if(lastName!=null){
			params.put("last_name", lastName);
		}
		params.put("disable_chat", disableChat);
		params.put("enable_e2e_encryption", enableEncrytion);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maCreateUser,params);
	}
	

	public int update(String apiKey,String apiToken,String userId, int type, int meetingCapacity,String firstName,
			String lastName, boolean disableChat, boolean enableEncrytion) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", userId);
		
		if(type>0){
			params.put("type", type);
		}
		if(firstName!=null){
			params.put("first_name", firstName);
		}
		if(lastName!=null){
			params.put("last_name", lastName);
		}
		params.put("meeting_capacity", meetingCapacity);
		params.put("disable_chat", disableChat);
		params.put("enable_e2e_encryption", enableEncrytion);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateUser,params);
		if(retMap.get("error") == null){
			return UserConstant.UPDATE_USER_SUCCESS;
		}
		return UserConstant.UPDATE_USER_FAIL;
	}
	
	public int maUpdate(String apiKey,String apiToken,String accountId,
			String userId, int type, int meetingCapacity,String firstName,
			String lastName, boolean disableChat, boolean enableEncrytion) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", userId);
		
		if(type>0){
			params.put("type", type);
		}
		if(firstName!=null){
			params.put("first_name", firstName);
		}
		if(lastName!=null){
			params.put("last_name", lastName);
		}
		params.put("meeting_capacity", meetingCapacity);
		params.put("disable_chat", disableChat);
		params.put("enable_e2e_encryption", enableEncrytion);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maUpdateUser,params);
		if(retMap.get("error") == null){
			return UserConstant.UPDATE_USER_SUCCESS;
		}
		return UserConstant.UPDATE_USER_FAIL;
	}

	public boolean modifyPassword(String apiKey,String apiToken,String userId, String password) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", userId);
		params.put("password", password);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updatePasswordUser,params);
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean maModifyPassword(String apiKey,String apiToken,
			String accountId, String userId, String password) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", userId);
		params.put("password", password);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maUpdatePasswordUser,params);
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}

	public boolean delete(String apiKey,String apiToken,String userId) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", userId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.deleteUser,params);
		if(retMap!=null && retMap.get("id")!=null && !retMap.get("id").toString().equals("")){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean maDelete(String apiKey,String apiToken,
			String accountId,String userId) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", userId);
		
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maDeleteUser,params);
		if(retMap!=null && retMap.get("id")!=null && !retMap.get("id").toString().equals("")){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listAll(String apiKey,String apiToken,int pageSize, int pageNumber) {

		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		if(pageSize>0 && pageSize<300){
			params.put("page_size", pageSize);//每页的条数 Defaults to 30.Max of 300 meetings
		}else if(pageSize <= 0){
			params.put("page_size", 30);
		}else if(pageSize >= 300){
			params.put("page_size", 300);
		}
		params.put("page_number", pageNumber>0?pageNumber:1);//页数  Default to 1
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listUser,
				params);
		if(tempMap.get("error")!=null){
			return new ArrayList<Map<String,Object>>();
		}
		List<Map<String, Object>> retList = (List<Map<String, Object>>) tempMap.get("users");
		return retList;
	}
	
	
	public  Map<String, Object> pagelistInfo(String apiKey,String apiToken,int pageSize, int pageNumber) {
		
//		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		if(pageSize>0 && pageSize<300){
			params.put("page_size", pageSize);//每页的条数 Defaults to 30.Max of 300 meetings
		}else if(pageSize <= 0){
			params.put("page_size", 30);
		}else if(pageSize >= 300){
			params.put("page_size", 300);
		}
		params.put("page_number", pageNumber>0?pageNumber:1);//页数  Default to 1
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.listUser,
				params);
		if(tempMap.get("error")!=null){
			return new HashMap<String, Object>();
		}
		
		return tempMap;
		
		
	}

	public Map<String, Object> get(String apiKey,String apiToken,String id) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", id);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.getUser,
				params);
	}
	
	public Map<String, Object> maGet(String apiKey,String apiToken,
			String accountId,String id) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", id);
		
		return HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maGetUser,
				params);
	}

	@Override
	public String getZoomToken(String apiKey,String apiToken,String id) {
		Map<String, Object> userMap = get(apiKey,apiToken,id);
		if (userMap.get("token")!=null && !userMap.get("token").toString().equals("")) {
			
			return userMap.get("token").toString();
		}
		return null;
	}
	
	
	@Override
	public String getZoomPMI(String apiKey,String apiToken,String id) {
		Map<String, Object> userMap = get(apiKey,apiToken,id);
		if (userMap.get("pmi")!=null && !userMap.get("pmi").toString().equals("")) {
			
			return userMap.get("pmi").toString();
		}
		return "";
	}
	
	@Override
	public String maGetZoomPMI(String apiKey,String apiToken,
			String account,String id) {
		Map<String, Object> userMap = maGet(apiKey,apiToken,account,id);
		if (userMap.get("pmi")!=null && !userMap.get("pmi").toString().equals("")) {
			
			return userMap.get("pmi").toString();
		}
		return "";
	}

	@Override
	public boolean setZoomPMI(String apiKey,String apiToken,String id, String pmi) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("id", id);
		params.put("pmi", pmi);
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateUser,
				params);
		if(tempMap.get("id")!=null && !"".equals(tempMap.get("id").toString())){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean maSetZoomPMI(String apiKey,String apiToken,
			String accountId, String id, String pmi) {
		
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("id", id);
		params.put("pmi", pmi);
		
		Map<String, Object> tempMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maUpdateUser,
				params);
		if(tempMap.get("id")!=null && !"".equals(tempMap.get("id").toString())){
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyPortNum(String apiKey,String apiToken,String userId, int meetingCapacity) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		params.put("id", userId);
		//Add by Darren at 2014-12-24 
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		
		params.put("meeting_capacity", meetingCapacity);
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.updateUser,params);
		
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean maModifyPortNum(String apiKey,String apiToken,
			String accountId, String userId, int meetingCapacity) {
		Map<String, Object> params = HttpReqeustUtil.getAuthMap();
		params.put("id", userId);
		params.put("api_key", apiKey);
		params.put("api_secret", apiToken);
		params.put("account_id", accountId);
		
		params.put("meeting_capacity", meetingCapacity);
		Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.maUpdateUser,params);
		
		if(retMap.get("error") == null){
			return true;
		}
		return false;
	}

	@Override
	public int changeUserOwnerAccount(String masterKey, String masterSecret,
			String accountId, String userId) {
		
		 if(StringUtil.isEmpty(masterKey) || StringUtil.isEmpty(masterSecret)
				 || StringUtil.isEmpty(accountId) || StringUtil.isEmpty(userId)){
			 return APIReturnCode.CALLING_FAILED;
		 }
		 Map<String, Object> params = new HashMap<String, Object>(); 
		 params.put("api_key", masterKey); 
		 params.put("api_secret", masterSecret); 
		 params.put("account_id", accountId); 
		 params.put("user_id", userId); 
		 params.put("data_type", "json"); 
		 
		 Map<String, Object> retMap = HttpReqeustUtil.sendSSLPostRequest(HttpURLUtil.CHANGE_OWNER_ACCOUNT,params);
		 if(retMap.get("error") == null){
			 return APIReturnCode.RET_SUCCESS;
		 }else{
			 return APIReturnCode.CALLING_FAILED;
		 }
	}

	
	
}
