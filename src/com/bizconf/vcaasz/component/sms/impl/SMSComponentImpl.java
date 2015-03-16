package com.bizconf.vcaasz.component.sms.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.action.user.ConfController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.component.sms.SMSComponent;
import com.bizconf.vcaasz.component.sms.SMSUtils;
import com.bizconf.vcaasz.component.zoom.HttpReqeustUtil;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfCycle;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.EmailLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.SMSService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;

/**
 * @desc 短信发送通知Component
 * @author Darren
 * @date 2014-9-3
 */
@Component
public class SMSComponentImpl implements SMSComponent {

	private final Logger logger = Logger.getLogger(SMSComponentImpl.class);
	/**邮件服务获取业务数据接口*/
	@Autowired
	private EmailLogic emailLogic;
	
	@Autowired
	private ConfService confService;
	
	@Autowired
	private SMSService smsService;
	/**
	 * 发送短信邀请
	 * @param users 	被邀请参会者
	 * @param confInfo	会议信息
	 * @param sendType	被邀请出席的会议类型 正在进行 =1、预约的普通和周期会议=2  和修改会议发送短信（普通和周期【本次和全部】）=3
	 * @param currentUserBase 当前发送邀请主持人
	 * */
	@Override
	public Map<String, String> sendMsgInviteToConfUser(List<ConfUser> users,
			ConfBase confInfo, Integer sendType,UserBase currentUser) {

		Map<String, String> retMap = new HashMap<String, String>();
		
		if(users == null || users.isEmpty() || users.size() < 1 ){
			retMap.put("result", "");
			retMap.put("description", "发送邀请联系人为空");
			return retMap;
		}
		if(confInfo == null){
			retMap.put("result", "");
			retMap.put("description", "会议信息为空");
			return retMap;
		}
		//转化会议时间
		transferConfDate(confInfo);
		StringBuilder userNObuBuilder = new StringBuilder();
		int len = users.size();
		int i = 0;
		for(ConfUser confUser : users){	//发送短信限制1000条
			userNObuBuilder.append(confUser.getTelephone());
			if(i != len-1){
				userNObuBuilder.append(",");
			}
		}
		
		String sendUserNO = userNObuBuilder.toString();
		//号码为空
		if("".equals(sendUserNO)|| sendUserNO.isEmpty() || sendUserNO == null){
			retMap.put("result", "");
			retMap.put("description", "发送信息号码为空");
			return retMap;
		}
		//根据发送的会议类型组织发送内容并且发送
		if(sendType.intValue() == 1){		//正在进行会议
			retMap = sendMeetingMsgToUser(confInfo,sendUserNO,currentUser);
		}else if(sendType.intValue() == 2){//预约的普通和周期会议
			//单次预约会议
			if(confInfo.getCycleId()!=null && confInfo.getCycleId().intValue() == 0){
				retMap = sendSingleBookConfMsgToUser(confInfo,sendUserNO,currentUser);
			}else{	//周期会议
				ConfCycle confCycle = confService.getConfCyclebyConfId(confInfo.getCycleId().intValue());
				String cycleMode = cycleMode(confCycle);
				retMap = sendCycleBookConfMsgToUser(confInfo,sendUserNO,currentUser,cycleMode);
			}
		}else if(sendType.intValue() == 3){//修改会议发送短信（普通和周期【本次和全部】）
			retMap = sendUpdateConfMsgToUser(confInfo,sendUserNO,currentUser);
		}else if(sendType.intValue() == 4){
			retMap = sendCancleConfMsgToUser(confInfo,sendUserNO,currentUser);
		}
		return retMap;
	}
	
	/**
	 * 取消会议发送短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	@Override
	public Map<String, String> sendCancleConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser) {
		//(用户名)邀请您出席的视频会议[会议主题]已取消
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(currentUser.getTrueName());
			buffer.append(")邀请您出席的视频会议[");
			buffer.append(confInfo.getConfName()+"]已取消");
			
			retMap = sendMsgForUserByPhones(buffer.toString(), sendUserNO);
			if(!retMap.isEmpty()){
				smsService.saveSMSInfo(currentUser,confInfo,buffer.toString(),sendUserNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 修改会议发送短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	@Override
	public Map<String, String> sendUpdateConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser) {
		
		//(用户名)邀请您出席的视频会议[会议主题]时间已变更为(时间),请点击:链接地址
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(currentUser.getTrueName());
			buffer.append(")邀请您出席的视频会议["+confInfo.getConfName()+"]时间已变更为(");
			buffer.append(sdf.format(confInfo.getStartTime()));
			buffer.append("("+confInfo.getTimeZoneDesc()+"))");
			buffer.append(",请点击:");
			buffer.append(genJoinUrl(confInfo));
			retMap = sendMsgForUserByPhones(buffer.toString(), sendUserNO);
			if(!retMap.isEmpty()){
				smsService.saveSMSInfo(currentUser,confInfo,buffer.toString(),sendUserNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 发送周期预约会议短信提醒
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * @param cycleMode 
	 * */
	@Override
	public Map<String, String> sendCycleBookConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser, String cycleMode) {
		//(用户名) 邀请您出席视频会议，会议时间 每周一、周二 10：30（北京 GMT+8），请点击：http://vcaas.cn/j/18601280261
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(currentUser.getTrueName());
			buffer.append(")邀请您出席视频会议["+confInfo.getConfName()+"],会议时间(");
			buffer.append(cycleMode);
			buffer.append(sdf.format(confInfo.getStartTime()));
			buffer.append("("+confInfo.getTimeZoneDesc("zh-cn")+")),请点击:");
			buffer.append(genJoinUrl(confInfo));
			
			retMap = sendMsgForUserByPhones(buffer.toString(), sendUserNO);
			if(!retMap.isEmpty()){
				smsService.saveSMSInfo(currentUser,confInfo,buffer.toString(),sendUserNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * 发送单次预约会议
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	@Override
	public Map<String, String> sendSingleBookConfMsgToUser(ConfBase confInfo,
			String sendUserNO, UserBase currentUser) {
		
		//(用户名)邀请您出席视频会议（会议主题），开始时间2014-8-20 10:30（北京 GMT+8）开始，请点击：http://vcaas.cn/j/18601280261
		Map<String, String> retMap = new HashMap<String, String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(currentUser.getTrueName());
			buffer.append(")邀请您出席视频会议[");
			buffer.append(confInfo.getConfName());
			buffer.append("],开始时间(");
			buffer.append(sdf.format(confInfo.getStartTime()));
			buffer.append("("+confInfo.getTimeZoneDesc()+"))开始,请点击:");
			buffer.append(genJoinUrl(confInfo));
			retMap = sendMsgForUserByPhones(buffer.toString(), sendUserNO);
			if(!retMap.isEmpty()){
				smsService.saveSMSInfo(currentUser,confInfo,buffer.toString(),sendUserNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 正在进行的会议发送邀请
	 * @param confInfo 
	 * @param sendUserNO 
	 * @param currentUser 
	 * */
	@Override
	public Map<String, String> sendMeetingMsgToUser(ConfBase confInfo, String sendUserNO,UserBase currentUser) {
		
		//(用户名)邀请您参加一场正在进行的视频会议（会议主题），点击加入：http:// vcaas.cn/j/18601280261
		Map<String, String> retMap = new HashMap<String, String>();
		try {//组织发送内容
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(currentUser.getTrueName());
			buffer.append(")邀请您参加一场正在进行的视频会议[");
			buffer.append(confInfo.getConfName());
			buffer.append("],点击加入:");
			buffer.append(genJoinUrl(confInfo));
			retMap = sendMsgForUserByPhones(buffer.toString(), sendUserNO);
			if(!retMap.isEmpty()){
				smsService.saveSMSInfo(currentUser,confInfo,buffer.toString(),sendUserNO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * 周期
	 * */
	private String cycleMode(ConfCycle confCycle){
		
		String[] weekName = {"周日","周一","周二","周三","周四","周五","周六"}; 
		
		//定期模式：按月（6号；第2个周一）
		StringBuilder cycleMode = new StringBuilder("");
		if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_MONTHLY.intValue()){      //按月循环的
			if(confCycle.getCycleValue().indexOf(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT)>0){     //有分号的
				String[] monthValueArray = confCycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_MONTH_SPLIT);
//				String week = ResourceHolder.getInstance().getResource("system.month." + monthValueArray[1]);
				int week = Integer.parseInt(monthValueArray[1]);
				cycleMode.append(String.format("按月(每月第%s个%s)", monthValueArray[0], weekName[week]));   // 格式化字符串，按月(每月第几个周几)
			}else{
				cycleMode.append(String.format("按月(每月第%s天)", confCycle.getCycleValue()));   // 格式化字符串，按月(每月第几天)
			}
		}else if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_DAILY.intValue()){    //按日循环的
			cycleMode.append(String.format("按日(每%s天)", confCycle.getCycleValue()));      // 格式化字符串，按日(每几天)
		}else if(confCycle.getCycleType().intValue() == ConfConstant.CONF_CYCLE_WEEKLY.intValue()){   //按周循环的
			StringBuilder week = new StringBuilder();
			for(String weekValue : confCycle.getCycleValue().split(ConfConstant.CONF_CYCLE_VALUE_DAYS_SPLIT)){
//				week.append(ResourceHolder.getInstance().getResource("system.month." + weekValue)).append(",");
				week.append(weekName[Integer.parseInt(weekValue)-1]).append(",");
			}
			cycleMode.append(String.format("按周(每%s)", 
					week.deleteCharAt(week.lastIndexOf(",")).toString()));      // 格式化字符串，按周（每周几）
		}
		return cycleMode.toString();
	}
	
	/**
	 * 生成加入会议连接(短链接)
	 * */
	@Override
	public String getJionUrl(ConfBase conf, int type) {
		
		//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
		String encrypt = "bizconfzoom";
		MD5 md5 = new MD5();
		String joinUrl = "";
		if(type==ConfConstant.HOST_JOIN_TYPE_EMAIL){//主持人通过email入会
			joinUrl = "j/"+conf.getId()+""+ConfConstant.HOST_JOIN_TYPE_EMAIL+"/"+md5.encrypt(encrypt + ConfConstant.HOST_JOIN_TYPE_EMAIL + conf.getId());
		}else if(type==ConfConstant.USER_JOIN_TYPE_EMAIL){//参会者通过email入会
			joinUrl = "j/"+conf.getId()+""+ConfConstant.USER_JOIN_TYPE_EMAIL+"/"+md5.encrypt(encrypt + ConfConstant.USER_JOIN_TYPE_EMAIL + conf.getId());
		}
		
//		joinUrl = "http://"+SiteIdentifyUtil.getCurrentDomine() + "/" + joinUrl;
		joinUrl = SMSUtils.SMSMSG_URL + joinUrl;
		return joinUrl;
	}
	
	/**
	 * modified by Chris Gao 02/16/15
	 * @param conf
	 * @return
	 */
	private String genJoinUrl(ConfBase conf){
		String joinUrl = "";
		if(conf!=null){
			if(conf.getJoinUrl().contains("www.zoomus.cn")){
				joinUrl = conf.getJoinUrl().replace("www.zoomus.cn", "www.confcloud.cn");
			}else if(conf.getJoinUrl().contains("bizconf.zoomus.cn")){
				joinUrl = conf.getJoinUrl().replace("bizconf.zoomus.cn", "www.confcloud.cn");
			}else if(!StringUtil.isEmpty(conf.getHostKey())){
				joinUrl = conf.getJoinUrl();
				if(joinUrl.contains("?pwd=")){
					joinUrl = joinUrl.substring(0, joinUrl.indexOf("?pwd="));
				}
				joinUrl += "?pwd="+conf.getHostKey();
			}else{
				joinUrl = conf.getJoinUrl();
			}
			return joinUrl.replaceFirst("https", "http");
		}
		return "";
	}
	
	/**
	 * 转化会议时间为站点的当地时间
	 * @param conf
	 */
	private void transferConfDate(ConfBase conf){
		if(conf!=null){
			if(conf.getTimeZone()!=null){
				conf.setStartTime(DateUtil.getOffsetDateByGmtDate(conf.getStartTime(), conf.getTimeZone().longValue()));
				conf.setEndTime(DateUtil.getOffsetDateByGmtDate(conf.getEndTime(), conf.getTimeZone().longValue()));
			}else {
				conf.setStartTime(emailLogic.getSiteLocalDate(conf.getStartTime(), conf.getSiteId()));
				conf.setEndTime(emailLogic.getSiteLocalDate(conf.getEndTime(), conf.getSiteId()));
			}
		}
	}
	
	/**
	 * 发送短信
	 * @param sendUserNO 手机号,如果 多个以逗号分隔
	 * @param msgContent 短信发送模板内容
	 * @return {result=0, description=发送短信成功, taskid=22434596014, task_id=22434596014, faillist=}
	 * */
	@Override
	public Map<String, String> sendMsgForUserByPhones(String msgContent,  String sendUserNO){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			logger.info("发送短信内容(GBK编码前)>>>>"+msgContent);
			msgContent = URLEncoder.encode(msgContent,"GBK");
			String retParam = SMSUtils.smsAuthGet(msgContent, sendUserNO);
			resultMap = sendMsgByGet(SMSUtils.SMS_URL+retParam);
			logger.info("发送短信内容(GBK编码后)>>>>"+msgContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 通过Post方式发送短信通知
	 * @param reqURL 短信服务平台连接
	 * @param params 发送短信的参数（Map形式）
	 * @return Map<String, String>
	 * */
	@SuppressWarnings({ "deprecation" })
	@Override
	public Map<String, String> sendMsgByPost(String reqURL , Map<String, String> params){
		long responseLength = 0;                         //响应长度 
		String responseContent = null;                   //响应内容 
		HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例 
		
		HttpPost httpPost = new HttpPost(reqURL);                        //创建HttpPost 
        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //构建POST请求的表单参数 
        for(Map.Entry<String,String> entry : params.entrySet()){ 
     	   formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
        } 
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost); //执行POST请求 
	        HttpEntity entity = response.getEntity();             //获取响应实体 
	        if (null != entity) { 
	            responseLength = entity.getContentLength(); 
	            responseContent = EntityUtils.toString(entity, "GBK"); 
	            EntityUtils.consume(entity); //Consume response content 
	        } 
	        logger.info("请求地址: " + httpPost.getURI()); 
	        logger.info("响应状态: " + response.getStatusLine()); 
	        logger.info("响应长度: " + responseLength); 
	        logger.info("响应内容: " + responseContent); 
	        
	        Map<String, String> retMap = new HashMap<String, String>();
	        if(responseContent!=null){
	        	//将返回结果组织成Map的形式
	        	String[] keyValue = responseContent.split("&");
	        	for(int i = 0;i < keyValue.length; i++){
	        		String[] keyValTemp = keyValue[i].split("=");
	        		if(keyValTemp.length == 1){
		        		retMap.put(keyValTemp[0], "");
		        		continue;
	        		}
	        		retMap.put(keyValTemp[0], keyValTemp[1]);
	        	}
	        }
	        logger.info("发送短信返回结果 = >>>>>"+retMap);
	        return retMap;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return null;
	}
	
	
	/**
	 * 通过Get方式发送短信通知
	 * @param reqURL 短信服务平台连接
	 * @return Map<String, String>
	 * */
	@Override
	public Map<String, String> sendMsgByGet(String reqURL){
		
		String responseContent = HttpReqeustUtil.sendMsgByGet(reqURL);
		Map<String, String> retMap = new HashMap<String, String>();
        if(responseContent!=null){
        	//将返回结果组织成Map的形式
        	String[] keyValue = responseContent.split("&");
        	for(int i = 0;i < keyValue.length; i++){
        		String[] keyValTemp = keyValue[i].split("=");
        		if(keyValTemp.length == 1){
	        		retMap.put(keyValTemp[0], "");
	        		continue;
        		}
        		retMap.put(keyValTemp[0], keyValTemp[1]);
        	}
        }
        logger.info("发送短信返回结果 = >>>>>"+retMap);
        return retMap;
	}

}
