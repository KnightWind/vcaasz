package com.bizconf.vcaasz.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.encrypt.Base64;
import com.bizconf.encrypt.MD5;


public class TestService {
	
	
	public static void makeJoinMtgUrl(String siteId,String mtgTitle,String mtgKey,String userName,String userId){
		
		
//		String siteId="int";
		String language="0";
		String duration="120";
//		String mtgTitle="测试外部入口进入会议003";
//		String mtgKey="int2013202";
//		String userName="Anke";
//		String userId="0";ymkfzc
		String hostPwd="112233445566";
		hostPwd=Base64.encode(hostPwd,"utf8").replaceAll("/","_");
		String mtgPwd="8q8w8e8s";
		mtgPwd=Base64.decode("FX0GDWvLEP5ZwsgasfFOog==");
		System.out.println("mtgPwd====   "+mtgPwd);
//		String userType="8";
//		String timestamp=(new Date()).getTime()+"";//(new Date()).getTime()+"";	
//		String authSource=ConstantUtil.JOIN_MTG_PUBLIC_KEY+siteId+mtgKey+userId+userType+timestamp;
////		authBuffer.append(""+ConstantUtil.JOIN_MTG_PUBLIC_KEY);
////		authBuffer.append(""+siteSign);
////		authBuffer.append(""+mtgKey);
////		authBuffer.append(""+userId);
////		authBuffer.append(""+userType);7e357968d85b6698f799c6965105e726
////		authBuffer.append(""+timestamp);
//		String authId=MD5.encodePassword(authSource, "MD5");
//		StringBuffer joinUrlBuffer=new StringBuffer();
//		joinUrlBuffer.append(""+siteId+".confcloud.cn/");
//		joinUrlBuffer.append("join_mtg.asp?siteId="+siteId);
//		joinUrlBuffer.append("&language="+language);
//		joinUrlBuffer.append("&duration="+duration);
//		joinUrlBuffer.append("&mtgTitle="+string2Unicodes(mtgTitle));
//		joinUrlBuffer.append("&mtgKey="+mtgKey);
//		joinUrlBuffer.append("&hostPwd="+hostPwd);
//		joinUrlBuffer.append("&mtgPwd="+mtgPwd);
//		joinUrlBuffer.append("&userName="+string2Unicodes(userName));
//		joinUrlBuffer.append("&userId="+userId);
//		joinUrlBuffer.append("&userType="+userType);
//		joinUrlBuffer.append("&timestamp="+timestamp);
//		joinUrlBuffer.append("&authId="+authId);
////		System.out.println(Base64.decode("MTYyODMzNDU1MDY4","utf8"));
////		System.out.println(MD5.encodePassword(authSource, "MD5"));
////		
//		System.out.println("");
//		System.out.println(userName);
//		System.out.println("");
//		System.out.println(joinUrlBuffer.toString());
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String siteId="go";
//		String mtgTitle=siteId+"站点上通过外部入会地址进入会议";
//		String mtgKey="201300112211121"; 
//		List<String[]> userList=new ArrayList<String[]>();
//		userList.add(new String[]{"Dick","5158"});
//		userList.add(new String[]{"Chris","9997"});
////		userList.add(new String[]{"John","9993"});
////		userList.add(new String[]{"Anke","9992"});
//		userList.add(new String[]{"Frank","9998"});
////		userList.add(new String[]{"William","9991"});
////		userList.add(new String[]{"Alan","9996"});
////		userList.add(new String[]{"Martin","9995"});
////		userList.add(new String[]{"Jack","9990"});
//	//	userList.add(new String[]{"John001","0"});
//		for(String[] userInfo:userList){
//			makeJoinMtgUrl(siteId,mtgTitle,mtgKey,userInfo[0],userInfo[1]);
//		}
//		
		
//		System.out.println();
//		String siteId="int";
//		String language="0";
//		String duration="120";
//		String mtgTitle="测试外部入口进入会议003";
//		String mtgKey="int2013201";
//		String userName="Anke";
//		String userId="0";
//		String hostPwd="1q2w3e4r5t6y";
//		hostPwd=Base64.encode(hostPwd,"utf8").replaceAll("/","_");
//		String mtgPwd="8q8w8e8r8g8y";
//		mtgPwd=Base64.encode(mtgPwd,"utf8").replaceAll("/","_");
//		String userType="8";
//		String timestamp=(new Date()).getTime()+"";//(new Date()).getTime()+"";	
//		String authSource=ConstantUtil.JOIN_MTG_PUBLIC_KEY+siteId+mtgKey+userId+userType+timestamp;
////		authBuffer.append(""+ConstantUtil.JOIN_MTG_PUBLIC_KEY);
////		authBuffer.append(""+siteSign);
////		authBuffer.append(""+mtgKey);
////		authBuffer.append(""+userId);
////		authBuffer.append(""+userType);7e357968d85b6698f799c6965105e726
////		authBuffer.append(""+timestamp);
//		String authId=MD5.encodePassword(authSource, "MD5");
//		StringBuffer joinUrlBuffer=new StringBuffer();
//		joinUrlBuffer.append(""+siteId+".confcloud.cn/");
//		joinUrlBuffer.append("join_mtg.asp?siteId="+siteId);
//		joinUrlBuffer.append("&language="+language);
//		joinUrlBuffer.append("&duration="+duration);
//		joinUrlBuffer.append("&mtgTitle="+string2Unicodes(mtgTitle));
//		joinUrlBuffer.append("&mtgKey="+mtgKey);
//		joinUrlBuffer.append("&hostPwd="+hostPwd);
//		joinUrlBuffer.append("&mtgPwd="+mtgPwd);
//		joinUrlBuffer.append("&userName="+string2Unicodes(userName));
//		joinUrlBuffer.append("&userId="+userId);
//		joinUrlBuffer.append("&userType="+userType);
//		joinUrlBuffer.append("&timestamp="+timestamp);
//		joinUrlBuffer.append("&authId="+authId);
//		System.out.println(Base64.decode("MTYyODMzNDU1MDY4","utf8"));
//		System.out.println(MD5.encodePassword(authSource, "MD5"));
//		
//		
//		System.out.println(joinUrlBuffer.toString());
//		String joinUrl="join_mtg.asp?siteId="+siteId+"";
//		ConfService confservice = AppContextFactory.getAppContext().getBean(ConfService.class);
//		confservice.syncConfStatus(confservice.getConfBasebyConfId(2249));
//		
//
//		System.out.println("0&1=="+('0'&'1'));
//		System.out.println("1&1=="+('1'&'1'));
//		System.out.println("0&0=="+('0'&'0'));
//	//	System.out.println("0^1=="+(0^1));
//		System.out.println("0|1=="+(0|1));
//		System.out.println("0|0=="+(0|0));
//		System.out.println("1|1=="+(1|1));
//
//		EmpowerConfigService empowerService=AppContextFactory.getAppContext().getBean(EmpowerConfigService.class);
//		EmpowerConfig userPower=empowerService.getUserEmpowerConfigByUserId(1000150);
//		System.out.println("userPower=="+userPower);


//		EmpowerConfigService empowerService=AppContextFactory.getAppContext().getBean(EmpowerConfigService.class);
//		UserService userService=AppContextFactory.getAppContext().getBean(UserService.class);
//		UserBase userBase=userService.getUserBaseById(1000150);
////		EmpowerConfig userPower=empowerService.getUserEmpowerConfigByUserId(1000150);
////
////		EmpowerConfig sitePower=empowerService.getSiteEmpowerConfigBySiteId(11);
//	//	EmpowerConfig globalPower=empowerService.getSiteEmpowerGlobalBySiteId(11);
//		
//		EmpowerConfig empowerConfig=empowerService.makeEmpowerForConf(userBase);
//		System.out.println("userPower=="+empowerConfig);
		
		
		

//		ConfManagementService confManagementService=AppContextFactory.getAppContext().getBean(ConfManagementService.class);
		
		

		
//		ConfService confService=AppContextFactory.getAppContext().getBean(ConfService.class);
//		UserService userService=AppContextFactory.getAppContext().getBean(UserService.class);
//		UserBase userBase=userService.getUserBaseById(1000068);
//		//
//		List<ConfBase> confList=null;
//		PageBean<ConfBase> comingDate=null;
//		int pageCount=5;
//		for(int ii=1;ii<pageCount;ii++){
//			System.out.println("------------Host------------------>>>>>>"+ii);
//			comingDate=confService.getMonthlyComingConfListForHost(null, userBase, ii,null,null);//(userBase, ii);
//			confList=comingDate.getDatas();
//			if(confList!=null && confList.size() > 0){
//				for(ConfBase confBase:confList){
//					if(confBase!=null){
//						System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"-->> hostId"+confBase.getCompereUser()+"-->> hostId"+confBase.getCreateUser()+"--->>StartTime="+confBase.getStartTime());
//					}
//				}
//			}
//		}
//		
//
//		for(int ii=1;ii<pageCount;ii++){
//			System.out.println("-------Actor----------------------->>>>>>"+ii);
//			comingDate=confService.getMonthlyComingConfListForActor(null, userBase, ii,null,null);//(userBase, ii);
//			confList=comingDate.getDatas();
//			if(confList!=null && confList.size() > 0){
//				for(ConfBase confBase:confList){
//					if(confBase!=null){
//						System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"-->> hostId"+confBase.getCompereUser()+"-->> hostId"+confBase.getCreateUser()+"--->>StartTime="+confBase.getStartTime());
//					}
//					
//				}
//			}
//		}
//		
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 2);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				
//			}
//		}
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 3);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				
//			}
//		}
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 4);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				
//			}
//		}
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 5);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				
//			}
//		}
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 6);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				
//			}
//		}
//		comingDate=confService.getMonthlyComingConfListForHost(userBase, 7);
//		confList=comingDate.getDatas();
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					System.out.println("confId="+confBase.getId()+"--->>confName="+confBase.getConfName()+"--->>StartTime="+confBase.getStartTime());
//				}
//				http://int.confcloud.cn/?joinUrl=am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0zNjI2JnNjb2RlPTU2MjE5NjExNiZ1SWQ9MA==
//			}
//		}
//		System.out.println(comingDate.getDatas().size());
 
		
//		String base64="am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0zNjI2JnNjb2RlPTU2MjE5NjExNiZ1SWQ9MA==";
		//am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0zNjM3JnNjb2RlPSZ1SWQ9MA==
//http://thtfpc.confcloud.cn/?joinUrl=am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0zNjM3JnNjb2RlPSZ1SWQ9MA==
//		System.out.println(Base64.decode(base64));
		//http://int.confcloud.cn/?joinUrl=am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0zNjI2JnNjb2RlPSZ1SWQ9MA==
//		
//		
//				System.out.println(Base64.decode("aHR0cDovL3d3dy5jb25mY2xvdWQuY24vb3BlbmFwaS9DbGllbnRBUEkjaHR0cDovL3d3dy5jb25mY2xvdWQuY24vZG93bmxvYWQjMiMxIzQ="));
//		System.out.println(Base64.decode("am9pbi9qb2lucGFnZT9qb2luVHlwZT0zJmNJZD0yMCZzY29kZT02NDIwMzAzNDAmdUlkPTA="));
//		
//		ConfService confService = AppContextFactory.getAppContext().getBean(ConfService.class);
//		ConfManagementService confManageService=AppContextFactory.getAppContext().getBean(ConfManagementService.class);
//		ConfBase conf=confService.getConfBasebyConfId(744);
////		confManageService.queryConfInfo(conf.getConfHwid(), null, null);
//		confService.syncConfStatus(conf);
//		
//		//confManageService.
//		
////		System.out.println(empowerConfigService.getSiteEmpowerConfigBySiteId(27));
//		confService=null;
//		EmpowerConfigService empowerConfigService = AppContextFactory.getAppContext().getBean(EmpowerConfigService.class);
//		EmpowerConfig empowerConfig = new EmpowerConfig();
//		empowerConfig.setSiteId(48);
//		empowerConfig.setUserId(-1);
//		empowerConfig.setPhoneFlag(1);
//		empowerConfig.setAutoFlag(1);
//		empowerConfig.setRecordFlag(0);
//		empowerConfig.setShareMediaFlag(0);
//		empowerConfig.setEmTime(com.bizconf.vcaasz.util.DateUtil.getGmtDate(null));
//		empowerConfig.setEmUser(1);
//		empowerConfig.setEmUserType(9999);
////		empowerConfigService.saveSiteEmpowerGlobal(empowerConfig);
//		ConfService confService=AppContextFactory.getAppContext().getBean(ConfService.class);
//		ConfManagementService confManageService=AppContextFactory.getAppContext().getBean(ConfManagementService.class);
//		SiteService siteService=AppContextFactory.getAppContext().getBean(SiteService.class);
//		UserService userService=AppContextFactory.getAppContext().getBean(UserService.class);
//		
//		//取即将召开、正在召开会议列表
//		List<ConfBase> confList=confService.getConfListForTask();
//		
//		List<ConfBase> openingList=new ArrayList<ConfBase>();//正在召开的会议列表
//		List<ConfBase> comingList=new ArrayList<ConfBase>();//即将开始的会议列表
//		
//		if(confList!=null && confList.size() > 0){
//			for(ConfBase confBase:confList){
//				if(confBase!=null){
//					if(ConfConstant.CONF_STATUS_OPENING.equals(confBase.getConfStatus())){
//						openingList.add(confBase);
//					}
//					if(ConfConstant.CONF_STATUS_SUCCESS.equals(confBase.getConfStatus())){
//						comingList.add(confBase);
//					}
//				}
//			}
//		}
//		confList=null;
//		
//		SiteBase siteBase=null;
//		UserBase userBase=null;
//		if(openingList != null && openingList.size() > 0){
//			for(ConfBase confBase:openingList){
//				if(confBase != null){
//					siteBase=siteService.getSiteBaseById(confBase.getSiteId());
//					userBase=userService.getUserBaseById(confBase.getCreateUser());
//					confBase=confManageService.queryConfInfo(confBase.getId()+"", siteBase, userBase);
//					if(ConfConstant.CONF_STATUS_FINISHED.equals(confBase.getConfStatus())){
//						//更新会议状态
//						confService.updateConfStatus(confBase, ConfConstant.CONF_STATUS_FINISHED);
//						//查找会议的与会人数
//						
//					}
//				}
//			}
//		}
//		if(comingList != null && comingList.size() > 0){
//			for(ConfBase confBase:openingList){
//				if(confBase != null){
//					siteBase=siteService.getSiteBaseById(confBase.getSiteId());
//					userBase=userService.getUserBaseById(confBase.getCreateUser());
//					confBase=confManageService.queryConfInfo(confBase.getId()+"", siteBase, userBase);
//					if(ConfConstant.CONF_STATUS_OPENING.equals(confBase.getConfStatus())){
//						//更新会议状态
//						confService.updateConfStatus(confBase, ConfConstant.CONF_STATUS_OPENING);
//					}
//				}
//			}
//		}
//
//		System.out.println(openingList);
//		System.out.println(comingList);
//		comingList=null;
//		openingList=null;
//		confService=null;
//		confManageService=null;
//		siteService=null;
//		userService=null;
//	}
	}
	
	
	private static String string2Unicodes(String str) {
		StringBuilder unicodes = new StringBuilder();
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		for (char c : str.toCharArray()) {
			unicodes.append((int) c + ",");
		}
		return unicodes.deleteCharAt(unicodes.length() - 1).toString();
	}

}
